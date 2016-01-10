<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-终端商管理-交易会管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
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
        <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
            <jsp:param name="m" value="1" />
        </jsp:include>
	    <div class="inner">
	        <div class="info_list_con">
	            <div class="tool_bar clearfix">
	                <%-- <div class="add_btn">
	                    <a href="${ctx}/brand/tradeMeeting/edit"><i class="iconfont">&#xe611;</i>新增交易会</a>
	                </div> --%>
	                <div class="fr" style="display:none;">
	                    <div class="status_select inline-block">
	                        <label>状态:</label>
	                        <!-- 搜索条件表单 -->
	                        <div class="inline-block">
	                            <select class="js_select" id="auditState">
	                            	<option value="">--请选择--</option>
	                                <option value="0">未审核</option>
	                                <option value="1">审核通过</option>
	                                <option value="2">审核不通过</option>
	                                <option value="3">终止</option>
	                            </select>
	                        </div>
							<!-- end：搜索条件表单 -->                        
	                    </div>
	                </div>
	            </div>
	            <!-- 列表显示区 -->
	            <table class="list_table" id="listContent">
					<colgroup>
					    <col width="110" />
					    <col width="227" />
					    <col width="258" />
					    <col width="192" />
					    <!-- <col width="67" /> -->
					    <col width="178" />
					</colgroup>
				    <thead>
						<tr>
						    <th>发布时间</th>
						    <th>品牌</th>
						    <th>名称</th>
						    <th>持续时间</th>
						    <!-- <th>状态</th> -->
						    <th class="last ta-c">操作</th>
						</tr>
				    </thead>
				    <tbody>
				    </tbody>
				</table>
				<div class="pageParent mt10" ></div><!-- 页码 -->
			</div>
	    </div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!-- 列表模板 --> 
<script id="ajax-templage-list" class="ajax-templage" _url="${ctx}/brand/tradeMeeting/ajaxList" _page=".pageParent" _pid="#listContent tbody" type="text/html">
{{each rows}}
<tr class="{{$index%2==0 ? '':'odd'}}">
	<td>{{$formatDate $value.createTime}}</td>
	<td>{{$value.brandsName}}</td>
	<td>{{$value.meetName}}</td>
	<td>{{$formatDate $value.beginTime}} ~ {{$formatDate $value.endTime}}</td>


	<!--<td>
		{{if $value.endTime < object.currentTime}}
			<span class="status status_finish">已结束</span>
		{{else}}
			{{if $value.auditState==0}}
				<span class="status status_audit">未审核</span>
			{{/if}}
			{{if $value.auditState==1}}
				<span class="status status_on">审核通过</span>
			{{/if}}
			{{if $value.auditState==2}}
				<span class="status status_audit">审核不通过</span>
			{{/if}}
			{{if $value.auditState==3}}
				<span class="status status_audit">终止</span>
			{{/if}}
		{{/if}}
	</td>-->


	<td class="last" style="text-align: center;">
		<a class="link" href="http://{{$value.domain}}${zttx}/deal?meetId={{$value.refrenceId}}" target="_blank">查看</a>
		<a class="link" href="${ctx}/brand/tradeMeeting/enrollment?refrenceId={{$value.refrenceId}}" target="_blank">报名情况</a>


     <!--  <input name="noShow" type="checkbox" data-id="{{$value.refrenceId}}" {{if ($value.noShow == 1)}} checked {{/if}}  /><a class="link" href="">不发布</a>

		{{if ($value.auditState==0||$value.auditState==2)&&($value.endTime >= object.currentTime)}}
			<a class="link" href="${ctx}/brand/tradeMeeting/edit?refrenceId={{$value.refrenceId}}">修改</a>
		{{/if}}
		{{if ($value.endTime > object.currentTime)&&($value.auditState!=3)}}
			<a id="js-stopButton" class="link" data-rid="{{$value.refrenceId}}" href="javascript:void(0);" >终止</a>
		{{/if}}-->



	</td>
</tr>
{{/each}}
{{ if rows.length == 0 }}
	<tr>
		<td colspan="5" class="last" style="text-align:center;">暂无数据</td>
	</tr>
{{ /if }}
</script>
<script type="text/javascript">
var pageTemp, tradeMeeting;
$(function(){
	seajs.use(["pagination", "template", "validator", "moment"], function (Pagination, template, Validator, moment){
		
		template.helper('$formatDate', function (millsec) {
			return moment(millsec).format("YYYY-MM-DD");
		});
		
		tradeMeeting = {
			// 初始化
			init: function(){
				$("#auditState").bind("change", function(){
					var auditState = $(this).val();
					tradeMeeting.ajaxTemplate("ajax-templage-list", {auditState: auditState});
				});
				$(document).on('click','#js-stopButton',function(){
					var id = $(this).data("rid");
					tradeMeeting.stop(id);
				});
				
				$(document).on('click','',function(){
				
				});
			},
			// 分页
			ajaxTemplate: function(tempId, data){
				var $this = $("#" + tempId);
				pageTemp = new Pagination({
					url: $this.attr("_url"),
					data: data,
					elem: $this.attr("_page"),
					method: "post",
					handleData: function (data) {
					console.log(data);
						var html = template.render(tempId, data);
						$($this.attr("_pid")).html(html);
					}
				});
			},
			// 终止
			stop: function(refrenceId){
				$.post("${ctx}/brand/tradeMeeting/stop", {refrenceId: refrenceId}, function(data){
					if(data.code == zttx.SUCCESS)
					{
						remind("success", "终止成功");
						var page = pageTemp;
						page.goTo(page.current);
					}
					else
					{
						remind("error", data.message);
					}
				}, "json");
			}
		}
		
		tradeMeeting.init();
		
		var auditState = "${param.auditState}";
		$("#auditState").val(auditState).change();
	});
	
    $("#listContent").on("click","input[name=noShow]",function(){
        
        var _id = $(this).data("id");
        var _checkedNum;
        if($(this).prop("checked")){
            _checkedNum = 1;
        }else{
            _checkedNum = 0;
        }
        $.post("${ctx}/brand/tradeMeeting/noShow",{refrenceId :_id , noShow : _checkedNum },function(data){
            
             if(data ==126000){
             
               /*
                                                        修改成功！
               */
             
             }
        
        
        },"JSON");
      /*   $.ajax({
           
            url:"${ctx}/brand/tradeMeeting/noShow",
            data:"refrenceId="+_id +"&noShow="+_checkedNum,
            dataType:"json",
            success:function(data){
                
                alert("修改成功！");
               
            }
        }); */
    });
});



</script>
</body>
</html>