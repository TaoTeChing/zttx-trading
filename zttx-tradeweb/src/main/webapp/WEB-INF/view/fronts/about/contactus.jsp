<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>联系我们 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,天下商邦" />
    <meta name="description" content="8637品牌超级代理以O2O(Online to Offline)模式构建零中间商的“电子商务直供平台”，利用互联网技术服务品牌商及终端销售商，改变传统品牌供应链（专卖店、批发商（代理商）、品牌商），将通过互联网直连方式减少中间供应环节，打造从厂家到店铺的较短供应链。" />

    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet" />
    <link href="${res}/styles/fronts/about/contactus.css" rel="stylesheet" />
    <style>
        area{outline: none;}
        .ui-dlist .ui-dlist-det{width: 80%;}
        .ui-dlist .ui-dlist-tit{width: 70px;}
    </style>
</head>
<body>

<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="_header.jsp" >
        <jsp:param value="5" name="m"/>
    </jsp:include>
    <div class="main">
        <div class="ts-container">
            <div class="ts-mb-sm ts-mt-sm">
                <span>当前所在页</span> : <a class="text-primary" href="/">首页</a> > <a class="text-primary" href="/about/">关于我们</a> > <span class="text-danger">联系我们</span>
            </div>
            <div class="hr-dashed ts-mb-sm"></div>
            <div class="ts-mb-lg">
                <img src="${res}/images/fronts/about/about-index-pic8.jpg">
            </div>
            <div id="tabs-panel">
                <div class="ts-mb-sm">
                    <ul class="about-tab text-yahei text-md inline-float">
                        <li><a class="ui-switchable-active" href="javascript:;">联系我们</a></li>
                    </ul>
                </div>
                <div class="hr-dashed ts-mb-sm"></div>
                <div class="index-content">
                    <div class="about-tab-item content-a">
                        <div class="clearfix m-conus">
                            <div class="fl fore1">
                                <h3>服务热线</h3>
                                <dl class="clearfix detail_1">
                                    <dt>招商热线：</dt>
                                    <dd><em>400-111-8637</em></dd>
                                    <dt>客服热线：</dt>
                                    <dd><em>0574-87217777</em></dd>
                                    <dt>传真号码：</dt>
                                    <dd><em>0574-87219517</em></dd>
                                    <dt>综合邮箱：</dt>
                                    <dd><em>info@8637.com</em></dd>
                                    <dt>人事邮箱：</dt>
                                    <dd><em>hr@8637.com</em></dd>
                                    <dt>服务时间：</dt>
                                    <dd>周一到周五 8:30-17:00</dd>
                                </dl>
                            </div>
                            <div class="fr fore2">
                                <h3>办公区域</h3>
                                <div class="clearfix">
                                    <div class="fl fore2-1">
                                        <h5>宁波市电子商务产业园</h5>
                                        <dl class="clearfix detail_2">
                                            <dt>联系地址：</dt>
                                            <dd>中国浙江省宁波市海曙区丽园北路755号 宁波市电子商务产业园14楼/18楼</dd>
                                            <dt>交通路线：</dt>
                                            <dd>天一家园：902
                                                市中医院东（海曙交警大队）：33、527、827
                                                市中医院：5、9、630、631、613-1、813、902</dd>
                                            <dt>邮政编码：</dt>
                                            <dd>315000</dd>
                                        </dl>
                                    </div>
                                    <div class="fr fore2-2">
                                        <h5>恒茂大厦</h5>
                                        <dl class="clearfix detail_2">
                                            <dt>联系地址：</dt>
                                            <dd>中国浙江省宁波市海曙区民通街100号恒茂大厦10层</dd>
                                            <dt>交通路线：</dt>
                                            <dd>天一家园：902
                                                市中医院东（海曙交警大队）：33、527、827
                                                市中医院：5、9、630、631、613-1、813、902</dd>
                                            <dt>邮政编码：</dt>
                                            <dd>315000</dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <%--<img src="${res}/images/about/about-index-pic9.jpg" />--%>
                            <img src="${res}/images/fronts/about/contact-map.jpg" usemap="#contactMap" style="border: 1px solid #e5e5e5;"/>
                            <map name="contactMap" id="contactMap">
                                <area shape="rect" coords="91,421,267,481" href ="http://j.map.baidu.com/fHqg0" target="_blank" style="cursor: pointer" />
                                <area shape="rect" coords="466,174,603,202" href="http://j.map.baidu.com/b6Ag0" target="_blank" style="cursor: pointer" />
                            </map>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- #include file="_footer.asp" -->
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${src}/common/base-init.js"></script>
</div>
</body>
</html>
