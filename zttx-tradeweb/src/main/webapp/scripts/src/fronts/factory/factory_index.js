define(function(require, exports, module){
    var Dialog = require("dialog");
    var PCAS   = require("pcas");

    var Index = {
        init: function(){
            this.fillApply();
        },
        fillApply: function(){
            var applyDialog = new Dialog({
                trigger: "#fillApply",
                content: $("#applyDialog").html(),
                width: 500
            }).after("render", toValidator);

            function toValidator(){

                new PCAS({
                    province: "#p",
                    city: "#c",
                    area: "#a"
                });

                baseFormValidator({
                    selector: "#fillAppleForm",
                    isAjax: true,
                    beforeSubmitFn: function(){
                        //console.log("表单校验完成");

                        var _p = $("#p option:selected").val();
                        var _c = $("#c option:selected").val();
                        var _a = $("#a option:selected").val();
                        _p = _p == "" ? undefined : _p;
                        _c = _c == "" ? undefined : _c;
                        _a = _a == "" ? undefined : _a;

                        var areaNo = _a || _c || _p;

                        $.ajax({
                            url: "/fronts/joinInfo/save",
                            method: "post",
                            data: $("#fillAppleForm").serialize() + "&areaNo=" + areaNo,
                            dataType: "json",
                            success: function(data){
                                if(data.code == zttx.SUCCESS){
                                    remind("success", "申请提交成功");
                                    applyDialog.hide();
                                }else{
                                    remind("error", data.message || "申请提交出错，请刷新重试");
                                }
                            },
                            error: function(){
                                alert("请求出错，请稍后重试");
                            }
                        });
                    }
                });
            }
        }
    };

    module.exports = Index;
});