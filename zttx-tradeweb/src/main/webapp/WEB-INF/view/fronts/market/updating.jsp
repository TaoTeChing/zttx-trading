<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>8637品牌超级代理 - 首页</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <!--样式-->
    <link href="${res }/styles/common/global.css" rel="stylesheet" />
    <link href="${res }/styles/common/base.css" rel="stylesheet" />
    <link href="${res }/styles/common/index.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
         <jsp:include page="${ctx}/common/top" />
	    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
	    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
	        <jsp:param value="2" name="m"/>
	    </jsp:include>

        <!--主体开始-->
        <div class="main em100 mt10">
            <!--焦点图行开始-->
            <div class="main-grid mb10 em100 clearfix">
                <img src="${res }/images/updating/banner1.jpg" />
            </div>
            <!--焦点图行结束-->
            <!--第二部分开始-->
            <div class="main-grid mb10 em100 clearfix">
                <img src="${res }/images/updating/banner2.jpg" />
            </div>
            <!--第二部分结束-->

            <!--第三部分开始-->
            <div class="main-grid mb10 em100 clearfix">
                <ul class="inline-float updating-brand">
                <c:set value="${fns:getBrandesInfo(4,1) }" var="brandsList"></c:set>
                <c:forEach items="${brandsList }" var="brandes">
                	 <li>
                        <a class="js-img-center" href="/index/${brandes.refrenceId}" target="_blank">
                        	<c:set value="${res }${brandes.brandLogo }" var="url"></c:set>
                            <img src="${fns:getImageDomainPath(url, 240, 120) }" alt="${brandes.brandName }" />
                        </a>
                    </li>
                </c:forEach>
                   
                   <!--  <li>
                        <a href="#" target="_blank" title="樱知叶">
                            <img src="../images/updating/yingzhiye.jpg" alt="樱知叶" />
                        </a>
                    </li>
                    <li>
                        <a href="#" target="_blank" title="爱尔倍特">
                            <img src="../images/updating/aierbeite.jpg" alt="爱尔倍特" />
                        </a>
                    </li>
                    <li>
                        <a href="#" target="_blank" title="束婷">
                            <img src="../images/updating/suting.jpg" alt="束婷" />
                        </a>
                    </li> -->
                </ul>
            </div>
            <!--第三部分结束-->
        </div>
       
        <jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>

    <script src="${res }/scripts/jquery.min.js"></script>
    <script src="${res }/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res }/scripts/seajs_config.js"></script>
    <script src="${res }/scripts/common/base-init.js"></script>
    <script src="${res }/scripts/common/index.js"></script>
</body>
</html>
