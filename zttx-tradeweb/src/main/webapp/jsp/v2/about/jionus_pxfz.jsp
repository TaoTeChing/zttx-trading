<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>加入我们-培训发展</title>
    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/about/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <style>

    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/fronts/about/_header.jsp">
        <jsp:param value="4" name="m"/>
        <jsp:param value="3" name="n"/>
        <jsp:param value="2" name="k"/>
    </jsp:include>
    <div class="main">
        <div class="pxfz-pic"></div>
        <div class="pxfz-content">
            <table>
        <colgroup>
            <col width="198">
            <col width="350">
            <col width="198">
            <col width="350">
        </colgroup>
        <thead>
        <tr>
            <td style="border-right: 1px solid #fff">培训类别</td>
            <td style="border-right: 1px solid #e5e5e5;text-align: left;padding-left: 100px">培训内容</td>
            <td style="border-right: 1px solid #fff">培训类别</td>
            <td style="text-align: left;padding-left: 100px">培训内容</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>基础素质</td>
            <td>
                <p>着装礼仪、社交礼仪</p>
                <p>素质拓展</p>
                <p>有效沟通、有效执行、逻辑训练</p>
            </td>
            <td>兴趣爱好</td>
            <td>
                <p>心理学</p>
                <p>茶道、中医养生</p>
                <p>化妆美容</p>
                <p>摄影</p>
            </td>
        </tr>
        <tr>
        <tr>
            <td>管理培训</td>
            <td>
                <p>目标管理、时间管理</p>
                <p>人才培养计划</p>
                <p>专题管理培训</p>
            </td>
            <td>岗位培训</td>
            <td>
                <p>一对一导师制</p>
                <p>岗位流程、技能</p>
                <p>行业趋势、标准分析</p>
            </td>
        </tr>
        </tr>
        <tr>
            <td>技能培训</td>
            <td colspan="3">
                <p>敏捷开发</p>
                <p>从技术走向管理</p>
                <p>商业训练</p>
            </td>
        </tr>
        </tbody>
    </table>
    </div>
        <div class="pxfz-photo">
            <p>培训摄影</p>
            <div id="pxfz-lbt">
                    <ul data-role="content" class="ui-switchable-content">
                        <li class="ui-switchable-panel">
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px02.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px02.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px02.jpg" /></a></li>
                        <li class="ui-switchable-panel">
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px03.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px03.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px03.jpg" /></a>
                        </li>
                        <li class="ui-switchable-panel">
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px04.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px04.jpg" /></a>
                            <a href="#"><img src="${res}/images/fronts/about/jionus_px04.jpg" /></a>
                        </li>
                    </ul>
                    <ul data-role="nav" class="ui-switchable-nav" style="display: block">
                        <li class="ui-switchable-trigger ui-switchable-active">●</li>
                        <li class="ui-switchable-trigger" >●</li>
                        <li class="ui-switchable-trigger">●</li>
                    </ul>
                </div>
            </div>
        <div class="pxfz-td">
            <p>如果你经过了培训和个人工作努力尝试后，发现这个岗位并不适合自己，可以在任何时候以任何方式提交调岗申请。</p>
            <p>我们鼓励每个人找到自己喜欢的方向并将之做到极致，任何岗位做到精深都会受人尊敬，并非一定要从事管理工作。</p>
            <p>在这里，管理只是一种职能，而并不是一种层级。普通同事的工资高于管理人员是很普遍的情况。</p>
            <img src="${res}/images/fronts/about/jionus_px05.jpg"/>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
</div>
</body>

<script>
    seajs.use(['slide'], function(Slide){
        new Slide({
            element: '#pxfz-lbt',
            easing: 'easeOutStrong',
            effect: 'fade',
            autoplay: true,
            interval:5000
        }).render();
    });
</script>
</html>