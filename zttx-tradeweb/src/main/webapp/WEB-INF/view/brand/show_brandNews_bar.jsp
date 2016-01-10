<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
			<c:if test="${param.m!='1'}">
	        <div class="ui_tab main_ui_tab">
	            <ul class="ui_tab_items clearfix">
	                <%-- <li class="ui_tab_item<c:if test="${param.m=='2'}"> current</c:if>"><a href="${ctx}/brand/meeting/list">发布会</a></li> --%>
	                <li class="ui_tab_item<c:if test="${param.m=='3'}"> current</c:if>"><a href="${ctx}/brand/news">新闻资讯</a></li>
	            </ul>
	        </div>
	        </c:if>