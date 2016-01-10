<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-图片管理-回收站</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/classification.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
	<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
<div class="main_con">
    <!-- 面包导航，并不是每个页面都有 -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandImage/image">图片管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">回收站</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
	 <div class="inner">
        <!-- 内容都放这个地方  -->
        <!-- 图片管理  -->
        <div class="main_classification">
            <div class="ui_tabs">
                <div class="ui_tabsmenu">
                    <ul class="clearfix">
                         <li><a href="${ctx}/brand/brandImage/image">图片管理</a></li>
                        <li><a href="${ctx}/brand/brandImage/image/upload_picture">图片上传</a></li>
                        <li ><a href="${ctx}/brand/brandImgcate/imgcate">分类管理</a></li>
                        <li  class="selected"><a href="${ctx}/brand/brandImage/image/recycle_picture">回收站</a></li>
                    </ul>
                </div>
                <form:form name="imageForm" method="post" action="${ctx}/brand/brandImage/image/recycle_picture">
                 <div class="ui_tabsbox">
                    <!-- 图片管理：回收站 -->
                    <div class="recycle">
                        <div class="recycle-tips" style="display:none;"><em>*</em>回收站将删除图片保持7天，超时系统将自动删除</div>
                        <div class="checkallbox">
                           <div class="inline-block fl">
	                               		 <label>
	                                     <input type="checkbox" class="ui-checkbox chkALL" name="allchecked" >
	                               			     全选
	                               		 </label>
                         	</div>
                            <a class="checkstyle fl" id="batchReturn">批量还原</a>
                            <a class="checkstyle fl clrecy">清空回收站</a>
                            <%--<a class="checkstyle fr">下一页</a>
                            <a class="checkstyle fr">上一页</a>--%>
                        </div>
						<c:choose>
							<c:when test="${!empty pageResult.list}">
								<ul class="contant clearfix">
									<c:forEach var="image" items="${pageResult.list}">
										<li class="contantli">
											<div class="imgbox">
												<a target="_blank" href="${res}${image.domainName}${image.imageName}">
													<c:set value="${image.domainName}${image.imageName}" var="url"></c:set>
													<img src="${res}${fns:getImageDomainPath(url, 175, 175)}" style="width:175px;height:175px;"/>
												</a>
												<p class="title">
													<a target="_blank" href="${res}${image.domainName}${image.imageName}">${fn:substring(image.photoName,0,10)}</a>
												</p>
											</div>
											<div class="fn">
												<div class="inline-block">
													<input type="checkbox" class="ui-checkbox newclasschk" name="piclist" value="${image.refrenceId}">
												</div>
												<span class="bluefont replace" revenId='${image.refrenceId}' >还原</span>
												<span class="bluefont realDelete" revenId='${image.refrenceId}'>永久删除</span>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<div class="ta-c">暂无数据</div>
							</c:otherwise>
						</c:choose>

						<c:if test="${!empty pageResult.list}">
							<div class="pagination">
								<tags:page form="imageForm" page="${pageResult.page}"/>
							</div>
						</c:if>

                    </div>
                </div>
               </form:form>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/classification.js"></script>
<script src="${src}/brand/json2.js"></script>
<script>
		pictureManagement.init();
</script>
<script>
 $(function (){
 		//还原
   		$("span.replace").on('click', function(){
   		   var url="${ctx}/brand/brandImage/image/revert";
   		   var uuid=$(this).attr("revenId");
   		   var jsonStr='["'+uuid+'"]';
 		   var obj={title:"提示",content:"确定还原吗？"};
		     confirmDialog(obj,function(){
		    	 $.ajax({
						type:"post",
						url:url,
						data:jsonStr,
						contentType:"application/json;charset=UTF-8",
						success:function(data){
							if(data!=null){
								remind("success",JSON.parse(data).message);
								window.location.reload();
							}
						}
					});
		     });
		   	}); 
		   	//永久删除
		 $("span.realDelete").on('click', function(){
   		   var url="${ctx}/brand/brandImage/image/realdelete";
   		   var uuid=$(this).attr("revenId");
   		   var jsonStr='["'+uuid+'"]';
   		   var obj={title:"提示",content:"确定永久删除吗？"};
		     confirmDialog(obj,function(){
		    	 $.ajax({
						type:"post",
						url:url,
						data:jsonStr,
						contentType:"application/json;charset=UTF-8",
						success:function(data){
							if(data!=null){
								remind("success",JSON.parse(data).message);
								window.location.reload();
							}
						}
					});
		   	  });
		   	}); 
		   	
			//清空回收站
			$(".clrecy").click(function(){
				     var obj={title:"提示",content:"确定清空吗？"};
				     confirmDialog(obj,function(){
				    	 $.ajax({
								type:"post",
								url:"${ctx}/brand/brandImage/image/clean",
								success:function(data){
									if(data!=null){
										remind("success",JSON.parse(data).message);
										window.location.reload();
									}
								}
							});
				     });
			});
			
			// 批量还原
		   	 var strs="[";
		    $("#batchReturn").click(function(){
		        strs="[";
		        $('[name=piclist]:checkbox:checked').each(function(index){
		            index++;
		            strs+='"'+$(this).val()+'"';
		            if(index!= $('[name=piclist]:checkbox:checked').length){
		                strs+=","
		            }

		        })
		         strs+= "]"
		        //ajax 提交
		        var url="${ctx}/brand/brandImage/image/revert";
		   		   var jsonStr=strs;
			   		if(jsonStr.indexOf("[]")!=-1){
			   			remind("error","请选择要还原的项");
			   			return;
			   		}
			   	  var obj={title:"提示",content:"确定还原吗？"};
				     confirmDialog(obj,function(){
				    	 $.ajax({
								type:"post",
								url:url,
								data:jsonStr,
								contentType:"application/json;charset=UTF-8",
								success:function(data){
									if(data!=null){
										remind("success",JSON.parse(data).message);
										window.location.reload();
									}
								}
							});
				     });
			
		    });
		   	
 	});
 	
</script>
</body>
</html>