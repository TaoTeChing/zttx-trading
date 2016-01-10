<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>报名中心</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <style>
        .pagination {
            background: #fff;
            text-align: right;
            font-size: 0;
        }
        .pagination .total {
            margin-left: 10px;
            font-size: 12px;
        }
        .pagination .total strong {
            font-weight: normal;
        }
        .pagination .ellipsis {
            margin-left: 10px;
            font-size: 12px;
        }
        .pagination .page {
            font-size: 12px;
            display: inline-block;
            margin-left: 10px;
            padding: 0 6px;
            height: 22px;
            line-height: 22px;
            background: #f3f3f3;
            color: #666;
        }
        .pagination .page:hover {
            background-color: #c00;
            color: #fff;
        }
        .pagination .current {
            background-color: #c00;
            color: #fff;
        }
        .pagination .prev,
        .pagination .next {
            width: 58px;
            height: 20px;
            line-height: 20px;
            border: #ddd solid 1px;
            text-align: center;
            border-radius: 2px;
            margin-left: 10px;
            font-size: 12px;
            display: inline-block;
            color: #666;
        }
        .pagination .prev:hover,
        .pagination .next:hover {
            color: #c00;
        }
        .search_bar{
            position: relative;
        }
        .search_bar .item{
            display: inline;
            position: absolute;
            right: 0;
        }
        .search_bar .item select{
            width: 118px;
            padding: 5px;
        }
        .common_table{
            border-top: #ddd solid 1px;
        }
        .common_table td,.common_table th{
            padding: 8px 0;
            text-align: center;
        }
        .common_table td{
            border-right: #eee solid 1px;
            border-bottom: #eee solid 1px;
            padding-left: 8px;
            padding-right: 8px;
        }
        .common_table th{
            background: #f9f9f9;
            border-right: #eee solid 1px;
            font-weight: normal;
            color: #666;
        }
        .common_table .odd td{
            background: #f9f9f9;
        }
        .common_table .last{
            border-right: none;
        }
        .common_table .link{
            color: #0082cc;
        }
        .common_table .link:hover{
            color: #ff8800;
        }
        .status2{
            color: #097b23;
        }
        .status3{
            color: #999;
        }
        .status4{
            color: #cc0000;
        }
        .ui-button {
            display: inline-block;
            *display: inline;
            text-align: center;
            text-decoration: none;
            vertical-align: middle;
            cursor: pointer;
            /*font-size: 14px;*/
            /*font-family: inherit;*/
            /* Correct font family not being inherited in all browsers for <button> <input> inherit*/
            /*font-weight: initial;*/
            border-radius: 2px;
            padding: 0 20px;
            *zoom: 1;
            *overflow: visible;
            /* for a ie6/7 bug http://blog.csdn.net/jyy_12/article/details/6636099 */
            background-image: none;
            /* for old alice button style conflict */
        }
        .ui-button:hover {
            text-decoration: none;
            background-image: none;
            /* for old alice button style conflict */
        }
        .ui-button:active {
            position: relative;
            top: 1px;
            box-shadow: 1px 1px 3px #999 inset;
        }
        .ui-button-mred {
            color: #fff;
            border: 1px solid #c00;
            background-color: #c00;
        }
        .ui-button-mred:hover{
            background-color: #b10000;
        }
        a.ui-button-mred,
        a.ui-button-mred:hover,
        a.ui-button-mred:active{
            color: #fff;
        }
        .ui-button-mred {
            line-height: 28px;
            height: 28px;
        }
        a.ui-button-mred {
            line-height: 26px;
            height: 26px;
        }
        a.ui-button-mred:active {
            box-shadow: 1px 1px 3px #5c0707 inset;
        }
    </style>
</head>
<body>
   <!--完成-->
<div class="container">
<jsp:include page="${ctx}/dealer/mainmenu"/>
<div class="em100">
<div class="main clearfix pr">
<!--侧边导航-->
<jsp:include page="${ctx}/dealer/dealermenu"/>
<!--主体内容-->
<div class="main-right">
                    <!--提示框-->
                    <!--<div class="main-grid mb10">
                        <div class="alert c-w warning">
                            <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                        </div>
                    </div>-->
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <p>
                                <i class="icon i-setting"></i><a href="${ctx }/dealer/center">管理中心</a> >> <span class="bb">报名中心</span>
                                <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                            </p>
                        </div>
                    </div>
				<form:form id="meetform">
                  <div class="search_bar mb10 clearfix">
                    <a href="${ctx }/meet/index" class="ui-button ui-button-mred"  target="_blank">我要报名</a>
					   <%--	
                        <div class="item">
                            <label for="">
                                类型：
                            </label>
                            <select>
                                <option value="0">订货会</option>
                            </select>
                        </div> --%>

                       <%--  <div class="item">
                            <label for="">
                                发起方：
                            </label>
                            <select name="initiator" id="selectInitiator">
                            	<c:if test="${initiators != null}" >
                            		<option value="">全部</option>
                            		<c:forEach items="${initiators }" var="initiator">
                            			<option >${initiator }</option>
                            		</c:forEach>
                            	</c:if>
                                
                            </select>
                        </div> --%>

                        <div class="item">
                            <label for="">
                                状态：
                            </label>
                            <select name="status" id="selectStatus">
                            	<c:if test="${status != null }">
                            		<option value="">全部</option>
                            		<c:forEach items="${status }" var="status"	>
                            			<option value="${status.key }">${status.value }</option>
                            		</c:forEach>
                            	</c:if>
                            </select>
                        </div>
						</form:form>
                    </div>

                 <!--    <table class="common_table">
                        <colgroup>
                            <col width="160">
                            <col width="200">
                            <col width="200">
                            <col width="200">
                            <col width="120">
                            <col width="120">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>类型</th>
                                <th>名称</th>
                                <th>发起方</th>
                                <th>起止时间</th>
                                <th>状态</th>
                                <th class="last">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>订货会</td>
                                <td>朵彩春季新品订货会</td>
                                <td>朵彩</td>
                                <td>2014-02-15 至 2014-06-02</td>
                                <td>
                                    <span class="status1">已报名</span>
                                </td>
                                <td class="last">
                                    <a class="link" href="#">查看</a>
                                </td>
                            </tr>
                        
                          
                        </tbody>
                    </table> -->
              <!--       <div class="mt10">
                        <div class="pagination">
                            <a href="javascript:;" class="prev">上一页</a>
                            <a href="javascript:;" class="page current">1</a>
                            <a href="javascript:;" class="page">2</a>
                            <a href="javascript:;" class="next">下一页</a>
                            <span class="total">2/24页</span>
                        </div>
                    </div> -->
                    <div id="data">
                	</div>
                	<div class="mt10" id="pagination"></div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />

</body>
</html>

<script type="text/html" id="showRemark">
    <table class="common_table">
      <colgroup>
          <col width="160">
          <col width="200">
          <col width="200">
          <col width="200">
          <col width="120">
          <col width="120">
      </colgroup>
      <thead>
          <tr>
              <th>类型</th>
              <th>名称</th>
              <th>发起方</th>
              <th>起止时间</th>
              <th>状态</th>
              <th class="last">操作</th>
          </tr>
      </thead>
      <tbody>
		{{each rows}}
          <tr>
              <td>订货会</td>
              <td>{{$value.meetName}}</td>
              <td>{{$value.brandsName}}</td>
              <td>{{$formatDate $value.beginTime}} 至 {{$formatDate $value.endTime}}</td>
              <td>
                  <span class="{{$formatClass $value.joinState}}">{{$formatState $value.joinState}}</span>
              </td>
              <td class="last">
                  <a class="link"  target="_blank"  href="${ctx}/meet/detail?refrenceId={{$value.refrenceId}}">查看</a>
              </td>
          </tr>
		{{/each}}
   </tbody>
</table>
</script>


<script>

	var arr = ${list};
	var classArr = ['status1','status2','status3','status4'];
 seajs.use(["pagination","moment","template"], function (Pagination,moment,template) {
     	template.helper('$formatState', function (state) {
           	return arr[state];
       	}); 
       	template.helper('$formatDate', function (millsec) {
           	return moment(millsec).format(" YYYY-MM-DD");
       	});
       	template.helper('$formatClass', function (index) {
           	return classArr[index ];
       	});
        page = new Pagination({
             url: "${ctx}/dealer/meet/data",
             elem: "#pagination",
             form:$("#meetform"),
             method:"post",
             handleData: function (json) {
             	 var html = template.render("showRemark", json);
                 $("#data").html(html);
             }
         });
		
		//状态的选择
		$('#selectStatus').on('change' , function (){
			page.render();
		});
		
		//selectInitiator
		$('#selectInitiator').on('change' , function (){
			page.render();
		});
		
     });
     </script>

