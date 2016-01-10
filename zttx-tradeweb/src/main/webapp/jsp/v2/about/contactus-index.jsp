<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>联系我们</title>
    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/about/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/fronts/about/_header.jsp">
        <jsp:param value="4" name="m"/>
        <jsp:param value="4" name="n"/>
        <jsp:param value="1" name="k"/>
    </jsp:include>
    <div class="main">
        <div class="contact-pic"></div>
        <div class="contentus-main">
            <div class="contentus-message">
                <div class="contentus-phone">
                    <span><img src="${res}/images/fronts/about/contactus_02.jpg">服务热线</span>
                    <ul>
                        <li>招商热线：<p>400-111-8637</p></li>
                        <li>客服热线：<p>0574-87217777 </p></li>
                        <li>传真号码：<p>0574-87219517</p></li>
                        <li>综合邮箱：<p>info@8637.com</p></li>
                        <li>人事邮箱：<p>hr@8637.com</p></li>
                        <li>服务时间：周一到周五 8.30-17：00</li>
                    </ul>
                </div>
                <div class="contentus-area">
                    <span><img src="${res}/images/fronts/about/contactus_03.jpg">办公区域</span>
                    <div class="contentus-area1">
                        <p>宁波市电子商务产业园</p>
                        <ul>
                            <li><i>联系地址：</i><p>中国浙江省宁波市海曙区丽园北路755号 宁波市电子商务产业园14楼/18楼</p></li>
                            <li>
                                <i>交通路线：</i>
                                <div style="overflow: hidden">
                                <p>天一家园：902</p>
                                <p>市中医院东（海曙交警大队）：33、527、827</p>
                                <p>市中医院：5、9、630、631、613-1、813、902</p>
                                </div>
                            </li>
                            <li> <i>邮政编码：</i><p>315000</p></li>
                        </ul>
                    </div>
                    <div class="contentus-area2">
                        <p>恒茂大厦</p>
                        <ul>
                            <li><i>联系地址：</i><p>中国浙江省宁波市海曙区民通街100号恒茂大厦10层</p></li>
                            <li>
                                <i>交通路线：</i>
                                <div style="overflow: hidden">
                                    <p>天一家园：902</p>
                                    <p>市中医院东（海曙交警大队）：33、527、827</p>
                                    <p>市中医院：5、9、630、631、613-1、813、902</p>
                                </div>
                            </li>
                            <li> <i>邮政编码：</i><p>315000</p></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="contentus-map"></div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
</div>
</body>
</html>