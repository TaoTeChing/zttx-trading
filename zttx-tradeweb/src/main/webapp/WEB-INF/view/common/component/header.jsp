<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--
上一个版本
<div class="header-round">
    <div class="sticky">
        <div class="header clearfix">
            <div class="logo" style="display: block;">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="search">
                <div class="clearfix">
                <form id="search_form" action="${ctx}/search" onsubmit="return false;">
                    <div class="search-form">
                        <div class="search-box">&lt;%&ndash;${q}&ndash;%&gt;
                            <input type="text" name="q" id="q" placeholder="品牌/资讯" value="${filter.q}"/>
                        </div>
                        <div class="search-select">
                            <input name="keyType" type="hidden" id="key_type" value="${filter.keyType == null ? 0 :  filter.keyType}"/>
                            <span></span>
                            <dl>
                                <dd value="0"><a href="javascript:void(0)">品牌</a></dd>
                                <dd value="1"><a href="javascript:void(0)">资讯</a></dd>
                            </dl>
                        </div>
                        <div class="search-btn">
                            <a id="btn_header_search">搜索</a>
                        </div>

                    </div>
                </form>
                </div>
                <div class="hot-search" id="hostList"></div>
            </div>
            <div class="sticky-close"><a href="javascript:;" title="收起搜索条"></a></div>
        </div>
    </div>
</div>--%>
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
                                <input name="keyType" type="hidden" id="key_type" value="${filter.keyType == null ? 0 :  filter.keyType}"/>
                                <span></span>
                                <dl>
                                    <dd value="2"><a href="javascript:void(0)">商品</a></dd>
                                    <dd value="0"><a href="javascript:void(0)">品牌</a></dd>
                                    <dd value="1"><a href="javascript:void(0)">资讯</a></dd>
                                    <%--<dd value="2"><a href="javascript:void(0)">店铺</a></dd>--%>
                                </dl>
                            </div>
                            <div class="search-box" style="width: 345px;">
                                <input type="text" name="q" id="q" placeholder="商品/品牌/店铺" value="${filter.q}" autocomplete="off" />
                                <%--<div class="search-area">
                                    <div class="search-area-nav" style="width: 80px;">
                                        <span title="分类">分类</span> <i></i>
                                    </div>
                                    <div class="search-area-province" style="width: 80px;">
                                        <span title="省">省</span> <i></i>
                                    </div>
                                    <div class="search-area-city" style="width: 80px;">
                                        <span title="市">市</span> <i></i>
                                    </div>
                                    <dl class="area-nav-list" style="width: 70px;">
                                        <dd><a>女装</a></dd>
                                        <dd><a>男装</a></dd>
                                        <dd><a>内衣</a></dd>
                                        <dd><a>泳衣</a></dd>
                                        <dd><a>女装</a></dd>
                                        <dd><a>男装</a></dd>
                                        <dd><a>内衣</a></dd>
                                        <dd><a>泳衣</a></dd>
                                        <dd><a>女装</a></dd>
                                        <dd><a>男装</a></dd>
                                        <dd><a>内衣</a></dd>
                                        <dd><a>泳衣</a></dd>
                                        <dd><a>女装</a></dd>
                                        <dd><a>男装</a></dd>
                                        <dd><a>内衣</a></dd>
                                        <dd><a>泳衣</a></dd>
                                    </dl>
                                    <dl class="area-province-list" style="width: 70px;">
                                        <dd><a>浙江</a></dd>
                                        <dd><a>江苏</a></dd>
                                        <dd><a>湖南</a></dd>
                                        <dd><a>广东</a></dd>
                                    </dl>
                                    <dl class="area-city-list" style="width: 70px;">
                                        <dd><a>宁波</a></dd>
                                        <dd><a>温州</a></dd>
                                        <dd><a>杭州</a></dd>
                                        <dd><a>湖州</a></dd>
                                    </dl>
                                </div>--%>
                            </div>
                            <div class="search-btn">
                                <a id="btn_header_search">搜索</a>
                            </div>
                        </form>
                    </div>
                    <%--<div class="search-history">
                        <a class="history-btn">
                            我的足迹 <i></i>
                        </a>
                        <div class="history-box">
                            <ul>
                                <li>
                                    <a>
                                        内衣
                                        (浙江 宁波)
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        内衣
                                        (浙江 宁波)
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        大妈装
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        大妈装
                                    </a>
                                </li>
                            </ul>
                            <div class="history-box-delete">
                                <a href="javascript:;">删除以上记录</a>
                            </div>

                        </div>
                    </div>--%>
                </div>
                <div class="hot-search" id="hostList">

                </div>
            </div>
        </div>
    </div>
</div>
