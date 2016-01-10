<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html>
<head>
    <title>8637品牌超级代理 – 规则中心</title>
    <meta name="keywords" content="8637, 品牌超级代理,品牌加盟,品牌代理"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/global.css"/>
    <link rel="stylesheet" type="text/css" href="${res }/styles/fronts/help/index.css"/>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <div class="main">
        <div class="main-left">
            <jsp:include page="left.jsp"/>
        </div>
        <div class="main-right">
            <div class="banner">
                <img src="${res}/images/fronts/help/help-banner.jpg" alt="" title="">
            </div>
            <div class="main-content">
                <jsp:include page="_search.jsp"/>
                <h2 class="page-title">规则中心</h2>

                <div class="layout">
                    <div class="layout-item">
                        <div class="panel">
                            <div class="panel-head">
                                <div class="panel-head-title"><h3>最新规则信息</h3></div>
                                <div class="panel-head-more"><a href="${ctx}/rules/list">更多 &gt;&gt;</a></div>
                            </div>
                            <div class="panel-content">
                                <ul class="cont-list">
                                    <c:set value="${fns:getNewRulesInfo(6) }" var="result"/>
                                    <c:if test="${result != null }">
                                        <c:forEach items="${result}" var="rulesInfo">
                                            <c:if test="${'工厂店采购保证金协议' != rulesInfo.articleTitle}">
                                                <li>
                                                    <a title="${rulesInfo.articleTitle}" target="_blank"
                                                       href="${ctx}/rules/info/${rulesInfo.refrenceId}">${rulesInfo.articleTitle}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="layout-item">
                        <div class="panel">
                            <div class="panel-head">
                                <div class="panel-head-title"><h3>重要规则</h3></div>
                                <div class="panel-head-more">
                                    <a href="${ctx }/rules/list?cateId=EA73FFBDCD6243B4ADFDD24B128D0664">更多 &gt;&gt;</a>
                                </div>
                            </div>
                            <div class="panel-content">
                                <ul class="cont-list">
                                    <c:set value="${fns:getRulesInfoByCateKey('EA73FFBDCD6243B4ADFDD24B128D0664',6) }" var="result"/>
                                    <c:if test="${result != null }">
                                        <c:forEach items="${result}" var="rulesInfo">
                                            <li>
                                                <a title="${rulesInfo.articleTitle}" target="_blank"
                                                   href="${ctx}/rules/info/${rulesInfo.refrenceId}">${rulesInfo.articleTitle}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="layout-item">
                        <div class="panel">
                            <div class="panel-head">
                                <div class="panel-head-title"><h3>品牌商招商管理细则</h3></div>
                                <div class="panel-head-more">
                                    <a href="${ctx }/rules/list?cateId=0F0D0A1301FC40A6A01FAB680B843969">更多 &gt;&gt;</a>
                                </div>
                            </div>
                            <div class="panel-content">
                                <ul class="cont-list">
                                    <c:set value="${fns:getRulesInfoByCateKey('0F0D0A1301FC40A6A01FAB680B843969',6) }" var="result"/>
                                    <c:if test="${result != null }">
                                        <c:forEach items="${result}" var="rulesInfo">
                                            <li>
                                                <a title="${rulesInfo.articleTitle}" target="_blank"
                                                   href="${ctx}/rules/info/${rulesInfo.refrenceId}">${rulesInfo.articleTitle}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="layout-item">
                        <div class="panel">
                            <div class="panel-head">
                                <div class="panel-head-title"><h3>终端商招商管理细则</h3></div>
                                <div class="panel-head-more">
                                    <a href="${ctx }/rules/list?cateId=56116FF4A7EC4592A30216C65FC48014">更多 &gt;&gt;</a>
                                </div>
                            </div>
                            <div class="panel-content">
                                <ul class="cont-list">
                                    <c:set value="${fns:getRulesInfoByCateKey('56116FF4A7EC4592A30216C65FC48014',6) }" var="result"/>
                                    <c:if test="${result != null }">
                                        <c:forEach items="${result}" var="rulesInfo">
                                            <li>
                                                <a title="${rulesInfo.articleTitle}" target="_blank"
                                                   href="${ctx}/rules/info/${rulesInfo.refrenceId}">${rulesInfo.articleTitle}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-head">
                        <div class="panel-head-title"><h3>如果您有什么疑问请留下您的意见和建议</h3></div>
                        <%--<div class="panel-head-more"><a href="#">更多 &gt;&gt;</a></div>--%>
                    </div>
                    <div class="panel-content">
                        <form:form id="formSuggestion" class="ui-form" data-widget="validator">
                            <div class="ui-form-item">
                                <label class="ui-label">邮箱：</label>
                                <input name="email" type="email" class="ui-input" required data-display="邮箱">
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">留言：</label>
                                <textarea name="message" class="ui-textarea" data-display="留言" required
                                          placeholder="请在这里输入您的留言"></textarea>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">验证码：</label>
                                <input name="captcha" class="ui-input" data-display="验证码" required style="width: 60px;">
                                <img class="captcha_img" src="${ctx}/captcha" alt=""/>
                            </div>
                            <div class="ui-form-item">
                                <button class="btn orange" type="submit">发表留言</button>
                                <button class="btn" type="reset">重 置</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<%--<a class="ui-scrolltop" title="返回顶部">返回顶部</a>--%>
<script src="${res }/scripts/jquery.min.js"></script>
<script src="${res }/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res }/scripts/seajs_config.js"></script>
<script src="${res }/scripts/common.js"></script>
<script src="${res }/scripts/src/common/base-init.js"></script>
<script src="${src }/fronts/help/help.js"></script>
<script>
    //点击验证码图片
    $(document).on("click", ".captcha_img", function () {
        var url = "${ctx}/captcha?t=" + Math.random();
        $(this).attr("src", url);
    })

    seajs.use(["validator", "widget", "djmenu"], function (Validator, Widget) {
        Widget.autoRenderAll();
        var Core = Validator.query("#formSuggestion");
        Core.set("autoSubmit", false);
        Core.on('formValidated', function (error) {
            if (!error) {
                $.post("${ctx }/common/suggestion/add", $("#formSuggestion").serialize(), function (data) {
                    if (data.code == zttx.SUCCESS) {
                        remind("success", data.message, 2000, function () {
                            window.location.href = window.location.href;
                        });
                    } else {
                        remind("error", data.message);
                        $(".captcha_img").attr("src", "${ctx}/captcha?t=" + Math.random());
                    }
                }, "json");
            }
        });
        $('#tree').djMenu({
            url: "",         //请求地址
            parameter: "",   //请求参数
            val: "id",         //请求参数的 data-val
            templateId: "nav_list"   //需要使用模版的id
        });
    });
</script>
</body>
</html>
