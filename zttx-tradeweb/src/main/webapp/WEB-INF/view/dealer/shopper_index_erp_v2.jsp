<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的进货单</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/market/shop-cart.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top" />
    <div class="header">
        <div class="ts-container">
            <div class="logo">
                <a href="/"><img src="${res}/images/common/logo-old.png"></a> 我的进货单
            </div>
            <div class="step step1 animated slideInDown">
            </div>
        </div>
    </div>
    <div class="main">
        <div class="ts-container">
            <div class="search">
                <form>
                    <label>快速下单：</label>
                    <input id="key" name="key" autocomplete="off" type="text" placeholder="可以直接输入货号快速下单" /><%--<button>搜索</button>--%>
                </form>
                <div class="search-result hide" style="display: none;"></div>
            </div>
            <div class="operation">
                <label><input class="ui-checkbox  js-check-all" type="checkbox"> 全选</label>
                <a class="link js-product-remove-multi" href="javascript:;">移除</a>
                <a class="link js-product-remove-all" href="javascript:;">清空进货单</a>
                <a class="link " href="${ctx}/common/dealer/productList" target="_blank">继续进货</a>
            </div>

            <div class="order-all">

                <c:set var="invalid" value="<%=DealerConstant.productState.PRODUCT_INVALID%>" />
                <c:set var="down" value="<%=DealerConstant.productState.PRODUCT_DOWN%>" />
                <c:set var="noright" value="<%=DealerConstant.productState.PRODUCT_NORIGHT%>" />
                <c:set var="credit" value="<%=DealerConstant.productState.PRODUCT_CREDIT%>" />

                <c:forEach var="shopersModel" items="${shopersModelList}" varStatus="status1">
                    <div class="order-item">
                        <div class="m-brand">
                            <label>
                                <input type="checkbox" class="ui-checkbox js-check-sub"/>
                            </label>
                            <a href="">${shopersModel.brandName}</a>
                            <a href=""><i class="icon-cat"></i></a>
                            <span>品牌名称：</span>
                            <a href="">${shopersModel.brandesName}</a>
                        </div>
                        <c:forEach var="dealerShoper" items="${shopersModel.dealerShoperList}" varStatus="status2">
                            <div class="m-con clearfix<c:if test="${dealerShoper.type eq invalid || dealerShoper.type eq down || dealerShoper.type eq noright}"> product-disable</c:if>">
                                <div class="m-pic fl clearfix">
                                    <div class="pic-box clearfix">
                                        <input class="fl js-check" type="checkbox" data-shopper-id="${dealerShoper.refrenceId}" autocomplete="off">
                                        <p class="fl">
                                            <a target="_blank" href="/market/product/${dealerShoper.productId}">
                                                <img src="${res}${fns:getImageDomainPath(dealerShoper.productImage,90, 90)}"  title=""/></a>
                                            <a target="_blank" class="huohao js-huohao" href="/market/product/${dealerShoper.productId}">货号：${dealerShoper.productNo}</a>
                                        </p>
                                    </div>
                                </div>
                                <div class="m-detail fr" data-z="${dealerShoper.discount}">
                                    <div class="o-hd clearfix">
                                        <div class="tit fl">
                                            <span>${dealerShoper.productTitle}</span>
                                            <c:if test="${dealerShoper.type eq invalid}">
                                                <span class="warning"><i class="icon-warning"></i>产品已失效</span>
                                            </c:if>
                                            <c:if test="${dealerShoper.type eq down}">
                                                <span class="warning"><i class="icon-warning"></i>产品已下架</span>
                                            </c:if>
                                            <c:if test="${dealerShoper.type eq noright}">
                                                <span class="warning"><i class="icon-warning"></i>产品已无权限</span>
                                            </c:if>
                                        </div>
                                        <div class="operate fr clearfix">
                                            <c:if test="${dealerShoper.isSample}">
                                                <a href="javascript:;" class="${dealerShoper.productType eq 2 ?'selected':''}" data-type="2" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-ny"></i>拿样</a>
                                            </c:if>
                                            <a href="javascript:;"  class="${dealerShoper.productType eq 0 ?'selected':''}"  data-type="0" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-yuan"></i>现款</a>
                                            <c:if test="${dealerShoper.type eq credit}">
                                                <a href="javascript:;"  class="${dealerShoper.productType eq 1 ?'selected':''}"  data-type="1" data-id="${dealerShoper.refrenceId}" data-z="${dealerShoper.discount}"><i class="icon-sf"></i>授信</a>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="o-bd attr-item">
                                        <a class="edit-btn" href="javascript:;"><i class="icon-edit"></i>修改</a>
                                        <div class="simple-edit" style="display: none;"></div>
                                        <div class="simple-function">选择更多<i class="css-arrow"></i></div>
                                        <table class="">
                                            <colgroup>
                                                <col width="320"/>
                                                <col width="140"/>
                                                <col width="60"/>
                                                <col width="150"/>
                                                <col width="200"/>
                                                <col width="140"/>
                                            </colgroup>
                                            <tbody>
                                            <c:forEach var="dealerShopers" items="${dealerShoper.dealerShopersList}" varStatus="state" >
                                                <tr>
                                                    <td class="p-colorsize">${dealerShopers.productSkuName}</td>
                                                    <td class="p-price" id="price_${dealerShopers.refrenceId}_${dealerShoper.refrenceId}"><fmt:formatNumber value="${dealerShopers.productSkuPrice}" type="currency" pattern="0.00"/></td>
                                                    <td class="p-stock">${dealerShopers.productStock}</td>
                                                    <td class="p-number">
                                                        <div class="js-count-1">
                                                            <input type="text" value="${empty dealerShopers.purchaseNum ? '0': dealerShopers.purchaseNum}"  data-discount="${dealerShoper.discount eq null?1:dealerShoper.discount}" data-price="<fmt:formatNumber value="${dealerShopers.productSkuPrice}" type="currency" pattern="0.00"/>" data-shopsid="${dealerShopers.refrenceId}" data-shopid="${dealerShoper.refrenceId}" data-max="${dealerShopers.productStock}" class="num-amount"
                                                                   autocomplete="off" />
                                                        </div>
                                                        <div class="js-count-2" style="display: none;">
                                                            <a href="javascript:;"><i class="icon-del js-del-single"></i></a>
                                                        </div>
                                                    </td>
                                                    <c:if test="${state.first}">
                                                        <td class="p-xiaoji" rowspan="${fn:length(dealerShoper.dealerShopersList)}">
                                                            数量小计：<em class="js-number"></em>件<br />
                                                            起批量：<em class="js-min-number">${dealerShoper.startNum}</em>件
                                                        </td>
                                                    </c:if>
                                                    <td class="p-count js-price-single">0</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="o-fd clearfix">
                                        <div class="more fl">
                                            <a href="javascript:;">选择更多</a>
                                        </div>
                                        <div class="count fr">
                                            <span>金额小计：<em class="js-price">00.00</em></span>
                                            <c:if test="${dealerShoper.productType==1 && dealerShoper.isCredit}">
                                                <span>折扣优惠:<em class="js-zkj">${dealerShoper.discount}</em></span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="m-bottom mt10">

                                <a class="js-product-add-fav" data-productid="${dealerShoper.productId}" href="javascript:;"><i class="icon-star"></i></a>
                                <a class="js-product-remove-single" data-shoper-id="${dealerShoper.refrenceId}" href="javascript:;"><i class="icon-del"></i></a>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>

            </div>

            <div class="bbar">
                <div class="bbar-left">
                    <label><input class="ui-checkbox js-check-all" type="checkbox"> 全选</label>
                    <a class="link js-product-remove-multi" href="javascript:;">移除</a>
                    <a class="link js-product-remove-all" href="javascript:;">清空进货单</a>
                    <a class="link" href="${ctx}/common/dealer/productList" target="_blank">继续进货</a>
                    <a class="link js-product-remove-off" href="javascript:;">批量移除已下架产品</a>
                </div>
                <div class="bbar-right clearfix">
                    <div class="fl">
                        已选商品
                        <span class="js-allNumber">0</span>件 | 总货款（不含运费）：
                        <span class="js-allPrice">￥0.00</span><br />（其中：授信产品货款&nbsp;
                        <span class="js-dispribute normols">￥0.00</span>&nbsp;现款现货产品货款&nbsp;
                        <span class="js-nowproductprice normols">￥0.00</span>）
                    </div>
                    <div class="fr">
                        <button class="ui-button ui-button-lred js-clearing">结 算</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">

        </div>
    </div>
    <form:form id="addlist-form-quick" method="post" action="${ctx}/dealer/dealerShoper/addCart"/>
    <form:form id="submit-form-balance"  action="${ctx}/dealer/dealerShoper/balance" method="post"></form:form>
    <script id="history_product_list_tempalte" type="text/html">
        {{each rows}}
        <div class="weshop-item ui-switchable-panel">
            <a class="item-pic">
                <img src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}"/>
                <span></span>
            </a>
            <h3 class="item-title c-r">直供价<span class="fs14">￥{{$formatPrice $value.price}}</span></h3>
            <p class="item-content"></p>
            <div class="item-btn">
                {{if $value.productState=='STATE_UNAUTH' }}  <a class="ui-button ui-button-mwhite">询价</a> {{/if}}
                {{if $value.productState!='STATE_UNAUTH' }}  <a class="ui-button ui-button-mwhite" href="javascript:quickFormSubmit('{{$value.id}}')">加入进货单</a> {{/if}}
            </div>
        </div>
        {{/each}}
    </script>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <script src="${src}/common/jquery.cart.js"></script>
    <script src="${src}/dealer/json2.js"></script>
    <script src="${src}/common/base-init.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <script>
        var attrSimple = $(".simple-function"),
                attrItem = $(".attr-item");

        var myOrder = {
            init: function () {
                this.initUI();          //初始化界面
                this.initCheckbox();    //初始化多选
                this.initNumber();      //初始化数字输入控件
                this.initOrder();       //初始化订单列表
                this.initSubmit();      //初始化结算按钮
                this.initOpction();     //初始化操作按钮
                this.editAnddel();
                /*this.handelProductPrice();*/
                this.editAnddel();
            },
            initUI:function(){
                var disablebox =  $(".product-disable");    //寻找失效订单
                $(disablebox).find("input").each(function(i,o){o.disabled=true});   //禁止输入
                $(disablebox).find("button").each(function(i,o){o.disabled=true});  //禁止按钮
            },
            initCheckbox:function(){
                var $this = this;
                //总全选事件
                var js_check_all =  $(".js-check-all").click(function(){
                    var me = this;
                    $(js_check_all).each(function(){this.checked =me.checked; });
                    $(js_check_sub).each(function(){this.checked =me.checked; });
                    $(js_check).each(function(){if(!this.disabled){this.checked =me.checked;} });
                    $this._settlement();
                });
                //品牌内全选事件
                var js_check_sub = $(".js-check-sub").click(function(){
                    var me = this;
                    $(this).parents(".order-item").find(".js-check").each(function(){if(!this.disabled){this.checked =me.checked;} });
                    $this._settlement();
                });

                //单品全选事件
                var js_check = $(".js-check").click(function(){
                    $this._settlement();
                });
            },
            initNumber: function () {
                var me = this;
                //输入框插件引入
                $(attrItem).find("tr").cartInit({}, function () {},function(nm,$this){
                    me._total($this);//小结算
                    me._settlement();//结算
                    me._updateSingle();//记住数量
                });
            },
            //处理小结算
            _total:function(ctrl){
                var box = $(ctrl).parents(".attr-item");
                var panel = $(ctrl).parents("tr");
                var detail = $(ctrl).parents(".m-detail");
                var allprice= 0,allnumber=0;
                $(box).find(".num-amount").each(function(i,o){
                    var _number = Number(o.value);
                    var _price = Number($(o).data("price"));
                    $(this).parents("tr").find(".js-price-single").text((_number * _price).toFixed(2));
                    allprice += _number * _price;
                    allnumber += _number;
                });
                $(box).find(".js-number").text(allnumber);
                $(detail).find(".js-price").text(allprice.toFixed(2));
                $(detail).find(".js-zkj").text((allprice * (1 - Number($(detail).data("z")))).toFixed(2));
            },
            //处理大结算
            _settlement:function(){

                var allNumber = 0,allPrice = 0,dispributePrice = 0, nowPrice = 0;
                $(".js-check:checked").each(function(i,o){
                    //var panel = $(o).parents("tr:not(.product-disable)");
                    var panel = $(o).parents(".m-con:not(.product-disable)");
                    if($(panel).find(".operate a.selected").data("type") == "2" || $(panel).find(".operate a.selected").data("type") == "0"){
                        nowPrice += Number($(panel).find(".js-price").text());
                        allPrice+=Number($(panel).find(".js-price").text());
                    }
                    if($(panel).find(".operate a.selected").data("type") == "1"){
                        dispributePrice += (Number($(panel).find(".js-price").text()) - Number($(panel).find(".js-zkj").text()));
                        allPrice+=Number(Number($(panel).find(".js-price").text()) - Number($(panel).find(".js-zkj").text()));
                    }
                    allNumber+=Number($(panel).find(".js-number").text());

                });
                $(".js-allNumber").text(allNumber);
                $(".js-allPrice").text(allPrice.toFixed(2));
                $(".js-nowproductprice").text(nowPrice.toFixed(2));
                $(".js-dispribute").text(dispributePrice.toFixed(2));
            },
            //处理数量保存
            _updateSingle: function (type) {
                var _paramsArry = [];
                $(".js-ischange .num-amount").each(function(i,o){
                    var refrenceId = $(o).data("shopsid");
                    var shopId = $(o).data("shopid");
                    var discount =$(o).data("discount");
                    var purchaseNum = $(o).val();
                    var _params = { "refrenceId": refrenceId, "shopId":shopId, "purchaseNum": parseInt(purchaseNum),"discount":discount};
                    _paramsArry.push(_params);
                });
                //遮罩引入
                if(_paramsArry.length>0){
                    dialogMask(function(dd){
                        $.ajax({type:'POST',url:G_PATH+"/dealer/dealerShopers/batchUpdate",
                            data: JSON.stringify(_paramsArry),
                            contentType: 'application/json',
                            dataType:'json',
                            success:function(data){
                                //console.log(_paramsArry);
                                $(".js-clearing")[0].disabled = false;//.data({"issubmit":true});
                                $(".js-ischange").removeClass("js-ischange");
                                dd.hide();
                            }
                        });
                    });
                }
            },
            //订单列表处理
            initOrder: function () {
                var me = this;
                $(attrSimple).filter(":odd").css({ "background": "#f9f9f9" });
                $(attrItem).filter(":odd").css({ "background": "#f9f9f9" });

                //判断默认值加上状态
                $(".num-amount").each(function (i, $this) {
                    if ($this.value.length > 0 && $this.value!="0") {
                        $($this).parents("tr").addClass("js-notnull");
                    }
                });
                //初始第一个产品不隐藏
                $(attrItem).filter(':gt(0)').find("tr:not(.js-notnull)").hide();

                //展开按钮事件
                $(attrSimple).click(function () {
                    me._myHide();
                    $(this).hide();  //隐藏当前按钮
                    //显示列表
                    $(this).next().find("tr").show();

                }).filter(':gt(0)').show();
                me._myHide();
            },
            //提交处理
            initSubmit:function(){
                $(".js-clearing").click(function(){
                    //myOrder.handelProductPrice(function(){
                    //判断有没有勾选商品
                    var checkeds = $(".js-check:checked:not(:disabled)");
                    if ($(checkeds).length < 1) {
                        remind("error", '至少勾选一个产品');
                        return false;
                    }

                    var isSubmit = true;
                    $(checkeds).each(function(i,o){
                        var count = 0;
                        var box = $(o).parents("tr");
                        var minnumber = $(box).find(".js-min-number").css({"color": "#333"});
                        var amount = $(box).find(".num-amount").css({"background":"#fff"});
                        var nayangSign = $(box).find(".js-nayang").length;
                        var hasTake = $(box).find(".js-nayang").data("hastake");
                        $(amount).each(function(ix,n){
                            count+= Number(n.value);
                        });

                        //增加拿样产品的判断，拿样产品没有起批量要求
                        if(nayangSign <= 0 && Number($(minnumber).text()) > count){
                            $(minnumber).css({"color":"#e00"});
                            $(amount).css({"background":"#FFFFCC"});
                            remind("error","商品没有达到起批量的要求",function(){});
                            isSubmit =  false;
                        }
                        //没拿过样的才能拿样，拿过了的不能再拿
                        if(nayangSign > 0 && hasTake == true && Number($(minnumber).text()) > count){
                            $(minnumber).css({"color":"#e00"});
                            $(amount).css({"background":"#FFFFCC"});
                            remind("error","商品没有达到起批量的要求",function(){});
                            isSubmit =  false;
                        }
                    });

                    //将勾选商品存放并提交;
                    var shopperIds=[];
                    $(checkeds).each(function(){
                        shopperIds.push($(this).data("shopper-id"));
                    });

                    if(isSubmit && shopperIds.length > 0){
                        $('#submit-form-balance').append($('<input type="hidden" name="shopperIds">').val(shopperIds.join(","))).submit();
                    }
                });
                //});
            },
            //初始化展开按钮
            _myHide: function () {
                $(attrItem).each(function (i, o) {
                    var nul =  $(o).find("tr:not(.js-notnull)");
                    $(this).find(".simple-edit").css("height", $(this).height() + 10);
                    if ($(nul).length > 0) {
                        //$(attrSimple).eq(i).show().html('选择更多({a})<i class="css-arrow"></i>'.replace("{a}",$(nul).length));//显示所有展开按钮
                        $(attrSimple).eq(i).show().html('选择更多<i class="css-arrow"></i>');//显示所有展开按钮
                    }
                });
                //隐藏所有未填写列
                $(attrItem).find("tr:not(.js-notnull)").hide();
            },
            //删除 收藏等处理;
            initOpction: function(){
                /**快速加入进货单 By JuST4iT*/
                window['quickFormSubmit'] = function (productId,productType) {
                    $('#addlist-form-quick').append($('<input type="hidden">').attr('name', 'productsId').val(productId));
                    $('#addlist-form-quick').append($('<input type="hidden">').attr('name', 'productsType').val(productType)).submit();
                };

                /**清空进货单所有产品 By JuST4iT*/
                $(".js-product-remove-all").click(function () {
                    confirmDialog({ title: "提示", content: "确定清空吗" }, function () {
                        $.post(G_PATH+"/dealer/dealerShoper/removeCart", { dealerShopersId: null }, function (data) {console.log(data);

                            if (data != null) {
                                if (data.code == 126000) {
                                    remind("success", "成功清除");
                                    window.location.reload();
                                } else {
                                    remind("error", data.message);
                                }
                            }
                        }, "json");
                    });
                });

                /**清空进货单多个产品 By JuST4iT*/
                $(".js-product-remove-multi").click(function () {
                    var ids = [];
                    $(".js-check:checked").each(function (i, o) {
                        ids.push($(o).data("shopper-id"));
                    });
                    if (ids.length < 1) {
                        remind("error", '请选择需移除的产品');
                        return false;
                    }
                    confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                        $.post(G_PATH+"/dealer/dealerShoper/removeCart", { "dealerShopersId": ids.join(",") }, function (data) {
                            if (data != null) {
                                if (data.code == 111000 || data.code == 126000) {
                                    remind("success", "成功移除");
                                    window.location.reload();
                                } else {
                                    remind("error", data.message);
                                }
                            }
                        }, "json");
                    });

                });

                /**清空进货单单个产品 By JuST4iT*/
                $(".js-product-remove-single").click(function () {
                    var shopperId = $(this).data("shoper-id");
                    confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                        $.post(G_PATH+"/dealer/dealerShoper/removeCart", { "dealerShopersId": shopperId }, function (data) {
                            console.log(data);
                            if (data != null) {
                                if (data.code == 126000) {
                                    remind("success", "移除成功");
                                    window.location.reload();
                                } else {
                                    remind("error", data.message);
                                }
                            }
                        }, "json");
                    });
                });

                /**清空进货单下加产品 By JuST4iT*/
                $(".js-product-remove-off").click(function () {
                    confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                        $.post(G_PATH+"/dealer/shoper/deleteAllOff", function (data) {
                            if (data != null) {
                                if (data.code == 126000) {
                                    remind("success", "移除成功");
                                    window.location.reload();
                                } else {
                                    remind("error", data.message);
                                }
                            }
                        }, "json");
                    });
                });

                /**添加产品到产品收载夹 By JuST4iT*/
                $(".js-product-add-fav").click(function () {
                    var productId = $(this).data("productid");
                    $.post(G_PATH+"/dealer/dealerFavorite/addFavorite", { "productId": productId }, function (data) {
                        data = $.parseJSON(data);
                        if (data != null) {
                            if (data.code == 126000) {
                                remind("success", "产品收藏成功");
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                });
            },
            //处理产品价格同步的方法
            handelProductPrice: function(callback){
                var _self = this;
                dialogLoading(function(__d){
                    setTimeout(function(){
                        $.ajax({
                            url: "",   /*/dealer/productPriceLog/listLatestProductPrice*/
                            method: "post",
                            data: $("#_csrf-form").serialize(),
                            dataType: "json",
                            success: function(data){
                                //console.log(data);
                                if(data.rows && data.rows.length > 0){
                                    var __len = data.rows.length;
                                    for(var i = 0; i < __len; i++){
                                        var __list = data.rows[i];
                                        if(__list.valid == true){
                                            $("#hpp_pid_" + __list.productId).removeClass("product-disable"); // 设置产品可用
                                            $("#hpp_pid_" + __list.productId).find(".m-offline").remove();
                                            $("#hpp_pid_" + __list.productId).find(".ui-button-sorange").remove();
                                            if($("#hpp_pid_" + __list.productId).find(".js-product-add-fav").length <= 0){
                                                $("#hpp_pid_" + __list.productId).find(".js-product-remove-single").before('<a class="link js-product-add-fav" data-productid="' + __list.productId + '" href="javascript:;"><i class="icon i-add-blue"></i>加入收藏夹</a><br>');
                                            }
                                            $("#hpp_pid_" + __list.productId).find("input").prop("disabled", false);
                                            $("#hpp_pid_" + __list.productId).find("button").prop("disabled", false);
                                            //console.log(__list.listProductSku);
                                            if(__list.listProductSku && __list.listProductSku.length > 0){
                                                var __skuLen = __list.listProductSku.length;
                                                for(var j = 0; j < __skuLen; j++){
                                                    //__list.listProductSku[j]
                                                    if(__list.listProductSku[j].valid == true && __list.listProductSku[j].directPrice){
                                                        $("#hpp_price_" + __list.listProductSku[j].productSkuId).text(__list.listProductSku[j].directPrice.toFixed(2));
                                                    }else{
                                                        //跟后台协定：SKU失效的时候，前台把数量置为0，并和后台同步一下，再进行提交。
                                                        $("#hpp_price_" + __list.listProductSku[j].productSkuId).parent().find(".num-amount").val("0");
                                                        $("#hpp_price_" + __list.listProductSku[j].productSkuId).parent().addClass("js-ischange");
                                                        $("#hpp_price_" + __list.listProductSku[j].productSkuId).parent().find("input").each(function(i,o){o.disabled=true});
                                                        $("#hpp_price_" + __list.listProductSku[j].productSkuId).parent().find("button").each(function(i,o){o.disabled=true});
                                                    }
                                                }
                                            }
                                        }else{
                                            $("#hpp_pid_" + __list.productId).addClass("product-disable"); // 禁用已失效的产品
                                            $("#hpp_pid_" + __list.productId).find(".js-product-add-fav").remove();
                                            $("#hpp_pid_" + __list.productId).find(".js-huohao").after("<i class=\"m-offline\">已失效</i>");
                                            myOrder.initUI();
                                        }
                                    }
                                    //循环结束跟后台同步数量
                                    myOrder._updateSingle();
                                }
                                __d.hide();
                                if(callback && $.type(callback) == "function"){
                                    callback();
                                }
                            }
                        });
                    }, 1000);
                },"正在同步产品信息，请稍后...");
            },
            editAnddel: function(){
                $(".js-del-single").click(function(){
                    var $p_tr = $(this).parents("tr");
                    var $p_item = $(this).parents(".attr-item");
                    //$p_tr.removeClass("js-notnull").hide();
                    $p_tr.addClass("already-delete");
                    $p_tr.find(".num-amount").val("0");
                    $p_item.find(".simple-function").show();
                });

                $(".edit-btn").click(function(){
                    var $parents = $(this).parents(".attr-item");
                    if($(this).next().is(":visible")){
                        $(this).next().hide();
                        $parents.find(".js-count-1").show();
                        $parents.find(".js-count-2").hide();
                    }else{
                        if($parents.find("tr:visible").length > 0){
                            $(this).next().css("height", $parents.height() + 20).show();
                            $parents.find(".js-count-1").hide();
                            $parents.find(".js-count-2").show();
                        }
                    }
                });
            }

        };
        //非相关处理;
        var otherProduct = {
            init: function () {
                this.initTabs();
                //this.initCarousel();
                this.initQuickOrder();
            },
            initTabs: function () {
                var tab = $(".other .tab-head a");
                var item = $(".other .tab-item");
                $(tab).click(function () {
                    $(tab).removeClass("active");
                    var index = $(this).addClass("active").index();
                    $(item).hide().eq(index).show();
                });
            },
            initCarousel: function () {
                seajs.use(['carousel', '$'], function (Carousel, $) {
                    new Carousel({
                        element: '#Carousel1',
                        hasTriggers: false,
                        easing: 'easeOutStrong',
                        effect: 'scrollx',
                        panels: "#Carousel1 .ui-switchable-panel",
                        step: 1,
                        viewSize: [192],
                        circular: true,
                        autoplay: false
                    }).render();

                    new Carousel({
                        element: '#Carousel2',
                        hasTriggers: false,
                        easing: 'easeOutStrong',
                        effect: 'scrollx',
                        step: 1,
                        viewSize: [192],
                        circular: true,
                        autoplay: false
                    }).render();
                });
            },
            initQuickOrder: function () {
                /**快速下单 事件*/
                var monitor = 0;
                $('#key').keyup(function () {
                    clearTimeout(monitor);
                    var key = $(this).val();
                    if (key.length > 0) {
                        monitor = setTimeout(function () {
                            loadData(key);
                        }, 618);
                    }
                }).focus(function(){
                    $(this).trigger("keyup"); //0624 zxb add
                });

                function loadData(key) {
                    $.post("/dealer/dealerShoper/quickCart", { keyWord: key }, function (remote) {
                        if (remote.code == 126000) {
                            var data = remote.rows;
                            if (data.length > 0 && key.length > 0) {
                                var result = $(".search-result").html("<ul></ul>").show().hover(null, function () { $(this).hide(); }); //<i class='close'>
                                for (var i in data) {
                                    var _id = data[i].productId;
                                    var _text = data[i].productNo.replace(new RegExp(key, 'gi'), "<b>" + key + "</b> ");
                                    var _isAdd = data[i].isAdd;
                                    var _type = 0;   //普通产品
                                    if(data[i].isCredit)
                                    {
                                        type =1;     //授信产品
                                    }
                                    var li = $("<li>").appendTo($(result).find("ul"));
                                    var div = $("<div>").appendTo(li);
                                    var a = $(_isAdd ? '<a href="javascript:void(0)" class="disable">已加入进货单</a>' : '<a href="javascript:quickFormSubmit(\'' + _id + '\',\'' + _type + '\')">加入进货单</a>').appendTo(div);
                                    var span = $("<span>").html(_text).appendTo(div);
                                }
                                $(result).find("div").hover(function () {
                                    $(this).toggleClass("on");
                                });
                            }
                            else {
                                //搜索结果框鼠标移出事件
                                $(".search-result").hide();
                            }
                        }
                    }, "json");
                }
            }
        };


        /*更改交易模式*/
        seajs.use(['template'], function(Template){
            $(".operate a").click(function(){

                if($(this).hasClass("selected")){
                    $(this).removeClass("selected");
                    myOrder._settlement();
                    return false;
                }else{

                    var type = $(this).data("type");
                    var id = $(this).data("id");
                    var parents = $(this).parents(".attr-item");
                    var o_parents = $(this).parents(".m-con");

                    //拿样、现款
                    if(type == 0 || type == 2){
                        if(o_parents.find(".count span").length == 2){
                            o_parents.find(".count span").eq(1).remove();
                        }
                    }

                    //授信
                    if(type == 1){
                        var _z = parseFloat($(this).data("z"));
                        var _price = parseFloat(o_parents.find(".js-price").text());
                        var _count = (_price * (1 - _z)).toFixed(2);
                        //o_parents.find(".js-price").text();
                        if(o_parents.find(".count span").length == 1){
                            var tpl = '<span>折扣优惠:<em class="js-zkj">' + _count + '</em></span>';
                            o_parents.find(".count").append(tpl);
                        }else{
                            o_parents.find(".js-zkj").text(_count);
                        }
                    }

                    $.ajax({
                        url: '${ctx}/dealer/dealerShoper/updateTradeModel',
                        method:'post',
                        data: {productType: type, shoperId: id},
                        dataType: 'json',
                        success: function(data){
                            console.log(data);
                            if(data && data.object && data.object.dealerShopersList.length > 0){
                                for(var i = 0, _len = data.object.dealerShopersList.length; i < _len; i++){
                                    $("#price_" + data.object.dealerShopersList[i].refrenceId + "_" + data.object.dealerShopersList[i].shoperId).text(data.object.dealerShopersList[i].productSkuPrice.toFixed(2));
                                }
                            }
                        }
                    });

                    $(this).parent().find("a").removeClass("selected");
                    $(this).addClass("selected");
                    myOrder._settlement();
                    return false;
                }
            });
        });

        /*新增的js 结束*/


        otherProduct.init();
        myOrder.init();
        setInterval(myOrder.handelProductPrice, 1000 * 60 * 20);
    </script>
</body>
</html>