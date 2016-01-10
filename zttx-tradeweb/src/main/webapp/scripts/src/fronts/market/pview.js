/**
 * Created by yefeng on 2014/5/16.
 */
$(function(){
    $.ajaxSetup({
       cache : true
    });
    if ($.browser.msie6() || $.browser.msie7()) {
        /*兼容ie6，7*/
        $.getScript("/scripts/market/masonry.min2.js",function(){
            //砖块排列配置
            $('.bricks').masonry({itemSelector:'.brick',columnWidth:230,isFitWidth:true});
        });
    }else{
        $.getScript("/scripts/market/masonry.min.js",function(){
            //砖块排列配置
            $('.bricks').masonry({itemSelector:'.brick',columnWidth:230,isFitWidth:true});
        });
    }

    /*信息滚动*/
    /*new zScroll({id:'userSlider',delayTime:2});*/

    var curImg = null;
    var scrollTop = 0;
    var preH = 0,
        preW = 0;
    /*点击显示大图*/
    $('.brick:not(.userActive)').click(function(){
        curImg = $(this);
        var src = $(this).attr("data-src");
        var newImg = new Image();
        newImg.onload = function(){
            var imgW = newImg.width,
            imgH = newImg.height,
            windowH = $(window).height(),
            windowW = $(window).width();
            preH = newImg.height;
            preW = newImg.width;
            if(windowH<imgH){
                imgH = newImg.height = windowH-20;
                imgW = newImg.width = (windowH*imgW/preH);
            }
            var left = parseInt(windowW-imgW)/2;
            var top = parseInt(windowH-imgH)/2;
            if($.browser.msie6()){
                scrollTop = $('html,body').scrollTop();
                $(".photo-modal").css({
                    height : parseInt(windowH+scrollTop)+"px"
                });
                $('html,body').css({
                    overflow : 'hidden'
                });
                $(".gamma-nav a").css({
                    top:parseInt(windowH-100)/2+scrollTop+'px'
                });
                $(".close-view").css({
                    top:parseInt(30+scrollTop)+'px'
                });
                $(newImg).css({
                    left : left+"px",
                    top:parseInt(top+scrollTop)+"px"
                });
            }else{
                $(newImg).css({
                    left : left+"px",
                    top : top+"px"
                });
            }
            $(".photo-modal,.view-content").show();
        }
        newImg.src=src;
        $(".view-content").append(newImg);
    });

    /*点击切换图片*/
    $(".gamma-nav a").click(function(){
        var node = null;
        if($(this).hasClass("gamma-prev")){
            node = curImg.prev();
            if(node.hasClass("userActive")){
                node = node.prev();
            }
        }else{
            node = curImg.next();
            if(node.hasClass("userActive")){
                node = node.next();
            }
        }
        if(node.length==0){
            return false;
        }
        var src = node.attr('data-src'),
            windowW = $(window).width(),
            windowH = $(window).height();

        $(".view-content img").fadeOut(300,function(){
            $(this).remove();
            var newImg = new Image();
            if($.browser.msie6()){
                newImg.onload = function(){
                    var imgW = newImg.width,
                        imgH = newImg.height;
                        preH = newImg.height;
                        preW = newImg.width;
                    if(windowH<imgH){
                        imgH = newImg.height = windowH-20;
                        imgW = newImg.width = (windowH*imgW/preH);
                    }
                    var left = parseInt(windowW-imgW)/2;
                    var top = parseInt(windowH-imgH)/2;
                    $(this).animate({
                        left : left+"px",
                        top : parseInt(top+scrollTop)+"px"
                    });
                }
            }else{
                newImg.onload = function(){
                    var imgW = newImg.width,
                        imgH = newImg.height;
                        preH = newImg.height;
                        preW = newImg.width;
                    if(windowH<imgH){
                        imgH = newImg.height = windowH-20;
                        imgW = newImg.width = (windowH*imgW/preH);
                    }
                    var left = parseInt(windowW-imgW)/2;
                    var top = parseInt(windowH-imgH)/2;
                    $(this).animate({
                        left : left+"px",
                        top : top+"px"
                    });
                }
            }
            newImg.src=src;
            $(".view-content").append(newImg);
        });
        curImg = node;
    });

    /*左右箭头快捷键*/
    $(document).keydown(function(event){
        if(!$(".view-content").is(":hidden")){
            switch(event.which){
                case 37:
                    $(".gamma-prev").click();
                    break;
                case 39:
                    $(".gamma-next").click();
                    break;
                case 27:
                    $(".close-view").click();
            }
        }
    });

    /*图片适应窗口*/

    $(window).resize(function(){
        if(!$(".view-content").is(":hidden")){
            var img = $(".view-content img");

            var wh = $(window).height(),
                ww = $(window).width();
            if(wh<preH){
                img.height(wh-20);
                img.width(wh*preW/preH);
            }else{
                img.height(preH);
                img.width(preW);
            }
            var imgH = img[0].height,
                imgW = img[0].width;
            img.stop().animate({
                left : parseInt(ww-imgW)/2+"px",
                top : parseInt(wh-imgH)/2+"px"
            })
        }
    });

    /*关闭视图*/
    $(".close-view").click(function(){
        $(".photo-modal,.view-content").hide();
        $(".view-content img").remove();
        if($.browser.msie6()){
            $('html,body').css({
                overflow : 'auto'
            });
        }
    });

    /*鼠标移入图片效果*/
    $(".brick").hover(function(){
        if(!$(this).hasClass("userActive")){
            $(this).addClass("hover");
        }
    },function(){
        $(this).removeClass("hover");
    });
});
