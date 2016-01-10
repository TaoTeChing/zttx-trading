<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="header-round">
    <div class="sticky">
        <div class="header clearfix" style="z-index: 216;">
            <div class="logo">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="search">
                <div class="clearfix">
                    <div class="search-form" style="width: 525px;">
                        <form id="search_form" action="${ctx}/search" onsubmit="return false;">
                            <div class="search-select">
                                <span></span>
                                <dl>
                                    <dd value="2"><a href="javascript:void(0)">商品</a></dd>
                                    <dd value="0"><a href="javascript:void(0)">品牌</a></dd>
                                    <dd value="1"><a href="javascript:void(0)">资讯</a></dd>
                                </dl>
                            </div>
                            <div class="search-box" style="width: 345px;">
                                <input type="text" name="q" id="q" placeholder="商品/品牌/店铺" value="${filter.q}" autocomplete="off" />
                            </div>
                            <div class="search-btn">
                                <a id="btn_header_search">搜索</a>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="hot-search" id="hostList">

                </div>
            </div>
        </div>
    </div>
</div>
