<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌商管理中心-天下商学院</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/school.css"/>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
		<div class="main_con">
			<div class="inner">
				<!-- 内容都放这个地方  -->
				
        		<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_top.jsp">
        			<jsp:param name="m" value="6" />
        		</jsp:include>
        		
				<div style="background: #fff; padding: 10px;">
				
					<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_bar.jsp">
						<jsp:param name="m" value="6" />
					</jsp:include>
					
    				<div class="main-grid mb10 ml20">
        				<div class="fs14 yahei nomalf" style="padding: 10px;">常规信息：</div>
    				</div>

    				<div class="main-grid mb10">
        				<div class="panel-form">
            				<form:form class="ui-form school-form clearfix" id="From_BaseInfo" method="post" enctype="multipart/form-data" action="${ctx}/brand/school/save">
            					<input name="refrenceId" type="hidden" value="${schoolArticle.refrenceId}" />
            					
                				<div class="ui-form-item">
                    				<label class="ui-label"><span class="c-r">*</span> 标 题：</label>
                    				<input value="${schoolArticle.articleTitle}" type="text" name="articleTitle"  class="ui-input" style="width:450px;" />
                    				<label>50字以内</label>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"> 标题缩略图：</label>
                					<div class="img-upload">
                						<div class="file_wrap">
                							<c:if test="${empty schoolArticle.articleIcon}">
                								<a class="upload fs14 mb10 uploadID yahei fs16" id="img">点击上传</a>
                							</c:if>
                							<c:if test="${not empty schoolArticle.articleIcon}">
                								<a class="upload fs14 mb10 uploadID yahei fs16" id="img">
													<c:set value="${schoolArticle.domainName}${schoolArticle.articleIcon}" var="url"></c:set>
                									<img src="${res}${fns:getImageDomainPath(url, 100, 100)}" style="width:100%;height:100%;"  alt=""/>
                									<input type="hidden" name="articleIcon" value="${schoolArticle.domainName}${schoolArticle.articleIcon}" />
                								</a>
                							</c:if>
                							<input class="input_file" type="file" id="articleIcon" name="photo" />
                						</div>
                						<span style="display:block; margin-top: 10px; ">(建议尺寸为：200*100，否则可能导致无法正常显示)</span>
                					</div>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"><span class="c-r">*</span> 学习权限：</label>
                					<div class="inline-block">
                						<select style="width:240px;height:32px;"  class="js-select ui-select" name="learnRight">
                							<option<c:if test="${empty schoolArticle.learnRight || schoolArticle.learnRight == 1}"> selected</c:if> value="1">平台公开</option>
                							<option<c:if test="${schoolArticle.learnRight == 2}"> selected</c:if> value="2">员工交流</option>
                						</select>
                					</div>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"><span class="c-r">*</span> 上传分类：</label>
                					<div class="inline-block">
                						<select style="width:240px;height:32px;" class="js-select ui-select" name="cataId">
                						<c:forEach items="${schoolCatalogs}" var="schoolCatalog">
                							<option value="${schoolCatalog.refrenceId}"  selected="${schoolCatalog.refrenceId == schoolArticle.cataId ? 'selected' : ''}">${schoolCatalog.cataName}</option>
                						</c:forEach>
                						</select>
                					</div>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label">出售价格：</label>
                					<input name="articlePrice" type="text" class="ui-input js-price" value="${schoolArticle.articlePrice}" />
                					<span class="err">不填或者“0”，表示免费</span>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"><span class="c-r">*</span> 内容简要：</label>
                					<textarea name="articleMark">${schoolArticle.articleMark}</textarea>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"><span class="c-r">*</span> 关 键 字：</label>
                					<input value="${schoolArticle.keyWord}" type="text" name="keyWord"  class="ui-input short js_comma" style="width:350px;" />
                					<label>用","分开，不超过五个</label>
                				</div>
                				
                				<div class="hr-dashed m020 mb20"></div>
                				
                				<div class="main-grid mb10 ml20">
                					<div class="fs14 yahei nomalf" style="padding: 10px; border-top:1px dashed #999;">详细内容：</div>
                				</div>
                				
                				<div class="ui-form-item">
                					<label class="ui-label"><span class="c-r">*</span> 内容输入：</label>
                					<textarea name="articleText" id="myEditor1">${schoolArticle.articleText}</textarea>
                				</div>
                				
                				<div class="ui-form-item myinput">
                					<label class="ui-label"> 上传附件：</label>
                					<input type='hidden' name='articleFile' value="${schoolArticle.articleFile}" />
                					<input type='text' name='fileName' id='textfield' class='txt' readonly value="${schoolArticle.fileName}" style="padding-left: 5px;" />
                					<input type="file" name="articleFile1" class="file" id="fileField" size="28" style="display: none;" />
                					<input type="button" class="btn" value="上传" onclick="$('#fileField').click();" />
                					<label>上传附件大小限制在5M以内</label>
                				</div>
                				
                				<div class="ui-form-item mt20">
                					<input class="ui_button ui_button_lorange yahei" type="submit" value="提 交" />
                					<input class="ui_button ui_button_lorange yahei buttonbg" type="reset" value="重 置" />
                				</div>
                			</form:form>
                		</div>
                	</div>
                	
                </div>
            </div>
        </div>
    </div>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<tags:message content="${message}" width="300"></tags:message>
<script>
    seajs.use(["validator", "umeditor","umeditor_config","umdeitor_style", "ajaxFileUpload"],function(Validator){
        UM.getEditor('myEditor1', {
            initialFrameWidth: 676,
            initialFrameHeight: 500
        });
        
        
		$('#articleIcon').bind('change', uploadImage);
		
		function showImage(url) {
			var html = '<img src="${res}' + url
					+ '" style="width:100%;height:100%;"  alt=""/>';
			html += '<input type="hidden" name="articleIcon" value="' + url
					+ '" />';
			$('#img').html(html);
		}
		
		function uploadImage(){
			$.ajaxFileUpload({
				url : '${ctx}/common/showImg',
				secureuri : false,
				fileElementId : 'articleIcon',
				dataType : 'json',
				success : function(data) {
					// 发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
					$('#articleIcon').bind('change', function() {
						uploadImage();
					});
					if (data.code != 126000) {
		
					} else {
						showImage(data.message);
					}
				}
			});
		}
		
		var validator = new Validator({
            element: '#From_BaseInfo',
            failSilently: true,
            stopOnError: true
        }).addItem({
            element: ':input[name="articleTitle"]',
            required: true,
            rule: 'minlength{min:1} maxlength{max:50}',
            errormessage: '请输入标题'
        }).addItem({
            element: ':input[name="articleMark"]',
            required: true,
            rule: 'minlength{min:1} maxlength{max:512}',
            errormessage: '请输入内容简要'
        }).addItem({
            element: ':input[name="keyWord"]',
            required: true,
            errormessage: '请输入关键字'
        }).addItem({
            element: ':input[name="articleText"]',
            required: true,
            errormessage: '请输入详细内容'
        }).addItem({
            element: '#myEditor1',
            required: true,
            errormessage: '请输入详细内容'
        }).addItem({
            element: ':input[name="articlePrice"]',
            required: true,
            rule: 'maxlength{max:7}',
            errormessage: '请输入小于7位的数'
        });

        /*
        *验证关键字，差复制
        **/
        $(".js_comma").on("keyup keydown input",function(ev){

            var str = $(this).val();
            var arr = str.match(/[,，]/g);
            var num = 0;
            var key = ev.keyCode;

            if(arr){
                numArr = arr.length;
                num = parseInt(numArr);
            }

            if( num > 4){

                if(key !== 8){
                    remind("error","关键词已满五个，删除最后一个‘，’号才能继续输入",3000);
                    return false;
                }
            }

        });

        /*
         *验证文件类型和大小
         **/
        $("#fileField").change(function(){

            var _this = $(this);
            var val = _this.val();
            var format = /\.(rar|zip|7z)$/;

            //文件格式
            if (val == "") {
                return false;
            } else if (!format.test(val)) {
                remind("error","文件格式必须是rar、zip、7z中的一种",3000);
                return false;
            }
            //文件大小

            document.getElementById('textfield').value=this.value.substring(this.value.lastIndexOf('\\')+1, this.value.length);

        });
    });

</script>
</body>
</html>