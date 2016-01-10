<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-设置活动属性</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/sign.css"/>
    <style>
        .ui_button_mdis{
            line-height: 26px;
            height: 26px;
            background-color: #ddd;
            cursor: default;
            color: #fff;
        }
        .ui_button_mdis:hover{
            background-color: #ddd;
            color: #fff;
        }
        .ui_button_mdis:active{
            top: 0;
            box-shadow: none;
        }
        .error-input{
            border:1px solid #f00;
            color:#f00;
        }
        .real_tip_icon{
            color:#67acf2;
            vertical-align: -1px;
            cursor: pointer;
        }
    </style>
</head>
<body class="sign-body">
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">设置活动属性</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner sign-box">
            <div class="v2-steps v2-3steps">
                <%--<span class="text1">选择产品线</span>--%>
                <span class="text1 current" style="left: 303px;">设置活动属性</span>
                <span class="text2" style="left: 579px;">等待审核</span>
                <div class="steps v2-steps-1" style="width: 275px;"></div>
            </div>
            <form:form class="ui-form search-form clearfix" id="activitySearchForm" method="post" style="margin:0;">
                <input type="hidden" name="id" id="id" value="${id}"/>
                <input type="hidden" name="state" id="state" value=""/>
                <input type="hidden" name="sort" id="sort" value=""/>

                <%--切换导航--%>
                <div class="jstabsmenu_t ui_tab" style="height: 56px;">
                    <ul class="ui_tab_items clearfix">
                        <li class="js-sign-filter ui_tab_item current"><a href="#">未报名</a></li>
                        <li class="js-sign-filter ui_tab_item" data-state="1"><a href="#">已报名</a></li>
                        <li class="js-sign-filter ui_tab_item" data-state="0"><a href="#">审核中</a></li>
                        <li class="js-sign-filter ui_tab_item" data-state="2"><a href="#">失败</a></li>
                    </ul>
                    <%--表单--%>
                    <div class="selectbar_list clearfix">
                        <input type="hidden" name="attrType" id="attrType" value="0">
                        <div class="fl pr">
                            <select class="ui-select js_select" name="wordType" id="wordType" style="height:35px;width:100px">
                                <option value="1">产品货号</option>
                                <option value="2">产品名称</option>
                            </select>
                        </div>
                        <input class="ui-input ml5 fl" style="width:200px;height: 25px;" name="keyWord"
                               id="keyWord"/>

                        <div class="fl pr ml5">
                            <select class="ui-select js_select" name="brandesId" style="height:35px;width:100px">
                                <option value="">所有品牌</option>
                                <c:forEach items="${brandes}" var="item">
                                    <option value="${item.refrenceId}">${item.brandName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="button" class="simple_button ml5" onclick="page.goTo(1);" style="margin-top: 1px;">搜 索</button>
                    </div>
                </div>
                <div class="tips" style="margin: 10px;height: auto;">
                    <p><i class="v2-icon-explain"></i>说明：审核失败的产品重新提交的时候，需要重新设置直供价价格。</p>
                    <p style="margin-left: 60px;">价格调低时，最近的一个整点时间生效；价格调高时，需审核通过后方可生效；</p>
                </div>

                <%--筛选条--%>
                <div class="sign-filter">
                    <div class="inline-block">
                        <label>
                            <input type="checkbox" class="ui-checkbox js-checkall"/>
                        </label>
                        全选
                    </div>
                    <div class="inline-block">
                        <span class="ml15">排序</span>

                        <div class="inline-block filter-item ml5">
                            <a class="active js-sort-filter" href="javascript:;">
                                默认
                                <i class="tag"></i>
                            </a>
                            <a href="javascript:;" class="js-sort-filter" data-sort="1">
                                最后修改
                                <i class="tag"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <%--主体内容--%>
                <div class="set-bd" style="margin: 0 10px; border: 1px solid #d9d9d9;">
                    <div id="data-list" class="data-list" style="margin:0;">
                        <ul>
                        </ul>
                    </div>
                </div>
                <div class="sign-filter">
                    <div class="inline-block fr page-item">
                        <div class="pagination" id="pagination">
                        </div>
                    </div>
                    <div class="inline-block">
                        <label>
                            <input type="checkbox" class="ui-checkbox js-checkall"/>
                        </label>
                        全选
                    </div>
                </div>
                <div class="ta-c footer-button">
                    <button type="button" class="ui_button ui_button_lblue btn-submit"
                            onclick="set_attr.saveProduct();">提交报名审核
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script id="temp_1" type="text/html">
    <li>
        <input type="hidden" name="productList[{{index}}].parityAry" id="parityAry" value="{{value}}"/>
        <label class="common-label keyword" for="">{{label}}</label>：
        <input class="ui-input js-price js-price-pt" style="width: 60px;" type="text" name="productList[{{index}}].parityPrice" maxlength="8"/> 元
        <label class="common-label" style="margin-left: 15px;" for="">产品链接</label>：
        <input class="ui-input" style="width: 310px;" type="text" name="productList[{{index}}].parityUrl"/>
    </li>
</script>
<script id="feedback-templage" type="text/html">

    {{each rows}}
    <li class="data-item">
        <input type="hidden" id="productId" name="productList[{{$index}}].productId" value="{{$value.refrenceId}}">
        <input type="hidden" id="skuInfos" name="productList[{{$index}}].skuInfos" value="">
        <input type="hidden" id="brandsId" name="productList[{{$index}}].brandesId" value="{{$value.brandsId}}">
        <input type="hidden" id="productNo" name="productNo" value="{{$value.productNo}}">

        <div class="left">
            <div class="product-show">
                <input type="checkbox" class="ui-checkbox fl js-chk-sub" name="productList[{{$index}}].proSel" value="1">
                <div class="product-info">
                    <p class="fn-text-overflow" title="{{$value.productNo}}" style="width: 120px;">
                        货号：{{$value.productNo}}
                    </p>

                    <div class="img_contain">
                        {{if null != $value.activityImage}}
                        <img src="${res}{{$getImageDomainPathFn $value.activityImage 160 160}}" width="120" height="120" alt=""/>
                        <input type="hidden" id="productImg" name="productList[{{$index}}].productImg" value="{{$value.activityImage}}"/>
                        {{else}}
                        <img src="${res}{{$getImageDomainPathFn $value.productImage 160 160}}" width="120" height="120" alt=""/>
                        <input type="hidden" id="productImg" name="productList[{{$index}}].productImg" value="{{$value.productImage}}"/>
                        {{/if}}
                    </div>
                    <div class="file_wrap mt10">
                        <input id="img_{{$value.refrenceId}}" name="photo" class="input_file product-pic" type="file">
                        <p class="replace_text">自定义产品主图</p>
                    </div>
                    <p class="explain">建议大小800x800以上</p>
                </div>
            </div>
        </div>

        <div class="info">
            <div class="info-padd">
                <div class="hd">
                    <%--{{if }}--%>
                    <%--非 已报名活动 和 审核中的时候--%>
                    {{if ${brandActivityList.code == 'A00003'} }}
                    <div>
                        <input type="radio" class="js-factype-puhuo" data-index="{{$index}}" name="productList[{{$index}}].factoryProductType" value="1" style="width: 18px;" {{if $value.factoryProductType== null || $value.factoryProductType== 1}} checked {{/if}}>铺货
                        <input type="radio" class="js-factype-xk" name="productList[{{$index}}].factoryProductType" value="0" style="width: 18px;" {{if $value.factoryProductType== 0}} checked {{/if}}>现款现货
                    </div>
                    {{/if}}
                    <div class="set-direct-content" style="padding: 5px 0;">
                        {{if $value.priceSet == 1 }}
                            <div>
                                价格设置：<input type="radio" class="ui-radio js-uniform-setting" name="productList[{{$index}}].priceSet" value="1" checked style="width: 15px;" />
                                统一设置 直供价：{{$value.directPrice}} 元 ${brandActivityList.name}直供价：<input type="text" name="productList[{{$index}}].directPrice" value="{{if $value.priceSet == 1 }}{{formatNumber $value.price}}{{/if}}" class="ui-input js-price"> 元
                            </div>
                            <div style="margin-left: 71px;padding: 5px 0;">
                                <input type="radio" class="ui-radio js-single-setting" name="productList[{{$index}}].priceSet" value="0" style="width: 15px;" />
                                单独设置 直供价：{{$value.directPrice}} 元 <a class="ml5 ui_button ui_button_mdis js-set-direct " data-id="{{$value.refrenceId}}" hasJoin="{{$value.hasJoin}}" actProId="{{$value.actProId}}" style="margin-top: 1px;">${brandActivityList.name}直供价设置</a>
                            </div>
                        {{else}}
                            <div>
                                价格设置：<input type="radio" class="ui-radio js-uniform-setting" name="productList[{{$index}}].priceSet" value="1" style="width: 15px;" />
                                统一设置 直供价：{{$value.directPrice}} 元 ${brandActivityList.name}直供价：<input type="text" name="productList[{{$index}}].directPrice" value="{{if $value.priceSet == 1 }}{{formatNumber $value.price}}{{/if}}" class="ui-input js-price"> 元
                            </div>
                            <div style="margin-left: 71px;padding: 5px 0;">
                                <input type="radio" class="ui-radio js-single-setting" name="productList[{{$index}}].priceSet" value="0" checked style="width: 15px;" />
                                单独设置 直供价：{{$value.directPrice}} 元 <a class="ml5 ui_button ui_button_mgreen js-set-direct " data-id="{{$value.refrenceId}}" hasJoin="{{$value.hasJoin}}" actProId="{{$value.actProId}}" style="margin-top: 1px;">${brandActivityList.name}直供价设置</a>
                            </div>
                        {{/if}}
                    </div>
                    {{if ${brandActivityList.code == 'A00003'} }}
                        {{if ($value.factoryProductType == null || $value.factoryProductType== 1) && ($value.factoryState ==2)}}
                        <div class="operation-modify" style="padding-bottom: 5px;">
                            <div>
                                调价生效方式：<input type="radio" class="ui-radio" name="productList[{{$index}}].priceEffectType" value="0" {{if !$value.priceEffectType || $value.priceEffectType == 0}}checked{{/if}} style="width: 15px;" />实时库存生效 <i class="iconfont real_tip_icon">&#xe629;</i>
                            </div>
                            <div style="margin-left: 99px;padding: 5px 0;">
                                <input type="radio" class="ui-radio" name="productList[{{$index}}].priceEffectType" value="1" {{if $value.priceEffectType == 1}}checked{{/if}} style="width: 15px;" />新订单生效
                            </div>
                        </div>
                        {{/if}}
                    {{/if}}
                    <div class="clearfix">
                        <div class="price-item"><label>吊牌价格</label>：
                            <input class="ui-input js-price js-dpj" type="text" readonly="readonly" name="" value="{{formatNumber $value.productPrice}}" maxlength="8"> 元
                        </div>
                        <div class="price-item"><label>省代价格</label>：
                            <input class="ui-input js-price js-price-province" type="text" id="provincePrice" name="productList[{{$index}}].provincePrice"
                                   value="{{formatNumber $value.provincePrice}}" maxlength="8"> 元
                        </div>
                        {{if $value.state == 2 && $value.checkResult}}
                        <span class="js-sign-tip fr fn-text-overflow"
                              style="width:490px;margin-top:3px;text-align:right;font-size: 12px;color: #ff5243;"
                              title="{{$value.checkResult}}">{{$value.checkResult}}</span>
                        {{/if}}
                    </div>
                </div>
                <div class="bd">
                    <%--非 已报名活动 和 审核中的时候--%>
                    <div class="select-platform">
                        <label class="common-label" for="">选择平台</label>：
                        {{each $value.columnList as value index1}}
                        <input data-label="{{value.name}}" data-index="{{$index}}" class="chk" type="checkbox"
                               value="{{value.refrenceId}}" {{if value.isSelect}}checked{{/if}}/>{{value.name}}
                        {{/each}}
                    </div>
                    <ul class="select-item-list">
                        {{each $value.productList as value index1}}
                        <li>
                            <input type="hidden" name="productList[{{$index}}].parityAry" id="parityAry" value="{{value.parityId}}"/>
                            <label class="common-label keyword" for="">{{value.columnName}}</label>：
                            <input class="ui-input js-price js-price-pt js-{{value.style}}" style="width: 60px;"
                                type="text" name="productList[{{$index}}].parityPrice" value="{{formatNumber value.price}}" maxlength="8"/> 元
                            <label class="common-label" style="margin-left: 15px;" for="">产品链接</label>：
                            <input class="ui-input" style="width: 310px;" type="text"
                                name="productList[{{$index}}].parityUrl" value="{{value.url}}"/>
                        </li>
                        {{/each}}
                    </ul>
                    {{if null!=$value.state}}
                    <div class="bottom">
                        <button class="simple_button js-cancel" type="button" data-id="{{$value.refrenceId}}">取消活动报名</button>
                    </div>
                    {{/if}}
                </div>
            </div>
        </div>

        {{if null!=$value.state}}
        <c:if test="${brandActivityList.code == 'A00003'}">
            {{if $value.state==0 && 0==$value.factoryState}}
            <span class="tag status_4">待审核</span>
            {{else if $value.state==0 && 1==$value.factoryState}}
            <span class="tag status_2">审核中</span>
            {{else if 1==$value.state}}
            <span class="tag status_1">已报名活动</span>
            {{else if 2==$value.state}}
            <span class="tag status_3">审核失败</span>
            {{/if}}
        </c:if>
        <c:if test="${brandActivityList.code != 'A00003'}">
            {{if 0==$value.state}}
            <span class="tag status_2">审核中</span>
            {{else if 1==$value.state}}
            <span class="tag status_1">已报名活动</span>
            {{else if 2==$value.state}}
            <span class="tag status_3">审核失败</span>
            {{/if}}
        </c:if>
        {{/if}}
    </li>
    {{/each}}
    {{ if rows.length == 0 }}
    <li style="padding: 10px;text-align: center;">
        暂无数据
    </li>
    {{ /if }}
</script>
<script id="set-activity-price" type="text/html">
    <div class="ui-head">
        <h3>设置活动直供价</h3>
    </div>
    <div>
        <div class="tip_text ta-c">
            <table class="direct-table" style="margin: 10px;width: 500px;">
                <colgroup>
                    <col width="150"/>
                    <col width="150"/>
                    <col width="200"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>颜色 | 尺码</th>
                        <th>SKU直供价</th>
                        <th>活动直供价</th>
                    </tr>
                </thead>
                <tbody>
                    {{each rows}}
                    <tr>
                        <td>{{$value.productSkuName}}</td>
                        <td>{{formatNumber $value.directPrice}}</td>
						{{if $value.activityPrice}}
                        <td><input type="text" class="ui-input js-price js-entered-direct" data-skuid="{{$value.productSkuId}}" value="{{formatNumber $value.activityPrice}}"/></td>
						{{else}}
						<td><input type="text" class="ui-input js-price js-entered-direct" data-skuid="{{$value.productSkuId}}" value="{{formatNumber $value.directPrice}}"/></td>
						{{/if}}
                    </tr>
                    {{/each}}
                </tbody>
            </table>
        </div>
        <div class="ta-c operate">
            <a style="vertical-align: middle" href="javascript:;" class="confirm_btn simple_button ml5" id="setDirectSave">保存</a>
            <a style="vertical-align: middle" href="javascript:;" class="simple_button cancel_btn ml5" id="setDirectReset">重置</a>
        </div>
    </div>
</script>
<script id="temp_2" type="text/html">
    <div class="operation-modify" style="padding-bottom: 5px;">
        <div>
            调价生效方式：<input type="radio" class="ui-radio" name="productList[{{index}}].priceEffectType" value="0" checked style="width: 15px;" />实时库存生效
        </div>
        <div style="margin-left: 99px;padding: 5px 0;">
            <input type="radio" class="ui-radio" name="productList[{{index}}].priceEffectType" value="1" style="width: 15px;" />新订单生效
        </div>
    </div>
</script>
<script>
    var set_attr = {
        init: function () {
            //this.handleCheckbox();
            this.handlePriceInput();
            this.checkPrice(); //检测输入的价格
            this.submitIndex = 0;
            this.checkTempArr = [];
            this.filter();
            this.sortFilter();
            this.showFilter();
            this.setDirectPrice();
            this.operationModifyPrice();
        },
        renderAfter: function () {
            this.handleCheckbox();
            this.cancelProduct();
            this.realTipIcon();
        },
        handleCheckbox: function () {
            seajs.use("template", function (template) {
                $(".chk").on("click", function () {
                    var box = $(this).parents(".bd").find(".select-item-list");
                    var label = $(this).data("label");
                    if ($(this).prop("checked")) {
                        var data = {
                            label: label,
                            value: $(this).val(),
                            index: $(this).data("index")
                        }
                        var html = template.render("temp_1", data);

                        $(html).appendTo(box);
                    } else {
                        $(box).find("li").each(function () {
                            if ($(this).find(".keyword").html() == label) {
                                $(this).remove();
                            }
                        })
                    }
                })
            });
        },
        cancelProduct: function () {
            //取消活动报名
            $(".js-cancel").on("click", function () {
                var _this = this;
                confirmDialog("确定要取消活动报名吗？", function () {
                    var activityId = $.trim($("#id").val());
                    var productId = $.trim($(_this).data("id"));
                    $.ajax({
                        type: "POST",
                        url: "${ctx}/brand/activity/products/cancel",
                        data: {activityId: activityId, productId: productId},
                        dataType: "json",
                        success: function (json) {
                            if (zttx.SUCCESS == json.code) {
                                page.render();
                            } else
                                remind("error", json.message);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            remind("error", errorThrown);
                        }
                    });
                });
            });
        },
        saveProduct: function () {
            var _this = this;
            if ($(".js-chk-sub:checked").size() <= 0) {
                remind("error", "请选择需要报名的产品");
            } else {
                var errorType = "success";
                /*if (_this.submitIndex === 3 && _this.checkTempArr && _this.checkTempArr.length > 0) {
                    errorType = _this.setSubmitErrorMsg(_this.checkTempArr);
                }*/
                //用以校验直供价格是否设置过
                var directPriceVer = _this.setSubmitVerify();
                if (errorType == "success" && directPriceVer) {
                    $.post("${ctx}/brand/activity/products/save", $("#activitySearchForm").serialize(), function (data) {
                        if (data.code == zttx.SUCCESS) {
                            remind("success", "保存成功", function () {
                                page.render();
                            });
                        } else {
                            remind("error", data.message);
                        }
                    }, "json");
                }
            }
        },
        handlePriceInput: function () {
            $(document).on("keyup", ".js-price", function () {
                $(this).isPrice();
            });
        },
        checkPrice: function () {
            $("#data-list").on("change", ".hd .js-price-8637", function () {

                var thisVal = $(this).val();
                var dpj = parseFloat($(this).parents(".hd").find(".js-dpj").val());
                var hdj = thisVal ? parseFloat(thisVal) : 0;
                if (hdj > dpj) {
                    remind("error", "活动价不能高于吊牌价");
                }
                $(this).val(hdj.toFixed(2));
                //隐藏 bug #3694 新增的提示
                $(this).parents(".data-item").find(".js-sign-tip").hide();
            });
            $("#data-list").on("change", ".bd .js-price-pt", function () {

                var val = $(this).parents(".info").find(".js-price-8637").val();
                var thisVal = $(this).val();
                var hdj = val ? parseFloat(val) : 0;
                var ptj = thisVal ? parseFloat(thisVal) : 0;

                if (hdj > ptj) {
                    remind("error", "平台价不能低于活动价");
                }
                $(this).val(ptj.toFixed(2));
                //隐藏 bug #3694 新增的提示
                $(this).parents(".data-item").find(".js-sign-tip").hide();
            });

            $("#data-list").on("change", ".js-dpj", function () {
                var thisVal = $(this).val();
                var dpj = thisVal ? parseFloat(thisVal) : 0;
                $(this).val(dpj.toFixed(2));
                //隐藏 bug #3694 新增的提示
                $(this).parents(".data-item").find(".js-sign-tip").hide();
            });

            $("#data-list").on("change", ".js-price-province", function () {

                var val = $(this).parents(".info").find(".js-price-8637").val();
                var thisVal = $(this).val();
                var hdj = val ? parseFloat(val) : 0;
                var sdj = thisVal ? parseFloat(thisVal) : 0;
                if (hdj > sdj) {
                    remind("error", "省代价格不能低于活动价格");
                }
                $(this).val(sdj.toFixed(2));
                //隐藏 bug #3694 新增的提示
                $(this).parents(".data-item").find(".js-sign-tip").hide();
            });

            $("#searchBut").on("click", function () {
                page.render();
            });
        },
        filter: function () {
            var _this = this;

            //通用筛选方法
            $(".js-sign-filter").click(function () {
                var state = $.trim($(this).data("state"));
                $("#state").val(state);
                page.goTo(1);
            });

            //筛选时tabs切换
            $(".jstabsmenu_t li").click(function () {
                var index = $(this).index();
                index == 0 ? $(".js-add-product").show() : $(".js-add-product").hide();
                index == 2 ? $(".footer-button").hide() : $(".footer-button").show();
                _this.submitIndex = index;
                $(this).addClass("selected").siblings().removeClass("selected");
            });

            //全选
            $(".sign-box").on("click", ".js-checkall", function () {
                $(".js-checkall").prop("checked", this.checked);
                $(".js-chk-sub").prop("checked", this.checked);
            });
        },
        sortFilter: function () {
            //通用筛选方法
            $(".js-sort-filter").click(function () {
                var sort = $.trim($(this).data("sort"));
                $("#sort").val(sort);
                $(this).addClass("active").siblings().removeClass("active");
                page.render();
            });
            //筛选时tabs切换
            $(".jstabsmenu_t li").click(function () {
                $(this).addClass("current").siblings().removeClass("current");
            });

        },
        showFilter: function () {
            //通用筛选方法
            $("#isShow").click(function () {
                if ($(this).prop("checked")) {
                    $("input[name=isShow]").val("true");
                } else {
                    $("input[name=isShow]").val("false");
                }
                page.goTo(1);
            });
        },
        setSubmitErrorMsg: function (data, callback) {
            //这个方法用来判断重新提交的时候值有没有修改，如没修改过，则提示用户修改
            var errorType = "success";
            var len = data.length;
            var errorTpl = '<span class="js-sign-tip" style="padding: 5px;font-size: 12px;color: #ff5243;">' +
                    '<i class="inline-block" style="width:16px;height:23px;background: url(/images/common/icon_warn.png) 0 -243px no-repeat; ' +
                    'vertical-align: -4px;margin-right: 5px"></i>请修改报名信息</span>';

            function addErrorMsg(element) {
                if (element.find(".price-lg .js-sign-tip").length > 0) {
                    element.find(".price-lg .js-sign-tip").show();
                } else {
                    element.find(".price-lg").append(errorTpl);
                }
                errorType = "error";
                //element.focus();//有错误的节点获取焦点。
            }

            //提交前校验数据是否有更改  bug #3694 新增方法
            $("#data-list .data-item .js-chk-sub:checked").each(function () {
                var parents = $(this).parents(".data-item");
                var id = parents.find("#productId").val();
                var pListLen = parents.find(".select-platform input[type=checkbox]:checked").length;
                //8637直供价
                var activityPrice = parents.find(".js-price-8637").val();
                //吊牌价
                var dpj = parents.find(".js-dpj").val();
                //省代价格
                var provincePrice = parents.find(".js-price-province").val();
                for (var i = 0; i < len; i++) {
                    if (id === data[i].refrenceId) {
                        //所有比较结果，只要有一处修改过，就可以跳出循环。
                        if (data[i].productList) {
                            var plen = data[i].productList.length;
                            var ptPriceLen = 0;
                            //原始数据存在平台的时候 只要选中了的大于原数据长度，就跳出循环
                            if (pListLen > plen) {
                                break;
                            }
                            for (var j = 0; j < plen; j++) {
                                var ptPrice = parents.find(".js-" + data[i].productList[j].style).val();
                                if (ptPrice == data[i].productList[j].price) {
                                    ptPriceLen++;
                                }
                            }
                            if (activityPrice == data[i].activityPrice && dpj == data[i].productPrice && provincePrice == data[i].provincePrice && ptPriceLen == plen) {
                                addErrorMsg(parents);
                                break;
                            }
                        } else {
                            //原始数据不存在平台的时候 只要选中了，就跳出循环
                            if (parents.find(".select-platform input[type=checkbox]").prop("checked")) {
                                break;
                            }
                            if (activityPrice == data[i].activityPrice && dpj == data[i].productPrice
                                    && provincePrice == data[i].provincePrice) {
                                addErrorMsg(parents);
                                break;
                            }
                        }
                    }
                }
            });
            if (callback && $.type(callback) == "function") {
                callback();
            }
            return errorType;
        },
        setSubmitVerify: function(){
            //提交前表单校验的方法
            var flag = true;
            var len = $("#data-list .data-item .js-chk-sub:checked").length;
            for(var i = 0;i < len;i++){
                var parents = $("#data-list .data-item .js-chk-sub:checked").eq(i).parents(".data-item");
                var singleType = parents.find(".js-single-setting").prop("checked");
                var skuInfos = parents.find("#skuInfos").val();
                if(singleType && skuInfos === ""){
                    remind("error", "请设置直供价");
                    flag = false;
                    break;
                }
            }
            return flag;
        },
        setDirectPrice: function(){
            //设置活动直供价
            seajs.use(["dialog", "template"],function(Dialog, Template){

                $("#data-list").on("click",".js-uniform-setting",function(){
                    var parents = $(this).parents(".data-item");
                    var directBtn = parents.find(".js-set-direct");
                    directBtn.removeClass("ui_button_mgreen").addClass("ui_button_mdis");
                });
                $("#data-list").on("click",".js-single-setting",function(){
                    var parents = $(this).parents(".data-item");
                    var directBtn = parents.find(".js-set-direct");
                    directBtn.removeClass("ui_button_mdis").addClass("ui_button_mgreen");
                });
                $(document).on("change blur",".js-entered-direct",function(){
                    var val = $(this).val();
                    if(val !== ""){
                        $(this).removeClass("error-input");
                    }else{
                        $(this).addClass("error-input");
                    }
                });
                //点击直供价设置
                $("#data-list").on("click",".js-set-direct",function(){
                    var parents = $(this).parents(".data-item");
                    var productId = $(this).data("id");
                    var hasJoin = $(this).attr("hasJoin");
                    var actProId = $(this).attr("actProId");

                    //选中统一设置的时候，弹窗无效
                    if(parents.find(".js-uniform-setting").prop("checked") == true){
                        return false;
                    }

                    $.ajax({
                        url: "/brand/activity/getSkuInfo?activityId=${id}&hasJoin="+ hasJoin +"&productId=" + productId+"&actProId=" + actProId,
                        method: "post",
                        dataType: "json",
                        success: function(data){
                            //console.log(data);
                            //保存之前操作记录处理开始  方法是重写data，写了一个新值selfDirect
                            if(parents.find("#skuInfos").val() !== ""){
                                var jsonStr = parents.find("#skuInfos").val();
                                //console.log(jsonStr);
                                var jsonObj = eval('(' + jsonStr + ')');
                            }
                            if(jsonObj){
                                for(var i = 0,ilen = data.rows.length; i < ilen;i++){
                                    for(var j = 0,jlen = jsonObj.length;j < jlen;j++){
                                        if(jsonObj[j].productSkuId == data.rows[i].productSkuId){
                                            data.rows[i].activityPrice = jsonObj[j].directPrice;
                                            break;
                                        }
                                    }
                                }
                            }
                            //保存之前操作记录处理结束
                            var html = Template.render("set-activity-price", data);
                            var d = new Dialog({
                                content: html,
                                width: 520
                            }).before("show",function(){
                                        $(".js-price").isPrice(true);
                                    }).show().after("hide",function(){
                                        d.destroy();
                                    });
                            //保存
                            $("#setDirectSave").click(function(){
                                var conFlag = true;
                                var postObj = "[";
                                $(".js-entered-direct").each(function(i){
                                    //通过索引来查找
                                    var skuId = $(this).data("skuid");
                                    var value = $(this).val();
                                    if(value !== ""){
                                        //postObj += "{'" + skuId + "':" + value + "},";
                                        postObj += "{productSkuId: " + skuId + ", directPrice: " + value + "},";
                                    }else{
                                        //如果为空则给提示，提示价格必须全部都输入
                                        $(this).addClass("error-input");
                                        conFlag = false;
                                    }
                                });
                                postObj = postObj.substr(0, postObj.length - 1);
                                postObj += "]";
                                //处理什么都没填的操作
                                if(postObj == "]"){
                                    postObj = "";
                                }
                                //console.log(postObj);
                                if(conFlag){
                                    parents.find("#skuInfos").val(postObj);
                                    d.hide();
                                }
                            });
                            //重置
                            $("#setDirectReset").click(function(){
                                $(".js-entered-direct").val("");
                            });
                        },
                        error:function(){
                            //console.log("error");
                        }
                    });
                });
            });
        },
        operationModifyPrice: function(){
            var _this = this;
            seajs.use(["template"],function(Template){
                function renderModifyTpl($el){
                    if($el.find(".js-factype-puhuo").prop("checked") === true){
                        var index = $el.find(".js-factype-puhuo").data("index");
                        if($el.find(".operation-modify").length <= 0){
                            var data = {};
                            data.index = index;
                            var html = Template.render("temp_2", data);
                            console.log(html);
                            $el.find(".set-direct-content").after(html);
                        }
                    }else{
                        $el.find(".operation-modify").remove();
                    }
                }
                $("#data-list").on("click", ".js-factype-puhuo,.js-factype-xk", function(){
                    if(_this.submitIndex == 1){
                        var parents = $(this).parents(".info");
                        renderModifyTpl(parents);
                    }
                });
            });
        },
        realTipIcon: function(){
            seajs.use("tip",function(Tip){
                //防止多次创建tip
                if(window.REAL_TIP_ICON){
                    REAL_TIP_ICON.destroy();
                }
                window.REAL_TIP_ICON = new Tip({
                    trigger: '.real_tip_icon',       // 触发器
                    content: '<div style="color: #666;">调价后，对以下产品均有效：<br/>（1）终端商已下单但品牌商未发货的订单内产品；<br/>（2）品牌商已发货，但终端商未入库的订单内产品；<br/>（3）终端商已入库但未销售的产品；</div>',    // 提示框显示的内容
                    arrowPosition: 7,
                    theme: 'blue'
                });
            });
        }
    };
    set_attr.init();
</script>
<script>
    var page, page1;
    $(function () {
        seajs.use(["pagination", "moment", "template"], function (Pagination, moment, template) {
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD");
            });
            template.helper('$getImageDomainPathFn', function (url, width, height) {
                return getImageDomainPath(url, width, height);
            });
            template.helper('formatNumber', function (price) {
                if (isNaN(price)) {
                    return price;
                } else {
                    return parseFloat(price).toFixed(2);
                }
            });

            var renderPagination = function () {
                page = new Pagination({
                    url: "${ctx}/brand/activity/products/data",
                    elem: "#pagination",
                    form: $("#activitySearchForm"),
                    method: "post",
                    handleData: function (json) {
                        //console.log(json);
                        if (json.rows && json.rows.length > 0) {
                            var html = template.render("feedback-templage", json);
                            $("#data-list ul").html(html);
                            set_attr.checkTempArr = json.rows;
                            //console.log(set_attr.checkTempArr);
                            set_attr.renderAfter();
                            $('.product-pic').each(function () {
                                $(this).bind('change', function () {
                                    uploadImage($(this).attr('id'));
                                });
                            });
                        } else {
                            $("#data-list ul").html("");
                        }
                    }
                });
            };

            renderPagination();

            //图片上传相关
            function showImage(uploadId, url, oldName) {
                var img = $("#" + uploadId).parent().prev().find("img");
                var input = $("#" + uploadId).parent().prev().find("input");
                img.attr({
                    "src": "${res}" + url,
                    "alt": oldName
                });
                input.val(url);
            }

            function uploadImage(uploadId) {
                seajs.use(["ajaxFileUpload"], function () {
                    $.ajaxFileUpload({
                        url: '${ctx}/common/showImg?fSize=2',
                        secureuri: false,
                        fileElementId: uploadId,
                        dataType: 'json',
                        success: function (data) {
                            //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                            $('#' + uploadId).bind('change', function () {
                                uploadImage(uploadId);
                            });

                            if (data.code == zttx.SUCCESS) {
                                showImage(uploadId, data.message, data.object);
                            }
                            else {
                                remind("error", data.message);
                            }
                        }
                    });
                });
            }

        });
    });
</script>
</body>
</html>