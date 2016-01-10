<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-消息中心-系统消息</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/message.css" rel="stylesheet" />
</head>
<body>
    <!-- #include file="母版页.html" -->  
    <div class="container">
        <jsp:include page="/common/menuInfo/mainmenu"/>
    <!--完成-->

        <div class="em100">
            <div class="main clearfix">
                <!--侧边导航-->
                <jsp:include page="/common/menuInfo/sidemenu"/>
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
                                <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">消息中心</span>
                                <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                            
                        </div>
                    </div>
                    <div class="inner">
                        <form:form id="js-msg-form" action="${ctx}/dealer/message/system" method="post">
                            <div class="panel-table clearfix">
                                <input type="hidden" id="searchWord" name="searchWord" value="${dealerMessageModel.searchWord}" />
                                <input type="hidden" id="selectMsgIds" name="selectMsgIds" value="" />
                                <input type="hidden" id="msgStatus" name="msgStatus" value="${dealerMessageModel.msgStatus}" />
                                <input type="hidden"  name="menuId" value="<%=request.getParameter("menuId")%>" />
                                <div class="panel-table-title">
                                    <div class="fl">
                                        <input class="ui-checkbox" id="checkAll" type="checkbox" /><label class="" for="checkAll">全选</label>
                                        <a id="btn_del" class="btn btn-n ml10" href="#"><i class="iconfont">&#xe619;</i> 删除</a> <a id="btn_read" class="btn btn-n" href="#"><i></i>标记已读</a> <a id="btn_clear" class="btn btn-n" href="#"><i></i>清空</a>
                                    </div>
                                    <div class="fr">
                                        <div class="inline-block pr">
                                            <select id="select1" name="searchType" class="js_small_select ui-select js-select">
                                                <option value="0">全部</option>
                                                <c:if test="${messageType != null }">
                                                    <c:forEach items="${messageType }" var="message">
                                                        <option value="${message.dictValue }" ${dealerMessageModel.searchType == message.dictValue ? 'selected="selected"':''}>${message.dictValueName }</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                        <div class="inline-block">
                                            <input type="text" id="searchWordA" class="ui-input" placeholder="关键字" value="${dealerMessageModel.searchWord}" />
                                        </div>
                                        <div class="inline-block">
                                            <input id="btn_search" type="button" class="ui-button ui-button-lwhite" value="搜 索" />
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-table-content">
                                    <table class="ui-table">
                                        <thead>
                                        <tr>
                                            <th class="cell-1"></th>
                                            <th class="cell-2">类别</th>
                                            <th class="cell-3">日期</th>
                                            <th class="cell-4" style="text-align: center;">标题</th>
                                            <th class="cell-5">标记</th>
                                            <th class="cell-4">发件人</th>
                                            <th class="cell-6">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${fn:length(dataList) == 0}">
                                            <tr>
                                                <td colspan="7" style="text-align:center">暂无数据</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="obj" items="${dataList}">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" class="chk" msgId="${obj.refrenceId}" />
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${obj.msgCate eq 1}">系统消息</c:when>
                                                        <c:when test="${obj.msgCate eq 2}">站内消息</c:when>
                                                        <c:when test="${obj.msgCate eq 3}">订单消息</c:when>
                                                        <c:otherwise>未定义${obj.msgCate}</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><span>${fns:getTimeFromLong(obj.createTime,"yyyy.MM.dd")}</span></td>
                                                <td class="cell-4"><a class="js-btn-browse link" href="javascript:void(0)" msgId="${obj.refrenceId}">${obj.msgTitle}</a></td>
                                                <td>
                                                    <c:choose>
                                                        <%--<c:when test="${not empty obj.dealerReadList}"><span class="c-r">已读</span></c:when>--%>
                                                        <c:when test="${obj.readed}"><span class="c-r">已读</span></c:when>
                                                        <c:otherwise><span class="c-b">未读</span></c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><span>${obj.brandName}</span></td>
                                                <td><a class="js-btn-browse link" href="javascript:void(0)" msgId="${obj.refrenceId}">查看</a> <a class="js-btn-delete link" href="javascript:void(0)" msgId="${obj.refrenceId}">删除</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="7">
                                                <page:page form="$('#js-msg-form')" page="${page}"></page:page>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </form:form>
                    </div>

            	</div>
        	</div>
    	</div>
    	<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        $(function () {

            $("#select1").val("${dealerMessageModel.searchType}" != "" ? "${dealerMessageModel.searchType}" : 0);
            
            //全选
            checkAll("#checkAll",".ui-table .chk");
            
            //获取已选择的消息ID
            function getSelectMsgIds()
            {
            	var selectMsgIds = "";
            	
            	
				$(".panel-table-content tbody .chk").each(function(){
            		if(!$(this).is(":checked"))
            		{
            			return;
            		}
            		if(selectMsgIds != "")
            		{
            			selectMsgIds += ",";
            		}
            		selectMsgIds += $(this).attr("msgId");
            	});
            	return selectMsgIds;
            }
            
            //选择删除按钮-点击事件
            $("#btn_del").bind("click", function(){
            	var selectMsgIds = getSelectMsgIds();
            	if(selectMsgIds == "")
            	{
            		confirmDialog({'title':"提示",'content':'请选择消息'},function(){},false,function(){},true);
            		return;
            	}
            	confirmDialog({'title':"提示",'content':'确认删除?'},function(){
            		$("#selectMsgIds").val(selectMsgIds);
                	$("#js-msg-form").attr("action", "${ctx}/dealer/message/delete").submit();
            	},false);
            	
            });
            
          	//标记已读按钮-点击事件
            $("#btn_read").bind("click", function(){
            	var selectMsgIds = getSelectMsgIds();
            	if(selectMsgIds == "")
            	{
            		confirmDialog({'title':"提示",'content':'请选择消息'},function(){},false,function(){},true);
            		return;
            	}
            	confirmDialog({'title':"提示",'content':'确认已读?'},function(){
            		$("#selectMsgIds").val(selectMsgIds);
                	$("#js-msg-form").attr("action", "${ctx}/dealer/message/read").submit();
            	},false);
            });
          
            //清空按钮-点击事件
            $("#btn_clear").bind("click", function(){
            	confirmDialog({title:"提示",content:"确定清除所有消息"},function(){
            		$("#js-msg-form").attr("action", "${ctx}/dealer/message/clear").submit();
                });
            }); 
          
            //搜索按钮-点击事件
            $("#btn_search").bind("click", function(){
            	var searchWord = $("#searchWordA").val();
            	if(searchWord == "关键字")
            	{
            		searchWord = "";
            	}
            	$("#searchWord").val(searchWord);
            	$("#js-msg-form").attr("action", "${ctx}/dealer/message/system").submit();
            });

            //删除按钮-点击事件
            $(".panel-table-content tbody .js-btn-delete").bind("click", function(){
            	var msgid=$(this).attr("msgId");
            	confirmDialog({title:"提示",content:"确定删除吗"},function(){
                	$("#selectMsgIds").val(msgid);
                	$("#js-msg-form").attr("action", "${ctx}/dealer/message/delete").submit();
                });
            });
            
            //查看按钮-点击事件
            $(".panel-table-content tbody .js-btn-browse").bind("click", function(){
            	var msgId = $(this).attr("msgId");
            	$("#js-msg-form").attr("action", "${ctx}/dealer/message/readMsg/" + msgId).submit();
            });
        });
    </script>

</body>
</html>
