<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>管理中心-终端商财务账</title>
  <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
  <link rel="stylesheet" href="${res}/styles/brand/factory.css" />
</head>
<body>
<div class="container">
  <c:if test="${param.isClient==null||param.isClient==false}">
  <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
  <div class="main layout">
    <!--侧边导航-->
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <!--主体内容-->
    <div class="main_con">
      <!--面包屑-->
      <div class="bread_nav">
        <div class="fl">
          <a class="link" href="${ctx}/brand/center">管理中心</a>
          <span class="arrow">&gt;&gt;</span>
          <span class="current">终端商财务账</span>
        </div>
        <div class="fr">
          <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
      </div>
      </c:if>
      <div class="inner">
        <form:form id="financial_brand_normal_form" name="pageForm" class="ui-form" onsubmit="return false;" style="padding-top:20px;">
          <div class="ui-form-item">
            <label class="ui-label">
              终端商名称：
            </label>
            <input type="text" class="ui-input" name="dealerName"/>
            <input type="button" class="ui_button ui_button_morange" style="vertical-align: -1px" value='搜 索' />
          </div>
        </form:form>
        <%--<div class="panel-tabs">
            <div class="panel-head">
                <ul class="clearfix">
                    <li class="js-order-status">财务账</li>
                </ul>
            </div>
        </div>--%>
        <table class="factory-count-table ui-table">
          <colgroup>
            <col width="125"/>
            <col width="125"/>
            <col width="125"/>
            <col width="125"/>
            <col width="125"/>
            <col width="125"/>
            <col width="70"/>
            <col width="95"/>
            <col width="95"/>
          </colgroup>
          <thead>
          <tr>
            <th>品牌商名称</th>
            <th colspan="3">订单/退款统计</th>
            <th colspan="3">财务数据</th>
            <th>当期欠付款</th>
            <th>操作</th>
          </tr>
          <tr>
            <th></th>
            <th>进行中订单金额</th>
            <th>已完成订单金额</th>
            <th>退款</th>
            <th>应付款</th>
            <th>已付款</th>
            <th>欠付款</th>
            <th></th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <!--financial_brand_normal_table_template-->
          </tbody>
        </table>
        <div id="pagination" style="padding-bottom: 20px;"></div>
        <%--<div class="factory-count-box mt10">
            合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            欠收退款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
            欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
        </div>--%>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="financial_brand_normal_table_template" type="text/html">
  {{each rows}}
  <tr>
    <td>{{$value.dealerName}}</td>
    <td>{{$value.totalInIngOrderPrice}} </td>
    <td>{{$value.totalFinishOrderPrice}}</td>
    <td>{{$value.totalRefundOrderPrice}}</td>
    <td>{{$value.totalProductPrice}}</td>
    <td>{{$value.totalPayPrice}}</td>
    <td>{{$value.totalDebtPrice}}</td>
    <td><a href="${ctx}/brand/brandFinancial/currentFinancial/detail?dealerId={{$value.dealerId}}">{{$value.allDebtPrice}}</a></td>
    <td>
      <a class="link" href="">付款</a><br />
      <a class="link" href='${ctx}/brand/brandFinancial/financial/detail?dealerId={{$value.dealerId}}'>查看详情</a><br />
      <a class="link" href="">申请退款</a><br />
    </td>
  </tr>
  {{/each}}
  {{if !rows || rows.length == 0 }}
  <tr>
    <td class="ta-c" colspan="9">暂无数据</td>
  </tr>
  {{/if }}
</script>
<script id="financial_brand_normal_total_template" type="text/html">
  <%-- {{if !object || object.length == 0 }}
    合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
         已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
         欠收退款：<em class="fn-rmb">￥</em><span class="consult">0.00</span>&nbsp;|&nbsp;
         欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
   {{else}}
   合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[0]}}</span>&nbsp;|&nbsp;
       已付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[1]}}</span>&nbsp;|&nbsp;
       欠收退款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[5]}}</span>&nbsp;|&nbsp;
       欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[4]}}</span>
    {{/if }}--%>
</script>
<script>
  /**初始分页控件*/
  seajs.use(["pagination","template"], function (Pagination,template) {
    template.helper('$formatPrice', function (price) {
      return price.toFixed(2);
    });

    var n_pages = new Pagination({
      url: "${ctx}/brand/brandFinancial/financial/data",                   /* ${ctx}/dealer/order/financial/data*/
      elem: "#pagination",
      form:$("#financial_brand_normal_form"),
      method:"post",
      handleData: function (json) {
        var html1 = template.render("financial_brand_normal_table_template",json);
        /* var html2 = template.render("financial_brand_normal_total_template",json);*/
        $(".factory-count-table tbody").html(html1);
        //$(".factory-count-box").html(html2);
      }
    });
    /**查询按键*/
    $(".ui_button").click(function(){
      n_pages.goTo(1);
    });
  });
</script>
</body>
</html>