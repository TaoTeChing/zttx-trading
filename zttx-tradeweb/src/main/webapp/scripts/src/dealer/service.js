//菜单滚动效果
var ml = $(".main-left");
var pt = $(".panel-tbar");
$(window).scroll(function () {
    var me = this;
    if ($(me).scrollTop() > 100) {

        !ml.hasClass("navFix") ? ml.addClass("navFix") : null;

        if ($(me).scrollTop() > 196) {
            !pt.hasClass("navFix") ? pt.addClass("navFix") : null;
        } else {
            pt.hasClass("navFix") ? pt.removeClass("navFix") : null;
        }
    }
    else {
        
        ml.hasClass("navFix") ? ml.removeClass("navFix") : null;
    }
})

$(function () {
    //=======搜索
    //输入框默认背景提示
    $('[deftxt]').css({ color: '#bbb' }).focus(function () {
        if ($(this).val() == $(this).attr("deftxt")) {
            $(this).val("").css({ color: '#666' });
        }
    }).blur(function () {
        if ($(this).val() == '') {
            $(this).val($(this).attr("deftxt")).css({ color: '#bbb' });
        }
    }).keyup(function () {
        var key = $(this).val();
        if (key.length > 0) {
            //异步获取搜索结果,json
            //$.get({url:"",function(ret){  }});
            //模拟数据
            var data = [{ id: 1001, text: "朵彩保暖内衣加厚", isAdd: false }, { id: 1001, text: "朵彩内衣", isAdd: false }, { id: 1001, text: "朵彩彩棉内衣", isAdd: false }, { id: 1001, text: "朵彩纯棉内衣", isAdd: true }];

            //搜索结果框鼠标移出事件
            var result = $(".search-result").html("<ul></ul>").show().hover(null, function () { $(this).hide() }); //<i class='close'>

            for (var i in data) {
                var _id = data[i].id;
                var _text = data[i].text.replace(key, "<b>" + key + "</b> ");
                var _isAdd = data[i].isAdd;
                var li = $("<li>").appendTo($(result).find("ul"));
                var div = $("<div>").appendTo(li);
                var a = $(_isAdd ? '<a href="javascript:void(0)" class="disable">已加入进货单</a>' : '<a href="javascript:void(0)">加入进货单</a>').appendTo(div);
                var span = $("<span>").html(_text).appendTo(div);
            }
            $(result).find("div").hover(function () {
                $(this).toggleClass("on");
            });
        }
        else {
            $(".search-result").hide();
        }
    });
    //===========


});