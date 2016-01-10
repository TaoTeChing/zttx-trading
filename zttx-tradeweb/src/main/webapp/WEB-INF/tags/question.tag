<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ attribute name="list" type="java.util.List" required="true" description="集合分页对象" %>
<%@ attribute name="className" type="java.lang.String" required="true" description="样式名称" %>

<div class="common-tit">
    <span class="tit">常见问题</span>
    <span class="detail"><a target="_blank" title="更多" class="more" href="${ctx }/help"></a></span>
</div>
<div class="${className }">
    <ul>
        <c:if test="${list != null }">
            <c:forEach items="${list}" var="helpInfo">
                <li>
                    <a target="_blank" title="${helpInfo.title}"
                       href="${ctx }/help/info/${helpInfo.refrenceId}">${helpInfo.title}</a>
                </li>
            </c:forEach>
        </c:if>
    </ul>
</div>