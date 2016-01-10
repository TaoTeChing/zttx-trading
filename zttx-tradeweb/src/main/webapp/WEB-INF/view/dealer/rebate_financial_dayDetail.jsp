<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>管理中心-终端商财务帐-返点财务帐</title>
  <link rel="stylesheet" href="${res}/styles/dealer/global.css"/>
  <link rel="stylesheet" href="${res}/styles/dealer/factory.css"/>
  <style>
    .ui-form-item{display: inline-block;*display: inline;*zoom: 1;padding-left: 100px;}
    .ui-label{width: 100px;margin-left: -100px;}
  </style>
</head>
<body>
<div class="container">
  <jsp:include page="/common/menuInfo/mainmenu"/>
  <div class="em100">
    <div class="main clearfix pr">
      <jsp:include page="/common/menuInfo/sidemenu"/>
      <div class="main-right">
        <!--面包屑-->
        <div class="main-grid mb10">
          <div class="panel-crumbs">
            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">返点明细</span>
            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
          </div>
        </div>
        <div class="inner">
          <!-- 内容都放这个地方 -->
          <form:form id="favorite" name="" class="ui-form factory-seeking-form" onsubmit="return false;" style="padding-top:20px;">
            <div class="ui-form-item inline-block">
              <label class="ui-label">
                产品名称：
              </label>
              <input type="text" class="ui-input" name="productTitle"/>
            </div>
            <div class="ui-form-item inline-block">
              <label class="ui-label">
                产品货号：
              </label>
              <input type="text" class="ui-input" name="productNo"/>
            </div>
            <div class="ui-form-item inline-block" style="padding-left: 20px;">
              <input type="button" class="ui-button ui-button-mwhite event-query" style="vertical-align: -1px"
                     value='搜 索'/>
            </div>
          </form:form>
          <div>
            <table class="factory-count-table ui-table">
              <colgroup>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
                <col width="91"/>
              </colgroup>
              <thead>
              <tr>
                <th>产品货号</th>
                <th>产品名称</th>
                <th>颜色/尺码</th>
                <th>销量</th>
                <th>销售额</th>
                <th>退货数</th>
                <th>退货金额</th>
                <th>盘亏数量</th>
                <th>盘亏成本</th>
                <th>销售成本</th>
                <th>合计成本</th>
              </tr>
              </thead>
              <tbody>

              </tbody>
            </table>
            <div id="pagination"></div>
            <div class="factory-count-box clearfix" style="font-size: 12px;"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="data-template" type="text/html">
  {{if null || length ==0 }}
  <tr>
    <td colspan="11">暂无数据！</td>
  </tr>
  {{else}}
  {{each}}
  <tr>
    <td>{{$value.productNo}}</td>
    <td>{{$value.productTitle}}</td>
    <td>{{$value.productSkuName}}</td>
    <td>{{$value.saleNum}}</td>
    <td>{{$value.salePrice}}</td>
    <td>{{$value.refundNum}}</td>
    <td>{{$value.refundPrice}}</td>
    <td>{{$value.lossNum}}</td>
    <td>{{$value.lossCost}}</td>
    <td>{{$value.saleCost}}</td>
    <td>{{$value.countCost}}</td>
  </tr>
  {{/each}}
  {{/if}}
</script>
<script id="count-template" type="text/html">
  <div class="fr">
    合计成本:
        <span class="consult" style="color: #f00;">
        {{if count!=0}}{{ $formatPrice sumCountCost}}{{else}}0.00{{/if}}
        </span>&nbsp;
    共 <span class="consult" style="color: #f00;">{{count}}</span> 条
  </div>

</script>

<script>
  $(function () {
    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
      template.helper('$formatPrice', function (price) {
        if (isNaN(price)) {
          return price;
        } else {
          return price.toFixed(2);
        }
      });
      template.helper('$formatDate', function (millsec) {
        return moment(millsec).format("YYYY-MM-DD HH:mm:00");
      });
      template.helper('$getLocalTime', function (millsec) {
        return moment(millsec).format("YYYY-MM-DD HH:mm:00");
      });

      window['g_pagination'] = new Pagination({
        url: "${ctx}/dealer/dealerFinancialPoint/saleDetail/data",
        elem: "#pagination",
        form: "#favorite",
        data: {brandId: "${param.brandId}", erpTime: "${param.erpTime}"},
        method: "post",
        pageSize: 20,
        handleData: function (json) {
          if(json.code==126000)
          {
            var html1 = template.render("data-template", json.rows);
            var html2 = template.render("count-template", json.object);
            $(".factory-count-table tbody").html(html1);
            $(".factory-count-box").html(html2);
          }else{
            remind("error", json.message);
          }
        }
      });
    });
    $(".event-query").click(function () {
      g_pagination.goTo(1);
    });
  });

</script>

</body>
</html>