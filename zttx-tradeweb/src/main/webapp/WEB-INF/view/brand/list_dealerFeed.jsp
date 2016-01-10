<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-服务评价</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
        .main .inner{
            background: #fff;
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
                <span class="current">服务评价</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
	    <div class="inner">
	    <!-- 内容都放这个地方  -->
		<form:form id="searchTermForm">
            <div style=" margin: 10px">
                <label>品牌：</label>
                <div class="inline-block">
                    <select class="js_select" style="width:235px; height: 37px;" name="brandsId" id="setid" onchange="page.goTo(1)">
                    	<option value="">-- 请选择品牌 --</option>
                    	<c:forEach items="${brandesInfoList}" var="info">
                    		<option value="${info.refrenceId}">${info.brandsName}</option>
                    	</c:forEach>
                    </select>
                </div>
                <!-- <label class="ml15">查看状态：</label>
                <div class="inline-block">
                    <select class="js_select" style="width:275px; height: 30px;" name="lookname" id="setid">
                        <option value="">请选择</option>
                        <option value="">已查看</option>
                        <option value="">未查看</option>
                    </select>
                </div> -->
            </div>
		</form:form>
		
            <!-- 面包屑结束 -->
	        <div class="trade_service">
	        	<%-- 内容列表区 --%>
	           	<div id="pagination" class="mt10"></div>
	        </div>
	    </div>
	</div>
</div>
<!-- footer(品牌商管理中心) -->
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />

<!-- 回复模板 -->
<script id="reply_tep" type="text/html">
	<form>
    	<div class="reply_contain">
        	<div class="reply_box">
				<input class="hidden" type="text" name="refrenceId" value=""/>
     	       	<textarea name="replyText"></textarea>
    	        <div class="mt10 ta-r">
    	            <a class="btn js_confirm" href="javascript:;">确定</a>
    	            <a class="btn js_cancel" href="javascript:;">取消</a>
  	          	</div>
   		   	</div>
    	</div>
	</form>
</script>
<script id="reply_contain" type="text/html">
	<div class="reply_contain">
		<span class="reply_text"></span>
		<p class="time"></p>
	</div>
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/plugin/jquery-dateFormat.min.js"></script>
<script id="feedback-templage" type="text/html">
<div class="messages_list" id="listContent">
<form name="dealerFeetForm">
	<div class="th_row">
        <span style="margin-left: 15px;width: 175px;" class="column_1 column">品牌</span>
        <span class="column_2 column">产品终端商反馈内容</span>
        <span class="column_3 column">来源</span>
        <span class="column_4 column">反馈时间</span>
        <span class="column_5 column">回复</span>
	</div>

	{{each rows as dealerFeed index}}
		<div class="td_row {{(index+1)%2!=0 ? '':'td_odd_row'}}">
            <div class="column_1 column"><p class="content">{{dealerFeed.brandsName}}</p></div>
		    <div class="column_2 column" title="{{dealerFeed.feedText}}">
				<div style="margin-right: 20px;">
			    	<p>{{$trimLongString(dealerFeed.feedText, 77, null)}}</p>
				</div>
			</div>
			<div class="column_3 column">{{dealerFeed.dealerName}}</div>
            <div class="column_4 column">
                <p>{{$formatDate dealerFeed.createTime}}</p>
            </div>
			<div class="column_5 column">
				<input class="hidden" type="text" value="{{dealerFeed.refrenceId}}"/>
				{{if dealerFeed.replyText==null}}
				<a class="js_replay ui_button ui_button_morange" href="javascript:;">回&nbsp;复</a>
				{{/if}}
			</div>
			{{if dealerFeed.replyText!=null}}
				<div class="reply_contain">
					<span class="reply_text">回复：{{dealerFeed.replyText}}</span>
					<p class="time">{{$formatDate dealerFeed.replyTime}}</p>
			    </div>
			{{/if}}
		</div>
	{{/each}}
    {{ if rows.length == 0 }}
    <div class="td_row">
        <div style="float: none;display: block;" class="column ta-c">暂无数据</div>
    </div>
    {{ /if }}

</form>
</div>
</script>
<c:set var="SUCCESS" value="<%=com.zttx.web.consts.BrandConst.SUCCESS.code%>" />
<script>
	/** ajax分页 */
	var page;

	$(function () {
		seajs.use(["pagination","template"], function (Pagination,template) {

            template.helper('$formatDate', function (millsec) {
                return $.format.date(new Date(millsec), 'yyyy-MM-dd');
            });
            
            template.helper('$trimLongString', trimLongString);

			var renderPagination = function(){
				page = new Pagination({
					/************** 需要修改 *************/
					url: "${ctx}/dealer/dealerFeed/brand/ajaxList",
					elem: "#pagination",
					form:$("#searchTermForm"),
					method:"post",
					handleData: function (json) {
						var html = template.render("feedback-templage", json);
						$("#listContent").remove();
						$("#pagination").before($(html));
					}
				});
			};
			renderPagination();
		});
		

	}); 

    var trade_service = {
        init: function(){
            this.reply();
         },
        reply: function(){

            $(document).on("click",".js_replay",function(){

                var op = $(this).parents(".td_row");
                var reply_box = op.find(".reply_contain");
                var tpl = $("#reply_tep").html();
                if(reply_box.length != 0){
                
                    reply_box.show();
                  
               
                }else{
                    $(tpl).appendTo(op);
                    op.children("form").children().children().children("input:hidden").val($(this).prev().val());
                    op.find(".js_confirm").on("click", function()
                    {
                        var formModel = $(this).parent().parent().parent().parent();
                        var jsonStrData = formModel.serialize();
                        var reply_box = $(formModel).parents(".td_row").find(".js_replay");
                        $.ajax({
                            type : "POST",
                            url : "${ctx}/brand/feed/reply",
                            data : jsonStrData,
                            dataType: "json",
                            success : function(json) {
                                if (json.code==${SUCCESS}) {
                                    var replyContain = $("#reply_contain").html();
                                    formModel.before(replyContain);
                                    formModel.prev().children(".reply_text").html("回复：" + json.object.replyText);
                                    formModel.prev().children(".time").html(json.object.replyTime);
                                    formModel.remove();
                                    reply_box.remove();
                                } else {
                                    alert(json.message);
                                    $(".js_cancel").click();
                                }
                            },
                            error:function(XMLHttpRequest, textStatus, errorThrown){
                                alert(errorThrown);
                            }
                        });
                    })
                }

            })

            $(document).on("click",".js_cancel",function(){
                $(this).parents(".reply_contain").hide();
            })
        }
    }

    trade_service.init();
</script>
</body>
</html>