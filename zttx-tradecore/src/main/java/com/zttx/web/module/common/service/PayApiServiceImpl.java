package com.zttx.web.module.common.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.zttx.pay.common.consts.BusiTypeConsts;
import com.zttx.pay.remoting.api.PayOrderApi;
import com.zttx.pay.remoting.api.PayUserApi;
import com.zttx.pay.remoting.crm.TransferApi;
import com.zttx.pay.remoting.exception.RemotingException;
import com.zttx.pay.remoting.model.PayAccount;
import com.zttx.pay.remoting.model.PayOrderDetails;
import com.zttx.pay.remoting.model.TransferObj;
import com.zttx.pay.remoting.model.TransferReturnObj;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.RedissonUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.DepositBack;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.MessageHistoryMapper;
import com.zttx.web.module.common.model.TransferModel;
import com.zttx.web.module.dealer.entity.DealerClearing;
import com.zttx.web.module.dealer.entity.DealerClearingPay;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerClearingMapper;
import com.zttx.web.module.dealer.service.DealerClearingPayService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 支付
 * <p>File：PayApiServiceImpl.java</p>
 * <p>Title: PayApiServiceImpl</p>
 * <p>Description:PayApiServiceImpl</p>
 * <p>Copyright: Copyright (c) Jul 7, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 陈建平
 * @version 1.0
 */
@Service
public class PayApiServiceImpl implements PayApiService {
    private final static Logger logger = LoggerFactory.getLogger(PayApiServiceImpl.class);

    @Autowired
    private TransferApi transferApi;

    @Autowired
    private PayOrderApi payOrderApi;

    @Autowired
    private PayUserApi payUserApi;

    @Autowired
    private BrandInfoService brandInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DealerInfoService dealerInfoService;

    @Autowired
    private MessageHistoryMapper messageHistoryMapper;

    @Autowired
    private TextMessageSender textMessageSender;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private DealerJoinService dealerJoinService;

    @Autowired
    private DepositBackService depositBackService;

    @Autowired
    private BrandesInfoService brandesInfoService;

    @Autowired
    private DealerClearingMapper dealerClearingMapper;

    @Autowired
    private DealerClearingPayService dealerClearingPayService;

    private static Object obj = new Object();

    private static Object objTransfer = new Object();


    /**
     * 经销商批量转账（用于经销商的品牌商财务帐  支付当期应付款）
     *
     * @param dealerId
     * @param transferModelList
     * @throws BusinessException
     * @author 易永耀
     */
    @Override
    public void executeTransfer(String dealerId, List<TransferModel> transferModelList) throws BusinessException {
        if (StringUtils.isNotBlank(dealerId) && !transferModelList.isEmpty()) {
            for (TransferModel transferModel : transferModelList) {
                transferModel.setType(2);
                RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(transferModel.hashCode()));
                try {
                    lock.lock();
                    this.addOrUpdate(dealerId, transferModel);
                    this.executeTransfer(null, dealerId, transferModel);
                } catch (Exception e) {
                    throw new BusinessException("经销商批量转账失败！");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度,生成支付记录
    private void addOrUpdate(String dealerId, TransferModel transferModel) {
        DealerClearing dealerClearing = new DealerClearing();
        dealerClearing.setDealerId(dealerId);
        dealerClearing.setBrandId(transferModel.getToUserId());
        dealerClearing.setClearingStatus(true);
        List<DealerClearing> dealerClearingList = dealerClearingMapper.selectDealerClearingNoPayedByDealerIdAndBrandId(dealerClearing);
        for (DealerClearing clearing : dealerClearingList) {
            DealerJoin dealerJoin = dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(clearing.getDealerId(), clearing.getBrandsId());
            dealerJoin.setCreditCurrent(dealerJoin.getCreditCurrent().subtract(clearing.getClearingAmount()));
            dealerJoin.setClearingAmount(BigDecimal.ZERO); // 付款后当期应付款摊到授信中的额度已经付款，并且是全额付款
            dealerJoinService.updateByPrimaryKey(dealerJoin);
        }
        dealerClearingMapper.updateDealerClearingBy(dealerClearing);
        // 当期支付记录
        DealerClearingPay dealerClearingPay = new DealerClearingPay();
        dealerClearingPay.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerClearingPay.setDealerId(dealerId);
        dealerClearingPay.setBrandId(transferModel.getToUserId());
        dealerClearingPay.setPayClearingAmount(transferModel.getAmount());
        dealerClearingPay.setDelFlag(false);
        dealerClearingPay.setCreateTime(CalendarUtils.getCurrentLong());
        dealerClearingPayService.insert(dealerClearingPay);
    }


    /**
     * 普通转账接口,-返还佣金
     *
     * @param title        划账简单说明
     * @param sourceUser   划出方用户 ID
     * @param targetUser   划入方用户 ID
     * @param amount       金额
     * @param exId         流水
     * @param fromUserName 出款方名称
     * @param toUserName   收款方名称
     * @param type         业务类型 {@link com.zttx.pay.common.consts.BusiTypeConsts}
     * @param backFee      返还的佣金 不收取时为 请用 {@link BigDecimal#ZERO} 表示
     * @param feeProvider  佣金提供帐号 不收取佣金时请用 null
     * @return {@link com.zttx.pay.remoting.model.PayOrderDetails}
     */
    @Override
    public void executeRefund(String brandId, TransferModel transferModel) throws BusinessException {
        if (StringUtils.isNotBlank(brandId)) {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            if (null == brandInfo) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "品牌商不存在");
            }
            Long userId = userInfoService.executeFindPayUserId(brandId);
            if (null == userId) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "未开通支付平台");
            }
            transferModel.setSourceUser(userId);
            transferModel.setTargetUser(userInfoService.executeFindPayUserId(transferModel.getToUserId()));
            transferModel.setFromUserName(brandInfo.getComName());
            try {
                PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
                if (payAccount.getBalance().compareTo(transferModel.getAmount()) < 0) {
                    throw new BusinessException(CommonConst.FAIL, "帐户可用余额不足，无法转账");
                }
                // 品牌商退款 平台返回给品牌商的扣点金额
                BigDecimal pointAmount = transferModel.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("0.03"))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
                // 流水号
                String exId = SerialNumberUtil.getSerialNumber18();
                payOrderApi.validatePaymentPwd(transferModel.getSourceUser(), transferModel.getPayPwd());
                //加锁
                RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(transferModel.hashCode()));
                try {
                    lock.lock();
                    transferApi.transferBackFee(transferModel.getTitle(), transferModel.getSourceUser(), transferModel.getTargetUser(), transferModel.getAmount(),
                            Long.parseLong(exId), transferModel.getFromUserName(), transferModel.getToUserName(), BusiTypeConsts.TYPE_REFUND, pointAmount,
                            CommonConstant.OrderPay.PAY_MERCHANT_ID);
                } catch (Exception e) {
                    throw new BusinessException("经销商总帐转账失败！");
                } finally {
                    lock.unlock();
                }
            } catch (RemotingException e) {
                logger.error("调用支付系统接口失败：", e);
                throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
            } catch (com.zttx.pay.common.exception.BusinessException e) {
                throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
            }
        }
    }

    /**
     * 转账
     *
     * @param brandId       当brandId不为空时 为品牌商给终端商转账
     * @param dealerId      当dealerId不为空时 为终端商给品牌商转账
     * @param transferModel type = 1 会验证是否合作  type = 2 不会验证是否合作
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public void executeTransfer(String brandId, String dealerId, TransferModel transferModel) throws BusinessException {
        synchronized (objTransfer) {
            Long userId = null;
            if (StringUtils.isNotBlank(brandId)) {
                BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
                if (null == brandInfo) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "品牌商不存在");
                }
                userId = userInfoService.executeFindPayUserId(brandId);
                transferModel.setSourceUser(userId);
                transferModel.setTargetUser(userInfoService.executeFindPayUserId(transferModel.getToUserId()));
                transferModel.setFromUserName(brandInfo.getComName());
                if (transferModel.getType() == 1) {
                    if (StringUtils.isBlank(transferModel.getDealerJoinId())) {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "双方未合作，无法转账");
                    }
                    DealerJoin dealerJoin = dealerJoinService.selectByPrimaryKey(transferModel.getDealerJoinId());
                    if (null == dealerJoin || dealerJoin.getJoinState() != DealerConstant.DealerJoin.COOPERATED) {
                        throw new BusinessException(CommonConst.FAIL.getCode(),
                                "双方未合作，无法转账");
                    }
                }
            } else if (StringUtils.isNotBlank(dealerId)) {
                DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
                if (null == dealerInfo) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "终端商不存在");
                }
                userId = userInfoService.executeFindPayUserId(dealerId);
                transferModel.setSourceUser(userId);
                transferModel.setTargetUser(userInfoService.executeFindPayUserId(transferModel.getToUserId()));
                transferModel.setFromUserName(dealerInfo.getDealerName());
                if (transferModel.getType() == 1) {
                    if (StringUtils.isBlank(transferModel.getDealerJoinId())) {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "双方未合作，无法转账");
                    }
                    DealerJoin dealerJoin = dealerJoinService.selectByPrimaryKey(transferModel.getDealerJoinId());
                    if (null == dealerJoin || dealerJoin.getJoinState() != DealerConstant.DealerJoin.COOPERATED) {
                        throw new BusinessException(CommonConst.FAIL.getCode(),
                                "双方未合作，无法转账");
                    }
                }
            }
            if (null == userId) {
                throw new BusinessException(CommonConst.FAIL, "未开通支付平台");
            }
            try {
                PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
                if (payAccount.getBalance().compareTo(transferModel.getAmount()) < 0) {
                    throw new BusinessException(CommonConst.FAIL, "帐户可用余额不足，无法转账");
                }
                // 流水号
                String exId = SerialNumberUtil.getSerialNumber18();
                payOrderApi.validatePaymentPwd(transferModel.getSourceUser(), transferModel.getPayPwd());
                PayOrderDetails payOrderDetails = null;
                if (transferModel.getType() == 2) // 支付当期应付款，需要扣除佣金，暂时定为 0.03 的扣点
                {
                    BigDecimal pointAmount = transferModel.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("0.03"))
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                    payOrderDetails = transferApi.transferWithFee(transferModel.getTitle(), transferModel.getSourceUser(), transferModel.getTargetUser(),
                            transferModel.getAmount(), Long.parseLong(exId), transferModel.getFromUserName(), transferModel.getToUserName(),
                            com.zttx.pay.common.consts.BusiTypeConsts.TYPE_TRANSFER, pointAmount, CommonConstant.OrderPay.PAY_MERCHANT_ID, null);
                } else {
                    payOrderDetails = transferApi.transferWithFee(transferModel.getTitle(), transferModel.getSourceUser(), transferModel.getTargetUser(),
                            transferModel.getAmount(), Long.parseLong(exId), transferModel.getFromUserName(), transferModel.getToUserName(),
                            com.zttx.pay.common.consts.BusiTypeConsts.TYPE_TRANSFER, BigDecimal.ZERO, null, null);
                }
                if (null != payOrderDetails && null != payOrderDetails.getId()) {
                    if (StringUtils.isNotBlank(transferModel.getToUserMobile())) {
                        SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_TRANSFER_MONEY);
                        if (null != smsTemplate && StringUtils.isNotEmpty(smsTemplate.getContent())) {
                            String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()),
                                    transferModel.getFromUserName(), transferModel.getAmount());
                            SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, transferModel.getToUserMobile(), smsContent);
                            new Thread(sendSmsClient).start();
                        }
                    }
                } else {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "转账失败，请重试");
                }
            } catch (RemotingException e) {
                logger.error("调用支付系统接口失败：", e);
                throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
            } catch (com.zttx.pay.common.exception.BusinessException e) {
                throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
            }
        }
    }

    /**
     * 退押金
     *
     * @param brandId
     * @param transferModel
     * @param isStop        是否终止合作
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public String executeBrandDepositTransfer(String brandId, TransferModel transferModel, boolean isStop) throws BusinessException {
        synchronized (obj) {
            Long userId = null;
            if (null != transferModel && StringUtils.isBlank(transferModel.getDealerJoinId())) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "请选择要终止合作的终端商");
            }
            DealerJoin dealerJoin = dealerJoinService.selectByPrimaryKey(transferModel.getDealerJoinId());
            if (null == dealerJoin || dealerJoin.getJoinState() != DealerConstant.DealerJoin.COOPERATED) {
                throw new BusinessException(CommonConst.FAIL.getCode(),
                        "双方未合作，无法转账");
            }
            if (null == transferModel || null == transferModel.getAmount() || transferModel.getAmount().compareTo(new BigDecimal(0)) <= 0) {
                throw new BusinessException(
                        CommonConst.FAIL.getCode(), "请输入要退押金金额");
            }
            String exId = "";
            if (null != dealerJoin.getPaidAmount()) {
                if (isStop) {
                    if (transferModel.getAmount().compareTo(dealerJoin.getPaidAmount()) > 0) {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "所退押金金额不能大于已付押金金额");
                    }
                    if (dealerJoin.getRefundAmount() != null && dealerJoin.getRefundAmount().compareTo(new BigDecimal(0)) > 0) {
                        throw new BusinessException(
                                CommonConst.FAIL.getCode(), "已经退过押金了");
                    }
                } else {
                    BigDecimal depositTotalAmount = dealerJoin.getDepositTotalAmount() == null ? new BigDecimal(0) : dealerJoin.getDepositTotalAmount();
                    BigDecimal paidAmount = dealerJoin.getPaidAmount();
                    BigDecimal refundAmount = depositTotalAmount.subtract(paidAmount);
                    if (refundAmount.compareTo(new BigDecimal(0)) < 0) {
                        if (transferModel.getAmount().compareTo(refundAmount.abs()) > 0) {
                            throw new BusinessException(CommonConst.FAIL.getCode(), "所退押金金额不能超过待退押金金额");
                        }
                    } else {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "不需要退押金，请刷新页面");
                    }
                }
                BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
                userId = userInfoService.executeFindPayUserId(brandId);
                transferModel.setSourceUser(userId);
                transferModel.setFromUserName(brandInfo.getComName());
                if (null == userId) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "未开通支付平台");
                }
                DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerJoin.getDealerId());
                UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerJoin.getDealerId());
                Long toUserId = userInfoService.executeFindPayUserId(dealerInfo.getRefrenceId());
                transferModel.setTargetUser(toUserId);
                transferModel.setToUserName(dealerInfo.getDealerName());
                transferModel.setToUserMobile(userInfo.getUserMobile());
                BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
                if (null == brandesInfo) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "合作的品牌不存在");
                }
                if (null == toUserId) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "对方未开通支付平台");
                }
                try {
                    PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
                    if (payAccount.getBalance().compareTo(transferModel.getAmount()) < 0) {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "帐户可用余额不足，无法转账");
                    }
                    // 流水号
                    exId = SerialNumberUtil.getSerialNumber18();
                    payOrderApi.validatePaymentPwd(transferModel.getSourceUser(), transferModel.getPayPwd());
                    PayOrderDetails payOrderDetails = transferApi.transferWithFreeze(transferModel.getTitle(), transferModel.getSourceUser(),
                            transferModel.getTargetUser(), transferModel.getAmount(), Long.parseLong(exId), transferModel.getFromUserName(), transferModel.getToUserName());
                    if (null != payOrderDetails && null != payOrderDetails.getId()) {
                        if (isStop) {
                            dealerJoin.setRefundAmount(transferModel.getAmount());
                        } else {
                            dealerJoin.setPaidAmount(dealerJoin.getPaidAmount().subtract(transferModel.getAmount()));
                            if (dealerJoin.getPaidAmount().compareTo(dealerJoin.getDepositTotalAmount()) >= 0) {
                                dealerJoin.setIsPaid(true);
                            }
                        }
                        dealerJoinService.updateByPrimaryKey(dealerJoin);
                        // 保存转账记录
                        DepositBack depositBack = new DepositBack();
                        depositBack.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        depositBack.setExId(exId);
                        depositBack.setBillId(payOrderDetails.getId());
                        depositBack.setBackAmount(transferModel.getAmount());
                        depositBack.setDepositBackTime(CalendarUtils.getCurrentLong());
                        depositBack.setDealerId(dealerInfo.getRefrenceId());
                        depositBack.setDealerName(dealerInfo.getDealerName());
                        depositBack.setBrandId(brandId);
                        depositBack.setBrandName(brandInfo.getComName());
                        depositBack.setBrandsId(dealerJoin.getBrandsId());
                        depositBack.setBrandsName(brandesInfo.getBrandsName());
                        depositBack.setDealerjoinId(dealerJoin.getRefrenceId());
                        depositBack.setPaidAmount(dealerJoin.getPaidAmount());
                        depositBack.setStatus((short) 0);
                        if (isStop) {
                            depositBack.setType((short) 0);
                        } else {
                            depositBack.setType((short) 1);
                        }
                        depositBackService.insert(depositBack);
                        if (isStop) {
                            // 终止合作
                            dealerJoinService.updateStopBrandJoinState(dealerJoin, transferModel.getDesc());
                        }
                        // 发送短信
                        if (StringUtils.isNotBlank(transferModel.getToUserMobile())) {
                            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_REFUND_TRANSFER_MONEY);
                            if (null != smsTemplate && StringUtils.isNotEmpty(smsTemplate.getContent())) {
                                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()),
                                        transferModel.getFromUserName(), transferModel.getAmount());
                                SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, transferModel.getToUserMobile(), smsContent);
                                new Thread(sendSmsClient).start();
                            }
                        }
                    } else {
                        throw new BusinessException(CommonConst.FAIL.getCode(), "转账失败，请重试");
                    }
                } catch (RemotingException e) {
                    logger.error("调用支付系统接口失败：", e);
                    throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
                } catch (com.zttx.pay.common.exception.BusinessException e) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
                }
            } else {
                throw new BusinessException(CommonConst.FAIL.getCode(), "终端商未缴纳押金，无法操作");
            }
            return exId;
        }
    }

    /**
     * 缴押金
     *
     * @param dealerId
     * @param transferModel
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public String executeDealerDepositTransfer(String dealerId, TransferModel transferModel) throws BusinessException {
        Long userId = null;
        if (null != transferModel && StringUtils.isBlank(transferModel.getDealerJoinId())) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "请选择要缴纳押金的品牌商");
        }
        DealerJoin dealerJoin = dealerJoinService.selectByPrimaryKey(transferModel.getDealerJoinId());
        if (null == dealerJoin || dealerJoin.getJoinState() != DealerConstant.DealerJoin.COOPERATED) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "双方未合作，无法转账");
        }
        String exId = "";
        if (null != transferModel.getAmount() && transferModel.getAmount().compareTo(new BigDecimal(0)) > 0) {
            BigDecimal totalPaidAmount = new BigDecimal(0);
            if (dealerJoin.getPaidAmount() != null) {
                totalPaidAmount = dealerJoin.getPaidAmount().add(transferModel.getAmount());
            } else {
                totalPaidAmount = transferModel.getAmount();
            }
            if (totalPaidAmount.compareTo(dealerJoin.getDepositTotalAmount()) > 0) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "已缴押金总金额不能大于品牌商给终端商设定的押金额度");
            }
            DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
            userId = userInfoService.executeFindPayUserId(dealerInfo.getRefrenceId());
            transferModel.setSourceUser(userId);
            transferModel.setFromUserName(dealerInfo.getDealerName());
            if (null == userId) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "未开通支付平台");
            }
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerJoin.getBrandId());
            UserInfo userInfo = userInfoService.selectByPrimaryKey(brandInfo.getRefrenceId());
            Long toUserId = userInfoService.executeFindPayUserId(dealerJoin.getBrandId());
            transferModel.setTargetUser(toUserId);
            transferModel.setToUserName(brandInfo.getComName());
            transferModel.setToUserMobile(userInfo.getUserMobile());
            BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
            if (null == brandesInfo) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "合作的品牌不存在");
            }
            if (null == toUserId) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "对方未开通支付平台");
            }
            try {
                PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
                if (payAccount.getBalance().compareTo(transferModel.getAmount()) < 0) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "帐户可用余额不足，无法转账");
                }
                // 流水号
                exId = SerialNumberUtil.getSerialNumber18();
                payOrderApi.validatePaymentPwd(transferModel.getSourceUser(), transferModel.getPayPwd());
                PayOrderDetails payOrderDetails = transferApi.transferWithFreeze(transferModel.getTitle(), transferModel.getSourceUser(), transferModel.getTargetUser(),
                        transferModel.getAmount(), Long.parseLong(exId), transferModel.getFromUserName(), transferModel.getToUserName());
                if (null != payOrderDetails && null != payOrderDetails.getId()) {
                    dealerJoin.setPaidAmount(totalPaidAmount);
                    if (totalPaidAmount.compareTo(dealerJoin.getDepositTotalAmount()) == 0) {
                        dealerJoin.setIsPaid(true);
                    }
                    dealerJoinService.updateByPrimaryKey(dealerJoin);
                    // 保存转账记录
                    DepositBack depositBack = new DepositBack();
                    depositBack.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    depositBack.setExId(exId);
                    depositBack.setBillId(payOrderDetails.getId());
                    depositBack.setBackAmount(transferModel.getAmount());
                    depositBack.setDepositBackTime(CalendarUtils.getCurrentLong());
                    depositBack.setDealerId(dealerInfo.getRefrenceId());
                    depositBack.setDealerName(dealerInfo.getDealerName());
                    depositBack.setBrandId(dealerJoin.getBrandId());
                    depositBack.setBrandName(brandInfo.getComName());
                    depositBack.setBrandsId(dealerJoin.getBrandsId());
                    depositBack.setBrandsName(brandesInfo.getBrandsName());
                    depositBack.setDealerjoinId(dealerJoin.getRefrenceId());
                    depositBack.setPaidAmount(dealerJoin.getPaidAmount());
                    depositBack.setType((short) 2);
                    depositBack.setStatus((short) 0);
                    depositBackService.insert(depositBack);
                    // 发送短信
                    if (StringUtils.isNotBlank(transferModel.getToUserMobile())) {
                        SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_REFUND_TRANSFER_MONEY);
                        if (null != smsTemplate && StringUtils.isNotEmpty(smsTemplate.getContent())) {
                            String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()),
                                    transferModel.getFromUserName(), transferModel.getAmount());
                            SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, transferModel.getToUserMobile(), smsContent);
                            new Thread(sendSmsClient).start();
                        }
                    }
                } else {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "转账失败，请重试");
                }
            } catch (RemotingException e) {
                logger.error("调用支付系统接口失败：", e);
                throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
            } catch (com.zttx.pay.common.exception.BusinessException e) {
                throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
            }
        } else {
            throw new BusinessException(CommonConst.FAIL.getCode(), "缴纳押金金额必须大于零");
        }
        return exId;
    }

    /**
     * 解冻
     *
     * @param refrenceId
     * @param operateUserId
     * @param operateUserName
     * @author chenjp
     */
    @Override
    public void updateDepositBackStatus(String refrenceId, String operateUserId, String operateUserName) throws BusinessException {
        DepositBack depositBack = depositBackService.selectByPrimaryKey(refrenceId);
        if (null == depositBack) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "记录不存在");
        }
        if (depositBack.getStatus() == (short) 0) {
            if (StringUtils.isBlank(operateUserId) || StringUtils.isBlank(operateUserName)) {
                throw new BusinessException(CommonConst.FAIL.getCode(), "操作人ID或者操作人姓名不能为空");
            }
            try {
                PayOrderDetails payOrderDetails = transferApi.transferWithUnfreeze(depositBack.getBillId(), depositBack.getBackAmount(), operateUserName);
                if (null == payOrderDetails) {
                    throw new BusinessException(CommonConst.FAIL.getCode(), "调用支付系统接口失败");
                }
            } catch (com.zttx.pay.common.exception.BusinessException ex) {
                throw new BusinessException(CommonConst.FAIL.getCode(), ex.getMessage());
            }
            depositBack.setStatus((short) 1);
            depositBack.setOperateTime(CalendarUtils.getCurrentLong());
            depositBack.setOperateUserId(operateUserId);
            depositBack.setOperateUserName(operateUserName);
            depositBackService.updateByPrimaryKey(depositBack);
        }
    }

    /**
     * 经销商财务帐 支付
     *
     * @param password
     * @param dealerId
     * @param transferObjList
     * @return
     * @throws BusinessException
     * @throws RemotingException
     */
    @Override
    public List<TransferReturnObj> executeTransferBatch(String password, String dealerId, List<TransferObj> transferObjList) throws BusinessException, RemotingException {

        List<TransferReturnObj> transferReturnObjList = null;
        if (StringUtils.isBlank(password) || StringUtils.isBlank(dealerId) || !ListUtils.isNotEmpty(transferObjList)) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常：财务帐付款参数异常");
        }
        Long sourceUser = userInfoService.executeFindPayUserId(dealerId);
        if (null == sourceUser) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "经销商未开通支付平台");
        }
        BigDecimal AllpayAmount = BigDecimal.ZERO;
        for (int i = 0, length = transferObjList.size(); i < length; i++) {
            TransferObj transferObj = transferObjList.get(i);
            if (null == transferObj || null == transferObj.getTargetUser()) {
                throw new BusinessException(CommonConst.PARAM_NULL);
            }
            AllpayAmount = AllpayAmount.add(transferObj.getAmount());
        }
        PayAccount payAccount = payUserApi.loadPayAccountByUserId(sourceUser);
        if (payAccount.getBalance().subtract(AllpayAmount).compareTo(BigDecimal.ZERO) == -1) {
            throw new BusinessException(CommonConst.FAIL.getCode(), "支付异常：支付余额不足");
        }
        payOrderApi.validatePaymentPwd(sourceUser, password);  //支付密码校验
        try {
            transferReturnObjList = transferApi.transferWithFeeBatch(transferObjList);
        } catch (Exception e) {
            LoggerUtils.logError(logger, "调用异常:调用结算平台transferApi.transferWithFeeBatch()异常" + e.getLocalizedMessage());
        }
        if (ListUtils.isNotEmpty(transferReturnObjList)) {

            Map<String, TransferObj> transferObjMap = Maps.uniqueIndex(transferObjList, new Function<TransferObj, String>() {
                @Override
                public String apply(TransferObj transferObj) {
                    return transferObj.getExId().toString();
                }
            });
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_TRANSFER_MONEY);
            if (null != smsTemplate && StringUtils.isNotEmpty(smsTemplate.getContent())) {
                for (int i = 0, length = transferReturnObjList.size(); i < length; i++) {
                    TransferReturnObj transferReturnObj = transferReturnObjList.get(i);
                    if (!transferReturnObj.isSuccess()) {
                        continue;
                    }
                    TransferObj transferObj = transferObjMap.get(transferReturnObj.getExId());
                    UserInfo userInfo = userInfoService.selectByPrimaryKey(transferObj.getTargetUser().toString());
                    String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), transferObj.getFromUserName(), transferObj.getAmount());
                    SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, userInfo.getUserMobile(), smsContent);
                    new Thread(sendSmsClient).start();
                }
            }
        }
        return transferReturnObjList;
    }
}
