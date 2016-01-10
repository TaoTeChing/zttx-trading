<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-终端商管理-浏览过我的终端商</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
	<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
	<div class="main_con">
		<jsp:include page="/WEB-INF/view/brand/search_dealer_head.jsp">
                <jsp:param value="5" name="m"/>
            </jsp:include>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<div class="agency-seeking-contant">
					<div class="js_agency_tabs">
						<jsp:include page="/WEB-INF/view/brand/dealer_manage_head.jsp">
                     		<jsp:param value="5" name="m"/>
                     	</jsp:include>
						<div class="js_agency_con">
                            <div class="tips explain">
                                <i class="v2-icon-explain"></i>
                                <label for="">图标示例</label>
                                <span class="down_icon icons"></span><span class="text">下载过联系人</span>
                                <span class="pic_icon icons"></span><span class="text">店铺图片</span>
                                <span class="map_icon icons"></span><span class="text">地图</span>
                            </div>
							<div class="agency-seeking-recom">
								<!--寻找更多的终端商-->
								<div id="targetDiv" class="clearfix">
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
	</div>
	<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
	<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp" />
	<script id="feedback-templage" type="text/html">
	<table id="tempTable" class="agency-seeking-table">
		<colgroup>
			<col width="170">
			<col width="90">
			<col width="80">
			<col width="100">
			<col width="170">
			<col width="180">
			<col width="100">
			<col width="140">
		</colgroup>
		<thead>
			<tr>
				<th>终端商名</th>
				<th>实体店数量</th>
				<th>员工数量</th>
				<th>浏览品牌</th>
				<th>所在地区</th>
				<th>经营品类</th>
				<th>成立时间</th>
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
				<td class="ta-c"><span class="agency-seeking-td-jppn">{{$value.shopNum}}</span></td>
				<td class="ta-c"><span class="agency-seeking-td-class">{{$value.empNum}}</span></td>
				<td><span class="agency-seeking-td-exp">{{$value.brandsName}}</span></td>
				<td><span class="agency-seeking-td-haceshou">{{$value.areaName}}</span></td>
				<td><span class="agency-seeking-td-exp">{{$value.brandName}}</span></td>
				<td><span class="agency-seeking-td-exp">{{if $value.foundTime==null || $value.foundTime<=0}}--{{else}}{{$formatDate $value.foundTime}}{{/if}}</span></td>
				<td>
					<a class="link js-invite-join" href="javascript:;" data-dealerid="{{$value.id}}">诚邀加盟</a>
					<a class="link" href="${ctx}/brand/dealer/info/{{$value.id}}" target="_blank">查看资料</a>
				</td>
			</tr>
		{{/each}}
        {{ if rows==null||rows.length == 0 }}
        <tr>
            <td class="ta-c" colspan="8">暂无数据</td>
        </tr>
        {{ /if }}
		</tbody>
	</table>
	</script>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/agencymanag.js"></script>
	<script src="${src}/plugin/template-simple.js"></script>
	<script>
	function inviteJoin(obj,invitedio){
		$("#dealerId").val($.trim(obj.attr("data-dealerid")));
		invitEditDiv=invitedio;
	}
	</script>
	<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment"], function (Pagination,moment) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format("YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/brand/dealer/dealerVisited/data",
              elem: "#pagination",
              form:$("#dealerSearchForm"),
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