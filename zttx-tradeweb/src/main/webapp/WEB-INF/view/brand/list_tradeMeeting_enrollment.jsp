<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>终端商管理-交易会管理-报名情况</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style>
        .info_list_con .tool_bar .fl{
            margin-top: 5px;
        }
        .info_list_con .title{
            margin-top: 15px;
        }
        .info_list_con .title h3{
            float: left;
            font-weight: bold;
            font-size: 14px;
        }
        .info_list_con .title .about{
            float: left;
            display: inline;
            margin: 4px 0 0 20px;
            color: #666;
        }
        .info_list_con .view_person strong{
            font: bold 14px "Arial";
            color: #cc0000;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
	<!-- 导航菜单(品牌商管理中心) -->
    <jsp:include page="${ctx}/brand/brandmenu" />
	<div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <c:choose>
                    <c:when test="${param.m=='1'}">
                        <span class="arrow">&gt;&gt;</span>
                        <a class="link" href="${ctx}/brand/dealer/listDealerApply">终端商管理</a>
                        <span class="arrow">&gt;</span>
                        <span class="current">交易会管理</span>
                    </c:when>
                    <c:otherwise>
                        <span class="arrow">&gt;&gt;</span>
                        <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                        <span class="arrow">&gt;</span>
                        <span class="current">资讯管理</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包导航，并不是每个页面都有 -->
        <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
            <jsp:param name="m" value="1" />
        </jsp:include>
	    <div class="inner">
        <!-- 内容都放这个地方  -->
        <form:form action="" method="post" id="enrolForm">
       	<input type="hidden" name="meetingId" id="meetingId" value="${tradeMeeting.refrenceId}"/>
        <div class="info_list_con">
            <div class="title clearfix">
                <h3>${tradeMeeting.meetName}</h3>
                <span class="about">${fns:getTimeFromLong(tradeMeeting.beginTime,"yyyy-MM-dd")}至${fns:getTimeFromLong(tradeMeeting.endTime,"yyyy-MM-dd")}</span>
            </div>
            <div class="view_person mt10">
                共有<strong>${tradeMeeting.viewNum}</strong>人查看了您的交易会，其中<strong>${tradeMeeting.joinNum}</strong>人向您发送了参会申请
            </div>
            <div class="tool_bar mt10 clearfix">
                <div class="fl">
                    <div class="area_select inline-block">
                        <label>所在地区:</label>
                        <div class="inline-block">
                            <select class="js_select" name="areaNo" id="areaNo" style="width: 92px;">
	                        <option value="">全国</option>
	                        <option value="110000">北京市</option>
	                        <option value="120000">天津市</option>
	                        <option value="130000">河北省</option>
	                        <option value="140000">山西省</option>
	                        <option value="150000">内蒙古自治区</option>
	                        <option value="210000">辽宁省</option>
	                        <option value="220000">吉林省</option>
	                        <option value="230000">黑龙江省</option>
	                        <option value="310000">上海市</option>
	                        <option value="320000">江苏省</option>
	                        <option value="330000">浙江省</option>
	                        <option value="340000">安徽省</option>
	                        <option value="350000">福建省</option>
	                        <option value="360000">江西省</option>
	                        <option value="370000">山东省</option>
	                        <option value="410000">河南省</option>
	                        <option value="420000">湖北省</option>
	                        <option value="430000">湖南省</option>
	                        <option value="440000">广东省</option>
	                        <option value="450000">广西壮族自治区</option>
	                        <option value="460000">海南省</option>
	                        <option value="500000">重庆市</option>
	                        <option value="510000">四川省</option>
	                        <option value="520000">贵州省</option>
	                        <option value="530000">云南省</option>
	                        <option value="540000">西藏自治区</option>
	                        <option value="610000">陕西省</option>
	                        <option value="620000">甘肃省</option>
	                        <option value="630000">青海省</option>
	                        <option value="640000">宁夏回族自治区</option>
	                        <option value="650000">新疆维吾尔自治区</option>
	                        <option value="710000">台湾省</option>
	                        <option value="810000">香港特别行政区</option>
	                        <option value="820000">澳门特别行政区</option>
	                    </select>
	                    <input type="button" value="刷新" onclick="page.goTo(1);"/>
                        </div>
                    </div>
                    <!-- <div class="category_select ml5 inline-block">
                        <label>主营类目:</label>
                        <div class="inline-block">
                            <select style="width: 92px;" class="js_select" name="category" id="category">
                                <option value="0">裤子</option>
                                <option value="1">鞋子</option>
                            </select>
                        </div>
                    </div>
                    <div class="experience_select ml5 inline-block">
                        <label>经验年限:</label>
                        <div class="inline-block">
                            <select style="width: 92px;" class="js_select" name="experience" id="experience">
                                <option value="0">5-10年</option>
                            </select>
                        </div>
                    </div> -->
                </div>
                <!-- <div class="fr">
                    <a class="simple_button" href="javascript">批量同意</a>
                    <a class="simple_button" href="javascript">批量拒绝</a>
                </div> -->
            </div>

            <table class="list_table">
                <colgroup>
                    <col width="110" />
                    <col width="200" />
                    <col width="110" />
                    <col width="120" />
                    <col width="250" />
                    <col width="80" />
                    <col width="100" />
                </colgroup>
                <thead>
                    <tr>
                        <th>姓名</th>
                        <th>所在地区</th>
                        <th>手机号</th>
                        <th>用户类型</th>
                        <th>留言</th>
                        <th>类型</th>
                        <th>申请时间</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
            <div class="mt10" id="pagination"></div>
        </div>
        </form:form>
    </div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!-- 列表模板 --> 
<script id="feedback-templage" type="text/html">
{{each rows}}
<tr>
	<td>{{$value.realName}}</td>
	<td>{{$value.areaName}}</td>
	<td>{{$value.mobile}}</td>
	<td>
	{{if $value.userType==0}}实体门店{{/if}}
	{{if $value.userType==1}}个人{{/if}}
	{{if $value.userType==2}}网店{{/if}}
	{{if $value.userType==3}}品牌厂商{{/if}}
	</td>
	<td>
        {{ if $value.remark != "" }}
            <div class="scrollArea" style="overflow: auto; height: 50px;">{{$value.remark}}</div>
        {{ /if }}
    </td>
	<td>
	{{if $value.appointment==0}}报名{{/if}}
	{{if $value.appointment==1}}参观{{/if}}
	</td>
	<td>{{$formatDate $value.createTime}}</td>
</tr>
{{/each}}
</script>
<script type="text/javascript">
$("#areaNo").on("change",function(){
	page.goTo(1);
});
</script>
<script>
  var page;
  $(function () {
    seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
    	
    	template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD");
		});
		
       	var renderPagination = function(){
       	page = new Pagination({
             url: "${ctx}/brand/tradeMeeting/enrollment/data",
             elem: "#pagination",
             form:$("#enrolForm"),
             method:"post",
             handleData: function (json) {
                 var html = template.render("feedback-templage", json);
                 $(".list_table tbody").html(html);
                 seajs.use("jscroll",function(){
                     $(".scrollArea").jscrollbar({
                         width: "5"
                     });
                 })
             }
         });
         };
         renderPagination();
       });
     });
</script>
</body>
</html>