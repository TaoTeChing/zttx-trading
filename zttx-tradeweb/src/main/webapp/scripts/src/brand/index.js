//品牌管理中心首页的js
var index = {
    init: function(){
        this.closeTip(); //关闭安全的提示
    },
    closeTip: function(){
        $(".security_tip").on("click",".close",function(){
            $(this).parent().slideUp(300, function () {
                $(this).remove();
            });
        })
    }
}
index.init();