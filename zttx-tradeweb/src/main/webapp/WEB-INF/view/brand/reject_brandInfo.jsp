<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-账户信息</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/common/through.css"/>
    <style>
        .flow-steps li{
            width: 320px;
        }
    </style>
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <div id="quick_content" class="quick_content" data-widget-cid="widget-0" style="z-index: 99; display: none; position: absolute; left: 296.5px; top: 98px;">
        <ul>
        </ul>
    </div>
    <div id="sidebar" class="sidebar" style="top: 0px; z-index: 99; position: relative; left: 0px;">
        <div class="hd">
            <h2>导航菜单</h2>

            <div class="quick_nav">
                <a class="quick_link" href="javascript:;">快捷通道<i class="iconfont"></i></a>
            </div>
        </div>
        <div class="bd">
            <ul>
                <li class="item j_item current_item">
                    <div class="item_hd">
                        <h3>
                            <i class="icon i1 "></i>
                            账户信息
                        </h3>
                        <i class="down_arrow iconfont"></i>
                        <i class="up_arrow iconfont"></i>
                    </div>
                    <div class="item_sub ">
                        <ul>
                            <li class="sub_item" id="menu55">
                                <a href="${ctx}/brand/contact/list">联系资料</a>
                            </li>
                            <li class="sub_item" id="menu56">
                                <a class="current_sub" href="${ctx}/brand/info/index">企业资料</a>
                            </li>
                            <li class="sub_item" id="menu57">
                                <a href="${ctx}/brand/card/exeucte">证书管理</a>
                            </li>
                            <li class="sub_item" id="menu58">
                                <a href="${ctx}/brand/password">密码管理</a>
                            </li>
                            <li class="sub_item" id="menu59">
                                <a href="${ctx}/brand/info/verifPage">安全验证</a>
                            </li>
                        </ul>
                    </div>
                </li>

                <li class="item j_item ">
                    <div class="item_hd">
                        <h3>
                            <i class="icon i2 "></i>
                            权限管理
                        </h3>
                        <i class="down_arrow iconfont"></i>
                        <i class="up_arrow iconfont"></i>
                    </div>
                    <div class="item_sub ">
                        <ul>
                            <li class="sub_item" id="menu61">
                                <a href="${ctx}/brand/role/deptmanage">成员列表</a>
                            </li>
                            <li class="sub_item" id="menu62">
                                <a href="${ctx}/brand/role/rolemanage">权限分配</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="main_con">
        <div class="inner">
            <div class="bread_nav">
                <div class="fl">
                    <i class="icon"></i>
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <span>账户信息</span>
                </div>
                <%--<div class="fr">
                    <%@ include file="common_quick_bar.jsp" %>
                </div>--%>
            </div>

            <!-- 步骤条 -->
            <div class="flow-steps mt15">
                <ol class="mt10">
                    <li class="done"><span>注册成功</span></li>
                    <li class="done current-prev"><span>填写企业资料</span></li>
                    <li class="last-current"><span>确认审核</span></li>
                </ol>
            </div>

            <div class="mycount-nothrough">
                <dl class="clearfix">
                    <dt class="imgbox"></dt>
                    <dd class="tips">温馨提示:</dd>
                    <dd class="lager">请修改您的申请资料</dd>
                    <dd class="another">未通过原因：<em>${empty brandAudit ? '其他原因':brandAudit.checkMark}</em>，请重新提交</dd>
                    <dd>
                        <a class="ui_button ui_button_morange" href="${ctx}/brand/info/index">修改资料</a>
                    </dd>
                </dl>
                <div class="mycount-through-content">
                    您也可以通过以下方式
                    <a class="link" href="/about/contactus.jsp" target="_blank"><i class="through-icon"></i>联系我们</a> 电话：<em>400-111-8637</em> 邮箱：<em>services@8637.com</em>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
</body>
</html>