<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-品牌资料完善</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style type="text/css">
        .uploadimages ul li{
            overflow: hidden;
        }
        .area-ui-select{
            width:187px;height:25px;
            vertical-align: middle;
        }
        /*地区样式*/
        .gcity-box{
            width: 80px;
            margin-top: 10px;
        }
        .pcity-box{
            width:690px;
        }
        .ts-select-city{
            width: 795px;
        }
        .ts-select-city .ts-sc-content{
            padding: 5px 10px 10px 10px;
        }
        .ts-sc-gcity{
            position: relative;
        }
        .ts-sc-bottom{
            padding: 10px 0;
            text-align: center;
            border-bottom: 1px solid #DDD;
        }
        .ts-select-city .arrow{
            display: inline-block;
            /*position: relative;*/
            /*right: 0px;*/
            top: 5px;
            width: 0;
            height: 1px;
            margin-left: 3px;
            border-style: solid;
            border-color: #666 #fff #fff #fff;
            _border-color: #000000 tomato tomato tomato;
            _filter: chroma(color=tomato);
            border-width: 5px 4px;
            overflow: hidden;
            z-index: 9;
            cursor: pointer;
            vertical-align: -5px;
            *vertical-align: middle;
        }
        .ts-select-city input{
            *vertical-align: 1px;
        }
        .ts-select-city .province-box{
            width: 160px;
        }
        /*弹出框样式*/
        .ts-area{
            outline:none;
        }
        .ts-area .ts-area-close{
            position: absolute;
            top: 7px;
            right: 13px;
        }
        .ts-select-city .city-box {
            width: 280px;
        }
    </style>
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
                <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">品牌资料完善</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <form:form id="mainForm" name="mainForm" cssClass="ui-form" data-widget="validator" action="${ctx}/brand/brandesInfoRegional/save" method="post">
                <input type="hidden" name="brandesId" value="${brandesInfo.refrenceId}"/>
                <!-- 品牌基本资料 start -->
                <jsp:include page="/WEB-INF/view/brand/brands_perfect_menu.jsp">
                    <jsp:param value="5" name="m"/>
                </jsp:include>
                <!-- 品牌基本资料 end -->
                <div class="tips">
                    <i class="v2-icon-explain"></i>
                    说明：以下选中区域用户可购买当前品牌产品
                </div>
                <div class="tab_con">
                    <div class="ui-form-item js_area_box">
                        <label class="ui-label">
                            选择地区：
                            <span class="ui-form-required">*</span>
                        </label>
                        <div class="mt5 inline-block">
                            <div id="js-showArea" class="inline-block" style="width: 770px;">
                                <div class="ts-select-city">
                                    <%--<div class="title">
                                        请选择区域：全选 <input type="checkbox" class="js-select-all"/> 反选 <input type="checkbox" class="js-inverse-all"/>
                                    </div>
                                    <div class="ts-sc-content clearfix" style="height: 335px;">

                                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <button type="submit" class="ui_button ui_button_morange" id="btn-submit">保存</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/common/area.js"></script>
<script type="text/html" id="showAreaList">
    <div class="title">
        请选择区域：全选 <input type="checkbox" class="js-select-all"> 反选 <input type="checkbox" class="js-inverse-all">
    </div>
    <div class="ts-sc-content clearfix">
        {{each}}
        <div class="ts-sc-gcity clearfix" style="z-index: {{30 - $index}};">
            <div class="gcity-box fl">
                <label>
                    <input type="checkbox" value="{{$value.zid}}"/>
                    <strong>{{$value.zone}}</strong>
                </label>
            </div>
            <div class="pcity-box fl">
                {{each $value.st}}
                {{if $value.pid == 710000 || $value.pid == 810000 || $value.pid == 820000}}
                <%-- 港澳台需特殊处理 --%>
                <div class="province-box" style="z-index: {{200 - $index}};">
                    <div class="province">
                        <input class="js-city" value="{{$value.pid}}" type="checkbox" />
                        <span class="pro">{{$value.p}}</span>
                        <span class="num"></span>
                        <i class="arrow"></i>
                    </div>
                    <div class="city-box">
                        <label><input class="js-city" value="{{$value.pid}}" type="checkbox" name="areaAry" id="city_value_{{$value.pid}}">{{$value.p}}</label>
                    </div>
                </div>
                {{else}}
                <div class="province-box" style="z-index: {{200 - $index}};">
                    <div class="province">
                        <input class="js-city" value="{{$value.pid}}" type="checkbox" id="city_value_{{$value.pid}}" />
                                <span class="pro">
                                <%--处理一些名称比较长的省份--%>
                                {{if $value.pid == 450000}}
                                    广西
                                {{else if $value.pid == 650000}}
                                    新疆
                                {{else}}
                                    {{$value.p}}
                                {{/if}}
                                </span>
                        <span class="num"></span>
                        <i class="arrow"></i>
                    </div>
                    <div class="city-box">
                        {{each $value.ct}}
                        <label><input class="js-city" value="{{$value.cid}}" type="checkbox" name="areaAry" id="city_value_{{$value.cid}}"/>{{$value.c}}</label>
                        {{/each}}
                    </div>
                </div>
                {{/if}}
                {{/each}}
            </div>
        </div>
        {{/each}}
    </div>
</script>
<script>
    $(function(){
        $("#brandsId").on("change",function(){
            var brandsId=$(this).val();
            if(brandsId){
                window.location.href="${ctx}/brand/brandesInfoRegional/view?id="+brandsId;
            }
        });

        seajs.use(['validator', 'widget', 'template'], function(Validator, Widget, Template){
            Widget.autoRenderAll();
            var Core = Validator.query("#mainForm");
            Core.set({
                failSilently: true,
                autoSubmit: false
            });
            Core.on('formValidated', function (error) {
                if (error) {
                    return;
                }
                $("#btn-submit").attr("disabled", true);
                $.ajax({
                    url: '${ctx}/brand/brandesInfoRegional/save',
                    type: 'post',
                    data: $("#mainForm").serialize(),
                    dataType: 'json',
                    success: function(data, textStatus)
                    {
                        if(zttx.SUCCESS==data.code){
                            remind("success","保存成功");
                        }
                        else{
                            remind("error",data.message);
                        }
                    },
                    error: function(data)
                    {
                        remind("error",data.message);
                    }
                });
                $("#btn-submit").attr("disabled", false);
            });

            //区域选择初始化
            function showArea(){
                var arr = [];
                var zobj = {};
                for (var p in area) {
                    var obj = {};
                    var arrcity = [];
                    var pArr = p.split(" ");

                    obj.p = pArr[0];
                    obj.pid = pArr[1];

                    for (var c in area[p]) {
                        var objcity = {};
                        var cArr = c.split(" ");

                        objcity.c = cArr[0];
                        objcity.cid = cArr[1];

                        arrcity.push(objcity);
                    }

                    obj.ct = arrcity;
                    arr.push(obj);
                }

                //地区数据重新组装
                var zoneArr =[
                    {"zone":"华东",zid:"310000,320000,330000,340000,360000",st:[]},
                    {"zone":"华北",zid:"110000,120000,140000,370000,130000,150000",st:[]},
                    {"zone":"华中",zid:"430000,420000,410000",st:[]},
                    {"zone":"华南",zid:"440000,450000,350000,460000",st:[]},
                    {"zone":"东北",zid:"210000,220000,230000",st:[]},
                    {"zone":"西北",zid:"610000,650000,620000,640000,630000",st:[]},
                    {"zone":"西南",zid:"500000,530000,520000,540000,510000",st:[]},
                    {"zone":"港澳台",zid:"810000,820000,710000",st:[]}
                ];

                for(var n = 0,nlen = zoneArr.length;n<nlen;n++){
                    var zoneCityArr =  zoneArr[n].zid.split(",");
                    for(var b in zoneCityArr){
                        for(var j = 0,alen = arr.length;j<alen;j++){
                            if(arr[j].pid == zoneCityArr[b]){
                                zoneArr[n].st.push(arr[j]);
                            }
                        }
                    }
                }
                var html = Template.render("showAreaList", zoneArr);
                $(".ts-select-city").html(html);

                //编辑的情况下做回显用
                <c:forEach var="obj" items="${regionalList}" varStatus="status">
                $("#city_value_${obj.areaNo}").attr("checked", true);
                </c:forEach>
            }
            showArea();
            //此方法初始化的时候才用到，用以勾选区域，和省份的复选框
            var _initNum = function(){
                //区域
                $(".ts-sc-gcity").each(function(){
                    var len = 0;
                    var provinceLen =  $(this).find(".province-box").length;
                    //省份或直辖市
                    $(this).find(".province-box").each(function(){
                        var cityLen = $(this).find(".city-box .js-city").length;
                        var chkCityLen = $(this).find(".city-box .js-city:checked").length;
                        if(cityLen === chkCityLen){
                            $(this).find(".province .js-city").prop("checked", true);
                            len ++;
                        }
                    });
                    if(len === provinceLen){
                        $(this).find(".gcity-box input").prop("checked", true);
                    }
                });
            };
            _initNum();

            var _selectNum = function(){
                $(".ts-sc-content .province-box").each(function () {
                    var _num = $(this).find(".city-box input[type=checkbox]:checked").length;
                    if (_num > 0) {
                        $(this).find(".num").html("(" + _num + ")");
                    }
                    if (_num == 0) {
                        $(this).find(".num").html("");
                    }
                });
            };
            //初始化数量需要显示一下
            _selectNum();

            var _gcityInverse = function(o){
                //区域反向选择
                var gcityParents = o.parents(".ts-sc-gcity");
                var gcityLen = gcityParents.find(".pcity-box input[type=checkbox]").length;
                var gcityChkLen = gcityParents.find(".pcity-box input[type=checkbox]:checked").length;
                if(gcityLen == gcityChkLen){
                    gcityParents.find(".gcity-box input[type=checkbox]").prop("checked",true);
                }else{
                    gcityParents.find(".gcity-box input[type=checkbox]").prop("checked",false);
                }
            };

            //移上去拉开可选内容
            var timer = null;
            var province_box =  $(".province-box").hover(function(){
                var $this = $(this);
                timer = setTimeout(function(){
                    //var pBox = $this.parents(".province-box");
                    province_box.removeClass("hover");
                    $this.addClass("hover");
                    _selectNum();
                }, 200);
            },function(){
                //var $this = $(this);
                //var pBox = $(this).parents(".province-box");
                $(this).removeClass("hover");
                clearTimeout(timer);
            });

            //区域全选
            $(".gcity-box input").click(function(){
                var _parents = $(this).parents(".ts-sc-gcity");
                _parents.find("input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _selectNum();
            });

            //省份全选 区域反选
            $(".province input[type=checkbox]").click(function () {
                var _parents = $(this).parents(".province-box");
                _parents.find(".city-box input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _gcityInverse($(this));
                _selectNum();
            });
            //地区单选 省份反选 区域反选

            $(".province-box .city-box input").click(function(){
                var provinceParents = $(this).parents(".province-box");
                var provinceLen = provinceParents.find(".city-box input[type=checkbox]").length;
                var provinceChkLen = provinceParents.find(".city-box input[type=checkbox]:checked").length;

                if(provinceLen == provinceChkLen){
                    provinceParents.find(".province input[type=checkbox]").prop("checked",true);
                }else{
                    provinceParents.find(".province input[type=checkbox]").prop("checked",false);
                }
                _gcityInverse($(this));
                _selectNum();
            });

            //全选
            $(".js-select-all").click(function () {
                $(".ts-sc-content input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _selectNum();
            });

            //反选
            $(".js-inverse-all").click(function () {
                $(".ts-sc-content input[type=checkbox]:not(:disabled)").each(function () {
                    this.checked = !this.checked;
                });
                _selectNum();
            });

        });
    });
</script>
</body>
</html>