/**
 * 新闻资讯
 * create by : 冯唐路 2015-09-02
 * */
define(function(require, exports, module){

    var $     = require("$");
    var Slide = require("slide");
    var Tabs  = require("tabs");
    var Popup = require("popup");

    var News = {
        init: function(){
            this.navHover();
            this.bannerSlide();
            this.newsTab();
        },
        navHover: function(){

            var _padding = 50;

            //初始化跟随移动的箭头
            function initHoverArrow(){
                if($("#navMain .hover").length > 0){
                    var _width = $("#navMain .hover").width() + _padding;
                    var o_l = $("#navMain .hover").offset().left;
                    $("#navMain .nav-arrow").width(_width).stop().animate({
                        left: o_l
                    }, 100);
                }
            }
            initHoverArrow();

            $("#nav .drop_menu li").hover(function(){
                //无限菜单 打开
                var $menu = $(this).children(".menu");
                if($menu){
                    $menu.show();
                }
            }, function(){
                //无限菜单 收回
                var $menu = $(this).children(".menu");
                if($menu){
                    $menu.hide();
                }
            });

            $("#nav li.item").hover(function(){
                //跟随移动的箭头
                var _width = $(this).width() + _padding;
                var o_l = $(this).offset().left;
                //console.log("width:" + _width + "; OL:" + o_l);
                $("#navMain .nav-arrow").width(_width).stop().animate({
                    left: o_l
                }, 100);
            }, function(){
                //跟随移动的箭头 收回
                initHoverArrow();
            });

            $("#nav li").each(function(){
                new Popup({
                    trigger: $(this),
                    element: $(this).find(".drop_menu"),
                    align: {
                        baseXY: [0, 50]
                    }
                });
            });
        },
        bannerSlide: function(){
            if($("#slide_box").length > 0){
                new Slide({
                    element: '#slide_box',
                    panels: '#slide_box .item',
                    effect: 'fade',
                    activeIndex: 0
                });
            }
        },
        newsTab: function(){
            if($("#tab").length > 0){
                new Tabs({
                    element: '#tab',
                    triggers: '#tab .tab-menu li',
                    panels: '#tab .tab-con div',
                    activeTriggerClass: 'active'
                });
            }
        }
    };

    module.exports = News;
});