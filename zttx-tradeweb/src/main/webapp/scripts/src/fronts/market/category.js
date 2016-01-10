$(function () {
    //导航下拉菜单
    $(".nav-center > ul > li").mouseenter(function () {
        $(this).find('.dropmenu').stop(false, true).show();
        $(this).siblings().removeClass('hover');
        $(this).find('.alink-bg').addClass('hover');
    }).mouseleave(function () {
        $(this).find('.dropmenu').stop(false, true).slideUp();
        $(this).find('.alink-bg').removeClass('hover');
    });
    //让图片在一个容器里面垂直居中
    $(".js_img_center,.js-img-center").each(function () {
        $("<span></span>").appendTo($(this));
    });
});
var category = {
    init: function () {
        this.lookmore();//点击分类-更多
        this.classifyhover();//
        this.curretHover();//调用轮播图插件
    },
    lookmore:function(){
        $(".classify-sel").on("click",".js-brand-more",function(){//点击更多
            if($(this).parents(".classify-sel").find(".classify-ul").hasClass("classify-height")){
                $(this).parents(".classify-sel").find(".classify-ul").removeClass("classify-height");
            }else{
                $(this).parents(".classify-sel").find(".classify-ul").addClass("classify-height");
            }
        });
        /*
         一期不上线，二期使用
         $(".classify-sel").on("click",".js-brand-selmore",function(){//点击多选
         var classul = $(this).parents(".classify-sel").find(".classify-ul");
         if(classul.hasClass("classify-height")){
         classul.removeClass("classify-height");
         }else{
         classul.addClass("classify-height");
         }
         $(this).parents(".classify-moregroup").hide();
         $(this).parents(".classify-sel").find(".classify-btngroup").show();
         });

         $(".classify-btngroup").on("click",".js-btn-cancle",function(){//点击取消
         $(this).parents(".classify-btngroup").hide();
         $(this).parents(".classify-btngroup").prev().show();
         $(this).parents(".classify-sel").find(".classify-ul").addClass("classify-height");
         $(this).parents(".classify-sel").find("a").removeClass("pp-selested");
         });

         $(".classify-ul li").on("click","a",function(){
         if($(this).parents(".classify-sel").find(".classify-btngroup").is(":visible")){
         $(this).hasClass("pp-selested") ? $(this).removeClass("pp-selested") : $(this).addClass("pp-selested");
         return false;
         }
         });*/
    },
    classifyhover:function(){
        /*$(".classify").hover(function(){
         var _this=$(this);
         var height = _this.height() - 2;
         var width = _this.width() - 4;
         if(!_this.children().hasClass("alreadyhover")){
         _this.append('<div class="alreadyhover" style="height: ' + height + 'px; width:'+ width + 'px; border:2px solid #d1cdc5; position:absolute; top:-1px; left:0; display:none;"></div>');
         }
         _this.find(".alreadyhover").stop().fadeToggle();
         });*/
        $(".classify").hover(function(){
            $(this).addClass("classify-hover");
        },function(){
            $(this).removeClass("classify-hover");
        });
    },
    curretHover:function(){//面包屑导航选中移动
        $(".bread-nav .current").hover(function(){
            $(this).css({
                "border-color":"#cc3333",
                "color":"#cc3333"
            });
            $(this).find(".icons-brand-up").css("background-position","-64px -13px");
            //afterhover();
        },function(){
            $(".bread-nav .current").css({
                "border-color":"#a9a9a9",
                "color":"#666"
            });
            $(".bread-nav .current").find(".icons-brand-up").css("background-position","-76px -13px");
        });
        /*function afterhover(){
         if($(".breadnavdropdown").is(":hidden")){
         console.log("111");
         $(".bread-nav .current").css({
         "border-color":"#a9a9a9",
         "color":"#666"
         });
         $(".bread-nav .current").find(".icons-brand-up").css("background-position","-76px -13px");
         }else{
         $(".bread-nav .current").css({
         "border-color":"#cc3333",
         "color":"#cc3333"
         });
         $(".bread-nav .current").find(".icons-brand-up").css("background-position","-64px -13px");
         }
         }*/
    }
}
category.init();

(function ($) {//右侧推荐品牌轮换图
    $.fn.extend({
        //可以用作多图滚动，也可以用做幻灯片
        "someSlide": function (options) {
            //设置默认值
            options = $.extend({
                xuanid: '#focus',
                changdu: '#focus ul li',
                changul: '#focus ul',
                anniu: '.btnBg',
                huaru: '.btnBg .btnn span',
                pre: '.btnBg .pre',
                next: '.btnBg .next',
                toumingdu: '.btnBg .preNext'
            }, options);
            var sWidth = $(options.xuanid).width(); //获取焦点图的宽度（显示面积）
            var len = $(options.changdu).length; //获取焦点图个数
            var index = 0;
            var picTimer;

            //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
            var btn = "<div class='btnBg'><div class='btnn'>";
            for (var i = 0; i < len; i++) {
                btn += "<span></span>";
            }
            btn += "</div><div class='preNext pre'><i class='brandicons icons-brand-prev'></i></div><div class='preNext next'><i class='brandicons icons-brand-next'></i></div></div>";
            $(options.xuanid).after(btn);
            /*$(options.anniu).css("opacity", 0.5);*/
            //添加移动显示事件
            /*$(options.xuanid).hover(function(){
             $(options.toumingdu).stop().toggle();
             });*/
            //为小按钮添加鼠标滑入事件，以显示相应的内容
            $(options.huaru)/*.css("opacity", 0.4)*/.mouseover(function () {
                index = $(options.huaru).index(this);
                showPics(index);
            }).eq(0).trigger("mouseover");
            //上一页、下一页按钮透明度处理
            /*$(options.toumingdu).css("opacity", 0.2).hover(function () {
             $(this).stop(true, false).animate({"opacity": "0.5"}, 300);
             clearInterval(picTimer);
             }, function () {
             $(this).stop(true, false).animate({"opacity": "0.2"}, 300);
             });*/
            $(options.toumingdu).hover(function () {
                $(this).stop(true, false);
                clearInterval(picTimer);
            }, function () {
                $(this).stop(true, false);
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

                $(".btnn span").eq(index).addClass("on").siblings().removeAttr("class");
            }
        }
    });
})(jQuery);
$("#focus").someSlide();