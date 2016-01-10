<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-图片管理-分类管理</title>
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
            <span class="current">分类管理</span>
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
                       <li class="selected"><a href="${ctx}/brand/brandImgcate/imgcate">分类管理</a></li>
                       <li><a href="${ctx}/brand/brandImage/image/recycle_picture">回收站</a></li>
                    </ul>
                </div>
                <div class="ui_tabsbox">
                    <!-- 图片管理：分类管理 -->
                    <div class="classfication">
                        <div class="newclass">
                            <i class="iconfont">&#xe611;</i>
                            <span class="newclasspan">新建分类</span>
                        </div>
                        <a href="javascript:void(0);" class="deletepic bluefont inline-block" id="delselect" ><i class="uploadicondel"></i>批量删除</a>
                       <form:form action="" method="post" id="cateForm" name="cateForm">
                        <table style="clear:both;" class="table">
                            <colgroup>
                                <col width="35" />
                                <col width="35" />
                                <col width="350" />
                                <col width="150" />
                                <col width="150" />
                                <col width="100" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>分类名称</th>
                                    <th>添加子分类</th>
                                    <th>移动</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                           	<c:if test="${empty listCate}">
                           		<tbody></tbody>
                           	</c:if>
                           <c:forEach var="imgCate" items="${listCate}">
                               <c:if test="${empty imgCate.parentId}"><tbody></c:if>
                              <c:if test="${empty imgCate.parentId}">
                             	 <tr class="maintree">
                             	   <input type="hidden" name="uuid" value="${imgCate.refrenceId}"/>
                                   <input type="hidden" name="parentId" value="${imgCate.parentId}"/>
                                   <input type="hidden" name="level" value="1"/>
                                    <td class="td_0">
                                            <input type="checkbox" class="ui-checkbox newclasschk mainclasschk" name="delChk"  value="${imgCate.refrenceId}">
                                    </td>
                                    <td class="td_1">
                                        <i id="trplus" class="main_iconskai"></i>
                                    </td>
                                    <td class="td_2">
                                        <input type="text" class="newclasstext" value="${imgCate.cateName}" name="cateName"/>
                                    </td>
                                    <td class="td_3">
                                        <span class="addsubclass">添加子分类</span>
                                    </td>
                                    <td class="td_4">
                                        <div class="movebox">
                                            <span class="moveup mainmoveup" title="上移"></span>
                                            <span class="movedown mainmovedown" title="下移"></span>
                                        </div>
                                    </td>
                                    <td class="td_5"><span class="zz-delete main-delete bluefont ${imgCate.delFlag?'revertFile':'deleteFile'}" revenId="${imgCate.refrenceId}" data-type="1" style="cursor: pointer;">${imgCate.delFlag?"还原":"删除"}</span></td>
                                </tr>
                             </c:if>
                              <c:forEach var="subCate" items="${listCate}">
                                 <c:if test="${not empty subCate.parentId && subCate.parentId==imgCate.refrenceId}">
	                                <tr class="subtree">
                                        <input type="hidden" name="uuid" value="${subCate.refrenceId}"/>
                                        <input type="hidden" name="parentId" value="${subCate.parentId}"/>
                                        <input type="hidden" name="level" value="2"/>
	                                    <td class="td_0">
	                                        <input type="checkbox" class="ui-checkbox newclasschk" name="delChk" value="${subCate.refrenceId}">
	                                    </td>
	                                    <td class="td_1">

	                                    </td>
	                                    <td class="td_2">
	                                        <div class="sub_zhe inline-block fl"></div>
	                                        <input type="text" class="newclasstext" value="${subCate.cateName}" name="cateName"/>
	                                    </td>
	                                    <td class="td_3">

	                                    </td>
	                                    <td class="td_4">
	                                        <div class="movebox">
	                                            <span class="moveup submoveup" title="上移"></span>
	                                            <span class="movedown submovedown" title="下移"></span>
	                                        </div>
	                                    </td>
	                                    <td class="td_5">
	                                    <span class="zz-delete sub-delete bluefont ${subCate.delFlag?'revertFile':'deleteFile'}" revenId="${subCate.refrenceId}" data-type="2" style="cursor: pointer;">${subCate.delFlag?"还原":"删除"}</span>
	                                    </td>
	                                </tr>
                                  </c:if>
                              </c:forEach>
                               <c:if test="${empty imgCate.parentId}"></tbody></c:if>
                          </c:forEach>
                        </table>
                        <div class="ui_button ui_button_lblue saveclass" id="saveclasses">保存分类</div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/classification.js"></script>
<script>
	pictureManagement.init();
 </script>
 <script type="text/javascript">
	
   	$(function (){
   		// 保存分类
   		$("#saveclasses").on("click",function(eventObject){
            if($(".newclasstext").length == 0){
                return;
            }
   			goSubmit();
   			eventObject.stopPropagation();
		});
   		
 		// 主类删除
   		$("span.main-delete").on('click', function(){
   			var ids = [$(this).attr("revenId")];
	        deleteCate(ids);
	    });
        // 子类删除
        $("span.sub-delete").on('click', function(){
            var ids = [$(this).attr("revenId")];
            var flag = true;
            deleteCate(ids,flag);
        });
 		
   		// 批量删除
   		$("#delselect").click(function(){
   			var ids = [];
   	        $('[name=delChk]:checkbox:checked').each(function(index){
   	        	ids[ids.length] = $(this).val();
   	        });
	        deleteCate(ids);
   		});
 		
 	});
   	
   	function goSubmit(){
        var error = 0;
        $(".newclasstext").each(function(){
            if($(this).val() == ""){
                error ++;
            }
        })
        if(error > 0){
            remind("error","请输入分类名称");
            return;
        }
   		$.post("${ctx}/brand/brandImgcate/imgcate/add", $("#cateForm").serialize(), function(data){
   			if(data.code == zttx.SUCCESS){
   				remind("success", "保存成功", function(){
   					window.location.reload();
   				});
			}else{
				remind("error",data.message);
			}
   		}, "json");
   	}
	
	function deleteCate(ids,flag)
	{
		if(ids.length == 0)
		{
	        remind("error", "请选择记录");
	        return;
	    }
        if(!flag){
            if(!confirm("删除一级将会同时删除子集，您确定吗？"))
            {
                return;
            }
        }

		$.post("${ctx}/brand/brandImgcate/imgcate/delete", $.param({ids: ids}, true), function(data){
			if(data.code == zttx.SUCCESS){
   				remind("success", "删除成功", function(){
   					window.location.reload();
   				});
			}else{
				remind("error",data.message, function(){
					window.location.reload();
				});
			}
		}, "json");
	}
    </script>
</body>
</html>