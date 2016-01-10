$(function () {

    //品牌推荐切换
    var m3 = $(".main-three-items");
    var lineb = $(".line b");
    var m3d = [{ width: 190, left: 0 }, { width: 190, left: 198 }, { width: 190, left: 396 }, { width: 190, left: 594 }, { width: 190, left: 792 }, { width: 190, left: 990 }];
    var items = $(m3).find("li").hover(function () {
        //$(items).not(this).find("dl").css({ opacity: 0.7 });
        $(items).not(this).css({ opacity: 0.7 });

        var items_i = $(items).index(this);

        $(lineb).show().stop().animate({ "margin-left": m3d[items_i].left, width: m3d[items_i].width }, 400);

    }, function () {
        //$(items).find("dl").css({ opacity: 1 });
        $(items).css({ opacity: 1 });
        $(lineb).hide();
        //$(old).animate({opacity: 0.5},300);
    });

});

