<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>账户管理_成员列表</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/account.css" rel="stylesheet" />
    <style>
        #Form_AddChildren .ui-form-row .ui-form-item .ui-form-explain{
            margin-left: 0;
        }
        #Form_updateChildren {
            margin-top: 20px;
            overflow: hidden;
            width: 100%;
            z-index: 99;
        }
        #Form_updateChildren .ui-form-item {
            padding: 0 5px 20px 0;
        }
        #Form_updateChildren .ui-form-row {
        }
        #Form_updateChildren .ui-form-row:before, #Form_AddChildren .ui-form-row:after {
            content: "";
            display: table;
            font: 0px/0 a;
        }
        #Form_updateChildren .ui-form-row:after {
            clear: both;
        }
        #Form_updateChildren .ui-form-row .ui-form-item {
            float: left;
            padding: 0 5px 20px 60px;
            width: 180px;
        }
        #Form_updateChildren .ui-form-row .ui-form-item .ui-label {
            color: #666666;
            font-size: 12px;
            margin-left: -60px;
            width: 50px;
        }
        #Form_updateChildren .ui-form-row .ui-form-item .ui-select {
            margin: 0;
            padding: 5px;
        }
        #Form_updateChildren .ui-form-row .ui-form-item .ui-input {
            line-height: 16px;
            padding: 5px 9px;
        }
        #Form_updateChildren .ui-form-row .ui-form-item .ui-form-explain {
            clear: both;
            height: 20px;
            line-height: 20px;
            margin-left: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <!-- #include file="母版页.html" -->  
    <div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <!--完成-->
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
 				<jsp:include page="${ctx}/dealer/dealermenu"/>
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/account/infor" title="">账户管理</a> > <span class="bb">权限管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="panel-tabs">
                            <div class="panel-head">
                                <ul>
                                    <li class="on">
                                        <span class="yahei fs14">成员列表</span>
                                    </li>
                                    <li>
                                        <a href="${ctx}/dealer/account/rolemanage" class="yahei fs14">权限分配</a>
                                    </li>
                                </ul>
                            </div>
                            
                            <div class="panel-content">
                                <div class="tab-item">
                                    <div class="list-member">
                                        <div class="department">
                                            <div class="department-toolbar">
                                                <h3 class="yahei fs14">部门列表</h3> 
                                                <a class="btn-add c-b" href="javascript:;"><i class="account-icon i-add-blue"></i><span>新建</span></a>
                                            </div>
                                            <div class="department-items">
                                                <ul class="clearfix">
                                                <c:forEach items="${deptList}" var="dept">
                                                    <li>
                                                        <input type="text" data-id="${dept.refrenceId}" class="text item" value="${dept.deptName}" /><a class="btn btn-r hide" href="javascript:;">保存</a><a class="edit-department" href="javascript:void(0)"><i class="icon i-pencil"></i></a> <a class="delete-department" href="javascript:void(0)"><i class="icon i-delete"></i></a>
                                                   		 <input id="${dept.refrenceId}" type="hidden" value="${dept.deptName}" />
                                                    </li>
                                                </c:forEach>
                                                    <li class="newitem">
                                                        <input class="text item edititem" id="deptName"/><a class="btn btn-r">保存</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="members clearfix">
                                            <div class="members-toolbar">
                                                <div class="yahei fs14 c-h fl ml10">
                                                    <span>当前子账号使用情况:可使用(<span class="c-r-on">${userStateCount[0]}</span>) 可新建(<span class="c-r-on">${userStateCount[2]}</span>) 冻结(<span class="c-r-on">${userStateCount[1]}</span>)</span>
                                                </div>
                                                <form:form action="" method="post">
                                                <div class="search fr mr10">
                                                    <input class="text ui-input keyword" name="userName" value="${userName}"/><input type="submit" style="border-radius:0" class="ui-button ui-button-mred" value="搜 索" />
                                                    <c:if test="${userStateCount[2]>0}">
                                                    <input type="button" class="btn-addid ui-button ui-button-mred" value="新建子账号" />
                                                    </c:if>    
                                                </div>
                                                </form:form>
                                            </div>
                                            <div class="members-list">
                                                <table>
                                                    <thead>
                                                        <tr>
                                                            <th class="cell-1" style="text-align: center;">姓名</th>
                                                            <th class="cell-2" style="text-align: center;">部门</th>
                                                            <th class="cell-3" style="text-align: center;">角色</th>
                                                            <th class="cell-4" style="text-align: center;">状态</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${usermList}" var="dealer">
	                                                	<tr>
                                                            <c:choose>
                                                            	<c:when test="${1==dealer.userState}">
                                                            <td style="cursor: pointer" class="js_username" data_brandId="${dealer.refrenceId}">${dealer.userName}</td>
                                                            <td>
                                                                <div class="inline-block">
                                                                    <select name="select2" id="deptSelect${dealer.refrenceId}" data-display="部门"
                                                                    class="ui-select js_small_select" onchange="$ROL.updateDealerDept('${dealer.refrenceId}')">
                                                                    <option value="">请选择部门</option>
                                                                    <c:forEach items="${deptList}" var="dept">
                                                                        <option value="${dept.refrenceId}" <c:if test="${dept.refrenceId==dealer.deptId}">selected</c:if>>${dept.deptName}</option>
                                                                    </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="inline-block">
                                                                    <select name="select1" id="roleSelect${dealer.refrenceId}" requ data-display="角色"
                                                                    class="ui-select js_small_select select-department" onchange="$ROL.updateDealerRole('${dealer.refrenceId}');">
                                                                    <option value="">请选择角色</option>
                                                                    <c:forEach items="${roleList}" var="role"> 
                                                                        <option value="${role.refrenceId}" <c:if test="${role.refrenceId==dealer.roleId}">selected</c:if>>${role.roleName}</option>
                                                                    </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </td>
                                                            <td>
                                                            	<a class="btn btn-n" id="u${dealer.refrenceId}" href="javascript:$ROL.updateDealerAccountType('${dealer.refrenceId}')">
                                                            	<i class="icon i-play"></i>使用中</a>
                                                            </td>
                                                            </c:when>   
                                                            <c:when test="${2==dealer.userState}">
                                                          	  <td>${dealer.userName}</td>
                                                            	<td>
                                                                <div class="inline-block">
                                                                    <select name="select2" id="deptSelect${dealer.refrenceId}" data-display="部门" disabled="disabled"
                                                                    class="ui-select js_small_select" onchange="$ROL.updateDealerDept('${dealer.refrenceId}')">
                                                                    <option value="">请选择部门</option>
                                                                    <c:forEach items="${deptList}" var="dept">
                                                                        <option value="${dept.refrenceId}" <c:if test="${dept.refrenceId==dealer.deptId}">selected</c:if>>${dept.deptName}</option>
                                                                    </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="inline-block">
                                                                    <select name="select1" id="roleSelect${dealer.refrenceId}" requ data-display="角色" disabled="disabled"
                                                                    class="ui-select js_small_select select-department" onchange="$ROL.updateDealerRole('${dealer.refrenceId}');">
                                                                    <option value="">请选择角色</option>
                                                                    <c:forEach items="${roleList}" var="role"> 
                                                                        <option value="${role.refrenceId}" <c:if test="${role.refrenceId==dealer.roleId}">selected</c:if>>${role.roleName}</option>
                                                                    </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </td>
                                                            <td>
                                                            	<a class="btn btn-n"  href="javascript:$ROL.updateDealerAccountType('${dealer.refrenceId}')"><i class="icon i-pause"></i>已冻结</a>
                                                            </td>
                                                            </c:when>   
                                                            </c:choose>
                                                        </tr>
                                                	</c:forEach>
                                                    </tbody>
                                                </table>
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
    <div class="hide">
        <div id="newChildrenID2" class="newChildrenID2 ">
        <div class="ui-head">
                <h3>新建子账号</h3>
            </div>
            <form id="Form_AddChildren" class="clearfix ui-form">
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">手　机:</label><input type="text" name="userMobile" id="userMobile" class="ui-input long" data-display="手机" maxlength="11"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">邮　箱:</label><input autocomplete="off" type="text" name="userMail" id="userMail" class="ui-input long" data-display="邮箱" />
                    </div>
                </div>
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">密　码:</label><input autocomplete="off" type="password" name="userPwd" id="userPwd" class="ui-input long" data-display="密码" maxlength="20"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">部　门:</label>
                        <select class="ui-select" name="deptId" data-display="部门" id="deptId" >
                            <option value="">请选择部门</option>
		                    <c:forEach items="${deptList}" var="dept">
		                        <option value="${dept.refrenceId}">${dept.deptName}</option>
		                    </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">姓　名:</label><input name="userName" id="userName" type="text" class="ui-input" data-display="姓名" maxlength="16"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">角　色:</label>

                        <select class="ui-select" name="roleId" data-display="角色" id="roleId">
                            <option value="">请选择角色</option>
		                    <c:forEach items="${roleList}" var="role">
		                         <option value="${role.refrenceId}">${role.roleName}</option>
		                     </c:forEach>
                        </select>

                    </div>
                </div>
                <div class="ta-c ui-form-item mt10">
                    <button style="line-height: normal;" class="ui-button ui-button-mred btn-saveid" type="submit">确&nbsp;认</button>
                    <a class="ui-button ui-button-mred btn-cancel">取 消</a>
                </div>
            </form>
        </div>
    </div>
    <div class="hide">
        <div id="newChildrenID3" class="newChildrenID2 ">
            <div class="ui-head">
                <h3>修改子账号</h3>
            </div>
            <form id="Form_updateChildren" class="clearfix ui-form">
            <input type="hidden" name="refrenceId" id="refrenceId"/>
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">手　机:</label><input type="text" name="userMobile" id="userMobile" class="ui-input long" data-display="手机" maxlength="11"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">邮　箱:</label><input autocomplete="off" type="text" name="userMail" id="userMail" class="ui-input long" data-display="邮箱" />
                    </div>
                </div>
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">密　码:</label><input autocomplete="off" type="password" name="userPwd" id="upuserPwd" class="ui-input long" data-display="密码" maxlength="20"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">部　门:</label>
                        <select class="ui-select" name="deptId" data-display="部门" id="deptId" >
                            <option value="">请选择部门</option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.refrenceId}">${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ui-form-row">
                    <div class="ui-form-item">
                        <label class="ui-label">姓　名:</label><input name="userName" id="userName" type="text" class="ui-input" data-display="姓名" maxlength="16"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">角　色:</label>

                        <select class="ui-select" name="roleId" data-display="角色" id="roleId">
                            <option value="">请选择角色</option>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.refrenceId}">${role.roleName}</option>
                            </c:forEach>
                        </select>

                    </div>
                </div>
                <div class="ta-c ui-form-item mt10">
                    <button style="line-height: normal;" class="ui-button ui-button-mred" type="submit">确&nbsp;认</button>
                    <a class="ui-button ui-button-mred btn-cancel">取 消</a>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script src="${res}/scripts/dealer/roleManage.js"></script>
    <script src="${res}/scripts/common/ValidateUtils.js"></script>
    <script type="text/javascript">
    seajs.use(['$', 'dialog'], function ($, Dialog) {
            var o = new Dialog({
                trigger: ".btn-addid",
                content: '#newChildrenID2'
            });

            var o2 = new Dialog({
                trigger: ".js_username",
                content: '#newChildrenID3'
            }); 
            o2.after('show', function() {
				var btn = this.activeTrigger;
                $("#Form_updateChildren")[0].reset();
                $("#upDeptId_div span").html("请选择部门");
                $("#upRoleId_div span").html("请选择角色");
                var brandId=btn.attr("data_brandId");
                resetValidatorError("#newChildrenID3");
                $.post("${ctx}/dealer/account/sunUser",{refrenceId:brandId},function(data){
			   		if(data.code==126000){
			   			$("#newChildrenID3 #refrenceId").val(data.object.refrenceId);
			   			$("#newChildrenID3 #userMobile").val(data.object.userMobile);
			   			$("#newChildrenID3 #userMail").val(data.object.userMail);
			   			$("#newChildrenID3 #upuserPwd").val(data.object.userPwd);
			   			$("#newChildrenID3 #deptId").val(data.object.deptId);
			   			$("#newChildrenID3 #userName").val(data.object.userName);
			   			$("#newChildrenID3 #roleId").val(data.object.roleId);
						$("#newChildrenID3").show();
			   		}else{
			   			remind("error",data.message);  
			   		}
		   		},"json");
			});	
            //添加一个部门
            $(".btn-add").click(function () {
                $(".newitem").show().find("input.item").val('');
            });
            //添加一个子账号
            $(".btn-cancel").click(function () {
                o.hide();
                o2.hide();
            });
            //部门列表鼠标移动事件
            $(document).on({
                mouseover: function () {
                    $(this).toggleClass("on");
                },
                mouseout: function () {
                    $(this).removeClass("on");
                }
            }, ".department-items li");
            
            $(".item").not('.newitem .item').attr({ readonly: "readonly" });
            //按下编辑后
            $(document).on("click", ".edit-department", function () {
                $(".department-items li").removeClass("on-edit").find(".item").attr("readonly","readonly").removeClass("edititem").next().hide();
                $(".edit-department").removeAttr("style").next().removeAttr("style");
                //修改状态
                $(this).parent().addClass("on-edit").find(".item").removeAttr("readonly").addClass("edititem").next().show();
                //隐藏编辑前按钮
                $(this).hide().next().hide();


            });

            //按下修改后的事务
            $(document).on("click", '.on-edit a.btn', function () {
                var obj = $(this);
                var deptName = $.trim(obj.prev().val());
                var refrenceId = obj.prev().data("id");
                var oldDeptName = $("#"+refrenceId).val();
                
                if(deptName == ""){
                    remind("error","请输入部门名称");
                    return;
                }
                if(Validate.length(deptName)>32){
                	remind("error","请输入不大于32字符的部门名称");
                    return;
                }
        		$.ajax({
        			type : "POST",
        			url : "updateDept",
        			data : {deptName:deptName,refrenceId:refrenceId},
        			dataType: "json",
        			success : function(json) {
        				if(0==json.code){
							window.location.reload();
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
                $.ajax({
        			type : "POST",
        			url : "${ctx}/dealer/account/insertDept",
        			data : {deptName:val},
        			dataType: "json",
        			success : function(json) {
        				if(0==json.code){
        			        //准备好添加到列表的模板
        			        var newli = $('<li><input type="text" readonly="readonly" class="text item" /><a class="btn btn-r hide">保存</a><a class="edit-department" href="javascript:void(0)"><i class="icon i-pencil"></i></a> <a class="delete-department" href="javascript:void(0)"><i class="icon i-delete"></i></a></li>');
        			        //模板的值和文本赋值就绪
        			        $(newli).find("input").data({ "id": json.object }).val(val);
        			        //添加模板到列表最后位置
        			        $(me).parent().before(newli);
        			        //下拉框数据添加
        			        $(".select-department").append($("<option>").val(json.object).text(val));
        			        $("#addDeptId").append($("<option>").val(json.object).text(val));
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
            $(document).on("click", ".delete-department", function () {
                var me = this;
                var id = $(me).parent().find("input.item").data("id");
                confirmDialog("确定是否要删除？",function(){
                    $.ajax({
        				type : "POST",
        				url : "delDealerDept",
        				data : {refrenceId:id},
        				dataType: "json",
        				success : function(json) {
        					if(0==json.code){
        						$(".select-department").find("option[value='" + id + "']").remove();
        						$("#addDeptId").find("option[value='" + id + "']").remove();
        			            $(me).parent().remove();
        					}else
        						remind("error",json.message);
        				},
        				error:function(XMLHttpRequest, textStatus, errorThrown){
        					remind("error",errorThrown);
        				}
        			}); 
                })
            });
        });
    
    seajs.use(['validator', '$'], function (Validator, $) {
                
            var validator = new Validator({
                element: '#Form_AddChildren',
                autoSubmit: false,// 当验证通过后不自动提交
                    onFormValidated: function(error,results,element){
                    	if(!error){
                    		// 当表单验证通过，ajax提交表单
                    		$ROL.insertSumUserm();
                    	}
                    }
            });
            
            validator.addItem({
                element: '[name=userMobile]',
                required: true,
                rule: 'mobile'
            })
                .addItem({
                    element: '[name=userMail]',
                    required: false,
                    rule: 'email'
                })
                .addItem({
                    element: '[name=userPwd]',
                    required: true,
                    rule: 'minlength{"min":5} maxlength{"max":20}'
                })
                .addItem({
                    element: '[name=deptId]',
                    required: true
                })
                .addItem({
                    element: '[id=userName]',
                    required: true,
                    rule: 'minlength{"min":1} maxlength{"max":16}'
                })
                .addItem({
                    element: '[name=roleId]',
                    required: true
                });


        var validator2 = new Validator({
            element: '#Form_updateChildren',
            autoSubmit: false,// 当验证通过后不自动提交
            onFormValidated: function(error,results,element){
                if(!error){
                    // 当表单验证通过，ajax提交表单
                    $ROL.updateSumUserm();
                }
            }
        });
        
        Validator.addRule('upPassword',function(options){
            var v = options.element.val();
            console.log(v.length);
            if(v.length != 32 && v.length > 20 || v.length < 6 ){
            	return false;
            }else{
                return true;
            }
        }, '长度必须大于6并且小于20');

        validator2.addItem({
            element: '#Form_updateChildren [name=userMobile]',
            required: true,
            rule: 'mobile'
        })
        .addItem({
            element: '#Form_updateChildren [name=userMail]',
            required: false,
            rule: 'email'
        })
        .addItem({
            element: "#Form_updateChildren [name=userPwd]",
            rule: "upPassword"
        })
        .addItem({
            element: '#Form_updateChildren [name=deptId]',
            required: true
        })
        .addItem({
            element: '#Form_updateChildren [name=userName]',
            required: true
        })
        .addItem({
            element: '#Form_updateChildren [name=roleId]',
            required: true
        });
    });
    </script>
</body>
</html>
