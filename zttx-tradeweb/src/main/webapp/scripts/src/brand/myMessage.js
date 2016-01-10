//品牌管理中心首页的js
var myMessagesend = {
    init: function(){
        this.seajsUM(); //关闭安全的提示
    },
    seajsUM:function(){
        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor', {
                initialFrameWidth: 772,
                initialFrameHeight: 350
            });
        })
    }
}

var myMessagemsgman = {
    init: function(){
        this.seajsuseData(); //关闭安全的提示
        this.validateReply();//回复留言表单验证
    },
    seajsuseData:function(){
        baseCalendar("#date",{dateFmt: 'yyyy年MM月dd日 HH时mm分ss秒'})
        rangeCalendar("start-cal","end-cal");
    },
    validateReply:function(){
        if($(".message-info-reply").length > 0){
            baseFormValidator({
                selector:".messageReply",
                isAjax:true,
                beforeSubmitFn:function(){
                    alert("验证完成");
                }
            })
        }
    }
}

var myMessageSever={
    init: function(){
        this.onofftoggle(); //关闭安全的提示
    },
    onofftoggle:function(){
        var $onoff = $(".mobile-onoff");
        var on = "mobile-on";
        var off = "mobile-off";

        $onoff.click(function(){
            if($(this).hasClass(on)){
                $(this).removeClass(on).addClass(off);
            }else{
                $(this).removeClass(off).addClass(on);
            }
        });
    }
}