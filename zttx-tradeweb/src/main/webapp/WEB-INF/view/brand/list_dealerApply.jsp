<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-终端商管理-申请中的终端商</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
	<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
	<div class="main_con">
		<jsp:include page="/WEB-INF/view/brand/search_dealer_head.jsp">
                <jsp:param value="2" name="m"/>
            </jsp:include>
			<div class="inner">
				<!-- 内容都放这个地方  -->
					<div class="agency-seeking-contant">
						<div class="js_agency_tabs">
							<jsp:include page="/WEB-INF/view/brand/dealer_manage_head.jsp">
                     			<jsp:param value="2" name="m"/>
                     		</jsp:include>
							<div class="js_agency_con">
                                <div class="tips explain">
                                    <i class="v2-icon-explain"></i>
                                    <label for="">图标示例</label>
                                    <span class="down_icon icons"></span><span class="text">下载过联系人</span>
                                    <span class="pic_icon icons"></span><span class="text">店铺图片</span>
                                    <span class="map_icon icons"></span><span class="text">地图</span>
                                </div>
								<div class="agency-seeking-recom clearfix">
									<!--寻找更多的终端商-->
									<div class="agency-seeking-order-tbody clearfix hide">
										<div class="fl">
											<span class="order-look">当前还可查看终端商： <em>8</em> 家</span> <a
												href="" class="order-pagemove">订购</a>
										</div>
										<div class="fr">
											<c:choose>
												<c:when test="${page.hasPreviousPage}">
													<a class="order-pagemove order-pageup"
														href="javascript:$Z.goPrevious($('#imageForm'));">上一页</a>
												</c:when>
												<c:otherwise>
													<a class="order-pagemove order-pageup" href="javascript:">上一页</a>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${page.hasNextPage}">
													<a class="order-pagemove order-pagedown"
														href="javascript:$Z.goNext($('#imageForm'))">下一页</a>
												</c:when>
												<c:otherwise>
													<a class="order-pagemove order-pagedown" href="javascript:">下一页</a>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
                                    <div id="data_contain">

                                    </div>
									<div class="mt10" id="pagination"></div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<!-- 拒绝申请 -->

		<div style="display: none">
			<!--弹窗内容-->
			<div class="js-reject-show info-invite-showbox">
			<form:form action="" method="post" id="applyForm">
			<input type="hidden" name="dealerId" id="dealerId"/>
			<input type="hidden" name="brandsId" id="brandsId"/>
				<div class="ui-head">
					<h3>拒绝申请</h3>
				</div>
				<div class="info-invite-textarea">
					<textarea class="ui-textarea" id="auditMark" name="auditMark" placeholder="请输入拒绝理由..."></textarea>
				</div>
				<div class="ta-c">
					<a href="javascript:void(0);" class="simple_button confirm_btn">确认</a> 
					<a href="javascript:void(0);" class="simple_button cancel_btn ml5">取消</a>
				</div>
			</form:form>
			</div>
		</div>
	
	<script id="feedback-templage" type="text/html">
	<table id="tempTable" class="agency-seeking-table">
		<colgroup>
				<col width="160">
				<col width="150">
				<col width="100">
				<col width="100">
				<col width="126">
				<col width="200">
				<col width="170">
		</colgroup>
		<thead>
			<tr>
				<th>终端商名</th>
				<th>所在地区</th>
				<th>经营品类</th>
				<th>申请时间</th>
				<th>申请加盟的品牌</th>
				<th>留言</th>
				<th class="last">操 作</th>
			</tr>
		</thead>
		<tbody>
		{{each rows}}
			<tr class="{{$index%2==0 ? '':'odd'}}">
				<td>
					<span title="{{$value.dealerName}}">{{$value.dealerName}}</span>
                    {{if $value.isExist}}
                    <span class="down_icon icons"></span>
                    {{/if}}
                    {{if $value.dealerLogo!=null && $value.dealerLogo!=""}}
                    <span class="pic_icon icons"></span>
                    {{/if}}
                    {{if $value.gpsX!=null && $value.gpsX!="" && $value.gpsY!=null && $value.gpsY!=""}}
                    <span class="map_icon icons"></span>
                    {{/if}}
                    {{if $value.dealerLevel!=null && $value.dealerLevel!=""}}
                    <span class="star{{$value.dealerLevel}}_icon icons"></span>
                    {{/if}}
				</td>
				<td>{{$value.areaName}}</td>
				<td>{{$value.brandName}}</td>
				<td>{{$formatDate $value.inviteTime}}</td>
				<td class="ta-c">{{$value.brandsName}}</td>
				<td>{{$value.inviteText}}</td>
				<td class="last ta-c">
					<a class="agency-seeking-td-state js_agree link" href="javascript:agreeJoin('{{$value.id}}','{{$value.brandsId}}');" value="${info.dealerApplyId}" id="dealerid_{{$value.id}}" data-dealername="{{$value.dealerName}}" data-isfactoryjoin="{{if $value.isFactoryJoin == true }}0{{else}}1{{/if}}">同意</a>
					<a class="agency-seeking-td-state js_reject link" href="javascript:;" data_dealer_id="{{$value.id}}" data_brands_id="{{$value.brandsId}}">拒绝</a>
					<a href="${ctx}/brand/dealer/info/{{$value.id}}" class="agency-seeking-td-state link">查看资料</a>
				</td>
			</tr>
		{{/each}}
        {{ if rows==null||rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="7">暂无数据</td>
        </tr>
        {{ /if }}
		</tbody>
	</table>
	</script>
    <script id="depositEditTpl" type="text/html">
        <div class="ui-head">
            <h3>设置押金</h3>
        </div>
        <form:form id="depositForm" class="ui-form mt15" method="post" action="" data-widget="validator">
            <div class="ui-form-item">
                <label class="ui-label" style="font-size:12px;">
                    工厂店终端商：
                </label>
                <input type="text" class="ui-input" disabled value="{{dealername}}" style="width: 176px;" />
            </div>
            <div class="ui-form-item">
                <label class="ui-label" style="font-size:12px;">
                    押金设置：
                </label>
                <input type="text" class="ui-input" name="depositAmount" required data-display="押金"
                {{if rule == 1}}
                data-rule="isamount"
                {{/if}}
                style="width: 176px;" />
            </div>
            <div class="ta-c mt10">
                <button type="submit" class="simple_button">确认</button>
                <button type="button" href="javascript:;" class="simple_button ml10 js-depositcansole-btn">取消</button>
            </div>
        </form:form>
    </script>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/agencymanag.js"></script>
	<script>
	function showReject(obj){
		var dealerId=obj.attr("data_dealer_id");
		var brandsId=obj.attr("data_brands_id");
		$("#dealerId").val(dealerId);
		$("#brandsId").val(brandsId);
	};
	function agreeJoin(dealerId, brandsId){

        seajs.use(['template', 'moment', 'dialog'], function (template, moment, Dialog) {
            $("#dealerId").val(dealerId);
            $("#brandsId").val(brandsId);
            var $this = $("#dealerid_"+ dealerId);
            var factoryFlag = $this.data("isfactoryjoin");
            var depositDialog = new Dialog({
                content: $("#depositEditTpl").html(),
                width: 300
            });
            var editObj = {};
            editObj.dealername = $this.data("dealername");
            editObj.rule = 1;
            confirmDialog("是否允许终端商加盟？",function(){
                $.post("${ctx}/brand/dealer/agree",$("#applyForm").serialize(),function(data){
                    if(data.code == 126000){
                        if(factoryFlag == 1){
                            remind("success", "终端商已成功加盟");
                            page.render();
                        }
                        if(factoryFlag == 0){
                            var html = template.render("depositEditTpl",editObj);
                            depositDialog.element.html(html);
                            depositDialog.show();
                            baseFormValidator({
                                selector: "#depositForm",
                                isAjax: true,
                                addElemFn:function(Core, Validator){
                                    Validator.addRule("isamount", function(options) {
                                        var element = options.element;
                                        return isAmount(element.val());
                                    }, "押金格式错误");
                                },
                                beforeSubmitFn:function(){
                                    $.ajax({
                                        url: '${ctx}/brand/join/setDeposit',
                                        method: 'post',
                                        data: $("#depositForm").serialize() + "&joinId=" + data.object,
                                        dataType: 'json',
                                        success: function (json) {
                                            depositDialog.hide();
                                            if (json.code === zttx.SUCCESS) {
                                                remind("success", "押金设置成功");
                                                page.render();
                                            }else{
                                                remind("error",json.message);
                                                page.render();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }else{
                        remind("error",data.message);
                    }
                },"json");
            });
            //取消
            $(document).on("click",".js-depositcansole-btn",function(){
                page.render();
                depositDialog.hide();
            });
        });
	}
	function rejectJoin(obj){
        if($.trim($("#auditMark").val()) == ""){
            remind("error","拒绝理由不能为空");
            return;
        }
		$.post("${ctx}/brand/dealer/reject",$("#applyForm").serialize(),function(data){
	   		if(data.code==zttx.SUCCESS){
	   			obj.hide();
	   			remind("success", "终端商加盟申请已拒绝");
				page.render(); 
	   		}else{
	   			remind("error",data.message); 
	   		}
	   	},"json");
	}
	</script>
	<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format("YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/brand/dealer/invite/data",
              elem: "#pagination",
              form:$("#dealerSearchForm"),
              data:{applyState:0},
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#data_contain").html(html);
                  agencyApply.init();
              }
          });
          };
          renderPagination();
        });
      });
	</script>
</body>
</html>