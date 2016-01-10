<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--<!-- 视频主持人 -->--%>
<c:if test="${not empty brandsVideo}">
<script>
    //vedio
    //url 是视频地址 支持http://  8637_20140529.flv
    var strArray = {id: "${brandsVideo.refrenceId}", url: "${brandsVideo.videoName}", width: ${brandsVideo.width}, height: ${brandsVideo.height},position:'${empty brandsVideo.position ? 'rb':fn:toLowerCase(brandsVideo.position)}',issavecookie:true};
</script>
<script src="${res}/video/winston.js"></script>
</c:if>