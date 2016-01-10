<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我是终端商 - 8637品牌超级代理</title>
    <meta name="keywords" content="门店加盟,专卖店加盟,服装店加盟,招商加盟,加盟网"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="/styles/common/base.css" rel="stylesheet"/>
    <link href="/styles/stores/index.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
    <jsp:include page="${ctx}/common/top" />
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="3" name="m"/>
    </jsp:include>
    <%-- nav end --%>
    <div class="ts-main ts-container">
        <div class="ts-grid-row">
            <div class="ts-grid-item area-1-1">
                <div class="ts-box contact-box">
                    <div class="inner">
                        <div class="choose-reason">
                            <img src="${res}/images/stores/choose-reason.jpg" alt=""/>
                        </div>
                        <%--<div class="contact">
                            <a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw" target="_blank">
                                <img src="/images/market/qq-service.png" alt="点击这里给我发消息">
                            </a>
                        </div>--%>
                        <div class="tel">
                            <img src="${res}/images/stores/contact-tel.jpg" alt=""/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ts-grid-item area-1-2">
                <div class="clearfix">
                    <div id="banner" class="fl">
                        <tags:index_staticHtml webDefTemplate="${fns:getDocument(index_dealer_focus) }"></tags:index_staticHtml>
                    </div>
                    <div class="banner-dealer fr">
                        <div class="imdealer-i">
                            <div class="head">
                                <h3><i class="icon icon-dealer"></i> 我是终端商</h3>
                                <p>一手价格一手货源，最低成本渠道快建</p>
                            </div>
                            <%--<div class="content">
                                <p>入驻店铺：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("RZZDS")}</span> 家</p>
                                <p>累计节约：<span class="text-orange text-yahei text-md">${fns:getWebInfoCount("JSHK")}</span> 元</p>
                            </div>--%>
                            <div class="content">
                                <p>·<a href="/apply/terminalOperatorsSettled.jsp#process" target="_blank">终端商入驻资质介绍</a></p>
                                <p>·<a href="/apply/terminalOperatorsSettled.jsp#qualification" target="_blank">终端商加盟规则介绍</a></p>
                            </div>
                            <a class="btn-apply" href="${ctx}/apply/terminalOperatorsSettled.jsp" class="btn-apply" target="_blank">终端商加盟</a>　
                        </div>
                    </div>
                </div>
                <%--<div class="mt10">
                    <div class="ts-box">
                        <div class="ts-box-content">
                            <ul class="inline-float text-yahei stores-total">
                                <li class="text-center">
                                    <h4 class="text-sm">筹备入驻品牌</h4>
                                    <i class="icon-a"></i>
                                    <p class="text-red"><span class="text-lg">${fns:getWebInfoCount("CBRZPP")}</span> 个</p>
                                </li>
                                <li class="text-center">
                                    <h4 class="text-sm">提交产品数</h4>
                                    <i class="icon-b"></i>
                                    <p class="text-red"><span class="text-lg">${fns:getWebInfoCount("TJCPS")}</span> 个</p>
                                </li>
                                <li class="text-center">
                                    <h4 class="text-sm">达成加盟关系</h4>
                                    <i class="icon-c"></i>
                                    <p class="text-red"><span class="text-lg">${fns:getWebInfoCount("DCJMGX")}</span> 次</p>
                                </li>
                                <li class="text-center">
                                    <h4 class="text-sm">节省货款</h4>
                                    <i class="icon-d"></i>
                                    <p class="text-red"><span class="text-lg">${fns:getWebInfoCount("JSHK")}</span> 元</p>
                                </li>
                                <li class="text-center last">
                                    <h4 class="text-sm">近6个月累计进货金额</h4>
                                    <i class="icon-e"></i>
                                    <p class="text-red"><span class="text-lg">${fns:getWebInfoCount("LJJHJE")}</span> 元</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>--%>
            </div>
        </div>
        <div class="ts-grid-row">
            <div class="ts-grid-item area-2-1">
                <div class="ts-common-box">
                    <div class="ts-common-head">
                        <div class="title fl">
                            <h3><a href="${ctx}/common/dealer/storeList">优质品牌推荐</a></h3>
                        </div>
                        <a class="more fr" href="${ctx}/common/dealer/storeList" target="_blank"> 更多 <i class="icon icon-more"></i></a>
                    </div>
                    <div class="ts-common-content">
                        <ul class="brand-list mt10">
                        	<c:set value="${fns:getBrandesInfo(6, 2)}" var="brandsList"></c:set>
               			 	<c:forEach items="${brandsList}" var="brandes">
               			    <c:set value="${fns:getBrandsIdSubDomain(brandes.refrenceId)}" var="domain"></c:set>
                                <%--<c:set value="${fns:getBrandsDomainById(brandes.refrenceId)}" var="domain"></c:set>--%>
                                <li class="mb20">
                                <div class="brand-item">
                                   <div class="brand-item-media">
                                        <a  href="http://${domain}${zttx}" target="_blank" title="${brandes.brandName }品牌加盟">
                                            <c:choose>
                                                <c:when test="${empty brandes.image}">
                                                <img src="${res}${brandes.proLogo}" width="463" height="250">
                                                </c:when>
                                                <c:otherwise>
                                                <img src="${res}${brandes.image}" width="463" height="250">
                                                </c:otherwise>
                                             </c:choose>
                                        </a>
                                    </div>
                                    <div class="brand-item-body">
                                        <dl class="text-grey clearfix">
                                            <dt class="text-yahei">
                                                <a href="http://${domain}${zttx}" target="_blank" title="${brandes.brandName }品牌加盟">
                                                          <img src="${res}${brandes.brandLogo}" width="100" height="50">
                                                </a>
                                                <span class="fn-text-overflow" title="${brandes.brandName}">${brandes.brandName}</span>
                                            </dt>
                                            <dd class="text-yahei clearfix" style="height:40px">
                                                <p class="fl"><span class="text-red text-lg"><fmt:formatNumber pattern="0.0">${fns:getBrandsMinAigo(brandes.refrenceId)}</fmt:formatNumber></span>折起</p>
                                               <c:if test="${brandes.factoryStore!=true}" ><p class="fr">${brandes.comName}</p></c:if>
                                                <%--工厂店图标--%><c:if test="${brandes.factoryStore==true}" ><p class="fr ta-r" style="line-height: 18px;"><img class="ml5" src="/images/common/factory-brand.jpg" /><br />${brandes.comName}</p></c:if>
                                                <%--工厂店图标<p class="fr ta-r" style="line-height: 18px;"><img class="ml5" src="/images/common/factory-brand.jpg" /><br />${brandes.comName}</p>--%>
                                            </dd>
                                            <dd class="ts-hr-dashed clearfix" style="padding-top:5px;">
                                                <!-- <a class="ts-button ts-button-white" href="javascript:;">约逛咨询 </a> -->
                                                <p class="fl">
                                                    <c:set var="brandRecurit" value="${fns:isOpenBrandRecruit(brandes.refrenceId) }"/>
                                                    <c:if test="${brandRecurit}">
                                                    <a class="ts-button ts-button-orange" href="http://${domain}${zttx}/recruit" target="_blank">申请加盟</a>
                                                    </c:if>
                                                    <c:if test="${!brandRecurit }">
                                                     <a class="ts-button ts-button-disabled" >品牌招募书未开启</a>
                                                    </c:if>
                                                    <a class="ts-button ts-button-otherwhite" href="http://${domain}${zttx}/product" target="_blank">查看产品</a>
                                                </p>
                                               	<p class="fr"><span class="text-orange">${brandes.meetCount}</span>家满足加盟条件</p>
                                            </dd>
                                        </dl>
                                    </div>
                                    <%-- 本地开发环境测试用，请勿删除
                                    <div class="brand-item-media">
                                        <a  href="http://www${zttx}/market/brand/index/${domain.brandsId}" target="_blank" title="${brandes.brandName }品牌加盟">
                                            <c:choose>
                                                <c:when test="${empty brandes.image}">
                                                    <img src="${res}${brandes.proLogo}" width="463" height="250">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${res}${brandes.image}" width="463" height="250">
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </div>
                                    <div class="brand-item-body">
                                        <dl class="text-grey clearfix">
                                            <dt class="text-yahei">
                                                <a href="http://www${zttx}/market/brand/index/${domain.brandsId}" target="_blank" title="${brandes.brandName }品牌加盟">
                                                    <img src="${res}${brandes.brandLogo}" width="100" height="50">
                                                </a>
                                                <span>${brandes.brandName}</span>
                                            </dt>
                                            <dd class="text-yahei clearfix" style="height:40px">
                                                <p class="fl"><span class="text-red text-lg"><fmt:formatNumber pattern="0.0">${fns:getBrandsMinAigo(brandes.refrenceId)}</fmt:formatNumber></span>折起</p>
                                                <p class="fr">${brandes.comName}</p>
                                            </dd>
                                            <dd class="ts-hr-dashed clearfix" style="padding-top:5px;">
                                                <!-- <a class="ts-button ts-button-white" href="javascript:;">约逛咨询 </a> -->
                                                <p class="fl">
                                                    <a class="ts-button ts-button-orange" href="http://www${zttx}/market/brand/viewBrandRecruit/${domain.brandsId}/${domain.brandId}" target="_blank">申请加盟</a>
                                                    <a class="ts-button ts-button-otherwhite" href="http://www${zttx}/market/brand/viewProductInfo/${domain.brandsId}/${domain.brandId}" target="_blank">查看产品</a>
                                                </p>
                                                <p class="fr"><span class="text-orange">${brandes.meetCount}</span>家满足加盟条件</p>
                                            </dd>
                                        </dl>
                                    </div>--%>
                                </div>
                            </li>
               			 	</c:forEach>
                        </ul>
                        <div class="mb20 text-yahei b-l-lookmore"><a class="ts-bar-gray" href="${ctx }/common/dealer/storeList" target="_blank">查看更多品牌...</a></div>
                    </div>
                </div>
                <div class="ts-common-box">
                    <div class="ts-common-head">
                        <div class="title fl">
                            <h3><a href="${ctx }/common/dealer/explosionList">爆款产品</a></h3>
                        </div>
                        <a class="more fr" href="${ctx }/common/dealer/explosionList" target="_blank" title="更多">更多 <i class="icon icon-more"></i> </a>
                    </div>
                    <div class="ts-common-content">
                        <ul class="explosion-list inline-float mt10 mb20">
                        	<jsp:include page="${ctx}/common/activity/products">
								<jsp:param value="0" name="showType"/>
								<jsp:param value="6" name="size"/>
							</jsp:include>
                        </ul>
                        <div class="text-yahei b-l-lookmore"><a href="${ctx}/common/dealer/explosionList" class="ts-bar-gray" target="_blank">查看更多爆款...</a></div>
                    </div>
                </div>
            </div>
            <div class="ts-grid-item area-2-2">
                <div class="explosion-col mb20">
                    <div class="explosion-col-head">
                        <h3>意向加盟</h3>
                    </div>
                    <div class="explosion-col-content">
                        <div class="ts-list-area" style="height: 359px; overflow: hidden;">
                        	<c:set value="${fns:getTradeMeetRtkList(0, 12)}" var="meetRtkList"></c:set>
                        	<c:if test="${!empty meetRtkList}">
                        	<ul class="ts-list">
        						<c:forEach items="${meetRtkList}" var="meetRtk">
        						 <c:set value="${fns:getBrandsIdSubDomain(meetRtk.brandsId)}" var="domain"></c:set>
                                <li>
                                	<a class="" href="http://${domain}${zttx}" target="_blank">
                                		<span class="text-bold">${meetRtk.address}</span>
                                		<span class="text-bold">${meetRtk.realName}</span>
                                         正在查看
                                        <span class="text-bold">${meetRtk.brandsName}</span>
                                    </a>
                                 </li>
                            	</c:forEach>
                        	</ul>
                        	</c:if>
                        </div>
                    </div>
                </div>
                <div class="abc-area-230x110 mb10">
                	<c:set value="${fns:getAdvertPosit(index_dealer_right1)}" var="posit"></c:set>
        			<c:if test="${posit != null && posit.viewNum > 0}">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                        <a href="${ctx}${list.urlAddress }" target="_blank">
                            <img class="mb10" src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                        </a>
                    </c:forEach>
            		</c:if>
                </div>
                <div class="explosion-col mb20">
                    <div class="explosion-col-head">
                        <h3>实时动态</h3>
                    </div>
                    <div class="explosion-col-content">
                        <div class="real-time-area" style="height: 534px; overflow: hidden;">
                        	<c:set value="${fns:getTradeMeetRtkList(10, 12)}" var="meetRtkList"></c:set>
                        
                        	<c:if test="${!empty meetRtkList}">
                            <div class="real-time-con">
                            	<c:forEach items="${meetRtkList}" var="meetRtk">
                            		 <c:set value="${fns:getImageDomainPath(meetRtk.productImg,160,160)}" var="productImage"></c:set>
                                <dl class="real-time">
                                    <a href="${ctx }/market/product/${meetRtk.productId}" target="_blank">  <dt class="pic"><img src="${res}${productImage}" alt="${meetRtk.actionMark}" width="70" height="70"/></dt></a>
                                    <dd class="title">${meetRtk.address}  ${meetRtk.realName}</dd>
                                    <dd class="des"><a href="${ctx }/market/product/${meetRtk.productId}" target="_blank">${fns:trimLongStringWithEllipsis(meetRtk.actionMark, 20, '...') }</a></dd>
                                </dl>
                            	</c:forEach>
                            </div>
                        	</c:if>
                        </div>
                    </div>
                </div>
                <div class="abc-area-230x110 mb20">
                	<c:set value="${fns:getAdvertPosit(index_dealer_right2)}" var="posit"></c:set>
        			<c:if test="${posit != null && posit.viewNum > 0}">
                    <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum}">
                        <a href="${ctx}${list.urlAddress}" target="_blank">
                            <img src="${res}${list.adLogo}" width="${posit.imgWidth}" height="${posit.imgHeight}">
                        </a>
                    </c:forEach>
            		</c:if>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
</div>

<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common/base-init.js"></script>

<script>


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
            parentID:".ts-list",childID:".ts-list li",_len:10,pad:0
        });
        $.realMoveFn({
            parentID:".real-time-con",childID:".real-time",_len:6,pad:20
        });

        seajs.use(["slide","tool"], function (Slide,Zttx) {
            //记录原位置
            $("[data-animate]").each(function (i, o) {
                $(o).data({ ox: $(o).css("left"), oy: $(o).css("top") });
            }).css("opacity", 0);

            var slide = new Slide({
                element: "#banner",
                panels: "#banner .ts-banner-item",
                //triggers:"#banner .ts-banner-nav",
                effect: "scrollx",
                interval: 6180
            }).on("switched", function (a, b) {
               slidePlay(a, b);
            });
            slidePlay = function (toIndex, fromIndex) {

            };

            //模拟滚动条
            //console.log(Zttx);
            $(".js-scrollarea").each(function(){
                Zttx.scrollArea($(this));
            });
        });
</script>

</body>
</html>

