<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>管理中心-产品管理-返点产品调价明细</title>
  <link rel="stylesheet" href="${res}/styles/dealer/global.css"/>
  <link rel="stylesheet" href="${res}/styles/dealer/factory.css"/>
</head>
<body>
<div class="container">
  <%--<jsp:include page="/common/menuInfo/mainmenu"/>--%>
  <div class="em100">
    <div class="main clearfix">
      <%--<jsp:include page="/common/menuInfo/sidemenu"/>--%>
      <div class="main-right">
        <!--面包屑-->
        <div class="main-grid mb10">
          <div class="panel-crumbs">
            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">返点产品调价明细</span>
            <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
          </div>
        </div>
        <div class="inner">
          <!-- 内容都放这个地方 -->
          <div style="padding: 10px;">
            <table class="factory-count-table ui-table">
              <colgroup>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
                <col width="125"/>
              </colgroup>
              <thead>
              <tr>
                <th>产品货号</th>
                <th>产品名称</th>
                <th>颜色/尺码</th>
                <th>销量数</th>
                <th>退货数</th>
                <th>盘亏数</th>
                <th>返点价</th>
                <th>返点比例</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>001_1</td>
                <td>名称</td>
                <td>红色/XL</td>
                <td>100</td>
                <td>10</td>
                <td>100</td>
                <td>10.00</td>
                <td>15%</td>
              </tr>
              <tr>
                <td>001_1</td>
                <td>名称</td>
                <td>红色/XL</td>
                <td>100</td>
                <td>10</td>
                <td>100</td>
                <td>10.00</td>
                <td>15%</td>
              </tr>
              <tr>
                <td>001_1</td>
                <td>名称</td>
                <td>红色/XL</td>
                <td>100</td>
                <td>10</td>
                <td>100</td>
                <td>10.00</td>
                <td>15%</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
</body>
</html>