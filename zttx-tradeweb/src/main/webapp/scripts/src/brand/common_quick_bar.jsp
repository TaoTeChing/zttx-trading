<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!-- 添加到快速通道 -->
<c:if test="${param.isShow != 0}">
    <a data-title="test" data-id="5" class="js_quick_menu add" style="display: none;" href="javascript:void(0);">
        <i class="iconfont add_icon">&#xe611;</i><span>添加到快捷通道</span>
    </a>
</c:if>
<!-- 取消快速通道 -->
<a data-id="5" class="js_quick_menu cancel" style="display: none;" href="javascript:;">
    <i class="iconfont add_icon">&#xe633;</i><span>从快捷通道中移除</span>
</a>
<a class="ui_button ui_button_mblue" href="${ctx}/help/index" target="_blank">
    <i class="iconfont help_icon" style="display: none;">&#xe610;</i><i class="v2-icon-help"></i><span>帮助中心</span>
</a>
