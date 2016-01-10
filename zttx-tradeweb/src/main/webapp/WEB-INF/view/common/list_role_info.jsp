<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<% String[] arr = {"#ff0000","#d603f1","#0082cc","#009944","#996c33"}; %>
    <title>管理中心-权限管理-角色管理</title>
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
                <a class="link" href="${ctx}/brand/order/purorder">权限管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">角色管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包屑结束 -->
    <div class="inner">
    <!-- 内容都放这个地方  -->
        <form:form class="ui-form tradorder-form clearfix" id="orderForm">
            <div class="ui-form-item">
                <label class="">
                    	角色代码：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="roleCode"/>
            </div>
            <div class="ui-form-item">
                <label class="">
                    	角色名称：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="roleName"/>
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
            <div class="ui-form-item" style="margin-left:74px;">
                <a class="simple_button tradorder-btn-sousuo" href="javascript:page.goTo(1);">搜索</a>
                <a class="simple_button" onclick="javascript:addRole()">新增</a>
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
                                <col width="140" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>角色代码</th>
                                    <th>角色名称</th>
                                    <th>是否可删除</th>
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
                <col width="140" />
			</colgroup>
			<tbody>
			<tr>
				<td class="orderbox-td-tc">{{$value.roleCode}}</td>
				<td class="orderbox-td-tc">{{$value.roleName}}</td>
				<td class="orderbox-td-tc">
					{{if $value.canDel}}
						是
					{{else}}
						否
					{{/if}}
				</td>
				<td class="orderbox-td-tc">
					<a href="javascript:;" onclick="modifyRole('{{$value.refrenceId}}')" >修改</a>
					 {{if $value.delFlag}}
						<a href="javascript:;" onclick="enableRole('{{$value.refrenceId}}')">启用</a>
					 {{else}}
						<a href="javascript:;" onclick="deleteRole('{{$value.refrenceId}}')">删除</a>	
					 {{/if}}
                     
                     <a href="javascript:;" onclick="setMenu('{{$value.refrenceId}}')">设置权限</a>
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
<script id="modify-template" type="text/html">
<form id="modify-form">
<input type="hidden" name="refrenceId" value="{{refrenceId}}"/>
<table>
    <tr>
        <td>角色代码</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="roleCode" value="{{roleCode}}"/></td>
    </tr>
	<tr>
        <td>角色名</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="roleName" value="{{roleName}}"/></td>
    </tr>

	<tr>
        <td>是否可删除</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;" id="canDel" name="canDel">
				<option value="0">不可以</option>
				<option value="1">可以</option>
			</select>
		</td>
    </tr>
</table>
<div class="operate" style="text-align: center;">
        <a class="simple_button confirm_btn modify-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn modify-cancel" style="" href="javascript:;">取消</a>
</div>
</from>
</script>
<script id="add-template" type="text/html">
<form id="add-form">
<table>
    <tr>
        <td>角色代码</td>
        <td><input class="ui-input js-setcom-dprice js-price" type="text" name="roleCode" /></td>
    </tr>
	<tr>
        <td>角色名</td>
        <td><input style="width:300px;" class="ui-input" type="text" name="roleName" /></td>
    </tr>

	<tr>
        <td>是否可删除</td>
        <td>
			<select class="ui-input" style="width:150px;height:30px;"  name="canDel">
				<option value="0">不可以</option>
				<option value="1">可以</option>
			</select>
		</td>
    </tr>
</table>
<div class="operate" style="text-align: center;">
        <a class="simple_button confirm_btn add-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn add-cancel" style="" href="javascript:;">取消</a>
</div>
</from>
</script>
<script type="text/html" id="set-role-template">
<div class="">
		<ul id="menuTree" class="ztree"></ul>
</div>
<div class="operate" style="text-align: center;">
        <a class="simple_button confirm_btn set-menu-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn set-menu-cancel" style="" href="javascript:;">取消</a>
</div>
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

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
 	   window.modifyRole = function(id){
 	       dialogLoading(function(dd){
 	           $.ajax({
 	               url: "${ctx}/common/roleInfo/modify/forward",
 	               data: {refrenceId:id},
 	               method: "post",
 	               dataType: "json",
 	               success: function(data){
 	                   html = template.render("modify-template", data);
 	                   dd.hide();
 	                   modifyDialog.element.html(html);
 	                   modifyDialog.show();
 	                   if(data.canDel){
 	                	   $("#canDel").val(1);
 	                   }else{
 	                	   $("#canDel").val(0);
 	                   }
 	                   
 	               }
 	           });
 	       }, "请求数据中，请稍后...");
 	   }  
 	   
 	   $(document).on("click",".modify-confirm",function(){
            $.ajax({
                url: "${ctx}/common/roleInfo/modify",
                data: $("#modify-form").serialize(),
                method: "post",
                dataType: "json",
                success: function(data){
                    page.goTo(page.current);
                }
            });
            modifyDialog.hide();
        });
        //取消
        $(document).on("click",".modify-cancel",function(){
     	   modifyDialog.hide();
        });
        
        window.deleteRole= function(id){
     	    confirmDialog("是否删除",function(){
     	        $.ajax({
     	            url:"/common/roleInfo/remove",
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
        
        window.enableRole=function(id){
     	   confirmDialog("是否启用除",function(){
    	        $.ajax({
    	            url:"/common/roleInfo/enable",
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
        
        
        window.addRole=function(){
     	   html = template.render("add-template", "");
            addDialog.element.html(html);
            addDialog.show();
              
        }
        
        $(document).on("click",".add-confirm",function(){
            $.ajax({
                url: "${ctx}/common/roleInfo/add",
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
        
        var setRoleDialog = new Dialog({
  	       content: "",
  	       width: 600
  	   });
        var setting = {
    			check: {
    				enable: true
    			},
    			data: {
    				simpleData: {
    					enable: true
    				}
    			}
    		};
        var zNodes;
        $.getJSON("/common/menuInfo/ztree/data",{},function(data){
        	zNodes=data;
        });
        var roleId;
        window.setMenu=function(id){
        	 html = template.render("set-role-template", "");
        	 setRoleDialog.element.html(html);
        	 setRoleDialog.show();
        	 $.fn.zTree.init($("#menuTree"), setting, zNodes);
        	 roleId=id;
        }
    	
        $(document).on("click",".set-menu-confirm",function(){
        	var treeObj=$.fn.zTree.getZTreeObj("menuTree");
            var nodes=treeObj.getCheckedNodes(true);
            var paramter='{refrenceId:"'+roleId+'",';
            var menuList="";
            for(var i=0;i<nodes.length;i++){
                menuList+='\"roleMenuList['+i+'].menuId\":"'+nodes[i].id+'",';
            }
            if(menuList.length>0){
            	menuList=menuList.substring(0,menuList.length-1);
            }
            paramter+=menuList;
            paramter+="}";
            $.ajax({
                url: "${ctx}/common/roleInfo/setMenu",
                data: eval('('+paramter+')'),
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
            setRoleDialog.hide();
        });
        //取消
        $(document).on("click",".set-menu-cancel",function(){
        	setRoleDialog.hide();
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
             url: "${ctx}/common/roleInfo/data",
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