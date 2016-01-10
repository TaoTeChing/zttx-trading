package com.zttx.web.module.common.service;

import com.zttx.pay.remoting.exception.RemotingException;
import com.zttx.pay.remoting.model.TransferObj;
import com.zttx.pay.remoting.model.TransferReturnObj;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.model.TransferModel;

import java.util.List;

/**
 * 支付
 * <p>File：PayApiService.java</p>
 * <p>Title: PayApiService</p>
 * <p>Description:PayApiService</p>
 * <p>Copyright: Copyright (c) Jul 7, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public interface PayApiService
{

    /**
     * 品牌商总账退款
     * @author 易永耀
     * @param brandId  退款的品牌商Id
     * @param transferModel  包含推给经销商的Id
     * @throws BusinessException
     */
    void executeRefund(String brandId,TransferModel transferModel)throws BusinessException;

	/**
	 * 转账
	 * @author 陈建平
	 * @param brandId
	 * @param dealerId
	 * @param transferModel
	 * @throws BusinessException
	 */
    void executeTransfer(String brandId,String dealerId,TransferModel transferModel) throws BusinessException;

    /**
     * 经销商批量转账（用于经销商的品牌商财务帐  支付当期应付款）
     * @param dealerId
     * @param transferModelList
     * @throws BusinessException
     * @author 易永耀
     */
    void executeTransfer(String dealerId ,List<TransferModel> transferModelList) throws BusinessException;
    
    /**
     * 退押金
     * @author 陈建平
     * @param brandId
     * @param transferModel
     * @param isStop 是否终止合作
     * @throws BusinessException
     * @return
     */
    String executeBrandDepositTransfer(String brandId, TransferModel transferModel,boolean isStop) throws BusinessException;
    
    /**
     * 缴押金
     * @author 陈建平
     * @param dealerId
     * @param transferModel
     * @throws BusinessException
     * @return
     */
    String executeDealerDepositTransfer(String dealerId, TransferModel transferModel) throws BusinessException;
    
    /**
     * 解冻
     * @author chenjp
     * @param refrenceId
     * @param operateUserId
     * @param operateUserName
     */
    void updateDepositBackStatus(String refrenceId, String operateUserId, String operateUserName) throws BusinessException;

    /**
     * 财务帐付款
     * @param password
     * @param dealerId
     * @param transferObjList
     * @return
     */
    List<TransferReturnObj> executeTransferBatch(String password, String dealerId, List<TransferObj> transferObjList) throws BusinessException, RemotingException;
}
