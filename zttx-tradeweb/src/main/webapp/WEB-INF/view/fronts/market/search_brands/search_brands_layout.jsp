<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:choose>
        <c:when test="${t == 1}">
            <c:set var="cateWord" value="服装服饰"/>
        </c:when>
        <c:when test="${t == 2}">
            <c:set var="cateWord" value="鞋靴箱包"/>
        </c:when>
        <c:when test="${t == 3}">
            <c:set var="cateWord" value="家纺"/>
        </c:when>
        <c:when test="${t == 4}">
            <c:set var="cateWord" value="童装/母婴类"/>
        </c:when>
        <c:otherwise>
            <c:set var="cateWord" value="所有行业"/>
        </c:otherwise>
    </c:choose>
    <c:if test="${not empty filter.q}">
        <c:set value="${filter.q}-" var="q"/>
    </c:if>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${q}${cateWord}- 品牌市场 - 8637品牌超级代理</title>
    <meta name="keywords" content="${cateWord}, ${filter.q}, ${cateWord}品牌代理, ${cateWord}品牌招商, ${filter.q}品牌代理, ${filter.q}品牌招商, 品牌市场"/>
    <meta name="description" content="8637品牌超级代理-品牌市场"/>
    <link href="${res}/styles/market/base.css" rel="stylesheet"/>
    <link href="${res}/styles/market/category.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<jsp:include page="${ctx}/common/top"/>
<!--// top end-->
<jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
<!--// header end-->
<jsp:include page="/WEB-INF/view/common/component/nav.jsp">
    <jsp:param value="2" name="m"/>
</jsp:include>
<!--// nav end-->
<div class="brands font mt20 fn-clear">
<div class="brands-mainleft">
    <!-- 面包屑导航-->
    <div class="bread-nav">
        <div class="bread-nav-f inline-block">
            <a class="link" href="${ctx}/market/brands/search">品牌市场</a>
        </div>
        <div class="bread-nav-f inline-block">
            <span class="arrow">&gt;</span>
        </div>
        <c:set var="dealNo" value="${filter.dealNo}"/>

        <div class="bread-nav-f inline-block">
            <c:choose>
                <c:when test="${t == 1}">
                    <a class="link" href="${ctx}/market/brands/search?dealNo=1&list=${filter.list}">服装服饰</a>
                </c:when>
                <c:when test="${t == 2}">
                    <a class="link" href="${ctx}/market/brands/search?dealNo=2&list=${filter.list}">鞋靴箱包</a>
                </c:when>
                <c:when test="${t == 3}">
                    <a class="link" href="${ctx}/market/brands/search?dealNo=3&list=${filter.list}">家纺</a>
                </c:when>
                <c:when test="${t == 4}">
                    <a class="link" href="${ctx}/market/brands/search?dealNo=4&list=${filter.list}">童装/母婴类</a>
                </c:when>
                <c:otherwise>
                    <a class="link" href="${ctx}/market/brands/search?&list=${filter.list}">全部</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="bread-nav-f inline-block">
            <span class="arrow">&gt;</span>
        </div>
        <div class="bread-nav-f inline-block">
            <input class="search" type="text" value="${filter.q}" placeholder="在当前条件下搜索"/>
            <a id="btn-search" href="javascript:;" class="ui-brand-btn ui-brand-btnmini" style="line-height: 11px;">搜索</a>
        </div>
    </div>
    <%--<!-- 分类 -->--%>
    <div class="classify classify-main">
        <div class="classify-tit inline-block">行业</div>
        <div class="classify-sel inline-block">
            <ul class="classify-ul classify-height inline-block">
                <li ${t == 0 ? 'class="active"':''}>
                    <a href="${ctx}/market/brands/search?&q=${filter.q}&list=${filter.list}">全部<i class="brandicons"></i></a>
                </li>
                <li ${t == 1 ? 'class="active"':''}>
                    <a href="${ctx}/market/brands/search?dealNo=1&q=${filter.q}&list=${filter.list}">服装服饰<i class="brandicons"></i></a>
                </li>
                <li ${t == 2 ? 'class="active"':''}>
                    <a href="${ctx}/market/brands/search?dealNo=2&q=${filter.q}&list=${filter.list}">鞋靴箱包<i class="brandicons"></i></a>
                </li>
                <li ${t == 3 ? 'class="active"':''}>
                    <a href="${ctx}/market/brands/search?dealNo=3&q=${filter.q}&list=${filter.list}">家纺<i class="brandicons"></i></a>
                </li>
                <li ${t == 4 ? 'class="active"':''}>
                    <a href="${ctx}/market/brands/search?dealNo=4&q=${filter.q}&list=${filter.list}">童装/母婴类<i class="brandicons"></i></a>
                </li>
            </ul>
            <div class="classify-btngroup">
                <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-sure">确定</a>
                <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-cancle">取消</a>
            </div>
        </div>
    </div>
    <%--<!-- 分类结束 -->
    <!-- 合作品牌 -->--%>
    <c:if test="${not empty secondDealDics}">
        <div class="classify">
            <div class="classify-tit inline-block">类别</div>
            <div class="classify-sel inline-block">
                <ul class="classify-ul inline-block">
                    <c:forEach items="${secondDealDics}" var="item">
                        <li ${dealNo == item.dictValue ? 'class="active"':''}>
                            <a href="${ctx}/market/brands/search?dealNo=${item.dictValue}&q=${filter.q}&list=${filter.list}">${item.dictValueName}<i class="brandicons"></i></a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="classify-btngroup">
                    <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-sure">确定</a>
                    <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-cancle">取消</a>
                </div>
            </div>
        </div>
    </c:if>

    <div class="classify-bottom">
    </div>
    <!-- 筛选条 -->
    <div class="filter">
        <div class="filter-box">
            <c:choose>
                <c:when test="${filter.sortName == 0}">
                    <a class="filter-sort filter-select ${filter.direction == 0 ? 'filter-downstyle':'filter-upstyle '}" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=0&direction=${filter.direction == 0 ? '1':'0'}&list=${filter.list}">默认
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="filter-sort filter-downstyle" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=0&direction=0&list=${filter.list}">默认
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="filter-box">
            <c:choose>
                <c:when test="${filter.sortName == 1}">
                    <a class="filter-sort filter-select ${filter.direction == 0 ? 'filter-downstyle':'filter-upstyle '}" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=1&direction=${filter.direction == 0 ? '1':'0'}&list=${filter.list}">销量
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="filter-sort filter-downstyle" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=1&direction=0&list=${filter.list}">销量
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="filter-box">
            <c:choose>
                <c:when test="${filter.sortName == 2}">
                    <a class="filter-sort filter-select ${filter.direction == 0 ? 'filter-downstyle':'filter-upstyle '}" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=2&direction=${filter.direction == 0 ? '1':'0'}&list=${filter.list}">时间
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="filter-sort filter-downstyle" href="${ctx}/market/brands/search?q=${filter.q}&dealNo=${dealNo}&sortName=2&direction=0&list=${filter.list}">时间
                        <i class="brandicons icons-brand-greyarrow"></i>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="filter-box">
            <a class="filter-liststyle ${filter.list ? '':'l-selected'}" href="javascript:refresh(false);">
                <i class="brandicons icons-brand-lookpic"></i>
            </a>
        </div>
        <div class="filter-box">
            <a class="filter-liststyle ${filter.list ? 'l-selected':''}" href="javascript:refresh(true);">
                <i class="brandicons icons-brand-lookfont"></i>
            </a>
        </div>
    </div>
    <!-- 筛选条结束 -->
    <div class="brand-content mt15">
        <layout:block name="content"/>
    </div>
    <!-- 品牌展示内容结束 -->
    <!-- 分页 -->
    <div class="pro-pages clear"></div>
    <!--分页结束-->
</div>
<div class="brands-mainright">
    <div class="brandsup">
        <h2 class="tit">品牌优势</h2>
        <dl class="brandsup-items">
            <dt class="imgbox">
                <i class="brandicons icons-brand-globe"></i>
            </dt>
            <dd class="tit">渠道快建</dd>
            <dd class="des">平台为品牌商与终端商搭建桥梁，实现较低成本渠道快建。</dd>
        </dl>
        <dl class="brandsup-items">
            <dt class="imgbox">
                <i class="brandicons icons-brand-briefcase"></i>
            </dt>
            <dd class="tit">直控终端</dd>
            <dd class="des">品牌商能及时获得各终端店货品进销存数据，有效管理终端。</dd>
        </dl>
        <dl class="brandsup-items">
            <dt class="imgbox">
                <i class="brandicons icons-brand-setting"></i>
            </dt>
            <dd class="tit">资金安全</dd>
            <dd class="des">平台与银联公司展开广泛的合作，有效保障资金安全。</dd>
        </dl>
        <dl class="brandsup-items">
            <dt class="imgbox">
                <i class="brandicons icons-brand-analy"></i>
            </dt>
            <dd class="tit">数据共享</dd>
            <dd class="des">与各大平台数据互通，便于掌控市场，指导经营。</dd>
        </dl>
    </div>
    <div class="brands-rcommon mt10" style="margin-bottom: 10px;">
        <div class="brands-rcommon-tit">
            <h4 class="title">推荐品牌</h4>
        </div>
        <div id="focus">
            <ul class="recombox">
                <c:forEach items="${recommendBrands}" var="recommend" begin="0" end="4">
                    <c:set var="mark" value="${fns:cleanHtmlElems(recommend.brandMark)}"/>
                    <li>
                        <div class="imgbox">
                        	<c:set value="${ fns:getBrandsIdSubDomain(recommend.refrenceId)}" var="domain"></c:set>
                            <c:set value="${res}${recommend.proLogo}" var="proLogoUrl"></c:set>
                            <a href="http://${domain}${zttx}"  target="_blank" title="${recommend.brandName}"><img src="${fns:getImageDomainPath(proLogoUrl, 300, 300) }" alt="${recommend.brandName}" width="260"/></a>
                        </div>
                        <div class="imgicon">
                        </div>
                        <div class="logo">
                            <a href="http://${domain}${zttx}" target="_blank" title="${recommend.brandName}"><img src="${res}${fns:getImageDomainPath(recommend.brandLogo, 100, 50)}" alt="${recommend.brandName}" width="90" height="45"/></a>
                        </div>
                        <div class="attent">${fns:cleanHtmlElems(recommend.brandName)}
                        </div>
                        <div class="des">
                            <span title="${mark}">${fns:trimLongString(mark, 30)}</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<div style="display: none;">
    <div class="leaveMsg js-leaveMsgbox">
        <div class="ui-head">
            <h3>在线留言</h3>
        </div>
        <form class="ui-form mt10">
            <div class="ui-form-item" style="padding-left: 0px;">
                <label class="ui-label" style="margin-left: -60px;">邮箱：</label>
                <input type="text" class="ui-input" style="width: 220px;"/>
            </div>
            <div class="ui-form-item" style="padding-left: 0px;">
                <label class="ui-label" style="margin-left: -60px;">留言：</label>
                <textarea class="ui-textarea" style="width:445px; height: 100px;"></textarea>
            </div>
            <div class="ui-form-item" style="padding-left: 0px;">
                <label class="ui-label" style="margin-left: -60px;">&nbsp;</label>
                <a href="javascript:;" class="ui-brand-btn">提交</a>
            </div>
        </form>
    </div>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common/base-init.js"></script>
<script src="${res}/scripts/common/search.js"></script>
<script src="${res}/scripts/market/category.js"></script>
<script type="text/javascript">
    function parseURL(url) {
        var a = document.createElement('a');
        a.href = url;
        return {
            source: url,
            protocol: a.protocol.replace(':', ''),
            host: a.hostname,
            port: a.port,
            query: a.search,
            params: (function () {
                var ret = {},
                        seg = a.search.replace(/^\?/, '').split('&'),
                        len = seg.length, i = 0, s;
                for (; i < len; i++) {
                    if (!seg[i]) {
                        continue;
                    }
                    s = seg[i].split('=');
                    ret[s[0]] = s[1];
                }
                return ret;
            })(),
            file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
            hash: a.hash.replace('#', ''),
            path: a.pathname.replace(/^([^\/])/, '/$1'),
            relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
            segments: a.pathname.replace(/^\//, '').split('/')
        };
    }
    var categorySelf = {
        init: function () {
            this.curretHover();//当前页面加的脚本
            this.listMove();//列表页产品移动
        },
        curretHover: function () {
            seajs.use(['popup', 'dialog', 'pagination'], function (Popup, Dialog, Pagination) {
                var popup = new Popup({
                    trigger: ".bread-nav .current",
                    element: ".breadnavdropdown",
                    effect: "fade",
                    align: {
                        baseXY: [0, 30]
                    }
                });

                var dialog = new Dialog({//在线留言弹框
                    trigger: ".js-online",
                    content: ".js-leaveMsgbox"
                });



                function searchCon() {
                    var name = $.trim($('input.search').val());
                    var params = parseURL(window.location.href).params;
                    params.currentPage = 1;
                    params.q = name;
                    window.location.href = '?' + $.param(params);
                }

                $('#btn-search').on('click', function () {
                    searchCon();
                });
                $("input.search").keyup(function (ev) {
                    var _key = ev.keyCode;
                    if (_key == 13) {
                        searchCon();
                    }
                });

                var pagination = new Pagination({
                    elem: ".pro-pages",
                    total:'${brandsPage.page.totalPage}' || 0 ,
                    currentPage:'${brandsPage.page.currentPage}' || 0,
                    skin: "new",
                    showTotal: false,
                    pageClick: function (page) {
                        var params = parseURL(window.location.href).params;
                        if (!params) {
                            params = {};
                        }
                        params.currentPage = page;
                        window.location.href = '?' + $.param(params);
                        window.event.returnValue = false;
                    }
                });
            });
        },
        listMove:function(){
            seajs.use(['carousel'], function (Carousel){
                if($(".newstate-show").length > 0){
                    $(".newstate-show").each(function(){
                        var _length = $(this).find(".newstate-showul .newstate-showli").length;
                        if(_length > 0){
                            var carousel = new Carousel({
                                "element": $(this),
                                "panels": $(this).find(".newstate-showul .newstate-showli"),
                                "effect": "scrollx",
                                "step": 1,
                                "viewSize": [142],
                                "circular": false,
                                "autoplay": false,
                                hasTriggers:false,
                                prevBtn:$(this).find(".prev"),
                                nextBtn:$(this).find(".next")
                            });
                        }
                    });
                }
            });
        }
    }
    var refresh = function(isList) {
        var params = parseURL(window.location.href).params || {};
        if (params.q) {
            params.q = decodeURIComponent(params.q);
        }
        params.list = isList;
        window.location.href = '?' + $.param(params);
    }
    categorySelf.init();
</script>
</body>
</html>
