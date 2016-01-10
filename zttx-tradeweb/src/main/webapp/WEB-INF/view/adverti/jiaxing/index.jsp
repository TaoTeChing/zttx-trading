<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>8637线下交易会_嘉兴站 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦" />
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。" />
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <link href="${res}/adverti/jiaxing/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="${ctx}/common/top"/>
<jsp:include page="/WEB-INF/view/include/search.jsp"/>
<div class="wallpaper_01">
    <%--<img src="${res}/adverti/jiaxing/img/jiaxing_01.jpg"/>--%>
</div>
<div class="wallpaper_02">
    <div>
        <img class="img_2" src="${res}/adverti/jiaxing/img/jiaxing_02.jpg" width="1190"/>
    </div>
    <div class="m-f-list">
        <dl class="clearfix">
            <dt><img src="${res}/adverti/jiaxing/img/j_l_1.png" /></dt>
            <dd>
                <div class="fl"><img src="${res}/adverti/jiaxing/img/j_r_1.png" alt=""/></div>
                <ul class="inline-float fl">
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_01.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_02.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_03.png" alt=""/>
                    </li>
                </ul>
            </dd>
        </dl>
        <dl class="clearfix">
            <dt><img src="${res}/adverti/jiaxing/img/j_l_2.png" /></dt>
            <dd>
                <div class="fl"><img src="${res}/adverti/jiaxing/img/j_r_2.png" alt=""/></div>
                <ul class="inline-float fl">
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_04.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_05.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_06.png" alt=""/>
                    </li>
                </ul>
            </dd>
        </dl>
        <dl class="clearfix">
            <dt><img src="${res}/adverti/jiaxing/img/j_l_3.png" /></dt>
            <dd>
                <div class="fl"><img src="${res}/adverti/jiaxing/img/j_r_3.png" alt=""/></div>
                <ul class="inline-float fl">
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_07.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_08.png" alt=""/>
                    </li>
                    <li>
                        <img src="${res}/adverti/jiaxing/img/ra/j_ra_09.png" alt=""/>
                    </li>
                </ul>
            </dd>
        </dl>
    </div>
    <div class="m-f-logins clearfix">
        <div class="log-coll fl">
            <h2>品牌商报名<img src="${res}/adverti/jiaxing/img/j_j.png" alt=""/></h2>
            <form class="ui-form" action="${ctx}/tradeMeeting/activity/signup">
                <input type="hidden" name="userType" value="0" />
                <div class="ui-form-item clearfix">
                    <label class="ui-label">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
                    <input type="text" class="ui-input" name="realName"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">电&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
                    <input type="text" class="ui-input" name="mobile"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">经营类目：</label>
                    <input type="text" name="businessCategory" class="ui-input"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">&nbsp;</label>
                    <input type="submit" class="btn1" value="提交"/>
                </div>
            </form>
        </div>
        <div class="log-colr fr">
            <h2>终端商报名<img src="${res}/adverti/jiaxing/img/j_j.png" alt=""/></h2>
            <form class="ui-form" action="${ctx}/tradeMeeting/activity/signup">
                <input type="hidden" name="userType" value="1" />
                <div class="ui-form-item clearfix">
                    <label class="ui-label">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
                    <input type="text" class="ui-input" name="realName"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">电&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
                    <input type="text" class="ui-input" name="mobile"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">经营类目：</label>
                    <input type="text" class="ui-input" name="businessCategory"/>
                </div>
                <div class="ui-form-item clearfix">
                    <label class="ui-label">&nbsp;</label>
                    <input type="submit" class="btn1" value="提交"/>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="m-f-bottom">
    <div class="center">
        <p class="p1">地址：嘉兴香溢大酒店</p>
        <p class="p2">咨询电话：400-111-8637 13058806396（刘先生） 15105748637（郭先生）</p>
        <p class="p3">好的产品 好的政策 现场大促销 惊喜不断<span class="span1"> ！！！</span></p>
    </div>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/src/common/base-init.js"></script>
<script>
    $(function(){
        $('.ui-form').find(".btn1").click(function () {
            var parent = $(this).parents(".ui-form");
            var Parameter = {
                realName: parent.find("[name=realName]").val(),
                mobile: parent.find("[name=mobile]").val(),
                businessCategory: parent.find("[name=businessCategory]").val(),
                userType: parent.find("[name=userType]").val(),
                stageCode: 'jiaxing'
            };

            if (Parameter.realName.length > 1 && Parameter.realName.length < 5
                    && Parameter.mobile.length == 11 && Parameter.businessCategory.length < 33) {
                $.post("${ctx}/adverti/meetingJoin/save", Parameter, function (data) {
                    if (data.code == 126000) {
                        alert("恭喜您，"+Parameter.realName+"！已报名成功。我们会在3个工作日内与您取得联系，祝您商祺！");
                    }
                    else{
                        alert(data.message);
                    }
                }, 'json');
            }
            else {
                alert("请认真填写报名信息");
            }

            return false;
        });
    });
</script>
</body>
</html>