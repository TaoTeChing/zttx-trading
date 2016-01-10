<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-品牌管理-资讯管理</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
<style>
    .file_wrap{
        width: 270px;
        height: 146px;
        border: #cccccc dashed 1px;
        background: #f9f9f9;
    }
    .file_wrap .replace_text{
        line-height: 146px;
        color: #ccc;
        font-size: 14px;
    }
    .file_wrap .input_file{
        width: 270px;
        height: 146px;
        font-size: 14px;
    }
    .explain {
        color: #999;
    }
</style>
</head>
<body>

<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
<jsp:include page="/common/menuInfo/sidemenu"/>
		
		<div class="main_con">
            <!-- 面包导航，并不是每个页面都有 -->
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <c:choose>
                        <c:when test="${param.m=='1'}">
                            <span class="arrow">&gt;&gt;</span>
                            <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                            <span class="arrow">&gt;</span>
                            <span class="current">交易会管理</span>
                        </c:when>
                        <c:otherwise>
                            <span class="arrow">&gt;&gt;</span>
                            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                            <span class="arrow">&gt;</span>
                            <span class="current">资讯管理</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
	        	<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
    				<jsp:param name="m" value="3" />
    			</jsp:include>
		
				<form:form data-widget="validator" id="newsForm" class="ui-form mt20 info-form" action="${ctx}/brand/brandNews/save">
					<input id="refrenceId" name="refrenceId" type="hidden" value="${result.refrenceId}">
					<div class="ui-form-item">
						<label class="ui-label" for=""> 品牌：
							<span class="ui-form-required">*</span> 
						</label>
						<div class="inline-block">
							<select required data-errormessage-required="请选择品牌" class="ui-select js_select" name="brandsId">
                                <c:if test="${brandesInfos.size() == 0 || brandesInfos.size() > 1}">
								    <option value="">请选择</option>
                                </c:if>
								<c:forEach items="${brandesInfos}" var="brandesInfo">
									<c:choose>
										<c:when test="${brandesInfo.refrenceId == result.brandsId}">
											<option value="${brandesInfo.refrenceId}" selected="selected">${brandesInfo.brandsName}</option>
										</c:when>
										<c:otherwise>
											<option value="${brandesInfo.refrenceId}">${brandesInfo.brandsName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="ui-form-item" style="display:none;">
						<label class="ui-label" for=""> 类型：
							<span class="ui-form-required">*</span> 
						</label>
						<div class="inline-block">
							<select required data-errormessage-required="请选择类型" class="ui-select js_select" name="cateId" id="cateId">
								<!-- <option value="">请选择</option> -->
								<c:forEach items="${cates}" var="cate">
									<c:choose>
										<c:when test="${cate.refrenceId == result.cateId}">
											<option value="${cate.refrenceId}" selected="selected">${cate.cateName}</option>
										</c:when>
										<c:otherwise>
											<option value="${cate.refrenceId}">${cate.cateName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="ui-form-item">
						<label class="ui-label" for=""> 标题：
							<span class="ui-form-required">*</span> 
						</label>
						<input data-display="标题" id="newsTitle" value="${result.newsTitle}"
							name="newsTitle" style="width: 648px;" type="text" class="ui-input" required maxlength="128">
					</div>
                    <div class="ui-form-item">
                        <label class="ui-label" for=""> 配图：
                        	<span class="ui-form-required">*</span> 
                        </label>
                        <div class="file_wrap" style="width:145px;height:145px">
                        	<div id="showUPImage">
                        		<c:choose>
                        			<c:when test="${result.imageUrl==null}">
                        				<p class="replace_text">点击上传</p>
                        			</c:when>
                        			<c:otherwise>
										<c:set value="${fileUrl}${result.imageUrl}" var="url"></c:set>
                        				<img src="${url}" style="width:145px;height:145px;" alt="">
    				   					<input type="hidden" name="attachtNames" value="${result.imageUrl}" />
                        			</c:otherwise>
                        		</c:choose>
                            </div>
                            <input class="input_file" type="file" name="photo" id="attachtNames"/>
                        </div>
                        <p class="mt5 explain">（图片尺寸145*145px，仅支持JPG、GIF、PNG图片文件，且文件小于2M）</p>
                        <input id="haspic" type="hidden" value="${result.imageUrl}"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for=""> 摘要：</label>
                        <div class="inline-block">
                            <input style="width: 648px;" type="text" class="ui-input" name="newsSummary" value="${result.newsSummary}" maxlength="256">
                        </div>
                    </div>
					<div class="ui-form-item vertical-tip">
						<label class="ui-label" for=""> 内容：<span
							class="ui-form-required">*</span> </label>
						<div class="inline-block">
							<textarea id="myEditor" class="ui-textarea guide_textarea" name="newsContent" >${result.newsContent}</textarea>
						</div>
					</div>
					<div class="ui-form-item">
                        <span>
							<c:choose>
                                <c:when test="${result.isWaitSend == true}">
                                    <input id="cron" name="isWaitSend" class="js_chk" type="checkbox" checked="checked"/>
                                </c:when>
                                <c:otherwise>
                                    <input id="cron" name="isWaitSend" class="js_chk" type="checkbox"/>
                                </c:otherwise>
                            </c:choose>
                            定时发布
						</span>
                        <input data-display="定时发布的时间" style="width: 190px;" type="text" class="ui-input ml5 Wdate" id="clock_time" name="cronDate" value="${(result.isWaitSend) ? result.cronDate:''}"/>
					</div>
                    <div class="ui-form-item">
						<input type="submit" class="ui_button ui_button_lblue" value="发&nbsp;布">
						<a href="javascript:;" class="ui_button simple_button ui_button_lgray cancel_btn ml10" type="button">取&nbsp;消</a>
					</div>
				</form:form>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<script>
 var G_PATH="${ctx}";
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>
		
	//新增新闻
	var add_news = {
	    init: function(){
            this.initCalendar(); //处理日期
            this.handleForm();  //处理表单
            this.cancelEdit();  //取消功能
        },
        initCalendar: function(){
            baseCalendar("#clock_time",{dateFmt: 'yyyy-MM-dd HH:mm:ss'},true);
        },
			
        handleForm: function(){
            baseFormValidator({
                isAjax: false, //默认为false，如果需要ajax提交，则把该值修正为true
                selector: "#newsForm",
                //如果该页面有编辑器，则传，没有，就不需要。
                editor: {
                    id: "myEditor",
                    width: 660,
                    height: 400,
                    "display": "内容"
                },
                addElemFn: function(Core,Validator){

				Core.addItem({
                        element: "#haspic",
                        required: true,
                        errormessageRequired:"请上传配图"
                    })
                    $("#cron").on("click",function(){
                       if($("#cron").prop("checked")){
                           Core.addItem({
                               element: "#clock_time",
                               required: true
                           })
                       }else{
                           Core.removeItem("#clock_time");
                       }
                    })
                    Core.removeItem("#clock_time");

                    //Core对象为Validator的实体对象，通过它可以.addItem
                    //Validator可用于新增一些规则
                    /*
                    Validator.addRule('notEmail', Validator.getRule('email').not(), '不能输入email!');
                    Core.addItem({
                        element: "#person_name",
                        required: true,
                        display: "品牌持有人名称"
                    })
                    */
                },
                beforeSubmitFn: function(){
                    //这里的操作是ajax，或者是在表单提交之前的赋值操作
                    //强调：这里不能做校验操作，任何的reuturn false，不会起作用。
                    // alert("我通过校验，进来了");
                    var newsContent = $.trim($("input[name='newsSummary']").val());
                    if(newsContent=="")
                    {
                    	var newsContent = $.trim(UM.getEditor('myEditor').getContentTxt());
                    	var charNum = 128;// 截取字符数
	                    newsContent = newsContent.substring(0, charNum);
	                    $("input[name='newsSummary']").val(newsContent);
                    }
                }
            })
        },

        cancelEdit: function(){
            $(".cancel_btn").on("click",function(){
                // alert("我点击了取消按钮");
                // location.href = "${ctx}/brand/news";
                if(remind==true) {
		   			if(confirm("确定取消保存？")==true){
		   				window.history.back();
		   			}
		   		} else {
		   			window.history.back();
		   		}
            });
        }

	}

	add_news.init();
	
	var remind = false;// 是否提醒用户(用户修改过表单就提醒)
	
	$('input[name="photo"]').bind('change', function(){uploadImage($(this).attr('id'));});		
	
	var resultImageUrl = '${result.imageUrl}';
	
	$(function(){
		if (resultImageUrl =="")
		{
			$("#haspic").val("");
		}
		
		$("#newsForm").each(function(index, obj){
    		$(obj).change(function(){
    			remind = true;
    		});
    	});
	});
	
	function showImage(uploadId, url,imageUrl){
   		var html = '<img src="'+ url +'" style="width:145px;height:145px;">' +
   				   '<input type="hidden" name="'+ uploadId +'" value="' + imageUrl + '" />';
       	$("#showUPImage").html(html);
       	$("#haspic").val("pass");// 通过前台校验
   	}
   	
   	function uploadImage(uploadId){
   		seajs.use(["$","ajaxFileUpload"],function($){
   			$.ajaxFileUpload({
       			url: '${ctx}/common/showImg',
       			secureuri: false,
                   fileElementId: uploadId,
                   dataType: 'json',
                   success: function(data)
                   {
                   	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                   	$('#' + uploadId).bind('change', function(){
               			uploadImage(uploadId);
               		});
                   	if(data.code == 126000)
                   	{
                   		showImage(uploadId,'${fileUrl}'+data.message,data.message);
                   	}
                   	else
                	{
                		remind("error",data.message);
                	}
            	}
       		});
   	    });
   	} 
		
        /*
		var f = $("#newsForm").Validform({
			tiptype : 4,
			postonce : false,
			btnSubmit : "#dealerSubmit",
			datatype : {
				"brandsId" : function(value) {
					return ("0" == value) ? false : true;
				},
				"cateId" : function(value) {
					return ("0" == value) ? false : true;
				}
			},
			callback : function(form) {
				formSubmit();
				return false;
			}
		});
		f.tipmsg.w.brandsId = "请选择品牌！";
		f.tipmsg.w.cateId = "请选择类型！";
		f.tipmsg.w.newsTitle = "不能为空！";
		
		

		function loadContent(uuid)
		{
			$.post("/brand/news/content","uuid="+uuid,function(data){
				if(data.code == 0)
        		{
        			var _editor = UM.getEditor('myEditor');
        			_editor.setContent(UM.utils.html(data.object));
        		}
			},"json");
		}
		
		function formSubmit() {
			var _editor = UM.getEditor('myEditor');
			var _data = $("#newsForm").serialize();
			var _summary = _editor.getContentTxt();
			if (_summary.length > 255) {
				_summary.substr(0, 255)
			}
			
			_data += "&newsContent=" + _editor.getContent();
			_data += "&newsSummary=" + _summary;

			$.post("/brand/news/save",_data,function(data){
				if (data.code == 0) {
					if(data.object)
					{
						$("#refrenceId").val(data.object.refrenceId);
					}
					window.onbeforeunload = null
					remind("success","保存成功！");
				}
				else
				{
					remind("error","保存失败！");
				}
			},"json");
			
		}
		
		function cancelEidt()
		{
			window.onbeforeunload = null
			location.href = "${ctx}/brand/news";
		}

		*/
		
</script>
</body>
</html>