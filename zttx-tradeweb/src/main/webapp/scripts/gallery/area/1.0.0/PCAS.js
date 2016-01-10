define(function(require){

    require("src/common/area.js");

    /**
     *
     * @param config
     * @constructor
     * province
     * city
     * area
     */

    var PCAS = function(config){
        this.province = config.province;
        this.city = config.city;
        this.area = config.area;
        this.currentP = config.currentP;
        this.currentC = config.currentC;
        this.currentA = config.currentA;
        this.init();
    }

    PCAS.prototype = {
        init: function(){

            this.initSel();

            this.selProCity();

            var selectJson = {}

            if(this.currentP){
                selectJson["p"] = this.currentP;
            }

            if(this.currentC){
                selectJson["c"] = this.currentC;
            }

            if(this.currentA){
                selectJson["a"] = this.currentA;
            }

            this.selectedElem(selectJson);

        },
        initSel: function(){

            var _this = this;

            var json = [
                {"elem": _this.province,"msg": "请选择省"},
                {"elem": _this.city,"msg": "请选择市"},
                {"elem": _this.area,"msg": "请选择区"}
            ]

            $.each(json,function(index,obj){

                if(obj.elem){
                    var msg = $(obj.elem).attr("reqmsg") || obj.msg;
                    $(obj.elem).append("<option value=''>"+msg+"</option>");
                    if($.isFunction(window.renderSelect)){
                        renderSelect(obj.elem);
                    }
                }

            })

        },
        selProCity: function(){
            var _this = this;

            $.each(area,function(p){
                var strs = p.split(" ");
                $(_this.province).append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
            });

            if($(this.city).length > 0){
                $(this.province).on("change",function(){
                    _this.selCity($(this).val());
                })
            }
            if($.isFunction(window.renderSelect)){
                renderSelect(_this.province);
            }
        },
        selCity: function(pvalue){

            var _this = this;

            var cityX;

            $(_this.city)[0].length = 1;

            if(_this.area){
                $(_this.area)[0].length = 1;
            }

            if($.isFunction(window.renderSelect)){
                renderSelect(_this.city);
                if(_this.area){
                    renderSelect(_this.area);
                }
            }

            $.each(area,function(p, x){
                var pstrs = p.split(" ");
                if(pvalue == pstrs[1])
                {
                    $.each(x,function(c,cx){
                        var strs = c.split(" ");
                        cityX = x;
                        $(_this.city).append("<option value='" + strs[1] + "'>" + strs[0] + "</option>");
                    });
                    if($(_this.area).length > 0){
                        $(_this.city).on("change",function(){
                            _this.selArea($(this).val(),cityX);
                        })
                    }

                    return;
                }
            });
        },
        selArea: function(cvalue,x,pvalue){

            var cityX = x;

            if(!cityX && !pvalue){
                return;
            }

            if(!cityX && pvalue){
                $.each(area,function(p, x){
                    var pstrs = p.split(" ");
                    if(pvalue == pstrs[1]){
                        $.each(x,function(c,cx){
                            cityX = x;
                        });
                    }
                })
            }

            var _this = this;

            $(_this.area)[0].length = 1;

            if($.isFunction(window.renderSelect)){
                renderSelect(_this.area);
            }

            $.each(cityX,function(c,cx){
                var cstrs = c.split(" ");
                if(cvalue == cstrs[1])
                {
                    $.each(cx.split(","),function(){
                        var strs = this.split(" ");
                        if(strs[1]== undefined || strs[0]==""){
                            return;
                        }
                        $(_this.area).append("<option value='" + strs[1] + "'>" + strs[0] + "</option>");
                    });

                    return;

                }
            });

        },
        selectedElem:function(config){

            if(config.p){
                $(this.province).val(config.p);
            }
            if(config.c){
                this.selCity(config.p);
                $(this.city).val(config.c);
                this.selArea(config.c,null,config.p);
            }

            if(config.a){
                this.selArea(config.c,null,config.p);
                $(this.area).val(config.a);
            }

            if($.isFunction(window.renderSelect)){
                renderSelect([this.province,this.city,this.area]);
            }

        }
    }

    return PCAS;

})