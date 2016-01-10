<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-交易管理-发货地址</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/brand_trade.css"/>
    <style>
        .main .inner {
            background: #fff;
        }
        .Validform_wrong{
            color: #FF5243;
            background: url("/images/common/icon_warn.png") no-repeat scroll 0 -243px rgba(0, 0, 0, 0);
            padding-left: 20px;
            display: inline-block;
            *display: inline;
            *zoom: 1;
            height: 28px;
            line-height: 28px;
        }
        .vertical-tip .Validform_wrong{
            display: block;
        }
        .ui-select{
            width: 120px;
        }
    </style>
</head>
<body>
	<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">发货地址</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <!-- 面包屑结束 -->
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="trade_address">
                <h3 class="mt10 add_address_title">
                    <span>修改地址</span>
                </h3>
                <div class="tips">
                    <i class="v2-icon-explain"></i>说明：您可根据品牌设置品牌的退货地址，终端商退货时，会将货品退至您设置的地址
                </div>
                <form:form id="address-form" class="ui-form addAddress-form" method="post" action="${ctx}/brand/brandAddress/update">
                    <div class="ui-form-item">
                        <input type="hidden" name="refrenceId" value="${id}" />
                        <label class="ui-label" for="">
                            品牌名：<span class="ui-form-required">*</span>
                        </label>
                        <span class="ui-form-noEdit">${brandsName}</span>
                        <input type="hidden" name="brandsId" value="${oldAddress.brandsId}">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            仓库：<span class="ui-form-required">*</span>
                        </label>
                        <%--<label for="">
                            <input class="ui-radio" name="newStore" value="false" type="radio" ${empty stores ? '':'checked="true"'}/>使用现有仓库
                        </label>

                        <div class="inline-block">
                            <select id="js-select-stores" style="width: 110px;" name="storeId" class="js_select ui-select">
                                <option value="">请选择仓库...</option>
                                <c:forEach items="${stores}" var="store">
                                    <option value="${store.refrenceId}" ${store.refrenceId == oldAddress.storeId? "selected='selected'":""}>${store.storeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="ml10" for="">
                            <input class="ui-radio" name="newStore" type="radio" value="true" ${empty stores ? 'checked="true"':''} />使用新仓库
                        </label>
                        <input id="js-store-name" name="newStoreName" type="text" maxlength="64"  placeholder="输入文本" class="ui-input"/>
                        
                        --%>
                        <input type="hidden" name="newStore" value="true" />
                        <c:forEach items="${stores}" var="store">
                        	<c:if test="${store.refrenceId == oldAddress.storeId}">
                        		<input id="js-store-name" name="newStoreName" type="text" datatype="*1-32,isNull" nullmsg="请填写仓库" maxlength="64" placeholder="请填写仓库" class="ui-input" value="${store.storeName}"/>
                        	</c:if>
                        </c:forEach>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            发货人：<span class="ui-form-required">*</span>
                        </label>
                        <input id="js-user-name" name="userName" maxlength="32"  datatype="*1-32,isNull" nullmsg="请填写发货人" placeholder="长度不超过32个字" type="text" class="ui-input" value="${oldAddress.userName}"/>
                    </div>
                    <div class="ui-form-item pcas">
                        <label class="ui-label" for="">
                            所在地区：<span class="ui-form-required">*</span>
                        </label>

                        <div class="inline-block">
                            <jsp:include page="${ctx}/common/regional/searchAllArea">
                                <jsp:param value="${oldAddress.areaNo}" name="regAreaNo"/>
                                <jsp:param value="ui-select js-select" name="style"/>
                            </jsp:include>
                        </div>
                    </div>
                    <div class="ui-form-item vertical-tip">
                        <label class="ui-label" for="">
                            街道地址：<span class="ui-form-required">*</span>
                        </label>
                        <textarea id="js-address" name="street" maxlength="128" datatype="*1-128,isNull" nullmsg="请填写接到地址" placeholder="请填写街道地址，最少5个字，最多不能超过128个字，不能全部为数字或字母" class="ui-textarea">${oldAddress.street}</textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">电话：</label>
                        <input id="js-tel-zone" style="width: 64px;" placeholder="区号" type="text" maxlength="4" class="ui-input js-price" value="${empty oldAddress.userTel ? "":fn:split(oldAddress.userTel, "-")[0]}">
                        <input id="js-tel-number" style="width: 125px;" placeholder="电话号码" maxlength="8" type="text" class="ui-input js-price" value="${empty oldAddress.userTel ? "":fn:split(oldAddress.userTel, "-")[1]}">
                        <input id="js-tel-ext" style="width: 64px;" placeholder="分机" maxlength="6" type="text" class="ui-input js-price" value="${empty oldAddress.userTel ? "":fn:split(oldAddress.userTel, "-")[2]}">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">手机：</label>
                        <input name="userMobile" id="js-mobile" datatype="m" maxlength="11" ignore="ignore" type="text" class="ui-input js-price" value="${oldAddress.userMobile}"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">邮政编码：<span class="ui-form-required">*</span></label>
                        <input id="js-zipcode" name="zipCode" datatype="*1-32,isNull" maxlength="8" nullMsg="请填写邮政编码" type="text" class="ui-input" value="${oldAddress.zipCode}"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            备注：
                        </label>
                        <textarea id="js-comments" name="addressMark" maxlength="256" placeholder="输入文本"class="ui-textarea">${oldAddress.addressMark}</textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            设为默认：
                        </label><%--
                        <label class="set_default" for="">
                            <input id="js-sender-default" name="sendDefault" type="checkbox" type="" class="js_chk" ${oldAddress.sendDefault ? "checked='true'":""}/>设为默认发货地址
                        </label>
                        --%><label class="set_default" for="">
                            <input id="js-return-default" name="returnDefault" type="checkbox" class="js_chk" ${oldAddress.returnDefault ? "checked='true'":""}/>设为默认退货地址
                        </label>
                    </div>
                    <div class="ui-form-item">
                        <button id="js-sub-btn" class="ui_button ui_button_lblue" type="submit">保&nbsp;存</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${src}/brand/brand_trade.js"></script>
<script type="text/javascript" src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
    $(function () {
        var $storeSelect = $('#js-select-stores'); // 仓库
        var $storeName = $('#js-store-name'); // 仓库名
        var $userName = $('#js-user-name'); // 发货人
        var $telZone = $('#js-tel-zone'); // 电话区号
        var $telNum = $('#js-tel-number'); // 座机号码
        var $telExt = $('#js-tel-ext'); // 分机号码
        var $mobile = $('#js-mobile'); // 手机号码
        var $zipcode = $('#js-zipcode'); // 邮编
        var $province = $('select[name="province"]');
        var $city = $('select[name="city"]');
        var $county = $('select[name="county"]');
        // 获取地址选择信息
        var getAreaNo = function () {
            var county = $.trim($county.val());
            if(county=="")
            {
            	county = $.trim($city.val());
            }
            if (county != '') {
                return county;
            }
            var province = $.trim($province.val());
            if (province == '710000' || province == '810000' || province == '820000') {
                return province;
            }
            return '';
        }

        // 获取电话号码
        var getTelNumber = function () {
            var arr = [];
            arr.push($.trim($telZone.val()));
            if($.trim($telNum.val()) != ""){
                arr.push($.trim($telNum.val()));
            }
            if($.trim($telExt.val()) != ""){
                arr.push($.trim($telExt.val()));
            }
            return arr.join("-");
        }

        var getStoreValue = function () {
            var isnew = $('input[name="newStore"]:checked').val();
            if (isnew == 'false') {
                return $.trim($storeSelect.val());
            } else {
                return $.trim($storeName.val());
            }
        }

        $('#address-form').Validform({
            tiptype: 4,
            postonce: true,
            datatype:{
                isNull:function(gets, obj, curform, regxp){
                    $(obj).val($.trim(gets.replace(/\s/g, "")));
                    return $.trim(gets) != "";
                }
            },
            beforeSubmit: function (curform) {
                if (getAreaNo() == '') {
                    remind('error', '请指定地区');
                    return false;
                }
                if(!/^([0-9]{3,4}-{1}[0-9]{7,8}(-[0-9]{2,6})?)?$/.test(getTelNumber())){
                    remind("error","请输入正确的电话号码");
                    return false;
                }

                if ( getTelNumber() == "" && $.trim($mobile.val()) == '') {
                    remind('error', '请至少输入一个手机号码或电话号码');
                    return false;
                }

                $('<input type="hidden" name="areaNo">').val(getAreaNo()).appendTo(curform);
                $('<input type="hidden" name="userTel">').val(getTelNumber()).appendTo(curform);
                $('<input type="hidden" name="storeValue">').val(getStoreValue()).appendTo(curform);
                return true;
            }
        });
    });
</script>
</body>
</html>