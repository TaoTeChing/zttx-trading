<%@ page language="java" contentType="text/html;charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-加盟管理-浏览过我的品牌商</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
     <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
 				<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> > <span class="bb">浏览过我的品牌商</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <div class="panel-tabs">
                        	<jsp:include page="/WEB-INF/view/dealer/copartner_header.jsp">
                        		<jsp:param value="1" name="m"/>
                        	</jsp:include>
                        	
                            <div class="panel-content">
                                <div class="tab-item">
                                    <div class="panel-table table-favorite clearfix">
                                        <div class="panel-table-content">
		                                     <table class="ui-table ui-table-inbox">
												<thead>
												<tr>
													<th class="tb35">品牌商</th>
													<th class="tb15">公司规模</th>
													<th class="tb15">年营业额</th>
													<th class="tb25">操作</th>
												</tr>
												</thead>
												<tbody id="copartner_tbl_body">
												</tbody>
											</table>
                                        	<div id="pagination" class="mt10"></div>
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
		<div id="look_brand">
            <div class="ui-head">
                <h3>申请加盟品牌</h3>
            </div>
            <div style="height:400px;overflow:auto;">
                <table id="applyJoin" class="ui-table ui-table-noborder">
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div id="add_brand" style="height:300px;width:400px;">
            <div class="ui-head">
                <h3>申请加盟</h3>
            </div>
            <form:form class="ui-form mt20" id="joinForm">
	            <input type="hidden" id="brandId"/>
	            <input type="hidden" id="brandsId"/>
                <div class="ui-form-item">
                    <label class="ui-label">联 系 人：</label><span class="ui-form-text"> ${dealerInfo.dealerUser} </span>
                </div>

                <div class="ui-form-item">
                    <label class="ui-label">联系方式：</label><input type="text" class="ui-input" id="telphone"  value="${dealerUserm.userMobile}"/>
                </div>

                <div class="ui-form-item">
                    <label class="ui-label">留　　言：</label><textarea class="ui-textarea" style="height:60px;width:250px;" id="applyText"></textarea>
                </div>

                <div class="ui-form-item">
                    <a href="javascript:void(0);" id="submit" class="ui-button ui-button-mred">申请提交</a>
                </div>
            </form:form>
        </div>
    </div>
    <script id="copartner-template" type="text/html">
		{{each rows}}
		<tr>
			<td><span>{{$value.brandName}}</span></td>
			<td> {{$value.emploeeNumName}} </td>
			<td> {{$value.moneyNumName}} </td>
			<td><a class="ui-button ui-button-morange" data_id="{{$value.brandId}}">加盟旗下品牌</a></td>
		</tr>
		{{/each}}
{{ if rows.length == 0 }}
	<tr>
		<td colspan="4">暂无数据</td>
	</tr>
{{ /if }}
</script>
<script id="feedback-templage-brands" type="text/html">
{{each rows}}
	<tr>
		<td width="20%"><a target="_blank" href='http://{{$value.domain}}${zttx}'><img src="${res}{{$getImageDomainPathFn $value.logoDomin+$value.brandLogo 100 50}}"  class="l-100x50" style="width:100px;height:50px;"/></a></td>
		<td width="50%"><a target="_blank" href='http://{{$value.domain}}${zttx}'><h3>{{$value.brandName}}</h3></a></td>
		<td width="30%" class="ta-c" data_id="{{$value.refrenceId}}">
		{{if $value.applyState==0}}
			<a href="javascript:updateApple('{{$value.refrenceId}}');" class="ui-button ui-button-sorange mb10">撤销申请</a><br /><span class="c-r">正在审核您的申请</span>
		{{/if}}
		{{if $value.applyState==1}}
			<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="link">查看招募书</a><%-- <a href="#" class="ml10 link">查看品牌证书</a>--%>
		{{/if}}
		{{if $value.applyState==3}}
			<a href="javascript:updateInvitedApple('{{$value.refrenceId}}');" class="ui-button ui-button-sorange mb10"><i class="iconfont">&#xe620;</i> 接受邀请</a>
			<br />
			<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml20 c-b">查看品牌证书</a>--%>
		{{/if}}
		{{if $value.applyState==2 || $value.applyState==4 || $value.applyState==5 || $value.applyState==6 || $value.applyState==null}}
			{{if $value.brandRecruit==true}}
			<a href="javascript:void(0);" class="ui-button ui-button-sorange mb10 apply"><i class="iconfont">&#xe618;</i>申请加盟</a>
			{{/if}}
			{{if $value.brandRecruit==false}}
			招募书未开启
			{{/if}}
			<br />
			<a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml20 c-b">查看品牌证书</a>--%>
		{{/if}}
		</td>
	</tr>
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="3">暂无数据</td>
    </tr>
    {{ /if }}
{{/each}}
</script>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${src}/plugin/template-simple.js"></script>
    <script src="${src}/brand/prolinemanage.js"></script>
    <script>
        function joinBrands(brandId){
        	$.ajax({
  				type : "POST",
  				url : "${ctx}/dealer/copartner/bevisited/applylist",
  				data : {brandId:brandId},
  				dataType: "json",
  				success : function(json) {
  					if(126000==json.code){
                        template.helper('$getImageDomainPathFn',function (url, width, height){
                            return getImageDomainPath(url, width, height);
                        });
  						var html = template.render("feedback-templage-brands", json);
  						$("#applyJoin tbody").html(html);
                        initTable();//表格样式处理
  						seajs.use(["$", 'dialog'], function ($, Dialog) {
				            var d1 = new Dialog({
				                trigger: '#look_brand a.ui-button.apply',
				                content: "#add_brand",
                                width:400
				            });
				            d1.after('show', function () {
				                var btn = this.activeTrigger;
				                $("#brandId").val(brandId);
				                $("#brandsId").val($.trim(btn.parent("td").attr("data_id")));
				                 $(this.element).find('#submit').unbind('click').click(function () {
				                   joinApple(d1); 
				                }); 
				            });
				        });
  						
  					}else
  						remind("error",json.message);
  				},
  				error:function(XMLHttpRequest, textStatus, errorThrown){
  					remind("error",errorThrown);
  				}
  			}); 
        };
        function joinApple(obj){
        	var brandId = $.trim($("#brandId").val());
        	var brandsId = $.trim($("#brandsId").val());
        	var applyText = $.trim($("#applyText").val());
        	var telphone = $.trim($("#telphone").val());
        	
        	$.post("${ctx}/dealer/joinManage/apply/join",{brandsId:brandsId,inviteText:applyText,telphone:telphone},function(data){
	    		if(data.code==126000){
	    			remind("success","成功提交申请!");
	    			obj.hide();
	    			$("#applyText").val('');
	    			joinBrands(brandId);
	    		}else{
	    			remind("error",data.message); 
	    		}
	    	},"json");
        };
        function updateApple(brandsId){
        	confirmDialog("确定是否要撤销？",function(){
	        	var brandId = $.trim($("#brandId").val());
	        	$.post("${ctx}/dealer/joinManage/apply/update",{brandsId:brandsId},function(data){
		    		if(data.code==126000){
		    			remind("success","成功撤销申请!");
		    			joinBrands(brandId==''?data.object:brandId);
		    		}else{
		    			remind("error",data.message+"，不能撤销申请"); 
		    		}
		    	},"json");
	    	});
        };
        function updateInvitedApple(brandsId){
        	confirmDialog("确定是否接受邀请？", function() {
        	var brandId = $.trim($("#brandId").val());
	       	$.post("${ctx}/dealer/invited/accept",{brandsId:brandsId},function(data){
	    		if(data.code==126000){
	    			remind("success","成功接受邀请!");
	    			joinBrands(brandId);
	    		}else{
	    			remind("error",data.message+"，不能授受邀请"); 
	    		}
	    	},"json");
        	});
    	}
    </script>

	<script>
		  
		   function doCopartnerSearch() {
		    	g_pagination.goTo(1);
		   }
		   
		   seajs.use(["pagination","template","dialog"], function (Pagination,template,Dialog) {
	        	window['g_pagination'] = new Pagination({
	              url: "${ctx}/dealer/dealerJoinManage/beBrandVisited/data",
	              elem: "#pagination",
	              form:$("#copartner_form"),
	             // method:"post",
	              handleData: function (data) {
	            	  /*template.helper('$getImageDomainPathFn',function (url, width, height){
	  			        return getImageDomainPath(url, width, height);
	  			      });*/
	                  var html = template.render("copartner-template", data);
	                  $("#copartner_tbl_body").empty().append(html);
				    	var dlg = new Dialog({
			                trigger: '.table-favorite a.ui-button',
			                content: "#look_brand",
	                        width:650
			            }).after("show",function(){
			            	var btn = this.activeTrigger;
			            	var brandId=$.trim(btn.attr("data_id"));
			            	if(brandId!=null)
			            		joinBrands(brandId);
			           });
	              }
	          });
        });
	</script>

</body>
</html>
