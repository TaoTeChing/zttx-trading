/**
 * Created by txxb on 2014/5/28.
 */
$(function(){
    //判断浏览器
    $.browser = {
        mozilla: function () {
            return /firefox/.test(navigator.userAgent.toLowerCase());
        },
        webkit: function () {
            return /webkit/.test(navigator.userAgent.toLowerCase());
        },
        opera: function () {
            return /opera/.test(navigator.userAgent.toLowerCase());
        },
        msie: function () {
            return /msie/.test(navigator.userAgent.toLowerCase());
        },
        msie6: function () {
            return /msie 6\.0/i.test(navigator.userAgent.toLowerCase());
        },
        msie7: function () {
            return /msie 7\.0/i.test(navigator.userAgent.toLowerCase());
        },
        //竺显斌 2014-04-22
        msie8: function () {
            return /msie 8\.0/i.test(navigator.userAgent.toLowerCase());
        }
        //竺显斌 2014-04-22
    };

    //如果是IE6就加载png24的js文件
    if ($.browser.msie6()) {
        $.getScript("/scripts/DD_belatedPNG.js", function () {
            DD_belatedPNG.fix("*");
        })
    }
});
