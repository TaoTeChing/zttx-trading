<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理中心-终端商管理-寻找终端商</title>
<link rel="stylesheet" href="${res}/styles/brand/global.css" />
<link rel="stylesheet" href="${res}/styles/brand/agencymanag.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
        <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
		<div class="main_con">
            <jsp:include page="/WEB-INF/view/brand/search_dealer_head.jsp">
                <jsp:param value="7" name="m"/>
            </jsp:include>
			<div class="inner">
				<!-- 内容都放这个地方  -->
					<div class="agency-seeking-contant">
					<div class="js_agency_tabs">
						<jsp:include page="/WEB-INF/view/brand/dealer_manage_head.jsp">
		                	<jsp:param value="7" name="m"/>
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
								<div class="agency-seeking-order-tbody clearfix hide">
								<div class="fl">
									<span class="order-look">当前还可查看终端商： <em>8</em> 家</span> <a
										href="" class="order-pagemove">订购</a>
								</div>
								<div class="fr">
									<a href='javascript:if (parseInt($(".pagination .current").html())>1){$Z.loadPage("findForm","${ctx}/brand/dealer/findDealerAjax","targetDiv",(parseInt($(".pagination .current").html())-1));}' class="order-pagemove order-pageup">上一页</a> 
									<a href='javascript:if (${pageResult.page.hasNextPage}){$Z.loadPage("findForm","${ctx}/brand/dealer/findDealerAjax","targetDiv",(parseInt($(".pagination .current").html())+1));}'class="order-pagemove order-pagedown">下一页</a>
								</div>
								</div>
								<div id="targetDiv clearfix">
                                    <div id="data_contain">
                                        <%--在这里--%>
                                        <table id="tempTable" class="agency-seeking-table">
                                            <colgroup>
                                                <col width="190" />
                                                <col width="110" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="160" />
                                                <col width="100" />
                                                <col width="140" />
                                            </colgroup>
                                            <thead>
                                            <tr>
                                                <th><span>终端商名</span></th>
                                                <th><span>实体店数量<a href="javascript:;" data-attr="1" class="ml5 arrow-down"></a></span></th>
                                                <th><span>员工数量<a href="javascript:;" data-attr="2" class="ml5 arrow-down"></a></span></th>
                                                <th><span>所在地区</span></th>
                                                <th><span>经营品类</span></th>
                                                <th><span>成立时间<a href="javascript:;" data-attr="3" class="ml5 arrow-down"></a></span></th>
                                                <th><span>操 作</span></th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
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
				<td class="ta-c"><span>{{$value.shopNum}}</span></td>
				<td class="ta-c"><span>{{$value.empNum}}</span></td>
				<td><span>{{$value.areaName}}</span></td>
				<td><span>{{$value.dealerClassess}}</span></td>
				<td><span>{{if $value.foundTime==null || $value.foundTime<=0}}--{{else}}{{$formatDate $value.foundTime}}{{/if}}</span></td>
				<td class="ta-c">
					<a class="link js-invite-join" href="javascript:;" data-dealerid="{{$value.id}}">诚邀加盟</a>
					<a href="${ctx}/brand/dealer/info/{{$value.id}}" class="link">查看资料</a>
				</td>
			</tr>
		{{/each}}
	</script>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
    <script src="${src}/brand/agencymanag.js"></script>
	<script>
	function inviteJoin(obj,invitedio){
		$("#dealerId").val($.trim(obj.attr("data-dealerid")));
		invitEditDiv=invitedio;
	}
	</script>
	<script>
        var page;
        $(function () {

            $(document).on('click','.arrow-down,.arrow-down-current,.arrow-top-current',function(){

                var $this = $(this), addClass = '';
                if($this.hasClass('arrow-down'))
                {
                    $('#attrType').val($this.data('attr'));
                    $('#descType').val(1);
                    addClass = 'arrow-down-current';
                }else if($this.hasClass('arrow-down-current'))
                {
                    $('#attrType').val($this.data('attr'));
                    $('#descType').val(0);
                    addClass = 'arrow-top-current';
                }else if($this.hasClass('arrow-top-current'))
                {
                    $('#attrType').val("");
                    $('#descType').val("");
                    addClass = 'arrow-down';
                }
                $('.arrow-down,.arrow-down-current,.arrow-top-current').removeClass("arrow-down-current").removeClass("arrow-top-current").addClass("arrow-down");
                $this.removeClass('arrow-down').addClass(addClass);

                page.goTo(page.current);

            });


            seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
                template.helper('$formatDate', function (millsec) {
                    return moment(millsec).format(" YYYY-MM-DD");
                });
                var renderPagination = function(){
                    page = new Pagination({
                        url: "/brand/dealer/findDealer/data",
                        elem: "#pagination",
                        form:$("#dealerSearchForm"),
                        method:"post",
                        handleData: function (json) {
                            var html = template.render("feedback-templage", json);
                            $("#tempTable tbody").html(html);
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