<%--
  @(#)list_unaddProducts 2014/3/26 9:12
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品线管理-未选择产品</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/prolinemaneg.css"/>
    <style>
        .proline-add-stepT .list-TD-imgs{
            margin: 0 10px;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/line/execute">产品线管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">未选择产品</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <form:form id="pagination-form" method="post" class="ui-form proline-step2-form clearfix">
                <div class="ui-form-item inline-block">
                    <label class="label">
                        选择分类：
                    </label>

                    <div class="inline-block">
                        <select class="ui-select js_select" name="cateId" id="proline-ppche">
                            <option value="">全部分类</option>
                            <c:forEach items="${productCatalogList}" var="option">
                                <option value="${option.id}" ${filter.cateId == option.id ? 'selected="selected"':''}>${option.name}</option>
                                <c:forEach items="${option.children}" var="sunOption">
                                <option value="${sunOption.id}" ${filter.cateId == sunOption.id ? 'selected="selected"':''}>&nbsp;|----${sunOption.name}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item inline-block">
                    <label class="label">
                        产品价格：
                    </label>
                    <input name="lowPrice" type="text" value="${filter.lowPrice}" class="ui-input js-price"/>
                    -
                    <input name="upperPrice" type="text" value="${filter.upperPrice}" class="ui-input js-price"/>
                </div>
                <%-- <div class="ui-form-item inline-block">
                    <label class="label">
                       货号：
                    </label>
                    <input name="productName" value="${filter.productName}" type="text" class="ui-input input-lager"/>
                </div> --%>
                <div class="ui-form-item inline-block">
                    <label class="label">
                        产品名称：
                    </label>
                    <input name="productName" value="${filter.productName}" type="text" class="ui-input input-lager"/>
                </div>
                <div class="ui-form-item inline-block">
                    <label class="label">
                        产品货号：
                    </label>
                    <input name="productNo" value="${filter.productNo}" type="text" class="ui-input input-lager"/>
                </div>
                <div class="ui-form-item inline-block">
                    <button type="button" class="simple_button" onclick="page.goTo(1);">搜索</button>
                </div>
            </form:form>
            <div class="js_addline2_menu clearfix">
            </div>
            <div class="js_addline2_con">
                <div class="js_addline2_conbox">
                    <!-- 未选择box（如果有问题尝试给这一行加入class：proline-contant） -->
                    <div class="stepT-checkallbar">
                        <label class="stepT-cb-span">
                            <input id="checkall" type="checkbox" class="ui-checkbox"/>
                            全选
                        </label>
                        <%--<a class="stepT-cb-a stepT-cb-zero">批量加入</a>--%>
                        <a class="stepT-cb-a stepT-cb-zero step-set-zero" style="display:none;">批量抹零</a>
                        <%--<a class="stepT-cb-a">批量移除</a>--%>
                        <a class="">批量设置折扣率：
                            <input type="text" class="ui-input ratetext js-price"/>折</a>
                        <a class="stepT-cb-sure stepT-nocb-sure">确定</a>
                        <a id="btn-submit" href="javascript:;" class="stepT-cb-sure ml5">批量加入产品线</a>
                    </div>
                    <table class="proline-add-stepT">  
                    </table>
                    <div id="pagination"></div>
                    <div class="stepT-sure-false clearfix">

                    	<a href="/brand/line/execute?id=${line.refrenceId}" class="ui_button ui_button_lgray fl ml5">上一步</a>
                         <a href="javascript:void(0);" class="ui_button ui_button_lblue fr mr5" id="butFinish">完成</a>
                        <%--<a href="${ctx}/brand/line/list" class="ui_button ui_button_lgray">取消</a>--%>
                    </div>
                    <form:form id="add-form" action="" method="post" cssStyle="display: none;">
                    </form:form>
                </div>
            </div>
            <!-- 批量抹零 -->
            <div class="alltozero" style="display:none;">
                <label>
                    <input type="radio" name="alltozero" class="ui-radio tozero" checked="checked"/>
                    直接抹零
                </label>
                <label class="mt5">
                    <input type="radio" name="alltozero" class="ui-radio tofourfive"/>
                    四舍五入
                </label>
                <a href="javascript:;" class="simple_button mt5 tosure">确认</a>
                <a href="javascript:;" class="simple_button mt5">取消</a>
            </div>
            <!-- 结束 -->
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script type="text/javascript" src="${res}/scripts/brand/prolinemanage.js"></script>
<script id="line-count-template" type="text/html">
<ul class="clearfix">
	<li class="selected">
		<a href="javascript:;">未选择（{{object.notSelCount}}）</a>
	</li>
	<li>
		<a href="${ctx}/brand/line/${line.refrenceId}/added">已选择（{{object.selCount}}）</a>
	</li>
</ul>
</script>
<script id="line-template" type="text/html">
<colgroup>
	<col width="410"/>
	<col width="190"/>
	<col width="90"/>
	<col width="90"/>
</colgroup>
<thead>
	<tr>
		<th>产品信息</th>
		<th><span>已加入的产品线</span></th>
		<th><span>折扣率</span></th>
		<th><span>折后价格</span></th>
	</tr>
</thead>
<tbody>
	{{each rows}}
	<tr>
		<td class="td-000">
			<div class="inline-block list-TD-check fl">
			<input type="checkbox" name="product" value="{{$value.id}}"></div>
			<img src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}" style="width:70px;height:70px;" alt="" class="list-TD-imgs fl"/>
			<div class="inline-block fl list-TD-des">
			<p class="number fn-text-overflow">货号：{{$value.productNo}}</p>
			<h4 class="title">&nbsp;{{$value.productTitle}}</h4>
			<p class="number fn-text-overflow">吊牌价：<span class="fn-rmb">￥</span> <span class="yprice">{{$value.productPrice}}</span></p>
			</div>
		</td>
		<td class="td-001">
		{{if $value.addedLines != null && $value.addedLines.length>0}}
		<ul class="scllobarbox">
		{{each $value.addedLines}}
			<li>{{$value.lineName}}</li>
		{{/each}}
		</ul>
		{{/if}}
		</td>
		<td class="td-002">
			<input type="text" class="proline-stepT-si noreteval js-price" placeholder="3.5" value="${showAgio}"/>&nbsp;折
		</td>
		<td class="td-003">
			<strong class="pro-price"><span class="fn-rmb">￥</span> <span class="nprice">{{$formatMoney $value.productPrice*${line.lineAgio}}}</span></strong>
		</td>
	</tr>
    {{/each}}
</tbody>
</script>
<script type="text/javascript">
    $(function () {
        addline2.init();
        var $addForm = $('#add-form');
        $('#btn-submit').on('click', function () {
            var $inputs = $('input[name="product"]:checked');
            if ($inputs.size() > 0) {
            	 $addForm.html("");
                 $inputs.each(function (index) {
                    var $this = $(this);
                    var id = $this.val();
                    var agio = $this.parents('tr').find('input:text.noreteval').val();
                    $('<input type="hidden">').attr('name', 'list[' + index + '].product').val(id).appendTo($addForm);
                    $('<input type="hidden">').attr('name', 'list[' + index + '].productAgio').val(agio).appendTo($addForm);
                }); 
                 $.ajax({
					type : "POST",
					url : "${ctx}/brand/line/${line.refrenceId}/add",
					data:$("#add-form").serialize(),
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS==json.code){
							remind("success","产品已选择成功",function(){
								page.render();
							}); 
						}else
							remind("error",json.message); 
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown); 
					}
				}); 

            } else {
                remind('error','请选择产品');
                return false;
            }
        });
        $('#checkall').click(function () {
            $('input[name="product"]').prop("checked", this.checked);
        });
        /* seajs.use(["pagination"], function (Pagination) {
            var pagination = new Pagination({
                currentPage: ${productsPage.page.currentPage},
                total: ${productsPage.page.totalPage},
                elem: '#pagination',
                pageClick: function (page) {
                    var $paginationForm = $('#pagination-form');
                    $('<input type="hidden" name="currentPage">').val(page).appendTo($paginationForm);
                    $paginationForm.submit();
                }
            });
        }); */
    });
</script>
<script>
  var page;
  $(function () {
    seajs.use(["pagination", "template", "jscroll"], function (Pagination, template) {
    	template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
    	
    	template.helper('$getImageDomainPathFn',function (url, width, height){
            return getImageDomainPath(url, width, height);
        });
    	
       	var renderPagination = function(){
       	page = new Pagination({
             url: "${ctx}/brand/line/${line.refrenceId}/unadd.json",
             elem: "#pagination",
             form:$("#pagination-form"),
             method:"post",
             handleData: function (data) {
             	 var html = template.render('line-count-template', data);
             	 $(".js_addline2_menu").html(html);
                 var html = template.render('line-template', data);
                 $(".proline-add-stepT").html(html);
                 $(".js-price").isPrice();

                 //处理小数点
                 $(".js-price").on("keyup",function(){
                     if($(this).val().split(".")[1]){
                         var discount = $(this).val().split(".")[1];
                         var discount_val = discount;
                         var len = discount.length;
                         if(len > 2){
                             discount = discount.substring(0,2);
                         }
                         $(this).val($(this).val().split(".")[0]+"."+discount);
                     }
                 })

                 $(".noreteval").on("blur",function(){

                     var op = $(this).parents("tr");
                     var _val = $(this).val();
                     var yprice = parseFloat(op.find(".yprice").html()),
                         nprice = op.find(".nprice");
                     if(_val > 10){
                         remind("error","折扣应小于10！");
                         $(this).val("10.00");
                     }
                     if(_val <= 0){
                         remind("error","折扣应大于1！");
                         $(this).val("1.00");
                     }
                     $(this).val(parseFloat(_val).toFixed(2));
                     nprice.html( parseFloat(yprice*$(this).val()/10).toFixed(2));
                     op.find("input[name=product]").prop("checked",true);
                 });

                 //点确定
                 $(".stepT-nocb-sure").on("click",function(){

                     var checkList = $(".list-TD-check input[type='checkbox']:checked");

                     if(checkList.length == 0){
                         alert("请选择您要设置的产品");
                         return;
                     }

                     var valNo = $(this).parent().find(".ratetext").val();
                     if($.trim(valNo)<=0){
                         remind("error","折扣应大于1！");
                         return;
                     }
                     if($.trim(valNo)>10){
                         remind("error","折扣应小于10！");
                         return;
                     }
                     if($.trim(valNo) != ""){

                         checkList.each(function(){

                             var op = $(this).parents("tr");

                             var yprice = parseFloat(op.find(".yprice").html());

                             op.find(".noreteval").val(valNo);

                             op.find(".nprice").html( parseFloat(yprice*valNo/10).toFixed(2));

                         })

                     }

                 })


             }
         });
         };
         renderPagination();
       });
       $("#butFinish").on("click",function(){
       		var $inputs = $('input[name="product"]:checked');
       		var $addForm = $('#add-form');
            if ($inputs.size() > 0) {
            	 $addForm.html("");
                 $inputs.each(function (index) {
                    var $this = $(this);
                    var id = $this.val();
                    var agio = $this.parents('tr').find('input:text.noreteval').val();
                    $('<input type="hidden">').attr('name', 'list[' + index + '].product').val(id).appendTo($addForm);
                    $('<input type="hidden">').attr('name', 'list[' + index + '].productAgio').val(agio).appendTo($addForm);
                }); 
                 $.ajax({
					type : "POST",
					url : "${ctx}/brand/line/${line.refrenceId}/add",
					data:$("#add-form").serialize(),
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS==json.code){
							window.location.href="${ctx}/brand/line/list";
						}else
							remind("error",json.message); 
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown); 
					}
				}); 

            } else {
                window.location.href="${ctx}/brand/line/list";
            }
       });
     });
</script>
</body>
</html>
