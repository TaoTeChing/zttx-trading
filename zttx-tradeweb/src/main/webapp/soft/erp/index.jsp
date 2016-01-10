<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>智慧门店管理系统-8637品牌超级代理产品中心</title>
    <link rel="stylesheet" href="${res}/styles/fronts/soft/erp/index.css"/>
    <link rel="stylesheet" href="${res}/styles/fronts/about/animate.css"/>
</head>
<body>
<div class="main clearfix">
    <jsp:include page="_header.jsp" />
    <div class="banner">
        <div class="banner-center">
            <div class="erp-banner">
                <img class="e-b-img01 s-animated moveLeft" src="${res}/images/fronts/soft/erp/erp-computer.png" alt="智慧门店管理系统"/>
                <img class="e-b-img02 s-animated moveRight" src="${res}/images/fronts/soft/erp/erp-camera.png" alt="360°旋转摄像头"/>
                <img class="e-b-img03 s-animated moverDelay" src="${res}/images/fronts/soft/erp/erp-mouse.png" alt="智能无线红外人体感应"/>
                <%--<img class="e-b-img04 s-animated scaleIn" src="${res}/images/fronts/soft/erp/erp-ring.png" alt="mouse"/>
                <img class="e-b-img04" src="${res}/images/fronts/soft/erp/erp-ring.png" alt="mouse"/>--%>
            </div>
            <div class="banner-downdes">
                <h1 class="s-animated moveRightDelay" title="智慧门店管理系统"><img src="${res}/images/fronts/soft/erp/erp-title.png" alt="智慧门店管理系统"/></h1>
                <p class="mt5 s-animated moveLeftDelay">
                    <span class="b-d-tit">智能无线红外人体感应</span><br />
                    <span class="b-d-des">管采购、管销售、管财务、管库存、管会员、管客流、管促销、
                    管员工、管门店...</span>
                </p>
                <p class="banner-downdes-suit s-animated moveRightDelay">
                    <img src="${res}/images/fronts/soft/erp/erp-8637.png" alt="8637"/>
                    智慧门店管理系统，店铺的那点事儿统统都可以帮您管理<br />
                    适用：WIN2K/XP/WIN7/WIN8
                </p>
                <p class="banner-downdes-update s-animated moveRightDelay">2015-10-21发布Ver.3.0.0.1</p>
                <a class="dl-btn s-animated moveLeftDelay" href="http://images.8637.com/upload/soft/erp/Setup3.0.0.1.exe" target="_blank">立即下载</a>
                <a class="hy-btn ml10 s-animated moveLeftDelay" href="${ctx}/common/register" target="_blank">开通会员服务</a>
            </div>
        </div>
        <div class="slide-center">
            <ul class="slide-center-ul clearfix">
                <li class="slide-center-item active"><a href="javascript:;"></a>
                </li>
                <li class="slide-center-item"><a href="javascript:;"></a>
                </li>
                <%--<li class="slide-center-item2 fl" style="width: 50px; height: 20px; background: #000;"><a href="javascript:;" style="width:20%;display: inline-block; height: 10px; background: #fff;"></a>
                </li>
                <li class="slide-center-item2 fl" style="width: 50px; height: 20px; background: #000;"><a href="javascript:;" style="width:30%;display: inline-block; height: 10px; background: #fff;"></a>
                </li>--%>
            </ul>
        </div>
    </div>
    <ul class="slide-contain">
        <li class="slide-contain-item" style="background: url('${res}/images/fronts/soft/erp/banner1.jpg') no-repeat;"></li>
        <li class="slide-contain-item" style="background: url('${res}/images/fronts/soft/erp/banner2.jpg') no-repeat; opacity: 0; filter:alpha(opacity=0);"></li>
    </ul>
</div>
<div class="manager">
    <div class="manager-cen clearfix">
        <dl class="manager-item m-i-jxc">
            <dt class="manager-dt"></dt>
            <dd class="title">进销存管理</dd>
            <dd class="des">进货、销售、库存即时统计与管理，一键追单，库存报警双向<br/>提醒，数据云分析。</dd>
        </dl>
        <dl class="manager-item m-i-dm">
            <dt class="manager-dt"></dt>
            <dd class="title">店铺商学院</dd>
            <dd class="des">提供最新、最全的销售秘籍，让员工提升销售技巧，让终端店<br/>铺卖的更多，卖的更好。</dd>
        </dl>
        <dl class="manager-item m-i-klfx">
            <dt class="manager-dt"></dt>
            <dd class="title">客流分析</dd>
            <dd class="des">进店人员分析统计，顾客消费分析统计，成交率，<br/>转化率综合大数据分析。</dd>
        </dl>
        <dl class="manager-item m-i-yggl">
            <dt class="manager-dt"></dt>
            <dd class="title">员工管理</dd>
            <dd class="des">员工考勤管理、员工绩效考核、员工技能培训、店<br/>长互动交流。</dd>
        </dl>
        <dl class="manager-item m-i-dpdt">
            <dt class="manager-dt"></dt>
            <dd class="title">店铺动态监控</dd>
            <dd class="des">多功能店堂监控影像，支持多平台实时查看，掌握<br/>店内动态，了解在店内消费实情。</dd>
        </dl>
        <dl class="manager-item m-i-syjs">
            <dt class="manager-dt"></dt>
            <dd class="title">收银结算管理</dd>
            <dd class="des">POS收银台，每日销售账单结算、货品盘点，核算<br/>员工销售提成，核算货品利润。</dd>
        </dl>
        <dl class="manager-item m-i-hydx">
            <dt class="manager-dt"></dt>
            <dd class="title">会员定向营销</dd>
            <dd class="des">针对会员喜好定向营销、会员信息管理、新品促销、会员语音<br/>营销。</dd>
        </dl>
        <dl class="manager-item m-i-wgsj">
            <dt class="manager-dt"></dt>
            <dd class="title">约逛手机商圈</dd>
            <dd class="des">约逛手机客户端结合智慧门店管理系统让终端门店建立起小模<br/>式、区域性、体验式的微型电子商务平台。</dd>
        </dl>
        <div class="m-c-bg">

        </div>
    </div>
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
                            <div class="new">敬请期待...</div>
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="/WEB-INF/view/include/footer.jsp" />
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/fronts/soft/erp/index.js"></script>
<script>
    $(document).ready(function(){
        var Num = 0;
        function imgChange(Num){//图片变化处理
            $(".slide-contain li").eq(Num).addClass("active").stop().animate({
                opacity: 1
            },600).siblings().stop().animate({
                opacity: 0
            },600);
        }
        $(".slide-center").on("click",".slide-center-item",function(){
            var index = $(this).index();
            Num = index;
            $(this).addClass("active").siblings().removeClass("active");
            imgChange(index);
        });

        $(".recomment li").hover(function(){
            $(this).addClass("hover");
        },function(){
            $(this).removeClass("hover");
        });
    });
</script>
</body>
</html>
