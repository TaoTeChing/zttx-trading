<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>8637品牌超级代理-店铺装修-背景设置</title>
<meta name="keywords" content="8637品牌超级代理-店铺装修">
<meta name="description" content="8637品牌超级代理-店铺装修">
<link href="${res}/styles/market/kuang.css" rel="stylesheet" type="text/css" />
<link href="${res}/styles/market/btnico.css" rel="stylesheet" type="text/css" />
<link href="${res}/styles/market/stylemanage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/view/market/brand_edit_k_top.jsp" />
<div class="k-body">
    <div class="k-left f-l">
    		<h1 class="k-title">样式管理</h1>
            <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
            <ul class="k-leftul">
                <li class="selected"><a href="背景设置(下级页面).html" title="" target="">背景设置</a></li>
                <li><a href="选择配套颜色.html" title="" target="">选择配套颜色</a></li>
            </ul>
    </div>
    <div class="k-right f-l">
    	<div class="shousuo"></div>
    	<div class="k-method"><span>确认发布</span></div>
        <div class="k-editall">
        	<div class="k-editpages">
            	<form>
                    <ul>
                    	<li>请选择要设置的页面：</li>
                        <li><select class="k-edisel">
                            <option>所有页面</option>
                            <option>首页</option>
                            <option>其他页面</option>
                        </select></li>
                        <li><input type="button" value="重置" class="btn btn-small k-editbtn" /></li>
                    </ul>
                </form>
            </div>
            <div class="k-editbgcolor clear">
                <p class="pleasec"><span class="f-l">请选择背景颜色：</span><span class="colorbox f-l" style=" display:block; background:#b01000 url(${ctx}/images/fronts/market/img/cxcolor.png); width:18px; height:18px"></span></p>
                <p id="colorpickerHolder" style=" display:none"></p>
            </div>
            <div class="k-editbgpic clear">
                <form>
                <span>页面背景图：</span>
					<input type="file" size="60" id="inputBkgUrl" />
					<select id="inputRepeatMode"><option value="repeat" selected="selected">重复</option><option value="no-repeat">不重复</option><option value="repeat-x">横向重复</option><option value="repeat-y">纵向重复</option></select>
                    <input type="button" onclick="doChangeBkg()" value="确定更换" class="btn btn-small k-editbtn" />
 				 </form>
                 <!--<div>显示</div><div>不显示</div>-->
            </div>
            <div class="k-sub"><div class="btn btn-small btn-inverse k-cath f-l">保存</div><div class="btn btn-small btn-inverse f-l">取消</div></div>
        </div>
        <div class="k-sliupdown"><div class="k-sliup">收起</div><div class="k-slidown">展开</div></div>
    	<iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="22222.html" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
<div class="k-footer">
	<p>copyright&copy;2014 浙江天下商邦科技股份有限公司 版权所有</p>
</div>
<script src="${res}/scripts/jquery-1.11.0.min.js"></script>
<script src="${res}/scripts/common/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>
<script src="${res}/scripts/market/colorpicker.js" type="text/javascript"></script><!--颜色获取-->
<script>
$(function(){
	$('#colorpickerHolder').ColorPicker({
		flat: true,
		onSubmit: function(hsb, hex, rgb) {
			$("#testIframe",parent.document.body).css('backgroundColor', '#' + hex);
		},
		onChange: function (hsb, hex, rgb) {
			$('.colorbox').css('backgroundColor', '#' + hex);
		}
	});
	$('.colorbox').click(function(){
		$('#colorpickerHolder').fadeToggle();
		return false;
	});
	$('#colorpickerHolder').click(function(){
		return false;
	});
	$(document).click(function(){
		$('#colorpickerHolder').fadeOut();
	});
	$('.k-sliup').click(function(){
		$('.k-editall').slideUp();
		$(this).hide();
		$('.k-slidown').show();
	});
	$('.k-slidown').click(function(){
		$('.k-editall').slideDown();
		$(this).hide();
		$('.k-sliup').show();
	});
});
</script>
<script src="${res}/scripts/market/kuang.js" type="text/javascript"></script>
</body>
</html>