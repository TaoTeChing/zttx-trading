<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<!-- 产品分类信息展示 -->
<div class="plist font">
	<div class="plist-bg">
		<h4 class="title">搜索分类</h4>
		<h5 class="tit"><i class="jian"></i><a href="javascript:;" title="" target="">查看所有宝贝</a></h5>
		<ul class="plistul">
			<li>
				<a href="#" title="" target="">按综合</a>
				<a href="#" title="" target="">按销量</a>
				<a href="#" title="" target="">按新品</a>
				<a href="#" title="" target="">按价格</a>
			</li>
		</ul>
		
		<h4 class="title">宝贝分类</h4>
		<div class="proclass">
			<c:forEach items="${catalogList}" var="catalog">
				<h5 class="tit">
					<i class="jian"></i>
					<a href="javascript:;" title="" target="">${catalog.name}</a>
				</h5>
				<ul class="plistul">
					<c:forEach items="${catalog.children}" var="child">
						<li><b>·</b><a href="#" title="" target="">${child.name}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
	</div>
	
	<!-- <h4 class="title">新品推荐<a href="#" title="" target=""><span class="more f-r">更多</span></a></h4>
	<div class="recommend">
		<div class="recombox">
			<div class="imgbox">
				<img src="/images/market/temp/tuij.jpg" title="" width="172" />
			</div>
			<p class="title">￥149.00</p>
			<p class="des">朵彩彩棉加厚加绒保暖内衣男士女士棉毛衫套装 非化纤内衣更健康</p>
			<p class="number">总销量：<span>26924</span></p>
		</div>
		<div class="recombox">
			<div class="imgbox">
				<img src="/images/market/temp/tuij.jpg" title="" width="172" />
			</div>
			<p class="title">￥149.00</p>
			<p class="des">朵彩彩棉加厚加绒保暖内衣男士女士棉毛衫套装 非化纤内衣更健康</p>
			<p class="number">总销量：<span>26924</span></p>
		</div>
	</div> -->
</div>