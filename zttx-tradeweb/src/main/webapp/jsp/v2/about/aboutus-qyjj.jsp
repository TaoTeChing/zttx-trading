<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>关于我们-企业简介</title>
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
        <jsp:param value="2" name="m"/>
        <jsp:param value="2" name="n"/>
        <jsp:param value="1" name="k"/>
    </jsp:include>
    <div class="main">
        <div class="qyjj-pic"></div>
        <div class="qyjj-content">
            <div style="margin-top: 50px;">
                <img src="${res}/images/fronts/about/aboutus_qyjj02.png" style="float: left;padding-right: 29px;">
                <div class="qyjj-p" style="float: left">
                    <p>浙江天下商邦科技股份有限公司，是营运“8637品牌超级代理”平台的互联网科技服务型企业。</p>
                    <p>8637品牌超级代理，是零中间商的网络直供平台，创造性的B2B2C模式以互联网科技，实现品牌商与终端店铺的直接对接，打通品牌商和终端商之间的物流、现金流及信息流， 高效整合传统渠道产销链，为品牌商和终端商插上互联网的翅膀，使品牌商、终端商、消费者多方受益。</p>
                    <p>天下商邦已在国内20个省市建立超过100家服务机构，上万名推广专员服务于全国近百万的品牌商与终端商，同时天下商邦每年持续投入亿元的推广费用，确保每一个合作伙伴都能够有效的与潜在客户充分接洽，直至达成商业合作。</p>
                    <p>天下商邦拥有一支100多人的研发团队，汇集众多国内一线技术精英，每年研发投入数以亿计，8637品牌超级代理平台、企业ERP系统、门店招财猫系统、手机APP系统，这些创造性的产品极大的促进了传统商业渠道模式的变革。</p>
                    <p>天下商邦致力于推动信息科技根植于传统商业领域，通过对渠道体系的改善，影响中国传统渠道商业模式的转型，并积极促进企业为提升竞争力展开各项变革，以渠道模式的创新，实现中国传统制造业转型。无论是广大的生产企业或品牌商，还是更为众多的终端商，都在这一过程中获得极大的改善与提升，从而获取更多的商业利润。</p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
</div>
</body>
</html>