$(function () {

    //侧边栏
    if($("#sidebar").length > 0){
        
    	//侧栏鼠标移入事件
    	$("#sidebar").on("click",".j_item .item_hd",function(){
        	$(this).parent().siblings().removeClass("current_item");
            $(this).parent().toggleClass("current_item");
        });
        
        //固定sidebar
        var dOffsetTop = $('#sidebar').offset().top;

        //判断当前滚动条的位置        由于新版左侧导航比较长，因此取消悬浮
        /*if( $(document).scrollTop() >= dOffsetTop ){
            $('#sidebar').css(
                {
                    'position': "fixed",
                    "top": 0,
                    "left": $("#sidebar").offset().left,
                    "z-index": 1000
                }
            )
        }

        $(window).scroll(function () {
            if( $(document).scrollTop() >= dOffsetTop ){
                $('#sidebar').css(
                    {
                        'position': "fixed",
                        "top": 0,
                        "left": $("#sidebar").offset().left,
                        "z-index": 1000
                    }
                )
            }else if( $(document).scrollTop() < dOffsetTop ){
                $('#sidebar').css(
                    {
                        'position': "relative",
                        'left': 0
                    }
                )
            }
        });*/


//        if(typeof currMenuId != "undefined"){
//            $("#menu"+currMenuId).find("a").addClass("current_sub");
//            $("#menu"+currMenuId).parents(".j_item").addClass("current_item");
//        }

    }

    //账户信息和快速通道移动显示
    $("#sidebar .j_hover_item").hover(function(){
        $(this).siblings().removeClass("current_item");
        $(this).toggleClass("current_item");
    });

    //通用上传图片容器（本地上传、图库选择） tab切换
    $(".js_uploadfile").each(function(){
        var _this = this;
        $(this).on("click",".ui_tab_item",function(){
            var index = $(this).index();
            $(".ui_tab_item",$(_this)).removeClass("current").eq(index).addClass("current");
            $(".file_tab_con",$(_this)).hide().eq(index).show();
        });
    });

    (function(){

        if($("#sidebar").length > 0) {
            //初始化位置
            /*
            var left = $("#sidebar .quick_link").offset().left + 72;
            var top = $("#sidebar .quick_link").offset().top - 15;
            $("#add_cloud").css({
                left: left,
                top: top
            });
            */
            $("#add_cloud").on("click", ".close", function () {
                $(this).parent().hide();
            })

            $("#quick_content").on("mouseenter mouseleave", "li", function (ev) {
                var close = $(this).find(".close");
                if (ev.type == "mouseenter") {
                    close.show();
                } else if (ev.type == "mouseleave") {
                    close.hide();
                }
            })

            $("#quick_content").on("click", ".close", function (ev) {
            	$this = $(this);
            	var refrenceId = $this.parents("li").data("id");
            	$.post("/brand/defMenu/del", {refrenceId:refrenceId}, function(data){
        			if(data.code == zttx.SUCCESS)
        			{
        				ev.preventDefault();
                        var op = $this.parent();
                        op.remove();
        			}else{
        				remind("error",data.message);
        			}
        		}, "json");
            })
            
             if($("#quick_content").length > 0){

                seajs.use(["popup"], function (Popup) {
                    new Popup({
                        trigger: '#sidebar .hd',
                        element: '#quick_content',
                        align: {
                            baseXY: ["right", 0]
                        }
                    }).before("show", function () {
                            $("#add_cloud").hide();
                        });
                })
            }
        }
    })()

    //添加到快速通道
    $(".js_quick_menu").on("click",function(ev){
        ev.preventDefault();
        var op = $(this).parent(),
        href = window.location.pathname;

        var flag = $(this).hasClass("add");

        if(flag){
        	$.post("/brand/defMenu/save", {menuForwardUrl:href}, function(data){
    			if(data.code == zttx.SUCCESS)
    			{
    				//操作添加
    	            $("#add_cloud").show();
    	            setTimeout(function(){
    	                $("#add_cloud").hide();
    	            },3000);

    	            var id = data.object.refrenceId;
    	            var tit = data.object.menuName;

    	            //$(this).hide();
    	            //op.find(".cancel").show();

    	            var html = $('<li data-id="'+id+'"><a class="name" href="'+href+'">'+tit+'</a><a class="close" href="javascript:;">×</a>');

    	            $("#quick_content ul").append(html);
    			}else{
    				remind("error",data.message);
    			}
    		}, "json");
        }else{
            //操作取消

            var id = $(this).data("id");
            
            $("#quick_content li").each(function(){
                var current_id = $(this).data("id");
                if(current_id == id){
                    $(this).remove();
                    return;
                }
            })

            remind("success","已经从快捷通道中移除");
            $(this).hide();
            op.find(".add").show();

        }


    })


    //seajs.use(["placeholders"],function(){ });

    //处理select
    $("select.js_select,select.js-select").SelectBox();

    //处理价格
    $(".js_price,.js-price").isPrice();

    //处理数字
    $(".js_number,.js-number").isPrice(false);

    //让图片在一个容器里面垂直居中
    $(".js_img_center,.js-img-center").each(function () {
        $("<span></span>").appendTo($(this));
    });


    $("input[type='text']").each(function(){
         placeholder($(this));
    });

    initTable();

    //下拉选择
    $(".search_box .select").hover(function(){
        $(this).addClass("hover");
        $(".search_box ul").show();
    },function(){
        $(this).removeClass("hover");
        $(".search_box ul").hide();
    });

    $(".search_box a").click(function(){
        var txt = $(this).text(),
            val = $(this).data('type');
        $(".search_box .type").text(txt);
        $("#selectType").val(val);
        $(".search_box ul").hide();
    });

    //快速通道
    $(".fast_content dd").click(function(){
       $(this).toggleClass("selected");
    });

    $(".fast_panel .button_cancel").click(function(){
        $("#sidebar .current_item").removeClass("current_item");
    });
});

