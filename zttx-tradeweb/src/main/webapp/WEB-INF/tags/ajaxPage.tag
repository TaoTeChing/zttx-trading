<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ attribute name="form" type="java.lang.String" required="true" description="Form表单名称" %>
<%@ attribute name="requestUrl" type="java.lang.String" required="true" description="ajax提交URL" %>
<%@ attribute name="page" type="com.zttx.sdk.bean.Pagination" required="true" description="页面对象" %>
<%@ attribute name="targetDiv" type="java.lang.String" required="true" description="要替换的DIV" %>
<script src="${res}/scripts/common/ZttxUtils.js"></script>
<script type="text/javascript">
</script>
<c:if test="${null != page}">
    <c:set var="iStep" value="3"/>
    <c:set var="iLeftNum" value="0"/>
    <c:set var="iRightNum" value="0"/>

    <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>

    <div class="pagination mt15">

        <!-- 上一页 -->
        <c:choose>
            <c:when test="${page.hasPreviousPage==true}">
                <a class="prev"
                   href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},(${page.currentPage}-1));">上一页</a>
            </c:when>
            <c:otherwise>
                <a class="prev" href="javascript:">上一页</a>
            </c:otherwise>
        </c:choose>

        <!-- 第一页 -->
        <c:choose>
            <c:when test="${page.currentPage==1}">
                <a class="page current" href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},1);">1</a>
            </c:when>
            <c:otherwise>
                <a class="page" href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},1);">1</a>
            </c:otherwise>
        </c:choose>

        <!-- 显示的页码 -->
        <c:if test="${page.totalPage > 2}">

            <!-- 设置 iLeftNum 和 iRightNum -->
            <c:choose>
                <c:when test="${page.currentPage <= 5 && page.currentPage >= page.totalPage - 4}">
                    <c:set var="iLeftNum" value="2"/>
                    <c:set var="iRightNum" value="${page.totalPage > 7 ? page.totalPage - 2 : page.totalPage - 1}"/>
                </c:when>
                <c:when test="${page.currentPage <= 5 && page.currentPage <= page.totalPage - 4}">
                    <c:set var="iLeftNum" value="2"/>
                    <c:set var="iRightNum" value="${page.totalPage <= 7 ? page.totalPage - 1 : '6'}"/>
                </c:when>
                <c:when test="${page.currentPage >= page.totalPage - 4}">
                    <c:set var="iLeftNum" value="${page.totalPage - 5 == 1 ? '2' : page.totalPage - 5}"/>
                    <c:set var="iRightNum" value="${page.totalPage - 1}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${page.totalPage <= 7}">
                            <c:set var="iLeftNum" value="2"/>
                            <c:set var="iRightNum" value="${page.totalPage - 1}"/>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${page.currentPage - 3 > 1}">
                                    <c:set var="iLeftNum" value="${page.currentPage - 2}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="iLeftNum" value="2"/>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${page.currentPage + 3 < page.totalPage}">
                                    <c:set var="iRightNum" value="${page.currentPage + 2}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="iRightNum" value="${page.totalPage -1}"/>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <!-- 显示的 ... -->
            <c:if test="${page.currentPage > 5 && page.totalPage > 7}">
                <span class="ellipsis">…</span>
            </c:if>

            <!-- 显示的页码 -->
            <c:forEach var="i" begin="${iLeftNum}" end="${iRightNum}" step="1">
                <c:choose>
                    <c:when test="${page.currentPage==i}">
                        <a class="page current">${i}</a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a class="page" href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},${i});">${i}</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- 显示的... -->
            <c:if test="${(page.currentPage < page.totalPage - 4 && page.totalPage > 7)
				|| ((page.currentPage <= 5 && page.currentPage >= page.totalPage - 4) && (page.totalPage > 7))}">
                <span class="ellipsis">…</span>
            </c:if>
        </c:if>

        <!-- 最后一页 -->
        <c:if test="${page.totalPage > 1}">
            <c:choose>
                <c:when test="${page.currentPage==page.totalPage}">
                    <a class="page current"
                       href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},${page.totalPage});">${page.totalPage}</a>
                </c:when>
                <c:otherwise>
                    <a class="page"
                       href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},${page.totalPage});">${page.totalPage}</a>
                </c:otherwise>
            </c:choose>
        </c:if>

        <!-- 下一页 -->
        <c:choose>
            <c:when test="${page.hasNextPage==true}">
                <a class="next"
                   href="javascript:$Z.loadPage(${form},${requestUrl},${targetDiv},(${page.currentPage}+1));">下一页</a>
            </c:when>
            <c:otherwise>
                <a class="next" href="javascript:">下一页</a>
            </c:otherwise>
        </c:choose>

        <!-- 记录数统计 -->
        <span class="total">共${page.totalCount}条记录</span>

    </div>
</c:if>