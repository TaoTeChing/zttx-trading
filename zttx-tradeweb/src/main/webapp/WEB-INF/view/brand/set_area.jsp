<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<div style="display: none;">
    <div class="ts-select-city" style="background: #fff;">
    </div>
</div>
<style>
    /*地区样式*/
    .gcity-box{
        width: 80px;
        margin-top: 10px;
    }
    .pcity-box{
        width:770px;
    }
    .ts-select-city{
        width: 873px;
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
    /*弹出框样式*/
    .ts-area{
        outline:none;
    }
    .ts-area .ts-area-close{
        position: absolute;
        top: 7px;
        right: 13px;
    }
</style>
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
                        <div class="province-box">
                            <div class="province">
                                <input class="js-city" value="{{$value.pid}}" type="checkbox" id="city_value_{{$value.pid}}" />
                                <span class="pro">{{$value.p}}</span>
                                <span class="num"></span>
                                <i class="arrow"></i>
                            </div>
                            <div class="city-box">
                                <label><input class="js-city" value="{{$value.pid}}" type="checkbox" name="areaAry">{{$value.p}}</label>
                            </div>
                        </div>
                    {{else}}
                        <div class="province-box">
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
    <div class="ts-sc-bottom">
        <a class="simple_button js-set-area-sure mr10" href="javascript:;">确定</a>
        <a class="simple_button js-set-area-cancel" href="javascript:;">取消</a>
    </div>
</script>
<%--
对下面的文件有依赖，需要用到文件里面的地区数据
<script src="/scripts/common/area.js"></script>
<!--select_city.scss 样式表-->
--%>
<script>

    /*
    * elem：调用地区选择的节点。
    * elemTiggerFn：单击地区选择节点的回调。
    * confirmFn：点击确定的回调，传出的是 1.点击显示 地区选择的 jquery对象;2.选中状态地区的value；3.选中状态地区的 名称。
    */
    var setAreaCom = function(config){
        //默认设置
        var setting = {

        };

        var config = $.extend(setting,config);

        for(var prop in config){
            this[prop] = config[prop];
        }

        var thisEditItem;

        this.init();
    };
    setAreaCom.prototype = {
        init:function(){
            this.showArea();//地区初始化
        },
        showArea:function(){
            //模版渲染，渲染完成再去绑定其他方法
            var _this = this;
            seajs.use(["template"], function (Template) {
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

                //地区数据
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
                //_this.initAreaName(arr); 回显城市名后台做了
                _this.showAreaDialog();
                _this.selectArea();
                _this._selectNum();
            });
        },
        showAreaDialog:function(){
            //绑定区域选择弹窗
            var _this = this;
            seajs.use(["dialog"],function(Dialog){

                var d = new Dialog({
                    "content": $(".ts-select-city"),
                    "width": 875,
                    "classPrefix":"ts-area",
                    "closeTpl":"[ 关闭 ]",
                    "hasMask":false
                });
                //确定
                $(".js-set-area-sure").click(function(){
                    //确定的时候带选中的值
                    _this._selectValue(d);
                    d.hide();
                });
                //取消
                $(".js-set-area-cancel").click(function(){
                    d.hide();
                });
                //调用弹窗的节点
                $(document).on("click",_this.elem,function(){
                    _this.thisEditItem = $(this);
                    if(_this.elemTiggerFn && $.isFunction(_this.elemTiggerFn)){
                        _this.elemTiggerFn($(this));
                    }
                    _this._selectNum();
                    d.show();
                });

            });
        },
        selectArea: function () {
            var _this = this;
            //单击拉开可选内容
            $(".province-box .arrow").click(function(){
                var pBox = $(this).parents(".province-box");
                if(pBox.hasClass("hover")){
                    pBox.removeClass("hover");
                }else{
                    $(".province-box").removeClass("hover");
                    pBox.addClass("hover");
                }
                _this._selectNum();
            });
            //区域全选
            $(".gcity-box input").click(function(){
                var _parents = $(this).parents(".ts-sc-gcity");
                _parents.find("input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _this._selectNum();
            });
            //省份全选 区域反选
            $(".province input[type=checkbox]").click(function () {
                var _parents = $(this).parents(".province-box");
                _parents.find(".city-box input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _this._gcityInverse($(this));
                _this._selectNum();
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
                _this._gcityInverse($(this));
                _this._selectNum();
            });
            //全选
            $(".js-select-all").click(function () {
                $(".ts-sc-content input[type=checkbox]:not(:disabled)").prop("checked", this.checked);
                _this._selectNum();
            });
            //反选
            $(".js-inverse-all").click(function () {
                $(".ts-sc-content input[type=checkbox]:not(:disabled)").each(function () {
                    this.checked = !this.checked;
                });
                _this._selectNum();
            });
        },
        _gcityInverse:function(o){
            //区域反向选择
            var gcityParents = o.parents(".ts-sc-gcity");
            var gcityLen = gcityParents.find(".pcity-box input[type=checkbox]").length;
            var gcityChkLen = gcityParents.find(".pcity-box input[type=checkbox]:checked").length;
            if(gcityLen == gcityChkLen){
                gcityParents.find(".gcity-box input[type=checkbox]").prop("checked",true);
            }else{
                gcityParents.find(".gcity-box input[type=checkbox]").prop("checked",false);
            }
        },
        _selectNum:function(){
            $(".ts-sc-content .province-box").each(function () {
                var _num = $(this).find(".city-box input[type=checkbox]:checked").length;
                if (_num > 0) {
                    $(this).find(".num").html("(" + _num + ")");
                }
                if (_num == 0) {
                    $(this).find(".num").html("");
                }
            });
        },
        _selectValue:function(){
            //获取选中状态 的省份 地区 市 等 的值。
            var html = [],valArr = [];

            $(".ts-select-city .ts-sc-gcity .province-box").each(function(){
                var pcity = $(this).find(".province input[type=checkbox]");

                //总城市长度和已选中的城市长度
                var pcityLen = $(this).find(".city-box input[type=checkbox]").length;
                var pcityChk = $(this).find(".city-box input[type=checkbox]:checked").length;

                if(pcity.prop("checked")){
                    if(pcityLen == pcityChk){
                        html.push($.trim($(this).find(".province .pro").text()));
                        valArr.push($(this).find(".province .js-city").val());
                    }else{
                        $(this).find(".city-box label").each(function(){
                            var ccity = $(this).find("input[type=checkbox]");
                            if(ccity.prop("checked")){
                                html.push($.trim($(this).text()));
                                valArr.push(ccity.val());
                            }
                        });
                    }
                }else{
                    $(this).find(".city-box label").each(function(){
                        var ccity = $(this).find("input[type=checkbox]");
                        if(ccity.prop("checked")){
                            html.push($.trim($(this).text()));
                            valArr.push(ccity.val());
                        }
                    });
                }
            });

            //console.log(valArr);
            /*$(".ts-sc-content input[name=areaAry]:checked").each(function(){
                valArr.push($(this).val());
            });*/

            if(html.length == 0){
                html.push("未添加地区");
                html = html.toString();
            }else{
                html = html.join(",");
            }

            if(this.confirmFn && $.isFunction(this.confirmFn)){
                this.confirmFn(this.thisEditItem,valArr,html);
            }
        },
        _againRender:function(arr){
            //根据获取到的值，重写地区的选中状态
            //arr = ["520200", "520400", "522400", "522200", "522600"];
            //此方法可以利用回调去操作，暂不开放
        },
        initAreaName:function(cityArr){
            //用于回显区域名称
            //测试数据：
            //1.兴安盟，包头市
            //2.大连市，鸡西市
            //2.丽水市,池州市,淮南市

            /*var itemArr = [
                    [152200,150200],
                    [210200,230300],
                    [331100,341700,340400]
            ];*/

            //console.log(cityArr);

            $(this.elem).each(function(j,o){
                var thisArr = $(this).data("value");
                if(thisArr && $.type(thisArr) !== "array"){
                    thisArr = "["+ thisArr + "]";
                    thisArr = $.parseJSON(thisArr);
                }
                var areaName = [];

                for (var i = 0, ilen = cityArr.length; i < ilen; i++) {
                    var sum = [];
                    for (var n = 0, nlen = cityArr[i].ct.length; n < nlen; n++) {

                        for(var m  = 0,mlen = thisArr.length;m<mlen;m++){
                            if(thisArr[m] == cityArr[i].ct[n].cid){

                                //正常情况直接push
                                sum.push(cityArr[i].ct[n].c);

                                //当省份下的城市都被选的时候，输出省份名称
                                if(sum.length == nlen){
                                    sum = [];
                                    sum.push(cityArr[i].p);
                                }

                            }
                        }

                        //遍历到最后一个元素去做合并
                        if(n == nlen - 1 && sum.length != 0){
                            areaName.push(sum.join(","));
                        }

                    }
                }

                //香港澳门台湾 做特殊处理
                for(var m  = 0,mlen = thisArr.length;m<mlen;m++){
                    if(thisArr[m] == 820000){
                        areaName.push("澳门特别行政区");
                    }
                    if(thisArr[m] == 810000){
                        areaName.push("香港特别行政区");
                    }
                    if(thisArr[m] == 710000){
                        areaName.push("台湾省");
                    }
                }

                $(this).next().html(areaName.join(","));
            });
        }
    };

</script>
