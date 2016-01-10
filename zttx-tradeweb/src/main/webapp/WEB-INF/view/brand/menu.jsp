<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div id="quick_content" class="quick_content">
    <%--<ul>
        <c:forEach items="${defMenuList}" var="defMenu">
            <li data-id="${defMenu.refrenceId}"><a class="name" href="${defMenu.menuAddr}" title="">${defMenu.menuName}</a><a class="close" href="javascript:void(0);">×</a></li>
        </c:forEach>
    </ul>
--%></div>
<div id="sidebar" class="sidebar">
    <div class="bd">
        <ul>
            <li class="item j_hover_item user_info">
                <div class="item_hd">
                    <span class="photo">
                        <c:choose>
                            <c:when test="${not empty brandContact.userPhoto }">
                                <c:set value="${brandContact.userPhoto }" var="url"></c:set>
                                <img id="js-photo-img" src="${res}${fns:getImageDomainPath(url, 70,70)}" height="70"
                                     width="70">
                            </c:when>
                            <c:otherwise>
                                <img id="js-photo-img" src="${res}/images/brand/defalut_photo.png" height="70"
                                     width="70" alt=""/>
                            </c:otherwise>
                        </c:choose>
                        <input id="js-brand-photo" name="photo" class="input_file" type="file">
                    </span>
                    <span><span class="fn-text-overflow" title="${brandInfo.comName}"
                                style=" width: 58px; height: 20px; overflow: hidden;margin-top: 0;">${brandInfo.comName}</span><br/>账户信息</span>
                    <i class="down_arrow iconfont">&#xe609;</i>
                    <i class="up_arrow iconfont">&#xe60b;</i>
                </div>
                <div class="item_sub panel user_panel">
                    <p class="user_name">${brandInfo.comName}
                    </p>

                    <p>
                        <label>公司名：</label>
                        <span>${brandInfo.comName}</span>
                    </p>
                    <c:if test="${ not empty brandInfo && brandInfo.checkState != 2 }">
                        <div class="operate">
                            <a class="recharge_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_RECHARGE%>">充&nbsp;值</a>
                            <a class="withdrawal_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_WITHDRAW%>">提&nbsp;现</a>
                            <a class="inquiry_btn" target="_blank" href="<%=ZttxConst.PAYAPI_WEBURL_PORTAL%>">余额查询</a>
                        </div>
                    </c:if>
                </div>
            </li>
            <c:if test="${!empty menuTreeList}">
                <c:forEach items="${menuTreeList}" var="menuTree" varStatus="status">
                    <li class="item j_item <c:if test='${menuTree.menuOpen}'>current_item</c:if>">
                        <div class="item_hd">
                            <h3>
                                <i class="${menuTree.menuStyle}"></i>
                                <c:choose>
                                    <c:when test="${menuTree.leaf}">
                                        <a href="${menuTree.menuAddr}">${menuTree.menuName}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${menuTree.menuName}
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                            <c:if test="${!menuTree.leaf}">
                                <i class="down_arrow iconfont">&#xe609;</i>
                                <i class="up_arrow iconfont">&#xe60b;</i>
                            </c:if>
                        </div>
                        <c:if test="${!menuTree.leaf}">
                            <div class="item_sub ">
                                <ul>
                                    <c:forEach items="${menuTree.children}" var="sunMenuTree">
                                        <c:if test="${sunMenuTree.showflag}">
                                            <li class="sub_item" id="menu${sunMenuTree.refrenceId}">
                                                <a
                                                        <c:if test="${sunMenuTree.refrenceId==subMenuId}">class="current_sub"</c:if>
                                                        href="${sunMenuTree.menuAddr}">${sunMenuTree.menuName}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </li>
                </c:forEach>
                <%--<c:if test="${empty idMap.parentId || idMap.parentId==21}">
                <li class="item j_item <c:if test="${'52'==idMap.openId}">current_item</c:if>">
                    <div class="item_hd">
                        <h3>
                            <i class="icon i9"></i>
                            <a href="${ctx}/brand/brandAdvice/index">优化建议</a>
                        </h3>
                     </div>
                 </li>
                </c:if>--%>
                <li class="item j_item">
                    <div class="item_hd">
                        <h3>
                            <i class="icon i8"></i>
                            我的报表
                        </h3>
                        <i class="down_arrow iconfont">&#xe609;</i>
                        <i class="up_arrow iconfont">&#xe60b;</i>
                    </div>
                    <div class="item_sub ">
                        <ul>
                            <li class="sub_item">
                                <a target="_blank" href="<%=ZttxConst.BIAPI_WEBURL%>/dealerProductSellReport/qryDealerProductSellReportPage?brandId=${userInfo.refrenceId}">我的报表</a>
                            </li>
                            <li class="sub_item">
                                <a href="${ctx}/brand/product/report">工厂店库存报表</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
</div>