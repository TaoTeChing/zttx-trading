// <reference path="plugin/jquery-1.10.2.min.js" />
//前台页面公用脚本
$(function () {

    //判断浏览器
    if(!$.browser) {
        $.browser = {
            /**
             * 简单的浏览器特性判断(欢迎补充)
             */
            runBrowser: function () {
                var me = this;
                var html = document.getElementsByTagName("html")[0];
                var userAgent = navigator.userAgent.toLowerCase();
                //判断浏览器版本
                var browser = [
                    {core: "chrome", is: /chrome/.test(userAgent)},
                    {core: "mozilla", is: /firefox/.test(userAgent)},
                    {core: "webkit", is: /webkit/.test(userAgent)},
                    {core: "opera", is: /opera/.test(userAgent)},
                    {core: "msie", is: /msie/.test(userAgent)},
                    {core: "msie6", is: /msie 6\.0/i.test(userAgent)},
                    {core: "msie7", is: /msie 7\.0/i.test(userAgent)},
                    {core: "msie8", is: /msie 8\.0/i.test(userAgent)}//,
                    //{core:"msie9"   ,is:/msie 9\.0/i.test(userAgent)}
                ];

                for (var i in browser) {
                    //给浏览器增加特定样式
                    if (browser[i].is) {
                        html.className += browser[i].core + " ";
                    }
                    //给$增加脚本判断;
                    if(/msie 7\.0/i.test(userAgent)||/msie 6\.0/i.test(userAgent))
                    {
                        eval("me." + browser[i].core + " = function () { return "+browser[i].is+" }");
                    }
                    else{
                        me[browser[i].core] = function () {
                            return browser[i].is;
                        }
                    }
                }
                //监视浏览器分辨率
            }
        };
        $.browser.runBrowser();
    }

    /**
     * 针对IE下的placeholder
     * @param elem i,i为索引
     */
    function placeholderFix(elem,i){
        var supportPlaceholder = "placeholder" in document.createElement("input");
        if(supportPlaceholder){
            return;
        }
        var $elem = $(elem);
        if($elem.is(":hidden")){
            return;
        }

        var flag = $elem.attr("type") == "text" || $elem.prop("tagName").toLowerCase() == "textarea";
        var text = $elem.attr("placeholder");
        var defaultValue = $elem.val();
        var defaultClass = $elem.attr("class") || "noClass",selfClass = "placeHolderFix" + i;

        if(flag && text){

            var inputDoc = '<input class="' + defaultClass + ' ' + selfClass + '" type=\"text\" value="' + text + '" style="color:#999;" />';

            $(elem).hide();
            $(elem).after(inputDoc);

            $("."+selfClass).on("click",function(){
                $(this).prop("disabled",true).hide();
                $elem.show().focus();
            });

            if(defaultValue == ""){
                $("."+selfClass).show();
            }
            /*$elem.focus(function(){
                $("."+selfClass).hide();
                $(this).show().focus();
            });*/
            $elem.blur(function(){
                if($elem.val() === ""){
                    $("."+selfClass).prop("disabled",false).show();
                    $elem.hide();
                }
            });
        }
    }
    $("input[type='text']").each(function(i){
        placeholderFix($(this),i);
    });

    //新首页公用样式
    var newindex = {
        init:function(){
            this.pinTop();//顶部跟随
            this.topMenu();//顶部导航
            this.navHover();//导航移动
            //this.backToTop();  //返回顶部
            this.areaSearch(); //栏目及区域搜索;
            this.myOrderGlobal();//全站我的进货单
            //this.searchInput();//全站搜索框事件
            this.cart();
            this.autoComplate();
        },
        pinTop:function(){
            if(!"1"[0]) {//IE6~7
                return false;
            };

            var head = $(".sticky");
            var coki = getCookie("close_sticky");

            //var close_sticky = "0";

            var close_sticky = coki?coki:"0";
            /*console.log(close_sticky);
            if(close_sticky=="1") {
                //关掉
                $(".sticky-close").trigger("click").toggleClass("active",true);
            }
            else{
                //开启
                $(".sticky-close").trigger("click").toggleClass("active",false);
            }*/

            $(window).bind("scroll", function () {
                var t = this.scrollY || document.documentElement.scrollTop || document.body.scrollTop;
                $(head).toggleClass("fixed", t > 160);
            });

            $(document).on("click",".fixed .header:not(.active) .sticky-close",function(){
                $(".fixed .header").addClass("active");//.animate({"margin-top":-50});
                $(this).find("a").attr("title","打开搜索条");
                setCookie("close_sticky","1");
                //$(this);
            });

            $(document).on("click",".fixed .header.active .sticky-close",function(){
                $(".fixed .header").removeClass("active");//.animate({"margin-top":0});
                $(this).find("a").attr("title","收起搜索条");
                setCookie("close_sticky","0");
                //$(this);
            });

            function setCookie(name,value)
            {
                var Days = 30;
                var exp = new Date();
                exp.setTime(exp.getTime() + Days*24*60*60*1000);
                document.cookie = name + "="+ value + ";expires=" + exp.toGMTString();
            }

            //读取cookies
            function getCookie(name)
            {
                var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

                if(arr=document.cookie.match(reg))
                    return arr[2];
                else
                    return null;
            }

        },
        topMenu:function(){
            //顶部网站导航
            $(".site-nav-bd .top-menu").hover(function(){
                var panel = $(this).find(".menu-bd");
                if(panel.length==1){
                    $(this).addClass("hover");
                    panel.show();
                }
            },function(){
                var panel = $(this).find(".menu-bd");
                if(panel.length==1){
                    $(this).removeClass("hover");
                    panel.hide();
                }
            });
        },
        navHover:function(){
            /*var fltBox = $(".nav-left .float-box.open");
            if(fltBox.length>0){
                $(".nav-left").mouseenter(function(){
                    $(fltBox).slideDown(200);
                }).mouseleave(function(){
                    $(fltBox).slideUp(200);
                });
            }*/
        },
        backToTop: function(){
            var ph = $(window).innerHeight();
            $(window).scroll(function () {
                $(".ui-scrolltop").css({ display: ph < $(window).scrollTop() ? "block" : "none" });
            })
            $(".ui-scrolltop").click(function () {
                $('html,body').animate({ scrollTop: 0 }, 500);
            });
            if ($.browser.msie6) {
                $(window).scroll(function () {
                    $(".ui-scrolltop").css({ top: $(window).scrollTop() + $(window).innerHeight() - 150 });
                });
            }
        },
        areaSearch:function(){
            seajs.use("popup",function(Popup){
                var navlist = new Popup({
                    trigger:".search-area-nav",
                    element :".area-nav-list",
                    //triggerType:"click",
                    align: {
                        //selfXY: [10,0]//,
                        baseXY: [0,31]
                    }
                });
                var provincelist = new Popup({
                    trigger:".search-area-province",
                    element :".area-province-list",
                    //triggerType:"click",
                    align: {
                        //selfXY: [10,0]//,
                        baseXY: [0,31]
                    }
                });
                var citylist = new Popup({
                    trigger:".search-area-city",
                    element :".area-city-list",
                    //triggerType:"click",
                    align: {
                        //selfXY: [10,0]//,
                        baseXY: [0,31]
                    }
                });

                $(document).on("click",".area-nav-list dd",function(){
                    $(".search-area-nav span").text(this.innerText);
                    navlist.hide();
                })

                $(document).on("click",".area-province-list dd",function(){
                    $(".search-area-province span").text(this.innerText);
                    provincelist.hide();
                })

                $(document).on("click",".area-city-list dd",function(){
                    $(".search-area-city span").text(this.innerText);
                    citylist.hide();
                })

            });
        },
        loadHotList: function(){
           /** $.ajax({
                type : "GET",
                url : "/search/loadHostList",
                dataType: "html",
                success : function(data) {
                    $("#hostList").html(data);
                }
            });*/
        },
        myOrderGlobal:function(){
            if($(".my-order-global").length > 0){
                //勾选获得全选
                var checkAllFn = function(){
                    if($(".js-myOrder-chb:checked").length + $(".js-myOrder-chg:checked").length == len){
                        $(".js-myOrder-cha").prop("checked",true);
                    }else{
                        $(".js-myOrder-cha").prop("checked",false);
                    }
                };
                //已选择的商品数量
                var alreadyCheckNum = function(){
                    $(".js-already-chk").html($(".js-myOrder-chg:checked").length);
                };
                //全选
                $(".js-myOrder-cha").click(function(){
                    $(".js-myOrder-chb").prop("checked",this.checked);
                    $(".js-myOrder-chg").prop("checked",this.checked);
                    alreadyCheckNum();
                });

                $(".js-myOrder-chb").click(function(){
                    var parent = $(this).parents(".my-order-brand");
                    parent.find(".js-myOrder-chg").prop("checked",this.checked);
                    checkAllFn();
                    alreadyCheckNum();
                });

                var len = $(".js-myOrder-chb").length+$(".js-myOrder-chg").length;

                $(".js-myOrder-chg").click(function(){
                    var brandLen = $(this).parents(".my-order-brand").find(".js-myOrder-chg").length;
                    if($(this).parents(".my-order-brand").find(".js-myOrder-chg:checked").length == brandLen){
                        $(this).parents(".my-order-brand").find(".js-myOrder-chb").prop("checked",true);
                    }else{
                        $(this).parents(".my-order-brand").find(".js-myOrder-chb").prop("checked",false);
                    }
                    checkAllFn();
                    alreadyCheckNum();
                });

                $("#settle-accounts").click(function(){
                    var arr = [];
                    $(".js-myOrder-chg:checked").each(function(){
                        var id = $(this).data("id");
                        arr.push(id);
                    });
                    alert(arr.toString());
                });
            }
        },
        searchInput:function(){
            $("#searchTxt").on("focus blur",function(ev){
                var __val = $(this).val();
                var __defalutValue = "搜索您需要的商品";
                if(ev.type == "focus"){
                    if(__val == __defalutValue){
                        $(this).val("").next().hide();
                    }
                }
                if(ev.type == "blur"){
                    if(__val == ""){
                        $(this).val(__defalutValue).next().show();
                    }
                }
            });
        },
        cart: function(){
            //${ctx}/common/dealerShoper/homeCart
            //${ctx}/common/dealerShoper/deleteHomeCart?shoperId=xxx

            seajs.use(['underscore'], function(_){

                var _noProduct = '<li style="line-height: 30px;text-align: center;background-color: #f2f2f2;">暂无产品</li>';

                function reRenderCart(data){
                    //console.log(data);
                    if(data.code == "126000"){
                        if(data.rows && data.rows.length > 0){
                            var _html = [];
                            for(var i = 0; i < data.rows.length; i++){
                                _html.push('<li class="clearfix">');
                                _html.push('<div class="d2-img">');
                                _html.push('<img src="'+window.IMAGE_DOMIAN + data.rows[i].productImage + '_50x50.jpg" alt="" width="50" height="50"/>');
                                _html.push('</div>');
                                _html.push('<div class="d2-name">');
                                _html.push('<a href="/market/product/' + data.rows[i].productId + '" title="' + data.rows[i].productTitle + '" target="_blank">' + data.rows[i].productTitle + '</a>');
                                _html.push('</div>');
                                _html.push('<div class="d2-detail">');
                                //_html.push('<span>￥12.00</span><br />');
                                _html.push('<a href="javascript:;" class="jsDeleteSku" data-id="' + data.rows[i].refrenceId + '">删除</a>');
                                _html.push('</div>');
                                _html.push('</li>');
                            }
                            //console.log(_html);
                            $("#cart #cartList").html(_html.join(""));
                            $("#cart .cartCount").html(data.object + data.rows.length);
                        }else if(data.rows && data.rows.length <= 0){
                            $("#cart #cartList").html(_noProduct);
                            $("#cart .cartCount").html("0");
                        }
                        //数量
                        //$("#cart .cartCount").html(data.object + 3);
                        $("#cart .cartSurplus").html(data.object);
                    }
                }

                function deleteSkuItem(){
                    var $this = $(this);
                    var _shoperId = $this.data("id");
                    $.ajax({
                        url: "/common/dealerShoper/deleteHomeCart",
                        method: "get",
                        data:{shoperId: _shoperId},
                        dataType:"json",
                        success: function(data){
                            initCartAjax();
                            if(data.code == "126000"){
                                //remind("success", "删除产品成功");
                            }else{
                                remind("error", "删除产品失败");
                            }
                        }
                    });
                }

                function initCartAjax(callback){
                    $.ajax({
                        url:"/common/dealerShoper/homeCart",
                        method: "get",
                        dataType:"json",
                        success:function(data){
                            reRenderCart(data);
                            callback && callback();
                        }
                    });
                }
                if($("#cart").length > 0){
                    initCartAjax();
                }

                var _query = _(initCartAjax).debounce(200);

                //删除购物车商品
                $(document).on("click", "#cart .jsDeleteSku", deleteSkuItem);

                //购物车
                $("#cart").hover(function(){
                    var $this = $(this);
                    _query(function(){
                        $this.addClass("hover");
                    });
                },function(){
                    $(this).removeClass("hover");
                });
            });
        },
        autoComplate: function(){
            seajs.use(['underscore'], function(_){
                var $searchTxt = $('#searchTxt');
                var $searchList = $('#searchList');
                var $mainId = $('#mainId4AutoComplete');
                var _i = -1; //键盘操作 序列数字
                var _query = _(function(event) {
                    if(event.which != 40 && event.which != 38){
                        var param_data = 'q=' + $searchTxt.val();
                        if($mainId != null && $mainId.val() != undefined && $mainId.val() != 'undefined')
                        {
                            param_data += '&mainId=' + $mainId.val();
                        }
                        _i = -1;
                        $.ajax({
                            url: '/search/autoCompletion',
                            data: param_data,
                            method: 'get',
                            dataType: 'json',
                            success: function(data){
                                if(data.rows && data.rows.length >= 0){
                                    var htm = '';
                                    for(var i = 0; i < data.rows.length; i++){
                                        if(data.rows[i].count > 0){
                                            htm += '<li class="search-item"><a href="javascript:;">' + data.rows[i].name + '</a></li>';
                                        }
                                    }
                                    if(htm != ''){
                                        $searchList.show().empty().html(htm);
                                    }
                                    else{
                                        $searchList.empty().hide();
                                    }
                                    /*if(data.rows.length > 0){
                                        $searchList.show().empty().html(htm);
                                    }
                                    if(data.rows.length == 0){
                                        $searchList.empty().hide();
                                    }*/
                                }else{
                                    $searchList.hide();
                                }
                            }
                        });
                    }
                }).debounce(200);
                var _hide = _(function(){
                    $searchList.hide();
                }).debounce(200);


                var _keyboardOprate = function(event){
                    var $searchListli = $("#searchList li");
                    var _len = $searchListli.length;

                    //上：38, 下：40,  回车：13
                    if(event.which == 38){
                        if(_i == 0 || _i == -1 ){
                            _i = _len - 1;
                            $searchListli.eq(_i).addClass("hover").siblings().removeClass("hover");
                            $searchTxt.val($("#searchList li.hover a").text());
                            return false;
                        }
                        if(_i > 0 && _i < _len){
                            _i --;
                            $searchListli.eq(_i).addClass("hover").siblings().removeClass("hover");
                            $searchTxt.val($("#searchList li.hover a").text());
                            return false;
                        }

                    }
                    if(event.which == 40){
                        if(_i == _len - 2){
                            _i = -1;
                            $searchListli.eq(_i).addClass("hover").siblings().removeClass("hover");
                            $searchTxt.val($("#searchList li.hover a").text());
                            return false;
                        }
                        if(_i >= -1 && _i < _len - 1){
                            _i ++;
                            $searchListli.eq(_i).addClass("hover").siblings().removeClass("hover");
                            $searchTxt.val($("#searchList li.hover a").text());
                            return false;
                        }
                    }
                };

                //单击搜索结果
                $("#searchList").on("click", ".search-item a", function(){
                    var _q = $(this).text();
                    if($("#searchForm").length > 0){
                        $("#searchFormQ").val(_q);
                        $("#searchForm").submit();
                    }else{
                        $("#headerSearch").submit();
                    }
                });

                //_keyboardOprate();
                $searchTxt.bind('keyup', _query);
                $searchTxt.bind('keydown',_keyboardOprate);
                $searchTxt.bind('focus', _query);
                $searchTxt.bind('blur', _hide);

            });
        }
    };
    newindex.init();
});
function searchMe(key){
	$("#search_form input[id=q]").val(key);		
	window.location.href = "/market/brands/search?" + "q=" + encodeURIComponent($.trim(key)) ;
}
/*
bug #6399 导航头部：点击“收藏本站”，报错 去除收藏，可删除
function AddFavorite(title, url) {//收藏本站
    try {
        window.external.addFavorite(url, title);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(title, url, "");
        }
        catch (e) {
            alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}*/
// 路径解析
function parseURL(url) {
    var a = document.createElement('a');
    a.href = url;
    return {
        source: url,
        protocol: a.protocol.replace(':', ''),
        host: a.hostname,
        port: a.port,
        query: a.search,
        params: (function () {
            var ret = {},
                seg = a.search.replace(/^\?/, '').split('&'),
                len = seg.length, i = 0, s;
            for (; i < len; i++) {
                if (!seg[i]) {
                    continue;
                }
                s = seg[i].split('=');
                ret[s[0]] = s[1];
            }
            return ret;
        })(),
        file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
        hash: a.hash.replace('#', ''),
        path: a.pathname.replace(/^([^\/])/, '/$1'),
        relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
        segments: a.pathname.replace(/^\//, '').split('/')
    };
}
/**
 *   ajax登录
 */
function ajaxLogin(){
    if($("#ajaxLogin").length > 0 && $("#ajaxLogin").html() != ""){
        $("#ajaxLogin").show();
        return;
    }
    $.get("/common/login_dialog",function(data){
        $('<div id="ajaxLogin">'+data+'</div>').appendTo($("body"));
        $(".login-dialog-tabmenu").on("click", "li a", function () {
            var index = $(this).parents("li").index();
            $(".login-dialog-tabmenu  li").removeClass("selected").eq(index).addClass("selected");
            if(index == 0){
                $("#userType").val(1);
            }else if(index == 1){
                $("#userType").val(0);
            }
        });
        $("#login-btn").on("click",function(){
            if($("input[name='account']").val() == ""){
                remind("error","请输入会员名");
                return;
            }
            if($("input[name='pwd']").val() == ""){
                remind("error","请输入密码");
                return;
            }
            $.ajax({
                url: $("#command").attr("action"),
                method: "post",
                data: $("#command").serialize(),
                dataType: "json",
                success: function(data){
                    if (data.code === 126000) {
                        var redirect = $.trim($('input[name="redirect"]').val());
                        if (redirect == '') {
                            redirect = data.object.redirect;
                        } else {
                            var link = parseURL(redirect);
                            var current = parseURL(window.location.href);
                            if (link.host == current.host) { // 相同域名
                                var userType = parseInt($('#userType').val());
                                switch (userType) {
                                    case 0:
                                        if (link.path.indexOf('/dealer') == 0) {
                                            redirect = data.object.redirect;
                                        }
                                        break;
                                    case 1:
                                        if (link.path.indexOf('/brand') == 0) {
                                            redirect = data.object.redirect;
                                        }
                                        break;
                                    default :
                                        break;
                                }
                            } else {
                                redirect = data.object.redirect;
                            }
                        }
                        window.location.href = redirect;
                    } else {
                        remind("error",data.message);
                    }
                }
            });
            return false;
        });
        $("#ajaxLogin .login-dialog-close").on("click",function(){
            $("#ajaxLogin").hide();
        })

    });
}

//让图片在一个容器里面垂直居中
$(".js_img_center,.js-img-center").each(function () {
    //if(!$(this).find("span")){
        $("<span></span>").appendTo($(this));
    //}
});

$(function(){
    if(/msie 6\.0/i.test(navigator.userAgent.toLowerCase()))
    {
        $(window).scroll(function(){
            var wheight = $(window).height() - 50;
            var pos = parseInt(this.scrollY || document.documentElement.scrollTop || document.body.scrollTop) + wheight;
            $(".sorry-ie").css({top:pos});
        });

        $(".close-alert").click(function(){
            $(".sorry-ie").remove();
        });
    }
});