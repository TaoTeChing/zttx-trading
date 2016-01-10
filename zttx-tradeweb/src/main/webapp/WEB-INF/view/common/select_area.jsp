<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<style>
    #select_area{
        padding: 3px 5px;
        border: 1px solid #ddd;
        background: #fff;
        cursor: pointer;
    }
    /*城市选择样式*/
    .ts-select-area{
        width: 350px;
        margin:20px auto 0 auto;
        padding: 10px 15px 0px 15px;
        border: 1px solid #d5d5d5;
        background: #fff;
        position: relative;
    }
    .ts-select-area li{
        margin-right: 20px;
        margin-bottom: 10px;
        position: relative;
        white-space: nowrap;
        z-index: 1;
    }
    .ts-select-area .ts-province-box a{
        color: #333;
    }
    .ts-select-area .ts-city-box{
        width: 345px;
        padding: 5px 10px 0px 10px;
        display: none;
        border: 1px solid #f4e1ce;
        background: #fff8eb;
        position: absolute;
        top:26px;
        left: -8px;
        z-index: 2;
        white-space: nowrap;
    }
    .ts-select-area .ts-city-box a{
        float: left;
        /*line-height: 23px;*/
        padding: 0 5px;
        margin-right: 5px;
        margin-bottom: 5px;
        white-space: nowrap;
    }
    .ts-select-area .ts-city-box a:hover{
        background: #f70;
        color: #fff;
    }
    .ts-select-area .selected .ts-province-box a{
        /*font-weight: bold;*/
        color: #f70;
    }
    .ts-select-area .selected .ts-city-box{
        display: block;
    }
    /*画箭头*/
    .ts-select-area .em{
        position: absolute;
        left: 90px;
        top: -10px;
        width: 0;
        height: 0;
        border-style: solid;
        border-color: transparent transparent #BABABA transparent;
        border-width: 5px 5px;
        overflow: hidden;
    }
    .ts-select-area .span{
        position: absolute;
        left: 91px;
        top: -8px;
        width: 0;
        height: 0;
        border-style: solid;
        border-color: transparent transparent #FFF transparent;
        border-width: 4px 4px;
        overflow: hidden;
    }
    .ts-select-area .ts-city-box em{
        position: absolute;
        left: 20px;
        top: -11px;
        width: 0;
        height: 0;
        border-style: solid;
        border-color: transparent transparent #f4e1ce transparent;
        _border-color:#000000 tomato tomato tomato;
        _filter:chroma(color=tomato);
        border-width: 5px 5px;
        overflow: hidden;
    }
    .ts-select-area .ts-city-box span{
        position: absolute;
        left: 21px;
        top: -8px;
        width: 0;
        height: 0;
        border-style: solid;
        border-color: transparent transparent #fff8eb transparent;
        border-width: 4px 4px;
        overflow: hidden;
    }
    /*弹窗*/
    .ts-area{
        outline:none;
    }
    .ts-area .ts-area-close{
        position: absolute;
        top: 7px;
        right: 13px;
    }
</style>
<div style="display:none;">
    <div class="ts-select-area" id="ts-select-area">
        <em class="em"></em>
        <span class="span"></span>
        <ul class="inline-float">
        </ul>
    </div>
</div>
<script type="text/html" id="showCityTpl">
    {{each}}
    <li style="z-index:{{200-$index}};">
        <div class="ts-province-box">
            <a href="javascript:;" data-value="{{$value.pid}}">{{$value.p}}</a>
        </div>
        <div class="ts-city-box clearfix">
            <em></em>
            <span></span>
            {{if $value.ct.length ==0}}
            <a href="javascript:;" data-value="{{$value.pid}}">{{$value.p}}</a>
            {{else}}
            {{each $value.ct}}
            <a href="javascript:;" data-value="{{$value.cid}}">{{$value.c}}</a>
            {{/each}}
            {{/if}}
        </div>
    </li>
    {{/each}}
</script>
<script src="${src}/common/area.js"></script>
<script type="text/javascript">
    /*
     ** elem：调用弹窗的节点
     ** ensureFn：选中回调
     */
    var selectCity = function(config){
        //默认设置
        var setting = {
            align:[100, -10]
        };

        var config = $.extend(setting,config);

        for(var prop in config){
            this[prop] = config[prop];
        }

        var thisDialog;

        this.init();
    };
    selectCity.prototype = {
        init:function(){
            this.renderCity();
            this.dialogArea();
            this._clickCity();
            if(this.renderName && $.isFunction(this.renderName)){
                var _name = this.cityNameInit();
                this.renderName(_name);
            }
        },
        renderCity:function(){
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

                var html = Template.render("showCityTpl", arr);
                $(".ts-select-area ul").html(html);
                _this._slideArea();
            });
        },
        dialogArea:function(){
            var _this = this;
            seajs.use(["dialog"],function(Dialog){
                thisDialog = new Dialog({
                    trigger: _this.elem,
                    content: $("#ts-select-area"),
                    classPrefix:"ts-area",
                    hasMask: false,
                    width:430,
                    effect:"fade",
                    align:{
                        selfXY: _this.align,
                        baseElement:_this.elem,
                        baseXY: [0, 0]
                    }
                });
            });
        },
        _slideArea:function(){
            var leaveTimer = null;
            $("#ts-select-area").find("li").hover(function(){
                clearTimeout(leaveTimer);
                $("#ts-select-area li").removeClass("selected");
                $(this).addClass("selected");
            },function(){
                leaveTimer = setTimeout(function(){
                    $("#ts-select-area li").removeClass("selected");
                },382);
            });
        },
        _clickCity:function(){
            var _this = this;
            $("#ts-select-area").on("click",".ts-city-box a",function(){
                var _value = $(this).data('value');
                var _text = _this.cityNameInit(_value);
                if(_this.ensureFn && $.isFunction(_this.ensureFn)){
                    _this.ensureFn(_value,_text);
                }
                thisDialog.hide();
            });
        },
        cityNameInit:function(value){
            value = value || $(this.elem).data("value");
            if(value && $.type(value) != "string"){
                value = value.toString();
            }
            if(value){
                var provinceVal = value.substring(0, 2) + "0000";
                var cityVal = value.substring(0, 4) + "00";
                var countyVal = value;
                var address = [];

                for (var p in area) {
                    var pArr = p.split(" ");

                    if(provinceVal== pArr[1]){
                        address.push(pArr[0]);
                    }

                    for (var c in area[p]) {
                        var cArr = c.split(" ");

                        if(cityVal == cArr[1]){
                            address.push(cArr[0]);
                        }
                    }
                }
            }

            if(address && address.length != 0){
                return address.join(" ");
            }else{
                return "请选择地区";
            }
        }
    };
</script>