//放全网站的通用js
var zttx = {
    SUCCESS: 126000, // ajax 成功代码
    VALIDERR: 126047, // 表单提交验证错误
    NOT_LOGIN: 126013 // 未登录,
};
//jquery的ajax基础配置
/*
$.ajaxSetup({
    data: {"s": 2}
})
*/
/**
 * 安全的使用 console.log 和 console.error
 */
if (typeof window.console == 'undefined') {
    window.console = {};
    $.each(['log', 'error'], function (i, v) {
        if (typeof window.console[v] == 'undefined') {
            window.console[v] = function () { };
        }
    })
}
//解析
$.unparam = function (url) {

    var vars = {}, hash, i,
        urlParams = url.indexOf('?') > -1 ? url.split('?')[1] : url
    ;
    if(urlParams === url){
        return {};
    }
    var hashes = urlParams.split('&');
    for (i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars[hash[0]] = decodeURIComponent(hash[1]).replace(/\+/g, ' ');
    }
    return vars;
};

//select
$.fn.SelectBox = function () {
    //新版本
    /*
    return this.each(function () {

        var _this = this;

        var height = $(this).data("height") || 30;

        seajs.use(["select"],function(Select){
            var s = new Select({
                trigger: $(_this),
                width: $(_this).outerWidth(),
                triggerTpl: '<div class="common_select_box"><span class="fn-text-overflow" data-role="trigger-content"></span><i class="arrow"></i></div>',
                maxHeight: 200
            }).before("render",function(){
                    var trigger = this.get('trigger');
                    trigger.css("height",height);
                    trigger.css("line-height",height+"px");
                    //处理小箭头的位置，3为箭头的高度的一半，就相当于top:50%-(xx的一半)
                    trigger.find(".arrow").css("top",height/2-3);
                    trigger.find("span").css("width",$(_this).outerWidth()-20);
                    $(_this).data("select",s);
                });

            s.on("change",function(){
                $(_this).trigger("change");
            });

            s.render();

        });

    });
    */
    //老版本
    return this.each(function () {
        if ($.browser.msie6()) {
            return;
        }

        var className = "";

        var oparent = $(this).parent();

        if (oparent.find("select").length > 1) {
            $(this).wrap('<div class="inline-block"></div>');
        }

        oparent = $(this).parent();

        if (!oparent.hasClass("pr")) {
            oparent.addClass("pr");
        }


        $(this).css({
            "opacity": 0,
            "position": "absolute",
            "left": 0,
            "top": 0,
            "filter": "alpha(opacity=0)"
        })

        var str = "";

        var name_val = $(this).attr("name");
        var id_val = $(this).attr("id");

        if (name_val) {
            str = name_val;
        } else {
            if (id_val) {
                str = id_val;
            } else {
                console.error("没找到select的name或者ID值");
                return;
            }
        }

        $('<div id="' + str + '_div" class="common_select_box"><span class="fn-text-overflow">' + $(this).find("option:selected").text() + '</span><i class="arrow"></i></div>').insertBefore($(this));

        //处理小箭头的位置，3为箭头的高度的一半，就相当于top:50%-(xx的一半)
        $("#" + str + "_div .arrow").css("top", $(this).outerHeight() / 2 - 3);


        $("#" + str + "_div").css({
            "width": $(this).outerWidth() - 2,
            "height": $(this).outerHeight() - 2,
            "line-height": $(this).outerHeight() - 2 + "px"
        });

        $("#" + str + "_div span").css("width", $(this).outerWidth() - 20);

        $(this).on("change", function () {
            $("#" + str + "_div span").html($(this).find("option:selected").text());
        })

    });
}

/**
 *
 * @param select css选择器
 */
function renderSelect(select) {
    if ($.type(select) == "string") {
        var name = $(select).attr("name");
        $("#" + name + "_div span").html($(select).find("option:selected").text());
    }

    if ($.type(select) == "array") {
        $.each(select, function (i, ele) {
            var name = $(ele).attr("name");
            $("#" + name + "_div span").html($(ele).find("option:selected").text());
        })
    }

    var name = $(select).attr("name");
    $("#" + name + "_div span").html($(select).find("option:selected").text());
}

//校验price
$.fn.isPrice = function (flag) {
    return this.each(function () {

        //$(this).on("keyup keydown input", function () {//会出现选中全部输入在后面追加，而不是覆盖的情况
        $(this).on("keyup change", function () {
            //如果只是数字
            if (flag == false) {
                $(this).val($(this).val().replace(/[^\d]/g, ''));
                return;
            }else{

                var val = $(this).val();

                val = val.replace(/[^\d.]/g,"");                                        //第一步，排除非数字和“.”
                val = val.replace(/^\./g,"");                                           //第二步，排除“.”开头
                val = val.replace(/\.{2,}/g,".");                                       //第三步，排除多个“.”在一起的情况
                val = val.replace(".","$#$").replace(/\./g,"").replace("$#$",".");    //第四步，排除多个“.”不在一起的情况

                var str = val.split(".");
                if(str[1]){
                    val =  str[0].toString() + "." + str[1].toString().substring(0,2);      //第五步排除“.”后超过两位的情况
                }

                $(this).val(val);

                //干掉非数字
                //$(this).val($(this).val().replace(/[^0-9+.?0-9]/g, ''));
                //处理折扣之类的
                var min = parseFloat($(this).data("min")),
                    max = parseFloat($(this).data("max"));

                var value = parseFloat($(this).val());

                if (min && max) {
                    min = Math.min(min, max);
                    max = Math.max(min, max);
                }
                if (max) {
                    value > max ? $(this).val(max) : "";
                }
                if (min) {
                    value < min ? $(this).val(min) : "";
                }

            }
        });
    });
}
//倒计时
$.fn.cutDown = function (format, onEnd, onBefore) {

    var getDHMS = function (s) {
        var d = parseInt(s / 86400);//秒数除以（3600*24） 则可以取到天数
        s %= 86400;//取余数
        var h = parseInt(s / 3600);
        s %= 3600;
        var m = parseInt(s / 60);
        s %= 60;
        var s = parseInt(s);
        return { d: d, h: h, m: m, s: s };
    };

    if (!format) {
        format = function (dhms) {
            return (dhms.d * 24 + dhms.h) + ':' + dhms.m + ':' + dhms.s;
        };
    }

    this.each(function () {
        var $that = $(this);
        var start = +new Date();
        var end = $that.data('endtime');

        if (end <= start) {

            if (typeof onBefore === 'function') {
                onBefore();
            }

            return;
        }

        var interval = setInterval(function () {

            var remain = end - new Date();

            if (remain <= 0) {
                remain = 0;
                clearInterval(interval);

                if (typeof onEnd === 'function') {
                    onEnd();
                }
            }

            $that.html(format(getDHMS(remain / 1000), remain));

        }, 900);
    });
    return this;
};
//判断浏览器
/*if(!$.browser) {
    $.browser = {
        *//**
         * 简单的浏览器特性判断(欢迎补充)
         *//*
        runBrowser: function () {
            var me = this;
            var html = document.getElementsByTagName("html")[0];
            var userAgent = navigator.userAgent.toLowerCase();
            //判断浏览器版本
            var browser = [
                {core: "chrome", is: /chrome/.test(userAgent)},
                {core: "mozilla", is: /firefox/.test(userAgent)},
                {core: "webkit", is: /webkit/.test(userAgent)},
                {core: "opera", is: /opera/.test(userAgent)},
                {core: "msie", is: /msie/.test(userAgent)},
                {core: "msie6", is: /msie 6\.0/i.test(userAgent)},
                {core: "msie7", is: /msie 7\.0/i.test(userAgent)},
                {core: "msie8", is: /msie 8\.0/i.test(userAgent)}//,
                //{core:"msie9"   ,is:/msie 9\.0/i.test(userAgent)}
            ];

            for (var i in browser) {
                //给浏览器增加特定样式
                if (browser[i].is) {
                    html.className += browser[i].core + " ";
                }
                //给$增加脚本判断;

                if(/msie 7\.0/i.test(userAgent)||/msie 6\.0/i.test(userAgent))
                {
                    eval("me." + browser[i].core + " = function () { return "+browser[i].is+" }");
                }
                else{
                    me[browser[i].core] = function () {
                        return browser[i].is;
                    }
                }
            }

            //监视浏览器分辨率
        }
    };
    $.browser.runBrowser();
}*/
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

//简单的table隔行变色
function initTable() {
    $("table tbody tr:odd").addClass("odd");
    //竺显斌 2014-05-05
    $(".ui-table tbody tr:odd").addClass("ui-table-split");
    //竺显斌 2014-05-05
}


//获取验证码的函数
function getCodeFn(obj, num) {

    $(obj).attr("disabled", "disabled");
    var num = typeof num == "undefined" ? 60 : parseInt(num);
    function cutDown() {
        if (num == 0) {
            clearInterval(getCodeFn.timer);
            $(obj).html("获取验证码");
            $(obj).removeAttr("disabled");
            return;
        }
        $(obj).html("倒计时（" + num + "）");
        num--;
    }
    cutDown();
    getCodeFn.timer = setInterval(function () {
        cutDown();
    }, 1000);

}

//弹出提示信息
function remind(type, message, timeout, fn) {
    if ($.isFunction(timeout)) {
        fn = timeout;
        timeout = 0;
    }

    timeout = timeout || 1600;

    remindfn(type, message, timeout, fn);
}

function remindfn(type, message, timeout, fn) {
    seajs.use(["underscore", "dialog", "template"], function (_, Dialog, template) {
        //var types = 'simple,error,warning,success,tip,loading';
        var types = 'success,error';
        if (_.indexOf(types.split(','), type) == -1) {
            return;
        }
        var format = '<div style="border: #bababa solid 1px;background: #fff;box-shadow: 0 0 3px rgba(0,0,0,.4);">'+
                        '<div style="font-size: 18px;line-height: 42px;padding: 10px 15px;">'+
                            '<i style="display: inline-block;height: 42px;margin-right: 10px;vertical-align: middle;width: 42px;{{style}}"></i>'+
                            '<span>{{message}}</span>'+
                        '</div>'+
                      '</div>';

        var render = template.compile(format);
        var html = render({
            "type": type,
            "message": message,
            "style": (function () {
                var str = ""
                if (type == "success") {
                    str = "background: url(" + window.IMAGE_DOMIAN+ "/images/common/remind_icon.png) 0 0 no-repeat;";
                } else if (type == "error") {
                    str = "background: url(" + window.IMAGE_DOMIAN+ "/images/common/remind_icon.png) 0 -50px no-repeat;";
                }
                return str;
            }())
        });
        var d = new Dialog({
            "content": html,
            "hasMask": false,
            "classPrefix": "remind-dialog",
            "closeTpl": "",
            "width": "auto",
            "zIndex": 1001
        });
        d.after('show', function () {
            setTimeout(function () {
                d.destroy();
                if ($.isFunction(fn)) {
                    fn()
                }
            }, timeout);
        });
        d.show();
    });
}

//确认弹出框
function confirmDialog(str, fn, isClose, beforeFn,isCancel) {
    var d = null;

    var isClose = isClose || "true";

    var title = "提示", content = "";

    if ($.type(str) == "string") {
        content = str;
    }

    if ($.type(str) == "object") {
        title = str.title;
        content = str.content;
    }

    seajs.use(["dialog"], function (Dialog) {
    	
    	var isCancelStyle = isCancel ? "display: none" : "";  
    	
        d = new Dialog({
            "content": '<div id="confirmDialog"><div class="ui-head"><h3>' + title + '</h3></div><div class="confirm_bd"><div class="ta-c mt20 fz14" style="padding: 0 1em;">' + content + '</div><div class="operate"><a class="simple_button confirm_btn" href="javascript:;">确定</a><a class="simple_button cancel_btn" style="'+isCancelStyle+'" href="javascript:;">取消</a></div></div></div>',
            "width": 256
        });
        d.before("show", function () {
            if ($.isFunction(beforeFn)) {
                beforeFn(d);
            }
        });
        d.after("show", function () {
            $("#confirmDialog .confirm_btn").on("click", function (ev) {
                ev.preventDefault();
                if (isClose == "true") {
                    d.hide();
                }
                if ($.isFunction(fn)) {
                    fn(d);
                }
            });
        })
        d.show();
    });

    $(document).on("click", "#confirmDialog .cancel_btn", function () {
        d.hide();
    });

}

//form表单数据重渲染
function setErrMsg(form, json) {

    $.each(json, function (i, item) {

        //$("[name='" + json[0].errName+"']",form)
        var elem_obj = null;

        if($("#"+item.errName).length > 0){
            elem_obj = $("#"+item.errName,form);
        }else{
            elem_obj = $("[name='" + item.errName+"']",form);
        }

        var op = elem_obj.parents(".ui-form-item");

        op.addClass("ui-form-item-error");

        var explain = $(".ui-form-explain", op);

        if (explain.length == 0) {

            $('<div class="ui-form-explain">' + item.errMsg + '</div>').appendTo(op);

        } else {
            explain.html(item.errMsg);
        }

        
        elem_obj.attr("data-explain", "");

    });

    var first_obj = null;

    if($("#"+json[0].errName).length > 0){
        first_obj = $("#"+json[0].errName,form);
    }else{
        first_obj = $("[name='" + json[0].errName+"']",form);
    }

    var top_value = first_obj.parents(".ui-form-item") && first_obj.parents(".ui-form-item").offset().top;

    $(document).scrollTop(top_value);

}

//创建editor
function createEditor(id, width, height) {
    seajs.use(["umeditor", "umeditor_config", "umdeitor_style"], function () {
        UM.getEditor(id, {
            initialFrameWidth: width || 300,
            initialFrameHeight: height || 300
        });
    })
}

//form base版
//目前假定form中最多只有一个editor
//[selector,isAjax,editor[display,id,width,height],beforeSubmitFn,addElemFn]
function baseFormValidator(json) {
    // selector,isAjax,fn,editor
    var setting = {
        "isAjax": false
    }

    var editor = {};
    var config = $.extend(setting, json);

    seajs.use(["validator", "widget", "umeditor", "umeditor_config", "umdeitor_style"], function (Validator, Widget) {

        var flag = !!config.editor;

        //处理编辑器
        if (flag) {

            editor = UM.getEditor(config.editor.id, {
                initialFrameWidth: config.editor.width || 300,
                initialFrameHeight: config.editor.height || 300
            });

            $("#" + config.editor.id).parents(".ui-form-item").append("<input id='editor_hidden' type='hidden' value='123' />");

            Validator.addRule('editor', function () {
                return editor.hasContents();
            }, '请输入{{display}}');

        }

        Widget.autoRenderAll();

        var Core = Validator.query(config.selector);

        if (config.isAjax) {
            Core.set("autoSubmit", false);
        }

        if (flag) {
            Core.addItem({
                element: '#editor_hidden',
                rule: "editor",
                display: config.editor.display || "内容"
            })
        }

        if ($.isFunction(config.addElemFn)) {
            config.addElemFn(Core, Validator);
        }

        Core.on('formValidated', function (error) {
            if (!error) {
                if ($.isFunction(config.beforeSubmitFn)) {
                    config.beforeSubmitFn();
                }
            }
        });
    });
}

//calendar
function baseCalendar(elem, json, flag) {
    seajs.use(["my97DatePicker"], function () {
        $(elem).on("click", function () {

            if (typeof flag != "undefined") {

                var newjson = $.extend(json || {}, { minDate: '%y-%M-%d' });

                WdatePicker(newjson);

            } else {
                WdatePicker(json || {});
            }

        })
    });
}

//应用场景：起始日期，截止日期
/**
 *
 * @param start 起始的input ID
 * @param end  截止的input ID
 * @param flag  是否从今天开始
 * @param addClass  类型object 需要给input加一个类
 */
function rangeCalendar(start, end, flag,addClass) {
    if(addClass){
        $("#" + start).add("#"+end).addClass(addClass.name || "hasDatepicker");
    }

    seajs.use(["my97DatePicker"], function () {
        $("#" + start).on("focus", function () {
            if (typeof flag != "undefined") {
                WdatePicker({ maxDate: '#F{$dp.$D(\'' + end + '\')}', minDate: '%y-%M-%d' });
            } else {
                WdatePicker({ maxDate: '#F{$dp.$D(\'' + end + '\')}' });
            }
        })

        $("#" + end).on("focus", function () {
            WdatePicker({ minDate: '#F{$dp.$D(\'' + start + '\')}' });
        })
    });
}

//重置错误信息（让错误信息隐藏）
function resetValidatorError(form) {
    var form = !form ? "" : form;
    $(form + " .ui-form-item").removeClass("ui-form-item-error");
    $(form + " .ui-form-explain").html("");
}

/**
 * 字符串截取
 * @param origText
 * @param length
 * @param ellipsisStr
 */
function trimLongString(origText, len, ellipsisStr) {
    var newText = "";
    var ellipsis = ellipsisStr || "...";
    if ($.type(origText) == "string") {
        newText = origText;
    }
    if (len > 0 && len < newText.length) {
        newText = newText.slice(0, len) + ellipsis;
    }

    return newText;
}

/**
 * 复制表单隐匿域
 * @param str
 * @param selector
 */
function createFormHiddenFn(str,selector,editor){
	
	var html = "";
	
	var arr = str.split("&");
	
	for(var i = 0;i<arr.length;i++){
		var s = arr[i].split("=");
        if(s[0] != editor.name){
            html += '<input type="hidden" name="'+s[0]+'" value="'+decodeURIComponent(s[1])+'" />';
        }
	}
	
	
	if(editor){
		html += '<textarea name="'+editor.name+'">'+$(editor.selector).val()+'</textarea>';
	}

    //console.log(html);

	$(selector).html(html);
	
	
	
}

function getImageDomainPath(url, width, height){
    //E3CB136D491B4B60AC2B865DA0899E7A.jpg_100x100.jpg
    var arr = url.split(".");
    var suffix = arr[arr.length-1];
    if(width && height){
        return url+"_"+parseInt(width)+"x"+parseInt(height)+"."+suffix;
    }else{
        return url;
    }
}
/**
 * 或者字符的实际字符数
 * @param str
 */
function getCharLength(str){
    var str = str.replace(/[^\x00-\xff]/g, '**');
    return str.length;
}
/**
 * 针对IE下的placeholder
 * @param elem
 */
function placeholder(elem){
    var supportPlaceholder = "placeholder" in document.createElement("input");
    if(supportPlaceholder){
        return;
    }
    var $elem = $(elem);
    if($elem.is(":hidden")){
        return;
    }
    var flag = $elem.attr("type") == "text" || $elem.prop("tagName").toLowerCase() == "textarea";
    var text = $elem.attr("placeholder");
    var defaultValue = $elem.val();
    if(flag && text){
        var pl = $elem.css("padding-left");
        if(!$elem.data("label")){
            var label = $('<label>'+text+'</label>');
            label.css({
                position: "absolute",
                left: $elem.offset().left+parseInt(pl),
                top: $elem.offset().top,
                width: $elem.outerWidth(),
                height: $elem.outerHeight(),
                color: "#999",
                display: "none",
                cursor: "text"
            })
            label.on("click",function(){
                $(this).hide();
                $(elem).focus();
            })
            if($elem.attr("type") == "text"){
                label.css({
                    "line-height": $elem.outerHeight()+"px"
                })
            }
            label.appendTo($("body"));
            $elem.data("label",label);
        }
        if(defaultValue == ""){
            $(elem).data("label").show();
        }
        $elem.focus(function(){
            $(elem).data("label").hide();
        })
        $elem.blur(function(){
            if($elem.val() === ""){
                $(elem).data("label").show();
            }
        })
    }
}
//0614 zxb
function dialogLoading(afterFn,text){
    var str = text || "文件上传中..请稍后";
    seajs.use(["dialog"], function (Dialog) {
    	var  dd = new Dialog({
            "content": '<div style="padding:10px;background: #fff;border:1px solid #ddd;"><h3 style="line-height: 2;">'+str+'</h3><img src="' + window.IMAGE_DOMIAN + '/images/common/012.gif" /></div>',
            "classPrefix": "zttx-dialog",
            "closeTpl": "",
            "width": "auto"
        });
        dd.after("show", function () {
                if ($.isFunction(afterFn)) {
                    afterFn(this);
                }
        }).show();
        return dd;
    });
}

function dialogMask(afterFn){
    seajs.use(["dialog"], function (Dialog) {
        var  dd = new Dialog({
            "content": '<div></div>',
            "classPrefix": "zttx-dialog",
            "closeTpl": "",
            "width": "auto"
        });
        dd.after("show", function () {
            if ($.isFunction(afterFn)) {
                afterFn(this);
            }
            $(".ui-mask").css({"opacity": 0});
        }).show();
        return dd;
    });
}
//0614 zxb
/**
 * 判断是否是金额
 * @param str 具体的金额值
 * @returns {boolean}
 */
function isAmount(str){
    return /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(str);
}
/**
 * 判断是否是手机号码
 * @param num 输入的手机号码
 * @returns {boolean}
 */
function mobileNumReg(num){
    return /^(1[3-8]\d{9})$/.test(num);
}
/**
 * 字符串依","截取
 * @param str 用“,”隔开的字符串
 * @param n 需要留下的个数
 */
function trimAreaNum(str,n){
    n = n || 10;
    if($.type(str) != "array"){
        str = str.split(",");
    }
    if(str.length > n){
        str.splice(n,str.length-n);
        return str.join("、") + "...";
    }else{
        return str.join("、");
    }
}
/**
 * @param file 上传文件的input
 * @param arrow 允许上传文件的后缀
 * @returns {boolean}
 * */
function verifyFileSuffix(file, arrow){
    var arrowFile = arrow || "jpg,jpeg,png,gif,bmp,JPG,JPEG,PNG,GIF,BMP";
    var _path = file.value;
    var _fileArr = _path.replace(/(\\+)/g, "#").split("#");
    var _fileNameArr = _fileArr[_fileArr.length - 1].split(".");
    var _suffix = _fileNameArr[_fileNameArr.length - 1];
    var _result = arrowFile.indexOf(_suffix);
    if(_result >= 0){
        return true;
    }else{
        remind("error", "您选择的上传文件不是有效的图片文件！");
        return false;
    }
}
/**
 * 跳转到登录页面
 * */
function jumpToLogin(parames){
    parames = parames || "?redirect=" + encodeURIComponent(location.href);
    window.location.href = window.DOMIN + "/common/login" + (parames ? parames : "");
}

//头像上传
var uploadAvatar = {
    initPhoto:function(){
        var me = this;
        var res = IMAGE_DOMIAN;
        var ctx = DOMIN;
        $('#js-photo').bind('change', function() {
            uploadImage($(this).attr('id'));
        });

        function showImage(uploadId, url, oldName) {
            $('#js-photo-img').attr("src", res+url);
        }

        function uploadImage(uploadId) {
            dialogLoading(function (dialog) {
                seajs.use([ "$", "ajaxFileUpload" ], function($) {
                    $.ajaxFileUpload({url : ctx+'/common/showImg',secureuri : false,
                        fileElementId : uploadId,
                        dataType : 'json',
                        success : function(data) {
                            //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                            $('#' + uploadId).bind('change', function() {
                                uploadImage(uploadId);
                            });

                            if (data.code == 126000) {
                                updatePhoto(data.message,uploadId);
                            } else {
                                remind("error", data.message);
                            }
                            dialog.hide();
                        },error: function(data,status,e){
                            remind("error", "文件可能过大!"+status+" "+data+ " "+e);
                        }
                    });
                });
            });

        }

        function updatePhoto(userPhoto,uploadId) {
            $.post(ctx+"/dealer/updatePhoto", {userPhoto : userPhoto}, function(data) {
                if (data.code == 126000) {
                    showImage(uploadId, data.object,data.message );
                    remind("success", "成功修改头像");
                } else {
                    remind("error", data.message);
                }
            }, "json");
        }


    }
};
uploadAvatar.initPhoto();

