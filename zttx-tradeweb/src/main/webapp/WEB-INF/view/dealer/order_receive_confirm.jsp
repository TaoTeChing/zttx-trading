<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>确认收货</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/cart.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/alreas.css" rel="stylesheet" />
    <style>
        .reconfirm-box{border: 1px solid #DDDDDD;padding: 20px;}
        .reconfirm-box .tip{font-weight: bold;;}
        .reconfirm-box .tip strong{color: #cc0000;}
        .reconfirm-box .ui-form-item{padding-bottom: 12px;;}
        .reconfirm-box .ui-form{position: relative;left: -90px;}
        .reconfirm-box .ui-form .ui-button-mred{line-height: normal;}
        .code_btn{border: none;height: 34px;padding: 0 10px;border: #b8b5b4 solid 1px;border-radius: 3px;background: #f5f5f5;font: 14px/26px "微软雅黑";display: inline-block;*display: inline;*zoom: 1;*overflow: visible;vertical-align: top;cursor: pointer;line-height: normal;color: #999;}
        .code_btn:hover{background-color: #eee;color: #333;}
        .code_btn_loading{padding-left: 26px;background: url("/images/common/loading-16-16.gif") no-repeat 5px center;}
        #reconfirm_Form .ui-form-item{padding-left: 198px;}
        #alreadySendCode{padding: 5px 0 5px 20px;background: url("/images/common/right.png") left center no-repeat;color: #85c749;}
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->

                <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>

                <!--主体内容-->
                <div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> >> <a href="${ctx }/dealer/dealerOrder/order" title="">已进货的订单</a> > <span class="bb">订单状态</span>
                            <a class="panel-crumbs-help" href="${ctx }/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner clearfix" style="padding-top:5px;padding-left: 10px;padding-right: 10px;">
                        <div class="main-grid mb10">
                            <div class="panel-step">
                                <h3 class="fl yahei fs14 lh2">订单流程:</h3>
                                <!--流程样式 step1-4 -->
                                <div class="step step3"></div>
                            </div>
                        </div>
                        <div style="border:1px solid #ddd;padding:10px;" class="main-grid mb10 msyh ta-c fs16">
                            <i class="icon i-right-32" style="vertical-align: -8px"></i> 已收到货，同意付款给品牌商
                        </div>
                        <div class="main-grid mb10" id="print_Region">
                            <!--startprint-->
                            <div class="panel-table">
                                <div class="panel-table-title">
                                    <div class="panel-table-top clearfix ">
                                        <!--附加样式名: debt:欠款 advance:预付 -->
                                        <div class="fl"><span class="yahei fs16 ml10">订单信息：</span></div>
                                        <div style="float:right;" class="tools">
                                            <a href="javascript:;" class="close_btn print link ml5">收起</a>
                                            <a style="display: none;" href="javascript:;" class="open_btn print link ml5">展开</a>
                                        </div>
                                    </div>
                                    <div class="panel-table-bottom">
                                       <ul class="clearfix inline-float">
                                            <li><span>订单号:${dealerOrder.orderId}</span>
                                            </li>
                                            <c:set value="${fns:getBrandsIdSubDomain(dealerOrder.brandsId)}" var="domain"></c:set>
                                            <li><span>品牌:<a target="_blank" href='http://${domain}${zttx}'>${dealerOrder.brandsName}</a></span></li>
                                            <li><span>联系人:${brandContact.userName}</span>
                                            </li>
                                            <li><span>电话:${brandContact.userTelphone}</span>
                                            </li>
                                            <li><span>手机:${brandContact.userMobile}</span>
                                            </li>
                                            <li><span>所在地:${brandInfo.provinceName} ${brandInfo.cityName}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="panel-table-content clearfix">
                                    <table class="goods ui-table">
                                        <thead>
                                            <tr>
                                                <th>商品信息</th>
                                                <th width="110">颜色/尺码</th>
                                                <th width="110">单价</th>
                                                <th width="110">数量</th>
                                                <th width="110">金额</th>
                                                <th width="80">数量小计</th>
                                                <th width="80">金额小计</th>
                                            </tr>
                                        </thead>
                                        <tbody id="product-item">
                                        <!--商品1-->
                                        <c:set var="MaxTotalNum" value="0"></c:set>
                                        <c:set var="MaxTotalPrice" value="0"></c:set>
                                        <c:forEach items="${orderModels}" var="orderModel">
                                            <tr class="goods-item">
                                                <td class="goods-td goods-main-info cell-1">
                                                    <div class="js-img-center inline-block"
                                                         style="width:90px;height:90px;overflow:hidden"><a
                                                            target="_blank"
                                                            href="${ctx}/market/product/${orderModel.productId}"><img
                                                            style="width:100%" src="${res}${orderModel.productImage}"/></a>
                                                    </div>
                                                    <br/>
                                                    <a target="_blank"
                                                       href="${ctx}/market/product/${orderModel.productId}">
                                                        货号: ${orderModel.productNo} </a>
                                                </td>
                                                <td colspan="4" class="goods-td goods-attribute">
                                                    <table>
                                                        <tbody>
                                                        <c:set var="totalNum" value="0"></c:set>
                                                        <c:set var="totalPrice" value="0"></c:set>
                                                        <c:set var="allTotalPrice" value="0"></c:set>
                                                        <c:forEach items="${orderModel.itemsModels}" var="itemsModel">
                                                            <tr class="backf9">
                                                                <td class="goods-other-attribute" colspan="4">
                                                                    <div class="attributes">
                                                                        <table id="id1">
                                                                            <tbody>
                                                                            <tr>
                                                                                <td class="widthtit">${itemsModel.productSkuName}</td>
                                                                                <td class="widthtit"><!--价格-->
                                                                                    <c:choose>
                                                                                        <c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
                                                                                            <fmt:formatNumber
                                                                                                       value="${itemsModel.adjustPrice/itemsModel.quantity}"
                                                                                                       type="currency"
                                                                                                       pattern="0.00"/>
                                                                                            <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
                                                                                                       value="${itemsModel.price}"
                                                                                                       type="currency"
                                                                                                       pattern="0.00"/></s>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <c:choose>
                                                                                                <c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
                                                                                                    <c:choose>
                                                                                                        <c:when test="${itemsModel.oldPrice != null}"><!--有调价,则单价不用再计算折扣-->
                                                                                                            <fmt:formatNumber
                                                                                                                       value="${itemsModel.price}"
                                                                                                                       type="currency"
                                                                                                                       pattern="0.00"/>
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                            <fmt:formatNumber
                                                                                                                       value="${itemsModel.price*itemsModel.discount}"
                                                                                                                       type="currency"
                                                                                                                       pattern="0.00"/>
                                                                                                            <s style="color: #AAAAAA;margin-left: 5px;"><fmt:formatNumber
                                                                                                                       value="${itemsModel.price}"
                                                                                                                       type="currency"
                                                                                                                       pattern="0.00"/></s>
                                                                                                        </c:otherwise>
                                                                                                    </c:choose>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                    <fmt:formatNumber
                                                                                                               value="${itemsModel.price}"
                                                                                                               type="currency"
                                                                                                               pattern="0.00"/>
                                                                                                </c:otherwise>
                                                                                            </c:choose>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </td>
                                                                                <td class="widthtit">${itemsModel.quantity}</td>
                                                                                <td class="widthtit"><!--金额-->
                                                                                    <c:choose>
                                                                                        <c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
                                                                                            <fmt:formatNumber
                                                                                                       value="${itemsModel.adjustPrice}"
                                                                                                       type="currency"
                                                                                                       pattern="0.00"/>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <c:choose>
                                                                                                <c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
                                                                                                    <c:choose>
                                                                                                        <c:when test="${itemsModel.oldPrice != null}">
                                                                                                            <fmt:formatNumber
                                                                                                                       value="${itemsModel.price * itemsModel.quantity}"
                                                                                                                       type="currency"
                                                                                                                       pattern="0.00"/>
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                            <fmt:formatNumber
                                                                                                                       var="discountPrice"
                                                                                                                       value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}"
                                                                                                                       pattern="0.00"/>
                                                                                                            <fmt:formatNumber
                                                                                                                       value="${itemsModel.price * itemsModel.quantity - discountPrice}"
                                                                                                                       type="currency"
                                                                                                                       pattern="0.00"/>
                                                                                                        </c:otherwise>
                                                                                                    </c:choose>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                    <fmt:formatNumber
                                                                                                               value="${itemsModel.price * itemsModel.quantity}"
                                                                                                               type="currency"
                                                                                                               pattern="0.00"/>
                                                                                                </c:otherwise>
                                                                                            </c:choose>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </td>
                                                                                <c:set var="totalNum"
                                                                                       value="${totalNum+itemsModel.quantity}"></c:set>

                                                                                <c:choose>
                                                                                    <c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
                                                                                        <c:set var="totalPrice"
                                                                                               value="${totalPrice + itemsModel.adjustPrice.doubleValue()}"></c:set>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <c:choose>
                                                                                            <c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
                                                                                                <c:choose>
                                                                                                    <c:when test="${itemsModel.oldPrice != null}">
                                                                                                        <c:set var="totalPrice"
                                                                                                               value="${totalPrice + itemsModel.price  * itemsModel.quantity}"></c:set>
                                                                                                    </c:when>
                                                                                                    <c:otherwise>
                                                                                                        <fmt:formatNumber
                                                                                                                   var="discountPrice"
                                                                                                                   value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}"
                                                                                                                   pattern="0.00"/>
                                                                                                        <c:set var="totalPrice"
                                                                                                               value="${totalPrice + itemsModel.price * itemsModel.quantity - discountPrice}"></c:set>
                                                                                                    </c:otherwise>
                                                                                                </c:choose>
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                                <c:set var="totalPrice"
                                                                                                       value="${totalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                                <c:choose>
                                                                                    <c:when test="${itemsModel.adjustPrice != null && itemsModel.adjustPrice.doubleValue() >= 0}">
                                                                                        <c:set var="allTotalPrice"
                                                                                               value="${allTotalPrice + itemsModel.adjustPrice}"></c:set>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <c:choose>
                                                                                            <c:when test="${itemsModel.discount != null && itemsModel.discount.doubleValue() != 0 && itemsModel.discount.doubleValue() != 1 }">
                                                                                                <c:choose>
                                                                                                    <c:when test="${itemsModel.oldPrice != null}">
                                                                                                        <c:set var="allTotalPrice"
                                                                                                               value="${allTotalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
                                                                                                    </c:when>
                                                                                                    <c:otherwise>
                                                                                                        <fmt:formatNumber
                                                                                                                   var="discountPrice"
                                                                                                                   value="${itemsModel.price*itemsModel.quantity*(1-itemsModel.discount)}"
                                                                                                                   pattern="0.00"/>
                                                                                                        <c:set var="allTotalPrice"
                                                                                                               value="${allTotalPrice + itemsModel.price * itemsModel.quantity - discountPrice}"></c:set>
                                                                                                    </c:otherwise>
                                                                                                </c:choose>
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                                <c:set var="allTotalPrice"
                                                                                                       value="${allTotalPrice + itemsModel.price * itemsModel.quantity}"></c:set>
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        <c:set var="MaxTotalNum"
                                                               value="${MaxTotalNum+totalNum}"></c:set>
                                                        <c:set var="MaxTotalPrice"
                                                               value="${MaxTotalPrice+allTotalPrice}"></c:set>
                                                        </tbody>
                                                    </table>
                                                </td>
                                                <td class="goods-td goods-main-count cell-8">${totalNum}</td>
                                                <td class="goods-td goods-main-amount cell-9"><fmt:formatNumber
                                                        value="${totalPrice}" type="currency" pattern="0.00"/></td>

                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>

                                </div>
                                <table class="ui-table mt10">
                                    <tbody>
                                    <tr>
                                        <td style="width:35%" class="ta-l">
                                            <p>
                                                <span class="bb">寄送至：</span>
                                                <span>${dealerOrder.dealerAddrProvince} ${dealerOrder.dealerAddrCity} ${dealerOrder.dealerAddrArea} ${dealerOrder.dealerAddress} </span>
                                            </p>
                                            <p>
                                                <span class="bb">收货人：</span><span>${dealerOrder.shipName} ${dealerOrder.dealerMobile}</span>
                                            </p></td>
                                        <td  class="total"><span class="fs14 yahei">进货总量：<span
                                                class="fs18 c-r">${MaxTotalNum}</span> 件
                                                        &nbsp;&nbsp;|&nbsp;&nbsp; </span><span class="fs14 yahei">总价:<span>
                                                                <fmt:formatNumber
                                                                           value="${MaxTotalPrice}"
                                                                           pattern="0.00"/>元
                                                                <c:if test="${dealerOrder.adjustPrice>=0}">
                                            +${dealerOrder.adjustPrice}元
                                        </c:if> <c:if test="${dealerOrder.adjustPrice<0}">
                                            ${dealerOrder.adjustPrice}元
                                        </c:if> <span>（优惠）</span>+${dealerOrder.adjustFreight} <span>（运费）
												<c:if test="${!empty dealerOrder.noSendGoodsAmount}">
                                                    -<fmt:formatNumber value="${dealerOrder.noSendGoodsAmount}"
                                                                       pattern="0.00"/>元
                                                    <span>（关闭发货）</span>
                                                </c:if>
                                                                =</span>
                                                            <strong class="fs18 c-r">&nbsp;<fmt:formatNumber value="${MaxTotalPrice+dealerOrder.adjustPrice+dealerOrder.adjustFreight-dealerOrder.noSendGoodsAmount}" pattern="0.00"
                                                                                                             type="currency"/></strong>元</span>
                                                </span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!--endprint-->
                        </div>
                        <div class="main-grid mb10 reconfirm-box">
                            <p class="tip">
                                    品牌超级代理提醒您：<strong>为了您的资金安全，请收到货后，再确认收货！</strong>
                            </p>


                            <form:form  data-widget="validator" class="ui-form mt20"  id='reconfirm_Form' onsubmit="return false;">
                                  <c:if test="${dealerOrder.orderStatus==4}">
                                <div class="ui-form-item">
                                    <label class="ui-label" for="">平台支付密码：</label>
                                    <c:if test="${!payword}">
                                    <!-- <input type="button" value="请先设置支付密码" id="initPayPasswordBtn"> -->
                                    <a class="ui-button ui-button-mwhite" href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>" target="_blank">请先设置支付密码</a>下次支付需要您输入支付密码,请牢记
                                    </c:if>
                                     <c:if test="${payword}">
                                    <input required data-display="支付密码" style="width: 255px;" type="password" id='payword' class="ui-input" autocomplete="off">
                                     <a href="<%=ZttxConst.PAYAPI_WEBURL_RESETPWD%>" target="_blank" class="link">忘记密码?</a> 此密码为8637品牌超级代理平台支付密码
                                     </c:if>
                                </div>
                                <c:if test="${dealerInfo.rcvSmsVerify}">
                                <div class="ui-form-item">
                                    <label class="ui-label" for="">手机号码：</label>
                                    <input required data-display="手机号码" disabled="disabled" data-rule="mobile" style="width: 118px;" type="text" class="ui-input" name='telcode'  id="phonenum" value="${dealerUserm.userMobile}">
                                    <button class="code_btn ml5" id="telcodebtn" type="button">点击获取验证码</button>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label" for=""> 验证码：</label>
                                    <input required data-display="验证码" id="codenum" style="width: 118px;" type="text" class="ui-input">
                                </div>
                                </c:if>
                                <div class="ui-form-item">
                                    <button class="ui-button ui-button-mred" type="submit">确认收货</button>
                                </div>
                                </c:if>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="hide">
         <div id="initUserPayPassord">
            <div class="ui-head" >
                <h3>设置支付密码</h3>
            </div>
            	  <form:form id="init_payword_form" data-widget="validator" class="ui-form" action="#">
            <dl class="ui-form-item" style="padding-top: 10px;">
           
                          <dt class="ui-label"><span class="ui-form-required">*</span>
                                    新 密 码:
                          </dt>
                                <dd>
                                    <input type="text" style="display: none;" />
                                    <input autocomplete="off" id="password" name="newPassowrd" required
                                           class="text yahei long ui-input"
                                           type="password"
                                           value="" data-display="新密码"
                                           data-explain="请输入您的新密码"/>

                                    <div class="ui-form-explain">请输入您的新密码</div>
                                </dd>
                            </dl>
                        <dl class="ui-form-item">
                            <dt class="ui-label"><span class="ui-form-required">*</span>
                                重复密码:
                            </dt>
                            <dd>
                                <input id="confirm-pwd" name="cfmPassword"
                                       class="text yahei long ui-input"
                                       type="password"
                                       value="" data-display="新密码" required/>

                                <div class="ui-form-explain">请再输入一次您的新密码
                                </div>
                            </dd>
                        </dl>
                        <dl class="ui-form-item">
                            <dd>
                                <input type="submit"  class="ui-button-mred ui-button yahei fs16" value="确定"/>
                            </dd>
                        </dl>
                       </form:form>
        </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        baseFormValidator({
            isAjax: true, //默认为false，如果需要ajax提交，则把该值修正为true
            selector: "#reconfirm_Form",
            beforeSubmitFn: function(){
              var telphone=$('#phonenum').val();
              var telcode=$('#codenum').val();
              var payword=$('#payword').val();
                //$.post("${ctx}/dealer/dealerOrder/receive/confirm${dealerOrder.dealerOrderType==2?'2':''}/${dealerOrder.refrenceId}", {
                $.post("${ctx}/dealer/dealerOrder/receive/confirm/${dealerOrder.refrenceId}", {
                    payword: payword,
                    telcode: telcode
                }, function (data) {
            	  if(data.code==126000){
                      window.location.href = "${ctx}/dealer/dealerOrder/order";
            		  return;
            	  }else  if(data.code==126107){
            		  remind('error',data.message);
            		  $('#telcodebtn').html('发送手机验证码');
            		  return;
            	  }else if(data.code==111044){
            		  remind('error',data.message);
            		  $('#telcodebtn').html('发送手机验证码');
            		  $('#telcodebtn').removeAttr('disabled','disabled');
     				  clearInterval(t);
            		  return;
            	  }else if(data.code==111045){
            		  remind('error',data.message);
            		  $('#telcodebtn').html('发送手机验证码');
            		  $('#telcodebtn').removeAttr('disabled','disabled');
     				  clearInterval(t);
            		  return;
            	  }else{
            		  remind('error',data.message);
            		  return;
            	  }
              },"json");
            }
        });
        $('#telcodebtn').click(function(){
            $("#telcodebtn").addClass("code_btn_loading");
            $.post('${ctx}/common/forgotpass/sendDealerCode', function (data) {
                if(data.code==111004||data.code==111003 ){
                    var i=0;
                    var waitTime= 60;
                    $("#alreadySendCode").remove();
                    $("#telcodebtn").removeClass("code_btn_loading");
                    $("#telcodebtn").after("<div id='alreadySendCode'>验证码已发送到您手机,5分钟内输入有效,请勿泄露</div>");
                    $('#telcodebtn').html('倒计时('+(waitTime)+'秒)!');
                    window['t']= setInterval(function(){
                        if(i++<waitTime){
                            $('#telcodebtn').html('倒计时('+(waitTime-i)+'秒)!');
                        }else{
                            $('#telcodebtn').html('发送手机验证码');
                            $('#telcodebtn').removeAttr('disabled','disabled');
                            $("#alreadySendCode").remove();
                            clearInterval(t);
                        }
                    },1000);
                }
            },"json");
            $('#telcodebtn').attr('disabled','disabled');
        });
        
        var setVerifyTimeInfo=function(){
        	
        };
        
        $(document).on("click",".close_btn",function(){
            $(".panel-table-bottom").stop().slideUp();
            $(".panel-table-content").stop().slideUp();
            $(this).hide();
            $(".open_btn").show();
        });
        $(document).on("click",".open_btn",function(){
            $(".panel-table-bottom").stop().slideDown();
            $(".panel-table-content").stop().slideDown();
            $(this).hide();
            $(".close_btn").show();
        });
        
        seajs.use(["dialog"],function(Dialog){
			window ['d1'] = new Dialog({
                content:"#initUserPayPassord",
                width: 400,
                height:250
            });
			
            d1.after('show', function() {
            	$('#initUserPayPassord').show();
            });
           $('#initPayPasswordBtn').click(function(){
            	   d1.show();
           });
        });
        
        seajs.use(['validator', 'widget'], function(Validator, Widget){
            Widget.autoRenderAll();
            var Core = Validator.query("#init_payword_form");
            Core.set("autoSubmit",false);
            Core.addItem({
                element: '#password',
                required: true,
                rule: 'minlength{"min":6} maxlength{"max":20}',
                display: '新密码'
            }).addItem({
                element: '#confirm-pwd',
                required: true,
                rule: 'confirmation{target: "#password", name: "第一遍"}',
                display: '密码',
                errormessageRequired: '请再重复输入一遍密码，不能留空'
            })
            Core.on('formValidated', function(error, message, elem) {
                if(!error){
                	 $.post("${ctx}/dealer/payword/update", $('#init_payword_form').serialize(), function(data){
     					if(data.code == zttx.SUCCESS)
     					{
     						remind("success", "支付密码修改成功");
     						window.location.reload();
     						d1.hide();
     					}else{
     						remind("error",data.message);
     					}
     				}, "json");
                }
            });
        });
    </script>
</body>
</html>
