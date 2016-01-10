define("common/index",["$","arale/switchable/1.0.1/slide","gallery/turn/0.0.1/turn"],function(require, exports, module){

    var $ = require("$");
    var Slide = require("arale/switchable/1.0.1/slide");
    var Turn = require("gallery/turn/0.0.1/turn");

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

    var Index = {
        partA: function(){
            this.slide();
            this.overstepText();//banner图下面超出文字的部分
            this.brandLast();

        },
        partC: function(){
            this.realTime1();
        },
        partD: function(){
            this.dealImg();
        },
        partE: function(){
            this.realTime2();
        },
        partF: function(){
            this.turnImg();
        },
        partG: function(){
            this.infoSlide();
        },
        partH: function(){
             this.showSavingAmount();
        },
        slide: function () {
            var panels = $("#banner .ts-banner-item");
            panels.find("[data-animate]").each(function(i,o){
                $(o).data({ox: o.offsetLeft,oy: o.offsetTop});
            }).css({opacity:0});

            var slide = new Slide({
                element: "#banner",
                panels: "#banner .ts-banner-item",
                //triggers:"#banner .ts-banner-nav",
                effect: "scrollx",
                interval: 6180,
                viewSize:[800,250]
            }).on("switched", function (a, b) {
                    slidePlay(a, b);
                });

            var slidePlay = function (toIndex, fromIndex) {
                var $from = $(panels).eq(fromIndex).find("[data-animate]").css({opacity:0});
                $from.each(function (i, o) {
                    $(o).css({ left: $(o).data("ox"), top: $(o).data("oy") });
                });

                var $to = $(panels).eq(toIndex).find("[data-animate]");
                $to.each(function (i, o) {
                    var styles = eval($(o).data("animate"));
                    styles.opacity = 1;
                    setTimeout(function () {
                        $(o).animate(styles, 618);
                    }, 618 * (i + 1));
                });
            };
            slidePlay(0,1);
        },
        overstepText:function(){
            $(".product-item").hover(function(){
                $(this).find(".product-item-word").stop().animate({"top":-30,"height":100})
            },function(){
                $(this).find(".product-item-word").removeClass("active").stop().animate({"top":0,"height":66});
            });
        },
        brandLast:function(){
            $(".js-brand-last").hover(function(){
                $(this).stop().animate({"margin-top":-50},312);
            },function(){
                $(this).stop().animate({"margin-top":0},312);
            });
        },
        realTime1: function(){
            $.realMoveFn({
                parentID:".ts-list",childID:".ts-list li",_len:9,pad:0
            });
        },
        realTime2: function(){
            $.realMoveFn({
                parentID:".real-time-con",childID:".real-time",_len:6,pad:20
            });
        },
        dealImg: function(){
            $(".online-fair-start img").hover(function(){
                $(".online-fair-start img").stop().animate({
                    opacity:0.8
                });
                $(this).stop().animate({
                    opacity:1
                });
            },function(){
                $(".online-fair-start img").stop().animate({
                    opacity:1
                });
            });
        },
        turnImg: function(){
            new Turn({
                element:"#brand_recommend",
                panels : "#brand_recommend div",
                count : 10,
                interval : 6180,
                animates : ["bounceIn","bounceOut"],
                much : 3
            });

            $(".part-f").on("mouseenter mouseleave",".js-img-center a",function(e){

                var $img = $(this).find("img");

                if(e.type == "mouseenter"){
                    $(this).css("border","1px solid #e1e1e1");
                    $img[0].src = $($img).data("logo");
                }

                if(e.type == "mouseleave"){
                    $(this).css("border","0");
                    $img[0].src = $($img).data("pic");
                }

            });
        },
        infoSlide: function(){
            var length = $(".info-list-pic .info-list-ul li").length;
            if(length > 0){
                new Slide({
                    element: '.info-list-pic',
                    trigger: '',
                    panels: '.info-list-pic .info-list-ul li',
                    effect: 'scrollx',
                    activeIndex: 0
                });
            }
        },
        showSavingAmount:function(){
            var elem = $("#saving-amount");
            var html = this.formatNumber($.trim(elem.html()));
            html = html.replace(/\d/g,function($1){
                return "<span>"+$1+"</span>"
            })
            $(elem).html(html).show();
        },
        formatNumber: function(str){
            if(str.length <= 3){
                return str;
            } else {
                return this.formatNumber(str.substr(0,str.length-3))+'<span class="comma">&nbsp;</span>'+str.substr(str.length-3);
            }
        }
    }

    module.exports = Index;

});

