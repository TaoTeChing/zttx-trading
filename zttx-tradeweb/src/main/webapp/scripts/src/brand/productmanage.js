var productmanage = {
    init:function(){
        this.tabSmenu();
        //this.TitclickHS();
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.jstabsmenu_t ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.tabsbox_t > div").eq(index).show().siblings().hide();
        });
    }

}


var addNew = {
    init:function(){
        this.tabSmenu();
        this.TitclickHS();
//        this.selectgroup();//图库选择
        this.seajsuseData();
        this.seajsUM();
        this.seajsuseMM();
        this.seajsvalidator();
        this.percentage();
        //this.proToggle();
        this.fareTplTabs();
    },

    tabSmenu:function(){//选项表切换
        var $div_li =$("div.jstabsmenu_t ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.tabsbox_t > div").eq(index).show().siblings().hide();
        });
    },

    TitclickHS:function(){//展示的显示和隐藏
        $(".mininfo").on("click",".title",function(){
            var $TitHS = $(".TitclickHS");
            $TitHS.toggle();
            if($TitHS.is(":hidden")){
                $(this).find(".iconfont").html("&#xe606;");
            }
            if($TitHS.is(":visible")){
                $(this).find(".iconfont").html("&#xe605;");
            }
        });

        $(".mininfo2").on("click",".title",function(){
            var $TitHS = $(".product-nature");
            $TitHS.toggle();
            if($TitHS.is(":hidden")){
                $(this).find(".iconfont").html("&#xe606;");
            }
            if($TitHS.is(":visible")){
                $(this).find(".iconfont").html("&#xe605;");
            }
        });


    },

    seajsuseData:function(){//时间拾取
        /*
        seajs.use(["calendar","calendar_style"],function(Calendar){
            $("#start-cal,#end-cal").addClass("hasDatepicker");
            var c1 = new Calendar({trigger: '#start-cal'})
            var c2 = new Calendar({trigger: '#end-cal'});

            $("#start-cal2,#end-cal2").addClass("hasDatepicker");
            var c3 = new Calendar({trigger: '#start-cal2'})
            var c4 = new Calendar({trigger: '#end-cal2'});
        })
        */
        seajs.use(["my97DatePicker"], function () {

            $("#start-cal,#end-cal").addClass("hasDatepicker");
            $("#start-cal2,#end-cal2").addClass("hasDatepicker");

            $("#start-cal").on("focus", function () {
                  WdatePicker({ maxDate: '#F{$dp.$D(\'end-cal\')}' });
            })

            $("#end-cal").on("focus", function () {
                var startTime = +new Date($("#start-cal").val());
                var currentTime = +new Date();
                if(startTime >= currentTime){
                    WdatePicker({ minDate: '#F{$dp.$D(\'start-cal\')}' });
                }else{
                    WdatePicker({ minDate: '%y-%M-%d' });
                }
            })

            $("#start-cal2").on("focus", function () {
                if(typeof flag != "undefined"){
                    if($("#start-cal").val == ""){
                        WdatePicker({ maxDate: '#F{$dp.$D(\'end-cal2\')}',minDate: '%y-%M-%d' });
                    }else{
                        WdatePicker({ maxDate: '#F{$dp.$D(\'end-cal2\')}',minDate: '#F{$dp.$D(\'start-cal\')}' });
                    }
                }else{
                    WdatePicker({ maxDate: '#F{$dp.$D(\'end-cal2\')}' });
                }
            })

            $("#end-cal2").on("focus", function () {
                WdatePicker({ minDate: '#F{$dp.$D(\'start-cal2\')}' });
            })


        });
    },
    seajsuseMM:function(){
        seajs.use(["my97DatePicker"],function(){
            $("#datatimemm,#dateRebate").addClass("hasDatepicker");
            $("#datatimemm").on("click",function(){
                WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d {%H+1}:00:00', readOnly: true});
            });
            $("#dateRebate").on("click",function(){
                WdatePicker({dateFmt:'yyyy-MM-dd', minDate: '%y-%M-{%d+1}', readOnly: true});
            });
        });
    },
    seajsUM:function(){
        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor', {
                initialFrameWidth: 678,
                initialFrameHeight: 500
            });
        })
    },
    seajsvalidator:function(){

        seajs.use(['validator', 'widget', 'dialog'], function(Validator, Widget, Dialog) {
            Widget.autoRenderAll();
            var validator = Validator.query('.productadd-form');
            validator.set("autoSubmit", false);
            /*
            var validator = new Validator({
                element:'.productadd-form',
                autoSubmit:false
            });
            */

            //提醒门店是否生效的弹窗
            var submitFn;

            var fdDialog = new Dialog({
                content: $("#remindFdTpl").html(),
                width: 300
            });

            $(document).on("click", ".js-fdcome-sure", function(){
                $("input[name=send]").val("true");
                fdDialog.hide();
                submitFn();
            });
            $(document).on("click", ".js-fdcome-cancel", function(){
                $("input[name=send]").val("false");
                fdDialog.hide();
                submitFn();
            });

            validator.on("formValidated",function(error){//验证时做的事情
            	if(error){
                    return;
                }
                var nodes = cateTreeObj.getCheckedNodes(true);
               	var menuCodeAry = new Array();
               	for(var i=0,j=0;i<nodes.length;i++){
               		if(nodes[i].id!=undefined){
               			menuCodeAry[j]=nodes[i].id;
               			j++;
               		}
               	}
               	if(menuCodeAry.length > 0){
               		$("#cateIds").val(menuCodeAry);
               	}
               	var opType = $.trim($("#opType").val());

                //二期追加的sku验证 需要验证的字段为吊牌价和直供价
                //var _len = $("#selectmix tr").length;  _p：价格变量的开头
                //有属性才去验证
                if($("#proSaleAttr").length > 0){
                    var validatorFlag = true;
                    var _p_ny = $("#samplePrice").val();
                    var _p_pjz = 0;//现款现货价的最大值
                    var tipsFlag = 0;

                    var errorTpl = '';

                    //获取设置的返点比例
                    if($(".js-rebate-pro:checked").data("type") == 0){
                        var discountPoint = 10;
                    }else{
                        if(parseFloat($(".js-rebate-other").val()) && parseFloat($(".js-rebate-other").val()) >= 10){
                            var discountPoint = parseFloat($(".js-rebate-other").val());
                        }else{
                            $(".js-rebate-other").addClass("setcommon-error").focus();
                            remind("error", "返点比例设置不正确");
                            return false;
                        }
                    }

                    $("#selectmix tr").each(function(i, o){
                        var $this = $(this);
                        var _p_dp = parseFloat($this.find("input[name=attr_combo_price]").val());
                        var _p_zg = parseFloat($this.find("input[name=attr_combo_direct_price]").val());
                        var _p_sx = parseFloat($this.find("input[name=attr_combo_credit_price]").val());
                        var _p_fd = parseFloat($this.find("input[name=attr_combo_rebate_price]").val());

                        var _p_oldfd = parseFloat($this.find("input[name=attr_combo_rebate_price]").data("oldprice"));

                        if(!_p_dp || _p_dp == 0){
                            validatorFlag = false;
                            $(this).find("input[name=attr_combo_price]").addClass("input-error");
                            if(tipsFlag == 0){
                                errorTpl = '吊牌价不能为空或0';
                                $this.find("input[name=attr_combo_price].input-error").focus();
                                if(tiggerSxFlag !== true) {
                                    tipsFlag = 2;
                                }
                            }
                        }
                        if(!_p_zg || _p_zg == 0 || _p_zg > _p_dp){
                            validatorFlag = false;
                            $(this).find("input[name=attr_combo_direct_price]").addClass("input-error");
                            if(tipsFlag == 0){
                                errorTpl = '直供价、授信价不能为0，且必须小于吊牌价';
                                $this.find("input[name=attr_combo_direct_price].input-error").focus();
                                if(tiggerSxFlag !== true) {
                                    tipsFlag = 2;
                                }
                            }
                        }
                        //有授信价的时候，返点价和授信价比较
                        if(tiggerSxFlag == true && tiggerFdFlag == true) {
                            if (!_p_fd || _p_fd == 0 || (_p_fd * (1 - discountPoint/100)) > _p_sx) {
                                validatorFlag = false;
                                $(this).find("input[name=attr_combo_rebate_price]").addClass("input-error");
                                if (tipsFlag == 0) {
                                    errorTpl = '返点成本价不能为0，且必须小于等于授信价';
                                    $this.find("input[name=attr_combo_rebate_price].input-error").focus();
                                    if (tiggerSxFlag !== true) {
                                        tipsFlag = 2;
                                    }
                                }
                            }
                        }

                        //无授信价的时候，返点价和现款价比较
                        if(tiggerSxFlag == false && tiggerFdFlag == true){
                            if (!_p_fd || _p_fd == 0 || (_p_fd * (1 - discountPoint/100)) > _p_zg) {
                                validatorFlag = false;
                                $(this).find("input[name=attr_combo_rebate_price]").addClass("input-error");
                                if (tipsFlag == 0) {
                                    errorTpl = '返点成本价不能为0，且必须小于等于直供价';
                                    $this.find("input[name=attr_combo_rebate_price].input-error").focus();
                                    if (tiggerSxFlag !== true) {
                                        tipsFlag = 2;
                                    }
                                }
                            }
                        }

                        if(tiggerFdFlag == true){
                            //bug #7274 修改返点价时，新的成本价不能高于原来的成本价
                            if(_p_oldfd != 0 && _p_oldfd < _p_fd){
                                validatorFlag = false;
                                $(this).find("input[name=attr_combo_rebate_price]").addClass("input-error");
                                if (tipsFlag == 0) {
                                    errorTpl = '新的成本价不能高于原来的成本价';
                                    $this.find("input[name=attr_combo_rebate_price].input-error").focus();
                                    if (tiggerSxFlag !== true) {
                                        tipsFlag = 2;
                                    }else{
                                        tipsFlag = 1;
                                    }
                                }
                            }
                        }

                        //tiggerSxFlag 在productinfo_edit.js 里面定义的全局变量 表示：支持授信是否处于选中状态
                        if(tiggerSxFlag == true){
                            if(!_p_sx || _p_sx == 0 || _p_sx > _p_dp){
                                validatorFlag = false;
                                $(this).find("input[name=attr_combo_credit_price]").addClass("input-error");
                                if(tipsFlag == 0){
                                    errorTpl = '直供价、授信价不能为0，且必须小于吊牌价';
                                    $this.find("input[name=attr_combo_credit_price].input-error").focus();
                                    tipsFlag = 2;
                                }
                            }
                        }
                        if(i == 0){
                            _p_pjz = _p_zg;
                        }else{
                            if(_p_zg > _p_pjz){
                                _p_pjz = _p_zg;
                            }
                        }
                        //拿样价大于吊牌价校验
                        /*if(_p_ny > _p_dp){

                         }*/
                    });

                    if(errorTpl != ''){
                        remind('error', errorTpl);
                    }
                    
                    submitFn = function(){
                        /*工厂店二期增加能容，用以在提交之前去掉 产品货号 、属性 等的 disabled属性 2015年3月18日 16:12:04*/
                        $("#productNo").prop("disabled",false);
                        $(".coloreditul .colorchk").prop("disabled",false);
                        $(".coloreditul .getext").prop("disabled",false);
                        /*工厂店二期增加能容 结束*/
                        if(!error){
                            addNew_formSubmit();
                        }
                        var formData = $(".productadd-form").serialize();
                        //console.log(formData);
                        if("0"==opType){
                            $("#productSave").attr({
                                "disabled":"true"
                            }).css({
                                "backgroundColor": "#ccc"
                            });
                            dialogLoading(function(dd){
                                $.ajax({
                                    url: '/brand/product/save',
                                    type: 'post',
                                    data: formData,
                                    dataType: 'json',
                                    async: true,
                                    success: function(data, textStatus)
                                    {
                                        $("#productSave").attr("disabled", false).css({'backgroundColor': "#FF8800"});
                                        dd.hide();
                                        if(zttx.SUCCESS==data.code){
                                            setTimeout(function(){
                                                window.location.href="/brand/product/success/"+data.object;
                                            },2000);
                                        }else if(zttx.VALIDERR==data.code)
                                        {
                                            setErrMsg(".productadd-form",data.rows);
                                        }
                                        else{
                                            remind("error",data.message);
                                        }
                                    },
                                    error: function(data)
                                    {
                                        $("#productSave").attr("disabled", false).css({'backgroundColor': "#FF8800"});
                                        dd.hide();
                                        remind("error",data.message);
                                    }
                                });
                            }, "添加产品中...请稍候");
                        }else{
                            $("#productSave").attr({
                                "disabled":"true"
                            }).css({
                                "backgroundColor": "#ccc"
                            });
                            $.ajax({
                                //url: '/brand/product/preview/',
                                url: '/brand/product/preview/validator',
                                type: 'post',
                                data: formData,
                                dataType: 'json',
                                async: true,
                                success: function(data, textStatus)
                                {
                                    $("#productSave").attr("disabled", false).css({'backgroundColor': "#FF8800"});
                                    if(zttx.SUCCESS==data.code){
                                        //调用产品详情展示页面预览
                                        //$("#viewForm").attr("action","/market/product/"+data.object);
                                        //$("#viewForm").attr("action","/brand/product/preview/"+data.object);
                                        //$("#viewForm").submit();
                                        createFormHiddenFn(formData, "#viewForm", {name:"productMark","selector":"#myEditor"});
                                        $("#viewForm").submit();
                                        $("#viewPanel").animate({left:0},618);
                                        $("#viewPanel #closeView").click(function(){
                                            $("#viewPanel").animate({left:-970},618);
                                        });
                                    }else if(zttx.VALIDERR==data.code)
                                    {
                                        setErrMsg(".productadd-form",data.rows);
                                    }
                                    else{
                                        remind("error",data.message);
                                    }
                                },
                                error: function(data)
                                {
                                    $("#productSave").attr("disabled", false).css({'backgroundColor': "#FF8800"});
                                    remind("error",data.message);
                                }
                            });
                        }
                    }
                    
                    if(!validatorFlag ){
                        return false;
                    }

                    //若未选择SKU，则提示
                    if($("#selectmix .maintbody tr").length <= 0){
                        var _top = $(".mininfo").offset().top;
                        remind("error", "请选择至少一组产品销售属性", 1600, function(){
                            $(document).scrollTop(_top);
                        });
                        return false;
                    }

                    if($("input[name=isSample]").prop("checked") == true && _p_ny == ""){
                        //remind("error", "拿样价必填");
                        $("#samplePrice").addClass("input-error").focus();
                        return false;
                    }

                    if($("#support_rebate").prop("checked") == true && $(".js-rebate-pro:checked").data("type") == 1){
                        var _val = parseFloat($(".js-rebate-other").val());
                        if(_val <= 10 || _val > 100){
                            $(".js-rebate-other").addClass("setcommon-error");
                            remind("error", "返点比例大于10%, 不能高于 100%");
                            return false;
                        }
                    }

                    //如果拿样价低于现款现货价，提醒用户：您设置的拿样价，低于现款现货的价格，确认吗？
                    if(_p_ny < _p_pjz && $("input[name=isSample]").prop("checked") == true ){
                        confirmDialog("您设置的拿样价，低于现款现货的价格，确认吗？", function(){
                            if(isSpportFd == true && tiggerFdFlag == false){
                                fdDialog.show();
                            }else{
                                submitFn();
                            }
                        });
                    }else{
                        if(isSpportFd == true && tiggerFdFlag == false){
                            fdDialog.show();
                        }else{
                            submitFn();
                        }
                    }
                }else{
                    submitFn();
                }

                
            });
            $("input[name='beginType']").change(function(){
//                var beginType = this.value;
                beginTypeValidated(validator);
            });
            //$("input[name='productCate']").change(function(){
            //    productCateValidated(validator);
            //});
            beginTypeValidated(validator);
            //productCateValidated(validator);

            //校验规则
            Validator.addRule('NumEn',/^[a-zA-Z0-9]{1,30}?$/,"请输入1-30位英文或数字组合的产品货号");
            Validator.addRule('NumberInt',/^[0-9]{1,8}?$/,"必须为整数且不超过8位");
            Validator.addRule('productTitle',function(options){
                var v = options.element.val();
                var len = getCharLength(v);
                if(len<1||len>60){
                    return false;
                }else{
                    return true;
                }
            },'产品标题必须在1-60个字符内');
            Validator.addRule('productChinese',function(options){
                var v = options.element.val();
                return !/.*[\u4e00-\u9fa5]+.*$/.test(v);
            },'货号不能包含中文');
            Validator.addRule('productNo',function(options){
                var v = options.element.val();
                var len = getCharLength(v);
                if(len<1||len>32){
                    return false;
                }else{
                    return true;
                }
            },'产品货号必须在1-32个字符内');
            //校验 产品包装后的重量
            Validator.addRule("weightInput",function(options){
                var _weight = options.element.val();
                var _reg = /^(([1-9]\d{0,9})|0)(\.\d{1,3})?$/;
                return _reg.test(_weight);
            },"请输入正确的{{display}}");
            Validator.addRule('lengthTW',function(options){
                var v = options.element.val();
                var len = getCharLength(v);
                if(len<1||len>32){
                    return false;
                }else{
                    return true;
                }
            },'长度必须在1-32个字符内');
            //校验产品货号
            Validator.addRule('checkProductNo',function(options, commit){
                var element = options.element,
                    item = validator.getItem(element);
                var brandsId = $.trim($("#ppchoice").val()),productNo = item.find("input[type=text]").val(),productId = $("input[name=refrenceId]").val();
                item.addClass('ui-form-item-loading').find(".ui-form-explain").text("货号查询中");
                var copy=$("#copy").val();
                
                $.ajax({
                    url:"/brand/product/isSameProductNo",
                    method:"post",
                    dataType:"json",
                    data:{brandsId: brandsId,
                    	productNo: productNo,
                    	productId: productId,
                    	copy:copy,
                    	csrfToken: $("input[name=csrfToken]").val()
                    },
                    success:function(data){
                        item.removeClass('ui-form-item-loading');
                        item.removeClass('ui-form-item-success');
                        if(data.code == zttx.SUCCESS){
                            if(data.object == true){
                                commit(false, "货号重复，请修改");
                            }else{
                                item.addClass('ui-form-item-success');
                                commit(true, "货号可以使用");
                            }
                        }else{
                            commit(false, data.message);
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        item.removeClass('ui-form-item-loading');
                        item.removeClass('ui-form-item-success');
                        commit(false, "查询失败");
                    }
                });
            });

            //校验
            validator.addItem({
                element: '.articleno',
                required: true,
                rule:'productNo checkProductNo',
                errormessageRequired: '请输入产品货号'
            })
                .addItem({
                element:'.bulkclass',
                required: true,
                rule:  'NumberInt',
                errormessageRequired: '请输入起批量'
            })
                .addItem({
                    element:'#groomCheck',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '推荐窗口数已满，不能设置该产品为推荐产品'
                })
                .addItem({
                    element: '.ppchoice',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请选择品牌'
                })
                .addItem({
                    element: '#unitNo',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请选择产品单位'
                }).addItem({
                    element:'.owner',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请选择产品类型'
                })
                .addItem({
                    element:'[id="myEditor"]',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请填写产品描述'
                })
                .addItem({
                	element:'#attrError',
                	required:true
                })
                .addItem({
                    element:'[id="imgValidate"]',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请上传产品主图'
                })


                .addItem({
                    element: '.productitle',
                    required: true,
                    rule: 'productTitle',
                    errormessageRequired: '请输入产品标题'
                })
                /*.addItem({
                    element: '.productprice',
                    required: true,
                    rule: 'max{max: 100000000}',
                    errormessageRequired: '请输入产品价格'
                })*/
                /*.addItem({
                    element: '.productinventory',
                    required: true,
                    rule: 'NumberInt',
                    errormessageRequired: '请输入产品库存'
                })*/
                .addItem({
                    element: '.chengdanwul',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请选择运费物流方式'
                }).addItem({
                    element: '.samplePrice',
                    required: false,
                    rule: 'max{max: 100000000}',
                    errormessageRequired: '请输入拿样价格'
                })
                .addItem({
                    element: '#myEditor',
                    required: true
                })
                .addItem({
                    element: 'input[name=productWeight]',
                    required: true,
                    rule:"weightInput",
                    display:"产品包装后的重量"
                })/*.addItem({
                    element: '.directprice',
                    required: function(){
                        //选中颜色尺码的时候不需验证，未选中颜色和尺码的时候需要去校验
                        var directFlag = $(".js-no-directprice .ui-form-required").length;
                        if(directFlag >= 1){
                            return true;
                        }else{
                            return false;
                        }
                    },
                    rule: 'min{min: 0.01} max{max: 100000000}',
                    errormessageRequired: '请输入直供价格'
                })*/

            //请选择运费模版 校验需动态添加
            function productCarryInit(){
                var _value = $(".chengdanwul:checked").val();
                validator.removeItem("select[name=freTemplateId]");
                if(_value == "1"){
                    $("#fareSwitchItem").show();
                    $(".fare-change").show();
                    validator.addItem({
                        element: 'select[name=freTemplateId]',
                        required: true,
                        errormessageRequired:"请选择运费模版"
                    });
                }else if(_value == "2"){
                    $("#fareSwitchItem").hide();
                    $(".fare-change").hide();
                }
            }
            productCarryInit();
            
            $(".samplePrice").off("keyup change").isPrice(true);

            $('.chengdanwul').change(function(e) {
                productCarryInit();
            });
        });
    },
    //控制进度条
    percentage:function(){
        var hasLen = $(".to-omple").length;//class=hasalready文本框的总个数
        var WID = $(".jindutiao").width();//进度条的总宽度

        $(".to-omple").each(function(){
            var value = $(this).val();
            if(value !="" ){
                $(this).addClass("notnull");
            }
        });

        //计算 当前产品资料完善度 百分比
        function countComplePercent(){
            var len = $(".notnull").length;
            $(".comple").animate({width:len/hasLen*WID},function(){
                var text =len/hasLen * 100;
                var baifen = parseInt(text);
                $(".comple").text(baifen + '%');
            });
        }
        countComplePercent();

        $(document).on("change",".to-omple",function(ev){
            var _this = $(this);
            if(_this.val() != ""){
                _this.addClass("notnull");
            }else{
                _this.removeClass("notnull");
            }

            $("textarea#myEditor").removeClass("notnull");

            countComplePercent();
        });

        $(document).on("input propertychange keydown keyup",".edui-body-container",function(){//单独处理百度编辑器
        //$(".edui-body-container").bind("input propertychange",function(){

            var _html = UM.getEditor("myEditor").getContent();

            if(_html !== ''){
                $(".edui-body-container.to-omple").addClass("notnull");
            }else{
                $(".edui-body-container.to-omple").removeClass("notnull");
            }

            $("textarea#myEditor").removeClass("notnull");

            countComplePercent();
        });

    },
    //产品类型下面显示和隐藏
    /*proToggle:function(){   //产品预订相关，因业务调整，移除，此处注释可删除
        $(".yuding-pro").on("click",function(){
            $(".proman-templetedit").show();
        });
        $(".now-pro").on("click",function(){
            $(".proman-templetedit").hide();
        });
    },*/
    selectgroup:function(){
        seajs.use(['dialog'],function(Dialog){
            var selectgroup = new Dialog({//产品发布单独图库选择
                trigger: '.js-selectgroup-pic',
                effect: 'fade',
                hasMask:true,
                content: $(".js-selectgroup-box"),
                width:700
            });
            $(document).on("click",".ui-dialog-close",function(){
                selectgroup.hide();
            });
            $(".js-Color-Url").on("click",".item",function(){
                var fileInputId = $("#fileInputId").val();
                var url = $(this).find("img").data("icon");
                var domainName = $("#show_color_img input[name='colorImgDomainName']").val();
                setColorImgUrlData(fileInputId,url,domainName);
                selectgroup.hide();
            })
            $(".js-selectgroup-box").on("click",".js-selectgroup-cansole",function(){
                selectgroup.hide();
            });
        })
    },
    /*treeToggle:function(){ //此处原本是属于产品线的，因产品线模块一处，此处注释。可以删除
        $(".onefenl").find("a.title").click(function(){
            $(this).next().toggle();
        });
    },*/
    fareTplTabs:function(){
        //运费模版 快递 物流切换
        $(".fare-change").on("click",".fare-change-tabs li.fare-item",function(){
            var index = $(this).index();
            $(".fare-change-tabs li").removeClass("selected");
            $(this).addClass("selected");
            $(".fare-change-tabs .fare-tabs-con").hide().eq(index).show();
        });
    }
}

var productmanagelist = {
    init:function(){
        //this.tabSmenu();
        //this.seajsTip();
        //this.changeN();
        //this.seajsCopy();//复制链接  暂时去除
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.jstabsmenu_t ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.tabsbox_t > div").eq(index).show().siblings().hide();
        });
    },
    seajsTip:function(){
        seajs.use(['popup'], function(Popup) {
            var example2 = new Popup({//价格tip
                trigger: '.js_tip_triggerjiage',
                element: '.js-tip-jiage',
                align: {
                    baseXY: [-55, 22]
                }
            });
            $(".js-tip-jiage").on("click",".js-savebtn",function(){//保存
                if($.isFunction(jiageClick)){
                    jiageClick(example2);
                };
            })
            $(".js-tip-jiage").on("click",".js-cancelbtn",function(){//取消
            	example2.hide();
            })

            var example3 = new Popup({//库存tip
                trigger: '.js_tip_triggerkucun',
                element: '.js-tip-kucun',
                align: {
                    baseXY: [-55, 22]
                }
            });
            
            $(".js-tip-kucun").on("click",".js-savebtn",function(){//保存
                if($.isFunction(kucunClick)){
                    kucunClick(example3);
                }
            })
            $(".js-tip-kucun").on("click",".js-cancelbtn",function(){//取消
            	example3.hide();
            })

        });
    },
    changeN:function(){//点击数字的时候(功能有修改，此处可能不用)
    	/*$(".js-do-number").css({
    		display:"block",
    		width:58,
    		height:20
    	});
        $("td").on("click",".js-do-number",function(){
        	$(this).hide();
            $(this).parent().append('<div style="position: relative;"><input type="text" class="ui-input js-setnumder" style="width: 45px; padding: 0 5px;"></div>');
        });*/
    }/*,
    seajsCopy:function(){
    	$(".js-copylink-url").each(function(){
    		//var value = $(this).parent().find(".js-seturl").attr("href");
    		var value = "http://"+document.domain+"/market/product/"+$(this).data("id");
    		$(this).val(value);
    	});
    	
    	seajs.use(['zeroclipboard'],function(ZeroClipboard){
    		var clip = new ZeroClipboard( $(".js-copylink"), {
                moviePath: window.SWF_ZeroClipboard_URL
	        });
	        clip.on( "load", function(client) {
	            clip.addEventListener( "complete", function() {
	            	remind("success","复制成功");
	            	//alert("复制成功");
	            });
	        });
    	})
    }*/
}

var productmanageclass = {
    init:function(){
        this.tabSmenu();
        //this.seajsZtree();
        this.movEdit();
        this.canmovesee();
        this.addpropic();
        //this.jstoclass();
        //this.addclassHover();
        this.comparePrice();
    },
    setCatalogFn:function(obj){

    },

    tabSmenu:function(){//选项表切换
        var $div_li =$("div.jstabsmenu_t ul li");
        var onOff = true;
        $div_li.click(function(){
        	var index = $(this).index();//监听页面所处的位置
            if(index == 1){
                if(onOff){

                   onOff = false;
                }
            }
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.tabsbox_t > div").eq(index).show().siblings().hide();
        });
    },

    seajsZtree:function(){
        seajs.use(["$","ztree"],function($,_){
            var setting = {	};

            var zNodes =[
                { name:"父节点1 - 展开", open:true,
                    children: [
                        { name:"父节点11 - 折叠",
                            children: [
                                { name:"叶子节点111"},
                                { name:"叶子节点112"},
                                { name:"叶子节点113"},
                                { name:"叶子节点114"}
                            ]},
                        { name:"父节点12 - 折叠",
                            children: [
                                { name:"叶子节点121"},
                                { name:"叶子节点122"},
                                { name:"叶子节点123"},
                                { name:"叶子节点124"}
                            ]},
                        { name:"父节点13 - 没有子节点", isParent:true}
                    ]},
                { name:"父节点2 - 折叠",
                    children: [
                        { name:"父节点21 - 展开", open:true,
                            children: [
                                { name:"叶子节点211"},
                                { name:"叶子节点212"},
                                { name:"叶子节点213"},
                                { name:"叶子节点214"}
                            ]},
                        { name:"父节点22 - 折叠",
                            children: [
                                { name:"叶子节点221"},
                                { name:"叶子节点222"},
                                { name:"叶子节点223"},
                                { name:"叶子节点224"}
                            ]},
                        { name:"父节点23 - 折叠",
                            children: [
                                { name:"叶子节点231"},
                                { name:"叶子节点232"},
                                { name:"叶子节点233"},
                                { name:"叶子节点234"}
                            ]}
                    ]},
                { name:"父节点3 - 没有子节点", isParent:true}

            ];

            $.fn.zTree.init($("#tree"), setting, zNodes);

        })
    },

    movEdit:function(){

        function addmiantree(){//添加主分类
            $(document).on("click",".newclass",function(){
                if($(this).parent().find(".tablearrow tbody").hasClass("tbody")){
                    $('.tablearrow').find('tbody:last').after(
                        '<tbody>'+
                            '<tr class="maintree">'+//主分类
			    '<input type="hidden" name="cateLevelAry" value="1"/>'+
                            '<input type="hidden" name="cateIconAry" id="cateIconAry" value=""/>'+
                            '<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value=""/>'+
                            '<td class="td_0"><input type="checkbox"class="ui-checkbox newclasschk mainclasschk" name="chkRefrenceId"/></td>'+
                            '<td class="td_1"><i id="trplus" class="main_iconsguan"></i></td>'+
                            '<td class="td_2"><input type="text"class="newclasstext"value="" name="cateNameAry"/></td>'+
                            '<td class="td_3"><span class="addsubclass"><i class="iconfont">&#xe615;</i>添加图片</span></td>'+
                            '<td class="td_4">' +
                            '<div class="movebox">' +
                            '<span class="movetop mainmovetop" title="顶部"></span>'+
                            '<span class="moveup mainmoveup" title="上移"></span>'+
                            '<span class="movedown mainmovedown" title="下移"></span>'+
                            '<span class="movebottom mainmovebottom" title="底部"></span>'+
                            '</div>' +
                            '</td>'+
                            '<td class="td_5"><span class="delete">删除</span></td>'+
                            '</tr>'+
                            '<tr class="subtree addsubtree">' +//添加子分类
                            '<td class="td_0"></td>' +
                            '<td class="td_1"></td>' +
                            '<td class="td_2"><div class="sub_zhe fl"></div><div class="js_add_sub">添加子分类</div></td>' +
                            '<td class="td_3"></td>' +
                            '<td class="td_4"></td>' +
                            '<td class="td_5"></td>' +
                            '</tr>'+
                            '</tbody>');
                    maintreemovechange();
                }
                if(!$(this).parent().find(".tablearrow tbody").hasClass("tbody")){
                    $('.tablearrow').append(
                        '<tbody class="tbody">'+
                            '<tr class="maintree">'+//主分类
                            '<input type="hidden" name="cateLevelAry" value="1"/>'+
                            '<input type="hidden" name="cateIconAry" id="cateIconAry" value=""/>'+
                            '<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value=""/>'+
                            '<td class="td_0"><input type="checkbox" class="ui-checkbox newclasschk mainclasschk" name="chkRefrenceId"/></td>'+
                            '<td class="td_1"><i id="trplus" class="main_iconsguan"></i></td>'+
                            '<td class="td_2"><input type="text" class="newclasstext" value="" name="cateNameAry"/></td>'+
                            '<td class="td_3"><span class="addsubclass"><i class="iconfont">&#xe615;</i>添加图片</span></td>'+
                            '<td class="td_4">' +
                            '<div class="movebox">' +
                            '<span class="movetop mainmovetop" title="顶部"></span>'+
                            '<span class="moveup mainmoveup" title="上移"></span>'+
                            '<span class="movedown mainmovedown" title="下移"></span>'+
                            '<span class="movebottom mainmovebottom" title="底部"></span>'+
                            '</div>' +
                            '</td>'+
                            '<td class="td_5"><span class="delete">删除</span></td>'+
                            '</tr>'+
                            '<tr class="subtree addsubtree">' +//添加子分类
                            '<td class="td_0"></td>' +
                            '<td class="td_1"></td>' +
                            '<td class="td_2"><div class="sub_zhe fl"></div><div class="js_add_sub">添加子分类</div></td>' +
                            '<td class="td_3"></td>' +
                            '<td class="td_4"></td>' +
                            '<td class="td_5"></td>' +
                            '</tr>'+
                            '</tbody>');
                    maintreemovechange();
                }
                keyupChange();
            });
        }
        addmiantree();

        function addsubtree(){//添加子分类
            $(document).on("click",".addsubtree",function(){
                $(this).parent().find('tr:last').before('' +
                    '<tr class="subtree subtreemove">' +
                    	'<input type="hidden" name="cateLevelAry" value="2"/>'+
                    	'<input type="hidden" name="cateIconAry" id="cateIconAry" value=""/>'+
                    	'<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value=""/>'+
                        '<td class="td_0"><input type="checkbox"class="ui-input newclasschk"></td>' +
                        '<td class="td_1"></td>' +
                        '<td class="td_2"><div class="sub_zhe inline-block fl"></div><input type="text"class="newclasstext"value="" name="cateNameAry"/></td>' +
                        '<td class="td_3"><span class="addsubclass"><i class="iconfont">&#xe615;</i>添加图片</span></td>' +
                        '<td class="td_4"><div class="movebox"><span class="moveup submoveup" title="上移"></span><span class="movedown submovedown" title="下移"></span></div></td>' +
                        '<td class="td_5"><span class="delete">删除</span></td>' +
                    '</tr>');
                subtreemovechange();
                keyupChange();
            });
        }
        addsubtree();

        function maintreemovechange(){//移动时主分类上下按钮的变化
            if($(".table tbody").children("tr.maintree").size()==1)
            {
                $(".table tbody").find("tr.maintree").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
            }
            if($(".table tbody").children("tr.maintree").size()==2)
            {
                $(".table tbody tr.maintree:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                $(".table tbody tr.maintree:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
            }
            if($(".table tbody").children("tr.maintree").size()>=3)
            {
                $(".table tbody tr.maintree:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
                $(".table tbody tr.maintree:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                $(".table tbody tr.maintree:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
            }
        }
        maintreemovechange();

        function subtreemovechange(){//移动时子分类上下按钮的变化
            $(".table").find("tbody").each(function(){
                if($(this).children("tr.subtreemove").size()==1)
                {
                    $(this).find("tr.subtreemove").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
                }
                if($(this).children("tr.subtreemove").size()==2)
                {
                    $(this).find("tr.subtreemove:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                    $(this).find("tr.subtreemove:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
                }
                if($(this).children("tr.subtreemove").size()>=3)
                {
                    $(this).find("tr.subtreemove:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
                    $(this).find("tr.subtreemove:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                    $(this).find("tr.subtreemove:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
                }
            });
        }
        subtreemovechange();

        function mainmoveTree(){//主分类上下移动样式
            $(document).on("click","span.mainmoveup",function(){//上移
                var onthis = $(this).parent().parent().parent().parent();
                var getup = $(this).parent().parent().parent().parent().prev();
                if($(this).parent().parent().parent().hasClass("subonly")||$(this).parent().parent().parent().hasClass("subfirst")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).before(onthis);
                maintreemovechange();
            });
            $(document).on("click","span.mainmovedown",function(){//下移
                var onthis = $(this).parent().parent().parent().parent();
                var getdown = $(this).parent().parent().parent().parent().next();
                if($(this).parent().parent().parent().parent().hasClass("sublast") || $(this).parent().parent().parent().parent().hasClass("subonly")){
                    //alert("底部元素不能下移");
                    return false;
                }
                $(getdown).after(onthis);
                maintreemovechange();

            });
            $(document).on("click","span.mainmovetop",function(){//顶部
                var onthis = $(this).parent().parent().parent().parent();
                var getup = $(this).parent().parent().parent().parent().parent().find("tbody:first");
                if($(this).parent().parent().parent().hasClass("subonly")||$(this).parent().parent().parent().hasClass("subfirst")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).before(onthis);
                maintreemovechange();
            });
            $(document).on("click","span.mainmovebottom",function(){//顶部
                var onthis = $(this).parent().parent().parent().parent();
                var getup = $(this).parent().parent().parent().parent().parent().find("tbody:last");
                if($(this).parent().parent().parent().hasClass("sublast")||$(this).parent().parent().parent().hasClass("subonly")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).after(onthis);
                maintreemovechange();
            });
        }
        mainmoveTree();

        function submoveTree(){//子分类上下移动样式
            $(document).on("click","span.submoveup",function(){//上移
                var onthis = $(this).parent().parent().parent();
                var getup = $(this).parent().parent().parent().prev();
                if(onthis.hasClass("subonly")||onthis.hasClass("subfirst")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).before(onthis);
                subtreemovechange();
            });
            $(document).on("click","span.submovedown",function(){//下移
                var onthis = $(this).parent().parent().parent();
                var getdown = $(this).parent().parent().parent().next();
                if(onthis.hasClass("sublast") || onthis.hasClass("subonly")){
                    //alert("底部元素不能下移");
                    return false;
                }
                $(getdown).after(onthis);
                subtreemovechange();

            });
        }
        submoveTree();

        $("#trplus").each(function(){//开关按钮样式的判断
            var hgff = $(this).parent().parent().nextAll();
            if($(hgff).is(':visible')){
                $(this).addClass('main_iconsguan');
            }else{
                $(this).removeClass().addClass('main_iconskai');
            }
        });

        $(document).on("click","#trplus",function(){//点击打开关闭
            var hgff = $(this).parent().parent().nextAll();
            $(hgff).toggle();
            if($(hgff).is(':visible')){
                $(this).addClass('main_iconsguan');
            }else{
                $(this).removeClass().addClass('main_iconskai');
            }
        });
        $(document).on("click",".look",function(){//查看按钮
            var hgff = $(this).parent().parent().nextAll();
            $(hgff).toggle();
            if($(hgff).is(':visible')){
                $(this).parent().parent().find("#trplus").addClass('main_iconsguan');
            }else{
                $(this).parent().parent().find("#trplus").removeClass().addClass('main_iconskai');
            }
        });

        $(document).on("click",".subtree td .delete",function(){//子菜单移除
            $(this).parent().parent().remove();
            $("#saveclasses").addClass("saveclass");//添加按钮可点击的class
            subtreemovechange();
        });

        $(document).on("click",".maintree td .delete",function(){//主菜单移除
            var threeP = $(this).parent().parent().parent();
            /*var fourP = $(this).parent().parent().parent().parent();//当主分类里面还有子分类的时候
             if($(this).parent().parent().parent().parent().find(".subtree").size() > 0){
             alert("该分类中还有子分类，不可删除！");
             return false;
             }*/
            threeP.remove();
            $("#saveclasses").addClass("saveclass");//添加按钮可点击的class
        });

        function keyupChange(){//输入监听文本框的变化
            $('.table tbody').keyup(function() {
                $("#saveclasses").addClass("saveclass");
            });
        }
        keyupChange();

        //全选
        $(document).on("click",".chkALL",function(){
            $('input[type=checkbox].newclasschk').prop("checked", this.checked );
        });
        $(document).on("click",".mainclasschk",function(){
            $(this).parent().parent().parent().find('input[type=checkbox].newclasschk').prop("checked", this.checked );
        });

        //批量删除2014-03-17
        $(document).on("click",".setdelall",function(){
            $('[name=chkRefrenceId]:checkbox:checked').each(function(){

                if($(this).parent().parent().hasClass("maintree")){
                    if (window.confirm("当前内容包含一级分类，删除一级分类会删除相应的子分类，确认删除吗?")) {
                        $(this).parent().parent().parent().remove();
                    } else {
                        return false;
                    }
                }
                else{
                    $(this).parent().parent().remove();
                }
            })
        });

        //最后保存分类的时候判断文本框是否为空
        var addtext =
            '<div class="js_linshi_box" style="position: relative">' +
                '<div style="background: #fef4e8;position: absolute;padding: 4px 5px; left: 195px;top:-27px">' +
                '<i class="inline-block" style="width:16px;height:16px;background: url(../../images/dealer/icons-home.png) no-repeat 0 -336px; vertical-align: -4px;margin-right: 5px"></i>' +
                '分类名称不能为空' +
                '</div>' +
            '</div>'
        $(document).on("click",".saveclass",function(){
        	var isBlank = true;
            $(".newclasstext").each(function(){
                var _this = $(this);
                $(this).parent().find(".js_linshi_box").remove();//判断之前先移除提示框
                if(_this.val() ==""){
                    //alert("空")
                    $(this).parent().append(addtext);
                    isBlank = false;
                }else{
                    $(this).parent().find(".js_linshi_box").remove();
                }
            });
            if(isBlank){
            	saveCatalog();
            }
        });
        $(document).on("blur",".newclasstext",function(){
            var _this = $(this);
            $(this).parent().find(".js_linshi_box").remove();//判断之前先移除提示框
            if(_this.val() ==""){
                //alert("空")
                $(this).parent().append(addtext);
            }else{
                $(this).parent().find(".js_linshi_box").remove();
            }
        }).on("keyup",".newclasstext",function(){
                var _this = $(this);
                $(this).parent().find(".js_linshi_box").remove();//判断之前先移除提示框
                if(_this.val() ==""){
                    //alert("空")
                    $(this).parent().append(addtext);
                }else{
                    $(this).parent().find(".js_linshi_box").remove();
                }
        });
    },

    canmovesee:function(){
        $(".tbale tbody tr").hover(function(){

        });
    },

    addpropic:function(){//添加图片
        var picHtml =
            '<div class="addProPicbox" style="position:relative;z-index: 9">' +
            '<div class="addProPic">' +
                '<h4>请选择分类图片上传</h4>' +
                '<div>' +
                    '<div class="inline-block">' +
                        '<div class="file_wrap">' +
                            '<a href="javascript:;" class="replace_text simple_button">上传图片</a>' +
                            '<input type="file" value="文件上传" name="photo" class="input_file" id="uploadFile" />' +
                        '</div>' +
                    '</div>' +
                '</div>' +
		'<a href="javascript:;" class="ui_button ui_button_sgray addpropicsure">确定</a>'+
                '<a href="javascript:;" class="ui_button ui_button_sgray addpropicansle">取消</a>' +
            '</div>';//添加图片的div
        var piclook =
            '<div class="piclook" style="position: relative">' +
                '<div class="piclookbox" style="background: #fff; position: absolute; width: 170px; height: 170px;z-index: 9; border: 1px solid #d9d9d9; top:0 ' +
                'top:10px;"><img src="http://placehold.it/170x170" alt=""/></div>' +
            '</div>';
        var already =
            '<span class="haspic">' +
                '<a href="javascript:;" class="haspic-edit">编辑</a>' +
                '<a href="javascript:;" class="haspic-del" style="margin-left: 5px">删除</a>' +
            '</span>' +
            '<div class="piclook" style="position: relative;display: none">' +
                '<div class="piclookbox" style="background: #fff; position: absolute; width: 170px; height: 170px;z-index: 9; border: 1px solid #d9d9d9; top:0;">' +
                '<img src="http://placehold.it/170x170" alt=""/>' +
                '</div>' +
            '</div>';

        $(document).on("click",".addsubclass",function(){//点击添加图片
            $(this).parents("table").find(".addProPicbox").remove();
            $(this).parent().append(picHtml);
        });

        $(document).on("click",".addpropicsure",function(){//点击确定
           // $(this).parent().parent().parent().html(already);
            ajaxFileUpLoad($(this),"uploadFile");
        });
        $(document).on("click",".addpropicansle",function(){//点击取消
            $(".addProPicbox").remove();
        });

        $(document).on("click",".haspic-del",function(){//点击删除
        	$(this).parents("tr").find("#cateIconAry").val("");
            var nopic = '<span class="addsubclass"><i class="iconfont">&#xe615;</i>添加图片</span>';
            $(this).parent().parent().html(nopic);
        });

        $(document).on("click",".haspic-edit",function(){//点击编辑
            $(this).parents("table").find(".addProPicbox").remove();
            $(this).parent().parent().append(picHtml);
        });

        $(document).on("mouseenter",".haspic-edit",function(){//移到编辑上面
            $(this).parent().parent().find(".piclook").show();
        });
        $(document).on("mouseout",".haspic-edit",function(){//离开编辑
            $(this).parent().parent().find(".piclook").hide();
        })

    },
    
    jstoclass:function(){

        var _this = this;
    	seajs.use(['popup'],function(Popup){

    		var jstoclasstip1 = new Popup({//批量分类
    			trigger: '.js-sel-toclass',
                element: '.js-toclass-showbox',
                align: {
                    baseXY: [-25, 30]
                }
    		})

    		var jstoclasstip = new Popup({//添加分类
    			trigger: '.js-addclass-triggerbtn',
                element: '.js-addclass-showbox',
                align: {
                    baseXY: [-25, 25]
                }
    		}).after("show",function(){
                _this.setCatalogFn(this.activeTrigger);
            })

    	})
    },
    addclassHover:function(){
    	/*$(".js-addclass-triggerbtn").mouseover(function(){
    		if($.isFunction(hoverSet)){//移上来
                hoverSet();
            }
    	});*/
    },
    comparePrice:function(){
        $(".short-width").blur(function(){
            var _val1 = parseFloat($(".short-width").eq(0).val());
            var _val2 = parseFloat($(".short-width").eq(1).val());
            if(_val1 > _val2){
                $(".short-width").eq(0).val(_val2);
                $(".short-width").eq(1).val(_val1);
            }
        });
    }
}

var selectcate = {
    init:function(){
        this.handleCategory();
        this.selectcate();
    },
    handleCategory: function(){
        if($("#select_category").size() == 0){
            return;
        }
        var _this = this;
        //这里的数据也可以异步请求
        $.each(_dealList,function(ind,item){//主分类
            var html = '<li id="'+ item.dealNo + '" class="item" name="'+ind+'"><span>'+item.dealName+'</span><i class="iconfont"></i></li>';
            $("#select_category .select_inner").append(html);
        })
        $("#select_category .select_inner").on("click",".item",function(){
        	$("#selectadd").val(0);
        	renderSelect("#selectadd");
            $("#select_category .item").removeClass("current");
            var i = parseFloat($(this).attr("name"));
            $(this).addClass("current");
            $("#select_class .select_inner").empty();
            $("#select_product .select_inner").empty();
            $.each(_dealList[i].children,function(ind,item){//子分类
                var html = '<li id="'+ item.dealNo + '" class="item" name="'+ind+'"><span>'+item.dealName+'</span><i class="iconfont"></i></li>';
                $("#select_class .select_inner").append(html);
            })
        });

               /* var category_arr = new Array();
                $.each(_dealList,function(ind,item){//主分类
                    category_arr[index] = {"id":item.id,"item":item.item};
                })
                var category_obj = selectcate.createItem(category_arr);
                category_obj.appendTo($("#select_category .select_inner"));
                $("#select_category").on("click",".item",function(){
                    $("#select_category .item").removeClass("current");
                    $(this).addClass("current");
                    //发起请求，得到二级类目，这里未做数据缓存，如果需要，则要一个cache变量来保存请求完的数据
                    $("#select_class .select_inner").empty();
                    $("#select_product .select_inner li").empty();

                    $.ajax({
                        type:"POST",
                        url:"/brand/product/subdic",
                        data:{"parentId":$(this).attr("data-id")},
                        dataType:"json",
                        cache:true,
                        success:function(subDics){
                            var class_arr = new Array();
                            for(var index in subDics){
                                class_arr[index] = {"id":subDics[index].refrenceId,"item":subDics[index].dealName,"dealNo":subDics[index].dealNo};
                            }
                            var class_obj = selectcate.createItem(class_arr);
                            class_obj.appendTo($("#select_class .select_inner"));
                        }
                    })


                });*/
                $("#select_class").on("click",".item",function(){
                    /*if($(this).attr("clicked") == "true"){
                     return;
                     }*/
                    var dealNo = $(this).attr("id");
                    $("#select_class .item").removeClass("current");
                    $(this).addClass("current");
                    $("#select_product .select_inner").empty();

                    var html = '<li class="item" data-dealno='+dealNo+'><span>'+$(this).find("span").html()+'</span></li>';
                    $("#select_product .select_inner").append(html);
                });




    },
    /**
     *
     * @param arr
     * @param type  默认为choose
     * @returns {*|jQuery|HTMLElement}
     */
    createItem: function(arr,type){
        var item_li = [];
        type = !type ? "choose": type;
        $.each(arr,function(index,obj){
            if(type == "choose"){
                item_li.push("<li data-dealno='"+obj.dealNo+"' data-id="+obj.id+" id='item_"+obj.id+"' class='item'><span>"+obj.item+"</span><i class='iconfont'>&#xe60f;</i></li>")
            }else if(type == "close"){
                item_li.push("<li data-dealno='"+obj.dealNo+"' data-id="+obj.id+" class='item'><span>"+obj.item+"</span><i class='iconfont close_icon'>&#xe602;</i></li>");
            }
        })
        return $(item_li.join(""));
    },
    selectcate:function(){
        $(".select_category").on("click",".item",function(){
            var value = $(this).html();
            $(".selectcatemain").html(value);
            $(".selectcatesub").html("");
            $(".selectcateproduct").html("");
        });
        $(".select_class").on("click",".item",function(){
            var value = $(this).html();
            $(".selectcatesub").html(value);
            $(".selectcateproduct").html("");
        });

        /*if($(".select_category").text() != ""){
         $(".arrow-select").show();
         }*/
    }
}

var rebateDetail = {
    init: function(){
        this.selectDate();
    },
    selectDate: function(){
        rangeCalendar("startDate1", "endDate1");
        rangeCalendar("startDate2", "endDate2");
    }
};