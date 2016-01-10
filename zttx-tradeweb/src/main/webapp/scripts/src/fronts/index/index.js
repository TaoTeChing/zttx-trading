/**
 * 首页, 产品列表，品牌列表 js
 * create by : 冯唐路 2015-08-19
 * */
define(function(require, exports, module){

    var $         = require("$");
    var Carousel  = require("carousel");
    var Popup     = require("popup");
    var LazyLoad  = require("gallery/lazyLoad/1.0.0/lazyLoad");
    var Template  = require("template");
    var Dialog    = require("dialog");
    var SetNumber = require("setNumber");

    Template.helper("$formatPrice", function(price){
        return price.toFixed(2);
    });

    var _common_fn = {
        ajaxMethod: function(){
            window.addCart = function(productId,productType){
                $.ajax({
                    url: "/dealer/dealerShoper/productAddCar?csrfToken=" + $("input[name=csrfToken]").val() ,
                    method: "POST",
                    data: {productId: productId, productType: productType, skuIds: '', amounts:''},
                    async: false,
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            remind("success", "此产品已成功");
                        }else if(data.code == 126013){
                            var parames = "?redirect=" + encodeURIComponent(location.href);
                            jumpToLogin(parames);
                        }else{
                            remind("error", data.message);
                        }
                    }
                });
            };
            window.addFavorite = function(productId){
                $.ajax({
                    url: "/dealer/dealerFavorite/addFavorite?csrfToken=" + $("input[name=csrfToken]").val(),
                    method: "POST",
                    async: false,
                    data: {productId: productId},
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            remind("success","已加入收藏夹")
                        }else if(data.code == 126013){
                            var parames = "?redirect=" + encodeURIComponent(location.href);
                            jumpToLogin(parames);
                        }else{
                            remind("error", data.message);
                        }
                    }
                });
            };
            window.applyJoin = function(productId, brandsId){
                $.ajax({
                    url: "/dealer/joinManage/apply/join?csrfToken=" + $("input[name=csrfToken]").val(),
                    method: "POST",
                    async: false,
                    data: {productId: productId, inviteText: "申请加盟", brandsId: brandsId},
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            remind("success","已提交加盟申请");
                        }else if(data.code == 126013){
                            var parames = "?redirect=" + encodeURIComponent(location.href);
                            jumpToLogin(parames);
                        }else{
                            remind("error",data.message);
                        }
                    }
                });
            };
            //品牌列表页 添加收藏
            window.addCollection = function(brandsId){
                $.ajax({
                    url: "/market/brand/attentionNum/add",
                    method: "GET",
                    async: false,
                    data: {brandesId: brandsId},
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            remind("success","收藏成功");
                            $("#collect_" + brandsId).html("已收藏");
                        }else if(data.code == 126013){
                            var parames = "?redirect=" + encodeURIComponent(location.href);
                            jumpToLogin(parames);
                        }else{
                            remind("error",data.message);
                        }
                    }
                });
            }
        },
        lazyLoad: function(){
            //图片延迟加载，暂未应用到结构
            //console.dir(LazyLoad);
            new LazyLoad();
        },
        searchLogic: function(){
            //搜索框的逻辑
            $("#searchBtn").click(function(){
                if($("#searchForm").length > 0){
                    var _q = $("#searchTxt").val();
                    $("#searchFormQ").val(_q);
                    $("#searchForm").submit();
                }else{
                    $("#headerSearch").submit();
                }
                return false;
            });
        }
    };

    var index_index = {
        init: function(){
            _common_fn.searchLogic();
            this.typeMove();
            this.banner();
            //this.cardTab();
            this.goodBrand();
            this.hall();
            this.news();
            _common_fn.ajaxMethod();
            //_common_fn.lazyLoad();
        },
        typeMove: function(){
            //分类移动
            var itemTimer = null;
            $(".type-item").mouseenter(function(){
                var _self = $(this);
                itemTimer = setTimeout(function(){
                    $(".type-item .type-item-sub").removeClass("hover");
                    _self.find(".type-item-sub").addClass("hover");
                    _self.find(".type-item-sub").stop(false, false).fadeIn("normal",function(){
                        $(".type-item .type-item-sub").not(_self.find(".type-item-sub")).stop(false, false).fadeOut();
                    });
                }, 328);
            });
            $(".type-item").mouseleave(function(){
                clearTimeout(itemTimer);
            });
            $(".type-box").mouseleave(function(){
                setTimeout(function(){
                    $(".type-item .type-item-sub").stop().fadeOut();
                }, 328);
            });
        },
        banner: function(){
            //banner图轮播
            if($("#focus").length > 0){
                new Carousel({
                    element: '#focus',
                    //panels: '#focus .focus-list',
                    panels: '#focus .ts-banner-item',
                    effect: 'fade',
                    prevBtn: $('#focus').find('.prev'),
                    nextBtn: $('#focus').find('.next'),
                    autoplay: true
                });
            }

            //banner图下方轮播
            if($("#brand-list-con .brand-list-item").length > 0){
                new Carousel({
                    element: '#brand-list-con',
                    panels: '#brand-list-con .brand-list-item',
                    effect: 'scrollx',
                    prevBtn: '.brand-list .prev',
                    nextBtn: '.brand-list .next',
                    step: 5,
                    autoplay: true,
                    circular: false,
                    interval: 5400
                });
            }

            if($("#card-hd").length > 0) {
                /*new Carousel({
                    element: '#card-hd',
                    panels: '#card-hd li.item',
                    effect: 'scrollx',
                    autoplay: true,
                    interval: 5300
                });*/
            }
        },
        cardTab: function(){
            //公告切换
            $("#card-tab li").hover(function(){
                var index = $(this).index();
                $("#card-tab li").removeClass("current").eq(index).addClass("current");
                $("#card-tab-content .card-news").hide().eq(index).show();
            });
        },
        goodBrand: function(){
            //优选品牌    暂时的方法，并不好，只是为了布局
        },
        hall: function(){
            //hall 轮播图
            if($(".hall-focus").length > 0) {
                $(".hall-focus").each(function () {
                    if($(this).find(".hall-focus-list").length > 0) {
                        new Carousel({
                            element: $(this).find(".hall-focus-content"),
                            panels: $(this).find(".hall-focus-list"),
                            effect: 'fade',
                            prevBtn: $(this).find(".prev"),
                            nextBtn: $(this).find(".next"),
                            //step: 3,
                            autoplay: true
                        });
                    }
                });
                $(".hall-focus,.focus").hover(function () {
                    $(this).find(".prev").stop().animate({
                        left: 0
                    }, 128);
                    $(this).find(".next").stop().animate({
                        right: 0
                    }, 128);
                }, function () {
                    $(this).find(".prev").stop().animate({
                        left: -30
                    }, 128);
                    $(this).find(".next").stop().animate({
                        right: -30
                    }, 128);
                });
            }
        },
        news: function(){
            //news 轮播
            if($("#news-pic").length > 0) {
                new Carousel({
                    element: '#news-pic',
                    panels: '#news-pic .news-pic-item',
                    effect: 'fade',
                    viewSize: [365, 250],
                    //step: 1,
                    autoplay: true
                });
            }
        }
    };

    var index_product_list = {
        init: function(){
            this.selectOperation();
            this.removeResults();
            this.listImgHover();
            this.controlBrandMore();
            this.delaySetPrice();
            //_common_fn.ajaxMethod();
            _common_fn.searchLogic();
            _common_fn.lazyLoad();
            this.joinShopSku();
        },
        selectOperation: function(){
            var select_duo_flag = false;  //多选开关
            var select_more_flag = true; //更多开关
            var tpl_up = '收起<i class="icon-close"></i>';
            var tpl_down = '更多<i class="icon-close-down"></i>';
            var tpl_show = '显示筛选<i class="icon-close-down"></i>';
            var tpl_hide = '收起筛选<i class="icon-close"></i>';

            //收起筛选
            $(".js-slide-filter").click(function(){
                if($(this).find(".icon-close").length > 0){
                    $(".m-brand").stop().fadeOut("fast");
                    $(".m-cates").stop().fadeOut("fast");
                    $(".js-slide-filter").html(tpl_show);
                }else{
                    $(".m-brand").stop().fadeIn("fast");
                    $(".m-cates").stop().fadeIn("fast");
                    $(".js-slide-filter").html(tpl_hide);
                }
            });

            //多选
            $("#select_duo").click(function(){
                $(this).hide();
                $("#brand_btns").show();
                select_duo_flag = true;
            });

            //更多
            $("#select_more").click(function(){
                /*if(select_more_flag){
                 $("#brand_search").show();
                 $(this).html(tpl_up);
                 select_more_flag = false;
                 }else{
                 $("#brand_search").hide();
                 $(this).html(tpl_down);
                 select_more_flag = true;
                 }*/

                /**/
                if(select_more_flag){
                    $("#selected_brand").css("height", "auto");
                    $(this).html(tpl_up);
                    select_more_flag = false;
                }else{
                    $("#selected_brand").css("height", 52);
                    $(this).html(tpl_down);
                    select_more_flag = true;
                }

            });

            //取消
            $(".brand_btns_cancel").click(function(){
                $("#select_duo").show();
                $("#brand_search").hide();
                $("#brand_btns").hide();
                $("#selected_brand").css("height", 52);
                $("#select_more").html(tpl_down);
                $("#brand_list li .hover").remove();
                $("#brand_list li .selected").remove();
                $(".brand_btns_confirm").addClass("disabled");
                select_duo_flag = false;
                select_more_flag = true;
            });
            //确定
            $(".brand_btns_confirm").click(function(){
                if(!$(this).hasClass("disabled")){
                    //多选品牌提交
                    var _str = "";
                    $("#selected_brand li.current").each(function(){
                        var _val = $(this).find("a").data("cache");
                        if(_str === ""){
                            _str += _val;
                        }else{
                            _str += "," + _val;
                        }
                    });
                    $("input[name=brandsId]").val(_str);
                    //当前页码重置为1
                    $("#currentPage").val("1");
                    $("#searchForm").submit();
                }
            });

            $("#brand_list li").hover(function(){
                var $hover = $(this).find(".hover");
                var len = $hover.length;
                if(len <= 0){
                    //var title = trimLongString($(this).find('a').attr('title'), 5) || "null"; //title 必须加
                    var title = trimLongString($(this).find('a').attr('title'), 100) || "null"; //title 必须加
                    var $tpl = $('<span class="hover" title="' + title + '">' + title + '</span>');
                    $(this).append($tpl);
                }else{
                    $hover.show();
                }
            },function(){
                //$(this).find(".hover").stop().fadeOut();
                $(this).find(".hover").hide();
            });

            $(".js-cates-more").click(function(){
                var $parents = $(this).parents(".m-mod");
                if($parents.hasClass("mod-height")){
                    $parents.removeClass("mod-height");
                    $(this).html(tpl_up);
                }else{
                    $parents.addClass("mod-height");
                    $(this).html(tpl_down);
                }
            });

            //品牌点击 写到表单的隐藏域
            $("#brand_list li").click(function(){
                if(select_duo_flag){
                    var $hover = $(this).find(".hover");
                    var $selected = $(this).find(".selected");
                    var $tpl = $('<span class="selected"></span>');
                    if($selected.length <= 0){
                        $(this).addClass("current");
                        $(this).append($tpl);
                    }else{
                        $(this).removeClass("current");
                        $selected.remove();
                    }
                    if($("#brand_list .selected").length > 0){
                        $(".brand_btns_confirm").removeClass("disabled");
                    }else{
                        $(".brand_btns_confirm").addClass("disabled");
                    }
                }else{
                    //单选品牌提交
                    var _val = $(this).find("a").data("cache");
                    $("input[name=brandsId]").val(_val);
                    //当前页码重置为1
                    $("#currentPage").val("1");
                    $("#searchForm").submit();
                }
            });

            //点击相应内容 写到表单的隐藏域
            var term_list = "cate,dealId filter,skuColor,skuSize,otherAttr".split(" ");
            var _len = term_list.length;
            for(var i = 0; i< _len ; i++){
                (function(i){
                    var item = term_list[i].split(',');
                    if(item[0] === "filter"){
                        $("#selected_" + item[0] + " li a").click(function(){
                            var _val = $(this).data("cache");
                            var index = $(this).parents("li").index() + 1;
                            if(_val){
                                $("input[name=" + item[index] + "]").val(_val);
                                $("#searchForm").submit();
                            }
                            return false;
                        });
                    }else{
                        $("#selected_" + item[0] + " .list a").click(function(){
                            var _val = $(this).data("cache");
                            $("input[name=" + item[1] + "]").val(_val);
                            //当前页码重置为1
                            $("#currentPage").val("1");
                            $("#searchForm").submit();
                            return false;
                        });
                    }

                })(i)
            }

            if($("#sort_price").length > 0){
                new Popup({
                    trigger: '#sort_price',
                    element: "#sort_price_pop",
                    align: {
                        baseXY: [0, 0]
                    }
                });
            }
            //排序
            $(".m-filter li.m-f-1").not("#sort_price").click(function(){
                var _val = $(this).find("a").data("type");
                $("input[name=sorts]").val(_val);
                $("input[name=orderBy]").val("desc");
                $("#searchForm").submit();
            });
            /*$("#sort_price_pop a").click(function(){
             var _type = "price";
             var _val = $(this).data("type");
             $("input[name=sorts]").val(_type);
             $("input[name=orderBy]").val(_val);

             $("#searchForm").submit();
             });*/

            //推荐，拿样，授权，授信
            $(".m-recom label input").click(function(){
                var type = $(this).data("type");
                var _val = this.checked;
                switch(type){
                    case 0:
                        $("input[name=recom]").val(_val);
                        break;
                    case 1:
                        $("input[name=samples]").val(_val);
                        break;
                    case 2:
                        $("input[name=authorize]").val(_val);
                        break;
                    case 3:
                        $("input[name=credit]").val(_val);
                        break;
                    case 4:
                        $("input[name=point]").val(_val);
                        break;
                }
                $("#searchForm").submit();
            });

            //品牌产品切换
            $("#change_st a").click(function(){
                var _val = $(this).data("type");
                $("input[name=st]").val(_val);
                //当前页码重置为1
                $("#currentPage").val("1");
                $("#searchForm").submit();
            });

        },
        removeResults: function(){
            var _arr = ["remove_q","remove_brandsId","remove_dealId"];//需要擦除的数组
            for(var i = 0;i < _arr.length; i++){
                (function(i){
                    var _name = _arr[i].split("_")[1];
                    $("." + _arr[i]).click(function(){
                        $("input[name=" + _name + "]").val("");
                        $("#searchForm").submit();
                    });
                })(i)
            }
        },
        listImgHover: function(){
            $(".m-list li").hover(function(){
                if($.trim($(this).find(".operate").text()) != ""){
                    $(this).addClass("hover");
                }
            }, function(){
                $(this).removeClass("hover");
            });
        },
        controlBrandMore: function(){
            //更多判断
            var $a = $('#selected_brand li');
            if($a.length < 10){
                $("#more1").hide();
            }
            var $b = $("#selected_cate .list").height();
            if($b <= 40){
                $("#more2").hide();
            }
        },
        delaySetPrice: function(){
            //延迟设置价格等
            var sumLen = $("#product_list li").length;
            var _arr = [];
            var _size = 10;
            var _count = (sumLen % _size) > 0 ? ((sumLen / _size) + 1) : sumLen / _size;

            $("#product_list li").each(function(i){
                var id = $(this).data("refrenceid");
                _arr.push(id);
            });

            for(var j = 0; j < _count; j++){
                (function(){
                    $.ajax({
                        url: "/getAuthPrice",
                        type: "post",
                        data:{productIds:_arr.splice(0,10).toString()},
                        dataType: "json",
                        success: function(json){
                            if(json.rows && json.rows.length > 0){
                                for(var i = 0; i < json.rows.length; i++){
                                    var _id = json.rows[i].productId;
                                    var joinHtml  = Template.render("join_tpl", json.rows[i]);
                                    var priceHtml = Template.render("price_tpl", json.rows[i]);
                                    $("#operate_" + _id).append(joinHtml);
                                    $("#price_" + _id).html(priceHtml);
                                }
                            }
                        }
                    });
                })(j);
            }

            _common_fn.ajaxMethod();
        },
        joinShopSku: function(){
            if($("#joinOrderTpl").length <= 0){
                return false;
            }

            var joinOrderDialog = new Dialog({
                width: 500,
                content: $("#joinOrderTpl").html()
            });

            /*加进货单方法作修改*/
            window.joinShop = function(productId, type){
                //$("#productId").val(productId);
                //console.log(type);
                /*发请求取得 sku 列表*/
                $.ajax({
                    url:"/dealer/dealerShoper/listSkuIds?csrfToken=" + $("input[name=csrfToken]").val(),
                    method:"POST",
                    async: false,
                    data: {productId: productId},
                    dataType: "json",
                    success : function(data){
                        if(data.code == 126013){
                            var currentUrl = location.href;
                            location.href = '/common/login?loginType=1&redirect='
                            + encodeURIComponent(currentUrl);
                        }else if(data.code==126000){
                            var _html = Template.render("joinOrderTpl", data.object);
                            joinOrderDialog.set("content", _html).show();
                            joinOrderDialog.show();
                        }else{
                            remind("error",data.message);
                        }
                    }
                });
            };

            /*确定*/
            $(document).on("click", ".set_comfirm", function(){
                var params = $("#joinOrderForm").serialize();
                //console.log(params);
                $.ajax({
                    url:"/dealer/dealerShoper/addCart?" + params + "&csrfToken=" + $("input[name=csrfToken]").val(),
                    method:"POST",
                    dataType:"JSON",
                    success : function(data){
                        if(data.code == 126000){
                            remind("success", "成功加入进货单");
                            joinOrderDialog.hide();
                        }else{
                            remind("error", data.message);
                        }
                    }
                });
            });

            //计算现款价和授信价
            function countAllPrice(){
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

            new SetNumber({
                callback: countAllPrice
            });
        }
    };

    //module.exports = Index;
    return{
        index: index_index,
        index_product_list: index_product_list
    }

});