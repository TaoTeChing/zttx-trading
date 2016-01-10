<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<!-- 操作记录详情    start -->
<c:if test="${drrList != null }">
        <ul class="c-h">
        	<c:forEach items="${drrList}" var="dealerRefReply" varStatus="status">
             <li class="lh2">
               <div>
                <span class="c-r">*</span> 
                <span>${fns:getStringTime(dealerRefReply.createTime,"yyyy-MM-dd HH:mm:ss")}</span> 
                <span>${dealerRefReply.replyContent }</span>
               </div>
               <c:if test="${dealerRefReply.draList != null }">
                <div class="mt10">
                	<c:forEach  items="${dealerRefReply.draList}" var="dealerRefAttacht">
                		<%--<a href="${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}"
                		data-lightbox="arraylist_${status.index}">--%>
                  	<img width="50" height="50" 
                  	src="${res}${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}" />
                 	<%--</a>--%>
               </c:forEach>
               	</div>
               </c:if>
            </li>
        	</c:forEach>
        </ul>
</c:if>
<!-- 操作记录详情    end   -->
<%--
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
 --%>
<script>
	//seajs.use(["lightbox"],function(){});
</script>