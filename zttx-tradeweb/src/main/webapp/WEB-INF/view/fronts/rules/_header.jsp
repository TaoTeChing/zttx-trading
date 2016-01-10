<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="header">
    <jsp:include page="${ctx}/common/top" />
    <div id="search" class="clearfix">
        <div id="logo" class="fl">
            <div class="logo fl">
                <a href="/" class="logo">
                    <img src="${res }/images/common/logo-old.png" alt="8637品牌超级代理"/>
                </a>
            </div>
            <div class="rules-center fl">
                <span>规则中心</span>
            </div>
        </div>
    </div>
    <div id="navigation" class="clearfix">
        <ul class="nav-items">
            <li>
                <a class="_blank" href="${ctx }/help">帮助中心</a>
            </li>
            <li class="active">
                <a class="_blank" href="${ctx }/rules">规则中心</a>
            </li>
        </ul>
        <div class="crumbs">
            <div class="crumbs-content">
                <span>服务中心</span> &gt; <a href="${ctx }/rules">规则中心</a>
            </div>
        </div>
    </div>
</div>
