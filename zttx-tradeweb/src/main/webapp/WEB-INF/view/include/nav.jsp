<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="nav">
    <div class="ts-container clearfix">
        <div class="nav-left fl">
            <h2 class="btn">全部产品分类</h2>
            <div class='float-box <c:if test="${empty param.m || param.m!='0'}">open</c:if>'>
                ${fns:getDocument(new_index_left_category) }
            </div>
        </div>
        <ul class="nav-items js-nav-items fr clearfix">
            <li><a href="${ctx}/" title="8637品牌超级代理" <c:if test="${param.m=='0'}"> class="hover"</c:if>>首页</a></li>
            <li><a href="${ctx}/lady?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='1'}"> class="hover"</c:if>>女装</a></li>
            <li><a href="${ctx}/man?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='2'}"> class="hover"</c:if>>男装</a></li>
            <li><a href="${ctx}/underwear?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='3'}"> class="hover"</c:if>>内衣</a></li>
            <li><a href="${ctx}/textiles?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='4'}"> class="hover"</c:if>>家纺</a></li>
            <li><a href="${ctx}/children?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='5'}"> class="hover"</c:if>>童装</a></li>
            <li><a href="${ctx}/shoes&hats?st=${param.st != null ? param.st: "p"}" <c:if test="${param.m=='6'}"> class="hover"</c:if>>鞋帽</a></li>
            <li><a href="${ctx}/brandJoin" <c:if test="${param.m=='7'}"> class="hover"</c:if>>品牌加盟</a></li>
            <li><a href="${ctx}/factory/introduce" <c:if test="${param.m=='8'}"> class="hover"</c:if>>工厂店加盟</a></li>
        </ul>
        <div class="nav-move-item"></div>
    </div>
</div>
<%-- nav end --%>
