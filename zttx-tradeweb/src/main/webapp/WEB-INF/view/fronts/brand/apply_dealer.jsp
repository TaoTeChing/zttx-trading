<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>品牌商入驻 - 加盟入驻 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌加盟,品牌入驻"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/apply/index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <%--header部分和首页不一样--%>
    <div class="header-round">
        <div class="header" style="z-index: 216;">
            <div class="logo">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="nav">
                <ul>
                    <li><a href="${ctx}/brandJoin" class="hover">品牌商入驻</a></li>
                    <li><a href="${ctx}/about">了解品牌超级代理</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="banner">
            <div class="banner-main">
                <a href="${ctx}/common/register?t=1">注册账号</a>
            </div>
        </div>
        <%--banner end--%>
        <div class="process">
            <div class="process-main">
                <div class="m-title">
                    <h3><span>品牌商</span><em>入驻流程</em><i>&nbsp;</i></h3>
                </div>
                <div class="fore">
                    <dl class="list">
                        <dt class="pic"><i class="icon_01"></i></dt>
                        <dd class="tit">在线注册</dd>
                        <dd class="des">
                            <p>注册品牌商会员</p>
                            <p>账号按要求填写资料，在线签署</p>
                            <p>《服务协议》,注册成功之后进行下一步骤</p>
                        </dd>
                    </dl>
                    <dl class="list">
                        <dt class="pic"><i class="icon_02"></i></dt>
                        <dd class="tit">提交资质</dd>
                        <dd class="des">
                            <p>根据要求提交入驻时所需的资料</p>
                        </dd>
                    </dl>
                    <dl class="list">
                        <dt class="pic"><i class="icon_03"></i></dt>
                        <dd class="tit">资质审核</dd>
                        <dd class="des">
                            <p>8637品牌超级代理会在资料审核后</p>
                            <p>进行系统提示或电话通知</p>
                        </dd>
                    </dl>
                    <dl class="list">
                        <dt class="pic"><i class="icon_04"></i></dt>
                        <dd class="tit">签订合同</dd>
                        <dd class="des">
                            <p>与8637品牌超级代理签署《合作协议》</p>
                        </dd>
                    </dl>
                    <dl class="list" style="background: none;">
                        <dt class="pic"><i class="icon_05"></i></dt>
                        <dd class="tit">品牌合作</dd>
                        <dd class="des">
                            <p>寻找希望加盟的品牌商，</p>
                            <p>申请加盟，等待品牌商审核</p>
                            <p>审核通过后与品牌商进行交易</p>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
        <%--process end--%>
        <div class="natural">
            <div class="natural-main">
                <div class="m-title">
                    <h3><span>品牌商</span><em>入驻资质</em><i>&nbsp;</i></h3>
                </div>
                <div class="list">
                    <ul>
                        <li>持有并提交<em>《企业法人营业执照》</em>副本的原件扫描件(须完成有效年检且所售商品属于其经营范围内)；</li>
                        <li>持有并提交<em>《税务登记证》</em>的原件扫描件<em>（国税、地税均可）</em>；</li>
                        <li>持有并提交<em>《组织机构代码证》</em>的原件扫描件；</li>
                        <li>法定代表人及负责人身份证正反面原件扫描件；</li>
                        <li>银行开户许可证扫描件；</li>
                        <li>由国家商标总局颁发的<em>商标注册证或商标注册申请受理通知书</em>（若办理过变更、转让、续展，请一并提供商标总局颁发的变更、转让、续展证明或受理通知书）；</li>
                        <li>若由品牌权利人授权入驻成为品牌商，须提供<em>品牌权利人出具的排他授权书</em>（如品牌权利人为自然人，则须同时提供其亲笔签名的身份证复印件）；</li>
                        <li>若入驻成为多个自有品牌的品牌商，须提供品牌属于同一实际控制人的证明材料。</li>
                    </ul>
                </div>
            </div>
        </div>
        <%--natural end--%>
        <div class="rules">
            <div class="rules-main">
                <div class="m-title">
                    <h3><span>品牌商</span><em>加盟规则</em><i>&nbsp;</i></h3>
                </div>
                <div class="tab" id="tab-view">
                    <div class="tab-menu">
                        <ul class="rule-nav inline-float">
                            <li class="active"><a href="javascript:;">入驻</a></li>
                            <li><a href="javascript:;">招商管理</a></li>
                            <li><a href="javascript:;">违规处理</a></li>
                            <li><a href="javascript:;">退出</a></li>
                        </ul>
                        <div class="line"></div>
                    </div>
                    <div class="tab-con inner">
                        <div>
                            <dl>
                                <dt><span class="num">1</span>账户</dt>
                                <dd>1.1 按照8637品牌超级代理提示填写相关信息进行账户注册。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">2</span>资质</dt>
                                <dd>2.1 品牌商入驻8637品牌超级代理时资质要求：</dd>
                                <dd>2.1.1 持有并提交《企业法人营业执照》副本的原件扫描件(须完成有效年检且所售商品属于其经营范围内)；</dd>
                                <dd>2.1.2 持有并提交《税务登记证》的原件扫描件（国税、地税均可）；</dd>
                                <dd>2.1.3 持有并提交《组织机构代码证》的原件扫描件；</dd>
                                <dd>2.1.4 法定代表人及负责人身份证正反面原件扫描件；</dd>
                                <dd>2.1.5 银行开户许可证扫描件；</dd>
                                <dd>2.1.6 由国家商标总局颁发的商标注册证或商标注册申请受理通知书（若办理过变更、转让、续展，请一并提供商标总局颁发的变更、转让、续展证明或受理通知书）；</dd>
                                <dd>2.1.7 若由品牌权利人授权入驻成为品牌商，须提供品牌权利人出具的排他授权书（如品牌权利人为自然人，则须同时提供其亲笔签名的身份证复印件）；</dd>
                                <dd>2.1.8 若入驻成为多个自由品牌的品牌商，须提供品牌属于同一实际控制人的证明材料。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">3</span>资质审核</dt>
                                <dd>3.1 在品牌商提交资质后，平台将对该申请资料进行审核，并结合终端商需求、经营状况、服务水平等因素决定是否审核通过。</dd>
                                <dd>3.2 品牌商须提交公司目前经营状态的基本信息（包括但不限于基本信息、经营类目、经营品牌等）以方便8637品牌超级代理审核。</dd>
                                <dd>3.3 品牌商提交资质后，等待8637品牌超级代理进行资质审核，审核的结果会在系统中进行提示并电话通知，请及时登录系统查看。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">4</span>签订合同</dt>
                                <dd>4.1 品牌商审核通过后，工作人员会联系并确认合作细节。如信息确认无误后，工作人员会寄出合同文件。</dd>
                                <dd>4.2 品牌商收到合同文件后，确认合同细节后须盖章签字，同时按照流程寄回8637品牌超级代理，同时寄回所要求的各类纸质资质文件（均需加盖企业公章并保证内容清晰可辨）。</dd>
                                <dd>4.3 8637品牌超级代理收到品牌商寄回的资料后，平台工作人员会尽快进行复审，审核通过后，即可进行交费。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">5</span>关于费用</dt>
                                <dd>5.1 品牌商根据系统提示或工作人员提示交纳相关费用。</dd>
                                <dd>5.2 品牌商须上传交费回执单电子版，8637品牌超级代理据此进行财务确认。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">6</span>成功入驻</dt>
                                <dd>6.1 8637品牌超级代理确认品牌商缴费成功后，即品牌商成功入驻，同时会将合同寄回供品牌商留存。</dd>
                                <dd>6.2 品牌商成功入驻后，可登入品牌商管理中心进行发布产品、设置产品线、发布招商信息、管理经销商等相关运营操作。</dd>
                            </dl>
                        </div>
                        <div style="display: none;">
                            <dl>
                                <dt><span class="num">1</span>须知</dt>
                                <dd>1.1 8637品牌超级代理有权根据终端商需求、公司经营状况、服务水平等其他因素退回申请。</dd>
                                <dd>1.2 8637品牌超级代理有权在品牌商申请入驻及后续经营阶段要求提供其他资质。</dd>
                                <dd>1.3 8637品牌超级代理结合各行业发展动态、国家相关规定及平台用户需求，有权在任何需要的时候更新和修改相关规则并公告。</dd>
                                <dd>1.4 品牌商应如实提供企业运营的主体及经营情况等相关信息。</dd>
                                <dd>1.5 品牌商确保在入驻流程及后续经营阶段中提供的相关资质和信息的真实性，有效性。一旦发现或证实提供虚假信息或资质，8637品牌超级代理将终止与该品牌商的合作并根据与品牌商签署的相关协议进行处理。</dd>
                                <dd>1.6 8637品牌超级代理暂不接受个体工商户的入驻申请，品牌商须为正式注册企业，亦暂时不接受非中国大陆注册企业的入驻申请。</dd>
                                <dd>1.7 8637品牌超级代理暂不接受未取得国家商标总局颁发的商标注册证或商标受理通知书的品牌入驻。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">2</span>8637品牌超级代理保证金/平台使用费/费率标准</dt>
                                <dd>2.1 保证金</dd>
                                <dd>2.1.1 保证金：品牌商向8637品牌超级代理缴纳的用以保证品牌商规范运营及对产品和服务质量进行担保的金额。当品牌商发生违约、违规行为时，8637品牌超级代理有权按照与品牌商签署的协议以及8637品牌超级代理相关规则扣除相应金额的保证金作为违约金或作为给予终端商的赔偿。</dd>
                                <dd>2.1.2 保证金的补足、退还、扣除等依据品牌商签署的相关协议及8637品牌超级代理平台规则约定办理。</dd>
                                <dd>2.2 平台使用费</dd>
                                <dd>2.2.1 使用费：品牌商依照与8637品牌超级代理签署的相关协议使用和享受8637品牌超级代理提供的各项技术支持及服务时须缴纳的固定费用。品牌商须在入驻成功时一次性缴纳相应服务期间的费用。</dd>
                                <dd>2.2.2 品牌商主动要求停止平台服务的不返还平台使用费。</dd>
                                <dd>2.2.3 品牌商在使用平台服务时发生违规行为或被视为的资质造假而被清退的不返还平台使用费。</dd>
                                <dd>2.2.4 每个品牌商的平台使用费将以年度预缴方式计算并缴纳。</dd>
                                <dd>2.3 费率标准</dd>
                                <dd>2.3.1 品牌商根据其达成的交易销售额缴纳一定比例（简称“费率”）的技术支持服务费。</dd>
                            </dl>
                        </div>
                        <div style="display: none;">
                            <dl>
                                <dt><span class="num">1</span>违规处理</dt>
                                <dd>1.1 用户发生违规行为的，其违规行为应当纠正，并扣以一定分值，扣分累计达到节点，将进行节点处罚。违规扣分在每年的十二月三十一日二十四时清零。</dd>
                                <dd>1.2 8637品牌超级代理有权进行单方认定并视情节严重程度采取市场管控措施，给予警告、账号限权直至清退等处罚。</dd>
                                <dd>1.3 用户因不同违规行为会被扣以不同的分数，多次的违规行为扣分会被累加，当扣分达到一定分值就会被执行节点处罚，违规扣分累计100分时，8637品牌超级代理有权清退该品牌商。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">2</span>违规行为</dt>
                                <dd>2.1虚假交易，品牌商通过不正当方式或手段提高账户信誉评分或交易积分，妨害终端商权益的行为的，每次扣25分。8637品牌超级代理将删除虚假交易产生的信誉评分和交易积分，并根据视情节轻重对用户予以警告、账号限权直至账号关闭等处罚。虚假交易包括以下情形：</dd>
                                <dd class="level3">2.1.1 品牌商在8637品牌超级代理以不正当提升排序为目的，提供虚构、伪造的交易凭证或在线生成虚假交易数据的行为；</dd>
                                <dd class="level3">2.1.2 品牌商通过变更产品页面信息，或大幅度修改产品价格等违规手段提高商品销量；</dd>
                                <dd class="level3">2.1.3 其他利用不正当方式提高商品销量或提高信誉评分的行为，8637品牌超级代理视情节严重程度采取市场管控措施。</dd>
                                <dd>2.2 不实交易，品牌商在8637品牌超级代理发布的商品，但在与终端商交易的具体过程中，却以种种理由拒绝在网站平台进行交易。8637品牌超级代理根据实际情形予以警告、账号限权直至账号关闭等处罚，包括以下情形：</dd>
                                <dd class="level3">2.2.1 品牌商发布产品信息后，终端商发起线上交易，且交易状态为“等待终端商付款”，但品牌商以种种理由拒绝在线交易或取消在线交易，试图进行线下交易且成功在线下进行交易的，每次扣100分；</dd>
                                <dd class="level3">2.2.2 终端商对品牌商发布的产品信息进行联系咨询时，品牌商试图引导终端商在线下进行交易且成功在线下完成交易的，每次扣100分；</dd>
                                <dd class="level3">2.2.3 品牌商发布产品信息后，终端商发起线上交易，且交易状态为“等待终端商付款”，但品牌商以种种理由拒绝在线交易或取消在线交易，试图引导终端商进行线下交易但未成功在线下完成交易的，每次扣50分；</dd>
                                <dd class="level3">2.2.4 终端商对品牌商发布的产品信息进行联系咨询时，品牌商试图引导终端商在线下进行交易，但未成功在线下完成交易的，每次扣50分；</dd>
                                <dd class="level3">2.2.5 其他任何构成不实交易方式的情形。</dd>
                                <dd>2.3 延迟发货，是指除特殊商品外，品牌商未在终端商付款后的七十二小时内发货（定制、预售及其他特殊情形或双方另行约定发货时间的商品除外），妨害终端商权益的行为的，每次扣25分。品牌商的发货时间，以快递公司系统内记录的时间为准。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">3</span>违规申诉</dt>
                                <dd>3.1 针对违规行为的处罚，品牌商可向8637品牌超级代理网站提交违规申诉申请，品牌商提交申诉资料后将由8637品牌超级代理核实处理。</dd>
                                <dd>详见<a href="/rules/">《品牌商违规行为及处理办法》</a></dd>
                            </dl>
                        </div>
                        <div style="display: none;">
                            <dl>
                                <dt><span class="num">1</span>若您向8637品牌超级代理提出注销账户申请，经平台审核同意后，由8637品牌超级代理注销该用户账户，在账户被正式注销后即与8637品牌超级代理平台终止合作。</dt>
                            </dl>
                            <dl>
                                <dt><span class="num">2</span>在您申请注销该用户账户后，8637品牌超级代理仍有权：</dt>
                                <dd class="level3">2.1 保留您注册时的相关信息以及在8637品牌超级代理产生的的全部交易行为记录；</dd>
                                <dd class="level3">2.2 若您的账户在注销前在8637品牌超级代理上存在违法行为或违反本协议的行为，8637品牌超级代理仍可保留追究权利。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">3</span>在下列情况下，8637品牌超级代理有权通过注销用户账户的方式单方解除合作关系，且无须为此向您或者第三方承担任何责任：</dt>
                                <dd class="level3">3.1 如您违反本协议相关规定时，8637品牌超级代理有权暂停或终止您的账户在8637品牌超级代理的权限；</dd>
                                <dd class="level3">3.2 一经发现您的注册信息包含虚假内容，8637品牌超级代理即有权终止向该您提供平台服务；</dd>
                                <dd class="level3">3.3 8637品牌超级代理法律法规在修改或更新时，如您表示不愿接受新的服务协议条款的，8637品牌超级代理有权终止向您提供服务；</dd>
                                <dd class="level3">3.4 8637品牌超级代理认为需终止的其他情况。</dd>
                            </dl>
                            <dl>
                                <dt><span class="num">4</span>您在服务中止或终止之前已经与其他会员有已部分履行的买卖合同，8637品牌超级代理可以不删除该项交易，但8637品牌超级代理有权将相关情形通知您的交易对方。</dt>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--rules end--%>
        <div class="problems">
            <div class="problems-main">
                <div class="title clearfix">
                    <div class="fl">常见问题<i class="arrow"></i></div>
                    <div class="fr"><img src="${res}/images/fronts/apply/v2_tel.png" alt=""/>全国免费终端商服务热线：<em>400-111-8637</em> <span>找不到好的项目，来8637品牌超级代理这里有品牌直供价格</span></div>
                </div>
                <div class="list">
                    <ul class="inline-float">
                        <li>
                            <i class="num_1"></i>
                            <p><a href="${ctx}/help/info/8863FE71110140F8A76D64F4CA068A72" target="_blank">平台上面产品信息是否有假的？</a></p>
                            <p><a href="${ctx}/help/info/8D6DA13919D84481882BD1AD5BCCE105" target="_blank">8637品牌超级代理会员注册流程？</a></p>
                            <p><a href="${ctx}/help/info/74C8FD67212843E98C58DEF2A2716C50" target="_blank">我已经注册了账号，如何登录？</a></p>
                        </li>
                        <li>
                            <i class="num_2"></i>
                            <p><a href="${ctx}/help/info/20720F605FC140B0B6E7C2B5823AA5B7" target="_blank">什么是8637品牌超级代理？</a></p>
                            <p><a href="${ctx}/help/info/05067EAA2D414120A423CDDB8A34813E" target="_blank">怎样在平台寻找品牌？</a></p>
                            <p><a href="${ctx}/help/info/5CA219EFE7044946A18CAEAC9F166509" target="_blank">如何查看账户余额与交易记录？</a></p>
                        </li>
                        <li>
                            <i class="num_3"></i>
                            <p><a href="${ctx}/help/info/FA3C9FCFBE6C4DBB8CA660625ABE2604" target="_blank">在哪里可以查看待发货的订单？</a></p>
                            <p><a href="${ctx}/help/info/48D79B96710E4AEB9EBAB712051F36AA" target="_blank">在哪查看我的订单？</a></p>
                            <p><a href="${ctx}/help/info/10B3348D626F4CC5A84E9CCF0D074E5E" target="_blank">如何修改已经通过认证的手机号码？</a></p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <%--problems end--%>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/apply/apply"], function (Index) {
        //Index.index.init();
    });
</script>
</body>
</html>