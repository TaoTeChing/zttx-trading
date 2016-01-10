<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页静态内容测试页面</title>
</head>
<body>
		<h3>静态内容添加</h3>
		<form:form action="${ctx }/save" method="post">
			文字广告:<input value="1" name="modelName" type="radio">
			图片广告:<input value="2" name="modelName" type="radio"><br/>
			模块的key:<input name="modelKey" /><br/>
			简介：<input name="subDesc" /><br/>
			内容：<textarea rows="5" cols="20" name="htmlText"></textarea><br/>
			<input type="submit" value="提交">
		</form:form>
		
		
		
		<h3>广告位添加</h3>
		<form:form action="${ctx }/saveAd" method="post">
			图片广告:<input value="1" name="advertCate" type="radio">
			标题广告:<input value="2" name="advertCate" type="radio"><br/>
			广告位名称:<input name="advertName" /><br/>
			key:<input name="advertKey" /><br/>
			广告描述：<input name="advertDesc" /><br/>
			显示数量：<input name="viewNum" /><br/>
			图片宽：<input name="imgWidth" /><br/>
			图片高：<input name="imgHeight" /><br/>
			<input type="submit" value="提交">
		</form:form>
		
		
		<h3>广告添加</h3>
		<form:form action="${ctx }/saveAdd" method="post">
			广告位编号:<input name="advertId" /><br/>
			广告权重：<input name="imgWeight" /><br/>
			广告标题：<input name="adTitle" /><br/>
			key:<input name="advertKey" /><br/>
			链接地址：<input name="urlAddress" /><br/>
			广告图片：<input name="adLogo" /><br/>
			图片/附档的alt描述：<input name="altMark" /><br/>
			广告开始时间：<input name="beginTime" /><br/>
			广告结束时间：<input name="endTime" /><br/>
			排序：<input name="orderId" /><br/>
			<input type="submit" value="提交">
		</form:form>
		
		
</div>




</body>
</html>