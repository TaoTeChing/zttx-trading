<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>搜索结果</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/search/index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="3" name="m"/>
    </jsp:include>
    <div class="ts-container clearfix mb20">
        <div>
            <!-- 一级目录 -->
            <div class="classify classify-main clearfix">
                <div class="classify-tit fl">类别</div>
                <div class="classify-sel fl" style="width: 1104px;">
                    <ul class="classify-ul inline-block" style="width: 1100px;">
                        <li>
                            <a href="${ctx}/common/dealer/productList?q=" class="${empty dealerProductListModel.dealMain?'active':''}">全部<i class="brandicons"></i></a>
                        </li>
                        <c:if test="${not empty dealMains}">
                            <c:forEach items="${dealMains}" var="obj">
                                <li>
                                    <a href="${ctx}/common/dealer/productList?dealMain=${obj.dealNo}&q="
                                       class="${dealerProductListModel.dealMain==obj.dealNo?'active':''}">
                                       ${obj.dealName}<i class="brandicons"></i></a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
            <!-- 二级目录 -->
            <c:if test="${not empty dealerProductListModel.dealMain}">
                <div class="classify clearfix">
                    <div class="classify-tit fl">&nbsp;</div>
                    <div class="classify-sel fl" style="width: 1104px;">
                        <ul class="classify-ul inline-block" style="width: 1100px;">
                            <c:forEach items="${dealDicList}" var="obj">
                                <li>
                                    <a href="${ctx}/common/dealer/productList?dealMain=${dealerProductListModel.dealMain}&dealNo=${obj.dealNo}&q=${dealerProductListModel.q}"
                                       class="${obj.dealNo==dealerProductListModel.dealNo?'active':''}">${obj.dealName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>
            <div class="filter clearfix mb10">
                <c:set var="descType" value="${dealerProductListModel.descType == 0 ? '1':'0'}"/>
                <c:set var="url" value="${ctx}/common/dealer/productList?dealMain=${dealerProductListModel.dealMain }&dealNo=${dealerProductListModel.dealNo}&q=${dealerProductListModel.q}&minPrice=${dealerProductListModel.minPrice}&maxPrice=${dealerProductListModel.maxPrice}&descType=${descType} "/>
                <div class="filter-tit fl">
                    <a href="${url}" ${dealerProductListModel.attrType == null ? 'class="current"':"" } >默认排序 <span class="icon-arr"><i></i><em></em></span></a>
                </div>
                <div class="fl">
                    <div class="filter-tag">
                        <a href="${url}&attrType=0" ${dealerProductListModel.attrType == 0 ? 'class="current"':"" }>销量 ${dealerProductListModel.attrType == 0 and dealerProductListModel.descType== 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=1" ${dealerProductListModel.attrType == 1 ? 'class="current"':"" }>时间 ${dealerProductListModel.attrType == 1  and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=2" ${dealerProductListModel.attrType == 2 ? 'class="current"':"" }>点击数量 ${dealerProductListModel.attrType== 2 and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                        <a href="${url}&attrType=3" ${dealerProductListModel.attrType == 3 ? 'class="current"':"" }>价格${dealerProductListModel.attrType == 3 and dealerProductListModel.descType == 0 ? '↑':'↓'}
                            <span class="icon-arr"><i></i><em></em></span></a>
                    </div>
                </div>
                <div class="fl ml20" style="line-height: 40px;">
                    <%--productGroom 值为boolean 即true,false--%>
                    <label class="ml10"><input id="productGroom" type="checkbox" ${dealerProductListModel.productGroom =='true' ? 'checked':''} name="productGroom" class="ui-checkbox"/> 推荐产品</label>
                    <%--
                    <label><input type="checkbox" class="ui-checkbox"/> 授权产品</label>
                    <label class="ml10"><input type="checkbox" class="ui-checkbox"/> 爆款产品</label>
                    <label class="ml10"><input type="checkbox" class="ui-checkbox"/> 清仓产品</label>
                    --%>
                </div>
                <%--<div class="fl ml20">--%>
                    <%--<div class="ts-input-filter">--%>
                        <%--<form>--%>
                            <%--<input class="input-price" name="minPrice" value="${dealerProductListModel.minPrice }">--%>
                            <%--<b>-</b><input class="input-price" name="maxPrice" value="${dealerProductListModel.maxPrice }">--%>
                            <%--<button class="btn-search">筛选</button>--%>
                        <%--</form>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            
                <form:form id="dealerShopForm" method="get">
                
                    <input type="hidden" name="dealMain" value="${dealerProductListModel.dealMain}">
                    <input type="hidden" name="dealNo" value="${dealerProductListModel.dealNo}">
                    <input type="hidden" name="q" value="${dealerProductListModel.q}">
                    <input type="hidden" name="minPrice" value="${dealerProductListModel.minPrice}">
                    <input type="hidden" name="maxPrice" value="${dealerProductListModel.maxPrice}">
                    <input type="hidden" name="descType" value="${dealerProductListModel.descType}">
                    <input type="hidden" name="attrType" value="${dealerProductListModel.attrType}">
                    <input type="hidden" name="productGroom" value="${dealerProductListModel.productGroom}"/>
                    <c:if test="${not empty paginateResult.list}">
                    <ul class="order-grid">
                        <c:forEach items="${paginateResult.list}" var="brands" varStatus="status">
                            <c:if test="${brands.userGrantModel.isValid}">  <!--有效的产品才会显示-->
                            <li class="goods-sale">
                                <div class="list-item clearfix">

                                    <a class="list-item-pic js-img-center" href="${ctx}/market/product/${brands.id}<c:if test='${!brands.userGrantModel.isStateChange}'>?code=${ brands.userGrantModel.typeNo}</c:if>"
                                       title="${brands.productTitle}" target="_blank">
                                        <%--<img src="${res}${fns:getImageDomainPath(brands.productImage,440,440)}" width="220" height="220"><!-- 440X440 -->--%>
                                        <img src="${res}${fns:getImageDomainPath(brands.productImage,440,440)}">
                                    </a>

                                    <div class="pic-nav">
                                        <div class="pic-thumbs">
                                            <ul class="pic-thumb-list">
                                                <c:forEach items="${brands.images}" var="imageName" begin="0" end="4">
                                                    <li class="js-img-center">
                                                       <%--<!-- 160X160,40X40 -->--%>
                                                        <img src="${res}${fns:getImageDomainPath(imageName,160,160)}" data-src="${res}${imageName}" class="image0">
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="info">
                                        <p class="clearfix">
                                            <c:choose>
                                                <c:when test="${brands.userGrantModel.priceType!=0}">
                                                    <label>采购价：</label><span class="text-yahei text-red" style="font-size: 14px;vertical-align: -1px;">
                                                        <em>￥</em><fmt:formatNumber pattern="0.00">${brands.userGrantModel.proLowestSkuPrice}</fmt:formatNumber></span>
                                                    <del class="text-yahei"><fmt:formatNumber pattern="0.00">${brands.productPrice}</fmt:formatNumber></del>
                                                </c:when>
                                                <c:otherwise>
                                                    <label>吊牌价：</label>
                                                    <span class="text-yahei text-red" style="font-size: 18px;"><em class="text-md">￥</em><fmt:formatNumber pattern="0.00">${brands.productPrice}</fmt:formatNumber></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <p class="fn-text-overflow" title="${brands.productTitle}" style="color: #666;">
                                            ${brands.productTitle}
                                        </p>
                                        <p class="clearfix fn-text-overflow" title="${brands.productNo}" style="color: #666;">
                                            货号：${brands.productNo}
                                        </p>
                                    </div>

                                    <div class="operate">
                                        <c:if test="${not isBrand}">
                                            <c:choose>
                                                <c:when test="${brands.userGrantModel.priceType!= 0}">
                                                    <a href="javascript:joinShop('${brands.userGrantModel.typeNo}','${brands.id}')" class="ts-button btn-addcart">加入进货单</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${ctx}/market/product/${brands.id}" target="_blank" class="ts-button btn-query">我要询价</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${!brands.tag}">
                                                <a href="javascript:;" class="ts-button btn-favorites" onclick="javascript:addFavor('${brands.id}',this)">加入收藏夹</a>
                                            </c:if>
                                            <c:if test="${brands.tag}">
                                                <a href="javascript:;" class="ts-button btn-favorites " style="color: green" onclick="javascript:addFavor('${brands.id}',this)">已加入收藏夹</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${isBrand}">
                                            <a href="${ctx}/market/product/${brands.id}" target="_blank" class="ts-button btn-query btn-disabled">我要询价</a>
                                            <a href="javascript:;" class="ts-button btn-favorites btn-disabled" onclick="javascript:addFavor('${brands.id}',this)">加入收藏夹</a>
                                        </c:if>
                                    </div>
                                </div>
                            </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    </c:if>
                    <tags:page form="dealerShopForm" page="${paginateResult.page}"/>
                </form:form>
            
            <c:if test="${empty paginateResult.list}">
                <div class="no-result">
                    <div class="n-r-say">
                        <p>抱歉！我们花了九牛二虎之力还是没找到您要找的</p>
                        <p style="font-size: 14px;"><span>${dealerProductListModel.q}</span>搜索结果</p>
                    </div>
                    <!-- <div class="n-r-recomed">
                        <span>你还可能喜欢：</span><a href="#">饰品衣服</a> <a href="#"> 2014衣服</a> <a href="#">收纳衣服</a> <a href="#">女装衣服</a> <a href="#">2013衣服</a> <a href="#">配饰衣</a> <a href="#">服秋冬衣服</a>
                    </div> -->
                    <div class="n-r-search">
                        <form action="${ctx}/common/dealer/productList" method="get">
                            <input type="text" name="q" class="fl n-r-text" placeholder="换个关键词试试呗">
                            <input type="submit" value="" class="fl n-r-btn">
                        </form>
                    </div>
                </div>
            </c:if>
            <div class="hr-dashed mt20 mb20"></div>
            <h3 class="text-md text-yahei mb10">相关品牌推荐</h3>
            <ul class="brand-grid" style="margin-left: -20px;">
                <c:set value="${fns:getBrandesInfo(20, 9)}" var="brandsList"></c:set>
                <c:if test="${ brandsList != null }">
                    <c:forEach items="${brandsList}" var="brandes" varStatus="status" begin="0" end="3">
                        <c:set value="${fns:getBrandsIdSubDomain(brandes.refrenceId)}" var="domain"></c:set>
                        <c:set value="${fns:getImageDomainPath(brandes.image, 440, 440)}" var="brandesImage"></c:set>
                        <li>
                            <div class="list-item clearfix">
                                <a class="list-item-pic js-img-center" href="http://${domain}${zttx}" title="${brandes.brandName }" target="_blank">
                                    <c:if test="${not empty brandes.image}">
                                    	<img src="${res}${brandes.image}">
                                    </c:if>
                                    <c:if test="${empty brandes.image}">
                                    	<img src="${res}${brandes.brandLogo}">
                                    </c:if>
                                </a>
                                <h4>${brandes.brandName }</h4>
                                <div class="list-item-logo">
                                    <a class="js-img-center" href="http://${domain}${zttx}" target="_blank">
                                        <img alt="${brandes.brandName }" src="${res}${brandes.brandLogo}">
                                    </a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/component/bottom.jsp"></jsp:include>

<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js" type="text/javascript"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/common/base-init.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script>
    //推荐产品状态切换,
    var productGroomFn = function(self){
        if(self.prop("checked")){
            $("input[name=productGroom]").val("true");
        }else{
            $("input[name=productGroom]").val("false");
        }
        $Z.changPage($("#dealerShopForm"));
    };
    //如需初始化执行下面的
    //productGroomFn($("#productGroom"));
    $("#productGroom").click(function(){
        productGroomFn($(this));
    });

    //加入进货单
    function joinShop(code,productId, status) {
        var objdata = new Object();
        objdata.title = "提示";
        $.post(
                        "/dealer/shoper/purchase/"
                        + productId
                        + (code=="A00001"?"/A00001":"/default"),
                function (data) {
                    if (data.code == 126000) {
                        remind("success", "此产品已成功加入进货单");
                        var item = {};
                        item.productId = productId;
                        item.image = $('a.jqzoom img').attr('src');
                        item.title = $('h1.title ').text();
                        item.price = $('span.pro-dt-nomber').text();
                        item.cartId = data.object;
//                        cart.add(item);
                        if (status) {
                            location.href = '/dealer/shopper';
                        }

                    } else if (data.code == 111048) {
                        //尚未购买服务
                        objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                        confirmDialog(
                                objdata,
                                function () {
                                    window
                                            .open("/dealer/service/details?refrenceId=S001");
                                });
                    } else if (data.code == 111040) {
                        //已加入购物车
                        if (status == null) {
                            remind("error", "已加入进货单，请勿重复添加");
                        } else {
                            location.href = '/dealer/shopper';
                        }
                    } else {
                        remind("error", data.message);
                        //remind("error","此产品已在进货单,无需重复加入");
                    }
                }, "json");
    }

    //加入收藏夹
    function addFavor(productId, obj) {
    	
        $.post("${ctx}/dealer/favorite/save",{"productId": productId}, function (data) {
            if (data.code == 126000) {
                remind("success", "产品收藏成功", function () {
                    $(obj).html("已加入收藏夹");
                    $(obj).css("color", "green");
                });
            } else {
                remind("error", data.message);
            }
        }, "json");
    }
</script>
</body>
</html>
