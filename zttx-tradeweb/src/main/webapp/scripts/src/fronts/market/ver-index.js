//与编辑相关的js

//编辑模式下遮罩层
$(".common-cover,.common-cover-mould").hover(function(){
    var _hei = $(this).outerHeight() - 2;//减二是因为外面的边框；
    var _wid = $(this).outerWidth() - 2;
    //console.log(_hei);
    $(this).find(".common-cover-content").show().height(_hei).width(_wid);
},function(){
    $(this).find(".common-cover-content").hide();
});

//编辑模式下tabs切换
$(".jsedit-tabs-menus").on("click","li",function(){
    var index = $(this).index();
    $(this).parent().find("li").removeClass("current").eq(index).addClass("current");
    $(this).parent().next().find(".jsedit-contact").hide().eq(index).show();
});

//编辑模式下弹出层
seajs.use(["dialog"],function(Dialog){
    var headerDialog = new Dialog({
        width:560,
        trigger:"#js-header-edit",
        content:$(".js-header-boxes"),
        classPrefix:"edit-dialog"
    });
    var navDialog = new Dialog({
        width:560,
        trigger:"#js-nav-edit",
        content:$(".js-nav-boxes"),
        classPrefix:"edit-dialog"
    });
    var DIYDialog = new Dialog({
        width:560,
        trigger:"#js-DIY-edit",
        content:$(".js-DIY-boxes"),
        classPrefix:"edit-dialog"
    });
    var prospectus = new Dialog({
        width:800,
        trigger:"#js-prospectus-edit",
        content:$(".js-prospectus-boxes"),
        classPrefix:"edit-dialog"
    });
    var visual = new Dialog({
        width:800,
        trigger:"#js-visual-edit",
        content:$(".js-visual-boxes"),
        classPrefix:"edit-dialog"
    });
    var explosion = new Dialog({
        width:800,
        trigger:"#js-explosion-edit",
        content:$(".js-explosion-boxes"),
        classPrefix:"edit-dialog"
    });
    var store = new Dialog({
        width:800,
        trigger:"#js-store-edit",
        content:$(".js-store-boxes"),
        classPrefix:"edit-dialog"
    });
    var news = new Dialog({
        width:560,
        trigger:"#js-news-edit",
        content:$(".js-news-boxes"),
        classPrefix:"edit-dialog"
    });
    var download = new Dialog({
        width:560,
        trigger:"#js-download-edit",
        content:$(".js-download-boxes"),
        classPrefix:"edit-dialog"
    });
    var message = new Dialog({
        width:560,
        trigger:"#js-message-edit",
        content:$(".js-message-boxes"),
        classPrefix:"edit-dialog"
    });
    var product = new Dialog({
        width:800,
        trigger:"#js-product-edit",
        content:$(".js-product-boxes"),
        classPrefix:"edit-dialog"
    });
    var company = new Dialog({
        width:560,
        trigger:"#js-company-edit",
        content:$(".js-company-boxes"),
        classPrefix:"edit-dialog"
    });
    var fair = new Dialog({
        width:560,
        trigger:"#js-fair-edit",
        content:$(".js-fair-boxes"),
        classPrefix:"edit-dialog"
    });
    var addmodle =  new Dialog({
        width:560,
        trigger:"#js-addmodle-edit",
        content:$(".js-addmodle-boxes"),
        classPrefix:"edit-dialog"
    });
    //隐藏
    $(".edit-common-cancel").click(function(){
        headerDialog.hide();
        navDialog.hide();
        DIYDialog.hide();
        prospectus.hide();
        visual.hide();
        explosion.hide();
        store.hide();
        news.hide();
        download.hide();
        message.hide();
        product.hide();
        company.hide();
        fair.hide();
        addmodule.hide();
    });
});
//创建百度编辑器
createEditor("Editor-header","540","335");
createEditor("Editor-custom","540","335");
createEditor("Editor-prospectus","540","335");
createEditor("Editor-visual","782","335");
createEditor("Editor-explosion","782","335");
createEditor("Editor-store","782","335");
createEditor("Editor-product","782","335");

//编辑模式下删除模块
$(".cover-btn-delete").click(function(){
    $(this).parents(".common-area").remove();
});

//编辑模式下 上下移动
$(".common-area .cover-btn-up").click(function(){
    var _content = $(this).parents(".common-area");
    var _cover = $(this).parents(".common-cover-content");
    var _index = _content.index();
    if(0 == _index){
        remind("error","已在最顶部");
        return false;
    }
    _content.prev().before(_content);
    _cover.hide();
});
$(".common-area .cover-btn-down").click(function(){
    var _content = $(this).parents(".common-area");
    var _cover = $(this).parents(".common-cover-content");
    var _len = $(".common-area").length;
    var _index = _content.index();
    if(_len - 1 == _index){
        remind("error","已在最底部");
        return false;
    }
    _content.next().after(_content);
    _cover.hide();
});
$(".common-area").mouseover(function(){
    var _cover = $(this).find(".common-cover-content");
    _cover.show();
});

//图片设置选择
var imgSet = {
    init:function(){
        this.imgCommon();
    },
    imgCommon:function(){

        var $li = $(".common-img-area li");

        $li.css({
            "position":"relative"
        });

        $li.hover(function(){
            var html = '<i class="inline-block js-commonImg-delet" style="width: 16px;height: 16px;position: absolute;top:2px;right: 2px;background: url(\'/images/market/delet.png\') no-repeat; cursor: pointer;"></i>';
            if($(this).find("a").length == 0){
                html += '<a class="inline-block js-commonImg-select" href="javascript:;" style="width: 90px; height: 20px;line-height: 20px; border-top: 1px solid #ddd; background: blue; color: #fff;position: absolute;left:0;bottom: 0;text-align: center;">选择</a>';
            }

            $(this).append(html);

        },function(){
            $(this).find("i").remove();
            $(this).find(".js-commonImg-select").remove();
        });


        $(".common-img-area").on("click",".js-commonImg-delet",function(){
            $(this).parents("li").remove();
        });

        $(".common-img-area").on("click",".js-commonImg-select",function(){
            var _this = $(this);
            _this.text("已选择").removeClass("js-commonImg-select").addClass("js-commonImg-already");
        });
        $(".common-img-area").on("click",".js-commonImg-already",function(){
            var _this = $(this);
            _this.text("选择").removeClass("js-commonImg-already").addClass("js-commonImg-select");
        });
    }
};
imgSet.init();