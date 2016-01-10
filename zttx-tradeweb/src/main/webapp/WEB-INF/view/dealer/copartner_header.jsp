<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="panel-head">
    <ul>
    	<li class="long <c:if test='${param.m==1}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/beBrandVisited" class="fs14 yahei nomalf"><i class="iconfont">&#xe622;</i> 浏览过我的品牌商</a>
        </li>
        <li class="long <c:if test='${param.m==0}'>on</c:if>">
       		<a href="${ctx}/dealer/dealerJoinManage/visitedBrands" class="fs14 yahei nomalf"><i class="iconfont">&#xe61e;</i> 我浏览过的品牌</a>
        </li>
        <li class="short <c:if test='${param.m==2}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/applying" class="fs14 yahei nomalf"><i class="iconfont">&#xe626;</i> 申请中的</a>
        </li>
        <li class="short <c:if test='${param.m==3}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/inventing" class="fs14 yahei nomalf"><i class="iconfont">&#xe626;</i> 邀请中的</a>
        </li>
        <li class="short <c:if test='${param.m==4}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/disApply" class="fs14 yahei nomalf"><i class="iconfont">&#xe619;</i> 我撤销的</a>
        </li>
        <li class="short <c:if test='${param.m==5}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/noPass" class="fs14 yahei nomalf"><i class="iconfont">&#xe602;</i> 未通过审核的</a>
       	</li>
        <li class="control <c:if test='${param.m==6}'>on</c:if>">
        	<a href="${ctx}/dealer/dealerJoinManage/brandsCollect" class="fs14 yahei nomalf"><i class="iconfont">&#xe631;</i> 品牌收藏夹</a>
        </li>
    </ul>
    <form id="copartner_form" action="" method="post">
        <div class="toolbar">
            <c:if test="${param.m==6}">
                <div class="ui-form-item inline-block">
                    <input class="ui-checkbox" id="allcheck" type="checkbox"/> <label>全选</label>
                    <a class="ui-button ui-button-swhite mr10 ml10" id="btn_remove"><i class="iconfont">&#xe619;</i> 批量删除</a>
                </div>
            </c:if>
            <div class="ui-form-item inline-block">
                <label>公司规模：</label>
                <div class="pr inline-block">
                    <select class="ui-select js_small_select js_select mr10" id="emploeeNum" name="emploeeNum">
                        <option value="">全部</option>
                        <c:forEach items="${companyScope }" var="scope">
                             <option value="${scope.dictValue }">${scope.dictValueName }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="ui-form-item inline-block">
                <label class="ml10">年营业额：</label>
                <div class="pr inline-block">
                    <select class="ui-select js_small_select js_select mr10" id="moneyNum" name="moneyNum">
                        <option value="">全部</option>
                        <c:forEach items="${turnoverScope }" var="turnover">
                             <option value="${turnover.dictValue }">${turnover.dictValueName }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="ui-form-item inline-block">
                <label class="ml10"><c:if test='${param.m==0 || param.m==1}'>浏览</c:if><c:if test='${param.m==2}'>申请</c:if><c:if test='${param.m==3}'>邀请</c:if><c:if test='${param.m==4}'>撤销</c:if><c:if test='${param.m==5}'>审核</c:if><c:if test='${param.m==6}'>收藏</c:if>时间</label>
                <div class="inline-block proline-getimehide">
                    <input value="" name="startTimeStr" class="ui-input start-date" id="start-cal" readonly="readonly" type="text"/>
                    -
                    <input value="" name="endTimeStr" class="ui-input end-date" id="end-cal" readonly="readonly" type="text"/>
                </div>
            </div>
            <input class="ui-button ui-button-lwhite" type="button" value="搜索" onclick="doCopartnerSearch();"/>
         </div>
    </form>
</div>
