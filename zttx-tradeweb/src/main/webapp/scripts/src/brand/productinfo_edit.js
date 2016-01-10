//js 页面缓存
productmanage.dataCache={};

var cateTreeObj;
var brandTmpJson = [];
var tiggerSxFlag = false; //支持授信是否处于选中状态
var tiggerFdFlag = false; //支持返点是否处于选中状态
var isSpportFd   = $("#support_rebate").prop("checked"); //返点状态最开始的支持情况

//支持授信状态初始化
var changeCreditFlag = function(){
    if($("#supportCredit").prop("checked")){
        $(".js-tigger-sx").show();
        $("#js-setcommon-type option[value=3]").show().prop("disabled", false);
        tiggerSxFlag = true;
    }else{
        $(".js-tigger-sx").hide();
        $("#js-setcommon-type option[value=3]").hide().prop("disabled", true);
        tiggerSxFlag = false;
    }
};
changeCreditFlag();


var addNew_changeBrandTmp = function (brandsId){

    $.ajax({
        type: "POST",
        url: "/brand/product/brandTemplate",
        data: {"brandsId": brandsId},
        dataType: "json",
        cache: false,
        success: function (result) {
            var opStr = "<option value=''></option>";
            for (var index in result) {
                var id = result[index].refrenceId;
                brandTmpJson[id] = result[index];
                opStr += "<option value='"+id+"'>"+result[index].templateName+"</option>";
            }
            $("#ppchoice1").html(opStr);
            $("#ppchoice1").on("change",function(){
                var value = $(this).val();
                var tempJson = brandTmpJson[value];
                $("#orderName").val(tempJson.templateName);
                $("#startNum").val(tempJson.startNum);
                $("#orderNum").val(tempJson.orderNum);
                $("#orderMoney").val(tempJson.orderMoney);
                $("input[name='orderSelect']")[tempJson.orderSelect].checked = true;
                if(tempJson.orderSelect == 1){
                    $("#order_amount").show();
                }
                var outStart = new Date(tempJson.outStart);
                var outEnd = new Date(tempJson.outEnd);
                var orderStart = new Date(tempJson.orderStart);
                var orderEnd = new Date(tempJson.orderEnd);
                $("#start-cal2").val(outStart.getFullYear()+"-"+(outStart.getMonth()+1)+"-"+outStart.getDate());
                $("#end-cal2").val(outEnd.getFullYear()+"-"+(outEnd.getMonth()+1)+"-"+outEnd.getDate());
                $("#start-cal").val(orderStart.getFullYear()+"-"+(orderStart.getMonth()+1)+"-"+orderStart.getDate());
                $("#end-cal").val(orderEnd.getFullYear()+"-"+(orderEnd.getMonth()+1)+"-"+orderEnd.getDate());

            });
        }
    });


}
var addNew_loadAttr = function(){
    /*$("#proSaleAttr ul[id^='attrul_']").each(function(){
     var attrNo = $(this).attr("data-attrno");
     var productId = $(this).attr("data-proid");
     $.ajax({
     type: "POST",
     url: "/brand/product/ajax_mergeProAttrValue",
     data: {"attributeNo": attrNo,"productId":productId},
     dataType: "json",
     cache: false,
     success: function (result) {
     addNew.dataset(result);
     *//*var checkBoxName = "attrbox_"+attrNo;
     var liStr = "";
     for(var index in result){
     var attrValue = result[index];
     liStr += "<li><input type='checkbox' name='"+checkBoxName+"' class='ui-checkbox colorchk' id='"+attrValue.refrenceId+"' />" +
     "<label for='"+attrValue.refrenceId+"'><span class='colorfont'>"+attrValue.attributeValue
     +"</span></label><input type='text' name='getext' class='getext' value='"+attrValue.attributeValue+"'>"+"</li>";
     }
     $("#attrul_"+attrNo).append(liStr);*//*
     }
     })
     });
     $("#proAttr select[id^='attrsel_']").each(function(){
     var attrNo = $(this).attr("data-attrno");
     var productId = $(this).attr("data-proid");
     var $sel = $(this);
     $.ajax({
     type: "POST",
     url: "/brand/product/ajax_mergeProAttrValue",
     data: {"attributeNo": attrNo,"productId":productId},
     dataType: "json",
     cache: false,
     success: function (result) {
     var html = "";
     for(var i in result){
     var attr = result[i];
     html += "<option value='"+attr.refrenceId;
     if(attr.selected){
     html += " selected='selected' ";
     }
     html += "'>"+attr.attributeValue+"</option>";
     }
     $sel.html(html);
     }
     })
     });*/
};
var addNew_setProCate = function(cateIds,brandesId){
    $.ajax({
        type: "POST",
        url: "/brand/product/catalogTree",
        data: {"brandesId": brandesId},
        dataType: "json",
        cache: false,
        success: function (result){

            var zNodes=result;

            seajs.use(['ztree'], function (ztree) {
                var setting = {
                    check: {
                        enable: true,
                        chkboxType: { "Y": "ps", "N": "s" }
                    },
                    view: {
                        showIcon: false
                    }
                };

                cateTreeObj = $.fn.zTree.init($("#catalog"), setting, zNodes);
                cateTreeObj.expandAll(true);
                //选中
                if(cateIds!=""){
                    var cateIdArr = cateIds.split(",");
                    var nodes = cateTreeObj.transformToArray(cateTreeObj.getNodes());
                    for(var nodeIndex in nodes){
                        var node = nodes[nodeIndex];
                        for(var i in cateIdArr){
                            if(cateIdArr[i]==node.id){
                                cateTreeObj.checkNode(node, true);
                                break;
                            }
                        }
                    }
                }
                //$('.js_scllobox').jscrollbar();
            });
        }
    })
};
var addNew_setProLine = function(refrenceId,brandesId){
    $.ajax({
        type: "POST",
        url: "/brand/product/lineTree",
        data: {"brandesId": brandesId,"productId":refrenceId},
        dataType: "json",
        cache: false,
        success: function (result){
            var liArr = new Array();
            var lineIds = "";
            for(var index in result){
                //var lineIds = ","+lineIds+",";
                var line = result[index];
                var liStr = "<li><input type='checkbox' class='ui-checkbox' name='lineIdAry' value='"+line.refrenceId+"' id='"+line.refrenceId+"'";
                if(line.selected){
                    liStr += " checked='checked'";
                }

                /*if(lineIds.indexOf(","+line.refrenceId+",")!=-1){
                 liStr += " checked='checked'";
                 }*/
                liStr+=" /><a href='javascript:;'>"+line.lineName+"</a></li>";

                liArr[index] = liStr;
            }
            $("#proLine_ul").html(liArr.join(""));
            $("#proLine_ul a").click(function(){
                $(this).parent().find("input").click();
            });
        }
    })
};
var addNew_formSubmit = function(){
    var cateNodes = cateTreeObj.getCheckedNodes();
    var cateIds = new Array();
    var count = 0;
    for(var index in cateNodes){
        var node = cateNodes[index];
        if(node.children.length==0){
            cateIds[count] = node.id;
            count++;
        }
    }
    $("#cateIds").val(cateIds.join(","));//产品分类id串
    var $lineSel = $("#proLine_ul input:checked");
    var lineIds="";
    $lineSel.each(function(){
        if(lineIds!=""){
            lineIds +=",";
        }
        lineIds += this.id;
    });
    $("#lineIds").val(lineIds);//产品线id串
    $("#brandsName").val($("#ppchoice :selected").text());

    setAttrFormData();
    setProAttrPriceFormData();

    /*var imgValidate = $("#imgValidate").val();
     if(imgValidate==""){
     remind("error","产品主图不能为空。");
     }
     if($("#myEditor").val()==""){
     remind("error","产品描述不能为空。");
     }*/
    /*   var groomTotal = $("#groomTotal").data("groom");
     var groomUsed = $("#groomUsed").data("groom");
     var groomChecked = $("input[name='productGroom']").prop("checked");
     if(groomChecked){
     if(groomTotal>groomUsed){
     $("#groomCheck").val(groomUsed+1);
     }
     }else{
     $("#groomCheck").val(groomUsed);
     alert(groomUsed);
     }*/

//    $("#imgValidate").val($("input[name='imageUrls']").val());//为验证图片用


};

/**
 * 显示 自定义 属性模块
 * */

productmanage.showCustomerTip=function(){
    var attrStatus=true;
    var attrItems=[];
    $("#proSaleAttr ul.coloreditul").each(function(){
        attrStatus&=$(this).find(":checked").size()!=0;
        attrItems.push({name:$(this).data("name"),chks:$(this).find(":checked")});
    });
    //clickSetNum();
    var showConfig=[];
    // 如果为真 表示每个属性类至少 有一个属性值被选中
    if(!attrStatus){
        //未选择颜色和尺码
        if($(".coloreditul").length==1){
            $("div.notalltip").hide();
        }else{
            $("div.notalltip").show();
        }
        $("#selectmix").hide();
        $("#selectmix tbody").empty();
        //20140604 修改table的布局
        $(".tablelong-auto").hide();
    }else{
        $("div.notalltip").hide();
        $("#selectmix").show();
        //20140604 修改table的布局
        $(".tablelong-auto").show();
        for(var i=0;i<attrItems.length;i++){
            $("#selectmix thead tr th:eq("+i+")").html(attrItems[i]['name']);
        };
        var _name="";
        if($(".coloreditul").length > 1){
        	var x=0;
            attrItems[0]['chks'].each(function(i,o){
                var attr1=$(this);
                //console.log(attr1);
                var attrIndex1 = i;
                //console.log(attrIndex1);
                attrItems[1]['chks'].each(function(j){
                    var attr2=$(this);
                    //console.log(attr2);
                    var arrIndex2 = j, attrIndex = attrIndex1.toString() + arrIndex2.toString();
                    var a1 = "{'a':'" + attr1.data("type") + "','v':'" + attr1.data("text") + "','vid':'" + attr1.data("id") + "'}";
                    var a2 = "{'a':'" + attr2.data("type") + "','v ':'" + attr2.data("text") + "','vid':'" + attr2.data("id") + "'}";
                    //console.log(a1);
                    //console.log(a2);
                    var html='[' + a1 + ',' + a2 + ']';//console.log(html);
                    var value_key=[];

                    value_key.push(attr1.data("type")+"_"+attr1.data("id"));
                    value_key.push(attr2.data("type")+"_"+attr2.data("id"));
                    var vals = productmanage.dataCache[value_key.join("-")];
                    //console.log(vals);
                    //console.log(value_key);
                    //console.log($("span[name=attrcomb_2795_36A4EFB656A74A879CE18B1719120CBD]"));
                    if(vals==null){
                        vals=[0,0,0,'',0,0];
                    }
                    showConfig.push('<tr style="display: table-row; *display:block;">');
                    _name=attr1.data("text");
                    showConfig.push('<td width="90"><span class="colorfont" name="attrcomb_'+attr1.data("type")+'_'+attr1.data("id")+'">'+attr1.data("text")+'</span><input type="hidden" name="attr_color_super_ids" value="' + attr1.data("type") + '"/><input type="hidden" name="attr_color_ids" value="' + attr1.data("id") + '" /><input type="hidden" name="attr_color_values" data-id="color_value_' + attr1.data("type") + '_' + attr1.data("id") +'" value="' + attr1.data("text") + '" /></td>');
                    showConfig.push('<td width="90"><span class="colorfont" name="attrcomb_'+$(this).data("type")+'_'+$(this).data("id")+'">'+attr2.data("text")+'</span><input type="hidden" name="attr_size_super_ids" value="' + attr2.data("type") + '"/><input type="hidden" name="attr_size_ids" value="' + attr2.data("id") + '" /><input type="hidden" name="attr_size_values" data-id="size_value_' + attr2.data("type") + '_' + attr2.data("id") +'" value="' + attr2.data("text") + '" /></td>');
                    showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input wantnumber js-price" name="attr_combo_price" style="width:65px;" value='+(vals[0]==null?0.00:parseFloat(vals[0]).toFixed(2))+'></td>');
                    showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input js-price" name="attr_combo_direct_price" style="width:65px;" value='+(vals[2]==null ? 0.00:parseFloat(vals[2]).toFixed(2))+' ></td>');
                    showConfig.push('<td width="90" class="js-tigger-sx" style="' + (tiggerSxFlag == false ? 'display: none;' : '') + '"><input type="text" data-index=' + '"' + html+ '"' + ' data-sx="' + attr1.data("text") + attr2.data("text") + '" class="ui-input js-price" name="attr_combo_credit_price" style="width:65px;" value='+(vals[4]==null ? 0.00:parseFloat(vals[4]).toFixed(2))+'></td>');
                    showConfig.push('<td width="90" class="js-tigger-fd" style="' + (tiggerFdFlag == false ? 'display: none;' : '') + '"><input type="text" data-index=' + '"' + html+ '"' + ' data-sx="' + attr1.data("text") + attr2.data("text") + '" class="ui-input js-price" name="attr_combo_rebate_price" style="width:65px;" value="'+(vals[5]==null ? 0.00:parseFloat(vals[5]).toFixed(2))+'" data-oldprice="'+(vals[6]==null ? 0.00:parseFloat(vals[6]).toFixed(2))+'"></td>');
                    showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input wantnumber js-price" data-max="100000000" style="width:65px;" name="attr_combo_count" value='+(vals[1]==null ?0:vals[1])+'></td>');
                    showConfig.push('<td width="100"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input" name="attr_combo_bar_code" style="width:80px;" value='+(vals[3]==null? " ":vals[3])+'></td>');
                    //showConfig.push('<td width="199"><a class="bluefont js_setcomprice">批量设为本价格</a> <a class="bluefont js_setcomnumber">批量设为本数量</a></td>');
                    showConfig.push('</tr>');
                });
            });
        }else if($(".coloreditul").length == 1){
            attrItems[0]['chks'].each(function(i){
                var attr1=$(this);
                var attrIndex = i;
                var a1 = "{'a':'" + attr1.data("type") + "','v':'" + attr1.data("text") + "','vid':'" + attr1.data("id") + "'}";
                var html='['+a1+']';
                var value_key=[];
                value_key.push(attr1.data("type")+"_"+attr1.data("id"));
                var vals=productmanage.dataCache[value_key.join("-")];

                if(vals==null){
                    vals=[0,0,0,'',0,0];
                }
                showConfig.push('<tr style="display: table-row;*display:block;">');
                showConfig.push('<td width="90"><span class="colorfont" name="attrcomb_'+attr1.data("type")+'_'+attr1.data("id")+'">'+attr1.data("text")+'</span><input type="hidden" name="attr_color_super_ids" value="' + attr1.data("type") + '"/><input type="hidden" name="attr_color_ids" value="' + attr1.data("id") + '" /><input type="hidden" name="attr_color_values" data-id="color_value_' + attr1.data("type") + '_' + attr1.data("id") +'" value="' + attr1.data("text") + '" /></td>');
                showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input wantnumber js-price" name="attr_combo_price" value='+(vals[0]==null?0.00:parseFloat(vals[0]).toFixed(2))+'></td>');
                showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input js-price" name="attr_combo_direct_price" value='+(vals[2]==null? 0.00:parseFloat(vals[2]).toFixed(2))+'></td>');
                showConfig.push('<td width="90" class="js-tigger-sx" style="' + (tiggerSxFlag == false ? 'display: none;' : '') + '"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input js-price" name="attr_combo_credit_price" data-sx="' + attr1.data("text") + '" value='+(vals[4]==null? 0.00:parseFloat(vals[4]).toFixed(2))+' ></td>');
                showConfig.push('<td width="90" class="js-tigger-fd" style="' + (tiggerFdFlag == false ? 'display: none;' : '') + '"><input type="text" data-index=' + '"' + html+ '"' + ' data-sx="' + attr1.data("text") + '" class="ui-input js-price" name="attr_combo_rebate_price" style="width:65px;" value="'+(vals[5]==null ? 0.00:parseFloat(vals[5]).toFixed(2))+'" data-oldprice="'+(vals[6]==null ? 0.00:parseFloat(vals[6]).toFixed(2))+'"></td>');
                showConfig.push('<td width="90"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input wantnumber js-price" data-max="100000000" name="attr_combo_count" value='+(vals[1]==null?0:vals[1])+'></td>');
                showConfig.push('<td width="100"><input type="text" data-index=' + '"' + html+ '"' + ' class="ui-input" name="attr_combo_bar_code" style="width:80px;" value='+(vals[3]==null? " ":vals[3])+'></td>');
                //showConfig.push('<td width="200"><a class="bluefont js_setcomprice">批量设为本价格</a> <a class="bluefont js_setcomnumber">批量设为本数量</a></td>');
                showConfig.push('</tr>');
                for(i=0;i<5;i+=2){
                    if(!/\./.test(vals[i])) //为整数字符串在末尾添加.00
                        vals[i] += '.00';
                }
            });
            //$("#selectmix").width("581px");
        }

        $("#selectmix tbody").empty().append(showConfig.join(""));
        $("#selectmix tbody input.js-price").isPrice();
        $("#selectmix tbody input.js-number").isPrice(false);

        //检查返点是怎么写的，如果是 10%，则需要禁用返点价单独修改
        productmanage.setEditDisabled($(".js-rebate-pro:checked").data("type"));
    }
    /**
     * 用以控制 产品直供价 必填或选填
     */
    /*var directPriceTpl_1 = " 产品直供价：&nbsp;&nbsp;";
    var directPriceTpl_2 = " 产品直供价：<span class=\"ui-form-required\">*</span> ";
    if($("#selectmix tbody tr").length > 0){
        $(".js-no-directprice .ui-label").html(directPriceTpl_1);
    }else{
        $(".js-no-directprice .ui-label").html(directPriceTpl_2);
    }*/
};

/**
 * 显示 颜色自定义
 * */
productmanage.showColorConfig=function(){
    var isColorShow=false;
    var colorConfig=[];
    $("#proSaleAttr ul").each(function(){
        if($(this).data("color")){
            $(this).find(":checked").each(function(i){
                isColorShow=true;
                var attrId = $(this).data("id");
                var attrType = $(this).data("type");
                var $uploadImg = $("#colorImgUrls_"+attrId);
                var attrIcon = "";
                var domainName = $(this).data("domain");
                if($uploadImg.size()!=0){
                    attrIcon = $uploadImg.val();
                }
                if(attrIcon==""){
                    attrIcon = $(this).data("icon");
                }
                colorConfig.push('<tr class="colorshow" style="display: table-row;*display:block;">');
                colorConfig.push('<td class="td-first-Bor">');
                if(attrIcon.toString().length <= 6 ){
                    colorConfig.push('    <div id="ico_fileUp_'+attrId+'" style="background:#' + attrIcon + ';margin-left: 5px;" class="colorpickbox inline-block"></div>');
                }else{
                    //attrIcon =window.IMAGE_DOMIAN+attrIcon;
                    colorConfig.push('    <div id="ico_fileUp_'+attrId+'" style="background-image:url('+window.IMAGE_DOMIAN+attrIcon+');background-repeat:no-repeat;background-size:16px 16px;margin-left: 5px;" class="colorpickbox inline-block"></div>');
                }
                colorConfig.push('    <span class="colorfont" id="attrtext_'+attrType+'_'+attrId+'">'+$(this).data("text")+'</span>');
                colorConfig.push('<input type="hidden" id="colorImage_ids_' + attrType + '_' + attrId + '" name="attr_colorImage_ids" value="' + attrId +'" />');
                colorConfig.push('<input type="hidden" id="colorImage_urls_' + attrType + '_' + attrId + '" name="attr_colorImage_urls" value="' + attrIcon + '" />');
                colorConfig.push(' </td>');
                colorConfig.push('<td>');
                colorConfig.push('    <div class="file_wrap inline-block">');
                colorConfig.push('          <p class="simple_button">文件上传</p>');
                colorConfig.push('         <input type="file" value="文件上传" name="photo" id="fileUp_'+attrId+'" onchange="colorImgUpload(this);" class="input_file" data-type='+attrType+' data-id='+attrId+'>');
                colorConfig.push('     <div class="data" style="display: none;" id="div_fileUp_'+attrId+'"><input type="hidden" id="colorImgUrls_'+attrId+'" name="colorImgUrls" value='+attrIcon+' /><input type="hidden" id="colorImgNames_'+attrId+'" name="colorImgNames" value="'+domainName+'" /></div>');
                colorConfig.push('     </div>');
                colorConfig.push('     <a class="simple_button js-selectgroup-pic" onclick="setPosition(this);">图库选择</a>');
                colorConfig.push('     <a href="javascript:;" onclick="delColorImage(this);" class="orangefont">移除图片</a>');
                colorConfig.push(' </td>');
                colorConfig.push('</tr>');
            });
        }
    });
    if(isColorShow){
        $("#selectchicun tbody").empty().append(colorConfig.join(""));
        $("#selectchicun").show();
    }else{
        $("#selectchicun").hide();
    }
    addNew.selectgroup();
};
/**
 * 全选事件
 * */
productmanage.checkAllAttr=function(){
    var key=$(this).attr("id").split("_")[1];
    var state=$(this).prop("checked");
    $("#attrul_"+key+" :checkbox").not(":disabled").each(function(){
        $(this).prop("checked",state);
        if($(this).prop("checked") == true){
            $(this).parent().find(".colorfont").hide();
            $(this).parent().find(".getext").css("display","inline-block");
        }
        if($(this).prop("checked") == false){
            $(this).parent().find(".colorfont").show();
            $(this).parent().find(".getext").hide();
        }
    });
    productmanage.showCustomerTip();
    productmanage.showColorConfig();
};

/**
 * 编辑组属性时
 * */
productmanage.editAttrCombo=function($this){

    var cache_key=[];
    var val1=$this.parent().parent().find("input[name=attr_combo_price]").val();
    var val2=$this.parent().parent().find("input[name=attr_combo_count]").val();
    var val3=$this.parent().parent().find("input[name=attr_combo_direct_price]").val();
    var val4=$this.parent().parent().find("input[name=attr_combo_bar_code]").val();
    var val5=$this.parent().parent().find("input[name=attr_combo_credit_price]").val();
    var val6=$this.parent().parent().find("input[name=attr_combo_rebate_price]").val();
    var dataIndexArr = eval('(' + $this.data("index") + ')');
    $.each(dataIndexArr, function(i, obj){
        cache_key.push(obj.a+"_"+obj.vid);
    });
    productmanage.dataCache[cache_key.join("-")]=[val1,val2,val3,val4,val5,val6];
//	console.debug(productmanage.dataCache);
};
/**
 * 属性名字编辑事件
 * */
productmanage.attrEdit=function(){
    var _attr_flags=$(this).attr("id").split("_");
    $("#attrtext_"+_attr_flags[1]+"_"+_attr_flags[2]).html($(this).val());
    $("#attrshow_"+_attr_flags[1]+"_"+_attr_flags[2]).html($(this).val());
    $("span[name=attrcomb_"+_attr_flags[1]+"_"+_attr_flags[2]+"]").html($(this).val());
    //$("#attrcomb_"+_attr_flags[1]+"_"+_attr_flags[2]).html($(this).val());
    $("#attr_chk_"+_attr_flags[1]+"_"+_attr_flags[2]).data('text',$(this).val());
    //写颜色value
    $("input[data-id=color_value_" + _attr_flags[1] + "_" + _attr_flags[2] + "]").val($(this).val());
    //写尺寸value
    $("input[data-id=size_value_" + _attr_flags[1] + "_" + _attr_flags[2] + "]").val($(this).val());
};

/**
 * 解析 组合规格的 初始值
 */
productmanage.parseValue=function(){
    if($("#init_value").size()!=0){
        var obj=eval('('+$("#init_value").text()+')');
        //var obj = $.parseJSON($("#init_value").text());
        for(var i=0;i<obj.length;i++){
            var key=[];
            for(var j=0;j<obj[i]['z'].length;j++){
                key.push(obj[i]['z'][j]['a']+"_"+obj[i]['z'][j]['vid']);
            }
            //索引为6的数值: 最开始的返点价
            if(obj[i]['fdold'] == undefined){
                productmanage.dataCache[key.join("-")]=[obj[i]['p'],obj[i]['s'],obj[i]['dp'],obj[i]['bc'],obj[i]['cp'],obj[i]['fd'],obj[i]['fd']];
            }else{
                productmanage.dataCache[key.join("-")]=[obj[i]['p'],obj[i]['s'],obj[i]['dp'],obj[i]['bc'],obj[i]['cp'],obj[i]['fd'],obj[i]['fdold']];
            }
        }

        productmanage.showCustomerTip();
        productmanage.showColorConfig();
    }
};
/**
 *点击批量设置时数据新加数据拼接
 **/
function clickSetNum(){
    var len = $(".maintbody tr").length;
    var data = '[';
    for(var i=0;i<len;i++){
        var price = $(".maintbody tr").eq(i).find("input[name=attr_combo_price]").val();
        var count = parseFloat($(".maintbody tr").eq(i).find("input[name=attr_combo_count]").val());
        var comboCc = $(".maintbody tr").eq(i).find("input[name=attr_combo_direct_price]").val();
        var comboCreditPrice = $(".maintbody tr").eq(i).find("input[name=attr_combo_credit_price]").val();
        var comboBc = $(".maintbody tr").eq(i).find("input[name=attr_combo_bar_code]").val();
        var fdPrice = $(".maintbody tr").eq(i).find("input[name=attr_combo_rebate_price]").val();
        var fdOldPrice = $(".maintbody tr").eq(i).find("input[name=attr_combo_rebate_price]").data("oldprice");
        var priceIndex = $(".maintbody tr").eq(i).find("input[name=attr_combo_price]").data("index");
        var dataIndexArr = eval('(' + priceIndex + ')');
        data += '{"p":"'+price+'","s":'+count+',"dp":"'+comboCc+'","cp":"'+comboCreditPrice+'","bc":"'+comboBc+'","fd":"'+fdPrice+'","fdold":"'+fdOldPrice+'","z":[';
        $.each(dataIndexArr,function(ind,item){
            data += '{"a":"' + item.a + '","v":"' + item.v +'","vid":"' + item.vid + '"}';
            if(ind<1){
                data += ',';
            }
        });
        data += ']}';
        if(i<(len-1)){
            data +=',';
        }
    }
    data += ']';

    if( $("#init_value").length <= 0){
        $("body").append('<div id="init_value" style="display: none;">'+data+'</div>');
    }else{
        $("#init_value").html(data);
    }
    productmanage.parseValue();
}
productmanage.batchSetting = function(){
    $("input[name=dprice_n]").click(function(){
        var reviseZprice = $(".js-revise-zprice");
        if($(".js-revise-zjradio").prop("checked") == true){
            reviseZprice.attr("maxlength", 8).removeClass("setcommon-error");
        }else{
            var val = reviseZprice.val();
            reviseZprice.attr("maxlength", 4);
            if(val <= 0 || val > 10){
                reviseZprice.addClass("setcommon-error");
            }
        }
    });
    $(".js-revise-zprice").change(function(){
        var val = $(this).val();
        if($(".js-revise-zkradio").prop("checked") == true){
            if(val <= 0 || val > 10){
                $(this).addClass("setcommon-error");
                remind("error", "折扣必须大于0小于10");
            }else{
                $(this).removeClass("setcommon-error");
            }
        }else{
            $(this).removeClass("setcommon-error");
        }
    });

    $(document).on("click", ".js-revise-set", function(){
        var dprice = $(".js-revise-dprice").val() == "" ? 0 : parseFloat($(".js-revise-dprice").val()); //吊牌价
        var zprice = $(".js-revise-zprice").val() == "" ? 0 : parseFloat($(".js-revise-zprice").val()); //直供价
        var cprice = $(".js-revise-cprice").val() == "" ? 0 : parseFloat($(".js-revise-cprice").val()); //授信价
        var dcount = $(".js-revise-dcount").val(); //库存
        var dpType = $(".js-revise-zjradio").prop("checked") == true ? 0 : 1; // 0: 直接设置， 1：折扣设置
        if(dprice != 0){
            $("input[name^=attr_combo_price]").val(dprice.toFixed(2));
        }
        $("#selectmix .maintbody tr").each(function(){
            if(dpType == 0){
                if(zprice != ""){
                    $("input[name=attr_combo_direct_price]").val(zprice.toFixed(2));
                }
            }else if(dpType == 1){
                if(zprice != ""){
                    if(zprice != 0 && zprice <= 10){
                        if(dprice != 0){
                            $(this).find("input[name=attr_combo_direct_price]").val((dprice * zprice / 10).toFixed(2));
                        }else{
                            var ddprice = $(this).find("input[name=attr_combo_price]").val() == "" ? 0 : $(this).find("input[name=attr_combo_price]").val();
                            $(this).find("input[name=attr_combo_direct_price]").val((ddprice * zprice / 10).toFixed(2));
                        }
                    }else{
                        remind("error", "折扣必须大于0小于10");
                    }
                }
            }
        });
        if(dcount != ""){
            $("input[name=attr_combo_count]").val(dcount);
        }
        if(cprice!=0){
            $("input[name=attr_combo_credit_price]").val(cprice.toFixed(2));
        }
        //批量设置之后去写一下缓存
        clickSetNum();
        //批量设置完都清空原来的值
        $(".js-revise-dprice").val("");
        $(".js-revise-zprice").val("");
        $(".js-revise-dcount").val("");
        $(".js-revise-cprice").val("");
    });
};

/**
 * bug #6756 新增库存生效可选方式 实时监测
 */
productmanage.realModifyPrice = function(){
    var _cacheObjSky = {};
    $("#selectmix input[name=attr_combo_credit_price]").each(function(){
        var _index = $(this).data("sx");
        _cacheObjSky["sx_" + _index] = {};
        _cacheObjSky["sx_" + _index].op = $(this).val();
    });

    $("#selectmix").on("blur", "input[name=attr_combo_credit_price]", function(){
        var _index = $(this).data("sx");
        if(_cacheObjSky["sx_" + _index]){
            _cacheObjSky["sx_" + _index].np = $(this).val();
            _checkModifyPrice();
        }
    });

    function _checkModifyPrice(){
        //比较所有修改过授信价的SKU，如果新价格小于旧价格，则 “当前终端商的库存生效” 不能选
        var $costPushRadio = $("input[name=costPush]");
        for(var key in _cacheObjSky){
            //console.log();
            if(_cacheObjSky[key].np != undefined){
                if(parseFloat(_cacheObjSky[key].op) > parseFloat(_cacheObjSky[key].np)){
                    $costPushRadio.eq(0).prop({
                        checked: false,
                        disabled: true
                    });
                    $costPushRadio.eq(1).prop("checked", true);
                    return false;
                }
            }
        }
        $costPushRadio.eq(0).prop({
            disabled: false
        });
    }
};
//返点需求相关操作
productmanage.rebate = function(){

    function supportRebate(){
        if($("#support_rebate").prop("checked") == true){
            $(".js-rebate-display,.js-tigger-fd").show();
            tiggerFdFlag = true;
        }else{
            $(".js-rebate-display,.js-tigger-fd").hide();
            tiggerFdFlag = false;
        }
    }
    supportRebate();

    $("#support_rebate").click(function(){
        supportRebate();
    });

    $(".js-rebate-pro").click(function(){
        var _type = $(this).data("type");
        productmanage.setEditDisabled(_type);
    });

    $(".js-rebate-other").on("change", function(){
        var _val = parseFloat($(this).val());
        if(_val <= 10 || _val > 100){
            $(this).addClass("setcommon-error");
            remind("error", "返点比例大于10%, 不能高于 100%");
        }else{
            $(this).removeClass("setcommon-error");
        }
    });

    //设置
    $(".js-rebate-set").click(function(){
        var _type = $(".js-rebate-pro:checked").data("type");
        var p_fd;
        if(_type == 0){
            p_fd = parseFloat($("#select_rebate option:selected").val()) ? parseFloat($("#select_rebate option:selected").val()) : "0.00";
        }else if(_type == 1){
            p_fd = parseFloat($(".js-rebate-setext").val()) ? parseFloat($(".js-rebate-setext").val()) : "0.00";
        }
        if(p_fd != "0.00" || p_fd != 0){
            $("input[name=attr_combo_rebate_price]").val(p_fd.toFixed(2));
        }

        //批量设置之后去写一下缓存
        clickSetNum();
    });
};
productmanage.setEditDisabled = function(type){
    if(type == 0){
        $(".js-rebate-other,.js-rebate-setext").prop("disabled", true);
        $("#select_rebate").prop("disabled", false);
        $("input[name=attr_combo_rebate_price]").prop("readonly", true);
    }else if(type == 1){
        $(".js-rebate-other,.js-rebate-setext").prop("disabled", false);
        $("#select_rebate").prop("disabled", true);
        $("input[name=attr_combo_rebate_price]").prop("readonly", false);
    }
}

/**
 * 文档载入完成时调用
 */
$(function(){

    $("input[id^='attrCheckedAll']").click(productmanage.checkAllAttr);
    $("input[id^='attredit']").change(productmanage.attrEdit);
    $("#selectmix").on("change","input[name^='attr_combo']:not(input[name=attr_combo_count],input[name=attr_combo_bar_code])", function(){
        productmanage.editAttrCombo($(this));
        //二期追加的sku验证取消
        var _val = parseFloat($(this).val()) ? parseFloat($(this).val()) : 0;
        if(_val > 0){
            $(this).removeClass("input-error");
        }else{
            $(this).addClass("input-error");
        }
        $(this).val(_val.toFixed(2));
    });
    $("#samplePrice").blur(function(){
        var _val = parseFloat($(this).val()) ? parseFloat($(this).val()) : 0;
        if (_val > 0) {
            $(this).removeClass("input-error");
        } else {
            $(this).addClass("input-error");
        }
        $(this).val(_val.toFixed(2));
    });
    //批量设置
    productmanage.batchSetting();
    //返点需求
    productmanage.rebate();

    /*二期修改，首次渲染页面的时候，延迟一下*/
    //productmanage.parseValue();
    setTimeout(function(){
        productmanage.parseValue();
        //实现实时监测库存生效可选方式
        productmanage.realModifyPrice();
    }, 500);


    /*$("#selectmix").on("click","a.bluefont.js_setcomprice",function(){
        clickSetNum();
    });
    $("#selectmix").on("click","a.bluefont.js_setcomnumber",function(){
        clickSetNum();
    });*/

    $(".productadd").on("click",".colorchk",function(){
        if($(this).prop("checked") == true){
            $(this).parent().find(".colorfont").hide();
            $(this).parent().find(".getext").css("display","inline-block");
        }
        if($(this).prop("checked") == false){
            $(this).parent().find(".colorfont").show();
            $(this).parent().find(".getext").hide();
        }
        productmanage.showCustomerTip();
        productmanage.showColorConfig($(this));
    });

    $(".productadd").on("click", "#supportCredit", function(){
        changeCreditFlag();
    });

    
});
//属性表单数据设置
function setAttrFormData(){
    $("#saleAttrFormData").html("");
    $("#proAttrFormData").html("");
    $("#proSaleAttr input[id^='attr_chk_']:checked").each(function(){
        var index = $("#saleAttrFormData div.data").size();
        var prefix = "saleAttrs["+index+"]";
        var attrNo = $(this).attr("data-type");
        var valueId = $(this).attr("data-id");
        var value = $(this).parent().find("input.getext").val();
        var icon = $("#colorImgUrls_"+valueId).val();
        var domainName = $("#colorImgNames_"+valueId).val();
//        alert(value+"--"+icon);
        if(icon==undefined){
            icon="";
        }
        var html = "<div class='data' >";
        html += "<input type='text' name='"+prefix+".attributeNo' value='"+attrNo+"' >";
        html += "<input type='text' name='"+prefix+".attributeValue' value='"+value+"' >";
        html += "<input type='text' name='"+prefix+".valueId' value='"+valueId+"' >";
        html += "<input type='text' name='"+prefix+".attributeIcon' value='"+icon+"' >";
        html += "<input type='text' name='"+prefix+".domainName' value='"+domainName+"' >";
        html +="</div>";
        $("#saleAttrFormData").append(html);
    });
    //产品属性设置待续
    $("#proAttribute input.ui-checkbox:checked,#proAttribute select").each(function(){
        var index = $("#proAttrFormData div.data").size();
        var prefix = "attributes["+index+"]";
        var attrNo = $(this).attr("data-attrno");
        var valueId = $(this).val();
        var html = "<div class='data' >";
        html += "<input type='text' name='"+prefix+".attributeNo' value='"+attrNo+"' >";
        html += "<input type='text' name='"+prefix+".attrValueId' value='"+valueId+"' >";
        html +="</div>";
        $("#proAttrFormData").append(html);
    });
    $("#proAttribute input.ui-input").each(function(){
        var index = $("#proAttrFormData div.data").size();
        var prefix = "attributes["+index+"]";
        var attrNo = $(this).attr("data-attrno");
        var value = $(this).val();
        if(value!=""){
            var html = "<div class='data' >";
            html += "<input type='text' name='"+prefix+".attributeNo' value='"+attrNo+"' >";
            html += "<input type='text' name='"+prefix+".attrValue' value='"+value+"' >";
            html +="</div>";
            $("#proAttrFormData").append(html);
        }

    });


}

function setProAttrPriceFormData(){
    $("#proAttrPriceFormData").html("");
    $("#selectmix .maintbody tr").each(function(){

        var index = $("#proAttrPriceFormData div.data").size();
        var prefix = "priceModels["+index+"]";

        var $dataDom = $(this).find("input[name='attr_combo_price']");
        var data = eval('(' + $dataDom.attr("data-index") + ')');
        var price = $dataDom.val();
        var store = $(this).find("input[name='attr_combo_count']").val();

        var html = "<div class='data' >";
        html += "<input type='text' name='"+prefix+".p' value='"+price+"' >";
        html += "<input type='text' name='"+prefix+".s' value='"+store+"' >";
        for(var i in data){
            var $colorImg = $("#colorImgUrls_"+data[i].vid);
            var icon = "";
            if($colorImg.size()!=0){
                icon = $colorImg.val();
                if(icon==""){
                    icon = $("#attr_chk_"+data[i].a+"_"+data[i].vid).data("icon");
                }
            }
            var value = $("#attredit_"+data[i].a+"_"+data[i].vid).val();
            html += "<input type='text' name='"+prefix+".z["+i+"].a' value='"+data[i].a+"' >";
            html += "<input type='text' name='"+prefix+".z["+i+"].v' value='"+value+"' >";
            html += "<input type='text' name='"+prefix+".z["+i+"].vid' value='"+data[i].vid+"' >";
            if($colorImg.size()!=0){
                html += "<input type='text' name='"+prefix+".z["+i+"].icon' value='"+icon+"' >";
            }
        }
        html +="</div>";

        $("#proAttrPriceFormData").append(html);

    });
}

$("#productGroom").change(function(){
    var groomChecked = this.checked;
    if(groomChecked){
        var groomTotal = $("#groomTotal").data("groom");
        var groomUsed = $("#groomUsed").data("groom");
        if(groomTotal<=groomUsed){
            $("#groomCheck").val("");
        }else{
            $("#groomCheck").val("normal");
        }
    }else{
        $("#groomCheck").val("normal");
    }
});

function productCateValidated(validator){
    var productCate = $("input[name='productCate']:checked").val();
    if(productCate==2){
        validator.addItem({
            element: '.presetname',
            required: true,
            rule: 'minlength{min:1}',
            errormessageRequired: '请输入预定名称'
        })
            /*.addItem({
             element:'.pretemplate',
             required: true,
             rule: 'minlength{min:1}',
             errormessageRequired: '请选择预定模板'
             })*/
            .addItem({
                element:'.shippingtime',
                required: true,
                rule: 'minlength{min:1}',
                errormessageRequired: '请选择出货时间'
            })
            .addItem({
                element:'.ordertime',
                required: true,
                rule: 'minlength{min:1}',
                errormessageRequired: '请选择订货时间'
            })
            .addItem({
                element:'.needeposit',
                required: true,
                rule: 'minlength{min:1}',
                errormessageRequired: '请选择是否需要订金'
            })
            .addItem({
                element:'.orderamount',
                required: true,
                rule: 'minlength{min:1}',
                errormessageRequired: '请输入订货量'
            })
    }
    else{
        validator.removeItem(".presetname")
//                        .removeItem(".pretemplate")
            .removeItem(".shippingtime")
            .removeItem(".ordertime")
            .removeItem(".needeposit")
//                        .removeItem(".bulkclass")
            .removeItem('.orderamount')
    }
}
function beginTypeValidated(validator){
    var beginType = $("input[name='beginType']:checked").val();
    if(beginType==2){
        validator.addItem({
            element: '#datatimemm',
            required: true,
            rule: 'minlength{min:1}',
            errormessageRequired: '请输入开始时间'
        })
    }else{
        validator.removeItem("#datatimemm");
    }
}
