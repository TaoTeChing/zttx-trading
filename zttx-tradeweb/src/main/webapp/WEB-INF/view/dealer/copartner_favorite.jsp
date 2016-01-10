﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理中心-加盟管理-收藏夹</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
<link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet" />
</head>
<body>
	<!--完成-->
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix pr">
				<!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu">
					<jsp:param name="openId" value="4"/>
				</jsp:include>
				<!--主体内容-->
				<div class="main-right">
					<!--提示框-->

					<!--面包屑-->
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> &gt;&gt; <span
								class="bb">品牌收藏夹</span> <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="inner">
						<div class="panel-tabs panel-favorite">
							<jsp:include page="/WEB-INF/view/dealer/copartner_header.jsp">
                        		<jsp:param value="6" name="m"/>
                        	</jsp:include>

							<div class="panel-content">
								<div class="tab-item">
									<div class="panel-table table-favorite clearfix">
										<div class="panel-table-content">
											<div class="mt10" id="pagination"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
	</div>
	<div class="hide">
        <div id="add_brand">
            <div class="ui-head">
                <h3>申请加盟</h3>
            </div>
            <form:form class="ui-form mt20" id="joinForm" data-widget="validator">
            <input type="hidden" id="brandsId"/>
                <div class="ui-form-item">
                    <label class="ui-label">联 系 人：</label>
                    <span class="ui-form-text">${dealerInfo.dealerUser}</span>
                </div>

                <div class="ui-form-item">
                    <label class="ui-label">联系方式：</label>
                    <input type="text" class="ui-input" id="telphone" value="${dealerUserm.userMobile}" required data-display="手机号码"/>
                </div>

                <div class="ui-form-item">
					<label class="ui-label">申请信息：</label>
					<textarea class="ui-textarea" id="applyText" style="height:60px;width:250px;" required data-display="申请信息"></textarea>
				</div>

                <div class="ui-form-item">
                        <input type="submit" class="ui-button ui-button-mred" value="申请提交"/>
                </div>
            </form:form>
        </div>
    </div>
	
	<script id="feedback-templage" type="text/html">
	<table id="tempTable" class="ui-table ui-table-inbox">
		<thead>
            <tr>
                <th class="tb5"></th>
                <th class="tb25">品牌LOGO</th>
                <th class="tb20">品牌商</th>
                <th class="tb15">公司规模</th>
                <th class="tb15">年营业额</th>
                <th class="tb25">操作</th>
            </tr>

		</thead>
		<tbody>
		{{each rows}}
			<tr>
				<td><input type="checkbox" class="ui-checkbox" value="{{$value.refrenceId}}"/></td>
				<td>
					<a target="_blank" href='http://{{$value.domain}}${zttx}'>
                        <div class="l-100x50" style="text-align: center"><img src="${res}{{$getImageDomainPathFn $value.logoDomin+$value.brandsLogo 100 50}}" /></div><h3>{{$value.brandsName}}</h3>
					</a>				
				</td>
				<td>
                    <p><span>{{$value.brandName}}</span></p>
				</td>
				<td> {{$value.emploeeNumName}} </td>
				<td> {{$value.moneyNumName}} </td>
				<td data_id="{{$value.brandsId}}">
					{{if $value.applyState==0}}
						<span class="c-r mr10">正在审核您的申请</span>
						<a href="javascript:updateApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mt10">撤销申请</a>
						<a href="#" class="ui-button ui-button-swhite mt10" data_id="{{$value.refrenceId}}"><i class="icon i-delete"></i>移除</a>
					{{/if}}
					{{if $value.applyState==1}}
						<a href="#" class="ui-button ui-button-swhite mb10" data_id="{{$value.refrenceId}}"><i class="icon i-delete"></i>移除</a><br />
						<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="link">查看招募书</a><%-- <a href="#" class="ml10 link">查看品牌证书</a>--%>
					{{/if}}
					{{if $value.applyState==3}}
						<a href="javascript:updateInvitedApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb10"><i class="iconfont">&#xe620;</i> 接受邀请</a>
						<a href="#" class="ui-button ui-button-swhite mb10" data_id="{{$value.refrenceId}}"><i class="icon i-delete"></i>移除</a>
						<br />
						<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><%--<a target="_blank" href="${ctx}/market/brandes/certs/{{$value.brandsId}}/{{$value.brandId}}" class="ml20 c-b">查看品牌证书</a>--%>
					{{/if}}
					{{if $value.applyState==2 || $value.applyState==4 || $value.applyState==5 || $value.applyState==6 || $value.applyState==null}}
						
		{{if $value.brandRecruit==true}}
		<a href="javascript:void(0);" class="ui-button ui-button-sorange mb10"><i class="iconfont i-pencil">&#xe618;</i> 申请加盟</a>
             {{/if}}
                    <a href="#" class="ui-button ui-button-swhite mb10" data_id="{{$value.refrenceId}}"><i class="icon i-delete"></i>移除</a>
						<br />
                    {{if $value.brandRecruit==false}} 招募书未开启 {{/if}} <a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><%--<a target="_blank" href="${ctx}/market/brandes/certs/{{$value.brandsId}}/{{$value.brandId}}" class="ml20 c-b">查看品牌证书</a>--%>
					{{/if}}
				</td>
			</tr>
		{{/each}}   
			{{ if rows.length == 0 }}
				<tr>
					<td colspan="6">暂无数据</td>
				</tr>
			{{ /if }}
		</tbody>
	</table>
	</script>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script type="text/javascript" src="${src}/plugin/template-simple.js"></script>
	<script>
		var dealer_favorites = {};
		dealer_favorites.checkAll = function() {
			$("div.panel-table-content table td .ui-checkbox").each(function() {
				$(this).prop("checked", $('#allcheck').prop("checked"));
			});
		};
		
		dealer_favorites.removeCollect = function() {
			var refrenceId=$.trim($(this).attr("data_id"));
			confirmDialog("确定是否要删除？",function(){
				$.post("${ctx}/dealer/dealerCollect/collect/delete", {
					refrenceIds : refrenceId
				}, function(data) {
				console.log(data);
					if(data.code==126000){
	    				remind("success","删除成功!");
// 	    				page.render();
						g_pagination.goTo(1);
	    				//window.location.href="${ctx}/dealer/copartner/favorite";
		    		}else{
		    			remind("error","删除失败!"); 
		    		}
				},"json");
			});
		};
		dealer_favorites.deleteAll = function() {
			confirmDialog("确定是否要删除？",function(){
				var delArray = [];
				$("div.panel-table-content table td .ui-checkbox").each(function() {
					if ($(this).prop("checked")) {
						delArray.push($.trim($(this).val()));
					}
				});
				if(delArray.length==0){
					remind("error","请选择一个品牌!"); 
					return;
				}
				$.post("${ctx}/dealer/dealerCollect/collect/delete", {
					refrenceIds : delArray.join(",")
				}, function(data) {
					if(data.code==126000){
	    				remind("success","删除成功!");
	    				g_pagination.goTo(1);
		    		}else{
		    			remind("error","删除失败!"); 
		    		}
				},"json");
			});
		};
		
		
		
		function doCopartnerSearch() {
	    	g_pagination.goTo(1);
	    }
        function joinApple(obj){
        	var brandsId = $.trim($("#brandsId").val());
        	var applyText = $.trim($("#applyText").val());
        	var telphone = $.trim($("#telphone").val()); 
        	$.post("${ctx}/dealer/joinManage/apply/join",{brandsId:brandsId,inviteText:applyText,telphone:telphone},function(data){
	    		if(data.code==126000){
	    			remind("success","成功提交申请!");
	    			obj.hide();
	    			$("#applyText").val('');
	    			g_pagination.goTo(1);
	    		}else{
	    			remind("error",data.message);
	    		}
	    	},"json");
        };
		function updateApple(brandsId){
	      	confirmDialog("确定是否要撤销？",function(){
	       	$.post("${ctx}/dealer/joinManage/apply/update",{brandsId:brandsId},function(data){
	    		if(data.code==126000){
	    			remind("success","成功撤销申请!");
	    			g_pagination.goTo(1);
	    		}else{
	    			remind("error",data.message+"，不能撤销申请"); 
	    		}
	    		},"json");
	   		});
	    };
	    function updateInvitedApple(brandsId){
	       	$.post("${ctx}/dealer/invited/accept",{brandsId:brandsId},function(data){
	    		if(data.code==126000){
	    			remind("success","成功接受邀请!");
	    			g_pagination.goTo(1);
	    		}else{
	    			remind("error",data.message+"，不能授受邀请"); 
	    		}
	    	},"json");
    	}
	</script>
	
	<script>
	  $(function () {
	    seajs.use(["pagination"], function (Pagination) {
	    	window['g_pagination'] = new Pagination({
	              url: "${ctx}/dealer/dealerJoinManage/brandsCollect/data",
	              elem: "#pagination",
	              form:$("#copartner_form"),
	              method:"post",
	              handleData: function (json) {
	            	  
	            	  template.helper('$getImageDomainPathFn',function (url, width, height){
	                      return getImageDomainPath(url, width, height);
	                  });
	            	  
	                  var html = template.render("feedback-templage", json);
	                  $("#tempTable").remove();
	                  $("#pagination").before($(html));   
	                  $('td a.ui-button:has(.i-delete)').click( dealer_favorites.removeCollect);

                      /*//让图片在一个容器里面垂直居中
                      $(".js_img_center,.js-img-center").each(function () {
                          $("<span></span>").appendTo($(this));
                      });*/


	                  seajs.use(["$", 'dialog'], function ($, Dialog) {
				            //表格样式
				            $(".panel-table-content tbody tr:odd").css("background-color", "#f9f9f9");
				
				            var trs = $(".panel-table-content tr");
				            $(trs).find("td:first,th:first").css("border-left", 0);
				            $(trs).find("td:last,th:last").css("border-right", 0);			
				
				            var d1 = new Dialog({
				                trigger: 'td a.ui-button:has(.i-pencil)',
				                content: "#add_brand", 
				                width: 400
				            });
				            d1.after('show', function () {
				                var btn = this.activeTrigger;
					            $("#brandsId").val($.trim(btn.parent("td").attr("data_id")));
                                baseFormValidator({
                                    selector:"#joinForm",
                                    isAjax:true,
                                    addElemFn:function(Core){
                                        Core.addItem({
                                            element: '#telphone',
                                            required: true,
                                            rule: 'mobile'
                                        });
                                    },
                                    beforeSubmitFn:function(){
                                        //console.log("验证通过");
                                        joinApple(d1);
                                    }
                                });

				            });
				        });
	              }
	          });
        });
        
		$('#allcheck').click(dealer_favorites.checkAll);
		$('#btn_remove').click(dealer_favorites.deleteAll);
      });
	</script>

</body>
</html>
