﻿<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
<meta charset="UTF-8">
<title>服务管理 - 微店设置 - 微店设置</title>
<link href="/styles/dealer/global.css" rel="stylesheet" />
<link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/dealer/mainmenu" />
		<div class="em100">
			<div class="main clearfix pr">
				<jsp:include page="${ctx}/dealer/dealermenu" />
				<div class="main-right">
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="/dealer/center">管理中心</a>
							&gt;&gt; <span class="bb">服务管理</span> <a
								class="panel-crumbs-help" href="/help/index"><i
								class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
					<div class="main-grid mb40 clearfix">
						<div class="weshop-manage">
							<h2>我的微店设置</h2>
							<jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp" ><jsp:param value="5" name="tab"/></jsp:include>
							<div class="hr-dashed"></div>
							<%--页面开始--%>
							<div class="weshop-manage-setting">
								<input name="shopId" value="${shopId}" type="hidden">
								

								<div class="setting-item">
									<label class="ui-label">店铺LOGO</label>
									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeLOGO">修改LOGO</a>
										<span class="block c-h">(一个月内只能申请修改一次)</span>
										<div class="hide">
											<form:form action="" class="LOGOFrom">
												<div id="dialogChangeLOGO">
													<div class="ui-head">
														<h3>修改LOGO</h3>
													</div>
                                                    <div class="pt20 changelogo-box hide">
                                                        <input name="logo_img" value="" type="hidden">
                                                        <input name="logo_imgName" value="" type="hidden">
                                                        <img class="changelogo-img" style="" src="">
                                                    </div>
													<div class="pt20 clearfix">
														<input class="hide" type="file" name="file" id="LogoBJ" />
														<button class="ui-button ui-button-mblue fl" id="LogoUpload">上传图片</button>
														<button class="ui-button ui-button-mred fr" id="SaveLOGO">保存</button>
													</div>
												</div>
											</form:form>
										</div>

									</div>

									<div class="js-img-center LogoImg" style="width:80px;height:80px">
										<img src="${weshop}${json.object.shopLogo }">
									</div>

								</div>
								<div class="setting-item">
									<label class="ui-label">店招背景</label>

									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeBG">修改</a>

										<div class="hide">
											<div id="dialogChangeBG">
												<div class="ui-head">
													<h3>修改店招背景</h3>
												</div>
												<div class="pt20 changebg-box hide">
                                                    <input name="logo_img" value="" type="hidden">
                                                    <input name="logo_imgName" value="" type="hidden">
													<img class="changebg-img" style="" src="">
												</div>
												<div class="pt20 clearfix">
													<input class="hide" type="file" name="file" id="LogoBG" />
													<button class="ui-button ui-button-mblue fl" id="uploadBG">上传图片</button>
													<button class="ui-button ui-button-mred fr" id="SaveBG">保存</button>
												</div>
											</div>
										</div>
									</div>

									<div class="js-img-center bgImage" style="width:320px;height:110px">
										<%--注意，图片原图必须大于这个尺寸的两倍，考虑手机屏幕--%>
										<img src="${weshop}${json.object.backImage }">
									</div>
								</div>
								<div class="setting-item">
									<label class="ui-label">名称</label> <span class="ui-form-text">
										${json.object.shopName }</span>

								</div>
								<div class="setting-item">
									<label class="ui-label">绑定邮箱</label>

									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeMail">修改</a> <span
											class="block c-h">(一个月内只能申请修改一次)</span>

										<%--修改邮箱地址--%>
										<div class="hide">
											<%--第一步,验证登录密码--%>
											<div id="dialogChangeMail-step1">
												<div class="ui-head">
													<h3>修改邮箱地址</h3>
												</div>
												<div style="width:300px;margin:50px auto;">
													<h3 class="mb20 fs14 yahei">请先输入登录密码进行身份验证</h3>
													<input class="ui-input" type="password" style="width:300px;" name="validatePas">
													<%--
													<h3 class="mb20 fs14 yahei">
														<i class="icon i-right-32" style="margin-left:-35px"></i>身份验证通过
													</h3>
													 --%>
													<h3 class="mb20 fs14 yahei">请输入新的邮箱地址</h3>
													<input class="ui-input" type="text" style="width:300px;" name="emailAddress">
														
												</div>
												<div class="ta-r">
													<button id="btnMailStep1" class="ui-button ui-button-mred">下一步</button>
												</div>
											</div>
											<%--第二步,输入新邮箱地址--%>
											<div id="dialogChangeMail-step2">
												<div class="ui-head">
													<h3>修改邮箱地址</h3>
												</div>
												<div style="width:300px;margin:50px auto;">
													<h3 class="mb20 fs14 yahei">
														<i class="icon i-right-32" style="margin-left:-35px"></i>身份验证通过
													</h3>
													<h3 class="mb20 fs14 yahei">请输入新的邮箱地址</h3>
													<input class="ui-input" type="text" style="width:300px;">
												</div>
												<div class="ta-r">
													<button id="btnMailStep2" class="ui-button ui-button-mred">下一步</button>
												</div>
											</div>

											<%--第三步,发送验证邮件--%>
											<div id="dialogChangeMail-step3">
												<div class="ui-head">
													<h3>修改邮箱地址成功</h3>
												</div>
												<div class="mt20"
													style="width:300px;margin-left:auto;margin-right:auto;">
													<h3 class="mb20 fs14 yahei">
														<i class="iconfont"
															style="font-size: 24px;vertical-align: -4px;">&#xe61f;</i>
														验证邮箱
													</h3>
													<ul>
														<li>已发送邮件到你的登录邮箱:<span class="code newUserMail">124125125@qq.com</span>
														</li>
														<li>请进入邮箱查看邮件,验证登录邮箱</li>
														<li><a class="link" href="{data}" target="_blank">登录邮箱</a>
														</li>
														<li class="mt20">没有收到邮件?</li>
														<li>检查你的邮件垃圾箱</li>
														<li>若仍未收到确认,请尝试<a class="link" 
															 id="reSend">重新发送</a></li>
													</ul>
												</div>
												<div class="mt20 ta-r">
													<button id="btnMailStep3"
														class="ui-button ui-button-morange">关闭</button>
												</div>
											</div>
										</div>
									</div>

									<span class="ui-form-text userEmail"> ${json.object.userEmail } </span>
								</div>
								<div class="setting-item">
									<label class="ui-label">约逛号</label> <span class="ui-form-text">
										${json.object.userCode} -</span>
								</div>
								<div class="setting-item">
									<label class="ui-label">登录手机</label>
									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeMobile">修改</a> <span
											class="block c-h">(一个月内只能申请修改一次)</span>
										<%--修改登录手机号--%>
										<div class="hide">
											<%--第一步,验证并修改密码--%>
											<div id="dialogChangeMobile-step1">
												<div class="ui-head">
													<h3>修改登录手机号</h3>
												</div>
												<form class="ui-form mt40" id="sendValidation">
												<form id="firstValidation">
													<div class="ui-form-item">
														<label class="ui-label">手 机 号:</label>
                                                        <input class="ui-input" disabled  id="oldTelephone" value="${map.userMobile}" >
														<button class="ui-button ui-button-lorange"  name="firstValidation" type="button">获取验证码</button>
													</div>
												</form>	
													<div class="ui-form-item">
														<label class="ui-label">验 证 码:</label>
                                                        <input class="ui-input" style="width: 60px;" name="firstValM">
                                                        <a class="link block" href="${ctx}/dealer/securityCert" target="_blank">旧号码已经停用了,收不到验证码?</a>
													</div>
													<div class="ui-form-item">
														<label class="ui-label">新的手机号码:</label>
                                                        <input class="ui-input" id="newTelephone">
														<button class="ui-button ui-button-lorange" name="secondValidation" type="button">获取验证码</button>
													</div>
													<div class="ui-form-item">
														<label class="ui-label">验 证 码:</label>
                                                        <input class="ui-input" style="width: 60px;" name="secondValM">
													</div>
												</form>
												<div class="ui-form-item">
													<button class="ui-button ui-button-lred" id="btnMobileStep1" type="button">提交</button>
												</div>
											</div>
											<%--第二步,完成--%>
											<div id="dialogChangeMobile-step2">
												<div class="ui-head">
													<h3>修改登录手机号</h3>
												</div>
												<div style="width:250px;margin:50px auto;">
													<i class="icon i-right-32"></i> <span class="yahei fs14">您的登录手机号码修改成功</span>
												</div>
												<div class="mt10 ta-c">
													<button id="btnMobileStep2" class="ui-button ui-button-mred">完成</button>
												</div>
											</div>
										</div>
									</div>
									<span class="ui-form-text userMobile"> ${json.object.userMobile } </span>
								</div>
							<%-- 	<div class="setting-item">
									<label class="ui-label">隐私设置</label>

									<div class="setting-box">
										<input class="js-check-capsule" id="checkPrivacy" checked
											type="checkbox">
									</div>
									<span class="ui-form-text"> <span id="LabelPrivacy"
										class="code">已允许</span><span class="c-h">（是否允许用户通过微店名称搜到该账号，但通过约逛号和二维码可以搜到）</span>
									</span>
								</div> --%>
								<div class="setting-item">
									<label class="ui-label">店铺地址</label>

									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeAddress">设置</a>
										<div class="hide">
											<div id="dialogChangeAddress">
												<div class="ui-head">
													<h3>修改店铺地址</h3>
												</div>
												<div style="margin:40px auto;width:450px;">
												
												 <form:form  id="changeAddress">
													<div class="mb20">
													    <input name="shopId" value="${shopId}" type="hidden">
														<span>新 地 址：</span>
														
														
                                                        <input type="hidden" name="provinceName" value="${map.provinceName}">
														<input type="hidden" name="cityName"  value="${map.cityName }" /> 
														<input type="hidden" name="areaName" value="${map.areaName }" />
													    <input type="hidden" name="areaNo" value="" /> <%-- 接口数据未供  --%>
											  
														<jsp:include page="${ctx}/client/Regional/searchaAll">
															<jsp:param value="" name="regProvince" />
															<jsp:param value="" name="regCity" />
															<jsp:param value="" name="regCounty" />
															<jsp:param value="test1" name="sale" />
															<jsp:param value="ui-select seeking-select-width"
																name="style" />
														</jsp:include>
													</div>
													<div style="padding-left:67px;">
														<input value="${map.address}" type="text"
															name="dealerAddress" class="ui-input" style="width:322px"
															placeholder="请输入详细地址" required data-display="详细地址" />
													</div>
													</form:form>
													<div class="mt20 ta-c">
														<button class="ui-button ui-button-lred" type="button">提 交</button>
													</div>
												
													
												</div>
											</div>
										</div>
									</div>
									<span class="ui-form-text" id="showAddress">${map.provinceName}${map.cityName }${map.areaName }${map.address }</span>
								</div>
								<div class="setting-item">
									<label class="ui-label">店铺介绍</label>

									<div class="setting-box">
										<a class="link" href="javascript:;" id="ChangeIntroduce">修改</a>
										<span class="block c-h">(一个月内只能申请修改一次)</span>

										<div class="hide">
											<div id="dialogChangeIntroduce">
												<div class="ui-head">
													<h3>修改店铺介绍</h3>
												</div>
												<div class="m020">
													<h3 class="lh2 fs14 yahei mt10 mb10">请输入店铺介绍</h3>
													<textarea class="ui-textarea" name="introduce"  id="introduceText" rows="4"
														style="width:100%"> ${json.object.shopMark} </textarea>
													<span class="c-r block mt5 mb10">* 店铺介绍长度为4-120个字。</span>
													<p class="c-h">
														审核通过后,你可以使用新的店铺介绍，审核时间约为3个工作日。审核成功后1个月内不能再更改店铺介绍。</p>
												</div>
												<div class="hr mt10 mb10"></div>
												<div class="ta-c">
													<button class="ui-button ui-button-lred">提 交</button>
												</div>
											</div>
										</div>

									</div>
									<span class="ui-form-text"  id="showIntroduce"> ${json.object.shopMark} </span>
								</div>
								
								<%-- 需求未明 暂不开发 --%>
								<div class="setting-item">
									<label class="ui-label">二维码</label>
									<div class="setting-box">
										<a class="link" href="javascript:;" id="QRSize">选择更多尺寸</a>

										<div class="hide">
											<div id="dialogQRSize">
												<div class="ui-head">
													<h3>更多尺寸的二维码</h3>
												</div>
												<div class="ui-table-container mt10">
													<table class="ui-table">
														<thead>
															<tr>
																<th>二维码边长(cm)</th>
																<th>建议扫描距离(米)</th>
																<th>下载链接</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>8cm</td>
																<td>0.5m</td>
																<td><a><i class="iconfont">&#xe620;</i> </a>
																</td>
															</tr>
															<tr>
																<td>12cm</td>
																<td>0.8m</td>
																<td><a><i class="iconfont">&#xe620;</i> </a>
																</td>
															</tr>
															<tr>
																<td>15cm</td>
																<td>1m</td>
																<td><a><i class="iconfont">&#xe620;</i> </a>
																</td>
															</tr>
															<tr>
																<td>30cm</td>
																<td>1.5m</td>
																<td><a><i class="iconfont">&#xe620;</i> </a>
																</td>
															</tr>
															<tr>
																<td>50cm</td>
																<td>2.5m</td>
																<td><a><i class="iconfont">&#xe620;</i> </a>
																</td>
															</tr>
														</tbody>
													</table>
													<p class="c-h mt10 mb10">二维码尺寸请按照43像素的整数倍缩放,以保持最佳效果</p>
													<div class="ta-c">
														<button class="ui-button ui-button-lred">关闭</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="myQRCode"></div>

								</div>
								<%--<div class="setting-item">
                                <label class="ui-label">图片水印</label>
                                <div class="setting-box">
                                    <a class="link" href="javascript:;" id="ChangeWaterMark">设置</a>
                                    <div class="hide">
                                        <div id="dialogChangeWaterMark">
                                            <div class="ui-head"><h3>修改店铺介绍</h3></div>
                                            <div class="m020 clearfix">
                                                <div class="watermark-preview ta-c">
                                                    <div class="preview-img">
                                                        <img src="">
                                                    </div>
                                                    <span>预览图</span>
                                                </div>
                                                <div class="watermark-setting">
                                                    <h3>设置图文消息和图片消息的约逛水印</h3>
                                                    <ul>
                                                        <li><label><input class="ui-radio" type="radio" name="watermark"> 使用约逛号</label></li>
                                                        <li><label><input class="ui-radio" type="radio" name="watermark"> 使用店铺名称</label></li>
                                                        <li><label><input class="ui-radio" type="radio" name="watermark"> 不添加</label></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="hr mt10 mb10"></div>
                                            <div class="ta-c">
                                                <button class="ui-button ui-button-lred">确 定</button>
                                                <button class="ui-button ui-button-lorange">取 消</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <span class="ui-form-text">
                                    使用店铺名作为水印
                                </span>
                            </div>--%>
							</div>
							<%--页面结束--%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
	</div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script>
    $.fn.offButton = function (callback) {
        this.each(function (i, o) {
            var btn = $("<div class='ui-offbutton'><span></span></div>")
                    .click(function () {
                        $(o).trigger("click");
                    }).addClass(o.checked ? "active" : "");
            $(o).change(function () {
                btn.toggleClass("active", o.checked);
                callback(o, btn);
            }).hide().after(btn);
        })
    }

    var weshopManage = {
        init: function () {
            this.initChangeMail(); //选择邮箱
            this.initChangeMobile(); //选择手机
            this.initChangeLOGO(); //选择LOGO
            this.initChangeBG(); //选择背景
            this.initSelectMoreQRSize(); //二维码
            this.initSelectPrivacy(); //隐私
            this.initChangeIntroduce(); //介绍
            this.initChangeAddress(); //地址
            //this.initChangeWaterMark();//水印
        },
        initChangeMail: function () {

            seajs.use(["dialog"], function (Dialog) {

                var cm2 = new Dialog({
                    content: "#dialogChangeMail-step2"
                }).after("show", function () {
                            cm1.hide();
                        });

                //修改邮箱
                var cm1 = new Dialog({
                    trigger: "#ChangeMail",
                    content: "#dialogChangeMail-step1"
                }).after("render", function () {
                            $(this.element[0]).find("#btnMailStep1").bind("click", function () {

                                var $validatePas = $("input[name='validatePas']").val();
                                var $emailAddress = $("input[name='emailAddress']").val();

                                if ($validatePas == null || "" == $validatePas) {
                                    remind("error", "密码不能为空！")
                                }

                                if (!(/^[a-z]([a-z0-9]*[-_]?[a-z0-9])*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i).test($emailAddress)) {

                                    remind("error", "邮箱格式不正确！")
                                    return false;
                                }

                                var $shopId = $("input[name='shopId']").val();
                                $.post("/weshop/config/changeEmail",{ "validatePas": $validatePas, "newEmail": $emailAddress, "shopId": $shopId },function(data){
                                	if (data.code == 121000) {
                                        remind('success', "修改邮箱成功!");
                                        cm1.hide();
                                        $('.userEmail').text($emailAddress);
                                        $('.newUserMail').text($emailAddress);
                                        cm3.show();
                                    } else {
                                        remind('error', data.message );
                                    }
                                },"json");
                            });
                        })



                var cm3 = new Dialog({
                    content: "#dialogChangeMail-step3"
                }).after("show", function () {
                            cm2.hide();
                });


                $("#reSend").click(function () {
                    cm3.hide();
                    cm1.show();
                });

                $("#btnMailStep3").click(function () {

                    cm3.hide();

                });
                //修改邮箱
            });
        },

        initChangeMobile: function () {

            seajs
                    .use(
                    ["dialog"],
                    function (Dialog) {
                        // 修改电话
                        var cm1 = new Dialog({
                            trigger: "#ChangeMobile",
                            content: "#dialogChangeMobile-step1"
                        }).after("render", function () {
                                    $(this.element[0]).find('button[name="firstValidation"]').click(
                                            function () {
                                                var urlAddr = "/common/sendvcode";
                                                var userMobile = $("#oldTelephone").val();
                                                changeMobile(urlAddr, { "userMobile": userMobile });
                                            });
                                    $(this.element[0]).find("button[name='secondValidation']").click(function () {
                                        var userMobile = $("#newTelephone").val();

                                        if (null == userMobile || "" == userMobile) {
                                            remind("error", "请输入新的手机号！");
                                            return false;
                                        };

                                        if (!(mobileNumReg(userMobile))) {
                                            remind("error", "请输入有效的手机号！");
                                            return false;
                                        }
                                        $.post("/common/sendvcode", { "userMobile": userMobile },function(data){
                                        	if (data.code == 126000) {
                                                remind('success', "验证发送成功!");
                                            } else {
                                            	if(data.code==3){
                                            		remind('error', "验证已发送！");
                                            	}else{
                                            		remind('error', "发送验证失败！");
                                            	}
                                            }
                                        },"json");
                                        
                                    });
                                    $(this.element[0]).find("#btnMobileStep1").click(
                                            function () {
                                                var oldUserMobile = $("#oldTelephone").val();
                                                var newUserMobile = $("#newTelephone").val();
                                                var $firstValM = $("input[name='firstValM']").val();
                                                var $secondValM = $("input[name='secondValM']").val();
                                                if (null == newUserMobile || "" == newUserMobile) {
                                                    remind("error", "请输入新的手机号！");
                                                    return false;
                                                };
                                                if (!(mobileNumReg(newUserMobile))) {
                                                    remind("error", "请输入有效的手机号！")
                                                    return false;
                                                }
                                                if ($secondValM.length != 6 || $firstValM.length != 6) {
                                                    remind("error", "请输入手机上的6位验证码！");
                                                    return false;
                                                };


                                                
                                                var params={ oldUserMobile: oldUserMobile,newUserMobile: newUserMobile,firstValM: $firstValM, secondValM: $secondValM,shopId: $('input[name="shopId"]').val()}
                                          
                                                $.post("/weshop/config/changeMobile", params,function(data){
                                                	if (data.code == 121000) {
                                                        remind('success', "修改手机成功!");
                                                        $('.userMobile').text(newUserMobile);
                                                        return;
                                                    } else {
                                                    	remind('error', "修改手机失败!");
                                                    }
                                                },"json");
                                                
                                                cm1.hide();
                                            });

                                });
                    });
        },

        initChangeLOGO: function () {

            seajs.use(["dialog", "ajaxFileUpload"], function (Dialog) {
                //修改LOGO
                var cm1 = new Dialog({
                    trigger: "#ChangeLOGO",
                    content: "#dialogChangeLOGO"
                });

                var cut = {},jcrop_api;

                seajs.use(["Jcrop"], function () {

                    $('#dialogChangeLOGO .changelogo-img').Jcrop({
                            boxWidth: 320,
                            boxHeight: 320,
                            minSize: [160, 160],
                            onChange: updatePreview,
                            onSelect: updatePreview,
                            aspectRatio: 1 / 1
                        },
                        function () {
                            jcrop_api = this;
                            jcrop_api.animateTo([0, 0,160, 160]);
                    });
                    function updatePreview(c) {
                        c.h = (c.h).toFixed(0);
                        c.w = (c.w).toFixed(0);
                        c.x = (c.x).toFixed(0);
                        c.y = (c.y).toFixed(0);
                        c.x2 = (c.x2).toFixed(0);
                        c.y2 = (c.y2).toFixed(0);
                        cut = c;
                    };
                });

                //修改LOGO 上传图片功能呢
                $("#dialogChangeLOGO #LogoUpload").click(function () {
                    $("#LogoBJ").click();
                });

                $("#LogoBJ").change(function(){
                    $.ajaxFileUpload({
                        url: '/dealer/weshop/upload',
                        secureuri: false,
                        fileElementId: "LogoBJ",
                        dataType: 'json',
                        success: function (data) {
                            if (data.code == 121000) {
                                $('#dialogChangeLOGO .changelogo-img').attr("src", "${weshop}" + data.rows[0].path);
                                $('#dialogChangeLOGO input[name="logo_img"]').val(data.rows[0].path);
                                $('#dialogChangeLOGO input[name="logo_imgName"]').val(data.rows[0].origName);
                                $('#dialogChangeLOGO .jcrop-preview').attr("src", "${weshop}" + data.rows[0].path);
                                $("#dialogChangeLOGO .changelogo-box").show();
                                jcrop_api.setImage("${weshop}" + data.rows[0].path);
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                });

                //修改LOGO  图片保存功能
                $("#dialogChangeLOGO #SaveLOGO").click(function () {
                    var img = $('#dialogChangeLOGO input[name="logo_img"]').val();
                    var imgName = $('#dialogChangeLOGO input[name="logo_imgName"]').val();
                    if (img == '' || imgName == '') {
                        remind("error", "请先上传图片");
                        return false;
                    }
                    cut.img = img;
                    cut.imgName = imgName;
                    cut.shopId = $.trim($('input[name="shopId"]').val());
                    $.ajax({
                        type: "POST",
                        url: "/weshop/config/saveLogo",
                        data: cut,
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 121000) {
                                //var img = $('#dialogChangeLOGO input[name="logo_img"]').val();
                                //$('.LogoImg img').attr("src", "${weshop}" + data.rows[0].path);
                                //remind("success", "图片保存成功!");
                                window.location.reload();
                                //cm1.hide();
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                });

            });
        },
        initChangeBG: function () {
            seajs.use(["dialog", "ajaxFileUpload"],function (Dialog) {
                //修改背景
                var cm1 = new Dialog({
                    trigger: "#ChangeBG",
                    content: "#dialogChangeBG"
                });
                var cut = {},jcrop_api;
                seajs.use(["Jcrop"], function () {
                    $('#dialogChangeBG .changebg-img').Jcrop({
                        boxWidth: 320,
                        boxHeight: 320,
                        minSize: [320, 110],
                        aspectRatio: 160 / 55,
                        onChange: updatePreview,
                        onSelect: updatePreview
                    }, function () {
                        jcrop_api = this;
                        jcrop_api.animateTo([0, 0, 160, 55]);
                    });
                    function updatePreview(c) {
                        c.h = (c.h).toFixed(0);
                        c.w = (c.w).toFixed(0);
                        c.x = (c.x).toFixed(0);
                        c.y = (c.y).toFixed(0);
                        c.x2 = (c.x2).toFixed(0);
                        c.y2 = (c.y2).toFixed(0);
                        cut = c;
                    };
                });
                //图片上传
                $('#uploadBG').click(function () {
                    $("#LogoBG").click();
                });

                $("#LogoBG").change(function(){
                    $.ajaxFileUpload({
                        url: '/dealer/weshop/upload',
                        secureuri: false,
                        fileElementId: "LogoBG",
                        dataType: 'json',
                        success: function (data) {
                            if (data.code == 121000) {
                                $('#dialogChangeBG .changebg-img').attr("src", "${weshop}" + data.rows[0].path);
                                $('#dialogChangeBG input[name="logo_img"]').val(data.rows[0].path);
                                $('#dialogChangeBG input[name="logo_imgName"]').val(data.rows[0].origName);
                                $('#dialogChangeBG .jcrop-preview').attr("src", "${weshop}" + data.rows[0].path);
                                $("#dialogChangeBG .changebg-box").show();
                                jcrop_api.setImage("${weshop}" + data.rows[0].path);
                                jcrop_api.Options({boxWidth: 160,boxHeight: 160,minSize: [320, 110],aspectRatio: 160 / 55});
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                });


                //背景上传
                $("#SaveBG").click(function () {
                    var img = $('#dialogChangeBG input[name="logo_img"]').val();
                    var imgName = $('#dialogChangeBG input[name="logo_imgName"]').val();
                    if (img == '' || imgName == '') {
                        remind("error", "请先上传图片");
                        return false;
                    }

                    cut.img = img;
                    cut.imgName = imgName;
                    cut.shopId = $.trim($('input[name="shopId"]').val());
                    $.ajax({
                        type: "POST",
                        url: "/weshop/config/saveBG",
                        data: cut,
                        dataType: "json",
                        success: function (data) {
                            if (data.code == 121000) {
                              //  $('.bgImage img').attr("src", "${weshop}" + data.object.shopLogo);
                                remind("success", "图片保存成功!");
                                window.location.reload();
                                cm1.hide();
                            } else {
                                remind("error", data.message);
                            }
                        }
                    });
                });
            });
        },
        /*下载更多二维码*/
        initSelectMoreQRSize: function () {
            seajs.use(["dialog", "qrcode"], function (Dialog, Qrcode) {

                var qr = new Qrcode({
                    text: "hello world!!",
                    width: 120,
                    height: 120
                });
                $(".myQRCode").append(qr);

                var my = new Dialog({
                    trigger: "#QRSize",
                    content: "#dialogQRSize"
                }).after("render", function () {
                            $(this.element[0]).find("button").click(function () {
                                my.hide();
                            });
                        });
            });
        },
        initSelectPrivacy: function () {
            //开关按钮
            $("#checkPrivacy").offButton(function (a, b) {

                $("#LabelPrivacy").text(a.checked ? "已允许" : "不允许");

            });
        },
        initChangeIntroduce: function () {

            seajs.use(["dialog"], function (Dialog) {
                //更新店铺介绍
                var my = new Dialog({
                    trigger: "#ChangeIntroduce",
                    content: "#dialogChangeIntroduce"
                }).after("render", function () {
                            $(this.element[0]).find("button").bind("click", function () {
                                var introduction_val = $("#introduceText").val();

                                if (introduction_val.length > 120 || introduction_val.length < 4) {
                                    alert("介绍长度为4-120个字！");
                                    return false;
                                }

                                $.ajax({
                                    type: "POST",
                                    url: "/weshop/config/changeIntroduce",
                                    data: {
                                        "introduction_val": introduction_val,
                                        "shopId": $('input[name="shopId"]').val()
                                    },
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == 121000) {
                                            remind('success', "更新介绍成功！");
                                            $("#showIntroduce").text(introduction_val);
                                        } else {
                                            remind('error', data.message);
                                        }
                                        my.hide();
                                    }
                                });
                            });

                        });
            });
        },
        initChangeAddress: function () {

            seajs.use(["dialog"], function (Dialog) {
                //修改店铺地址
                var my = new Dialog({
                    trigger: "#ChangeAddress",
                    content: "#dialogChangeAddress"
                }).after("render", function () {
                            $(this.element[0]).find("button").bind("click", function () {

                                var $province = $("#test1province option:selected").val();
                                var $city = $("#test1city option:selected").val();
                                var $county = $("#test1county option:selected").val();
                                var $areaNo = "";
                                var $dealerAddress = $("input[name='dealerAddress']").val();

                                if ($county != "") {
                                    $areaNo = $county;
                                } else if ($city != "") {
                                    $areaNo = $county;
                                } else if ($province != "") {
                                    $areaNo = $county;
                                } else {
                                    alert("请在下拉框中选择地址！");
                                    return false;
                                }

                                $("#changeAddress input[name='provinceName']").val($province);
                                $("#changeAddress input[name='cityName']").val($city);
                                $("#changeAddress input[name='areaName']").val($county);
                                $("#changeAddress input[name='areaNo']").val($areaNo);
                                $("#changeAddress input[name='dealerAddress']").val($dealerAddress);

                                $.ajax({
                                    type: "POST",
                                    url: "/weshop/config/changeAddress",
                                    data: $("#changeAddress").serialize(),
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == 121000) {
                                            remind('success', "更新地址成功！");
                                            var $provinceName = data.object.provinceName;
                                            var $cityName = data.object.cityName;
                                            var $areaName = data.object.areaName;
                                            var $dealerAddress = data.object.address;

                                            //装值倒装，从后往前
                                            //问题：若重复提交会页面信息累计
                                            $("#showAddress").text("");

                                            var addre = $provinceName + $cityName + $areaName + $dealerAddress;
                                            var showAddress = $("#showAddress").text(addre);


                                        } else {
                                            remind('error', data.message);
                                        }
                                        my.hide();
                                    }
                                });
                            });
                        });
            });
        },
        initChangeWaterMark: function () {
            seajs.use(["dialog"], function (Dialog) {
                //修改邮箱
                var my = new Dialog({
                    trigger: "#ChangeWaterMark",
                    content: "#dialogChangeWaterMark"
                }).after("render", function () {
                            $(this.element[0]).find("button").click(function () {
                                my.hide();
                            });
                        });
            });
        }

    };

    //自定义AJAX跳转方法
    function changeMobile(urlAddr, dataString) {
        $.ajax({
            type: "POST",
            url: urlAddr,
            data: dataString,
            dataType: "json",
            success: function (data) {
                if (data.code == 126000) {
                    remind('success', "验证发送成功!");
                    return data;
                } else {
                	if(data.code==3){
                		remind('error', "验证已发送！");
                	}else{
                		remind('error', "发送验证失败！");
                	}
                }
            }
        });

    };

    weshopManage.init();
	</script>
</body>
</html>
