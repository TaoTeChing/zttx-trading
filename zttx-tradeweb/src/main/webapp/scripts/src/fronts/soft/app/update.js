/**
 * Created by yefeng on 2014/5/28.
 */
$(function(){
    $(".nav li").removeClass('active');
    $(".nav li:eq(2)").addClass('active');
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
    });

    //更新日志年切换
    $(".year").click(function(){
        var logContent = $(this).parent().find('.log-content');
        var bl = logContent.is(":hidden");
        if(bl){
            $(this).parents('.content-box').find('.log-content').hide();
            logContent.show();
            if($(this).parent().hasClass('last')){
                $(this).next().show();
            }else{
                $(this).parents('.content-box').find('.last .year-line').hide();
            }
        }
    });

    $(window).scroll(function(e) {
        var scrollTop = $(window).scrollTop();
        if (scrollTop > 80) {
            $(".header").addClass("fixed");
        } else {
            $(".header").removeClass("fixed");
        }
    });
});