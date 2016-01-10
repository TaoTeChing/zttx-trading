/**
 * Created by yefeng on 2014/5/26.
 */
$(function(){
    $(".banner .info .tit,.banner .info .descr").css({'opacity':'0'}).animate({'opacity':'0'});
    /*banner动画*/
    var banner = function(){
        $(".phone2").animate({left:'145',opacity:'1'},800,function(){
            $(".saturn img").animate({width:'569',opacity:'1'},800,function(){
                $(".banner .info .tit").animate({left:'755',opacity:'1'},500,function(){
                    $(".banner .info .descr").animate({left:'755',opacity:'1'},500);
                }).fadeIn();
            });
        });

    };
    banner();
    $("#animate1").mouseover(function(){
        $(".phone2").animate({left: '250', top: '50', 'zIndex': '200'}, 1000);
        $(".phone2 img").animate({width: '275', height: '521'}, 1000);
        $(".phone1").animate({left: '145', top: '90', 'zIndex': '100'}, 1000);
        $(".phone1 img").animate({width: '224', height: '460'}, 1000);
    });

    $("#animate2").mouseover(function(){
        $(".phone1").animate({left:'250',top:'50','zIndex':'200'},1000);
        $(".phone1 img").animate({width:'261',height:'535'},1000);
        $(".phone2").animate({left:'145',top:'90','zIndex':'100'},1000);
        $(".phone2 img").animate({width:'242',height:'460'},1000);
    });
    /*
    $("#animate0").hover(function(){
            $(".phone1").animate({'left':'300'},500);
            $(".phone2").animate({'left':'80'},500);
    },function(){
            $(".phone1").animate({'left': '260'}, 500);
            $(".phone2").animate({'left': '145'}, 500);
    });
    */
    //热门推荐
    $(".recomment li").hover(function(){
        $(this).addClass("hover");
    },function(){
        $(this).removeClass("hover");
    })
});

seajs.use('dialog',function(Dialog){
    var mixDialog = new Dialog({
        trigger: ".dl-btn",
        width: "415px",
        height:"200px",
        content: $("#mixDialog"),
        effect: "fade"
    });

    var androidDialog = new Dialog({
        trigger: ".plat-android",
        width: "415px",
        height:"300px",
        content: $("#androidDialog"),
        effect: "fade"
    });

    var iphoneDialog = new Dialog({
        trigger: ".plat-iphone",
        width: "415px",
        height:"300px",
        content: $("#iphoneDialog"),
        effect: "fade"
    });

});