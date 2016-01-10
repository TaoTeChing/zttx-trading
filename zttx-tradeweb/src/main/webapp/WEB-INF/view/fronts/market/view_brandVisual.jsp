<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
    <meta charset="utf-8" />
    <title>陈列视觉  ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
    <meta name="keywords" content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/fronts/market/prodisshow.css" rel="stylesheet" type="text/css" />
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
        <jsp:param name="url" value="/visual"/>
    </jsp:include>

    <%--<!--// header end-->--%>
    <div class="do-body">
    <div class="all-prodisshow">
        <div class="prodisshow clear">
            <%--<ul class="virtual-tit">
                <li class="selected"><a href="VisionImge.jsp" title="" target="" class="virtual-tit1 vir">图片展示</a></li>
                <li><a href="VisionVideo.jsp" title="" target="" class="virtual-tit2 vir">视频展示</a></li>
                <li><a href="Vision3D.jsp" title="" target="" class="virtual-tit3 vir">3D展厅</a></li>
            </ul>--%>
            <div class="virtual clear">
                <style type="text/css">
                    .c-slide {width: 1175px; height: 500px;margin: 0 auto; background: url("${res}/images/fronts/market/c-silde-bg.png") repeat; position: relative;}
                    .c-slide li {position: absolute; left: 600px; overflow: hidden;background: #000;border: 1px solid #fff;border-radius: 3px;}
                    #c-prev,#c-next{ width: 26px; height:50px; display: inline-block; *display: inline; *zoom:1; background: url("${res}/images/fronts/market/c-silde-page.png") no-repeat; position: absolute; top:230px;}
                    #c-prev{ background-position: 0 0;left:115px;}
                    #c-next{ background-position: -26px 0; right:110px;}
                </style>
                <div class="c-slide clearfix">
                    <ul>
                        <c:set var="isEmpty" value="${empty imageList}"/>
                        <c:choose>
                            <c:when test="${isEmpty}">
                                <li>
                                    <%--<a href="${res}/images/common/nopic-260.gif" target="_blank">--%>
                                        <img src="${res}/images/common/nopic-260.gif"  class="image1" width="100%" height="100%" />
                                    <%--</a>--%>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="obj" items="${imageList}" varStatus="status">
                                    <li>
                                        <a href="${res}${obj.imageName}" title="${brandAlbum.imageName}" data-lightbox="bigPicGroup">
                                            <img src="${res}${obj.imageName}"  width="100%" height="100%" />
                                            <%--<img src="${res}${obj.imageName}" class="image${status.index}" />--%>
                                        </a>
                                    </li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                    <a id="c-prev" href="javascript:;"></a>
                    <a id="c-next" href="javascript:;"></a>
                </div>
                <%-- 此处暂时保留，图片展现方式已修改 <div class="ad-gallery ad-gallery2">
                    <div class="ad-image-wrapper">
                    </div>
                    <div class="ad-controls">
                    </div>
                    <div class="ad-nav">
                        <div class="ad-thumbs">
                            <ul class="ad-thumb-list">
                                <c:set var="isEmpty" value="${empty imageList}"/>
                                <c:choose>
                                    <c:when test="${isEmpty}">
                                        <li>
                                            <a href="${res}/images/common/nopic-260.gif">
                                                <img src="${res}/images/common/nopic-260.gif" class="image1" />
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="obj" items="${imageList}" varStatus="status">
                                            <li>
                                                <a href="${res}${obj.imageName}">
                                                    <img src="${res}${obj.imageName}" class="image${status.index}" />
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>--%>
            </div>
        </div>
    </div>
    <%--<!--// listbody -->--%>
    </div>
    <%--视频主持人--%>
    <%--<jsp:include page="${ctx}/market/brand/rightFlash">
        <jsp:param name="brandsId" value="${brandesId}"/>
    </jsp:include>--%>
    <%-- 结束 --%>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
    <%--<!-- // footer -->--%>
    <script src="${res}/scripts/jquery.min.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${src}/common/base-init.js"></script>
    <script src="${src}/fronts/market/jquery.elastislide.js"></script>
    <script src="${src}/fronts/market/jsZoomPic.js"></script>
    <script type="text/javascript">
        $(function () {
            /*$('.ad-gallery').adGallery();
            $(".ad-slideshow-controls").css("display","none");*/
            seajs.use(["lightbox"], function () {
                $(".lb-details").hide();
            });

            setTimeout(function() {
                $.post('${ctx}/market/user_rtk', {
                    id: '${brandesId}',
                    code: 3
                });
            }, 2000);
        });
    </script>
    <script src="${src}/fronts/market/last.js" type="text/javascript"></script>
</body>
</html>
