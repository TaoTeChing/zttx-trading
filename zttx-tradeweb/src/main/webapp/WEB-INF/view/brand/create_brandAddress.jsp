<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-交易管理-退货地址</title>
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
                <span class="current">退货地址</span>
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
                    <span>添加新地址</span>
                </h3>
                <div class="tips">
                    <i class="v2-icon-explain"></i>说明：您可根据品牌设置品牌的退货地址，终端商退货时，会将货品退至您设置的地址
                </div>
                <form:form id="address-form" class="ui-form addAddress-form" action="${ctx}/brand/brandAddress/save">
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            品牌名：<span class="ui-form-required">*</span>
                        </label>

                        <div class="inline-block">
                            <select id="js-select-brands" name="brandsId" class="js_select ui-select" datatype="*" nullmsg="请选择品牌！">
                                <option value="">请选择品牌...</option>
                                <c:forEach items="${brandes}" var="brand">
                                    <option value="${brand.refrenceId}">${brand.brandsName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">仓库：<span class="ui-form-required">*</span></label>
                        <%--<div id="chooseUse" class="inline-block mr10" style="display: none;">
                            <label for="">
                                <input class="ui-radio" name="newStore" value="false" type="radio"/>使用现有仓库
                            </label>
                            <div class="inline-block">
                                <select id="js-select-stores" style="width: 110px;" name="storeId" class="js_select ui-select" disabled="disabled">
                                    <option value="">请选择仓库...</option>
                                </select>
                                <c:forEach items="${storeList}" var="brandStore">
                                    <script type="text/html" id="${brandStore.brandsId}-stores">
                                            <c:forEach items="${brandStore.stores}" var="store">
                                                <option value="${store.refrenceId}">${store.storeName}</option>
                                            </c:forEach>
                                    </script>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="inline-block">
                            <label for="">
                                <input class="ui-radio" name="newStore" value="true" type="radio" checked="true"/>使用新仓库
                            </label>
                            <input id="js-store-name" name="newStoreName" type="text" maxlength="64" placeholder="输入文本" class="ui-input"/>
                        </div>
                    --%>
                    	<input type="hidden" name="newStore" value="true" />
                    	<input id="js-store-name" name="newStoreName" type="text" datatype="*1-32,isNull" nullmsg="请填写仓库" maxlength="64" placeholder="请填写仓库" class="ui-input" />
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                           收货人：<span class="ui-form-required">*</span>
                        </label>
                        <input id="js-user-name" name="userName" datatype="*1-32,isNull" nullmsg="请填写收货人" maxlength="32" placeholder="长度不超过32个字" type="text" class="ui-input"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            所在地区：<span class="ui-form-required">*</span>
                        </label>

                        <div class="inline-block pcas">
                            <jsp:include page="${ctx}/common/regional/searchAllArea">
                                <jsp:param value="" name="province"/>
                                <jsp:param value="" name="city"/>
                                <jsp:param value="" name="county"/>
                                <jsp:param value="ui-select js-select" name="style"/>
                            </jsp:include>
                        </div>
                    </div>
                    <div class="ui-form-item vertical-tip">
                        <label class="ui-label" for="">
                            街道地址：<span class="ui-form-required">*</span>
                        </label>
                        <textarea id="js-address" name="street" maxlength="128" datatype="*1-128,isNull" nullmsg="请填写街道地址" placeholder="请填写街道地址，最多不能超过128个字" class="ui-textarea" errormsg="不能有空格!"></textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">电话：</label>
                        <input id="js-tel-zone" style="width: 64px;" placeholder="区号" type="text" maxlength="4" class="ui-input js-price">
                        <input id="js-tel-number" style="width: 125px;" placeholder="电话号码" type="text" maxlength="8" class="ui-input js-price">
                        <input id="js-tel-ext" style="width: 64px;" placeholder="分机" maxlength="6" type="text" class="ui-input js-price">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">手机：</label>
                        <input id="js-mobile" name="userMobile" datatype="m" maxlength="11" ignore="ignore" errorMsg="手机号码格式不正确" type="text" class="ui-input  js-price"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">邮政编码：<span class="ui-form-required">*</span></label>
                        <input id="js-zipcode" name="zipCode" datatype="*1-32,isNull" maxlength="8" nullMsg="请填写邮政编码" type="text" class="ui-input"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">备注：</label>
                        <textarea id="js-comments" name="addressMark" maxlength="256" class="ui-textarea"></textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">设为默认：</label>
                        <%--<label class="set_default" for="">
                            <input id="js-sender-default" type="checkbox" name="sendDefault" value="true" type="" class="js_chk"/>设为默认发货地址
                        </label>
                        --%><label class="set_default" for="">
                            <input id="js-return-default" type="checkbox" name="returnDefault" value="true" class="js_chk"/>设为默认退货地址
                        </label>
                    </div>
                    <div class="ui-form-item">
                        <button id="js-sub-btn" class="ui_button ui_button_lblue" type="submit">
                            保&nbsp;存
                        </button>
                    </div>
                </form:form>
                <form:form id="operate-form" cssStyle="display: none;"></form:form>
                <div id="address-list" class="address_list">
                    <table>
                        <colgroup>
                            <col width="116"/>
                            <col width="85"/>
                            <col width="80"/>
                            <col width="156"/>
                            <col width="150"/>
                            <col width="124"/>
                            <col width="80"/>
                            <col width="116"/>
                            <col width="120"/>
                        </colgroup>
                        <tr>
                            <th>品牌</th>
                            <th>仓库</th>
                            <th>联系人</th>
                            <th>所在地区</th>
                            <th>街道地址</th>
                            <th>电话/手机</th>
                            <th>邮编</th>
                            <th></th>
                            <th>操作</th>
                        </tr>
                        <c:choose>
                        	<c:when test="${!empty addressPage.list}">
                        	<c:forEach items="${addressPage.list}" var="address">
                            <tr ${address.returnDefault ? "class='default_tr'" : ""}>
                                <td>${address.brandsName}</td>
                                <td>${address.storeName}</td>
                                <td>${address.userName}</td>
                                <td>${address.areaName}</td>
                                <td>${address.street}</td>
                                <td>${address.userMobile}<c:if test="${not empty address.userMobile}"><br/></c:if>${address.userTel != "--" ? address.userTel:""}</td>
                                <td>${address.zipCode }</td>

                                <td>
                                    <c:choose>
                                        <c:when test="${address.returnDefault}">
                                            <span>默认退货地址</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:;" class="set_default" data-id="${address.id}">设为默认</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${ctx}/brand/brandAddress/execute?id=${address.id}" class="edit_icon">修改</a>
                                    <a href="javascript:;" data-id="${address.id}" class="del_icon iconfont">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        	</c:when>
                        	<c:otherwise>
                        	<tr><td colspan="8" style="text-align:center;">暂无数据</td></tr>
                        	</c:otherwise>
                        </c:choose>
                    </table>
                    <div id="pagination" class="mt15"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/javascript" src="${src}/brand/brand_trade.js"></script>
<script type="text/javascript" src="${src}/plugin/Validform_v5.3.2_min.js"></script>
<script id="store-template" type="text/html">
    <option value="" selected="selected">请选择仓库...</option>
    {{each rows}}
    <option value="{{$value.refrenceId}}">{{$value.storeName}}</option>
    {{/each}}
</script>
<script id="address-page" type="text/html">
    <table>
        <colgroup>
            <col width="116"/>
            <col width="85"/>
            <col width="80"/>
            <col width="156"/>
            <col width="150"/>
            <col width="120"/>
            <col width="80"/>
            <col width="120"/>
            <col width="120"/>
        </colgroup>
        <tr>
            <th>品牌</th>
            <th>仓库</th>
            <th>联系人</th>
            <th>所在地区</th>
            <th>街道地址/邮政编码</th>
            <th>电话/手机</th>
            <th>邮编</th>
            <th></th>
            <th>操作</th>
        </tr>
        {{each rows}}
        {{if $value.returnDefault}}
        <tr class="default_tr">
            {{else}}
        <tr>
            {{/if}}
            <td>{{$value.brandsName}}</td>
            <td>{{$value.storeName}}</td>
            <td>{{$value.userName}}</td>
            <td>{{$value.areaName}}</td>
            <td>{{$value.street}}</td>
            <td>
                {{$value.userMobile}}
                {{if $value.userMobile && $value.userMobile != ''}}
                    <br/>
                {{/if}}
                {{if $value.userTel && $value.userTel != '--'}}
                    {{$value.userTel}}
                {{/if}}
            </td>
            <td>{{$value.zipCode}}</td>
            <td>
                {{if $value.returnDefault}}
                <span>默认退货地址</span>
                {{else}}
                <a href="javascript:void(0)" class="set_default" data-id="{{$value.id}}">设为默认</a>
                {{/if}}
            </td>
            <td>
                <a href="${ctx}/brand/brandAddress/execute?id={{$value.id}}" class="edit_icon">修改</a>
                <a href="javascript:;" data-id="{{$value.id}}" class="del_icon iconfont">删除</a>
            </td>
        </tr>
        {{/each}}
	{{if rows.length == 0 }}
		<tr>
			<td colspan="8" style="text-align:center;">暂无数据</td>
		</tr>
	{{ /if }}
    </table>
</script>
<script type="text/javascript">
    $(function () {
        seajs.use(['pagination', 'template'], function (Pagination, template) {
            var handleTable = function () {
                $(".address_list").on("mouseenter mouseleave", "tr:not('.default_tr')", function (ev) {
                    if (ev.type == "mouseenter") {
                        $(this).addClass("hover_tr");
                        $(this).find(".set_default").show();
                    } else if (ev.type == "mouseleave") {
                        $(this).removeClass("hover_tr");
                        $(this).find(".set_default").hide();
                    }
                })
            }
            handleTable();
            var $list = $('#address-list');
            // 分页页码组件
            var pagination = new Pagination({
                url: "${ctx}/brand/brandAddress/list.json", //URL地址
                elem: "#pagination", //插入的容器元素
                handleData: function (obj) {
                    if (obj.code === 126000) {
                        // 渲染分页模板
                        var html = template.render('address-page', obj);
                        $list.empty().append(html);
                        handleTable();
                    }
                },
                total: ${addressPage.page.totalPage}   //表示共有6页，如果一开始不传这个值，会发起ajax请求。
            });

            var $brandsSelect = $('#js-select-brands'); // 品牌
            var $storeSelect = $('#js-select-stores'); // 仓库
            var $storeName = $('#js-store-name'); // 仓库名
            var $userName = $('#js-user-name'); // 发货人
            var $telZone = $('#js-tel-zone'); // 电话区号
            var $telNum = $('#js-tel-number'); // 座机号码
            var $telExt = $('#js-tel-ext'); // 分机号码
            var $mobile = $('#js-mobile'); // 手机号码
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

            window["getTelNumber"] = getTelNumber;

            var $radios = $('input[name="newStore"]:radio');
            // 品牌商选择二级联动绑定
            $brandsSelect.change(function (eventObject) {
                $storeSelect.empty();
                var val = $brandsSelect.val();

                if($.trim($('#' + val + '-stores').html()) == ""){
                    $("#chooseUse").css({
                        "display": "none"
                    })
                     return;
                }

                $("#chooseUse").css({
                    "display": "inline-block"
                })

                var options = '<option value="">请选择仓库...</option>';
                if (val == '') {
                    $storeSelect.empty().append(options).prop('disabled', true);
                } else {
                    $storeSelect.empty().append(options + $('#' + val + '-stores').html()).prop('disabled', false);
                }
                renderSelect('#js-select-stores');

                if ($storeSelect.children().length > 1) {
                    $radios.filter('[value="false"]').prop('checked', true);
                } else {
                    $radios.filter('[value="true"]').prop('checked', true);
                }
            });

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

            // 设为默认事件绑定
            $(".address_list").on('click', 'a.set_default', function (ev) {
                ev.stopPropagation();
                var $this = $(this);
                var data = $('#operate-form').clone().append($('<input type="hidden" name="addressId">').val($this.attr("data-id"))).serialize();
                $.post('${ctx}/brand/brandAddress/setDefault', data, function (data, status, xhr) {
                    if (data.code === 126000) {
                        remind("success", "设置成功！");
                        pagination.render();
                    }
                }, 'json');
            });

            // 删除事件绑定
            $(".address_list").on('click', "a.del_icon", function (event) {
                event.stopPropagation();
                var $this = $(this);
                confirmDialog('确认删除?', function () {
                    var data = $('#operate-form').clone().append($('<input type="hidden" name="addressId">').val($this.attr("data-id"))).serialize();
                    $.post('${ctx}/brand/brandAddress/delete', data, function (data, status, jqXHR) {
                        if (data.code === 126000) {
                            remind("success", "删除成功！");
                            pagination.render();
                        }
                    }, 'json');
                });
            });
        });
        var brandsIdVal = "${brandsId}";
        if(typeof brandsIdVal != "undefinded"){
	        $("#js-select-brands option[value="+brandsIdVal+"]").attr("selected","selected");
	        $("#brandesId_div span").html($("#js-select-brands option[value="+brandsIdVal+"]").text());
        }
    });
</script>
</body>
</html>