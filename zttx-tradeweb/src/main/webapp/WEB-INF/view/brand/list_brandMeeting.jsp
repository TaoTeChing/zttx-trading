<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <c:set var="SUCCESS" value="<%=com.zttx.web.consts.BrandConst.SUCCESS.getCode()%>"></c:set>
    <title>管理中心-品牌管理-资讯管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css"/>
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <!-- 导航菜单(品牌商管理中心) -->
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
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <jsp:include page="${ctx}/WEB-INF/view/brand/show_brandNews_bar.jsp">
                <jsp:param name="m" value="2"/>
            </jsp:include>

            <div class="info_list_con">
                <div class="tool_bar clearfix">
                    <div class="add_btn">
                        <a href="edit">
                            <i class="iconfont">&#xe611;</i>新增发布会
                        </a>
                    </div>
                    <div class="status_select inline-block">
                        <label>状态:</label>
                        <!-- 搜索条件表单 -->
                        <div class="inline-block">
                            <form:form id="searchTermForm">
                                <label for="s_all"><input type="radio" name="auditState" value="" id="s_all" checked/>全部</label>
                                <label for="s_0"><input type="radio" name="auditState" value="0" id="s_0"/>未审核</label>
                                <label for="s_1"><input type="radio" name="auditState" value="1" id="s_1"/>审核通过</label>
                                <label for="s_2"><input type="radio" name="auditState" value="2" id="s_2"/>审核不通过</label>
                                <label for="s_3"><input type="radio" name="auditState" value="3" id="s_3"/>终止</label>
                                <label for="s_10"><input type="radio" name="auditState" value="10" id="s_10"/>过期</label>
                            </form:form>

                        </div>
                        <!-- end：搜索条件表单 -->
                    </div>
                </div>
                <!-- 列表显示区 -->
                <div id="pagination" class="mt10"></div>
                <!-- 页码 -->
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/plugin/template-simple.js"></script>
<script src="${res}/scripts/plugin/jquery-dateFormat.min.js"></script>
<!-- 列表模板 -->
<script id="feedback-templage" type="text/html">
    <table class="list_table" id="listContent">
        <colgroup>
            <col width="128"/>
            <col width="142"/>
            <col width="128"/>
            <col width="164"/>
            <col width="160"/>
            <col width="67"/>
            <col width="178"/>
        </colgroup>
        <thead>
        <tr>
            <th>发布时间</th>
            <th>品牌</th>
            <th>名称</th>
            <th>持续时间</th>
            <th>地点</th>
            <th>状态</th>
            <th class="last">操作</th>
        </tr>
        </thead>
        <tbody>
        {{each rows}}
        <tr class="{{$index%2==0 ? '':'odd'}}">
            <td>{{$formatDate $value.createTime}}</td>
            <td>{{$value.brandsName}}</td>
            <td>{{$value.meetName}}</td>
            <td>{{$formatDate $value.beginTime}}~{{$formatDate $value.endTime}}</td>
            <td>{{$value.meetAddress}}</td>
            <td>
                {{if $value.endTime < object.currentTime}}
                <span class="status status_finish" title="{{$value.auditWhy}}">已结束</span>
                {{else}}
                {{if $value.auditState==0}}
                <span class="status status_audit">未审核</span>
                {{/if}}
                {{if $value.auditState==1}}
                <span class="status status_on">审核通过</span>
                {{/if}}
                {{if $value.auditState==2}}
                <span class="status status_audit" title="{{$value.auditWhy}}">审核不通过</span>
                {{/if}}
                {{if $value.auditState==3}}
                <span class="status status_audit">终止</span>
                {{/if}}
                {{/if}}
            </td>
            <td class="last" style="text-align: center;">
                <a class="link" href="/meet/detail?refrenceId={{$value.refrenceId}}" target="_blank">查看</a>
                {{if ($value.auditState==0||$value.auditState==2)　&& $value.endTime > object.currentTime}}
                <a class="link" href="edit?refrenceId={{$value.refrenceId}}">修改</a>
                <a id="{{$value.refrenceId}}" class="link" href="javascript:stopBrandMeeting('{{$value.refrenceId}}');">终止</a>
                {{/if}}
                {{if $value.auditState==1 && $value.beginTime > object.currentTime}}
                <a id="{{$value.refrenceId}}" class="link" href="javascript:stopBrandMeeting('{{$value.refrenceId}}');">终止</a>
                {{/if}}
            </td>
        </tr>
        {{/each}}
        {{ if rows.length == 0 }}
        <tr>
            <td colspan="7" class="last" style="text-align:center;">暂无数据</td>
        </tr>
        {{ /if }}
        </tbody>
    </table>
</script>
<script type="text/javascript">
    function stopBrandMeeting(refrenceId) {
        $.ajax({
            type: "POST",
            url: "${ctx}/brand/meeting/stop",
            data: {"refrenceId": refrenceId},
            dataType: "json",
            success: function (json) {
                if (json.code == zttx.SUCCESS) {
                    $("#" + refrenceId).parent().prev().children("span").removeClass();
                    $("#" + refrenceId).parent().prev().children("span").addClass("status status_audit");
                    $("#" + refrenceId).parent().prev().children("span").text("终止");
                    $("#" + refrenceId).prev().remove();
                    $("#" + refrenceId).remove();
                } else {
                    remind("error", json.message);
                }
            }
        });
    }

    // ajax 分页 =============================================================================
    var page;

    $(function () {
        template.helper('$formatDate', function (millsec) {
            return $.format.date(new Date(millsec), 'yyyy-MM-dd');
        });
        seajs.use(["pagination"], function (Pagination) {
            var renderPagination = function () {
                page = new Pagination({
                    /************** 需要修改 *************/
                    url: "${ctx}/brand/meeting/ajaxList",
                    elem: "#pagination",
                    form: $("#searchTermForm"),
                    method: "post",
                    handleData: function (json) {
                        var html = template.render("feedback-templage", json);
                        $("#listContent").remove();
                        $("#pagination").before($(html));
                    }
                });
            };
            renderPagination();
        });
        $("#searchTermForm input").change(function () {
            page.goTo(1);
        });
    });
</script>
</body>
</html>