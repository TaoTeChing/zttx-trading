<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-开通微店</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->

                    <jsp:include page="${ctx}/dealer/dealermenu"  />

                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span class="bb">服务管理</span>
                            <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb40 clearfix">
                        <div class="weshop-apply">
                            <h2>开通我的微店</h2>
                            <div class="hr-dashed">
                            </div>
                            <div class="clearfix apply-box">
                                <div class="apply-flog">
                                    <div class="apply-btn">
                                        <h4>一步开通约逛及微店</h4>
                                        <span  class="lock"></span>
                                        <p>
                                           使用您现在登录的终端商账号开通相同手机号的约逛登录账户，并一步开通激活微店功能，两者密码可相同也可独立自行修改。
                                        </p>
                                    </div>
                                </div>
                                <div class="apply-form">
                                    <form id="applyFrom" class="ui-form" action="" >
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                终端商名:
                                            </label>
                                            <span class="ui-form-text">${dealerInfo.dealerName}</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                终端商登录账号:
                                            </label>
                                            <span class="ui-form-text">${dealerUserm.userMobile}</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                开通的约逛账号:
                                            </label>
                                            <span class="ui-form-text">${dealerUserm.userMobile}</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                约逛账号密码:
                                            </label>
                                            <label class="ui-form-text"><input class="ui-checkbox" type="radio" name="pswdType" checked="checked" value='1'> 使用原密码</label>
                                            <label class="ui-form-text"><input class="ui-checkbox" type="radio" name="pswdType" value='2'> 使用新的密码</label>
                                        </div>
                                        <div id="pswdType" class="hide">
                                            <div class="ui-form-item">
                                                <label class="ui-label">约逛密码:</label>
                                                <input class="ui-input" type="password" id="password1" name="password1" >
                                            </div>
                                            <div class="ui-form-item">
                                                <label class="ui-label">重复密码:</label>
                                                <input class="ui-input" type="password" id="password2" name="password2"><span id='pswdErrorInfo' style="color: red;margin-left: 10px;"></span>
                                            </div>
                                        </div>
                                        <%-- 暂无此功能
                                        <div class="ui-form-item">
                                            <label class="ui-label">约逛服务套餐:</label>
                                            <label class="ui-form-text"><input class="ui-checkbox" type="radio" name="servType" checked="checked"> 基础服务(免费)</label>
                                            <label class="ui-form-text"><input class="ui-checkbox" type="radio" name="servType"> 加强版服务 </label>
                                            <a class="ui-form-text link">查看服务区别</a><span id='typeErrorInfo'></span>
                                        </div>
                                         --%>
                                        <div class="ui-form-item">
                                            <label class="ui-label">
                                                约逛服务时长:
                                            </label>
                                            <span class="ui-form-text">您在8637品牌超级代理的终端服务时间为${fns:getStringTime(dealerBuyService.beginTime,'yyyy-MM-dd')}至${fns:getStringTime(dealerBuyService.endTime,'yyyy-MM-dd')} <br>约逛的服务时间与此相同</span>
                                        </div>
                                        <div class="ui-form-item">
                                            <button class="ui-button ui-button-mred" type="button" id="submitBtn">立即开通</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="hr mt10 mb10">
                            </div>
                            <div class="m020">
                                <a class="ui-button ui-button-lred mr10" href="" target="_blank">前往下载约逛</a>
                                <a class="link" href="" target="_blank">了解约逛</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
           <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>

    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script type="text/javascript">
        var pswdType = $("[name=pswdType]").click(function(){
            $("#pswdType").toggle(pswdType.index(this)==1);
        });
		$('#submitBtn').click(function(){
			var password1=$('#password1').val();
			var password2=$('#password2').val();
			$('#pswdErrorInfo').html('');
			var newpswd=$("input[name=pswdType]:checked").val()==2;
			if(newpswd){
				if(password1==null||password1==''||password1.length<6){
					$('#pswdErrorInfo').html('密码长度不能小于6位');
					return;
				}
				if(password1!=password2){
					$('#pswdErrorInfo').html('两次输入密码不一样');
					return;
				}
			}
			$('#submitBtn').html("处理中...");
			$.post("${ctx }/dealer/weshop/apply1",{newpswd:newpswd,password1:password1,password2:password2},function(data){
				$('#submitBtn').html("立即开通");
				if(data.code==126000){
					remind('success','微店开通成功',function(){
                        window.location.href="${ctx}/dealer/weshop/success?type=1&refrenceId="+data.object.refrenceId;
                    });
				}else{
					remind('error','微店开通失败,请联系客服!');
				}
			},"json");
		});
	</script>
</body>
</html>
