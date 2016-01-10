<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>${brandesInfo.brandName} 产品展示–8637品牌超级代理</title>
    <meta name="keywords" content="${brandesInfo.brandName},${brandesInfo.brandName}品牌代理,${brandesInfo.brandName}品牌招商"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${res}/styles/market/brandviewbase.css"/>
    <jsp:include page="/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
    <link rel="stylesheet" href="${res}/styles/market/product_view.css"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <%--<!--// top end-->--%>
    <jsp:include page="${ctx}/market/header">
        <jsp:param name="brandesId" value="${brandesId}"/>
        <jsp:param name="url" value="/product"/>
    </jsp:include>
    <div class="do-body" style="padding-top:15px;padding-bottom: 20px;">
        <%--<!--// header end-->--%>
        <%--<div class="comsite">
            <div class="comlogobox f-l">
                <img src="${res}/images/market/temp/duo.jpg">
            </div>
            <div>
                <div class="comname">朵彩/DOCARE<span>中国十大内衣品牌</span></div>
                <div class="comclassic">
                    <a target="" title="" href="#" class="selected"><span>总款式191件</span></a>
                    <a target="" title="" href="#" class="selected"><span>2013款（51件）</span></a>
                    <a target="" title="" href="#" class="selected"><span>2012款（51件）</span></a>
                    <a target="" title="" href="#" class="selected"><span>2011款（51件）</span></a>
                    <a target="" title="" href="#"><span>更早&gt;&gt;</span></a>
                </div>
            </div>
            <div class="purchase">
                <a target="" title="" href="#" class="quick-order">快速采购<span>清爽列表+货号</span></a>

                <p class="font">我的收藏：10件 我的进货单：10件</p>
                <a href="Products.jsp" class="return-pic font"><i class="seticon return-pic-icon mr5"></i>返回列表</a>
            </div>
        </div>--%>
        <div class="img-box" style="min-height: 300px;">
            <div class="sort clearfix">
                <label class="inline-block f-l">类别：</label>
                <ul class="sort-list f-l">
                    <li ${empty filter.cataId ? 'class="active"':''}>
                        <a href="/product_photos">全部分类</a>
                    </li>
                    <c:forEach items="${primaryList}" var="item">
                        <li ${primaryCate == item.refrenceId ? 'class="active"':''}>
                            <a href="/product_photos?cataId=${item.refrenceId}">${item.cateName}</a>
                        </li>
                    </c:forEach>
                </ul>
                <%--<c:choose>--%>
                    <%--<c:when test="${isLogin}">--%>
                        <%--<a href="${ctx}/market/brand/viewProductInfo/${brandesId}/${brandId}" class="return-pictures font f-r"><i class="seticon return-pic-icon mr5"></i>返回列表</a>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<a href="javascript:;" id="joinbtn" class="return-pictures font f-r" style="border-radius: 10px; background: #bbb;">加盟该品牌后并登录即可显示价格详情</a>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <a href="/product" class="return-pictures font f-r"><i class="seticon return-pic-icon mr5"></i>返回列表</a>
            </div>
            <div class="bricks masonry">
                <%-- <div class="brick col1 userActive">
                     <div class="sliderBox">
                         <ul id="userSlider" class="userSlider">
                             <li>
                                 <a target="_blank" class="userImg" href="/jsp/school/OthersHome.jsp">
                                     <img width="30" height="30" src="/images/market/user_avatar.jpg" alt="张三">
                                 </a>
                                 <div class="actvieText">
                                     <p>刚刚浏览了</p>
                                     <p><a href="javascript:void(0);">男士上装</a></p>
                                 </div>
                                 <a class="proUser" href="javascript:void(0);"><img src="/images/market/product_avartar.jpg" alt="男士上装"></a>
                             </li>
                             <li>
                                 <a target="_blank" class="userImg" href="/jsp/school/OthersHome.jsp">
                                     <img width="30" height="30" src="/images/market/user_avatar.jpg" alt="张三">
                                 </a>
                                 <div class="actvieText">
                                     <p>刚刚浏览了</p>
                                     <p><a href="javascript:void(0);">女士上装</a></p>
                                 </div>
                                 <a class="proUser" href="javascript:void(0);"><img src="/images/market/product_avartar.jpg" alt="女士上装"></a>
                             </li>
                             <li>
                                 <a target="_blank" class="userImg" href="/jsp/school/OthersHome.jsp">
                                     <img width="30" height="30" src="/images/market/user_avatar.jpg" alt="张三">
                                 </a>
                                 <div class="actvieText">
                                     <p>刚刚浏览了</p>
                                     <p><a href="javascript:void(0);">男士裤装</a></p>
                                 </div>
                                 <a class="proUser" href="javascript:void(0);"><img src="/images/market/product_avartar.jpg" alt="男士裤装"></a>
                             </li>
                             <li>
                                 <a target="_blank" class="userImg" href="/jsp/school/OthersHome.jsp">
                                     <img width="30" height="30" src="/images/market/user_avatar.jpg" alt="张三">
                                 </a>
                                 <div class="actvieText">
                                     <p>刚刚浏览了</p>
                                     <p><a href="javascript:void(0);">男士内衣</a></p>
                                 </div>
                                 <a class="proUser" href="javascript:void(0);"><img src="/images/market/product_avartar.jpg" alt="男士内衣"></a>
                             </li>
                             <li>
                                 <a target="_blank" class="userImg" href="/jsp/school/OthersHome.jsp">
                                     <img width="30" height="30" src="/images/market/user_avatar.jpg" alt="张三">
                                 </a>
                                 <div class="actvieText">
                                     <p>刚刚浏览了</p>
                                     <p><a href="javascript:void(0);">女士内衣</a></p>
                                 </div>
                                 <a class="proUser" href="javascript:void(0);"><img src="/images/market/product_avartar.jpg" alt="女士内衣"></a>
                             </li>
                         </ul>
                     </div>
                 </div>--%>
                <c:if test="${fn:length(pageResult.list) == 0}">
                    <div>暂无产品信息</div>
                </c:if>
                <c:forEach var="item" items="${pageResult.list}" varStatus="status">
                    <div class="brick ${status.index == bigIndex ? 'col2':'col1'}" data-src="${res}${item.productImage}">
                        <div class="content">
                            <a href="javascript:void(0);">
                                <c:choose>
                                    <c:when test="${status.index == bigIndex}">
                                    	<c:set value="${res}${item.productImage}" var="productImage"></c:set>
                                        <img src="${fns:getImageDomainPath(productImage, 440, 440) }" width="440" height="440"/>
                                    </c:when>
                                    <c:otherwise>
                                    	<c:set value="${res}${item.productImage}" var="productImageUrl"></c:set>
                                        <img src="${fns:getImageDomainPath(productImageUrl, 220, 220) }" width="220" height="220"/>
                                    </c:otherwise>
                                </c:choose>

                            </a>

                                <%--<div class="views">
                                    <div class="txt">人气浏览量</div>
                                    <div class="count">1800</div>
                                </div>--%>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="pro-pages clearfix" style="clear:both;"></div>
        </div>
    </div>
    <%--视频主持人--%>
    <%--<jsp:include page="${ctx}/market/brand/rightFlash">
        <jsp:param name="brandsId" value="${brandesId}"/>
    </jsp:include>--%>
    <%-- 结束 --%>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>
</div>
<div class="photo-modal hide"></div>
<div class="view-content hide">
    <div class="gamma-nav">
        <a class="gamma-prev">‹</a>
        <a class="gamma-next">›</a>
    </div>
    <a href="javascript:void(0);" class="close-view"></a>
</div>
<%--没有加盟的时候--%>
<div id="form-area" class="js_login_box js-all-throw" style="display: none; position: relative;">
    <div class="ui-box-tanchuang">
        <div class="ui-head"><h3 class="">申请加盟</h3><i>X</i></div>
        <form:form id="applyForm" method="post">
            <table class="login_box_table mt15">
                <colgroup>
                    <col width="70"/>
                    <col width="200"/>
                </colgroup>
                <tbody>
                <input id="brandsId" type="hidden" value="${brandesId}" name="brandsId"/>
                <%--<tr>
                    <td style="padding: 5px 0;">联 系 人：</td>
                    <td style="padding: 5px 0;">

                    </td>
                </tr>
                <tr>
                    <td style="padding: 5px 0;">联系方式：</td>
                    <td style="padding: 7px 0;">
                        <input type="text" class="ui-input"/>
                    </td>
                </tr>--%>
                <tr>
                    <td>申请信息：</td>
                    <td>
                        <textarea class="login_box_textarea" name="inviteText" datatype="*"></textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input id="applyFormSubmit" type="button" value="提交申请资料" class="button mt5">
                    </td>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>
</div>
<div class="lig" style="z-index: 220;"></div>
<%--结束--%>
<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/common/base-init.js"></script>
<%--<script src="${res}/scripts/market/zScroll.min.js"></script>--%>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/market/pview.js"></script>
<script src="${res}/scripts/market/last.js"></script>
<script type="text/javascript">
    $(function () {
        setTimeout(function () {
            $.post('${ctx}/market/user_rtk', {
                id: '${brandesId}',
                code: 4
            });
        }, 2000);
        seajs.use(['pagination'], function (Pagination) {

            function parseURL(url) {
                var a = document.createElement('a');
                a.href = url;
                return {
                    source: url,
                    protocol: a.protocol.replace(':', ''),
                    host: a.hostname,
                    port: a.port,
                    query: a.search,
                    params: (function () {
                        var ret = {},
                                seg = a.search.replace(/^\?/, '').split('&'),
                                len = seg.length, i = 0, s;
                        for (; i < len; i++) {
                            if (!seg[i]) {
                                continue;
                            }
                            s = seg[i].split('=');
                            ret[s[0]] = s[1];
                        }
                        return ret;
                    })(),
                    file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
                    hash: a.hash.replace('#', ''),
                    path: a.pathname.replace(/^([^\/])/, '/$1'),
                    relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
                    segments: a.pathname.replace(/^\//, '').split('/')
                };
            }

            var pagination = new Pagination({
                elem: ".pro-pages",
                total:${pageResult.page.totalPage},
                currentPage:${pageResult.page.currentPage},
                skin: "new",
                showTotal: false,
                pageClick: function (page) {
                    var params = parseURL(window.location.href).params;
                    if (!params) {
                        params = {};
                    }
                    params.page = page;
                    window.location.href = '?' + $.param(params);
                }
            })
        });
    });
</script>
</body>
</html>
