//导航下拉菜单
$(document).ready(function () {
    $(".nav-center > ul > li").mouseenter(function () {
        $(this).find('.dropmenu').stop(false, true).show();
        $(this).siblings().removeClass('hover');
        $(this).find('.alink-bg').addClass('hover');
    }).mouseleave(function () {
        $(this).find('.dropmenu').stop(false, true).slideUp();
        $(this).find('.alink-bg').removeClass('hover');
    });

    //浏览收藏下拉菜单
    $(".collect-glance > ul > li").mouseenter(function () {
        $(this).find('.dropdown').stop(false, true).show();
        $(this).find('.b-san').stop(false, true).show();
        $(this).find('.collect').css('background', '#000');
    }).mouseleave(function () {
        $(this).find('.dropdown').stop(false, true).hide();
        $(this).find('.b-san').stop(false, true).hide();
        $(this).find('.collect').css('background', '#333');
    });

    //收藏单条删除、批量删除
    $(".collect-glance li dl").hover(function(){
        var _html = '<a class="js-collect-delete" href="javascript:;" style="color: #333;position:absolute; right: 0; top: 0; padding:0 5px;">X</a>';
        $(this).append(_html);
    },function(){
        $(this).find(".js-collect-delete").remove();
    });

    $(".collect-glance").on("click",".js-collect-delete",function(){
        $(this).parents("dl").remove();
    })

    $(".js-collect-clearcah").click(function(){
        $(this).parents(".dropdown").children("dl").remove();
    })

    //焦点图下面的logo特效
    $(".fd-one .show a").hover(function () {
        $(this).find('#tanchu').stop().animate({
            bottom: '0px'
        }, 300);
        $(this).find('img').stop().animate({
            bottom: '5px'
        }, 500);
    }, function () {
        $(this).find('#tanchu').stop().animate({
            bottom: '-21px'
        }, 300);
        $(this).find('img').stop().animate({
            bottom: '0px'
        }, 500);
    });

    //all-logo的特效
    $('#new-rz-logo a,#new-rz-logo1 a,#new-rz-logo2 a').hover(function () {
        $(this).find('span').stop().animate({
            left: "2px"
        },200);
    }, function () {
        $(this).find('span').stop().animate({
            left: "-90px"
        }, 200);
    });

    //聚光灯特效
    $(".fload-all .fd-two .dress-pp a").hover(function () {
        $(this).siblings().addClass("opacity-bg");
    }, function () {
        $(this).siblings().removeClass("opacity-bg");
    });


    //输入框默认背景提示
    $('[deftxt]').css({ color: '#bbb' }).focus(function () {
        if ($(this).val() == $(this).attr("deftxt")) {
            $(this).val("").css({ color: '#666' });
        }
    }).blur(function () {
        if ($(this).val() == '') {
            $(this).val($(this).attr("deftxt")).css({ color: '#bbb' });
        }
    });

    //首页国际品牌
    $(".brand-inter-abCon a img").hover(function(){//国际品牌
        $(".brand-inter-abCon a img").stop().animate({
            opacity:0.6
        });
        $(this).stop().animate({
            opacity:1
        });
    },function(){
        $(".brand-inter-abCon a img").stop().animate({
            opacity:1
        });
    });
    //分类右侧
    $(".brand-classfiy-common a").hover(function(){
        var _this = $(this);
        var Wid = _this.find("img").width() - 8;
        var Hei = _this.find("img").height() - 8;
        var html =  '<div class="js-just-ab" style="width:'+ Wid + 'px;height:' + Hei + 'px;border:4px solid #d2d2d2;position: absolute; top:0px;left:0px;"></div>';
        _this.append(html);
    },function(){
        $(this).find(".js-just-ab").remove();
    });
    //最新入驻品牌、你可能感兴趣的品牌、热门加盟品牌
    $(".brand-latest-items img,.brand-mayhot-common img").hover(function(){
        $(this).stop().animate({
            opacity:0.6
        })
    },function(){
        $(this).stop().animate({
            opacity:1
        })
    });
    //让图片在一个容器里面垂直居中
    $(".js_img_center,.js-img-center").each(function () {
        $("<span></span>").appendTo($(this));
    });
    $(function () {//首页大焦点图
        var aSlidePage = $('#slide .slide-nav');
        var aSlideCon = $('#slide .slide-box li');
        var iSize = aSlideCon.size();
        var iNow = 0;
        var timer = null;
        var btn = "<div class='slide-nav-box'>";
        for (var i = 0; i < iSize; i++) {
            btn += "<a href='javascript:;'></a>";
        }
        btn += "</div>";
        aSlidePage.append(btn);
        aSlidePage.find("a:eq(0)").addClass("active");
        aSlidePage.on("click","a",function () {
            var index = $(this).index();
            iNow = index;
            slideRun();
        });
        function slideRun() {
            aSlidePage.find("a").removeClass('active');
            aSlidePage.find("a").eq(iNow).addClass('active');
            aSlideCon.stop();
            aSlideCon.find('b').stop();
            aSlideCon.eq(iNow).siblings().animate({
                'z-index': '1',
                opacity: 0
            }, 600).find('b').animate({
                'z-index': '1',
                opacity: 0,
                top: -40
            }, 600);
            aSlideCon.eq(iNow).animate({
                'z-index': '3',
                opacity: 1
            }, 600).find('b').animate({
                'z-index': '3',
                opacity: 1,
                top: -10
            }, 600);
        }
        $('#slide').hover(function(){
            clearInterval(timer);
        },function(){
            timer = setInterval(function () {
                iNow++;
                if (iNow > iSize - 1) iNow = 0;
                slideRun();
            },5000)
        });
    });
});