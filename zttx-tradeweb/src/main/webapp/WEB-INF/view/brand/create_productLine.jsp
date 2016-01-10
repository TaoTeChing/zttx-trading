<%--
  @(#)create_ProductLine.jsp 14-3-18 上午9:42
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
	<c:set value="新增产品线" var="pageName"/>
	<c:if test="${!empty id}">
		<c:set value="修改产品线" var="pageName"/>
	</c:if>
    <title>管理中心-产品线管理-${pageName}</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/prolinemaneg.css"/>
    <%--<link rel="stylesheet" href="${res}/styles/common/validformStyle.css"/>--%>
    <style>
        .Validform_wrong{
            line-height: 28px;
            vertical-align: top;
            color: red;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <!-- 内容都放这个地方  -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/line/list">产品线管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">${pageName}</span>
        </div>
        <div class="fr">
            <c:choose>
                <c:when test="${empty id}">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </c:when>
                <c:otherwise>
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                        <jsp:param value="0" name="isShow"/>
                    </jsp:include>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="inner">
        <form:form id="add-form" class="ui-form addLine_form" data-widget="validator" method="post">
            <div class="tips">
                <i class="v2-icon-explain"></i>
                说明：产品线可以用来控制您的供货价格，供货区域，供货产品，以及想要供货的终端商
            </div>
        	<input type="hidden" id="id" name="id" value="${id}"/>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    选择品牌：
                    <span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                	<c:choose>
                		<c:when test="${empty id}">
                		<select class="ui-select js_select" name="brandesId" id="sel-brandsId" datatype="s32-32" required data-display="产品线名称" nullmsg="请选择品牌" style="height: 35px;width: 200px;">
                            <option value="">请选择品牌...</option>
	                        <c:forEach items="${brands}" var="brand">
	                            <option value="${brand.refrenceId}">${brand.brandName}</option>
	                        </c:forEach>
                    	</select>
                		</c:when>
                		<c:otherwise>
                		<span class="ui-form-noEdit">${line.brandsName}</span>
                		<input type="hidden" name="brandesId" id="sel-brandsId" value="${line.brandsId}"/>
                		</c:otherwise>
                	</c:choose>
                </div>
            </div>
            <div class="ui-form-item factoryItem">
                <label class="ui-label" for="">
                    工厂店活动：
                    <span class="ui-form-required">*</span>
                </label>
                <div class="radio_box inline-block">
                    <label><input class="ui-radio" name="factoryStore" type="radio" <c:if test="${line.factoryStore}">checked</c:if>
                                  value="1" <c:if test="${factoryFlag}">disabled</c:if>/>是 </label>
                    <label><input class="ui-radio ml5" name="factoryStore" type="radio"  <c:if test="${!line.factoryStore}">checked</c:if>
                                  value="0" <c:if test="${line.factoryStore}">disabled</c:if>/>否</label>
                    <span>（使用工厂店产品线报名参加工厂店活动，且该产品线不允许取消工厂店标记，不允许删除。）</span>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    用户分类：
                    <span class="ui-form-required">*</span>
                </label>
                <div class="radio_box inline-block">
                    <label><input class="ui-radio js-pt-user" name="lineType" type="radio" value="0"  <c:if test="${empty line.lineType || line.lineType==0}">checked</c:if>/>
                    普通用户</label>
                    <label><input class="ui-radio ml5 js-sq-user" name="lineType" type="radio" value="1" <c:if test="${line.lineType==1}">checked</c:if>/>
                    加盟用户</label>
                </div>
            </div>
            <div class="ui-form-item js-usertype-box">
                <label class="ui-label">
                    &nbsp;
                </label>
                <div>
                    <input type="checkbox" name="openUserAry" value="1" <c:if test="${openTypeAry[0]==1}">checked</c:if>>游客　
                    <input type="checkbox" name="openUserAry" value="2" <c:if test="${openTypeAry[1]==1}">checked</c:if>>注册会员　
                    <input type="checkbox" name="openUserAry" value="3" <c:if test="${openTypeAry[2]==1}">checked</c:if>>认证会员
                </div>
             </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    产品线名称：
                    <span class="ui-form-required">*</span>
                </label>
                <input type="text" name="lineName" id="lineName" class="ui-input proline-name" value="${line.lineName}" required data-display="产品线名称"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    产品线折扣率：
                    <span class="ui-form-required">*</span>
                </label>
                <span>吊牌价X</span>
                <input id="agio" name="lineAgio" type="text" class="ui-input proline-sellzhe js-price" placeholder="10.0" value="${agio}" required data-display="产品线折扣率" data-rule="min{min:'0.01'} max{max:'10.00'}" />
                <span>折</span>
                <span>（折扣范围 0.01-10折，最多可设置小数点后2位）</span>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    产品线采购时间：
                    <span class="ui-form-required">*</span>
                </label>

                <div class="radio_box inline-block">
                    <input checked="checked" class="ui-radio" name="buyTimeType" type="radio" value="0" id="owner1" <c:if test="${empty line.buyType || line.buyType==0}">checked</c:if>/>
                    <label for="owner1">不限时间</label>
                    <input class="ui-radio ml5" name="buyTimeType" type="radio" id="owner2" value="1" <c:if test="${line.buyType==1}">checked</c:if>/>
                    <label for="owner2">设定时间</label>

                    <div class="inline-block proline-getimehide" <c:if test="${empty line.buyType || line.buyType==0}">style="display: none;"</c:if>>
                        <input <c:if test="${!empty line.buyStart}" >value="${fns:getTimeFromLong(line.buyStart, 'yyyy-MM-dd')}"</c:if> name="buyStartTime" type="text" class="ui-input date" id="start-cal" readonly="readonly"/>
                        -
                        <input <c:if test="${!empty line.buyEnd}" >value="${fns:getTimeFromLong(line.buyEnd, 'yyyy-MM-dd')}"</c:if> name="buyEndTime" type="text" class="ui-input date" id="end-cal" readonly="readonly"/>
                    </div>
                </div>
                <input type="hidden" id="timeError" name="timeError" datatype="*" value="1"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    指定类型：
                    <span class="ui-form-required">*</span>
                </label>
                <div class="radio_box inline-block">
                    <input class="ui-radio js-area" name="appointType" type="radio" value="0"  <c:if test="${empty line.appointType || line.appointType==0}">checked</c:if>/>
                    <label>指定区域</label>
                    <input class="ui-radio ml5 js-user" name="appointType" type="radio" value="1" <c:if test="${line.appointType==1}">checked</c:if>/>
                    <label>指定用户</label>
                </div>
            </div>
            <!--div class="ui-form-item">
                <label class="ui-label" for="">
                    该产品线起批量：
                    <span class="ui-form-required">*</span>
                </label>

                <div class="inline-block proline-N-che">
                    <input name="buyNum" maxlength="4" type="text" class="ui-input proline-number js-price"/>件
                    <input name="buyMoney" maxlength="11" type="text" class="ui-input proline-number js-price" data-max="99999999.99"/>元
                </div>
                <span class="ui-form-other">(请至少填写一个)</span>
            </div-->
            <jsp:include page="/WEB-INF/view/brand/list_dealer_level.jsp">
             	<jsp:param value="sel-brandsId" name="brandsName"/>
             	<jsp:param value="1" name="addLevelType"/>
             	<jsp:param value="${id}" name="lineId"/>
             	<jsp:param value="1" name="docOpen"/> 
             	<jsp:param value="1" name="isShowTitle"/>
             </jsp:include>
             
            <div class="ui-form-item js_area_box">
                <label class="ui-label" for="">
                    选择地区：
                    <span class="ui-form-required">*</span>
                </label>
                 <div class="mt5 inline-block">
                     <%--<label>
                         <input type="checkbox">浙江省
                     </label>
                     <label>
                         <input type="checkbox" name="areaAry" value="330100">杭州
                     </label>
                     <label>
                         <input type="checkbox" name="areaAry" value="330200">宁波
                     </label>
                     <label>
                         <input type="checkbox" name="areaAry" value="330300">温州
                     </label>--%>
                     <%--<style>
                         dl.select-area-item{ border-bottom: 1px solid #ddd;}
                         dl.select-area-item dt{ width: 13%; float: left;padding: 5px;}
                         dl.select-area-item dd{width: 80%;float: left; line-height: 30px;}
                         dl.select-area-item dd label{padding: 5px;}
                     </style>--%>
                     <div id="js-showArea" class="inline-block" style="width: 770px;">
                         <div class="ts-select-city">
                             <div class="title">
                                 请选择区域：全选 <input type="checkbox" class="js-select-all"/> 反选 <input type="checkbox" class="js-inverse-all"/>
                             </div>
                             <div class="ts-sc-content clearfix" style="height: 335px;">

                             </div>
                         </div>
                     </div>
                 </div>
             </div>
            <div class="ui-form-item">
                <label class="ui-label">
                    &nbsp;
                </label>
                <button class="ui_button ui_button_lblue proline-next-btn" type="submit" id="lineSave">
                    下一步
                </button>
            </div>
        </form:form>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/brand/prolinemanage.js"></script>
<script src="${res}/scripts/common/area.js"></script>
<%--<script type="text/javascript" src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>--%>
<script>


    function showArea()
    {

        var s = "",i = 200;
        for(var p in area)
        {
            var pArr = p.split(" ");
            i--;
            s += '<div class="province-box" style="z-index:'+i+';">';
            s += '<div class="province"><input value="' + pArr[1] + '" type="checkbox"/>'+ pArr[0] +'<span class="num"></span><i class="arrow"></i></div>';
            s += '<div class="city-box">';
            if(pArr[1] in {"710000":"", "810000":"", "820000":""})
            {
                s += '<label><input value="' + pArr[1] + '" type="checkbox" name="areaAry"  id="city_value_'+pArr[1]+'"/>'+ pArr[0] +'</label>';
            }
            for(var c in area[p])
            {
                var cArr = c.split(" ");
                s += '<label><input value="' + cArr[1] + '" type="checkbox" name="areaAry" id="city_value_'+cArr[1]+'"/>'+ cArr[0] +'</label>';
            }
            s += '</div>';
            s += '</div>';
        }
        $("#js-showArea .ts-select-city .ts-sc-content").html(s);

        <c:forEach var="obj" items="${regionalList}" varStatus="status">
            <%--改用ID来选择城市//$(":input[name='areaAry'][value='${obj.areaNo}']").attr("checked", true);--%>
            $("#city_value_${obj.areaNo}").attr("checked",true);
        </c:forEach>
    }

    //新增代码20140913
    var productLine = {
        init:function(){
            this.userType();//用户分类初始化
            this.clickUserType();//用户分类点击的时候变化
            this.theType();//指定类型初始化
            this.clickTheType();//指定类型点击的时候变化
            this.fixedDiscount();
        },
        userType:function(){
            if($(".js-pt-user").prop("checked")){
                $(".js-usertype-box").show();
                $(".js-user").hide().next().hide();
            }
            if($(".js-sq-user").prop("checked")){
                $(".js-usertype-box").hide();
                $(".js-user").show().next().show();
            }
        },
        clickUserType:function(){
            var _this = this;
            $("input[name=lineType]").click(function(){
                _this.userType();
                if($(this).hasClass("js-pt-user")){
                    $(".js-area").prop("checked",true);
                    _this.theType();
                }
            });
        },
        theType:function(){
            if($(".js-area").prop("checked")){
                $(".js_dealers_box").hide();
                $(".js_area_box").show();
            }
            if($(".js-user").prop("checked")){
                $(".js_dealers_box").show();
                $(".js_area_box").hide();
            }
        },
        clickTheType:function(){
            var _this = this;
            $("input[name=appointType]").click(function(){
                _this.theType();
            });
        },
        selectArea:function(){

            //初始化省的状态
            var initNumber = function(){
                $(".ts-sc-content .province-box").each(function(){
                    var _num = $(this).find(".city-box input[type=checkbox]:checked").length;
                    if(_num > 0 ){
                        $(this).find(".province input[type=checkbox]").prop("checked",true);
                        $(this).find(".num").html("("+_num+")");
                    }
                    if(_num == 0 ){
                        $(this).find(".province input[type=checkbox]").prop("checked",false);
                        $(this).find(".num").html("");
                    }
                });
            };
            initNumber();

            var countNumber = function(self){
                var _num = self.find(".city-box input[type=checkbox]:checked").length;
                if(_num == 0){
                    self.find(".num").html("");
                    return;
                }
                self.find(".num").html("("+_num+")");
            };

            $(".province-box").hover(function(){
                $(this).addClass("hover");
            },function(){
                $(this).removeClass("hover");
                $(this).find("iframe").remove();
                countNumber($(this));
            });

            $(".province input[type=checkbox]").click(function(){
                var _parents = $(this).parents(".province-box");
                _parents.find(".city-box input[type=checkbox]").prop("checked",this.checked);
            });
            <%-- 全选 --%>
            $(".js-select-all").click(function(){
                $(".ts-sc-content input[type=checkbox]").prop("checked",this.checked);
                initNumber();
            });
            <%-- 反选 --%>
            $(".js-inverse-all").click(function(){
                $(".ts-sc-content input[name=areaAry]").each(function(){
                    this.checked =! this.checked;
                });
                initNumber();
            });
        },
        fixedDiscount:function(){
            $("#agio").change(function(){
                var value = $(this).val();
                if(value == ""){
                    return false;
                }
                $(this).val(parseFloat(value).toFixed(2));
            });
        }
    };
    $(function () {
        productLine.init();

        seajs.use(['template'], function (template) {
            rangeCalendar('start-cal', 'end-cal',null,true);
            var $selBrandsId = $('#sel-brandsId');
            var $selectLevel = $('#level_select');
            $selBrandsId.change(function (event) {
                dealerLevel.init();
                <%-- 2015-03-30 工厂店二期需求 切换的时候查询品牌是否属于工厂店品牌 --%>
                var refrenceId = $(this).find("option:selected").val();
                if(refrenceId == ""){
                    $("input[name=factoryStore]").eq(0).prop("disabled",false);
                    productLine.userType();
                    return false;
                }
                $.ajax({
                    url:"${ctx }/brand/line/verify",
                    method:"post",
                    data:{brandsId:refrenceId},
                    dataType:"json",
                    success:function(data){
                        //console.log(data);
                        if(data.code == zttx.SUCCESS){
                            if(data.object && data.object.brandesFactory == true){
                                $(".factoryItem").show();
                                if(data.object && data.object.factoryStore == true){
                                    $("input[name=factoryStore]").eq(0).prop("disabled",true);
                                    $("input[name=factoryStore]").eq(1).prop("checked",true);
                                }else{
                                    $("input[name=factoryStore]").eq(0).prop("disabled",false);
                                    $("input[name=factoryStore]").eq(1).prop("checked",true);
                                }
                                productLine.userType();
                            }else{
                                $(".factoryItem").hide();
                            }

                        }else{
                            remind("error",data.message);
                        }
                    }
                });
            });

            addlineONE.init();

            baseFormValidator({
                selector:"#add-form",
                isAjax:true,
                beforeSubmitFn:function(){
                    if($(".js-pt-user").prop("checked") == true && $("input[name=openUserAry]:checked").length == 0){
                        remind("error","普通用户请选择身份");
                        return;
                    }
                    if($(".js-area").prop("checked")== true && $("input[name=areaAry]:checked").length == 0){
                        remind("error","请选择区域");
                        return;
                    }
                    $("#lineSave").attr("disabled", true);
                    $.ajax({
                        url: '${ctx}/brand/line/save',
                        type: 'post',
                        data: $("#add-form").serialize(),
                        dataType: 'json',
                        success: function(data, textStatus){
                            $("#lineSave").attr("disabled", false);
                            if(zttx.SUCCESS==data.code){
                                var url = "${ctx}/brand/line/" + data.object.id + "/unadd";
                                if(data.object.isAdd){
                                    window.location.href= url;
                                }else{
                                    window.location.href=url;
                                }
                            }else if(zttx.VALIDERR==data.code){
                                setErrMsg("#add-form",data.rows);
                            }else{
                                remind("error",data.message);
                            }
                        },
                        error: function(data){
                            $("#lineSave").attr("disabled", false);
                            remind("error",data.message);
                        }
                    });
                    return false;
                }
            });
        });
    });

    $(function(){
        //IE下浏览器假死的原因为 校验遍历表单里面所有的input元素， 而地区选择input太多，导致假死。
        setTimeout(function(){
            // 选中地区
            showArea();
            // 选择区域操作
            productLine.selectArea();
        },100)
    })
</script>
</body>
</html>
