<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退款退货管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/common.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/complain_refund.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container">
        <jsp:include page="header.jsp" />
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                <div class="main-left fl">
                    <jsp:include page="menu.jsp" />
                </div>
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
                            <i class="icon i-setting"></i><a href="/brand/center">管理中心</a> >> <span class="bb">退款管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb10">
                        <form class="ui-form">
                            <div class="ui-form-row">
                                <div class="ui-form-item">
                                    <label class="ui-label">订单编号:</label><input class="ui-input" style="width:180px;" />
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">退款申请时间:</label><input class="ui-input date date-start" style="width:120px;" /> - <input class="ui-input date date-end" style="width:120px;" />
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">退款编号:</label><input class="ui-input" style="width:180px;" />
                                </div>
                            </div>

                            <div class="ui-form-row">
                                <div class="ui-form-item">
                                    <label class="ui-label">退款状态:</label><select class="ui-select" style="width:150px;"><option>全部</option><option>退款中</option></select>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">客服介入:</label><select class="ui-select" style="width:150px;"><option>全部</option><option>客服介入中</option></select>
                                    <button class="ui-button ui-button-mwhite">搜 索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="main-grid mb10">
                        <div class="ui-table-container">
                            <table class="ui-table ui-table-inbox">
                                <colgroup>
                                    <col class="tb10" />
                                    <col class="tb10" />
                                    <col class="tb15" />
                                    <col class="tb10" />
                                    <col class="tb10" />
                                    <col class="tb15" />
                                    <col class="tb15" />
                                    <col class="tb10" />
                                    <col class="tb5" />
                                </colgroup>
                                <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                <thead>
                                    <tr>
                                        <th>退款编号</th>
                                        <th>订单号</th>
                                        <th>品牌商</th>
                                        <th>交易金额(元)</th>
                                        <th>退款金额(元)</th>
                                        <th>退款申请时间</th>
                                        <th>退款完成时间</th>
                                        <th>退款状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead><!-- 表头可选 -->
                                <tbody>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-r">纠纷处理中</span></td>
                                        <td><a href="Purchases-dispute-3.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-g">退款成功</span></td>
                                        <td><a href="Purchases-refund-1-2.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-ch">退款关闭</span></td>
                                        <td><a href="Purchases-refund-1-close.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-r">拒绝退款</span></td>
                                        <td><a href="Purchases-complain-3.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-r">请退货</span></td>
                                        <td><a href="Purchases-refund-2-2.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-r">申请退款中</span></td>
                                        <td><a href="Purchases-refund-2-1.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-ch">退款关闭</span></td>
                                        <td><a href="Purchases-refund-1-close.jsp" class="link">查看</a></td>
                                    </tr>
                                    <tr>
                                        <td>123456</td>
                                        <td>654321</td>
                                        <td>朵彩内衣</td>
                                        <td>123456.00</td>
                                        <td>123456.00</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td>2014-12-12 12:12:12</td>
                                        <td><span class="c-ch">退款关闭</span></td>
                                        <td><a href="Purchases-refund-1-close.jsp" class="link">查看</a></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="mt10">
                                <div class="pagination">
                                    <a class="prev" href="javascript:;">上一页</a>
                                    <a class="page current" href="javascript:;">1</a>
                                    <a class="page" href="javascript:;">2</a>
                                    <a class="next" href="javascript:;">下一页</a>
                                    <span class="total">2/24页</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div><jsp:include page="footer.jsp" />
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>

        seajs.use(['calendar', 'calendar_style'], function (Calendar) {

            new Calendar({ trigger: '.date-start' });
            new Calendar({ trigger: '.date-end' });

        });

        $(function () {
            //表格样式
            $(".ui-table tr").find("td:last,th:last").css("border-right", 0);
            $(".ui-table tbody tr:odd").addClass("ui-table-split");


            var checkbox = $("td .ui-checkbox");
            $('#checkAll').click(function () {
                var checked = $(this)[0].checked;
                checkbox.each(function (i, o) {
                    o.checked = checked;
                });
            });

            $('#btn_remove').click(function () {

                if (confirm('确认删除吗？')) {

                    checkbox.each(function (i, o) {
                        if (o.checked) {
                            $(o).parent().parent().remove();
                        }
                    });
                }
            });

        });
    </script>

    <!--[if IE 6]>
    <script src="${res}/scripts/DD_belatedPNG.js"></script>
    <script>
        DD_belatedPNG.fix(".logo a,.icon");
    </script>
    <![endif]-->
</body>
</html>
