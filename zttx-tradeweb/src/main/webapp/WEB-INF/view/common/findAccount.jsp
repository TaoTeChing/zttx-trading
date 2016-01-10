﻿<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>忘记用户名</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <%--<!--样式-->--%>
    <link href="${res }/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
    <%--<link href="${res }/styles/common/validformStyle.css" rel="stylesheet" />--%>
    <%--<style>
        .showUpload .ui-form-item{padding: 0 5px 20px 100px;}
    </style>--%>
</head>
<body>
    <div class="container findaccount">
  		<jsp:include page="login/view_top.jsp"/>
        <div class="main">
            <div class="box">
                <div class="head">
                    <h2 class="c-r-on">忘记用户名</h2>
                </div>
                <div class="main-left">
                    <div class="content">
                        <form:form id="findAccountForm" class="ui-form findAccountForm" action="${ctx}/common/forgotpass/findAccountSave" method="post">
                            <div class="brforeShowUpload">
                                <dl class="ui-form-item">
                                    <dt>
                                    账户类型</dt>
                                    <dd>
                                        <select id="clientType" name="uesrType" class="chosen-select" style="margin-top: 0;">
                                            <option value="0">
                                                	品牌商
                                            </option>
                                            <option value="1">
                                                	终端商
                                            </option>
                                        </select>
                                    </dd>
                                </dl>
                                <dl class="ui-form-item">
                                    <dt>
                                        法人代表</dt>
                                    <dd>
                                        <input name="realName" type="text" class="txt long" datatype="s2-15" errormsg="输入的字符长度不匹配" autocomplete="off" /></dd>
                                </dl>
                                <dl class="ui-form-item">
                                    <dt>
                                        证件类型</dt>
                                    <dd>
                                        <select id="certificateType" name="certType" class="chosen-select" style="width: 150px;margin-top: 0;">
                                            	<option value="1">
                                                	法人身份证号
                                            	</option>
                                            	<option value="2">
                                                	营业执照编号
                                           		</option>
                                        </select>
                                    </dd>
                                </dl>
                                <dl class="ui-form-item">
                                    <dt>
                                        证件号</dt>
                                    <dd>
                                        <input name="certNo" type="text" class="txt long" datatype="*" autocomplete="off" /></dd>
                                </dl>
                                <dl class="ui-form-item">
                                    <dt>
                                        新手机号</dt>
                                    <dd>
                                        <input type="text" name="userMobile" value="" id="userMobile" class="txt long" datatype="m" errormsg="手机号码格式不正确" ajaxurl="${ctx}/common/findMobile?uesrType=0" autocomplete="off"/>
                                        <span id="mobileInfo"></span>
                                    </dd>
                                    <span class="ui-form-text">新手机作为联系方式</span>
                                </dl>

                            <%--</div>
                            &lt;%&ndash; 下面为合并过来的 &ndash;%&gt;
                            <div class="showUpload">--%>
                                <dl class="ui-form-item">
                                    <div class="block">
                                        <div class="img-upload">
                                            <div class="file_wrap img fl" style="display: none;">
                                                <img src="" style="width:112px;height:112px;margin: 5px;" alt="">
                                                <i href="javascript:;" class="iconfont close" title="删除此图片才能重新传图">&#xe612;</i>
                                            </div><div class="file_wrap addimg fl">
                                                <i class="icon-fileupload"></i>
                                                <input class="input_file" type="file"  name="photo" id="photo">
                                            </div>
                                            <div class="fl" style="display: inline-block; *display:inline;*zoom:1;height: 26px;margin-top: 53px;">
                                                <input type="hidden" name="certPhoto" datatype="*" nullmsg="证件照不能为空" value="" autocomplete="off">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt10">请上传您注册时使用的法人代表身份证件</div>
                                </dl>
                                <dl class="ui-form-item">
                                    <button class="btn" id="mySubmit" type="button">提 交</button>
                                </dl>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="main-right">
                    <%--<div class="head">
                        <h2>
                            安全小贴士
                        </h2>
                        <div class="hr-dashed"></div>
                    </div>--%>
                    <div class="content">
                        <p>
                            <img src="${res }/images/common/findaccount-right.gif">
                        </p>
                        <div class="hr-dashed mt10 mb10"></div>
                        <a class="btn" href="${ctx }/common/register" target="_blank">注册</a>
                        <br>
                        <a class="btn" href="${ctx }/common/login" target="_blank">登录</a>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="login/view_foot.jsp"/>
    </div>
    <script src="${res }/scripts/jquery.min.js"></script>
    <script src="${res }/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res }/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res }/scripts/seajs_config.js"></script>
    <script src="${res }/scripts/common.js"></script>
     <tags:message content="${message}" width="300"></tags:message>
    <script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        $(function(){

             $("#mySubmit").click(function(){
            	
            	 var check = /^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}[x,X])?$/;
                 //var checkperson = /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{2,15}$/;
                 var mobilcode = /^(1[3-8]\d{9})$/;
                 var business = /^\d{15}$/;
                 var userMobile = $("[name=userMobile]").val();
                 var certNo = $("[name=certNo]").val();
                 var realName = $("[name=realName]").val();
                 var certPhoto = $("[name=certPhoto]").val();
                 var photo = $("[name=photo]").val();
                 if(realName==''||realName.length<2||realName.length>15){
                     remind("error","法人代表或姓名格式不正确，请输入2-15个字符串");
                     return;
                 }
                 if((!check.test(certNo)&&!business.test(certNo))||certNo==''){
                     remind("error","身份证或营业执照格式不正确");
                     return;
                 }
                 if(!mobilcode.test(userMobile)||userMobile==''){
                     remind("error","手机格式不正确");
                     return;
                 }
                 if(certPhoto==''){
                     remind("error","上传文件不能为空");
                     return;
                 }
                $("#findAccountForm")[0].submit();
            });

            var items =$("dl.ui-form-item"); //$("dt.ui-label");
            $("#clientType").change(function(){
                var clientType =  $(this).val();

                if(clientType=="1")
                {
                    $(items[1]).find("dt.ui-label").html("真实姓名：");
                    $("#certificateType").html("<option value='1'>法人身份证号</option>");
                    $(items[3]).find("dt.ui-label").html("法人代表身份证号:");
                    $(items[6]).find("div:first").html("请上传您注册时使用的法人代表身份证件");
                    //$(items[2]).hide();
                    //$(items[3]).find("dt.ui-label").html("营业执照编号:");
                }
                else{
                    $(items[1]).find("dt.ui-label").html("法人代表：");
                    $("#certificateType").html("<option value='1'>法人身份证号</option> <option value='2'>营业执照编号</option>");
                   // $(items[2]).show();
                    //$(items[3]).find("dt.ui-label").html("法人代表身份证号:");
                }
            }).trigger("change");
			
			$('#userMobile').attr("ajaxurl","${ctx}/common/findMobile?uesrType=" + $.trim($('#clientType').val()) );
			
			
			
			$('#clientType').on('change', function (){
				var val = $.trim($(this).val());
				$('#userMobile').attr("ajaxurl","${ctx}/common/findMobile?uesrType=" + val);
			});
			
			
			/* $('input[name="userMobile"]').on('blur', function (){
				$.ajax({
					  type : 'post',
					  url: "${ctx}/common/findMobile",
					  cache: false,
					  dataType: "json",
					  data : {
					  	uesrType : $.trim($('#clientType').val()),
					  	userMobile : $.trim($('input[name="userMobile"]').val()),
					  },
					  success: function(data){
					   	if(data.code == zttx.SUCCESS){
					   		$('#mobileInfo').removeClass().addClass('c-g').text("该号码可以使用");
					   	}else{
												   	
					   		$('#mobileInfo').removeClass().addClass('c-r').text(data.message);
					   	}
					  }
				});
			});
			 */
			
			$("#photo").on('change', function (){
				uploadImage("photo");
			});
			

			function uploadImage(uploadId){
    			seajs.use(["$","ajaxFileUpload"],function($){
	    			$.ajaxFileUpload({
	        			url: '${ctx}/common/showImg?fSize=2',
	        			secureuri: false,
	                    fileElementId: uploadId,
	                    dataType: 'json',
	                    success: function(data)
	                    {
	                    	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
	                     	$('#' + uploadId).bind('change', function(){
	                			uploadImage(uploadId);
	                		});
	                    	if(data.code == zttx.SUCCESS)
	                    	{
	                    		$('.img img').attr('src', '${res}' + data.message);
	                    		$('input[name="certPhoto"]').val(data.message);
                                $('.img').show();
                                $(".addimg").hide();
	                    	}
	                    	else
	                    	{
	                    		remind("error",data.message);
	                    	}
	                    }
	        		});
    	    });
    	}
            $(".img-upload .img").hover(function(){
                $(".close").show();
            },function(){
                $(".close").hide();
            });
            $(".img-upload .img").on("click",".close",function(){
                $("input[name=certPhoto]").val("");//清空验证图片是否存在的隐藏域value值
                $('.img').hide();
                $(".addimg").show();
            });


            $("#certificateType").change(function(){
                var certificateType =  $(this).val();
                switch (certificateType)
                {
                    case "2":
                        $(items[3]).find("dt.ui-label").html("营业执照编号：");
                        $(items[6]).find("div:first").html("请上传您注册时使用的营业执照");

                        break;
                    case "3":
                        $(items[3]).find("dt.ui-label").html("其他证件号码：");
                        $(items[6]).find("div:first").html("请上传您注册时使用的其他证件");
                        /**请上传您注册时使用的营业执照:*/
                        break;
                    default:
                        $(items[3]).find("dt.ui-label").html("法人代表身份证号：");
                        $(items[6]).find("div:first").html("请上传您注册时使用的法人代表身份证件");
                        break;
                }
            }).trigger("change");


			/*$('.findAccountForm').Validform({
				tiptype :3
			}) .addRule({
				ele : '#userMobile',
				ajaxurl : $('#userMobile').attr("ajaxurl"),
				callback : function (data){
					alert(data);
				}
			
			}); */

            //此方法在common.js用法可以参考一下,用来验证的
            /*baseFormValidator({
                selector: ".findAccountForm",
                isAjax: true,
                beforeSubmitFn: function(){
                    *//*校验图片的方法*//*
                    var imgs = $('.file_wrap img');//这里放你想要验证的图片
                    if(imgs.size()!=2){

                    }else{
                        $('.imgUploadTip').hide();
                        //图片验证通过，可以在后面加入ajax提交

                    }
                }
            });*/
        });
    </script>
</body>
</html>
