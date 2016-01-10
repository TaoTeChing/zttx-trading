<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修-留言互动</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />
 	<link href="${res}/styles/market/brandviewbase.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/market/decoration.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/common/validformStyle.css" rel="stylesheet" type="text/css" />
    <style>
        <!--
        html { overflow: -moz-scrollbars-vertical; }
        -->
    </style>
    <jsp:include page="/market/header_css" >
        <jsp:param name="brandesId"  value="${brandesId}" />
    </jsp:include>
</head>

<body>
    <!-- top -->
    <jsp:include page="${ctx}/common/top" />

    <!-- head -->
    <jsp:include page="/market/header">
        <jsp:param name="brandesId" value="${brandesId}"/>
        <jsp:param name="url" value="/leaveMessage/"/>
    </jsp:include>
    
    <div class="listbody clear">
        <div class="sidebar-l">
            <h3 class="title">留言互动</h3>
            <div class="message font">
                <form:form id="leaveMessageForm" action="${ctx}/dealer/message/leaveToBrand" method="post">
                    <table>
                        <tr>
                           	<input type="hidden" name="dealerId" value="${dealerUserm.refrenceId}" />
                           	<input type="hidden" name="brandId" value="${brandId}" />
                           	<input type="hidden" name="senderName" value="${dealerUserm.userName}" />
                           	<input type="hidden" name="phone" value="${dealerUserm.userMobile}" />
                            <td colspan="3"><span class="uname">用户名：${dealerUserm.userName}</span></td>
                        </tr>
                        <tr>
                            <td style="line-height: 35px; _line-height: 43px;">
                                <span class="uname f-l">留言类型：</span>
                                <span class="radio f-l">
                                    <label>
                                    <input type="radio" id="radio1" name="netMsgCate" value="合作加盟" class="messageradio f-l" />合作加盟</label>
                                </span>
                                <span class="radio f-l ml5">
                                    <label>
                                    <input type="radio" id="radio2" name="netMsgCate" value="建议与意见" checked="checked" class="messageradio f-l" />建议与意见</label>
                                </span>
                                <span class="radio f-l ml5">
                                    <label>
                                    <input type="radio" id="radio3" name="netMsgCate" value="其他" class="messageradio f-l" />其他</label>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <!--<textarea name="" cols="" rows="" class="messagetextarea" id="test" readonly>
									1.我对贵公司服装感兴趣，请尽快寄资料给我！
									2.请问我所在的地区有加盟商了吗？
									3.我想详细的了解加盟流程，请电话联系我！
									4.做为代理加盟商能得到哪些支持？
									5.请问投资所需要的费用有哪些？
									6.请问哪里有你们的店面形象可以看？
                                </textarea>-->
                                <textarea name="content" datatype="*" class="messagetextarea" id="test" ${isLogin ? '':'readonly'}>${isLogin ? '':'您需要登录后才可以留言'}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                            	<c:choose>
                            		<c:when test="${isLogin}">
		                                <input id="submitBt" style="cursor: pointer" type="button" value="提交留言" class="messagebutton"/>
                            		</c:when>
                            		<c:otherwise>
                            			<input type="button" value="提交留言" class="messagebutton"/>
                            		</c:otherwise>
                            	</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><span class="tip"><em>*</em> 温馨提示：每一位发表评论者对其发布的信息、言论承担全部法律责任，请务必遵守良好的社会公德和国家法律法规的规定。</span></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>

	    <div class="sidebar-r">
	    	<!-- 关注度信息 -->
			<jsp:include page="${ctx}/market/brand/attentionNum" >
				<jsp:param name="brandesId"  value="${brandesId}" />
			</jsp:include>
			
			<!-- 品牌信息 -->
			<jsp:include page="${ctx}/market/brand/brandesInfo" >
				<jsp:param name="brandesId"  value="${brandesId}" />
			</jsp:include>
			
			<!-- 品牌商基本信息 -->
			<jsp:include page="${ctx}/market/brand/brandInfo" >
				<jsp:param name="brandId" value="${brandId}"/>
				<jsp:param name="brandesId" value="${brandesId}"/>
			</jsp:include>
			
			<!-- 品牌商旗下所有品牌信息 -->
			<jsp:include page="${ctx}/market/brand/brandesList">
				<jsp:param name="brandId" value="${brandId}"/>
			</jsp:include>
			
			<%-- <!-- 在线联系信息 -->
			<jsp:include page="${ctx}/market/brand/contactOnline" >
				<jsp:param name="brandesId"  value="${brandesId}" />
			</jsp:include> --%>
	    </div>
    </div>
    
	<!-- footer -->
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>

    <script src="${res}/scripts/jquery.min.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <script src="${res}/scripts/market/jquery.elastislide.js"></script>
    <script src="${res}/scripts/market/last.js" type="text/javascript"></script>
    <c:set var="SUCCESS" value="<%=com.zttx.web.consts.DealerConst.SUCCESS.code%>"></c:set>
    <script src="${res}/scripts/plugin/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript">
	  	$(function(){
	  		$("#leaveMessageForm").Validform({
	  			tiptype : 4,
	  			btnSubmit:"#submitBt",
	  			postonce : true,
				ajaxPost : true,
				callback : function(data) {
				    if(data.code==${SUCCESS})
				    {
				    	alert("留言成功");
				    	window.location.reload(true);
				    }else{
				    	alert("留言失败");
				    }
				}
	  		});
		})
	</script>
</body>
</html>
