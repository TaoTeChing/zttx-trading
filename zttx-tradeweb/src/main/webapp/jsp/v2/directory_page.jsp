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
        #panel h1{font-size: 12px !important;}
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
                <a href="#">终端商名录</a>
                <span>&gt;</span>
                <span>XXXX服饰店</span>
            </div>
            <div class="m-pages-detail">
                <div class="pic">
                    <div class="pic-con">
                        <div>
                            <ul class="inline-float">
                                <%for(int i=0;i<5;i++){%>
                                <li class="pic-item">
                                    <a href="#" class="js-img-center"></a>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <a class="prev" href="javascript:;">prev</a>
                        <a class="next" href="javascript:;">next</a>
                    </div>
                </div>
                <div class="details">
                    <h1>爱尔倍特服饰西安门店</h1>
                    <p class="sign"><span>2015.08.05 发布</span><span>访问1092次</span></p>
                    <dl>
                        <dt>主营：</dt>
                        <dd><span>男装/女装/内衣</span></dd>
                        <dt>联系人：</dt>
                        <dd><span class="link">李先生</span><%--看了下，后台去掉了留言功能<a href="">给我留言</a>--%></dd>
                        <dt>会员等级：</dt>
                        <dd><i class="iconfont">&#xe601;</i><span>认证会员</span></dd>
                        <dt>联系电话：</dt>
                        <dd>[<a href="${ctx}/common/login" class="redtext">请登录</a>]</dd>
                        <dt>运营状态：</dt>
                        <dd><span class="link">营业中</span></dd>
                        <dt>连锁店数：</dt>
                        <dd><em class="redtext">100家</em></dd>
                        <dt>加盟品牌数：</dt>
                        <dd><em class="redtext">100个</em></dd>
                        <dt>开店日期：</dt>
                        <dd><span>1990.01.01</span></dd>
                        <dt>所在地址：</dt>
                        <dd><span><em id="address">浙江省宁波市海曙区天一广场</em></span></dd>
                    </dl>
                    <p class="tips"><i class="iconfont">&#xe62d;</i>温馨提示：<a href="${ctx}/common/login" class="redtext">登录</a>后才能进行店铺收藏和邀请加盟</p>
                    <p>
                        <a href="#" class="btns"><i class="iconfont">&#xe631;</i>收藏店铺</a>
                        <a href="#" class="btns btns1">邀请店铺加盟</a>
                    </p>
                </div>
            </div>
            <div class="m-map">
                <div class="title">地图查询</div>
                <div class="clearfix">
                    <div class="map_content fl" id="map_content">
                        map
                    </div>
                    <div class="map_search fl">
                        <div class="tabs">
                            <ul class="inline-float tabs-menu">
                                <li class="active"><a href="javascript:;">查找附近</a></li>
                                <li><a href="javascript:;">线路查询</a></li>
                            </ul>
                            <div class="tabs-content">
                                <label class="cate_item"><p><i class="cate_icon_man"></i></p><input class="api_cate" type="checkbox" value="男装"/>男装</label>
                                <label class="cate_item"><p><i class="cate_icon_women"></i></p><input class="api_cate" type="checkbox" value="女装"/>女装</label>
                                <label class="cate_item"><p><i class="cate_icon_ch"></i></p><input class="api_cate" type="checkbox" value="童装"/>童装</label>
                                <label class="cate_item"><p><i class="cate_icon_bra"></i></p><input class="api_cate" type="checkbox" value="内衣"/>内衣</label>
                            </div>
                            <div class="tabs-content" style="display: none;">
                                <div>
                                    <div style="height: 52px;">
                                        <p
                                            style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;word-wrap: normal;"
                                            title="宁波市海曙区天一广场">
                                            当前位置：<span>宁波市海曙区天一广场</span></p>
                                        <input type="text" id="map_searchtext"/>
                                        <button type="button" id="map_searchbtn">查找</button>
                                    </div>
                                    <div id="panel" style=" height:258px; overflow: auto;">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="g-rightside">
            <div class="m-collect">&nbsp;</div>
            <div>
                <a class="register_shop" href="#"><i class="reg_collect"></i>免费登记店铺信息</a>
            </div>
            <div class="m-rightcommon">
                <div class="title"><h3>附近其他商家</h3></div>
                <div class="list_3">
                    <%for(int i=0;i<5;i++){%>
                    <dl>
                        <dt>
                            <a href="#" class="js-img-center">
                                <img src="222" alt=""/>
                            </a>
                        </dt>
                        <dd class="name">
                            <a href="#" title="樱知叶服饰宁波店">樱知叶服饰宁波店</a>
                        </dd>
                        <dd class="c">
                            <span title="樱知叶服饰宁波店">男装|女装|内衣</span>
                        </dd>
                        <dd class="address">
                            <span title="浙江 杭州">浙江 杭州</span>
                        </dd>
                    </dl>
                    <% }%>
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=726ace34e0f0ec5bc372d4fa40c53a45"></script>
<script>
    seajs.use(["${jsrel}/fronts/index/index"], function (Index) {
        //Index.index.init();

        $(".tabs-menu li").click(function(){
            var index = $(this).index();
            $(".tabs-menu li").removeClass("active").eq(index).addClass("active");
            $(".tabs-content").hide().eq(index).show();
        });

        var addressText = $("#address").text();

        //创建和初始化地图函数：
        function initMap(){
            createMap();//创建地图
            setMapEvent();//设置地图事件
            addMapControl();//向地图添加控件
        }

        //创建地图函数：
        function createMap(){
            var map = new BMap.Map("map_content");//在百度地图容器中创建一个地图
            map.centerAndZoom(addressText, 17);//设定地图的中心点和坐标并将地图显示在地图容器中
            var transit = new BMap.TransitRoute(map, {
                renderOptions: {map: map, panel: 'panel'}
            });
            var local = new BMap.LocalSearch(map, {
                renderOptions: {map: map, autoViewport: true}
            });

            //设置全局变量
            window.map = map;
            window.transit = transit;
            window.local = local;
        }

        //地图事件设置函数：
        function setMapEvent(){
            map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
            map.enableScrollWheelZoom();//启用地图滚轮放大缩小
            map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            map.enableKeyboard();//启用键盘上下左右键移动地图
        }

        //地图控件添加函数：
        function addMapControl(){
            //向地图中添加缩放控件
            var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
            map.addControl(ctrl_nav);
            //向地图中添加缩略图控件
            var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
            map.addControl(ctrl_ove);
            //向地图中添加比例尺控件
            var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
            map.addControl(ctrl_sca);
        }

        //创建和初始化地图
        initMap();

        //查询路线方法
        function searchMapTransit(from, to){
            transit.search(from, to);
        }

        //搜索路线
        $("#map_searchbtn").click(function(){
            var _val = $("#map_searchtext").val();
            searchMapTransit(addressText, _val);
        });

        //筛选附近的男装，女装，童装，内衣等
        $(".api_cate").click(function(){
            var arr = [];
            $(".api_cate:checked").each(function(){
                var val = $(this).val();
                arr.push(val);
            });
            local.searchNearby(arr, addressText);
        });
    });
</script>
</body>
</html>