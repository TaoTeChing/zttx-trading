<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="col-1">
    <div class="focus">
        <div id="focus">
            <ul class="ts-banner-content">
                <c:set value="${fns:getAdvertPosit(advert_focus_middle) }" var="posit"/>
                <c:if test="${posit != null && posit.viewNum > 0 }">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum-1}">
                        <li class="ts-banner-item" style="background:${list.backgroundColor}">
                            <div class="focus_li_1">
                                <div class="focus_li_2">
                                <a href="${list.urlAddress}" target="_blank" title="${list.adTitle}">
                                    <img title="${list.adTitle}" border="0" align="" src="${res}${list.adLogo}"
                                         alt="${list.altMark}" width="${posit.imgWidth}" height="${posit.imgHeight }"/>
                                </a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
            <div class="ts-banner-oprate">
                <div class="focus_op">
                    <a href="javascript:;" class="prev">&lt;</a>
                    <a href="javascript:;" class="next">&gt;</a>
                </div>
            </div>
        </div>
    </div>
    <div class="ts-container clearfix pr">
        <div class="card">
            <%-- <div class="card-hd" id="card-hd">
                 <div class="card－hd-view">
                     <ul class="inline-float">
                         <li class="item">
                             <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂1</a></h3>
                             <span title="桐乡市雅">桐乡市雅</span>
                         </li>
                         <li class="item">
                             <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂2</a></h3>
                             <span title="桐乡市雅">桐乡市雅</span>
                         </li>
                         <li class="item">
                             <h3 title="绍兴雅格制衣厂通过验厂"><a href="#">绍兴雅格制衣厂通过验厂3</a></h3>
                             <span title="桐乡市雅">桐乡市雅</span>
                         </li>
                     </ul>
                 </div>
             </div>--%>
            <div class="card-login">
                <c:set value="${fns:getUserPrincipal()}" var="userPrincipal"/>
                <c:choose>
                    <c:when test="${null != userPrincipal}">
                        <div class="pic clearfix">
                            <img src="${res}/images/fronts/index/defaule_portrait.jpg" alt=""/>
                            <h2>HI你好！${userPrincipal.userName != '' ? userPrincipal.userName:userPrincipal.userMobile}</h2>
                            <span>欢迎来到8637品牌超级代理</span>
                        </div>
                        <div class="btns clearfix">
                        <c:choose>
                            <c:when test="${userPrincipal.userType eq 0}">
                                <a class="a-btn2" href="${ctx}/brand/center">进入品牌商管理中心</a>
                            </c:when>
                            <c:otherwise>
                                <a class="a-btn2" href="${ctx}/dealer/center">进入终端商管理中心</a>
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="pic clearfix">
                            <img src="${res}/images/fronts/index/defaule_portrait.jpg" alt=""/>
                            <h2>HI你好！请<a href="${ctx}/common/login">登录</a></h2>
                            <span>欢迎来到8637品牌超级代理</span>
                        </div>
                        <div class="btns clearfix">
                            <a href="${ctx}/factory/introduce">终端商加盟</a>
                            <a class="ml10" href="${ctx}/brandJoin">品牌商入驻</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-notice">
                <div class="card-tab" id="card-tab">
                    <ul class="inline-float">
                        <li class="current">平台公告</li>
                        <li><a href="${ctx}/brandJoin" target="_blank">品牌商入驻流程</a></li>
                        <%--<li>终端商加盟流程</li>--%>
                    </ul>
                </div>
                <div class="card-tab-content" id="card-tab-content">
                    <div class="card-news">
                        <ul>
                            <c:set value="${fns:findSimpleByInfo(terrace_Notice, 8) }" var="noticeInfo"/>
                            <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                <li>
                                    <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                       title="${info.articleTitle }">
                                            ${fns:trimLongStringWithEllipsis(info.articleTitle, 20, '...') }</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="card-news" style="display: none;"></div>
                    <div class="card-news" style="display: none;"></div>
                </div>
            </div>
            <div class="card-other">
                <a href="${ctx}/soft/erp/"><i class="icon-home"></i>智慧门店管理系统</a>
                <a href="http://app.8637.com/" style="margin-left: 3px;"><i class="icon-phone"></i>约逛-手机APP</a>
            </div>
        </div>
    </div>
</div>
<div class="col-2 ts-container clearfix">
    <c:set value="${fns:getAdvertPosit(advert_top_left_1) }" var="posit"></c:set>
    <c:if test="${posit != null && posit.viewNum > 0}">
        <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum - 1}">
            <a class="fl" href="${ctx}${list.urlAddress}" target="_blank" title="${list.adTitle}" style="width: 210px;">
                <img src="${res}${list.adLogo}" width="${posit.imgWidth }" height="${posit.imgHeight}">
            </a>
        </c:forEach>
    </c:if>
    <div class="brand-list fl">
        <a class="prev" href="javascript:;">&nbsp;</a>
        <a class="next" href="javascript:;">&nbsp;</a>

        <div class="brand-list-con">
            <div id="brand-list-con">
                <ul class="ui-switchable-content">
                    <c:set var="posit" value="${fns:getAdvertPosit(advert_top_left_2) }" ></c:set>
                    <c:if test="${posit != null && posit.viewNum > 0}">
                        <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum - 1}">
                            <li class="brand-list-item">
                                <div>
                                    <a class="js-img-center" href="${ctx}${list.urlAddress}" target="${list.target}">
                                        <img src="${res}${list.adLogo}" width="${posit.imgWidth }" height="${posit.imgHeight}" alt="">
                                    </a>
                                </div>
                                <div class="brand-name">
                                    <a href="${ctx}${list.urlAddress}" title="${list.adTitle}" target="${list.target}">${list.adTitle}</a>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
