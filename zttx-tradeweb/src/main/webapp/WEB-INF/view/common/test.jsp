<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>ERP 一键补货 财务账 接口 调试JSP</h1>

<form action="${ctx}/erp/financial">
   <input type="text" name="dealerId" value="${dealerId}"> </p>
  <input type="text" name="userDes" value="${userDes}"> </p>
  <input type="text" name="encrypt" value="${encrypt}"></p>
    <div>
       www.dev8.com/erp/financial?dealerId=${dealerId}&&userDes=${userDes}&&encrypt=${encrypt}
    </div>
  <input type="submit" value="财务账">
</form>
<form action="${ctx}/erp/shopper">
    <input type="text" name="dealerId" value="${dealerId}"> </p>
    <input type="text" name="userDes" value="${userDes}"> </p>
    <input type="text" name="encrypt" value="${encrypt}"></p>
    <div>
    www.dev8.com/erp/shopper?dealerId=${dealerId}&&userDes=${userDes}&&encrypt=${encrypt}
        </div>
    <input type="submit" value="一键补货">
</form>
</body>
</html>
