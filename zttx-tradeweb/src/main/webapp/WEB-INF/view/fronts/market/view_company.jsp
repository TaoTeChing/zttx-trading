<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html>
<head>
<meta charset="utf-8" />
<title>企业展示  ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
<meta name="keywords" content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
<meta name="description" content=""/>
<link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css" />
<link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css" />
    <jsp:include page="/market/header_css" >
        <jsp:param name="brandesId"  value="${brandesId}" />
    </jsp:include>
</head>

<body>
<%--<!-- top -->--%>
<jsp:include page="${ctx}/common/top" />

<%--<!-- head -->--%>
<jsp:include page="/market/header">
    <jsp:param name="brandesId" value="${brandesId}"/>
    <jsp:param name="url" value="/company"/>
</jsp:include>

<%--<!-- body -->--%>
<div class="do-body">
<div class="listbody clear">
    <style>
        .listbody .sidebar-l .content{border: 0;background: none;}
    </style>
	<div class="sidebar-l">
    	<%--<h3 class="title">企业展示</h3> 要求去掉，勿恢复--%>
    	<div class="content">
            <!-- <div class="imgbox">广告图片</div> -->
            <p class="sidebar-p">${brandInfo.comMark}</p>
        </div>
        <%--<h3 class="title">品牌介绍</h3>
        <div class="introduction">
        	<div class="intimg f-l">
            	<img src="${ctx}/images/market/temp/intimg.jpg" />
            </div>
			<div class="intcontent f-l" id="scrollbar2">
            	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
                <div class="viewport">
                	<div class="overview">
                		<h2 class="x-bt">品牌定位</h2>
                		<p class="sidebar-p">${brandMark}</p>
                	</div>
                </div>
            </div>
            <div class="clear"></div>
        </div>--%>
        
		<!-- 产品展示信息 -->
		<%-- 
		<jsp:include page="${ctx}/market/brand/listProduct" >
			<jsp:param name="brandesId"  value="${brandesId}" />
		</jsp:include>
		--%>
    </div>
    <%--<!--// 右侧功能条 -->--%>
    <%--<%@ include file="right_sidebar.jsp" %>--%>
    <jsp:include page="${ctx}/market/brand/rightSidebar">
        <jsp:param name="brandId" value="${brandId}"/>
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
</div>
</div>
<%--<!--// body -->--%>
<%--视频主持人--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>--%>
<%-- 结束 --%>
<%--<!-- footer -->--%>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>

<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js" type="text/javascript"></script>
<script src="${src}/fronts/market/jquery.elastislide.js" type="text/javascript"></script><!--产品切换-->
<script type="text/javascript">
		$(document).ready(function(){
            setTimeout(function() {
                $.post('${ctx}/market/user_rtk', {
                    id: '${brandesId}',
                    code: 5
                });
            }, 2000);

            //收藏品牌
            $(".attention").on('click', '.js-collected-brands', function () {
                $.post("${ctx}/market/brand/attentionNum/add", {brandesId: "${brandesId}"}, function (data) {
                    switch (data.code) {
                        case zttx.SUCCESS:
                            remind("success", "收藏成功", function () {
                                var _value = parseInt($("em.gz-num").text());
                                $(".attention span.weiguanzhu").addClass("yiguanzhu").removeClass("weiguanzhu").next().text("已收藏").removeClass("js-collected-brands");
                                $("em.gz-num").text(_value+1);
                            });
                            break;
                        case zttx.NOT_LOGIN:
                            var currentUrl = location.href;
                            location.href = '${ctx}/common/login?loginType=1&redirect=' + encodeURIComponent(currentUrl);
                            break;
                        default :
                            remind("error", data.message);
                    }
                }, "json");
            });
		});
</script>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
</body>
</html>