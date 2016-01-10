<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-我的账户-账户信息-证书管理</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/myAccount.css" />
<link rel="stylesheet" href="${res}/styles/brand/accout_certificate.css" />
<style>
    .main_con .inner{
        padding-bottom: 15px !important;
    }
</style>
</head>
<body>
	<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/contact/list">我的账户</a>
                    <span class="arrow">&gt;</span>
                    <a class="link" href="${ctx}/brand/contact/list">账户信息</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">证书管理</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<div class="certificate_manage">
					<%--<h3 class="mt10 title">添加企业证书：</h3>--%>
                    <div class="ui_tab">
                        <ul class="ui_tab_items clearfix">
                            <li class="ui_tab_item current">
                                <a href="javascript:;">添加企业证书</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tips">
                        <i class="v2-icon-explain"></i>
                        说明：提交更多有效的证书，可以为您的品牌获得更多展示的机会
                    </div>
					<form:form id="saveCardForm" action="${ctx}/brand/card/save" class="addCertificate_form ui-form vertical-tip">
						<input name="refrenceId" type="hidden" autocomplete="off" />
						<input name="vailResult" type="hidden" autocomplete="off" />
						<div class="ui-form-item">
							<label class="ui-label" for=""> 证书名：<span class="ui-form-required">*</span></label>
							<input name="certName" style="width: 214px;" type="text" class="ui-input">
						</div>
						<div class="ui-form-item">
							<label class="ui-label" for=""> 图片上传：<span class="ui-form-required">*</span> </label>
							<div class="inline-block">
								<div class="file_wrap brand_logo">
									<input name="certPhoto" type="hidden" />
									<input name="certImage" type="hidden" />
									<input name="certImagePath" type="hidden" />
									<p class="replace_text">点击上传</p>
									<input id="certImg" class="input_file" name="photo" type="file" />
								</div>
								<p class="ui-form-explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
							</div>
						</div>
						<div class="ui-form-item">
							<label class="ui-label" for=""> 内容描述：<span class="ui-form-required">*</span> </label>
							<textarea name="certMark" class="ui-textarea"></textarea>
						</div>
						<div class="ui-form-item">
							<button id="saveButton" class="ui_button ui_button_lblue mr10">提交保存</button>
							<button id="resetButton" class="ui_button ui_button_lgray">重置</button>
						</div>
					</form:form>
					<%--<h3 class="mt30 title">管理企业证书：</h3>--%>
                    <div class="ui_tab">
                        <ul class="ui_tab_items clearfix">
                            <li class="ui_tab_item current">
                                <a href="javascript:;">管理企业证书</a>
                            </li>
                        </ul>
                    </div>
					<div class="certificate_list">
						<table class="list_table">
							<colgroup>
								<col width="200" />
								<col width="288" />
								<col width="350" />
								<col width="130" />
							</colgroup>
                            <thead>
								<tr>
									<th>图片</th>
									<th>证书名</th>
									<th>描述</th>
									<th class="last">操作</th>
								</tr>
                            </thead>
                            <tbody></tbody>
						</table>
						<div class="pageParent mt15"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="ajax-templage-card" class="ajax-templage" _url="${ctx}/brand/card/list" _page=".certificate_list .pageParent" _pid=".certificate_list tbody" type="text/html">
{{each rows}}
<tr>
	<input type="hidden" name="refrenceId" value="{{$value.refrenceId}}" />
	<input type="hidden" name="certName" value="{{$value.certName}}" />
	<input type="hidden" name="certImage" value="{{$value.domainName}}{{$value.certImage}}" />
	<input type="hidden" name="certPhoto" value="{{$value.certPhoto}}" />
	<input type="hidden" name="certMark" value="{{$value.certMark}}" />
	<td class="ta-c">
		<img alt="{{$value.certPhoto}}" style="width:100px; height:50px;" class="thumbImage" src="${res}{{$getImageDomainPathFn $value.certImage 100 50}}" />
	</td>
	<td class="ta-c">{{$value.certName}}</td>
	<td>{{$value.certMark}}</td>
	<td class="last">
		<p class="operate"><a id="js-updateCard" class="link" href="javascript:void(0);"><i class="iconfont edit_icon">&#xe618;</i>修改</a></p>
		<p class="operate mt5"><a id="js-removeCard" class="link" href="javascript:void(0);"><i class="iconfont del_icon">&#xe619;</i>删除</a></p>
	</td>
</tr>
{{/each}}
</script>
<script type="text/javascript">
	var pageTemp, validator;
	seajs.use(["pagination", "template", "validator"], function (Pagination, template, Validator){
		var $this = $("#ajax-templage-card");
		var tempId = $this.attr("id");
		
		template.helper('$getImageDomainPathFn',function (url, width, height){
            return getImageDomainPath(url, width, height);
        });
		
		pageTemp = new Pagination({
			url: $this.attr("_url"),
			elem: $this.attr("_page"),
			method: "post",
			handleData: function (data) {
				var html = template.render(tempId, data);
				$($this.attr("_pid")).html(html);
			}
		});

		validator = new Validator({
		    element: '#saveCardForm',
		    autoSubmit: false
		}).addItem({
		    element: '#saveCardForm [name=certName]',
		    rule:'maxlength{max:64}',
		    required: true,
		    display: '证书名'
		}).addItem({
		    element: '#saveCardForm [name=certMark]',
		    rule:'maxlength{max:2048}',
		    required: true,
		    display: '内容描述'
		}).addItem({
		    element: '#saveCardForm [name=vailResult]',
		    required: true,
		    onItemValidate:function(elem)
		    {
		    	if($("#saveCardForm .file_wrap img").length == 0)
		    	{
			    	$('#saveCardForm [name=vailResult]').val("");
		    		remind("error","请选择图片！");
		    	}
		    	else
		    	{
			    	$('#saveCardForm [name=vailResult]').val("true");
		    	}
		    }
		});
		
		brandCard.init();
	});
	
	var brandCard = {
		init: function(){
			$(document).on('click','#js-updateCard',function(){
				brandCard.modify($(this).parents("tr"));
			});
			
			$(document).on('click','#js-removeCard',function(){
				brandCard.remove($(this).parents("tr"));
			});
			
			$(document).on('click','#saveButton',function(){
				validator.execute(function(hasError, results, element){
					if(!hasError){
						brandCard.save();
					}
				});
			});
			
			$(document).on('click','#resetButton',function(){
				brandCard.reset();
			});
			
			$('#certImg').bind('change',function(){
				if(verifyFileSuffix(this)){
					brandCard.uploadImage("certImg");
				}
			});
		},
		modify: function($tr){
            resetValidatorError("#saveCardForm");
			$(".certificate_manage h3.mt10").text("修改企业证书：");
            $("#saveCardForm :input[name='refrenceId']").val($tr.find(":input[name='refrenceId']").val());
			$("#saveCardForm :input[name='certName']").val($tr.find(":input[name='certName']").val());
			$("#saveCardForm :input[name='certMark']").val($tr.find(":input[name='certMark']").val());
			brandCard.showImage($tr.find(":input[name='certImage']").val(), $tr.find(":input[name='certPhoto']").val(), false);
		},
		remove: function($tr){
			var id = $tr.find(":input[name='refrenceId']").val();
			$.post("/brand/card/delete",{uuid: id},function(data){
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "删除成功");
					brandCard.refresh();
				}
				else
				{
					remind("error", data.message);
				}
			},"json");
		},
		refresh: function(){
			var page = pageTemp;
			page.goTo(page.current);
		},
		reset: function(){
			$(".certificate_manage h3.mt10").text("添加企业证书：");
			$("#saveCardForm .file_wrap .replace_text").html("点击上传");
			$("#saveCardForm :input[name='refrenceId']").val("");
			$("#saveCardForm :input[name='certPhoto']").val("");
			$("#saveCardForm :input[name='certImagePath']").val("");
			$("#saveCardForm :input[name='certImage']").val("");
			$("#saveCardForm")[0].reset();
		},
		save: function(){
			$.post("${ctx}/brand/card/save", $('#saveCardForm').serialize(), function(data) {
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "保存成功");
					brandCard.reset();
					brandCard.refresh();
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		},
		showImage: function (url, oldName, isTemp){
			$replaceText = $("#saveCardForm .file_wrap .replace_text").html("");
			$('<img style="width:100px; height:50px;" />').appendTo($replaceText).attr("src", "${res}"+url).attr("alt", oldName);
			$("#saveCardForm :input[name='certPhoto']").val(oldName);
			if(isTemp){
				$("#saveCardForm :input[name='certImagePath']").val(url)
				$("#saveCardForm :input[name='certImage']").val("");
			}else{
				$("#saveCardForm :input[name='certImagePath']").val("")
				$("#saveCardForm :input[name='certImage']").val(url);
			}
		},
		uploadImage: function(uploadId){
			seajs.use(["$","ajaxFileUpload"],function($){
				$.ajaxFileUpload({
	    			url: '${ctx}/common/showImg?fSize=2',
	    			secureuri: false,
	                fileElementId: uploadId,
	                dataType: 'json',
	                success: function(data)
	                {
	                	$('#certImg').bind('change',function(){
	        				brandCard.uploadImage("certImg");
	        			});
	                	if(data.code == zttx.SUCCESS)
	                	{
	                		brandCard.showImage(data.message, data.object, true);
	                	}
	                	else
	                	{
	                		remind("error", data.message);
	                	}
	                }
	    		});
		    });
		}
	}
</script>
</body>
</html>
