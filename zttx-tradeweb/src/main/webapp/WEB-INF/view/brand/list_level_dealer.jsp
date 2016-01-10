<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商管理-设置等级</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/agencymanag.css"/>
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
		            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
		            <span class="arrow">&gt;</span>
		            <span class="current">${title}</span>
        </div>
             <div class="fr">
		            <c:choose>
		                <c:when test="${empty info.refrenceId}">
		                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
		                </c:when>
		                <c:otherwise>
		                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
		                        <jsp:param value="0" name="isShow"/>
		                    </jsp:include>
		                </c:otherwise>
		            </c:choose>
      		  </div>
    </div>
    
    
    <div class="inner">
        <div class="agency-grademanage-contant">
            <div class="js_grademanage_menu clearfix">
                <ul class="clearfix">
                    <li><a href="${ctx}/brand/brandLevel/level" style="display: block">等级管理</a></li>
                    <li class="selected"><a href="${ctx}/brand/level/setup" style="display: block">设置等级</a></li>
                </ul>
            </div>
            <div class="js_grademanage_con">
                <div class="tips">
                    <i class="v2-icon-explain"></i>
                    说明：当您在设置产品线时，可以通过等级对不同的终端商设置不同的优惠力度。
                </div>
                <div>
                	<form:form action="${ctx}/brand/brandLevel/setup" method="post" id="levelForm" name="levelForm">
                	<input type="hidden" name="levelId" id="levelId" value="${param.levelId}"/>
                    <div class="setlevel-box-pp">
                        	品牌：
                        <div class="inline-block">
                        	<select class="js_select grademanage-setlevel-select" name="brandsId" id="brandsId" onchange="$('#levelForm').submit();">
	                        	<option value="" <c:if test="${empty param.brandsId}">selected</c:if>>请选择</option>
	                        <c:forEach items="${brandesInfoList}" var="info">
	                        	<option value="${info.refrenceId}" <c:if test="${info.refrenceId==param.brandsId}">selected</c:if>>${info.brandsName}</option>
	                        </c:forEach>
	                        </select>
                        </div>
                    </div>
                    </form:form>
                    <div class="setlevel-tabsall">
                        <div class="setlevel-menu">
                            <ul class="inline-float">
                            	<c:forEach items="${levelList}" var="level">
                            		<li id="${level.refrenceId}"<c:if test="${level.refrenceId==param.levelId}"> class="current"</c:if>>${level.levelName}</li>
                            	</c:forEach>
                            	<li id="0">未分组终端商</li>
                            	<li id="" <c:if test="${empty param.levelId}"> class="current"</c:if>>全部终端商</li>
                            </ul>
                        </div>
                        <div class="setlevel-con">
                            <div class="setlevel-con-box">
                                <div class="setlevel-seladdall">
                                    <input id="chk9" class="js_chk" type="checkbox" />
                                    <label for="chk9">全选</label>
                                    <a href="javascript:void(0)" class="setlevel-addall js-seltosetlel-btn">批量设置等级</a>
                                    <a href="javascript:delDealer(0)" class="setlevel-addall">批量移除</a>
                                </div>
                                <table class="grademanage-add-table"></table>
                                <div id="pagination" class="pagination"></div>
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
<!--批量设置等级-->
<div class="setlevel-style-showbox js-seltosetlel-showbox">
	<div class="toclass-edit-area-arrow">
    	<em></em>
    	<span></span>
    </div>
    <c:forEach items="${levelList}" var="level">
    <label>
    	<input type="radio" class="ui-checkbox" name="set-level-radio" value="${level.refrenceId}" />${level.levelName}
    </label>
    </c:forEach>
    <div class="ta-c">
    	<a href="javascript:setDealer(0);" class="simple_button js-fn-sure">确定</a>
    	<a href="javascript:void(0);" class="simple_button js-fn-cancel">取消</a>
    </div>
</div>
<!--结束-->
<!--设置等级鼠标移上去显示的内容-->
<input type="hidden" id="selLevelId" value=""/>
<input type="hidden" id="selDealerId" value=""/>
<div class="setlevel-style-showbox js-setlevel-showbox">
	<div class="toclass-edit-area-arrow">
    	<em></em>
    	<span></span>
    </div>
    <c:forEach items="${levelList}" var="level">
    <label>
    	<input type="radio" class="ui-checkbox" name="set-level-radio" value="${level.refrenceId}"/>${level.levelName}
    </label>
    </c:forEach>
</div>
<!--结束-->
<script id="feedback-templage" type="text/html">

    <colgroup>
        <col width="90">
        <col width="70">
        <col width="100">
        <col width="95">
        <col width="100">
        <col width="95">
        <col width="95">
        <col width="80">
        <col width="90">
    </colgroup>
	<thead>
		<tr>
			<th>名称</th>
			<th>所在地区</th>
			<th>经营品类</th>
			<th>累计进货次数</th>
			<th>累计进货数量</th>
			<th>累计进货金额</th>
			<th>最近进货时间</th>
			<th>合作时长</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	{{each rows}}
		<tr>
			<td class="add-table-tdfl">
				<input class="js_chk" type="checkbox" name="dealer" id="dealer" value="{{$value.dealerId}}" />{{$value.dealerName}}
			</td>
			<td>{{#$value.areaNoName}}</td>
			<td>
			{{each $value.dealerDicList}}
				{{$value.dealName}}<br/>
			{{/each}}
			</td>
			<td>{{$value.orderTime}}</td>
			<td>{{$value.orderNum}}</td>
			<td>{{$value.orderMoney}}元</td>
			<td>{{if null!=$value.lastOrder}}{{$formatDate $value.lastOrder}}{{/if}}</td>
			<td>{{$value.startTimeStr}}</td>
			<td><a href="javascript:;" class="bluefont js-setlevel-btn" data_id="{{$value.dealerId}}" level_id="{{$value.levelId}}">设置等级</a> <a href="javascript:delDealer(1,'{{$value.dealerId}}')" class="bluefont">移除</a></td>
		</tr>
	{{/each}}
	</tbody>

</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/agencymanag.js"></script>
<script>

    //全选
    $("#chk9").on("click",function(){
        var checked = $(this).prop("checked");
        $(".grademanage-add-table input[type='checkbox']").each(function(){
            $(this).prop("checked",checked);
        })
    })


grademanage.setLevevlFn=function(obj){
	var levelId=obj.attr("level_id");
	$("#selDealerId").val(obj.attr("data_id")); 
	$(".js-setlevel-showbox input:radio").each(function(){
		 if(levelId==$(this).val())
		 	$(this).prop("checked",true);
		 else
		 	$(this).prop("checked",false); 
	});                                                                 
};
var page;
$(function () {
  seajs.use(["pagination","template","moment"], function (Pagination, template, moment) {
  	template.helper('$formatDate', function (millsec) {
		return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
	});
  	var renderPagination = function(){
  		
  		
  	page = new Pagination({
        url: "${ctx}/brand/brandLevel/dealerList",
        elem: "#pagination",
        form:$("#levelForm"),
        method:"post",
        handleData: function (json) {
           var html = template.render("feedback-templage", json);
           $(".grademanage-add-table").html(html);
           grademanage.init();
        }
    });
    };
    renderPagination();
  });
  
  $(".inline-float li").on("click",function(){
  	var id=$(this).attr("id");
  	$("#levelId").val(id);
  	page.goTo(1);
  });
  $(".js-setlevel-showbox input:radio").on("click",function(){
  	var selLevelId = $.trim($("#selLevelId").val());
  	var levelId = $.trim($(this).val());
  	if(levelId!=selLevelId)
  	{
  		var id=$.trim($("#selDealerId").val());
  		setDealer(1, levelId, id);
  	}
  });
  $("#chk9_span").bind("click",function (){
	$('.add-table-tdfl input[type="checkbox"]').click();
  });
});
function delDealer(t, id){
	confirmDialog("确定是否要移除？",function(){
	  	var menuIdAry = new Array();
	  	if(t==1)
	  		menuIdAry[0]=id;
	  	else{
			$('#dealer:checked').each(function (index, data){
			 	menuIdAry[index] = $(this).val();
			});
	  	}
	  	if(menuIdAry.length <= 0){
			remind("error","请选择一个终端商");
			return;
		}
		var brandsId=$.trim($("#brandsId").val());
		var data={idAry:menuIdAry,brandsId:brandsId};
		levelAjax(data,"终端商移除成功");
	});
};
function setDealer(t, level, id){
  	var menuIdAry = new Array();
  	if(t==1)
  		menuIdAry[0]=id;
  	else{
  		level = $.trim($(".js-seltosetlel-showbox input:radio:checked").val());
  		if(level==""){
  			remind("error","请选择一个等级");
  			return;
  		}
		$('#dealer:checked').each(function (index, data){
		 	menuIdAry[index] = $(this).val();
		});
  	}
  	if(menuIdAry.length <= 0){
		remind("error","请选择一个终端商");
		return;
	}
	var brandsId=$.trim($("#brandsId").val());
	var data={idAry:menuIdAry,brandsId:brandsId,levelId:level};
	levelAjax(data,"终端商设置成功");
};
function levelAjax(data, msg)
{
	$.ajax({
		type : "POST",
		url : "setDealer",
		data : data,
		dataType: "json",
		traditional:true,			
		success : function(json) {
			if(zttx.SUCCESS==json.code){
				remind("success",msg);
				page.render();
			}else
				remind("error",json.message);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			remind("error",errorThrown);
		}
	});
}

</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>