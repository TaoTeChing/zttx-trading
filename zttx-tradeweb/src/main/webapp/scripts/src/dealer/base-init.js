
var dealerDefMenu = {
    addDefMenu:function(){
        var href = window.location.pathname;
        $("#menuForwardUrl").val(href);
        $.post("/dealer/defMenu/save", $("#logout-form").serialize(), function(data){
            if(data.code == zttx.SUCCESS)
            {
                var id = data.object.refrenceId;
                var tit = data.object.menuName;

                var html = '<li data-id="'+id+'">\
                                <a href="'+href+'" title="">'+tit+'</a>\
                                <b style="display: none;" class="yahei" title="删除该快捷导航">X</b>\
                            </li>';
                $(html).appendTo($(".quickmenu ul"));
                //remind("success", "快捷菜单添加成功");
                $("#add_cloud").fadeIn();
                setTimeout(function(){
                    $("#add_cloud").fadeOut();
                },3000);

            }else{
                remind("error",data.message);
            }
        }, "json");
    }
};

//seajs.use(["sticky"], function (sticky) {
//    sticky(".main>.main-left", 0);
//});


//快速导航
var qkm = $(".quickmenu");
$(".treetitle .quickbtn").hover(function () {
    if ($(qkm).find("li").length > 0)
    { $(qkm).show(); }
},function(){
    $(qkm).hide();
});


$("#add_cloud").on("click", ".close", function () {
    $(this).parent().hide();
});


//显示隐藏关闭按钮
qkm.on("mouseenter mouseleave", "li", function (ev) {

    var close = $(this).find("b");
    if (ev.type == "mouseenter") {
        close.show();
    } else if (ev.type == "mouseleave") {
        close.hide();
    }
});

//快速导航删除
qkm.on("click", "li b", function () {
    var $this = $(this);
    var refrenceId = $this.parents("li").data("id");
    $.post("/dealer/defMenu/del", { refrenceId: refrenceId }, function (data) {
        if (data.code == zttx.SUCCESS) {
            $this.parent().remove();
            //判断是否最后一个
            if ($(qkm).find("li").length == 0) { $(qkm).hide(); }
        } else {
            remind("error", data.message);
        }
    }, "json");
});

//提示框关闭操作
$(".alerts .alert b").click(function () { $(this).parent().slideUp(300, function () { $(this).remove(); }) });

//侧栏鼠标移入事件
$(".treepanel").on("click",".j_item .item_hd",function(){
    $(this).parent().siblings().removeClass("current_item");
    $(this).parent().toggleClass("current_item");
});
$(".treepanel .j_hover_item").hover(function(){
    $(this).siblings().removeClass("current_item");
    $(this).toggleClass("current_item");
});
$(".treepanel .accordion li.on").prev().addClass("nb");


//全选操作
//{a:控制元素,b:被操作元素集}
function checkAll(a, b, c) {
    $(document).on('click', a, function () {
        var me = this;
        $(b).each(function (i, o) {
            if (!o.disabled) {
                o.checked = me.checked;
            }
        });
        if($.isFunction(c)){c();}
    });
    $(document).on('click', b, function () {
        $(a)[0].checked = ($(b + ":checked").not(":disabled").length == $(b).length);
        if($.isFunction(c)){c();}
    });
}

//横向列表间距处理函数,但建议是外框margin-left负数
function setAlpha(obj, num) {
    if (!num) { $(obj).last().addClass('last'); }

    $(obj).each(function (i, o) {
        if ((i) % num == 0) {
            $(o).addClass("alpha");
        }
    });
}

//seajs.use(["placeholders"], function () { });
//$("input,textarea").each(function () {
//    ZTTX.placeholder(this);
//});

//处理select
$("select.js_select,select.js-select").SelectBox();

//处理价格
$(".js_price,.js-price").isPrice();

//处理数字
$(".js_number,.js-number").isPrice(false);

//让图片在一个容器里面垂直居中
$(".js_img_center,.js-img-center").each(function () {
    $("<span></span>").appendTo($(this));
});

initTable();

//$("#data1,#data2").baseCalendar();
$.fn.baseCalendar = function (o) {
    var me = this;
    var cals = [];
    seajs.importStyle(".ui-calendar-footer-item { float: left; display: inline; width: 50%; height: 22px; line-height: 22px; }.ui-calendar-footer-item:hover { background-color: #eee; cursor: pointer; }");
    seajs.use(["$", 'calendar', 'calendar_style'], function ($, Calendar) {
        Calendar.prototype.zPlugin = function () {
            var $this = this;
            var calelement = this.element;
            var calfoot = $(calelement).find(".ui-calendar-footer");
            if (calfoot.length == 0) {
                var $empty = $("<span>").addClass("ui-calendar-footer-item").text("清空");
                var $today = $("<span>").addClass("ui-calendar-footer-item").text("今天");
                var calfoot = $("<div class='ui-calendar-footer'>").append($empty).append($today);
                $(calelement).find(".ui-calendar-container").after(calfoot);
            };
            var $empty = calfoot.find(".ui-calendar-footer-item:eq(0)").text("清空");
            var $today = calfoot.find(".ui-calendar-footer-item:eq(1)").text("今天");
            $($empty).click(function () {
                $($this.attrs.trigger.value).val("");
                $this.hide();
                if(cals.length>0)
                {
                    for(var i in cals)
                    {
                        cals[i].range([null, null]);
                    }
                }
            });
            $($today).click(function () {
                $($this.attrs.trigger.value).val(new Date().getTime().toStringByMillsec());
                $this.hide();
            });

            return this;
        }

        cals[0] = new Calendar({
            trigger: me[0]
        }).zPlugin();
        if (me.length == 2) {
            cals[1] = new Calendar({
                trigger: me[1]
            }).on("selectDate", function (date) {
                var $today = $(cals[0].element).find(".ui-calendar-footer-item:eq(1)");
                if(date._d<new Date())
                {
                    $today.hide();
                }
                else{
                    $today.show();
                }
                    cals[0].range([null, date]);
            }).zPlugin();

            cals[0].on("selectDate", function (date) {
                var $today = $(cals[1].element).find(".ui-calendar-footer-item:eq(1)")
                if(date._d>new Date())
                {
                    $today.hide();
                }
                else{
                    $today.show();
                }
                cals[1].range([date, null]);
            })
        }
    });

    //"YYYY-MM-DD"
    Number.prototype.toStringByMillsec = function (format) {
        var _format = format || "YYYY-MM-DD";
        var me = this;
        var ret = "";
        seajs.use(["moment"], function (moment) {
            ret = moment(me).format(_format);
        });
        return ret;
    }
};

//开始时间 结束时间
//rangeCalendar('start-cal', 'end-cal');
$('#start-cal,#end-cal').baseCalendar();



(function ($) {
    $.fn.extend({
        numberPlugin: function (options,callback) {
            var defaults = {
                maxerror:"购买量太大",
                minerror:"商品没有达到起批量的要求",
                mouseRoller:true,
                keyRoller:true
            };

            var opts = $.extend(defaults, options);
            var me = $(this);
            var time = 0;
            $(me).each(function (i, o) {

                var minus = $('<a href="javascript:void(0)" class="minus disable">-</a>'); //$(o).find(".minus");
                var plus = $('<a href="javascript:void(0)" class="plus">+</a>'); //$(o).find(".plus");

                //数量增减
                var mynumberBox =  $("<div class='number-plugin'>").appendTo($(o).parent());
                mynumberBox.append(o).append(plus).prepend(minus);
                var max = Number($(o).data("max"));
                var min = $(o).data("min")?Number($(o).data("min")):0;
                var now = Number($(o).val());

                if($(o).has(":disabled").length==0){

                    if(opts.mouseRoller) {
                        $(o).numberRoller(function (val) {
                            o.value = val > 0 ? Number(o.value) + 1 : Number(o.value) - 1;
                            $(o).trigger("keyup");
                        });
                    }

                    $(minus).click(function (ev) {
                        ev.preventDefault();
                        now = now - 1;
                        changeVal();
                    });

                    $(plus).click(function (ev) {
                        ev.preventDefault();
                        now = now + 1;
                        changeVal();
                    });

                    $(o).keyup(function(e){
                        now = Number($(this).val());
                        changeVal();
                    });

                    if(opts.keyRoller)
                    {
                        $(o).keydown(function(e){
                            if(e.keyCode==39||e.keyCode==38)
                            {
                                $(plus).trigger("click");
                            }
                            else if(e.keyCode==40||e.keyCode==37)
                            {
                                $(minus).trigger("click");
                            }
                        });
                    }

                    function changeVal() {
                        if (now >= min) {
                            if ($(minus).hasClass("disable")) {
                                $(minus).removeClass("disable");
                            }
                        }
                        else {
                            $(minus).addClass("disable");
                            now = min;
                        }

                        if (now <= max) {
                            if ($(plus).hasClass("disable")) {
                                $(plus).removeClass("disable");
                            }
                        }
                        else {
                            $(plus).addClass("disable");
                            now = max;
                            remind("error",opts.maxerror,function(){});
                        }

                        $(o).val(now);

                        clearTimeout(time);
                        time = setTimeout(function(){
                           if(callback){
                               callback(now,$(o))
                           };
                        },382);
                    }
                }
            });
        },numberRoller:function(callback){
            $(this).focus(function(){
                if(this.addEventListener) this.addEventListener("DOMMouseScroll",getval,false);
                this.onmousewheel = getval;
            });

            function getval(e){
                if(e) {
                    e.preventDefault();
                    e = e || window.event;
                    callback((e.wheelDelta||-e.detail)>0?1:0);
                }
            }
        }
    })
})(jQuery);

//快速通道
$(".fast_content dd").click(function(){
    $(this).toggleClass("selected");
});

$(".fast_panel .button_cancel").click(function(){
    $(".treepanel .current_item").removeClass("current_item");
});
