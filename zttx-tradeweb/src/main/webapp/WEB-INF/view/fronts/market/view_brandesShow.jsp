<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="comdown">
    <h3 class="title">相关品牌:</h3>

    <div id="focus" ${fn:length(braList) < 3 ? 'style="height:94px;"':''}>
        <ul class="comdownul fontcolor3 font">
            <c:forEach items="${braList}" step="3" varStatus="status">
                <li>
                    <dl>
                        <c:forEach var="obj" begin="${status.index}" end="${status.index + status.step}" items="${braList}">
                            <dd>
                                <div class="imgbox">
                                	<c:set value="${ fns:getBrandsIdSubDomain(obj.refrenceId)}" var="domain"></c:set>
                                	<c:set value="${fns:getImageDomainPath(obj.brandLogo, 100, 50) }" var="url"></c:set>
                                    <a href="http://${domain}${zttx}"><img src="${res}${url}" alt="${obj.brandsName}" style="width:100px; height:50px;"/></a>
                                </div>
                                <div class="des">
                                    <a href="http://${domain}${zttx}">${obj.brandsName}</a></div>
                            </dd>
                        </c:forEach>
                    </dl>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="contact clear" style="padding: 20px 15px;position: relative;">
    <img src="${res}/images/fronts/market/contect.png" alt=""/>
    <div class="qq-area">
        <%--<!-- WPA Button Begin -->--%>
        <script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODAwMTU5Nl8xNTE2NjRfNDAwMTExODYzN18"></script>
        <%--<!-- WPA Button End -->--%>
    </div>
</div>