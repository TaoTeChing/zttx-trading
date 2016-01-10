<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>资讯列表 - 最低成本招商加盟渠道快建|一手价格一手货源</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。
    利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，
    打造从厂家到店铺的最短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/info/v2_index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <%--header部分和首页不一样--%>
    <div class="header">
        <div class="header-main" style="z-index: 216;">
            <div class="logo">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="name">
                新闻资讯
            </div>
            <div class="search">
                <form class="form" action="">
                    <input type="text" class="stxt" placeholder="请输入资讯标题"/>
                    <input type="submit" class="sbtn" value=""/>
                </form>
            </div>
        </div>
    </div>
    <%-- //header end --%>
    <div class="nav" id="nav">
        <div class="nav-main">
            <ul>
                <li class="item hover"><a href="#">首页</a></li>
                <li class="item">
                    <a href="#">平台公告</a>
                    <ul class="drop_menu">
                        <li><a href="">平台公告21</a></li>
                        <li><a href="">平台公告22</a></li>
                        <li class="has">
                            <a href="">平台公告23</a>
                            <ul class="menu">
                                <li><a href="">平台公告231</a></li>
                                <li><a href="">平台公告232</a></li>
                                <li class="has">
                                    <a href="">平台公告233</a>
                                    <ul class="menu">
                                        <li class=""><a href="">平台公告2331</a></li>
                                        <li class=""><a href="">平台公告2332</a></li>
                                        <li class=""><a href="">平台公告2333</a></li>
                                        <li class=""><a href="">平台公告2334</a></li>
                                    </ul>
                                </li>
                                <li><a href="">平台公告234</a></li>
                            </ul>
                        </li>
                        <li class=""><a href="">平台公告24</a></li>
                    </ul>
                </li>
                <li class="item"><a href="#">平台资讯</a></li>
                <li class="item"><a href="#">品牌资讯</a></li>
                <li class="item"><a href="#">电商动态</a></li>
                <li class="item"><a href="#">潮流趋势</a></li>
                <li class="item"><a href="#">产经资讯</a></li>
                <li class="item"><a href="#">展会报道</a></li>
                <li class="item"><a href="#">统计报告</a></li>
                <li class="item"><a href="#">政策参考</a></li>
            </ul>
        </div>
    </div>
    <%-- //nav end --%>
    <div class="container">
        <div class="g-main">
            <div class="g-coll">
                <div class="m-title">
                    <h3><em>最新</em>资讯</h3>
                    <a href="#" class="more">&nbsp;</a>
                </div>
                <div class="newest">
                    <div>
                        <%
                            int xx;
                            for(xx = 0;xx<10;xx++){
                        %>
                        <dl>
                            <dt>
                            <h5><a href="">2015春夏趋势要素：镂空网格<%=xx + 1 %></a></h5>
                            <span>4小时前</span>
                            </dt>
                            <dd class="des">
                                本季，镂空网格元素再次回归T台，规整的几何形状密集排列，本季，镂空网格元素再次回归T台，规整的几何形状密集排列，以激光切割或编织工艺为这个春夏打本季，镂空网格元素再次回归T台，规整的几何形状密集排列，以激光切割或编织工艺为这个春夏打...
                            </dd>
                        </dl>
                        <%
                            }
                        %>
                    </div>
                    <div class="more">
                        <a href="">更多文章</a>
                    </div>
                </div>
            </div>
            <%-- //g-coll end --%>
            <div class="g-colr">
                <div class="brand-top">
                    <div class="m-title">
                        <h4>总排行榜</h4>
                    </div>
                    <div class="list">
                        <ul>
                            <li><i class="num_1">1</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_2">2</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_3">3</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">4</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">5</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">6</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">7</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">8</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">9</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                            <li><i class="num_4">10</i><a href="#" title="引导消费--麻纺行业的时尚升级之路">引导消费--麻纺行业的时尚升级之路</a></li>
                        </ul>
                    </div>
                </div>
                <div class="mt20">
                    <a href=""><img src="" alt="" width="300" height="100" /></a>
                    <%--广告 300 * 100--%>
                </div>
                <div class="brand-famous mt20">
                    <div class="m-title">
                        <h4>知名品牌</h4>
                        <a href="#" class="more">&nbsp;</a>
                    </div>
                    <div class="list">
                        <%
                            int x2;
                            for(x2 = 0;x2<6;x2++){
                        %>
                        <dl>
                            <dt><a href=""><img src="" alt="" width="80" height="40"/></a></dt>
                            <dd><a href="" title="蔓身姿">蔓身姿</a></dd>
                        </dl>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="brand-ad mt20">
                    <div class="list">
                        <ul>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <%-- //g-colr end --%>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${src}/fronts/info/index"], function (News) {
        News.init();
    });
</script>
</body>
</html>