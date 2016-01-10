<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
    <title>管理中心-我的消息-消息管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
        <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/message/list">我的消息</a>
            <span class="arrow">&gt;</span>
            <span class="current">消息管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <div class="mymessage">
            <div class="js_grademanage_menu">
                <ul class="clearfix">
                    <li<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 0}"> class="selected"</c:if> listType="0" searchType="0"><a href="#">全部消息</a></li>
                    <li<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 2}"> class="selected"</c:if> listType="0" searchType="2"><a href="#">站内消息</a></li>
                    <li<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 1}"> class="selected"</c:if> listType="0" searchType="1"><a href="#">系统消息</a></li>
                    <li<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 4}"> class="selected"</c:if> listType="0" searchType="4"><a href="#">在线留言</a></li>
                    <li<c:if test="${brandMessageModel.listType == 1 && brandMessageModel.searchType == 0}"> class="selected"</c:if> listType="1" searchType="0"><a href="#">我发送的消息</a></li>
                    <%--<li><a href="#">留言信息</a></li>--%>
                </ul>
            </div>
            <div class="js_grademanage_con">
                <div class="mymessage-msgman">
                    <form:form id="js-msg-form" class="ui-form clearfix" action="${ctx}/brand/message/list" method="post">
                    	<input type="hidden" id="selectMsgIds" name="selectMsgIds" value="" />
                    	<input type="hidden" id="listType" name="listType" value="${brandMessageModel.listType}" />
                    	<input type="hidden" id="searchType" name="searchType" value="${brandMessageModel.searchType}" />
                    	<input type="hidden" id="msgStatus" name="msgStatus" value="${brandMessageModel.msgStatus}" />
                    	<input type="hidden" id="sendBeginTime" name="sendBeginTime" value="${brandMessageModel.getSendBeginTimeString()}" />
                    	<input type="hidden" id="sendEndTime" name="sendEndTime" value="${brandMessageModel.getSendEndTimeString()}" />
                        <input type="hidden" name="menuId" value="<%=request.getParameter("menuId")%>"/><%--todo 锁定菜单 临时添加--%>

                        <div class="ui-form-item">
                            <label>
                                状态：
                            </label>
                            <div class="inline-block">
                                <select class="ui-select js_select" id="msgStatusA">
                                	 <c:if test="${messageStatus != null}">
					                    <c:forEach items="${messageStatus}" var="status">
					                    	 <option value="${status.dictValue}"<c:if test="${brandMessageModel.msgStatus == status.dictValue}"> selected="selected"</c:if>>${status.dictValueName}</option>
					                    </c:forEach>
				                    </c:if>

                                   <%--  <option value="0"<c:if test="${brandMessageModel.msgStatus == 0}"> selected="selected"</c:if>>全部</option>
                                    <option value="2"<c:if test="${brandMessageModel.msgStatus == 2}"> selected="selected"</c:if>>未查阅</option>
                                    <option value="1"<c:if test="${brandMessageModel.msgStatus == 1}"> selected="selected"</c:if>>已查阅</option> --%>


                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label>
                                信息类型：
                            </label>
                            <div class="inline-block">
                                <select class="ui-select js_select" id="searchTypeA">
                                    <option value="0"<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 0}"> selected="selected"</c:if> listType="0" searchType="0">全部消息</option>
                                    <option value="1"<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 2}"> selected="selected"</c:if> listType="0" searchType="2">站内消息</option>
                                    <option value="2"<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 1}"> selected="selected"</c:if> listType="0" searchType="1">系统消息</option>
                                    <option value="4"<c:if test="${brandMessageModel.listType == 0 && brandMessageModel.searchType == 4}"> selected="selected"</c:if> listType="0" searchType="4">在线留言</option>
                                    <option value="3"<c:if test="${brandMessageModel.listType == 1 && brandMessageModel.searchType == 0}"> selected="selected"</c:if> listType="1" searchType="0">我发送的消息</option>
                                </select>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label>
                                发送时间：
                            </label>
                            <div class="inline-block">
                                <input readonly="readonly" type="text" class="ui-input hasDatepicker" id="start-cal" value="${brandMessageModel.getSendBeginTimeString()}">
                                -
                                <input readonly="readonly" type="text" class="ui-input hasDatepicker" id="end-cal" value="${brandMessageModel.getSendEndTimeString()}">
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <a href="javascript:;" class="simple_button">搜索</a>
                        </div>
                    <table class="msgman-table">
                        <colgroup>
                            <col width="350" />
                            <col width="110" />
                            <col width="140" />
                            <col width="125" />
                            <col width="125" />
                            <col width="110" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>消息标题</th>
                                <th>状态</th>
                                <c:if test="${!(brandMessageModel.listType eq 1)}"><th>发送人</th></c:if>
                                <c:if test="${brandMessageModel.listType eq 1}"><th>收件人</th></c:if>
                                <th>消息类型</th>
                                <th>发送时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:choose>
                        		<c:when test="${!empty dataList}">
                        		<c:forEach var="obj" items="${dataList}">
                        		<tr>
                                	<td class="msgman-table-tdtl"><a href="javascript:void(0);" onclick="skipInfo('${obj.refrenceId}');">${obj.msgTitle}</a></td>
                                	<td>
                                		<c:choose>
                                        	<c:when test="${!(brandMessageModel.listType eq 1) && (obj.isReaded)}">已查阅</c:when>
                                        	<c:when test="${!(brandMessageModel.listType eq 1) && !(obj.isReaded)}">未查阅</c:when>
                                        	<c:when test="${brandMessageModel.listType eq 1 && (obj.isReaded)}">已查阅</c:when>
                                        	<c:when test="${brandMessageModel.listType eq 1 && !(obj.isReaded)}">未查阅</c:when>
                                        </c:choose>
                                	</td>
                                	<td class="msgman-table-tdtl">${obj.userName}</td>
                                	<td class="msgman-table-tdtl">
                                		<c:choose>
                                        	<c:when test="${obj.msgCate eq 1}">系统消息</c:when>
                                            <c:when test="${obj.msgCate eq 2}">站内消息</c:when>
                                            <c:when test="${obj.msgCate eq 3}">订单消息</c:when>
                                            <c:when test="${obj.msgCate eq 4}">在线留言</c:when>
                                            <c:otherwise>未定义${obj.msgCate}</c:otherwise>
                                        </c:choose>
                                	</td>
                                	<td>${fns:getTimeFromLong(obj.createTime,"yyyy-MM-dd")}</td>
                                	<td>
                                        <a href="javascript:void(0);" onclick="skipInfo('${obj.refrenceId}');" class="bluefont msgman-view">查看</a>
                                        <a href="javascript:;" msgId="${obj.refrenceId}" class="bluefont msgman-delete">删除</a>
                                    </td>
                            	</tr>
                        		</c:forEach>
                        		</c:when>
                        		<c:otherwise>
                        		<tr><td colspan="6" class="ta-c">暂无数据</td></tr>
                        		</c:otherwise>
                        	</c:choose>

                        </tbody>
                    </table>
                    <c:if test="${!empty dataList}">
                    <page:page form="$('#js-msg-form')" page="${page}"></page:page>
                    </c:if>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/myMessage.js"></script>
<script>
    myMessagemsgman.init();

    $(function(){

    	// 点击标签
    	$(".mymessage li").bind("click", function(){
    		// 信息类型
    		$("#listType").val($(this).attr("listType"));
    		$("#searchType").val($(this).attr("searchType"));
        	// 设置返回为第一页
    		$("#currentPage").val("1");
        	// 发送
    		$("#js-msg-form").submit();
    	});

    	// 搜索按钮-点击事件
        $("#js-msg-form .simple_button").bind("click", function(){
        	// 消息状态
        	$("#msgStatus").val($("#msgStatusA").val());
        	// 信息类型
        	$("#listType").val($("#searchTypeA option:selected").attr("listType"));
        	$("#searchType").val($("#searchTypeA option:selected").attr("searchType"));
        	// 发送时间
        	$("#sendBeginTime").val($("#start-cal").val());
        	$("#sendEndTime").val($("#end-cal").val());
        	// 设置返回为第一页
    		$("#currentPage").val("1");
        	// 发送
        	$("#js-msg-form").submit();
        });

      	//删除按钮-点击事件
        $(".msgman-table tbody .msgman-delete").bind("click", function(){
        	var $this = $(this);
        	confirmDialog("是否删除消息？",function(){
            	$("#selectMsgIds").val($this.attr("msgId"));
            	$("#js-msg-form").attr("action", "${ctx}/brand/message/delete").submit();
        	});
        });

    });

    function skipInfo(id)
    {
    	$("#js-msg-form").attr("action", "${ctx}/brand/message/readMsg/" + id).submit();
    }


</script>
</body>
</html>