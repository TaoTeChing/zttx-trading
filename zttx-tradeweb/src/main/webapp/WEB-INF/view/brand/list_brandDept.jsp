<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的账户-权限管理-成员列表</title>
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
	            <span class="current">成员列表</span>
	        </div>
	        <div class="fr">
	            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
	        </div>
	    </div>
	    <div class="competence_manage">
	        <div class="left">
	            <div class="dep_inner">
	                <div class="dep_list">
	                    <div class="hd">
	                        <h3>部门列表</h3>
	                        <a id="addDep" href="javascript:;" class="addDep">
	                            <i class="iconfont add_icon">&#xe611;</i>新建
	                        </a>
	                    </div>
	                    <div class="bd">
	                        <ul class="dep_data_list">
	                        <c:forEach items="${deptList}" var="dept">
                                <li class="item" data="${dept.refrenceId}">
	                                <span class="fl data_name fn-text-overflow">${dept.deptName}</span>
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
	            <div class="tool_bar clearfix">
	                <p class="fl">
	                    当前子账号使用情况：可使用（<span class="link">${userStateCount[0]}</span>） 可新建（<span class="link">${userStateCount[2]}</span>） 冻结（<span class="link">${userStateCount[1]}</span>）
	                </p>
	                <form:form class="search_form" action="" method="post">
	                    <div class="search_box fl">
	                        <i class="iconfont">&#xe600;</i>
	                        <div class="search_input fl">
	                            <input placeholder="搜号" type="text" value="${userName}" name="userName"/>
	                        </div>
	                        <button type="submit">搜&nbsp;索</button>
	                    </div>
	                </form:form>
	            </div>
	            <c:if test="${userStateCount[2]>0}">
	            <a id="addAccount" href="javascript:;" class="addAccount">
	                <i class="iconfont add_icon">&#xe611;</i>新建子账号
	            </a>
                <div style="color: #666;margin: -22px 5px 0 0;float: right;display: inline;">
                    说明：您可以在此新建子账号，供员工使用
                </div>
	            </c:if>
	            <div class="account_list">
	                <table class="list_table">
	                    <colgroup>
	                        <col width="230" />
	                        <col width="202" />
	                        <col width="204" />
	                        <col width="160" />
	                    </colgroup>
	                    <tr>
	                        <th>姓名</th>
	                        <th>部门</th>
	                        <th>角色</th>
	                        <th class="last">状态</th>
	                    </tr>
	                    <c:forEach items="${usermList}" var="brand">
	                    <tr>
	                        <td class="js_update" style="cursor: pointer" data_brandId="${brand.refrenceId}">
	                            ${brand.userName}(${brand.userMobile})
                            </td>
	                        <td>
	                            <select class="select-department" name="select_1" id="deptSelect${brand.refrenceId}" onchange="$ROL.updateBrandDept('${brand.refrenceId}');">
	                                <option value="">请选择部门</option>
                                    <c:forEach items="${deptList}" var="dept">
                                        <option value="${dept.refrenceId}" <c:if test="${dept.refrenceId==brand.deptId}">selected</c:if>>${dept.deptName}</option>
                                    </c:forEach>
	                            </select>
	                        </td>
	                        <td>
	                            <select name="select_1" id="roleSelect${brand.refrenceId}" onchange="$ROL.updateBrandRole('${brand.refrenceId}');">
	                                <option value="">请选择角色</option>
                                    <c:forEach items="${roleList}" var="role"> 
                                        <option value="${role.refrenceId}" <c:if test="${role.refrenceId==brand.roleId}">selected</c:if>>${role.roleName}</option>
                                    </c:forEach>
	                            </select>
	                        </td>
	                        <td class="last">
	                        <c:choose>                        	
                              	<c:when test="${1==brand.userState}"><a class="status js_status_on" id="u${brand.refrenceId}" href="javascript:$ROL.updateBrandAccountType('${brand.refrenceId}')"><i class="iconfont">&#xe623;</i><span>使用中</span></c:when>   
                              	<c:when test="${2==brand.userState}"><a class="status js_status_stop" id="u${brand.refrenceId}" href="javascript:$ROL.updateBrandAccountType('${brand.refrenceId}')"><i class="iconfont">&#xe624;</i><span>冻结</span></c:when>   
                              	<c:otherwise></c:otherwise>
                            </c:choose>
	                        </td>
	                    </tr>
	                    </c:forEach>
	                </table>
	            </div>
	            <div class="add_account_form" style="display: none;" id="add_form" >
	                <div class="ui-head"> 
	                    <h3>新建子账号</h3>
	                </div>
	                <form:form class="ui-form" action="" id="newAccountForm" data-widget="validator">
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            手机号：
	                        </label>
	                        <input type="text" class="ui-input" name="userMobile" id="userMobile" required data-display="手机号" data-rule="mobile" maxlength="11"/>
	                    </div>
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            邮箱：
	                        </label>
	                        <input value=" " autocomplete="off" type="text" class="ui-input" name="userMail" id="userMail" data-display="邮箱" data-rule="email"/>
	                    </div>
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            密码：
	                        </label>
                            <input autocomplete="off" type="password" class="ui-input" name="userPwd" id="userPwd" required data-display="密码" data-errormessage-minlength="长度必须大于6并且小于20" data-errormessage-maxlength="长度必须大于6并且小于20" minlength="6" maxlength="20" value="" />
	                    </div>
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            部门：
	                        </label>
	                        <div class="inline-block">
	                            <select class="ui-select js_select" name="deptId" id="deptId" required data-errormessage-required="请选择部门"/>
	                                <option value="">请选择部门</option>
				                    <c:forEach items="${deptList}" var="dept">
				                        <option value="${dept.refrenceId}">${dept.deptName}</option>
				                    </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            姓名：
	                        </label>
	                        <input type="text" class="ui-input" name="userName" id="userName" required data-display="姓名" maxlength="16"/>
	                    </div>
	                    <div class="ui-form-item">
	                        <label class="ui-label" for="">
	                            角色：
	                        </label>
	                        <div class="inline-block">
	                            <select class="ui-select js_select" name="roleId" id="roleId" required data-errormessage-required="请选择角色"/>
	                                <option value="">请选择角色</option>
				                    <c:forEach items="${roleList}" var="role">
				                         <option value="${role.refrenceId}">${role.roleName}</option>
				                     </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="submit_button">
	                        <button class="ui_button ui_button_morange">确认</button>
	                    </div>
	                </form:form>
	            </div>
                <div class="add_account_form" style="display: none;" id="update_form" >
                    <div class="ui-head">
                        <h3>修改子账号</h3>
                    </div>
                    <form:form class="ui-form" action="" id="updateAccountForm" data-widget="validator">
                    	<input type="hidden" name="refrenceId" id="refrenceId"/>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                手机号：
                            </label>
                            <input type="text" readonly class="ui-input" value="" name="userMobile" id="userMobile" required data-display="手机号" data-rule="mobile" maxlength="11"/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                邮箱：
                            </label>
                            <input autocomplete="off" type="text" class="ui-input" name="userMail" id="userMail" data-display="邮箱" data-rule="email"/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                密码：
                            </label>
                            <input autocomplete="off" type="password" class="ui-input" name="userPwd" id="upuserPwd" required data-display="密码" value=""/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                部门：
                            </label>
                            <div class="inline-block">
                                <select class="ui-select js_select" name="upDeptId" id="deptId" required data-errormessage-required="请选择部门"/>
                                <option value="">请选择部门</option>
                                <c:forEach items="${deptList}" var="dept">
                                    <option value="${dept.refrenceId}">${dept.deptName}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                姓名：
                            </label>
                            <input type="text" class="ui-input" name="userName" id="userName" required data-display="姓名" maxlength="16"/>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label" for="">
                                角色：
                            </label>
                            <div class="inline-block">
                                <select class="ui-select js_select" name="upRoleId" id="roleId" required data-errormessage-required="请选择角色"/>
                                <option value="">请选择角色</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.refrenceId}">${role.roleName}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="submit_button">
                            <button class="ui_button ui_button_morange">确认</button>
                        </div>
                    </form:form>
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
    var department = {
        init: function(){
            this.depOperate();
            this.addCount();
            this.updateCount();
        },
        addCount: function(){
            seajs.use(['dialog'], function(Dialog) {

                var d1 = new Dialog({
                    trigger: '#addAccount',
                    content: "#add_form",
                    width: 542
                });
                d1.after('show', function() {
                    resetValidatorError("#newAccountForm");
                	$("#newAccountForm")[0].reset();
                    $("#deptId_div span").html("请选择部门");
                    $("#roleId_div span").html("请选择角色");
                    $('#add_form').show();
                });
				
				//初始化表单
                  seajs.use(['validator','widget'], function(Validator,Widget) {
                    Widget.autoRenderAll();
                    //得到表单对象
                    var Core = Validator.query('#newAccountForm');
                    Core.set("autoSubmit",false);
                    Core.on('formValidated', function(error, message, elem) {
                        if(!error){
                            $ROL.insertSumUserm();
                        }
                    });
                }); 

            });
        },
        updateCount: function(){

            seajs.use(['dialog'], function(Dialog) {

                var d1 = new Dialog({
                    trigger: '.js_update',
                    content: "#update_form",
                    width: 542
                });
                d1.after('show', function() {
                	var btn = this.activeTrigger;
                    $("#updateAccountForm")[0].reset();
                    $("#upDeptId_div span").html("请选择部门");
                    $("#upRoleId_div span").html("请选择角色");
                    var brandId=btn.attr("data_brandId");
                    resetValidatorError("#update_form");
                    $.post("${ctx}/brand/role/sunUser",{refrenceId:brandId},function(data){
				   		if(data.code==zttx.SUCCESS){
				   			$("#update_form #refrenceId").val(data.object.refrenceId);
				   			$("#update_form #userMobile").val(data.object.userMobile);
				   			$("#update_form #userMail").val(data.object.userMail);
				   			$("#update_form #upuserPwd").val(data.object.userPwd);
				   			$("#update_form #deptId").val(data.object.deptId);
                           	$("#update_form #userName").val(data.object.userName);
				   			$("#update_form #roleId").val(data.object.roleId);
							$('#update_form').show();
                            renderSelect("#update_form #deptId");
                            renderSelect("#update_form #roleId");
				   		}else{
				   			remind("error",data.message);  
				   		}
				   	},"json");
                });

                //初始化表单
                seajs.use(['validator','widget'], function(Validator,Widget) {
                    Widget.autoRenderAll();
                    Validator.addRule('upPassword',function(options){
                        var v = options.element.val();
                        console.log(v.length);
                        if(v.length != 32 && v.length > 20 || v.length < 6 ){
                        	return false;
                        }else{
                            return true;
                        }
                    }, '长度必须大于6并且小于20');

                    //得到表单对象
                    var Core = Validator.query('#updateAccountForm');
                    Core.set("autoSubmit",false);

                    Core.addItem({
                        element: "#upuserPwd",
                        rule: "upPassword"
                    })

                    Core.on('formValidated', function(error, message, elem) {
                        if(!error){
                            $ROL.updateSumUserm();
                        }
                    });
                });

            });
        },
        depOperate: function(){
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

            $("#addDep").on("click",function(){
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
                	remind("error","请输入部门名称");
                    return;
                }

                if(val.length > 32){
                    remind("error","部门名称长度不能超过32");
                    return;
                }
                
                var url="insertDept";
                var data = {deptName:val};
                if(operate == "update"){
                	url="updateDept";
                	var refrenceId = $.trim(oinput.attr("data"));
                	data={deptName:val,refrenceId:refrenceId};
                }
                
                //异步处理
                $.ajax({
        			type : "POST",
        			url : url,
        			data : data,
        			dataType: "json",
        			success : function(json) {
        				if(zttx.SUCCESS==json.code){
	        			    if(operate == "insert"){
	                    		$("#createInput").hide();
				            }
			                var new_li = '<span class="fl data_name fn-text-overflow">'+val+'</span><div class="fr operate"><a href="javascript:;" class="edit_icon iconfont">&#xe618;</a><a href="javascript:;" class="del_icon iconfont">&#xe602;</a></div>';
			                //如果是插入
			                if(operate == "insert"){
			                    $('<li class="item" data="'+json.object+'">'+new_li+'</li>').data("id",json.object).appendTo($(".dep_list ul"));;
			                    //下拉框数据添加
			                    $(".select-department").append($("<option>").val(json.object).text(val));
			                    $("#newAccountForm #deptId").append($("<option>").val(json.object).text(val));
			                }else if(operate == "update"){
                                _this.cancelMask(_This);
                                op.data("id",json.object).html(new_li);
                                op.removeClass("edit");
			                    $(".select-department").find("[value='" + json.object + "']").text(val);
			                    $("#updateAccountForm #deptId").find("[value='" + json.object + "']").text(val);
                            }
        				}else
        					remind("error",json.message);
        			},
        			error:function(XMLHttpRequest, textStatus, errorThrown){
        				remind("error",errorThrown);
        			}
        		});
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
                if($("#createInput").length>0){
                    $("#createInput").remove();
                }
                oli.removeClass("current");
                oli.html('<input maxlength="32" class="createInput" data="'+id+'" value="'+old_val+'" /><a class="createSave" data-operate="update" href="javascript:;">保存</a></div>');
                _this.doInsert();
            });
        },
        doDel: function(){
            var _this = this;
            $(".dep_data_list").on("click",".del_icon",function(){
            	var op =  $(this).parents("li");
            	confirmDialog("确定是否要删除？",function(){
	                var id = $.trim(op.attr("data"));
	                $.ajax({
	       				type : "POST",
	       				url : "delBrandDept",
	       				data : {refrenceId:id},
	       				dataType: "json",
	       				success : function(json) {
	       					if(zttx.SUCCESS==json.code){
	       						op.remove();
	                			$(".select-department").find("option[value='" + id + "']").remove();
	                			$("#newAccountForm #deptId").find("option[value='" + id + "']").remove();
	                			$("#updateAccountForm #deptId").find("option[value='" + id + "']").remove();
	                			remind("success","部门删除成功");
	       					}else
	       						remind("error",json.message);
	       				},
	       				error:function(XMLHttpRequest, textStatus, errorThrown){
	       					remind("error",errorThrown);
	       				}
	       			});
            	});
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
                "z-index": "1"
            });
            if($("#mask").length > 0){
                $("#mask").hide();
            }
        }
    };
    department.init();
   // $('input.txt').after('<a class="Validform_checktip">');
</script>
    
</body>
</html>