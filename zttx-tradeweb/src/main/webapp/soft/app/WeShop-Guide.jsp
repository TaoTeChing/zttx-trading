<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>微店引导</title>
    <link href="${res}/styles/fronts/soft/app/guide.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/about/animate.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <jsp:include page="_header.jsp">
            <jsp:param value="fixed" name="fixed"/>
        </jsp:include>
        <div class="sub-nav">
            <a class="active" href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
        </div>
        <div class="page-main">
            <div class="page-item page-banner">
                <div class="item-content">
                    <a href="javascript:pagejs.initSwitch(1);"><img class="index-0-0" src="${res}/images/fronts/soft/app/guide/arrow.png"></a>
                </div>
            </div>
            <div class="page-item page-start">
                <div class="item-content">
                    <img class="index-0-1 animated bounceInLeft" src="${res}/images/fronts/soft/app/guide/guide-phone-1.jpg" />
                    <img class="index-0-2 animated bounceInUp" src="${res}/images/fronts/soft/app/guide/guide-phone-2.jpg" />
                    <img class="index-0-3 animated bounceInRight" src="${res}/images/fronts/soft/app/guide/guide-phone-3.jpg" />
                </div>
                <div class="start-bar">
                    <div class="ts-container">
                        <div class="nav-num guide-icon" id="goto_3">GO</div> 开通步骤
                    </div>
                </div>
            </div>
            <div class="page-item page-index-1">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">01</div>
                    </div>
                    <div class="item-content">
                        <div class="media download">
                            <h2 class="media-title">下载微逛APP，并成功注册</h2>
                            <div class="media-content layout">
                                <div class="layout-left animated fadeInDown">
                                    <a class="guide-icon btn" href="${res}/upload/soft/app/weiguang_V1.0.0_8637_release_201409121348.ipa" title="微逛APP-iPhone版下载" target="_blank">
                                        <i class="guide-icon ios"></i>
                                        <h3>iPhone版下载</h3>
                                        <span>版本号：V1.2</span>
                                    </a>
                                    <a class="guide-icon btn" href="${res}/upload/soft/app/weiguang_V1.0.2_8637_release_201410231113.apk" title="微逛APP-Android版下载" target="_blank">
                                        <i class="guide-icon android"></i>
                                        <h3>Android版下载</h3>
                                        <span>版本号：V1.1</span>
                                    </a>
                                </div>
                                <div class="layout-right">
                                    <div class="qrcode animated bounceIn" id="qrcode1"></div>
                                </div>
                            </div>
                            <img class="index-1-1" src="${res}/images/fronts/soft/app/guide/index-1-1.png" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-item page-index-2">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">02</div>
                    </div>
                    <div class="item-content">
                        <div class="media register">
                            <h2 class="media-title">注册8637交易平台</h2>
                            <p class="media-title-sub">如果您从交易平台进货，可以很方便的同步数据到微店哦，免除手动上传产品的烦恼</p>
                            <div class="media-content">
                                <img class="index-2-1 animated fadeInDown" src="${res}/images/fronts/soft/app/guide/index-2-1.png" />
                                <img class="index-2-2" src="${res}/images/fronts/soft/app/guide/index-2-2.png" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-item page-index-3">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">03</div>
                    </div>
                    <div class="item-content">
                        <div class="media login">
                            <h2 class="media-title">登录后台首页，找到开通微店入口，点击“立即开通”按钮</h2>
                            <p class="media-title-sub">开通以后，您就可以在后台首页方便的管理您的微店啦~还支持多微店管理呢</p>
                            <div class="media-content">
                                <img class="index-3-1" src="${res}/images/fronts/soft/app/guide/index-3-1.gif" />
                                <img class="index-3-2 animated fadeInLeft" src="${res}/images/fronts/soft/app/guide/index-3-2.png" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-item page-index-4">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">04</div>
                    </div>
                    <div class="item-content">
                        <div class="media enter">
                            <h2 class="media-title">输入注册微逛时使用的手机号，获取验证码，开通微店。</h2>
                            <p class="media-title-sub">一个手机号/微逛号 只能绑定一个终端商账号</p>
                            <div class="media-content">
                                <img class="index-4-1 animated fadeInDown" src="${res}/images/fronts/soft/app/guide/index-4-1.png" />
                                <img class="index-4-2" src="${res}/images/fronts/soft/app/guide/index-4-2.png" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-item page-index-5">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">05</div>
                    </div>
                    <div class="item-content">
                        <div class="media perfect">
                            <h2 class="media-title">完成最后一步，完善店铺资料，您的微店就开通完成啦~</h2>
                            <p class="media-title-sub">准确无误的地址可以让买家更容易的在微逛上找到你的店哦~</p>
                            <div class="media-content">
                                <img class="index-5-1" src="${res}/images/fronts/soft/app/guide/index-5-1.gif" />
                                <img class="index-5-2 animated fadeInRight" src="${res}/images/fronts/soft/app/guide/index-5-2.png" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-item page-index-6">
                <div class="ts-container">
                    <div class="page-nav">
                        <div class="nav-border"></div>
                        <div class="nav-num guide-icon animated bounceInDown">06</div>
                    </div>
                    <div class="item-content">
                        <div class="media success">
                            <h2 class="media-title">手机登录微逛app，在“微应用”中可以看到“我的微店”<br>点进去看看吧~</h2>
                            <div class="media-content">
                                <img class="index-6-1" src="${res}/images/fronts/soft/app/guide/index-6-1.png" />
                                <img class="index-6-2 animated fadeInRight" src="${res}/images/fronts/soft/app/guide/index-6-2.png" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="_footer.jsp" />
            <%--<div class="page-item footer">
                <div class="footer-bar">
                    <div class="ts-container">
                        <div class="bar-item">
                            <i class="guide-icon icon-mail"></i> <span>weiguang@8637com</span>
                        </div>
                        <div class="bar-item">
                            <i class="guide-icon icon-weibo"></i> <a href="" title="">微逛团队</a>
                        </div>
                        <div class="bar-item">
                            <i class="guide-icon icon-weixin"></i> <span id="qrcode3" class="qrcode"></span>
                        </div>
                        <div class="bar-item">
                            <i class="guide-icon icon-tel"></i> <span>0874-87217777</span>
                        </div>
                    </div>
                </div>
                <div class="ts-container">
                    <div class="layout">
                        <div class="layout-left qi-shi-wo-ye-hen-tiao-pi">
                        </div>
                        <div class="layout-right">
                            <div class="footer-down-app clearfix">
                                <div class="fl">
                                    <a class="guide-icon btn" href="" title="">
                                        <i class="guide-icon ios"></i>
                                        <h3>iPhone版下载</h3>
                                        <span>版本号：V1.2</span>
                                    </a>
                                    <a class="guide-icon btn" href="" title="">
                                        <i class="guide-icon android"></i>
                                        <h3>Android版下载</h3>
                                        <span>版本号：V1.1</span>
                                    </a>
                                </div>
                                <div class="qrcode fl" id="qrcode2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>--%>
        </div>
    </div>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <!--[if IE 6]>
    <script src="${res}/scripts/DD_belatedPNG.js" type="text/javascript"></script>
    <script>DD_belatedPNG.fix("*");</script>
    <![endif]-->
    <script>
        var pagejs = {
            init: function () {
                this.initItemReset();//初始化屏幕;
                this.initMouseScroll();//鼠标滚轮处理;
                this.initSwitch(0);//切换处理;
                this.initResize();//窗口调整;
                this.initSubNav();//小导航;
                this.initQrcode();//二维码;
                this.initAnimate(0);
                window.scrollTo(0,0);

            },
            initItemReset: function () {
                pageItem.each(function (i, o) {
                }).height(winHeight);
                this.initSwitch(pageIndex);
            },
            initMouseScroll: function () {
                var me = this;
                var isScroll = true; //是否触发;
                var scrollFunc = function (e) {

                    if (isScroll) {
                        e = e || window.event;
                        if (e.wheelDelta || e.detail) {
                            if (e.wheelDelta > 0 || e.detail < 0) {
                                //上滚
                                pageIndex = pageIndex > 0 ? --pageIndex : 0;

                            } else {
                                //下滚
                                pageIndex = pageTotal-1 > pageIndex ? ++pageIndex : pageTotal-1;
                            }
                            me.initSwitch(pageIndex);
                        }
                        isScroll = false;//不继续触发;
                        setTimeout(function () {
                            isScroll = true;//延时1秒后继续支持触发;
                        }, 1000);
                    }
                };
                document.addEventListener && document.addEventListener("DOMMouseScroll", scrollFunc, !1), window.onmousewheel = document.onmousewheel = scrollFunc;
            },
            initSwitch: function (index) {
                this.initAnimate(index);
                //var me = this;
                //console.log(index,pageTotal-1);
                if(index==0){
                    $(".header").slideDown();
                }else{
                    $(".header").slideUp();
                }
                if(index!=pageTotal-1)
                {
                    var top = -winHeight * index;
                }
                else
                {
                    var top = -winHeight * (index-1) - 344 ;
                }
                $(pageMain).stop().animate({ "top": top });
                $(subNav).removeClass("active").eq(index).addClass("active");

            },
            initSubNav: function () {
                var me = this;
                $(subNav).click(function (e) {
                    e.preventDefault();
                    pageIndex = $(this).index();
                    me.initSwitch(pageIndex);
                });
                $("#goto_3").click(function(){
                    $(subNav).eq(2).trigger("click");
                });
            },
            initResize: function () {
                var me = this;
                var time = 0;
                window.onresize = function () {
                    clearTimeout(time);
                    time = setTimeout(function () {
                        winHeight = $(window).innerHeight();
                        me.initItemReset();
                    }, 618);
                }
            },
            initQrcode: function () {
                seajs.use("qrcode", function (Qrcode) {
                    $('#qrcode1').append(new Qrcode({
                        text: "http://www.8637.com/soft/appshop/download.jsp",
                        width: 150,
                        height: 150
                    }));
                });
            },
            initAnimate:function(index){
                var items = $(pageItem).eq(index).find(".animated").hide();
                items.each(function(i,o){
                    setTimeout(function(){
                        $(o).show();
                    },100*i);
                });
            }
        },
        header = $(".header"),
        pageMain = $(".page-main"),
        subNav = $(".sub-nav a"),
        pageItem = $(".page-item"),
        pageIndex = 0,
        //detail = true;
        pageTotal = pageItem.length,
        winHeight = $(window).innerHeight();
        //animateElmt = $(".animated");
        pagejs.init();
    </script>

</body>
</html>
