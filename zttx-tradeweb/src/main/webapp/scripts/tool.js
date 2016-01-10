define(function(require, exports, module){

    var _ = require("underscore");
    var Dialog = require("dialog");
    var template = require("template");
    /**
     * 安全的使用 console.log 和 console.error
     */
    if (typeof window.console == 'undefined') {
        window.console = {};
        $.each(['log', 'error','dir'], function (i, v) {
            if (typeof window.console[v] == 'undefined') {
                window.console[v] = function () { };
            }
        })
    }
    /**
     * ZTTX对象是一个工具类
    */
    var ZTTX = {
        SUCCESS: 126000, // ajax 成功代码
        VALIDERR: 126047, // 表单提交验证错误,
        /**
         * 将序列化的字符串转成对象
         * @param str  字符串
         * @returns {{}}
         */
        unparam: function(str){
            var vars = {}, hash, i,
                strParams = str.indexOf('?') > -1 ? str.split('?')[1] : str
                ;
            var hashes = strParams.split('&');
            for (i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars[hash[0]] = decodeURIComponent(hash[1]).replace(/\+/g, ' ');
            }
            return vars;
        },
        /**
         * 判断是否为IE浏览器
         * @returns {boolean}
         */
        isIE: function(){
            return /msie/.test(navigator.userAgent.toLowerCase());
        },
        /**
         * 判断是否为IE6
         * @returns {boolean}
         */
        isIE6: function(){
            return /msie 6\.0/i.test(navigator.userAgent.toLowerCase());
        },
        /**
         * ajax二次封装
         * @param config  object对象
         * url: 请求路径
         * method: 提交方式，默认为post
         * data: 提交数据
         * timeout：超时时间，默认为3000毫秒
         * type: button的类型，默认为null，可选值：submit，submit的话
         * form: 表单selector，如果type是submit，form这个参数必须要有
         * successFn: 成功的回调（判断条件是code值和126000相等）
         * errorFn: 错误的回调（值不等于126000）
         */
        ajax: function(config){

            var _this = this;

            var bSubmit = !!(config.type == "submit" && config.form);

            var sColor = "";

            if(bSubmit){
                var obutton = $("[type='submit']",config.form);
                sColor = obutton.css("backgroundColor")
                obutton.attr({
                    "disabled":"true"
                }).css({
                    "backgroundColor": "#ccc"
                });
            }

            function resetSubmit(){
                if(bSubmit){
                    $("[type='submit']",config.form).removeAttr("disabled").css({
                        "backgroundColor": sColor
                    });
                }
            }

            $.ajax({
                url: config.url,
                method: config.method || "post",
                data: config.data || {},
                dataType: "json",
                timeout: config.timeout || 3000,
                success: function(json){
                    resetSubmit();
                    if(json.code == _this.SUCCESS){
                        if(config.successFn && $.isFunction(config.successFn)){
                            config.successFn(json);
                        }
                    }else{
                        _this.remind("error",json.message);
                    }
                },
                statusCode: {
                    "403": function(){
                        _this.remind("error","安全密钥已过期，请重新刷新页面！",function(){
                            resetSubmit();
                        });
                    },
                    "404": function(){
                        _this.remind("error","page not found",function(){
                            resetSubmit();
                        });
                    },
                    "500": function(){
                        _this.remind("error","服务器内部出错",function(){
                            resetSubmit();
                        });
                    }
                },
                error: function(XMLHttpRequest,textStatus,errorThrown){
                    //超时处理
                    if(textStatus == "timeout" || textStatus == "error"){
                        _this.remind("error","请求超时",function(){
                            resetSubmit();
                        });
                    }
                }
            })
        },
        /**
         * 提示函数
         * @param type  类型，目前只支持success和error
         * @param message  提示的信息
         * @param timeout  显示的时间，默认为1600毫秒
         * @param fn  显示完后的回调函数，如果第三个参数是function，那么就是指这个fn
         */
        remind: function(type, message, timeout, fn) {
            if ($.isFunction(timeout)) {
                fn = timeout;
            }

            timeout = timeout || 1600;

            this._remindFn(type, message, timeout, fn);
        },
        _remindFn: function(type, message, timeout, fn){
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
                        str = "background: url(/images/common/remind_icon.png) 0 0 no-repeat;";
                    } else if (type == "error") {
                        str = "background: url(/images/common/remind_icon.png) 0 -50px no-repeat;";
                    }
                    return str;
                }())
            });
            var d = new Dialog({
                "content": html,
                "hasMask": false,
                "classPrefix": "remindDialog",
                "closeTpl": "",
                "width": "auto"
            });
            d.after('show', function () {

                setTimeout(function () {
                    d.hide();
                    if ($.isFunction(fn)) {
                        fn()
                    }
                }, timeout);

            });
            d.show();
        },
        /**
         * confirmBox
         * @param config
         * title 默认为提示
         * content 默认为空
         * hasCancel 是否有取消按钮，默认为true
         * width 默认为256
         * beforeFn 在显示前的事件
         * clickFn 点击确定后的事件
         * isClose 点击确定按钮后，是否关闭，默认为true
         * hideFn 隐藏的回调，此方法的作用主要是用来保存当前打开的dialog，以便下次不必再实例一次
         * classPrefix 默认为ui-dialog
         * effect 动画效果，默认为fade
         */
        confirmDialog: function(config){
            var id = _.uniqueId("confirmDialog_");
            var title = config.title || "提示";
            var content = config.content || "";
            var reg = /<([^>]*)>/;  //判断content有没有html标签，没有的话，则认为是简单的提示
            if(!reg.test(content)){
                content = '<p class="ta-c mt20 fz14">'+content+'</p>';
            }
            var html = '<div id="confirmDialog_'+id+'">'+
                                '<div class="ui-head"><h3>' + title + '</h3></div>'+
                                '<div class="confirm_bd">' + content +
                                '<div class="operate"><a class="confirm_btn" href="javascript:;">确定</a>';
            if(config.hasCancel == false){
                html += '</div></div></div>';
            }else{
                html += '<a class="cancel_btn" href="javascript:;">取消</a></div></div></div>';
            }
            var d = new Dialog({
                "content": html,
                "effect": config.effect || "fade",
                "width": config.width || 256,
                "classPrefix": config.classPrefix || "ui-dialog"
            }).before("show", function () {
                config.beforeFn && $.isFunction(config.beforeFn) && config.beforeFn();
            }).show()

            $(document).on("click", "#confirmDialog_"+id+" .confirm_btn", function (ev) {
                ev.preventDefault();
                if(config.isClose == false){}else{
                    d.hide();
                }
                config.clickFn && $.isFunction(config.clickFn) && config.clickFn(d);
            });

            $(document).on("click", "#confirmDialog_"+id+" .cancel_btn", function () {
                config.hideFn && $.isFunction(config.hideFn) && config.hideFn(d);
                d.hide();
            });
        },
        /**
         * selectBox 生成简单的select框，不兼容IE6（因为IE6无法对select透明）
         * @param elem：select元素（选择器或者jquery对象）
         */
        selectBox: function(elem){
            if (this.isIE6()) {
                return;
            }
            var className = "";
            var oparent = $(elem).parent();
            if (oparent.find("select").length > 1) {
                $(elem).wrap('<div class="inline-block"></div>');
            }
            oparent = $(elem).parent();
            if (!oparent.hasClass("pr")) {
                oparent.addClass("pr");
            }
            $(elem).css({
                "opacity": 0,
                "position": "absolute",
                "left": 0,
                "top": 0,
                "filter": "alpha(opacity=0)"
            })
            var str = "";
            var name_val = $(elem).attr("name");
            var id_val = $(elem).attr("id");
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
            $('<div id="' + str + '_div" class="common_select_box"><span class="fn-text-overflow">' + $(elem).find("option:selected").text() + '</span><i class="arrow"></i></div>').insertBefore($(elem));
            //处理小箭头的位置，3为箭头的高度的一半，就相当于top:50%-(xx的一半)
            $("#" + str + "_div .arrow").css("top", $(elem).outerHeight() / 2 - 3);


            $("#" + str + "_div").css({
                "width": $(elem).outerWidth() - 2,
                "height": $(elem).outerHeight() - 2,
                "line-height": $(elem).outerHeight() - 2 + "px"
            });

            $("#" + str + "_div span").css("width", $(elem).outerWidth() - 20);

            $(elem).on("change", function () {
                $("#" + str + "_div span").html($(elem).find("option:selected").text());
            })
        },
        /**
         * 彻底的将select隐藏，div+css模拟select，兼容IE6
         * 适用场景：小范围的select
         * @param elem：select元素（选择器或者jquery对象）
         * 如果使用这种方式来生成，那么css中的height并不会起作用，必须要给select加data-height，才会起作用
         */
        selectComplexBox: function(elem){

            var height = $(elem).data("height") || 30;

            seajs.use(["select"],function(Select) {
                var s = new Select({
                    trigger: $(elem),
                    width: $(elem).outerWidth(),
                    triggerTpl: '<div class="common_select_box"><span class="fn-text-overflow" data-role="trigger-content"></span><i class="arrow"></i></div>',
                    maxHeight: 200
                }).before("render", function () {
                    var trigger = this.get('trigger');
                    trigger.css("height", height);
                    trigger.css("line-height", height + "px");
                    //处理小箭头的位置，3为箭头的高度的一半，就相当于top:50%-(xx的一半)
                    trigger.find(".arrow").css("top", height / 2 - 3);
                    trigger.find("span").css("width", $(elem).outerWidth() - 20);
                    $(elem).data("select", s);
                });

                s.on("change", function () {
                    $(elem).trigger("change");
                });

                s.render();

            });
        },
        /**
         * selectBox 重渲染
         * @param select
         */
        renderSelect: function(select){
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
        },
        /**
         * 文件上传
         * @param config  object对象或者字符串
         * 当为对象时，传ids数组，即ids: ["xx","xx","xx"]
         * xx为元素的ID
         */
        uploadFiles: function(config){
            var _this = this;
            if($.type(config.ids) == "string"){
                $('#' + config.ids).bind('change', function(){
                    _this._uploadFile($(this).attr('id'),config.url,config.successFn);
                });
            }else if($.type(config.ids) == "array"){
                 $.each(config.ids,function(index,obj){
                    $('#' + obj).bind('change', function(){
                        _this._uploadFile($(this).attr('id'),config.url,config.successFn);
                    });
                })
            }
        },
        _uploadFile: function(uploadId,url,successFn){
            var _this = this;
            seajs.use(["ajaxFileUpload"],function(){
                var d = window["uploadLodingDialog"] || new Dialog({
                    "content": '<div style="padding:10px;background: #fff;border:1px solid #ddd;"><h3 style="line-height: 2;">文件上传中..请稍后</h3><img src="/images/common/012.gif" /></div>',
                    "classPrefix": "zttx-dialog",
                    "closeTpl": "",
                    "width": "auto"
                })

                d.after("show",function(){
                    $.ajaxFileUpload({
                        url:  url || '/common/showImg',
                        secureuri: false,
                        fileElementId: uploadId,
                        dataType: 'json',
                        success: function(data){
                            d.hide();
                            //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                            $('#' + uploadId).bind('change', function(){
                                _this._uploadFile(uploadId);
                            });

                            if(data.code == zttx.SUCCESS){

                                successFn(uploadId,data);

                            }
                            else{
                                _this.remind("error",data.message);
                            }
                        }
                    });
                })

                d.show();

                window["uploadLodingDialog"] = d;


            });
        },
        /**
         * 生成模拟滚动条
         * @param elem 滚动条区域选择器
         */
        scrollArea: function(elem){
            seajs.use("jscroll",function(){
                $(elem).jscrollbar({
                    width: "5"
                });
            })
        },
        /**
         * 简单的日历
         * @param config
         * elem：需要触发的element元素
         * json：其他配置
         * fromNow： 是否要从今天开始，默认为false
         * className: 默认为hasDatepicker
         */
        baseCalendar: function(config){
            seajs.use(["my97DatePicker"], function () {
                $(config.elem).addClass( config.className || "hasDatepicker");
                $(config.elem).on("click", function () {
                    if (config.fromNow) {
                        var newjson = $.extend(config.json || {}, { minDate: '%y-%M-%d' });
                        WdatePicker(newjson);
                    } else {
                        WdatePicker(config.json || {});
                    }
                })
            });
        },
        /**
         * 起始日期 - 截止日期的功能
         * @param startConfig
                        elem：值为id
                        fromNow 是否从现在开始，默认为false
         * @param endConfig
                        elem  值为id
                        endNow  是否从现在结束
         * @param className　默认为hasDatepicker
         */
        rangeCalendar: function(startConfig,endConfig,className){
            var className = typeof className == "undefined" ? "hasDatepicker" : className;
            $("#" + startConfig.elem).add("#"+endConfig.elem).addClass(className);
            seajs.use(["my97DatePicker"], function () {
                $("#" + startConfig.elem).on("focus", function () {
                    if (startConfig.fromNow) {
                        WdatePicker({ maxDate: '#F{$dp.$D(\'' + endConfig.elem + '\')}', minDate: '%y-%M-%d' });
                    } else {
                        WdatePicker({ maxDate: '#F{$dp.$D(\'' + endConfig.elem + '\')}' });
                    }
                })
                $("#" + endConfig.elem).on("focus", function () {
                    if(endConfig.endNow){
                        WdatePicker({ minDate: '#F{$dp.$D(\'' + startConfig.elem + '\')}',maxDate: '%y-%M-%d' });
                    }else{
                        WdatePicker({ minDate: '#F{$dp.$D(\'' + startConfig.elem + '\')}' });
                    }
                })
            });
        },
        /**
         * 后台通过它来做表单其他错误的渲染
         * @param json 请求回来的错误数组
         */
        setErrMsg: function(json){
            $.each(json, function (i, item) {
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
        },
        /**
         * 重置表单错误，场景通常是弹出框关闭后，重新显示，或者回显的时候，需要重置
         * @param form   表单的selector
         */
        resetValidatorError: function(form){
            var form = !form ? "" : form;
            $(form + " .ui-form-item").removeClass("ui-form-item-error");
            $(form + " .ui-form-explain").html("");
        },
        /**
         * 截取字符串
         * @param origText 原始文本
         * @param len   截取的长度
         * @param ellipsisStr  截取后放在文本结束后的字符串，默认为“...”
         * @returns {string}
         */
        trimLongString: function(origText, len, ellipsisStr){
            var newText = "";
            var ellipsis = ellipsisStr || "...";
            if ($.type(origText) == "string") {
                newText = origText;
            }
            if (len > 0 && len < newText.length) {
                newText = newText.slice(0, len) + ellipsis;
            }

            return newText;
        },
        /**
         * 将序列化的字符串生成input hidden或者textarea，放到另外一个form表单中
         * 场景：预览的功能
         * @param config
         * str: 序列化的字符串
         * form: 新表单的选择器
         * editor：编辑器对象，可选
              editor: {
                   name: "",
                   selector: ""
              }
         */
        createFormHiddenFn: function(config){
            var html = "";
            var arr = config.str.split("&");
            for(var i = 0;i<arr.length;i++){
                var s = arr[i].split("=");
                html += '<input type="hidden" name="'+s[0]+'" value="'+decodeURIComponent(s[1])+'" />';
            }
            if(config.editor){
                html += '<textarea name="'+config.editor.name+'">'+$(config.editor.selector).val()+'</textarea>';
            }
            $(config.form).html(html);
        },
        /**
         * 返回字符串的字符数
         * @param str  字符串，一个中文算2个字符，包括全角
         * @returns {*}
         */
        getCharLength: function(str){
            if($.type(str) == "string"){
                var str = str.replace(/[^\x00-\xff]/g, '**');
                return str.length;
            }else{
                return str;
            }
        },
        /**
         * 得到压缩后的图片URL
         * @param url 图片URL地址
         * @param width 宽度
         * @param height 高度
         * @returns {*}
         */
        getImageDomainPath: function(url,width,height){
            //E3CB136D491B4B60AC2B865DA0899E7A.jpg_100x100.jpg
            var arr = url.split(".");
            var suffix = arr[arr.length-1];
            if(width && height){
                return url+"_"+parseInt(width)+"x"+parseInt(height)+"."+suffix;
            }else{
                return url;
            }
        },
        /**
         * 在IE下实现placeholder
         * @param elem 选中元素
         * 不适合多文字的，特别是textarea，可能会出现的情况是被撑破
         */
        placeholder: function(elem){
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
        },
        /**
         * 判断是否是金额（小数点后最多2位）
         * @param str 具体金额值
         * @returns {boolean}
         */
        isAmount: function(str){
            return /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(str);
        }
    }

    module.exports = ZTTX;

})