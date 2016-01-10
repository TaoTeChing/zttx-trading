<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="ad-gallery ad-gallery2">
    <div class="ad-image-wrapper">
    </div>
    <div class="ad-controls">
    </div>
    <div class="ad-nav">
        <div class="ad-thumbs">
            <ul class="ad-thumb-list">
            	<c:forEach var="obj" items="${imageList}" varStatus="status">
                	<li>
                    	<a href="${obj.domainName}${obj.imageName}">
                        	<img src="${obj.domainName}${obj.imageName}" title="这里存放标题" alt="这里存放描述" class="image${status.index}" />
                    	</a>
                	</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>