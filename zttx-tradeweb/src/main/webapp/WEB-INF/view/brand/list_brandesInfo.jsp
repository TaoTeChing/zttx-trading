<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ page import="com.zttx.web.consts.BrandConstant" %>
    <c:set var="BRAND_STATE_NEW" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED%>"/>
    <c:set var="BRAND_STATE_VERIFY" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED%>"/>
    <c:set var="BRAND_STATE_JOIN" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED%>"/>
    <c:set var="BRAND_STATE_CANCEL" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_CANCEL%>"/>
    <c:set var="BRAND_STATE_PAST" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED%>"/>
    <c:set var="BRAND_STATE_FAIL" value="<%=BrandConstant.BrandesInfoConst.BRAND_STATE_FAILURE%>"/>
    <%@ page import="com.zttx.web.consts.BrandesInfoConst" %>
    <c:set var="TOURIST" value="<%=BrandesInfoConst.UserAuth.TOURIST%>"/>
    <c:set var="MEMBER_REGISTER" value="<%=BrandesInfoConst.UserAuth.MEMBER_REGISTER%>"/>
    <c:set var="MEMBER_CERTIFIED" value="<%=BrandesInfoConst.UserAuth.MEMBER_CERTIFIED%>"/>
    <c:set var="MEMBER_JOIN" value="<%=BrandesInfoConst.UserAuth.MEMBER_JOIN%>"/>
    <title>管理中心-品牌管理-我的品牌</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css"/>
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
        <a class="link" href="${ctx}/brand/brandesInfo/brandes">品牌管理</a>
        <span class="arrow">&gt;</span>
        <span class="current">我的品牌</span>
    </div>
</div>
<div class="inner">
<!-- 内容都放这个地方  -->
<%--帮助中心<div class="fr">
    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp"/>
</div>--%>
<div class="ui_tab main_ui_tab">
    <ul class="js_tab_box ui_tab_items clearfix">
        <li class="ui_tab_item <c:if test="${brandState==null}">current</c:if>">
            <a href="/brand/brandesInfo/brands/list">全部</a>
        </li>
        <li class="ui_tab_item <c:if test="${brandState==BRAND_STATE_JOIN}">current</c:if>">
            <a href="/brand/brandesInfo/brands/list/${BRAND_STATE_JOIN}">合作中</a>
        </li>
        <li class="ui_tab_item <c:if test="${brandState==BRAND_STATE_VERIFY}">current</c:if>" style="display:none;">
            <a href="/brand/brandesInfo/brands/list/${BRAND_STATE_VERIFY}">通过</a>
        </li>
        <li class="ui_tab_item <c:if test="${brandState==BRAND_STATE_NEW}">current</c:if>">
            <a href="/brand/brandesInfo/brands/list/${BRAND_STATE_NEW}">审核中</a>
        </li>
        <li class="ui_tab_item <c:if test="${brandState==BRAND_STATE_FAIL}">current</c:if>">
            <a href="/brand/brandesInfo/brands/list/${BRAND_STATE_FAIL}">未通过</a>
        </li>
        <li class="ui_tab_item <c:if test="${brandState==BRAND_STATE_PAST}">current</c:if>">
            <a href="/brand/brandesInfo/brands/list/${BRAND_STATE_PAST}">过期</a>
        </li>
    </ul>
</div>
<div class="js_tab_con_box tab_con_box warrant_tab_con">
<div class="tab_con" style="display: block;">
<ul class="wrap_ul">
<c:forEach items="${brandList}" var="brand">
	<c:set value="${ fns:getBrandsIdSubDomain(brand.refrenceId)}" var="domain"></c:set>
    <li class="item">
        <div class="pic_area js_img_center">
            <!-- 产品形象图 -->
            <c:set value="${fileUrl}${brand.proLogo}" var="url"></c:set>
            <c:choose>
                 	<c:when test="${brand.brandState==BRAND_STATE_JOIN}">
                 	<a href="http://${domain}${zttx}" target="_blank"><img src="${fns:getImageDomainPath(url, 220, 220)}" style="width:240px;height:240px;" alt=""/></a>
                 	</c:when>
                 	<c:otherwise>
                 	<img src="${fns:getImageDomainPath(url, 220, 220)}" style="width:240px;height:240px;" alt=""/>
                 	</c:otherwise>
            </c:choose>
            <!--div class="small_pic js_img_center" style="width: 240px; height: 85px; overflow: hidden;">

            </div-->
        </div>
        <div class="info_area">
            <div class="top clearfix">
                <div class="small_pic">
                	<c:choose>
	                 	<c:when test="${brand.brandState==BRAND_STATE_JOIN}">
	                 		<c:choose>
		                        <c:when test="${!empty brand.brandLogo}">
		                        	<c:set value="${fileUrl}${brand.brandLogo}" var="url"></c:set>
		                            <a href="http://${domain}${zttx}" target="_blank"><img src="${fns:getImageDomainPath(url, 240, 120)}" style="width:160px;height:80px;" alt=""/></a>
		                        </c:when>
		                        <c:otherwise>
		                            <a href="http://${domain}${zttx}" target="_blank"><img src="${res}/images/common/160-80.gif" onerror="javascript:this.src='${res}/images/common/160-80.gif'" style="width:160px;height:80px;" alt=""/></a>
		                        </c:otherwise>
		                    </c:choose>
	                 	</c:when>
	                 	<c:otherwise>
		                 	<c:choose>
		                        <c:when test="${!empty brand.brandLogo}">
		                        	<c:set value="${fileUrl}${brand.brandLogo}" var="url"></c:set>
		                            <img src="${fns:getImageDomainPath(url, 240, 120)}" style="width:160px;height:80px;" alt=""/>
		                        </c:when>
		                        <c:otherwise>
		                            <img src="${res}/images/common/160-80.gif" onerror="javascript:this.src='${res}/images/common/160-80.gif'" style="width:160px;height:80px;" alt=""/>
		                        </c:otherwise>
	                    	</c:choose>
	                 	</c:otherwise>
            		</c:choose>
                    
                </div>
                <div class="right">
                    <h3>
                    	<c:choose>
	                    	<c:when test="${brand.brandState==BRAND_STATE_JOIN}">
	                    	<a href="http://${domain}${zttx}" target="_blank">${brand.brandsName}</a>
	                    	</c:when>
	                    	<c:otherwise>
	                    	${brand.brandsName}
	                    	</c:otherwise>
                    	</c:choose>
                        <c:if test="${brand.brandState==BRAND_STATE_VERIFY || brand.brandState==BRAND_STATE_JOIN}">
                            <span style="margin-left: 8px;" class="link"><a href="http://${domain}${zttx}" target="_blank">详细资料&gt;</a></span>
                        </c:if>
                    </h3>
                    <c:if test="${!empty brand.brandDealList}">
                    <p class="mt15 fn-text-overflow" title="所属类目：${brand.brandDealAllName}">所属类目：
        			${brand.brandDealAllName}           
					</p>
                    </c:if>
                    <c:choose>
                    	<c:when test="${brand.brandState==BRAND_STATE_VERIFY || brand.brandState==BRAND_STATE_JOIN}">
                    		<div class="rank">当前类目排名：> ${empty brand.brandsCount ? 0 : brand.brandsCount.ranking }名</div>
                    	</c:when>
                    </c:choose>
                </div>
            </div>
                <c:choose>
                    <c:when test="${brand.brandState==BRAND_STATE_VERIFY || brand.brandState==BRAND_STATE_JOIN}">
                        <div class="detail_info">
                            <p>
                                <span>已加盟终端商：<a href="${ctx}/brand/join/list?bid=${brand.refrenceId}"><strong>${empty brand.brandsCount ? 0 : brand.brandsCount.joinCount}</strong></a> 家</span>
                                <span>申请中：<a href="${ctx}/brand/dealer/listDealerApply"><strong>${empty brand.brandsCount ? 0 : brand.brandsCount.applyCount}</strong></a> 家</span>
                                <span class="last">已有产品：<a href="${ctx}/brand/product/list?list_brandsId=${brand.refrenceId}"><strong>${empty brand.brandsCount ? 0 : brand.brandsCount.productCount}</strong></a> 款</span>
                                <%--<span class="last">已有产品线：<a href="${ctx}/brand/line/list?brandsId=${brand.refrenceId}"><strong>${empty brand.brandsCount ? 0 : brand.brandsCount.productCountline}</strong></a> 款</span>
                            	--%>
                            </p>
                        </div>
                    </c:when>
                    <c:when test="${brand.brandState==BRAND_STATE_PAST}">
                        <div class="detail_info">
                            <p>
                                <span>已加盟终端商：<strong>${empty brand.brandsCount ? 0 : brand.brandsCount.joinCount}</strong> 家</span>
                                <span>申请中：<strong>${empty brand.brandsCount ? 0 : brand.brandsCount.applyCount}</strong> 家</span>
                                <span class="last">已有产品：<strong>${empty brand.brandsCount ? 0 : brand.brandsCount.productCount}</strong> 款</span>
                                <%--<span class="last">已有产品线：<strong>${empty brand.brandsCount ? 0 : brand.brandsCount.productCountline}</strong> 款</span>
                            	--%>
                            </p>
                        </div>
                    </c:when>
                    <c:otherwise>


                    </c:otherwise>


                </c:choose>
            <c:choose>
                <c:when test="${brand.brandState==BRAND_STATE_JOIN}">
                    <c:set value="合作中" var="state"></c:set>
                    <c:set value="status_1" var="stateClass"></c:set>
					<c:if test="${not empty brand.beginTime && not empty brand.endTime && not empty brand.ensureMoney}">
                    <div class="other_info">
                        <p>
                            服务时间：${fns:getTimeFromLong(brand.beginTime, 'yyyy-MM-dd')}到${fns:getTimeFromLong(brand.endTime, 'yyyy-MM-dd')}
                            <c:if test="${!empty brand.ensureMoney}"><span class="ml15">已缴纳<strong>${brand.ensureMoney}</strong>元保证金</span></c:if>
                        </p>
                    </div>
                    </c:if>
                    <div class="operate">
                        <ul class="inline-float">
                            <li class="view_btn">
                                <a href="http://${domain}${zttx}" target="_blank">
                                    查看展厅
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/product/execute">发布产品</a>
                            </li>
                            <li>
                                <a class="simple_button" href="/decoration/managePage/${brand.refrenceId}" target="_blank">展厅装修</a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandRoom/execute?id=${brand.refrenceId}">
                                    修改资料
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandRecruit/execute?id=${brand.refrenceId}" target="_blank">
                                    品牌招募书
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandNetwork/execute?id=${brand.refrenceId}" target="_blank">
                                    门店展示
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandVisual/visual?id=${brand.refrenceId}" target="_blank">
                                    陈列视觉
                                </a>
                            </li>
                            <c:if test="${brand.brandState==BRAND_STATE_JOIN}">
                            <li>
                                <a class="simple_button" href="/brand/brandesInfoRegional/view?id=${brand.refrenceId}" target="_blank">
                                    区域设置
                                </a>
                            </li>
                            <li>
                                <a class="simple_button js-warrant" href="javascript:;" data-id="${brand.refrenceId}" data-values="${brand.userAuth}">
                                    用户授权
                                </a>
                            </li>
                            </c:if>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${brand.brandState==BRAND_STATE_VERIFY}">
                    <c:set value="通过" var="state"></c:set>
                    <c:set value="status_2" var="stateClass"></c:set>
                    <div class="other_info" style="display:none;">
                        <p>服务时间：未购买，<a class="buy_link" href="#">点击购买</a>
                        </p>

                        <p>还未缴纳保证金，<a class="buy_link" href="#">点击缴纳</a>
                        </p>
                    </div>
                    <div class="operate">
                        <ul class="inline-float">
                            <li class="view_btn">
                                <a href="http://${domain}${zttx}" target="_blank">
                                    查看展厅
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/decoration/managePage/${brand.refrenceId}">展厅装修</a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandRoom/execute?id=${brand.refrenceId}">
                                    修改资料
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/recruit/execute?id=${brand.refrenceId}" target="_blank">
                                    品牌招募书
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/brandNetwork/execute?id=${brand.refrenceId}" target="_blank">
                                    门店展示
                                </a>
                            </li>
                            <li>
                                <a class="simple_button" href="/brand/visual?id=${brand.refrenceId}" target="_blank">
                                    陈列视觉
                                </a>
                            </li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${brand.brandState==BRAND_STATE_NEW}">
                    <c:set value="审核中" var="state"></c:set>
                    <c:set value="status_3" var="stateClass"></c:set>
                    <div class="other_info">
                        <p class="error_info">客服人员正在进行审核，请耐心等待</p>
                    </div>
                    <div class="operate">
                        <ul class="inline-float">
                            <li>
                                <a class="simple_button" href="/brand/brandesInfo/update/${brand.refrenceId}?t=${brandState}">
                                    修改资料
                                </a>
                            </li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${brand.brandState==BRAND_STATE_FAIL}">
                    <c:set value="未通过" var="state"></c:set>
                    <c:set value="status_4" var="stateClass"></c:set>
                    <div class="other_info">
                        <p class="error_info">${brand.checkMark}</p>
                    </div>
                    <div class="operate">
                        <ul class="inline-float">
                            <li>
                                <a class="simple_button" href="/brand/brandesInfo/update/${brand.refrenceId}?t=${brandState}">
                                    修改资料
                                </a>
                            </li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${brand.brandState==BRAND_STATE_PAST}">
                    <c:set value="过期" var="state"></c:set>
                    <c:set value="status_5" var="stateClass"></c:set>
                    <div class="other_info">
                        <p>
                            服务时间：<strong>已过期</strong>
                            <c:if test="${!empty brand.ensureMoney}"><span class="ml15">已缴纳<strong class="price">${brand.ensureMoney}</strong>元保证金</span></c:if>
                        </p>
                    </div>
                    <div class="operate">
                        <ul class="inline-float">
                            <li class="view_btn">
                                <a href="http://${domain}${zttx}" target="_blank">
                                    查看展厅
                                </a>
                            </li>
                            <li style="display:none;">
                                <a class="simple_button" href="#">签约续期</a>
                            </li>
                            <li style="display:none;">
                                <a class="simple_button" href="/decoration/managePage/${brand.refrenceId}">展厅装修</a>
                            </li>
                            <li style="display:none;">
                                <a class="simple_button" href="/brand/brandes/${brand.refrenceId}?t=${brandState}">
                                    修改资料
                                </a>
                            </li>
                        </ul>
                    </div>
                </c:when>

            </c:choose>
        </div>
        <span class="tag ${stateClass}">${state}</span>
    </li>
</c:forEach>
<li class="item add_brand">
    <a class="add_brand_link" href="/brand/brandesInfo/brandes">
        <i class="iconfont">&#xe611;</i>
        <p>添加品牌</p>
    </a>
</li>
</ul>
</div>
</div>
<div class="service_con">
    <h3><span>服务内容说明</span></h3>
    <p>
        <label>服务周期：</label>指的是平台为您提供的运维时间，周期内您可以享受平台为您提供的各项服务。
    </p>

    <p>
        <label>审核周期：</label>指的是您在提交新增加的品牌资料时，平台对您提交的资料的审核时间，一般为1-3个工作日。
    </p>
</div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script id="warrant-tpl" type="text/html">
    <div class="ui-head">
        <h3>用户授权</h3>
    </div>
    <form class="ui-form mt15" id="warrant_form" action="" method="post">
        <div class="confirm_bd">
            <input type="hidden" id="warrant_id" name="brandsId"/>
            <div class="ui-form-item">
                <label class="ui-label">授权用户：</label>
                <div style="line-height: 30px;">
                    <div><label><input type="checkbox" name="userAuthCodes" id="user_auth_code_yk" value="${TOURIST}" autocomplete="off"/> 游客</label></div>
                    <div><label><input type="checkbox" name="userAuthCodes" id="user_auth_code_zchy" value="${MEMBER_REGISTER}" autocomplete="off"/> 注册会员</label></div>
                    <div><label><input type="checkbox" name="userAuthCodes" id="user_auth_code_rzhy" value="${MEMBER_CERTIFIED}" autocomplete="off"/> 认证会员</label></div>
                    <div><label><input type="checkbox" name="userAuthCodes" id="user_auth_code_jmyh" value="${MEMBER_JOIN}" checked="checked" disabled autocomplete="off"/> 加盟用户</label></div>
                </div>
            </div>
        </div>
        <div class="operate ta-c">
            <a class="simple_button confirm_btn js-warrant-confirm" href="javascript:;">确定</a>
            <%--<button class="simple_button confirm_btn js-warrant-confirm" type="submit">确定</button>--%>
            <a class="simple_button cancel_btn js-warrant-cancel" style="" href="javascript:;">取消</a>
        </div>
    </form>
</script>
<script>
    $(function(){
        seajs.use(['dialog', 'template'],function(Dialog, Template){
            var myBrand = {
                init: function(){
                    this.userWarrant();
                },
                userWarrant: function(){
                    var triggrCache;    //缓存点击触发的按钮，用以在请求完成以后写值
                    var warrantDialog = new Dialog({
                        content: $("#warrant-tpl").html()
                    });
                    $(".js-warrant").click(function(){
                        var id = $(this).data("id");
                        var values = $(this).data("values");
                        triggrCache = $(this);
                        warrantDialog.show();
                        $("#warrant_id").val(id);
                        $("input[name=userAuthCodes]:not(:disabled)").prop("checked", false);
                        if(values !== ""){
                            var valuesArr = values.split(",");
                            for(var i = 0; i < valuesArr.length; i++){
                                $("input[name=userAuthCodes][value=" + valuesArr[i] + "]").prop("checked", true);
                            }
                        }
                    });
                    $(document).on("click", ".js-warrant-confirm" ,function(){
                        dialogLoading(function(dd){
                            $.ajax({
                                url: '${ctx}/brand/brandesInfo/userAuth',
                                method: 'post',
                                data: $('#warrant_form').serialize(),
                                dataType: 'json',
                                success: function(data){
                                    if(data.code !== 126000){
                                        remind("error",data.message);
                                    }else{
                                        var __str = "";
                                        $("input[name=userAuthCodes]:checked").each(function(){
                                            var __val = $(this).val();
                                            __str += __val + ",";
                                        });
                                        triggrCache.data("values", __str.substring(0, __str.length - 1));
                                    }
                                    dd.hide();
                                }
                            });
                        },"正在处理用户授权，请稍后...");
                        warrantDialog.hide();
                    });
                    $(document).on("click", ".js-warrant-cancel" ,function(){
                        warrantDialog.hide();
                    });

                    //勾选逻辑
                    //游客
                    $(document).on("click", "#user_auth_code_yk", function(){
                        $("#user_auth_code_zchy,#user_auth_code_rzhy").prop("checked", this.checked);
                    });
                    //注册会员
                    $(document).on("click", "#user_auth_code_zchy", function(){
                        $("#user_auth_code_rzhy").prop("checked", this.checked);
                        if(this.checked == false){
                            $("#user_auth_code_yk").prop("checked", false);
                        }
                    });
                    //认证会员
                    $(document).on("click", "#user_auth_code_rzhy", function(){
                        if(this.checked == false){
                            $("#user_auth_code_yk,#user_auth_code_zchy").prop("checked", false);
                        }
                    });
                }
            };
            myBrand.init();
        });
    });
</script>
</body>
</html>