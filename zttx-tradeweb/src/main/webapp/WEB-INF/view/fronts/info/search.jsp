<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="header">
    <div class="header-main" style="z-index: 216;">
        <div class="logo">
            <a href="${ctx}/">
                <h1>8637品牌超级代理</h1>
            </a>
        </div>
        <div class="name">
            新闻资讯
        </div>
        <div class="search">
            <form class="form" action="${ctx}/info/list">
                <%--<input name="cateId" type="hidden" value="${filter.cateId}"/>--%>
                <input name="q" type="text" class="stxt" value="${filter.q}" placeholder="请输入资讯标题" autocomplete="off"/>
                <input type="submit" class="sbtn" value=""/>
            </form>
        </div>
    </div>
</div>
