var pictureManagement1 = {
    init:function(){
        this.seajsuseData();
        this.checkAll();
        this.zlipCopy();
    },
    seajsuseData:function(){//时间拾取
        seajs.use(["calendar","calendar_style"],function(Calendar){
            if($("#start-cal").length == 0){
                return;
            }
            $("#start-cal,#end-cal").addClass("hasDatepicker");
            var c1 = new Calendar({trigger: '#start-cal'})
            var c2 = new Calendar({trigger: '#end-cal'});
        })
    },
    checkAll:function(){
        //全选
        $(document).on("click","input[name=allchecked]:checkbox",function(){
            $("input[name=piclist]:checkbox").prop("checked", this.checked );
        });
        //非全选的时候
        $("input[name=piclist]:checkbox").click(function(){
            var $tmp=$('[name=piclist]:checkbox');
            $("input[name=allchecked]:checkbox").attr('checked',$tmp.length==$tmp.filter(':checked').length);
        });
    },
    zlipCopy:function(){

        var _this = this;

        //触发mouseover，每一次移动都监听
        $(".checkallbox").on("mouseover",function(){
               _this.getCopy();
        })

        this.copyEvent(".copythislink");

        this.copyEvent(".copythiscode");

        $(".flash_copy").each(function(){
            _this.copyEvent($(this));
        })

    },

    copyEvent: function(el,type){

        var _this = this;

        seajs.use(["zeroclipboard"],function(ZeroClipboard){

            var clip = new ZeroClipboard( $(el), {
                moviePath: window.SWF_ZeroClipboard_URL
            });

            clip.on( "load", function(client) {
                clip.addEventListener( "complete", function(client,textobj) {
                    if(textobj.text == ""){
                        remind("error","请选择一张图片");
                    }else{
                        remind("success","复制成功")
                    }
                });
            });

        });

    },

    getCopy: function(){
        var links = []; //存储筛选后的links数组
        var codes = [];
        $('[name=piclist]:checkbox:checked').each(function(){

            var _this = $(this);

            var obj =  _this.parentsUntil(".contant").find(".imgbox");

            var link = obj.find("a").attr("href");

            var code = '<img src="'+obj.find("img").attr("src")+'" />';

            links.push(link);

            codes.push(code);

        });

        $("#copy_links_hidden").val(links.join("\r\n"));

        $("#copy_codes_hidden").val(codes.join("<br />"));

    }

}

var pictureManagement2={
    init:function(){
        this.seajscllo();//模拟右侧下拉
        this.ztreeToggle();
    },
    seajscllo:function(){
        seajs.use(["jscroll","$"],function(Jscroll,$){
            $('#test1').jscrollbar({
                width:8, //滚动条宽度
                color:'#ccc', //滚动条颜色
                opacity:0.7, //透明度
                position:'inner', //滚动条位置
                mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
            });
        })
    },
    ztreeToggle:function(){
        $(".ztreeP span.toggleH").click(function(){
            $(".zatree").toggle();
            if($(".zatree").is(":hidden")){
                $("#flashButton").show();
            }
            if($(".zatree").is(":visible")){
                $("#flashButton").hide();
            }
        });
    }
}

var pictureManagement = {
    init:function(){
        //this.tabSmenu();//选项卡切换
        this.seajsuseData();//时间选取
        this.movEdit();//移动编辑
        this.removePic();//图片上传-删除图片
        this.checkAll();//全选
        this.seajscllo();//模拟下拉条
    },

    /*tabSmenu:function(){//选项表切换
        var $div_li =$("div.ui_tabsmenu ul li");
        var onOff = true;
        $div_li.click(function(){
            var index = $(this).index();//监听页面所处的位置
            if(index == 1){
                if(onOff){
                    seajs.use(["jscroll","$"],function(Jscroll,$){
                        $('#test1').jscrollbar({
                            width:8, //滚动条宽度
                            color:'#ccc', //滚动条颜色
                            opacity:0.7, //透明度
                            position:'inner', //滚动条位置
                            mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
                        });
                    })
                    onOff = false;
                }
            }
            //console.log(index);
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.ui_tabsbox > div").eq(index).show().siblings().hide();
        });
    },*/

    seajsuseData:function(){//时间拾取
        seajs.use(["calendar","calendar_style"],function(Calendar){
            if($("#start-cal").length == 0){
                return;
            }
            $("#start-cal,#end-cal").addClass("hasDatepicker");
            var c1 = new Calendar({trigger: '#start-cal'})
            var c2 = new Calendar({trigger: '#end-cal'});
        })
    },

    seajscllo:function(){
        seajs.use(["jscroll","$"],function(Jscroll,$){
            $('#test1').jscrollbar({
                width:8, //滚动条宽度
                color:'#ccc', //滚动条颜色
                opacity:0.7, //透明度
                position:'inner', //滚动条位置
                mouseScrollDirection:'horizontal' //鼠标滚动时滚动的方向
            });
        })
    },

    movEdit:function(){
        function addmiantree(){//添加主分类
            $(document).on("click",".newclass",function(){
                var str = '<tbody>'+
                    '<tr class="maintree">'+
                    '<input type="hidden" name="uuid" value=" "/>'+
                    '<input type="hidden" name="parentId" value=" "/>'+
                    '<input type="hidden" name="level" value="1"/>'+
                    '<td class="td_0"><input type="checkbox"class="ui-checkbox newclasschk mainclasschk"></td>'+
                    '<td class="td_1"><i id="trplus" class="main_iconskai"></i></td>'+
                    '<td class="td_2"><input type="text" class="newclasstext" value="" name="cateName" /></td>'+

                    '<td class="td_3"><span class="addsubclass">添加子分类</span></td>'+
                    '<td class="td_4"><div class="movebox"><span class="moveup mainmoveup" title="上移"></span> <span class="movedown mainmovedown" title="下移"></span></div></td>'+
                    '<td class="td_5"><span class="delete">删除</span></td>'+
                    '</tr>'+
                    '</tbody>';
                $('.table').find('tbody:last').after(str);
                maintreemovechange();
                keyupChange();
            });
        }
        addmiantree();

        function addsubtree(){//添加子分类
            $(document).on("click",".addsubclass",function(){

                $(this).parents("tbody").find('tr:last').after('<tr class="subtree">' +
                    '<input type="hidden" name="uuid"/>'+
                    '<input type="hidden" name="parentId" value=" "/>'+
                    '<input type="hidden" name="level" value="2"/>'+
                    '<td class="td_0"><input type="checkbox"class="ui-checkbox newclasschk"></td>' +
                    '<td class="td_1"></td>' +
                    '<td class="td_2"><div class="sub_zhe inline-block fl"></div><input type="text" class="newclasstext" name="cateName"/></td>' +
                    '<td class="td_3"></td>' +
                    '<td class="td_4"><div class="movebox"><span class="moveup submoveup" title="上移"></span> <span class="movedown submovedown" title="下移"></span></div></td>' +
                    '<td class="td_5"><span class="delete">删除</span></td>' +
                    '</tr>');

                //给子分类的hidden 赋值
                var parentValue = $(this).parents("tbody").find("tr.maintree input[type=hidden][name=uuid]").val();
                if(parentValue!=""){
                    $(this).parents("tbody").find("tr.subtree input[type=hidden][name=parentId]").val(parentValue);
                }
                $(this).parents("tbody").find("tr").show();//添加的时候子分类全部显示

                $(this).parents("tbody").find('tr:last').find(".newclasstext").focus();

                var hgff = $(this).parent().parent().nextAll();
                if($(hgff).is(':visible')){//判断前面小三角的状态
                    $(this).parent().parent().find("#trplus").addClass('main_iconsguan');
                }else{
                    $(this).parent().parent().find("#trplus").removeClass().addClass('main_iconskai');
                }
                subtreemovechange();
                keyupChange();

            });
        }
        addsubtree();

        function maintreemovechange(){//移动时主分类上下按钮的变化
            if($(".table tbody").children("tr.maintree").size()==1)
            {
                $(".table tbody").find("tr.maintree").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
            }
            if($(".table tbody").children("tr.maintree").size()==2)
            {
                $(".table tbody tr.maintree:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                $(".table tbody tr.maintree:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
            }
            if($(".table tbody").children("tr.maintree").size()>=3)
            {
                $(".table tbody tr.maintree:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
                $(".table tbody tr.maintree:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                $(".table tbody tr.maintree:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
            }
        }
         maintreemovechange();

        function subtreemovechange(){//移动时主分类上下按钮的变化
            $(".table").find("tbody").each(function(){
                if($(this).children("tr.subtree").size()==1)
                {
                    $(this).find("tr.subtree").removeClass("subnormal").removeClass("sublast").removeClass("subfirst").addClass("subonly");
                }
                if($(this).children("tr.subtree").size()==2)
                {
                    $(this).find("tr.subtree:eq(0)").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                    $(this).find("tr.subtree:eq(1)").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
                }
                if($(this).children("tr.subtree").size()>=3)
                {
                    $(this).find("tr.subtree:gt(0)").removeClass("subonly").removeClass("subfirst").removeClass("sublast").addClass("subnormal");
                    $(this).find("tr.subtree:first").removeClass("subonly").removeClass("subnormal").removeClass("sublast").addClass("subfirst");
                    $(this).find("tr.subtree:last").removeClass("subonly").removeClass("subnormal").removeClass("subfirst").addClass("sublast");
                }
            });
        }
        subtreemovechange();

        function mainmoveTree(){//主分类上下移动样式
            $(document).on("click","span.mainmoveup",function(){//上移
                var onthis = $(this).parent().parent().parent().parent();
                var getup = $(this).parent().parent().parent().parent().prev();
                if($(this).parent().parent().parent().hasClass("subonly")||$(this).parent().parent().parent().hasClass("subfirst")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).before(onthis);
                maintreemovechange();
            });
            $(document).on("click","span.mainmovedown",function(){//下移
                var onthis = $(this).parent().parent().parent().parent();
                var getdown = $(this).parent().parent().parent().parent().next();
                if($(this).parent().parent().parent().parent().hasClass("sublast") || $(this).parent().parent().parent().parent().hasClass("subonly")){
                    //alert("底部元素不能下移");
                    return false;
                }
                $(getdown).after(onthis);
                maintreemovechange();

            });
        }
        mainmoveTree();

        function submoveTree(){//子分类上下移动样式
            $(document).on("click","span.submoveup",function(){//上移
                var onthis = $(this).parent().parent().parent();
                var getup = $(this).parent().parent().parent().prev();
                if(onthis.hasClass("subonly")||onthis.hasClass("subfirst")){
                    //alert("顶级元素不能上移");
                    return false;
                }
                $(getup).before(onthis);
                subtreemovechange();
            });
            $(document).on("click","span.submovedown",function(){//下移
                var onthis = $(this).parent().parent().parent();
                var getdown = $(this).parent().parent().parent().next();
                if(onthis.hasClass("sublast") || onthis.hasClass("subonly")){
                    //alert("底部元素不能下移");
                    return false;
                }
                $(getdown).after(onthis);
                subtreemovechange();

            });
        }
        submoveTree();

        $("#trplus").each(function(){//开关按钮样式的判断
            var hgff = $(this).parent().parent().nextAll();
            if($(hgff).is(':visible')){
                $(this).addClass('main_iconsguan');
            }else{
                $(this).removeClass().addClass('main_iconskai');
            }
        });

        $(document).on("click","#trplus",function(){//点击打开关闭
            var hgff = $(this).parent().parent().nextAll();
            $(hgff).toggle();
            if($(hgff).is(':visible')){
                $(this).addClass('main_iconsguan');
            }else{
                $(this).removeClass().addClass('main_iconskai');
            }
        });

        $(document).on("click",".subtree td .delete",function(){//子菜单移除
            $(this).parent().parent().remove();
            $("#saveclasses").addClass("saveclass");//添加按钮可点击的class
            subtreemovechange();
        });

        $(document).on("click",".maintree td .delete",function(){//主菜单移除
            var threeP = $(this).parent().parent().parent();
            /*var fourP = $(this).parent().parent().parent().parent();//当主分类里面还有子分类的时候
            if($(this).parent().parent().parent().parent().find(".subtree").size() > 0){
                alert("该分类中还有子分类，不可删除！");
                return false;
            }*/
            threeP.remove();
            $("#saveclasses").addClass("saveclass");//添加按钮可点击的class
        });

        function keyupChange(){//输入监听文本框的变化
            $('.table tbody').keyup(function() {
                $("#saveclasses").addClass("saveclass");
            });
        }
        keyupChange();

        //全选
        $(document).on("click",".chkALL",function(){
            $('input[type=checkbox].newclasschk').prop("checked", this.checked );
        });
        $(document).on("click",".mainclasschk",function(){
            $(this).parent().parent().parent().find('input[type=checkbox].newclasschk').prop("checked", this.checked );
        });
    },

    removePic:function(){
        $(".uploadtable").on("click",".removepic",function(){
           $(this).parent().parent().remove();
        });
    },

    checkAll:function(){

        //全选功能
        $(document).on("click", ".CheckBox", function () {//一个页面多个位置用到checkbox的时候要注意
            if ($(this).hasClass("checked")) {

                $("[name=piclist]").attr("checked", "checked");

                $(this).parent().parent().next().find(".CheckBox").addClass("checked");
            } else {

                $("[name=piclist]").attr("checked", "false");

                $(this).parent().parent().next().find(".CheckBox").removeClass("checked");
            }
        });

        //查找
        var chk_len = $(".contant .js_chk").length;
    }

}


