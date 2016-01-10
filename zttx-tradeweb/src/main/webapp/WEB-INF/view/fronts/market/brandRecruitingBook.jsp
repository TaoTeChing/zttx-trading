<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />
    <link href="${res}/styles/market/brandviewbase_edit.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/market/decoration_edit.css" rel="stylesheet" type="text/css" />
  	<link href="${res}/styles/market/btnico.css" rel="stylesheet" type="text/css" />
 	<link href="${res}/styles/market/stylemanage.css" rel="stylesheet" type="text/css" />
    <link href="${res}/scripts/plugin/ueditor_mini1_0_0-utf8-jsp/themes/default/css/umeditor.min.css" rel="stylesheet" />
    <!--百度UM编辑器样式-->
    <style>
        <!--
        /*编辑模式下遮挡层*/
        .lig { position: fixed; top: 0; left: 0; z-index: 300; width: 100%; height: 100%; display: none; /*background:#000; filter:alpha(opacity=70); opacity:0.7;*/ }

        li.first #up { cursor: default; background-image: none; opacity: 0.65; filter: alpha(opacity=65); -webkit-box-shadow: none; -moz-box-shadow: none; box-shadow: none;; }
        li.last #down { cursor: default; background-image: none; opacity: 0.65; filter: alpha(opacity=65); -webkit-box-shadow: none; -moz-box-shadow: none; box-shadow: none;; }
        -->
    </style>
</head>
<body id="allbody">
    <div class="top">
        <div class="top-center">
            <img src="${ctx }/images/market/brandmarket/top.png" /></div>
    </div>
    <!--// top end-->
    <div class="header-nav" id="header-nav">
        <div class="header-navcen">
            <div class="header-cen" style="height: 120px" id="headerhover">
                <div class="navbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="edit btn btn-small btn-inverse"><i class="icon-pencil icon-white"></i>编辑</span><span class="delete btn btn-small btn-inverse"><i class="icon-trash icon-white"></i>删除</span><span class="add btn btn-small btn-inverse">添加模块</span></div>
                </div>
                <style>
                    /*.header-cen{ background:url(${ctx }/images/market/brandmarket/temp/banar.jpg) no-repeat}*/
                </style>
                <!--<img src="${ctx }/images/market/brandmarket/temp/banar.jpg" width="1200" height="120px" />-->
                <div class="logo" style="height: 55px">s1015847</div>
            </div>
            <div class="nav-cen" style="height: 30px" id="headerhover1">
                <div class="navbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="navedit btn btn-small btn-inverse"><i class="icon-pencil icon-white"></i>编辑</span><span class="navdelete btn btn-small btn-inverse"><i class="icon-trash icon-white"></i>删除</span><span class="navadd btn btn-small btn-inverse">添加模块</span></div>
                </div>
                <ul class="menu-list">
                    <li class="menu"><a class="menu-link" href="/" title="" target=""><span class="title">品牌首页</span></a></li>
                    <li class="menu"><a class="menu-link selected" href="/recruit" title="" target=""><span class="title">品牌招募书</span></a></li>
                    <li class="menu"><a class="menu-link" href="/visual" title="" target=""><span class="title">陈列视觉</span></a></li>
                    <li class="menu"><a class="menu-link" href="/product" title="" target=""><span class="title">产品展示</span></a></li>
                    <li class="menu"><a class="menu-link" href="/company" title="" target=""><span class="title">企业展示</span></a></li>
                    <li class="menu"><a class="menu-link" href="/network" title="" target=""><span class="title">门店展示</span></a></li>
                    <li class="menu"><a class="menu-link" href="/news" title="" target=""><span class="title">品牌新闻</span></a></li>
                    <li class="menu"><a class="menu-link" href="/document" title="" target=""><span class="title">资料下载</span></a></li>
                    <li class="menu menulast"><a class="menu-link" href="#" title="" target=""><span class="title">留言互动</span></a></li>
                    <span class="address font f-r">店铺地址：http://duocare.8637.com 复制</span>
                </ul>
            </div>
            <div class="biaochi"></div>
        </div>
    </div>
    <!--// header end-->
    <div class="listbody clear">
        <div class="sidebar-l">
            <div id="k-movecontent">
                <div class="conbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="btn btn-small btn-inverse" id="">添加模块</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
                </div>
                <h3 class="title" id="re-title">自定义模块</h3>
                <div class="content" id="diycon">
                    自定义模块
                </div>
            </div>
            <div id="k-movecontent">
                <div class="conbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="btn btn-small btn-inverse" id="">添加模块</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
                </div>
                <h3 class="title">品牌介绍</h3>
                <div class="content">
                    <div class="imgbox">
                        <img src="${ctx }/images/market/brandmarket/temp/content.jpg" width="845" /></div>
                    <h2 class="x-bt">品牌定位</h2>
                    <p class="sidebar-p">朵彩售点遍布全国一二线城市主流商场，坚持高品质、高品位的产品、品牌路线。不断创新新产品品质、提升品牌附加值。朵彩以其“内衣天使”、“绿色天然”的中高品牌定位，成为中国内衣中高端的代表</p>
                    <h2 class="x-bt">品牌定位</h2>
                    <p class="sidebar-p">朵彩售点遍布全国一二线城市主流商场，坚持高品质、高品位的产品、品牌路线。不断创新新产品品质、提升品牌附加值。朵彩以其"内衣天使"、"绿色天然"的中高品牌定位，成为中国内衣中高端的代表</p>
                    <h2 class="x-bt">品牌定位</h2>
                    <p class="sidebar-p">朵彩售点遍布全国一二线城市主流商场，坚持高品质、高品位的产品、品牌路线。不断创新新产品品质、提升品牌附加值。朵彩以其"内衣天使"、"绿色天然"的中高品牌定位，成为中国内衣中高端的代表</p>
                    <h2 class="x-bt">品牌定位</h2>
                    <p class="sidebar-p">朵彩售点遍布全国一二线城市主流商场，坚持高品质、高品位的产品、品牌路线。不断创新新产品品质、提升品牌附加值。朵彩以其"内衣天使"、"绿色天然"的中高品牌定位，成为中国内衣中高端的代表</p>
                </div>
            </div>
            <div id="k-movecontent">
                <div class="conbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="btn btn-small btn-inverse" id="">添加模块</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
                </div>
                <h3 class="title">加盟申请</h3>
                <div class="joinapp">
                    <form>
                        <table class="sp f-l">
                            <tbody>
                                <tr>
                                    <td>申请人：</td>
                                    <td>
                                        <input type="text" name="" class="spname" /></td>
                                </tr>
                                <tr>
                                    <td>我的留言：</td>
                                    <td>
                                        <textarea name="" cols="" rows="" class="message"></textarea></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="2">
                                        <input type="button" value="提交申请资料" class="button" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <div class="another f-l">
                        <p>需登录账户才能申请加盟</p>
                        <div>
                            <a class="btnn f-l" href="#" title="" target="">注册账号</a>
                            <p><a href="#" title="" target="">成为VIP会员</a></p>
                            <p><a href="#" title="" target="">VIP会员有哪些服务</a></p>
                        </div>
                        <p class="kefu">客户服务热线：990102131231</p>
                    </div>
                </div>
            </div>
            <div id="k-movecontent">
                <div class="conbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="btn btn-small btn-inverse" id="">添加模块</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
                </div>
                <h3 class="title clear">产品展示</h3>
                <div class="productshow">
                    <div id="carousel" class="es-carousel-wrapper">
                        <div class="es-carousel">
                            <ul class="productshowul mainlist piclist">
                                <li><a href="#" title="" target="">
                                    <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                    <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                     <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                     <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                	 <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                	 <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                	 <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                                <li><a href="#" title="" target="">
                                	 <img src="${ctx }/images/market/brandmarket/temp/productshow.jpg" width="230" height="259" /></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="k-movecontent lastk-m" class="lastk-m">
                <div class="btn" id="addmodlebtn">添加模块</div>
                <span style="font-family: Verdana; margin: 0 0 0 10px; color: #ccc">(请选择需要添加的模块)</span></div>
        </div>
        <!--// sidebar-l -->
        <div class="sidebar-r">
            <div class="attention">
                <p class="font fontcolor3"><span class="guanzhudu"></span>关注度：124</p>
                <p class="font fontcolor3"><span class="yiguanzhu"></span>已关注</p>
            </div>
            <div class="hidelogobox">
                <div class="imgbox"><a href="#" title="" target="">
                    <img src="${ctx }/images/market/brandmarket/temp/hidelogo.jpg" /></a></div>
                <p class="title"><a href="#" title="" target="">朵彩/DOCARE</a></p>
                <span class="font fontcolor9">中国彩棉服饰领导品牌</span>
                <div class="btnn"></div>
            </div>
            <div class="suppbra font">
                <h3 class="title">品牌供应商:</h3>
                <div class="content">
                    <p class="companyname"><a href="#" title="" target="">上海朵彩服饰有限公司</a></p>
                    <div class="atte"><a href="#" title="" target="">
                        <img src="${ctx }/images/market/brandmarket/temp/atte.jpg" alt="" width="132" height="44" /></a></div>
                    <p class="telphone">电话：021-63321111</p>
                    <p class="website">官网:<a href="#" title="" target="">www.docare.com</a></p>
                    <p class="site">官网:上海市闵行区新建东路58号弄绿科技园</p>
                    <div class="sitemsg clear">
                        <p class="fontcolor6">注册资金：5000万人民币</p>
                        <p class="fontcolor6">公司规模：1000人</p>
                        <a class="f-r" href="#" title="" target="">查看经销网络</a>
                        <p class="fontcolor6">实体店面：3000家</p>
                    </div>
                    <a class="join" href="#" title="" target=""></a>
                    <a class="brannk" href="#" title="" target="">查看品牌招募书</a>
                    <a class="rule" href="#" title="" target="">查看经销合作规则</a>
                </div>
            </div>
            <div class="comdown">
                <h3 class="title">公司旗下品牌:</h3>
                <div id="focus">
                    <ul class="comdownul fontcolor3 font">
                        <li>
                            <dl>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>

                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱/DOLAI</a></div>
                                </dd>
                                <dd>
                                    <div class="imgbox"><a href="#" title="" target="">
                                        <img src="${ctx }/images/market/brandmarket/temp/comdown2.jpg" width="111" height="46" alt="" /></a></div>
                                    <div class="des"><a href="#" title="" target="">朵莱客/DOCAN</a></div>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="contact clear">
                <h3 class="title">在线沟通:</h3>
                <div class="conbody">
                    <a href="#" title="" target="">
                        <img src="${ctx }/images/market/brandmarket/conc.jpg" /></a>
                </div>
            </div>
        </div>
        <!--// sidebar-r -->
        <div class="ratebar font">
            <div class="guanzhudu">
                <p>关注度</p>
                <p>124</p>
                <img src="${ctx }/images/market/brandmarket/guanzhudu.gif" />
            </div>
            <div class="guanzhuwm">
                <p>关注我们</p>
                <img src="${ctx }/images/market/brandmarket/qrin.gif" />
            </div>
            <div class="online">
                <p>在线资讯</p>
                <p>8:30-17:00</p>
                <img src="${ctx }/images/market/brandmarket/qq.gif" />
            </div>
            <a href="#" title="" target="" class="joinus">
                <img src="${ctx }/images/market/brandmarket/joinus.jpg" /></a>
        </div>
        <div class="hideover"></div>
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
                        <img src="${ctx }/images/market/brandmarket/code-one.gif" width="58" height="58" alt="" /></dt>
                    <dd>手机APP应用:逛逛</dd>
                    <dd>轻松一点  轻松管理</dd>
                    <dd><a href="#" title="" target="">
                        <img src="${ctx }/images/market/brandmarket/appstore.gif" width="74" height="20" alt="" style="margin-right: 10px" /></a><a href="#" title="" target=""><img src="${ctx }/images/market/brandmarket/android.gif" width="74" height="20" alt="" /></a></dd>
                </dl>
                <dl class="foot-codeone clear">
                    <dt class="f-l">
                        <img src="${ctx }/images/market/brandmarket/code-two.gif" width="58" height="58" alt="" /></dt>
                    <dd>关注8637品牌超级代理官方微信</dd>
                    <dd>扫描二维码，与我们零距离沟通，更快了解最新的动态</dd>
                    <dd></dd>
                </dl>
            </div>
        </div>
        <p><a href="#" title="" target="">关于8637品牌超级代理</a> | <a href="#" title="" target="">帮助中心</a> | <a href="#" title="" target="">诚聘英才</a> | <a href="#" title="" target="">联系我们</a> | <a href="#" title="" target="">版权说明</a>  <a href="#" title="" target="">浙江天下商邦股份有限公司</a> 客服热线：0574-87217777</p>
        <span>Copyright©2003-2012，版权所有8637.com  增值电信业务经营许可证：浙B2-20130224</span>
    </div>
    <!-- // footer -->
    <div class="popslidebox">
        <div class="title">店铺招牌<span style="cursor: pointer; position: absolute; right: 10px; *top: 0px">关闭</span></div>
        <div class="content" id="tabs">
            <div class="tab_menu">
                <ul>
                    <li class="selected">默认招牌</li>
                    <li>自定义招牌</li>
                    <li>招牌超市</li>
                </ul>
            </div>
            <div class="tab_box">
                <div>
                    <form>
                        <div class="defaultp"><span>店铺名称：</span> <span>s1015847</span> <a>修改</a> 是否显示店铺名称:
                            <input type="checkbox" class="haderlogo" checked="checked" /></div>
                        <div class="defaultp"><span>&nbsp;&nbsp;高&nbsp;&nbsp;度：</span><input type="text" value="120" class="h-c-height" style="width: 40px">px <span>（建议不超过120px，否则导航显示可能异常）</span></div>
                        <div class="defaultp">
                            <span class="f-l">背景颜色：</span>
                            <p class="pleasec f-l">请选择颜色<span class="colorbox" style="display: block; background: #b01000 url(${ctx }/images/market/brandmarket/img/cxcolor.png); width: 18px; height: 18px"></span></p>
                            <p class="f-l" id="colorpickerHolder" style="display: none; position: absolute; top: 130px; left: 50px"></p>
                        </div>
                        <div class="btn btn-inverse popsure" style="margin: 10px 0 0 60px">确定</div>
                        <div class="btn btn-inverse popcancel" style="margin: 10px 0 0 10px">取消</div>
                    </form>
                </div>
                <div class="hide">
                    <p>TIP：自定义模式会清除前面所有的设置</p>
                    <script type="text/plain" id="headerEditor" style="width: 860px; height: 260px">
					<p style=" font-size:12px">图片高度最好不要超过120px</p>
                    </script>
                    <div class="btn btn-inverse popselfsure" style="margin: 10px 0 0 0">确定</div>
                    <div class="btn btn-inverse popselfcancel" style="margin: 10px 0 0 10px">取消</div>
                </div>
                <div class="hide">
                    <p>招牌超市提供购买招牌业务</p>
                </div>
            </div>
            <a href="#" style="position: absolute; top: 55px; right: 20px"><i class="icon-question-sign"></i>使用帮助</a>
        </div>
    </div>
    <div class="lig"></div>
    <!--点击编辑-->
    <div class="t-model">
        <div class="title">自定义内容区<span style="cursor: pointer; position: absolute; right: 10px; *top: 0px">关闭</span></div>
        <div class="content" id="tabs">
            <div class="tab_menu">
                <ul>
                    <li class="selected">自定义内容</li>
                    <li>自定义其他</li>
                </ul>
            </div>
            <div class="tab_box">
                <div>
                    <form style="margin: 0 0 10px 0">
                        <input type="radio" value="隐藏标题" name="hidetitle" id="hidetitle" />隐藏标题
            		<input type="radio" value="显示标题" name="hidetitle" checked="checked" id="showtitle" />显示标题<input type="text" id="get-title" />
                    </form>
                    <script type="text/plain" id="myEditor" style="width: 860px; height: 240px">
					<p style=" font-size:12px">请添加内容</p>
                    </script>
                    <div class="btn btn-inverse btn-small" id="ensure" style="margin: 20px 0 0 0">确定</div>
                    <div class="btn btn-small" style="margin: 20px 0 0 10px">取消</div>
                </div>
                <div class="hide">2</div>
            </div>
        </div>
        <a href="#" style="position: absolute; top: 55px; right: 20px"><i class="icon-question-sign"></i>使用帮助</a>
    </div>
    <!--添加自定义模块-->
    <div class="addmodlebox">
        <div class="title">添加模块<span style="cursor: pointer; position: absolute; right: 10px; *top: 0px">关闭</span></div>
        <div class="content" id="tabs">
            <div class="tab_menu f-l">
                <ul>
                    <li class="selected">基础模块</li>
                    <li>其他模块1</li>
                    <li>其他模块2</li>
                </ul>
            </div>
            <div class="tab_box f-l">
                <div>
                    <p style="background: #f2f2f2; display: block">共有 3个 模块</p>
                    <div class="modeladdli">
                        <ul>
                            <li>加盟申请模块<div class="btn btn-small f-r" id="joinapp">添加</div>
                            </li>
                            <!--<li>产品展示模块<div class="btn btn-small f-r" id="prodshow">添加</div></li>-->
                            <li>品牌介绍模块<div class="btn btn-small f-r" id="introshow">添加</div>
                            </li>
                            <li>成功案例模块<div class="btn btn-small f-r" id="successcase">添加</div>
                            </li>
                            <!--<li>留言互动模块<div class="btn btn-small f-r" id="messageint">添加</div></li>-->
                            <li>自定义模块<div class="btn btn-small f-r" id="cusmodle">添加</div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="hide">其他模块1</div>
                <div class="hide">其他模块2</div>
            </div>
        </div>
    </div>
    <!--添加模块-->
    <div class="naveditarea">
        <div class="title">导航<span style="cursor: pointer; position: absolute; right: 10px; *top: 0px">关闭</span></div>
        <div>
            <ul>
                <li class="menu"><a class="menu-link" href="#" title="" target="">品牌首页</a></li>
                <li class="menu movenav"><a class="menu-link selected" href="#" title="" target="">品牌招募书</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">陈列视觉</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">产品展示</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">企业展示</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">门店展示</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">品牌新闻</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">资料下载</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <li class="menu movenav"><a class="menu-link" href="#" title="" target="">留言互动</a><div class="btn-group f-r"><span class="btn btn-small" style="margin: 7px 0 0 0" id="up">上移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="down">下移</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="addnavedit">编辑</span><span class="btn btn-small" style="margin: 7px 0 0 0" id="remove">删除</span></div>
                </li>
                <span class="address font f-r">店铺地址：http://duocare.8637.com 复制</span>
            </ul>
        </div>
        <div class="btn f-r" style="margin: 0 10px 0 0" id="addnav">添加导航</div>
        <div class="xzz" style="width: 100%; height: 100%; background: #000; opacity: 0.5; filter: Alpha(Opacity=50); position: absolute; top: 0; display: none"></div>
        <div style="position: absolute; width: 500px; top: 30px; left: 195px; border: 3px solid #ccc; background: #fff; display: none" class="addnavbox">
            <div class="title">添加导航内容<em style="cursor: pointer; position: absolute; right: 10px; *top: 0px">关闭</em></div>
            <div>
                <a>自定义链接</a><a>自定义页面</a><a>分类导航</a>
                <div>
                    自定义链接
            	链接名称：<input type="text" id="linkname" value="自定义链接" />
                    链接网址：<input type="text" value="http://" id="linkaddress" />
                    <br />
                    <span class="btn" id="addnavlist">添加</span>
                </div>
            </div>
        </div>
        <div class="tips" style="margin: 0 0 10px 10px; color: #666">* 建议导航不要超过十项内容(如果导航字数少，可适量增加)</div>
        <div class="btn btn-inverse" style="margin: 0 5px 10px 10px" id="naveditsure">确定</div>
        <div class="btn btn-inverse" style="margin: 0 0 10px 0">取消</div>
        <a href="#" style="position: absolute; top: 413px; left: 360px"><i class="icon-question-sign"></i>使用帮助</a>
    </div>
    <!--导航编辑区域-->
    <script src="${res}/scripts/jquery-1.11.0.min.js"></script>
<script src="${res}/scripts/common/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="${res}/scripts/market/brandmarket/jquery.elastislide.js" type="text/javascript"></script>
    <!--<script src="../res/decorationmodule/plugs/umeditor.config.js" type="text/javascript"></script>
<script src="../res/decorationmodule/plugs/editor_api.js" type="text/javascript"></script>
<script src="../res/decorationmodule/plugs/zh-cn.js" type="text/javascript"></script>
<script type="text/javascript">
    //实例化编辑器
    UM.getEditor('myEditor');
	UM.getEditor('headerEditor');
</script>-->
    <script src="${res}/scripts/market/brandmarket/colorpicker.js" type="text/javascript"></script><!--颜色获取-->
    <script>
        $(function () {
            $('#colorpickerHolder').ColorPicker({
                flat: true,
                onSubmit: function (hsb, hex, rgb) {
                    $('#header-nav').css('backgroundColor', '#' + hex);
                },
                onChange: function (hsb, hex, rgb) {
                    $('.colorbox').css('backgroundColor', '#' + hex);
                }
            });
            $('.colorbox').click(function () {
                $('#colorpickerHolder').fadeToggle();
                return false;
            });
            $('#colorpickerHolder').click(function () {
                return false;
            });
            $(document).click(function () {
                $('#colorpickerHolder').fadeOut();
            });
            $("a").click(function () {
                return false;
            });
        });
    </script>
    <script src="${res}/scripts/market/brandmarket/brandviewindex.js" type="text/javascript"></script>
</body>
</html>
