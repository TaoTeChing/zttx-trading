<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html>
<head>
<meta charset="UTF-8">
<title>管理中心-图片管理-图片上传</title>
<link rel="stylesheet" href="${res}/styles/brand/classification.css" />
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
</head>
<body>

<jsp:include page="${ctx}/common/menuInfo/mainmenu"/><!--这里面重复了一份脚本-->
	<div class="main layout">
        <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <!-- 面包导航，并不是每个页面都有 -->
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/brandImage/image">图片管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">图片上传</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<!-- 图片管理  -->
				<div class="main_classification">
					<div class="ui_tabs">
						<div class="ui_tabsmenu">
							<ul class="clearfix">
								<li><a href="${ctx}/brand/brandImage/image">图片管理</a></li>
								<li class="selected"><a
									href="${ctx}/brand/brandImage/image/upload_picture">图片上传</a></li>
								<li><a href="${ctx}/brand/brandImgcate/imgcate">分类管理</a></li>
								<li><a href="${ctx}/brand/brandImage/image/recycle_picture">回收站</a></li>
							</ul>
						</div>
						<div class="ui_tabsbox">
							<form id="js-form-upload" action="${ctx}/brand/image/batchupload">
							<!-- 图片管理：图片上传 -->
							<div class="uploadimg">
                                <div class="inline-block" style="margin-left:20px;">
                                    <select class="js-select" id="category" name="cateId">
                                        <c:if test="${empty listCate}">
                                            <option value="">默认分类</option>
                                        </c:if>
                                        <c:if test="${not empty listCate}">
                                            <c:forEach var="obj" items="${listCate}">
                                                <option value="${obj.id}" ${condition.cateId==obj.id ? "selected" : ""}>${obj.name}</option>
                                                <c:forEach var="obj" items="${obj.children}">
                                                    <option value="${obj.id}" ${condition.cateId==obj.id ? "selected" : ""}>┠┄┄${obj.name}</option>
                                                </c:forEach>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="inline-block">
                                    <div class="file_wrap js-file-wrap" style="height: 28px;">
                                        <a class="simple_button js-addpic-btn ml5"><i class="iconfont">&#xe611;</i>添加图片</a>
                                        <input type='file' name='file' class="input_file" id="upload_file" />
                                    </div>
                                </div>
                                <div class="inline-block uploadtips">当前图库大小为 ${currentSize} 总共使用${percent}的图片空间（上限为${totalSize}）</div>
                                <div class="tips">
                                    <i class="v2-icon-explain"></i>小提示：单张图片大于3M可选择强制压缩支持.jpg .jpeg .png .gif .bmp格式，一次上传不限张数
                                </div>
								<!-- 批量添加 -->
								<%--<div style="font-size:15px;line-height:25px; margin-top: 10px; margin-bottom: 10px; font-size: 12px;" id="flashButton">
									批量添加：<input type="file" name="file_upload" id="file_upload" />
									<div id="fileQueue"></div>
								</div>--%>
								<!-- 批量添加结束 -->
                                <div class="piclass-upload-head clearfix">
                                    <div class="upload-head-items" style="width: 55%; text-align: left; text-indent: 10px;">文件名</div>
                                    <div class="upload-head-items">文件大小</div>
                                    <div class="upload-head-items">上传状态</div>
                                    <div class="upload-head-items"> </div>
                                </div>
                                    <div class="piclass-upload-con" style="margin-bottom: 20px;">
                                        <ul></ul>
                                    </div>
								<a style="display: none;" class="uploadpicbtn ui_button ui_button_lblue" href="javascript:;" id="upload">开始上传</a>
                                <div style="display: none;" class="adddelpic inline-block">
									<a href="javascript:void(0)" class="clearpic ui_button ui_button_lorange">清空图片</a>
									<span class="addwatermark" style="display:none;">
										<input type="checkbox" class="js_chk ui-checkbox mr10" id="setdef11" />
										<label class="markfont" for="setdef11">添加水印</label>
										<em>（为您的图片添加水印，水印内容为www.8637.com）</em>
									</span>
								</div>
							</div>
                            </form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
    <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
    <script src="${src}/brand/classification.js"></script>
    <script type="text/javascript" src="${src}/plugin/uploadify3.2/jquery.uploadify.js"></script>
<script>
	var cateId, waterStatus;

    pictureManagement2.init();

    $(function(){
        //修改为多图上传
        seajs.use(['uploadify'], function(){
            var photoUpload = $('#upload_file').uploadify({
                'formData'     : {
                    fSize: '7',
                    csrfToken: $("input[name=csrfToken]").val(),
                },
                'swf'      : window.UPLOADIFY_SWF_URL,
                'uploader' : '${ctx}/brand/brandImage/image/batchupload',
                'fileObjName': 'file',
                'method': 'post',
                'buttonClass': 'buttonClass',
                'buttonText': '添加图片',
                'width': 78,
                'height': 30,
                'queueID'  : 'some_file_queue',
                fileSizeLimit: '3MB',
                fileTypeExts: '*.jpg;*.jpeg;*.png;*.gif;*.bmp',
                'dataType': 'json',
                //选择上传文件后调用
                'onSelect' : function(file) {
                    //console.log(file);
                },
                //返回一个错误，选择文件的时候触发
                'onSelectError':function(file, errorCode, errorMsg){
                    switch(errorCode) {
                        case -100:
                            //alert("上传的文件数量已经超出系统限制的" + imgTotal + "个文件！");
                            break;
                        case -110:
                            alert("文件 ["+file.name+"] 大小超出系统限制的"+ photoUpload.uploadify('settings','fileSizeLimit')+"大小！");
                            break;
                        case -120:
                            alert("文件 ["+file.name+"] 大小异常！");
                            break;
                        case -130:
                            alert("文件 ["+file.name+"] 类型不正确！");
                            break;
                    }
                },
                //检测FLASH失败调用
                'onFallback':function(){
                    alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
                },
                'onUploadStart': function(){
                    photoUpload.uploadify('settings','formData', {
                        fSize: '7',
                        csrfToken: $("input[name=csrfToken]").val(),
                        cateId:  $("#category").val(),
                        brandId: "${brandId}",
                        waterStatus: $("#setdef11").is(":checked")
                    });
                },
                'onUploadSuccess': function(file, data, response){
                    //console.log(file);
                    //console.log(data);
                    data = $.parseJSON(data);

                    console.log(data);

                    var html = "";

                    if(data.code == zttx.SUCCESS){
                        var size = data.object.sizeKb ? data.object.sizeKb + " KB" : "0 KB";

                        html = '<li class="piclass-upload-li clearfix success-font">                                                                               \
                                 <div class="upload-li-items js-upload-name" style="width: 55%; text-align: left; text-indent: 10px;">' + data.object.fileName + '</div>                  \
                                 <div class="upload-li-items js-upload-size">' + size +'</div>                                                                                                \
                                 <div class="upload-li-items js-upload-state noupload-font">上传成功</div>                                                                          \
                                 <div class="upload-li-items"></div>                          \
                             </li>';

                    }else{
                        remind("error", data.message);
                        html = '<li class="piclass-upload-li fail-font clearfix">                                                                               \
                                             <div class="upload-li-items js-upload-name" style="width: 55%; text-align: left; text-indent: 10px;"></div>                  \
                                     <div class="upload-li-items js-upload-size"></div>                                                                                                \
                                     <div class="upload-li-items js-upload-state noupload-font">上传失败</div>                                                                          \
                                     <div class="upload-li-items"></div>                          \
                                </li>';
                    }

                    $(html).appendTo(".piclass-upload-con ul");
                }
            });

            $(".piclass-upload-con").on("click", ".upload-li-del", function () {
                if($(".piclass-upload-li").length == 1){
                    $("#upload").hide();
                    $(".adddelpic").hide();
                }
                $(this).parents(".piclass-upload-li").remove();
            });

            $(document).on('click','.clearpic',function(){
                $(".piclass-upload-li").remove();
                $("#upload").hide();
                $(".adddelpic").hide();
            });

            $("#category").on("change",function(){
                $(".piclass-upload-li").each(function(){
                    if($(this).find(".js-upload-state").html() == "上传成功"){
                        $(this).remove();
                    }
                })
            });
        });
    });
</script>
</body>
</html>