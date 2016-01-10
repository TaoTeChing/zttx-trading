seajs.use(['carousel'],function(Carousel){
    var v2_index = {
        init: function(){
            this.search();
            this.cart();
            this.typeMove();
            this.banner();
            //this.cardTab();
            this.goodBrand();
            this.hall();
            this.news();
        },
        search: function(){
            //头部搜索框
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
            //购物车
            $("#cart").hover(function(){
                $(this).addClass("hover");
                //$("#cartDrop").stop().fadeIn("fast");
            },function(){
                $(this).removeClass("hover");
                //$("#cartDrop").stop().fadeOut("fast");
            });
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
                /*$(".type-item .type-item-sub").removeClass("hover").hide();
                 $(this).find(".type-item-sub").addClass("hover").show();*/
                /*$(this).find(".type-item-sub").stop(false, false).fadeIn("normal",function(){
                 $(".type-item .type-item-sub").not(_self.find(".type-item-sub")).stop(false, false).fadeOut();
                 });*/
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
            new Carousel({
                element: '#focus',
                panels: '#focus .focus-list',
                effect: 'scrollx',
                autoplay: true
            });

            //banner图下方轮播
            new Carousel({
                element: '#brand-list-con',
                panels: '#brand-list-con .brand-list-item',
                effect: 'scrollx',
                prevBtn: '.brand-list .prev',
                nextBtn: '.brand-list .next',
                step: 3,
                autoplay: true,
                circular: false,
                interval: 5400
            });

            new Carousel({
                element: '#card-hd',
                panels: '#card-hd li.item',
                effect: 'scrollx',
                autoplay: true,
                interval: 5300
            });
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
            //优选品牌
            var tpl = [
                [
                    '<a href="" style="width: 197px;"><img class="animated moveLeft1" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 395px;"><img class="animated moveLeft2" src="images/temp/brand_02.jpg" alt="" /></a>',
                    '<a href="" style="width: 196px;"><img class="animated moveLeft3" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft4" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft5" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft1" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 196px;"><img class="animated moveLeft2" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft3" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 395px;"><img class="animated moveLeft4" src="images/temp/brand_02.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft5" src="images/temp/brand_03.jpg" alt="" /></a>'
                ],
                [
                    '<a href="" style="width: 197px;"><img class="animated moveLeft1" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 196px;"><img class="animated moveLeft2" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft3" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 395px;"><img class="animated moveLeft4" src="images/temp/brand_02.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft5" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft1" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 395px;"><img class="animated moveLeft2" src="images/temp/brand_02.jpg" alt="" /></a>',
                    '<a href="" style="width: 196px;"><img class="animated moveLeft3" src="images/temp/brand_03.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft4" src="images/temp/brand_01.jpg" alt="" /></a>',
                    '<a href="" style="width: 197px;"><img class="animated moveLeft5" src="images/temp/brand_03.jpg" alt="" /></a>'
                ]
            ], changeType = 0;
            $(".js-change-brand").click(function(){
                var len = tpl.length;
                if(changeType < (len - 1)){
                    changeType++;
                    $("#change_brand").html(tpl[changeType].join(""));
                    return;
                }
                if(changeType >= (len - 1)){
                    changeType = 0;
                    $("#change_brand").html(tpl[changeType].join(""));
                    return;
                }
            });
            $("#change_brand").on("mouseenter", "img", function(){
                $(this).stop().animate({
                    marginLeft: "10px"
                }, 200);
            });
            $("#change_brand").on("mouseleave", "img", function(){
                $(this).stop().animate({
                    marginLeft: 0
                }, 200);
            });
        },
        hall: function(){
            //hall 轮播图
            $(".hall-focus").each(function(){
                new Carousel({
                    element: $(this).find(".hall-focus-content"),
                    panels: $(this).find(".hall-focus-list"),
                    effect: 'fade',
                    prevBtn: $(this).find(".prev"),
                    nextBtn: $(this).find(".next"),
                    //step: 3,
                    autoplay: true
                });
            });
            $(".hall-focus").hover(function(){
                $(this).find(".prev").stop().animate({
                    left: 0
                }, 128);
                $(this).find(".next").stop().animate({
                    right: 0
                }, 128);
            },function(){
                $(this).find(".prev").stop().animate({
                    left: -30
                }, 128);
                $(this).find(".next").stop().animate({
                    right: -30
                }, 128);
            });
            //hall跟随移动的横线
            //var $hallListTpl =$('<div class="hall-border"></div>');
            /*$(".hall-list li").hover(function(){
             var $this = $(this);
             var __height = $(this).height();
             //$hallListTpl.css("height", __height - 2);
             $this.append($hallListTpl);
             },function(){
             $(this).find($hallListTpl).remove();
             });*/
            /*$(".hall-list").mouseleave(function(){
                $(this).find($hallListTpl).remove();
            });
            $(".hall-list li").mouseenter(function(){
                var width = $(this).width() + 1;
                var index = $(this).index();
                $(this).parents(".hall-list").append($hallListTpl);
                $(this).parents(".hall-list").find($hallListTpl).stop().animate({
                    left: width * index - 1
                });
            });*/
        },
        news: function(){
            //news 轮播
            new Carousel({
                element: '#news-pic',
                panels: '#news-pic .news-pic-item',
                effect: 'fade',
                viewSize: [365, 250],
                //step: 1,
                autoplay: true
            });
        }
    };
    v2_index.init();
});