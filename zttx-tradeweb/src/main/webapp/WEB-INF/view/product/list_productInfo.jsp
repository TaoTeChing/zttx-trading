<%@ page import="com.zttx.web.consts.ProductConsts" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:set var="productcate_order" value="<%=ProductConsts.CATE_ORDER.getKey()%>"></c:set>
<c:set var="TABSEARCH_UP" value="<%=ProductConsts.TABSEARCH_UP.getKey()%>"></c:set>
<c:set var="TABSEARCH_DOWN" value="<%=ProductConsts.TABSEARCH_DOWN.getKey()%>"></c:set>
<c:set var="TABSEARCH_GROOM" value="<%=ProductConsts.TABSEARCH_GROOM.getKey()%>"></c:set>
<c:set var="TABSEARCH_ACTIVITY" value="<%=ProductConsts.TABSEARCH_ACTIVITY.getKey()%>"></c:set>
<c:set var="TABSEARCH_RECYCLE" value="<%=ProductConsts.TABSEARCH_RECYCLE.getKey()%>"></c:set>
<c:set var="TABSEARCH_LITTLE" value="<%=ProductConsts.TABSEARCH_LITTLE.getKey()%>"></c:set>
<c:set var="TABSEARCH_NULL" value="<%=ProductConsts.TABSEARCH_NULL.getKey()%>"></c:set>
<c:set var="BEGIN_TYPE_FIRST" value="<%=ProductConsts.BEGIN_TYPE_FIRST.getKey()%>"></c:set>
<c:set var="BEGIN_TYPE_STORE" value="<%=ProductConsts.BEGIN_TYPE_STORE.getKey()%>"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <c:if test="${infoModel.tabSearch==TABSEARCH_UP}"><c:set var="title" value="上架的产品"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_DOWN}"><c:set var="title" value="下架的产品"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_GROOM}"><c:set var="title" value="推荐的产品"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_ACTIVITY}"><c:set var="title" value="预定的产品"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_RECYCLE}"><c:set var="title" value="回收站"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_LITTLE}"><c:set var="title" value="库存紧张"/></c:if>
    <c:if test="${infoModel.tabSearch==TABSEARCH_NULL}"><c:set var="title" value="库存缺货"/></c:if>
    <title>管理中心-产品管理-${title}</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css" />
    <style>
        .js-moreFilter{display: none;}
        .js-edit-title{cursor: pointer;}
    </style>
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="/common/menuInfo/sidemenu"/>

    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">${title}</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="selectbar_list clearfix">
                <form:form cssClass="ui-form productadd-form" action="/brand/product/list" method="post" id="search-form">
                    <input type="hidden" name="attrType" id="attrType" value="0">
                    <input type="hidden" name="descType" id="descType" value="1">
                    <input type="hidden" name="menuId" value="${productList_menuId}"/>
                    <input type="hidden" name="subMenuId" value="${productList_subMenuId}"/>
                    <div class="ui-form-item">
                        <label  class="ui-label">
                            选择品牌：
                        </label>
                        <div class="fl">
                            <select class="ui-select js_select list-select-width" name="list_brandsId" id="brandsId">
                                <option value="">全部品牌</option>
                                <c:if test="${brandesList!=null}">
                                    <c:forEach items="${brandesList}" var="brandes">
                                        <option value="${brandes.refrenceId}" <c:if test="${brandes.refrenceId==infoModel.list_brandsId}"> selected="selected" </c:if> >${brandes.brandsName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <%--<div class="ui-form-item">
                        <label  class="ui-label">
                            产品线：
                        </label>
                        <div class="fl">
                            <select class="ui-select js_select list-select-width" name="lineIds" id="lineIds">
                                <!--  <option value="">全部产品线</option>-->
                            </select>
                        </div>
                    </div>--%>
                    <div class="ui-form-item">
                        <label  class="ui-label">
                            产品货号：
                        </label>
                        <input class="ui-input list-pro-searchi" type="text" placeholder="产品货号" name="list_productNo" value="${infoModel.list_productNo}" style="width: 215px;">
                    </div>
                    <div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            一级类目：
                        </label>
                        <div class="fl">
                            <select class="ui-select js_select list-select-width" name="list_dealNo" id="list_dealNo">
                                <option value="">全部类目</option>
                                <c:if test="${dealDicList!=null}">
                                    <c:forEach items="${dealDicList}" var="dic">
                                        <option value="${dic.dealNo}" <c:if test="${dic.dealNo==infoModel.list_dealNo}"> selected="selected" </c:if>>${dic.dealName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <%--<div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            店内类目：
                        </label>
                        <div class="inline-block">
                            <select class="ui-select js_select" name="catalogSuper" name="catalogSuper" id="catalogSuper" style="width:120px; _width:107px; height:30px;">
                                <!--  <option value="">全部类目</option>-->
                            </select>
                        </div>
                        <div class="inline-block">
                            <select class="ui-select js_select" name="catalogSub" name="catalogSub" id="catalogSub" style="width:121px; _width:107px; height:30px;">
                            </select>
                        </div>
                    </div>--%>
                    <div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            产品价格：
                        </label>
                        <input type="text" class="ui-input list-select-swidth js-price" name="minPrice" value="${infoModel.minPrice}" style="width: 105px;" />
                        -
                        <input type="text" class="ui-input list-select-swidth js-price" name="maxPrice" value="${infoModel.maxPrice}" style="width: 106px;" />
                    </div>
                    <div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            产品销量：
                        </label>
                        <input type="text" class="ui-input list-select-swidth js-price" name="saleNumStart" value="${infoModel.saleNumStart}"/>
                        -
                        <input type="text" class="ui-input list-select-swidth js-price" name="saleNumEnd" value="${infoModel.saleNumEnd}"/>
                    </div>
                    <div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            发布时间：
                        </label>
                            <%--<input class="ui-input list-pro-stime str-date" type="text" id="start-cal"  name="saleStartTimeStr"/>
                            --%><input class="ui-input list-pro-stime str-date" type="text" id="start-cal" readonly="readonly" name="saleStartTimeStr"
                            <c:if test="${!empty infoModel.saleStartTime}"> value="${fns:getTimeFromLong(infoModel.saleStartTime, 'yyyy-MM-dd')}"</c:if>/>
                        -
                            <%--<input class="ui-input list-pro-stime end-date" type="text"id="end-cal"  name="saleEndTimeStr"/>
                            --%><input class="ui-input list-pro-stime end-date" type="text"id="end-cal" readonly="readonly" name="saleEndTimeStr"
                            <c:if test="${!empty infoModel.saleEndTime}"> value="${fns:getTimeFromLong(infoModel.saleEndTime, 'yyyy-MM-dd')}"</c:if>/>
                    </div>
                    <div class="ui-form-item js-moreFilter">
                        <label  class="ui-label">
                            	是否可见：
                        </label>
                        <div class="fl">
                        <select class="ui-select js_select list-select-width" name="show" id="show">
                                <option value="1">全部</option>
                                <option value="2">前台可见</option>
                                <option value="3">前台不可见</option>
                         </select>
                         </div>
                    </div>
                    
                    <div class="ui-form-item" style="float: right;">
                        <input type="button" class="ui_button_myourself fl" value="搜索" onclick="page.goTo(1);">
                        <input type="button" class="ui_button_myourself selfbtnM fl" value="批量导出产品" onclick="exportExcel();">
                        <input type="button" class="ui_button_myourself fl js-triggerFilter" value="更多筛选条件">
                    </div>
                    <%--<div class="ui-form-item">
                        <input type="button" class="ui_button_myourself fl js-triggerFilter" value="更多筛选条件">
                    </div>--%>
                    <input type="hidden" name="tabSearch" id="tabSearch" value="${infoModel.tabSearch}">
                </form:form>
            </div>


            <div class="pro-list">
                <div class="jstabs_t">
                    <div class="jstabsmenu_t">
                        <ul class="clearfix">
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_UP}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_UP}');" >上架的产品</a></li>
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_DOWN}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_DOWN}');" >下架的产品</a></li>
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_GROOM}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_GROOM}');" >推荐的产品</a></li>
                            <%--<li <c:if test="${infoModel.tabSearch==TABSEARCH_ACTIVITY}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_ACTIVITY}');" >预定的</a></li>--%>
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_RECYCLE}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_RECYCLE}');" >回收站</a></li>
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_LITTLE}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_LITTLE}');">库存紧张</a></li>
                            <li <c:if test="${infoModel.tabSearch==TABSEARCH_NULL}">class="selected"</c:if>><a href="javascript:tabSearch('${TABSEARCH_NULL}');">库存缺货</a></li>
                            <%--<li style="width: 146px;"><a href="javascript:;">待优化产品</a></li>--%>
                        </ul>
                    </div>
                    <div class="tabsbox_t">
                        <div class="online-pro"><!-- 第一个div -->
                            <div class="checkallbox clearfix" id="proOper">
                                <div class="inline-block chenkweizhi fl">
                                    <input type="checkbox" class="js-chkall ui-checkbox mr5" id="setdef120">
                                </div>
                                <label class="checkalltext fl" for="setdef120">全选</label>
                                <c:choose>
                                    <c:when test="${infoModel.tabSearch==TABSEARCH_UP || infoModel.tabSearch==TABSEARCH_NULL
                                || infoModel.tabSearch==TABSEARCH_LITTLE || infoModel.tabSearch==TABSEARCH_ACTIVITY }">
                                        <a class="checkstyle fl" href="javascript:groomProduct();">推荐产品</a>
                                        <a class="checkstyle fl" href="javascript:ungroomProduct();">取消推荐</a>
                                        <a class="checkstyle fl" href="javascript:delProduct();" >删除</a>
                                        <a class="checkstyle fl" href="javascript:downProduct();">下架</a>
                                    </c:when>
                                    <c:when test="${infoModel.tabSearch==TABSEARCH_DOWN}">
                                        <a class="checkstyle fl" href="javascript:delProduct();" >删除</a>
                                        <a class="checkstyle fl" href="javascript:upProduct();">上架</a>
                                    </c:when>
                                    <c:when test="${infoModel.tabSearch==TABSEARCH_RECYCLE}">
                                        <a class="checkstyle fl" href="javascript:returnProduct();">还原</a>
                                        <a class="checkstyle fl" href="javascript:batchDelete();">批量删除</a>
                                    </c:when>
                                    <c:when test="${infoModel.tabSearch==TABSEARCH_GROOM}">
                                        <a class="checkstyle fl" href="javascript:ungroomProduct();">取消推荐</a>
                                        <a class="checkstyle fl" href="javascript:delProduct();" >删除</a>
                                        <a class="checkstyle fl" href="javascript:downProduct();">下架</a>
                                    </c:when>
                                </c:choose>
                                <c:if test="${infoModel.tabSearch!=TABSEARCH_RECYCLE}">
                                    <a class="checkstyle fl js-setwarning" href="javascript:;" style="width: 105px;">设置库存预警值</a>
                                </c:if>
                                <c:if test="${infoModel.tabSearch!=TABSEARCH_RECYCLE}">
                                    <a class="checkstyle fl" style="width: 105px;" onclick="javascript:setSsample();">设置运费模版</a>
                                    <a class="checkstyle fl" style="width: 105px;" onclick="javascript:setSamplePrice();">支持拿样</a>
                                    <a class="checkstyle fl" style="width: 105px;" onclick="javascript:sampleProduct(false);">取消拿样</a>
                                </c:if>
                                <c:if test="${infoModel.tabSearch==TABSEARCH_UP}">
                                		<a class="checkstyle fl" href="javascript:upTabShow();">前台可见</a>
                                        <a class="checkstyle fl" href="javascript:upTabUnshow();">前台不可见</a>
                                </c:if>
                                <c:if test="${infoModel.tabSearch==TABSEARCH_DOWN}">
                                	<a class="checkstyle fl" href="javascript:downTabShow();">前台可见</a>
                                    <a class="checkstyle fl" href="javascript:downTabUnshow();">前台不可见</a>
                                </c:if>
                                <%--<a class="fr ui_button ui_button_lorange mt5" href="${ctx}/brand/activity/list" style="margin-top: 7px;">活动报名</a>
                                --%><%--<a class="checkstyle fr">下一页</a>
                            <a class="checkstyle fr">上一页</a>--%>
                            
                            </div>
                            <div class="divThead">
                                <div class="dTname" style="width: 295px;">产品名称</div>
                                <div class="dTpress">吊牌价<a href="javascript:;" data-attr="1" class="ml5 arrow-down"></a></div>
                                <div class="dTpress">现款价<a href="javascript:;" data-attr="3" class="ml5 arrow-down"></a></div>
                                <div class="dTpress">授信价<%--<a href="javascript:;" data-attr="" class="ml5 arrow-down"></a>--%></div>
                                <div class="dTpress">返点价<%--<a href="javascript:;" data-attr="" class="ml5 arrow-down"></a>--%></div>
                                <div class="dThave" style="width: 100px;">库存</div>
                                <div class="dTsell" style="width: 63px;">总销量<a href="javascript:;" data-attr="2" class="ml5 arrow-down"></a></div>
                                <div class="dTorder" style="width: 84px;">显示顺序</div>
                                <div class="dTadd">发布时间<a href="javascript:;" data-attr="0" class="ml5 arrow-down"></a></div>
                                <div class="dTdo">操作</div>
                            </div>
                            <c:if test="${infoModel.tabSearch==TABSEARCH_RECYCLE}">
                                <div class="tips mt10">
                                    <i class="v2-icon-explain"></i>
                                    说明：删除的产品会被放入回收站中，如有误删，请至回收站中还原
                                </div>
                            </c:if>
                            <table class="list-table" id="list-table">
                                <colgroup>
                                    <col width="295" />
                                    <col width="66" />
                                    <col width="66" />
                                    <col width="66" />
                                    <col width="66" />
                                    <col width="100" />
                                    <col width="63" />
                                    <col width="84" />
                                    <col width="100" />
                                    <col width="115" />
                                </colgroup>
                                <tbody>
                                </tbody>
                            </table>
                            <div id="pagination" class="pagination"></div>

                            <div class="tips mt10" style="height: auto;">
                                <%--<p><i class="v2-icon-explain"></i>说明：预定产品未到预定开始时间，标记为下架产品，到期后会自动上架；</p>
                                <p style="margin-left: 60px;">已有订单产品无法删除，可做下架处理</p>
                            --%>
                            <p><i class="v2-icon-explain"></i>说明：已有订单产品无法删除，可做下架处理</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form:form id="ajaxForm" cssStyle="display: none" method="post" target="_black">

</form:form>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div style="display: none;">
    <div class="js-setwarning-box">
        <div class="ui-head">
            <h3>设置库存预警值</h3>
        </div>
        <div class="ta-c mt15">
            库存少于 <input class="ui-input js-number" style="width:60px;" name="storeWarnNum" value="${infoModel.storeWarnNum}" id="storeWarnNum" /> 件提醒
        </div>
        <div class="ta-c mt15">
            <a href="javascript:;" class="simple_button js-setwarning-sure" style="padding:0;">确定</a>
            <a href="javascript:;" class="simple_button js-setwarning-cancel" style="padding:0;">取消</a>
        </div>
    </div>
</div>

<%--<input type="hidden" name="initLineIds" id="initLineIds" value="${infoModel.lineIds}">
--%><input type="hidden" name="initCatalogSuper" id="initCatalogSuper" value="${infoModel.catalogSuper}">
<input type="hidden" name="initCatalogSub" id="initCatalogSub" value="${infoModel.catalogSub}">

<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/productmanage.js" type="text/javascript"></script>
<script src="${src}/brand/productinfo_list.js" type="text/javascript"></script>
<script id="line-template" type="text/html">
    {{each rows}}
    <tr>
        <td class="list-TD-pro">
            <div class="inline-block list-TD-check fl">
                <input type="checkbox"
                       class="js_chk ui-checkbox mr5"
                       id="{{$value.refrenceId}}"
                       data-productno="{{$value.productNo}}"
                       data-productitle="{{$value.productTitle}}"
						data-sampleprice="{{$value.samplePrice}}"
						data-brandsname="{{$value.brandsName}}"
						/>
            </div>
            <input type="hidden" class="js-copylink-url" data-id="{{$value.refrenceId}}" id="copy_links_hidden_{{$value.refrenceId}}" />
            <%--todo 产品展示页--%>
            <div class="imgbox">
                {{if $value.domainName}}
                <a href="${ctx}/market/product/{{$value.refrenceId}}" class="js-seturl"><img style="width:70px;height:70px;" src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}" alt="" class="list-TD-imgs fl" /></a>
                {{/if}}
                {{if !$value.domainName}}
                <a href="${ctx}/market/product/{{$value.refrenceId}}" class="js-seturl"><img
                        style="width:70px;height:70px;"
                        src="${res}{{$getImageDomainPathFn $value.productImage 160 160}}" alt=""
                        class="list-TD-imgs fl"/></a>
                {{/if}}
                {{if $value.productGroom}}
                <div class="recom-product-after">推荐产品</div>
                {{/if}}
            </div>
            <div class="inline-block fl list-TD-des" style="width:150px;">
                <a class="js-edit-title" data-id="{{$value.refrenceId}}"><h4 class="title">{{$value.productTitle}}<i class="iconfont">&#xe62f;</i></h4></a>
                <p class="number fn-text-overflow">货号：{{$value.productNo}}</p>
                {{if $value.isSample}}
                <p class="sample inline-block">
                    <img src="${res}/images/brand/sample_icon.png" alt=""/>拿样
                </p>
                {{/if}}
				{{if $value.isShow}}
					<p class="destine inline-block">
                    	<i class="iconfont">&#xe60f;</i>可见
                	</p>
				{{/if}}
                <%--<p class="destine inline-block">
                    <i class="iconfont">&#xe60f;</i>可见
                </p>
                <p class="sample inline-block">
                    <i class="iconfont">&#xe60f;</i>不可见
                </p>--%>
                {{if $value.productCate==${productcate_order}}}
                <p class="destine inline-block">预定产品<i class="iconfont">&#xe60f;</i></p>
                {{/if}}
                <%--<p class="tip"><i class="iconfont mr5">&#xe630;</i>产品标题待优化</p>
                <p class="tip"><i class="iconfont mr5">&#xe630;</i>类目待优化</p>--%>
            </div>
        </td>
        <td class="list-TD-price">
            <strong class="bluefont js_tip_triggerjiage">{{formatNumber $value.productPrice}}</strong>
        </td>
        <td class="list-TD-price">
            <strong class="bluefont js_tip_triggerjiage">{{formatNumber $value.minDirectPrice}}</strong>
        </td>
        <td class="list-TD-price">
            <strong class="bluefont js_tip_triggerjiage">{{formatNumber $value.creditPrice}}</strong>
        </td>
        <td class="list-TD-price">
            <strong class="bluefont js_tip_triggerjiage">{{formatNumber $value.pointPrice}}</strong>
        </td>
        <td class="list-TD-have">
            {{$value.productStore}}
            <!--div class="scrollArea" style="height: 94px;overflow: auto;">
            {{if $value.priceModels!=null}}
                {{each $value.priceModels}}
                   <div class="mt5">
                    {{if $value.z != null}}
                    {{each $value.z}}
                    <span name="modelvalue" title="{{if $value.v==0}}无{{else}}{{$value.v}}{{/if}}" class="fn-text-overflow" style="padding-right: 2px;display:inline-block;width:60px;vertical-align: top;">{{if $value.v==0}}无{{else}}{{$value.v}}{{/if}}</span>
                    {{/each}}
                    {{if $value.z.length < 2}}
                    <span name="modelvalue" title="无" class="fn-text-overflow" style="padding-right: 2px;display:inline-block;width:60px;vertical-align: top;">无</span>
                    {{/if}}
                    {{/if}}
                    <span name="modelvalue" style="padding-right: 2px;display:inline-block;width:65px;vertical-align: top;">{{$formatMoney $value.p}}</span>
                   {{if $value.s<=object}}
                    <span name="modelvalue" style="padding-right: 2px;display:inline-block;width:62px;background:#E96200;color:#fff;vertical-align: top;text-align: center;">{{$value.s}}</span>
                   {{else}}
                    <span name="modelvalue" style="padding-right: 2px;display:inline-block;width:65px;vertical-align: top;">{{$value.s}}</span>
                    {{/if}}
                   </div>
                {{/each}}
            {{/if}}
            </div-->
        </td>
        <td class="list-TD-sell">{{$value.saleNum}}</td>
        <td class="list-TD-order">
            {{if $value.topTime != null}}
            <%--<span class="arrow-hasToTop" title="已置顶"></span>--%>
            <a href="javascript:;" data-id="{{$value.refrenceId}}" class="arrow-cancel" title="取消置顶"></a>
            {{else}}
            <a href="javascript:;" data-id="{{$value.refrenceId}}" class="arrow-toTop" title="置顶"></a>
            {{/if}}
        </td>
        <td class="list-TD-data"><span>{{$formatDate $value.createTime}}</span></td>
        <td class="list-TD-do">
            {{if $value.delFlag}}
            	<a href="javascript:;" onclick="returnProduct('{{$value.refrenceId}}')">还原</a>
				<a href="javascript:;" onclick="realDeleteProduct('{{$value.refrenceId}}')">删除</a>
            {{else}}
            {{if $value.beginType!='${BEGIN_TYPE_FIRST}'}}
            <a href="/brand/product/edit/{{$value.refrenceId}}">编辑产品</a>
            <a href="javascript:;" onclick="delProduct('{{$value.refrenceId}}')">删除</a>
            <a href="javascript:;" onclick="upProduct('{{$value.refrenceId}}')">上架</a>
            {{else}}
            <a href="/brand/product/edit/{{$value.refrenceId}}">编辑产品</a>
            <a href="/brand/product/copy/{{$value.refrenceId}}">复制新增</a>
            <%--<a href="javascript:;" class="js-copylink" data-clipboard-target="copy_links_hidden_{{$value.refrenceId}}">复制链接</a>--%>
            <a href="javascript:;" onclick="delProduct('{{$value.refrenceId}}')">删除</a>
            <a href="javascript:;" onclick="downProduct('{{$value.refrenceId}}')">下架</a>
            {{/if}}
            <a href="javascript:;" class="js-operation-revise" data-id="{{$value.refrenceId}}">修改价格及库存</a>
            {{/if}}
        </td>
    </tr>
    {{ /each }}
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="10">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script id="revise-price-template" type="text/html">
    <div class="ui-head">
        <h3>修改价格及库存</h3>
    </div>
    <div class="confirm_bd">
        <form action="" method="post" id="revise-price-form">
            <input type="hidden" name="send" value="false" />
            <div style="margin: 10px;">
                <label>
                    <input type="checkbox" class="ui-checkbox" name="sample"  {{sample == true ? 'checked' : ''}}>支持拿样
                </label>
                <input type="text" name="samplePrice" id="samplePrice" value="{{samplePrice}}" class="ui-input ml5 samplePrice js-price" placeholder="请输入拿样价格" style="padding:0 3px;" >
                <label>
                    <input type="checkbox" class="ui-checkbox ml5" name="credit" id="supportCredit"  {{credit == true ? 'checked' : ''}} autoComplate="off">支持授信
                </label>
            </div>
            <div style="margin: 10px;">
                <span>
                    吊牌价：<input type="text" class="ui-input js-revise-dprice js-price" style="width: 50px;padding:0 3px;" maxlength="8" />
                </span>
                <span>
                    直供价：<label><input type="radio" name="dprice_n" class="ui-radio js-revise-zjradio" checked/> 直接设置</label>
                </span>
                <span>
                    <label><input type="radio" name="dprice_n" class="ui-radio" /> 折扣设置</label>
                </span>
                <span>
                    <input type="text" class="ui-input js-revise-zprice js-price" style="width: 50px;padding:0 3px;" maxlength="8" />
                </span>
                <span class="js-sx-type" style="{{credit == true ? "" : "display: none;"}}">
                    授信价：<input type="text" class="ui-input js-revise-cprice js-price" style="width: 71px;padding:0 3px;" maxlength="8" />
                </span>
                <span>
                    库存：<input type="text" class="ui-input js-revise-dcount js-number" style="width: 71px;padding:0 3px;" maxlength="8" />
                </span>
                <button type="button" class="simple_button js-revise-set" style="margin-top: -1px;padding: 0 3px;font-size: 12px;height: 24px;line-height: 23px;">批量设置</button>
            </div>
            <div style="margin: 10px;">
                <ul class="inline-float">
                    <li>
                        <label>
                            <input type="checkbox" name="point" value="true" class="ui-checkbox" id="support_rebate"  {{point == true ? 'checked' : ''}} />
                            支持返点
                        </label>
                    </li>
                    <li class="js-rebate-display" style="display: none;margin-left: 8px;">
                        返点比例：
                        <label>
                            <input type="radio" name="pointPercent" value="0.1" class="ui-radio js-rebate-pro" data-type="0" {{pointPercent==null || pointPercent==0.1?'checked':''}} />
                            10%
                        </label>
                        <label>
                            <input type="radio" name="pointPercent" value="2" class="ui-radio js-rebate-pro" data-type="1" {{pointPercent!=null && pointPercent!=0.1?'checked':''}} />
                            其他
                        </label>
                        <input type="text" name="pointPercentOther" class="ui-input js-rebate-other js-price" value="{{pointPercent!=null && pointPercent!=0.1?(pointPercent*100):''}}" style="width: 20px;padding:0 3px;"/>%
                    </li>
                    <li class="js-rebate-display" style="display: none;">
                        &nbsp;&nbsp;返点价：
                        <select name="" id="select_rebate">
                            <option value="">请选择</option>
                            <option value="9.00">9.00</option>
                            <option value="19.00">19.00</option>
                            <option value="29.00">29.00</option>
                            <option value="39.00">39.00</option>
                            <option value="49.00">49.00</option>
                            <option value="59.00">59.00</option>
                            <option value="69.00">69.00</option>
                        </select>
                        <input type="text" class="ui-input js-rebate-setext" style="width:50px;padding:0 3px;"/>
                        <button type="button" class="simple_button js-rebate-set" style="margin-top: -1px;padding: 0 3px;font-size: 12px;height: 24px;line-height: 23px;">设置</button>
                    </li>
                </ul>
            </div>
            <div style="margin: 10px;">
                <label>
                    <input type="checkbox" name="discount" {{discount == true ? 'checked' : ''}} > 参加加盟终端商折扣
                </label>
            </div>
            <div style="width: 723px;overflow: hidden;">
            <table class="revise-table" style="width: 100%;margin:0 10px;">
                <thead>
                <tr>
                    <th width="100">颜色_尺码</th>
                    <th width="80">吊牌价</th>
                    <th width="100">现款价</th>
                    <th class="js-tigger-sx" width="100" style="{{credit == true ? "" : "display: none;"}}">授信价</th>
                    <th class="js-tigger-fd" width="100" style="">返点价</th>
                    <th width="100">库存</th>
                    <th width="130">条形码</th>
                </tr>
                </thead>
            </table>
            </div>
            <div style="margin:0 10px 10px 10px;max-height: 200px;overflow-x: hidden;overflow-y: scroll;border-bottom:1px solid #eee;">
                <table class="revise-table" style="width: 100%;">
                    <tbody id="revise-price-list"></tbody>
                </table>
            </div>
            <div class="js-sx-type"  style="margin: 10px;{{credit == true ? "" : "display: none;"}}">
                <label><input type="checkbox" checked/>授信价格的生效方式</label>
                <div style="margin: 10px 0 0 30px;">
                    <div><label><input type="radio" class="ui-radio" name="costPush" value="true" />当前终端商的库存生效</label></div>
                    <div class="mt5"><label><input type="radio" class="ui-radio" name="costPush" value="false" checked />新订单生效</label></div>
                </div>
            </div>
            <div class="js-tigger-fd" style="padding: 10px 20px;">
                <label><input type="checkbox" checked/>返点价生效时间：</label>
                <input type="text" class="ui-input" id="dateRebate" name="pointEffTimeStr" value="{{pointEffTimeStr}}"/>
            </div>
        </form>
    </div>
    <div class="operate" style="text-align: center;">
        <%--<button type="submit" class="simple_button confirm_btn js-revise-confirm">确定</button>--%>
        <a class="simple_button confirm_btn js-revise-confirm" href="javascript:;">确定</a>
        <a class="simple_button cancel_btn js-revise-cancel" style="" href="javascript:;">取消</a>
    </div>
</script>
<script id="revise-price-list-template" type="text/html">
    {{each productSkuList}}
    <tr>
        <td width="100">
            {{$value.productSkuName}}
            <input type="hidden" name="productSkuList[{{$index}}].productId" value="{{$value.productId}}" />
            <input type="hidden" name="productSkuList[{{$index}}].refrenceId" value="{{$value.refrenceId}}"/>
            <input type="hidden" name="productSkuList[{{$index}}].productSkuBarcode.productId" value="{{$value.productId}}"/>
            <input type="hidden" name="productSkuList[{{$index}}].attrItemIds" value="{{$value.attrItemIds}}"/>
        </td>
        <td width="80">
            <input class="ui-input js-setcom-dprice js-price" type="text" name="productSkuList[{{$index}}].productSkuPrice.skuPrice"
                   value="{{if $value.productSkuPrice == null}}{{$formatPrice 0}}{{else}}{{$formatPrice $value.productSkuPrice.skuPrice}}{{/if}}"/></td>
        <td width="100">
            <input class="ui-input js-setcom-zprice js-price" type="text" name="productSkuList[{{$index}}].productSkuPrice.directPrice"
                   value="{{if $value.productSkuPrice == null}}{{$formatPrice 0}}{{else}}{{$formatPrice $value.productSkuPrice.directPrice}}{{/if}}"/></td>
        <td width="100" class="js-tigger-sx" style="{{productExtInfo.credit == true ? "" : "display: none;"}}">
            <input class="ui-input js-setcom-cprice js-price" type="text" name="productSkuList[{{$index}}].productSkuPrice.creditPrice"
                   value="{{if $value.productSkuPrice == null}}{{$formatPrice 0}}{{else}}{{$formatPrice $value.productSkuPrice.creditPrice}}{{/if}}" id="sx_search_{{$index}}" data-index="{{$index}}"/></td>
        <td width="100" class="js-tigger-fd" style="">
            <input class="ui-input js-setcom-fprice js-price" type="text" name="productSkuList[{{$index}}].productSkuPrice.pointPrice"
                   value="{{if $value.productSkuPrice == null}}{{$formatPrice 0}}{{else}}{{$formatPrice $value.productSkuPrice.pointPrice}}{{/if}}"
                   data-oldprice="{{if $value.productSkuPrice == null}}{{$formatPrice 0}}{{else}}{{$formatPrice $value.productSkuPrice.pointPrice}}{{/if}}" /></td>
        <td width="100"><input class="ui-input js-setcom-dcount js-number" type="text" name="productSkuList[{{$index}}].quantity" value="{{$value.quantity}}"/></td>
        <td width="130"><input class="ui-input" style="width: 80px;" type="text" name="productSkuList[{{$index}}].productSkuBarcode.barCodeValue" value="{{$value.productSkuBarcode == null ? '' : $value.productSkuBarcode.barCodeValue}}"/></td>
    </tr>
    {{/each}}
</script>
<script id="set-sample-template" type="text/html">
    <div class="ui-head">
        <h3>设置拿样价格</h3>
    </div>
    <form class="ui-form mt20" id="setSampleForm" data-widget="validator" action="">
        <div class="ui-form-item" style="padding-left: 77px;">
            <label class="ui-label">
                拿样价：
            </label>
            <input type="text" id="setSamplePrice" name="samplePrice" class="ui-input js-sspValue" maxlength="8" /> 元
            <a class="simple_button" id="batchSetSamplePrice" style="height: 28px;line-height: 26px;margin-left: 10px;" href="javascript:;">批量设置</a>
        </div>
        <div style="width: 480px;">
            <table class="revise-table" style="margin: 0 10px 20px 10px;">
                <colgroup>
                    <col width="100"/>
                    <col width="100"/>
                    <col width="100"/>
                    <col width="100"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>品牌</th>
                        <th>产品货号</th>
                        <th>产品名称</th>
                        <th>拿样价</th>
                    </tr>
                </thead>
                <tbody id="sample-price-list">
                    <%--<tr>
                        <td>品牌名</td>
                        <td>123456</td>
                        <td>名称</td>
                        <td><input type="text" class="ui-input js-single-sspValue" /></td>
                    </tr>
                    <tr>
                        <td>品牌名</td>
                        <td>123456</td>
                        <td>名称</td>
                        <td><input type="text" class="ui-input js-single-sspValue" /></td>
                    </tr>--%>
                </tbody>
            </table>
        </div>
        <div class="operate ta-c">
            <button id="updatePriceBtn" type="submit" class="simple_button confirm js-ssprice-sure">确定</button>
            <button type="button" class="simple_button ml5 cancel js-ssprice-cancel">取消</button>
        </div>
    </form>
</script>
<script id="remindFdTpl" type="text/html">
    <div class="ui-head">
        <h3>提示</h3>
    </div>
    <div>
        <p style="padding: 10px;">未生效的返点价智慧门店是否仍继续生效？</p>
    </div>
    <div class="operate ta-c">
        <button type="submit" class="simple_button confirm js-fdcome-sure">是</button>
        <button type="button" class="simple_button ml5 cancel js-fdcome-cancel">否</button>
    </div>
</script>
<script type="text/javascript">
    productmanagelist.init();

    seajs.use(["dialog", "template", "my97DatePicker"],function(Dialog, Template){

        Template.helper('$formatPrice', function (price) {
            if($.isNumeric(price)){
                return price.toFixed(2);
            }else{
                return price;
            }
        });

        var pmlist = {
            init: function(){
                this.handleDate();
                this.setWarning();//设置库存预警值
                this.revisePrice();//修改价格库存
                this.tiggerSxFlag = false; //支持授信是否处于选中状态
                this.tiggerFdFlag = false; //支持返点是否处于选中状态
                this.isSpportFd = false;   //支持返点一开始的状态
                this.cacheObjSky = {};
                this.sxChange();
                this.sxClick();
                this.fdChange();
                this.fdClick();
                this.editTitle();  //修改标题的方法
            },
            handleDate: function(){
                rangeCalendar("start-cal","end-cal");
            },
            setWarning: function(){
                var dialog = new Dialog({
                    trigger:".js-setwarning",
                    content:$(".js-setwarning-box"),
                    width:248
                });

                $(".js-setwarning-sure").on("click",function(){//确定
                    var storeWarnNum = $("#storeWarnNum").val();
                    $.ajax({
                        url:"/brand/product/ajax_saveWarnNum",
                        type:"post",
                        data:{storeWarnNum:storeWarnNum},
                        dataType: "json",
                        success:function(json){
                            if(zttx.SUCCESS==json.code){
                                remind("success","修改库存预警值成功");
                                page.render();
                            }else{
                                remind("error",json.message);
                            }
                        },
                        error:function(XMLHttpRequest, textStatus, errorThrown){
                            remind("error",errorThrown);
                        }
                    });
                    dialog.hide();
                });
                $(".js-setwarning-cancel").on("click",function(){//取消
                    dialog.hide();
                })
            },
            revisePrice: function(){
                var dpDialog, htm, _self = this;

                function renderReviseFnc(htm){
                    $("#revise-price-list").html(htm);
                }
                //var data=true;
                dpDialog = new Dialog({
                    //content:Template.render("revise-price-template", data),
                    width:750
                }).after("render",function(){
                            _self.setRevisePrice();
                            _self.setRebatePrice();
                            _self.fdDateSelect();
                            $(".js-revise-zprice").isPrice();
                        }).after("show",function(){
                            renderReviseFnc(htm);
                            _self.sxChange();
                            _self.fdChange();
                            _self._realModifyPrice();
                            _self._setEditDisabled($(".js-rebate-pro:checked").data("type"));
                            $("#dateRebate").addClass("hasDatepicker");
                            $(".js-price,.js-number").off("keyup change");
                            $(".js-price").isPrice();
                            $(".js-number").isPrice(false);
                        });

                //单击按钮，请求获得数据修改价格库存的数据
                $("#list-table").on("click", ".js-operation-revise", function(){
                    var id = $(this).data("id");
                    $.ajax({
                        url: "${ctx}/brand/product/isCredit",
                        data: {productId: id},
                        method: "post",
                        dataType: "json",
                        async:false,
                        success: function(data){
                            var datas = {};
                            datas.credit = data.credit;
                            datas.sample = data.sample;
                            datas.samplePrice = data.samplePrice;
                            datas.discount = data.discount;
                            datas.point = data.point;
                            datas.pointPercent = data.pointPercent;
                            datas.pointEffTimeStr=data.pointEffTimeStr;
                            dpDialog.set("content", "");

                            //这个值用来控制提交的时候，判断是否需要提醒 统一返点价到智慧门店
                            _self.isSpportFd = datas.point;

                            var h = Template.render("revise-price-template", datas);
                            dpDialog.set("content", h);
                        }
                    });
                    dialogLoading(function(dd){
                        $.ajax({
                            url: "${ctx}/brand/product/quickEdit/"+id,
                            data: "",
                            method: "post",
                            dataType: "json",
                            success: function(data){
                                //console.log(data);
                                htm = Template.render("revise-price-list-template", data.object);
                                dd.hide();
                                dpDialog.show();
                            }
                        });
                    }, "请求数据中，请稍后...");
                });

                //提醒门店是否生效的弹窗
                var fdDialog = new Dialog({
                    content: $("#remindFdTpl").html(),
                    width: 300
                });

                $(document).on("click", ".js-fdcome-sure", function(){
                    $("input[name=send]").val("true");
                    fdDialog.hide();
                    skuSubmit();
                });
                $(document).on("click", ".js-fdcome-cancel", function(){
                    $("input[name=send]").val("false");
                    fdDialog.hide();
                    skuSubmit();
                });
                function skuSubmit(){
                    $.ajax({
                        url: "${ctx}/brand/product/quickEdit/save",
                        data: $("#revise-price-form").serialize(),
                        method: "post",
                        dataType: "json",
                        success: function(data){
                            remind("success","修改价格库存成功");
                            page.goTo(page.current);
                        }
                    });
                    dpDialog.hide();
                }
                //确定
                $(document).on("click",".js-revise-confirm",function(){
                    if(_self._submitValidator()){
                        if(_self.isSpportFd === true && _self.tiggerFdFlag === false){
                            fdDialog.show();
                        }else{
                            skuSubmit();
                        }
                    }
                });
                //取消
                $(document).on("click",".js-revise-cancel",function(){
                    dpDialog.hide();
                });
                //授信价 操作
                $(document).on("blur", ".js-setcom-cprice", function(){
                    var _index = $(this).data("index");
                    _self.cacheObjSky["sx_" + _index].np = $(this).val();
                    _self._checkModifyPrice();
                });
                //取消红色框
                $(document).on("blur", "input[name^=productSkuList].ui-input", function(){
                    var _val = $(this).val();
                    if(_val != "" && parseFloat(_val) > 0){
                        $(this).removeClass("input-error");
                    }else{
                        $(this).addClass("input-error");
                    }
                });
            },
            setRevisePrice: function(){
                $(document).on("click", "input[name=dprice_n]", function(){
                    var reviseZprice = $(".js-revise-zprice");
                    if($(".js-revise-zjradio").prop("checked") == true){
                        reviseZprice.attr("maxlength", 8).removeClass("setcommon-error");
                    }else{
                        var val = reviseZprice.val();
                        reviseZprice.attr("maxlength", 4);
                        if(val <= 0 || val > 10){
                            reviseZprice.addClass("setcommon-error");
                        }
                    }
                });
                $(document).on("change", ".js-revise-zprice", function(){
                    var val = $(this).val();
                    if($(".js-revise-zkradio").prop("checked") == true){
                        if(val <= 0 || val > 10){
                            $(this).addClass("setcommon-error");
                            remind("error", "折扣必须大于0小于10");
                        }else{
                            $(this).removeClass("setcommon-error");
                        }
                    }else{
                        $(this).removeClass("setcommon-error");
                    }
                });

                $(document).on("click", ".js-revise-set", function(){
                    var dprice = $(".js-revise-dprice").val() == "" ? 0 : parseFloat($(".js-revise-dprice").val()); //吊牌价
                    var zprice = $(".js-revise-zprice").val() == "" ? 0 : parseFloat($(".js-revise-zprice").val()); //直供价
                    var cprice = $(".js-revise-cprice").val() == "" ? 0 : parseFloat($(".js-revise-cprice").val()); //授信价
                    var dcount = $(".js-revise-dcount").val(); //库存
                    var dpType = $(".js-revise-zjradio").prop("checked") == true ? 0 : 1; // 0: 直接设置， 1：折扣设置
                    $("#revise-price-list tr").each(function(){
                        if(dprice != 0){
                            $(this).find(".js-setcom-dprice").val(dprice.toFixed(2));
                        }
                        if(dpType == 0){
                            if(zprice != ""){
                                $(this).find(".js-setcom-zprice").val(zprice.toFixed(2));
                            }
                        }else if(dpType == 1){
                            if(zprice != ""){
                                if(zprice != 0 && zprice <= 10){
                                    if(dprice != 0){
                                        $(this).find(".js-setcom-zprice").val((dprice * zprice / 10).toFixed(2));
                                    }else{
                                        var ddprice = $(this).find(".js-setcom-dprice").val() == "" ? 0 : $(this).find(".js-setcom-dprice").val();
                                        $(this).find(".js-setcom-zprice").val((ddprice * zprice / 10).toFixed(2));
                                    }
                                }else{
                                    remind("error", "折扣必须大于0小于10");
                                }
                            }
                        }
                        if(dcount != ""){
                            $(this).find(".js-setcom-dcount").val(dcount);
                        }
                        if(cprice!=0){
                            $(this).find(".js-setcom-cprice").val(cprice.toFixed(2));
                        }
                    });
                    $(".js-revise-dprice").val("");
                    $(".js-revise-zprice").val("");
                    $(".js-revise-dcount").val("");
                    $(".js-revise-cprice").val("");
                });
            },
            setRebatePrice: function(){
                var _self = this;

                $(document).on("click", ".js-rebate-pro", function(){
                    var _type = $(this).data("type");
                    _self._setEditDisabled(_type);
                });

                $(document).on("change", ".js-rebate-other", function(){
                    var _val = parseFloat($(this).val());
                    if(_val <= 10 || _val > 100){
                        $(this).addClass("setcommon-error");
                        remind("error", "返点比例大于10%, 不能高于 100%");
                    }else{
                        $(this).removeClass("setcommon-error");
                    }
                });

                //设置
                $(document).on("click", ".js-rebate-set", function(){
                    var _type = $(".js-rebate-pro:checked").data("type");
                    var p_fd;
                    if(_type == 0){
                        p_fd = parseFloat($("#select_rebate option:selected").val()) ? parseFloat($("#select_rebate option:selected").val()) : "0.00";
                    }else if(_type == 1){
                        p_fd = parseFloat($(".js-rebate-setext").val()) ? parseFloat($(".js-rebate-setext").val()) : "0.00";
                    }
                    if(p_fd != "0.00" || p_fd != 0){
                        $(".js-setcom-fprice").val(p_fd.toFixed(2));
                    }
                });
            },
            sxChange: function(){
                var _self = this;
                if($("#supportCredit").prop("checked") == true){
                    $(".js-tigger-sx").show();
                    $(".js-sx-type").show();
                    _self.tiggerSxFlag = true;
                }else{
                    $(".js-tigger-sx").hide();
                    $(".js-sx-type").hide();
                    _self.tiggerSxFlag = false;
                }
            },
            sxClick: function(){
                var _self = this;
                //支持授信点击
                $(document).on("click", "#supportCredit", function(){
                    _self.sxChange();
                });
            },
            fdChange: function(){
                var _self = this;
                if($("#support_rebate").prop("checked") == true){
                    $(".js-rebate-display,.js-tigger-fd").show();
                    _self.tiggerFdFlag = true;
                }else{
                    $(".js-rebate-display,.js-tigger-fd").hide();
                    _self.tiggerFdFlag = false;
                }
            },
            fdClick: function(){
                var _self = this;
                $(document).on("click", "#support_rebate", function(){
                    _self.fdChange();
                });

            },
            fdDateSelect: function(){
                $(document).on("click", "#dateRebate", function(){
                    WdatePicker({dateFmt:'yyyy-MM-dd', minDate: '%y-%M-{%d+1}', readOnly: true});
                });
            },
            _submitValidator: function(){
                var _self = this;
                var validatorFlag = true;
                var _p_ny = $("#samplePrice").val();
                var _p_pjz = 0;//现款现货价的最大值
                var tipsFlag = 0;

                //获取设置的返点比例
                if($(".js-rebate-pro:checked").data("type") == 0){
                    var discountPoint = 10;
                }else{
                    if(parseFloat($(".js-rebate-other").val()) && parseFloat($(".js-rebate-other").val()) >= 10){
                        var discountPoint = parseFloat($(".js-rebate-other").val());
                    }else{
                        validatorFlag = false;
                        $(".js-rebate-other").addClass("setcommon-error").focus();
                        remind("error", "返点比例设置不正确");
                    }
                }

                //加入校验
                $("#revise-price-list tr").each(function(i, o){
                    var $this = $(this);
                    var _p_dp = parseFloat($this.find(".js-setcom-dprice").val());
                    var _p_zg = parseFloat($this.find(".js-setcom-zprice").val());
                    var _p_sx = parseFloat($this.find(".js-setcom-cprice").val());
                    var _p_fd = parseFloat($this.find(".js-setcom-fprice").val());

                    var _p_oldfd = parseFloat($this.find(".js-setcom-fprice").data("oldprice"));
                    //console.log("吊牌价：" + _p_dp + " 现款价：" + _p_zg + " 授信价：" + _p_sx);
                    //console.log("_p_oldfd: " + _p_oldfd + " _p_fd: " + _p_fd);


                    if(!_p_dp || _p_dp == 0){
                        validatorFlag = false;
                        $this.find(".js-setcom-dprice").addClass("input-error");
                        if(tipsFlag == 0){
                            remind("error", "吊牌价不能为空或0", 1600, function(){
                                $this.find(".js-setcom-dprice.input-error").focus();
                            });
                            if(_self.tiggerSxFlag !== true) {
                                tipsFlag = 2;
                            }else{
                                tipsFlag = 1;
                            }
                        }
                    }

                    if(!_p_zg || _p_zg == 0 || _p_zg > _p_dp){
                        validatorFlag = false;
                        $(this).find(".js-setcom-zprice").addClass("input-error");
                        if(tipsFlag == 0){
                            remind("error", "直供价、授信价不能为0，且必须小于吊牌价", 1600, function(){
                                $this.find(".js-setcom-zprice.input-error").focus();
                            });
                            if(_self.tiggerSxFlag !== true) {
                                tipsFlag = 2;
                            }else{
                                tipsFlag = 1;
                            }
                        }
                    }

                    if(_self.tiggerSxFlag == true && _self.tiggerFdFlag == true) {
                        if (!_p_fd || _p_fd == 0 || (_p_fd * (1 - discountPoint/100)) > _p_sx) {
                            validatorFlag = false;
                            $(this).find(".js-setcom-fprice").addClass("input-error");
                            if (tipsFlag == 0) {
                                remind("error", "返点成本价不能为0，且必须小于等于授信价", 1600, function () {
                                    $this.find(".js-setcom-fprice.input-error").focus();
                                });
                                if (_self.tiggerSxFlag !== true) {
                                    tipsFlag = 2;
                                }else{
                                    tipsFlag = 1;
                                }
                            }
                        }
                    }

                    //无授信价的时候，返点价和现款价比较
                    if(_self.tiggerSxFlag == false && _self.tiggerFdFlag == true){
                        if (!_p_fd || _p_fd == 0 || (_p_fd * (1 - discountPoint/100)) > _p_zg) {
                            validatorFlag = false;
                            $(this).find(".js-setcom-fprice").addClass("input-error");
                            if (tipsFlag == 0) {
                                remind("error", "返点成本价不能为0，且必须小于等于直供价", 1600, function () {
                                    $this.find(".js-setcom-fprice.input-error").focus();
                                });
                                if (_self.tiggerSxFlag !== true) {
                                    tipsFlag = 2;
                                }else{
                                    tipsFlag = 1;
                                }
                            }
                        }
                    }

                    if(_self.tiggerFdFlag == true){
                        //bug #7274 修改返点价时，新的成本价不能高于原来的成本价
                        if(_p_oldfd != 0 && _p_oldfd < _p_fd){
                            validatorFlag = false;
                            $(this).find(".js-setcom-fprice").addClass("input-error");
                            if (tipsFlag == 0) {
                                remind("error", "新的成本价不能高于原来的成本价", 1600, function () {
                                    $this.find(".js-setcom-fprice.input-error").focus();
                                });
                                if (_self.tiggerSxFlag !== true) {
                                    tipsFlag = 2;
                                }else{
                                    tipsFlag = 1;
                                }
                            }
                        }
                    }

                    //console.log(_self.tiggerSxFlag)
                    if(_self.tiggerSxFlag == true){
                        if(!_p_sx || _p_sx == 0 || _p_sx > _p_dp){
                            validatorFlag = false;
                            $(this).find(".js-setcom-cprice").addClass("input-error");
                            if(tipsFlag == 0){
                                remind("error", "直供价、授信价不能为0，且必须小于吊牌价", 1600, function(){
                                    $this.find(".js-setcom-cprice.input-error").focus();
                                });
                                tipsFlag = 2;
                            }
                        }
                    }

                    if(i == 0){
                        _p_pjz = _p_zg;
                    }else{
                        if(_p_zg > _p_pjz){
                            _p_pjz = _p_zg;
                        }
                    }

                });
                //拿样价
                if($("input[name=sample]").prop("checked") == true){
                    if(_p_ny == 0 || _p_ny == ""){
                        validatorFlag = false;
                        $("#samplePrice").focus().addClass("input-error");
                    }
                }
                //console.log(_p_ny < _p_pjz && $("input[name=sample]").prop("checked") == true)
                /*if(_p_ny < _p_pjz && $("input[name=sample]").prop("checked") == true){
                    validatorFlag = false;
                    remind("error", "您设置的拿样价，低于现款现货的价格，确认吗？");
                }*/

                if($("#support_rebate").prop("checked") == true && $(".js-rebate-pro:checked").data("type") == 1){
                    var _val = parseFloat($(".js-rebate-other").val());
                    if(_val <= 10 || _val > 100){
                        validatorFlag = false;
                        $(".js-rebate-other").addClass("setcommon-error");
                        remind("error", "返点比例大于10%, 不能高于 100%");
                    }
                }

                return validatorFlag;
            },
            _realModifyPrice: function(){
                //{sx_0: {op: 12, np: 23}}
                var _self = this;
                _self.cacheObjSky = {};
                $("#revise-price-list .js-setcom-cprice").each(function(i){
                    var _index = $(this).data("index");
                    _self.cacheObjSky["sx_" + _index] = {};
                    _self.cacheObjSky["sx_" + _index].op = $(this).val();
                });


                //console.log(_self.cacheObjSky);
            },
            _checkModifyPrice: function(){
                var _self = this;
                //比较所有修改过授信价的SKU，如果新价格小于旧价格，则 “当前终端商的库存生效” 不能选
                var $costPushRadio = $("input[name=costPush]");
                for(var key in _self.cacheObjSky){
                    //console.log();
                    if(_self.cacheObjSky[key].np != undefined){
                        if(parseFloat(_self.cacheObjSky[key].op) > parseFloat(_self.cacheObjSky[key].np)){
                            $costPushRadio.eq(0).prop({
                                checked: false,
                                disabled: true
                            });
                            $costPushRadio.eq(1).prop("checked", true);
                            return false;
                        }
                    }
                }
                $costPushRadio.eq(0).prop({
                    disabled: false
                });
            },
            _setEditDisabled: function(type){
                if(type == 0){
                    $(".js-rebate-other,.js-rebate-setext").prop("disabled", true);
                    $("#select_rebate").prop("disabled", false);
                    $(".js-setcom-fprice").prop("readonly", true);
                }else if(type == 1){
                    $(".js-rebate-other,.js-rebate-setext").prop("disabled", false);
                    $("#select_rebate").prop("disabled", true);
                    $(".js-setcom-fprice").prop("readonly", false);
                }
            },
            editTitle: function(){
                var editClass = "js-edit-title";
                var tempText = "";

                function checkProductTitle(str){
                    var len = getCharLength(str);
                    if (len < 1 || len > 120) {
                        return false;
                    } else {
                        return true;
                    }
                }

                $("#list-table").on("click", ".js-edit-title", function(){
                    $(this).find(".iconfont").remove();
                    var _text = $(this).text();
                    var $tpl = $('<textarea class="ui-textarea js-edititle-input">' + _text + '</textarea>');
                    tempText = _text;
                    $(this).removeClass(editClass).html($tpl);
                    $tpl.focus();
                });
                $("#list-table").on("blur", ".js-edititle-input", function(){
                    var _parent = $(this).parent();
                    var _text = $(this).val();
                    var _id = _parent.data("id");
                    //标题有修改变动，且在规定的字符内
                    if(tempText != _text){
                        if(checkProductTitle(_text)) {
                            $.ajax({
                                url: "/brand/product/editTitle",
                                type: "post",
                                data: {title:_text,refrenceId:_id},
                                dataType: "json",
                                success: function(json){
                                    if (zttx.SUCCESS == json.code) {
                                        remind("success", "修改标题成功");
                                    } else {
                                        _text = tempText;
                                        remind("error", "修改失败");
                                    }
                                    var _tpl = '<h4 class="title">' + _text + '<i class="iconfont">&#xe62f;</i></h4>';
                                    _parent.addClass(editClass).html(_tpl);
                                }
                            });
                        }else{
                            remind("error", "标题长度不能超过60个汉字");
                        }
                    }else{
                        var _tpl = '<h4 class="title">' + _text + '<i class="iconfont">&#xe62f;</i></h4>';
                        _parent.addClass(editClass).html(_tpl);
                    }
                });
            }
        };
        pmlist.init();
    });

    var $tbody = $('#list-table').find('tbody');
</script>
<script>
    var page;
    $(function () {
        $(document).on('click','.arrow-down,.arrow-down-current,.arrow-top-current',function(){
            var $this = $(this), addClass = '';
            if($this.hasClass('arrow-down'))
            {
                $('#attrType').val($this.data('attr'));
                $('#descType').val(1);
                addClass = 'arrow-down-current';
            }else if($this.hasClass('arrow-down-current'))
            {
                $('#attrType').val($this.data('attr'));
                $('#descType').val(0);
                addClass = 'arrow-top-current';
            }else if($this.hasClass('arrow-top-current'))
            {
                $('#attrType').val(0);
                $('#descType').val(1);
                addClass = 'arrow-down';
            }
            $('.arrow-down,.arrow-down-current,.arrow-top-current').removeClass("arrow-down-current").removeClass("arrow-top-current").addClass("arrow-down");
            $this.removeClass('arrow-down').addClass(addClass);

            page.goTo(page.current);
        });

        $(document).on('click','.arrow-toTop',function(){
            var productId = $(this).data("id");
            $.post("${ctx}/brand/product/setTop", {productId: productId}, function(data){
                if(data.code == zttx.SUCCESS)
                {
                    remind("success", "置顶成功");
                    page.goTo(page.current);
                }
                else
                {
                    remind("error", data.message);
                }
            }, "json");
        });

        $(document).on('click','.arrow-cancel',function(){
            var productId = $(this).data("id");
            $.post("${ctx}/brand/product/cancelTop", {productId: productId}, function(data){
                if(data.code == zttx.SUCCESS)
                {
                    remind("success", "置顶取消成功");
                    page.goTo(page.current);
                }
                else
                {
                    remind("error", data.message);
                }
            }, "json");
        });

        seajs.use(["pagination", "moment", "template", "dialog"], function (Pagination, moment, template, Dialog) {
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD");
            });
            template.helper('formatNumber',function(price){
                if(isNaN(price)){
                    return price;
                }else{
                    return parseFloat(price).toFixed(2);
                }
            });
            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });
            template.helper('$formatMoney', function (num) {
                num = num == null ? 0 : num;
                return num.toFixed(2);
            });

            $(".checkallbox").on("click",".js-chkall",function(){
                $(".js_chk").prop("checked",this.checked);
            });

            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/brand/product/ajax_list",
                    elem: "#pagination",
                    form:$("#search-form"),
                    pageSize: 20,
                    method:"post",
                    handleData: function (json) {
                        var html = template.render("line-template", json);
                        $tbody.empty().append(html);
                        if(json.rows=="")
                        {
                            $("#proOper").hide();
                        }else
                        {
                            $("#proOper").show();
                        }
                        seajs.use("jscroll",function(){
                            $(".scrollArea").jscrollbar({
                                width: "5"
                            });
                        });

                        $tbody.find("input[type='checkbox']").each(function(){
                            $(this).click(function(){
                                /*    console.log(); */
                                if(!$(this).prop("checked")){
                                    $(".js-chkall").prop("checked",false);
                                }
                            });
                        });

                        productmanagelist.init();
                    }
                });
            };
            renderPagination();

            //bug #6256 支持拿样 改为 输入拿样价格
            var sspDialog = new Dialog({
                content: $("#set-sample-template").html(),
                width: 500
            }).after("render", function(){
                        $(".js-sspValue").isPrice();
                        baseFormValidator({
                            selector:"#setSampleForm",
                            isAjax: true,
                            beforeSubmitFn: function(){
                                //console.log($("#ajaxForm").serialize() + "&samplePrice=" + $(".js-sspValue").val()+"&sample=true");
                                var _str = "", _tipFlag = 0;
                                $(".js-single-sspValue").each(function(){
                                    var id = $(this).data("id");
                                    var _val = $(this).val();
                                    //提示
                                    if(_val == "" || _val == 0){
                                        if(_tipFlag == 0){
                                            _tipFlag = 1;
                                            $(this).focus();
                                            remind("error", "拿样价不能为空或0");
                                        }
                                    }
                                    //这里可以组合需要的id和value
                                    if(_str == ""){
                                        _str += "productIds=" + id + "&samplePrice=" + _val;
                                    }else{
                                        _str += "&productIds=" + id + "&samplePrice=" + _val;
                                    }
                                });
                                _str+="&sample=true";
                                if(_tipFlag == 0) {
                                    $.ajax({
                                        url: "/brand/product/set/sample",
                                        type: "post",
                                        data: _str,
                                        dataType: "json",
                                        success: function (json) {
                                            if (zttx.SUCCESS == json.code) {
                                                remind("success", "设置拿样价格成功");
                                            } else {
                                                remind("error", json.message);
                                            }
                                            sspDialog.hide();
                                            refresh();
                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            remind("error", errorThrown);
                                        }
                                    });
                                }
                            }
                        });
                    }).render();
            window.setSamplePrice = function(){
                var mark = setAjaxFormData();
                if(!mark){
                    return false;
                }

                var _simpleEditArr = [], _htm = [];
                $("#list-table input.js_chk:checked").each(function(){
                    var _parents = $(this).parents("tr"),
                            _productNo = $(this).data("productno"),
                            _productTitle = $(this).data("productitle"),
                            _brandsName=$(this).data("brandsname"),
                            _samplePrice=$(this).data("sampleprice"),
                            _simpleEditObj = {};
                    //_simpleEditObj.brandName =
                    _simpleEditObj.productNo = _productNo;
                    _simpleEditObj.productTitle = _productTitle;
                    _simpleEditObj.id =  $(this).attr("id");
                    _simpleEditObj.brandsName=_brandsName;
                    _simpleEditObj.samplePrice=_samplePrice;
                    _simpleEditArr.push(_simpleEditObj);
                });
                for(var i = 0; i < _simpleEditArr.length; i++){
                    _htm.push('<tr><td>'+_simpleEditArr[i].brandsName+'</td><td>' + _simpleEditArr[i].productNo + '</td><td>' + _simpleEditArr[i].productTitle + '</td><td><input type="text" data-id="' + _simpleEditArr[i].id + '" class="ui-input js-single-sspValue" value="' + _simpleEditArr[i].samplePrice + '" maxlength="8" /></td></tr>');
                }
                $("#sample-price-list").html(_htm.join(""));
                $(".js-single-sspValue").isPrice();
                sspDialog.show();
            };
            $("#batchSetSamplePrice").click(function(){
                var _price = $("#setSamplePrice").val();
                if(_price != ""){
                    $(".js-single-sspValue").val(_price);
                }
            });
            $(document).on("click", ".js-ssprice-cancel", function(){
                sspDialog.hide();
            });

            //bug #5976
            $(".js-triggerFilter").click(function(){
                var _val = $(this).val();
                if(_val == "更多筛选条件"){
                    $(".js-moreFilter").show();
                    $(this).val("隐藏筛选条件")
                }
                if(_val == "隐藏筛选条件"){
                    $(".js-moreFilter").hide();
                    $(this).val("更多筛选条件")
                }
            });
        });
    });
</script>
<script>
    function sampleProduct(isSample){
        var mark = setAjaxFormData();
        if(!mark){
            return;
        }
        var msg = "取消拿样";
        if(isSample){
            msg = "支持拿样";
        }
        confirmDialog("是否"+msg,function(){
            $.ajax({
                url:"/brand/product/ajax_sample",
                type:"post",
                data:$("#ajaxForm").serialize()+"&isSample="+isSample,
                dataType: "json",
                success:function(json){
                    if(zttx.SUCCESS==json.code){
                        remind("success",msg+"成功");
                    }else
                        remind("error",json.message);
                    refresh();
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    remind("error",errorThrown);
                }
            });
        });
    }
    function setSsample(){
        var mark = setAjaxFormData();
        if(!mark){
            return;
        }

        confirmDialog("是否设置运费",function(){
            var productIds = "";
            $("#list-table input.js_chk:checked").each(function(){
                var id = $.trim($(this).attr("id"));
                if(id!=""){
                    productIds += id+",";
                }
            });
            //console.log(productIds);
            $("#ajaxForm input[name='productIds']").remove();
            $("#ajaxForm").append('<input type="hidden" name="productIds" value="'+productIds+'">');
            $("#ajaxForm").attr("action", "${ctx}/brand/freightTemplate/list");
            $("#ajaxForm").submit();
        });
    }

</script>

</body>
</html>