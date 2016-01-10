<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <c:set var="tradeName" value=""></c:set>
    <c:set var="modelName" value=""></c:set>
    <c:if test="${dealerShopEnv.trade==1 }"><c:set var="tradeName" value="男装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==2 }"><c:set var="tradeName" value="女装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==3 }"><c:set var="tradeName" value="童装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==4 }"><c:set var="tradeName" value="内衣"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==5 }"><c:set var="tradeName" value="家居"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==6 }"><c:set var="tradeName" value="鞋类"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==7 }"><c:set var="tradeName" value="箱包"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==8 }"><c:set var="tradeName" value="其他"></c:set></c:if>


    <c:if test="${dealerShopEnv.model==1 }"><c:set var="modelName" value="专卖店"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==2 }"><c:set var="modelName" value="混合店"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==3 }"><c:set var="modelName" value="专卖加混合"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==4 }"><c:set var="modelName" value="其他"></c:set></c:if>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="${dealerShopEnv.cityName}${dealerShopEnv.areaName}，${dealerShopEnv.cityName}${dealerShopEnv.areaName}${tradeName}${modelName}，${tradeName}招商加盟，${tradeName}品牌代理"/>
    <meta name="description"
          content="${dealerShopEnv.cityName}${dealerShopEnv.areaName}${dealerShopEnv.shopName}是销售${tradeName}的${modelName}，主营${tradeName}，欢迎优质品牌厂商通过8637品牌超级代理平台进行产品招商加盟"/>
    <title>${dealerShopEnv.cityName}${dealerShopEnv.areaName}${dealerShopEnv.shopName}_${tradeName} ${modelName}详情信息_招商加盟-8637品牌超级代理</title>
    <link href="${res}/styles/terminal/global.css" rel="stylesheet"/>
    <link href="${res}/styles/terminal/index.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <script src="/scripts/terminal/respond.src.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="2" name="m"/>
    </jsp:include>
    <div class="em100">
        <div class="row mt10">
            <div class="col-left">
                <div style="height:20px;line-height:20px;font-family: '宋体';"><a href="/">首页</a> &gt;&gt; <a
                           href="${ctx}/shop/default">终端商名录</a> &gt; <strong>${dealerShopEnv.shopName}</strong></div>
                <div class="panel mt10">
                    <div class="terminal-picture">
                        <div id="slide_box" class="slide-box">
                            <span class="prev">〈</span>
                            <span class="next">〉</span>
                            <ul class="slide-main ui-switchable-content">
                                <c:forEach items="${dealerImages}" var="dealerImage">
                                    <li>
                                        <a class="js-img-center" href="${res}${dealerImage.imageName}">
                                            <img class="js-glasses"
                                                 src="${res}${fns:getImageDomainPath(dealerImage.imageName, 400, 400)}"
                                                 data-glasses="${res}${dealerImage.imageName}"
                                                 alt="${dealerImage.photoName}"/>
                                        </a>
                                    </li>
                                </c:forEach>
                                <%--<li class="ui-switchable-panel" style="float: left;">
                                    <a class="js-img-center" href="http://images.8637.com/upload/dealer/img/2015/20150105/FC45B4E39F774279A966A0765DCD65FF.temp">
                                        <img class="js-glasses" src="http://images.8637.com/upload/dealer/img/2015/20150105/FC45B4E39F774279A966A0765DCD65FF.temp" data-glasses="http://images.8637.com/upload/dealer/img/2015/20150105/FC45B4E39F774279A966A0765DCD65FF.temp" alt="">
                                    </a>
                                </li>
                                <li class="ui-switchable-panel" style="float: left;">
                                    <a class="js-img-center" href="http://images.8637.com/upload/dealer/img/2015/20150106/AC4F2625CAA640058EFB168614381D51.jpg">
                                        <img class="js-glasses" src="http://images.8637.com/upload/dealer/img/2015/20150106/AC4F2625CAA640058EFB168614381D51.jpg" data-glasses="http://images.8637.com/upload/dealer/img/2015/20150106/AC4F2625CAA640058EFB168614381D51.jpg" alt="">
                                    </a>
                                </li>
                                <li class="ui-switchable-panel" style="float: left;">
                                    <a class="js-img-center" href="http://images.8637.com/upload/dealer/img/2015/20150105/7C92E34F97094F3E8B415AC4761DC03B.jpg">
                                        <img class="js-glasses" src="http://images.8637.com/upload/dealer/img/2015/20150105/7C92E34F97094F3E8B415AC4761DC03B.jpg" data-glasses="http://images.8637.com/upload/dealer/img/2015/20150105/7C92E34F97094F3E8B415AC4761DC03B.jpg" alt="">
                                    </a>
                                </li>
                                <li class="ui-switchable-panel" style="float: left;">
                                    <a class="js-img-center" href="http://172.16.1.8:81/upload/dealer/img/2015/20150106/12504FD387E04E7495DF79C1A604DC15.png">
                                        <img class="js-glasses" src="http://172.16.1.8:81/upload/dealer/img/2015/20150106/12504FD387E04E7495DF79C1A604DC15.png_400x400.png" data-glasses="http://172.16.1.8:81/upload/dealer/img/2015/20150106/12504FD387E04E7495DF79C1A604DC15.png" alt="">
                                        <span></span><span></span></a>
                                </li>--%>
                            </ul>
                        </div>
                    </div>
                    <div class="terminal-detail">
                        <h3>${dealerShopEnv.shopName}</h3>

                        <p class="t-d-time">
                            <span>${fns:getTimeFromLong(dealerShopEnv.createTime,'yyyy.MM.dd')} 发布</span>
                            <span>访问${dealerShopEnv.viewCount==null?0:dealerShopEnv.viewCount}次</span></p>

                        <%--<dl><dt>主营:</dt><dd>男装/内衣/女装</dd></dl>
                        <dl><dt>地址:</dt><dd class="js-address">浙江省台州市椒江区</dd></dl>
                        <dl><dt>在线联系:</dt><dd>李先生 <a>给我留言</a></dd></dl>
                        <dl>
                            <dt>联系方式:</dt>

                            <dd><a class="text-red" href="${ctx}经销商后台对应查看地址">立即查看</a></dd>
                        </dl>
                        <dl><dt>店铺简介:</dt><dd>这里有八十个汉字,160个英文字,这里有八十个汉字,160个英文字,这里有八十个汉字,160个英文字</dd></dl>--%>
                        <div class="t-d-item">
                            <dl>
                                <dt>主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营:</dt>
                                <dd>
                                    <c:choose>
                                        <c:when test="${dealerShopEnv.trade==1}">男装</c:when>
                                        <c:when test="${dealerShopEnv.trade==2}">女装</c:when>
                                        <c:when test="${dealerShopEnv.trade==3}">童装</c:when>
                                        <c:when test="${dealerShopEnv.trade==4}">内衣</c:when>
                                        <c:when test="${dealerShopEnv.trade==5}">家居</c:when>
                                        <c:when test="${dealerShopEnv.trade==6}">鞋类</c:when>
                                        <c:when test="${dealerShopEnv.trade==7}">箱包</c:when>
                                        <c:when test="${dealerShopEnv.trade==8}">其他</c:when>
                                        <c:otherwise>未知</c:otherwise>
                                    </c:choose>
                                </dd>
                            </dl>
                        </div>
                        <div class="t-d-item">
                            <dl>
                                <dt>联&nbsp;系&nbsp;&nbsp;人:</dt>
                                <dd>${dealerInfo.dealerUser }</dd>
                            </dl>
                        </div>
                        <div class="clearfix t-d-item">
                            <dl class="fl t-d-level">
                                <dt>会员等级:</dt>
                                <dd>${dealerInfo.checkState==1?'认证会员':'普通会员'} </dd>
                            </dl>
                            <dl class="fl t-d-yy">
                                <dt>运营状态:</dt>
                                <dd>营业中</dd>
                            </dl>
                        </div>
                        <div class="clearfix t-d-item">
                            <dl class="fl t-d-store">
                                <dt>连锁店数:</dt>
                                <dd>${dealerShopEnv.scale}家</dd>
                            </dl>
                            <dl class="fl t-d-brand">
                                <dt>加盟品牌数:</dt>
                                <dd>${dealerJoinBrandesNum}家</dd>
                            </dl>
                        </div>
                        <div class="clearfix t-d-item">
                            <dl class="fl t-d-level">
                                <dt>联系电话:</dt>
                                <dd id="phone_num">
                                    <c:choose>
                                        <c:when test="${!isAuth}">
                                            <a href="javascript:ajaxLogin_();" class="t-d-look">立即查看</a>
                                        </c:when>
                                        <c:when test="${not isBrand}">
                                            <c:choose>
                                                <c:when test="${isDealer}">
                                                    ${userMobile}
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="javascript:;" class="t-d-lookdis">立即查看</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- 变灰 -->
                                            <!-- <a href="javascript:;" class="t-d-lookdis">立即查看</a> -->
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${isExits}">
                                                    ${userMobile}
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="js-look t-d-look" href="javascript:;">立即查看</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </dd>
                            </dl>
                            <dl class="fl" style="width: 150px;">
                                <dt>开店日期:</dt>
                                <dd>
                                    <c:if test="${not empty dealerShopEnv.openTime && dealerShopEnv.openTime != 0}">${fns:getTimeFromLong(dealerShopEnv.openTime*1000,'yyyy.MM.dd')}</c:if></dd>
                            </dl>
                        </div>
                        <div class="t-d-item">
                            <dl>
                                <dt>所在地址:</dt>
                                <dd class="js-address">${dealerShopEnv.provinceName}${dealerShopEnv.cityName}${dealerShopEnv.areaName}</dd>
                            </dl>
                        </div>
                        <c:if test="${not isAuth}">
                            <div class="t-d-item t-d-item-tip" name="prompt">
                                <i class="shop-icon shop-icon-remind"></i> 温馨提示：<a href="javascript:ajaxLogin_();"
                                                                                   style="color: #c00;">登录</a>后才能进行店铺收藏和邀请加盟
                            </div>
                        </c:if>
                        <div class="t-d-item">
                            <c:choose>
                                <c:when test="${!isAuth}">
                                    <a href="javascript:ajaxLogin_();" class="btn btn-orange t-d-fav inline-block"><i
                                               class="shop-icon-collect"></i> 收藏店铺</a>
                                    <a href="javascript:ajaxLogin_();" class="btn btn-orange t-d-apply inline-block"><b>邀请店铺加盟</b></a>
                                </c:when>
                                <c:when test="${not isBrand}">
                                    <!-- 变灰 -->
                                    <a href="javascript:;" class="btn btn-orange t-d-fav inline-block btn-disabled"><i
                                               class="shop-icon-collect"></i> 收藏店铺</a>
                                    <a href="javascript:;"
                                       class="btn btn-orange t-d-apply inline-block btn-disabled"><b>邀请店铺加盟</b></a>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not isCollected}">
                                        <a href="javascript:void(0);"
                                           class="btn btn-orange t-d-fav inline-block js-collect"
                                           data-dealerid="${dealerInfo.refrenceId}"><i class="shop-icon-collect"></i>
                                            收藏店铺</a>
                                    </c:if>
                                    <c:if test="${isCollected}">
                                        <a href="javascript:void(0);"
                                           class="btn btn-orange t-d-fav inline-block js-unCollect"
                                           data-dealerid="${dealerInfo.refrenceId}"><i class="shop-icon-collect"></i>
                                            取消收藏</a>
                                    </c:if>
                                    <%--<a href="javascript:void(0);" class="ui_button ui_button_lorange js-invite-join" tabindex="-1" data-dealerid="${info.refrenceId}">邀请加盟1</a>--%>
                                    <a href="javascript:void(0);"
                                       class="btn btn-orange t-d-apply inline-block js-invite-join"
                                       data-dealerid="${dealerInfo.refrenceId}"><b>邀请店铺加盟</b></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="mt10 commitment">
                    郑重承诺：所提供的店铺信息如实描述，期待我们的合作
                </div>
                <div class="panel mt10" style="border-top: 0;">
                    <div class="list-head">
                        <ul class="tab-dealer inline-float js-tabs-lu">
                            <li><a class="active">店铺地图</a></li>
                            <!-- <li><a>店铺介绍</a></li> -->
                        </ul>
                        <ul class="tab-order inline-float">
                            <li><a><i class="">&nbsp;</i></a></li>
                        </ul>
                    </div>
                    <div class="js-tabs-div">
                        <div class="js-tabs-con" id="BDmap-content" style="width: 100%; height: 390px;">

                        </div>
                        <div class="js-tabs-con" style="display: none;">
                            ${dealerShopEnv.briefIntroduction}
                        </div>
                    </div>

                </div>
                <div class="main-list mt10">
                    <div class="list-head">
                        <ul class="tab-dealer inline-float">
                            <li><a class="active">其他店铺推荐</a></li>
                        </ul>
                        <ul class="tab-order inline-float">
                            <%--<li><a><input class="ui-checkbox" type="checkbox" onclick="setOnlyShowPic()" id="onlyShowPic"> 只看有图</a></li>--%>
                            <li style="border-left: 1px solid #ddd;"><a class="active"><i
                                       class="shop-icon shop-icon-grid" title="大图展示">1</i></a></li>
                            <li><a><i class="shop-icon shop-icon-list" title="列表展示">2</i></a></li>
                        </ul>
                    </div>
                    <form:form id="list_data_form">
                        <div class="list-dealer mt10">
                            <ul class="order-grid" id="list_grid">
                            </ul>
                        </div>
                        <div class="pagination ta-c mt10">
                            <div class="mt10" id="pagination"></div>
                        </div>
                    </form:form>
                </div>
            </div>
            <div class="col-right">
                <div class="clearfix" style="height:20px;line-height:20px;"></div>
                <div class="mt10"><a class="btn btn-orange btn-block btn-lg" href="${ctx}/shop/collect"><i
                           class="shop-icon shop-icon-write"></i>免费登记店铺信息</a></div>

                <div class="panel mt10">
                    <div class="panel-head">
                        <h3>最新登记商家</h3>
                        <!-- <div class="panel-head-more"><a><i class="shop-icon shop-icon-more"></i></a></div> -->
                    </div>
                    <div class="panel-body">
                        <%--<a class="link">男装</a> | <a class="link">女装</a> | <a class="link">童装</a> | <a class="link">内衣</a>--%>
                        <ul class="media-list mt10">
                            <c:forEach items="${dealerShopEnvs}" var="dealerShopEnv" varStatus="status">
                            <c:set value="${fns:getImageDomainPath(dealerShopEnv.dealerImages[0].imageName, 300, 300)}"
                                   var="shopImage"></c:set>
                            <li>
                                <a href="${ctx}/shop/details/${dealerShopEnv.refrenceId}">
                            	     <span class="media">
                            	     	<c:if test="${fn:length(dealerShopEnv.dealerImages) >0}">
                                            <img src="${res}${shopImage}">
                                        </c:if>
                                                <c:if test="${fn:length(dealerShopEnv.dealerImages)==0}">
                                                    <img src="${res}/images/common/nopic-400.gif">
                                                </c:if>
									</span>

                                    <p class="media-title">
                                            ${dealerShopEnv.shopName}<br/>
                                        <span><c:choose>
                                            <c:when test="${dealerShopEnv.trade==1}">男装</c:when>
                                            <c:when test="${dealerShopEnv.trade==2}">女装</c:when>
                                            <c:when test="${dealerShopEnv.trade==3}">童装</c:when>
                                            <c:when test="${dealerShopEnv.trade==4}">内衣</c:when>
                                            <c:when test="${dealerShopEnv.trade==5}">家居</c:when>
                                            <c:when test="${dealerShopEnv.trade==6}">鞋类</c:when>
                                            <c:when test="${dealerShopEnv.trade==7}">箱包</c:when>
                                            <c:when test="${dealerShopEnv.trade==8}">其他</c:when>
                                            <c:otherwise>未知</c:otherwise>
                                        </c:choose></span><br/>
                                    </p>
                                </a>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="panel mt10">
                    <div class="panel-head">
                        <h3>相关行业资讯</h3>

                        <div class="panel-head-more"><a href="${ctx}/info/"><i class="shop-icon shop-icon-more"></i></a>
                        </div>
                    </div>
                    <div class="panel-body">
                        <c:set value="${fns:loadLatestNews(10) }" var="paginateResult"></c:set>
                        <ul>
                            <c:if test="${paginateResult.list != null }">
                                <c:forEach items="${paginateResult.list}" var="result" varStatus="status">
                                    <li><em>${fns:getTimeFromLong(result.createTime,'yyyy.MM.dd')}</em><a
                                               href="${ctx }/info/${result.refrenceId }" target="_blank"
                                               title="${result.articleTitle }">${status.index+1}.${fns:trimLongText(result.articleTitle,10) }</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt10"></div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>
</div>
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>

<div style="disliay:none" id="temp_id">${dealerShopEnv.dealerId}</div>
<div style="display: none;">
    <div class="js-tips">
        <div class="ui-head">
            <h3>温馨提示</h3>
        </div>
        <div>
            <div class="p15 fs14 ta-c yahei">
                <p>您当前还剩余 <span style="color:#24883a;" id="chance">*</span> 次机会</p>

                <p class="mt10">本次操作会扣除 <span class="c-r">[1]</span> 次查看机会，再次对该终端商操作将不再扣除。</p>
            </div>
            <div class="operate p15 fr">
                <a class="confirm_btn ui_button ui_button_lorange" href="javascript:;">确定</a>
                <a class="cancel_btn simple_button" href="javascript:;">取消</a>
            </div>
        </div>
    </div>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/src/common/base-init.js"></script>

<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp"/>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/src/brand/agencymanag.js"></script>

<script id="shopenv-template" type="text/html">
    {{each rows}}
    <li>
        <div class="list-item clearfix">
            <a class="js-img-center" href="${ctx}/shop/details/{{$value.refrenceId}}" target="_blank">
                {{if $value.dealerImages.length>0}}
                <img src="${res}{{$getImageDomainPathFn $value.dealerImages[0].imageName 300 300}}">
                {{/if}}
                {{if $value.dealerImages.length==0}}
                <img src="${res}/images/common/nopic-400.gif">
                {{/if}}
            </a>

            <div class="info">
                <div>
                    <h5 title="{{$trimLongString($value.shopName)}}"> {{$trimLongString($value.shopName)}}&nbsp;</h5>
                    <em>{{$value.payedUser==true?"收费用户":"实体认证"}}</em>
                </div>
                <p>
                    {{if $value.trade==1}}男装{{/if}}
                    {{if $value.trade==2}}女装{{/if}}
                    {{if $value.trade==3}}童装{{/if}}
                    {{if $value.trade==4}}内衣{{/if}}
                    {{if $value.trade==5}}家居{{/if}}
                    {{if $value.trade==6}}鞋类{{/if}}
                    {{if $value.trade==7}}箱包{{/if}}
                    {{if $value.trade==8}}其他{{/if}}
                    {{if $value.trade==''||$value.trade==null}}未知{{/if}}
                </p>
                <span class="address"><i></i>{{$value.provinceName}}-{{$value.cityName}}</span>
                <span class="date">{{$formatDate $value.createtime}}</span>
            </div>
        </div>
    </li>
    {{/each}}
</script>
<script>
    //下面两个全局变量用来控制，放大镜在图片滚动的时候获取位置不准的情况
    var outsideTrigger = true, timeMove = null;

    seajs.use(["slide", "tip", "dialog"], function (Slide, Tip, Dialog) {
        var slide = new Slide({
            element: '#slide_box',
            //trigger:"",
            effect: "scrollx",
            panels: "#slide_box .ui-switchable-content li",
            interval: 5000,
            activeIndex: 0,
            autoplay: false,
            hasTriggers: false,
            viewSize: ["390"]
        }).render();

        $("#slide_box .prev").click(function (e) {
            e.preventDefault();
            slide.prev();
            outsideTrigger = false;
            clearTimeout(timeMove);
            timeMove = setTimeout(function () {
                outsideTrigger = true;
            }, 618);
        });
        $("#slide_box .next").click(function (e) {
            e.preventDefault();
            slide.next();
            outsideTrigger = false;
            clearTimeout(timeMove);
            timeMove = setTimeout(function () {
                outsideTrigger = true;
            }, 618);
        });

        seajs.importStyle(".arale-tip-1_2_1 .ui-poptip-container {padding: 5px 5px;}");
        new Tip({
            trigger: ".media-list .media",
            arrowPosition: 2,
            theme: "blue"
        }).before("show", function () {
                       var html = this.activeTrigger.html();
                       //$(html).css({width:240,height:200})
                       this.set("content", html);
                   });

        var items = $(".terminal-rim-item");
        var tabs = $(".terminal-rim ul a").click(function (e) {
            e.preventDefault();
            var indx = $(tabs).removeClass("active").index($(this).addClass("active"));
            items.hide().eq(indx).show();
        });

        //需求 #111 增加脚本 开始
        var lookDialog = new Dialog({
            content: $(".js-tips"),
            effect: 'fade',
            hasMask: false,
            width: 450,
            classPrefix: "view-dialog",
            zIndex: 210
        });
        $(".js-look").click(function () {
            $.ajax({
                type: "post",
                url: "/shop/viewCount",
                dataType: "json",
                success: function (data) {
                    $("#chance").text("[" + data.object + "]");
                    lookDialog.show();
                }
            })
        });
        //隐藏
        $(document).on("click", ".cancel_btn", function () {
            lookDialog.hide();
        });
        //确定
        $(document).on("click", ".confirm_btn", function () {
            $.post("/brand/dealer/viewContact", {dealerId: $("#temp_id").text(), isShow: true}, function (data) {
                if (data.code == zttx.SUCCESS) {
                    $("#phone_num").html(data.object.userMobile);
                    lookDialog.hide();
                } else {
                    remind("error", data.message);
                }
            }, 'json');
        });
        //邀请加盟
        var invitedio = new Dialog({//邀请加盟按钮
            trigger: '.js-invite-join',
            effect: 'fade',
            hasMask: false,
            content: $(".js-invite-show"),
            width: 360,
            classPrefix: "view-dialog",
            zIndex: 210
        }).after("show", function () {
                       $("#inviteText").val("");
                       target = this.activeTrigger;
                       if ($.isFunction(inviteJoin)) {
                           inviteJoin(target, invitedio);
                       }
                   }).before("show", function () {
                       $("#brandsId").val("");
                       $("#brandsId_div span").html("请选择品牌");
                       $(".joindealer").hide();
                       $("#joinDiv").show();
                   });
        $(".js-invite-show").on("click", ".cancel_btn", function () {
            invitedio.hide();
        })

        function inviteJoin(obj, invitedio) {
            $("#dealerId").val($.trim(obj.attr("data-dealerid")));
            invitEditDiv = invitedio;
        }

        // 收藏
        $(document).on('click', '.js-collect', function () {
            var $this = $(this);
            var dealerId = $this.data("dealerid");
            $.post("${ctx}/brand/dealer/collectDealer", {dealerId: dealerId}, function (data) {
                if (data.code == zttx.SUCCESS) {
                    remind("success", "收藏成功");
                    $this.html("<i class=\"shop-icon-collect\"></i> 取消收藏");
                    $this.removeClass("js-collect").addClass("js-unCollect");
                }
                else {
                    remind("error", data.message);
                }
            }, "json");
        });

        // 取消收藏
        $(document).on('click', '.js-unCollect', function () {
            var $this = $(this);
            var dealerId = $this.data("dealerid");
            $.post("${ctx}/brand/dealer/unCollectDealer", {dealerId: dealerId}, function (data) {
                if (data.code == zttx.SUCCESS) {
                    remind("success", "取消收藏成功");
                    $this.html("<i class=\"shop-icon-collect\"></i> 收藏店铺");
                    $this.removeClass("js-unCollect").addClass("js-collect");
                }
                else {
                    remind("error", data.message);
                }
            }, "json");
        });
        //需求#111 增加脚本 结束
    });

    var tabOrder = $(".tab-order a:gt(0)").click(function () {

        tabOrder.not($(this).addClass("active")).removeClass("active");

        var idx = tabOrder.index(this);
        var listDealer = $(".list-dealer ul");
        if (idx == 0) {
            /*grid*/
            listDealer.removeClass().addClass("order-grid");
        }
        else {
            /*list*/
            listDealer.removeClass().addClass("order-list");
        }
    });

    //tab切换
    $(".js-tabs-lu li").click(function () {
        var index = $(this).index();
        $(".js-tabs-lu li a").removeClass("active");
        $(this).find("a").addClass("active");
        $(".js-tabs-div .js-tabs-con").hide().eq(index).show();
    });
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=726ace34e0f0ec5bc372d4fa40c53a45"></script>
<script>
    //引进百度地图
    var map = new BMap.Map("BDmap-content");
    //map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
    //缩放
    map.enableScrollWheelZoom();

    //两边的比例条
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_RIGHT,
        type: BMAP_NAVIGATION_CONTROL_SMALL
    }); //右上角，仅包含平移和缩放按钮
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);

    var BDaddress = $(".js-address").text();
    map.centerAndZoom(BDaddress, 15); //设置上面的地址为焦点

    /*var marker = new BMap.Marker(point);  // 创建标注
     map.addOverlay(marker);               // 将标注添加到地图中
     marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画*/


    //登陆
    function ajaxLogin_() {
        if ($("#ajaxLogin").length > 0 && $("#ajaxLogin").html() != "") {
            $("#ajaxLogin").show();
            return;
        }
        $.get("/common/login_dialog", function (data) {
            $('<div id="ajaxLogin">' + data + '</div>').appendTo($("body"));

            var mobileNullTpl = '<span class="login-dialog-explain">请输入会员名</span>';
            var mobileErrorTpl = '<span class="login-dialog-explain">请输入正确的手机号码</span>';
            var pwdNullrTpl = '<span class="login-dialog-explain">请输入密码</span>';

            var setErrorMsg = function (self, tpl) {
                var selfNext = self.next().attr("class");
                if (!selfNext || selfNext != "login-dialog-explain") {
                    self.after(tpl);
                }
            };
            var checkLoginFormFn = function () {
                var flag = true;
                $(".login-dialog-explain").remove();
                if ($("input[name='account']").val() == "") {
                    setErrorMsg($("input[name='account']"), mobileNullTpl);
                    flag = false;
                } else {
                    var phone = $("input[name='account']").val();
                    if (!mobileNumReg(phone)) {
                        setErrorMsg($("input[name='account']"), mobileErrorTpl);
                        flag = false;
                    }
                }
                if ($("input[name='pwd']").val() == "") {
                    setErrorMsg($("input[name='pwd']"), pwdNullrTpl);
                    flag = false;
                }
                return flag;
            };

            $("input[name='account'],input[name='pwd']").on("change keyup", function () {
                checkLoginFormFn();
            });

            $("#login-btn").on("click", function () {

                var loginFlag = checkLoginFormFn();
                if (loginFlag) {
                    $.ajax({
                        type: "post",
                        url: "/common/login/submitlogin",
                        dataType: "json",
                        data: $("#command").serialize(),
                        success: function (data) {
                            if (data.code === 126000) {
                                window.location.reload();
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                }
                return false;
            });
            $("#ajaxLogin .login-dialog-close").on("click", function () {
                $("#ajaxLogin").hide();
            })
        });
    }

    seajs.use(["pagination", "template", "moment", "glasses"], function (Pagination, template, moment, Glasses) {

        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format("yyyy.MM.dd");
        });
        template.helper('$getImageDomainPathFn', function (url, width, height) {
            if (url == null) return "";
            return getImageDomainPath(url, width, height);
        });
        template.helper('$trimLongString', function (origText) {
            return trimLongString(origText, 8, '...');
        });

        //分页
        new Pagination({
            url: "${ctx}/shop/list/data?areaNo=${dealerShopEnv.areaNo}&trade=${dealerShopEnv.trade}",
            elem: "#pagination",
            form: $("#list_data_form"),
            method: "post",
            handleData: function (data) {
                var html = template.render("shopenv-template", data);
                $('#list_grid').empty().append(html);
                $(".js-img-center").each(function () {
                    $("<span></span>").appendTo($(this));
                });
            }
        });

        //放大镜
        new Glasses({
            elem: ".js-glasses",
            changeBefore: function (_this) {
                //通过控制插件里面 outsideTrigger 的值，控制放大镜的显示
                _this.outsideTrigger = outsideTrigger;
            }
        });
    });


</script>

</body>
</html>
