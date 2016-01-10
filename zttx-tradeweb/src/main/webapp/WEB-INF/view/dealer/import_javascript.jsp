<%--
  @(#)import_javascript.jsp 2014/4/21 9:00
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/dealer/base-init.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>