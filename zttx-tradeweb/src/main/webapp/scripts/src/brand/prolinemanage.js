var prolinemanage = {
    init:function(){
        this.tabSmenu();
        this.amendTbodytoggle();
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.ui_tabsmenu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.ui_tabsbox > div").eq(index).show().siblings().hide();
        });
    },
    amendTbodytoggle:function(){
    	$(".pro-amend-dealer-table").on("click",".city-bodytoggle",function(){
    		var _this = this;
    		setTimeout(function(){
    			$(_this).parents("thead").next("tbody").toggle();
    		},0);
        });
    }
}
prolinemanage.init();

var warrant ={
	init:function(){
        this.checkall();
    },
    checkall:function(){
	    $(document).on("click", "#select-all_span", function () {
	        if ($(this).hasClass("checked")) {
	            $(".proline-table .js_chk").attr("checked", "checked");
	            $(".proline-table .CheckBox").addClass("checked");
	        } else {
	            $(".proline-table .js_chk").attr("checked", "false");
	            $(".proline-table .CheckBox").removeClass("checked");
	        }
	    });
    }
}

var addlineONE ={
    init:function(){
        this.Timetoggle();
        this.seajsuseData();
    },
    Timetoggle:function(){

        $("#owner2").on("click",function(){
            $(".proline-getimehide").show();
        });
        $("#owner1").on("click",function(){
            $(".proline-getimehide").hide();
        });
    },
    seajsuseData:function(){

        baseCalendar("#date",{dateFmt: 'yyyy年MM月dd日 HH时mm分ss秒'})
        rangeCalendar("startDate","endDate");

    }
}

var Amend = {
    init:function(){
        this.seajscllobar();
    },
    seajscllobar:function(){
        seajs.use(["jscroll","$"],function(Jscroll,$){
            $('.scllobarbox').jscrollbar({
                width:8, //滚动条宽度
                color:'#ccc', //滚动条颜色
                opacity:0.7, //透明度
                position:'inner', //滚动条位置
                mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
            });
        })
    }
}

var addline2 = {
    init:function(){
        this.setrate();//设置折扣
        this.seajsvalidator();//文本框验证
        //this.seajsTip();//批量抹零的tip
        //this.alltoaero();//批量抹零操作
        //this.tabSmenu();
        this.seajscllobar();
    },
    setrate:function(){
        function setun(){
            //$(document).on("click",".stepT-nocb-sure",function(){
            $(".stepT-nocb-sure").one("click",function(){
                var valNo = $(this).parent().find(".ratetext").val();
                $(".noreteval").val(valNo);
            })
        }
    },
    seajsvalidator:function(){
        /*seajs.use(['validator'], function(Validator) {
            $(function() {
                var validator = new Validator({
                    element: 'input.ratetext',
                    required: true,
                    rule: 'minlength{min:1}',
                    errormessageRequired: '请选择发布类目'
                });
            });
        });*/
    },
    seajsTip:function(){
        seajs.use(['popup'], function(Popup) {
            var example2 = new Popup({
                trigger: '.step-set-zero',
                element: '.alltozero',
                align: {
                    baseXY: [0, 20]
                }
            });
            var example3 = new Popup({
                trigger: '.js_tip_triggerkucun',
                element: '.js-tip-kucun',
                align: {
                    baseXY: [-55, 22]
                }
            });
        });
    },
    alltoaero:function(){//抹零和四舍五入
            /*$(".tosure").click(function(){
                if($(".tozero").prop("checked")==true){
                    alert("直接抹零");
                }
                if($(".tofourfive").prop("checked")==true){
                    $(".proline-add-stepT .pro-price").each(function(){
                        var _this = $(this);
                        var number = _this.text();
                        alert(number);
                        console.log(number);
                        var sisi = number.toFixed(0);
                        alert(sisi);
                    });
                }
            });*/
    },
    tabSmenu:function(){//
        var $div_li =$("div.js_addline2_menu ul li");
        var onOff = true;
        $div_li.click(function(){
            /*var index=$(this).index();
            if(index == 1){
                seajs.use(["jscroll","$"],function(Jscroll,$){
                    //console.log(jscroll);
                    $('.scllobarbox').jscrollbar({
                        width:8, //滚动条宽度
                        color:'#ccc', //滚动条颜色
                        opacity:0.7, //透明度
                        position:'inner', //滚动条位置
                        mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
                    });
                    onOff = false;
                })
            }*/
            //console.log(index);
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_addline2_con > .js_addline2_conbox").eq(index).show().siblings().hide();
        });
    },
    seajscllobar:function(){
        seajs.use(["jscroll","$"],function(Jscroll,$){
            $('.scllobarbox').jscrollbar({
                width:8, //滚动条宽度
                color:'#ccc', //滚动条颜色
                opacity:0.7, //透明度
                position:'inner', //滚动条位置
                mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
            });
        })
    }
}
