/**
 * 我的进货单
 * */
define(function(require, exports, module){

    var _        = require('underscore');
    var Template = require("template");
    var Tip      =  require("tip");

    Template.helper('$formatPrice', function (price ) {
        if(price == null){
            return 0.00;
        }else{
            return price.toFixed(2);
        }
    });

    var attrSimple = $(".simple-function"),
        attrItem = $(".attr-item");

    var cacheObj = {};    //当前页面缓存所有SKU信息的对象，下面提供了一些写该对象的方法。 操作关系到 “数量，价格，类型，折扣” 的一定要记得写该缓存。

    //用于初始化生成所需的缓存数组
    function setObjValues(options){
        for(var key in cacheObj){
            if(key === options.shopid){
                cacheObj[options.shopid].push(options);
            }
        }
        if(!cacheObj[options.shopid]){
            cacheObj[options.shopid] = [];
            cacheObj[options.shopid].push(options);
        }
    }

    //用于后面单个设置缓存数组的值，shopid/skuid 必须提供，其他值提供则重写，不提供则保持原样  设置type的时候不需传skuid
    function setObjSingleValues(options){
        var _len = cacheObj[options.shopid].length;
        for(var i = 0; i < _len; i++){
            if(options.skuid && cacheObj[options.shopid][i].skuid == options.skuid){
                if(options.num !== undefined){
                    cacheObj[options.shopid][i].num = options.num;
                }
                if(options.price !== undefined){
                    cacheObj[options.shopid][i].price = options.price;
                }
                if(options.type !== undefined){
                    cacheObj[options.shopid][i].type = options.type;
                }
                if(options.zk !== undefined){
                    cacheObj[options.shopid][i].zk = options.zk;
                }
                //console.log(cacheObj);
                return false;
            }
            if(!options.skuid){
                //设置type的时候不需传skuid
                cacheObj[options.shopid][i].type = options.type;
            }
        }
    }

    //初始化生成一个缓存对象，为了提升页面运行能力，可以改为由后台组装好提供给前台。
    function initObjValues(){
        //var skuLength = 0;
        $(".num-amount").each(function(){
            var shopid  = $(this).data("shopid");
            var shopsid = $(this).data("shopsid");
            var skuid   = $(this).data("skuid");
            var num     = $(this).val();
            var price   = $(this).data("price");
            var type    = $(this).data("type");
            var zk      = $(this).data("z");
            var fdbl    = parseFloat($(this).data("fdbl"));  //返点需求追加的返点比例
            setObjValues({
                shopid: shopid,
                shopsid: shopsid,
                skuid: skuid,
                num: num,
                price: price,
                type: type,
                zk: zk,
                fdbl: (fdbl/100).toFixed(2)
            });

            //skuLength ++;
        });
        //console.log("测试当前SKU总个数： " + skuLength);
    }

    //不传值则整体计算价格。传值则计算某个产品的价格数量等。
    function initSinglePrice(shopid){
        if(!shopid){
            //初始化所有单条价格
            for(var key in cacheObj){
                var _len = cacheObj[key].length;
                var _number = 0;  //数量统计
                var _price = 0;   //产品价格统计
                var _zk;          //折扣
                for(var i = 0; i < _len; i++){

                    if(cacheObj[key][i].type == 3 && cacheObj[key][i].fdbl){
                        var _singlePrice = cacheObj[key][i].num * (cacheObj[key][i].price * (1 - cacheObj[key][i].fdbl));
                    }else{
                        var _singlePrice = cacheObj[key][i].num * cacheObj[key][i].price;
                    }

                    _number += Number(cacheObj[key][i].num);

                    _price += _singlePrice;

                    _zk = Number(cacheObj[key][i].zk);
                    $("#single_" + cacheObj[key][i].skuid).text(_singlePrice.toFixed(2));
                }

                $("#number_" + key).text(_number);
                $("#price_" + key).text(_price.toFixed(2));
                if(_zk != ""){
                    $("#zkj_" + key).text( (_price * (1 - _zk)).toFixed(2) );
                }
            }
        }else{
            //只计算单个产品的单条价格
            var key = shopid;
            var _len = cacheObj[shopid].length;
            var _number = 0;  //数量统计
            var _price = 0;   //产品价格统计
            var _zk;          //折扣
            for(var i = 0; i < _len; i++){

                if(cacheObj[key][i].type == 3 && cacheObj[key][i].fdbl){
                    var _singlePrice = cacheObj[key][i].num * (cacheObj[key][i].price * (1 - cacheObj[key][i].fdbl));
                }else{
                    var _singlePrice = cacheObj[key][i].num * cacheObj[key][i].price;
                }

                _number += Number(cacheObj[key][i].num);
                _price += _singlePrice;

                _zk = Number(cacheObj[key][i].zk);
                $("#single_" + cacheObj[key][i].skuid).text( _singlePrice.toFixed(2) );
            }

            $("#number_" + key).text(_number);
            $("#price_" + key).text(_price.toFixed(2));
            if(_zk != ""){
                $("#zkj_" + key).text( (_price * (1 - _zk)).toFixed(2) );
            }
        }
    }

    setTimeout(function(){
        initObjValues();
        initSinglePrice();
        //console.log(cacheObj);
    }, 300);

    var myOrder = {
        init: function(){
            this.initUI();
            this.initCheckbox();
            this.initNumber();
            /*this.initOrder();  */
            this.initSubmit();
            this.initOpction();
            this.toggleMore();
            //this.editAnddel();
            this.fdTips();
        },
        initUI:function(){
            var disablebox =  $(".product-disable");                             //寻找失效订单
            $(disablebox).find("input").each(function(i,o){o.disabled=true});   //禁止输入
            $(disablebox).find("button").each(function(i,o){o.disabled=true});  //禁止按钮
        },
        initCheckbox:function(){
            var that = this;
            //全选
            $(".js-check-all").click(function(){
                $(".js-check-sub:not(:disabled),.js-check:not(:disabled),.js-check-all:not(:disabled)").prop("checked", this.checked);
                that._settlement();
            });
            //品牌选产品
            $(".js-check-sub").click(function(){
                $(this).parents(".order-item").find(".js-check:not(:disabled)").prop("checked", this.checked);
                that._settlement();
            });
            //产品勾选
            $(".js-check").click(function(){
                that._settlement();
            });
        },
        initNumber: function(){
            var that = this;

            function verifyNumbers(val, max, o){

                var result = 0;

                if(max == ""){
                    remind("error", "购买量太大");
                    return result;
                }

                if(!$.isNumeric(val)){
                    return result;
                }

                if(val < 0){
                    result = 0;
                }else if(val > max){
                    remind("error", "购买量太大");
                    result = max;
                }else{
                    result = val;
                }

                if(result == 0){
                    o.removeClass("js-notnull");
                }
                else{
                    o.addClass("js-notnull");
                }
                o.addClass("js-ischange");

                return result;
            }

            var countAndUpdate = _(function(shopid, skuid, _val) {
                setObjSingleValues({shopid: shopid, skuid: skuid, num: _val}); //写当前值的缓存数组
                initSinglePrice(shopid);  //小结算
                that._settlement();       //结算
                that._updateSingle();     //记住数量
            }).debounce(300);

            $(document).on("click", ".num-minus", function(){
                var $amount = $(this).next(),
                    $tr = $(this).parents("tr"),
                    shopid = $amount.data("shopid"),
                    skuid = $amount.data("skuid"),
                    _val = $amount.val(),
                    _max = $amount.data("max");
                _val--;
                _val = verifyNumbers(_val, _max, $tr);
                $amount.val(_val);

                countAndUpdate(shopid, skuid, _val);
            });
            $(document).on("click", ".num-plus", function(){
                var $amount = $(this).prev(),
                    $tr = $(this).parents("tr"),
                    shopid = $amount.data("shopid"),
                    skuid = $amount.data("skuid"),
                    _val = $amount.val(),
                    _max = $amount.data("max");
                _val++;
                _val = verifyNumbers(_val, _max, $tr);
                $amount.val(_val);

                countAndUpdate(shopid, skuid, _val);
            });
            $(document).on("keyup", ".num-amount", function(){
                var $amount = $(this),
                    $tr = $(this).parents("tr"),
                    shopid = $amount.data("shopid"),
                    skuid = $amount.data("skuid"),
                    _val = $amount.val().replace(/[^\d]/g, ''),
                    _max = $amount.data("max");

                $amount.val(_val);
                _val = verifyNumbers(_val, _max, $tr);
                $amount.val(_val);

                countAndUpdate(shopid, skuid, _val);
            });
        },
        //处理大结算
        _settlement:function(){

            var allNumber = 0,allPrice = 0,dispributePrice = 0, nowPrice = 0, rebatePrice = 0;
            $(".js-check:checked").each(function(i,o){
                //var panel = $(o).parents("tr:not(.product-disable)");
                var panel = $(o).parents(".m-con:not(.product-disable)");
                var _type = $(panel).find(".operate a.selected").data("type");

                if(_type == "0"){
                    var _zkjPrice = Number($(panel).find(".js-zkj").text()) ? Number($(panel).find(".js-zkj").text()) : 0;
                    nowPrice += (Number($(panel).find(".js-price").text()) - _zkjPrice);
                    allPrice+=Number($(panel).find(".js-price").text() - _zkjPrice);
                }
                if(_type == "2"){
                    nowPrice += Number($(panel).find(".js-price").text());
                    allPrice+=Number($(panel).find(".js-price").text());
                }
                if(_type == "1"){
                    dispributePrice += (Number($(panel).find(".js-price").text()) - Number($(panel).find(".js-zkj").text()));
                    allPrice+=Number(Number($(panel).find(".js-price").text()) - Number($(panel).find(".js-zkj").text()));
                }
                if(_type == "3"){
                    rebatePrice += Number($(panel).find(".js-price").text());
                    allPrice+=Number($(panel).find(".js-price").text());
                }
                allNumber+=Number($(panel).find(".js-number").text());

            });

            $(".js-allNumber").text(allNumber);                         //已选商品
            $(".js-allPrice").text(allPrice.toFixed(2));                //总货款
            $(".js-nowproductprice").text(nowPrice.toFixed(2));         //现款现货货款
            $(".js-dispribute").text(dispributePrice.toFixed(2));       //授信货款
            $(".js-rebateprice").text(rebatePrice.toFixed(2));          //返点货款
        },
        //处理数量保存
        _updateSingle: function (type) {
            var _paramsArry = [];
            $(".js-ischange .num-amount").each(function(i,o){
                var refrenceId = $(o).data("shopsid");
                var shopId = $(o).data("shopid");
                var discount =$(o).data("discount");
                var productSkuPrice = $(o).data("price");
                var productSkuId = $(o).data("skuid");
                var purchaseNum = $(o).val();
                var _params = { "refrenceId": refrenceId, "shopId":shopId, "purchaseNum": parseInt(purchaseNum),"discount":discount,"productSkuPrice":productSkuPrice,"productSkuId":productSkuId};
                _paramsArry.push(_params);
            });
            //遮罩引入
            if(_paramsArry.length>0){
                dialogMask(function(dd){
                    $.ajax({type:'POST',url:"/dealer/dealerShopers/batchUpdate",
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
            /*var me = this;
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
                var $parents = $(this).parents(".attr-item");

                me._myHide();
                $(this).hide();  //隐藏当前按钮
                //显示列表
                $(this).next().find("tr").show();
                $(this).prev().css("height", $parents.height() + 20);
            }).filter(':gt(0)').show();
            me._myHide();*/
        },
        //提交处理
        initSubmit:function(){
            $(".js-clearing").click(function(){
                myOrder.handelProductPrice(function(){
                    //判断有没有勾选商品
                    var checkeds = $(".js-check:checked:not(:disabled)"), remErrorMsg = "";

                    if ($(checkeds).length < 1) {
                        remind("error", '至少勾选一个产品');
                        return false;
                    }

                    var isSubmit = true;
                    $(checkeds).each(function(i,o){
                        var count = 0;
                        var box = $(o).parents(".m-con");
                        var minnumber  = $(box).find(".js-min-number").css({"color": "#333"});
                        var amount     = $(box).find(".num-amount").css({"background":"#fff"});
                        var nayangSign = $(box).find(".js-nayang.selected").length;
                        $(amount).each(function(ix,n){
                            count += Number(n.value);
                        });
                        //console.log(count);
                        //增加拿样产品的判断，拿样产品没有起批量要求
                        if(nayangSign <= 0 && Number($(minnumber).text()) > count){
                            $(minnumber).css({"color":"#e00"});
                            $(amount).css({"background":"#FFFFCC"});
                            //remind("error","商品没有达到起批量的要求");
                            if(remErrorMsg == ""){
                                remErrorMsg = "商品没有达到起批量的要求";
                            }
                            isSubmit =  false;
                        }
                        //console.log(nayangSign);
                        //没拿过样的才能拿样，拿过了的不能再拿
                        if(nayangSign > 0 && count != 1){
                            $(minnumber).css({"color":"#e00"});
                            $(amount).css({"background":"#FFFFCC"});
                            //remind("error","拿样产品只能选一件");
                            if(remErrorMsg == ""){
                                remErrorMsg = "拿样产品有且只有一件";
                            }
                            isSubmit =  false;
                        }
                    });

                    if(remErrorMsg != ""){
                        remind("error", remErrorMsg);
                    }
                    //将勾选商品存放并提交;
                    var shopperIds=[];
                    $(checkeds).each(function(){
                        shopperIds.push($(this).data("shopper-id"));
                    });

                    if(isSubmit && shopperIds.length > 0){
                        $('#submit-form-balance').append($('<input type="hidden" name="shopperIds">').val(shopperIds.join(","))).submit();
                    }
                });
            });
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
                    $.post("/dealer/dealerShoper/removeCart", { dealerShopersId: null }, function (data) {
                        //console.log(data);

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
                    $.post("/dealer/dealerShoper/removeCart", { "dealerShopersId": ids.join(",") }, function (data) {
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
                    $.post("/dealer/dealerShoper/removeCart", { "dealerShopersId": shopperId }, function (data) {
                        //console.log(data);
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
                var ids = [];
                $(".product-disable").each(function(){
                    var _chks = $(this).find(".js-check").data("shopper-id");
                    ids.push(_chks);
                });
                if(ids.length <= 0){
                    remind("error", "暂无失效产品");
                    return false;
                }
                confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                    $.post("/dealer/dealerShoper/removeCart", {"dealerShopersId": ids.join(",")}, function (data) {
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
        //显示或隐藏更多
        toggleMore: function(){
            var tpl_1 = '选择更多<i class="css-arrow"></i>',
                tpl_2 = '隐藏更多<i class="css-arrow"></i>';
            $(document).on("click", ".simple-function", function(){
                var $this = $(this);
                var _toggle = $this.data("toggle"); //0：隐藏着   1：显示着
                var _parent = $this.parent();
                //console.log("_toggle: " + _toggle + ",_isAjax: " + $this.data("isajax"));
                if(_toggle == 0){
                    var _isAjax = $this.data("isajax");
                    //显示的时候需要用 ajax 去获取 sku 信息, 可以在当前节点放一个 data-isajax="0", 为1 的时候，直接显示就好了，为 0 的时候才去 做请求。
                    if(_isAjax == 0) {
                        var _shopId = $this.data("shopid");
                        var _productId = $this.data("productid");
                        var _productType = $this.data("producttype");
                        var _discount = $this.data("discount");
                        var _fdbl = parseFloat($this.data("fdbl"));
                        $.ajax({
                            url: "/dealer/dealerShoper/showMore",
                            type: "post",
                            data: {"shoperId": _shopId, "productId": _productId, "productType": _productType}, //产品ID
                            dataType: "json",
                            success: function (data) {
                                //console.log(data);
                                if(data.rows.length <= 0){
                                    //_parent.find(".simple-tips").show();
                                }else{
                                    //写入一些需要的数据
                                    for(var i = 0; i < data.rows.length; i++){
                                        data.rows[i].discount = _discount;
                                        data.rows[i].fdbl     = _fdbl;
                                        //渲染过的数据写缓存
                                        setObjValues({
                                            shopid: data.rows[i].shoperId,
                                            shopsid: "",
                                            skuid: data.rows[i].productSkuId,
                                            num: "0",
                                            price: data.rows[i].productSkuPrice,
                                            type: data.rows[i].productType,
                                            zk: _discount,
                                            fdbl: (_fdbl/100).toFixed(2)
                                        });
                                    }
                                }

                                _parent.find(".simple-tips").show();
                                $this.data({
                                    toggle: "1",
                                    isajax: "1"
                                }).html(tpl_2);
                                var _html = Template.render("list_tempalte", data);
                                _parent.find("tbody").append(_html);

                                var _rowspan = _parent.find("tbody tr").length;
                                //_parent.find("tbody tr").eq(0).find(".p-xiaoji").attr("rowspan", _rowspan);
                            }
                        });
                    }else if(_isAjax == 1){
                        $this.data("toggle", "1").html(tpl_2);
                        _parent.find(".simple-tips").show();
                        _parent.find(".num-amount").parents("tr").show();
                    }
                }else if(_toggle == 1){
                    $this.data("toggle", "0").html(tpl_1);
                    _parent.find(".simple-tips").hide();
                    _parent.find("tr").each(function(i){
                        if($(this).find(".num-amount").val() == 0){
                            $(this).find(".num-amount").parents("tr").hide();
                        }
                    });
                }
            });
        },
        //处理产品价格同步的方法
        handelProductPrice: function(callback){
            var _self = this, useData = [];
            //找到选中的sku
            if($(".js-check:checked").length <= 0){
                remind("error", '至少勾选一个产品');
                return false;
            }

            $(".js-check:checked").each(function(i,o){
                var _shopid = $(this).data("shopper-id");
                var _parents = $(this).parents(".m-con");
                //console.log(_shopid);
                var _arr = cacheObj[_shopid];
                //console.log(_arr);
                if(_arr) {
                    for (var j = 0; j < _arr.length; j++) {
                        if (_arr[j].num != 0) {
                            var _obj = {
                                skuId: _arr[j].skuid,
                                productType: _arr[j].type,
                                productSkuPrice: _arr[j].price,
                                quantity: _arr[j].num,
                                pointPercent: _arr[j].fdbl
                            };
                            useData.push(_obj);
                        }
                    }
                }
            });
            //console.log(useData);
            //console.log(cacheObj);

            if(useData.length == 0){
                remind("error", '请选择购买数量');
                return false;
            }

            dialogLoading(function(__d){
                $.ajax({
                    url: "/dealer/dealerShoper/synShopers?csrfToken=" + $("input[name=csrfToken]").val(),
                    method: "POST",
                    data: JSON.stringify(useData),
                    contentType: 'application/json',
                    dataType: "json",
                    success: function(data){
                        //console.log(data);
                        if(data.code == 126000){
                            //allChangeLength: 产品库存或价格 有 修改过的条数，   _changeValidFlag: 产品失效  _changeTypeFlag: 类型有修改
                            var _obj = data.object, allChangeLength = 0, _changeValidFlag = false, _changeTypeFlag = false;

                            for(var key in _obj){
                                var _changeArr = _obj[key].changedSkuList;
                                var _len = _changeArr.length;
                                if(_len > 0){
                                    //产品有修改过
                                    allChangeLength += _len;
                                    for(var i = 0; i < _len; i++){
                                        $("#che_price_" + _changeArr[i].skuId).css("color", "red").html(_changeArr[i].productSkuPrice);
                                        $("#che_stock_" + _changeArr[i].skuId).css("color", "red").html(_changeArr[i].quantity);
                                        //当所选择的数量大于库存的时候，强制把所选数量设为库存值
                                        if(parseFloat($("#che_amount_" + _changeArr[i].skuId).val()) > _changeArr[i].quantity){
                                            $("#che_amount_" + _changeArr[i].skuId).val((parseFloat(_changeArr[i].quantity)).toFixed(2));
                                        }
                                    }
                                }

                                if(_obj[key].isValid == false){
                                    _changeValidFlag = true;
                                }

                                if(_obj[key].typeChanged == true){
                                    _changeTypeFlag = true;
                                }
                            }

                            if(allChangeLength > 0){
                                //有修改
                                confirmDialog("请注意，您所选择的产品其中 " + allChangeLength + " 款库存或价格有修改，是否继续提交？", function(){
                                    callback && callback();
                                });
                            }else{
                                //无修改
                                callback && callback();
                            }

                            if(_changeValidFlag){
                                //有失效产品
                                confirmDialog("请注意，您所选择的产品中存在有已失效的，是否刷新页面？", function(){
                                    window.location.reload();
                                });
                            }

                            if(_changeTypeFlag){
                                //有类型修改的产品
                                confirmDialog("请注意，您所选择的产品中有类型修改的，是否刷新页面？", function(){
                                    window.location.reload();
                                });
                            }

                        }else{
                            remind("error", data.message);
                        }
                        __d.hide();
                    },
                    error: function(){
                        remind("error", "请求出错");
                    }
                })
            }, "正在同步产品信息，请稍后...")
        },
        editAnddel: function(){
            var that = this;
            $(".js-del-single").click(function(){
                var $p_tr = $(this).parents("tr");

                if(!$p_tr.hasClass("already-delete")){
                    var $p_amount = $p_tr.find(".num-amount");
                    var shopid = $p_amount.data("shopid"),
                        skuid = $p_amount.data("skuid");
                    //num = $p_amount.val();

                    //$p_tr.removeClass("js-notnull").hide();
                    //$p_item.find(".simple-function").show();
                    $p_tr.addClass("already-delete js-ischange");
                    $p_tr.find(".num-amount").val("0");

                    setObjSingleValues({shopid: shopid, skuid: skuid, num: 0}); //写当前值的缓存数组
                    initSinglePrice(shopid);   //小结
                    that._updateSingle();      //更新数据
                }
            });

            $(".edit-btn").click(function(){

                var $parents = $(this).parents(".attr-item");

                if($(this).next().is(":visible")){
                    $(this).removeClass("active").next().hide();
                    $parents.find(".js-count-1").show();
                    $parents.find(".js-count-2").hide();
                    $parents.find("tr.already-delete").removeClass("already-delete");
                }else{
                    if($parents.find("tr:visible").length > 0){
                        $(this).addClass("active").next().css("height", $parents.height() + 20).show();
                        $parents.find(".js-count-1").hide();
                        $parents.find(".js-count-2").show();
                    }
                }
            });
        },
        fdTips: function(){
            var fdTip = new Tip({
                trigger: '#tips1',
                content: '<div style="padding:10px">返点产品单价 = 返点价 * （1 - 返点比例）</div>',
                arrowPosition: 1,
                theme: 'blue',
                effect: 'fade'
            });
        }
    };

    //非相关处理;
    var otherProduct = {
        init: function () {
            //this.initTabs();
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
                //$(this).removeClass("selected");
                //myOrder._settlement();
                return false;
            }else{
                var $this = $(this);
                var type = $(this).data("type");
                var id = $(this).data("id");
                var o_parents = $(this).parents(".m-con");
                var _attrItem = o_parents.find(".attr-item");

                //切换类型的时候，缓存里面对应的类型值也需要修改
                setObjSingleValues({shopid: id, type: type});

                $.ajax({
                    url: '/dealer/dealerShoper/updateTradeModel',
                    method:'post',
                    data: {productType: type, shoperId: id},
                    dataType: 'json',
                    success: function(data){
                        //console.log(data);
                        if(data && data.object && data.object.dealerShopersList.length > 0){
                            for(var i = 0, _len = data.object.dealerShopersList.length; i < _len; i++){
                                //$("#price_" + data.object.dealerShopersList[i].refrenceId + "_" + data.object.dealerShopersList[i].shoperId).text(data.object.dealerShopersList[i].productSkuPrice.toFixed(2));
                                $("#che_price_" + data.object.dealerShopersList[i].productSkuId).text(data.object.dealerShopersList[i].productSkuPrice.toFixed(2));
                                setObjSingleValues({shopid: data.object.dealerShopersList[i].shoperId, skuid: data.object.dealerShopersList[i].productSkuId, price: data.object.dealerShopersList[i].productSkuPrice});
                                initSinglePrice(data.object.dealerShopersList[i].shoperId);   //更新当前的价格计算
                                myOrder._settlement();  //大结算请求以后再去计算
                                $this.parent().find("a").removeClass("selected");
                                $this.addClass("selected");
                            }

                            //拿样
                            if(type == 2){
                                if(o_parents.find(".count span").length == 2){
                                    o_parents.find(".count span").eq(1).remove();
                                }

                                //非失效订单，拿样做特殊处理。
                                if(!o_parents.hasClass("product-disable")){
                                    //bug #6500 购物车中，将现款类型改成拿样类型，SKU数量和条数需要默认只保留一条
                                    _attrItem.find("tr .num-amount").val(0);
                                    _attrItem.find("tr").addClass("js-ischange");
                                    _attrItem.find("tr").eq(0).find(".num-amount").val(1);
                                    myOrder._updateSingle();
                                    _attrItem.find("tr .num-amount").each(function(){
                                        var shopid = $(this).data("shopid");
                                        var skuid = $(this).data("skuid");
                                        var _val = $(this).val();
                                        setObjSingleValues({shopid: shopid, skuid: skuid, num: _val});
                                    });
                                    initSinglePrice(_attrItem.find("tr .num-amount").data("shopid"));
                                    myOrder._settlement();
                                }
                            }

                            //授信、现款
                            if(type == 1 || type == 0){
                                var _z = parseFloat($this.data("z"));
                                //折扣不为空，有合作关系才去进行折扣的计算
                                if($this.data("z") != undefined){
                                    var _price = parseFloat(o_parents.find(".js-price").text());
                                    var _count = (_price * (1 - _z)).toFixed(2);
                                    //o_parents.find(".js-price").text();
                                    if(o_parents.find(".count span").length == 1){
                                        var tpl = '<span>折扣优惠:<em class="js-zkj" id="zkj_' + id + '">' + _count + '</em></span>';
                                        o_parents.find(".count").append(tpl);
                                    }else{
                                        o_parents.find(".js-zkj").text(_count);
                                    }
                                }
                            }

                        }
                    }
                });

                return false;
            }
        });
    });
    /*新增的js 结束*/

    otherProduct.init();
    myOrder.init();
    //setInterval(myOrder.handelProductPrice, 1000 * 60 * 20);

    return {
        myOrder: myOrder
    }
});