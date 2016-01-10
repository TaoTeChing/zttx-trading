<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心&gt;&gt;优化建议</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/suggest.css" rel="stylesheet" />
    <style type="text/css">
    	.ui-form-explain{float: right;}/***/
    </style>
</head>
<body>
    <div class="container">
    	 <jsp:include page="${ctx}/dealer/mainmenu"/>
		 <div class="em100">
            <div class="main clearfix pr">
                 <jsp:include page="${ctx}/dealer/dealermenu">
                 	<jsp:param name="m" value="10" /> 
                 </jsp:include>
                <div class="main-right">
                    <jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                                <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <span class="bb">优化建议</span>
                                <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb40 clearfix" style="margin-bottom: 0px;">
                        <div class="panel-form">
                        	<form:form id="form_suggestion" action="" method="post" data-widget="validator">
                                <p class="ui-form-item">
                                    <img src="${res}/images/dealer/icon-suggest.png" class="fl" />
                                    <span class="yahei fs16 lh2 block pt20 ml111">在平台使用过程中,有任何问题或更好的建议,都可以在这里提交,8637品牌超级代理会及时查看您提交的建议,并根据其中合理的建议对平台的功能和体验做出改动,让您使用起来更加方便快捷!</span>
                                </p>
                                <p class="ui-form-item">
                                    <textarea name="adviceText" class="area" style="padding: 5px;"  data-display="反馈内容" placeholder="输入您的建议或问题..."></textarea>
                                    <i class="keycount c-h nml np">已经输入0个字</i>
                                </p>
                                <p class="ui-form-item">
                                    <input type="submit" class="ui-button ui-button-lred yahei fs14 " value="提 交" />
                                </p>
                        	</form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
    		/** 初始表单验证*/
        	baseFormValidator({
                    selector:"#form_suggestion",
                    isAjax:true,
                    addElemFn:function(Core,validator){
	                    Core.addItem({
	                        element : '[name=adviceText]',
	                        display : '优化建议',
	                        required : true,
	                        rule : 'maxlength{"max":5120}'
	                    });
                    },
                    beforeSubmitFn:function(){
                        $.post('${ctx}/dealer/advice/save', $('#form_suggestion').serialize(), function(data) {
                            if (data.code == 126000) {
                                remind("success", "感谢您的建议!");
                                $(".keycount").text("已经输入0个字");
                                $('#form_suggestion')[0].reset();
                            } else {
                                remind("error", data.message);
                                // confirmDialog({'title':"操作提示",'content':data.message},function(){},false,function(){},true);
                            }
                        }, "json");
                    }

            });
	    	/** 初始输入事件 */
	    	$('[name=adviceText]').keyup(function() {
	    		$(".keycount").text("已经输入" + $(this).val().length + "个字");
	    	});

    </script>
</body>
</html>