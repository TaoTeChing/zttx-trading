$(document).ready(function () {
    /*$("#carousel").on("click", function () {
        //console.log($(this));
        //此处是关于产品展示点击不能跳转的问题解决
        //请勿删除
    });*/

    //输入框默认背景提示
    $('[deftxt]').css({ color: '#bbb' }).focus(function () {
        if ($(this).val() == $(this).attr("deftxt")) {
            $(this).val("").css({ color: '#666' });
        }
    }).blur(function () {
        if ($(this).val() == '') {
            $(this).val($(this).attr("deftxt")).css({ color: '#bbb' });
        }
    });
    //让图片在一个容器里面垂直居中
    $(".js_img_center,.js-img-center").each(function () {
        $("<span></span>").appendTo($(this));
    });
    
    
    //setTimeout(function(){此处效果不大
    	//$('.qq-area').html("<script charset=\"utf-8\" type=\"text/javascript\" src=\"http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18\"></script>");
        //$(".qq-area").load("/market/brand/qq");
    //},2000);
    /*if($(".qq-area").html()==''){//QQ在线
        $(".qq-area").html('<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>');
    }*/
    //侧边栏特效
    /*$('.sidebar-r .attention').hide();
     $('.sidebar-r .hidelogobox').css('border-top','#ddd 1px solid');
     $(window).scroll(function(){
     if ($(window).scrollTop()>10){
     $('.sidebar-r .attention').show(100);
     $('.sidebar-r .hidelogobox').css('border-top','0');
     }
     else
     {
     $('.sidebar-r .attention').hide(100);
     $('.sidebar-r .hidelogobox').css('border-top','#ddd 1px solid');
     }
     });*/

    //新首页公用样式
    var newindex = {
        init: function () {
            this.topMenu();//顶部导航
            //this.footerAttr();//底部关注样式
            this.carousel();//产品展示JS调用
            //this.successfulcase();//成功案例鼠标移动背景
            this.comdown();//右侧边栏‘公司旗下品牌’透明度
            this.joinbtn();//提交加盟申请书关闭按钮
            this.leaveMessage();//提交留言
        },
        topMenu: function () {
            //顶部网站导航
            $(".site-nav-bd .top-menu").hover(function () {
                var panel = $(this).find(".menu-bd");
                if (panel.length == 1) {
                    $(this).addClass("hover");
                    panel.show();
                }
            }, function () {
                var panel = $(this).find(".menu-bd");
                if (panel.length == 1) {
                    $(this).removeClass("hover");
                    panel.hide();
                }
            });
        },
        footerAttr: function () {
            var _hover = $(".footer-show-hover");
            var _sina = $(".footer-show-sinaWb");//暂时只给一个链接
            var _qcode = $(".footer-show-qcode");

            $(".footer-wx").hover(function () {
                _hover.show();
                _qcode.show();
            }, function () {
                _hover.hide();
                _qcode.hide();
            });
        },
        carousel:function(){
            /*if($('#carousel').length > 0){
                $('#carousel').elastislide({
                    imageW: 216,
                    minItems: 3,
                    margin: 20,
                    speed:1000,
                    isMove:true,
                    start:6,
                    easying:"ease-in-out",
                    onClick: function () {
                        return false;
                    }
                });
                $(".es-carousel li a").click(function(){//新窗口打开产品详细页
                    var _href = $(this).attr("href");
                    window.open(_href);
                });
            }*/
            if($('#carousel').length > 0 && $(".es-carousel li").length > 0){
                seajs.use(['carousel'],function(Carousel){
                    var carousel = new Carousel({
                        "element": $(".es-carousel"),
                        "panels": $(".es-carousel li"),
                        "effect": "scrollx",
                        "step": 1,
                        "viewSize": [242],
                        "circular": false,
                        "autoplay": true,
                        hasTriggers:false,
                        prevBtn:$(".es-nav .es-nav-prev"),
                        nextBtn:$(".es-nav .es-nav-next")
                    });
                });
            }
        },
        successfulcase:function(){
            if($(".successfulcase .sucdl").length > 0){
                $('.successfulcase .sucdl').hover(function () {
                    $(this).css('background', '#f2f2f2');
                }, function () {
                    $(this).css('background', '#fff');
                });
            }
        },
        comdown:function(){
            if($(".sidebar-r .comdown .comdownul li dd img").length>0){
                $('.sidebar-r .comdown .comdownul li dd img').hover(function () {
                    $(this).stop().animate({
                        opacity: '0.5'
                    });
                }, function () {
                    $(this).stop().animate({
                        opacity: '1'
                    });
                });
            }
        },
        joinbtn:function(){
            //提交加盟申请书关闭按钮
            $(document).on("click", ".ui-head i", function () {
                $(".js_login_box").hide();
                $(".lig").hide();
            });
            if($(".js-all-throw").length > 0){
                $(".do-body").on("click", "#joinbtn", function () {

                    $.post('/common/islogin', function (data) {
                        if (data.code == 126000) {
                            if (data.object == 1) {
                                var $listbody = $(".listbody");
                                var $jsLoginBox = $(".js_login_box");
                                $jsLoginBox.show();
                                var top = ($(window).height() - $jsLoginBox.height()) / 2;
                                var left = ($(window).width() - $jsLoginBox.width()) / 2;

                                var scrollTop = $(document).scrollTop();
                                var scrollLeft = $(document).scrollLeft();

                                if(top < 0){//top为负数的时候
                                    top = 10;
                                }
                                $listbody.css({
                                    position: 'static'
                                });
                                $jsLoginBox.css({
                                    position: 'absolute',
                                    top: top + scrollTop,
                                    left: left + scrollLeft,
                                    'z-index': '301'
                                });
                                $('.lig').show();
                            } else {
                                if (window.confirm("您还未登录，是否登录进行操作？")) {
                                    window.location = '/common/login?loginType=1&redirect=' + encodeURIComponent(window.location.href);
                                }
                            }
                        }else {
                            if (window.confirm("您还未登录，是否登录进行操作？")) {
                                window.location = '/common/login?loginType=1&redirect=' + encodeURIComponent(window.location.href);
                            }
                        }
                    }, 'json');
                });
                baseFormValidator({
                    selector:'#applyForm',
                    isAjax:true,
                    addElemFn:function(Core,Validator){
                        Core.addItem({
                            element: '[name=telphone]',
                            required: true,
                            rule: 'mobile'
                        });
                    },
                    beforeSubmitFn:function(){
                         //console.log("验证通过");
                         var $applyForm = $('#applyForm');
                         //$('#applyForm').on('click', '#applyFormSubmit', function(event) {
                         //event.preventDefault();
                             var applyText = $.trim($('textarea[name="inviteText"]').val());
                             if (applyText == '') {
                                 remind('error', '请填写申请信息');
                                 return false;
                             }
                        // nginx rewrite 地址
                             $.post('/addJoin', $applyForm.serialize(), function (data) {
                                 switch (data.code) {
                                     case 126000:
                                         remind('success', '加盟申请已经提交，等待品牌商处理');
                                         $(".js_login_box").hide();
                                         $(".lig").hide();
                                         return true;
                                     case 126013:
                                         if (window.confirm("您还未登录，是否登录进行操作？")) {
                                            window.location = '/common/login?loginType=1&redirect=' + encodeURIComponent(window.location.href);
                                         }
                                        return false;
                                     case 111017:
                                         remind('error', '您的申请正在审核中');
                                         $(".js_login_box").hide();
                                         $(".lig").hide();
                                        return false;
                                     default :
                                        remind('error', data.message);
                                        return false;
                                 }
                             }, 'json');
                         //});
                    }
                });
            }
        },
        leaveMessage:function(){
            var msgForm = $('#msg-form');
            $(".content").on('click', '#btn-send', function(){
                var content = $.trim(msgForm.find('textarea[name="content"]').val());
                if (content == '') {
                    remind('error', '留言内容不能为空');
                    return false;
                }
                $.ajax('/dealer/message/leaveToBrand', {
                    type: 'POST',
                    data: msgForm.serialize(),
                    dataType: 'json',
                    success: function(data) {
                        msgForm.find('textarea[name="content"]').val("");
                        remind('success', '留言成功');
                    },
                    error:function(){
                        remind('error', '留言失败');
                    },
                    statusCode: {

                        403: function() {
                            location.reload(true);
                        }
                    }
                });
            });
            msgForm.submit(function(event) {
                event.preventDefaultEvents();
            });
        }
    }
    newindex.init();
});
(function ($) {//右侧公司旗下品牌滚动
    $.fn.extend({
        //可以用作多图滚动，也可以用做幻灯片
        "someSlide": function (options) {
            //设置默认值
            options = $.extend({
                xuanid: '#focus',
                changdu: '#focus ul li',
                changul: '#focus ul',
                anniu: '#focus .btnBg',
                huaru: '#focus .btnn span',
                pre: '#focus .pre',
                next: '#focus .next',
                toumingdu: '#focus .preNext'
            }, options);
            var sWidth = $(options.xuanid).width(); //获取焦点图的宽度（显示面积）
            var len = $(options.changdu).length; //获取焦点图个数
            var index = 0;
            var picTimer;

            if (len < 2) {
                return false;
            }

            //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
            var btn = "<div class='btnBg'></div><div class='btnn'>";
            for (var i = 0; i < len; i++) {
                btn += "<span></span>";
            }
            btn += "</div><div class='preNext pre'>&lt;</div><div class='preNext next'>&gt;</div>";

            $(options.xuanid).append(btn);
            //$(options.anniu).css("opacity",0.5);
            //添加移动显示事件
            $(options.xuanid).hover(function () {
                $(options.toumingdu).stop().toggle();
            });
            //为小按钮添加鼠标滑入事件，以显示相应的内容
            $(options.huaru).css("opacity", 0.4).mouseover(function () {
                index = $(options.huaru).index(this);
                showPics(index);
            }).eq(0).trigger("mouseover");
            //上一页、下一页按钮透明度处理
            $(options.toumingdu).css("opacity", 0.2).hover(function () {
                $(this).stop(true, false).animate({"opacity": "0.5"}, 300);
            }, function () {
                $(this).stop(true, false).animate({"opacity": "0.2"}, 300);
            });
            //上一页按钮
            $(options.pre).click(function () {
                index -= 1;
                if (index == -1) {
                    index = len - 1;
                }
                showPics(index);
            });
            //下一页按钮
            $(options.next).click(function () {
                index += 1;
                if (index == len) {
                    index = 0;
                }
                showPics(index);
            });
            //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
            $(options.changul).css("width", sWidth * (len));

            //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
            $(options.xuanid).hover(function () {
                clearInterval(picTimer);
            }, function () {
                picTimer = setInterval(function () {
                    showPics(index);
                    index++;
                    if (index == len) {
                        index = 0;
                    }
                }, 4000); //此4000代表自动播放的间隔，单位：毫秒
            }).trigger("mouseleave");

            //显示图片函数，根据接收的index值显示相应的内容
            function showPics(index) { //普通切换
                var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
                $(options.changul).stop(true, false).animate({"left": nowLeft}, 300); //通过animate()调整ul元素滚动到计算出的position

                $(options.huaru).stop(true, false).animate({"opacity": "0.4"}, 300).eq(index).stop(true, false).animate({"opacity": "1"}, 300); //为当前的按钮切换到选中的效果
            }
        }
    });
})(jQuery);
$("#focus").someSlide();

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
}