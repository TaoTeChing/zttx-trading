
//运送方式操作
var shippingMethods = {
    init:function(){
        this.initItem();
        this.clickItem();
        this.validatorForm();
        this.inputChecking();
        this.inputError();
    },
    initItem:function(){
        if($(".js-edit-item").length > 0){
            $(".js-edit-item").each(function(){
                var _self = $(this);
                var parents = _self.parents(".js-edit-main");
                if(_self.prop("checked")){
                    parents.find(".js-edit-area").show();
                    parents.find(".js-edit-area input").prop("disabled",false);
                }else{
                    parents.find(".js-edit-area").hide();
                    parents.find(".js-edit-area input").prop("disabled",true);
                }
            });
        }
    },
    clickItem:function(){
        var _this = this;
        //单击 运送方式
        $(".js-edit-item").click(function(){
            _this.initItem();
        });

        $(".js-setarea-fare").click(function(){
            var parents = $(this).parents(".js-edit-main");
            var thisName = parents.find(".js-edit-item").attr("name"),carryTypeName;
            var areaCheckbox = parents.find(".js-setarea-checkbox");

            var i = 0;

            if(areaCheckbox && areaCheckbox.length > 0){
                i = parseInt(areaCheckbox.last().data("index")) + 1;
            }

            if(thisName == "isExpressUsed"){
                carryTypeName = "expressList";
            }
            if(thisName == "isLogisticsUsed"){
                carryTypeName = "logisticsList";
            }

            if(parents.find(".js-edit-item").prop("checked")){
                parents.find(".js-edit-areafare").show();
                var _html=[];
                _html.push("<tr>");
                _html.push("<td>");
                _html.push("<input type=\"checkbox\" class=\"ui-checkbox js-setarea-checkbox mr5\" data-index=\""+i+"\"");
                //选中的状态 需要去掉 style="display:none;"
                if(!parents.find(".js-batch-operation").attr("clicked")){
                    _html.push("style=\"display: none;\"");
                }
                _html.push("/>");
                _html.push("<input type=\"hidden\" name=\""+carryTypeName+"["+i+"].areaNos\" />");
                _html.push("<a class=\"link fr js-edit\" href=\"javascript:;\">编辑</a><span style='display: inline-block;width: 207px;vertical-align: middle'>未添加地区</span>");
                _html.push("</td>");
                _html.push("<td><input class=\"ui-input js-first-w\" name=\""+carryTypeName+"["+i+"].firstWeight\" type=\"text\" data-min=\"0.001\" data-max=\"9999999.999\" style=\"width: 85px;\" value=\"1.000\"></td>");
                _html.push("<td><input class=\"ui-input js-first-p\" name=\""+carryTypeName+"["+i+"].firstPrice\" type=\"text\" data-min=\"0.01\" data-max=\"9999999.99\" style=\"width: 85px;\"></td>");
                _html.push("<td><input class=\"ui-input js-zen-w\" name=\""+carryTypeName+"["+i+"].extendWeight\" type=\"text\" data-min=\"0.001\" data-max=\"9999999.999\" style=\"width: 85px;\" value=\"1.000\"></td>");
                _html.push("<td><input class=\"ui-input js-zen-p\" name=\""+carryTypeName+"["+i+"].extendPrice\" type=\"text\" data-min=\"0.01\" data-max=\"9999999.99\" style=\"width: 85px;\"></td>");
                _html.push("<td class=\"ta-c last\">");
                _html.push("<a class=\"link js-setarea-delete\" href=\"javascript:;\">删除</a>");
                _html.push("</td>");

                _html.push("</tr>");

                _html = _html.join(" ");

                parents.find(".fare-table tbody").append(_html);
            }
        });

        //批量设置
        $(".js-batch-operation").click(function(){
            var parents = $(this).parents(".js-edit-main");
            if(parents.find(".js-edit-areafare").is(":visible")){
                if(!$(this).attr("clicked")){
                    $(this).html("取消批量").attr("clicked","clicked");
                    parents.find(".js-setarea-checkbox,.js-edit-batch").show();
                }else{
                    $(this).html("批量操作").removeAttr("clicked");
                    parents.find(".js-setarea-checkbox,.js-edit-batch").hide();
                }
            }
        });

        //全选
        $(".js-edit-checkall").click(function(){
            var parents = $(this).parents(".js-edit-main");
            parents.find(".js-setarea-checkbox").prop("checked",this.checked);
        });

        //批量设置 下面的
        var thisSetAllParents;
        seajs.use(["dialog","template"],function(Dialog){
            var d = new Dialog({
                trigger:$(".js-set-all"),
                content:$("#setAllTpl").html(),
                hasMask:false,
                width:600
            }).after("show",function(o){
                    thisSetAllParents = $(o.activeTrigger).parents(".js-edit-main");
                });
            $(document).on("click",".js-cancel",function(){
                d.hide();
            });

            $(document).on("click",".js-sure",function(){

                var firstWeight = $(".js-f-w").val();
                var firstPrice = $(".js-f-p").val();
                var zenWeight = $(".js-z-w").val();
                var zenPrice = $(".js-z-p").val();

                thisSetAllParents.find(".js-edit-areafare .js-first-w").val(firstWeight);
                thisSetAllParents.find(".js-edit-areafare .js-first-p").val(firstPrice);
                thisSetAllParents.find(".js-edit-areafare .js-zen-w").val(zenWeight);
                thisSetAllParents.find(".js-edit-areafare .js-zen-p").val(zenPrice);

                //批量设置的时候，重写文本框样式
                _this._commonInputEach();

                d.hide();
            });
        });

        //批量删除
        $(".js-delete-all").click(function(){
            var parents = $(this).parents(".js-edit-main");
            var checkItem = parents.find(".js-setarea-checkbox:checked");
            if(checkItem.length <= 0){
                remind("error","请选择需要删除的地区");
                return;
            }
            confirmDialog({
                title:"提示",
                content:"确认要删除所选地区的设置么？"
            },function(){
                checkItem.parents("tr").remove();
            });
        });

        //单条删除
        $(".js-edit-areafare").on("click",".js-setarea-delete",function(){
            var _this = $(this);
            confirmDialog({
                title:"提示",
                content:"确认要删除所选地区的设置么？"
            },function(){
                _this.parents("tr").remove();
            });
        });
    },
    validatorForm:function(){
        var _this = this;
        baseFormValidator({
            selector:"#fare-add-form",
            isAjax:true,
            addElemFn:function(Core,Validator){
                Core.addItem({
                    element: '#province',
                    required:true,
                    errormessage: "请选择区域"
                })
            },
            beforeSubmitFn:function(){
                var $province = $('select[name="province"]');
                var $city = $('select[name="city"]');
                var $county = $('select[name="county"]');

                // 获取地址值
                var getAreaNo = function () {

                    var _province = $.trim($province.val());
                    var _city = $.trim($city.val());
                    var _county = $.trim($county.val());

                    var _areaNo = "";

                    if(_county == ""){
                        if(_city == ""){
                            _areaNo = _province;
                        }else{
                            _areaNo = _city;
                        }
                    }else{
                        _areaNo = _county;
                    }

                    return _areaNo;
                };

                //运送方式 为空的时候，边框变红提示
                var inputValCount = true;
                inputValCount = _this._commonInputEach();

                if(inputValCount === true){
                    $.ajax({
                        url:"/brand/freightTemplate/saveBrandFreight",
                        data:$("#fare-add-form").serialize()+"&template.areaNo="+getAreaNo(),
                        method:"post",
                        success:function(data){
                            data = $.parseJSON(data);
                            if(data.code != zttx.SUCCESS){
                                remind("error",data.message);
                            }else{
                                remind("success","操作成功");
                                window.location.href = "/brand/freightTemplate/list?isRecommend=0&productIds="+$("#productIds").val();
                            }
                        }
                    });
                }
            }
        });
    },
    inputChecking:function(){
        var _this = this;
        //校验重量
        $(document).on("keyup change focusout",".js-weight,.js-first-w,.js-zen-w,.js-f-w,.js-z-w",function(ev){
            _this._checkingInput($(this),ev,3);
        });
        //校验价格
        $(document).on("keyup change focusout",".js-input-price,.js-first-p,.js-zen-p,.js-f-p,.js-z-p",function(ev){
            _this._checkingInput($(this),ev,2);
        });
    },
    _checkingInput:function(_self,ev,digit){
        //重量输入固定，取自于价格
        var val = _self.val();
        val = val.replace(/[^\d.]/g,"");
        val = val.replace(/^\./g,"");
        val = val.replace(/\.{2,}/g,".");
        val = val.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        var str = val.split(".");
        if(str[1]){
            val =  str[0].toString() + "." + str[1].toString().substring(0,digit);
        }
        _self.val(val);
        if(ev.type == "change" || ev.type == "focusout"){
            var min = parseFloat(_self.data("min")),
                max = parseFloat(_self.data("max"));
            var value = parseFloat(_self.val());
            if (min && max) {
                min = Math.min(min, max);
                max = Math.max(min, max);
            }
            if (max) {
                if(value > max){
                    remind("error","最大值不能超过100000000");
                    //_self.val("");
                }
            }
            if (min) {
                if(value < min){
                    remind("error","最小值不能低于0");
                    //_self.val("");
                }
            }

            //保留小数位，少填补零
            var _val = _self.val();
            if(_val != ""){
                _val=parseFloat(_val).toFixed(digit);
                _self.val(_val);
            }
        }
    },
    inputError:function(){
        $(".js-edit-main").on("keyup change focus",".ui-input",function(){
            var inputVal = $(this).val();
            var minInput = $(this).data("min");
            var maxInput = $(this).data("max");
            if(inputVal != "" && parseFloat(inputVal) >= minInput && parseFloat(inputVal) <= maxInput){
                $(this).removeClass("input-error-tip");
            }else{
                $(this).addClass("input-error-tip");
            }
        });
    },
    _commonInputEach:function(){
        //验证文本框是否输入数据的方法
        var inputValCount = true;
        $(".js-edit-main .ui-input:not(:disabled)").each(function(){
            var inputVal = $(this).val();
            var minInput = $(this).data("min");
            var maxInput = $(this).data("max");
            if(inputVal != "" && parseFloat(inputVal) >= minInput && parseFloat(inputVal) <= maxInput){
                $(this).removeClass("input-error-tip");
            }else{
                $(this).addClass("input-error-tip");
                inputValCount = false;
            }
        });
        return inputValCount;
    }
};
shippingMethods.init();

function submitButton(){

    if(!$("#isExpressUsed").is(':checked')&&!$("#isExpressCollectUsed").is(':checked')&&!$("#isLogisticsCollectUsed").is(':checked')&&!$("#isLogisticsUsed").is(':checked')){
        alert("请选择运送方式");
        return ;
    }
    $("#fare-add-form").submit();
}
//构建一个城市多选的弹窗
new setAreaCom({
    elem:".js-edit",
    confirmFn:function(o,valArr,html){
        //把选中的城市名称给到对应的位置，以及把城市value给到另一个位置
        //console.log(html);
        //console.log(valArr);
        o.next().html(html);
        o.prev().attr("value",valArr);
        //把获取到的城市value保存起来,保存到当前触发的节点
        o.data("value",valArr);
    },
    elemTiggerFn:function(o){

        var parents = o.parents(".js-edit-main"),arr = [];
        var thisArr = o.data("value");

        //回显字符串转数组
        if(thisArr && $.type(thisArr) !== "array"){
            thisArr = "["+thisArr+"]";
            thisArr = $.parseJSON(thisArr);
        }

        //每次调用弹窗的时候重置 复选框的选中状态为未选中
        $(".ts-select-city input[type=checkbox]").prop({
            "disabled": false,
            "checked": false
        });

        //重置打开样式
        $(".province-box").removeClass("hover");
        //获取已经选过了的城市value
        parents.find(".js-edit").each(function(){
            var _val = $(this).data("value"),valArr = [];

            if(_val && $.type(_val) !== "array"){
                _val = "["+_val+"]";
                _val = $.parseJSON(_val);
            }
            if(_val){
                arr = arr.concat(_val);
            }
        });

        //设置已选过的城市为不可选
        for(var i in arr){
            var obj = $("#city_value_"+arr[i])[0];
            obj.checked = false;
            obj.disabled = true;
        }

        //省份不可选，城市也不可选
        $(".ts-sc-content .province-box").each(function(){

            if($(this).find(".province input[type=checkbox]").prop("disabled")){
                $(this).find("input[name=areaAry]").prop({
                    checked:false,
                    disabled:true
                });
            }

            var chk = $(this).find("input[name=areaAry]").length;
            var chkL = $(this).find("input[name=areaAry]:checked").length;
            var disL = $(this).find("input[name=areaAry]:disabled").length;

            if(chk == (chkL+disL)){
                $(this).find(".province input[type=checkbox]").prop({
                    disabled:true,
                    checked:false
                });
            }

        });

        //bug #6459 运费模板中，已选择的地区全面的复选框禁用
        $(".ts-sc-content .ts-sc-gcity").each(function(){
            var _len = $(this).find(".province .js-city").length;
            var _disLen = $(this).find(".province .js-city:disabled").length;
            if(_len == _disLen){
                $(this).find(".gcity-box input").prop({
                    disabled:true,
                    checked:false
                });
            }
            //console.log("_len: " + _len);
            //console.log("_disLen: " + _disLen);
        });

        //设置当前选中的城市为可编辑
        if(thisArr){
            for(var j in thisArr){
                var obj = $("#city_value_"+thisArr[j])[0];
                obj.checked = true;
                obj.disabled = false;
            }
        }

        //设置当前选中 的省份名选中，选择省份下属所有城市
        $(".ts-sc-content .province-box").each(function(){

            if($(this).find(".province input[type=checkbox]").prop("checked")){
                $(this).find("input[name=areaAry]").prop({
                    checked:true,
                    disabled:false
                });
            }

        });

    }
});
