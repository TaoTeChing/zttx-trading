<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>


<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商管理-终端商详情</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
    <style>
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
        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" >
            <jsp:param value="0" name="isShow"/>
        </jsp:include>
    </div>
</div>
<div class="inner">
<!-- 内容都放这个地方  -->
<!-- 终端商详情 -->
<div class="agency-teamed-info-contant">
    <div class="teamed-info-title">
        <h1 class="title inline-block">${dealerJoin.dealerName}</h1>
        <span class="account inline-block">账号（${dealerUserm.userMobile}）</span>
        <span class="account inline-block">授权品牌：${brandesInfo.brandsName}</span>
        <a javascript=":;" class="export simple_button js-leave-message">留言站内信</a>
    </div>
    <div class="js_teamedinfo_tabs">
        <div class="js_teamedinfo_menu clearfix">
            <ul>
                <li><a href="${ctx}/brand/join/dealerInfo/${joinId}">基本资料</a></li>
                <li class="selected"><a href="${ctx}/brand/join/dealerLevel/${joinId}">等级授权</a></li>
                <li><a href="${ctx}/brand/join/dealerDeal/${joinId}">进货明细</a></li>
            </ul>
        </div>
        <div class="js_teamedinfo_con">
            <div class="agency-info-recom"><!--等级授权-->
                <dl class="info-dllist clearfix">
                    <dt class="info-dllist-tit">授权等级：</dt>
                    <dd class="info-dllist-des">
                    	${empty brandLevel ? "未设置" : brandLevel.levelName} 
                    	<a href="javascript:void(0);" target="_blank" class="bluefont js_updateLevel">[更改等级]</a>
                    </dd>
                    <!-- 
                    <dt class="info-dllist-tit">授信：</dt>
                    <dd class="info-dllist-des">每笔交易可欠10%的货款 <a href="1.Agency_credit.jsp" target="_blank" class="bluefont">[更改授信额]</a></dd>

                    <dt class="info-dllist-tit">产品线：</dt>
                    <dd class="info-dllist-des">
                    	<c:if test="${empty lineList}">未设置</c:if>
                    	<c:if test="${not empty lineList}">
                    		<c:forEach var="obj" items="${lineList}" varStatus="status">
                    			<input type="hidden" class="js-line-id" value="${obj.refrenceId}" />
                    			<c:if test="${status.index > 0}">、</c:if>${obj.lineName}
                    		</c:forEach>
                    	</c:if>
                    	<a href="javascript:;" class="js_addLine bluefont">[添加产品线]</a>
                    </dd> -->
                    <dt class="info-dllist-tit">共授权产品：</dt>
                    <dd class="info-dllist-des">共 <em class="bluefont">${allProCate}</em> 款产品</dd>
                </dl>
                <div>
                    <div class="infosetlevel-menu">
                        <ul class="inline-float">
                            <li class="current"><c:if test="${not empty proCate}">已进货（${proCate}）款</c:if>
                                <c:if test="${empty proCate}">已进货（0）款</c:if>
                                <i class="arrow" style="display: inline"></i></li>
                            <li class="setlevelml3"><c:if test="${empty freProCate}">常进（0）款</c:if>
                                <c:if test="${not empty freProCate}">常进（${freProCate}）款</c:if><i class="arrow"></i></li>
                            <li class="setlevelml3"><c:if test="${not empty unProCate}">未进货（${unProCate}）款</c:if>
                                <c:if test="${empty unProCate}">未进货（${0}）款</c:if>
                                <i class="arrow"></i></li>
                        </ul>
                    </div>
                    <div class="infosetlevel-con">
                    	<div class="infosetlevel-con-box info-products clearfix">
                            <ul></ul>
                        </div>
                    </div>
                </div>
                <div class="pageParent"></div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
	<div style="display: none;">
    <!--留言-->
    <div class="js-leave-messagebox">
        <div class="ui-head">
            <h3>留言</h3>
        </div>
        <form:form id="msg-form" method="post" action="${ctx}/brand/message/sendDealer">
            <input name="dealerIds" type="hidden" class="js-getvalue" value="${dealerJoin.dealerId}">
            <div class="content">
                <input id="msg-title" name="title" type="text" placeholder="留言" class="ui-input mt10" style="width: 200px"/>
                <textarea id="msg-content" name="content" class="ui-textarea mt10" style="width:335px;height: 78px;" placeholder="请输入留言内容"></textarea>
            </div>
            <div class="ta-c mt10">
                <a href="javascript:;" class="simple_button js-lemsgsure-btn">确认</a>
                <a href="javascript:;" class="simple_button js-lemsgsurecansole-btn">取消</a>
            </div>
        </form:form>
    </div>
    <!--留言结束-->
	</div>
    <!-- 添加产品线 -->
    <div style="display: none;">
        <div id="add_productLine">
            <div class="ui-head">
                <h3>添加产品线</h3>
            </div>
            <table class="list_table">
                <colgroup>
                    <col width="32" />
                    <col width="164" />
                    <col width="190" />
                    <col width="90" />
                    <%--<col width="92" />--%>
                    <col width="104" />
                    <col width="106" />
                    <col width="128" />
                </colgroup>
                <thead>
                    <tr>
                        <th>
                            <input id="checkAll" type="checkbox" />
                        </th>
                        <th>品牌</th>
                        <th>产品线名称</th>
                        <th>折扣率（折）</th>
                        <%--<th>终端商等级</th>--%>
                        <th>已加入产品（款）</th>
                        <th>已有终端商（个）</th>
                        <th class="last">所在地区（个）</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div class="pageParent mt5">
            </div>
            <div class="ta-c mt15">
                <button id="js-line-btn" style="width: 74px;" class="confirm_btn simple_button" type="button">确定</button>
                <button style="width: 74px;" class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
    <!-- 添加产品线结束 -->
    <!-- 更改等级 -->
    <div style="display: none;">
        <div id="update_Level">
            <div class="ui-head">
                <h3>更改等级</h3>
            </div>
            <table class="list_table">
                <colgroup>
                    <col width="32" />
                    <col width="168" />
                    <col width="250" />
                </colgroup>
                <thead>
                    <tr>
                        <th></th>
                        <th>等级</th>
                        <th>描述</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div class="pageParent mt5">
            </div>
            <div class="ta-c mt15">
                <button id="js-level-btn" style="width: 74px;" class="confirm_btn simple_button" type="button">确定</button>
                <button style="width: 74px;" class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
    <!-- 更改等级结束 -->
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/agencymanag.js"></script>
<script id="ajax-templage-pro" class="ajax-templage" _url="${ctx}/brand/join/proCateList/${joinId}" _page=".agency-info-recom .pageParent" _pid=".infosetlevel-con ul" type="text/html">
{{each rows}}
<li>
	<div class="info-pro-imgbox">
		<a href="${ctx}/market/product/{{$value.refrenceId}}">
			<img style="width:180px;height:180px;" src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}"/>
		</a>
	</div>
	<p class="number">
		<strong>货号：{{$value.productNo}}</strong>
	</p>
	<h2 class="title fn-text-overflow">
		<a title="{{$value.productTitle}}" href="${ctx}/market/product/{{$value.refrenceId}}">{{$value.productTitle}}</a>
	</h2>
</li>
{{/each}}
</script>
<script id="ajax-templage-line" class="templage" _url="${ctx}/brand/join/listLine/${joinId}" _page="#add_productLine .pageParent" _pid="#add_productLine tbody" type="text/html">
{{if rows.length == 0}}
<tr>
	<td colspan="7" style="text-align:center;"><a href="${ctx}/brand/line/execute">请先添加产品线</a></td>
</tr>
{{/if}}
{{each rows}}
<tr>
	<td><input type="checkbox" data-lineid="{{$value.id}}" /></td>
	<td>{{$value.brandsName}}</td>
	<td>{{$value.lineName}}</td>
	<td>{{$value.lineAgio}}折</td>
    <%--<td>特级终端商</td>--%>
	<td>
		<strong class="link">{{$value.productsCount}}</strong>
	</td>
	<td>
		<strong class="link">{{$value.dealersCount}}</strong>
	</td>
	<td class="last">
		<strong class="link">{{$value.areaCount}}</strong>
	</td>
</tr>
{{/each}}
</script>
<script id="ajax-templage-level" class="templage" _url="${ctx}/brand/brandLevel/level/list?brandsId=${dealerJoin.brandsId}" _page="#update_Level .pageParent" _pid="#update_Level tbody" type="text/html">
{{if rows.length == 0}}
<tr>
	<td colspan="3" style="text-align:center;"><a href="${ctx}/brand/brandLevel/level">请先添加终端商等级</a></td>
</tr>
{{else}}
<tr>
	<td><input type="radio" name="levelRadio" value="" checked /></td>
	<td>未设置</td>
	<td></td>
</tr>
{{/if}}
{{each rows}}
<tr>
	<td><input type="radio" name="levelRadio" value="{{$value.refrenceId}}" /></td>
	<td>{{$value.levelName}}</td>
	<td>{{$value.levelMark}}</td>
</tr>
{{/each}}
</script>
<script>
agencyteamedinfo.init();

var $msgForm = $('#msg-form');
var $msgContent = $('#msg-content');


//新增产品线
seajs.use(["dialog", "pagination","template"],function(Dialog, Pagination,template){
    
	template.helper('$getImageDomainPathFn',function (url, width, height){
        return getImageDomainPath(url, width, height);
    });
	
	function ajaxTemplage(tempId, callbackFunc)
	{
		var $temp = $("#" + tempId);
    	new Pagination({
            url: $temp.attr("_url"),
            elem: $temp.attr("_page"),
            method: "post",
            handleData: function (data) {
            	var html = template.render(tempId, data);
                $($temp.attr("_pid")).html(html);
                if(callbackFunc){callbackFunc(data);}
            }
        }); 
	}
	
    var add_dialog = new Dialog({
        "trigger": ".js_addLine",
        "content": "#add_productLine",
        "width": 814
        
    }).before("show",function(){
    	ajaxTemplage("ajax-templage-line", function(data){
    		//选中已拥有的产品线
            if($(".js-line-id").length > 0){
            	$(".js-line-id").each(function(){
            		$("#add_productLine [data-lineid='" + $(this).val() + "']").attr("checked", "checked");
            	});
            }
    		
    		if(data.rows.length > 0)
    		{
    			$("#js-line-btn").show();
    		}
    		else
    		{
    			$("#js-line-btn").hide();
    		}
    	});
    })

    
    $(document).on("click","#add_productLine .confirm_btn",function(){
    	var lineIds = [];
    	$("#add_productLine tbody input[type='checkbox']:checked").each(function(){
    		lineIds[lineIds.length] = $(this).data("lineid");
    	});
        $.post("${ctx}/brand/join/saveLine/${joinId}", $.param({lineIds: lineIds}, true), function(data){
        	if(data.code == zttx.SUCCESS)
        	{
        		location.href = location.href;
        	}
        	else
        	{
        		remind("error", data.message);
        	}
        }, "json");
    })

    $(document).on("click","#add_productLine .cancel_btn",function(){
        add_dialog.hide();
    })
    
    
    var update_dialog = new Dialog({
        "trigger": ".js_updateLevel",
        "content": "#update_Level",
        "width": 450
        
    }).before("show",function(){
    	ajaxTemplage("ajax-templage-level", function(data){
    		//选中等级
            $("#update_Level [value='${brandLevel.refrenceId}']").attr("checked", "checked");
    		
    		if(data.rows.length > 0)
    		{
    			$("#js-level-btn").show();
    		}
    		else
    		{
    			$("#js-level-btn").hide();
    		}
    	});
    })

    
    $(document).on("click","#update_Level .confirm_btn",function(){
    	var levelId = $("#update_Level tbody input[type='radio']:checked").val();
    	var dealerId = "${dealerJoin.dealerId}";
    	var brandsId = "${dealerJoin.brandsId}";
    	$.post("${ctx}/brand/brandLevel/level/setDealer", $.param({idAry: [dealerId], levelId: levelId, brandsId: brandsId}, true), function(data){
        	if(data.code == zttx.SUCCESS)
        	{
        		location.href = location.href;
        	}
        	else
        	{
        		remind("error", data.message);
        	}
        }, "json");
    })

    $(document).on("click","#update_Level .cancel_btn",function(){
    	update_dialog.hide();
    })
    
    

})

//全选
$("#checkAll").on("click",function(){
    var checked = $(this).prop("checked");
    $(".list_table tbody input").prop("checked",checked);
})


	function leaMsg(obj, dialog) {//留言弹窗点击确定后的操作可以写在这里
        //点击”确定“后出发的方法
        var content = $.trim($msgContent.val());
        if (content == '') {
            remind('error', '请输入内容!');
            return false;
        }
        var title = $.trim($('#msg-title').val());
        if (title == '') {
        	$('#msg-title').val("留言");
        }
        $.post('${ctx}/brand/message/sendDealer', $msgForm.serialize(), function (data, status, jqXHR) {
            if (data.code === zttx.SUCCESS) {
                remind('success', '留言成功');
                $msgForm[0].reset();
                dialog.hide();
            } else {
                remind('error', data.message);
            }
        }, 'json');
    }

var pageList = {};
$(function(){
	seajs.use(["pagination","template"], function (Pagination,template){
        template.helper('$getImageDomainPathFn',function (url, width, height){
            return getImageDomainPath(url, width, height);
        });
		$(".ajax-templage").each(function(index){
			var $this = $(this);
			var tempId = $this.attr("id");
			if(tempId == null || tempId == ""){
				tempId = "templage" + index;
				$this.attr("id", tempId);
			}
			pageList[tempId] = new Pagination({
	            url: $this.attr("_url"),
	            elem: $this.attr("_page"),
	            method: "post",
	            handleData: function (data) {
	            	var html = template.render(tempId, data);
	                $($this.attr("_pid")).html(html);
	            }
	        });    
		});
	});
});
function selectProCate(index){
	var $this = $(this);
	var page = pageList["ajax-templage-pro"];
	page.data = {type: index};
	page.goTo(1);
}
</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>