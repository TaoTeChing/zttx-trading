<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>8637品牌超级代理 - 较低成本招商加盟渠道快建|一手价格一手货源</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。
    利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，
    打造从厂家到店铺的较短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <%--专门用于做专题入口的位置预留， 勿删除，有用...
    <div id="zhuanti">
        <style>
            .special-gg-1{width:100%;min-width:1200px;height:300px;border-bottom: 1px solid #ddd;background: url('${res}/adverti/haerbin/img/special/special_bg.jpg') no-repeat center top;display: none;overflow: hidden;}
            .special-cen-1,.special-cen-2,.special-cen-3{width: 1200px;margin: 0 auto;position: relative;}
            .special-cen-1{height:300px;}
            .special-original-01{position:absolute;top:0px;right:93px;z-index: 5;opacity:0; filter:alpha(opacity=0);width:417px;height:300px;background: url('${res}/adverti/haerbin/img/special/special_01.png') no-repeat;}
            .special-original-02{position:absolute;top:0px;right:570px;z-index: 4;opacity:0; filter:alpha(opacity=0);width:450px;height:195px;background: url('${res}/adverti/haerbin/img/special/special_02.png') no-repeat;}
            .special-original-03{position:absolute;top:300px;right:522px;z-index: 6;opacity:0; filter:alpha(opacity=0);width:376px;height:116px;background: url('${res}/adverti/haerbin/img/special/special_03.png') no-repeat;}
            .special-gg-2{width: 100%;min-width:1200px;height: 90px;border-bottom: 1px solid #ddd;background: url('${res}/adverti/haerbin/img/special/special_pic_01.jpg') no-repeat center top;display: none;overflow: hidden;}
            /*.special-cen-2{height: 90px;background: url('${res}/adverti/haerbin/img/special/special_pic_01.jpg') no-repeat -360px 0;}*/
            #special-gg-close{position: absolute;top:12px;right:-40px;width: 13px;height: 13px;background: url('${res}/adverti/haerbin/img/special/wsh.png') no-repeat;cursor: pointer;}
            .special-gg-3{width: 100%;display: none;}
            #special-gg-open{padding: 5px;position: absolute;top:-24px;;right: -45px;border:1px solid #ddd;border-top:0;border-radius: 0 0 5px 5px;width: 13px;height: 13px;background: url('${res}/adverti/haerbin/img/special/open.png') no-repeat center 5px;cursor: pointer;}
        </style>
        <a href="${rtx}/tradeMeeting/activity" target="_blank">
            <div class="special-gg-1">
                <div class="special-cen-1">
                    <div class="special-original-01"></div>
                    <div class="special-original-02"></div>
                    <div class="special-original-03"></div>
                </div>
            </div>
            <div class="special-gg-2">
                <div class="special-cen-2">
                    <div id="special-gg-close"></div>
                </div>
            </div>
            <div class="special-gg-3">
                <div class="special-cen-3">
                    <div id="special-gg-open"></div>
                </div>
            </div>
        </a>
        <script>
            window.onload = function(){
                function showSpecialAni(){
                    $(".special-gg-1").slideDown(500, function(){
                        $(".special-original-01").animate({
                            top: 0,
                            right: 169,
                            opacity: 1
                        }, 1000);
                    });
                    setTimeout(function(){
                        $(".special-original-02").animate({
                            top: 0,
                            right: 493,
                            opacity: 1
                        }, 1500);
                        $(".special-original-03").animate({
                            top: 167,
                            right: 522,
                            opacity: 1
                        }, 1500, function(){
                            setTimeout(function(){
                                //$(".special-gg-1").hide();
                                $(".special-gg-1").slideUp(618, function(){
                                    $(".special-gg-2").slideDown(500);
                                    $(".special-gg-1 .special-original-01").css({top: 0, right: 93, opacity: 0});
                                    $(".special-gg-1 .special-original-02").css({top: 0, right: 570, opacity: 0});
                                    $(".special-gg-1 .special-original-03").css({top: 300, right: 522, opacity: 0});
                                });
                            }, 6000);//第一屏大图停留时间在这里控制
                        });
                    }, 1000);
                }
                showSpecialAni();
                //关闭
                $("#special-gg-close").click(function(){
                    $(".special-gg-2").slideUp(500, function(){
                        $(".special-gg-3").show();
                        $("#special-gg-open").animate({"top":0}, 250);
                    });
                    return false;
                });
                //打开
                $("#special-gg-open").click(function(){
                    $("#special-gg-open").animate({
                        top: -24
                    }, 250, function(){
                        $(".special-gg-3").hide();
                        showSpecialAni();
                    });
                    return false;
                });
            };
        </script>
    </div>--%>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp">
        <jsp:param value="0" name="m"/>
    </jsp:include>
    <div class="main">
        <%--平台优势 --%>
        <jsp:include page="/WEB-INF/view/fronts/index/_part-a.jsp"/>
        <%-- 优选品牌 --%>
        <jsp:include page="/WEB-INF/view/fronts/index/_part-b.jsp"/>
        <div class="ts-container">
            <c:set var="notIn" scope="session" value="${advert_index_middle}"/>
            <%--女装品牌馆 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-c.jsp"/>
            <%--男装品牌馆 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-d.jsp" />
            <%--内衣家居品牌馆 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-d2.jsp" />
            <%--箱包 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-d3.jsp" />
            <%--童装母婴 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-e.jsp"/>
            <%--广告展示 --%>
            <jsp:include page="/WEB-INF/view/fronts/index/_part-f.jsp"/>
        </div>
        <jsp:include page="/WEB-INF/view/fronts/index/_part-g.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
    <jsp:include page="/WEB-INF/view/include/bottom.jsp"/>
</div>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/index/index"], function (Index) {
        Index.index.init();
    });
</script>
<!--升级维护 可以注释，别删除，以后还有用-->
<%--<div style="height:50px;line-height:50px;background: #e97e00;position: fixed;left:0;right:0;bottom: 0;z-index: 999;">
    <div style="width: 1190px;margin: 0 auto;color: #fff;text-align: center;font-size: 14px;">
        公告：8637品牌超级代理（www.8637.com）网站于2015年10月21日21点进行全面升级维护各项功能中，给您带来不便，恳请谅解。
    </div>
</div>--%>
</body>
</html>