<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>403错误页面</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/error/error.css" rel="stylesheet"/>
    <link href="${res}/styles/error/index.css" rel="stylesheet"/>
    <style>
        body, .main {
            background: #fff;
        }

        button, input, select, textarea, .main {
            font-family: Arial, "宋体";
        }

        .nav .ts-container {
            border-bottom: 2px solid #ed0100;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp" />
    <div class="main em100">
        <div class="content">
            <div class="info">
                <h3>403错误</h3>
                <p>您提交的数据中安全信息已过期：点击 <a href="${ctx }/about/contactus">联系我们。</a></p>
                <p>将在 <i class="second">10</i> 秒后自动转入<a href="/">网站首页</a>，请稍后...</p>
            </div>
        </div>
        <%@ include file="/WEB-INF/tags/adverts/brands.tag" %>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
    <jsp:include page="/WEB-INF/view/include/bottom.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    $(function(){
        /*倒计时跳转到首页*/
        var s= 0;
        var second = 10;
        setInterval(function(){
            s=s+1;
            if(s>second){
                location.href="/";
                return;
            }
            $(".second").text(second-s);
        },1000);

        //品牌推荐切换
        var m3 = $(".main-three-items");
        var lineb = $(".line b");
        var m3d = [{ width: 190, left: 0 }, { width: 190, left: 198 }, { width: 190, left: 396 }, { width: 190, left: 594 }, { width: 190, left: 792 }, { width: 190, left: 990 }];
        var items = $(m3).find("li").hover(function () {
            //$(items).not(this).find("dl").css({ opacity: 0.7 });
            $(items).not(this).css({ opacity: 0.7 });

            var items_i = $(items).index(this);

            $(lineb).show().stop().animate({ "margin-left": m3d[items_i].left, width: m3d[items_i].width }, 400);

        }, function () {
            //$(items).find("dl").css({ opacity: 1 });
            $(items).css({ opacity: 1 });
            $(lineb).hide();
            //$(old).animate({opacity: 0.5},300);
        });

    });
</script>
<script src="${res }/scripts/src/plugin/jquery.lazyload.js"></script>
<script>
    $(function(){
        $(".js-loadafter").lazyload();
    });
</script>
</body>
</html>
