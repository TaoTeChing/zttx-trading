<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%--网页导航--%>
<div class="nav mb10">
    <div class="ts-container clearfix">
        <div class="nav-left fl">
            <h2 class="btn">全部产品分类</h2>
            <div class="float-box <c:if test="${empty param.m || param.m!='1'}">open</c:if>"><%--这里需要判断是否是首页,如果是首页,去掉open样式名--%>
                <%--erp那边KEY里的内容放在这个下面 ..--%>
                <%--<tags:index_staticHtml webDefTemplate="${fns:getDocument(new_index_left_category) }"></tags:index_staticHtml>--%>
                <%--<div class="type-box">
                    <div class="type-item">
                        <div class="type-item-head">
                            <h3 class="type-item-title"><i class="service-women"></i> 女装</h3>
                            <a href="" title="" class="type-item-more">更多</a>
                        </div>
                        <div class="type-item-content">
                            <a href="" title="" target="_blank" class="link text-red">羽绒服</a>
                            <a href="" title="" target="_blank" class="link">棉服</a>
                            <a href="" title="" target="_blank" class="link">大衣</a>
                            <a href="" title="" target="_blank" class="link">风衣</a>
                            <a href="" title="" target="_blank" class="link">夹克</a>
                            <a href="" title="" target="_blank" class="link">马甲</a>
                            <a href="" title="" target="_blank" class="link">你大姨</a>
                            <a href="" title="" target="_blank" class="link">皮衣</a>
                            <a href="" title="" target="_blank" class="link">羽绒服</a>
                            <a href="" title="" target="_blank" class="link">棉服</a>
                            <a href="" title="" target="_blank" class="link">大衣</a>
                            <a href="" title="" target="_blank" class="link">风衣</a>
                            <a href="" title="" target="_blank" class="link">夹克</a>
                            <a href="" title="" target="_blank" class="link">马甲</a>
                            <a href="" title="" target="_blank" class="link">你大姨</a>
                            <a href="" title="" target="_blank" class="link">皮衣</a>
                        </div>
                    </div>
                    <div class="type-item">
                        <div class="type-item-head">
                            <h3 class="type-item-title"><i class="service-man"></i> 男装</h3>
                            <a href="" title="" class="type-item-more">更多</a>
                        </div>
                        <div class="type-item-content">
                            <a href="" title="" target="_blank" class="link">羽绒服</a>
                            <a href="" title="" target="_blank" class="link">棉服</a>
                            <a href="" title="" target="_blank" class="link">大衣</a>
                            <a href="" title="" target="_blank" class="link">风衣</a>
                        </div>
                    </div>
                    <div class="type-item">
                        <div class="type-item-head">
                            <h3 class="type-item-title"><i class="service-bags"></i> 箱包</h3>
                            <a href="" title="" class="type-item-more">更多</a>
                        </div>
                        <div class="type-item-content">
                            <a href="" title="" target="_blank" class="link">羽绒服</a>
                            <a href="" title="" target="_blank" class="link">棉服</a>
                            <a href="" title="" target="_blank" class="link">大衣</a>
                            <a href="" title="" target="_blank" class="link">风衣</a>
                            <a href="" title="" target="_blank" class="link">夹克</a>
                            <a href="" title="" target="_blank" class="link">马甲</a>
                            <a href="" title="" target="_blank" class="link">你大姨</a>
                            <a href="" title="" target="_blank" class="link">皮衣</a>
                        </div>
                    </div>
                    <div class="type-other"><a>其他分类</a></div>
                </div>--%>
            </div>
        </div>
        <div class="nav-right fr">
            <i class="icon icon-message"></i><a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw" target="_blank" class="text-whit ml5">加盟入驻咨询</a><i class="icon icon-pc ml5"></i><a href="http://www.8637.com/about/superiority.jsp" target="_blank" class="text-white ml5">平台优势介绍</a>
        </div>
        <ul class="nav-items js-nav-items clearfix">
            <li class="home<c:if test="${empty param.m || param.m=='1'}"> on</c:if>"><a href="/" title="8637品牌超级代理">首页</a></li>
            <li<c:if test="${param.m=='2'}"> class="on"</c:if>>
                <a href="${ctx}/iambrand/index">我是品牌商</a>
                <div class="nav-item-title">
                    <p>寻找千万店铺</p>
                    <p>加盟简捷快速</p>
                </div>
            </li>
            <li<c:if test="${param.m=='3'}"> class="on"</c:if>><i class="icon icon-hot"></i><a href="${ctx}/common/dealer">我是终端商</a>
                <div class="nav-item-title">
                    <p>国内外知名品牌</p>
                    <p>加盟开店选择多</p>
                </div>
            </li>
            <li<c:if test="${param.m=='4'}"> class="on"</c:if>>
                <a href="${ctx}/meet/deal">在线交易会</a>
                <div class="nav-item-title">
                    <p>报名品牌交易会</p>
                    <p>低价进货利润高</p>
                </div>
            </li>
            <li<c:if test="${param.m=='6'}"> class="on"</c:if>><a href="${ctx}/common/dealer/explosionList">品牌爆款</a>
                <div class="nav-item-title">
                    <p>今时品牌热销款</p>
                    <p>轻松知晓来进货</p>
                </div>
            </li>
            <li<c:if test="${param.m=='5'}"> class="on"</c:if>><i class="icon icon-fq"></i><a href="${ctx}/market/clearance">底价清仓</a>
                <div class="nav-item-title">
                    <p>众多品牌清仓货</p>
                    <p>超低价格质量好</p>
                </div>
            </li>
        </ul>
        <div class="nav-move-item"></div>
    </div>
</div>
