<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<% String[] arr = {"#ff0000","#d603f1","#0082cc","#009944","#996c33"}; %>
    <title>管理中心-用户管理-菜单管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
    	#privilege_select_div{
    		border: none;
    		background: none;
    	}
    	.free_shipping{
            color: #0082CC;
            margin-right: 5px;
            vertical-align: bottom;
        }
        .free_shipping:hover{
            color: #ff8800;
        }
   	</style>
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/> 
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">用户管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">菜单管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包屑结束 -->
    <div class="inner">
    <!-- 内容都放这个地方  -->
        <form:form class="ui-form tradorder-form clearfix" id="orderForm">
            <%--<div class="ui-form-item">
                <label class="">
                  	  品牌：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select140" name="brandsId">
                    	<option value="" >全部品牌</option>
                    	<c:if test="${infoList != null }">
                    		<c:forEach var="info" items="${infoList }">
                    			<option value="${info.refrenceId }">${info.brandName }</option>
                    		</c:forEach>
                    	</c:if>
                    </select>
                </div>
            </div>
            --%><div class="ui-form-item">
                <label class="">
                    	父菜单名称：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="parent.menuName"/>
            </div>
            <div class="ui-form-item">
                <label class="">
                    	菜单名称：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="menuName"/>
            </div>
            <div class="ui-form-item" >
                <label class="">
                    	菜单类型：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select115"  name="menuType">
                        <option value="0">菜单</option>
                        <option value="1">权限</option>
                    </select>
                </div>
            </div>
             <div class="ui-form-item" >
                <label class="">
                    	是否删除：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select115"  name="delFlag">
                        <option value="0">未删除</option>
                        <option value="1">已删除</option>
                    </select>
                </div>
            </div>
            <div class="ui-form-item" >
                <label class="">
                    	菜单级别：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select115"  name="menuLevel">
                        <option value="0">全部</option>
                        <option value="1">顶层</option>
                        <option value="2">头部菜单</option>
                        <option value="3">左侧第一级</option>
                        <option value="4">左侧第二级</option>
                    </select>
                </div>
            </div>
            <%--<div class="ui-form-item">
                <label class="">
                    终端商名：
                </label>
                <input type="text" class="ui-input tradorder-text-name" name="dealerName"/>
            </div>
            <div class="ui-form-item" style="display:none;">
                <label class="">
                    售后服务：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select115" name="list-pro-line2">
                        <option value="0">无</option>
                        <option value="1">有</option>
                    </select>
                </div>
            </div>
            <div class="ui-form-item">
                <label class="">
                    下单时间：
                </label>
                <input style="width: 101px;" readonly type="text" class="ui-input Wdate" id="start-cal" name="startTimeStr">
                -
                <input style="width: 102px;" readonly type="text" class="ui-input Wdate" id="end-cal" name="endTimeStr">
            </div>
            <div class="ui-form-item">
                <label class="">
                    订单状态：
                </label>
                <div class="inline-block">
                    <select class="ui-select js_select tradorder-select197" name="orderStatusStr">
                    	 <option value="">全部订单</option>
                    	 <c:if test="${orderStatus != null }">
		                    <c:forEach items="${orderStatus }" var="status">
		                    	 <option value="${status.dictValue }">${status.dictValueName }</option>
		                    </c:forEach>
	                    </c:if> 
                         
                        <option value="1">待付款订单</option>
                        <option value="2">待发货订单</option>
                        <option value="3">已发货订单</option>
                        <option value="4">成功的订单</option>
                        <option value="5">关闭的订单</option>
                        <option value="6">预订订单</option>
                        <option value="7">退款中的订单</option> 
                        
                    </select>
                </div>
            </div>
            --%><div class="ui-form-item" style="margin-left:74px;">
                <a class="simple_button tradorder-btn-sousuo" href="javascript:page.goTo(1);">搜索</a>
            	<a class="simple_button" onclick="javascript:addMenu()">新增</a>
            </div>
        </form:form>
        <div class="purorder">
            <div class="js_trador_tabs">
            	<div class="js_trador_con"><!-- 交易管理tabs内容 -->
                    <!-- tab 里面的 div 开始 -->
                    <div class="js_trador_conbox">
                        <table class="orderbox-table" style="margin-bottom: 0;">
                            <colgroup>
                                <col width="80" />
                                <col width="50" />
                                <col width="110" />
                                <col width="50" />
                                <col width="120" />
                                <col width="90" />
                                <col width="140" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>父菜单名称</th>
                                    <th>菜单名称</th>
                                    <th>菜单类型</th>
                                    <th>排序</th>
                                    <th>菜单级别</th>
                                    <th>显示</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                        </table>
                        <div class="checkallbox clearfix">
                            <div class="checkallsel inline-block">
                                <input type="checkbox" id="checkall"/>
                                <label for="checkall">全选</label>
                            </div>
                            <div class="page-top-down inline-block">
                                <a href="javascript:;" class="page-style page-top">上一页</a>
                                <a href="javascript:;" class="page-style page-down">下一页</a>
                            </div>
                        </div>
                        <div class="orderbox_contain" >
                        </div>
                        <div class="mt10" id="pagination"></div>
                    </div>
                    <!-- 结束 -->
                </div>
            </div><!-- tabs结束 -->
            
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div id="popup"></div>
<script id="feedback-templage" type="text/html">
{{each rows}}
	<div class="orderbox">
		<table class="orderbox-table">
			<colgroup>
				<col width="80" />
                <col width="50" />
                <col width="110" />
                <col width="50" />
                <col width="120" />
                <col width="90" />
                <col width="140" />
			</colgroup>
			<tbody>
			<tr>
				<td>
					<div class="inline-block list-TD-check fl">
                		<input type="checkbox" class="js_chk ui-checkbox mr5 addCheckbox" id="{{$value.refrenceId}}">
             		</div>
				</td>
				<td class="orderbox-td-tc">{{if $value.parent!=null}}{{$value.parent.menuName}}{{/if}}</td>
				<td class="orderbox-td-tc">{{$value.menuName}}</td>
				{{if $value.menuType}}
					<td class="orderbox-td-tc">权限</td>
				{{else}}
					<td class="orderbox-td-tc">菜单</td>
				{{/if}}
				<td class="orderbox-td-tc">{{$value.menuOrder}}</td>
				{{if $value.menuLevel==1}}
					<td class="orderbox-td-tc">顶级</td>
				{{else if $value.menuLevel==2}}
					<td class="orderbox-td-tc">头部</td>
				{{else if $value.menuLevel==3}}
					<td class="orderbox-td-tc">左侧第一级</td>
				{{else}}
					<td class="orderbox-td-tc">左侧第二级</td>
				{{/if}}
				
				{{if $value.showflag}}
					<td class="orderbox-td-tc">显示</td>
				{{else}}
					<td class="orderbox-td-tc">不显示</td>
				{{/if}}
				<td class="list-TD-do">
					 <a href="javascript:;" onclick="modify('{{$value.refrenceId}}')" >修改</a>
					 {{if $value.delFlag}}
						<a href="javascript:;" onclick="enableMenu('{{$value.refrenceId}}')">启用</a>
					 {{else}}
						<a href="javascript:;" onclick="deleteMenu('{{$value.refrenceId}}')">删除</a>	
					 {{/if}}
                     
                    
         		</td>
			</tr>
			</tbody>
		</table>
	</div>
{{/each}}
{{ if rows.length == 0 }}
    <div class="ta-c mt15">暂无数据</div>
{{ /if }}
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/brand_trade.js"></script>
<!-- 鼠标移入备注时的有数据的模板 -->
<script type="text/html" id="showRemark">
    <div id="remark_pop_{{id}}" class="remark_pop">
        <div class="hd">
            <label for="">
                <em style="color: {{color}}">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
               {{$trimLongString(text,50)}}
            </div>
            <div class="operate">
                <a data-id="{{id}}" data-colorindex="{{colorIndex}}" data-text="{{text}}" href="javascript:;" class="simple_button update_btn">修改</a>
            </div>
        </div>
    </div>
</script>
<!-- 鼠标点击备注时的模板 -->
<script type="text/html" id="dialogRemark">
    <div class="ui-head">
        <h3>备注</h3>
    </div>
    <div id="remark_dialog_{{id}}" class="remark_dialog">
        <div class="hd" id="levelMark">
			<label for="">
                <input name="leven" value="1" type="radio" {{ if colorIndex == 1 }} checked {{/if}}  /><em style="color: #ff0000">★</em>
            </label>
            <label for="">
                <input name="leven" value="2" type="radio" {{ if colorIndex == 2 }} checked {{/if}}   /><em style="color: #d603f1">★</em>
            </label>
            <label for="">
                <input name="leven" value="3" type="radio" {{ if colorIndex == 3 }} checked {{/if}}  /><em style="color: #0082cc">★</em>
            </label>
            <label for="">
                <input name="leven" value="4" type="radio" {{ if colorIndex == 4 }} checked {{/if}}  /><em style="color: #009944">★</em>
            </label>
            <label for="">
                <input name="leven" value="5" type="radio" {{ if colorIndex == 5 }} checked {{/if}}  /><em style="color: #996c33">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
                <textarea id="remark">{{text}}</textarea>
            </div>
            <div class="operate">
                <button class="confirm_btn simple_button" type="button">确定</button>
                <button class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
</script>
<script id="modify-template" type="text/html">
<form id="modify-form">
<input type="hidden" name="refrenceId" value="{{refrenceId}}"/>
{{if delFlag}}
<input type="hidden" name="delFlag" value="1"/>
{{else}}
<input type="hidden" name="delFlag" value="0"/>
{{/if}}

<table>
    <tr>
        <td>菜单名</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="menuName" value="{{menuName}}"/></td>
    </tr>
	<tr>
        <td>菜单地址</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="menuAddr" value="{{menuAddr}}"/></td>
    </tr>
	<tr>
        <td>权限</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="authority" value="{{authority}}"/></td>
    </tr>
	<tr>
        <td>菜单级别</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="menuLevel" name="menuLevel">
				<option value="1">顶级</option>
				<option value="2">头部菜单</option>
				<option value="3">左侧第一级</option>
				<option value="4">左侧第二级</option>
			</select>
		</td>
    </tr>
	<tr>
        <td>父菜单名</td>
        <td>
			{{if parent!=null}}
				<input class="ui-input js-setcom-dprice js-price" readonly type="text" value="{{parent.menuName}}"/>
			{{/if}}
		</td>
    </tr>
	<tr>
        <td>是否打开</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="menuOpen" name="menuOpen">
				<option value="0">不打开</option>
				<option value="1">打开</option>
			</select>
		</td>
    </tr>
	<tr>
        <td>菜单序号</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="menuOrder" value="{{menuOrder}}"/></td>
    </tr>
	<tr>
        <td>是否显示</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="showflag" name="showflag">
				<option value="0">不显示</option>
				<option value="1">显示</option>
			</select>
		</td>
    </tr>
	<%--<tr>
        <td>父菜单</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="" value="{{upMenuId}}"/></td>
    </tr>--%>
</table>
<div class="operate" style="text-align: center;">
        <a class="simple_button confirm_btn modify-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn modify-cancel" style="" href="javascript:;">取消</a>
</div>
</from>
</script>
<script id="add-template" type="text/html">
<form id="add-form">
<input type="hidden" name="upMenuId" value="{{pid}}"/>
<table>
    <tr>
        <td>菜单名</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="menuName" /></td>
    </tr>
	<tr>
        <td>菜单地址</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="menuAddr" /></td>
    </tr>
	<tr>
        <td>权限</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="authority" /></td>
    </tr>
	
	<tr>
        <td>菜单级别</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="menuLevel" name="menuLevel">
				<option value="1">顶级</option>
				<option value="2">头部菜单</option>
				<option value="3">左侧第一级</option>
				<option value="4">左侧第二级</option>
			</select>
		</td>
    </tr>
	<tr>
        <td>菜单类型</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;"  name="menuType">
				<option value="0">菜单</option>
				<option value="1">权限</option>
			</select>
		</td>
    </tr>
	<tr>
        <td>是否打开</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="menuOpen" name="menuOpen">
				<option value="0">不打开</option>
				<option value="1">打开</option>
			</select>
		</td>
    </tr>
	<tr>
        <td>菜单序号</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="menuOrder" value="{{menuOrder}}"/></td>
    </tr>
	<tr>
        <td>是否显示</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="showflag" name="showflag">
				<option value="0">不显示</option>
				<option value="1">显示</option>
			</select>
		</td>
    </tr>
	<%--<tr>
        <td>父菜单</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="" value="{{upMenuId}}"/></td>
    </tr>--%>
</table>
<div class="operate" style="text-align: center;">
        <a class="simple_button confirm_btn add-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn add-cancel" style="" href="javascript:;">取消</a>
</div>
</from>
</script>
<script>
   var page;
   var colorArr = ["#ff0000","#d603f1","#0082cc","#009944","#996c33"];

   function mouseRemarks(){
   	   seajs.use(["popup"],function(Popup){
   			$(".remarks").each(function(){
           var id = $(this).data("id");
           var color = $(this).data("color");
           if(color == ""){
               return;
           }
           new Popup({
               trigger: $(this),
               element: '#popup',
               align: {
                   selfXY: ["right", 0],
                   baseXY: [63, 35]
               }
           }).before('show',function(){
           
           	 var current = this.activeTrigger;
          	     var color = current.data("color");
          	     var text = current.data("text");
          	     var colorIndex = current.data("colorindex");
          	     var data = {
	           	 	id: id,
	           	 	color: color,
	           	 	text: text,
	           	 	colorIndex: colorIndex
	           	 }
                 template.helper('$trimLongString', function (string,num) {
                      return trimLongString(string,num);
                 });
           	 	var html = template.render('showRemark', data);
                this.element.html(html);
           });
           
       	 })	   		
   	   })
   }
   
  $(function () {
	   
    seajs.use(["dialog","pagination","moment","template","ztree"], function (Dialog,Pagination,moment,template,ztree) {
    	
    	var modifyDialog = new Dialog({
    	       content: "",
    	       width: 600
    	   });
    	   window.modify = function(id){
    	       dialogLoading(function(dd){
    	           $.ajax({
    	               url: "${ctx}/common/menuInfo/modify/forward",
    	               data: {refrenceId:id},
    	               method: "post",
    	               dataType: "json",
    	               success: function(data){
    	                   html = template.render("modify-template", data);
    	                   dd.hide();
    	                   modifyDialog.element.html(html);
    	                   modifyDialog.show();
    	                   $("#menuLevel").val(data.menuLevel);
    	                   if(data.menuOpen){
    	                	   $("#menuOpen").val(1);
    	                   }else{
    	                	   $("#menuOpen").val(0);
    	                   }
    	                   if(data.showflag){
    	                	   $("#showflag").val(1);
    	                   }else{
    	                	   $("#showflag").val(0);
    	                   }
    	                   
    	               }
    	           });
    	       }, "请求数据中，请稍后...");
    	   }  
    	   
    	   $(document).on("click",".modify-confirm",function(){
               $.ajax({
                   url: "${ctx}/common/menuInfo/modify",
                   data: $("#modify-form").serialize(),
                   method: "post",
                   dataType: "json",
                   success: function(json){
                	   if(zttx.SUCCESS==json.code){
   	                    remind("success","操作成功！");
   	                	page.goTo(page.current);
   	                }else
   	                    remind("error",json.message);  
                   }
               });
               modifyDialog.hide();
           });
           //取消
           $(document).on("click",".modify-cancel",function(){
        	   modifyDialog.hide();
           });
           
           window.deleteMenu= function(id){
        	    confirmDialog("是否删除",function(){
        	        $.ajax({
        	            url:"/common/menuInfo/remove",
        	            type:"post",
        	            data:{refrenceId:id},
        	            dataType: "json",
        	            success:function(json){
        	                if(zttx.SUCCESS==json.code){
        	                    remind("success","操作成功！");
        	                    page.render();
        	                }else
        	                    remind("error",json.message);
        	            },
        	            error:function(XMLHttpRequest, textStatus, errorThrown){
        	                remind("error",errorThrown);
        	            }
        	        });
        	    });
        	}
           
           window.enableMenu=function(id){
        	   confirmDialog("是否启用除",function(){
       	        $.ajax({
       	            url:"/common/menuInfo/enable",
       	            type:"post",
       	            data:{refrenceId:id},
       	            dataType: "json",
       	            success:function(json){
       	                if(zttx.SUCCESS==json.code){
       	                    remind("success","操作成功！");
       	                    page.render();
       	                }else
       	                    remind("error",json.message);
       	            },
       	            error:function(XMLHttpRequest, textStatus, errorThrown){
       	                remind("error",errorThrown);
       	            }
       	        });
       	     });
           }
           var addDialog = new Dialog({
    	       content: "",
    	       width: 600
    	   });
           
           
           window.addMenu=function(){
        	   //console.log($("[type='checkbox']:checked"))
        	   if($(".js_chk.ui-checkbox.mr5.addCheckbox:checked").size()!=1){
        		   alert("请选择一个父菜单");
        		   return ;
        	   }
        	   var id=$(".js_chk.ui-checkbox.mr5.addCheckbox:checked").attr("id");
        	   html = template.render("add-template", {pid:id});
               addDialog.element.html(html);
               addDialog.show();
                 
           }
           
           $(document).on("click",".add-confirm",function(){
               $.ajax({
                   url: "${ctx}/common/menuInfo/add",
                   data: $("#add-form").serialize(),
                   method: "post",
                   dataType: "json",
                   success: function(data){
                       if(zttx.SUCCESS==data.code){
      	                    remind("success","操作成功！");
      	                  	page.goTo(page.current);
      	                }else{
      	                	remind("error",data.message);
      	                }   
                   }
               });
               addDialog.hide();
           });
           //取消
           $(document).on("click",".add-cancel",function(){
        	   addDialog.hide();
           });
           
    	template.helper('$formatDate', function (millsec) {
           	return moment(millsec).format("YYYY-MM-DD HH:mm");
       	});
       	template.helper('$hanleColor',function(colorIndex){
       		return colorArr[colorIndex-1];
       	});
        template.helper('$trimLongString', function (string,num) {
            return trimLongString(string,num);
        });
		template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
        page = new Pagination({
             url: "${ctx}/common/menuInfo/data",
             elem: "#pagination",
             form:$("#orderForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".orderbox_contain").html(html);
                 if(json.rows.length == 0){
                     $(".checkallbox").hide();
                 }
                 mouseRemarks();
             }
         });

        $(".page-top").on("click",function(ev){
            ev.preventDefault();
            page.goPrev();
        });

        $(".page-down").on("click",function(ev){
            ev.preventDefault();
            page.goNext();
        });
        
        $("#nosee-order").on("click",function(){
        	if($(this)[0].checked){
        		$("#isShowClose").val("1");
        	}else{
        		$("#isShowClose").val("");
        	}
        	page.render();
        });
        
	            
        
       });
     });
     
     
     TradePurorder.confirmUpdatePop = function(orderID,type,dailog,discount,payState,brandsId){
      	if( discount > 10){
     		remind("error","折扣率不正确, 请重新输入");
     		return ;
     	} 
        if(type == 'price'){			//修改金额
			$('#updatePrice input[name="refrenceId"]').val(orderID);
			$('#updatePrice input[name="brandsId"]').val(brandsId);
			      $.ajax({
					type:"post",
					url:"${ctx}/brand/order/modPrice",
					data : $('#updatePrice').serialize(),
					traditional:true,
					dataType: "json",
					success:function(json){
						if(json.code == zttx.SUCCESS){
							remind("success","修改成功！");
							dailog.hide();
							page.render();
						}else{
							remind("error",json.message);
						}
					}
				});
        }else if(type == 'fare'){			//修改运费
            var _val = $(".wuliuprice").val();
            if(_val == "" ){
            	remind("error","请输入物流费");
                return false;
            }
            if(_val == 0 && payState==2){
                confirmDialog("终端商已全部付款，将运费设为0，终端商将无需支付运费，是否继续？",function(){
                	TradePurorder.updatePop(orderID, dailog);
                });
            }
            else
            	TradePurorder.updatePop(orderID, dailog);
        	}
       };
       
       TradePurorder.updatePop = function(orderID,dialog,brandsId){
       		$('#updatePrice input[name="refrenceId"]').val(orderID);
       		$('#updatePrice input[name="brandsId"]').val(brandsId)
            $.ajax({
                type:"post",
                url:"${ctx}/brand/order/modFare",
                data : $('#updatePrice').serialize(),
                traditional:true,
                dataType: "json",
                success:function(json){
                    if(json.code == zttx.SUCCESS){
                        remind("success","修改成功！");
                        dialog.hide();
                        page.render();
                    }else{
                        remind("error",json.message);
                    }
                }
            });
       };
       
      //关闭订单  
     TradePurorder.closeTradeFn = function(orderId,dialog,brandsId,brandsName){
     	$('#closeOrder input[name="orderId"]').val(orderId);
     	$('#closeOrder input[name="brandsId"]').val(brandsId);
     	$('#closeOrder input[name="brandsName"]').val(brandsName);
     	  $.ajax({
				type:"post",
				url:"${ctx}/brand/order/closeOrder",
				data : $('#closeOrder').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			}); 
		}	
       
         //站内信
         TradePurorder.sendMessageFn = function (dealerId, brandName, dialog ){
         	var title = "来自"+brandName+"信息";
          	var content = $.trim($('#message_text').val());
          	if(content == ''){
          		alert("内容不能为空");
          		return ;
          	}
          	if($.trim(dealerId) == ''){
          		return ;
          	}
       		  $.ajax({
				type:"post",
				url:"${ctx}/brand/message/sendDealer",
				data :{dealerIds : dealerId, title : title, content : content},
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","发送成功！");
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			}); 
       }  
        
          //延长收货期限
        TradePurorder.extensionTimeFn = function (orderId, dialog){
        	$('#outActTime input[name="refrenceId"]').val(orderId);
       		 $.ajax({
				type:"post",
				url:"${ctx}/brand/order/outActTime",
				data : $('#outActTime').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			}); 
       } 
      
      //备注
       TradePurorder.handleRemarkFn = function (current_obj,orderIds,dialog){
       		var levelMark = $.trim($('#levelMark input[name="leven"]:checked').val());
       		
       		if(levelMark < 1 || levelMark > 5){
       			alert("没有该星级");
       			return ;
       		}
       		var brandRemark = $.trim($('#remark').val());
       		if(brandRemark.length == ''){
       			alert("备注的内容不能为空!");
       			return ;
       		}
       		
       	
       		
       		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/remark",
				data : {orderIds : orderIds,levelMark : levelMark, brandRemark : brandRemark },
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","备注成功！");
						
						
						if(!current_obj){
							
							dialog.hide();
							
							page.render();
							
							return;
						}
						
						var star = current_obj.find(".red-star");
						
						star.css({
							color: colorArr[levelMark-1]
						})
						star.show();
						current_obj.data({
							"color": colorArr[levelMark-1],
							"text": brandRemark,
							"colorindex": levelMark
						})
						mouseRemarks();
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			});  
       }
     
     //批量免邮
     TradePurorder.handleShippingFn = function (orderIds,dialog){
     		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/avoidMail",
				data : {orderIds : orderIds},
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","操作完成！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			});  
     }
     
     TradePurorder.sendGoods = function(orderId, refundStatus){
     
     	if(1==refundStatus || 2==refundStatus || 3==refundStatus)
     	{
	     	confirmDialog("退款申请中，确认继续发货吗？",function(){
	            window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
	        });
        }else if(4==refundStatus){
            confirmDialog("已拒绝退款，确认继续发货吗？",function(){
                window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
            });
        }else{
        	window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
        }
     }
     
     TradePurorder.init();
     
	$('#checkall').on('click', function (){
        var checked = $(this).prop("checked");
        $('.orderbox input[type="checkbox"]').each(function(){
            $(this).prop("checked",checked);
        })
	});

   $('.orderbox_contain').on("click",'input[type="checkbox"]',function(){

       if(!$(this).prop("checked")){
           $('#checkall').prop("checked",false);
       }

   })

   
	
</script>
</body>
</html>