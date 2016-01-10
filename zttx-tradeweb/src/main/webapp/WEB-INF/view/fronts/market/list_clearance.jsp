<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>底价清仓 - 8637品牌超级代理</title>
    <meta name="keywords" content="服装清仓,底价清仓,清仓处理,换季清仓,8637品牌超级代理"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的较短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/market/clearance.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <%-- header-round end --%>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="5" name="m"/>
    </jsp:include>
    <%-- nav end --%>
    <div class="main ts-container pr">
        <%--清仓处理品牌--%>
        <%--<div class="main-grid part-a mb20">
            <div class="part-col1">
                <h3 class="text-grey text-yahei text-sm">清仓处理品牌</h3>
            </div>
            <c:if test="${not empty clearanceBrands.list}">
                <div class="part-col2">
                    <ul class="inline-float">
                        <c:forEach items="${clearanceBrands.list}" var="item">
                            <c:set value="${ fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain"></c:set>
                            <li>
                                <a href="http://${domain}${zttx}" target="_blank" title="${item.brandName}"></a><img src="${res}${fns:getImageDomainPath(item.brandLogo, 100, 50)}" style="width:80px;height:40px;"></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="part-col3">
                    <div class="paging">
                        <a href="javascript:;" class="prev">&lt;</a>
                        <c:forEach begin="0" end="${clearanceBrands.list.size() / 9}" varStatus="status">
                            <a href="javascript:;" class="${status.first ? 'dot active':'dot'}"></a>
                        </c:forEach>
                        <a href="javascript:;" class="next">&gt;</a>
                    </div>
                </div>
            </c:if>
        </div>--%>
        <%--banner--%>
        <div class="main-grid part-b mb20">
            <div class="part-col1">
                <div id="banner" class="ts-banner">
                    <tags:index_staticHtml webDefTemplate="${fns:getDocument(clearance_index_focus) }"></tags:index_staticHtml>
                </div>
            </div>
            <div class="part-col2">
                <div class="explosion-col">
                    <div class="explosion-col-head">
                        <h3>意向加盟</h3>
                    </div>
                    <div class="explosion-col-content">
                        <div class="ts-list-area" style="height: 225px; overflow: hidden;">
                            <c:set value="${fns:getTradeMeetRtkList(0, 12) }" var="meetRtkList"></c:set>
                            <c:if test="${!empty meetRtkList}">
                                <ul class="ts-list text-yahei">
                                    <c:forEach items="${meetRtkList}" var="meetRtk">
                                        <c:set value="${fns:getBrandsIdSubDomain(meetRtk.brandsId)}" var="domain"></c:set>
                                        <li>
                                            <a class="" href="http://${domain}${zttx}" target="_blank" >
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
            </div>
        </div>
        <%--列表--%>
        <div class="main-grid part-c mb20">
            <form:form action="" method="get" id="clearanceForm">
                <input type="hidden" name="sort" id="sort" value="${filter1.sort}"/>
                <input type="hidden" name="sortName" id="sortName" value="${filter1.sortName}"/>

                <div class="part-col1">
                    <div class="filter clearfix mb20">
                        <div class="fl">
                            <div class="ts-button-group ts-button-xs">
                                <a class="ts-button ts-button-white" data-sort="1" href="javascript:searchSort(1);" style="border-right: 0;">人气 <i class="clearance-iupdown"></i></a>
                                <a class="ts-button ts-button-white" data-sort="2" href="javascript:searchSort(2);" style="border-right: 0;">销量 <i class="clearance-iupdown"></i></a>
                                <a class="ts-button ts-button-white" data-sort="3" href="javascript:searchSort(3);">价格 <i class="clearance-iupdown"></i></a>
                            </div>
                        </div>
                        <div class="fl">
                            <div class="ts-input-filter" style="width: 389px;">
                                <input class="input-price" name="minPrice" value="${filter1.minPrice}"><b>-</b><input class="input-price" name="maxPrice" value="${filter1.maxPrice}"><b></b><input class="input-search" name="keyWord" placeholder="请输入搜索条件." value="${filter1.keyWord}">
                                <a class="btn-search ts-button ts-button-white ts-button-xs fr" href="javascript:searchPro();" style="line-height: 23px;">搜索</a>
                            </div>
                        </div>
                    </div>
                    <ul class="order-grid">
                    <c:forEach items="${result.list}" var="actPro">
				    <li id="item_${actPro.productId}">
				        <div class="list-item clearfix">
				          <a class="list-item-pic js-img-center" href="${ctx}/market/product/${actPro.productId}?code=A00002" title="${actPro.productTitle}" target="_blank">
				                <img id="item_image_${actPro.productId}"  src="${res}${fns:getImageDomainPath(actPro.productImage, 440, 440)}" width="223" height="223">
				            </a> 
				            <div class="pic-nav">
				                <div class="pic-thumbs">
				                    <ul class="pic-thumb-list">
				                    	<c:forEach items="${actPro.images}" var="image" varStatus="status">
				                    	<c:if test="${status.index<5}">
				                        <li>
				                            <a href="${ctx}/market/product/${actPro.productId }?code=A00002" title="${actPro.productTitle}" target="_blank">
				                                <img src="${res}${fns:getImageDomainPath(image, 40, 40)}" class="image0" width="30" height="30"/>
				                            </a>
				                        </li>
										</c:if>
				                        </c:forEach>
				                    </ul>
				                </div>
				            </div>
				            <div class="info">
				            	<c:choose>
				            		<c:when test="${not empty actPro.productMap.message}">
				            		<p class="clearfix">
									    <img class="fl" src="/images/common/index/clearance-price.jpg" alt="" style="vertical-align: baseline;"/> 
				                        <span class="text-yahei text-orange text-lg fl" id="item_price_${actPro.productId}"><span class="text-md" ></span>
				                        <c:choose>
				                        	<c:when test="${not empty actPro.productMap}">
				                        	<fmt:formatNumber value="${actPro.productMap.price}" type="currency" pattern="0.00" />
				                        	</c:when>
				                        	<c:otherwise>
				                        	<fmt:formatNumber value="${actPro.productPrice}" type="currency" pattern="0.00" />
				                        	</c:otherwise>
				                        </c:choose>
				                        </span>
				                        <s class="text-thin fl"><span class="text-yahei">￥</span><fmt:formatNumber value="${actPro.productPrice}" type="currency" pattern="0.00" /></s>
				                    </p>
				            		</c:when>
				            		<c:otherwise>
				            		<p class="unempower"></p>
				            		</c:otherwise>
				            	</c:choose>
				                <p class="simply text-grey" style="height:36px;margin-bottom:10px;border-bottom: 1px dashed #ddd;" id="item_title_${actPro.productId}">${actPro.productTitle}</p>
				                <%--<p style="color: #666;"><i class="icon icon-shop" style="margin-right: 10px;"></i>门店零售价：189 元</p>
				                <p style="color: #666;"><i class="icon icon-tmall" style="margin-right: 10px;"></i>天猫旗舰店：120 元</p>
				                <p style="color: #666;"><i class="icon icon-shengdai" style="margin-right: 10px;"></i>省代价格：90 元</p>--%>
								<c:if test="${not empty actPro.productList}">
									<c:forEach items="${actPro.productList}" var="productList" varStatus="status">
										<c:if test="${status.index<2}">
											<c:choose>
												<c:when test="${not empty productList.style}">
												<p style="color: #666; height: 22px;"><i class="icon ${productList.style}" style="margin-right: 10px;"></i>${productList.columnName}：${productList.price} 元</p>
												</c:when>
												<c:otherwise>
												<p style="color: #666; height: 22px;text-indent: 31px;">${productList.columnName}：${productList.price} 元</p>
												</c:otherwise>
											</c:choose>
									    </c:if>
									</c:forEach>
									<p style="color: #666; height: 22px;"><i class="icon icon-shengdai" style="margin-right: 10px;"></i>省代价格：${actPro.provincePrice} 元</p>
								</c:if>
				            </div>
				            <div class="operate">
				            	<c:if test="${not empty actPro.productMap.message && not actPro.isBrand }">
								<c:if test="${isDealerLogin!=null}">
										<a class="active" href="javascript:void(0);" onclick="joinShop('${actPro.productId}','A00002')" >加入进货单</a>
								</c:if>				
								<c:if test="${isDealerLogin==null}">
										<a class="active" href="javascript:void(0);" onclick="joinShopCookie('${actPro.productId}','A00002')" >加入进货单</a>
								</c:if>
								</c:if>
								<c:if test="${empty actPro.productMap.message || actPro.isBrand }">
				                	<a>加入进货单</a>
								</c:if>
				                月销量
				                <span  class="text-darkgold text-bold">${actPro.saleNum}件</span>
				            </div>
				        </div>
				    </li>
				    </c:forEach>
                    </ul>
                    <tags:page form="clearanceForm" page="${result.page}"/>
                    <!-- <div class="ts-pagination ts-pagination-sm mb20 mt10">
                        <div class="pagination" id="pagination">
                        </div>
                    </div> -->
                </div>
            </form:form>
            <div class="part-col2">
                <div class="explosion-col">
                    <div class="explosion-col-head" id="js-clearing-ot">
                        <h3>我的进货单</h3>
                    </div>
                    <div class="explosion-col-content">
                        <div class="real-time-con mb10" style="max-height: 476px;_height:476px;overflow: auto;">
                            <c:forEach items="${shopCartList}" var="item">
                             <c:set value="${fns:getImageDomainPath(item.image,160,160)}" var="cartProImage"></c:set>
                                <dl class="cart-item" id="id_${item.id}">
                                    <dt class="pic">
                                       <a href="${ctx}/market/product/${item.id}?code=A00002" target="_blank"><img src="${res}${cartProImage}" alt="" width="70" height="70"></a>
                                    </dt>
                                    <dd class="price">
                                        <span class="text-yahei text-orange text-lg"><span class="text-md">￥</span><fmt:formatNumber value="${item.pctivitiesPrice==null?item.productPrice:item.pctivitiesPrice}" type="currency" pattern="0.00" /> </span>
                                    </dd>
                                    <dd class="title"><a href="#">${item.productTitle}</a></dd>
                                    <dd class="del"><a class="icon icon-delete" href="javascript:;" onclick="removeCart('${item.cartId}','${item.id}')"></a></dd>
                                </dl>
                            </c:forEach>
                        </div>
                        <div class="clearing clearfix">
                            <a href="${ctx}/dealer/shopper" class="ts-button ts-button-orange fr" target="_blank">查看进货单</a>
                            <c:if test="${isDealerLogin}">
                                <a class="fr clearing-link" style="margin-right: 10px;" href="javascript:;" onclick="removeAll()">清空</a>
                            </c:if>
                            <c:if test="${isDealerLogin==null||isDealerLogin==false}">
                                <a class="fr clearing-link" style="margin-right: 10px;" href="javascript:;" onclick="cart.clear()">清空</a>
                            </c:if>
                            <p class="text-md text-yahei">共<span id="cartCount" class="text-orange">${fn:length(shopCartList)}</span>件商品</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/common/component/footer.jsp"></jsp:include>
<script src="/scripts/jquery.min.js"></script>
<script src="/scripts/seajs/seajs/2.1.1/sea.js" type="text/javascript"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="/scripts/seajs_config.js" type="text/javascript"></script>
<script src="${res}/scripts/common.js"></script>
<script src="/scripts/common/base-init.js"></script>

<%--<script type="text/javascript" charset="utf-8" src="${res}/scripts/dealer/json2.js" ></script>
<!--[if lte IE 8]><![endif]-->--%>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<%-- footer end --%>
</div>
<script>
    function searchPro() {
    	$("#currentPage").val(1);
        $("#clearanceForm").submit();
        //page.goTo(1);
    }

    $("[data-sort]").click(function(e){
        var sort = $(this).data("sort");
        searchSort(sort);

        $(".filter .ts-button-group .ts-button i").removeClass("clearance-idown").addClass("clearance-iupdown");
        $(this).find("i").addClass("clearance-idown").removeClass("clearance-iupdown");
        e.preventDefault();
    });

    function searchSort(sort) {
        $("#sort").val(sort);
    	var sortName = $.trim($("#sortName").val());
    	if(""==sortName || "asc"==sortName){
    		$("#sortName").val("desc");
    	}else{
    		$("#sortName").val("asc");
    	}
        searchPro();
    }

    //banner图
    seajs.use(["slide"], function (Slide) {
        new Slide({
            element: "#banner",
            panels: "#banner .ts-banner-item",
            effect: "fade",
            interval: 6180
        });
    });


</script>
<script>
	 var page;
    $(function () {
        
        //var html = template.render("feedback-templage", json);
        //$(".order-grid").html(html);
        //$(document).scrollTop($(".filter").offset().top-80);
        //购物车跟随底部浮动
        var _ot = $("#js-clearing-ot").offset().top;
        var _mainHei = $(".footer").offset().top;
        var _view = $(window).height();
       
        $(window).resize(function(){
            //窗口变化，重新获取可视范围高度
            _view = $(window).height();
        });

        $(".clearing").css({
            width:"210px",
            height:"30px"
        });

        $(window).scroll(function(){
            var _st = $(document).scrollTop();
            var flag = true;

            //w往下滚
            if(_st >= _ot && flag == true){
                $(".clearing").css({
                    "position":"fixed",
                    "bottom":"0",
                    zIndex:250
                });
                flag = false;
            }

            //往上滚
            if(_st < _ot){
                flag = true;
                $(".clearing").css("position","static");
            }

            //滚到底部
            if(_mainHei - _st <= _view){
                $(".clearing").css({
                    "position":"absolute",
                    "bottom":"0"
                });
            }
        });
        
        //从cookies 初始记录
        cart.loadGoods();
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
        parentID:".ts-list",childID:".ts-list li",_len:6,pad:0
    });
    //禁止筛选表单按回车键提交
    $("#clearanceForm").keydown(function(ev){
        if(ev.keyCode==13){
            return false;
        }
    });

    /**购物车*/
	var cart={};
    /**购物车 放入产品 添加 条目*/
    cart.addGoods=function(item){
    	if($("div.real-time-con").find('#id_'+item.productId).size()==1){
    		remind("error", "已加入进货单，请勿重复添加");
    		return;
    	}
    	var cartItem=[];
    	cartItem.push('<dl class="cart-item" id="id_'+item.productId+'">');
    	cartItem.push(' <dt class="pic">');
    	cartItem.push('  <a href="${ctx}/market/product/'+item.productId+'?code=A00002"><img src="'+item.image+'" alt="" width="70" height="70"></a>');
    	cartItem.push(' </dt>');
    	cartItem.push('<dd class="price">');
    	cartItem.push('    <span class="text-yahei text-orange text-lg"><span class="text-md">￥</span>'+item.price+'</span>');
    	cartItem.push(' </dd>');
    	cartItem.push('<dd class="title"><a href="${ctx}/market/product/'+item.productId+'?code=A00002">'+item.title+'</a></dd>');
    	//cartItem.push('<a onclick=cart.remove("'+item.productId+'")></a>');
    	cartItem.push('${isDealerLogin==true?"<dd class=\"del\"><a class=\"icon icon-delete\" href=\"javascript:;\" onclick=removeCart(\"'+item.cartId+'\",\"'+item.productId+'\")></a></dd>":"<dd class=\"del\"><a class=\"icon icon-delete\" href=\"javascript:;\" onclick=cart.remove(\"'+item.productId+'\")></a></dd>"}');
    	//<a onclick=cart.remove("'+item.productId+'")></a>
    	//<a onclick=removeCart("'+item.cartId+'","'+item.productId+'")></a>
    	cartItem.push('</dl>');
    	$("div.real-time-con").append(cartItem.join(""));
    	$("#cartCount").html(parseInt($("#cartCount").html())+1);
    };

    /**从cookies里载入物品到购物车*/
    cart.loadGoods=function(){
        seajs.use(["cookie"],function(Cookie){
            Cookie.get("cart_items",function(s){
                s = decodeURIComponent(s);
                //console.log(s);
                if(s == "undefined" || s == ""){
                    return;
                }
                if(${isDealerLogin!=null&&isDealerLogin}){
                    return;
                }
                var cart_items= jQuery.parseJSON(s);
                for(var i=0;i<cart_items.length;i++){
                    cart.addGoods(cart_items[i]);
                }
            });
        });
    };

    /**保存购物车到cookies*/
    cart.storeGoods=function(){
    	 seajs.use(["cookie"],function(Cookie){
    		 	var str_cart_item="";
    		 	var cart_items=[];
    		 	$("div.real-time-con dl").each(function(i,obj){
    		 		var cart_item={};
    		 		cart_item.productId=$(this).attr('id').split('_')[1];
    		 		cart_item.title=$(this).find('dd.title').html();
    		 		cart_item.price=$(this).find('dd.price span.text-lg').text().replace("￥","");;
    		 		cart_item.image=$(this).find('img').attr('src');
    		 		cart_item.code="A00002";
    		 		cart_items.push(cart_item);
    		 	});
    		 	str_cart_item=JSON.stringify(cart_items);
    	        Cookie.set("cart_items",str_cart_item,{path:'/',domain:document.domain});
    	 });
    };
    /**清空本地所有*/
    cart.clear=function(){
		$('div.real-time-con').empty();
		$("#cartCount").html(0);
		cart.storeGoods();
	};
	
	/**移除本地单个*/
	cart.remove=function(id){
		$('#id_'+id).detach();
		$("#cartCount").html(parseInt($("#cartCount").html()) - 1);
		cart.storeGoods();
	};
    /**直接加入后台购物车*/
    function joinShop(productId,code) {
        var objdata = new Object();
        objdata.title = "提示";
        $.post("${ctx}/dealer/shoper/purchase/" + productId+"/"+code, function (data) {
            if (data.code == 126000) {
            	remind("success", "此产品已成功加入进货单!");
            	var item={};
            	item.productId=productId;
            	item.image=$('#item_image_'+productId).attr('src');
            	item.title=$('#item_title_'+productId).text();
            	item.price=$('#item_price_'+productId).text();
            	item.cartId=data.object;
                cart.addGoods(item);
            }else if(data.code == 111048){
                //尚未购买服务
                objdata.content = "抱歉~您还未支付平台服务费，暂时无法下单马上支付1200元服务费，享受一手货源";
                confirmDialog(objdata,function(){
                    window.open("${ctx}/dealer/service/details?refrenceId=S001");
                });
            }else if(data.code == 111040){
                //已加入购物车
                remind("error", "已加入进货单，请勿重复添加");
            }
            else {
                remind("error", data.message);
            }
        }, "json");
    }
    /**直接加入本地购物车*/
    function joinShopCookie(productId,code) {
     	var item={};
     	item.productId=productId;
    	item.image=$('#item_image_'+productId).attr('src');
    	item.title=$('#item_title_'+productId).text();
    	item.price=$('#item_price_'+productId).text();
        cart.addGoods(item);
        cart.storeGoods();
    }


    /**移除远端购物车货物*/
    function removeCart(id,proudctId){
    	$.post("${ctx}/dealer/shoper/delete",{id:id},function(data){
    		if(data.code==111000){
    			cart.remove(proudctId);
    		}
    	},"json");
    }
    
    /**清空远端购物车货物*/
    function removeAll(){
    	$.post("${ctx}/dealer/shoper/deleteAll?ids=",function(data){
    		if(data.code==111000){
    			$('div.real-time-con').empty();
    			$("#cartCount").html(0);
    		}
    	},"json");
    }

</script>
</body>
</html>
