
if (!$.browser.msie6() && !$.browser.msie7() && !$.browser.msie8()) {
    var _innerHeight = $(window).innerHeight();//获取窗口高度

    seajs.use(["$", "/styles/about/animate.css"], function ($) {
        $(window).scroll(function () {
            //当前区域
            var region = $(this).scrollTop() + _innerHeight * 0.8;
            $('[data-animated]').each(function (i, o) {
                if ($(o).offset().top < region) {
                    $(o).addClass("animated " + $(o).data("animated"));
                }
                else {
                    $(o).removeClass("animated " + $(o).data("animated"));
                }
            });

            if ($(this).scrollTop() > 116) {
                $(".header").addClass("mini");
                $(".scrolltop").show();
            }
            else {
                $(".header").removeClass("mini");
                $(".scrolltop").hide();
            }

        });
    });
}

if ($.browser.msie6()) {
    $(window).scroll(function () {
        $(".scrolltop").css({ top: $(window).scrollTop() + 150 });
    });
}

$(".scrolltop a").click(function () {
    $('html,body').animate({ scrollTop: $(this.hash).offset().top - 116 }, 618);
    return false;
});

//$(".scrolltop a.top").hover(function () {
//    $(this).addClass("bounce");
//});

$('[data-run]').each(function (i, o) {
    var min = Number($(o).data("min"));
    var max = Number($(o).data("max"));
    var time = window.setInterval(function () {
        if (min < max) {
            min++;
            $(o).text(min);
        }
        else {
            clearInterval(time);
        }
    }, 2);
});


setAlpha(".bland-list li", 12);

$("#about-content-1 .nav a").hover(function () {
    $(this).stop().animate({ height: 200, width: 200 }, 300);
}, function () {
    $(this).stop().animate({ height: 180, width: 180 }, 300);
});

$("#about-content-3 .bland-list a img").hover(function () {
    $(this).stop().animate({ width: 90, height: 90, "margin-top": -20, "margin-left": -5 }, 300);
}, function () {
    $(this).stop().animate({ width: 80, height: 80, "margin-top": -15, "margin-left": 0 }, 300);
});

$(".banner.right").hover(function(){
    $(this).stop().animate({width:'60%'});
    //$(this)
},function(){
    $(this).stop().animate({width:'50%'});
});