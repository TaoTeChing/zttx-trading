<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-采购的订单-退款详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/refund_detail.css"/>
    <style>
        .address_list th {
            background: none repeat scroll 0 0 #E4ECF8;
            color: #666666;
            font-weight: normal;
        }
        .address_list th,.address_list td{
            height: 30px;
        }
        .address_list td{
            height: 40px;
            padding-left: 8px;
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
            <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/order/purorder">采购的订单</a>
            <span class="arrow">&gt;</span>
            <span class="current">退款详情</span>
        </div>
        <div class="fr">
            <%@ include file="common_quick_bar.jsp" %>
        </div>
    </div>
<div class="inner">
<!-- 内容都放这个地方  -->
    <!-- 步骤条 -->
    <div class="v2-steps v2-3steps">
        <span class="text1">申请退款</span>
        <span class="text2 current">品牌商处理退款申请</span>
        <span class="text3">退款完成</span>
        <div class="steps v2-steps-2"></div>
    </div>
    <div class="refund_detail">
        <div class="top clearfix">
            <div class="left">
                <div class="refund_info">
                    <h3>退款信息</h3>
                    <input type="hidden" id="refrenceId" value="${dealerRefund.refrenceId}">
                    <p>
                        <label for="">退款单号：</label><span class="num">${dealerRefund.refundId}</span>
                    </p>
                    <p>
                        <label for="">退款类型：</label>${dealerRefund.needRefund==true ?"退款退货":"仅退款"}
                    </p>
                    <p>
                        <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>元
                    </p>
                </div>
                <div class="order_info">
                    <h3>订单信息</h3>
                    <p>
                        <label for="">订单号：</label>
                         <a href="${ctx}/brand/order/view/${dealerOrder.refrenceId}" target="_blank" class="link">
                                                <span class="num">${dealerOrder.orderId}</span>
                                            </a>
                    </p>
                    <p>
                        <label for="">订货日期：</label>${fns:getStringTime(dealerOrder.createTime,"yyyy-MM-dd") }
                    </p>
                    <p>
                        <label for=""> 订货数量：</label>${dealerOrder.productCount}
                    </p>
                    <p>
                        <label for="">订货金额：</label><strong class="price">${dealerOrder.payment}</strong>元
                    </p>
                    <p>
                        <label for="">物流费用：</label>${dealerOrder.adjustFreight}元
                    </p>
                    <p>
                        <label for="">终端商：</label>${dealerOrder.dealerName}
                    </p>
                </div>
            </div>
            <div class="detail_box">
              <%-- 已要求客服介入,等待客服介入 --%>
                                	<c:if test="${dealerRefund.cusJoin==true}">

                                        <div class="tips">
                                           <c:if test="${dealerRefund.serProStatus==1}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>客服人员正在介入，请耐心等待...
                                           </c:if>
                                           <c:if test="${dealerRefund.serProStatus==2}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理中…
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==3}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷处理完毕
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==4}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>纠纷已关闭
                                           </c:if>
                                            <c:if test="${dealerRefund.serProStatus==5}">
                                            <i class="iconfont c-w mr5">&#xe626;</i>等待客服介入中...
                                           </c:if>
                                        </div>

                                	</c:if>
                                	
									<%-- 申请退款等待处理 --%>
            
                <div class="handle_refund">
                
                 <!--  <h3>纠纷处理中…</h3>
						<c:if test="${dealerRefund.serProStatus==2}">
                        <p class="explain mt10"><strong class="c-r time"  data-endtime="${endTime}"  style=" color:red"></strong>之后系统会将${dealerRefund.refundAmount}元货款打到终端商的账户中</p>
                		</c:if>-->
                
         
                    <h3>终端商请求退款</h3>
                    <p class="explain">您还有 <strong class="c-r time" data-endtime="${endTime}" style=" color:red"></strong> 来处理终端商的退货退款申请</p>
             
                    <div class="detail">
                        <p class="item">
                            <label for="">退款原因：</label>${dealerRefund.refundReason}
                        </p>
                        <p class="item">
                            <label for="">退款金额：</label><strong class="price">${dealerRefund.refundAmount}</strong>
                        </p>
                        <p class="item">
                            <label for="">退款说明：</label>${dealerRefund.refundMark}
                        </p>
                        <div class="item">
                            <label for="">凭证：</label><div class="inline-block">
                            
                            <c:forEach  items="${dealerRefAttachts}" var="dealerRefAttacht">
                		<a href="${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}" 
                		data-lightbox="arraylist_${status.index}">
                  	<img width="100" height="100" 
                  	src="${res}${dealerRefAttacht.domainName}${dealerRefAttacht.attachtName}" />
                 	</a>
               </c:forEach>
                              
                            </div>
                        </div>
                        <c:if test="${not empty defaultBrandAddress}">
	                       <div class="item return_address">
	                       
	                           <label style="vertical-align: middle" for="">退货地址：</label>
	                           <input id="brandRecAddr"  name="brandRecAddr'" style="width: 350px;" class="ui-input" value="${defaultBrandAddress.userName} ${defaultBrandAddress.fullAreaName}${defaultBrandAddress.street}${defaultBrandAddress.userMobile}${empty defaultBrandAddress.userTel?'':'/'}${defaultBrandAddress.userTel}${empty defaultBrandAddress.zipCode?'':'/' }${defaultBrandAddress.zipCode}"disabled />
	                           <a class="js_update link" href="javascript:;">修改</a>
	                           <a style="display: none;" class="js_save link" href="javascript:;">保存</a>
	                           <a style="display: none;" class="js_cancel link" href="javascript:;">取消</a>
	                           <br />
	                          <!--  <label style="vertical-align: middle" for="">联系电话：</label> <input id="phone" style="width: 70px;margin: 5px 0 0 60px;" class="ui-input" value="15854751254" disabled />-->
	                       </div>
	                       <div class="operation">
	                           <a class="ui_button ui_button_morange"  id="agreeRefund">同意</a>
	                           <a class="ui_button ui_button_mgray ml5" href="${ctx}/brand/refund/view/refuseBoth/${dealerRefund.orderNumber}">拒绝</a>
	                       </div>
                        </c:if>
                        <c:if test="${empty defaultBrandAddress}">
                        	<a href="${ctx}/brand/brandAddress/execute?brandsId=${dealerOrder.brandsId}" target="_blank"  style="background: #21AD39; color: #fff; padding: 5px;">请先添加收货地址</a>
                           
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom">
            
            <div class="info">
                
            </div>
            <!-- 操作记录详情    start -->
                <jsp:include page="/WEB-INF/view/dealer/refund_refReply.jsp" />
                <!-- 操作记录详情    end   -->
        </div>
    </div>
</div>
</div>
</div>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<div style="display: none" id="chooseAddress" class="chooseAddress">
    <div class="ui-head">
        <h3>选择收货地址</h3>
    </div>
    <div class="bd" id="resultList">
       <!--   <table class="address_list">
            <colgroup>
                <col width="30">
                <col width="116">
                <col width="105">
                <col width="80">
                <col width="166">
                <col width="190">
            </colgroup>
            <tr>
                <th></th>
                <th>品牌</th>
                <th>仓库</th>
                <th>联系人</th>
                <th>所在地区</th>
                <th>街道地址/邮政编码</th>
                <th>电话/手机</th>
            </tr>
            <tr>
                <td>
                    <input type="radio" id="kk"/>
                </td>
                <td><label for="kk">朵彩</label></td>
                <td>江苏仓库</td>
                <td>吴小莉</td>
                <td>浙江省 宁波市 海曙区</td>
                <td>
                    顾山镇 古塘路开发区85号 上海朵彩电子商务有限公司
                </td>
                <td class="ta-c">13565489956</td>
            </tr>
            <tr>
                <td>
                    <input type="radio"  id="kk"/>
                </td>
                <td>七匹狼男装</td>
                <td>江苏仓库</td>
                <td>吴小莉</td>
                <td>浙江省 宁波市 海曙区</td>
                <td>丽园北路755号 宁波市电子商务产业园18楼</td>
                <td class="ta-c">
                    0574-5698465<br>
                    13565489956
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" />
                </td>
                <td>卓诗尼</td>
                <td>江苏仓库</td>
                <td>吴小莉</td>
                <td>浙江省 宁波市 海曙区</td>
                <td>丽园北路755号 宁波市电子商务产业园18楼</td>
                <td class="ta-c">13565489956</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" />
                </td>
                <td>马克华菲</td>
                <td>江苏仓库</td>
                <td>吴小莉</td>
                <td>浙江省 宁波市 海曙区</td>
                <td>丽园北路755号 宁波市电子商务产业园18楼</td>
                <td class="ta-c">13565489956</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" />
                </td>
                <td>太子龙</td>
                <td>江苏仓库</td>
                <td>吴小莉</td>
                <td>浙江省 宁波市 海曙区</td>
                <td>丽园北路755号 宁波市电子商务产业园18楼</td>
                <td class="ta-c">13565489956</td>
            </tr>
        </table>-->
        
        
    </div>
    	<div class="mt10" id="pagination"></div>  
</div>
<script id="feedback-templage" type="text/html">
	<table class="address_list">
            <colgroup>
                <col width="30">
                <col width="116">
                <col width="105">
                <col width="80">
                <col width="166">
                <col width="190">
            </colgroup>
            <tr>
                <th></th>
                <th>品牌</th>
                <th>仓库</th>
                <th>联系人</th>
                <th>所在地区</th>
                <th>街道地址</th>
                <th>电话/手机</th>
            </tr>
			{{each rows}}
				<tr>
                <td>
                    <input type="radio" name="sure" value="8888"/>
                </td>
                <td>{{$value.brandsName}}</td>
                <td>{{$value.storeName}}</td>
                <td>{{$value.userName}}</td>
                <td>{{$value.fullAreaName}} </td>
                <td>
                    {{$value.street}}
                </td>
                <td class="ta-c">{{$value.userMobile}}  {{$value.userTel}}</td>

            </tr>
			{{/each}}

			 {{ if rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="8">暂无数据</td>
        </tr>
        {{ /if }}
		
                       
                   

</table>
		<div class="ta-c mt15">
            <button type="button" class="simple_button confirm_btn">提&nbsp;交</button>
            <button type="button" class="simple_button ml10 cancel_btn">取消</button>
        </div>
</script>
<script>
$(function() {

			$("#agreeRefund").on('click', function() {
				var url = "${ctx}/brand/refund/agreeReturnBoth1";
				var refrenceId = $("#refrenceId").val();
		
				var brandRecAddr = $("#brandRecAddr").val();
				if($.trim(brandRecAddr)==null||$.trim(brandRecAddr)==""){
					remind("error", "请选择收货地址");
					return;
				}
				
				$.ajax({
					type : "post",
					dataType : 'json',
					url : url,
					data : {
							refrenceId : refrenceId,
							brandRecAddr : brandRecAddr},
					//contentType : "application/json;charset=UTF-8",
					success : function(jsonMessage) {
						if (jsonMessage.code == zttx.SUCCESS) {
							remind("success","提交成功",function(){
								window.location.replace("${ctx}/brand/refund/view/"+${dealerRefund.orderNumber})
							});
						} else {
							remind("error", jsonMessage.message);
						}
					}

				});
			});

		});
		
 var page;
	  $(function () {
	    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format(" YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	var refrenceId = $("#refrenceId").val();
        	page = new Pagination({
              url: "${ctx}/brand/brandAddress/data",
              data : {
							refrenceId : refrenceId},
              elem: "#pagination",
              method:"get",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#resultList").html(html);
                 // $("#tempTable").remove();
                 // $("#pagination").before($(html));   
                 // agencyApply.init();
              }
          });
          };
          renderPagination();
        });
      });
      
      
    var refundDetail_address = {
        init: function(){
            this.updateAddress();
        },
        updateAddress: function(){

            var address_dialog;

            var _this = this;

            seajs.use(["dialog"],function(Dialog){
                address_dialog = new Dialog({
                    "trigger": ".return_address .js_update",
                    "content": "#chooseAddress",
                    "width": 768
                }).after("show",function(){
                    $("#chooseAddress").show();
                })

            });

            $(document).on("click","#chooseAddress .cancel_btn",function(){
                address_dialog.hide();
            })

            $(document).on("click","#chooseAddress .confirm_btn",function(){
                _this.handleAddress(address_dialog);
            })

        },
        handleAddress: function(dialog){
            
            $("#brandRecAddr").val($.trim($("input:checked").parent().parent().children("td").eq(3).text())+" "+$.trim($("input:checked").parent().parent().children("td").eq(4).text())+" "+$.trim($("input:checked").parent().parent().children("td").eq(5).text())+" "+$.trim($("input:checked").parent().parent().children("td").eq(6).text()));
           
            dialog.hide();
        }
    }
    refundDetail_address.init();
	$(".c-r.time").cutDown(function(dhms){
		        return dhms.d + "天"+ dhms.h+"时"+dhms.m+"分"+dhms.s+"秒"
		    });
</script>
</body>
</html>