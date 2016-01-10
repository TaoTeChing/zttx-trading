<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修-产品展示</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />
    <link href="${res}/styles/market/brandviewbase.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/market/prodisshow.css" rel="stylesheet" type="text/css" />
    <style>
        <!--
        -->
    </style>
</head>
<body>
    <jsp:include page="${ctx}/common/top" />
    <!--// top end-->
	<jsp:include page="/WEB-INF/view/market/view_head.jsp"></jsp:include>	
    <!--// header end-->
    <div class="all-prodisshow">
        <div class="prodisshow clear">
            <div class="comsite">
                <div class="comlogobox f-l">
                    <img src="${ctx}/images/market/temp/duo.jpg" /></div>
                <div class="purchase">
                    <a class="" href="#" title="" target="">快速采购<span>清爽列表+货号</span></a>
                    <p class="font">我的收藏：10件 我的进货单：10件</p>
                </div>
                <div class="comname">朵彩/DOCARE<span>中国十大内衣品牌</span></div>
                <div class="comclassic"><a class="selected" href="#" title="" target=""><span>总款式191件</span></a><a class="selected" href="#" title="" target=""><span>2013款（51件）</span></a><a class="selected" href="#" title="" target=""><span>2012款（51件）</span></a><a class="" href="#" title="" target=""><span>2011款（51件）</span></a><a href="#" title="" target=""><span>更早>></span></a></div>
            </div>
            <!--//comsite-->
            <p class="title clear">选择朵彩品牌产品浏览方式</p>
            <div class="photoshow">
                <ul>
                    <li><a href="#" title="" target="">
                        <img src="${ctx}/images/market/photo.jpg" /></a></li>
                    <li><a href="#" title="" target="">
                        <img src="${ctx}/images/market/gei.jpg" /></a></li>
                </ul>
            </div>
        </div>
    </div>
    <!--// listbody -->
    <div class="footer clear">
        <div class="foot">
            <ul>
                <li>
                    <span class="foot-ppbz">品牌保障</span>
                    <a href="#" title="" target="">·正品授权</a>
                    <a href="#" title="" target="">·线下实体</a>
                    <a href="#" title="" target="">·合同制</a>
                </li>
                <li>
                    <span class="foot-xsbz">新手帮助</span>
                    <a href="#" title="" target="">·正品授权</a>
                    <a href="#" title="" target="">·线下实体</a>
                    <a href="#" title="" target="">·合同制</a>
                </li>
                <li>
                    <span class="foot-qdgl">渠道管理</span>
                    <a href="#" title="" target="">·正品授权</a>
                    <a href="#" title="" target="">·线下实体</a>
                    <a href="#" title="" target="">·合同制</a>
                </li>
                <li>
                    <span class="foot-ppszq">品牌商专区</span>
                    <a href="#" title="" target="">·正品授权</a>
                    <a href="#" title="" target="">·线下实体</a>
                    <a href="#" title="" target="">·合同制</a>
                </li>
                <li>
                    <span class="foot-jxszq">终端商专区</span>
                    <a href="#" title="" target="">·正品授权</a>
                    <a href="#" title="" target="">·线下实体</a>
                    <a href="#" title="" target="">·合同制</a>
                </li>
            </ul>
            <div class="foot-code f-l">
                <dl class="foot-codeone">
                    <dt class="f-l">
                        <img src="${ctx}/images/market/code-one.gif" width="58" height="58" alt="" /></dt>
                    <dd>手机APP应用:逛逛</dd>
                    <dd>轻松一点  轻松管理</dd>
                    <dd><a href="#" title="" target="">
                        <img src="${ctx}/images/market/appstore.gif" width="74" height="20" alt="" style="margin-right: 10px" /></a><a href="#" title="" target=""><img src="${ctx}/images/market/android.gif" width="74" height="20" alt="" /></a></dd>
                </dl>
                <dl class="foot-codeone clear">
                    <dt class="f-l">
                        <img src="${ctx}/images/market/code-two.gif" width="58" height="58" alt="" /></dt>
                    <dd>关注8637品牌超级代理官方维信</dd>
                    <dd>扫描二维码，与我们零距离沟通，更快了解最新的动态</dd>
                    <dd></dd>
                </dl>
            </div>
        </div>
        <p><a href="#" title="" target="">关于8637品牌超级代理</a> | <a href="#" title="" target="">帮助中心</a> | <a href="#" title="" target="">诚聘英才</a> | <a href="#" title="" target="">联系我们</a> | <a href="#" title="" target="">版权说明</a>  <a href="#" title="" target="">浙江天下商邦股份有限公司</a> 客服热线：0574-87217777</p>
        <span>Copyright©2003-2012，版权所有8637.com  增值电信业务经营许可证：浙B2-20130224</span>
    </div>
    <!-- // footer -->
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>
    <script src="${res}/scripts/market/last.js" type="text/javascript"></script>
</body>
</html>
