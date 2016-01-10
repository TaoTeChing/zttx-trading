/// <reference path="../jquery.min.js" />
seajs.use(["popup"], function (Popup) {

    var mypopup = new Popup({
        trigger: ".main-nav li",
        element:".nav-child",
        //effect:"slide",
        delay: 382
    }).after("show",function(){
            $(this.element[0]).css({left:0});
            var trigger = this.activeTrigger[0];
            var _index = $(".main-nav li").index(trigger);
            var content = $(".nav-child").find("p").hide().eq(_index).show();
            if (!$(content).children().length > 0) {
                this.hide();
            }
    });
});


$(".header").hover(function () { }, function () { $(this).find(".nav-child").slideUp(200); });

if (!$.browser.msie6() && !$.browser.msie7()) {
    seajs.use(["sticky"], function (Sticky) {
        Sticky.fix(".header");
    });

}

function setAlpha(obj, num) {
    if (!num) { $(obj).last().addClass('last'); }

    $(obj).each(function (i, o) {
        if ((i) % num == 0) {
            $(o).addClass("alpha");
        }
    });
}

if(window.console) {
    console.log("一张网页，要经历怎样的过程，才能抵达用户面前？");
    console.log("一位新人，要经历怎样的成长，才能站在技术之巅？");
    console.log("探寻这里的秘密；");
    console.log("体验这里的挑战；");
    console.log("成为这里的主人；");
    console.log("你，可以影响世界。");
    console.log("请将简历发送至  hr@8637.com（ 邮件标题请以“姓名-应聘XX职位-来自console”命名）");
    console.log("职位介绍：http://8637.com/about/joinus.jsp(待改)");
}