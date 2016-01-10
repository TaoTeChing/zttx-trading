<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<script id="template_trends_grid" type="text/html">
{{each rows}}
<li>
	<div class="ui-box">
		<div class="ui-box-head">
			<span class="ui-box-head-text">{{$formatDate $value.createTime}}</span>
		</div>
		<div class="ui-box-container">
            <dl>
                {{each $value.newses}}
                {{if $index==0}}
                <dd class="ui-box-content">
                    <div class="js-img-center"><img  src="{{wshop}}{{$value.newsImg}}"/></div>
                    <h3>
                       {{$value.title}}
                    </h3>
                </dd>
                {{else}}
                <dd class="trends-cont-item">
                    <div class="cont fl">
                        <h3><a>{{$value.title}}</a></h3>
                    </div>
                    <div class="js-img-center fr">
                        <img src="{{wshop}}{{$value.newsImg}}"/>
                        <span></span></div>
                </dd>
                {{/if }}
                {{/each}}
            </dl>
		</div>
		<div class="ui-box-foot">
			<a href="${ctx}/dealer/weshop/trends/publish?groupId={{$value.groupId}}&shopId=${shopId}"><i class="iconfont edit">&#xe618;</i></a><em></em><a href="javascript:;" ><i data-groupid="{{$value.groupId}}" class="iconfont delete">&#xe619;</i></a>
		</div>
	</div>
</li>
{{/each}}
</script>