<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-投诉管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/complain_refund.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                
                     <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
               
                <div class="main-right">
                    <jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <span class="bb">投诉管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
		    <div class="inner">	
                    <form:form class="ui-form" action="" id="imageForm" onsubmit="return false;">
                    <div class="main-grid mb10">
                            <div class="ui-form-row">
                                <div class="ui-form-item">
                                    <label class="ui-label">订单编号:</label>
                                    <input name="keyOrderNumber" style="width:80px;" class="ui-input"/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">投诉时间:</label>
                                    	<input name="startTime" id='start-cal' readonly='readonly'  style="width:70px;" class="ui-input date" />
                                    - <input name="endTime" id='end-cal' readonly='readonly' style="width:70px;" class="ui-input date"/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">投诉编号:
                                    </label><input name="keyComplaintNumberId" style="width:80px;" class="ui-input" />
                                </div>

                                <div class="ui-form-item">
                                    <label class="ui-label">投诉状态:</label>
				    <div class="pr inline-block">
                                    <select class="ui-select js_select" name="comState">
                                    	<option value="-1" >全部</option>
                                    	<option value="0" >等待处理</option>
                                    	<option value="1" >客服介入</option>
                                    	<option value="2" >处理完成</option>
                                    	<option value="3" >撤消投诉</option>
                                    </select>
				    </div>
                                    <button class="ui-button ui-button-lwhite" id="doSearch">搜 索</button>
                                </div>
                            </div>
                    </div>
                    <div class="main-grid mb10">
                        <div class="ui-table-container">
                            <table class="ui-table ui-table-inbox">
                                <colgroup>
                                    <col class="tb10" />
                                    <col class="tb10" />
                                    <col class="tb15" />
                                    <col class="tb30" />
                                    <col class="tb10" />
                                    <col class="tb15" />
                                    <col class="tb10" />
                                </colgroup>
                                <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                <thead>
                                    <tr>
                                        <th>投诉编号</th>
                                        <th>订单编号</th>
                                        <th>投诉品牌</th>
                                        <th>投诉原因</th>
                                        <th>投诉状态</th>
                                        <th>申请时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead><!-- 表头可选 -->
                                <tbody id="datas">

                                </tbody>
                            </table>
                            <div class="mt10">
                                <div class="pagination" id="pagination">
                                </div>
                            </div>
                        </div>
                    </div>
                    </form:form>
		    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    	</div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    	<script id="feedback-templage" type="text/html">
	{{each rows}}
			<tr>
				<td>{{$value.complaintNumberId}}</td>
				<td>{{$value.orderNumber}}</td>
				<td>{{$value.brandName}}</td>
			    <td>
					{{if $value.complaintCause==0}}质量有问题{{/if}}
					{{if $value.complaintCause==1}}未按约定时间发货{{/if}}
					{{if $value.complaintCause==2}}未按成交价格进行交易{{/if}}
					{{if $value.complaintCause==3}}承诺的没做到{{/if}}
					{{if $value.complaintCause==4}}违反交易流程{{/if}}					
				</td>
				<td>
					{{if $value.comState==0}}等待处理{{/if}}
					{{if $value.comState==1}}客服介入{{/if}}
					{{if $value.comState==2}}处理完成{{/if}}
					{{if $value.comState==3}}撤消投诉{{/if}}
				</td>
				<td>{{$formatDate $value.createtime}}</td>
				<td style="border-right: 0px none;">
					<a href="${ctx}/dealer/dealerComplaint/process/{{$value.refrenceId}}" class="link">查看</a>
				</td>
			</tr>
	{{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="7" style="text-align:center">暂无数据</td>
    </tr>
    {{ /if }}
	</script>
    <script>
    	var page;
	  $(function () {
	    seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
	    	template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
			});
        	var renderPagination = function(){
        	page = new Pagination({
              url: "${ctx}/dealer/dealerComplaint/complaint.json",
              elem: "#pagination",
              form:$("#imageForm"),
              method:"post",
              handleData: function (json) {
                  var html = template.render("feedback-templage", json);
                  $("#datas").html(html);
              }
          });
          };
          renderPagination(); 
        });
        
        $("#doSearch").click(function(){
        	page.goTo(1);
        });
        
      });

    </script>

</body>
</html>
