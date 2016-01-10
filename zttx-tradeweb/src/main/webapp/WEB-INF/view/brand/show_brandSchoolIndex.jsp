<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌商管理中心-天下商学院</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/school.css"/>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
<div class="main_con">
    <div class="inner">
        		<!-- 内容都放这个地方  -->
        		<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_top.jsp">
        			<jsp:param name="m" value="1" />
        		</jsp:include>
                <div style="background: #fff; padding: 10px;">
					<jsp:include page="${ctx}/WEB-INF/view/brand/show_brandSchool_bar.jsp">
						<jsp:param name="m" value="1" />
					</jsp:include>
					
                    <div id="js-myUpload" class="main-grid mt10">
                        <div class="main-grid pt3">
                            <div class="tabpanel">
                                <div class="tabs clearfix">
                                    <div class="yahei fs16 block titles">
                                    	<a href="${ctx}/brand/school/list" class="link">我的上传</a>
                                    </div>
                                    <div class="more">
                                        <a href="${ctx}/brand/school/list" class="link">查看全部</a>
                                    </div>
                                </div>
                                <div class="content ">
                                    <div class="tabitem">
                                        <table class="panel-table">
                                        	<c:if test="${empty articleList}">
                                        		<thead>
													<tr>
														<td>您还没有上传过课程，赶紧去<a href="#" class="c-b">上传课程</a>获得积分吧！</td>
													</tr>
												</thead>
                                        	</c:if>
                                        	<c:if test="${not empty articleList}">
                                        		<thead>
                                        			<tr>
														<th class="tb30 tac">标题名称</th>
														<th class="tb20 tac">出售价格</th>
														<th class="tb20 tac">学习次数</th>
														<th class="tb20 tac">上传时间</th>
													</tr>
												</thead>
												<tbody>
                                        			<c:forEach var="obj" items="${articleList}" varStatus="status">
                                        				<tr <c:if test="${status.index % 2 == 0}">class="dan"</c:if><c:if test="${status.index % 2 != 0}">class="ou"</c:if>>
                                        					<td><a href="${ctx}/school/detail/${obj.refrenceId}">${obj.articleTitle}</a></td>
                                        					<td><fmt:formatNumber value="${obj.articlePrice}" type="currency" pattern="0.00" /></td>
                                        					<td>${obj.viewNum}</td>
                                        					<td>${fns:getTimeFromLong(obj.createTime,"yyyy-MM-dd HH:mm")}</td>
                                        				</tr>
                                        			</c:forEach>
                                        		</tbody>
                                        	</c:if>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="js-learn" class="main-grid mb10">
                        <div class="main-grid pt3">
                            <div class="tabpanel">
                                <div class="tabs clearfix">
                                    <div class="yahei fs16 block titles">
                                        <a href="${ctx}/brand/school/learn" class="link">我的学习</a>
                                    </div>
                                    <div class="more">
                                        <a href="${ctx}/brand/school/learn" class="link">查看全部</a>
                                    </div>
                                </div>
                                <div class="content ">
                                    <div class="tabitem">
                                        <table class="panel-table">
                                        	<c:if test="${empty learnList}">
                                        		<thead>
													<tr>
														<td>您还没有学习的课程，赶紧去<a href="#" class="c-b">挑选课程</a>进行学习吧！</td>
													</tr>
												</thead>
                                        	</c:if>
                                        	<c:if test="${not empty learnList}">
                                        		<thead>
                                        			<tr>
														<th class="tb30 tac">标题名称</th>
														<th class="tb20 tac">出售价格</th>
														<th class="tb20 tac">上传者</th>
														<th class="tb20 tac">上传时间</th>
													</tr>
												</thead>
												<tbody>
                                        			<c:forEach var="obj" items="${learnList}" varStatus="status">
                                        				<tr <c:if test="${status.index % 2 == 0}">class="dan"</c:if><c:if test="${status.index % 2 != 0}">class="ou"</c:if>>
                                        					<td><a href="${ctx}/school/detail/${obj.articleId}">${obj.articleTitle}</a></td>
                                        					<td><fmt:formatNumber value="${obj.articlePrice}" type="currency" pattern="0.00" /></td>
                                        					<td>${obj.userName}</td>
                                        					<td>${fns:getTimeFromLong(obj.uploadDate,"yyyy-MM-dd HH:mm")}</td>
                                        				</tr>
                                        			</c:forEach>
                                        		</tbody>
                                        	</c:if>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="main-grid mt10">
                        <a href="#">
                            <img src="${res}/uploads/ad01.jpg" width="100%" />
                        </a>
                    </div>

                    <div id="js-favorite" class="main-grid mt10">
                        <div class="main-grid pt3">
                            <div class="tabpanel">
                                <div class="tabs clearfix">
                                    <div class="yahei fs16 block titles">
                                        <a href="${ctx}/brand/school/favorite" class="link">我的收藏</a>
                                    </div>
                                    <div class="more">
                                        <a href="${ctx}/brand/school/favorite" class="link">查看全部</a>
                                    </div>
                                </div>
                                <div class="content ">
                                    <div class="tabitem">
                                        <table class="panel-table">
                                        	<c:if test="${empty favoriteList}">
                                        		<thead>
													<tr>
														<td>您还没有收藏课程，马上去<a href="#" class="c-b">收藏喜欢的课程</a>吧！</td>
													</tr>
												</thead>
                                        	</c:if>
                                        	<c:if test="${not empty favoriteList}">
                                        		<thead>
                                        			<tr>
														<th class="tb30 tac">标题名称</th>
														<th class="tb20 tac">出售价格</th>
														<th class="tb20 tac">上传者</th>
														<th class="tb20 tac">上传时间</th>
													</tr>
												</thead>
												<tbody>
                                        			<c:forEach var="obj" items="${favoriteList}" varStatus="status">
                                        				<tr <c:if test="${status.index % 2 == 0}">class="dan"</c:if><c:if test="${status.index % 2 != 0}">class="ou"</c:if>>
                                        					<td><a href="${ctx}/school/detail/${obj.articleId}">${obj.articleTitle}</a></td>
                                        					<td><fmt:formatNumber value="${obj.articlePrice}" type="currency" pattern="0.00" /></td>
                                        					<td>${obj.userName}</td>
                                        					<td>${fns:getTimeFromLong(obj.favoriteDate,"yyyy-MM-dd HH:mm")}</td>
                                        				</tr>
                                        			</c:forEach>
                                        		</tbody>
                                        	</c:if>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="js-comment" class="main-grid mt10">
                        <div class="main-grid pt3">
                            <div class="tabpanel">
                                <div class="content ">
                                    <div class="tabitem tabs brc-fa">
                                        <div class="yahei fs16 block titles bb1">我的评论</div>
                                        <div id="replyList"></div>
                                        <div class="reply_ipt">
                                            <div class="paginationTop">
                                                <div class="Dashed"></div>
                                            </div>
                                            <div class="paginationTop pageParent">
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
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/index.js"></script>
<script class="ajax-templage" _url="${ctx}/brand/school/index/comment.json?pageSize=7" _page=".pageParent" _pid="#js-comment #replyList" type="text/html">
<div class="review">共{{total}}条</div>
{{each rows}}
<div class="reply">
	<div class="title">{{$value.comContent}}</div>
	<div><span class="c-hh">最新回复（<i class="c-o-on">{{$value.artuserName}}</i>）：</span>“{{$value.repContent}}”</div>
	<div class="c-hh">{{$formatDate $value.createTime}} 时发表</div>
	<%--
	{{if "${userId}" == $value.artUserId}}
		<div class="title">{{$value.articleTitle}}</div>
		<div><span class="c-hh">回复：</span>“{{$value.comContent}}”</div>
		<div class="c-hh">{{$formatDate $value.createTime}} 时发表</div>
	{{/if}}
	{{if "${userId}" == $value.repUserId}}
		<div class="title">{{$value.articleTitle}}</div>
		<div><span class="c-hh">回复<i class="c-o-on">{{$value.artuserName}}</i>的评论：</span>“{{$value.repContent}}”</div>
		<div class="c-hh">{{$formatDate $value.repTime}} 时发表</div>
	{{/if}}
	--%>
</div>
<div class="reply_dd"></div>
{{/each}}
</script>
<script type="text/javascript">
	var pageList = {};
	seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment){
		template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD HH:mm");
		});
		
		$(".ajax-templage").each(function(index){
			var $this = $(this);
			var tempId = $this.attr("id");
			if(tempId == null || tempId == ""){
				tempId = "templage" + index;
				$this.attr("id", tempId);
			}
			pageList[tempId] = new Pagination({
				url: $this.attr("_url"),
				elem: $this.attr("_page"),
				method: "post",
				handleData: function (data) {
					var html = template.render(tempId, data);
					$($this.attr("_pid")).html(html);
				}
			});
		});
	});
</script>
</body>
</html>