<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>8637品牌超级代理 - 终端商名录</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <style>
        .nav {
            border-bottom: 2px solid #ed0100;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp"/>
    <div class="ts-container clearfix" style="margin-bottom: 30px;">
        <div class="g-leftside">
            <div class="m-breadnav">
                <a href="/">首页</a>
                <span>&gt;&gt;</span>
                <span>终端商名录</span>
            </div>
            <div>
                <div class="m-selected">
                    <span>已选条件：</span>
                    <a href="#">箱包<i>×</i></a>
                    <a href="#">江东<i>×</i></a>
                    <a href="#">清空<i>×</i></a>
                </div>
                <div class="m-condition">
                    <dl>
                        <dt>行业分类：</dt>
                        <dd><a class="active" href="#">不限</a><a href="#">服装</a><a href="#">鞋</a></dd>
                    </dl>
                    <dl>
                        <dt>所在地区：</dt>
                        <dd><a class="active" href="#">不限</a><a href="#">江东</a></dd>
                    </dl>
                    <dl class="last">
                        <dt>销售模式：</dt>
                        <dd><a class="active" href="#">不限</a><a href="#">专卖店</a><a href="#">混合店</a><a href="#">专卖加混合店</a><a href="#">其他</a></dd>
                    </dl>
                </div>
                <div class="">
                    <div class="m-tab">
                        <ul class="inline-float">
                            <li class="active"><a href="#">全部</a></li>
                            <li><a href="#">街铺</a></li>
                            <li><a href="#">市场/商场</a></li>
                            <li><a href="#">集散市场</a></li>
                        </ul>
                    </div>
                    <div class="m-dirlist">
                        <ul>
                            <%for(int i=0;i<5;i++){%>
                            <li>
                                <div class="pic">
                                    <a class="js-img-center" href="#"><img src="123" alt=""/></a>
                                </div>
                                <div class="detail">
                                    <h3><a href="#">鳄鱼T恤</a></h3>
                                    <p>
                                        <span>内衣<em>|</em>女装<em>|</em>其他的分类</span>
                                        <span>所在地区：江东</span>
                                        <span>销售模式：专卖店</span></p>
                                    <p class="times">累计进货次数：2</p>
                                </div>
                                <div class="address">
                                    浙江·杭州
                                </div>
                            </li>
                            <% }%>
                        </ul>
                    </div>
                    <div class="pagination mt15">
                        <!-- 上一页 -->
                        <a class="prev" href="javascript:">上一页</a>
                        <!-- 第一页 -->
                        <a class="page current" href="javascript:$Z.goPage(searchForm,1);">1</a>
                        <!-- 显示的页码 -->
                        <!-- 设置 iLeftNum 和 iRightNum -->
                        <!-- 显示的 ... -->
                        <!-- 显示的页码 -->
                        <a class="page" href="javascript:$Z.goPage(searchForm,2);">2</a>&nbsp;
                        <a class="page" href="javascript:$Z.goPage(searchForm,3);">3</a>&nbsp;
                        <a class="page" href="javascript:$Z.goPage(searchForm,4);">4</a>&nbsp;
                        <a class="page" href="javascript:$Z.goPage(searchForm,5);">5</a>&nbsp;
                        <a class="page" href="javascript:$Z.goPage(searchForm,6);">6</a>&nbsp;
                        <!-- 显示的... -->
                        <span class="ellipsis">…</span>
                        <!-- 最后一页 -->
                        <a class="page" href="javascript:$Z.goPage(searchForm,105);">105</a>
                        <!-- 下一页 -->
                        <a class="next" href="javascript:$Z.goNext(searchForm)">下一页</a>
                        <!-- 记录数统计 -->
                        <span class="total">共有5209笔记录</span>
                    </div>
                    <div class="directory_tips">
                        <span class="fl">以上同类终端商较少，其他终端商不妨考虑一下：</span>
                        <a class="fr" href="#">搜更多周边终端上&gt;&gt;</a>
                    </div>
                    <%--下面放和上面一样的终端商店铺列表--%>
                    <div class="m-dirlist">
                        <ul>
                            <%for(int i=0;i<3;i++){%>
                            <li>
                                <div class="pic">
                                    <a class="js-img-center" href="#"><img src="123" alt=""/></a>
                                </div>
                                <div class="detail">
                                    <h3><a href="#">鳄鱼T恤</a></h3>
                                    <p>
                                        <span>内衣<em>|</em>女装<em>|</em>其他的分类</span>
                                        <span>所在地区：江东</span>
                                        <span>销售模式：专卖店</span></p>
                                    <p class="times">累计进货次数：2</p>
                                </div>
                                <div class="address">
                                    浙江·杭州
                                </div>
                            </li>
                            <% }%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="g-rightside">
            <div class="m-collect">
                <a class="collect" href="#">添加到浏览器收藏夹</a>
                <a href="#"><img src="/images/fronts/market/qq-service.png" alt=""/></a>
            </div>
            <div>
                <a class="register_shop" href="#"><i class="reg_collect"></i>免费登记店铺信息</a>
            </div>
            <div class="m-rightcommon">
                <div class="title"><h4>为什么要登记店铺信息？</h4></div>
                <div>
                    <ul class="list_1">
                        <li><span>1.</span><a href="#" title="快速采购更低的品牌进货价格">快速采购更低的品牌进货价格</a></li>
                        <li><span>2.</span><a href="#" title="及时掌握最全面的品牌信息">及时掌握最全面的品牌信息</a></li>
                        <li><span>3.</span><a href="#" title="少批量多批次，资金周转快">少批量多批次，资金周转快</a></li>
                    </ul>
                </div>
            </div>
            <div class="m-rightcommon">
                <div class="title"><h3>最新店铺登记信息</h3></div>
                <div>
                    <ul class="list_2">
                        <%for(int i=0;i<10;i++){%>
                            <%if(i < 3){%>
                            <li><span class="num_1"><%=i+1%></span><a href="#" title="樱知叶服饰宁波店">樱知叶服饰宁波店</a></li>
                            <%}else{%>
                            <li><span><%=i+1%></span><a href="#" title="樱知叶服饰宁波店">樱知叶服饰宁波店</a></li>
                            <%}%>
                        <% }%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/index/index"], function (Index) {
        Index.index.init();
    });
</script>
</body>
</html>