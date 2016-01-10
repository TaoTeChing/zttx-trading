<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/common/component/keywords.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>品牌市场-8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,天下商邦"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/market/base.css" rel="stylesheet"/>
    <link href="${res}/styles/market/index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<jsp:include page="${ctx}/common/top"/>
<jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
<%--<!--// top end-->--%>
<jsp:include page="/WEB-INF/view/common/component/nav.jsp">
    <jsp:param value="2" name="m"/>
</jsp:include>
<%--<!--// nav end-->
<!--知名品牌加盟推荐 首页是可见状态-->
<!-- 顶部广告轮播 -->--%>
<div class="main">
    <div id="slide">
        <c:set var="bannerSlider" value="${fns:getAdvertPosit('market_index_banner_slider') }"/>
        <c:if test="${not empty bannerSlider}">
            <c:set var="bannerSliders" value="${fns:getAdverts(bannerSlider.refrenceId, bannerSlider.advertKey, bannerSlider.viewNum)}"/>
            <c:if test="${not empty bannerSliders}">
                <ul class='slide-box'>
                    <c:forEach items="${bannerSliders}" var="item" varStatus="status">
                        <li style="${status.first ? 'opacity: 1; filter: alpha(opacity=100);':''} background: url('${res}${item.adLogo}') no-repeat center top;">
                            <a href="${item.urlAddress}" target="${adverts.target}" title="${adverts.adTitle }"></a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </c:if>
        <c:if test="${not empty bannerSlider}">
            <div class='slide-nav'>
                <%--<div class='slide-nav-box'>
                    <c:forEach begin="0" end="${fn:length(bannerSliders)}" varStatus="status">
                        <a ${status.first ? 'class="active"':''} href="javascript:;"></a>
                    </c:forEach>
                </div>--%>
            </div>
        </c:if>

        <%--<c:set var="slideSimg" value="${fns:getAdvertPosit('market_index_slide_simg')}"/>
        <c:if test="${not empty slideSimg}">
            <c:set var="slideSimgs" value="${fns:getAdverts(slideSimg.refrenceId, slideSimg.advertKey, slideSimg.viewNum)}"/>
            <c:if test="${not empty slideSimgs}">
                <!-- 轮播下 360x90 * 6 广告位 -->
                <div class="slide-simg">
                    <div class='slide-nav-box'>
                        <c:forEach items="${slideSimgs}" var="item">
                            <a href="javascript:;">
                                <div>
                                    <img src="${res}${item.adLogo}" alt="" width="${slideSimg.imgWidth}" height="${slideSimg.imgHeight}"/>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </c:if>--%>
    </div>
</div>
<%--<!--// banar end-->
<!-- 品牌主体内容 -->--%>
<div class="brand-body">
    <%--<!--品牌专区开始-->--%>
    <div class="brand-zone mt20">
        <h2 class="brand-zone-title">
            <span style="font-weight: 400;">————</span> 品牌专区
            <span style="font-weight: 400;">————</span></h2>

        <p class="brand-zone-items" style="margin-top: 10px;">
            <a href="${ctx}/market/brands/search">全部</a>·<a href="${ctx}/market/brands/search?dealNo=1">服装服饰</a>·<a href="${ctx}/market/brands/search?dealNo=2">鞋靴箱包</a>·<a href="${ctx}/market/brands/search?dealNo=3">家纺</a>·<a href="${ctx}/market/brands/search?dealNo=4">童装/母婴类</a>
        </p>
    </div>
    <%--<!--品牌专区结束-->
    <!--国际品牌开始-->--%>
    <div class="brand-inter fn-clear">
        <div class="brand-inter-title">
            <h3>
                发现品牌 <em class="font" style="font-weight: 600;">&gt;&gt;</em>
            </h3>
            <%--
            <a class="brand-inter-more font" href="#">
                更多
                <i></i>
            </a>
            --%>
        </div>
        <div class="brand-inter-titafter">
            <p>&nbsp;DISCOVERY</p>
            <i class="brand-inter-arrow"></i>
        </div>
        <div class="brand-inter-content mt15 fn-clear">
            <div class="brand-inter-abCon">
                <ul>
                    <%--<!-- 两张大图 第一个是长的，第二个是高的-->--%>
                    <c:set var="intType2" value="${fns:getAdvertPosit('market_index_type_2')}"/>
                    <c:set var="intType2s" value="${fns:getAdverts(intType2.refrenceId, intType2.advertKey, 1)}"/>
                    <c:forEach items="${intType2s}" var="item">
                        <li class="brand-inter-abItem aditem-1">
                            <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target}" title="${item.adTitle}"><img src="${res}${item.adLogo}" alt="${item.adTitle}" width="${intType2.imgWidth}" height="${intType2.imgHeight}"/></a>
                        </li>
                    </c:forEach>
                    <c:set var="intType3" value="${fns:getAdvertPosit('market_index_type_3')}"/>
                    <c:set var="intType3s" value="${fns:getAdverts(intType3.refrenceId, intType3.advertKey, 1)}"/>
                    <c:forEach items="${intType3s}" var="item">
                        <li class="brand-inter-abItem aditem-2">
                            <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target }"  title="${item.adTitle}"><img src="${res}${item.adLogo}" alt="${item.adTitle}" width="${intType3.imgWidth}" height="${intType3.imgHeight}"/></a>
                        </li>
                    </c:forEach>
                </ul>
                <ul>
                    <%--<!-- 6张小图 -->--%>
                    <c:set var="intType1" value="${fns:getAdvertPosit('market_index_type_1')}"/>
                    <c:set var="intType1s" value="${fns:getAdverts(intType1.refrenceId, intType1.advertKey, 6)}"/>
                    <c:forEach items="${intType1s}" var="item" varStatus="status">
                        <li class="brand-inter-abItem aditem-${status.index + 3}">
                            <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target}" title=" ${item.adTitle}" ><img src="${res}${item.adLogo}" alt="${item.adTitle}" width="${intType1.imgWidth}" height="${intType1.imgHeight}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="brand-inter-new">
                <h4>最新品牌</h4>
                <c:set var="intType4" value="${fns:getAdvertPosit('market_index_type_4')}"/>
                <c:set var="intType4s" value="${fns:getAdverts(intType4.refrenceId, intType4.advertKey, 6)}"/>
                <ul class="brand-inter-newul fn-clear">
                    <c:forEach items="${intType4s}" var="item">
                        <li class="brand-inter-newitems">
                            <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target}" title="${item.adTitle}"><img src="${res}${item.adLogo}" alt="${item.adTitle}" width="${intType4.imgWidth}" height="${intType4.imgHeight}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <%--<!--国际品牌结束-->
    <!--分类区开始-->--%>
    <div class="brand-classfiy mt20">
        <div class="brand-classfiy-title fn-clear">
            <h3 class="brand-classfiy-tith3 f-l">热门品牌</h3>

            <div class="f-l">
                <p class="brand-classfiy-titen">HOT</p>

                <p class="brand-classfiy-titdes">较低成本渠道快捷|一手价格一手资源</p>
            </div>
            <a class="brand-classfiy-more font" href="${ctx}/market/brands/search">查看更多</a>
        </div>
        <div class="brand-classfiy-tance fn-clear">
            <div class="brand-classfiy-left fn-clear">
                <div class="brand-classfiy-common">
                    <c:set var="intType5" value="${fns:getAdvertPosit('market_index_type_5')}"/>
                    <c:set var="intType5s" value="${fns:getAdverts(intType5.refrenceId, intType5.advertKey, 4)}"/>
                    <c:forEach items="${intType5s}" var="item">
                        <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target}" title="${item.adTitle }"><img src="${res}${item.adLogo}" alt="${item.adTitle }" width="${intType5.imgWidth}" height="${intType5.imgHeight}"/></a>
                    </c:forEach>
                </div>
                <div class="brand-classfiy-common">
                    <c:set var="intType6" value="${fns:getAdvertPosit('market_index_type_6')}"/>
                    <c:set var="intType6s" value="${fns:getAdverts(intType6.refrenceId, intType6.advertKey, 2)}"/>
                    <c:forEach items="${intType6s}" var="item">
                        <a href="${fns:getAdUrl(item.urlAddress)}" target="${item.target}" title="${item.adTitle }"><img src="${res}${item.adLogo}" alt="${item.adTitle }" width="${intType6.imgWidth}" height="${intType6.imgHeight}"/></a>
                    </c:forEach>
                </div>
            </div>
            <ul class="brand-classfiy-right fn-clear font">
                <c:forEach items="${hotBrands}" var="item">
                	<c:set value="${ fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain"></c:set>
                    <li class="brand-classfiy-items">
                    	<c:set value="${res}${item.proLogo}" var="itemUrl"></c:set>
                        <a class="js-img-center" href="http://${domain}${zttx}"  target="_blank" title="${item.brandName}"><img src="${fns:getImageDomainPath(itemUrl, 220, 220) }"/></a>
                        <div class="brand-classfiy-ablogo">
                                <%--
                                <a href="${fns:trimLongString(item.brandMark, 30)}"  target="_blank">
                                --%>
                            <a href="http://${domain}${zttx}" class="js-img-center" target="_blank" title="${item.brandName}">
                            	<c:set value="${res}${item.brandLogo}" var="brandLogoUrl"></c:set>
                                <img src="${fns:getImageDomainPath(brandLogoUrl, 100, 50) }" alt="${fns:cleanHtmlElems(fns:trimLongString(item.brandSubMark, 30))}" width="75" height="40"/>
                            </a>
                        </div>
                        <div class="brand-classfiy-name">
                            <p class="brand-classfiy-sto">
                                <span>${item.brandName}</span>
                            </p>
                            <p class="brand-classfiy-slink" style="margin-top: 10px;"><a href="">${item.brandCompanyName}</a></p>
                        </div>
                        <div class="brand-classfiy-des">
                            <a href="http://${domain}${zttx}/company" target="_blank" >${fns:trimLongString(item.brandSubMark, 30)}</a>
                        </div>
                    <%-- 后加的两个按钮--%>
                        <div>
                            <a class="brand-classfiy-btn check-term" href="http://${domain}${zttx}/recruit" target="_blank">查看招募条件</a><%--链接到当前品牌前台“品牌招募书页面”--%>
                            <a class="brand-classfiy-btn leave-message" href="http://${domain}${zttx}" target="_blank">我要咨询留言</a><%--链接到当前品牌前台“留言”--%>
                        </div> 
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%--<!--分类区结束-->
    <!--最新入驻品牌开始-->--%>
    <div class="brand-latest" style="margin-top: 30px;">
        <div class="brand-latest-inner">
            <div class="brand-latest-title">
                <h5>最新入驻品牌</h5>
                <span>渠道快捷，一手价格·</span>
            </div>
            <ul class="fn-clear">
                <c:forEach items="${latestJoinedBrandes}" var="item">
                    <li class="brand-latest-items">
                    	<c:set value="${ fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain"></c:set>
                    	<c:set value="${res}${item.proLogo}" var="proLogoUrl"></c:set>
                        <a href="http://${domain}${zttx}" target="_blank" title="${item.brandName}"><img src="${fns:getImageDomainPath(proLogoUrl, 300, 300) }" alt="${item.brandName}" width="230" height="230"/></a>

                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%--<!--最新入驻品牌结束-->--%>
</div>
</div>
<%--<!-- 品牌主体内容结束 -->--%>
<jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common/base-init.js"></script>
<script src="${res}/scripts/market/index.js"></script>
<script src="${res}/scripts/common/search.js"></script>
</body>
</html>

