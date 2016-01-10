$(function($) {
    //150px高度提示框
	$('.biaochi').hover(function(){
		$(this).append('<span>头部区域限制高度150px,超出部分截取</br>导航建议高度25-45px</span>');
	},function(){
		$(this).find('span').remove();
	});

    //头部jqueryUI 拖拽
    $('.header-navcen').sortable({
        opacity: 0.5,
        helper: 'clone',
        placeholder: 'ui-state-highlight',
        revert: false//动画效果
        /*ajax 保存到数据库的方法update: function(){
             var new_order = [];
             $list.children(".modules").each(function() {
                new_order.push(this.title);
             });
             var newid = new_order.join(',');
             var oldid = $orderlist.val();
             $.ajax({
                type: "post",
                url: "",
                data: { id: newid, order: oldid },   //id:鏂扮殑鎺掑垪瀵瑰簲鐨処D,order锛氬師鎺掑垪椤哄簭
                beforeSend: function() {
                     $show.html("<img src='load.gif' /> 姝ｅ湪鏇存柊");
                },
                success: function(msg) {
                     //alert(msg);
                     $show.html("");
                }
             });
        }*/
    });
    //主体jqueryUI k-movecontent拖拽
    $('.sidebar-l').sortable({
        opacity: 0.5,
        helper: 'clone',
        placeholder: 'ui-state-highlight',
		handle:'.conbar',
        items:".k-movecontent",
        revert: false,//动画效果
        update: function(){
            configSort();
        }
    });

    $(".header-nav").on("mouseover",".header-cen,.nav-cen",function(){//头部上面覆盖层的高度
        var navbarHei = $(this).height() - 2;
        $(this).find(".navbar").height(navbarHei);
    });

    //隐藏到侧边
    $('.hidelogobox .btnn').click(function(){
        $('.sidebar-r').hide();
        $('.hideover').fadeIn();
        $('.ratebar').fadeIn();
        $('.sidebar-l').animate({
            width:'1200px'
        });
        $('.es-carousel-wrapper').animate({
            width:'1050px'
        });
        $('.es-carousel').animate({
            width:'1050px'
        });
    });

    $('.hideover').click(function(){
        $('.sidebar-r').show();
        $('.ratebar').hide();
        $(this).hide();
        $('.sidebar-l').css('width','885px');
        $('.es-carousel-wrapper').css('width','748px');
        $('.es-carousel').css('width','748px');
    });

    $('.hidelogobox .btnn').css('opacity','0.5');
    $('.hidelogobox .btnn').hover(function(){
        $(this).stop().animate({
            opacity:'1'
        });
    },function(){
        $(this).stop().animate({
            opacity:'0.5'
        });
    });

    //产品展示JS调用
    $('#carousel').elastislide({
        imageW  : 230,
        minItems    : 3,
        margin : 20
    });

    $(".do-body").on("mouseover",".k-movecontent",function(){//上面覆盖层的高度
        var conbarHei = $(this).height() - 12;
        $(this).find(".conbar").height(conbarHei);
    });

	//主体模块可编辑部分
	$('.sidebar-l').each(function(){

		$(this).on("click","span#k-up",function(){//上移
            var onthis = $(this).parents(".k-movecontent");
            var getup = $(this).parents(".k-movecontent").prev();
            $(getup).before(onthis);
            movecontent();
            configSort();
		});
		$(this).on("click","span#k-down",function(){//下移
			var onthis = $(this).parents(".k-movecontent");
			var getdown = $(this).parents(".k-movecontent").next();
            if($(this).parents(".k-movecontent").hasClass("last")){
                return false;
            }
			$(getdown).after(onthis);
            movecontent();
            configSort()
		});
        $(this).on("click","span#k-delet",function(){//移除
            var onthis = $(this).parents(".k-movecontent");
            var id = onthis.data("id");
            if(!id){
                onthis.remove();
                movecontent();
            }else{
                var delConfigUrl = $("#delConfigUrl").val()+id;
                $.ajax({
                    type: "POST",
                    url: delConfigUrl,
                    dataType: "json",
                    cache: false,
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(errorThrown);
                    },
                    success: function (result){
                        if(result.code  != 126000)
                        {
                            remind("error",result.message);
                        }else{
                            onthis.remove();
                            movecontent();
                        }
                    }
                })

            }
            /*var dbMark = onthis.data("db");//是否是数据库数据的标记
            if(id && dbMark){
                $("#delConfigData").append("<input name='configIds' value='"+id+"'> ");
            }*/

        });
	});
    function movecontent(){
        $(".sidebar-l .k-movecontent").removeClass("last");
        $(".sidebar-l .k-movecontent:last").addClass("last");
    }
    movecontent();
});

(function($) {//右侧公司旗下品牌
    $.fn.extend({
        //可以用作多图滚动，也可以用做幻灯片
        "someSlide":function(options){
            //设置默认值
            options=$.extend({
                xuanid:'#focus',
                changdu:'#focus ul li',
                changul:'#focus ul',
                anniu:'#focus .btnBg',
                huaru:'#focus .btnn span',
                pre:'#focus .pre',
                next:'#focus .next',
                toumingdu:'#focus .preNext'
            },options);
            var sWidth = $(options.xuanid).width(); //获取焦点图的宽度（显示面积）
            var len = $(options.changdu).length; //获取焦点图个数
            var index = 0;
            var picTimer;

            //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
            var btn = "<div class='btnBg'></div><div class='btnn'>";
            for(var i=0; i < len; i++) {
                btn += "<span></span>";
            }
            btn += "</div><div class='preNext pre'>&lt;</div><div class='preNext next'>&gt;</div>";

            $(options.xuanid).append(btn);
            //$(options.anniu).css("opacity",0.5);
            //添加移动显示事件
            $(options.xuanid).hover(function(){
                $(options.toumingdu).stop().toggle();
            });
            //为小按钮添加鼠标滑入事件，以显示相应的内容
            $(options.huaru).css("opacity",0.4).mouseover(function() {
                index = $(options.huaru).index(this);
                showPics(index);
            }).eq(0).trigger("mouseover");
            //上一页、下一页按钮透明度处理
            $(options.toumingdu).css("opacity",0.2).hover(function() {
                $(this).stop(true,false).animate({"opacity":"0.5"},300);
            },function() {
                $(this).stop(true,false).animate({"opacity":"0.2"},300);
            });
            //上一页按钮
            $(options.pre).click(function() {
                index -= 1;
                if(index == -1) {index = len - 1;}
                showPics(index);
            });
            //下一页按钮
            $(options.next).click(function() {
                index += 1;
                if(index == len) {index = 0;}
                showPics(index);
            });
            //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
            $(options.changul).css("width",sWidth * (len));

            //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
            $(options.xuanid).hover(function() {
                clearInterval(picTimer);
            },function() {
                picTimer = setInterval(function() {
                    showPics(index);
                    index++;
                    if(index == len) {index = 0;}
                },4000); //此4000代表自动播放的间隔，单位：毫秒
            }).trigger("mouseleave");

            //显示图片函数，根据接收的index值显示相应的内容
            function showPics(index) { //普通切换
                var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
                $(options.changul).stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position

                $(options.huaru).stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
            }
        }
    });
})(jQuery);
$(document).ready(function(){
    $(this).someSlide();
});