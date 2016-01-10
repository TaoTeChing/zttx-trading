<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-权限管理-权限分配</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myAccount.css"/>
    <style>
        .data_name{
            width: 120px;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <div class="inner">
        <div class="bread_nav">
            <div class="fl">
            <i class="icon"></i>
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/contact/list">我的账户</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/role/deptmanage">权限管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">权限分配</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="competence_manage">
        <div class="left">
            <div class="dep_inner">
                <div class="dep_list">
                <input type="hidden" value="" id="tmpRoleId"/>
                    <div class="hd">
                        <h3>权限角色列表</h3>
                        <a id="addRole" href="javascript:;" class="addDep">
                            <i class="iconfont add_icon">&#xe611;</i>新建
                        </a>
                    </div>
                    <div class="bd">
                        <ul class="dep_data_list">
                        <c:forEach items="${roleList}" var="role">
                            <li class="item" data="${role.refrenceId}">
                               <span class="fl data_name fn-text-overflow">${role.roleName}</span>
                               <div class="fr operate">
                                   <a href="javascript:;" class="edit_icon iconfont">&#xe618;</a>
                                   <a href="javascript:;" class="del_icon iconfont">&#xe602;</a>
                               </div>
                           	</li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="right">
            <div style="color: #666;margin:15px 5px 10px 10px;">
                说明：您可以通过部门和角色来控制员工使用平台的权限
            </div>
            <div class="limits_text" style="display:none;">
                <textarea>系统提供了资产负债表，损益表，库存月报表，现金流量表，并且以上报表都可以实现数据导出，对报表打印格式由用户自己定义，在报表内增加了查询条件的自定义组合，及报表的“穿透”显示，直至查到对应的会计凭证。用户还可根据实际情况选择自己的报表模块，进行计算，打印。</textarea>
            </div>
            <div class="limits_list">
                <div class="hd">
                    <h3>官方推荐权限</h3>
                    <a class="save_btn" href="javascript:saveRoleMenu();">
                        <i class="iconfont">&#xe625;</i>保存修改
                    </a>
                </div>
                <div class="limits_tree">
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${res}/scripts/brand/roleManage.js"></script>
	<script>
    var role = {
        init: function(){
            this.roleOperate();
            this.doTextarea();
        },
        roleOperate: function(){
            var _this = this;
            $(".dep_list").on("mouseenter mouseleave",".item",function(ev){
                var operate_div = $(this).find(".operate");
                if(ev.type == "mouseenter"){
                    $(this).addClass("current");
                    operate_div.show();
                }else if(ev.type == "mouseleave"){
                    $(this).removeClass("current");
                    operate_div.hide();
                }
            });

            _this.doEdit();

            _this.doDel();

            $("#addRole").on("click",function(){
            	if($("#createInput").length > 0){
                    $("#createInput input").val("");
                    $("#createInput").show();
                    return;
                }
                $('<div id="createInput" class="mt10 clearfix"><input maxlength="32" class="createInput" /><a class="createSave" data-operate="insert" href="javascript:;">保存</a></div>').appendTo(".dep_list .bd");
                _this.doInsert();
            });
        },
        doInsert: function(){

            var _this = this;

            $(".createSave").on("click",function(){

                var _This = this;

                var op = $(this).parent();
                var oinput = op.find(".createInput");
                var val = $.trim(oinput.val());
                var operate = $(this).data("operate");
                if(val == ""){
                    remind("error","请输入权限名称");
                    return;
                }
                if(val.length > 32){
                    remind("error","权限名称长度不能超过32");
                    return;
                }
                
                var url="insertRole";
                var data = {roleName:val};
                if(operate == "update"){
                	url="updateRole";
                	var refrenceId = $.trim(oinput.attr("data"));
                	data={roleName:val,refrenceId:refrenceId};
                }
                $.post(url, data, function(json){
                	if(zttx.SUCCESS == json.code){
    			        if(operate == "insert"){
		                    $("#createInput").hide();
		                }
		                var new_li = '<span class="fl fn-text-overflow data_name">'+val+'</span><div class="fr operate"><a href="javascript:;" class="edit_icon iconfont">&#xe618;</a><a href="javascript:;" class="del_icon iconfont">&#xe602;</a></div>';
		                //如果是插入
		                if(operate == "insert"){
		                    $('<li class="item" data="'+json.object.refrenceId+'">'+new_li+'</li>').appendTo($(".dep_list ul"));;
		                }else if(operate == "update"){
                            _this.cancelMask(_This);
                            op.removeClass("edit");
		                    op.html(new_li);
                        }
    				}else{
    					remind("error",json.message);
    				}
                }, "json");
            });
        },
        doEdit: function(){
            var _this = this;
            $(".dep_data_list").on("click",".edit_icon",function(){

                _this.createMask(this);

                var oli = $(this).parents(".item");
                
                oli.addClass("edit");
                
                var id = $.trim(oli.attr("data"));

                var old_val = oli.find("span").html();

                oli.removeClass("current");
                oli.html('<input class="createInput" maxlength="32" data="'+id+'" value="'+old_val+'" /><a class="createSave" data-operate="update" href="javascript:;">保存</a></div>');
                _this.doInsert();
            });
        },
        doDel: function(){
            var _this = this;
            $(".dep_data_list").on("click",".del_icon",function(){
                var op =  $(this).parents(".item");
                var id = $.trim(op.attr("data"));
                confirmDialog("确定是否要删除？",function(){
	                $.ajax({
	       				type : "POST",
	       				url : "delBrandRole",
	       				data : {refrenceId:id},
	       				dataType: "json",
	       				success : function(json) {
	       					if(zttx.SUCCESS==json.code){
	       						op.remove();
	       					}else
	       						remind("error",json.message);
	       				},
	       				error:function(XMLHttpRequest, textStatus, errorThrown){
	       					remind("error",errorThrown);
	       				}
	       			});
       			})
            });
        },

        doTextarea: function(){
            $(".limits_text textarea").focus(function () {
                $(this).addClass("edit");
            }).blur(function () {
                $(this).removeClass("edit");
            });
        },
        createMask: function(obj){
            $(obj).parents("li").css({
                "z-index": 9999,
                "position": "relative"
            });
            if($("#mask").length > 0){
                $("#mask").show();
            }else{
                $("<div id='mask'></div>").appendTo($("body"));
                $("#mask").css({
                    "position": "absolute",
                    "left": 0,
                    "top": 0,
                    "width": "100%",
                    "height": $(document).height(),
                    "backgroundColor": "#000",
                    "opacity": "0.5",
                    "z-index": 9998
                })
            }
        },
        cancelMask: function(obj){
            $(obj).parents("li").css({
                "position": "static",
                "z-index": 1
            });
            if($("#mask").length > 0){
                $("#mask").hide();
            }
        }

    };
    role.init();
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
        
        zNodes =${menuTreeList};
		
        $(document).ready(function () {
            zTreeObj = $.fn.zTree.init($(".limits_tree"), setting, zNodes);
            zTreeObj.expandAll(true);
        });
    });
    $(document).on("click",".dep_data_list .item",function(){
    	$(".dep_data_list .item").removeClass("selected");
    	$(this).addClass("selected");
        var roleId = $.trim($(this).attr("data"));
        $("#tmpRoleId").val(roleId);
		$.ajax({
				type : "POST",
				url : "listRole",
				data : {roleId:roleId},
				dataType: "json",
				success : function(json) {
					if(zttx.SUCCESS==json.code){
						if(json.rows!=null){
							var nodes =  zTreeObj.transformToArray(zTreeObj.getNodes());
							zTreeObj.checkAllNodes(false);
							for(var i=0;i<json.rows.length;i++)
							{
								for(var j=0;j<nodes.length;j++){
									if(json.rows[i]==nodes[j].code){
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
			});
    });
    
    function saveRoleMenu(){
    	var roleId = $.trim($("#tmpRoleId").val());
       	if(roleId==""){
       		remind("error","请选择一个角色！");
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
       	if(menuCodeAry.length<=0)
       	{
       		remind("error","请选择一个权限");
       		return;
       	}
       	$.ajax({
			type : "POST",
			url : "insertBrandRmenu",
			data : {menuCode:menuCodeAry,roleId:roleId},
			dataType: "json",
			traditional:true,			//传递数组序列化
			success : function(json) {
				if(zttx.SUCCESS==json.code){
					remind("success","权限设置成功");
				}else
					remind("error",json.message);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				remind("error",errorThrown);
			}
		}); 
    }
    
</script>
    
</body>
</html>