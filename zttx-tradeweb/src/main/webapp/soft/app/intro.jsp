<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>微逛APP功能介绍-8637品牌超级代理</title>
    <link rel="stylesheet" href="${res}/styles/soft/app/intro.css"/>
</head>
<body>
    <jsp:include page="_header.jsp" />
    <div class="contanier">
        <div class="tabs">
            <div class="item-box">
                <ul class="tab-items clearfix">
                    <li class="active" data-cls="box1">
                        <span class="text android-ico">选择Android版</span>
                        <span class="arrow"></span>
                    </li>
                    <li data-cls="box2">
                        <span class="text iphone-ico">选择iphone版</span>
                        <span class="arrow"></span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="tab-content">
            <!--android功能介绍-->
            <div class="content-box box1">
                <h3>手机微逛Android（安卓）版</h3>
                <a href="${res}/upload/soft/app/weiguang_V1.0.2_8637_release_201410231113.apk">
                    <div class="download">立即下载</div>
                    <div class="version">1.0.2版本</div>
                </a>
                <div class="intro">
                    <ul class="clearfix">
                        <li class="odd">
                            <div class="detail active" data-src="${res}/images/soft/app/tx.jpg">
                                <div class="content clearfix">
                                    <div class="ico1 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>探寻</h3>
                                        <p>寻找好友，寻找微逛聊，一起开心聊天</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="${res}/images/soft/app/gz.jpg">
                                <div class="content clearfix">
                                    <div class="ico2 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>关注</h3>
                                        <p>关注实体店铺，或对消费过的店铺关注，成为会员，享受优惠</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="odd">
                            <div class="detail" data-src="${res}/images/soft/app/lt.jpg">
                                <div class="content clearfix">
                                    <div class="ico3 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>聊天</h3>
                                        <p>时时在线沟通，消息云存储，并可对未安装微逛的用户也可以实现聊天</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="${res}/images/soft/app/dt.jpg">
                                <div class="content clearfix">
                                    <div class="ico4 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>动态</h3>
                                        <p>轻松获取本地商户最新的促销动态</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="odd">
                            <div class="detail" data-src="${res}/images/soft/app/wd.jpg">
                                <div class="content clearfix">
                                    <div class="ico5 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>微店</h3>
                                        <p>终端店铺可以申请开通自己的微店，实现O2O购物</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="${res}/images/soft/app/hy.jpg">
                                <div class="content clearfix">
                                    <div class="ico6 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>好友</h3>
                                        <p>好友更密切，沟通畅快</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="android phone">
                        <img src="${res}/images/soft/app/a-hy.jpg" alt=""/>
                    </div>
                </div>
            </div>
            <!--iphone功能介绍-->
            <div class="content-box box2 hide">
                <h3>手机微逛iphone（苹果）版</h3>
                <a href="javascript:void(0)">
                    <div class="download">专业版下载</div>
                    <div class="version">app store发布中...</div>
                </a>
                <div class="intro">
                    <ul class="clearfix">
                        <li class="odd">
                            <div class="detail active" data-src="${res}/images/soft/app/tx.jpg">
                                <div class="content clearfix">
                                    <div class="ico1 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>探寻</h3>
                                        <p>寻找好友，寻找微逛聊，一起开心聊天</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="${res}/images/soft/app/gz.jpg">
                                <div class="content clearfix">
                                    <div class="ico2 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>关注</h3>
                                        <p>关注实体店铺，或对消费者过的店铺关注，成为会员，享受优惠</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="odd">
                            <div class="detail" data-src="${res}/images/soft/app/lt.jpg">
                                <div class="content clearfix">
                                    <div class="ico3 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>聊天</h3>
                                        <p>时时在线沟通，消息云存储，并可对未安装微逛的用户也可以实现聊天</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="${res}/images/soft/app/dt.jpg">
                                <div class="content clearfix">
                                    <div class="ico4 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>动态</h3>
                                        <p>轻松获取本地商户最新的促销动态</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="odd">
                            <div class="detail" data-src="${res}/images/soft/app/wd.jpg">
                                <div class="content clearfix">
                                    <div class="ico5 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>微店</h3>
                                        <p>终端店铺可以申请开通自己的微店，实现O2O购物</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="even">
                            <div class="detail" data-src="/${res}images/soft/app/hy.jpg">
                                <div class="content clearfix">
                                    <div class="ico6 ico fl"></div>
                                    <div class="descr fr">
                                        <h3>好友</h3>
                                        <p>好友更密切，沟通畅快</p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="iphone phone">
                        <img src="${res}/images/soft/app/tx.jpg" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/common/component/footer.jsp" />
    <a class="ui-scrolltop" title="返回顶部">返回顶部</a>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/soft/app/intro.js"></script>
    <script src="${res}/scripts/soft/app/common.js"></script>
</body>
</html>
