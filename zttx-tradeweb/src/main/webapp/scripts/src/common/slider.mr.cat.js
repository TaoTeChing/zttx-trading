/// <reference path="jquery-1.10.2.min.js" />
(function ($) {
    $.fn.extend({
        slider: function (options) {

            var defaults = {
                speed: 500, // 速度
                isPage: true,   // 是否显示数字
                delay: 5000, //停留时间
                autoPlay: true,
                isDelay: true,
                mousePause: true
            };
            var opts = $.extend(defaults, options);

            var $this = $(this);
            var sWidth = $($this).width(); //获取焦点图的宽度（显示面积）
            var len = $($this).find("ul li").length; //获取焦点图个数
            var index = 0;
            var picTimer;

            $("[data-animate]").each(function(i,o){
                $(o).data({ ox: $(o).css("left"), oy: $(o).css("top") });
            });

            //以下代码添加数字按钮和按钮后的半透明长条
            if (opts.isPage) {
                var btn = "<div class='btnBg'></div><div class='btn'>";
                for (var i = 0; i < len; i++) {
                    btn += "<span>" + (i + 1) + "</span>";
                }
                btn += "</div>"
                $($this).append(btn);
                $($this).find(".btnBg").css("opacity", 0.5);

                //为数字按钮添加鼠标滑入事件，以显示相应的内容
                $($this).find(".btn span").mouseenter(function () {
                    index = $($this).find(".btn span").index(this);
                    showPics(index);
                    showAnimate(index);
                }).eq(0).trigger("mouseenter");
            }


            //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
            $($this).find("ul").css("width", sWidth * (len + 1));

            //鼠标滑入某li中的某div里，调整其同辈div元素的透明度，由于li的背景为黑色，所以会有变暗的效果
            $($this).find("ul li div").not("[data-animate]").hover(function () {
                $(this).siblings().css("opacity", 0.7);
            }, function () {
                $($this).find("ul li div").css("opacity", 1);
            });

            if (opts.autoPlay) {
                //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
                if (opts.mousePause) {
                    $($this).hover(function () {
                        clearInterval(picTimer);
                    }, function () {
                        picTimer = setInterval(function () {

                            if (index == len-1) { //如果索引值等于li元素个数，说明最后一张图播放完毕，接下来要显示第一张图，即调用showFirPic()，然后将索引值清零
                                showFirPic();
                                index = 0;
                            } else { //如果索引值不等于li元素个数，按普通状态切换，调用showPics()
                                index++;
                                showPics(index);
                            }
                            showAnimate(index);
                        }, opts.delay); //此3000代表自动播放的间隔，单位：毫秒
                    }).trigger("mouseleave");
                }
                else {
                    picTimer = setInterval(function () {
                        if (index == len) { //如果索引值等于li元素个数，说明最后一张图播放完毕，接下来要显示第一张图，即调用showFirPic()，然后将索引值清零
                            showFirPic();
                            index = 0;
                        } else { //如果索引值不等于li元素个数，按普通状态切换，调用showPics()
                            index++;
                            showPics(index);
                        }
                        showAnimate(index);

                    }, opts.delay);
                }

            }

            /*if (!opts.isDelay) {
                showPics(index);
                showAnimate(index);
                index++;
            }*/

            //显示图片函数，根据接收的index值显示相应的内容
            function showPics(index) { //普通切换
                var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
                $($this).find("ul").stop(true, false).animate({ "left": nowLeft }, opts.speed); //通过animate()调整ul元素滚动到计算出的position
                $($this).find(".btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
            }

            function showFirPic() { //最后一张图自动切换到第一张图时专用
                $($this).find("ul").append($($this).find("ul li:first").clone());
                var nowLeft = -len * sWidth; //通过li元素个数计算ul元素的left值，也就是最后一个li元素的右边
                $($this).find("ul").stop(true, false).animate({ "left": nowLeft }, opts.speed, function () {
                    //通过callback，在动画结束后把ul元素重新定位到起点，然后删除最后一个复制过去的元素
                    $($this).find("ul").css("left", "0");
                    $($this).find("ul li:last").remove();
                });
                $($this).find(".btn span").removeClass("on").eq(0).addClass("on"); //为第一个按钮添加选中的效果
            }

            function showAnimate(index) {
                $("[data-animate]").css({opacity:0});
                var nowli = $($this).find("ul li").eq(index);
                var n = $(nowli).find("[data-animate]");
                n.css({ position: "absolute" }).each(function (i, o) {
                    $(o).css({left:$(o).data("ox"),top:$(o).data("oy")});
                    var styles = eval($(o).data("animate"));
                    styles.opacity = 1;
                    setTimeout(function(){
                        /*var _newleft =$(o).data("end-x");
                        var _newtop =$(o).data("end-y");*/
                        $(o).animate(styles, 1000);
                    },618*i);
                });
            }
        }
    })
})(jQuery);
