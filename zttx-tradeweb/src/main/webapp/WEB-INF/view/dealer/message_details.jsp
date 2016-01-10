<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>阅读消息</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/message.css" rel="stylesheet" />
    <style>
    	.message-detail{
    		padding: 10px 20px;
    	}
    </style>
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
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
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="javascript:void(0);" onclick="returnList();" title="">消息中心</a> > <span class="bb">系统消息</span>
                            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="message-detail">
                    <a href="javascript:void(0);" onclick="returnList();"  class="ui-button ui-button-mred">返回列表</a>
                            <div class="message-title ta-c">
                                <h3>${dealerMessage.msgTitle}</h3>
                            </div>
                            <div class="message-info clearfix">
                                <div class="fl">
                                 	   发件人:${dealerMessage.brandName}
                                </div>
                                <div class="fr">
                         		          类型:<span class="mr20">
                                    <c:choose>
                                    	<c:when test="${dealerMessage.msgCate eq 1}">系统消息</c:when>	
                                    	<c:when test="${dealerMessage.msgCate eq 2}">站内消息</c:when>
                                    	<c:when test="${dealerMessage.msgCate eq 3}">订单消息</c:when>
                                    	<c:otherwise>未定义${obj.msgCate}</c:otherwise>
                                    </c:choose>
                                    </span> 日期: <span >${fns:getTimeFromLong(dealerMessage.createTime,"yyyy-MM-dd")}</span>
                                </div>
                            </div>
                            <div class="message-content" style="word-break:break-all;">
                                ${dealerMessage.msgTextHtml}
                            </div>
                        </div>
                    </div>
					<form:form id="js-msgList" action="${ctx}/dealer/message/system" method="post">
						<%-- <input type="hidden" name="pageSize" value="${param.pageSize}" /> --%>
						<input type="hidden" name="currentPage" value="${param.currentPage}" />
						<input type="hidden" name="searchType" value="${param.searchType}" />
						<input type="hidden" name="searchWord" value="${param.searchWord}" />
						<input type="hidden" name="msgStatus" value="${param.msgStatus}" />
                        <input type="hidden"  name="menuId" value="<%=request.getParameter("menuId")%>" />
					</form:form>
                </div>
            </div>
       </div>
       <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        $(function () {
            var lists = $(".card-items li").click(function () { lists.removeClass("on").removeClass("nbd"); $(this).addClass("on").prev().addClass("nbd"); });

            //表格样式
            $(".panel-table-content tbody tr:odd").css("background-color", "#f9f9f9");

        });
        
        function returnList()
        {
        	$("#js-msgList").submit();
        }
    </script>

</body>
</html>
