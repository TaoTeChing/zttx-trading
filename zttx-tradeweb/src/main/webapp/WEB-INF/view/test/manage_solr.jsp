<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
<title>首页静态内容测试页面</title>
</head>
<body>
		 
		<form:form name="indexForm" id="indexForm"  method="post" action="" >
		
		<b>缓存 读写测试</b>
		<table width="900" border="1" cellpadding="5" cellspacing="0" onDblClick="javascript:indexForm.reset()">
            <tr>
              <td width="10%" align="center">读取</td>
              <td width="20%">key 
              <input name="readKey" type="text" id="readKey"></td>
              <td width="46%">读取到的值
                <input name="readValue" type="text" id="readValue"></td>
              <td width="15%"><input name="btn_read" type="button" id="btn_read" value="读取" onClick="javascript:readData()"></td>
            </tr>
            <tr>
              <td align="center">写入</td>
              <td>key
                <input name="writeKey" type="text" id="writeKey"></td>
              <td>写入的值
                <input name="writeValue" type="text" id="writeValue"></td>
              <td><input name="btn_write" type="button" id="btn_write" value="写入"  onClick="javascript:writeData()"></td>
            </tr>
            <tr>
              <td align="center">删除</td>
              <td colspan="2">key
                <input name="delKey" type="text" id="delKey">              </td>
              <td><input name="btn_del" type="button" id="btn_del" value="删除"  onClick="javascript:delData()"></td>
            </tr>
            
            <tr>
              <td>按指定组</td>
              <td colspan="2">组
                <select name="groupKey" id="groupKey">
                	<option value="Zttx-Web.INDEX_ADVERTS">Zttx-Web.INDEX_ADVERTS 广告位</option>
                	<option value="Zttx-Web.TRADE_MEETING_NEW">Zttx-Web.TRADE_MEETING_NEW 交易会</option>
                	<option value="Zttx-Web.WEBDEF_TEMPLATE">Zttx-Web.WEBDEF_TEMPLATE</option>
                	<option value="Zttx-Web.INDEX_BRANDS">Zttx-Web.INDEX_BRANDS 品牌(下属产品)</option>
                	<option value="Zttx-Web.INDEX_MEET">Zttx-Web.INDEX_MEET 发布会 </option>
                	<option value="Zttx-Web.APPLY_QUESTION">Zttx-Web.APPLY_QUESTION 加盟入驻 常见问题 </option>
                	<option value="Zttx-Web.RULEINFO_NEW">Zttx-Web.RULEINFO_NEW 规则 </option>
                	<option value="Zttx-Web.RULEINFO_CATE">Zttx-Web.RULEINFO_CATE 规则分类 </option>
                	<option value="Zttx-Web.HELP_CATE">Zttx-Web.HELP_CATE 帮助分类 </option>
                	<option value="Zttx-Web.HELP_INFO">Zttx-Web.HELP_INFO 帮助 </option>
                	<option value="Zttx-Web.SEARCH_HOTLIST">Zttx-Web.SEARCH_HOTLIST 搜索关键字 </option>    
                	<option value="Zttx-Web.SEARCH_INFO_ALL">Zttx-Web.INFO_ALL 行业资讯信息</option> 
                	<option value="Zttx-Web.INFO_CATE">Zttx-Web.INFO_CATE 行业资讯类别 </option>  
                	<option value="Zttx-Web.BRAND_INFO">Zttx-Web.BRAND_INFO 品牌缓存</option>  
                	<option value="Zttx-Web.PRODUCT_INFO">Zttx-Web.PRODUCT_INFO 产品缓存</option>               	
                	<option value="Zttx-Web.BRANDS_DOMAIN">Zttx-Web.BRANDS_DOMAIN 品牌二级域名</option>               	
                </select>			  </td>              
              <td><input name="btn_del" type="button" id="btn_del" value="删除"  onClick="javascript:delByGroup()"></td>
            </tr>
		</table>

		<br>
		<b>Solr 索引管理  </b>
		<table width="900" border="1" cellpadding="5" cellspacing="0">
            <tr>
              <td width="10%" align="center">生成</td>
              <td colspan="2">目标
                <select name="core1" id="core1">
                  <option value="0">品牌</option>
                  <option value="1">产品</option>
                  <option value="2">资讯</option>
                  <option value="5">规则/帮助</option>
              </select></td>
              <td width="15%"><input type="button" name="Submit" value="全量生成" onClick="javascript:rebuildAll()"></td>
            </tr>
            <tr>
              <td rowspan="2" align="center">删除</td>
              <td width="20%"><label>目标
                <select name="core2" id="core2">
                      <option value="0">品牌</option>
                      <option value="1">产品</option>
                      <option value="2">资讯</option>
                      <option value="5">规则/帮助</option>
              </select>
                </label>
              <label> </label></td>
              <td><input name="docId" type="text" id="docId" size="42">
              待删除索引Id </td>
              <td><input name="btnDelById" type="button" id="btnDelById" value="根据ID删除" onClick="javascript:delIndexById()"></td>
            </tr>
            <tr>
              <td>目标
                <select name="core3" id="core3">
                    <option value="0">品牌</option>
                    <option value="1">产品</option>
                    <option value="2">资讯</option>
                    <option value="5">规则/帮助</option>
                </select></td>
              <td><p>
                  <textarea name="query" cols="40" rows="4" id="query"></textarea>
              指定查询范围删除(慎用,严禁*通配)</p>
              </td>
              <td><input name="btnDelByquery" type="button" id="btnDelByquery" value="根据查询删除" onClick="javascript:delIndexByQuery()"></td>
            </tr>
            <tr>
              <td align="center">清空</td>
              <td><label>目标
                  <select name="core4" id="core4">
                    <option value="0">品牌</option>
                    <option value="1">产品</option>
                    <option value="2">资讯</option>
                    <option value="5">规则/帮助</option>
                  </select>
              </label></td>
              <td>&nbsp;</td>
              <td><input name="btnDeleteAll" type="button" id="btnDeleteAll" value="清空目标内所有索引" onClick="javascript:delIndexByCore()"></td>
            </tr>
          </table>
          
		</form:form>
		
<script src="${res}/scripts/brand/uglify.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
		<script type="text/javascript"	>
			function rebuildAll(){
				var core = $("#CORE1").val();
				 
				if( core ==""){
					remind("error", "请指定目标");
					return;
				} 
				$.post("/temp/manage/rebuildIndex", $("#indexForm").serialize(), function(data){
					if(data.code == 126000)
					{
						remind("success", "索引重建成功！");
						$("#result").html(data.message);
					}
					else
					{
						remind("error", data.message);
					}
				}, "json");
			}
			
			
			function delIndexById(){
				var core = $.trim($("#CORE2").val());
				var docId = $.trim($("#indexForm #docId").val());
				 
				
				if(core=="" || docId==""){
					remind("error", "请指定目标和id");
					return;
				} 
				$.post("/temp/manage/delIndexById", $("#indexForm").serialize(), function(data){
					if(data.code == 126000)
					{
						remind("success", "索引删除成功！");
						$("#result").html(data.message);
					}
					else
					{
						remind("error", "索引删除失败！");	
						$("#result").html(data.message);
					}
				}, "json");
			}
			
			
			function delIndexByQuery(){
				var core = $.trim($("#CORE3").val());
				var query = $.trim($("#indexForm #query").val());
				 
				
				if(core=="" || query==""){
					remind("error", "请指定目标和query语句");
					return;
				} 
				$.post("/temp/manage/delIndexByQuery", $("#indexForm").serialize(), function(data){
					if(data.code == 126000)
					{
						remind("success", "索引删除成功！");						
					}
					else
					{
						remind("error", "索引删除失败！");
						$("#result").html(data.message);
					}
				}, "json");
			}
			
			
			function delIndexByCore(){
				var core = $.trim($("#CORE4").val());
				
				if(core == ""){
					remind("error", "请指定目标和id");
					return;
				} 
				if (confirm("确定要清空所选core的所有索引?")){
					$.post("/temp/manage/delIndexByCore", $("#indexForm").serialize(), function(data){
						if(data.code == 126000)
						{
							remind("success", "索引删除成功！");
							
						}
						else
						{
							remind("error", "索引删除失败！");
							$("#result").html(data.message);
						}
					}, "json");
				}
			}
			
			
			function delByGroup(){
				var groupKey = $.trim($("#groupKey").val());
				
				if(groupKey == ""){
					remind("error", "请指定组");
					return;
				}
				if (confirm("确定要清空所选组的所有缓存?")){
					$.post("/temp/manage/delCacheByGroupKey", $("#indexForm").serialize(), function(data){
						if(data  == "true")
						{
							remind("success", "缓存删除成功！");
						}
						else
						{
							remind("error", "缓存删除失败！");
							$("#result").html(data.message);
						}
					}, "json");
				}
			}
			
			function readData(){
				var readKey = $.trim($("#readKey").val());
				
				if(readKey == ""){
					remind("error", "key必须填写");
					return;
				} 
				$.post("/temp/manage/readKey", $("#indexForm").serialize(), function(data){
					$("#readValue").val(data);
				}, "html");
			}
			
			function writeData(){
				var writeKey = $.trim($("#writeKey").val());
				var writeValue= $.trim($("#writeValue").val());
				
				if(writeKey == "" || writeValue ==""){
					remind("error", "key 和 value 必须填写");
					return;
				} 
				$.post("/temp/manage/writeKey", $("#indexForm").serialize(), function(data){
					if(data  == "true")
					{
						remind("success", "写入缓存成功！");												
					}
					else
					{
						remind("error", "写入缓存失败！");
					}
				}, "html");
			}
			
			
			function delData(){
				var delKey = $.trim($("#delKey").val());
				
				if(delKey == "" || delKey ==""){
					remind("error", "key  必须填写");
					return;
				} 
				$.post("/temp/manage/delKey", $("#indexForm").serialize(), function(data){
					if(data  == "true")
					{
						remind("success", "删除缓存成功！");												
					}
					else
					{
						remind("error", "删除缓存失败！");
					}
				}, "html");
			}
			
		</script>
		
		执行结果 log<p>
		<div id="result"></div>
</body>
</html>