<%--
  Created by IntelliJ IDEA.
  User: yefeng
  Date: 2014/4/30
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${helpInfo.title } - 帮助中心 – 8637品牌超级代理</title>
    <meta name="keywords" content="8637, 品牌超级代理,品牌加盟,品牌代理" />
    <meta name="description" content="" />
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/global.css"/>
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/index.css"/><%--
    <jsp:include page="/WEB-INF/view/include/index_javascript.jsp" />
	<script src="${res }/scripts/help/help.js"></script>--%>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <div class="main">
        <div class="main-left">
            <jsp:include page="left.jsp"></jsp:include>
        </div>
        <div class="main-right">
            <div class="main-content">
                <%-- <jsp:include page="_search.jsp"></jsp:include> --%>
                <div class="cont-details">
                    <c:choose>
                        <c:when test="${helpInfo != null }">
                            <div class="details">
                                <div class="head">
                                    <h3>${fns:trimLongStringWithEllipsis(helpInfo.title, 60, '...') }</h3>
                                    <div class="version">
		                           <span class="pub-date">
		                               <label for="">帮助发布时间：</label>
		                               	<span>${fns:getTimeFromLong(helpInfo.createTime, 'yyyy年MM月dd日') }</span>
		                           </span>

                                    </div>
                                </div>
                                <div class="content">
                                        ${helpInfo.htmlText }
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <div class="details">
                                <div class="head">
                                    <h3>该文章不存在或者已经被删除</h3>
                                    <div class="version">
		                           <span class="pub-date">
		                               <label for=""></label>
		                               		<span></span>
		                           </span>

                                    </div>
                                </div>
                                <div class="content">
                                </div>
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
                <a href="${ctx }/help/index">帮助中心</a>
                >
                <c:forEach items="${parentCates }" var="parentCate">
                	<c:if test="${ not empty parentCate.helpName }">
	                	<a href="${ctx }/help/list?cateId=${parentCate.refrenceId}">${parentCate.helpName }</a>
	                	 >
                	</c:if>
                </c:forEach>
                <span>${helpCate.helpName }</span>
            </div>
        </div>--%>
        <%--<div class="em100 clearfix mb20">
        
        
         <jsp:include page="/WEB-INF/view/help/left.jsp" />
            
            
            
            
            <div class="article fr">
            

               
                <div class="question mt10">
                	<jsp:include page="/WEB-INF/view/help/seekHelp.jsp" />
                
                &lt;%&ndash;   <%@include file="/WEB-INF/tags/help/seekHelp.tag" %> &ndash;%&gt;
                    
                <!--     <h3>以上内容是否解决您的问题？</h3>
                    <div class="content">
                        <div class="support">
                            <span>是，已经解决。</span>
                            <a href="javascript:void(0);">查看更多帮助</a>
                        </div>
                        <div class="oppose">
                            <span>否，尚未解决。</span>
                            <a href="javascript:void(0);" class="tell-us">告诉我们原因</a>
                        </div>
                        <div class="online-consult">
                            <h4>在线咨询？</h4>
                            <div>
                                如果上述答案没有解决您的问题，您可以
                                <a href="javascript:void(0);">在线咨询</a>
                            </div>
                        </div>
                    </div> -->
                    
                </div>
            </div>
        </div>--%>
    <jsp:include page="/WEB-INF/view/include/footer.jsp" />
</div>
<script src="${res }/scripts/jquery.min.js"></script>
<script src="${res }/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res }/scripts/seajs_config.js" type="text/javascript"></script>
<script src="${res }/scripts/src/common/base-init.js"></script>
<script src="${src }/fronts/help/help.js"></script>
<%--<a class="ui-scrolltop" title="返回顶部">返回顶部</a>--%>
<script>
    $(function(){
        seajs.use(["djmenu"], function(){

            $(".cont-details img").each(function(idx,item){
                var w = item.width;
                if(w>880){
                    item.width=880;
                }
            });

            $('#tree').djMenu({
                url: "",         //请求地址
                parameter: "",   //请求参数
                val: "id",         //请求参数的 data-val
                templateId: "nav_list"   //需要使用模版的id
            });
        });
    });
</script>
</body>
</html>
