<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>智慧品牌管理系统-8637品牌超级代理产品中心</title>
    <link rel="stylesheet" href="${res}/styles/fronts/soft/erp/index.css"/>
    <link rel="stylesheet" href="${res}/styles/fronts/about/animate.css"/>
</head>
<body>
<div class="main clearfix">
    <jsp:include page="_header.jsp"/>
    <div class="banner">
        <div class="banner-center">
            <div class="erp-banner">
                <img class="e-b-img01 s-animated moveLeft" src="${res}/images/fronts/soft/erp/brand_img1.png"/>
            </div>
            <div class="banner-downdes" style="color: #d9d9d9;">
                <h1 class="s-animated moveRightDelay" title="智慧品牌管理系统">
                    <img src="${res}/images/fronts/soft/erp/brand-title.png" alt="智慧品牌管理系统"/></h1>

                <p class="mt5 s-animated moveLeftDelay">
                    <span>智慧品牌管理系统，帮你管产品、管销售、管客流、管门店、管渠道...统统都管。</span>
                </p>

                <p class="banner-downdes-suit s-animated moveRightDelay">
                    <img src="${res}/images/fronts/soft/erp/erp-8637.png" alt="8637"/>
                    智慧品牌总部系统<br/>
                    适用于：windows2000/XP/WIN7/WIN8
                </p>

                <p class="banner-downdes-update s-animated moveRightDelay">2015-07-17发布Ver.1.1.0.0</p>
                <a class="dl-btn s-animated moveLeftDelay" href="${res}/upload/soft/drp/Setup1.1.0.0.exe"
                   target="_blank">立即下载</a>
                <a class="hy-btn ml10 s-animated moveLeftDelay" href="${res}/common/register" target="_blank">开通会员服务</a>
            </div>
        </div>
        <div class="slide-center">
            <ul class="slide-center-ul clearfix">
                <li class="slide-center-item active"><a href="javascript:;"></a>
                </li>
                <li class="slide-center-item"><a href="javascript:;"></a>
                </li>
            </ul>
        </div>
    </div>
    <ul class="slide-contain">
        <li class="slide-contain-item"
            style="background: url(${res}/images/fronts/soft/erp/branderp_b2.jpg) no-repeat;"></li>
        <li class="slide-contain-item"
            style="background: url(${res}/images/fronts/soft/erp/branderp_b1.jpg) no-repeat; opacity: 0; filter:alpha(opacity=0);"></li>
    </ul>
</div>
<div class="brand-manager clearfix">
    <dl class="manager-item m-i-jbgl">
        <dt></dt>
        <dd class="title">基本管理</dd>
        <dd class="des">集结各种单据打印模板管理，发货地址管理，产品管理，一步操作，方便快捷。</dd>
    </dl>
    <dl class="manager-item m-i-ddgl">
        <dt></dt>
        <dd class="title">订单管理</dd>
        <dd class="des">同步8637品牌超级代理平台订单，快速打印配货单、拣货单、快递单，一步发货。</dd>
    </dl>
    <dl class="manager-item m-i-mdjk">
        <dt></dt>
        <dd class="title">门店监控</dd>
        <dd class="des">实时监控加盟门店运行状况，掌握商品的市场运行情况。</dd>
    </dl>
    <dl class="manager-item m-i-klfx">
        <dt></dt>
        <dd class="title">客流分析</dd>
        <dd class="des">进店人员分析统计，顾客消费分析统计，成交率、转化率综合大数据分析。</dd>
    </dl>
    <dl class="manager-item m-i-sjbb">
        <dt></dt>
        <dd class="title">数据报表</dd>
        <dd class="des">多功能店堂监控影像，支持多平台实时查看，掌握店内动态，了解在店内消费实情。</dd>
    </dl>
    <dl class="manager-item m-i-crm">
        <dt></dt>
        <dd class="title">CRM管理</dd>
        <dd class="des">邀请终端加盟品牌，管理合作终端，扩展渠道建设。</dd>
    </dl>
</div>
<div class="recomment">
    <div class="common-tit">
        <h3 class="title">热门推荐</h3>
    </div>
    <div class="rec-list">
        <ul class="clearfix">
            <li class="zc-erp">
                <a href="/soft/erp">
                    <div class="info">
                        <div class="content">
                            <div class="pic"></div>
                            <div class="title">智慧门店管理系统</div>
                            <div class="new">立即下载</div>
                        </div>
                    </div>
                </a>
            </li>
            <li class="gg">
                <a href="/soft/app">
                    <div class="info">
                        <div class="content">
                            <div class="pic"></div>
                            <div class="title">约逛手机APP</div>
                            <div class="new">立即下载</div>
                        </div>
                    </div>
                </a>
            </li>
            <li class="br-erp">
                <a href="javascript:void(0);">
                    <div class="info">
                        <div class="content">
                            <div class="pic"></div>
                            <div class="title">智慧品牌总部系统</div>
                            <div class="new">立即下载</div>
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/fronts/soft/erp/index.js"></script>
<script>
    $(document).ready(function () {
        var Num = 0;

        function imgChange(Num) {//图片变化处理
            $(".slide-contain li").eq(Num).addClass("active").stop().animate({
                opacity: 1
            }, 600).siblings().stop().animate({
                opacity: 0
            }, 600);
        }

        $(".slide-center").on("click", ".slide-center-item", function () {
            var index = $(this).index();
            Num = index;
            $(this).addClass("active").siblings().removeClass("active");
            imgChange(index);
        });

        $(".recomment li").hover(function () {
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        });
    });
</script>
</body>
</html>
