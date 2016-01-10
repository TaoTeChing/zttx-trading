<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css"/>
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
                <span class="current">品牌资料完善</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <form:form id="js-form" class="ui-form addBrand-form" data-widget="validator" action="${ctx}/brand/brandRecruit/save" method="post">
                <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
        			<jsp:param value="2" name="m"/>
        		</jsp:include>
                <div class="tab_con_box">
                    <div class="tips">
                        <i class="v2-icon-explain"></i>
                        说明：根据要求填写品牌招募书，招募终端商用户，扩建渠道
                    </div>
                    <!-- 品牌招募书开始 -->
                    <div class="tab_con" style="display: block;">
                        <div class="ui-form-item">
                            <label class="ui-label" for="title">
                                招募书标题：<span class="ui-form-required">*</span>
                            </label>
                            <input id="recruitTitle" name="recruitTitle" style="width: 464px;" type="text" class="ui-input" data-display="招募书标题" required minlength="2" maxlength="64" value="${recruit.recruitTitle}">
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="deal-brand">
                                经营品牌：<span class="ui-form-required">*</span>
                            </label>

                            <div class="inline-block">
                                <select class="ui-select js_select" name="dealBrand" id="dealBrand" required>
                                    <c:forEach items="${dealBrand}" var="brand">
                                        <option value="${brand.dictValue}" <c:if test="${recruit.dealBrand == brand.dictValue}">selected</c:if>>${brand.dictValueName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="deal-exp">
                                开店经验：<span class="ui-form-required">*</span>
                            </label>

                            <div class="inline-block">
                                <select style="width: 80px" class="ui-select js_select" name="dealExper" id="dealExper" required>
                                    <c:forEach items="${dealExper}" var="exper">
                                        <option value="${exper.dictValue}" <c:if test="${recruit.dealExper == exper.dictValue}">selected</c:if>>${exper.dictValueName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="deal-shop">
                                有无实体店：<span class="ui-form-required">*</span>
                            </label>

                            <div class="inline-block">
                                <select style="width: 80px" class="ui-select js_select" name="dealShop" id="dealShop" required>
                                    <c:forEach items="${dealShop}" var="shop">
                                        <option value="${shop.dictValue}" <c:if test="${recruit.dealShop == shop.dictValue}">selected</c:if>>${shop.dictValueName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="deal-year">
                                经营年限：<span class="ui-form-required">*</span>
                            </label>

                            <div class="inline-block">
                                <select style="width: 80px"class="ui-select js_select" name="dealYear" id="dealYear" required>
                                    <c:forEach items="${dealYear}" var="option">
                                        <option value="${option.dictValue}" <c:if test="${recruit.dealYear == option.dictValue}">selected</c:if>>${option.dictValueName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                招募书状态：<span class="ui-form-required">*</span>
                            </label>

                            <div class="radio_box inline-block">
                                <input value="1" <c:if test="${empty recruit.recruitState || recruit.recruitState == 1}">checked</c:if> class="ui-radio" name="recruitState" type="radio"/>开启招募
                                <input value="0" <c:if test="${recruit.recruitState == 0}">checked</c:if> class="ui-radio ml10" name="recruitState" type="radio" required id="recruitState"/>关闭招募（停止招募）
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                招募内容：<span class="ui-form-required">*</span>
                            </label>

                            <div class="inline-block">
                            	<textarea id="myEditor3" name="recruitText" data-display="招募内容" required minlength="2">${recruit.recruitText}</textarea>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <button type="submit" class="ui_button ui_button_morange" id='btn-submit'>保存修改</button>
                        </div>

                    </div>
                    <!-- 品牌招募书结束 -->
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${src}/brand/brand_manage.js"></script>
<script type="text/javascript">
    	data_perfect.init();
        seajs.use(['$','widget','validator'], function ($, Widget,Validator) {
            Widget.autoRenderAll();
	        Validator.query('#js-form').set("autoSubmit",false);
			Validator.query('#js-form').on('formValidated', function(error) {
				if(error){
					return;
				}
				$("#btn-submit").attr("disabled", true);
				$.ajax({
		 		  url: '/brand/brandRecruit/save',
		          type: 'post',
		          data: $("#js-form").serialize(),
		          dataType: 'json',
		          success: function(data, textStatus)
		          {
                      $("#btn-submit").attr("disabled", false);
		          	if(zttx.SUCCESS==data.code){
						remind("success","品牌招募书保存成功");
					}else if(zttx.VALIDERR==data.code)
					{
						setErrMsg("#js-form",data.rows);
					}
					else{
						remind("error",data.message);
					}
		          },
		  		  error: function(data)
		          {
                    $("#btn-submit").attr("disabled", false);
		  			remind("error",data.message);
		          }
		    	});

			});
			
			//选择品牌更新内容
			$('#brandsId').change(function (e) {
	            var id = $(this).val();
	            window.location.href = "${ctx}/brand/brandRecruit/execute?id=" + id;
       		 });
        });

</script>
</body>
</html>