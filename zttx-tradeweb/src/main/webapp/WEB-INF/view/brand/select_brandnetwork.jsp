<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
	<link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style type="text/css">
    	.uploadimages ul li{
        	overflow: hidden;
        }
        .area-ui-select{
        	width:187px;height:25px;
        	vertical-align: middle;
        }
        .choose_condition .submit_btn {
			display: inline-block;
			width: 88px;
			height: 20px;
			line-height: 20px;
			text-align: center;
			margin: 5px 0 0 8px;
			background: whiteSmoke;
			border: #BAB7B6 solid 1px;
			border-radius: 3px;
		}
    </style>
</head>
<body>
	<jsp:include page="/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>
<div class="main_con">
    <!-- 内容都放这个地方  -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">品牌资料完善</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <form:form id="brandNetworkForm" name="brandNetworkForm" cssClass="ui-form" action="${ctx}/brand/brandNetwork/select" method="post">
        <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
   			<jsp:param value="3" name="m"/>
   		</jsp:include>
        <div class="tips">
            <i class="v2-icon-explain"></i>
            说明：展示品牌门店形象，提高品牌竞争力
        </div>
        <div class="tab_con_box">
            <div class="tab_con distribution_net">
                <div class="ui_tab main_ui_tab" style="margin: 0 20px;">
                    <ul class="ui_tab_items clearfix">
                        <li class="ui_tab_item current">
                            <a href="${ctx }/brand/brandNetwork/execute?id=${id}">门店展示添加</a>
                        </li>
                        <li class="ui_tab_item">
                            <a href="${ctx }/brand/brandNetwork/list?id=${id}">门店展示管理</a>
                        </li>
					</ul>
                </div>
                <div class="inner_tab_con_box">
					<div class="inner_tab_con" style="display: block;">
                        <div class="toolbar">
							<label>
								<input type="radio" name="add" value="${ctx}/brand/brandNetwork/execute?id=${id}"/>  新添加
                            </label>
                            <label>
                                    <input id="choose_from_radio" checked="checked" type="radio" name="add" value="${ctx}/brand/brandNetwork/select?id=${id}"/> 从现有终端商选取
                            </label>
                            </div>
                            <!-- 从现有终端商选取开始 -->
                            <div class="choose_from" style="display: block;">
                                <div class="choose_condition">
                                    <div class="fl">
                                        <span class="fl check_box"><input id="chk_all" class="js_chk" type="checkbox" />全选</span>
                                        <div class="level fl">
                                            <label for="">终端商等级:</label>
                                            <div class="inline-block">
                                                <select class="js_select" name="levelId" id="level_select">
                                                    <option value="">全部终端商</option>
                                                    <c:forEach items="${brandLevels}" var="brandLevel">
                                                    	<option value="${brandLevel.refrenceId}" ${brandLevel.refrenceId==dealerJoin.levelId? 'selected="selected"' :''}>${brandLevel.levelName }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="area fl">
                                            <label for="">终端商区域:</label>
                                            <div class="inline-block">
                                            	<jsp:include page="${ctx}/common/regional/searchAllArea">
	                                        		<jsp:param value="${dealerJoin.province }" name="regProvince"/>
													<jsp:param value="${dealerJoin.city }" name="regCity"/>
													<jsp:param value="${dealerJoin.county }" name="regCounty"/>
													<jsp:param value="area-ui-select" name="style"/>
	                                        	</jsp:include>
                                            </div>
                                        </div>
                                        <div class="form_item fl">
                                        	<a class="submit_btn" href="javascript:page.goTo(1);">搜&nbsp;索</a>
                                        </div>
                                    </div>
                                </div>
                                <table>
                                    <colgroup>
                                        <col width="292" />
                                        <col width="170" />
                                        <col width="170" />
                                        <col width="170" />
                                        <col width="170" />
                                    </colgroup>
                                    <tr>
                                        <th>终端商名</th>
                                        <th>所在地区</th>
                                        <th>等级</th>
                                        <th>分店数量</th>
                                        <th class="last">月销售额</th>
                                    </tr>
                                </table>
                                <div class="choose_list">
                                    <div class="contain"></div>
                                    <div class="mt10" id="pagination"></div>
                                </div>
                                <div class="add_btn">
                                    <a class="ui_button ui_button_morange" href="javascript:void(0)" id="add_btn">添加到门店展示</a>
                                </div>
                            </div>
                            <!-- 从现有终端商选取结束 -->
                        </div>
                    </div>
                </div>
        </div>
		</form:form>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

<script src="${res}/scripts/brand/brand_manage.js"></script>
<script>
$(function(){
  	//选择品牌更新内容
	$('#brandsId').change(function (e) {
          var id = $(this).val();
          window.location.href = "${ctx}/brand/brandNetwork/select?id=" + id;
    });
    $("#chk_all").on("click",function(){
        var checked = $(this).prop("checked");
        $(".choose_list table input[type='checkbox']").prop("checked",checked);
    });
    $("#add_btn").on("click",function(){
    	var len = $(".choose_list table input:checkbox:checked").length;
    	if(len<=0)
    	{
			remind("error", "请选择一个终端商");
    		return;
    	}
    	$.post("${ctx}/brand/brandNetwork/select/insert", $("#brandNetworkForm").serialize(), function(data){
			if(data.code == zttx.SUCCESS)
			{
				remind("success", "终端商已成功加入到经绡网络");
				page.render();
			}
			else
			{
				remind("error", data.message);
			}
		}, "json");
	});
    $(".toolbar input:radio").on("click",function(){
    	var url = $.trim($(this).val());
    	if(url!="")
    	{
    		window.location.href=url;
    	}
    });
});
	
    
    //全选与全不选
	/* $(".check_box").click(function () {
		var _checked = $("#chk_all").attr("checked");
		if(_checked){
			$(".CheckBox").addClass("checked");
			$(this).attr("checked",true);
			$(".choose_list td input[type=checkbox]").attr("checked",true);
		}else{
			$(".CheckBox").removeClass("checked");
			$(this).attr("checked",false);
			$(".choose_list td input[type=checkbox]").attr("checked",false);
		}
	});
	
	//设置全选按钮是否选中
	$(".choose_list").on("click",".CheckBox",function(){
		var len = $("tbody td").find("input[type=checkbox]").length;
		var clen = $("tbody td").find("[checked=checked]").length;
		if(len==clen){
			$("#chk_all").attr("checked",true);
			$(".CheckBox").addClass("checked");
		}else{
			$("#chk_all").removeAttr("checked",true);
			$("#chk_all_span").removeClass("checked");
		}
	})
	
	
	$("#add_btn").on("click",function(){
		$("#brandNetworkForm").attr("action","${ctx}/brand/brandNetwork/insert")
		$("#brandNetworkForm").submit();
	});
	
	$(".toolbar input:radio").on("click",function(){
    	var url = $.trim($(this).val());
    	if(url!="")
    	{
    		window.location.href=url;
    	}
    }); */
</script>
<script>
  var page;
  $(function () {
    seajs.use(["pagination","template"], function (Pagination,template) {
       	var renderPagination = function(){
       	page = new Pagination({
             url: "${ctx}/brand/brandNetwork/select/data",
             elem: "#pagination",
             form:$("#brandNetworkForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".choose_list .contain").html(html);
             }
         });
         };
         renderPagination();
       });
     });
</script>
</body>
</html>