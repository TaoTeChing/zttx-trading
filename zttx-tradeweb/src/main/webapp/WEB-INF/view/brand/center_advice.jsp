<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-优化建议</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/suggestion.css" />
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
                    <span class="current">优化建议</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
				<div class="main_suggestion">
					<form:form action="${ctx}/brand/brandAdvice/submit" id="advice_suggestion_form">
						<p class="">
							<img src="${res}/images/brand/icon-suggest.png" class="suggestimg fl" /> <span class="suggestintr fr">在平台使用过程中,有任何问题或更好的建议,都可以在这里提交,8637品牌超级代理会及时查看您的提交的建议,并根据其中合理的建议对平台的功能和体验做出改动,让您使用起来更加方便快捷!</span>
						</p>
						<p>
							<textarea class="textareabox" name="adviceText" data-deftxt="输入您的建议或问题..."></textarea>
							<br />
						</p>
						<p>
							<input type="button" class="ui_button ui_button_lblue fr suggestsub" value="提 交" />
						</p>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>
	<script>
		var advice = {};
		advice.init = function() {
			$('[data-deftxt]').css({
				color : '#bbb'
			}).val($('[data-deftxt]').data("deftxt"));
			$('[data-deftxt]').css({
				color : '#bbb'
			}).focus(function() {
				if ($(this).val() == $(this).data("deftxt")) {
					$(this).val("").css({
						color : '#666'
					});
				}
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val($(this).data("deftxt")).css({
						color : '#bbb'
					});
				}
			});
		};

		/**
		 * 建议提交到后台
		 */
		advice.submit = function() {
			var url = $('#advice_suggestion_form').attr("action");
			var adviceText = $('#advice_suggestion_form textarea').val();
			if (adviceText == "" || adviceText == "输入您的建议或问题...") {
				remindfn('error',"请您填写建议!",3000);
				return;
			}
			$.post(url, $('#advice_suggestion_form').serialize(), function(data) {
				if (data.code == 126000) {
					$('[data-deftxt]').val($('[data-deftxt]').data("deftxt"))
							.css({
								color : '#bbb'
							});
					remindfn('success',"感谢您的建议!",3000);
				} else {
					remindfn('error',"提交建议失败!",3000);
				}
			}, "json");
		};

		/**
		 * 页面载入时 初始
		 */
		$(function() {
			advice.init();
			$('div[class=main_suggestion] input[type=button]').click(
					advice.submit);
			$('[data-deftxt]').val($('[data-deftxt]').data("deftxt")).css({
				color : '#bbb'
			});
		});
	</script>
</body>
</html>