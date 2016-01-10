<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>微店管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
<!--完成-->
    <div class="container">
        <jsp:include page="/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <jsp:include page="/common/menuInfo/sidemenu"/>
                <!--主体内容-->
                <div class="main-right">
                    <form:form id="ui_form" method="get" class="ui-form">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">微店管理</span>
                            <span class="ml20 c-h">订单处理请登录手机约逛app - 微应用 - 店铺助手</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid clearfix">
                        <div class="weshop-box" id="weishop">

                        </div>
                    </div>
                    </form:form>
                </div>
             </div>
        </div>
         <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />

<script id="weishop_template" type="text/html">
{{if rows.length<10}}
<div class="weshop-item weshop-item-new">
    <a href="${ctx }/dealer/weishop?forceOpen=true" class="btn-add-weshop" style="line-height: 14px;">
    	<i class="iconfont" style="font-size:100px;line-height: 130px;">&#xe615;</i><br />
        开通新的微店
    </a>
</div>
{{/if}}
{{each rows}}
	<div class="weshop-item">
     	<div class="shop-title">
     	 	<h3 class="fl">{{$value.wshopName}}</h3>
			<%--<a class="link fr" target="_blank" href="${ctx}/dealer/weishop/managerWeiShop?shopId={{$value.wshopId}}">管理</a>--%>
    	</div>
        <div class="shop-info">
            <span class="fl">绑定约逛号：{{$value.userCode}}</span>
            <span class="fr">手机号：{{$value.userMobile}}</span>
        </div>
        <ul class="shop-data">
            <li>待处理订单：<span class="c-o fs14 bb">{{$value.newOrderCount}}</span> 笔</li>
            <li>所有关注：<span class="c-o fs14 bb">{{$value.followCount}}</span> 人</li>
            <li>累计访问：<span class="c-o fs14 bb">{{$value.visitCount}}</span> 次</li>
            <li>累计咨询：<span class="c-o fs14 bb">{{$value.askCount}}</span> 人</li>
        </ul>
    </div>
{{/each}}
</script>
<script>
$(function(){
    seajs.use(['validator', '$','qrcode',"widget"], function (Validator, $,Qrcode,Widget) {
        Validator.addRule('checkPhone', function(options, commit) {
            var element = options.element,
                    item = Validator.query('#applyFrom').getItem(element);

            item.addClass('ui-form-item-loading');

            $.getJSON('./username.json', function(data) {
                item.removeClass('ui-form-item-loading');
                commit(data.valid, data.message);
                //message: string
                //valid: bool
            });
        });
        Widget.autoRenderAll();
        var qrnode = new Qrcode({
            text: "http://app.8637.com/soft/app/download",
            width:120,
            height:120
        });
        $('.qrcode').append(qrnode);
    });
    
    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
        page = new Pagination({
            url: "${ctx}/dealer/weishop/data",
            method: "post",
            handleData: function (json) {
                var html = template.render("weishop_template", json);
                $("#weishop").empty().append(html);
                //inquiry.init();
            }
        });
    });
});


	
</script>


</body>
</html>
