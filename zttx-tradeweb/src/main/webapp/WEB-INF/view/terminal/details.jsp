<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>8637品牌超级代理 – 终端商名录</title>
    <link href="${res}/styles/terminal/global.css" rel="stylesheet"/>
    <link href="${res}/styles/terminal/index.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <script src="/scripts/terminal/respond.src.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top" />
    <jsp:include page="/WEB-INF/view/common/component/header.jsp"/>
    <jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="0" name="m"/>
    </jsp:include>
    <div class="em100">
        <div class="row mt10">
            <div class="col-left">
                <div style="height:20px;line-height:20px;font-family: '宋体';"><a href="/">首页</a> &gt;&gt; <a href="/">终端商名录</a> &gt; <strong>艾特贝尔</strong></div>
                <div class="panel mt10">
                    <div class="terminal-picture" style="padding: 10px;">
                        <div id="slide_box" class="slide-box" style="width: 390px;height: 390px">
                            <span class="prev">〈</span>
                            <span class="next">〉</span>
                            <ul class="slide-main ui-switchable-content">
                                <li><a href="#"><img src="" width="390" height="390"/></a></li>
                                <li><a href="#"><img src="" width="390" height="390"/></a></li>
                                <li><a href="#"><img src="" width="390" height="390"/></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="terminal-detail" style="padding: 10px;margin-left: 410px;">
                        <h3>爱尔贝特台州店</h3>
                        <p class="mt10" style="padding-bottom:10px;border-bottom: 1px dotted #ddd;"><span>2014.05.20 发布</span> <span>访问10229次</span></p>
                        <%--<dl><dt>主营:</dt><dd>男装/内衣/女装</dd></dl>
                        <dl><dt>地址:</dt><dd class="js-address">浙江省台州市椒江区</dd></dl>
                        <dl><dt>在线联系:</dt><dd>李先生 <a>给我留言</a></dd></dl>
                        <dl>
                            <dt>联系方式:</dt>

                            <dd><a class="text-red" href="${ctx}经销商后台对应查看地址">立即查看</a></dd>
                        </dl>
                        <dl><dt>店铺简介:</dt><dd>这里有八十个汉字,160个英文字,这里有八十个汉字,160个英文字,这里有八十个汉字,160个英文字</dd></dl>--%>
                        <div style="height: 40px;margin-left: 15px;">
                            <dl>
                                <dt>主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营:</dt>
                                <dd>男装/内衣/女装</dd>
                            </dl>
                        </div>
                        <div style="height: 40px;margin-left: 15px;">
                            <dl>
                                <dt>联&nbsp;系&nbsp;&nbsp;人:</dt>
                                <dd>XXX</dd>
                            </dl>
                        </div>
                        <div class="clearfix" style="height: 40px;margin-left: 15px;">
                            <dl class="fl" style="width: 190px;">
                                <dt>会员等级:</dt>
                                <dd>认证会员</dd>
                            </dl>
                            <dl class="fl" style="width: 150px;">
                                <dt>运营状态:</dt>
                                <dd style="color: #309b00;">认证会员</dd>
                            </dl>
                        </div>
                        <div class="clearfix" style="height: 40px;margin-left: 15px;">
                            <dl class="fl" style="width: 177px;">
                                <dt>连锁店数:</dt>
                                <dd style="color: #c00;">2家</dd>
                            </dl>
                            <dl class="fl" style="width: 150px;">
                                <dt style="width: 80px;">加盟品牌数:</dt>
                                <dd style="color: #c00;">22家</dd>
                            </dl>
                        </div>
                        <div class="clearfix" style="height: 40px;margin-left: 15px;">
                            <dl class="fl" style="width: 190px;">
                                <dt>联系电话:</dt>
                                <dd>
                                    <%--未登录--%>
                                    <a href="javascript:ajaxLogin();" style="padding: 2px 5px;background: #eee;border: 1px solid #ccc; font-size: 12px;">立即查看</a>
                                    <%--已登录--%>
                                    <a class="js-look" href="javascript:;" style="padding: 2px 5px;background: #eee;border: 1px solid #ccc; font-size: 12px;">立即查看</a>
                                </dd>
                            </dl>
                            <dl class="fl" style="width: 150px;">
                                <dt>开店日期:</dt>
                                <dd>2014-01-01</dd>
                            </dl>
                        </div>
                        <div style="height: 40px;margin-left: 15px;">
                            <dl><dt>所在地址:</dt><dd class="js-address">浙江省台州市椒江区</dd></dl>
                        </div>
                        <div style="height: 40px;margin-left: 15px;font-size: 14px;font-weight: bold;color: #666;">
                            <i class="shop-icon shop-icon-remind"></i> 温馨提示：<a href="javascript:ajaxLogin();" style="color: #c00;">登录</a>后才能进行店铺收藏和邀请加盟
                        </div>
                        <div style="height: 40px;margin-left: 15px;">
                            <a href="javascript:;" class="btn btn-orange inline-block js-collect" style="padding:8px 20px;height:20px;line-height:20px;background: #f7f7f7;border: 1px solid #ddd;color: #666;font-size: 14px;"><i class="shop-icon-collect"></i> 收藏店铺</a>
                            <a href="javascript:;" class="btn btn-orange inline-block js-join" style="padding:8px 20px;height:20px;line-height:20px;border: 1px solid #FF941A;font-size: 16px;"><b>邀请店铺加盟</b></a>
                        </div>
                    </div>
                </div>
                <div class="mt10 text-grey" style="padding: 5px 10px;background: #f7f7f7;border: 1px solid #ddd; font-size: 14px;">
                    郑重承诺：所提供的店铺信息如实描述，期待我们的合作
                </div>
                <div class="panel mt10" style="border-top: 0;">
                    <div class="list-head">
                        <ul class="tab-dealer inline-float js-tabs-lu">
                            <li><a class="active">店铺地图</a></li>
                            <li><a>店铺介绍</a></li>
                        </ul>
                        <ul class="tab-order inline-float">
                            <li><a><i class="">&nbsp;</i></a></li>
                        </ul>
                    </div>
                    <div class="js-tabs-div">
                        <div class="js-tabs-con" id="BDmap-content" style="width: 100%; height: 390px;">

                        </div>
                        <div class="js-tabs-con" style="display: none;">
                            这里放 “店铺介绍”；
                        </div>
                    </div>

                </div>
                <div class="main-list mt10">
                <div class="list-head">
                    <ul class="tab-dealer inline-float">
                        <li><a class="active">其他店铺推荐</a></li>
                    </ul>
                    <ul class="tab-order inline-float">
                        <%--<li><a><input class="ui-checkbox" type="checkbox" onclick="setOnlyShowPic()" id="onlyShowPic"> 只看有图</a></li>--%>
                        <li style="border-left: 1px solid #ddd;"><a class="active"><i class="shop-icon shop-icon-grid" title="大图展示">1</i></a></li>
                        <li><a><i class="shop-icon shop-icon-list" title="列表展示">2</i></a></li>
                    </ul>
                </div>
                <div class="list-dealer mt10">
                <ul class="order-grid">
                    <li>
                        <div class="list-item clearfix">
                            <a class="js-img-center" href="details.jsp">
                                <img src="">
                            </a>
                            <div class="info">
                                <div>
                                    <h5>爱慕服饰中山店</h5>
                                    <em>收费用户</em>
                                </div>
                                <p>男装 | 内衣 | 鞋类 | 这里只有一行文字</p>
                                <span class="address"><i class="shop-icon shop-icon-address"></i>浙江·宁波</span>
                                <span class="date">2014.05.20</span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="list-item clearfix">
                            <a class="js-img-center" href="details.jsp">
                                <img src="">
                            </a>
                            <div class="info">
                                <div>
                                    <h5>爱慕服饰中山店</h5>
                                    <em>收费用户</em>
                                </div>
                                <p>男装 | 内衣 | 鞋类 | 这里只有一行文字</p>
                                <span class="address"><i class="shop-icon shop-icon-address"></i>浙江·宁波</span>
                                <span class="date">2014.05.20</span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="list-item clearfix">
                            <a class="js-img-center" href="details.jsp">
                                <img src="">
                            </a>
                            <div class="info">
                                <div>
                                    <h5>爱慕服饰中山店</h5>
                                    <em>收费用户</em>
                                </div>
                                <p>男装 | 内衣 | 鞋类 | 这里只有一行文字</p>
                                <span class="address"><i class="shop-icon shop-icon-address"></i>浙江·宁波</span>
                                <span class="date">2014.05.20</span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="list-item clearfix">
                            <a class="js-img-center" href="details.jsp">
                                <img src="">
                            </a>
                            <div class="info">
                                <div>
                                    <h5>爱慕服饰中山店</h5>
                                    <em>收费用户</em>
                                </div>
                                <p>男装 | 内衣 | 鞋类 | 这里只有一行文字</p>
                                <span class="address"><i class="shop-icon shop-icon-address"></i>浙江·宁波</span>
                                <span class="date">2014.05.20</span>
                            </div>
                        </div>
                    </li>
                </ul>
                </div>
                <div class="pagination ta-c mt10">
                    <a class="prev">&lt;</a>
                    <a class="page current">1</a><a class="page">2</a>
                    <a class="next">&gt;</a>
                </div>
                </div>
            </div>
            <div class="col-right">
                <div class="clearfix" style="height:20px;line-height:20px;"></div>
                <div class="mt10"><a class="btn btn-orange btn-block btn-lg"><i class="shop-icon shop-icon-write"></i>免费登记店铺信息</a></div>

                <div class="panel mt10">
                    <div class="panel-head">
                        <h3>最新登记商家</h3>
                        <div class="panel-head-more"><a><i class="shop-icon shop-icon-more"></i></a></div>
                    </div>
                    <div class="panel-body">
                        <%--<a class="link">男装</a> | <a class="link">女装</a> | <a class="link">童装</a> | <a class="link">内衣</a>--%>
                        <ul class="media-list mt10">
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />
                                </a>
                            </li>
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />
                                </a>
                            </li>
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />
                                </a>
                            </li>
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />
                                </a>
                            </li>
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />
                                </a>
                            </li>
                            <li>
                                <a>
                                    <span class="media"><img src="" /></span>
                                    <p class="media-title">
                                        樱知叶服饰宁波店<br />
                                        <span>男装/女装/内衣</span><br />

                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="panel mt10">
                    <div class="panel-head">
                        <h3>相关行业资讯</h3>
                        <div class="panel-head-more"><a><i class="shop-icon shop-icon-more"></i></a></div>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li><em>2014.08.05</em><a href="#">1.骆华林：无缝内衣领...</a></li>
                            <li><em>2014.08.05</em><a href="#">2.8637品牌超级代理华...</a></li>
                            <li><em>2014.08.05</em><a href="#">3.天下商邦“惊艳”20...</a></li>
                            <li><em>2014.08.05</em><a href="#">4.8637品牌超级代理成...</a></li>
                            <li><em>2014.08.05</em><a href="#">5.天下商邦常务副总裁...</a></li>
                            <li><em>2014.08.05</em><a href="#">6.宁波市委书记刘奇莅...</a></li>
                            <li><em>2014.08.05</em><a href="#">7.家纺业“遇见”新机...</a></li>
                            <li><em>2014.08.05</em><a href="#">8.宁波市副市长王剑侯...</a></li>
                            <li><em>2014.08.05</em><a href="#">9.六月内衣专场，朵彩...</a></li>
                            <li><em>2014.08.05</em><a href="#">10.宁波市长卢子跃视察...</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt10"></div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp"/>
</div>
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>
<div style="display: none;">
    <div class="js-tips">
        <div class="ui-head">
            <h3>温馨提示</h3>
        </div>
        <div>
            <div class="p15 fs14 ta-c yahei">
                <p>您当前还剩余 <span style="color:#24883a;">[200]</span> 次机会</p>
                <p class="mt10">本次操作会扣除 <span class="c-r">[1]</span> 次查看机会，再次对该终端商操作将不再扣除。</p>
            </div>
            <div class="operate p15 fr">
                <a class="confirm_btn ui_button ui_button_lorange" href="javascript:;">确定</a>
                <a class="cancel_btn simple_button" href="javascript:;">取消</a>
            </div>
        </div>
    </div>
    <div class="js-invite">
        <div class="ui-head">
            <h3>邀请加盟</h3>
        </div>
        <div>
            <div style="padding-top: 15px;">
                <form class="ui-form js-invite-form" data-widget="validator">
                    <div class="ui-form-item">
                        <label class="ui-label">品牌选择：</label>
                        <div class="inline-block">
                            <select style="-webkit-appearance: menulist;padding: 5px;" required data-display="品牌">
                                <option value="">品牌1</option>
                                <option value="1">品牌2</option>
                                <option value="">品牌3</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"></label>
                        <textarea class="ui-textarea" style="width: 320px;height: 110px;" required data-display="咨询的问题"></textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"></label>
                        <button type="submit" class="ui_button ui_button_morange">点击提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/common/base-init.js"></script>
<script>
    seajs.use(["slide","tip","dialog"],function(Slide,Tip,Dialog){
        var slide = new Slide({
            element:'#slide_box',
            //trigger:"",
            effect:"scrollx",
            panels:"#slide_box .ui-switchable-content li",
            interval:5000,
            activeIndex:1,
            hasTriggers:false
        }).render();

        $("#slide_box .prev").click(function(e){
            e.preventDefault();
            slide.prev();
        });
        $("#slide_box .next").click(function(e){
            e.preventDefault();
            slide.next();
        });

        seajs.importStyle(".arale-tip-1_2_1 .ui-poptip-container {padding: 5px 5px;}");
        new Tip({
            trigger:".media-list .media",
            arrowPosition:2,
            theme:"blue"
        }).before("show",function(){
           var html = this.activeTrigger.html();
           //$(html).css({width:240,height:200})
           this.set("content",html);
        });

        var items = $(".terminal-rim-item");
        var tabs = $(".terminal-rim ul a").click(function(e){
            e.preventDefault();
            var indx = $(tabs).removeClass("active").index($(this).addClass("active"));
            items.hide().eq(indx).show();
        });

        //需求 #111 增加脚本 开始
        var lookDialog = new Dialog({
            content:$(".js-tips"),
            effect: 'fade',
            hasMask: false,
            width: 450,
            classPrefix:"view-dialog"
        });
        $(".js-look").click(function(){
            lookDialog.show();
        });
        //隐藏
        $(document).on("click",".cancel_btn",function(){
            lookDialog.hide();
        });
        //确定
        $(document).on("click",".confirm_btn",function(){
            alert("确定了，请求发送中...");
            lookDialog.hide();
        });
        //邀请加盟
        var joinDialog = new Dialog({
            content:$(".js-invite"),
            effect: 'fade',
            hasMask: false,
            width: 450,
            classPrefix:"view-dialog"
        });
        $(".js-join").click(function(){
            joinDialog.show();
        });
        //表单校验
        baseFormValidator({
            selector:".js-invite-form",
            isAjax: true,
            beforeSubmitFn:function(){
                alert("校验通过");
                remind("success","店铺已加盟");
            }
        });
        //收藏
        $(".js-collect").click(function(){
            var html = "<i class=\"shop-icon-collect\"></i> 店铺已收藏";
            remind("success","店铺已收藏");
            $(this).html(html);
        });
        //需求#111 增加脚本 结束
    });

    var tabOrder =  $(".tab-order a:gt(0)").click(function(){

        tabOrder.not($(this).addClass("active")).removeClass("active");

        var idx = tabOrder.index(this);
        var listDealer = $(".list-dealer ul");
        if (idx==0){
            /*grid*/
            listDealer.removeClass().addClass("order-grid");
        }
        else{
            /*list*/
            listDealer.removeClass().addClass("order-list");
        }
    });

    //tab切换
    $(".js-tabs-lu li").click(function(){
        var index = $(this).index();
        $(".js-tabs-lu li a").removeClass("active");
                $(this).find("a").addClass("active");
        $(".js-tabs-div .js-tabs-con").hide().eq(index).show();
    });
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=726ace34e0f0ec5bc372d4fa40c53a45"></script><script>
    //引进百度地图
    var map = new BMap.Map("BDmap-content");
    //map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);
    //缩放
    map.enableScrollWheelZoom();

    //两边的比例条
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);

    var BDaddress =  $(".js-address").text();
    map.centerAndZoom(BDaddress,15); //设置上面的地址为焦点

    /*var marker = new BMap.Marker(point);  // 创建标注
     map.addOverlay(marker);               // 将标注添加到地图中
     marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画*/
</script>

</body>
</html>
