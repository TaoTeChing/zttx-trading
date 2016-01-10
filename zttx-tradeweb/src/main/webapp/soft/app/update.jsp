<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>微逛功能介绍-8637品牌超级代理</title>
    <link rel="stylesheet" href="${res}/styles/soft/app/update.css"/>
</head>
<body>
<jsp:include page="_header.jsp" />
<div class="contanier">
    <div class="tabs">
        <div class="item-box">
            <ul class="tab-items clearfix">
                <li class="active" data-cls="box1">
                    <span class="text android-ico">iphone更新日志</span>
                </li>
                <li data-cls="box2">
                    <span class="text iphone-ico">Android更新日志</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="tab-content">
        <!--iphone日志更新-->
        <div class="content-box box1">
            <h3 class="title">开发日志(苹果ios)</h3>
            <div class="content">
                <div class="block">
                    <div class="year">2014年</div>
                    <div class="year-line"></div>
                    <div class="log-content">
                        <div class="logs clearfix new">
                            <div class="date fl">
                                <div class="dd">06.4</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛ios开发版本1.0</dt>
                                <dd>1.完成好友信息界面！</dd>
                                <dd>2.完成我的信息管理功能</dd>
                                <dd>3.在线升能功能</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">05.25</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛ios开发版本1.0</dt>
                                <dd>1.完成数据架构</dd>
                                <dd>2.实现用户注册登录</dd>
                                <dd>3.实现客户应用插件管理</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">05.05</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛ios UI设计</dt>
                                <dd>1.完成微逛UI的全部设计工作！</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">04.02</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛ios 原型及结构设计</dt>
                                <dd>1.完成微逛的用户需求及原型稿设计！</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <!--                <div class="block">
                                    <div class="year">2013年</div>
                                    <div class="year-line"></div>
                                    <div class="log-content hide">
                                        <div class="logs clearfix">
                                            <div class="date fl"></div>
                                            <dl class="fl">
                                                <dt>无日志更新</dt>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                                <div class="block last">
                                    <div class="year">2012年</div>
                                    <div class="year-line"></div>
                                    <div class="log-content hide">
                                        <div class="logs clearfix">
                                            <div class="date fl"></div>
                                            <dl class="fl">
                                                <dt>无日志更新</dt>
                                            </dl>
                                        </div>
                                    </div>
                                </div>-->
            </div>
        </div>

        <!--android日志更新-->
        <div class="content-box box2 hide">
            <h3 class="title">更新日志(Android)</h3>
            <div class="content">
                <div class="block">
                    <div class="year">2014年</div>
                    <div class="year-line"></div>
                    <div class="log-content">
                        <div class="logs clearfix new">
                            <div class="date fl">
                                <div class="dd">10.23</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android 版本1.0.2</dt>
                                <dd>1.店铺助手 （卖家手机app管理微店）</dd>
                                <dd>2.erp微店管理(erp 端通过pc开通管理微店)</dd>
                                <dd>3.手机查看erp门店报表 （销售、库存、客流）</dd>
                                <dd>4.手机扫码关注店铺</dd>
                                <dd>5.店铺雷达 周边店铺分类查找</dd>
                                <dd>6.品牌推荐 为代理商、员工提供 授权手机查看许可区域内品牌推荐信息</dd>
                            </dl>
                        </div>

                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">09.15</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android 公测版本1.0.1</dt>
                                <dd>1.商户功能：店内远程视频监控模块：摄像头预设位置，分辨率切换，手指滑动功能！</dd>
                                <dd>2.商户功能：调整远程视频监控竖版界面布局</dd>
                                <dd>3.更新多处BUG：让微逛聊天更顺畅，手机匹配好友，让圈子更有味道</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">06.5</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android 开发版本1.0</dt>
                                <dd>1.完成好友信息界面！</dd>
                                <dd>2.完成我的信息管理功能</dd>
                                <dd>3.在线升能功能</dd>
                                <dd>4.完成数据接口</dd>
                                <dd>5.完成通讯协议</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">05.25</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android开发版本1.0</dt>
                                <dd>1.完成数据架构</dd>
                                <dd>2.实现用户注册登录</dd>
                                <dd>3.实现客户应用插件管理</dd>
                                <dd>4.完成店铺关注列表功能</dd>
                                <dd>5.完成个人信息管理功能</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">05.05</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android UI设计</dt>
                                <dd>1.完成微逛UI的全部设计工作！</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">04.02</div>
                                <div class="yy">2014</div>
                            </div>
                            <dl class="fl">
                                <dt>微逛Android 原型及结构设计</dt>
                                <dd>1.完成微逛的用户需求及原型稿设计！</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <!--                <div class="block">
                                    <div class="year">2013年</div>
                                    <div class="year-line"></div>
                                    <div class="log-content hide">
                                        <div class="logs clearfix">
                                            <div class="date fl"></div>
                                            <dl class="fl">
                                                <dt>无日志更新</dt>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                                <div class="block last">
                                    <div class="year">2012年</div>
                                    <div class="year-line"></div>
                                    <div class="log-content hide">
                                        <div class="logs clearfix">
                                            <div class="date fl"></div>
                                            <dl class="fl">
                                                <dt>无日志更新</dt>
                                            </dl>
                                        </div>
                                    </div>
                                </div>-->
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp" />
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/soft/app/update.js"></script>
<script src="${res}/scripts/soft/app/common.js"></script>
</body>
</html>
