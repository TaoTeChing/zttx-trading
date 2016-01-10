<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>产品与服务</title>
    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/about/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/fronts/about/_header.jsp">
        <jsp:param value="1" name="m"/>
        <jsp:param value="1" name="n"/>
        <jsp:param value="1" name="k"/>
    </jsp:include>

    <div class="main">
        <div style="height: 95px"></div>
        <div class="daili-content">
            <img src="${res}/images/fronts/about/8637cjdl1.png" >
            <div class="content1">
                <h1>8637品牌超级代理</h1>
                <p>8637品牌超级代理，是零中间商的网络直供平台，创造性的B2B2C模式以互联网科技，实现品牌商与终端店铺的直接对接，打
                    通品牌商和终端商之间的物流、现金流及信息流， 高效整合传统渠道产销链，为品牌商和终端商插上互联网的翅膀，使品牌商、终端商、消费者多方受益。</p>
                <a href="#"><div class="cc" ><p>点击进入</p></div></a>
            </div>
        </div>
        <div class="bg">
            <div class="range">
                <div class=" factory-content">
                    <div class="content2">
                        <h1>8637品牌工厂店</h1>
                        <p>8637品牌工厂店以厂价直供模式进行销售，用全新体验模式冲击传统电商。重点解决传统电商运营中产品售前体验难、退换货时间成本高等问题，
                            工厂店创新推出的“比价神器”更是确保了产品售价低于淘宝等购物网站最低价。天下商邦目标打造工厂店+电商的创新性O2O模式，今年预计在全国铺设400-500家工厂店。</p>
                    </div>
                    <img src="${res}/images/fronts/about/8637gcd.png">
                </div>
            </div>
        </div>
        <div class="yueguang-content">
            <img src="${res}/images/fronts/about/yueguang.png" >
            <div class="content3">
                <h1>约逛 - 您身边的微店</h1>
                <p>约逛是一款可以给您带来全新O2O体验的手机APP产品，创新的将在线语音沟通与在线购物融为一体，由浙江天下商邦科技股份有限公司倾力打造。</p>
                <a href="#"><div class="cc" ><p>点击进入</p></div></a>
            </div>
        </div>
        <div class="bg">
            <div class="range">
                <div class=" zhpp-content">
                    <div class="content4">
                        <h1>智慧品牌总部系统</h1>
                        <p>智慧品牌管理系统，帮你管产品、管销售、管客流、管门店、管渠道...统统都管。</p>
                        <a href="#"><div class="cc" ><p>点击进入</p></div></a>
                    </div>
                    <img src="${res}/images/fronts/about/zhpp_03.png">
                </div>
            </div>
        </div>
        <div class="zhmd-content">
            <img src="${res}/images/fronts/about/zhmd.png" >
            <div class="content5">
                <h1>智慧门店管理系统</h1>
                <p>智能无线红外人体感应
                    <br>
                    管采购、管销售、管财务、管库存、管会员、管客流、管促销、 管员工、管门店...
                </p>
                <a href="#"><div class="cc" ><p>点击进入</p></div></a>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
</div>
</body>
</html>