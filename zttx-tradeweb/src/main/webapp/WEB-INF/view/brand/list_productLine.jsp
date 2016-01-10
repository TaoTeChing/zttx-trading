<%--
  @(#)list_ProductLine.jsp 14-3-18 下午2:50
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品线管理-产品线授权</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/prolinemaneg.css"/>
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <!-- 内容都放这个地方  -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/line/execute">产品线管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">产品线授权</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
            </div>
        </div>
        <div class="inner">
            <form:form id="search-form" class="ui-form proline-warrant-form clearfix" action="${ctx}/brand/line/list"
                       method="get">
                <div class="ui-form-item">
                    <label for="">
                        选择品牌：
                    </label>

                    <div class="inline-block">
                        <select id="sel-brandsId" class="ui-select js_select pl-warrant-selectw1" name="brandsId">
                            <option value="" ${empty filter.brandsId ? 'selected="selected"':''}>全部品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.refrenceId}" ${filter.brandsId == brand.refrenceId ? 'selected="selected"':''}>${brand.brandName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <%--
                <div class="ui-form-item">
                    <label for="">
                        终端商等级：
                    </label>

                    <div class="inline-block">
                        <select id="sel-level" class="ui-select js_select pl-warrant-selectw2"  name="dealerLv">
                            <option value="">全部终端商等级</option>
                        </select>
                        <c:forEach items="${brandLevels}" var="brandLevel">
                            <script id="${brandLevel.refrenceId}-lvls" type="text/html">
                                <option value="">全部终端商等级</option>
                                    <c:forEach items="${brandLevel.levels}" var="level">
                                        <option value="${level.refrenceId}">${level.levelName}</option>
                                    </c:forEach>
                            </script>
                        </c:forEach>
                    </div>
                </div>
                --%>
                <div class="ui-form-item">
                    <label for="">
                        产品线名称：
                    </label>
                    <input name="lineName" type="text" class="ui-input" value="${filter.lineName}"/>
                </div>
                <div class="inline-block">
                    <input type="button" value="搜索" class="warrant-form-btn" onclick="page.goTo(1);"/>
                </div>
            </form:form>
            <div class="addproline-line">
                <a href="${ctx}/brand/line/execute" class="addproline-btn inline-block">
                    <i class="iconfont">&#xe611;</i>添加产品线
                </a>
            </div>
            <div class="proline-contant">
                <%-- <div class="checkallbox clearfix">
                    <div class="inline-block fl">
                        <input type="checkbox" id="select-all">
                    </div>
                    <label class="fl" for="select-all">全选</label>
                    <a id="delete-all" class="checkstyle fl">批量删除</a>
                    <form:form id="delete-form" cssStyle="display: none" action="${ctx}/brand/line/delete" method="post">

                    </form:form>
                </div> --%>
                <table id="line-table" class="proline-table">
                </table>
                <div id="pagination" class="pagination"></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script type="text/javascript" src="${res}/scripts/brand/prolinemanage.js"></script>
<script id="line-template" type="text/html">
    <colgroup>
        <%--<col width="35"/>--%>
        <col width="145"/>
        <col width="100"/>
        <col width="100"/>
        <col width="110"/>
        <%--<col width="90"/>--%>
        <col width="130"/>
        <col width="130"/>
        <col width="120"/>
        <col width="170"/>
    </colgroup>
    <thead>
    <tr>
        <%--<th></th>--%>
        <th><span>品牌</span></th>
        <th><span>产品线名称</span></th>
        <th><span>工厂店产品线</span></th>
        <th><span>折扣率（折）</span></th>
        <%--<th><span>终端商等级</span></th>--%>
        <th><span>已加入产品（款）</span></th>
        <th><span>已有终端商（个）</span></th>
        <th><span>所在地区（个）</span></th>
        <th><span>操作</span></th>
    </tr>
    </thead>
    {{each rows}}
    <tr>
        <%-- <td>
             <input name="lineId" type="checkbox" value="{{$value.id}}"/>
         </td>--%>
        <td class="proline-td-tl">{{$value.brandsName}}</td>
        <td class="proline-td-tl">
            <div class="fn-text-overflow" style="width: 100px;" title="{{$value.lineName}}">{{$value.lineName}}</div>
        </td>
        <td>
            {{if $value.factoryStore == true}}是{{else}}否{{/if}}
        </td>
        <td>{{$value.lineAgio}}折</td>
        <%--<td>特级终端商</td>--%>
        <td>
            <strong class="bluefont">{{$value.productsCount}}</strong>
        </td>
        <td>
            <strong class="bluefont">{{$value.dealersCount}}</strong>
        </td>
        <td>
            <strong class="bluefont">{{$value.areaCount}}</strong>
        </td>
        <td>
            <a href="${ctx}/brand/line/{{$value.id}}" class="bluefont">查看</a>
            <a href="${ctx}/brand/line/execute?id={{$value.id}}" class="bluefont">修改</a>
            <a href="${ctx}/brand/line/{{$value.id}}/unadd" class="bluefont">添加产品</a>
            {{if $value.factoryStore != true}}
            <a href="javascript:void(0)" class="bluefont delete" data-id="{{$value.id}}">删除</a>
            {{/if}}
        </td>
    </tr>
    {{/each}}
    {{if rows.length == 0}}
    <tr>
        <td colspan="7">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script type="text/javascript">
    $(function () {
        warrant.init();
        var $form = $('#delete-form');
        $('#line-table').on('click', 'a.delete', function () {
            var $this = $(this);
            var id = $.trim($this.attr("data-id"));
            confirmDialog("确认删除产品线？", function () {
                //$form.html("");
                //$form.append($('<input type="hidden" name="ids">').val(id)).submit();
                $.post("${ctx}/brand/line/delete", "id=" + id, function (data) {
                    if (data.code == zttx.SUCCESS) {
                        remind("success", "产品线删除成功");
                        page.goTo(1);
                    }
                    else {
                        remind("error", data.message);
                    }
                }, "json");
            });
        });
        $('#select-all').click(function () {
            $('input[name="lineId"]').prop("checked", this.checked);
        });

        /*
         var $selBrandsId = $('#sel-brandsId');
         var $selectLevel = $('#sel-level');
         $selBrandsId.change(function (event) {
         var bid = $selBrandsId.val();
         if (bid == '') {
         $selectLevel.empty().append('<option value="">全部终端商等级</option> ');
         $selectLevel.val('');
         renderSelect('#sel-level');
         } else {
         $selectLevel.empty().append($('#' + bid + '-lvls').html());
         $selectLevel.val('');
         renderSelect('#sel-level');
         }
         });
         */
        addlineONE.init();
    });
</script>
<script>
    var page;
    $(function () {
        seajs.use(["pagination", "template"], function (Pagination, template) {
            var renderPagination = function () {
                page = new Pagination({
                    url: "${ctx}/brand/line/list.json",
                    elem: "#pagination",
                    form: $("#search-form"),
                    method: "post",
                    handleData: function (data) {
                        var html = template.render('line-template', data);
                        $("#line-table").html(html);
                    }
                });
            };
            renderPagination();
        });
    });
</script>
</body>
</html>
