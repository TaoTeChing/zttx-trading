define(function(require, exports, module){

    var Dialog     = require("dialog");
    var Template   = require("template");
    var Pagination = require("pagination");
    var SetNumber  = require("setNumber");

    Template.helper('$formatPrice', function (price) {
        if(price == null){
            return 0.00;
        }else{
            return price.toFixed(2);
        }
    });

    Template.helper('$formatNumber', function (number) {
        if($.isNumeric(number)){
            return number<10000?number:(number/10000.0)+'万';
        }
        else if(number == undefined){
            return 0;
        }
        else{
            return number;
        }
    });

    Template.helper('$getImageDomainPathFn',function (url, width, height){
        return getImageDomainPath(url, width, height);
    });

    Template.helper('$handelBate',function(str){
        if(getCharLength(str) > 20){
            return trimLongString(str, 20);
        }else{
            return str;
        }
    });

    window.quickFormSubmit = function (productId,productType) {
        $('#addlist-form-quick').append($('<input type="hidden">').attr('name', 'productsId').val(productId));
        $('#addlist-form-quick').append($('<input type="hidden">').attr('name', 'productsType').val(productType)).submit();
    };

    var purchase = {
        init: function(options){
            this.beforeVersion(options);
            this.initQuickOrder();
            this.showOrderDialog();
        },
        beforeVersion: function(options){
            /**加入进货单 鼠标效果*/
            $(document).on("mouseenter mouseleave", ".product-item dl", function (ev) {
                if (ev.type == "mouseenter") {
                    $(this).find(".item-pic .btn-joinorder:not('.cantuse')").show();
                } else if (ev.type == "mouseleave") {
                    $(this).find(".item-pic .btn-joinorder:not('.cantuse')").hide();
                }
            });

            /**品牌下拉框 */
            $("#sel-brands").change(function (event) {
                var brands = $.trim($("#sel-brands").val());
                if (brands == '') {
                    $('#sel-category').empty().append('<option value="" selected="selected">全部类别</option>');
                    renderSelect('#sel-category');
                } else {
                    $.post("${ctx}/dealer/shopper/dealsolrConfig/dics", { brandsId: brands }, function (data) {
                        if (data.code == 126000) {
                            var options = template.render('category-template', data);
                            $('#category_div span').html('全部类别');
                            $('#sel-category').empty().append(options);
                        }
                    }, "json");
                };
            });

            /**Grid List视图效果切换*/
            var view_model = $(".order-bar ul li").click(function () {
                view_model.not($(this).addClass("on")).removeClass("on");
                if ($(this).data("order") == "grid") {
                    $(".panel-order").removeClass("panel-list").addClass("panel-grid");
                } else {
                    $(".panel-order").removeClass("panel-grid").addClass("panel-list");
                }
            });

            /**数据Ajax 载入*/
            window.g_pagination = new Pagination({
                url: options.url,
                elem: '#pagination',
                form: $("#filter-form"),
                method: options.method || "get",
                handleData: function (data) {
                    var html = template.render('products-template', data);
                    if (data.rows.length == 0) {
                        $('#btn-addlist').hide();
                        $('#product-item').empty().append('<div style="text-align: center" ><img src="' + window.IMAGE_DOMIAN + '/images/common/null.png" /></div>');
                    } else {
                        $('#product-item').empty().append(html);
                        $('#btn-addlist').show();
                        //让图片在一个容器里面垂直居中
                        $(".js_img_center,.js-img-center").each(function () {
                            $("<span></span>").appendTo($(this));
                        });
                    }

                    /*/分页后选中状态处理*/
                    //var pli = $('#product-item li');
                    /*$.each(selectedProductId,function(i,o){
                     $("a[data-product-id="+o+"]").addClass("on").trigger("mouseenter");
                     });*/
                }
            });
        },
        initQuickOrder: function(){
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
            });

            function loadData(key) {
                $.post("/dealer/dealerShoper/quickCart", { keyWord: key }, function (remote) {
                    if (remote.code == 126000) {
                        var data = remote.rows;
                        if (data.length > 0 && key.length > 0) {
                            var result = $(".search-result").html("<ul></ul>").show().hover(null, function () { $(this).hide() }); //<i class='close'>
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
        },
        showOrderDialog: function(){
            var that = this;

            var joinOrderDialog = new Dialog({
                width: 540,
                zIndex: 1000
            });

            /*加进货单方法作修改*/
            var joinShop = function(productId, type){
                /*发请求取得 sku 列表*/
                dialogLoading(function(__dd){
                    $.ajax({
                        url: "/dealer/dealerShoper/listSkuIds",
                        type: "POST",
                        data: {"productId":productId},
                        dataType: "json",
                        success: function(data){
                            //console.log(data);
                            var _html = Template.render("joinOrderTpl", data.object);
                            joinOrderDialog.set("content", _html).show();
                            __dd.hide();
                        }
                    });
                }, "正在请求数据请稍后...");
            };

            $(document).on("click", ".btn-joinorder", function(){
                var productId = $(this).data("product-id");
                var productType = $(this).data("product-type");
                joinShop(productId, productType);
            });

            /*确定*/
            $(document).on("click", ".set_comfirm", function(){
                var params = $("#joinOrderForm").serialize();
                $.ajax({
                    url: "/dealer/dealerShoper/addCart",
                    type: "POST",
                    data: params,
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            remind("success", "成功加入进货单");
                            joinOrderDialog.hide();
                        }else{
                            remind("error", data.message);
                        }
                    }
                });
            });

            new SetNumber({
                callback: that._countAllPrice
            });
        },
        _countAllPrice: function(){
            var _xk = 0, _sx = 0, _fd = 0;

            $("#orderTableBody tr").each(function(){
                var sinXk = parseFloat($(this).find(".js-xk-price").text());
                var sinSx = parseFloat($(this).find(".js-sx-price").text());
                var sinFd = parseFloat($(this).find(".js-fd-price").text());
                var _num = parseInt($(this).find(".num-amount").val()) || 0;

                _xk += (sinXk * _num);
                _sx += (sinSx * _num);
                _fd += (sinFd * _num);
            });

            $("#xk_price").text(_xk.toFixed(2));
            $("#sx_price").text(_sx.toFixed(2));
            $("#fd_price").text(_fd.toFixed(2));
        }
    };

    module.exports = purchase;
});