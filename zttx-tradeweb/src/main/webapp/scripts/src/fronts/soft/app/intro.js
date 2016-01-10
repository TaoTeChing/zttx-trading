/**
 * Created by yefeng on 2014/5/28.
 */
$(function(){
    $(".nav li").removeClass('active');
    $(".nav li:eq(1)").addClass('active');
    //功能介绍切换
    $(".tab-content .detail").click(function(){
        $(this).parents('.intro').find(".detail").removeClass("active");
        $(this).addClass('active');
        var src = $(this).data('src');
        $(this).parents('.intro').find('img').attr('src',src);
    });
    var t1,t2;

    var fn1 = function(){
        if($(".box1 .active").parent().next().length){
            var next = $(".box1 .active").parent().next().find('.detail');
            $(".box1 .detail").removeClass('active');
            next.addClass('active');
            var src = next.data('src');
        }else{
            $(".box1 .detail").removeClass('active');
            $(".box1 .detail:first").addClass('active');
            var src = $(".box1 .detail:first").data('src');
        }
        $(".box1 img").attr('src',src);
    }

    var fn2 = function(){
        if($(".box2 .active").parent().next().length){
            var next = $(".box2 .active").parent().next().find('.detail');
            $(".box2 .detail").removeClass('active');
            next.addClass('active');
            var src = next.data('src');
        }else{
            $(".box2 .detail").removeClass('active');
            $(".box2 .detail:first").addClass('active');
            var src = $(".box2 .detail:first").data('src');
        }
        $(".box2 img").attr('src',src);
    }

    t1 = setInterval(fn1,5000);

    //tab切换
    $(".tab-items li").click(function(){
        if($(this).hasClass('active')){
            return false;
        }
        $(".tab-items li").removeClass("active");
        $(this).addClass("active");
        var cls = $(this).data('cls');
        $(".tab-content .content-box").hide();
        $("."+cls).show();
        var curIdx = $('li.active',$(".tab-items")).index();
        if(curIdx){
            clearInterval(t1);
            t2 = setInterval(fn2,5000);
        }else{
            clearInterval(t2);
            t1 = setInterval(fn1,5000);
        }
    });
});
