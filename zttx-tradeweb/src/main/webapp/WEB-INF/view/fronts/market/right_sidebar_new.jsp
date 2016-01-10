<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="CAN_NOT_ALL" value="<%=com.zttx.web.consts.ProductConsts.CAN_NOT_ALL.getKey()%>"/>

<div class="sidebar-r">

    <%--<!-- 浏览量  关注  -->--%>
    <div class="attention">
        <p class="font fontcolor3">
            <span class="guanzhudu"></span>收藏：<em class="gz-num">${empty brandsCount ? 0 : brandsCount.favNum}</em>
        </p>
        <c:if test="${showFavBtn}">
            <p class="font fontcolor3">
                <c:if test="${isCollected}"><span class="yiguanzhu"></span>已收藏</c:if>
                <c:if test="${not isCollected}"><span class="weiguanzhu"></span><a href="javascript:void(0);" class="js-collected-brands" data-brandsid="${brandsId}">点击收藏</a></c:if>
            </p>
        </c:if>
    </div>

    <div class="m-brand">
        <div class="mb-fore1 js-img-center">
            <a href="#"><img src="${res}${brandesInfo.brandLogo}" alt="" width="180" height="90"/></a>
        </div>
        <div class="mb-fore2">
            <a href="#">${brandesInfo.brandsName}</a>
        </div>
        <div class="mb-fore3 clearfix">
            <a href="javascript:;"><i class="icon-view"></i>浏览量：<em>${brandsCount.viewNum}</em></a>
            <a href="javascript:;" style="margin-left: 20px;"><i
                    class="icon-follow"></i>关注：<em>${brandsCount.favNum}</em></a>
        </div>
        <div class="mb-fore4">
            <h3 class="tit">品牌供应商：</h3>

            <p>${brandInfo.comName}</p>
        </div>
        <div class="mb-fore5 clearfix">
            <span><i class="icon-ident"></i>认证企业</span>
            <span style="text-align: center;"><i class="icon-grade"></i>优质品牌</span>
            <c:if test="${join!=null}">
                <span style="text-align: right;"><i class="icon-joined"></i>已加盟</span>
            </c:if>
        </div>
    </div>
    <!--// 品牌及加盟信息 -->
    <div class="m-info">
        <%--未授权 开始--%>
        <c:if test="${null eq dealerJoin}">
                <c:if test="${null ne brandRecruit && brandRecruit.recruitState == 1}">
                    <c:choose>
                        <c:when test="${btnState == 1}">
                            <div class="mi-fore5">
                                <a class="join" id="joinbtn" href="javascript:;">提交加盟申请书</a>
                            </div>
                        </c:when>
                        <c:when test="${btnState == 2}">
                            <span>已加盟</span>
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </c:if>
        </c:if>
        <%--未授权 结束--%>
        <c:if test="${null ne dealerJoin}">
            <div class="mi-fore1">
                <dl class="clearfix">
                    <dt class="pic"><i class="icon-shou"></i></dt>
                    <dd class="tit">您是我们的加盟商户</dd>
                    <dd class="des">加盟关系：<em>${level.levelName}</em></dd>
                </dl>
            </div>
            <div class="mi-fore2 clearfix">
                <a href="${ctx}/dealer/dealerShoper/grant">我的授权产品库</a>
                <a href="${ctx}/dealer/dealerShoper/cart" style="margin-left: 10px;">我的进货单</a>
            </div>
            <div class="mi-fore3 clearfix">
                <p class="fl">累计进货<strong>${countAndAmount.count}</strong>款</p>

                <p class="fr">进货额<strong>${countAndAmount.amount}</strong>元</p>
            </div>
        </c:if>

        <div class="mi-fore4">
            <dl class="clearfix">
                <dt>企业官网</dt>
                <dd><a href="http://${brandInfo.comWeb}">${brandInfo.comWeb}</a></dd>
                <dt>联系地址</dt>
                <dd>${brandInfo.provinceName} ${brandInfo.cityName} ${brandInfo.areaName} ${brandInfo.comAddress}</dd>
                <dt>所属行业</dt>
                <dd>
                    <c:forEach items="${businesses}" var="business" varStatus="st">
                        <c:if test="${!st.last}">
                            ${business}|
                        </c:if>
                        <c:if test="${st.last}">
                            ${business}
                        </c:if>
                    </c:forEach>

                </dd>
                <%--
                <dt>注册资金</dt>
                <dd>${brandInfo.regMoney} 万</dd>
                --%>
                <dt>公司规模</dt>
                <dd>${emploeeNum}</dd>
                <%--
                <dt>年营业额</dt>
                <dd>${moneyNum}</dd>
                --%>
            </dl>
        </div>
    </div>

        <%--新增了一个浏览记录--%>
        <c:if test="${null!=browseHistroy}">
            <div class="recommend" style="border-left:0;border-right: 0;">
                <div class="clearfix">
                    <h3 class="title fl" style="border-top: 0;font-family: 'Microsoft Yahei';">浏览记录:</h3>
                    <a class="fr" href="http://www${zttx}/dealer/dealerShoper/browseHistroy" style="margin-top: 15px;margin-right: 15px;">更多</a>
                </div> 
                <c:forEach items="${browseHistroy}" var="product">
                    <dl class="recommend-combox">
                        <dt class="recommend-img"><a href="${ctx}/market/product/${product.productId}"><img
                                src="${res}${fns:getImageDomainPath(product.productImage, 45, 45) }" alt=""
                                width="45" height="45"/></a></dt>
                        <dd class="recommend-title"><a href="${ctx}/market/product/${product.productId}"
                                                       title="产品标题">${product.productTitle}</a></dd>
                        <dd class="recommend-price">
                            <c:choose>
                                <c:when test="${product.type==3}"><span>采购价：<i>￥</i><em>${product.cash}</em></span></c:when>
                                <c:when test="${product.type==4}"><span>授信价：<i>￥</i><em>${product.credit}</em></span></c:when>
                                <c:otherwise><span>吊牌价：<i>￥</i><em>${product.price}</em></span></c:otherwise>
                            </c:choose>
                            <c:if test="${product.type==3 || product.type==4}">
                                <i class="ml5"
                                   style="text-decoration: line-through;color:#999;">${product.price}</i>
                            </c:if>
                        </dd>
                    </dl>
                </c:forEach>
            </div>
        </c:if>

    <!--// 详细信息 -->
    <c:if test="${userOnlineService.showed == 1}">
        <div class="m-contact">
            <div class="mc-fore1">
                <p>${brandInfo.comName}在线咨询服务时间： ${fns:getTimeFromLong(online.onlineBeginTime,"HH:mm")}-${fns:getTimeFromLong(online.onlineEndTime,"HH:mm")}</p>
            </div>
            <%--
            <div class="mc-fore2">
                <p class="p1">${brandContact.userTelphone}</p>

                <p>+86 ${brandContact.userMobile}</p>
            </div>
            --%>
            <%--未授权 开始--%>
            <%--<div class="mc-fore2">
                <p class="p1">0574-555****888</p>
                <p class="p2"><a class="link" href="#">登录</a> | 平台查看完整的联系方式</p>
            </div>--%>
            <%--未授权 结束--%>
            <div class="mc-fore3">
                <ul class="inline-float">
                    <c:forEach items="${online.detailList}" var="detail">
                        <c:if test="${online.showed==1}">
                            <li>${detail.name}：<a
                                    href="http://wpa.qq.com/msgrd?v=3&amp;uin=${detail.qq}&amp;site=qq&amp;menu=yes"><img
                                    src="${res}/images/fronts/market/qq-service.png" alt="" width="75"/></a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:if>
    <!--// 联系信息 -->
    <div class="m-down">
        <h3 class="tit">公司旗下品牌：</h3>
        <ul class="inline-float">
            <c:forEach items="${braList}" var="brandes">
                <li>

                    <c:set value="${ fns:getBrandsIdSubDomain(brandes.refrenceId)}" var="domain"></c:set>
                    <c:set value="${fns:getImageDomainPath(brandes.brandLogo, 100, 50) }" var="url"></c:set>

                    <a href="http://${domain}${zttx}" class="js-img-center" target="_blank">
                        <img src="${res}${url}" alt="" width="70" height="35"/></a>
                    <p><a href="http://${domain}${zttx}" target="_blank">${brandes.brandsName}</a></p>


                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="contact clear" style="padding: 20px 15px;position: relative;border:0;">
        <img src="${res}/images/fronts/market/contect.png" alt="">

        <div class="qq-area">
            <script charset="utf-8" type="text/javascript"
                    src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>
        </div>
    </div>
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