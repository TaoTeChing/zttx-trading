<%@ page import="com.zttx.web.consts.DealerConstant" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@include file="/WEB-INF/view/include/indexKey.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的进货单</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/market/shop-cart.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top" />
    <div class="header">
        <div class="ts-container">
            <div class="logo">
                <a href="/"><img src="${res}/images/common/logo-old.png"></a> 结算订单
            </div>
            <div class="step step2 animated slideInDown">
            </div>
        </div>
    </div>
    <div class="main">
        <div class="ts-container" style="background-color: #fff;">
            <div class="order-all">
                <%--修改的第一个地方--%>
                <div class="m-title">
                    收获地址
                </div>
                <div class="m-infolist">
                    <ul id="address_list_cards" style="margin-bottom: 5px;"></ul>
                    <a class="link js-add-address" href="javascript:;" style="margin-left: 20px;"><i class="icon i-add-blue"></i>使用新地址</a>
                </div>
                <div class="m-title">
                    确认订单
                </div>

                <c:forEach var="shoperModel" items="${shopersModelList}" >
                    <div class="order-item" data-brandesid="${shoperModel.brandesId}" >
                        <div class="m-brand m-brand-2">
                            <a href="">${shoperModel.brandName}</a>
                            <a href=""><i class="icon-cat"></i></a>
                            <c:set value="${fns:getBrandsIdSubDomain(shoperModel.brandesId)}" var="domain"/>
                            <span>品牌名称：${shoperModel.brandesName}</span>
                        </div>
                        <c:forEach var="dealerShoper" items="${shoperModel.dealerShoperList}">
                        <div class="m-uselist">
                            <div class="m-con clearfix">
                                <div class="m-pic fl clearfix">
                                    <div class="pic-box clearfix">
                                        <p class="fl" style="margin-left: 0px;">
                                            <a target="_blank" href="/market/product/${dealerShoper.productId}"><img src="${fns:getImageDomainPath(dealerShoper.productImage,220,220)}" title="${dealerShoper.productTitle}" width="120" height="120"></a>
                                            <a target="_blank" class="huohao js-huohao" href="/market/product/${dealerShoper.productId}">货号：${dealerShoper.productNo}</a>
                                        </p>
                                        <input type="hidden" class="js-temp-id" value="${dealerShoper.refrenceId}" />
                                    </div>
                                </div>
                                <div class="m-detail fr">
                                    <div class="o-hd clearfix">
                                        <div class="tit fl">
                                            <span>${dealerShoper.productTitle}</span>
                                        </div>
                                        <div class="operate fr clearfix">
                                            <c:set var="sam" value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_SAM%>"/>
                                            <c:set var="cash" value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CASH%>"/>
                                            <c:set var="credit" value="<%=DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT%>"/>
                                            交易类型：
                                            <c:choose>
                                                <c:when test="${dealerShoper.productType eq sam}">
                                                    <i class="icon-ny" data-type="${sam}"></i>拿样
                                                </c:when>
                                                <c:when test="${dealerShoper.productType eq credit}">
                                                    <i class="icon-sf" data-type="${credit}"></i>授信
                                                </c:when>
                                                <c:when test="${dealerShoper.productType eq cash}">
                                                    <i class="icon-yuan" data-type="${cash}"></i>现款</a>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="o-bd attr-item">
                                        <table class="">
                                            <colgroup>
                                                <col width="320">
                                                <col width="140">
                                                <col width="60">
                                                <col width="200">
                                                <col width="140">
                                            </colgroup>
                                            <tbody>
                                            <c:forEach var="dealerShopers" items="${dealerShoper.dealerShopersList}" varStatus="state">
                                                <tr>
                                                    <td class="p-colorsize">${dealerShopers.productSkuName}</td>
                                                    <td class="p-price" ><fmt:formatNumber value="${dealerShopers.productSkuPrice}" type="currency"/></td>
                                                    <td class="p-number" >${empty dealerShopers.purchaseNum ? '0': dealerShopers.purchaseNum}</td>
                                                    <c:if test="${state.first}">
                                                        <td class="p-xiaoji" rowspan="${fn:length(dealerShoper.dealerShopersList)}">
                                                            数量小计：<em class="js-number">${dealerShoper.productNum}</em>件
                                                        </td>
                                                    </c:if>
                                                    <td class="p-count js-price-single" ><fmt:formatNumber value="${dealerShopers.productSkuPrice*dealerShopers.purchaseNum}" type="currency" pattern="0.00"/></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="o-fd clearfix">
                                        <div class="count fr js-count-total" data-productid="${dealerShoper.productId}" data-productcount="${dealerShoper.productNum}">
                                            <span>金额小计：<em><fmt:formatNumber value="${dealerShoper.productPrice}" type="currency" pattern="0.00"/></em></span>
                                            <c:if test="${dealerShoper.productType eq credit}">
                                                <span>折扣：<fmt:formatNumber value="${dealerShoper.discountPrice}" type="currency" pattern="0.00"/></span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            <div class="m-bottom clearfix mt10">
                                <div class="fl">
                                    给品牌商留言：<input type="text" id="remark_${shoperModel.brandesId}" class="ui-input" style="width: 370px;"/>
                                </div>
                                <div class="fr">
                                    <p class="to"><span>进货商品：${shoperModel.purchaseNumAllSum} 件</span><span>商品金额总计（不含运费）：<em class="js-part-price"><fmt:formatNumber value="${shoperModel.purchasePriceAllSum}" type="currency" pattern="0.00"/></em>元</span></p>
                                    <div id="freigth_${shoperModel.brandesId}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%--修改的第二个地方--%>
            <div class="m-balabce">
                <div class="bd">
                    <p><span>运费总计：</span><strong class="js-allyf">0.00</strong>元</p>
                    <p><span>商品金额总计：</span><strong class="js-allPrice">0.00</strong>元</p>
                    <p class="total"><b>应付金额总计（含运费）：</b><strong class="js-alltotal">0.00</strong>元</p>
                    <p class="tip"><span>（其中：授信产品货款</span><em class="js-allsx">0.00</em> 现款产品货款<em class="js-allxk">0.00</em>元)</p>
                    <p>
                        <a class="link" href="${ctx}/dealer/dealerShoper/cart">返回进货单修改</a>
                        <button class="btn" onclick="shopper_balance.confirmCreateOrder()">提交订单并支付</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
    </div>
</div>

<div class="hide">
    <div id="dialogModifyAddress">
        <div class="ui-head mb20"><h3>编辑收货地址</h3></div>
        <form id="address_form_edit" name="address_form_edit" data-widget="validator" class="ui-form" <%--action="/dealer/account/address" method="post"--%> >
            <input id="refrenceId" name="refrenceId" type="hidden" value="">
            <input id="provinceName" name="provinceName" type="hidden" value="">
            <input id="cityName" name="cityName" type="hidden" value="">
            <input id="areaName" name="areaName" type="hidden" value="">
            <input type="hidden" id=smartAddr value="${country}">
            <div class="ui-form-item">
                <label class="ui-label">选择所在地:</label>
                <jsp:include page="${ctx}/common/regional/searchAllArea">
                    <jsp:param value="${province}" name="province" />
                    <jsp:param value="${city}" name="regCity" />
                    <jsp:param value="${country }" name="dealerAddr" />
                    <jsp:param value="address" name="sale" />
                    <jsp:param value="ui-select" name="style" />
                </jsp:include>
                <div class="ui-form-explain"></div></div>
            <div class="ui-form-item">
                <label class="ui-label">街道地址:</label>
                <input id="dealerAddress" name="dealerAddress" class="ui-input" style="width:450px;" maxlength="120" minlength="5" data-display="街道地址" required="" placeholder="请填写街道地址,最少5个字,最多不能超过120个字,不能全部为数字或字母" data-widget-cid="widget-1" data-explain="">
                <div class="ui-form-explain"></div></div>
            <div class="ui-form-item">
                <label class="ui-label">收货人姓名:</label>
                <input id="dealerName" name="dealerName" class="ui-input long" style="width:290px;" placeholder="收货人姓名:张三" required="" data-display="收货人姓名" maxlength="25" data-widget-cid="widget-2" data-explain="">
                <div class="ui-form-explain"></div></div>
            <div class="ui-form-item">
                <label class="ui-label">电话:</label>
                <input id="dealerTel" name="dealerTel" class="ui-input long" style="width:290px;" placeholder="" data-display="电话" data-widget-cid="widget-4" data-explain="">
                <div class="ui-form-explain"></div></div>
            <div class="ui-form-item">
                <label class="ui-label">手机:</label>
                <input id="dealerMobile" name="dealerMobile" class="ui-input long" style="width:290px;" placeholder="" data-display="手机" data-widget-cid="widget-5" data-explain=""> 电话与手机 至少填写一个
                <div class="ui-form-explain"></div></div>
            <div class="ui-form-item">
                <label class="ui-label">邮编:</label>
                <input id="postCode" name="postCode" class="ui-input long js-number" style="width:290px;" placeholder="" data-display="邮编" maxlength="6" data-widget-cid="widget-3" data-explain="">
                <div class="ui-form-explain"></div></div>

            <div class="ui-form-item">
                <label class="ui-label">设为默认:</label>
                    <span class="ui-form-text">
                        <label><input id="dealerDefault" class="ui-checkbox" name="dealerDefault" type="checkbox"> 设为默认收货地址</label>
                    </span>
            </div>
            <div class="ui-form-item">
                <input id="dealerSubmit" type="submit" class="ui-button ui-button-mred" value="保 存">
                <input id="dealerReset" type="button" class="ui-button ui-button-mred" style="" value="取 消">
            </div>
            <input type="hidden" name="csrfToken" value="84DBCA061EA744F7AF591B6510F81D5F">
        </form>
    </div>
</div>
<div style="display: none;">
    <form:form id="order_comfirm" action="">
        <input type="hidden" name="addressId" id="address_select_id" value="">
        <input type="hidden" name="remarks" id="remarks_id" value=''>
        <input type="hidden" name="freight" id="freight_id" value=''>
    </form:form>
    <form:form id="order_payment" action="${ctx}/dealer/dealerOrder/payment" >
        <input name="orderIds" value="" id="orderIds_id"/>
    </form:form>
</div>
<script id="address_list_tempalte" type="text/html">
    {{each rows}}
    <li>
        <input type="radio" name="addr" class="address-item"  {{$value.dealerDefault==true?'checked':''}} data-area="{{$value.dealerAddr}}" data-id="{{$value.refrenceId}}"/>
        <span>{{$value.dealerName}} {{$value.dealerMobile}} {{$value.provinceName}} {{$value.cityName}} {{$value.areaName}} {{$value.dealerAddress}}</span>
        {{if $value.dealerDefault==true}}
        <span class="operate">默认地址</span>
        {{else}}
                             <span class="operate">
                                <a class="link js-address-setdef" href="javascript:;" data-id="{{$value.refrenceId}}">[设为默认地址]</a>
                                <a class="link js-address-modify" href="javascript:;"
                                   data-area="{{$value.areaName}}" data-city="{{$value.cityName}}"
                                   data-province="{{$value.provinceName}}" data-id="{{$value.refrenceId}}"
                                   data-addrcode="{{$value.dealerAddr}}" data-address="{{$value.dealerAddress}}"
                                   data-dealername="{{$value.dealerName}}" data-dealertel="{{$value.dealerTel}}"
                                   data-dealermobile="{{$value.dealerMobile}}" data-postcode="{{$value.postCode}}"
                                   data-dealerdefault="{{if $value.dealerDefault==true}}true{{else}}false{{/if}}">[修改]</a>
                            </span>
        {{/if}}
    </li>
    {{/each}}
</script>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/common/base-init.js"></script>
<jsp:include page="${ctx}/WEB-INF/view/common/setup_ajax.jsp"/>
<script>
    var attrSimple = $(".simple-function"),
            attrItem = $(".attr-item");
    myOrder = {
        init: function () {
            this.initOrder();
            this.initAddress();
            this.initValidator();
        },
        initOrder: function () {
            //var me = this;
            $(attrSimple).filter(":odd").css({ "background": "#f9f9f9" });
            $(attrItem).filter(":odd").css({ "background": "#f9f9f9" })
            $(attrItem).each(function(i,o){
                if($(o).find("tr").length===0){ $(o).remove();}
            });
        },
        initAddress:function(){

            //已经显示更多的情况
            if($("#link_moreAddress:hidden").length>0)
            {
                $(".address-item").show();
            }
            else{
                $(".address-item:lt(4)").show();
                var more = $(".address-item:gt(3)");
                $("#link_moreAddress").click(function(){
                    $(more).show();
                    $(this).hide();
                });
            }

            var item = $(".address-item").click(function(){
                shopper_balance.calcFreight();
                //选中后的处理;
            });

            $(document).on("click",".js-address-remove",function(e){
                e.preventDefault();
                var addrid=$(this).data("addrid");
                confirmDialog("确定删除这个地址吗?",function(){
                    $.post("/dealer/address/delete", "uuid=" + addrid, function(data) {
                        shopper_balance.loadAddress();
                    }, "json");
                });
            });

            seajs.use("dialog",function(Dialog){
                window['addressDlg'] = new Dialog({content:$("#dialogModifyAddress"), width:700 }).after("render",function(a){
                    $(a.element[0]).find("#dealerReset").click(function(){
                        addressDlg.hide();
                    });

                });

                $(".js-address-modify,.js-add-address").click(function(){
                    addressDlg.show();
                    shopper_balance.loadDealerAddr(this);
                });
                $(".js-address-setdef").click(function(){
                    var _id = $(this).data("id");
                    console
                    $.post("/dealer/dealerAddr/setDef",{addrId:_id},function(data) {
                        if (data.code == 126000) {
                            shopper_balance.loadAddress();
                        }else{
                            var msg = data.message || data.rows[0];
                            remind("error", msg);
                        }
                    }, "json");
                });
            });
        },
        initValidator:function(){


            var telphoneConfig = {
                        element: '[name="dealerTel"]',
                        rule: 'minlength{min:8} dianhua',
                        required: function() {
                            var mobileHasValue = !!$.trim($('[name="dealerMobile"]').val()).length;
                            return !mobileHasValue;
                        },
                        display: '电话',
                        errormessageMinlength: '电话号码的长度必须为7位',
                        errormessageMaxlength: '电话号码的长度必须为7位'
                    },
                    mobileConfig = {
                        element: '[name="dealerMobile"]',
                        rule: 'minlength{min:11} maxlength{max:11} mobile',
                        required: function() {
                            var telphoneHasValue = !!$.trim($('[name="dealerTel"]').val()).length;
                            return !telphoneHasValue;
                        },
                        display: '手机',
                        errormessageMinlength: '手机号码的长度必须为11位',
                        errormessageMaxlength: '手机号码的长度必须为11位'
                    };

            baseFormValidator({
                selector:"#address_form_edit",
                isAjax:true,
                addElemFn:function(Core, Validator){

                    Validator.addRule('dianhua', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');

                    Core.addItem(telphoneConfig);
                    Core.addItem(mobileConfig);

                },
                beforeSubmitFn:function(){
                    shopper_balance.comfireDealerAddr();
                }
            });
        }
    }
    myOrder.init();


    /**业务处理代码*/
    var shopper_balance={};

    /**载入修改地址表单 By JuST4iT*/
    shopper_balance.loadDealerAddr = function(obj) {
        var code_country = $(obj).data('addrcode');
        var code_provice = parseInt(code_country / 10000) * 10000;
        var code_city = parseInt(code_country / 100) * 100;

        $('#dialogModifyAddress div.ui-head h3').html(code_country == null ? '新增收货地址' : '修改当前地址');
        $('#comfirmbtn').val(code_country == null ? '确定新增' : '保存修改');
        if(code_country == null){
            var _code_country = $('#smartAddr').val();
            var _code_provice = parseInt(_code_country / 10000) * 10000;
            var _code_city = parseInt(_code_country / 100) * 100;

            selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), _code_provice, _code_city, _code_country);
        }else{
            selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), code_provice, code_city, code_country);
        }
        var text_address = $(obj).data('address');
        var text_dealerName = $(obj).data('dealername');
        var text_dealerTel = $(obj).data('dealertel');
        var text_dealerMobile = $(obj).data('dealermobile');
        var text_dealerDefault = $(obj).data('dealerdefault');
        var text_id = $(obj).data('id');
        var text_postCode = $(obj).data('postcode');
        $('#dealerAddress').val(text_address);
        $('#dealerName').val(text_dealerName);
        $('#dealerTel').val(text_dealerTel);
        $('#dealerMobile').val(text_dealerMobile);
        $('#postCode').val(text_postCode);
        $('#refrenceId').val(text_id);
        $("#dealerDefault").prop("checked",text_dealerDefault);
    };

    /**关闭地址框同时重置表单 By JuST4iT*/
    shopper_balance.closeAddressDlg = function() {
        addressDlg.hide();
        $('#address_form_edit')[0].reset();
    };

    /**增加or修改收货地址*/
    shopper_balance.comfireDealerAddr = function() {
        var isNew = $('#address_form_edit #refrenceId').val() == ""|| $('#address_form_edit #refrenceId').val() == null;
        $.post("/dealer/dealerAddr/save",$('#address_form_edit').serialize(),function(data) {
            if (data.code == 126000) {
                if (isNew) {
                    remind("success", "新增成功!");
                } else {
                    remind("success", "修改成功!");
                }
                shopper_balance.loadAddress();
                shopper_balance.closeAddressDlg();
            }else{
                var msg = data.message || data.rows[0];
                remind("error", msg);
            }
        }, "json");
        return false;
    };
    /**购物车结算-载入收货人地址信息 by JuST4iT*/
    shopper_balance.loadAddress=function(){
        seajs.use(["template"], function (template) {
            template.helper('$trimLongString', trimLongString);
            $.post('${ctx}/dealer/dealerAddr/data',function(data){
                $('#address_list_cards').empty().append(template.render("address_list_tempalte", data));
                myOrder.initAddress();
                shopper_balance.calcFreight();
            },"json");
        });
    };

    /**计算运费 by JuST4iT*/
    shopper_balance.calcFreight=function(){
        var area = $('#address_list_cards input:checked').data("area");
        $('#address_select_id').val($('#address_list_cards input:checked').data("id"));
        $('.order-item').each(function(){

            var brandesid=$(this).data("brandesid");
            var productIds=[];
            var productCnts=[];
            var $this = $(this);

            $(this).find(".js-count-total").each(function(){
                var productid=$(this).data("productid");
                var productcount=$(this).data("productcount");
                if(productid!=null){
                    productIds.push(productid);
                    productCnts.push(productcount);
                }
            });

            if(area!=null){
                var params={};
                params['areaNo']=area;
                params['brandesId']=brandesid;
                params['productIds']=productIds.join(",");
                params['productcount']=productCnts.join(",");

                $.post("${ctx}/dealer/dealerShoper/calcfreight",params,function(data){
                    if(data.code==126000){
                        var html=[];
                        if(data.rows==null||data.rows.length==0){
                            html.push('<p id="frefree_">运送方式：品牌商包邮</p>');
                        }else{
                            html.push('<p class="tip"><span class="c-r">*</span> 如需修改运费 请与品牌商联系</p>');
                            html.push('<p class="type">运送方式：');
                            for(var i=0;i<data.rows.length;i++){
                                html.push('<label><input class="ui-radio" type="radio" name="frePayType_'+data.object+'" value="'+data.rows[i]['carryType']+'" data-amount="'+(data.rows[i]['freightAmount']==null?0:data.rows[i]['freightAmount'])+'">'+data.rows[i]['carryName']+' '+((data.rows[i]['freightAmount']==null||data.rows[i]['freightAmount']==0)?'':data.rows[i]['freightAmount'])+'</label>');
                            }
                            html.push('</p>');
                        }
                        $('#freigth_' + data.object).empty().append(html.join(""));
                        console.log(data.object);
                    }else{
                        var objs=data.message.split(",");
                        if(objs.length==2){
                            $('#freigth_' + data.message.split(",")[1]).empty().append(data.message.split(",")[0] + ",请与品牌商联系");
                        }else{
                            $('#freigth_').empty().append("运费模板不存在,请与品牌商联系");
                        }
                    }
                },"json");
            }

        });
        /*var total = parseFloat($(".js-allPrice").data("totalamount"));
         $(".js-allPrice").html((total).toFixed(2));*/
        var total = 0;
        var xkTotal = 0;
        var sxTotal = 0;

        $(".js-part-price").each(function(){
            var partPrice = parseFloat($(this).text());
            var parentsI = $(this).parents(".m-uselist").find(".operate i");
            var parentsOrder = $(this).parents(".order-item");

            if(parentsI.data("type") == "1"){
                sxTotal += partPrice;
            }
            if(parentsI.data("type") == "0" || parentsI.data("type") == "2"){
                xkTotal += partPrice;
            }

            total += partPrice;
        });

        $(".js-allPrice").data("totalamount",total);
        $(".js-allPrice").html((total).toFixed(2));

        $(".js-allxk").html(xkTotal.toFixed(2));
        $(".js-allsx").html(sxTotal.toFixed(2));

        $(".js-alltotal").html(parseFloat($(".js-allPrice").text()).toFixed(2));
    };

    /**确认生成采购订单*/
    shopper_balance.confirmCreateOrder = function() {
        if(shopper_balance.setFreightRemark()){
            // 提交之前往表单加id
            $("#order_comfirm input[name=dealerShorperIds]").remove();  //防止多次点击，先移除。
            $(".js-temp-id").each(function(){
                var _val = $(this).val();
                var tpl = "<input type='hidden' name='dealerShoperIds' value='" + _val + "'>";
                $("#order_comfirm").append(tpl);
            });
            $.post("${ctx}/dealer/dealerOrder/addOrder",$("#order_comfirm").serialize(),function(data){
                if(data.code==126000){
                    $('#orderIds_id').val(data.object.join(","));
                    $("#order_payment")[0].submit();
                }else{
                    remind("error", data.message);
                }
            },"json");
        }
    };

    /**设置运费与留言*/
    shopper_balance.setFreightRemark=function(){
        var freight=[];
        var remarks=[];
        var bOk=true;
        $('.order-item').each(function(){
            var brandesid=$(this).data("brandesid");
            var remarkmsg=$('#remark_'+brandesid).val();
            if(remarkmsg.length> 1000){
                remind("error","留言最长为1000字！");
                bOk=false;
                return false;
            }
            if(!$("#address_list_cards input:checked")){
                remind("error","请选择收货地址");
                bOk=false;
                return false;
            }

            /*根据 bug #2734 此处加一个判断，当产品未设置运费模版的时候，作相应的提示*/
            var freigthtype=$('#freigth_' + brandesid + ' input.ui-radio:checked').val();
            /*产品未设置运费模版*/
            if($('#freigth_' + brandesid + ' input').size()==0){
                remind("error","产品未设置运费模板，无法下单");
                bOk=false;
                return false;
            }
            /*设置了运费模版未选*/
            if($('#frefree_'+brandesid).size()==0&&freigthtype==null){
                remind("error","请选择运送方式");
                bOk=false;
                return false;
            }
            if(brandesid=="") brandesid="-";
            if(remarkmsg=="") remarkmsg="-";
            freight.push(brandesid+"$"+freigthtype);
            remarks.push(brandesid+"$"+remarkmsg);
        });
        $('#remarks_id').val(remarks.join("|"));
        $('#freight_id').val(freight.join("|"));
        return bOk;
    };

    /**选择快递模板 重新计算价格*/
    $(document).on("click", ".ui-radio[name^='frePayType_']", function(){
        var yfTotal = 0;
        $(".ui-radio[name^='frePayType_']:checked").each(function(){
            yfTotal += parseFloat($(this).data("amount"));
        });

        $(".js-allyf").html(yfTotal.toFixed(2));

        $(".js-alltotal").html((parseFloat($(".js-allyf").text()) + parseFloat($(".js-allPrice").text())).toFixed(2));
    });


    $(function(){
        shopper_balance.loadAddress();
    });
</script>
</body>
</html>