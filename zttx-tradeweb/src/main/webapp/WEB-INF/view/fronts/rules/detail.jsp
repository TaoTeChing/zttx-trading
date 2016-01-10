<%--
  Created by IntelliJ IDEA.
  User: yefeng
  Date: 2014/4/28
  Time: 08:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${rulesInfo.articleTitle }–规则中心 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637, 品牌超级代理,品牌加盟,品牌代理"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/global.css"/>
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/index.css"/>
    <%--<jsp:include page="/WEB-INF/view/include/index_javascript.jsp" />
    <script src="${res }/scripts/rules/common.js"></script>--%>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <%-- <div class="header">
		    <jsp:include page="${ctx}/common/top" />
		    <jsp:include page="/WEB-INF/view/rules/_search.jsp" />
		    <jsp:include page="/WEB-INF/view/rules/_navigation.jsp" >
		    	<jsp:param value="${rulesCate.refrenceId }" name="id"/>
		    	<jsp:param value="${rulesCate.parentId }" name="parentId"/>
		    </jsp:include>
		</div>--%>
    <div class="main">
        <div class="main-left">
            <jsp:include page="left.jsp"/>
        </div>
        <div class="main-right">
            <div class="main-content">
                <%-- <jsp:include page="_search.jsp" /> --%>
                <div class="cont-details">
                    <c:choose>
                        <c:when test="${rulesInfo != null }">
                            <div class="head">
                                <h3>${rulesInfo.articleTitle }</h3>

                                <div class="version">
	                           <span class="pub-date">
	                               <label for="">规则发布时间：</label>
	                               <span>${fns:getTimeFromLong(rulesInfo.createTime, 'yyyy年MM月dd日') }</span>
	                           </span>
                                    <!--
	                           <c:if test="${rulesInfoLog != null }">
		                            <a href="${ctx }/common/rules/info/history?rulesId=${rulesInfo.refrenceId}" class="history-ver">
		                                	同一规则历史版本：${fns:getTimeFromLong(rulesInfoLog.createTime, 'yyyy年MM月dd日') }
		                            </a>
	                             </c:if>
	                              -->
                                </div>
                            </div>
                            <div class="content">
                                    ${rulesInfo.articleText }
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="head">
                                <h3>该文章不存在或者已经被删除了</h3>

                                <div class="version">
	                           <span class="pub-date">
	                               <label for=""></label>
	                               <span></span>
	                           </span>
                                    <a href="javascript:void(0);" class="history-ver">
                                    </a>
                                </div>
                            </div>
                            <div class="content">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <%--<div class="em100 mt10 mb10 clearfix">
        <div class="location">
            <label for="">当前所在页:</label>
            <a  href="${ctx }/rules/">规则中心</a>
            >
             <c:forEach items="${parentCates }" var="parentCate">
                <c:if test="${not empty parentCate }">
                     <a href="${ctx }/common/rules/list?cateId=${parentCate.refrenceId }&cateType=${parentCate.cateType}">${parentCate.cateName }</a>
                     >
                </c:if>
            </c:forEach>
            <span>${rulesCate.cateName }</span>
        </div>
    </div>
    <div class="em100 clearfix mb20">
          <jsp:include page="/WEB-INF/view/rules/rules_left.jsp" >
             <jsp:param value="${rulesCate.refrenceId }" name="id"/>
         </jsp:include>


        <div class="article fr">
            <div class="details">

            <c:choose>
                <c:when test="${rulesInfo != null }">
                    <div class="head">
                        <h3>${rulesInfo.articleTitle }</h3>
                        <div class="version">
                           <span class="pub-date">
                               <label for="">规则发布时间：</label>
                               <span>${fns:getTimeFromLong(rulesInfo.createTime, 'yyyy年MM月dd日') }</span>
                           </span>
                           <!--
                           <c:if test="${rulesInfoLog != null }">
                                <a href="${ctx }/common/rules/info/history?rulesId=${rulesInfo.refrenceId}" class="history-ver">
                                        同一规则历史版本：${fns:getTimeFromLong(rulesInfoLog.createTime, 'yyyy年MM月dd日') }
                                </a>
                             </c:if>
                              -->
                        </div>
                    </div>
                    <div class="content">
                        ${rulesInfo.articleText }
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="head">
                        <h3>该文章不存在或者已经被删除了</h3>
                        <div class="version">
                           <span class="pub-date">
                               <label for=""></label>
                               <span></span>
                           </span>
                            <a href="javascript:void(0);" class="history-ver">
                            </a>
                        </div>
                    </div>
                    <div class="content">
                    </div>
                </c:otherwise>
            </c:choose>



            </div>
            <div class="question mt10">

                <jsp:include page="/WEB-INF/view/help/seekHelp.jsp" />

               &lt;%&ndash;  <h3>以上内容是否解决您的问题？</h3>
                <div class="content">
                    <div class="support">
                        <span>是，已经解决。</span>
                        <a href="${ctx }/help/index">查看更多帮助</a>
                    </div>
                    <div class="oppose">
                        <span>否，尚未解决。</span>
                        <a href="javascript:void(0);" class="tell-us">告诉我们原因</a>
                    </div> &ndash;%&gt;
                   <!--  <div class="online-consult">
                        <h4>在线咨询？</h4>
                        <div>
                            如果上述答案没有解决您的问题，您可以
                            <a href="javascript:void(0);">在线咨询</a>
                        </div>
                    </div> -->
                </div>
            </div>
        </div>
    </div>
</div>--%>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res }/scripts/jquery.min.js"></script>
<script src="${res }/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res }/scripts/seajs_config.js"></script>
<script src="${res }/scripts/src/common/base-init.js"></script>
<script src="${src }/fronts/help/help.js"></script>
<script>
    seajs.use(["djmenu"], function(){
        $('#tree').djMenu({
            url: "",         //请求地址
            parameter: "",   //请求参数
            val: "id",         //请求参数的 data-val
            templateId: "nav_list"   //需要使用模版的id
        });
    });
</script>
<%--<a class="ui-scrolltop" title="返回顶部">返回顶部</a>--%>
<!--告诉我们原因模板-->
<%-- <div style="display: none">
    <div id="reason">
        <div class="ui-head">
            <h3>您好 欢迎光临8637品牌超级代理！</h3>
        </div>
        
        <%
			CSRFTokenManager cSRFTokenManager = SpringUtils.getBean("csrfTokenManager");
			String s = cSRFTokenManager.getTokenForRequest(request);
		 %>
		 
        <div class="reasonForm">
        	<form:form action="#" id="reasonForm" data-widget="validator">
                <div class="field-content ui-form-item">
                    <label for="name">姓名</label>
                    <input type="text" class="filed-input" id="name" name="nickName" required data-errormessage-required="请填写您的姓名"/>
                </div>
                <div class="field-content ui-form-item">
                    <label for="mobile">手机号码</label>
                    <input type="text" class="filed-input" id="mobile" name="mobile" required data-rule="mobile" data-errormessage-required="请填写您的电话号码"/>
                </div>
                <div class="field-content leave-msg ui-form-item">
                    <label for="leave-msg">留言</label>
                    <textarea name="questionDesc" id="leave-msg" cols="30" rows="10" placeholder="留言字数不超过1000字" maxlength="1000"></textarea>
                </div>
                <div class="btns">
                    <input type="button" id="subBtn" value="提交" class="save"/>
                    <input type="button" value="取消" class="cancel"/>
                </div>
           </form:form>
        </div>
    </div>
</div> --%>
<!-- <script src="/scripts/jquery.min.js"></script>
<script src="/scripts/common/base-init.js"></script>
<script src="/scripts/rules/common.js"></script>
<script src="/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="/scripts/seajs_config.js"></script> -->
<!-- <script>
    seajs.use(['dialog','validator', "widget",'$'],function(Dialog,Validator,Widget,$) {
        //告诉我们原因dialog
        var dialog = new Dialog({
            trigger: ".tell-us",
            width: "600px",
            content: $("#reason"),
            effect: "fade"
        });

        $("#reasonForm .cancel").click(function(){
            dialog.hide();
        });

        /*表单重置*/
        dialog.before('show',function(){
            $(".ui-form-item").removeClass("ui-form-item-error");
            $(".ui-form-explain").empty();
            $("#reasonForm")[0].reset();
        });

        Widget.autoRenderAll();

        var Core = Validator.query("#reasonForm");

        Core.set("autoSubmit", false);

        Core.on('formValidated', function (error) {
            if (!error) {
                //这里异步提交数据
            }
        });

    });
    
   $('#subBtn').on('click', function (){
		$.ajax({
		    type: "POST",
	   		url: "${ctx}/apply/subQuestion",
	   		data: $('#reasonForm').serialize(),
		    cache: false,
		    dataType: "json",
		    success: function(json){
		    	if(json.code == 126000){
						alert("提交已提交。可进入帮助中心查看问题的进度");
					}else{
						alert(json.message);
				}
		  	}
		});
	})
    
</script> -->
</body>
</html>
