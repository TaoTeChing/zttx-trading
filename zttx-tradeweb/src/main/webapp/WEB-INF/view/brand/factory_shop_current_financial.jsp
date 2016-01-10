<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商财务账</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/factory.css" />
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">终端商财务账</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <!-- 面包屑结束 -->
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <form:form class="ui-form factory-seeking-form clearfix" id="provite">
                <input type="hidden" name="dealerId" value="${dealerInfo.refrenceId}">

                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        终端商名称：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealerInfo.dealerName}" style="width: 210px;" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        联系人姓名：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealerInfo.connectUserName}" style="width: 210px;" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        联系方式：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealerInfo.userMobile}" style="width: 210px;" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        所在地：
                    </label>
                    <input class="ui-input" type="text" disabled value="${fns:getFullAreaNameByNo(dealerInfo.areaNo)}" style="width: 210px;" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        日期：
                    </label>
                    <input type="text" name="startTime" class="ui-input Wdate"
                           id="start-cal" readonly style="width: 90px;"/> 至
                    <input type="text" name="endTime" class="ui-input Wdate"
                           id="end-cal" readonly style="width: 90px;"/>
                </div>
                <div class="ui-form-item" style="width: 100px;margin-left: 20px;">
                    <div class="inline-block">
                        <input type="button" value="搜索" class="ui_button_myourself event-query">
                    </div>
                </div>
            </form:form>

            <table class="factory-count-table ui-table mt20">
                <colgroup>
                    <col width="200"/>
                    <col width="300"/>
                    <col width="205"/>
                    <col width="300"/>
                </colgroup>
                <thead>
                <tr>
                    <th>时间</th>
                    <th>应收金额（元）</th>
                    <th>已收金额（元）</th>
                    <th>支付状态</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <div id="pagination"></div>
            <div class="factory-count-box">
                合计 当期总应收款:<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
                已收款:<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
                欠收款:<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="template_data" type="text/html">
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{else}}
    {{each rows}}
    <tr>
        <td>{{$formateDate $value.clearingTime}}</td>
        <td>{{$foramtePrcie $value.clearingAmount}}</td>
        <td>{{$foramtePrcie $value.hasClearingAmount}}</td>
        <td>
            {{if !$value.clearingStatus}}未结算{{/if}}
            {{if $value.clearingStatus}}已结算{{/if}}
        </td>
    </tr>
    {{/each}}
    {{/if}}
</script>
<script id="total_template_data" type="text/html">
    当页合计 当期总应收款:<em class="fn-rmb">￥</em><span class="consult">{{ $foramtePrcie object.allOwedPrice}}</span> &nbsp;|&nbsp;
    已收款:<em class="fn-rmb">￥</em><span class="consult">{{$foramtePrcie object.allPayPrice}}</span> &nbsp;|&nbsp;
    欠收款:<em class="fn-rmb">￥</em><span class="consult">{{$foramtePrcie object.allDebtPrice}}</span>
</script>
<script>
    var page;



    $(function () {
        seajs.use(["pagination","template","moment"],function(Pagination,template,moment){
            var renderController = function(){
                template.helper('$foramtePrcie',function(price){
                    if($.isNumeric(price)){
                        return price.toFixed(2);
                    }else{
                        return price;
                    }
                });
                template.helper('$formateDate',function(date){
                    return moment(date).format("YYYY-MM-DD");
                });
                page = new Pagination({
                    url:"${ctx}/brand/brandFinancial/currentFinancial/detail/data",
                    elem:"#pagination",
                    form:"#provite",
                    method:"post",
                    handleData:function(json)
                    {
                        console.log(json)
                        var html1= template.render("template_data",json);
                        var html2 = template.render("total_template_data",json);
                        $(".factory-count-table tbody").html(html1);
                        $(".factory-count-box").html(html2);

                    }
                });
            }
            renderController();
        });
        $(".event-query").click(function(){
            page.goTo(1);
        });
        //时间选取
        if($("#start-cal").length > 0 && $("#end-cal").length > 0){
            rangeCalendar("start-cal","end-cal");
        }
    });
</script>
</body>
</html>