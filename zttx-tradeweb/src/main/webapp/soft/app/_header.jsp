<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="header ${param.fixed}">
    <div class="header-bd clearfix">
        <div class="logo fl">
            <a href="index.jsp">
                <img src="${res}/images/soft/app/shop-icon.png" alt=""/>
                <span class="product">微逛</span>
            </a>
            <a href="/index.jsp" class="descript">8637品牌超级代理旗下产品</a>
        </div>
        <div class="nav fr">
            <a target="_blank" href="${res}/upload/soft/app/weiguang_V1.0.0_8637_release_201409121348.ipa" class="download-btn" title="iphone">
                <i class="apple"></i>
                <span class="hardware">
                    <span>iphone版下载</span>
                    <span class="version">版本号 : V1.2</span>
                </span>
            </a>
            <a target="_blank" href="${res}/upload/soft/app/weiguang_V1.0.2_8637_release_201410231113.apk" class="download-btn" title="Android">
                <i class="android"></i>
                <span class="hardware">
                    <span>Android版下载</span>
                    <span class="version">版本号 : V1.1</span>
                </span>
            </a>
            <a href="WeShop-Guide.jsp" class="link">我要开微店</a>
            <a href="update.jsp" class="link green">更新日志</a>
        </div>
    </div>
</div>