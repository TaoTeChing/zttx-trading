<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-产品管理-分类管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
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
            <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">分类管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="inner" style="padding-top:1px;">
                <div class="tips">
                    <i class="v2-icon-explain"></i>
                    说明：您可根据产品材质、用途等信息设置不同的分类
                </div>
                <!-- 选择品牌 -->
                <form:form action="" method="post" id="catelogForm" name="catelogForm">
                <%--<input type="hidden" name="menuId" value="${menuId}"/>
				<input type="hidden" name="subMenuId" value="${subMenuId}"/>
                --%><input type="hidden" id="imgRefrenceId"/>
                <div class="selectbar">
                    <span>选择品牌:</span>
                    <div class="inline-block">
                        <select class="js_select" name="brandsId" id="brandsId" onchange="$('#catelogForm').submit();">
                        <c:forEach items="${brandesInfoList}" var="info">
                        	<option value="${info.refrenceId}" <c:if test="${info.refrenceId==brandsId}">selected</c:if>>${info.brandsName}</option>
                        </c:forEach>
                        </select>
                    </div>
                </div>
                <!-- tabs切换 -->
                <div class="jstabs_t">
                    <div class="jstabsmenu_t clearfix">
                        <ul class="clearfix">
                            <li class="selected"><a href="${ctx}/common/productCatalog?menuId=<%=request.getParameter("menuId")%>&subMenuId=<%=request.getParameter("subMenuId")%>">分类设置</a></li>
                            <li><a href="${ctx}/common/productCatalog/list?menuId=<%=request.getParameter("menuId")%>&subMenuId=<%=request.getParameter("subMenuId")%>">产品分类</a></li>
                        </ul>
                    </div>
                    
                    <div class="tabsbox_t">
                        <div class="productsmanage">
                            <div class="checkdel">
                                <div class="checkgroup">
                                    <label class="">
                                    <input type="checkbox" class="ui-checkbox chkALL">
                                    全选</label>
                                    <span class="delallspan setdelall"><i class="delall"></i>批量删除</span>
                                </div>
                            </div>
                            <div class="Thead">
                                <table class="table">
                                <colgroup>
                                    <col width="55" />
                                    <col width="50" />
                                    <col width="350" />
                                    <col width="150" />
                                    <col width="150" />
                                    <col width=100" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>分类名称</th>
                                    <th>分类图片</th>
                                    <th>移动</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                </table>
                            </div>
                            <div class="newclass" style="display: inline-table"><i class="iconfont">&#xe611;</i><span class="newclasspan">新建分类</span></div>
                            <table class="table tablearrow">
                                <colgroup>
                                    <col width="35" />
                                    <col width="35" />
                                    <col width="350" />
                                    <col width="150" />
                                    <col width="150" />
                                    <col width=100" />
                                </colgroup>
                                <c:forEach items="${catalogList}" var="catalog">
                                <tbody class="tbody">
                                <tr class="maintree" id="tr${catalog.id}">
                                	<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value="${catalog.id}"/>
                                	<input type="hidden" name="cateIconAry" id="cateIconAry" value="${catalog.img}"/>
                                	<input type="hidden" name="cateLevelAry" value="1"/>
                                    <td class="td_0">
                                        <input type="checkbox" class="ui-checkbox newclasschk mainclasschk" name="chkRefrenceId"  value="${catalog.id}"/>
                                    </td>
                                    <td class="td_1">
                                        <i id="trplus" class="main_iconskai"></i>
                                    </td>
                                    <td class="td_2">
                                        <input type="text" class="newclasstext" name="cateNameAry" value="${catalog.name}" />
                                    </td>
                                    <c:choose>
                                    	<c:when test="${empty catalog.img}">
                                    	<td class="td_3">
                                        <span class="addsubclass" ><i class="iconfont">&#xe615;</i>添加图片</span>
                                    	</td>
                                    	</c:when>
                                    	<c:otherwise>
                                    	<td class="td_3">
                                    	<span class="haspic"><a href="javascript:;" class="haspic-edit">编辑</a> <a href="javascript:;" class="haspic-del">删除</a></span>
                                        <div class="piclook" style="position: relative;display: none">
                                            <div class="piclookbox js-img-center" style="background: #fff; position: absolute; width: 170px; height: 170px;z-index: 9; border: 1px solid #d9d9d9; top:0;">
											<c:set value="${catalog.domainName}${catalog.img}" var="url"></c:set>
                                            <%--<img src="${res}${fns:getImageDomainPath(url, 170, 170)}" alt=""/>
                                            --%></div>
                                        </div>
                                    	</td>
                                    	</c:otherwise>
                                    </c:choose>
                                    <td class="td_4">
                                        <div class="movebox">
                                            <span class="movetop mainmovetop" title="顶部"></span><span class="moveup mainmoveup" title="上移" data="${catalog.id}"></span><span class="movedown mainmovedown" title="下移" data="${catalog.id}"></span><span class="movebottom mainmovebottom" title="底部"></span>
                                        </div>
                                    </td>
                                    <td class="td_5"><span class="delete" data="${catalog.id}">删除</span><span class="look">查看</span></td>
                                </tr>
                                <c:forEach items="${catalog.children}" var="sunCatalog" varStatus="status">
			            			<tr class="subtree subtreemove subfirst" id="tr${sunCatalog.id}">
			            			<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value="${sunCatalog.id}"/>
                                	<input type="hidden" name="cateIconAry" id="cateIconAry" value="${sunCatalog.img}"/>
                                	<input type="hidden" name="cateLevelAry" value="2"/>
                                    <td class="td_0">
                                        <input type="checkbox" class="ui-checkbox newclasschk" id="setdef${status.index+1}" name="chkRefrenceId" value="${sunCatalog.id}"/>
                                    </td>
                                    <td class="td_1">

                                    </td>
                                    <td class="td_2">
                                        <div class="sub_zhe fl"></div>
                                        <input type="text" class="newclasstext" name="cateNameAry" value="${sunCatalog.name}" />
                                    </td>
                                    <c:choose>
                                    	<c:when test="${empty sunCatalog.img}">
                                    	<td class="td_3" id="td${status.count}">
                                        <span class="addsubclass"><i class="iconfont">&#xe615;</i>添加图片</span>
                                    	</td>
                                    	</c:when>
                                    	<c:otherwise>
                                    	<td class="td_3">
                                        <span class="haspic"><a href="javascript:;" class="haspic-edit">编辑</a> <a href="javascript:;" class="haspic-del">删除</a></span>
                                        <div class="piclook" style="position: relative;display: none">
                                            <div class="piclookbox js-img-center" style="background: #fff; position: absolute; width: 170px; height: 170px;z-index: 9; border: 1px solid #d9d9d9; top:0;">
											<c:set value="${sunCatalog.domainName}${sunCatalog.img}" var="url"></c:set>
                                            <%--<img src="${res}${fns:getImageDomainPath(url, 170, 170)}"  alt=""/>
                                            --%></div>
                                        </div>
                                    	</td>
                                    	</c:otherwise>
                                    </c:choose>
                                    <td class="td_4">
                                        <div class="movebox">
                                            <span class="moveup submoveup" title="上移" data="${sunCatalog.id}"></span><span class="movedown submovedown" title="下移" data="${sunCatalog.id}"></span>
                                        </div>
                                    </td>
                                    <td class="td_5"><span class="delete" data="${sunCatalog.id}" >删除</span></td>
                                </tr> 
			            		</c:forEach>
			            		<tr class="subtree addsubtree">
                                    <td class="td_0">
                                    </td>
                                    <td class="td_1">

                                    </td>
                                    <td class="td_2">
                                        <div class="sub_zhe fl"></div>
                                        <div class="js_add_sub">添加子分类</div>
                                    </td>
                                    <td class="td_3">

                                    </td>
                                    <td class="td_4">

                                    </td>
                                    <td class="td_5"></td>
                                </tr>
                                </tbody>
                                </c:forEach>
                            </table>

                            <div class="saveclass ui_button ui_button_lblue">保存分类</div>
                        </div>
                    </div>
                </div>
                </form:form>
               <!--  <div class="addProPic">
                    <h4>请上传图片或者从空间选择图片</h4>
                    <div class="inline-block">
                        <div class="file_wrap">
                            <p class="replace_text simple_button">上传图片</p>
                            <input type="file" value="文件上传" class="input_file" name="photo" id="photo" onchange="ajaxFileUpLoad('photo');"/>
                        </div>
                    </div>
                    <a href="javascript:;" class="simple_button">空间图片</a>
                </div>-->
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
    <script src="${src}/brand/productmanage.js"></script>
    <script>
        $("#brandsId").val("${brandsId}");
        productmanageclass.init();
        function saveCatalog(){
        	var brandsId = $("#brandsId").val();
        	if(brandsId == null || brandsId == "")
        	{
        		remind("error", "未选择品牌");
        		return;
        	}
        	
        	$.ajax({
	   			url:"/common/productCatalog/save",
	   			type:"post",
	   			data:$("#catelogForm").serialize(),
	   			dataType: "json",
	   			success:function(json){
	   				if(zttx.SUCCESS==json.code){
	   					remind("success","分类保存成功",function(){
	   						$("#catelogForm").submit();
	   					});
					}else
						remind("error",json.message);
	   			},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					remind("error",errorThrown);
				}
   			});    
        };
		function ajaxFileUpLoad(obj, uploadId){
			seajs.use(["$","ajaxFileUpload"],function($){
    			$.ajaxFileUpload({
        			url: "${ctx}/common/showImg?fSize=1",
        			secureuri: false,
                    fileElementId: uploadId,
                    dataType: "json",
                    success: function(data)
                    {
                    	if(data.code != zttx.SUCCESS){
                    		remind("error",data.message);
                    	}
                    	else
                    	{
                    		var already =
				                '<span class="haspic">' +
				                    '<a href="javascript:;" class="haspic-edit">编辑</a>' +
				                    '<a href="javascript:;" class="haspic-del" style="margin-left: 5px">删除</a>' +
				                '</span>' +
				                '<div class="piclook" style="position: relative;display: none">' +
				                    '<div class="piclookbox" style="background: #fff; position: absolute; width: 170px; height: 170px;z-index: 9; border: 1px solid #d9d9d9; top:0;">' +
				                    '<img style="width:170px;height:170px;" src="${res}'+data.message+'" alt=""/>' +
				                    '</div>' +
				                '</div>';
				                obj.parent().parent().parent().parent("tr").find("#cateIconAry").val(data.message);
				                obj.parent().parent().parent().html(already);
                    	}
                    }
        		});
    	    });
		};
		$(document).ready(function () {
			var liObj = $("#menu"+currMenuId).parents("li");
			liObj.addClass("current_item");
		});
    </script>
</body>
</html>