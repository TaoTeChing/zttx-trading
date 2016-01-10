<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="CAN_NOT_ALL" value="<%=com.zttx.web.consts.ProductConsts.CAN_NOT_ALL.getKey()%>"/>
<link href="${res}/styles/common/validformStyle.css" rel="stylesheet" type="text/css"/>

<%--<!-- 品牌商基本信息控件 -->--%>
<div class="suppbra font">
    <h3 class="title">品牌供应商:</h3>

    <div class="content">
        <input id="v_brandesId" type="hidden" value="${brandesId}"/>
        <input id="v_brandId" type="hidden" value="${brandInfo.refrenceId}"/>

        <p class="companyname">
		<c:set value="${ fns:getBrandsIdSubDomain(brandesId)}" var="domain"></c:set>
            <a href="http://${domain}${zttx}/company">${brandInfo.comName}</a>
        </p>

        <%--<!-- 企业认证 -->--%>
        <div class="atte">
            <img src="${res}/images/fronts/market/atte.jpg" alt="" width="132" height="44"/>

            <%--<div class="qq-area" style="width: 100px; display: inline-block;*display: inline; *zoom:1; vertical-align: middle; margin-left: 5px;">--%>
            <%--<!-- WPA Button Begin -->--%>
                <%--<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>--%>
            <%--<!-- WPA Button End -->--%>
            <%--</div>--%>

        </div>
		
        <p class="telphone">电话：
            <c:choose>
                <c:when test="${empty dealerId}">
                       	登录后可看
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${empty brandContact}">
                            	暂无联系信息
                        </c:when>
                        <c:when test="${empty brandContact.userTelphone and empty brandContact.userMobile}">
                          	        暂无联系信息
                        </c:when>
                        <c:when test="${not empty brandContact.userTelphone and empty brandContact.userMobile}">
                          	    ${brandContact.userTelphone}
                        </c:when>
                        <c:when test="${empty brandContact.userTelphone and not empty brandContact.userMobile}">
                          	    ${brandContact.userMobile}
                        </c:when>
                        <c:otherwise>
                            ${brandContact.userTelphone}/${brandContact.userMobile}
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </p>
        <%-- 电话功能取消 --%>
        <c:if test="${not empty brandInfo.comWeb}">
            <p class="website">官网：
                <a href="${brandInfo.comWeb}" title="" target="_blank">${brandInfo.comWeb}</a>
            </p>
        </c:if>
        <p class="site">地址：${brandInfo.provinceName}${brandInfo.cityName}${brandInfo.areaName}${brandInfo.comAddress}</p>

        <div class="sitemsg clear">
            <p class="fontcolor6">注册资金：${brandInfo.regMoney}万人民币</p>

            <p class="fontcolor6">${emploeeNum!="" ? emploeeNum:''}</p>
            <%--<a class="f-r" href="${ctx}/market/brand/viewBrandNetwork/${brandesId}/${brandId}" title="" target="">查看经销网络</a>--%>
            <p class="fontcolor6">${moneyNum!="" ? moneyNum:''}</p>
        </div>
        <c:if test="${saleState!=CAN_NOT_ALL}">
            <c:if test="${not empty brandRecruit && brandRecruit.recruitState == 1}">
                <c:choose>
                    <c:when test="${btnState == 1}">
                        <a class="join" id="joinbtn" href="javascript:;"></a>
                    </c:when>
                    <c:when test="${btnState == 2}">
                        <span>已加盟</span>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
            </c:if>
        </c:if>
        <%--<div>
            <img src="/images/market/tel.png" alt=""/>
        </div>--%>
        <%--<a class="brannk" href="${ctx}/market/brand/viewBrandRecruit/${brandesId}/${brandId}" title="" target="">查看品牌招募书</a>--%>
        <%--<a class="rule" href="#" title="" target="">查看经销合作规则</a>--%>
    </div>
    <c:if test="${userOnlineService.showed == 1}">
    <div class="suppbra-times">
    <a href="${ctx}/market/brand/viewCompany/${brandesId}/${brandId}">${brandInfo.comName}</a><br />工作时间是：
     <%--<c:if test="${userOnlineService.onlineDateType==0}">周一 到周五  </c:if>
     <c:if test="${userOnlineService.onlineDateType==1}">周一 到周六  </c:if>
     <c:if test="${userOnlineService.onlineDateType==2}">周一 到周日  </c:if>
     <c:if test="${userOnlineService.onlineDateType==null}">暂时未定 </c:if>--%>
     
     
		     <c:if test="${userOnlineService.onlineBeginTime==null}">
		        09:00:00 
		     </c:if>
		     <c:if test="${userOnlineService.onlineBeginTime!=null}">
		           ${fns:getTimeFromLong(userOnlineService.onlineBeginTime,"HH:mm:ss")}
		     </c:if>
		   
		     <c:if test="${userOnlineService.onlineEndTime==null}">
		         -- 17:00:00
		     </c:if>
    
		     <c:if test="${userOnlineService.onlineEndTime!=null}">   <!-- &&userOnlineService.onlineEndTime!=0 -->
		        --${fns:getTimeFromLong(userOnlineService.onlineEndTime,"HH:mm:ss")}
		     </c:if>
    
        <%--时间 --%>
     
    </div>
    <%-- QQ --%>
    <div class="suppbra-qq-service">
        <c:forEach items="${userOnlineService.onlineDetailList}" var="obj"  >
            <%--${obj.name}:${obj.qq}--%>
            <div class="inline-block">
                    ${obj.name}:<a href="http://wpa.qq.com/msgrd?v=3&uin=${obj.qq}&site=qq&menu=yes" class="qq-service"></a>
            </div>
        </c:forEach>
    </div>
    <%--<p class="suppbra-tel">联系电话：0574-8215489 / 13548598556</p>--%>
   <!--  <div class="suppbra-qq-service">
        <div class="inline-block">
            小红:<div class="qq-service"></div>
        </div>
        <div class="inline-block">
            小白:<div class="qq-service"></div>
        </div>
        <div class="inline-block">
            小白:<div class="qq-service"></div>
        </div>
    </div> -->
    </c:if>
</div>
<div id="form-area" class="js_login_box js-all-throw" style="display: none; position: relative;">
    <div class="ui-box-tanchuang">
        <div class="ui-head"><h3 class="">申请加盟</h3><i>X</i></div>
        <form:form id="applyForm" method="post" data-widget="validator">
            <style>
                #applyForm .ui-form-item{padding-left: 0; padding-bottom: 0;}
                #applyForm .ui-form-explain{ display: inline-block; *display: inline; *zoom: 1;padding-top: 0;}
            </style>
            <table class="login_box_table mt15">
                <colgroup>
                    <col width="70"/>
                    <col width="200"/>
                </colgroup>
                <tbody>
                <input id="brandsId" type="hidden" value="${brandesId}" name="brandsId"/>
                <tr>
                    <td style="padding: 5px 0;">联 系 人：</td>
                    <td style="padding: 5px 0;">
                        ${dealerUserm.userName}
                    </td>
                </tr>
                <tr>
                    <td style="padding: 5px 0;">联系方式：</td>
                    <td style="padding: 7px 0;">
                        <div class="ui-form-item">
                            <input type="text" name="telphone" class="ui-input" value="${dealerUserm.userMobile}" required data-display="手机号码" maxlength="20"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>申请信息：</td>
                    <td>
                        <div class="ui-form-item">
                            <textarea class="login_box_textarea" name="inviteText" datatype="*" required data-display="申请信息" maxlength="512"></textarea>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input id="applyFormSubmit" type="submit" value="提交申请资料" class="button mt5">
                    </td>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>
</div>
<div class="lig" style="z-index: 10;"></div>
     
       




