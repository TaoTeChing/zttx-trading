<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<ul class="productsul">
</ul>
<div class="productsul-pages"></div>
<script class="ajax-templage" _url="${ctx}/market/brand/listProductInfo?brandesId=${brandesId}" _page=".productsul-pages" _pid=".productsul" type="text/html">
{{each rows}}
<li class="box">
	<div class="ad-gallery clearfix">
		<a href="/market/product/{{$value.refrenceId}}" title="" target="">
			<div class="ad-image-wrapper">
			</div>
		</a>
		<div class="ad-nav">
			<div class="ad-thumbs">
				<ul class="ad-thumb-list">
					<li>
						<a href="{{$value.domainName + $value.productImage}}" title="" target="">
							<img src="{{$value.domainName + $value.productImage}}" class="image0" width="220" height="220" />
						</a>
					</li>
					{{each productImageList}}
						<li>
							<a href="{{$value.domainName + $value.imageName}}" title="" target="">
								<img src="{{$value.domainName + $value.imageName}}" class="image0" width="220" height="220" />
							</a>
						</li>
					{{/each}}
				</ul>
			</div>
		</div>
	</div>
	<div class="title"><a href="#" title="" target="">{{$value.productTitle}}</a></div>
	<div>
		<span class="price">￥{{$formatMoney $value.productPrice}}</span>
		<span class="liang">总采购量：{{if $value.productCount == null}}0{{else}}{{$value.productCount.saleNum}}{{/if}}</span>
	</div>
	<div class="collect">
		<a class="cath" href="#" title="" target="">加入进货单</a>
		<a class="favorite" href="javascript:productCollect('{{$value.refrenceId}}')" title="" target="">加入收藏夹</a>
	</div>
</li>
{{/each}}
</script>
