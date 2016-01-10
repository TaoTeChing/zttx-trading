<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<title>终端商管理中心-首页</title>
<link href="${res}/styles/dealer/global.css" rel="stylesheet" />
<link href="${res}/styles/dealer/index.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
		<div class="em100">
			<div class="main clearfix">
				<jsp:include page="${ctx}/common/menuInfo/sidemenu" />
				<div class="main-right">
                    <%--<jsp:include page="${ctx }/dealer/message/unread" />--%>
                    <div class="layer1 clearfix">
                          <div class="workbench fl">
                              <div class="title clearfix">
                                  <span class="t1">交易前<i></i></span>
                                  <span class="t2">交易中<i></i></span>
                                  <span class="t3">交易后<i></i></span>
                              </div>
                              <dl class="list clearfix">
                                  <dd class="t1">
                                      <div class="trade">
                                          <span class="fname">加盟</span>
                                          <div class="items">
                                              <span class="item">邀请中的品牌商<a class="num" href="${ctx}/dealer/dealerJoinManage/inventing">（${empty dealerCount.inviteCount||dealerCount.inviteCount<0?0:dealerCount.inviteCount }）</a></span>
                                              <span class="item">申请中的品牌商<a class="num" href="${ctx}/dealer/dealerJoinManage/applying">（${empty dealerCount.applyCount||dealerCount.applyCount<0?0:dealerCount.applyCount }）</a></span>
                                          </div>
                                      </div>
                                  </dd>
                                  <dd class="t2">
                                      <div class="trade">
                                          <span class="item">已加盟的品牌商<a href="${ctx}/dealer/dealerJoinManage/copartner/brandes">（${empty dealerCount.joinCount||dealerCount.joinCount<0?0:dealerCount.joinCount }）</a></span>
                                      </div>
                                  </dd>
                                  <dd class="t3">
                                      <div class="trade">
                                          <%--<span class="item">合作终止<a class="num" href="javascript:void(0);">（0）</a></span>--%>
                                      </div>
                                  </dd>
                              </dl>
                              <dl class="list clearfix nb">
                                  <dd class="t1">
                                      <div class="trade">
                                          <span class="fname">订单</span>
                                          <div class="items">
                                              <span class="item">待付款<a href="${ctx}/dealer/dealerOrder/order?orderMuiltStatus=1">（${empty dealerCount.balanceCount||dealerCount.balanceCount<0?0:dealerCount.balanceCount}）</a></span>
                                          </div>
                                      </div>
                                  </dd>
                                  <dd class="t2">
                                      <div class="trade">
                                          <span class="item">待收货<a href="${ctx}/dealer/dealerOrder/order?orderMuiltStatus=3">（${empty dealerCount.waitConfirmCount||dealerCount.waitConfirmCount<0?0:dealerCount.waitConfirmCount}）</a></span>
                                          <span class="item">退款中<a href="${ctx}/dealer/dealerOrder/order?orderMuiltStatus=6">（${empty dealerCount.refundCount||dealerCount.refundCount<0?0:dealerCount.refundCount}）</a></span>
                                      </div>
                                  </dd>
                                  <dd class="t3">
                                      <div class="trade">
                                          <span class="item">投诉管理<a class="num" href="${ctx}/dealer/dealerComplaint/complaint">（0）</a></span>
                                      </div>
                                  </dd>
                              </dl>
                          </div>

                          <div class="info_area fr">
                              <div class="infoCenter">
                                  <div class="common_hd">
                                      <h3>最新动态</h3>
                                  </div>
                                  <div class="bd list">
                                      <ul>
                                          <c:set value="${fns:findSimpleByInfo( terrace_Inform, 5)}" var="info"></c:set>
                                          <c:forEach var="obj" items="${info.getList()}" varStatus="status">
                                              <li class="clearfix">
                                                  <a style="display: inline-block; width: 160px;" target="_blank"
                                                     class="fl fn-text-overflow" href="${ctx}/info/detail/${obj.refrenceId}">${obj.articleTitle}</a>
                                                  <span class="fr time">${fns:getTimeFromLong(obj.createTime,"yyyy.MM.dd")}</span>
                                              </li>
                                          </c:forEach>
                                      </ul>
                                  </div>
                              </div>
                          </div>
                      </div>
					<div class="layer2 clearfix">
						<div class="message">
                            <div class="common_hd">
                                <h3>相关信息</h3>
                            </div>
                            <dl>
                                <dt><i class="iconfont">&#xe61e;</i>诚信</dt>
                                <dd>
                                    <label>资质审核: </label>
                                    <span>
                                        <c:choose>
                                            <c:when test="${dealerInfo.checkState==0}"><font color="#0085CA"> 未审核</font> </c:when>
                                            <c:when test="${dealerInfo.checkState==1}"><font color="#0085CA"> 审核通过 </font></c:when>
                                            <c:otherwise><a href="javascript:void(0)"><font color="red">审核不通过</font></a></c:otherwise>
                                        </c:choose>
                                    </span>
                                </dd>
                                <dd>
                                    <label>账户认证/保障:</label>
                                    <span>
                                        <c:choose>
                                            <c:when test="${userInfo.mobileVerify == true && userInfo.mailVerify == true}"><a href="${ctx}/dealer/account/security">手机认证/邮箱认证</a></c:when>
                                            <c:when test="${userInfo.mobileVerify == true}"><a href="${ctx}/dealer/account/security">手机认证</a></c:when>
                                            <c:when test="${userInfo.mailVerify == true}"><a href="${ctx}/dealer/account/security">邮箱认证</a></c:when>
                                            <c:otherwise><a href="${ctx}/dealer/dealerInfo/account/security"><font color="red">未认证</font></a></c:otherwise>
                                        </c:choose>
                                    </span>
                                </dd>
                            </dl>
                            <dl class="nb">
                                <dt><i class="iconfont">&#xe61f;</i>消息</dt>
                                <dd>未读消息: <a href="${ctx}/dealer/message/system">（${empty headDealerCount ? 0: headDealerCount }）</a></dd>
                            </dl>
						</div>

                        <%--客服中心--%>
                        <div class="customerCenter">
                            <div class="common_hd">
                                <h3>客服中心</h3>
                            </div>
                            <c:choose>
                                <c:when test="${dealerService != null }">
                                    <div class="bd">
                                        <div class="photo fl">
                                                <%--头像放这里--%>
                                            <img src="${res}${dealerService.serviceImage}" width="70" height="70"/>
                                        </div>
                                        <div class="detail">
                                            <p>客服工号：${dealerService.jobNum}</p>
                                            <p>客服名字：${dealerService.serviceName}</p>
                                            <p>服务电话：0574-87217777<c:if test="${not empty dealerService.serviceMobile && 'null'!=dealerService.serviceMobile }">-${dealerService.serviceMobile}</c:if></p>
                                        </div>
                                        <div class="tel">
                                            <label>客户服务热线：</label>400-111-8637
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="no_service">
                                        尊敬的 ${ userInfo.userName } 用户，您还没有客服，请联系客服热线 400-111-8637
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
					</div>

                    <div class="layer3 mt10">
                        <ul class="handle-panel clearfix">
                            <li>
                                <a class="item1" href="${ctx}/dealer/dealerShoper/cart" title="进货管理">
                                    <i class="icon"></i>
                                    <span>
                                        进货<br/>管理
                                        <%--<span class="num">123</span>--%>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a class="item2" href="${ctx}/dealer/dealerOrder/order" title="交易管理">
                                    <i class="icon"></i>
                                    <span>
                                        交易<br/>管理
                                        <%--<span class="num">123</span>--%>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a class="item3" href="${ctx}/dealer/dealerJoinManage/applying" title="加盟管理">
                                    <i class="icon"></i>
                                    <span>
                                        加盟<br/>管理
                                        <%--<span class="num">123</span>--%>
                                    </span>
                                </a>
                            </li>
                            <%--<li><a class="item4" href="<%=ZttxConst.PAYAPI_WEBURL_PORTAL%>" title="财务中心"></a></li>--%>
                            <li>
                                <a class="item5" href="${ctx}/dealer/dealerInfo/account/infor" title="账户中心">
                                    <i class="icon"></i>
                                    <span>
                                        账户<br/>中心
                                        <%--<span class="num">123</span>--%>
                                    </span>
                                </a>
                            </li>
                            <%--<li><a class="item6" href="${ctx}/dealer/message" title="消息中心"></a></li>--%>
                            <%--<li><a class="item7" href="#" title="智慧门店ERP"></a><a class="ui-button ui-button-sorange" href="#">申请开通</a></li>--%>
                        </ul>
                    </div>

                    <%-- 微店信息--%>
					<div class="main-grid weshop mt10" id="weishop">
                        <%-- 未开启状态--%>
                        <div class="panel fn-joint-bottom weshop-nopass">
                            <div class="panel-head"><h3 class="yahei">我的微店</h3></div>
                            <div class="panel-content mt5">
                                <div class="">
                                    <div class="weshop-nopass-content">
                                        <h4>您还没开通任何微店</h4>
                                        <div>
                                            <span>什么是微店,</span>
                                            <a href="" class="link mr20">点这里了解</a>
                                            <a href="" class="ui-button ui-button-lorange">立即开通</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--开启状态
						<div class="panel panel-padd-md fn-joint-bottom">
                            <div class="panel-head"><h3 class="yahei">我的微店</h3></div>
                            <div class="panel-content mt5">
                                <div id="carousel" class="carousel">
                                    <span class="ui-switchable-prev-btn" data-switchable-role="prev"><i class="iconfont">&#xe60c;</i></span>
                                    <span class="ui-switchable-next-btn" data-switchable-role="next"><i class="iconfont">&#xe60f;</i></span>
                                    <div class="scroller">
                                        <div class="weshop-box ui-switchable-content" data-switchable-role="content" id="weishop">

                                        </div>
                                    </div>
                                </div>
                            </div>
						</div>--%>
					</div>
					<div class="main-grid mt10">
						<div class="tabpanel">
							<div class="tabs clearfix">
                                <a class="tab-item on" href="javascript:;">浏览过我的品牌商</a>
                                <a class="tab-item" href="javascript:;">我浏览过的品牌</a>
                                <a class="tab-item" href="javascript:;">我浏览过的产品</a>

								<div class="more">
									<a id="view_more_url"  href="${ctx}/dealer/copartner/bevisited" class="link">查看更多 &gt;</a>
								</div>
							</div>
							<div class="content ">
								<div class="tabitem">
									<form id="visitedForm" action="" method="post">
										<div class="panel-table-content">
											<table id="data_list_tbl" class="ui-table"></table>
											<div id="pagination"></div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
	<div class="hide">
		<div id="look_brand">
			<div class="ui-head">
				<h3>申请加盟</h3>
			</div>
			 <div style="height:400px;overflow:auto;">
			<table class="ui-table ui-table-noborder">
			</table>
			</div>
		</div>
	</div>

    <div class="hide">
        <div id="add_brand">
            <div class="ui-head">
                <h3>申请加盟</h3>
            </div>
            <form:form class="ui-form mt20" id="joinForm">
                <input type="hidden" id="brandsId" />

                <div class="ui-form-item" style="padding: 5px;">
                    <label class="ui-label" style="margin-left: 10px;width:80px;">联 系 人：</label> <span class="ui-form-text">${dealerInfo.dealerUser}</span>
                </div>

                <div class="ui-form-item" style="padding: 5px;">
                    <label class="ui-label" style="margin-left: 10px;width:80px;">联系方式：</label><input type="text" class="ui-input" id="telphone"  value="${userInfo.userMobile}"/>
                </div>

                <div class="ui-form-item" style="padding: 5px;">
                    <label class="ui-label"  style="margin-left: 10px;width:80px;">申请信息：</label>
                    <textarea class="ui-textarea" id="applyText" style="height:60px;width:250px;"></textarea>
                </div>

                <div class="ui-form-item">
                    <a href="javascript:void(0);" id="submit" class="ui-button ui-button-mred">申请提交</a>
                </div>
            </form:form>
        </div>
    </div>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />

	<script>

        var agency_index = {
            init:function(){
                var me = this;
                me.initTabChange();
                //me.initPhoto();

               /* $(".boderli:odd").css({
                    "background-color" : "#f9f9f9"
                });*/

                setTimeout(function(){
                    me.tabChange(0);
                },500);

            },
            initTabChange:function(){
                var me = this;
                var items = $(".tabs .tab-item").on("click", function() {
                    var index = $(this).index();
                    me.tabChange(index);
                    $(items).removeClass("on");
                    if(index == 0){
                        $("#view_more_url").attr("href", "${ctx}/dealer/copartner/bevisited");
                    }else if(index == 1){
                        $("#view_more_url").attr("href", "${ctx}/dealer/dealerJoinManage/visitedBrands");
                    }else if(index == 2){
                        $("#view_more_url").attr("href", "${ctx}/dealer/dealerShoper/browseHistroy");
                    }
                    $(this).addClass("on");
                });
            },
            /*initPhoto:function(){
                var me = this;
                $('#js-photo').bind('change', function() {
                    uploadImage($(this).attr('id'));
                });

                function showImage(uploadId, url, oldName) {
                    $('#js-photo-img').attr("src", '${res}'+url);
                }

                function uploadImage(uploadId) {
                    dialogLoading(function (dialog) {
                        seajs.use([ "$", "ajaxFileUpload" ], function($) {
                            $.ajaxFileUpload({url : '${ctx}/common/showImg',secureuri : false,
                                fileElementId : uploadId,
                                dataType : 'json',
                                success : function(data) {
                                    //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                                    $('#' + uploadId).bind('change', function() {
                                        uploadImage(uploadId);
                                    });

                                    if (data.code == 126000) {
                                        updatePhoto(data.message,uploadId);
                                    } else {
                                        remind("error", data.message);
                                    }
                                    dialog.hide();
                                },error: function(data,status,e){
                                	remind("error", "文件可能过大!"+status+" "+data+ " "+e);
                                }
                            });
                        });
                    });

                }

                function updatePhoto(userPhoto,uploadId) {
                    $.post("${ctx}/dealer/updatePhoto", {userPhoto : userPhoto}, function(data) {
                        if (data.code == 126000) {
                        	showImage(uploadId, data.object,data.message );
                            remind("success", "成功修改头像");
                        } else {
                            remind("error", data.message);
                        }
                    }, "json");
                }


            },*/
            joinBrands:function (brandId) {
                var me = this;
                me.joinBrands.clickId = brandId;

                $.ajax({
                    type : "POST",
                    url : "${ctx}/dealer/copartner/bevisited/applylist",
                    data : {brandId : brandId},
                    dataType : "json",
                    success : function(json) {
                        if (126000 == json.code) {
                            seajs.use([ "dialog", "template" ], function(Dialog,template) {
                                var html = template.render("feedback-templage-brands", json);
                                $(".ui-table-noborder").html(html);
                                var d1 = new Dialog({
                                    trigger : '#look_brand a.ui-button.apply',
                                    content : "#add_brand"
                                });
                                d1.after('show', function() {
                                    var btn = this.activeTrigger;
                                    $("#brandId").val(brandId);
                                    $("#brandsId").val($.trim(btn.parent("td").attr("data_id")));
                                    $(this.element).find('#submit').unbind('click').click(function() {
                                        me.joinApple_2(d1, btn, brandId);
                                    });
                                });
                            });

                        } else{
                            remind("error", json.message);
                        }

                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        remind("error", errorThrown);
                    }
                });
            },
            joinApple:function (obj) {
                var brandsId = $.trim($("#brandsId").val());
                var applyText = $.trim($("#applyText").val());
                var telphone = $.trim($("#telphone").val());
                $.post("${ctx}/dealer/joinManage/apply/join", {
                    brandsId : brandsId,
                    telphone: telphone,
                    inviteText : applyText
                }, function(data) {
                    if (data.code == 126000) {
                        remind("success", "成功提交申请!"); //111
                        obj.hide();
                        $("#applyText").val('');
                        g_pagination.render();
                    } else {
                        remind("error", data.message);
                    }
                }, "json");
            },
            joinApple_2:function (obj, btn, brandId) {
                var me = this;
                var brandsId = $.trim($("#brandsId").val());
                var applyText = $.trim($("#applyText").val());
                var telphone = $.trim($("#telphone").val());
                $.post("${ctx}/dealer/joinManage/apply/join", {
                    brandsId : brandsId,
                    telphone: telphone,
                    inviteText : applyText
                }, function(data) {
                    if (data.code == 126000) {
                        remind("success", "成功提交申请!");
                        g_pagination.render();
                        obj.hide();
                        me.joinBrands(brandId);
                    } else {
                        remind("error", data.message);
                    }
                }, "json");
            },
            updateApple:function (brandsId) {
                var me = this;
                confirmDialog("确定是否要撤销？", function() {
                    $.post("${ctx}/dealer/joinManage/apply/update", {brandsId : brandsId}, function(data) {
                        if (data.code == 126000) {
                            g_pagination.render();
                            remind("success", "成功撤销申请!");
                            me.joinBrands(me.joinBrands.clickId);
                        } else {
                            remind("error", data.message + "，不能撤销申请");
                        }
                    }, "json");
                });
            },
            tabChange:function (index) {
                var me = this;
                var data = {url : "",fn : function() {}};
                if (index == 0) {
                    data.url = "${ctx}/dealer/dealerJoinManage/beBrandVisited/data?pageSize=5";
                    data.fn = me.handleTab1;
                }
                if (index == 1) {
                    data.url = "${ctx}/dealer/dealerJoinManage/visitedBrands/data?pageSize=5";
                    data.fn = me.handleTab2;
                }
                if (index == 2) {
                    //我浏览过的产品请求URL
                    data.url = "${ctx}/dealer/dealerJoinManage/browseHistroy/data?pageSize=5";
                    data.fn = me.handleTab3;
                }
                seajs.use([ "pagination", "template" ], function(Pagination,template) {
                	
                 template.helper('$getImageDomainPathFn',function (url, width, height){
     			        return getImageDomainPath(url, width, height);
     			 });

                    window['g_pagination']=new Pagination({
                        url : data.url,
                        elem : "#pagination",
                        method : "post",
                        handleData : function(json) {
                            data.fn(json, template);
                        }
                    });
                });
            },
            handleTab1:function (json, template) {
                var me = agency_index;
                var html = template.render("feedback-template-main-brand", json);
                $("#data_list_tbl").empty().append(html);
                seajs.use([ 'dialog' ], function(Dialog) {
                    //表格样式
                    //$(".panel-table-content tbody tr:odd").css("background-color","#f9f9f9");
                    //var trs = $(".panel-table-content tr");
                    //$(trs).find("td:first,th:first").css("border-left", 0);
                    //$(trs).find("td:last,th:last").css("border-right", 0);
                    var d0 = new Dialog({
                        trigger : '#visitedForm a.ui-button',
                        content : "#look_brand",
                        width:560
                    }).after("show", function() {
                                var btn = this.activeTrigger;
                                var brandId = $.trim(btn.attr("data_id"));
                                if (brandId != null)
                                    me.joinBrands(brandId);
                            });
                });
            },
            handleTab2:function(json, template) {
                var me = agency_index;
                var html = template.render("feedback-template-main-brandes", json);
                $("#data_list_tbl").empty().append(html);
                seajs.use([ 'dialog' ], function(Dialog) {
                   // var trs = $(".panel-table-content tr");
                   // $(trs).find("td:first,th:first").css("border-left", 0);
                   // $(trs).find("td:last,th:last").css("border-right", 0);

                    var d1 = new Dialog({
                        trigger : 'td a.ui-button.apply',
                        content : "#add_brand",
                        width : 360
                    });
                    d1.after('show', function() {
                        var myd = this;
                                var btn = this.activeTrigger;
                                $("#brandsId").val($.trim(btn.parent("td").attr("data_id")));
                                $(this.element).find('#submit').unbind('click').click(function() {
                                    //console.log(me);
                                    me.joinApple(myd);
                                });
                            });
                });
            },
            handleTab3: function(json, template){
                var me = agency_index;
                var html = template.render("feedback-template-main-products", json);
                $("#data_list_tbl").empty().append(html);
            },
            updateInvitedApple:function(brandsId) {
                var me = this;
                var brandId = $.trim($("#brandId").val());
                confirmDialog("确定是否接受邀请？", function() {
                    $.post("/dealer/invited/accept", {brandsId : brandsId}, function(data) {
                        if (data.code == 126000) {
                            g_pagination.render();
                            remind("success", "成功接受邀请!", function() {
                                me.joinBrands(brandId);
                            });
                        } else {
                            remind("error", data.message + "，不能授受邀请");
                        }
                    }, "json");
                });
            },
            initCarousel:function(){
                if($("#carousel").length>0) {
                    seajs.use(['carousel', '$'], function (Carousel, $) {
                        var carousel = new Carousel({
                            element: '#carousel',
                            hasTriggers: false,
                            easing: 'easeOutStrong',
                            panels:'#carousel .weshop-item',
                            effect: 'scrollx',
                            step: 2,
                            viewSize: [312],
                            circular: false,
                            autoplay: false,
                            prevBtn:"#prev",
                            nextBtn:"#next"
                        }).render();
                    });
                }
            }
        };

        /**我的微店*/
        $(function(){
            agency_index.init();
            seajs.use(["template"], function (template) {
                $.post("${ctx}/dealer/weishop/data",function(json){
                    var html = template.render(json.rows && json.rows.length === 0 ? "weishop_template":"weishop_template_have", json);
                    $("#weishop").html(html);
                    agency_index.initCarousel();
                },"json");
            });
        });
	</script>
    
    <script id="weishop_template" type="text/html">
	<div class="panel fn-joint-bottom weshop-nopass">
                            <div class="panel-head"><h3 class="yahei">我的微店</h3></div>
                            <div class="panel-content mt5">
                                <div class="">
                                    <div class="weshop-nopass-content">
                                        <h4>您还没开通任何微店</h4>
                                        <div>
                                            <span>什么是微店,</span>
                                            <a href="http://app.8637.com/" class="link mr20" target="_blank">点这里了解</a>
                                            <a href="${ctx}/dealer/weishop" class="ui-button ui-button-lorange">立即开通</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
    </script>
    <script id="weishop_template_have" type="text/html"> 
	<div class="panel panel-padd-md fn-joint-bottom">
        <div class="panel-head"><h3 class="yahei">我的微店</h3></div>
        <div class="panel-content mt5">
            <div id="carousel" class="carousel">
                <span id="prev"><i class="iconfont">&#xe60c;</i></span>
                <span id="next"><i class="iconfont">&#xe60f;</i></span>
                <div class="scroller">
                    <div class="weshop-box ui-switchable-content" data-switchable-role="content" >
                        	{{each rows}}
       							<div class="weshop-item ui-switchable-panel">
        							   <div class="shop-title">
      							         <h3 class="fl" title="{{$value.wshopName}}">{{$value.wshopName}}</h3>
    								           <%--<a class="link fr" target="_blank" href="${ctx}/dealer/weishop/managerWeiShop?shopId={{$value.wshopId}}">管理</a>--%>
     							        </div>
    							       <div class="shop-info">
       							        <span class="fl">绑定约逛号：{{$value.userCode}}</span>
       							        <span class="fr">手机号：{{$value.userMobile}}</span>
       									</div>
          							 <ul class="shop-data">
           							    <li>待处理订单：<span class="c-o fs14 bb">{{$value.newOrderCount}}</span> 笔</li>
         							    <li>所有关注：<span class="c-o fs14 bb">{{$value.followCount}}</span> 人</li>
       						  	        <li>累计访问：<span class="c-o fs14 bb">{{$value.visitCount}}</span> 次</li>
       						 	       <li>累计咨询：<span class="c-o fs14 bb">{{$value.askCount}}</span> 人</li>
      						  	   </ul>
     						  </div>
      						{{/each}}
                    </div>
                </div>
            </div>
        </div>
     </div>     
    </script>
                                            
                                            
    <script id="feedback-template-main-brand" type="text/html">
        <thead>
        <tr>
            <th class="tb35" style="text-align: center;">品牌商</th>
            <th class="tb15" style="text-align: center;">公司规模</th>
            <th class="tb15" style="text-align: center;">年营业额</th>
            <th class="tb25" style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
        {{if rows.length==0}}
        <td style="text-align: center;" colspan='4'>暂无数据</td>
        {{/if}}
        {{each rows}}
        <tr>
            <td style="text-align: center;"><span>{{$value.brandName}}</span></td>
            <td style="text-align: center;"> {{$value.emploeeNumName}} </td>
            <td style="text-align: center;"> {{$value.moneyNumName}} </td>
            <td style="text-align: center;"><a class="ui-button ui-button-lorange" data_id="{{$value.brandId}}">加盟旗下品牌</a></td>
        </tr>
        {{/each}}
        </tbody>
    </script>
    <!-- 浏览过我的品牌 end -->
    <script id="feedback-templage-brands" type="text/html">
        <tbody>
        {{if rows.length==0}}
        	<tr><td colspan="4" style="text-align:center;">暂无数据</td></tr>
        {{/if}}
        {{each rows}}
        <tr {{if $index%2==1}}class="ui-table-split"{{/if}} style="height:60px;">
        <td width="10px;"><a target="_blank" href='http://{{$value.domain}}${zttx}'><img class="l-120x60" src="${res}{{$getImageDomainPathFn $value.logoDomin+$value.brandLogo 120 60}}" /></a></td>
        <td width="50%"><h3><a target="_blank" href='http://{{$value.domain}}${zttx}'>{{$value.brandName}}</a></h3></td>
        <td width="45%" class="ta-c" data_id="{{$value.refrenceId}}">
            {{if $value.applyState==0}}
            <a href="javascript:agency_index.updateApple('{{$value.refrenceId}}');" class="ui-button ui-button-sorange mb5">撤销申请</a>
            <span class="block c-r">正在审核您的申请</span>
            {{/if}}
            {{if $value.applyState==1}}
            <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="link">查看招募书</a><%-- <a href="#" class="ml10 link">查看品牌证书</a>--%>
            {{/if}}
            {{if $value.applyState==3}}
            <a href="javascript:agency_index.updateInvitedApple('{{$value.refrenceId}}');" class="ui-button ui-button-sorange mb5"><i class="iconfont">&#xe620;</i> 接受邀请</a>
            <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="block c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml10 c-b">查看品牌证书</a>--%>
            {{/if}}
            {{if $value.applyState==2 || $value.applyState==4 || $value.applyState==5 || $value.applyState==6 || $value.applyState==null}}
            <a href="javascript:void(0);" class="ui-button ui-button-sorange mb5 apply"><i class="iconfont">&#xe618;</i> 申请加盟</a>
            <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="block c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml10 c-b">查看品牌证书</a>--%>
            {{/if}}
        </td>
        </tr>
        {{/each}}
        </tbody>
    </script>
    <script id="feedback-template-main-brandes" type="text/html">
        <thead>
        <tr>
            <th class="tb25" style="text-align: center;">品牌LOGO</th>
            <th class="tb20" style="text-align: center;">品牌商</th>
            <th class="tb15" style="text-align: center;">公司规模</th>
            <th class="tb15" style="text-align: center;">年营业额</th>
            <th class="tb25" style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
        {{if rows.length==0}}
        <td style="text-align: center;" colspan='5'>暂无数据</td>
        {{/if}}
        {{each rows}}
        <tr>
            <td style="text-align: center;">
                <a target="_blank" href='http://{{$value.domain}}${zttx}'>
                    <img class="l-120x60" src="${res}{{$getImageDomainPathFn $value.domainName+$value.logoName 120 60}}"/>
                    <h3>{{$value.brandsName}}</h3>
                </a>
            </td>
            <td style="text-align: center;"><span>{{$value.brandName}}</span></td>
            <td style="text-align: center;"> {{$value.emploeeNumName}}</td>
            <td style="text-align: center;"> {{$value.moneyNumName}}</td>
            <td data_id="{{$value.brandsId}}" style="text-align: center;">
                {{if $value.applyState==0}}
                <a href="javascript:agency_index.updateApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb5 invite">撤销申请</a>
                <span class="block c-r">正在审核您的申请</span>
                {{/if}}
                {{if $value.applyState==1}}
                <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="link">查看招募书</a><%-- <a href="#" class="ml10 link">查看品牌证书</a>--%>
                {{/if}}
                {{if $value.applyState==3}}
                <a href="javascript:agency_index.updateInvitedApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb5"><i class="iconfont">&#xe620;</i> 接受邀请</a>
                <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="block c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml10 c-b">查看品牌证书</a>--%>
                {{/if}}
                {{if $value.applyState==2 || $value.applyState==4 || $value.applyState==5 || $value.applyState==6 ||
                $value.applyState==null}}
                <a href="javascript:void(0);" class="ui-button ui-button-sorange mb5 apply"><i class="iconfont">&#xe618;</i> 申请加盟</a>
                <a target="_blank" href="http://{{$value.domain}}${zttx}/recruit" class="block c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml10 c-b">查看品牌证书</a>--%>
                {{/if}}
            </td>
        </tr>
        {{/each}}
        </tbody>
    </script>
    <script id="feedback-template-main-products" type="text/html">
        <thead>
        <tr>
            <th class="tb25" style="text-align: center;">产品图片</th>
            <th class="tb20" style="text-align: center;">产品标题</th>
            <th class="tb15" style="text-align: center;">价格</th>
        </tr>
        </thead>
        <tbody>
        {{if rows.length==0}}
        <td style="text-align: center;" colspan='3'>暂无数据</td>
        {{/if}}
        {{each rows}}
        <tr>
            <td style="text-align: center;">
                <a target="_blank" href='${ctx}/market/product/{{$value.productId}}'>
                    <img class="l-120x60" src="${res}{{$getImageDomainPathFn $value.productImage 120 60}}"/>
                </a>
            </td>
            <td style="text-align: center;"><span>{{$value.productTitle}}</span></td>
            <td style="text-align: center;">
                {{if $value.type == 3}}
                {{$value.cash}}
                {{else if $value.type == 4}}
                {{$value.credit}}
                {{else}}
                {{$value.price}}
                {{/if}}
            </td>
        </tr>
        {{/each}}
        </tbody>
    </script>
</body>
</html>
