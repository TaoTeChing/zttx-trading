<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en" xmlns=http://www.w3.org/1999/xhtml>
<head>
<meta charset="UTF-8">
<c:set var="title" value="新增品牌"/>
<c:if test="${!empty info.refrenceId}">
<c:set var="title" value="编辑品牌"/>
</c:if>
    <title>管理中心-品牌管理-${title}</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style>
        .addBrand-form .brand_logo{
            width: 224px;
        }
        .addBrand-form .brand_logo .input_file{
            width: 224px;
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
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">${title}</span>
        </div>
        <div class="fr">
            <c:choose>
                <c:when test="${empty info.refrenceId}">
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

        <!-- 步骤条 -->
        <div class="v2-steps">
            <span class="text1 current">填写资料</span>
            <span class="text2">审核认证</span>
            <span class="text3">签订合同</span>
            <span class="text4">开通运维</span>
            <div class="steps v2-steps-1"></div>
        </div>
        <div class="v2-common-explain" style="margin: 0 20px;">
            <i class="v2-icon-explain"></i>说明：新添加的品牌提交后，工作人员会在3个工作日内进行审核，请耐心等待
        </div>
        <form:form id="addBrand-form" class="ui-form addBrand-form mt15" style="margin-left: 55px;" action="${ctx}/brand/brandesInfo/brandes/submit" method="post" data-widget="validator">
            <input type="hidden" value="${brandsId}" name="brandsId" id="brandsId">
        	<input type="hidden" id="checkResult" />
        	<input type="hidden" name="t" value="${t}"/>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    您的品牌名称：<span class="ui-form-required">*</span>
                </label>
                <c:choose>
                	<c:when test="${empty info.brandState || info.brandState==0}">
                		<input id="brandName" name="brandName" type="text" class="ui-input" required data-rule="isNull" data-display="品牌名称" maxlength="32" value="${info.brandsName}"/>
                	</c:when>
                    <c:otherwise>
                        <span>${info.brandsName}</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for=""> 选择品牌类型：<span class="ui-form-required">*</span></label>
                <div class="radio_box inline-block">
                    <label style="cursor: pointer" for="domestic_brand">
                        <input id="domestic_brand" <c:if test="${empty info.brandType || info.brandType!=2}"> checked="checked" </c:if> class="ui-radio" name="brandType" type="radio" value="1"/>自有品牌
                    </label>
                    <label style="cursor: pointer" for="foreign_brand">
                        <input id="foreign_brand" <c:if test="${info.brandType==2}"> checked="checked" </c:if>  class="ui-radio ml5" name="brandType" type="radio"  value="2"/>授权品牌
                    </label>
                    <input type="hidden" id="typeError" required value="1"/>
                </div>
            </div>
            <div class="ui-form-item  vertical-tip">
                <label class="ui-label" for="">
                    您的品牌证书：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                    <ul class="inline-float certificate_box">
                        <c:if test="${not empty certList}">
                            <c:forEach items="${certList}" var="cert">
                                <li class="item">
                                    <div class="img_contain">
                                    	<c:set value="${fileUrl}${cert.imageName}" var="url"></c:set>
                                        <img src="${url}" alt="${cert.fileName}" style="width:100px;height:100px;"/><span></span>
                                        <a data-display="品牌证书" href="javascript:;" class="iconfont close">&#xe612;</a>
                                        <input type="hidden" name="certImgPaths" value="${cert.imageName}" />
                                        <input type="hidden" name="certImgNames" value="${cert.fileName}" />
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>

                        <li class="add_certificate">
                            <div class="file_wrap">
                                <i class="iconfont">&#xe611;</i>
                                <input class="input_file" id="certImg" name="photo" type="file"/>
                            </div>
                        </li>
                    </ul>
                    <p class="mt5 explain">商标注册证复印件或商标受理通知书范本，授权品牌需要提供品牌授权书和商标注册证</p>
                    <p class="explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                </div>
                <input type="hidden" id="certError" required value="1"/>
            </div>
            <!-- 品牌授权书 -->
            <div id="attorney" <c:if test="${empty info.brandType || info.brandType!=2}">style="display: none;"</c:if> class="ui-form-item  vertical-tip">
                <label class="ui-label" for="">
                    品牌授权书：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                    <ul class="inline-float certificate_box">
                    	<c:if test="${not empty liceningList}">
                            <c:forEach items="${liceningList}" var="licening">
                                <li class="item">
                                    <div class="img_contain">
                                    	<c:set value="${fileUrl}${licening.imageName}" var="url"></c:set>
                                        <img src="${url}" alt="${licening.fileName}" style="width:100px;height:100px;"/><span></span>
                                        <a data-display="品牌授权书" href="javascript:;" class="iconfont close">&#xe612;</a>
                                        <input type="hidden" name="liceningImgPaths" value="${licening.imageName}" />
                                        <input type="hidden" name="liceningImgNames" value="${licening.fileName}" />
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>
                        <li class="add_certificate">
                            <div class="file_wrap">
                                <i class="iconfont">&#xe611;</i>
                                <input class="input_file"  type="file" id="liceningImg" name="photo" />
                            </div>
                        </li>
                    </ul>
                    <p class="mt5 explain">授权品牌需要提供品牌授权书</p>
                    <p class="explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                </div>
                <input type="hidden" id="liceningError" required value="1"/>
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">
                    您的品牌LOGO：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                    <div class="file_wrap brand_logo">
                        <c:choose>
                            <c:when test="${empty info.brandLogo}">
                                <p class="replace_text">点击上传</p>
                                <input class="input_file" id="logoImg" name="photo" type="file"/>
                            </c:when>
                            <c:otherwise>
                                <p class="replace_text">
                                    <c:set value="${fileUrl}${info.brandLogo}" var="url"></c:set>
                                    <img style="width:200px;height:100px;" alt="品牌logo" src="${url}">
                                    <input type="hidden" name="logoImgPath" value="${info.brandLogo}" />
                                    <input type="hidden" name="logoImgName" value="${logoImgName}" />
                                    <input class="input_file" id="logoImg" name="photo" type="file"/>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <p class="mt5 explain">
                        （上传图片建议为宽400像素高200像素，仅支持JPG、GIF、PNG图片文件，且文件小于2M）
                    </p>
                </div>
                <input type="hidden" id="logoError" required value="1"/>
            </div>
            <div class="ui-form-item vertical-tip">
                    <label class="ui-label" for="">
                        企业产品形象图：<span class="ui-form-required">*</span>
                    </label>
                    <div class="inline-block">
                        <ul class="inline-float certificate_box">
                        <c:if test="${not empty photoList}">
                            <c:forEach items="${photoList}" var="brandPhoto">
                                <li class="item">
                                    <div class="img_contain">
                                    	<c:set value="${fileUrl}${brandPhoto.imageName}" var="url"></c:set>
                                        <img style="width:100px;height:100px;" alt="${brandPhoto.photoName}" src="${url}"><span></span>
                                        <a data-display="企业产品形象图" href="javascript:;" class="iconfont close">&#xe612;</a>
                                    	<input type="hidden" value="${brandPhoto.imageName}" name="photoImgPaths">
                                    	<input type="hidden" value="${brandPhoto.photoName}" name="photoImgNames">
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>

                            <li class="add_certificate">
                                <div class="file_wrap">
                                    <i class="iconfont">&#xe611;</i>
                                    <input class="input_file" id="photoImg" name="photo" type="file"/>
                                </div>
                            </li>
                        </ul>
                        <p class="mt5 explain">企业产品形象图片需为正方形</p>
                        <p class="explain">（仅支持JPG、GIF、PNG图片文件，且图片小于2M）</p>
                    </div>
                    <input type="hidden" id="photoError" required value="1"/>
            </div>
            <c:if test="${not empty info}">
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">推荐图片：</label>
                <div class="inline-block">
                    <div class="file_wrap brand_logo">
                        <c:choose>
                            <c:when test="${empty info.recommendImage}">
                                <p class="replace_text">点击上传</p>
                                <input class="input_file" id="recommendImage" name="photo" type="file"/>
                            </c:when>
                            <c:otherwise>
                                <p class="replace_text">
                                    <c:set value="${fileUrl}${info.recommendImage}" var="url"></c:set>
                                    <img style="width:200px;height:100px;" alt="推荐图片" src="${url}">
                                    <input type="hidden" name="recommendImagePath" value="${info.recommendImage}" />
                                    <input type="hidden" name="recommendImageName" value="" />
                                    <input class="input_file" id="recommendImage" name="photo" type="file"/>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <p class="mt5 explain">
                        （上传图片建议为宽400像素高200像素，仅支持JPG、GIF、PNG图片文件，且文件小于2M）
                    </p>
                </div>
                <input type="hidden" id="recommendImageError" required value="1"/>
            </div>
            </c:if>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    品牌持有人：<span class="ui-form-required">*</span>
                </label>
                <div class="radio_box inline-block">
                    <input <c:if test="${empty info.brandHold || info.brandHold==1}"> checked="checked" </c:if> class="ui-radio js-company" name="brandHold" type="radio" value="1"/>公司
                    <input <c:if test="${info.brandHold==2}"> checked="checked" </c:if>  class="ui-radio ml5 js-people" name="brandHold" id="brandHold" type="radio" value="2" />自然人
                </div>
                <input type="hidden" id="holdError" required value="1"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    <span id="holdName_label">
                        <c:if test="${empty info.brandHold || info.brandHold==1}"> 品牌所属公司名称 </c:if>
                        <c:if test="${info.brandHold==2}"> 品牌持有人名称 </c:if>
                    </span>
                    <span class="ui-form-required">*</span>
                </label>
                <input type="text" id="holdName" name="holdName" class="ui-input" value="${info.holdName}" required maxlength="64" data-rule="isNull" data-display="<c:if test="${empty info.brandHold || info.brandHold==1}">品牌所属公司名称</c:if><c:if test="${info.brandHold==2}">品牌持有人名称</c:if>">
            </div>
            <div class="ui-form-item vertical-tip">
                <label class="ui-label" for="">
                    品牌主营类目：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block product_area" style="width:441px; background-position: 215px 126px;" >
                    <ul class="inline-float">
                        <li style="position: relative" id="select_category" class="select_category">
                            <ul class="select_inner">

                            </ul>
                        </li>
                        <%-- 暂时取消 <li id="select_class" class="select_class">
                            <ul class="select_inner">

                            </ul>
                        </li>--%>
                        <li id="select_product" class="select_product">
                            <ul class="select_inner">
                                <c:if test="${not empty brandDicList}">
                                    <c:forEach items="${brandDicList}" var="dic">
                                        <c:if test="${not empty dic.dealDic}">
                                            <li id="${dic.dealNo}" class="item"><span>${dic.dealDic.dealName}</span>
                                            <i class="iconfont close_icon">&#xe602;</i>
                                            <input type="hidden" value="${dic.dealDic.dealName}" name="dealNames">
                                            <input type="hidden" value="${dic.dealNo}" name="deals"></li>
                                        </c:if>

                                    </c:forEach>
                                </c:if>
                            </ul>
                        </li>
                    </ul>
                </div>
                <input type="hidden" id="dicError" required value="1"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    品牌标语：<span class="ui-form-required"></span>
                </label>
                <textarea class="ui-textarea" placeholder="品牌标语！" name="brandSubMark" style="width: 669px; height: 120px;">${info.brandSubMark}</textarea>
                <input type="hidden" id="subMarkError" required value="1"/>
            </div>
            <div class="ui-form-item  vertical-tip">
                <label class="ui-label" for="">
                    您的品牌简介：<span class="ui-form-required">*</span>
                </label>
                <div class="inline-block">
                    <textarea id="myEditor1" name="brandMark" required data-display="品牌简介">${info.brandMark}</textarea>
                </div>
            </div>
            <div class="ui-form-item">
                <c:if test="${notSave==null}">
                <button class="submit_btn" type="submit">提交品牌资料</button>
                </c:if>
            </div>
        </form:form>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/brand_manage.js"></script>

<!--<script type="text/html" id="test">
{{each list as items i}}
<li id="{{items.id}}" class="item" name="{{i}}"><span>{{items.item}}</span><i class="iconfont"></i></li>
{{/each}}
</script>

<script type="text/html" id="subtest">
{{each list as value}}
<li id="{{value.id}}" class="item"><span>{{value.item}}</span><i class="iconfont"></i></li>
{{/each}}
</script>-->
<script>

	var _dealList = ${dealList};

    /*2014-04-11*/
    $.each(_dealList,function(ind,item){//主分类
        var html = '<li id="'+ item.id + '" class="item" name="'+ind+'"><span>'+item.item+'</span><i class="iconfont"></i></li>';
        $("#select_category .select_inner").append(html);
    });

    function isExitInArr(id){
        var num = 0;
        var arr = [];
        $("#select_product li").each(function(){
        	arr.push($(this).attr("id"));
        });
        for(var i = 0;i<arr.length;i++){
            if(arr[i] == id){
                num ++;
            }
        }
        if(num > 0){
            return true;
        }else{
            return false;
        }
    }

    $("#select_category .select_inner").on("click",".item",function(){
        var i = parseFloat($(this).attr("id"));
        var product_obj = $(this).find("span").html();
        if(!isExitInArr(i)){
            $("#select_product .select_inner").append('<li class="item" id="'+ i + '"><span>'+product_obj+'</span><i class="iconfont close_icon">&#xe602;</i><input type="hidden" name="dealNames" value="'+ product_obj +'"  /><input type="hidden" name="deals" value="'+ i +'"  /></li>');
        }
    });

    $("#select_product").on("mouseenter mouseleave click",".item",function(ev){
        switch (ev.type){
            case "mouseenter":
                $(this).find(".close_icon").show();
                break;
            case "mouseleave":
                $(this).find(".close_icon").hide();
                break;
            case "click":
                var id = $(this).attr("id");
                $($("#"+id)).removeClass("current");
                $(this).remove();
        }
    });
    /*2014-04-11 结束*/
    add_brand.init();

    seajs.use(['validator', 'widget'], function(Validator, Widget){
        Widget.autoRenderAll();
        var Core = Validator.query("#addBrand-form");
        Core.set("autoSubmit",true);
        Validator.addRule("isNull", function(obj){
            var gets = $(obj.element).val();
            //$(obj.element).val($.trim(gets.replace(/\s/g, "")));
            return $.trim(gets) != "";
        }, "请填写正确字段");

        $("[name='brandHold']").on("click",function(){
            var value = $(this).val();
            if(value == 1){
                $("#holdName_label").html("品牌所属公司名称");
                Core.addItem({
                    element: "#holdName",
                    required: true,
                    display: "品牌所属公司名称"
                })
            }else if(value == 2){
                $("#holdName_label").html("品牌持有人名称");
                Core.addItem({
                    element: "#holdName",
                    required: true,
                    display: "品牌持有人名称"
                })
            }
        });

        Core.on('formValidated', function(error, message, elem) {
            if(!error){
                $(".submit_btn").css({
                    "backgroundColor": "gray",
                    "disabled":"disabled"
                })
            }
        });

        $("#domestic_brand").on("click",function(){
            $("#attorney").hide();
        });

        $("#foreign_brand").on("click",function(){
            $("#attorney").show();
        });

        if("${param.result}" == "success"){
            remind("success", "提交成功");
        }

    });
    
    
    /* seajs.use("validator",function(Validator){

        if("${param.result}" == "success"){
    		remind("success", "提交成功");
    	}
    	
        var validator = new Validator({
            element: '#addBrand-form',
            failSilently: true,
            stopOnError: true
        });
        validator.addItem({
            element: '#checkResult',
            required: true,
            onItemValidate: function(){
            $('#checkResult').val('true');
            	 if($(':input[name="logoImgPath"]').length == 0)
    			{
    				remind("error",'请上传您的品牌LOGO');
    			}
            	else if($(':input[name="certImgPaths"]').length == 0)
    			{
    				remind("error",'请上传您的品牌证书');
    			}
            	else if($(':input[name="photoImgPaths"]').length == 0)
    			{
    				remind("error",'请上传企业产品形象图');
    			}
            	else if($(':input[name="deals"]').length == 0)
    			{
    				remind("error",'请选择品牌主营类目');
    			}
            	else
            	{
            		$('#checkResult').val('true');
            	} 
            }
        });
    }); */
    //在brand_manage.js中加入
    //<input type='hidden' name='deals' value='"+obj.id+"'/>
    $(function(){
    	var uploadIds = ['certImg', 'logoImg', 'photoImg','liceningImg','recommendImage'];
    	for(var i = 0; i < uploadIds.length; i++){
    		$('#' + uploadIds[i]).bind('change', function(){uploadImage($(this).attr('id'));});
    	}
    	
    	function showImage(uploadId, url, oldName){
    	
    		var html = '';
    		
    		var brandsId = $.trim($("#brandsId").val());

    		//如果是修改的话
    		if(uploadId == 'logoImg'){
    			if(brandsId != ""){
    				$('#' + uploadId).parent().find("img").attr("src",url);
    				$('#' + uploadId).parent().find("[name='"+uploadId+"Path']").val(url);
    				$('#' + uploadId).parent().find("[name='"+uploadId+"Name']").val(oldName);
    				return;
    			}
    			html+= '<img src="' + url + '" alt="' + oldName + '" style="width:200px;height:100px;"/>'
					+  '<input type="hidden" name="' + uploadId + 'Path" value="' + url + '" />'
					+  '<input type="hidden" name="' + uploadId + 'Name" value="' + oldName + '" />';
				$('#' + uploadId).prev().html(html);
    		}
    		else if(uploadId == 'photoImg'){
    			/*html+= '<img src="' + url + '" alt="' + oldName + '" style="width:112px;height:112px;"/>'
					+  '<input type="hidden" name="' + uploadId + 'Paths" value="' + url + '" />'
    				+  '<input type="hidden" name="' + uploadId + 'Names" value="' + oldName + '" />';
				$('#' + uploadId).prev().html(html);*/
                html+= '<li class="item">'
                        +     '<div class="img_contain">'
                        +         '<img src="' + url + '" alt="' + oldName + '" style="width:100px;height:100px;"/><span></span>'
                        +         '<a href="javascript:;" class="iconfont close">&#xe612;</a>'
                        +		  '<input type="hidden" name="' + uploadId + 'Paths" value="' + url + '" />'
                        +		  '<input type="hidden" name="' + uploadId + 'Names" value="' + oldName + '" />'
                        +     '</div>'
                        + '</li>';
                $('#' + uploadId).parents('ul').prepend(html);
    		}else if(uploadId == 'recommendImage'){
    			if($('#' + uploadId).parent().find("[name='"+uploadId+"Path']").length > 0){
    				$('#' + uploadId).parent().find("img").attr("src",url);
    				$('#' + uploadId).parent().find("[name='"+uploadId+"Path']").val(url);
    				$('#' + uploadId).parent().find("[name='"+uploadId+"Name']").val(oldName);
    				return;
    			}
    			html+= '<img src="' + url + '" alt="' + oldName + '" style="width:200px;height:100px;"/>'
					+  '<input type="hidden" name="' + uploadId + 'Path" value="' + url + '" />'
					+  '<input type="hidden" name="' + uploadId + 'Name" value="' + oldName + '" />';
				$('#' + uploadId).prev().html(html);
    		}else{
    			html+= '<li class="item">'
    				+     '<div class="img_contain">'
    				+         '<img src="' + url + '" alt="' + oldName + '" style="width:100px;height:100px;"/><span></span>'
    				+         '<a href="javascript:;" class="iconfont close">&#xe612;</a>'
    				+		  '<input type="hidden" name="' + uploadId + 'Paths" value="' + url + '" />'
    				+		  '<input type="hidden" name="' + uploadId + 'Names" value="' + oldName + '" />'
    				+     '</div>'
    				+ '</li>';
    			$('#' + uploadId).parents('ul').prepend(html);
    		}
    	}
    	
    	function uploadImage(uploadId){
    		seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: '${ctx}/common/showImg?fSize=2',
        			secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function(data){
                    	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                    	$('#' + uploadId).bind('change', function(){
                			uploadImage(uploadId);
                		});
                    	
                    	if(data.code == zttx.SUCCESS)
                    	{
                    		showImage(uploadId, '${fileUrl}'+data.message, data.object);
                    	}
                    	else
                    	{
                    		remind("error",data.message);
                    	}
                    }
        		});
    	    });
    	}
    });
    
</script>
<c:if test="${!empty errCode}">
<script>
$(function(){
	var code = "${errCode}";
	if(zttx.VALIDERR==code){
		var errList = eval('${message}');
		setErrMsg("#addBrand-form", errList);
	}else{
		remind("error",'${message}');
	}
});
</script>
</c:if>
</body>
</html>