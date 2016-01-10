/**
 * Created by yefeng on 2014/4/18.
 */
seajs.use(['tabs','overlay'], function (Tabs,Overlay) {
    //锚点导航
    $("#anchor .title").click(function(){
        var tid = $(this).attr("data-id");
        $("#anchor .title").removeClass("active");
        $(this).addClass("active");
        $("html,body").animate({scrollTop:$("#"+tid).offset().top},500);
    });
    $("#anchor .title").hover(function(){
        $(this).addClass("hover");
    },function(){
        $(this).removeClass("hover");
    });
    if($("#tab-view").length){
        //加盟规则tab切换
        var tabs = new Tabs({
            element: '#tab-view',
            triggers: '.rule-nav li',
            panels: '.inner div',
            activeTriggerClass:'active'
        });
    }
    //入驻流程overlay
    var Popup = Overlay.extend({
        attrs:{
            trigger : null
        },
        setup:function(){
            var that = this;
            Popup.superclass.setup.call(this);
            this._setPosition();
            $(this.get("trigger")).hover(function(){
                that.show();
            },function(){
                that.hide();
            });
            //需要调用这句话来实现功能
            this._blurHide(this.get("trigger"));
        }
    });
    new Popup({
        trigger:".step1 .detail-panel",
        element:"#d1",
        align :{
            baseElement:'.step1 .detail-panel',
            baseXY:['100%',0]
        }
    });
    new Popup({
        trigger:".step5 .detail-panel",
        element:"#d2",
        align :{
            baseElement:'.step5 .detail-panel',
            baseXY:['-100%',20]
        }
    });
});
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
        var format = '<div class="remind-border"><div class="remind remind-{{type}}"><i class="remind-icon" style="{{style}}"></i><span class="remind-message">{{message}}</span></div></div>';

        var render = template.compile(format);
        var html = render({
            "type": type,
            "message": message,
            "style": (function () {
                var str = ""
                if (type == "success") {
                    str = "background: url(/images/common/icon_warn.png) -100px -0px no-repeat;";
                } else if (type == "error") {
                    str = "background: url(/images/common/icon_warn.png) -97px -48px no-repeat;";
                }
                return str;
            }())
        });
        var d = new Dialog({
            "content": html,
            "hasMask": false,
            "classPrefix": "remind-dialog",
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
    });
}
$(function(){
    //招商下拉
    $(".merchants").hover(function(){
        $(this).find(".items").show();
    },function(){
        $(this).find(".items").hide();
    });

    $(".items li").hover(function(){
        $(this).addClass("hover");
    },function(){
        $(this).removeClass("hover");
    });

    //品牌名称显示
    $(".panel-content-pic ul li a").hover(function () {
        $(this).find(".caption").css({ opacity: 0.8 }).stop().animate({ top: 0 }, 200).prev().stop().animate({ top: -10 });
    }, function () {
        $(this).find(".caption").stop().animate({ top: 80 }, 200).prev().stop().animate({ top: 0 });
    });

    var tabs = $(".main-grid-right .panel-tab-head-title").hover(function () {
        var tabIndex = tabs.removeClass("on").index($(this).addClass("on"));
        var panel = $(".panel-tab-content .panel");
        panel.not(":eq(" + tabIndex + ")").hide();
        panel.eq(tabIndex).show();
    });

    //问题提交
    $('#subBtn').on('click', function (){
        var leaveMsg = $("#question .fs14").val();
        var email = $("input[name=emailAddress]").val();
        if(leaveMsg=="在这输入您要咨询的问题" || Validate.isNull(leaveMsg)){
            remind("error","请输入您要咨询的问题");
            return false;
        }
        if(email=="在这输入您的电子邮箱" || Validate.isNull(email)){
            remind("error","请输入您的电子邮箱");
            return false;
        }
        if(!Validate.isValidMail(email,false,0)){
            remind("error","电子邮箱格式错误！");
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/apply/subQuestion",
            data: $('#question').serialize(),
            cache: false,
            dataType: "json",
            success: function(json){
                if(json.code == 126000){
                    remind("success","您的问题已经提交成功，我们将在三个工作日内以邮件的方式答复您。");
                    $('#question')[0].reset();
                }else{
                    remind("error",json.message);
                }
            },
            error : function(e){
                console.log(e);
                if(e.status==500){
                    remind("error", "服务器忙");
                }
            }
        });
    });

    $(document).keypress(function(e){
        if(e.which == 13 || e.which == 10) {
            var leaveMsg = $("#question textarea").is(":focus");
            var email = $("input[name=emailAddress]").is(":focus");
            if(leaveMsg || email){
                $('#subBtn').click();
            }
        }
    });
});
/*品牌推荐*/
function AutoScroll(obj){
    $(obj).find("ul:first").animate({
        marginLeft:"-148px"
    },500,function(){
        $(this).css({marginLeft:"0px"}).find("li:first").appendTo($(this));
    });
}
if($(".panel-content-pic li").length>8){
    setInterval('AutoScroll(".panel-content-pic")',3000);
}

