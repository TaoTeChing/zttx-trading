<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="news mt20">
	<div class="ts-container clearfix">
		<div class="fl news-l">
			<div class="news-menu">
				<a href="${ctx}/info/list?cateId=76E0F7D9A20742988E7FB1696BDE064B" class="news-cate" title="平台公告" target="_blank">平台公告</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=B0A2A06248C64DA98C3637855FB42B9D" class="news-cate" title="平台资讯" target="_blank">平台资讯</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=BAA0B835C97F436EA00F9CE57E381C7A" class="news-cate" title="品牌资讯" target="_blank">品牌资讯</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=7A261DBF854646AD937924271B63C1EC" class="news-cate" title="电商动态" target="_blank">电商动态</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=1F0F85286B464230BD5A8E4619460155" class="news-cate" title="潮流趋势" target="_blank">潮流趋势</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=76D00AD0BB904F7393393B04D801FE6A" class="news-cate" title="产经资讯" target="_blank">产经资讯</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=BE992CD5D574470B84053440EBB234E9" class="news-cate" title="展会报道" target="_blank">展会报道</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=46533F8688B342EBB541EC0806973666" class="news-cate" title="统计报告" target="_blank">统计报告</a>
				<span class="news-line"></span>
				<a href="${ctx}/info/list?cateId=D681E42E9E0842DE84E9B0AECDD06562" class="news-cate" title="政策参考" target="_blank">政策参考</a>
			</div>
			<div class="news-content clearfix">
				<div class="news-pic fl">
					<div id="news-pic">
						<ul>
							<c:set value="${fns:findArticleHead(6) }" var="articles"/>
							<c:forEach items="${articles.list }" var="article">
								<li class="news-pic-item">
									<a href="${ctx }/info/detail/${article.refrenceId}" target="_blank">
										<img src="${res}${fns:getImageDomainPath(article.articleImage,365 ,250)}"
											 width="365" height="250" alt="${article.articleTitle }"/>
									</a>
									<span class="title" title="${article.articleTitle }">${article.articleTitle }</span>
									<span class="tit"></span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="news-list fr">
					<h4>品牌资讯</h4>
					<ul>
						<c:set value="${fns:getArticleCate(brand_News) }" var="cate" />
						<!--品牌资讯列表-->
						<c:set value="${fns:findSimpleByInfo(cate.refrenceId , 10) }" var="newInfo" />
						<c:if test="${newInfo.list != null}">
							<c:forEach items="${newInfo.list }" var="info" begin="0" end="7">
								<li>
									<a href="${ctx }/info/list?cateId=${info.cateId}" target="_blank"
									   class="col_c3">[${info.cateName }]</a>
									<a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
									   title="${fns:trimLongStringWithEllipsis(info.articleTitle, 15, '...') }">
										<em>${fns:trimLongStringWithEllipsis(info.articleTitle, 15, '...') }</em></a>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
		<div class="fr news-r">
			<div class="news-join">
				<div class="news-join-hd">找不到好的项目，来<em>8637品牌超级代理</em>这里有低价的品牌直供价格</div>
				<div class="news-join-bd clearfix">
					<a href="${ctx}/factory/introduce" class="news-btn" target="_blank">终端商加盟</a>
					<span class="news-join-line"></span>
					<a href="${ctx}/brandJoin" class="news-btn" target="_blank">品牌商加盟</a>
				</div>
				<div class="news-join-fd">
					<p class="p1">品牌渠道建设慢，效果差，费用高，来8637品牌超级代理，这里有海量的中国门店信息为您的品牌打开更广的市场</p>
					<p class="p2">加盟服务热线：<em>400-111-8637</em></p>
				</div>
			</div>
		</div>
	</div>
</div>