<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>管理中心-库存明细</title>
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
    <div class="main clearfix">
      <jsp:include page="/common/menuInfo/sidemenu"/>
      <div class="main-right">
        <div class="main-grid mb10">
          <div class="panel-crumbs">
            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> > <span class="bb">返点产品调价明细</span><span class="arrow"> >> </span><span class="current">库存明细</span>
            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
          </div>
        </div>
        <div class="inner">
          <!-- 内容都放这个地方 -->
          <form:form id="favorite" name="" class="ui-form factory-seeking-form clearfix">
            <div class="ui-form-item">
              <label for="" class="ui-label">品牌商名称： </label>
              <input class="ui-input" type="text" disabled id="" style="width: 190px;"
                     value="${brandInfo.brandName}"/>
            </div>
            <div class="ui-form-item">
              <label for="" class="ui-label">联系人姓名：</label>
              <input class="ui-input" type="text" disabled id="" style="width: 190px;"
                     value="${brandInfo.connectUserName}"/>
            </div>
            <div class="ui-form-item">
              <label for="" class="ui-label">联系方式：</label>
              <input class="ui-input" type="text" disabled id="brandMobile" style="width: 190px;"
                     value="${brandInfo.userMobile}"/>
            </div>
            <div class="ui-form-item">
              <label for="" class="ui-label">所在地：</label>
              <input class="ui-input" type="text" disabled id="address" style="width: 190px;"
                     value="${fns:getFullAreaNameByNo(brandInfo.areaNo)}"/>
            </div>
            <div class="ui-form-item">
              <label for="" class="ui-label">产品货号：</label>
              <input class="ui-input" type="text" style="width: 190px;" value="" name="productNo"/>
            </div>
            <div class="ui-form-item">
              <label for="" class="ui-label">产品名称：</label>
              <input class="ui-input" type="text" style="width: 190px;" value="" name="productTitle"/>
            </div>
            <div class="ta-r fr mt10">
              <input type="button" value="搜索"
                     class="ui-button ui-button-mwhite event-query" style="margin-right: 35px;">
            </div>
          </form:form>
          <div class="panel-tabs">
            <div class="panel-head">
              <ul>
                <li class="long">
                  <a href="${ctx}/dealer/dealerFinancialPoint/stockCountByDate?brandId=${param.brandId}" class="fs14 yahei nomalf"><i class="iconfont"></i> 按日期查看</a>
                </li>
                <li class="long">
                  <a href="#" class="fs14 yahei nomalf active"><i class="iconfont"></i> 按产品查看</a>
                </li>
              </ul>
            </div>
          </div>
          <%-- 按产品查看 --%>
          <div>
            <table class="factory-count-table ui-table mt10">
              <colgroup>
                <col width="120"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
                <col width="111"/>
              </colgroup>
              <thead>
              <tr>
                <th>品牌</th>
                <th>产品名称</th>
                <th>产品货号</th>
                <th>颜色/尺码</th>
                <th>产品类型变更数</th>
                <th>发货数</th>
                <th>销售数</th>
                <th>退货数</th>
                <th>库存数</th>
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
  <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="data-template" type="text/html">
  {{if null||length==0}}
  <tr>
    <td colspan="9">暂无数据！</td>
  </tr>
  {{else}}
  {{each}}
  <tr>
    <td>{{$value.brandsName}}</td>
    <td>{{$value.productTitle}}</td>
    <td>{{$value.productNo}}</td>
    <td>{{$value.productSkuName}}</td>
    <td>{{$value.baseStock}}</td>
    <td>{{$value.sendNum}}</td>
    <td>{{$value.saleNum}}</td>
    <td>{{$value.refundNum}}</td>
    <td>
      <a href="${ctx}/dealer/dealerFinancialPoint/stockCountBySkuDetail?brandId=${param.brandId}&&productSkuId={{$value.productSkuId}}" class="link">{{$value.stockSum}}</a></td>
  </tr>
  {{/each}}
  {{/if}}
</script>

<script id="count-template" type="text/html">
  <div class="fr">
    合计:总库存量
        <span class="consult" style="color: #f00;">
        {{if stockSum!=null}}{{stockSum}}{{else}}0{{/if}}
        </span>&nbsp;
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
      template.helper('$chooseType', function (source) {
        switch (source) {
          case 0:
            return "采购";
          case 1:
            return "销售";
          case 2:
            return "退货";
          case 3:
            return "授信转返点";
        }
      });
      window['g_pagination'] = new Pagination({
        url: "${ctx}/dealer/dealerFinancialPoint/stockCountByProductSku/data",
        elem: "#pagination",
        form: "#favorite",
        data: {brandId: "${param.brandId}"},
        method: "post",
        handleData: function (json) {
          if (json.code == 126000) {
            var html1 = template.render("data-template", json.rows);
            var html2 = template.render("count-template", json.object);
            $(".factory-count-table tbody").html(html1);
            $(".factory-count-box").html(html2);
          } else {
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