<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>管理中心-产品管理-返点产品调价明细</title>
  <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
  <link rel="stylesheet" href="${res}/styles/brand/factory.css"/>
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
  <jsp:include page="/common/menuInfo/sidemenu"/>
  <div class="main_con">
    <div class="bread_nav">
      <div class="fl">
        <a class="link" href="${ctx}/brand/center">管理中心</a>
        <span class="arrow">&gt;&gt;</span>
        <span>终端商财务账</span>
        <span class="arrow">&gt;</span>
        <span class="current">库存明细</span>
      </div>
      <div class="fr">
        <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
      </div>
    </div>
    <div class="inner">
      <!-- 内容都放这个地方 -->
      <form:form id="favorite" name="" class="ui-form factory-seeking-form clearfix">
        <div class="ui-form-item">
          <label for="" class="ui-label">品牌名称： </label>
          <input class="ui-input" type="text" disabled value="${headMap.brandsName}" style="width: 210px;"/>
        </div>
        <div class="ui-form-item">
          <label for="" class="ui-label">产品名称：</label>
          <input class="ui-input" type="text" disabled value="${headMap.productTitle}" style="width: 210px;"/>
        </div>
        <div class="ui-form-item">
          <label for="" class="ui-label">产品货号：</label>
          <input class="ui-input" type="text" disabled value="${headMap.productNo}" style="width: 210px;"/>
        </div>
        <div class="ui-form-item">
          <label for="" class="ui-label">颜色/尺码：</label>
          <input class="ui-input" type="text" disabled value="${headMap.productSkuName}" style="width: 210px;"/>
        </div>
        <div class="ui-form-item">
          <label for="" class="ui-label"> 类型：</label>
          <div class="inline-block pr">
            <select class="js_select" name="source" style="width:220px;height: 30px;">
              <option value="">请选择</option>
              <c:forEach var="type" items="${headMap.type}">
                <option value="${type.value}">${type.key}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="ta-r fr mt10">
          <input type="button" value="搜索" class="ui_button_myourself event-query" style="margin-right: 35px;">
        </div>
      </form:form>
      <div>
        <table class="factory-count-table mt10">
          <colgroup>
            <col width="256"/>
            <col width="251"/>
            <col width="251"/>
            <col width="251"/>
          </colgroup>
          <thead>
          <tr>
            <th>时间</th>
            <th>单号</th>
            <th>类型</th>
            <th>数量</th>
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
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script id="data-template" type="text/html">
  {{if null||length==0}}
  <tr>
    <td colspan="4">暂无数据！</td>
  </tr>
  {{else}}
  {{each}}
  <tr>
    <td>{{$formatDate $value.createTime}}</td>
    <td>{{$value.orderNo}}</td>
    <td>{{$chooseType $value.source}}</td>
    <td>{{if $value.source==0}}{{$value.sendNum}}{{else if $value.source==1}}{{$value.saleNum}}{{else if $value.source==2}}{{$value.refundNum}}{{else if $value.source==3}}{{$value.baseStock}}{{/if}}</td>
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
      template.helper('$chooseType',function(source){
        switch(source)
        {
          case 0: return "采购";
          case 1: return "销售";
          case 2: return "退货";
          case 3: return "授信转返点";
        }
      });
      window['g_pagination'] = new Pagination({
        url: "${ctx}/brand/brandFinancialPoint/stockCountBySkuDetail/data",
        elem: "#pagination",
        form: "#favorite",
        data:{dealerId:"${param.dealerId}",productSkuId:"${param.productSkuId}"},
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