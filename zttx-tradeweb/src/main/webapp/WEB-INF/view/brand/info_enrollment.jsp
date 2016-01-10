<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>资讯管理-报名情况</title>
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
		 <jsp:include page="${ctx}/brand/brandmenu"/>
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
    <div class="inner">
        <!-- 内容都放这个地方  -->
	    <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
    		<jsp:param name="m" value="2" />
    	</jsp:include>
        
        <div class="info_list_con">
            <div class="title clearfix">
                <h3>${brandMeeting.meetName }</h3>
                <span class="about">${fns:getStringTime(brandMeeting.beginTime,"yyyy-MM-dd")}至${fns:getStringTime(brandMeeting.endTime,"yyyy-MM-dd")}   ${brandMeeting.meetAddress }</span>
            </div>
            <div class="view_person mt10">
                共有<strong>${brandMeeting.viewNum }</strong>人查看了您的订货会，其中<strong>${brandMeeting.joinNum }</strong>人向您发送了参会申请
            </div>
            <div class="tool_bar mt10 clearfix">
                <div class="fl">
                    <div class="area_select inline-block">
                        <label>所在地区:</label>
	                     <jsp:include page="${ctx}/client/Regional/searchaAll">
							<jsp:param value="" name="regProvince" />
							<jsp:param value="" name="regCity" />
							<jsp:param value="" name="dealerAddr" />
							<jsp:param value="enrolment" name="sale" />
							<jsp:param value="js_select" name="style" />
						</jsp:include>
                    </div>
                    <%--
                    <div class="category_select ml5 inline-block">
                        <label>主营类目:</label>
                        <div class="inline-block">
                            <select style="width: 92px;" class="js_select" name="category" id="category">
                                <option value="0">裤子</option>
                                <option value="1">鞋子</option>
                            </select>
                        </div>
                    </div>
                     --%>
                    <div class="experience_select ml5 inline-block">
                        <label>经营年限:</label>
                        <div class="inline-block">
                            <select style="width: 92px;" class="js_select" name="experience_year" id="experience_year">
                                <option value="0">不限</option>
                                <option value="1">1-3年</option>
                                <option value="2">3-5年</option>
                                <option value="3">5-10年</option>
                                <option value="4">10年以上</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="fr">
                    <a class="simple_button batch_accept" href="#">批量同意</a>
                    <a class="simple_button batch_reject" href="#">批量拒绝</a>
                </div>
            </div>

            <table class="list_table">
                <colgroup>
                    <col width="40" />
                    <col width="140" />
                    <col width="120" />
                    <col width="140" />
                    <col width="140" />
                    <col width="108" />
                    <col width="138" />
                    <col width="140" />
                </colgroup>
                <thead>
                    <tr>
                        <th></th>
                        <th>终端商</th>
                        <th>所在地区</th>
                        <th>主营类目</th>
                        <th>在营品牌</th>
                        <th>经营年限</th>
                        <th>申请时间</th>
                        <th class="last">操作</th>
                    </tr>
                </thead>
                <tbody id="enrolment_list">
                    
                </tbody>
            </table>
            <div class="pagination mt10" id="pagination">
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="bottom.jsp" %>
<script id="enrolment-templage" type="text/html">
   	     {{each rows}}
 				<tr>
                        <td>
                            <input type="checkbox" />
                        </td>
                        <td>{{$value.dealerUserm.userName}}</td>
                        <td>{{$value.dealerAreaNames}}</td>
                        <td>{{$value.dealDicNames}}</td> 
                        <td>{{$value.dealerbrandsName}}</td>
                        <td>{{$distanceDate $value.dealerInfo.foundTime}}</td>
                        <td>{{$formatDate $value.createTime}}</td>
                        <td class="last ta-c">
							 {{if $value.joinState==0}}
          			 			 <a class="link accept" href="#" data-userid="{{$value.userId}}" data-meetingid="{{$value.meetingId}}">同意</a>
                            	<a class="link reject" href="#" data-userid="{{$value.userId}}" data-meetingid="{{$value.meetingId}}">拒绝</a>
                       		 {{/if}}
							{{if $value.joinState==1}}
          			 			 <a class="link" href="#">已同意</a>
                       		 {{/if}}
							{{if $value.joinState==2}}
          			 			<a class="link" href="#">已拒绝</a>
							{{/if}}
                           </td>
                    </tr>
		 {{/each}}
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${res}/scripts/plugin/template-simple.js"></script>
<script>

    seajs.use(["pagination",'moment'], function (Pagination,moment) {
    	  template.helper('$formatDate', function (millsec) {
              return moment(millsec).format(" YYYY-MM-DD HH:mm:ss");
          });
    	  
    	  template.helper('$distanceDate', function (millsec) {
              return parseInt((new Date().getTime()-millsec)/1000/60/60/24/365);
          });
    	  
    	window['g_url']='${ctx}/brand/meeting/enrolment/data?meetingId=${brandMeeting.refrenceId}'; 
    	window['g_pagination']= new Pagination({
             url: "${ctx}/brand/meeting/enrolment/data?meetingId=${brandMeeting.refrenceId}",
             elem: "#pagination",
             form: $("#visitedForm"),
             method: "post",
             handleData: function (data) {
                 var html = template.render("enrolment-templage", data);
                 $('#enrolment_list').empty().append(html);
             }
         });
    });
    
    $(function () {
    	var g_last_area='';
    	$(document).on("change",'#enrolmentprovince,#enrolmentcity,#enrolmentcounty,#experience_year',function(){
    		if($(this).attr("id")!='experience_year'){
    			g_last_area=$(this).val();
    		}
    		g_pagination.config.url=g_url+'&areaNoStr='+g_last_area+'&expriStr='+$('#experience_year').val();
    		g_pagination.goTo(1);
    	});
		
    	$(document).on("click",".accept",function(){
    		$.post("${ctx}/brand/meeting/enrolment/accept",{dealerIds:$(this).data("userid"),meetingIds:$(this).data("meetingid")},function(data){
    			g_pagination.goTo(g_pagination.config.currentPage);
    		},"json");
    	});
    	
     	$(document).on("click",".reject",function(){
    		$.post("${ctx}/brand/meeting/enrolment/reject",{dealerIds:$(this).data("userid"),meetingIds:$(this).data("meetingid")},function(data){
    			g_pagination.goTo(g_pagination.config.currentPage);
    		},"json");
    	});
     	
    	$(document).on("click",".batch_accept",function(){
    		var dealerIds=[];
    		var meetingIds=[];
    		$('#enrolment_list tr:has(td input:checked)').each(function(i){
    			dealerIds.push($(this).find("td a:eq(0)").data("userid"));
    			meetingIds.push($(this).find("td a:eq(0)").data("meetingid"));
    		});
    		$.post("${ctx}/brand/meeting/enrolment/accept",{dealerIds:dealerIds.join(","),meetingIds:meetingIds.join(",")},function(data){
    			g_pagination.goTo(g_pagination.config.currentPage);
    		},"json");
    	});
    	
     	$(document).on("click",".batch_reject",function(){
    		var dealerIds=[];
    		var meetingIds=[];
    		$('#enrolment_list tr:has(td input:checked)').each(function(i){
    			dealerIds.push($(this).find("td a:eq(0)").data("userid"));
    			meetingIds.push($(this).find("td a:eq(0)").data("meetingid"));
    		});
    		$.post("${ctx}/brand/meeting/enrolment/reject",{dealerIds:dealerIds.join(","),meetingIds:meetingIds.join(",")},function(data){
    			g_pagination.goTo(g_pagination.config.currentPage);
    		},"json");
    	});
     	
     	
    });
</script>
</body>
</html>