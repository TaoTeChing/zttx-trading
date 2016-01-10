<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>品牌爆款 - 8637品牌超级代理</title>
    <meta name="keywords" content="服装爆款,品牌爆款,爆款产品,爆款,8637品牌超级代理"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="/styles/explosion/explosion-list.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background: #fff;">
<div class="container">
	<%--新增页面：我是经销商 列表页--%>
    <jsp:include page="${ctx}/common/top" />
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="6" name="m"/>
    </jsp:include>
    <%-- nav end --%>
<div class="em100 clearfix mb20">
    <%--面包屑导航 后添加的 提到了导航，先注释--%>
    <%--<div class="info-bread-nav" style="height: 30px;">
        <a href="${ctx}/">首页</a>
        <span style="color: #999;">&gt;&gt;</span>
        <a href="${ctx}/common/dealer">我是终端商</a>
        <span style="color: #999;">&gt;</span>
        <span>爆款列表页</span>
    </div>--%>

    <div class="explosion-slide">
        <tags:index_staticHtml webDefTemplate="${fns:getDocument(index_dealer_explosionList_focus)}"></tags:index_staticHtml>
    </div>
    <div class="explosion-sidebar fr" id="sidebar">
        <%--<div class="sidebar-common">
            <div class="title">
                爆款标签
            </div>
            <ul class="inline-float sidebar-common-content">
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
                <li><a href="#">大码女装</a></li>
            </ul>
        </div>--%>
        <div class="explosion-col mt20">
            <div class="explosion-col-head">
                <h3>交易会推荐</h3>
            </div>
            <div class="explosion-col-content">
                <ul class="sidebar-deal mt10">
                    <c:forEach var="obj" items="${tradeMeetingList}" varStatus="status" begin="0" end="4">
                        <c:set value="${fns:getBrandsIdSubDomain(obj.brandsId)}" var="domain"></c:set>
                        <li>
                            <div class="clearfix">
                                <span class="fl">${status.index + 1}</span>
                                <a class="deal-link fl" href="http://${domain}${zttx}/deal?meetId=${obj.refrenceId}" title="${obj.meetName}" target="_blank">${obj.meetName}</a>
                            </div>
                            <div class="pic">
                                <a href="http://${domain}${zttx}/deal?meetId=${obj.refrenceId}" target="_blank" title="${obj.meetName}">
                                    <img src="${res}${obj.meetLogo}"  width="230" height="140"/>
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="explosion-main">
        <ul class="inline-float explosion-product mt20">
        	<c:forEach items="${result.getList()}" var="actPro">
        	<li>
                <div class="pic">
                	<a href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank" title="${actPro.productTitle}"><img src="${res}${fns:getImageDomainPath(actPro.productImage,440,440)}" width="250" height="250"></a>
                </div>
                <div class="detail">
                    <h4 class="title"><a href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank">${actPro.productTitle}</a></h4>
                    <%-- 未开放 --%>
           			<c:if test="${empty actPro.productMap.message}">
           				   <p class="unempower"></p>
           			</c:if>
           			<%-- 已开放 --%>
                    <c:if test="${not empty actPro.productMap.message}">
                    	<p class="price"><fmt:formatNumber value="${actPro.productMap.price}" type="currency" pattern="0.00" /></p>
                    </c:if>
                    	<p class="time">品牌商承诺 全网最低价 仅在8637</p>
                    	<c:forEach items="${actPro.productList}" var="obj" begin="0" end="1">
           					<p><i class="icon ${obj.style}"></i>${obj.columnName}：<fmt:formatNumber value="${obj.price}" type="currency" pattern="0.00" />元</p>
           				</c:forEach>
                    	<p><i class="icon icon-shengdai"></i>省代价格：<fmt:formatNumber value="${actPro.provincePrice}" type="currency" pattern="0.00" />元</p>

                    <p class="mt10">
              			<c:set value="${fns:getBrandsIdSubDomain(actPro.brandsId)}" var="domain"></c:set>
                        <%--<a class="ts-button ts-button-white" href="javascript:;"><i class="icon icon-message-s"></i>约逛咨询</a>--%>
                        <c:if test="${empty actPro.productMap.message}">
                             <%--
                            <a class="ts-button ts-button-disabled" href="javascript:;"><i class="icon icon-file"></i>立即购买</a>
                             --%>
                            <a class="ts-button ts-button-query" href="${ctx}/market/product/${actPro.productId}" target="_blank">我要询价</a>
                        </c:if>
                        <c:if test="${not empty actPro.productMap.message}">
                            <a class="ts-button ts-button-orange ml10" href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank"><i class="icon icon-file"></i>立即购买</a>
                        </c:if>
                    </p>
                </div>
            </li>
        	</c:forEach>
        </ul>
        <form:form id="dealerShopEnvForm" method="get">
        <tags:page form="dealerShopEnvForm" page="${result.page}"/>
        </form:form>
    </div>
</div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
<div class="fixed-scroll">
    <ul>
        <li style="display:none;">
            <a href="#">
                <i class="exp-icons exp-icons-bk"></i>
                <p>爆款</p>
            </a>
        </li>
        <li>
            <a href="${ctx}/meet/deal">
                <i class="exp-icons exp-icons-jyh"></i>
                <p>交易会</p>
            </a>
        </li>
        <li>
            <a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw" target="_blank">
                <i class="exp-icons exp-icons-kf"></i>
                <p>在线客服</p>
            </a>
        </li>
        <li style="display: none;">
            <a href="#top" id="scrollTop">
                <i class="exp-icons exp-icons-fh"></i>
                <p>返回顶部</p>
            </a>
        </li>
    </ul>
</div>
<!-- // footer -->
</div>
<script src="/scripts/jquery.min.js" type="text/javascript"></script>
<script src="/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="/scripts/seajs_config.js" type="text/javascript"></script>
<script src="/scripts/common/base-init.js"></script>
<script>
    //右侧固定
    //var dOffset = $('#sidebar').offset();

    /*if( $(document).scrollTop() >= dOffset.top ){
        $('#sidebar').css({
            'position': "fixed",
            "top": 0,
            "left": dOffset.left,
            "z-index": 99
        })
    }*/

    /*$(window).scroll(function () {
        if( $(document).scrollTop() >= dOffset.top ){
            $('#sidebar').css({
                'position': "fixed",
                "top": 0,
                "left": dOffset.left,
                "z-index": 99
            });
        }else if( $(document).scrollTop() < dOffset.top ){
            $('#sidebar').css({
                'position': "relative",
                'left': 0
            });
        }
    });*/

    seajs.use(["slide"],function(Slide){
        var length = $(".explosion-slide .explosion-slide-main li").length;
        if(length > 0){
            var slide = new Slide({
                element: '.explosion-slide',
                trigger: '',
                panels: '.explosion-slide .explosion-slide-main li',
                effect: 'scrollx',
                activeIndex: 0
            }).render();
        }
    });

    //右侧固定框
    var ph = $(window).innerHeight();

    $("#scrollTop").click(function () {
        $('html,body').animate({ scrollTop: 0 }, 500);
    });

    $(window).scroll(function(){
        var _st = $(document).scrollTop();
        if(_st>100){
            $(".fixed-scroll li:last").show();
        }
        if(_st <= 100){
            $(".fixed-scroll li:last").hide();
        }
    });
    if (/msie 6\.0/i.test(navigator.userAgent.toLowerCase())) {
        //IE6 单独处理
        $(".fixed-scroll").css({position:"absolute",top: $(window).scrollTop() + $(window).height() - 450});
        var flag = true;
        $(window).scroll(function () {
            //console.log($(window).scrollTop() + 100);
            if(flag == false){
                return;
            }
            if(flag = true){
                setTimeout(function(){
                    $(".fixed-scroll").css({top: $(window).scrollTop() + $(window).height() - 450});
                    flag = true;
                },618);
            }
            flag = false;
        });
    }
</script>
</body>
</html>
