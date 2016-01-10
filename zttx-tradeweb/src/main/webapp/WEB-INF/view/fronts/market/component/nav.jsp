<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.zttx.web.consts.BrandConstant" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="nav">
    <div class="nav-center">
        <ul class="navul">
             <c:set var="searchUrl" value="${ctx}/market/brands/search"/>
            <li ${empty param.dealNo ? 'class="select"':''}>
                <a href="${ctx}/market/index" class="alink-bg">品牌首页</a>
            </li>
            <li ${param.dealNo >= 130000 and param.dealNo < 140000 ? 'class="select"':''}>
                <a href="${searchUrl}?dealNo=130000" class="alink-bg">服&nbsp;&nbsp;装</a>
            </li>
            <li ${param.dealNo >= 140000 and param.dealNo < 150000 ? 'class="select"':''}>
                <a href="${searchUrl}?dealNo=140000" class="alink-bg">鞋&nbsp;&nbsp;帽</a>
            </li>
            <li ${param.dealNo >= 150000 and param.dealNo < 160000 ? 'class="select"':''}>
                <a href="${searchUrl}?dealNo=150000" class="alink-bg">箱&nbsp;&nbsp;包</a>
            </li>
            <%--
            <c:forEach items="${primaryDealDics}" var="item">
                <li>
                    <a href="${searchUrl}?dealNo=${item.dealNo}" class="alink-bg">${item.dealName}</a>
                    &lt;%&ndash;<div class="dropmenu">&ndash;%&gt;
                        &lt;%&ndash;<c:forEach items="${primaryDealDics}" var="dic" varStatus="status">&ndash;%&gt;
                            &lt;%&ndash;<a href="${searchUrl}?dealNo=${dic.dealNo}" ${status.first ? 'style="z-index: 30"':''}><span>${dic.dealName}</span></a>&ndash;%&gt;
                        &lt;%&ndash;</c:forEach>&ndash;%&gt;
                    &lt;%&ndash;</div>&ndash;%&gt;
                </li>
            </c:forEach>
            --%>
            <%--
            <li class="select">
                <a href="${searchUrl}" class="alink-bg">所有品牌</a>

                <div class="dropmenu">
                    <c:forEach items="${primaryDealDics}" var="dic" varStatus="status">
                        <a href="${searchUrl}?dealNo=${dic.dealNo}" ${status.first ? 'style="z-index: 30"':''}><span>${dic.dealName}</span></a>
                    </c:forEach>
                </div>
            </li>
            <li class="select">
                <a href="${searchUrl}" class="alink-bg">所有品牌</a>

                <div class="dropmenu">
                    <c:forEach items="${primaryDealDics}" var="dic" varStatus="status">
                        <a href="${searchUrl}?dealNo=${dic.dealNo}" ${status.first ? 'style="z-index: 30"':''}><span>${dic.dealName}</span></a>
                    </c:forEach>
                </div>
            </li>
            <li>
                <c:set var="DOMESTIC" value="<%=BrandConstant.BrandesInfoConst.BRAND_TYPE_DOMESTIC%>"/>
                <c:set var="domesticUrl" value="${ctx}/market/brands/search?type=${DOMESTIC}"/>
                <a href="${domesticUrl}" title="" target="" class="alink-bg">国内品牌</a><span class="hot-ico"></span>

                <div class="dropmenu">
                    <c:forEach items="${primaryDealDics}" var="dic" varStatus="status">
                        <a href="${domesticUrl}&dealNo=${dic.dealNo}" ${status.first ? 'style="z-index: 30"':''}><span>${dic.dealName}</span></a>
                    </c:forEach>
                </div>
            </li>
            <li>
                <c:set var="FOREIGN" value="<%=BrandConstant.BrandesInfoConst.BRAND_TYPE_FOREIGN%>" />
                <c:set var="foreignUrl" value="${ctx}/market/brands/search?type=${FOREIGN}"/>
                <a href="${foreignUrl}" class="alink-bg">国际品牌</a>
                <div class="dropmenu">
                    <c:forEach items="${primaryDealDics}" var="dic" varStatus="status">
                        <a href="${foreignUrl}&dealNo=${dic.dealNo}" ${status.first ? 'style="z-index: 30"':''}><span>${dic.dealName}</span></a>
                    </c:forEach>
                </div>
            </li>
            --%>
        </ul>
    </div>
</div>
