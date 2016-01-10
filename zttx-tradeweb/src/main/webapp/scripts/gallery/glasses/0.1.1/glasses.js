/*
前台放大镜
author: 冯唐路
参数配置说明：
elem: 需要调用放大镜的图片
bigBox：大图的容器
mark：移动框
 */
define(function(require, exports, module){
    function glasses(config){
        var defaults = {
            elem:".js-glasses",
            bigBox:"#ts-glasses-pic",
            mark:".mask",
            datas:"glasses",
            zIndex:300,
            outsideTrigger:true
        };

        var config = $.extend(defaults,config);

        this.elem = config.elem;
        this.big = config.bigBox;
        this.mark = config.mark;
        this.bigImg = this.big+" img";
        this.datas = config.datas;
        this.zIndex = config.zIndex;
        this.outsideTrigger = config.outsideTrigger;
        this.changeBefore = config.changeBefore;

        this.tsMaskCls = config.tsMaskCls || "ts-glasses-mask";
        this.tsMaskTpl = "<div class="+this.tsMaskCls+" id="+this.tsMaskCls+"><div class='maskbg'></div><div class='mask'></div></div>";

        this.markStyle = {
            position:"absolute",
            background: "#fff",
            opacity: 0.5,
            border:"1px solid #333",
            cursor: "move",
            zIndex: 2,
            display: "none"
        };

        this.tsPicCls = config.tsPicCls || "ts-glasses-pic";
        this.tsPicTpl = "<div class="+this.tsPicCls+" id="+this.tsPicCls+"><img style='position: absolute;' src=''/></div>";

        this.init();
    }
    glasses.prototype = {
        constructor:glasses,
        init:function(){
            this.imgHover();
        },
        imgHover:function(){
            var _this = this;
            var timer = null;
            //移到元素上，创建 mask 和 放大镜显示框
            $(document).on("mouseenter mouseleave",this.elem,function(ev){
                var _self = $(this);
                if(_this.changeBefore && $.isFunction(_this.changeBefore)){
                    _this.changeBefore(_this);
                }
                if(ev.type === "mouseenter"){
                    clearTimeout(timer);
                    timer = setTimeout(function(){

                        var _width = _self.width();
                        var _height = _self.height();
                        var ol = _self.offset().left;
                        var ot = _self.offset().top;
                        var _src = _self.data(_this.datas) || _self.attr("src");

                        if(_width == 0 || _height == 0){
                            return;
                        }

                        //把原图地址放在data-XXX 的情况下
                        if(_self.data(_this.datas) && _this.outsideTrigger){
                            //当前图片大小和原图比较
                            var newImage = new Image();
                            newImage.src = _src;
                            //console.dir(newImage);
                            var newImageWidth = 0;
                            var newImageHeight = 0;
                            newImage.onload = function(){
                                newImageWidth = newImage.width;
                                newImageHeight = newImage.height;

                                //真实图片宽度并且高度小于触发图片
                                if(newImageWidth < _width && newImageHeight < _height){
                                    console.error("The real picture width and height is smaller than the trigger pictures.");
                                    return;
                                }

                                if($("#"+_this.tsMaskCls)){
                                    $("#"+_this.tsMaskCls).remove();
                                    $("body").append(_this.tsMaskTpl);
                                }

                                if($("#"+_this.tsPicCls)){
                                    $("#"+_this.tsPicCls).remove();
                                    $("body").append(_this.tsPicTpl);
                                }

                                $(_this.bigImg).attr({
                                    "src":_src
                                });

                                _this.foundPic(_width,_height,ol,ot);
                                _this.foundMask(_width,_height,ol,ot);
                                $("#"+_this.tsMaskCls).trigger("mousemove");

                            };
                        }

                        //src 即大图的情况
                        if(!_self.data(_this.datas)){

                            if($("#"+_this.tsMaskCls)){
                                $("#"+_this.tsMaskCls).remove();
                                $("body").append(_this.tsMaskTpl);
                            }

                            if($("#"+_this.tsPicCls)){
                                $("#"+_this.tsPicCls).remove();
                                $("body").append(_this.tsPicTpl);
                            }

                            $(_this.bigImg).attr({
                                "src":_src
                            });
                            /*$(_this.bigImg).attr({
                                "src":_src,
                                "width":800,
                                "height":800
                            });*/

                            _this.foundPic(_width,_height,ol,ot);

                            _this.foundMask(_width,_height,ol,ot);
                            $("#"+_this.tsMaskCls).trigger("mousemove");

                        }

                    },10);

                }
                if(ev.type === "mouseleave"){
                    clearTimeout(timer);
                }
            });
            $(document).on("mousemove","#"+_this.tsMaskCls,function(ev){
                _this.imgMouseMove($(this),ev);
                _this.markSize();
            });
        },
        foundMask:function(_width,_height,ol,ot){
            var _this = this;

            $("#"+_this.tsMaskCls).css({
                width:_width + 1,
                height:_height,
                position:"absolute",
                left:ol,
                top:ot,
                zIndex:_this.zIndex
            });

            //修复IE下遮不住的问题
            $(".maskbg").css({
                width:_width + 1,
                height:_height,
                backgroundColor:"#000",
                opacity:0.1
            });

            $("#"+_this.tsMaskCls).on("mouseleave",function(){
                _this.cancle();
            });
        },
        foundPic:function(_width,_height,ol,ot){
            var _this = this;
            if(_width < 310){
            $("#"+_this.tsPicCls).css({
                width:310,
                height:_height,
                position:"absolute",
                left:ol + _width + 10,
                top:ot - 1,
                backgroundColor:"#f5f5f5",
                border:"1px solid #333",
                zIndex:_this.zIndex,
                overflow:"hidden"
            });
            }
            else if(_height < 310){
                $("#"+_this.tsPicCls).css({
                    width:_width,
                    height:310,
                    position:"absolute",
                    left:ol + _width + 10,
                    top:ot - 1,
                    backgroundColor:"#f5f5f5",
                    border:"1px solid #333",
                    zIndex:_this.zIndex,
                    overflow:"hidden"
                });
            }else{
                $("#"+_this.tsPicCls).css({
                    width:_width,
                    height:_height,
                    position:"absolute",
                    left:ol + _width + 10,
                    top:ot - 1,
                    backgroundColor:"#f5f5f5",
                    border:"1px solid #333",
                    zIndex:_this.zIndex,
                    overflow:"hidden"
                });
            }
        },
        imgMouseMove:function($this,event){
            var $mark = $this.children(this.mark);
            //鼠标在小图的位置
            this.mouseMoveX = event.pageX-$this.offset().left - $mark.outerWidth()/2;
            this.mouseMoveY = event.pageY-$this.offset().top - $mark.outerHeight()/2;

            //最大值
            this.maxLeft =$this.width()- $mark.outerWidth();
            this.maxTop =$this.height()- $mark.outerHeight();
            this.markLeft = this.mouseMoveX;
            this.markTop = this.mouseMoveY;

            this.updataMark($mark);
            this.updataBig();
        },
        updataMark:function($mark){
            //小框移动范围
            if(this.markLeft < 0){
                this.markLeft = 0;
            }else if(this.markLeft>this.maxLeft){
                this.markLeft = this.maxLeft;
            }

            if(this.markTop < 0){
                this.markTop = 0;
            }else if(this.markTop>this.maxTop){
                this.markTop = this.maxTop;
            }
            //获取放大镜的移动比例，即这个小框在区域中移动的比例
            this.perX = this.markLeft/$("#"+this.tsMaskCls).outerWidth();
            this.perY = this.markTop/$("#"+this.tsMaskCls).outerHeight();
            //大图坐标
            this.bigLeft = -this.perX*$(this.bigImg).outerWidth();
            this.bigTop = -this.perY*$(this.bigImg).outerHeight();
            //设定小框的位置
            $mark.css(this.markStyle);
            $mark.css({"left":this.markLeft,"top":this.markTop}).show();
        },
        updataBig:function(){
            //大图坐标设置
            $(this.big).css({"display":"block"});
            $(this.bigImg).css({"display":"block","left":this.bigLeft,"top":this.bigTop});
        },
        cancle:function(){
            //关闭放大镜
            $("#"+this.tsMaskCls).remove();
            $("#"+this.tsPicCls).remove();
        },
        markSize:function(){
            //计算mark的宽度和高度
            this.bigWid = parseFloat($(this.bigImg).outerWidth());
            this.smallWid = parseFloat($("#"+this.tsMaskCls).outerWidth());
            this.bigHei = parseFloat($(this.bigImg).outerHeight());
            this.smallHei = parseFloat($("#"+this.tsMaskCls).outerHeight());

            this.markWid = this.smallWid/(this.bigWid/this.smallWid);
            this.markHei = this.smallHei/(this.bigHei/this.smallHei);

            //$("#"+ this.tsMaskCls + " "+ this.mark).css({"width":this.markWid,"height":this.markHei});
            //控制移动框大小
            $("#"+ this.tsMaskCls + " "+ this.mark).css({"width":100,"height":100});
        }
    };
    module.exports = glasses;
});