<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-终端商管理-邀请中的终端商</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
	<jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
	<div class="main_con">
		<jsp:include page="/WEB-INF/view/brand/search_dealer_head.jsp">
                <jsp:param value="1" name="m"/>
            </jsp:include>
			<div class="inner">
				<!-- 内容都放这个地方  -->
				<div class="agency-seeking-contant">
					<div class="js_agency_tabs">
						<jsp:include page="/WEB-INF/view/brand/dealer_manage_head.jsp">
                     		<jsp:param value="1" name="m"/>
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
													<a class="order-pagemove order-pageup" href="javascript:$Z.goPrevious($('#imageForm'));">上一页</a>
												</c:when>
												<c:otherwise>
													<a class="order-pagemove order-pageup" href="javascript:" >上一页</a> 
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${page.hasNextPage}">
													<a class="order-pagemove order-pagedown" href="javascript:$Z.goNext($('#imageForm'))">下一页</a>
												</c:when>
												<c:otherwise>
													<a class="order-pagemove order-pagedown" href="javascript:" >下一页</a>
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
	<script id="feedback-templage" type="text/html">
	<table id="tempTable" class="agency-seeking-table">
		<colgroup>
			<col width="170">
            <col width="160">
            <col width="100">
            <col width="100">
            <col width="156">
            <col width="220">
            <col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>终端商名</th>
				<th>所在地区</th>
				<th>经营品类</th>
				<th>邀请时间</th>
				<th>邀请加盟的品牌</th>
				<th>我的留言</th>
				<th class="last">操 作</th>
			</tr>
		</thead>
		<tbody>
		{{each rows}}
			<tr class="{{$index%2==0 ? '':'odd'}}">
				<td>
					<span class="fn-text-overflow" title="{{$value.dealerName}}">{{$value.dealerName}}</span>
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
				<td>{{$value.province}}/{{$value.city}}/{{$value.area}}</td>
				<td>{{$value.brandName}}</td>
				<td>{{$formatDate $value.inviteTime}}</td>
				<td>{{$value.brandsName}}</td>
				<td>{{$value.inviteText}}</td>
				<td class="last">
					<a class="link" href="${ctx}/brand/dealer/info/{{$value.id}}" target="_blank">查看资料</a>
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
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script src="${src}/brand/agencymanag.js"></script>
	<script src="${src}/plugin/template-simple.js"></script>

	<script>
	  var page;
	  $(function () {
	    seajs.use(["pagination","moment"], function (Pagination,moment) {
	    	template.helper('$formatDate', function (millsec) {
            	return moment(millsec).format(" YYYY-MM-DD");
        	});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/brand/dealer/invite/data",
              elem: "#pagination",
              form:$("#dealerSearchForm"),
              data:{applyState:3},
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