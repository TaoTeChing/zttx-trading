<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>8637工厂店项目介绍 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,招商加盟，天下商邦"/>
    <meta name="description" content="“8637品牌超级代理”以B2B2C（从品牌商到终端店铺再到消费者）的模式构建零中间商的招商加盟直供平台。利用互联网技术服务品牌商及终端商，改变传统品牌代理供应链（专卖店、批发商、代理商、品牌商），通过互联网直连方式招商加盟，减少中间供应环节，打造从厂家到店铺的最短供应链。"/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="v2_index.css" rel="stylesheet"/>
    <link href="v2_factory_info.css" rel="stylesheet">
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="v2_index.js"></script>
</head>
<body>
<div class="container">
    <div id="site-nav">
        <div class="site-nav-bd clearfix">
            <ul class="site-nav-bd-l fl">
                <li class="top-menu collect">
                    <div class="menu-hd">
                        <a href="javascript:void(0);" onclick="AddFavorite(document.title,location.href)">收藏本站</a>
                    </div>
                </li>
                <li class="top-line"></li>
                <c:choose>
                    <c:when test="${not empty brandUserm}">
                        <li class="top-menu">
                            <div class="menu-hd">您好！
                                <a class="high-light" href="http://www${zttx}/brand/center">${brandUserm.comName}</a>，
                                <a href="javascript:document.getElementById('logout-form').submit();" class="c_blue">退出登录</a>
                                <form:form id="logout-form" action="http://www${zttx}/common/logout?redirect=false" method="post" cssStyle="display: none;"></form:form>
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${not empty dealerUserm}">
                        <li class="top-menu">
                            <div class="menu-hd">您好！
                                <a class="high-light" href="http://www${zttx}/dealer/center">${dealerUserm.userName}</a>，
                                <a href="javascript:document.getElementById('logout-form').submit();" class="c_blue">退出登录</a>
                                <form:form id="logout-form" action="http://www${zttx}/common/logout?redirect=false" method="post" cssStyle="display: none;"></form:form>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <%-- <li class="top-menu">
                             <div class="menu-hd">您好！欢迎光临8637品牌超级代理</div>
                         </li>--%>
                        <li class="top-menu">
                            <div class="menu-hd">
                                <a class="high-light" href="http://www${zttx}/common/login">请登录</a>
                                <a class="high-light" href="http://www${zttx}/common/register">加盟入驻</a>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="site-nav-bd-r fr">
                <li class="top-menu">
                    <div class="menu-hd">
                        <a href="http://www${zttx}">8637品牌超级代理首页</a>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu order">
                    <div class="menu-hd">
                        <c:choose>
                            <c:when test="${not empty brandUserm}">
                                <a href="http://www${zttx}/brand/order/purorder">我的订单</a>
                            </c:when>
                            <c:when test="${not empty dealerUserm}">
                                <a href="http://www${zttx}/dealer/myorder">我的订单</a>
                            </c:when>
                            <c:otherwise>
                                <a href="http://www${zttx}/common/login">我的订单</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
                <li class="top-line"></li>
                <c:if test="${not empty dealerUserm}">
                    <li class="top-menu order">
                        <div class="menu-hd">
                            <a href="http://www${zttx}/dealer/shopper">我的进货单<span class="high-light">（${shopCount}）</span></a>
                        </div>
                    </li>
                    <li class="top-line"></li>
                </c:if>
                <li class="top-menu favorites">
                    <div class="menu-hd">
                        <span>我的收藏夹</span>
                    </div>
                    <div class="menu-bd" style="width: 85px;">
                        <div class="menu-spacer"></div>
                        <ul>
                            <c:choose>
                                <c:when test="${not empty brandUserm}">
                                    <a target="_blank" href="http://www${zttx}/brand/dealer/favorite">我收藏的终端商</a>
                                </c:when>
                                <c:when test="${not empty dealerUserm}">
                                    <li><a target="_blank" href="http://www${zttx}/dealer/favorite">我收藏的产品</a></li>
                                    <li><a target="_blank" href="http://www${zttx}/dealer/copartner/favorite">我收藏的品牌</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="http://www${zttx}/common/login">我收藏的产品</a></li>
                                    <li><a href="http://www${zttx}/common/login">我收藏的品牌</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down download">
                    <div class="menu-hd">
                        <span>产品下载</span>
                    </div>
                    <div class="menu-bd" style="width: 97px; display: none; ">
                        <ul>
                            <li><a href="http://www${zttx}/soft/erp/" target="_blank">智慧门店管理系统</a></li>
                            <li><a href="http://app${zttx}" target="_blank">约逛-手机APP</a></li>
                            <li><a href="http://www${zttx}/soft/drp/" target="_blank">智慧品牌管理系统</a></li>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down service">
                    <div class="menu-hd">
                        <span>客户服务</span>
                    </div>
                    <div class="menu-bd">
                        <ul>
                            <li><a target="_blank" href="http://www${zttx}/help/index">帮助中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/rules/">规则中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/about/contactus.jsp">联系我们</a></li>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu down website">
                    <div class="menu-hd">
                        <span>网站导航</span>
                    </div>
                    <div class="menu-bd" style="width: 90px;">
                        <ul>
                            <li><a target="_blank" href="http://www${zttx}/about">关于我们</a></li>
                            <li><a target="_blank" href="http://www${zttx}/info">新闻资讯</a></li>
                            <%-- <li><a target="_blank" href="http://www${zttx}/jsp/common/map.jsp">站点地图</a></li> --%>
                            <li><a target="_blank" href="${ctx}/common/dealer/explosionList">爆款产品</a></li>
                            <li><a target="_blank" href="http://www${zttx}/brand/center">品牌商管理中心</a></li>
                            <li><a target="_blank" href="http://www${zttx}/dealer/center">终端商管理中心</a></li>
                        </ul>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu">
                    <div class="menu-hd">
                        <a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&amp;key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw" target="_blank">加盟入驻咨询</a>
                    </div>
                </li>
                <li class="top-line"></li>
                <li class="top-menu">
                    <div class="menu-hd">
                        <a href="http://www.8637.com/about/superiority.jsp" target="_blank">平台优势介绍</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <%--<jsp:include page="/WEB-INF/view/common/component/header.jsp"/>--%>
    <div class="header-round">
        <div class="header clearfix" style="z-index: 216;">
            <div class="logo">
                <a href="/">
                    <h1>8637品牌超级代理</h1>
                </a>
            </div>
            <div class="fr clearfix" style="margin-top: 28px;">
                <div class="search fl clearfix">
                    <form action="" method="post">
                        <div class="form fl">
                            <input type="text" id="searchTxt" value="搜索您需要的商品" />
                            <span><a href="">短袖T恤</a><a href="">裙子</a><a href="">灯笼裤</a></span>
                        </div>
                        <a id="searchBtn" class="fl" href="javascript:;"><i class="icon-search"></i></a>
                    </form>
                </div>
                <div class="cart fl" id="cart">
                    <div class="cart-hd">
                        <i class="icon-cart"></i>
                        <%--<i class="more1">&gt;</i>--%>
                        <i id="cartCount">3</i>
                        <a href="javascript:;">我的购物车</a>
                    </div>
                    <div class="cart-drop" id="cartDrop">
                        <div class="d1">商品列表</div>
                        <div class="d2">
                            <ul>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                                <li class="clearfix">
                                    <div class="d2-img">
                                        <img src="" alt="" width="50" height="50"/>
                                    </div>
                                    <div class="d2-name">
                                        <a href="#" title="星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品">星达Sky-Watcher 130EQ反射高倍高清天文望远镜 深空神器 超大口径 活动款无赠品</a>
                                    </div>
                                    <div class="d2-detail">
                                        <span>￥12.00</span><br />
                                        <a href="javascript:;" class="delete">删除</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="d3 clearfix">
                            <span class="fl ta-r" style="word-break: break-all;">共 <strong>9</strong> 件商品 共计 <strong>￥110.00</strong> 元</span>
                            <a href="#" class="fr">去购物车</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- header-round end --%>
    <%--<jsp:include page="/WEB-INF/view/common/component/nav.jsp">
        <jsp:param value="1" name="m"/>
    </jsp:include>--%>
    <div class="f-nav">
        <div class="m-nav clearfix">
            <ul class="inline-float fl">
                <li><a href="" class="current">8637工厂店项目介绍</a></li>
                <li><a href="">加盟8637工厂店</a></li>
                <li><a href="">供货8637工厂店</a></li>
            </ul>
            <div class="fr slogan"></div>
        </div>
    </div>
    <%-- nav end --%>
    <div class="main">
        <div class="f-banner">
            <div class="m-banner">
                <div class="s-b1 m-l"></div>
                <div class="s-b2 m-r"></div>
                <div class="s-b3 fadeZoomIn">
                    <h3>已有<span>19</span>家直营和加盟店</h3>
                    <p>广东  浙江  福建  上海  江西  湖南  四川等地已有工厂店身影.</p>
                    <p>8637品牌工厂店一区一镇一店加盟模式，欢迎您的咨询</p>
                    <a href="#">提交申请</a>
                </div>
                <div class="s-b4"></div>
            </div>
        </div>
        <div class="f-main">
            <div class="m-main">
                <div class="m-intro clearfix">
                    <div class="fl">
                        <img src="images/factory_info/f_intro/f_i1.jpg" alt=""/>
                    </div>
                    <div class="fr">
                        <img src="images/factory_info/f_intro/f_i2.jpg" alt="" style="margin-bottom: 36px;"/>
                        <p>让品牌商最低成本快速搭建渠道。每一个进驻到8637平台及8637品牌工厂店的品牌商，都能够直接覆盖8637平台所有拥有的百万家终端门店，而8637品牌工厂店几乎能够适应任何的市场区域，那么品牌商就能够通过8637品牌工厂店在中国市场的持续渗透而最快速度的拓展渠道到全国市场。</p>
                        <p>让品牌商直控终端，快速推陈出新。8637品牌工厂店系统化的全国拓展宣传、有效的引流推广，让每一个品牌商能以最低的市场宣传费用、最短时间占领市场，第一时间将新品投放市场。</p>
                        <p>让品牌商及时收款，没有坏账。8637品牌工厂店通过创新的O2O运作模式，科技系统的管理方式极大的降低了品牌营运及管理成本，相比于传统渠道，品牌商实时掌控终端，同时资金又可得到有效保障。</p>
                        <img src="images/factory_info/f_intro/f_i3.jpg" alt="" style="float: right;margin-top: -36px;"/>
                    </div>
                </div>
                <div class="m-incom">
                    <div class="title">
                        <div class="line"></div>
                        <div class="tit1"></div>
                    </div>
                    <div class="fore1">
                        <div class="fore1_inner clearfix">
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j1.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-1"></i>8637在线交易平台</dd>
                                <dd class="des">汇聚数万品牌、百万终端门店的交易平台。加盟商从入驻的品牌中，选择中意的品牌、优势的产品，直接对接、直接选货、直接付款，品牌直接发货进入工厂店，一手货源一手价格，最低成本门店经营</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j2.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-2"></i>智慧门店管理系统</dd>
                                <dd class="des">卓越的店铺大管家，会员定向精准营销、进销存管理、店员管理、远程巡店管理，统统搞定，客流分析数据实时统计顾客转化成交率、营业毛利润，随时掌握一手信息，把控销售状况。</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j3.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-3"></i>“约逛”APP产品</dd>
                                <dd class="des">O2O购物平台，消费者足不出户就逛店铺，在线下单、预约购物随性选。帮助商户订单管理、客户管理、库存管理，对接智慧门店系统随时进行门店的远程巡视与管理，更能打造微商圈平台，大幅提升门店业绩。</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j4.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-4"></i>8637比价神器</dd>
                                <dd class="des">商品网购比价系统。利用互联网工具，实时检索主流网购平台各类商品的公开销售数据，跟踪比对网购平台的价格，可每天更新店内商品售价，确保所销售的商品价格能够PK网购。</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j5.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-5"></i>标准化装修方案</dd>
                                <dd class="des">标准化陈列规划、标准化道具规划、标准化功能区域规划标准化店头形象设计、店堂收银区形象设计、店堂活动区域形象设计、店堂装修风格设计；员工标准服装</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j6.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-6"></i>企宣引流指导</dd>
                                <dd class="des">店堂POP标准化方案规划；线上宣传引流方案规划；线下宣传引流方案。</dd>
                            </dl>
                            <dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j7.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-7"></i>店堂管理手册</dd>
                                <dd class="des">员工管理、店铺精细化管理方案、货品理货培训等。</dd>
                            </dl><dl>
                                <dt class="pic"><a href=""><img src="images/factory_info/f_intro/f_j8.jpg" alt=""/></a></dt>
                                <dd class="tit"><i class="f-num-8"></i>技术与解决方案培训</dd>
                                <dd class="des">软硬件使用培训；技术产品和解决方案应用培训。</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="m-incom">
                    <div class="title">
                        <div class="line"></div>
                        <div class="tit2"></div>
                    </div>
                    <div class="fore2">
                        <div class="fore2_inner">
                            <ul class="inline-float">
                                <li><i class="num_1"></i><p>合适区域，合适位置，合适面积，租金较低、交通便利。原则上一般市场规划至县一级，发达区域到镇一级。要求县镇级门店面积在300 ㎡-500㎡，地级市以上城市</p></li>
                                <li><i class="num_2"></i><p>加盟商须具备一定的实力。300㎡-500㎡的工厂店投入需在50万-80万。</p></li>
                                <li><i class="num_3"></i><p>高度认可8637品牌工厂店模式，能够按照8637品牌工厂店的各项规定来执行。</p></li>
                                <li><i class="num_4"></i><p>8637品牌工厂店将为每一家加盟店提供丰富且全面的系统支持，尤为包括众多的硬件支持与软件服务，同时按照加盟店的面积大小，第一年收取一定的加盟费，第二</p></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="m-incom" style="margin-top: 50px;">
                    <div class="title">
                        <div class="line"></div>
                        <div class="tit3"></div>
                    </div>
                    <div class="fore3" style="margin-top: 40px;">
                        <img src="images/factory_info/f_intro/f_p.jpg" alt=""/>
                    </div>
                </div>
                <div class="m-incom" style="margin-top: 50px;">
                    <div class="title">
                        <div class="line"></div>
                        <div class="tit4"></div>
                    </div>
                    <div class="fore4">
                        <div class="fore4_inner">
                            <ul class="inline-float">
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s1.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>上海店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>650㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>上海市嘉定区嘉唐公路838</dd>
                                            <dt>经济区域：</dt>
                                            <dd>较成熟的商圈</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中端消费人群</dd>
                                            <dt>组货方式：</dt>
                                            <dd>多品类多品牌组合式集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s2.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>宁波店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>1500㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>宁波市长阳东路106号</dd>
                                            <dt>经济区域：</dt>
                                            <dd>住宅区域、多家工厂</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中低端消费人群</dd>
                                            <dt>组货方式：</dt>
                                            <dd>多品类多品牌集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s3.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>舟山店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>230㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>舟山市普陀区六横镇蛟头坦岙润都置业3号楼</dd>
                                            <dt>经济区域：</dt>
                                            <dd>成熟购物街道、住宅区域</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中低端消费人群</dd>
                                            <dt>组货方式：</dt>
                                            <dd>多品类多品牌集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s4.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>温岭店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>960㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>温岭市万寿路166号</dd>
                                            <dt>经济区域：</dt>
                                            <dd>较成熟的商圈</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中高端消费群体</dd>
                                            <dt>组货方式：</dt>
                                            <dd>多品类多品牌集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s5.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>海门店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>650㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>海门市秀山东路869号</dd>
                                            <dt>经济区域：</dt>
                                            <dd>住宅区域，较成熟的商圈</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中高端消费群体</dd>
                                            <dt>组货方式：</dt>
                                            <dd>多品类多品牌集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="pic">
                                        <a href="#"><img src="images/factory_info/f_intro/f_s6.jpg" alt=""/></a>
                                    </div>
                                    <h4 class="tit">8637品牌工厂店-<em>昆明店</em></h4>
                                    <div class="des">
                                        <dl class="clearfix">
                                            <dt>店铺面积：</dt>
                                            <dd>170㎡</dd>
                                            <dt>联系地址：</dt>
                                            <dd>昆明市西山区船房村1154号</dd>
                                            <dt>经济区域：</dt>
                                            <dd>住宅区域</dd>
                                            <dt>目标人群：</dt>
                                            <dd>中高端消费群体</dd>
                                            <dt>组货方式：</dt>
                                            <dd>大装品类多品牌集成店</dd>
                                        </dl>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="ts-container clearfix">
            <div class="footer-coll fl">
                <img src="images/f-logo.gif" alt=""/>
            </div>
            <div class="footer-colr fl">
                <div class="clearfix footer-sev">
                    <ul class="footer-severs inline-float fl">
                        <li>
                            <dl>
                                <dt>8637保障</dt>
                                <dd><a href="http://www${zttx}/rules/" target="_blank">品牌授权</a></dd>
                                <dd><a href="http://www${zttx}/rules/" target="_blank">规则中心</a></dd>
                                <dd><a href="http://www.8637.com/common/rules/info/2FB444A80857476890C42CFB7A7E6336" target="_blank">退换货保障</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>新手上路</dt>
                                <dd><a href="http://www${zttx}/common/register" target="_blank">注册会员</a></dd>
                                <dd><a href="http://www${zttx}/help/index" target="_blank">常见问题</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>品牌商专区</dt>
                                <dd><a href="http://www${zttx}/apply/brandsSettled.jsp" target="_blank">申请入驻</a></dd>
                                <dd><a href="http://www${zttx}/brand/center" target="_blank">品牌管理</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>终端商专区</dt>
                                <dd><a href="http://www${zttx}/apply/brandsSettled.jsp" target="_blank">申请入驻</a></dd>
                                <dd><a href="http://www${zttx}/dealer/center" target="_blank">加盟管理</a></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>特色服务</dt>
                                <dd><a href="http://app${zttx}" target="_blank">约逛-手机App</a></dd>
                                <dd><a href="http://www${zttx}/soft/erp/" target="_blank">智慧门店管理系统</a></dd>
                            </dl>
                        </li>
                    </ul>
                    <div class="fl footer-qrcode">
                        <h3>关注8637动态</h3>
                        <img src="images/f-qrcode.gif" alt=""/>
                    </div>
                </div>
                <div class="footer-contact clearfix">
                    <p>
                        <span>全国免费招商热线：400-111-8637</span>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/" target="_blank">关于8637品牌超级代理</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/help/index" target="_blank">帮助中心</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/joinus.jsp" target="_blank">诚聘英才</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/about/contactus.jsp" target="_blank">联系我们</a>
                        <span class="line"></span>
                        <a href="http://www${zttx}/common/rules/list?cateId=7E02825C4B644058BB64E0ABDE23767B&cateType=1" target="_blank">版权说明</a>
                    </p>
                </div>
                <div class="footer-copyright">
                    Copyright&copy;2013-<script language="JavaScript" type="text/javascript">document.write(new Date().getFullYear());</script>，8637品牌超级代理版权所有 浙江天下商邦科技股份有限公司 客服热线：0574-87217777 浙ICP备14007458号-1
                </div>
            </div>
        </div>
    </div>
    <!--[if IE 6]>
    <div class="sorry-ie">
        <div class="alert">
            <a href="javascript:;" class="fr close-alert" style="margin-right: 20px;" >关闭</a>
            亲，您的浏览器版本过低导致图片打开速度过慢，提升打开速度您可以：<a href="http://windows.microsoft.com/zh-cn/windows/upgrade-your-browser" target="_blank">升级浏览器</a> 或者 点击下载 <a href="http://chrome.360.cn/" target="_blank">chrome浏览器</a>
        </div>
    </div>
    <![endif]-->
    <%-- footer end --%>
    <%--<jsp:include page="/WEB-INF/view/common/component/bottom.jsp"></jsp:include>--%>
    <div class="footer-bottom">
        <div class="ts-container clearfix">
            <div class="fl" style="width: 195px;">&nbsp;</div>
            <div class="fl" style="width: 920px;">
                <a target="_blank" href="http://idinfo.zjaic.gov.cn/bscx.do?method=hddoc&amp;id=33020000007109"><img src="/images/common/i_lo2.gif" border="0" height="45"></a>
                <%--<a key ="559b46c6efbfb017b0dcc81c" logo_size="124x47" logo_type="realname" href="http://www.anquan.org"><script src="http://static.anquan.org/static/outer/js/aq_auth.js"></script></a>--%>
            </div>
        </div>
    </div>
    <%-- footer-bottom end --%>
</div>
<script src="${res}/scripts/common/base-init.js"></script>
<script>

</script>
</body>
</html>