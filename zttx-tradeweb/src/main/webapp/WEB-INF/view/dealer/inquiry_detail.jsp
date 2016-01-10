<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>询价单详情</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/inquiry.css" rel="stylesheet" />
</head>
<body>
<div class="container">
<jsp:include page="${ctx}/dealer/mainmenu"/>
<div class="em100">
<div class="main clearfix pr">
<!--侧边导航-->
	<jsp:include page="${ctx}/dealer/dealermenu"/>
            <div class="main-right">
            <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a>
                        >> <a href="${ctx}/dealer/inquiry" title="">我的询价单</a> > 询价单详情
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner" style="padding-left:10px;padding-right: 10px;">
                    <!-- 第一步 (0:申请中，1：同意加盟，2：拒绝加盟，3：已放弃)-->
                    <c:choose>
                        <c:when test="${queryPrice.state==0}">
                            <%--<ol><li>发送询价</li><li  class="current"><span>等待确定</span></li><li><span>同意加盟</span></li><li class="last"><span>下单</span></li></ol>--%>
                            <div class="v2-steps">
                                <span class="text1">发送询价</span>
                                <span class="text2 current">等待确定</span>
                                <span class="text3">同意加盟</span>
                                <span class="text4">下单</span>
                                <div class="steps v2-steps-2"></div>
                            </div>
                        </c:when>
                        <c:when test="${queryPrice.state==1}">
                            <%--<ol><li>发送询价</li><li><span>等待确定</span></li><li class="current"><span>同意加盟</span></li><li class="last"><span>下单</span></li></ol>--%>
                            <div class="v2-steps">
                                <span class="text1">发送询价</span>
                                <span class="text2">等待确定</span>
                                <span class="text3 current">同意加盟</span>
                                <span class="text4">下单</span>
                                <div class="steps v2-steps-3"></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%--<ol><li  class="current">发送询价</li><li><span>等待确定</span></li><li><span>同意加盟</span></li><li class="last"><span>下单</span></li></ol>--%>
                            <div class="v2-steps">
                                <span class="text1 current">发送询价</span>
                                <span class="text2">等待确定</span>
                                <span class="text3">同意加盟</span>
                                <span class="text4">下单</span>
                                <div class="steps v2-steps-1"></div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                <div class="main-grid mb10">
                    <div class="ui-box">
                        <div class="ui-box-head"> <%--${order.isAdvancePayment?'bgc-dy':'bgc-dh'} "--%>
                            <div class="ui-box-head-title">
                                <h3>询价状态</h3>
                            </div>
                        </div>
                        <div class="ui-box-container">
                            <div class="ui-box-content clearfix">
                                <ul class="state-list">
                                	<c:forEach var="queryPriceLog" items="${queryPriceLogs}">
                                    	<li><span class="state-col">${fns:getTimeFromLong(queryPriceLog.createTime,"yyyy-MM-dd HH:mm:ss")} </span><span class="state-col">${queryPriceLog.queryText}</span></li>
                                  	</c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main-grid mb10">
                    <div class="ui-box">
                        <div class="ui-box-head"> <%--${order.isAdvancePayment?'bgc-dy':'bgc-dh'} "--%>
                            <div class="ui-box-head-title">
                                <h3>询价品牌及产品</h3>
                            </div>
                            <div class="ui-box-head-more">
                           		<%--
                                	<a class="progress c-o"><i class="iconfont">&#xe601;</i> 联系客服</a>
                                --%> 
                            </div>
                        </div>
                        <div class="ui-box-container">
                            <div class="ui-box-content clearfix pr">
                                <ul class="state-list">  
                                	<c:set value="${fns:getBrandsIdSubDomain(queryPrice.brandsId)}" var="domain"></c:set> 
                                    <li><span class="state-col-sm">品牌：<a class="link" href="http://${domain}${zttx}">${queryPrice.brandesInfo.brandName} </a></span><span class="state-col-sm">所在地： <c:if test="${defBrandAddress!=null }"> ${defBrandAddress }</c:if> <c:if test="${defBrandAddress==null }"> ${queryPrice.brandInfo.provinceName}&nbsp;${queryPrice.brandInfo.cityName}</c:if> </span><span class="state-col-sm">采购量：${queryPrice.purchaseNum}件</span></li>
                                    <li><span class="state-col-lg" style="word-break: break-all;">采购说明：${queryPrice.purchaseMark}</span></li>
                                </ul>
                                <c:if test="${queryPrice.state==1&&(queryPrice.queryState==1||queryPrice.productAgio!=null)}">
                                	<button class="ui-button ui-button-lred btn-addOrder" onclick="inquiry.joininShopper('${queryPrice.productId}','${queryPrice.purchaseNum}','${queryPrice.refrenceId}')">加入进货单</button>
                                </c:if>
                                
                            </div>
                        </div>
                    </div>
                    <style>
                        .ui-table td{vertical-align: top;}
                    </style>
                    <table class="ui-table" style="margin-top:-1px;">
                        <thead>
                            <tr>
                                <th width="200" class="ta-c">商品信息</th>
                                <th width="400" class="ta-c">颜色/尺码</th>
                                <th width="200">吊牌价</th>
                                <th width="200">报价</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td rowspan="${fn:length(queryPrice.productBaseInfo.productSkus)+1}" class="ta-c va-m" style="vertical-align: middle;">
                                    <div class="js-img-center" style="width:100px;height:100px;border: 1px solid #ddd;">
                                        <img src="${res}${queryPrice.productBaseInfo.domainName}${fns:getImageDomainPath(queryPrice.productBaseInfo.productImage,220, 220)}" title="${product.productTitle}" style="max-width: 100px;max-height: 100px;"/>
                                    </div>
                                    <p class="mt5"><a class="link" href="${ctx}/market/product/${queryPrice.productId}">${queryPrice.productBaseInfo.productTitle}</a></p>
                                    <p class="mt5">货号:${queryPrice.productBaseInfo.productNo}</p>
                                </td>
                            </tr>
                             <c:forEach items="${queryPrice.productBaseInfo.productSkus}" var="group" varStatus="status">
		                        <tr>
		                         <td style="vertical-align: middle;">
		                                ${group.productSkuName}
		                         </td>
                                <td colspan="2" style="padding:0;">
                                    <table class="myTable">
                                        <colgroup>
                                            <col width="300"/>
                                            <col width="300"/>
                                        </colgroup>
                                        <tbody>
		                        						<tr>
		                                                <td>
		                                                <fmt:formatNumber value="${queryPrice.productBaseInfo.productPrice}" type="currency" pattern="0.00" />
		                                                </td>
		                                                <td>
		                                                	<c:if test="${queryPrice.productAgio==null}">
		                                                		 <fmt:formatNumber value="${queryPrice.purchasePrice}" type="currency" pattern="0.00" />
		                                                	</c:if>
		                                                	<c:if test="${queryPrice.productAgio!=null}">
		                                                		 <fmt:formatNumber value="${p.p*queryPrice.productAgio}" type="currency" pattern="0.00" />
		                                                	</c:if>
		                                                </td>
		                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>	
                            
                            </c:forEach>
                            
                        </tbody>
                    </table>
                </div>

                <div class="main-grid mb10">
                    <div class="ui-box">
                        <div class="ui-box-head"> <%--${order.isAdvancePayment?'bgc-dy':'bgc-dh'} "--%>
                            <div class="ui-box-head-title">
                                <h3>询价人资料</h3>
                            </div>
                        </div>
                        <div class="ui-box-container">
                            <div class="ui-box-content clearfix">
                                <ul class="state-list">
                                    <li><span class="state-col">姓名：${queryPrice.dealerInfo.dealerUser} </span><span class="state-col">手机：${queryPrice.dealerUserm.userMobile}</span><span class="state-col">电话：${queryPrice.dealerInfo.dealerTel} </span></li>
                                    <li><span class="state-col-lg">地址：${queryPrice.dealerInfo.provinceName}&nbsp;${queryPrice.dealerInfo.cityName}&nbsp;${queryPrice.dealerInfo.areaName}&nbsp;${queryPrice.dealerInfo.dealerAddress}</span></li>
                                </ul>
                                
                            </div>
                        </div>
                    </div>
                    <div class="ta-r mt10">
                    	<c:if test="${queryPrice.state==0}">  <!-- ||(queryPrice.purchasePrice==null&&queryPrice.state!=3 -->
                    		<button class="ui-button ui-button-lwhite" data-closeid="">放弃询价</button>
                    	</c:if>
                    	<c:if test="${queryPrice.state==3}">
                    		<span>已放弃询价</span>
                    	</c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
</div>

<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script>

    var inquiry  = {
        init:function(){
            this.initDialog();
            this.initTable();
        },
        initDialog:function(){
            $('[data-closeid]').click(function(){
                var me = this;
                confirmDialog("放弃后,客服人员将不再为您跟踪此询价单。<br>您确定放弃本次询价吗？",function(){
                    var id = $(me).data("closeid");
                    $.post('${ctx}/dealer/inquiry/giveup?refrenceId=${queryPrice.refrenceId}',function(data){
                    	if(data.code==126000){
                    		$(me).replaceWith('<span>已放弃询价</span>');
                    		 //$(me).parents(".order-list-item").slideUp(300,function(){
                             //    $(me).remove();
                             //});
                    	}else{
                    		 remind("！抱歉，操作失败了，请联系客服人员，谢谢");
                    	}
                    },'json');
                });
            });
        },
        initTable:function(){

            $(".myTable").each(function(i,o){
                var html = $('<div class="myTable-on ta-c">展开 <i class="icon i-sandown"></i></div>').click(function(){
                    $(".myTable-on").not($(this).hide()).show();
                    $(".myTable").not($(o).fadeIn(300)).hide();
                });
                $(this).before(html).hide();
            });
            $(".myTable-on").first().trigger("click");
        }
    };
    inquiry.init();
    
    /**加入购物车*/
    inquiry.joininShopper=function(productId,number,qid){
    	$.post("${ctx}/dealer/shoper/purchase/"+productId+"/"+number+"_"+qid,function(data){
	   		if(data.code==126000){
	   			remind("success", "此产品已成功加入进货单");
	   			g_pagination.goTo(1);
	   		}else if(data.code==111048){
	   			seajs.use(["dialog"],function(Dialog){
        	           var sc =  new Dialog({content:$("#serviceCost"), width:320}).show();
        	            $("#serviceCost .js-cancel").click(function(){ sc.hide(); });
        	        });
		   	}else if(data.code==111018){
		   		remind("error","产品未授权,无法加入进货单!"); 
		   	}else{
	   			remind("error",data.message); 
	   		}
	   	},"json");
    };
</script>
</body>
</html>
