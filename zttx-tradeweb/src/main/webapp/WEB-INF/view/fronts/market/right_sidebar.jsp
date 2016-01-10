<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="sidebar-r">
    <%--<!-- 收藏信息 -->--%>
    <jsp:include page="${ctx}/market/brand/attentionNum">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>

    <%--<!-- 品牌信息 -->--%>
    <jsp:include page="${ctx}/market/brand/brandesInfo">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>

    <%--<!-- 品牌商基本信息 -->--%>
    <jsp:include page="${ctx}/market/brand/brandInfo">
        <jsp:param name="brandId" value="${brandId}"/>
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
    <%--<!-- 在线沟通的小企鹅 -->--%>
    <%--<div class="contact clear" style="border-bottom: 0;">
        <h3 class="title">在线沟通:</h3>

        <div class="conbody">
            <a href="#" title="" target="">
                <img src="/images/market/conc.jpg"></a>
        </div>
    </div>- -%>
    <%--<!-- 品牌商旗下所有品牌信息 -->--%>
    <jsp:include page="${ctx}/market/brand/brandesList">
        <jsp:param name="brandId" value="${brandId}"/>
    </jsp:include>

    <%--<!-- 在线留言 -->
    <jsp:include page="${ctx}/market/show_message_box"/>
    --%>
</div>