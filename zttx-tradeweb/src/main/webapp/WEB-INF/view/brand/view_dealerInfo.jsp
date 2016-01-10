<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<%@ page import="com.zttx.web.utils.CalendarUtils" %>
<title>管理中心-终端商管理-终端商详情</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
<style>
    .ui-tanchuang .check-sty label{
        margin-left: 0;
    }
    .store_pic{
        position: relative;
    }
    .store_pic .imgList{
        width: 400px;
        overflow: hidden;
    }
    .store_pic .imgList ul{
        float: left;
        width: 9999px !important;
    }
    .store_pic .prev,.store_pic .next{
        width: 22px;
        height: 22px;
        position: absolute;
        overflow: hidden;
        border-radius: 3px;
        background: #1d7ad9;
        top: 0;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        text-align: center;
    }
    .store_pic .disabled{
        background: #e3e3e3;
    }
    .store_pic .prev{
        right: 27px;
    }
    .store_pic .next{
        right: 0;
    }
</style>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <!-- 面包导航，并不是每个页面都有 -->
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                    <span class="arrow">&gt;</span>
                    <span class="current">终端商详情</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                        <jsp:param value="0" name="isShow"/>
                    </jsp:include>
                </div>
            </div>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<!-- 申请中的终端商 -->
				<div class="agency-info-contant">

					<div class="js_agency_con">
						<div class="agency-info-recom">

                            <div class="agency-info-btnbar clearfix">
                                <div class="fl">
                                    <a href="javascript:void(0);" class="ui_button ui_button_lorange js-invite-join" tabindex="-1" data-dealerid="${info.refrenceId}"><i class="editbtnblue-icons editbtnblue-icons-join"></i>邀请加盟</a>

                                    <!-- 点击下载资料的时候，直接弹出是否保存文件的提示 2014-04-03 -->
                                    <c:if test="${not isCollected}">
                                    	<a href="javascript:void(0);" class="simple_button js-collect" data-dealerid="${info.refrenceId}">
                                    		<i class="editbtnblue-icons editbtnblue-icons-cash"></i>
                                    		<em>收藏</em>
                                    	</a>
                                    </c:if>
                                    <c:if test="${isCollected}">
                                    	<a href="javascript:void(0);" class="simple_button js-unCollect" data-dealerid="${info.refrenceId}">
                                    		<i class="editbtnblue-icons editbtnblue-icons-cash"></i>
                                    		<em>取消收藏</em>
                                    	</a>
                                    </c:if>
                                </div>
                                <div class="info-cansee inline-block fr">
                                    <span class="info-look">当前还可查看经销商： <em id="viewDealerCount">${viewDealerCount}</em> 家</span>
                                    <a href="" class="info-order" style="display:none;">订购</a>
                                </div>
                            </div>
							
                            <div class="detail_contain">
                                <ul class="box">
                                    <li class="item">
                                        <div class="tit">
                                            <i class="i1"></i><span>店铺信息</span>
                                        </div>
                                        <div style="height:240px;" class="bd info_list">
                                            <c:if test="${!empty info.dealerName}">
                                                <p>
                                                    <label> 公司名： </label>
                                                    <span>${info.dealerName}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${!empty info.provinceName}">
                                                <p>
                                                    <label for=""> 所在地： </label>
                                                    <span>${info.provinceName} ${info.cityName} ${info.areaName}</span>
                                                    <c:if test="${!empty info.gpsX && !empty info.gpsY }">
                                                    <a href="http://api.map.baidu.com/marker?location=${info.gpsX},${info.gpsY}&title=终端商位置&content=${info.dealerName}&output=html" target="_blank">查看地图</a>
                                                	</c:if>
                                                </p>
                                            </c:if>
                                            <c:if test="${!empty categorys}">
                                                <p>
                                                    <label> 经营品类： </label>
                                                    <span style="display: inline-block;width:370px;vertical-align: middle" class="fn-text-overflow">
                                                        <c:forEach var="cate" items="${categorys}" varStatus="status">
                                                              <c:if test="${status.index > 0}">、</c:if>${cate.dealName}
                                                        </c:forEach>
                                                    </span>
                                                </p>
                                            </c:if>
                                            <p>
                                                <c:if test="${!empty info.foundTime}">
                                                    <label> 开店经验： </label>
                                                    <span style="margin-right: 148px;">
                                                        <c:set var="now" value="<%=CalendarUtils.getCurrentLong() %>"/>
                                                            ${info.foundTime<now?"有":"无"}
                                                    </span>
                                                </c:if>
                                                <label for=""> 实体店： </label>
                                                <span>
                                                    正在经营
                                                </span>
                                            </p>
                                            <c:if test="${!empty info.foundTime}">
                                                <p>
                                                    <label> 开店日期： </label>
                                                    <span>
                                                            ${fns:getStringTime(info.foundTime,"yyyy-MM-dd")}
                                                    </span>
                                                </p>
                                            </c:if>
                                            <c:if test="${!empty info.shopNum && info.shopNum > 0}">
                                                <p>
                                                    <label for=""> 连锁店数： </label>
                                                    <span>${info.shopNum}家</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${!empty info.dealerWeb}">
                                                <p>
                                                    <label for=""> 公司网站： </label>
                                                    <span>
                                                        <a href="${info.dealerWeb}" target="_blank">${info.dealerWeb}</a>
                                                    </span>
                                                </p>
                                            </c:if>
                                            <c:if test="${!empty info.dealerShop}">
                                                <p>
                                                    <label for=""> 网上店铺： </label>
                                                    <span>
                                                        <a href="${info.dealerShop}">${info.dealerShop}</a>
                                                    </span>
                                                </p>
                                            </c:if>
                                        </div>
                                    </li>
                                    <li class="item">
                                        <div class="tit">
                                            <i class="i2"></i><span>联系方式</span>
                                        </div>
                                        <div class="bd contact_info">
                                            <c:choose>
                                                <c:when test="${isExits}">
                                                    <div class="contact_main clearfix">
                                                        <div class="left">
                                                            <c:choose>
                                                                <c:when test="${empty info.headImage}">
                                                                    <img class="photo" src="${res}/images/brand/defalut_photo.png" alt="" width="100" height="100" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img class="photo" src="${res}${fns:getImageDomainPath(info.headImage, 100, 100)}" alt="" width="100" height="100" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="right">
                                                            <c:if test="${!empty info.dealerUser}">
                                                                <p>
                                                                    <label for=""> 联系人： </label>
                                                                    <span> ${info.dealerUser}</span>
                                                                </p>
                                                            </c:if>
                                                            <c:if test="${!empty dealerUserm.userMobile}">
                                                                <p>
                                                                    <label for=""> 手机号码： </label>
                                                                    <span>${dealerUserm.userMobile}</span>
                                                                </p>
                                                            </c:if>
                                                            <c:if test="${!empty info.dealerTel}">
                                                                <p>
                                                                    <label for=""> 联系电话： </label>
                                                            <span>
                                                                    ${info.dealerTel}
                                                            </span>
                                                                </p>
                                                            </c:if>
                                                            <c:if test="${!empty info.dealerFax}">
                                                                <p>
                                                                    <label for=""> 传真号码： </label>
                                                            <span>
                                                                    ${info.dealerFax}
                                                            </span>
                                                                </p>
                                                            </c:if>
                                                            <c:if test="${!empty info.dealerAddress}">
                                                                <p>
                                                                    <label for=""> 公司地址： </label>
                                                            <span>
                                                                    ${info.dealerAddress}
                                                            </span>
                                                                </p>
                                                            </c:if>
                                                        </div>
                                                    </div>

                                                </c:when>
                                                <c:otherwise>
                                                    <div id="trigger" style="cursor: pointer">
                                                        <img src="${res}/images/brand/nopurview.png" alt=""/>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </li>
                                    <li class="item">
                                        <div class="tit">
                                            <i class="i3"></i><span>终端简介</span>
                                        </div>
                                        <div class="bd intro">
                                            ${info.dealerMark }
                                            <c:if test="${empty info.dealerMark}">
                                                暂无数据
                                            </c:if>
                                        </div>
                                    </li>
                                    <li class="item">
                                        <div class="tit">
                                            <i class="i4"></i><span>门店图片</span>
                                        </div>
                                        <div id="carousel" class="bd store_pic">
                                            <c:choose>
                                                <c:when test="${!empty dealerImageList}">
                                                    <div class="imgList">
                                                        <ul class="inline-float">
                                                            <c:forEach var="dealerImage" items="${dealerImageList}">
                                                                <li class="panel">
                                                                    <img src="${res}${dealerImage.imageName}" width="400" />
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                    <c:if test="${dealerImageList.size() > 1}">
                                                        <a href="javascript:;" class="prev disabled">&lt;</a>
                                                        <a href="javascript:;" class="next">&gt;</a>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${res}/images/brand/noStorePic.png" />
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </li>
                                </ul>
                                
                                <c:if test="${not empty queryPriceList}">        
                                <!-- 询价内容开始 -->
                                <div class="inquiry_con">
                                    <div class="tit">
                                        <i class="iconfont add_icon">&#xe611;</i><span>询价内容</span>
                                    </div>
                                    <div class="bd">
                                        <ul style="margin-top: -10px;">
                                        	<c:forEach var="queryPrice" items="${queryPriceList}" varStatus="status">
                                            <li>
                                                <div class="i-hd">
                                                    单号：${queryPrice.queryNo}
                                                    <span class="date">日期：${fns:getTimeFromLong(queryPrice.createTime,"yyyy-MM-dd HH:mm:ss")}</span>
                                                </div>
                                                <div class="i-bd clearfix">
                                                    <div class="img-contain">
                                                        <img src="${res}${fns:getImageDomainPath(queryPrice.productImage, 160, 160)}" width="78" height="78" alt=""/>
                                                    </div>
                                                    <div class="info">
                                                        <p>
                                                            <label for="">产品名称：</label>
                                                            <a style="width: 224px;" class="inline-block fn-text-overflow link" href="${ctx}/market/product/${queryPrice.productId}">${queryPrice.productName}</a>
                                                            <label for="">货号：</label>
                                                            <span style="width: 232px;" class="inline-block fn-text-overflow">${queryPrice.productNo}</span>
                                                            <label for="">询价数量：</label>
                                                            <span class="num">${queryPrice.purchaseNum}${empty queryPrice.unit ? '件':queryPrice.unit}</span>
                                                        </p>
                                                        <p>
                                                            <label for="">采购说明：</label>
                                                            <span style="width: 650px;" class="inline-block fn-text-overflow">
                                                                ${queryPrice.purchaseMark}
                                                            </span>
                                                        </p>
                                                    </div>
                                                    <c:if test="${!empty queryPrice.attachment}">
                                                    <span class="download">
                                                        <i></i><a href="${ctx}/brand/line/queryDownFile/${queryPrice.refrenceId}" target="_blank">下载附件</a>
                                                    </span>
                                                    </c:if>
                                                </div>
                                            </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <!-- 询价内容结束-->
                                </c:if>
                                
                            </div>

							<div style="display: none">
								<!--弹窗内容-->
								<div class="ui-tanchuang">
									<div class="ui-head">
										<h3>提醒</h3>
									</div>
									<div class="ui-Ttipcon">
										本次操作会扣除<em>[1]</em>次查看机会机会,<br /> 再次对该终端商操作将不再扣除。<br />
										您当前还剩余<em>[${viewDealerCount}]</em>次机会
									</div>
									<a href="javascript:;"
										class="ui_button ui_button_morange confirm_btn js-confirm-dealerid" data-dealerid="${info.refrenceId}">确认</a> <a
										href="javascript:;"
										class="ui_button ui_button_mgray cancel_btn">取消</a>
									<div class="check-sty">
										<input type="checkbox" class="js_chk js-notip" id="setdef1" name="setdef1" <c:if test="${isShow}">checked</c:if> >
										 <label for="setdef1">不再提醒</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp" />
	
	
	<script id="feedback-templage" type="text/html">
        <div class="contact_main clearfix">
            <div class="left">
                {{if headImage==null || headImage==""}}
                <img class="photo" src="${res}/images/brand/defalut_photo.png" alt="" width="100" height="100" />
                {{else}}
                <img class="photo" src="${res}{{$getImageDomainPathFn headImage 100 100}}" alt="" width="100" height="100" />
                {{/if}}
            </div>
            <div class="right">
                {{if null!=dealerUser && ""!=dealerUser}}
                <p>
                    <label for=""> 联系人： </label>
                    <span>{{dealerUser}}</span>
                </p>
                {{/if}}
                {{if null!=userMobile && ""!=userMobile}}
                <p>
                    <label for=""> 手机号码： </label>
                    <span>{{userMobile}}</span>
                </p>
                {{/if}}
                {{if null!=dealerTel && ""!=dealerTel}}
                <p>
                    <label for=""> 联系电话： </label>
                    <span>{{dealerTel}}</span>
                </p>
                {{/if}}
                {{if null!=dealerFax && ""!=dealerFax}}
                <p>
                    <label for=""> 传真号码： </label>
                    <span>{{dealerFax}}</span>
                </p>
                {{/if}}
                {{if null!=dealerAddress && ""!=dealerAddress}}
                <p>
                    <label for=""> 公司地址： </label>
                    <span>{{dealerAddress}}</span>
                </p>
                {{/if}}
            </div>
        </div>
	</script>
	
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/agencymanag.js"></script>
	<script>
		agencyInfo.init();
		function inviteJoin(obj,invitedio){
			$("#dealerId").val($.trim(obj.attr("data-dealerid")));
			invitEditDiv=invitedio;
		}
        if($("#carousel .panel").length > 1){
            seajs.use("carousel",function(Carousel){
                new Carousel({
                    element: "#carousel",
                    panels: "#carousel .panel",
                    prevBtn: "#carousel .prev",
                    nextBtn: "#carousel .next",
                    disabledBtnClass: "disabled",
                    hasTriggers: false,
                    easing: "easeOutStrong",
                    effect: "scrollx",
                    step: 1,
                    viewSize: [400],
                    circular: false,
                    autoplay: true
                })
            })
        }


		function inviteJoinCallback(json)
		{

			seajs.use(["template"], function (template) {
				template.helper('$getImageDomainPathFn',function (url, width, height){
                	return getImageDomainPath(url, width, height);
            	});
		    	var html = template.render("feedback-templage", json);
		    	$(".contact_info").html(html);
		    	if(!json.isViewAdd){
		    		var count =parseInt($.trim($("#viewDealerCount").text()))-1;
		    		count = count<0 ? 0:count;
		    		$("#viewDealerCount").text(count);
		    	}

	        });
		}
		
		// 收藏
		$(document).on('click','.js-collect',function(){
			var $this = $(this);
			var dealerId = $this.data("dealerid");
			$.post("${ctx}/brand/dealer/collectDealer", {dealerId: dealerId}, function(data){
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "收藏成功");
					$this.removeClass("js-collect").addClass("js-unCollect");
					$this.find("em").html("取消收藏");
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		});
		
		// 取消收藏
		$(document).on('click','.js-unCollect',function(){
			var $this = $(this);
			var dealerId = $this.data("dealerid");
			$.post("${ctx}/brand/dealer/unCollectDealer", {dealerId: dealerId}, function(data){
				if(data.code == zttx.SUCCESS)
				{
					remind("success", "取消收藏成功");
					$this.removeClass("js-unCollect").addClass("js-collect");
					$this.find("em").html("收藏");
				}
				else
				{
					remind("error", data.message);
				}
			}, "json");
		});
	</script>
</body>
</html>