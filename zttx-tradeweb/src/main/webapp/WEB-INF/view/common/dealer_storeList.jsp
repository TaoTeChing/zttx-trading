<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>我是终端商 - 品牌商名录 - 8637品牌超级代理</title>
    <meta name="keywords" content="我是终端商,品牌商名录,8637品牌超级代理" />
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/stores/index.css" rel="stylesheet"/>
    <link href="${res}/styles/search/index.css" rel="stylesheet"/>
</head>
<body>


<div class="container">

    <%--新增页面：我是经销商 列表页--%>
    <jsp:include page="${ctx}/common/top" />
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="3" name="m"/>
    </jsp:include>
    <%-- nav end --%>

    <div class="ts-container">
        <div class="info-bread-nav" style="height: 30px;">
            <a href="${ctx }/">首页</a>
            <span style="color: #999;">&gt;&gt;</span>
            <a href="${ctx }/common/dealer">我是终端商</a>
            <span style="color: #999;">&gt;</span>
            <span>品牌商名录</span>
        </div>
    </div>
    <div class="ts-container clearfix mb20">
        <div class="brand-col-l fl">
            <form:form  id="brandShopForm"  method="get">
            	<input type="hidden" name="dealMain" id="dealMain" value="${dealerStoreListModel.dealMain }"/>
                <input type="hidden" name="dealNo" id="dealNo" value="${dealerStoreListModel.dealNo }"/>
                <div class="classify classify-main clearfix">
                    <div class="classify-tit fl">行业</div>
                    <div class="classify-sel fl">
                        <ul class="classify-ul inline-block">
                            <li ${dealerStoreListModel.dealMain == 0 ? 'class="active"':''}>
                                <a href="${ctx}/common/dealer/storeList?&q=${dealerStoreListModel.q}">全部<i class="brandicons"></i></a>
                            </li>
                            <li ${dealerStoreListModel.dealMain == 1 ? 'class="active"':''}>
                                <a href="${ctx}/common/dealer/storeList?dealMain=1&q=${dealerStoreListModel.q}">服装服饰<i class="brandicons"></i></a>
                            </li>
                            <li ${dealerStoreListModel.dealMain == 2 ? 'class="active"':''}>
                                <a href="${ctx}/common/dealer/storeList?dealMain=2&q=${dealerStoreListModel.q}">鞋靴箱包<i class="brandicons"></i></a>
                            </li>
                            <li ${dealerStoreListModel.dealMain == 3 ? 'class="active"':''}>
                                <a href="${ctx}/common/dealer/storeList?dealMain=3&q=${dealerStoreListModel.q}">家纺<i class="brandicons"></i></a>
                            </li>
                            <li ${dealerStoreListModel.dealMain == 4 ? 'class="active"':''}>
                                <a href="${ctx}/common/dealer/storeList?dealMain=4&q=${dealerStoreListModel.q}">童装/母婴类<i class="brandicons"></i></a>
                            </li>
                        </ul>
                            <%--<div class="classify-moregroup inline-block">
                                <div class="inline-block classify-more js-brand-more">
                                    <a href="javascript:;">更多</a>
                                    <i class="brandicons icons-brand-morejian"></i>
                                </div>
                            </div>--%>
                        <div class="classify-btngroup">
                            <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-sure">确定</a>
                            <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-cancle">取消</a>
                        </div>
                    </div>
                </div>
                <!-- 一级分类结束 -->
                <c:if test="${not empty dealDicList}">
                    <div class="classify clearfix">
                        <div class="classify-tit fl">类别</div>
                        <div class="classify-sel fl">
                            <ul class="classify-ul inline-block">
                                <c:forEach var="obj" items="${dealDicList}" varStatus="status">
                                    <c:if test="${obj.dictValue==dealerStoreListModel.dealNo}">
                                        <li><a href="/common/dealer/storeList?dealNo=${obj.dictValue}&q=${dealerStoreListModel.q}&dealMain=${dealerStoreListModel.dealMain}"><b>${obj.dictValueName}</b></a></li>
                                    </c:if>
                                    <c:if test="${obj.dictValue!=dealerStoreListModel.dealNo}">
                                        <li><a href="/common/dealer/storeList?dealNo=${obj.dictValue}&q=${dealerStoreListModel.q}&dealMain=${dealerStoreListModel.dealMain}">${obj.dictValueName}<i class="brandicons"></i></a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                            <div class="classify-btngroup">
                                <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-sure">确定</a>
                                <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-cancle">取消</a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <%--<div class="classify">
                    <div class="classify-tit inline-block">二级分类</div>
                    <div class="classify-sel inline-block">
                        <ul class="classify-ul classify-height inline-block">
                            <li><a href="#">外套1<i class="brandicons"></i></a></li>
                            <li><a href="#">外套2<i class="brandicons"></i></a></li>
                            <li><a href="#">外套3<i class="brandicons"></i></a></li>
                            <li><a href="#">外套1<i class="brandicons"></i></a></li>
                            <li><a href="#">外套2<i class="brandicons"></i></a></li>
                            <li><a href="#">外套3<i class="brandicons"></i></a></li>
                            <li><a href="#">外套1<i class="brandicons"></i></a></li>
                            <li><a href="#">外套2<i class="brandicons"></i></a></li>
                            <li><a href="#">外套3<i class="brandicons"></i></a></li>
                            <li><a href="#">外套1<i class="brandicons"></i></a></li>
                            <li><a href="#">外套2<i class="brandicons"></i></a></li>
                            <li><a href="#">外套3<i class="brandicons"></i></a></li>
                        </ul>
                        <div class="classify-moregroup inline-block">
                            <div class="inline-block classify-more js-brand-more">
                                <a href="javascript:;">更多</a>
                                <i class="brandicons icons-brand-morejian"></i>
                            </div>
                        </div>
                        <div class="classify-btngroup">
                            <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-sure">确定</a>
                            <a href="javascript:;" class="ui-brand-btn ui-brand-btnmini js-btn-cancle">取消</a>
                        </div>
                    </div>
                </div>
                --%>
                <%--<div class="filter">
                    <div class="filter-box">
                        <a class="filter-sort filter-select" href="javascript:;">默认</a>
                    </div>
                    <div class="filter-box">
                        <a class="filter-sort filter-select filter-downstyle" href="javascript:;">销量<i class="brandicons icons-brand-greyarrow"></i></a>
                    </div>
                    <div class="filter-box">
                        <a class="filter-sort" href="javascript:;">时间<i class="brandicons icons-brand-greyarrow"></i></a>
                    </div>
                    <div class="filter-box">
                        <a class="filter-sort" href="javascript:;">点击数量<i class="brandicons icons-brand-greyarrow"></i></a>
                    </div>
                </div>
                --%>

                <ul class="isbrand-content">
                    <c:forEach var="brands" items="${paginateResult.list}" varStatus="status" >
                        <c:set value="${fns:getBrandsIdSubDomain(brands.id) }" var="domain"></c:set>
                        <li>
                            <div class="pic">
                                <a href="http://${domain}${zttx}"   target="_blank" title="${brands.brandName }">
                                    <c:if test="${not empty brands.recommendImage}">
                                        <img src="${res}${brands.recommendImage}" alt="" width="370" height="200"/>
                                    </c:if>
                                    <c:if test="${empty brands.recommendImage}">
                                        <img src="${res}${brands.proLogo}" alt="" width="370" height="200"/>
                                    </c:if>
                                </a>
                            </div>
                            <div class="detail">
                                <div class="isbrand-logo clearfix">

                                    <div class="fl"> <a href="http://${domain}${zttx}" target="_blank" title="${brands.brandName }"><img src="${res}${brands.brandLogo}" alt="" width="100" height="50"/></a></div>
                                    <div class="fl isbrand-content-title">
                                        <p class="name">
                                            <a href="http://${domain}${zttx}" target="_blank">${brands.brandName}</a>
                                            <c:if test="${brands.factoryStore == true}">
                                            <img class="ml5" style="vertical-align: sub;*vertical-align: middle;" src="/images/common/factory-brand.jpg" alt=""/>
                                            </c:if>
                                        </p>
                                        <p><a href="http://${domain}${zttx}" target="_blank" class="color666 js-company" data-dealTypeName="${brands.dealType}" data-regMoney="${brands.regMoney}" data-createTime='${fns:getTimeFromLong(brands.createTime,"yyyy-MM-dd")}' data-comAddress="${brands.comAddress}">${brands.comName}</a></p>
                                    </div>
                                    <div class="fr address">
                                        <p>${brands.provinceName}&nbsp;&nbsp;${brands.cityName}</p>
                                            <%--<p><i class="levelicons levelicons-vip"></i><i class="levelicons levelicons-rz ml5"></i></p>--%>
                                    </div>
                                </div>
                                <div class="isbrand-info clearfix">
                                    <div class="fl">
                                        <p class="clearfix">
                                            <span class="i-i-mainpro fl">主营产品：</span>
                                    <span class="i-i-pro fl" title="<c:forEach var="dealDic" items="${brands.dealDicList}" varStatus="status"><c:if test="${status.index > 0}"> | </c:if>${dealDic.dealName}</c:forEach>">
                                	<c:forEach var="dealName" items="${brands.dealName}" varStatus="status">
                                        <c:if test="${status.index > 0}">
                                            |
                                        </c:if>
                                        ${dealName}
                                    </c:forEach>
                                    </span>
                                        </p>
                                        <p>门店数量： <span><em class="s-number">${brands.networkNum}</em> 家</span> </p>
                                        <p>招募条件：
                                            <c:if test="${brands.dealBrand == 1}">
                                                <span>单独品牌专卖实体店</span>
                                            </c:if>
                                            <c:if test="${brands.dealBrand == 2}">
                                                <span>多品牌专营实体店</span>
                                            </c:if>
                                            <c:if test="${brands.dealBrand != 1 && brands.dealBrand != 2}">
                                                <span>不限</span>
                                            </c:if>
                                        </p>
                                    </div>
                                    <div class="fr">
                                        <p>注册资金： <span class="price">${brands.regMoney}</span> <span>万</span></p>
                                        <p>关注度： <span class="number">${brands.askNum}</span> <span>人</span></p>
                                        <p>合作店铺： <span class="number">${brands.applyNum}</span> <span>家</span></p>
                                    </div>
                                </div>
                                <div class="isbrand-group mt10">
                                    <!--   <a class="ts-button ts-button-white" href="javascript:;">约逛咨询</a> -->
                                    <c:if test="${brands.join != null}">
                                        <a class="ts-button ts-button-orange" href="#">已加盟</a>
                                    </c:if>
                                    <c:if test="${brands.join == null}">
                                        <c:set var="brandRecurit" value="${fns:isOpenBrandRecruit(brands.id) }"/>
                                        <c:if test="${brandRecurit}">
                                            <a class="ts-button ts-button-orange" href="http://${domain}${zttx}/recruit" target="_blank">申请加盟</a>
                                        </c:if>
                                        <c:if test="${!brandRecurit }">
                                            <a class="ts-button ts-button-disabled" >品牌招募书未开启</a>
                                        </c:if>
                                    </c:if>
                                    <a class="ts-button ts-button-otherwhite" href="http://${domain}${zttx}/product" target="_blank">进货</a>
                                    <a class="i-g-link" href="http://${domain}${zttx}" target="_blank" target="_blank">了解详情</a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                    <c:if test="${empty paginateResult.list}">
                        <div class="no-result">
                            <div class="n-r-say">
                                <p>抱歉！我们花了九牛二虎之力还是没找到您要找的</p>
                                <p style="font-size: 14px;"><span>"${filter.q}"</span>的搜索结果</p>
                            </div>
                        </div>
                    </c:if>
                        <%-- 本地开发平台测试用，请勿删除
                        <c:forEach var="brands" items="${paginateResult.list}" varStatus="status" >
                                <c:set value="${brands.domainName}" var="domain"></c:set>
                                <li>
                                    <div class="pic">
                                        <a href="http://www${zttx}/market/brand/index/${domain.brandsId}"   target="_blank" title="${brands.brandName }">
                                            <c:if test="${not empty brands.recommendImage}">
                                                <img src="${res}${brands.recommendImage}" alt="" width="370" height="200"/>
                                            </c:if>
                                            <c:if test="${empty brands.recommendImage}">
                                                <img src="${res}${brands.proLogo}" alt="" width="370" height="200"/>
                                            </c:if>
                                        </a>
                                    </div>
                                    <div class="detail">
                                        <div class="isbrand-logo clearfix">

                                            <div class="fl"> <a href="http://www${zttx}/market/brand/index/${domain.brandsId}" target="_blank" title="${brands.brandName }"><img src="${res}${brands.brandLogo}" alt="" width="100" height="50"/></a></div>
                                            <div class="fl isbrand-content-title">
                                                <p class="name"><a href="http://www${zttx}/market/brand/index/${domain.brandsId}" target="_blank">${brands.brandName}</a></p>
                                                <p><a href="http://${domain}${zttx}" target="_blank" class="color666 js-company" data-dealTypeName="${brands.dealType}" data-regMoney="${brands.regMoney}" data-createTime='${fns:getTimeFromLong(brands.createTime,"yyyy-MM-dd")}' data-comAddress="${brands.comAddress}">${brands.comName}</a></p>
                                            </div>
                                            <div class="fr address">
                                                <p>${brands.provinceName}&nbsp;&nbsp;${brands.cityName}</p>
                                            </div>
                                        </div>
                                        <div class="isbrand-info clearfix">
                                            <div class="fl">
                                                <p class="clearfix">
                                                    <span class="i-i-mainpro fl">主营产品：</span>
                                            <span class="i-i-pro fl" title="<c:forEach var="dealDic" items="${brands.dealDicList}" varStatus="status"><c:if test="${status.index > 0}"> | </c:if>${dealDic.dealName}</c:forEach>">
                                            <c:forEach var="dealName" items="${brands.dealName}" varStatus="status">
                                                <c:if test="${status.index > 0}">
                                                    |
                                                </c:if>
                                                ${dealName}
                                            </c:forEach>
                                            </span>
                                                </p>
                                                <p>门店数量： <span><em class="s-number">${brands.networkNum}</em> 家</span> </p>
                                                <p>招募条件：
                                                    <c:if test="${brands.dealBrand == 1}">
                                                        <span>单独品牌专卖实体店</span>
                                                    </c:if>
                                                    <c:if test="${brands.dealBrand == 2}">
                                                        <span>多品牌专营实体店</span>
                                                    </c:if>
                                                    <c:if test="${brands.dealBrand != 1 && brands.dealBrand != 2}">
                                                        <span>不限</span>
                                                    </c:if>
                                                </p>
                                            </div>
                                            <div class="fr">
                                                <p>注册资金： <span class="price">${brands.regMoney}</span> <span>万</span></p>
                                                <p>已咨询： <span class="number">${brands.askNum}</span> <span>人</span></p>
                                                <p>已申请： <span class="number">${brands.applyNum}</span> <span>人</span></p>
                                            </div>
                                        </div>
                                        <div class="isbrand-group mt10">
                                            <!--   <a class="ts-button ts-button-white" href="javascript:;">约逛咨询</a> -->
                                            <c:if test="${brands.join != null}">
                                                <a class="ts-button ts-button-orange" href="#">已加盟</a>
                                            </c:if>
                                            <c:if test="${brands.join == null}">
                                                <a class="ts-button ts-button-orange" href="http://www${zttx}/market/brand/viewBrandRecruit/${domain.brandsId}/${domain.brandId}" target="_blank">申请加盟</a>
                                            </c:if>
                                            <a class="ts-button ts-button-otherwhite" href="http://www${zttx}/market/brand/viewProductInfo/${domain.brandsId}/${domain.brandId}" target="_blank">进货</a>
                                            <a class="i-g-link" href="http://www${zttx}/market/brand/index/${domain.brandsId}" target="_blank" target="_blank">了解详情</a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>--%>
                </ul>
                <tags:page form="brandShopForm" page="${paginateResult.page}"/>
            </form:form>
        </div>
        <div class="brand-col-r fr">
            <ul>
                <li>
                    <div class="imbrand-list">
                        <div class="head">
                            <h3><i class="icon icon-brand"></i> 我是品牌商</h3>
                            <p>全国优质终端商E网打尽</p>
                        </div>
                        <div class="content">
                            <p>筹备入驻品牌：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("CBRZPP")}</span> 家</p>
                            <p>达成合作意向：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("DCJMGX")}</span> 次</p>
                        </div>
                        <a class="btn-apply" href="${ctx }/common/register?t=1" target="_blank">品牌商入驻</a>
                    </div>
                </li>
                <li>
                    <div class="imdealer-list">
                        <div class="head">
                            <h3><i class="icon icon-dealer"></i> 我是终端商</h3>
                            <p>一手价格一手货源，最低成本渠道快建</p>
                        </div>
                        <div class="content">
                            <p>入驻店铺：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("RZZDS")}</span> 家</p>
                            <p>累计节约：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("JSHK")}</span> 元</p>
                        </div>
                        <a class="btn-apply" href="${ctx }/common/register" target="_blank">终端商加盟</a>
                    </div>
                </li>
            </ul>
            <div class="gg-230x110 mt10">
                <c:set value="${fns:getAdvertPosit(index_dealer_storeList_right1)}" var="posit"></c:set>
                <c:if test="${posit != null && posit.viewNum > 0}">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                        <a href="${ctx}${list.urlAddress }" target="_blank">
                            <img src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                        </a>
                    </c:forEach>
                </c:if>
            </div>
            <div class="explosion-col mt10">
                <div class="explosion-col-head">
                    <h3>实时动态</h3>
                </div>
                <div class="explosion-col-content">
                    <div class="real-time-area" style="height: 534px; overflow: hidden;">
                        <c:set value="${fns:getTradeMeetRtkList(10, 12)}" var="meetRtkList"></c:set>
                        <c:if test="${!empty meetRtkList}">
                            <div class="real-time-con">
                                <c:forEach items="${meetRtkList}" var="meetRtk">
                                    <dl class="real-time">
                                        <a href="${ctx}/market/product/${meetRtk.productId}" target="_blank"> <dt class="pic"><img src="${res}${fns:getImageDomainPath(meetRtk.productImg,160,160)}" alt="" width="70" height="70"/></dt></a>
                                        <dd class="title">${meetRtk.address}  ${meetRtk.realName}</dd>
                                        <a href="${ctx}/market/product/${meetRtk.productId}" target="_blank"><dd class="des">${fns:trimLongStringWithEllipsis(meetRtk.actionMark, 20, '...') }</dd></a>
                                    </dl>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="gg-230x110 mt10">
                <c:set value="${fns:getAdvertPosit(index_dealer_storeList_right2)}" var="posit"></c:set>
                <c:if test="${posit != null && posit.viewNum > 0}">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                        <a href="${ctx}${list.urlAddress }" target="_blank">
                            <img src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
    <%--提示框模版 开始--%>
    <%--<div id="tip-box" style="display: none;">

    </div>--%>
    <style>
        .arale-tip-1_2_1 .ui-poptip-container{background: #fff;}
    </style>
    <%--提示框模版 结束--%>
</div>

<script src="/scripts/jquery.min.js"></script>
<script src="/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="/scripts/seajs_config.js" type="text/javascript"></script>
<script src="/scripts/common/base-init.js"></script>
<script>
    seajs.use(["tool","tip"],function(Zttx,Tip){

        $(".js-company").each(function(){
            var _title = $(this).text(),
                    dealtypename = $(this).data("dealtypename") || "暂无此属性",
                    regmoney=$(this).data("regmoney") || "暂无此属性",
                    createtime=$(this).data("createtime") || "暂无此属性",
                    comaddress=$(this).data("comaddress") || "暂无此属性";
            var source = '<div style="width:185px;font-family: \'Microsoft yahei\'; color: #333;">           \
                                    <div style="font-size: 14px;padding-bottom:5px;border-bottom: 1px dashed #e1e1e1;">'+ _title +'</div>   \
                                    <dl class="clearfix mt5" style="line-height: 20px;">                                 \
                                       <dt class="fl" style="width:35%;color: #999;">企业性质</dt>                      \
                                        <dd class="fr" style="width:65%;color: #666;">' + dealtypename+ '</dd>           \
                                        <dt class="fl" style="width:35%;color: #999;">注册资金</dt>                      \
                                        <dd class="fr" style="width:65%;color: #666;">'+regmoney+'</dd>                 \
                                        <dt class="fl" style="width:35%;color: #999;">成立日期</dt>                      \
                                        <dd class="fr" style="width:65%;color: #666;">'+createtime+'</dd>               \
                                        <dt class="fl" style="width:35%;color: #999;">所在地</dt>                        \
                                        <dd class="fr" style="width:65%;color: #666;">'+comaddress+'</dd>               \
                                        </dl>                                                                            \
                                </div>';
            //var tips = new Tip({
            //    trigger:$(this),
            //    content:source,
            //    arrowPosition:11
            //});
            //$(this).hover(function(){
            //tips.show();
            //});
        });

    });

    $.realMoveFn = function(configs){
        //parentID,childID,_len,pad,time,styles
        //styles加入css3样式用到
        var len = $(configs.childID).length;
        var hei = $(configs.childID).height() + configs.pad;//pad为 padding 和 margin
        var timer = null;
        var moveTime = configs.time || 6000;

        if(len > configs._len){
            function realMove(){
                timer = setInterval(function(){
                    $(configs.parentID).stop().animate({
                        marginTop: hei
                    },function(){

                        //if(configs.styles){
                        //    $(configs.childID).addClass("animated").last().prependTo($(configs.parentID)).addClass(configs.styles);
                        //}else{
                        $(configs.childID).last().css("opacity","0").prependTo($(configs.parentID)).animate({"opacity":"1"});
                        //}

                        $(configs.parentID).css({
                            marginTop:0
                        });
                    });
                },moveTime);
            }

            realMove();

            $(configs.parentID).hover(function(){
                clearInterval(timer);
            },function(){
                realMove();
            });
        }
    };
    $.realMoveFn({
        parentID:".real-time-con",childID:".real-time",_len:6,pad:20
    });
</script>
</body>
</html>
