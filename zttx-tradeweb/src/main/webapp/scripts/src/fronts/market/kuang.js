$(function($) {
    $(function(){ //装修框架宽度和高度的调整
        //设置当前页面宽度
        var Wid = $(document.body).width() - 180;
        $('.k-right').width(Wid);
        //设置当前页面高度
        var Hei = $(document).height() - 62;
        var HeiI = $(window).height() - 97;

        $('.k-right').height(Hei);//整个主体的高度
        $('.shousuo').height(Hei);//左边线
        $('#testIframe').height(HeiI);//iframe的高度
        $('.k-left').height(Hei);//左边框架高度
    });
    $(window).resize(function(){
        //设置当前页面宽度
        var Wid = $(document.body).width() - 180;
        $('.k-right').width(Wid);
        //设置当前页面宽度
        var Hei = $(document).height() - 62;
        var HeiI = $(window).height() - 97;

        $('.k-right').height(Hei);//整个主体的高度
        $('.shousuo').height(Hei);//左边线
        $('#testIframe').height(HeiI);//iframe的高度
        $('.k-left').height(Hei);//左边框架高度
    });

    //框下拉
    $('.k-top-cen li').hover(function(){
        $(this).find('.k-dropmenu').show();
    },function(){
        $(this).find('.k-dropmenu').hide();
    });

    //左边导航
    $('.k-left .k-lefth2').click(function(){
        $(this).next('.k-leftul').slideToggle();
    });

    //左侧导航点击选中状态
    $('.k-body .k-left li').click(function(){
        $(this).addClass('selected').siblings().removeAttr('class');
    });
    //左边文字移动
    $('.k-body .k-left li').hover(function(){
        $(this).find('a').stop().animate({
            marginLeft:'10px'
        });
    },function(){
        $(this).find('a').stop().animate({
            marginLeft:'0px'
        });
    });


	/*分类管理操作*/
    $(".k-openall").click(function(){//展开全部
        $('table tbody .subtree').show();
        $('table tbody').find('#trplus').removeClass().addClass('minus');
        $(this).addClass('disabled');
        $('.k-selectbar .k-closeall').removeClass('disabled');
    });
    $(".k-closeall").click(function(){//收起全部
        $('table tbody .subtree').hide();
        $('table tbody').find('#trplus').removeClass().addClass('plus');
        $(this).addClass('disabled');
        $('.k-selectbar .k-openall').removeClass('disabled');
    });
    $('.k-selectbar .k-selall [name=allche]:checkbox').click(function(){//全选
        $('table').find('[name=choice]:checkbox').prop("checked", this.checked);
    });
    $('.k-delall').click(function(){//批量删除
        var str="你选中的是：";
        $('[name=choice]:checkbox:checked').each(function(){
            str+=$(this).val()+",";
        })
        alert(str);
    });
    $(document).on('click','#trplus',function(){//单个点开和关闭
        var hgff = $(this).parent().parent().siblings();
        $(this).parent().parent().siblings().toggle();
        if($(hgff).is(':visible')){
            $(this).addClass('minus');
        }else{
            $(this).removeClass().addClass('plus');
        }
    });
	$('tr.maintree td.td-001').find('.k-tbtxt').each(function(){//主菜单和子菜单名称验证
		$(document).on("keyup",".k-tbtxt",function(){
		//$('.k-tbtxt').blur(function(){
		  $(this).parent().find('span.onError').remove();
		  $(this).parent().find('span.onSuccess').remove();
		  if(this.value ==""){
				var errorMsg = '*输入错误';
				$(this).parent().append('<span style=" position:absolute; display:block; top: 8px; left:172px; height:26px; background:#FFFBEF; border:1px solid #ffbc76; padding:0 3px; color:#e96300; z-index:9" class="onError">'+errorMsg+'</span>');
		  }
		  if(this.value.length > "6"){
			   var errorMsg = '*字数超出';
			   $(this).parent().append('<span style=" position:absolute; display:block; top: 8px; left:172px; height:26px; background:#FFFBEF; border:1px solid #ffbc76; padding:0 3px; color:#e96300; z-index:9" class="onError">'+errorMsg+'</span>');
		  }
		  if(this.value.length <= "6" && this.value.length >= "1"){
				var okMsg = '*输入正确';
				$(this).parent().append('<span style=" position:absolute; display:block; top: 8px; left:172px; height:26px; background:#bcefbf; border:1px solid #2aa130; padding:0 3px; color:#19731d; z-index:9" class="onSuccess">'+okMsg+'</span>');
		  }
		}).keyup(function(){
			$(document).on("blur",".k-tbtxt",function(){
				$(this).triggerHandler("blur");
			});
        }).focus(function(){
           $('.k-tbtxt').triggerHandler("blur");
        });
	});

	$(document).on("click","#saveall",function(){//保存全部
		$(".k-tbtxt").each(function(){
			$(this).trigger('keyup');
		});
		var numError = $(".k-tbtxt").parent().find('.onError').length;
		if(numError){
		   return false;
		}else{
			alert("修改分类成功！");
		}
	});

    /*以下是主分类的操作*/
    function addmiantree(){}
    $('.addmiantree').click(function(){//添加主分类
        $('.table').find('tbody:last').after('<tbody class="tbody"><tr class="maintree"><td class="td-000"><input type="checkbox"name="choice"class="f-l"style="margin:4px 5px 0 0"value="衣服"/></td><td width="220"class="td-001"><span id="trplus"class="plus"></span><input type="text"value=""class="k-tbtxt" id="miantreename" /></td><td width="140"class="td-002"><a href=""title=""target="">编辑</a> <a href=""title=""target="">删除</a></td><td class="td-003"><span class="main-up">上</span> <span class="main-down">下</span></td><td class="td-004"><span>开关</span></td><td class="td-005"><span>2014-01-24</span></td><td class="td-006"><span>自动分类</span></td><td class="td-007"><a class="main-remove">删除</a></td></tr><tr class="subtree addsubtree"><td class="td-000"></td><td class="td-001">--- <input type="button"value="添加子分类"class="k-tbbtnadd btn btn-warning"/></td><td class="td-002"></td><td class="td-003"></td><td class="td-004"></td><td class="td-005"><span>2014-01-24</span></td><td class="td-006"></td><td class="td-007"></td></tr></tbody>');
        maintreemove();
    });
    function maintreemove(){//移动时主分类上下按钮的变化
        if($(".table tbody").children("tr.maintree").size()==1)
        {
            $(".table tbody").find("tr.maintree").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
        }
        if($(".table tbody").children("tr.maintree").size()==2)
        {
            $(".table tbody").find("tr.maintree:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
            $(".table tbody").find("tr.maintree:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
        }
        if($(".table tbody").children("tr.maintree").size()>=3)
        {
            $(".table tbody tr.maintree:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
            $(".table tbody tr.maintree:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
            $(".table tbody tr.maintree:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
        }
    }
    maintreemove();
    function mainT(){//主分类移动事件
        $(document).on("click",".main-up",function(){//上移
            var onthis = $(this).parent().parent().parent();
            var getup = $(this).parent().parent().parent().prev();
            if($(this).parent().parent().hasClass('subfirst')){
                //alert("顶级元素不能上移");
                return false;
            }
            $(getup).before(onthis);
            maintreemove();
        });
        $(document).on("click",".main-down",function(){//下移
            var onthis = $(this).parent().parent().parent();
            var getdown = $(this).parent().parent().parent().next();
            if($(this).parent().parent().hasClass('sublast')){
                //alert("底部元素不能下移");
                return false;
            }
            $(getdown).after(onthis);
            maintreemove();
        });
        $(document).on('click','a.main-remove',function(){//删除
            $(this).parent().parent().parent().remove();
            subtreemove();
        });
    }
    mainT();
    /*以下是子分类的操作*/
    $(document).on("click",".k-tbbtnadd",function(){
        $(this).parent().parent().before('<tr class="subtree subtreemove" style="display:table-row"><td class="td-000"><input type="checkbox" name="choice" class="f-l" style="margin:4px 5px 0 0" value="碎花裙2" /></td><td class="td-001">--- <input type="text" value="" class="k-tbtxt" id="miantreename" /></td><td class="td-002">+ <span>添加图片</span></td><td class="td-003"><span class="sub-up">上</span> <span class="sub-down">下</span></td><td class="td-004"></td><td class="td-005"><span>now</span></td><td class="td-006"><span>手动分类</span></td><td class="td-007"><a class="sub-remove">删除</a></td></tr>');
        subtreemove();
    });
    function subtreemove(){//移动时子分类上下按钮的变化
        if($(".table .tbody1").children("tr.subtreemove").size()==1)
        {
            $(".table .tbody1").find("tr.subtreemove").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
        }
        if($(".table .tbody1").children("tr.subtreemove").size()==2)
        {
            $(".table .tbody1").find("tr.subtreemove:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
            $(".table .tbody1").find("tr.subtreemove:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
        }
        if($(".table .tbody1").children("tr.subtreemove").size()>=3)
        {
            $(".table .tbody1 tr.subtreemove:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
            $(".table .tbody1 tr.subtreemove:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
            $(".table .tbody1 tr.subtreemove:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
        }
    }
    subtreemove();
    function submoveT(){//子分类上下移动样式
        $(document).on('click','span.sub-up',function(){//上移(.live在jquery1.9以后移除，用法如这个，相应的die()也移除了。用off())
            var onthis = $(this).parent().parent();
            var getup = $(this).parent().parent().prev();
            if($(this).parent().parent().hasClass('subfirst')||$(this).parent().parent().hasClass('subonly')){
                //alert("顶级元素不能上移");
                return false;
            }
            $(getup).before(onthis);
            subtreemove();
        });
        $(document).on('click','span.sub-down',function(){//下移
            var onthis = $(this).parent().parent();
            var getdown = $(this).parent().parent().next();
            if($(this).parent().parent().hasClass('sublast')||$(this).parent().parent().hasClass('subonly')){
                //alert("底部元素不能下移");
                return false;
            }
            $(getdown).after(onthis);
            subtreemove();
        });
        $(document).on('click','a.sub-remove',function(){//删除
            $(this).parent().parent().remove();
            subtreemove();
        });
    }
    submoveT();

    $('[deftxt]').css({ color: '#bbb' }).focus(function () {
            if ($(this).val() == $(this).attr("deftxt")) {
                $(this).val("").css({ color: '#666' });
            }
        }).blur(function () {
            if ($(this).val() == '') {
                $(this).val($(this).attr("deftxt")).css({ color: '#bbb' });
            }
    });
});