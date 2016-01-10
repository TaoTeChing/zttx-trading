<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="验证失败消息内容" %>
<%@ attribute name="width" type="java.lang.Integer" required="false" description="弹出窗口宽度" %>
<link rel="stylesheet" href="/scripts/arale/dialog/1.2.5/dialog.css"/>
<style>
    .ui-dialog-content {
        padding: 20px;
        text-align: left;
    }

    .errorMessage {
        color: red;
        line-height: 2;
    }
</style>
<c:if test="${not empty content}">
    <c:set var="dialogWitdh" value="500"></c:set>
    <c:if test="${not empty width}">
        <c:set var="dialogWitdh" value="${width }"></c:set>
    </c:if>
    <script type="text/javascript">
        seajs.use(["dialog"], function (Dialog) {
            new Dialog({
                width:${dialogWitdh},
                content: '<div class="errorMessage">${content}</div>'
            }).show();
        });
    </script>
</c:if>