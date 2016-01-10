<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-下载资料管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
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
                <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">下载资料管理</span>
            </div>
            <div class="fr">
                <c:choose>
                    <c:when test="${empty refrenceId}">
                        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
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
            <!-- 内容都放这个地方  -->
            <div class="ui_tab main_ui_tab">
                <ul class="js_tab_box ui_tab_items clearfix">
                    <li class="ui_tab_item current">
                        <a href="${ctx}/brand/brandDocument">资料上传</a>
                    </li>
                    <li class="ui_tab_item ">
                        <a href="${ctx}/brand/brandDocument/list">资料管理</a>
                    </li>
                    <li class="ui_tab_item">
                        <a href="${ctx}/brand/brandDocument/sort">分类管理</a>
                    </li>
                </ul>
            </div>
            <div class="tab_con_box">
                <div class="tab_con" >
                    <div class="tips">
                        <i class="v2-icon-explain"></i>
                        说明：针对不同的品牌，不同的终端商，上传品牌的宣传、培训资料
                    </div>
                <form:form  id="dataUpload-form"  data-widget="validator" class="ui-form addBrand-form dataUpload-form"  action="${ctx}/brand/document/execute" method="post">
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            品牌选择：<span class="ui-form-required">*</span>
                        </label>
                        <div class="inline-block">
                            <input type="hidden" name="refrenceId" id="refrenceId" value="${oldBrandDocument.refrenceId}"/>
                            <select class="ui-select js_select" name="brandsId" id="brandsId" required data-errormessage-required="请选择品牌">
                                <option value="">请选择品牌</option>
                                <c:forEach items="${brandesList}" var="brandes">
                                    <option   value="${brandes.refrenceId}"  <c:if test="${oldBrandDocument.brandsId==brandes.refrenceId}">selected="selected"</c:if> >${brandes.brandsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            分类选择：
                        </label>
                        <div class="inline-block">
                            <select  class="ui-select js_select" name="cateId" id="cateId">
                                <option value="">未分类</option>
                                <c:forEach items="${doccateList}" var="doccate">
                                    <option value="${doccate.refrenceId}" <c:if test="${oldBrandDocument.cateId==doccate.refrenceId}">selected="selected"</c:if> >${doccate.cateName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            资料名称：<span class="ui-form-required">*</span>
                        </label>
                        <input style="width: 510px;" type="text" class="ui-input" name="docName" id="docName" required data-display="资料名称" value="${oldBrandDocument.docName}" maxlength="64"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            网盘地址：
                        </label>
                        <input autocomplete="off" style="width: 510px;" type="text" class="ui-input" name="webAddress" id="webAddress" value="${oldBrandDocument.webAddress}" maxlength="128">
                        <input type="hidden" name="webAddressErr" id="webAddressErr" required value="1"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            资料上传：<span class="ui-form-required">*</span>
                        </label>
                        <div class="inline-block upload">
                            <div class="file_wrap">
                                <p class="replace_text" >选择文件上传</p>
                                <input class="input_file" type="file" name="zip" id="zip" onchange="down_manage.uploadFile();"/>
                                <input type="hidden" name="docoFile" id="docoFile" value="${oldBrandDocument.docoFile}"/>
                                <input type ="hidden" data-errormessage="请上传文件" name ="docnFile" id="docnFile" required value="${oldBrandDocument.docnFile}"/>
                            </div>
                            <span class="ui-form-explain">（请使用RAR格式打包上传，支持ZIP、RAR、7Z压缩格式，且文件小于50M）</span>
                        </div>
                        <div class="upload_contain">
                            <ul>
                                <li class="item" >
                                    <span id="filename" >${oldBrandDocument.docoFile}</span>
                                    <a style="display: none;" id="delFile" class="fr close mr5" href="javascript:;">×</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <input type="text" style="display: none;" />
                    <div class="ui-form-item" style="display:none;">
                        <label class="ui-label" for="">
                            资料密码：
                        </label>
                        <input style="width: 268px;" type="password" class="ui-input" name="docPass" id="docPass" value="${oldBrandDocument.docPass}" maxlength="32">
                        <p class="ui-form-explain">（通过加密，保证资料安全，只有指定终端商才可查看）</p>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            内容描述：<span class="ui-form-required">*</span>
                        </label>
                        <textarea class="ui-textarea" name="docMark" id="docMark" required data-display="内容描述">${oldBrandDocument.docMark}</textarea>
                    </div>
                    <jsp:include page="/WEB-INF/view/brand/list_dealer_level.jsp">
                        <jsp:param value="brandsId" name="brandsName"/>
                        <jsp:param value="2" name="addLevelType"/>
                        <jsp:param value="${oldBrandDocument.refrenceId}" name="docId"/>
                        <jsp:param value="${oldBrandDocument.docOpen}" name="docOpen"/>
                    </jsp:include>
                    <div class="ui-form-item">
                        <button type="submit" class="ui_button ui_button_morange" id="documentSave">保存提交</button>
                    </div>
                </form:form>
            </div>
        </div>

    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/brand_manage.js"></script>
<script>
	var down_manage = {
        init: function(){
           // this.handleSkyDrive(); //处理网盘
            this.handleForm();  //处理表单
            this.selBrand();
            this.delFile();
        },
        handleSkyDrive: function(){
            if($("#skyDrive_btn").length == 0){
                return;
            }
            $("#skyDrive_btn").on("click",function(){
                var value = $("#skyDrive_input").val();
                var str = '<li class="item"><a href="javascript:;"><span>'+value+'</span><i class="iconfont close">&#xe612;</i></a></li>';
                $(str).appendTo($(".out_result ul"));
                $("#skyDrive_input").val("");
            });
            $(".out_result").on("click",".close",function(){
                var confirm = window.confirm("是否要删除");
                if(!confirm){
                    return;
                }
                var op = $(this).parents(".item");
                op.remove();
            });
        },
        handleForm: function(){
            if($("#dataUpload-form").length == 0){
                return;
            }
            seajs.use(['validator', 'widget'], function(Validator, Widget){
                Widget.autoRenderAll();
                var Core = Validator.query("#dataUpload-form");
                Core.set("autoSubmit",false);
                Core.on('formValidated', function(error, message, elem) {
                if(error){
                  		return;
                    } 
                    $("#documentSave").attr("disabled", true);
                    $.ajax({
				 		  url: '/brand/brandDocument/save',
				          type: 'post',
				          data: $("#dataUpload-form").serialize(),
				          dataType: 'json',
				          success: function(data, textStatus)
				          {
                                $("#documentSave").attr("disabled", false);
					          	if(zttx.SUCCESS==data.code){
									remind("success","资料上传成功",function(){
										window.location.href="/brand/brandDocument/list";
									});
								}else if(zttx.VALIDERR==data.code)
								{
									setErrMsg("#dataUpload-form",data.rows);
								}
								else{
									remind("error",data.message);
								}
				          },
				  		  error: function(data)
				          {
                                $("#documentSave").attr("disabled", false);
				  				remind("error",data.message);
				          }
			    	});

                });
            });
        },
        selBrand:function(){
        	$("#brandsId").on("change",function(){
        		var brandsId = $.trim($(this).val());
	    		$.ajax({
					type : "POST",
					url : "${ctx}/brand/brandDocument/doccateList",
					data : {brandsId:brandsId},
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS==json.code){
							$("#cateId").children().remove();
							$("#cateId_div span").html("未分类");
						    $("#cateId").append("<option value='' selected='selected'>请选择分类</option>");
							for(var i=0; i<json.rows.length;i++){
								$("#cateId").append("<option value='"+json.rows[i].refrenceId+"'>"+json.rows[i].cateName+"</option>");
							}
						}else
							remind("error",json.message); 
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){ 
						remind("error",errorThrown); 
					}
				}); 
				dealerLevel.init();
        	});
    	},
     	uploadFile:function(){
   	    	seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showZip?fSize=8',
        			secureuri: false,
                    fileElementId: 'zip' ,
                    dataType: 'json',
                    success: function(data)
                    {

                    	if(data.code  != zttx.SUCCESS)
                    	{
                    		remind("error",data.message);
                    	}
                    	else
                    	{
                    		$("#filename").text(data.object);
                    		$("#docoFile").val(data.object);
                    		$("#docnFile").val(data.message);
                            $("#delFile").show();
                    	}
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){
                        remind("error","您提交的文件超过最大限制");
                    }
        		});
    	    });
    	},
        delFile: function(){
            $("#delFile").on("click",function(){
                var _this = this;
                confirmDialog("是否要删除？",function(){
                    $("#filename").text("");
                    $("#docoFile").val("");
                    $("#docnFile").val("");
                    $(_this).hide();
                });
            });
        }
    };
    down_manage.init();
</script>
<c:if test="${!empty errCode}">
<script>
$(function(){
	var code = "${errCode}";
	if(zttx.VALIDERR==code){
		var errList = eval('${message}');
		setErrMsg("#dataUpload-form", errList);
	}else{
		remind("error",'${message}');
	};
});
</script>
</c:if>
</body>
</html>