<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:if test="${not branderLogin}">
<div class="suppbra font" style="border-bottom: 1px solid #ddd;">
    <h3 class="title" style="border-top: 0;">在线留言:</h3>
    <div class="content">
        <c:choose>
            <c:when test="${dealerLogin}">
                <form:form id="msg-form" action="" method="post">
                    <div>
                        <textarea name="content" style="width: 258px; max-width: 258px; height: 80px;padding: 5px;margin-top: 10px;margin-bottom: 5px;"></textarea>
                        <input name="brandId" type="hidden" value="${brandId}">
                    </div>
                    <div>
                        <input id="btn-send" class="suppbra-content-subtn" type="button" value="提交留言">
                    </div>
                </form:form>
            </c:when>
            <c:otherwise>
                <p>您需要登录才能留言</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</c:if>