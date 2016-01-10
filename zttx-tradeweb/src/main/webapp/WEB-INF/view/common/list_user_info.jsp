<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<% String[] arr = {"#ff0000","#d603f1","#0082cc","#009944","#996c33"}; %>
    <title>管理中心-交易管理-用户管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
    	#privilege_select_div{
    		border: none;
    		background: none;
    	}
    	.free_shipping{
            color: #0082CC;
            margin-right: 5px;
            vertical-align: bottom;
        }
        .free_shipping:hover{
            color: #ff8800;
        }
   	</style>
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/> 
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">用户管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包屑结束 -->
    <div class="inner">
    <!-- 内容都放这个地方  -->
        <form:form class="ui-form tradorder-form clearfix" id="orderForm">
        	<div class="ui-form-item">
                <label class="">
                    	电话号码：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="userMobile"/>
            </div>
            <div class="ui-form-item">
                <label class="">
                    	角色：
                </label>
                <input type="text" class="ui-input tradorder-text-number" name="roleInfo.roleName"/>
            </div>
            <div class="ui-form-item" style="margin-left:74px;">
                <a class="simple_button tradorder-btn-sousuo" href="javascript:page.goTo(1);">搜索</a>
            </div>
        </form:form>
        <div class="purorder">
            <div class="js_trador_tabs">
            	<div class="js_trador_con"><!-- 交易管理tabs内容 -->
                    <!-- tab 里面的 div 开始 -->
                    <div class="js_trador_conbox">
                        <table class="orderbox-table" style="margin-bottom: 0;">
                            <colgroup>
                                <col width="80" />
                                <col width="50" />
                                <col width="110" />
                                <col width="50" />
                                <col width="120" />
                                <col width="90" />
                                <col width="90" />
                                <col width="90" />
                                <col width="140" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>电话号码</th>
                                    <th>电话验证</th>
                                    <th>电子邮件</th>
                                    <th>邮件验证</th>
                                    <th>用户角色</th>
                                    <th>账号状态</th>
                                    <th>用户名</th>
                                    <th>用户类型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                        </table>
                        <div class="checkallbox clearfix">
                            <div class="checkallsel inline-block">
                                <input type="checkbox" id="checkall"/>
                                <label for="checkall">全选</label>
                            </div>
                            <div class="page-top-down inline-block">
                                <a href="javascript:;" class="page-style page-top">上一页</a>
                                <a href="javascript:;" class="page-style page-down">下一页</a>
                            </div>
                        </div>
                        <div class="orderbox_contain" >
                        </div>
                        <div class="mt10" id="pagination"></div>
                    </div>
                    <!-- 结束 -->
                </div>
            </div><!-- tabs结束 -->
            
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div id="popup"></div>
<script id="feedback-templage" type="text/html">
{{each rows}}
	<div class="orderbox">
		<table class="orderbox-table">
			<colgroup>
				<col width="80" />
                <col width="50" />
                <col width="110" />
                <col width="50" />
                <col width="120" />
                <col width="90" />
                <col width="90" />
                <col width="90" />
                <col width="140" />
			</colgroup>
			<tbody>
			<tr>
				<td class="orderbox-td-tc">{{$value.userMobile}}</td>
				{{if $value.mobileVerify==0}}
					<td class="orderbox-td-tc">没通过验证</td>
				{{else}}
					<td class="orderbox-td-tc">通过验证</td>
				{{/if}}
				<td class="orderbox-td-tc">{{$value.userMail}}</td>
				{{if $value.mailVerify==0}}
					<td class="orderbox-td-tc">没通过验证</td>
				{{else}}
					<td class="orderbox-td-tc">通过验证</td>
				{{/if}}
				{{if $value.roleInfo!=null}}
					<td class="orderbox-td-tc">{{$value.roleInfo.roleName}}</td>
				{{else}}
					<td class="orderbox-td-tc"></td>
				{{/if}}
				{{if $value.userState==0}}
					<td class="orderbox-td-tc">等待审核</td>
				{{else if $value.userState==1}}
					<td class="orderbox-td-tc">审核通过</td>
				{{else if $value.userState==2}}
					<td class="orderbox-td-tc">冻结</td>
				{{else }}
					<td class="orderbox-td-tc">:审核不通过</td>
				{{/if}}
				<td class="orderbox-td-tc">{{$value.userName}}</td>
				{{if $value.userType==1}}
					<td class="orderbox-td-tc">终端商</td>
				{{else}}
					<td class="orderbox-td-tc">品牌商</td>
				{{/if}}
				
				<td class="orderbox-td-tc"></td>
			</tr>
			</tbody>
		</table>
	</div>
{{/each}}
{{ if rows.length == 0 }}
    <div class="ta-c mt15">暂无数据</div>
{{ /if }}
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${res}/scripts/brand/brand_trade.js"></script>
<!-- 鼠标移入备注时的有数据的模板 -->
<script type="text/html" id="showRemark">
    <div id="remark_pop_{{id}}" class="remark_pop">
        <div class="hd">
            <label for="">
                <em style="color: {{color}}">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
               {{$trimLongString(text,50)}}
            </div>
            <div class="operate">
                <a data-id="{{id}}" data-colorindex="{{colorIndex}}" data-text="{{text}}" href="javascript:;" class="simple_button update_btn">修改</a>
            </div>
        </div>
    </div>
</script>
<!-- 鼠标点击备注时的模板 -->
<script type="text/html" id="dialogRemark">
    <div class="ui-head">
        <h3>备注</h3>
    </div>
    <div id="remark_dialog_{{id}}" class="remark_dialog">
        <div class="hd" id="levelMark">
			<label for="">
                <input name="leven" value="1" type="radio" {{ if colorIndex == 1 }} checked {{/if}}  /><em style="color: #ff0000">★</em>
            </label>
            <label for="">
                <input name="leven" value="2" type="radio" {{ if colorIndex == 2 }} checked {{/if}}   /><em style="color: #d603f1">★</em>
            </label>
            <label for="">
                <input name="leven" value="3" type="radio" {{ if colorIndex == 3 }} checked {{/if}}  /><em style="color: #0082cc">★</em>
            </label>
            <label for="">
                <input name="leven" value="4" type="radio" {{ if colorIndex == 4 }} checked {{/if}}  /><em style="color: #009944">★</em>
            </label>
            <label for="">
                <input name="leven" value="5" type="radio" {{ if colorIndex == 5 }} checked {{/if}}  /><em style="color: #996c33">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
                <textarea id="remark">{{text}}</textarea>
            </div>
            <div class="operate">
                <button class="confirm_btn simple_button" type="button">确定</button>
                <button class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
</script>

<!-- 延长收货期限的模板 -->
<script type="text/html" id="extension_tpl">
    <div class="ui-head">
        <h3>延长收货期限</h3>
	</div>
	<div class="qixian_text mt15 ta-c"></div>
	 <form:form action="" id="outActTime">
<input type="hidden" name="refrenceId" value="" />
    <div class="confirm_bd extension_dialog">
        <div class="ta-c mt20">
            延长：
            <div class="inline-block ta-l">
                <select class="js_select extension_select" name="actTime">
                    <option value="3">3天</option>
                    <option value="5">5天</option>
                    <option value="10">10天</option>
                    <option value="15">15天</option>
                </select>
            </div>
        </div>
        <div class="operate">
            <a class="confirm_btn" href="javascript:;">确定</a>
            <a class="cancel_btn" href="javascript:;">取消</a>
        </div>
</form:form>
    </div>
</script>
<!-- 修改金额 -->
<script type="text/html" id="update_cash_tpl">
    <div class="ui-head">
        <h3>{{title}}</h3>
    </div>
      <form:form action="#" id="updatePrice">
		<input type="hidden" name="refrenceId" value="" />
        <input type="hidden" name="brandsId" value=""/>
        <table class="common_table">
            <colgroup>
                <col width="96" />
                <col width="246" />
                <col width="72" />
                <col width="92" />
                <col width="76" />
                <col width="120" />
                <col width="120" />
            </colgroup>
            <tr>
                <th>品牌</th>
                <th>产品</th>
                <th>数量(件）</th>
                <th>货款（元）</th>
                <th>折扣</th>
				 {{ if type == "price" }}
                <th>
					<div class="inline-block">
						<select style="width: 50px;height: 20px;" id="privilege_select" name="privilege_select">
							<option  value="0" {{if oldAdjustPrice<0}}selected{{/if}}>优惠</option>
							<option value="1" {{if oldAdjustPrice>0}}selected{{/if}}>加价</option>
						</select>
					</div>	
				</th>
				{{ /if }}
				{{ if type == "fare" }}
				<th>
					{{if oldAdjustPrice<=0}}优惠{{else}}加价{{/if}}
				</th>
				{{ /if }}
                <th class="last">物流费</th>
            </tr>
            <tr>
                <td>{{brand}}</td>
                <td>{{info}}</td>
                <td>{{num}}</td>
                <td class="yprice">{{$formatNumber price}}</td>
                {{ if type == "price" }}
                <td>
                    <input {{if oldAdjustPrice>0}} readonly="readonly" {{/if}} class="discount" data-min="1" data-max="10" name="discount" style="width: 36px;" type="text" value="" />
                    折
                </td>
                <td>
                    <input class="nprice" style="width: 70px;" type="text" value="{{if adjustPrice>0}}{{adjustPrice}}{{/if}}" name="adjustPrice" />
                    元
                </td>
                {{ /if }}
                {{ if type == "fare" }}
                <td>
                   {{discount}}
				<input class="discount" name="discount" type="hidden" value="{{discount}}" />
                    {{if discount!=null && discount!=""}}折{{/if}}
                </td>
                <td>
                   {{adjustPrice}}
				 <input class="nprice" type="hidden" value="{{adjustPrice}}" name="adjustPrice" />
                    元
                </td>
                {{ /if }}
                <td class="last">
                    <input class="wuliuprice" style="width: 70px;"  type="text" value='{{if oldAdjustFreight !=null &&  oldAdjustFreight!="" && oldAdjustFreight>=0}}{{adjustFreight}}{{/if}}'  name="adjustFreight"/>
                    元
                </td>
            </tr>
        </table>
        <div class="cash_info">
     
            <div class="item">
                <label for="">收货地址：</label>
                <span title="{{address}}" class="fn-text-overflow" style="display: inline-block; vertical-align: -5px; width: 420px;">{{address}}</span>
            </div>
            <div class="item">
                <label for="">实付款：</label>
                <strong class="price">{{price}}</strong><span class="price_explain">（货款）</span><span class="symbol">{{if oldAdjustPrice>0}}+{{else}}-{{/if}}</span><strong class="price prefer_price total_discount">{{adjustPrice || 0.00 }}</strong>
				<span class="price_explain">（<span class="symbol_text">{{if oldAdjustPrice>0}}加价{{else}}优惠{{/if}}</span>）</span>+<strong class="price total_wuliu">{{adjustFreight || 0.00 }}</strong><span class="price_explain">（物流费）</span>=
                <strong class="price total_price">{{totalprice}}</strong>元
                <a class="free_shipping" href="javascript:;">免运费</a>
            </div>
            <div class="operate">
                <button class="simple_button confirm" type="button" id="updatePriceBtn">确定</button>
                <button class="simple_button ml5 cancel" type="button">取消</button>
            </div>
        </div>
    </form:form>
    
</script>
<!-- 关闭订单 -->
<script type="text/html" id="closePanel_tpl">
    <div class="clearfix closePanel">
        <div class="ui-head">
            <h3>我要关闭交易</h3>
        </div>
        <form:form class="ui-form"  id="closeOrder">
			<input type="hidden" name="orderId" value="" />
			<input type="hidden" name="brandsId" value="" />
			<input type="hidden" name="brandsName" value="" />
            <fieldset>
                <legend>我要关闭交易</legend>
                <div class="ui-form-item mt15">
                    <label for="" class="ui-label">申请关闭交易的原因：</label>
                    <div class="inline-block">
                        <select class="ui-select reason-select" name="code">
                            <option value="4">长时间联系不到终端商</option>
							<option value="5">终端商不想买了</option>
							<option value="6">没有货了，交易无法完成</option>
							<option value="3">其他原因</option>
                        </select>
                    </div>
                </div>
                <div class="ta-c">
                    <button type="button" class="simple_button confirm_btn" id="closeOrderBtn">提&nbsp;交</button>
                    <button type="button" class="simple_button ml5 cancel_btn">取消</button>
                </div>
            </fieldset>
        </form:form>
    </div>
</script>
<!-- 发站内信 -->
<script type="text/html" id="message_tpl">
    <div class="message_dialog">
        <div class="ui-head">
            <h3>发站内信</h3>
        </div>
        <form:form class="ui-form message-form" action="#">
            <div class="ui-form-item mt15">
                <textarea placeholder="请输入内容..." class="ui-textarea" id="message_text"></textarea>
            </div>
            <div class="ta-c">
                <button type="button" class="simple_button confirm_btn">提&nbsp;交</button>
                <button type="button" class="simple_button ml5 cancel_btn">取消</button>
            </div>
        </form:form>
    </div>
</script>



<script>
   var page;
   var colorArr = ["#ff0000","#d603f1","#0082cc","#009944","#996c33"];

   function mouseRemarks(){
   	   seajs.use(["popup"],function(Popup){
   			$(".remarks").each(function(){
           var id = $(this).data("id");
           var color = $(this).data("color");
           if(color == ""){
               return;
           }
           new Popup({
               trigger: $(this),
               element: '#popup',
               align: {
                   selfXY: ["right", 0],
                   baseXY: [63, 35]
               }
           }).before('show',function(){
           
           	 var current = this.activeTrigger;
          	     var color = current.data("color");
          	     var text = current.data("text");
          	     var colorIndex = current.data("colorindex");
          	     var data = {
	           	 	id: id,
	           	 	color: color,
	           	 	text: text,
	           	 	colorIndex: colorIndex
	           	 }
                 template.helper('$trimLongString', function (string,num) {
                      return trimLongString(string,num);
                 });
           	 	var html = template.render('showRemark', data);
                this.element.html(html);
           });
           
       	 })	   		
   	   })
   }
   
  $(function () {
  
    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
    	template.helper('$formatDate', function (millsec) {
           	return moment(millsec).format("YYYY-MM-DD HH:mm");
       	});
       	template.helper('$hanleColor',function(colorIndex){
       		return colorArr[colorIndex-1];
       	});
        template.helper('$trimLongString', function (string,num) {
            return trimLongString(string,num);
        });
		template.helper('$formatMoney', function (num) {
    		num = num == null ? 0 : num;
            return num.toFixed(2);
        });
        page = new Pagination({
             url: "${ctx}/common/userInfo/data",
             elem: "#pagination",
             form:$("#orderForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".orderbox_contain").html(html);
                 if(json.rows.length == 0){
                     $(".checkallbox").hide();
                 }
                 mouseRemarks();
             }
         });

        $(".page-top").on("click",function(ev){
            ev.preventDefault();
            page.goPrev();
        });

        $(".page-down").on("click",function(ev){
            ev.preventDefault();
            page.goNext();
        });
        
        $("#nosee-order").on("click",function(){
        	if($(this)[0].checked){
        		$("#isShowClose").val("1");
        	}else{
        		$("#isShowClose").val("");
        	}
        	page.render();
        });
       });
     });
     
     
     TradePurorder.confirmUpdatePop = function(orderID,type,dailog,discount,payState,brandsId){
      	if( discount > 10){
     		remind("error","折扣率不正确, 请重新输入");
     		return ;
     	} 
        if(type == 'price'){			//修改金额
			$('#updatePrice input[name="refrenceId"]').val(orderID);
			$('#updatePrice input[name="brandsId"]').val(brandsId);
			      $.ajax({
					type:"post",
					url:"${ctx}/brand/order/modPrice",
					data : $('#updatePrice').serialize(),
					traditional:true,
					dataType: "json",
					success:function(json){
						if(json.code == zttx.SUCCESS){
							remind("success","修改成功！");
							dailog.hide();
							page.render();
						}else{
							remind("error",json.message);
						}
					}
				});
        }else if(type == 'fare'){			//修改运费
            var _val = $(".wuliuprice").val();
            if(_val == "" ){
            	remind("error","请输入物流费");
                return false;
            }
            if(_val == 0 && payState==2){
                confirmDialog("终端商已全部付款，将运费设为0，终端商将无需支付运费，是否继续？",function(){
                	TradePurorder.updatePop(orderID, dailog);
                });
            }
            else
            	TradePurorder.updatePop(orderID, dailog);
        	}
       };
       
       TradePurorder.updatePop = function(orderID,dialog,brandsId){
       		$('#updatePrice input[name="refrenceId"]').val(orderID);
       		$('#updatePrice input[name="brandsId"]').val(brandsId)
            $.ajax({
                type:"post",
                url:"${ctx}/brand/order/modFare",
                data : $('#updatePrice').serialize(),
                traditional:true,
                dataType: "json",
                success:function(json){
                    if(json.code == zttx.SUCCESS){
                        remind("success","修改成功！");
                        dialog.hide();
                        page.render();
                    }else{
                        remind("error",json.message);
                    }
                }
            });
       };
       
      //关闭订单  
     TradePurorder.closeTradeFn = function(orderId,dialog,brandsId,brandsName){
     	$('#closeOrder input[name="orderId"]').val(orderId);
     	$('#closeOrder input[name="brandsId"]').val(brandsId);
     	$('#closeOrder input[name="brandsName"]').val(brandsName);
     	  $.ajax({
				type:"post",
				url:"${ctx}/brand/order/closeOrder",
				data : $('#closeOrder').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			}); 
		}	
       
         //站内信
         TradePurorder.sendMessageFn = function (dealerId, brandName, dialog ){
         	var title = "来自"+brandName+"信息";
          	var content = $.trim($('#message_text').val());
          	if(content == ''){
          		alert("内容不能为空");
          		return ;
          	}
          	if($.trim(dealerId) == ''){
          		return ;
          	}
       		  $.ajax({
				type:"post",
				url:"${ctx}/brand/message/sendDealer",
				data :{dealerIds : dealerId, title : title, content : content},
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","发送成功！");
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			}); 
       }  
        
          //延长收货期限
        TradePurorder.extensionTimeFn = function (orderId, dialog){
        	$('#outActTime input[name="refrenceId"]').val(orderId);
       		 $.ajax({
				type:"post",
				url:"${ctx}/brand/order/outActTime",
				data : $('#outActTime').serialize(),
				traditional:true,
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","修改成功！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			}); 
       } 
      
      //备注
       TradePurorder.handleRemarkFn = function (current_obj,orderIds,dialog){
       		var levelMark = $.trim($('#levelMark input[name="leven"]:checked').val());
       		
       		if(levelMark < 1 || levelMark > 5){
       			alert("没有该星级");
       			return ;
       		}
       		var brandRemark = $.trim($('#remark').val());
       		if(brandRemark.length == ''){
       			alert("备注的内容不能为空!");
       			return ;
       		}
       		
       	
       		
       		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/remark",
				data : {orderIds : orderIds,levelMark : levelMark, brandRemark : brandRemark },
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","备注成功！");
						
						
						if(!current_obj){
							
							dialog.hide();
							
							page.render();
							
							return;
						}
						
						var star = current_obj.find(".red-star");
						
						star.css({
							color: colorArr[levelMark-1]
						})
						star.show();
						current_obj.data({
							"color": colorArr[levelMark-1],
							"text": brandRemark,
							"colorindex": levelMark
						})
						mouseRemarks();
						dialog.hide();
					}else{
						remind("error",json.message);
					}
				}
			});  
       }
     
     //批量免邮
     TradePurorder.handleShippingFn = function (orderIds,dialog){
     		$.ajax({
				type:"post",
				url:"${ctx}/brand/order/avoidMail",
				data : {orderIds : orderIds},
				dataType: "json",
				success:function(json){
					if(json.code == zttx.SUCCESS){
						remind("success","操作完成！");
						dialog.hide();
						page.render();
					}else{
						remind("error",json.message);
					}
				}
			});  
     }
     
     TradePurorder.sendGoods = function(orderId, refundStatus){
     
     	if(1==refundStatus || 2==refundStatus || 3==refundStatus)
     	{
	     	confirmDialog("退款申请中，确认继续发货吗？",function(){
	            window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
	        });
        }else if(4==refundStatus){
            confirmDialog("已拒绝退款，确认继续发货吗？",function(){
                window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
            });
        }else{
        	window.location.href="${ctx}/brand/order/sendGoods/"+orderId;
        }
     }
     
     TradePurorder.init();
     
	$('#checkall').on('click', function (){
        var checked = $(this).prop("checked");
        $('.orderbox input[type="checkbox"]').each(function(){
            $(this).prop("checked",checked);
        })
	});

   $('.orderbox_contain').on("click",'input[type="checkbox"]',function(){

       if(!$(this).prop("checked")){
           $('#checkall').prop("checked",false);
       }

   })
	
</script>
</body>
</html>