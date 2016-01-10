(function($) {//可用于全屏
        $.fn.extend({
            "slideMove":function(options){
                //设置默认值
                options=$.extend({
                    slidePage:'#slide .slide-nav-box a',
                    slideNum:'#slide .fd-one-box li',
                    slidePre:'#slide .pre',
                    slideNext:'#slide .next',
                    slidePselect:'active'
                },options);
                //定义变量
                var aPage = $(options.slidePage);
                var aCon = $(options.slideNum);
                var len = $(options.slideNum).length;
                var iSize = aCon.size();
                var iNow = 0;
                var timer = null;
                aPage.each(function(index){
                    $(this).mouseover(function(){
                        iNow = index;
                        slideGo()
                    })
                })
                function slideGo(){
                    aPage.removeClass(options.slidePselect);
                    aPage.eq(iNow).addClass(options.slidePselect);
                    aCon.stop();
                    aCon.find('b').stop();
                    aCon.eq(iNow).siblings().animate({
                        'z-index':'1',
                        opacity: 0
                    },600).find('b').animate({
                        'z-index':'1',
                        opacity: 0,
                        top:-40
                    },600);
                    aCon.eq(iNow).animate({
                        'z-index':'3',
                        opacity: 1
                    },600).find('b').animate({
                        'z-index':'3',
                        opacity: 1,
                        top:-10
                    },600);
                }

                $(this).hover(function(){
                    $(options.slidePre).stop().toggle();
                    $(options.slideNext).stop().toggle();
                    //$(this).find('span').toggle();
                });

                $(options.slidePre).click(function () {
                    iNow -= 1;
                    if (iNow == -1) { iNow = len - 1; }
                    slideGo(iNow);
                });
                $(options.slideNext).click(function () {
                        iNow += 1;
                        if (iNow == len) { iNow = 0; }
                        slideGo(iNow);
                });
                //自动播放
                autoRun();
                function autoRun(){
                    timer = setInterval(function(){
                        iNow++;
                        if(iNow>iSize-1) iNow=0;
                        slideGo();
                    },4000)
                };
                //鼠标停顿在图片上自动播放停止
                $(this).hover(function(){
                    clearInterval(timer);
                },function(){
                    autoRun();
                })
                return this;  //返回this，使方法可链。
            }
        });
})(jQuery);