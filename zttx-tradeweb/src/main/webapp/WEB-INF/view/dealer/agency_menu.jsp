<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="main-left fl">
    <div class="main-grid">
        <div class="treepanel clearfix">
            <ul class="accordion">
                <li class="item j_hover_item user_info">
                    <div class="item_hd">
                        <span class="photo">
                            <c:if test="${empty dealerInfo.headImage}">
                                <img class="l-70x70" id="js-photo-img" src="${res}/images/brand/defalut_photo.png" height="70" width="70" alt="" />
                            </c:if>
                            <c:if test="${not empty dealerInfo.headImage}">
                                <img class="l-70x70" id="js-photo-img" src="${res}${dealerInfo.headImage}" height="70" width="70" alt="" />
                            </c:if>
                            <input id="js-photo" name="photo" class="input_file" type="file">
                        </span>
                        <span>
                            <span class="fn-text-overflow" title="${dealerInfo.dealerUser}" style=" width: 58px; height: 20px; overflow: hidden;margin-top: 0;">
                            ${dealerInfo.dealerUser}</span>
                            <br/>账户信息
                        </span>
                        <i class="down_arrow iconfont">&#xe609;</i>
                        <i class="up_arrow iconfont">&#xe60b;</i>
                    </div>
                    <div class="item_sub panel user_panel">
                        <p class="user_name">${dealerInfo.dealerUser}
                            <span class="qq"><c:if test="${not empty userInfo.userMail}">(${userInfo.userMail}) </c:if></span>
                        </p>
                        <p>
                            <label for="">店铺名：</label>
                            <span>${dealerInfo.dealerName}</span>
                        </p>
                        <%--<p>
                            <label for="">服务期限：</label>
                            <span>
                                <c:if test="${null != dealerInfo.beginTime && null != dealerInfo.endTime}">
                                    ${fns:getStringTime(dealerInfo.beginTime,"yyyy-MM-dd")}
                                    至 ${fns:getStringTime(dealerInfo.endTime,"yyyy-MM-dd")}
                                </c:if>
                            </span>
                        </p>--%>
                        <div class="operate">
                            <a class="recharge_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_RECHARGE%>" target="_blank">充&nbsp;值</a>
                            <a class="withdrawal_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_WITHDRAW%>">提&nbsp;现</a>
                            <a class="inquiry_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_PORTAL%>">余额查询</a>
                        </div>
                    </div>
                </li>
                <c:if test="${!empty menuTreeList}">
                    <c:forEach items="${menuTreeList}" var="majorMenu" varStatus="status">
                        <li class="item j_item <c:if test="${majorMenu.menuOpen}">current_item</c:if>">
                            <c:if test="${majorMenu.showflag}">
                            <div class="item_hd">
                                <h3>
                                    <i class="${majorMenu.menuStyle}"></i>
                                    <c:choose>
                                        <c:when test="${majorMenu.leaf}">
                                            <a href="${majorMenu.menuAddr}">
                                                     ${majorMenu.menuName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                                  ${majorMenu.menuName}
                                        </c:otherwise>
                                    </c:choose>
                                </h3>
                                <c:if test="${!majorMenu.leaf}">
                                    <i class="down_arrow iconfont">&#xe609;</i>
                                    <i class="up_arrow iconfont">&#xe60b;</i>
                                </c:if>
                            </div>
                            </c:if>
                            <c:if test="${!majorMenu.leaf}">
                                <div class="item_sub ">
                                    <ul>
                                        <c:forEach items="${majorMenu.children}" var="minorMenu">
                                            <c:if test="${minorMenu.showflag}">
                                                <li class="sub_item" id="menu${minorMenu.refrenceId}">
                                                    <a <c:if test="${minorMenu.refrenceId==subMenuId}">class="current_sub"</c:if> href="${minorMenu.menuAddr}" > ${minorMenu.menuName}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </li>
                  </c:forEach>
				</c:if>
            </ul>
        </div>
    </div>
</div>
<script>
    var currMenuId = "${subMenuId}";
    console.log(currMenuId);
</script>