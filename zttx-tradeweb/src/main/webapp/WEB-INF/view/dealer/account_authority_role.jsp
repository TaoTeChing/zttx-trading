<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>账户管理_权限管理</title>
    <link href="${res}/styles/dealer/reset.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/icons.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                 <jsp:include page="${ctx}/dealer/dealermenu"/>
 
                <!--主体内容-->
                <div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <a href="${ctx}/dealer/account/infor" title="">账户管理</a> &gt; <span class="bb">权限管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="panel-tabs">
                            <div class="panel-head"> 
                                <ul>
                                    <li>
                                        <a href="${ctx}/dealer/account/authority" class="yahei fs14">成员列表</a>
                                    </li>
                                    <li class="on">
                                        <span class="yahei fs14">权限分配</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="panel-content">
							<input type="hidden" value="" id="tmpRoleId"/>
                                <div class="tab-item">
                                    <div class="list-limit clearfix">
                                        <div class="role">
                                            <div class="role-toolbar">
                                                <h3 class="yahei fs14">权限角色列表</h3>
                                                <a class="btn-add c-b" href="javascript:void(0)"><i class="account-icon i-add-blue"></i><span>新建</span></a>
                                            </div>
                                            <div class="role-items">
                                                <ul class="clearfix">
                                                <c:forEach items="${roleList}" var="role">
                                                	<li>
                                                        <input type="text" data-id="${role.refrenceId}" class="text item" value="${role.roleName}"/><a class="btn btn-r hide" href="javascript:;">保存</a><a class="edit-role" href="javascript:;"><i class="icon i-pencil"></i></a> <a class="delete-role" href="javascript:;"><i class="icon i-delete"></i></a>
                                                   		<input id="${role.refrenceId}" type="hidden" value="${role.roleName}" />
                                                    </li>
                                                </c:forEach>
                                                    <li class="newitem">
                                                        <input class="text item edititem" id="roleName"/><a class="btn btn-r">保存</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="limits clearfix">
                                        <!-- 
                                            <div class="limits-toolbar">
                                                <div class="bgc-w">
                                                    <textarea class="marg10 c-h p10 ofh lh1-5">系统提供了资产负债表，损益表，库存月报表，现金流量表，并且以上报表都可以实现数据导出，对报表打印格式由用户自己定义，在报表内增加了查询条件的自定义组合，及报表的“穿透”显示，直至查到对应的会计凭证。用户还可根据实际情况选择自己的报表模块，进行计算，打印。</textarea>
                                                </div>
                                            </div>
                                         -->
                                            <div class="limits-list">
                                                <div class="panel-limits">
                                                    <div class="panel-title">
                                                        <h3 class="fs14 yahei">官方推荐权限</h3>
                                                        <div class="title-toolbar">
                                                            <a class="btn btn-r"><i class="icon i-save-w"></i><span>保存权限</span></a>
                                                        </div>
                                                    </div>
                                                    <div class="limits-tree">
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${res}/scripts/dealer/roleManage.js"></script>
    <script src="${res}/scripts/common/ValidateUtils.js"></script>
    
    <script>
    
    
    //树形菜单

        seajs.use(['$', 'dialog'], function ($, Dialog) {

            //添加一个部门
            $(".btn-add").click(function () {
                $(".newitem").show().find("input.item").val('');
            });

            
            //部门列表鼠标移动事件
            $(document).on({
                click: function () {
                    $(".on-edit a.btn").not($(this).find("a.btn")).click();
                    $(".role-items li").not($(this)).removeClass("on");
                    $(this).addClass("on");
                }
            }, ".role-items li");

	    	//$(document).on("click", ".role-items li", function () {
           //     $(".role-items li").removeClass("check");
           //     $(this).addClass("check");
           // });

            $("input.item").not('.newitem .item').attr({ readonly: "readonly" });

            //点击列表后更改右侧权限配置
            $(document).on("click", "input.item:not('.newitem .item,.on-edit .item')", function () {
            	var roleId = $(this).attr("data-id");
            	$("#tmpRoleId").val(roleId);
            	checkRole(roleId);
            });


            //按下编辑后
            $(document).on("click", ".edit-role", function () {
                $(".role-items li").removeClass("on-edit").find(".item").attr("readonly","readonly").removeClass("edititem").next().hide();
                $(".edit-role").removeAttr("style").next().removeAttr("style");
                //修改状态
                $(this).parent().addClass("on-edit").find(".item").removeAttr("readonly").addClass("edititem").next().show();
                //隐藏编辑前按钮
                $(this).hide().next().hide();
            });

            //按下修改后的事务
            $(document).on("click", '.on-edit a.btn', function () {
                //这里写保存按钮按下后的事务处理
				var obj = $(this);
                var roleName = $(this).prev().val();
                var refrenceId = $(this).prev().data("id");
                var oldroleName = $("#"+refrenceId).val();
                if(roleName == ""){
                    remind("error","权限名称不能为空！");
                    return;
                }
        		$.ajax({
        			type : "POST",
        			url : "updateRole",
        			data : {roleName:roleName,refrenceId:refrenceId},
        			dataType: "json",
        			success : function(json) {
        				if(0==json.code){
        					 $(".select-department").find("[value='" + refrenceId + "']").text(roleName);
        					 //保存按钮事件
                			 obj.hide().prev().attr({ readonly: "readonly" }).removeClass("edititem").parent().removeClass("on-edit");
                             obj.nextAll().removeAttr("style");
        				}else
        					remind("error",json.message);
        			},
        			error:function(XMLHttpRequest, textStatus, errorThrown){
        				remind("error",errorThrown);
        			}
        		});


            });

            //按下添加保存后的事务
            $(document).on("click", '.newitem a.btn', function () {
                var me = this;
                
                //获得表单值
                var val = $.trim($(me).prev().val());
                if(val == ""){
                    $(".newitem").hide();
                    return;
                }
                if(Validate.length(val)>32){
                	remind("error","请输入不大于32字符的权限名称");
                    return;
                }

                $.ajax({
        			type : "POST",
        			url : "insertRole",
        			data : {roleName:val},
        			dataType: "json",
        			success : function(json) {
        				if(0==json.code){
        			        //准备好添加到列表的模板
        			        var newli = $('<li><input data-id="'+json.object+'" type="text" readonly="readonly" class="text item" /><a class="btn btn-r hide">保存</a><a class="edit-role" href="javascript:void(0)"><i class="icon i-pencil"></i></a> <a class="delete-role" href="javascript:void(0)"><i class="icon i-delete"></i></a></li>');
        			        //模板的值和文本赋值就绪
        			        $(newli).find("input").data({ "id": json.object }).val(val);
        			        //添加模板到列表最后位置
        			        $(me).parent().before(newli);
        			        //下拉框数据添加
        			        $(".select-role").append($("<option>").val(json.object).text(val));
        			        //隐藏添加表单
                			$(me).parent().hide();
        				}else
        					remind("error",json.message);
        			},
        			error:function(XMLHttpRequest, textStatus, errorThrown){
        				remind("error",errorThrown);
        			}
        		}); 
            });

            //按下删除
            $(document).on("click", ".delete-role", function () {
                var me = this;
                confirmDialog("确定是否要删除？",function() {
                    var id = $(me).parent().find("input.item").data("id");
                    $.ajax({
        				type : "POST",
        				url : "delDealerRole",
        				data : {refrenceId:id},
        				dataType: "json",
        				success : function(json) {
        					if(0==json.code){
        						$(".select-role").find("option[value='" + id + "']").remove();
        			            $(me).parent().remove();
        					}else
        						remind("error",json.message);
        				},
        				error:function(XMLHttpRequest, textStatus, errorThrown){
        					remind("error",errorThrown);
        				}
        			}); 

                });
            });


            $(".limits-toolbar textarea").focus(function () {
                $(this).addClass("edit");//.removeAttr("readonly");
            }).blur(function () {
                $(this).removeClass("edit");//.attr("readonly");
            });

            $(".title-toolbar a.btn").click(function(){
            	var roleId = $.trim($("#tmpRoleId").val());
            	if(roleId==""){
            		remind("error","请选择一个角色");
            		return;
            	}
            	var nodes = zTreeObj.getCheckedNodes(true);
            	var menuCodeAry = new Array();
            	for(var i=0,j=0;i<nodes.length;i++){
            		if(nodes[i].code!=undefined){
            			menuCodeAry[j]=nodes[i].code;
            			j++;
            		}
            	}
            	$.ajax({
    				type : "POST",
    				url : "insertDealerRmenu",
    				data : {menuCode:menuCodeAry,roleId:roleId},
    				dataType: "json",
    				traditional:true,			//传递数组序列化
    				success : function(json) {
    					if(0==json.code){
    						remind("success","权限设置成功");
    					}else
    						remind("error",json.message);
    				},
    				error:function(XMLHttpRequest, textStatus, errorThrown){
    					remind("error",errorThrown);
    				}
    			}); 

            });

        });
    
    
    var zTreeObj,zNodes;
        seajs.use(['$', 'ztree'], function ($, ztree) {
            var setting = {
                check: {
                	enable: true,
                    chkboxType: { "Y": "ps", "N": "s" }
                },
                view: {
					showIcon: false
				}
            };
            zNodes = ${menuTreeList};
            $(document).ready(function () {
                zTreeObj = $.fn.zTree.init($(".limits-tree"), setting, zNodes);
                zTreeObj.expandAll(true);
                //zTreeObj
            });
        });
        function checkRole(roleId){
        	$.ajax({
				type : "POST",
				url : "listRole",
				data : {roleId:roleId},
				dataType: "json",
				success : function(json) {
					if(0==json.code){
						if(json.object!=null){
							var nodes =  zTreeObj.transformToArray(zTreeObj.getNodes());
							zTreeObj.checkAllNodes(false);
							for(var i=0;i<json.object.length;i++)
							{
								for(var j=0;j<nodes.length;j++){
									if(json.object[i]==nodes[j].code){
										zTreeObj.checkNode(nodes[j], true);
										break;
									};
					        	};
							};
						};
					}else
						remind("error",json.message);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					remind("error",errorThrown);
				}
			})
        };
    </script>
</body>
</html>
