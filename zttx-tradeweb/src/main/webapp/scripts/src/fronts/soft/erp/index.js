/**
 * Created by yefeng on 2014/5/26.
 */
$(function(){

    //背景切换

    //鼠标滑过导航效果
    var nav = $(".nav .items");
    var cur = nav.find('.active a');
    var navLine = $(".nav .line");
    var anchor = $('a',nav.children());
    var curPosL = cur.position().left;
    var curW = cur.outerWidth(true);
    var curIdx = $('li.active',nav).index();

    navLine.css({'width':curW,'left':curPosL});
    anchor.not('li',nav).each(function(index){
        var posL = $(this).position().left,
            w = $(this).outerWidth(true);
        $(this).mouseenter(function(){
            navLine.stop().animate({'width':w,'left':posL},250);
            $(this).parent().addClass('active').siblings().removeClass('active');
        });
    });
    nav.mouseleave(function(){
        navLine.stop().animate({'width':curW,'left':curPosL},250);
        anchor.parent(':eq('+curIdx+')').addClass('active').siblings().removeClass('active');
    });
    //产品中心下拉

    $(".product-center").hover(function(){
        $(this).find('.panel').stop().slideDown();
    },function(){
        $(this).find('.panel').stop().slideUp();
    });


});