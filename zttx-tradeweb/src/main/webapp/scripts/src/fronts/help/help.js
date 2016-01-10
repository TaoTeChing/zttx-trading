/**
 * Created by yefeng on 2014/5/4.
 */
$(function(){

    //侧边导航初始化
    var navItem =  $(".nav-tree .tree li").click(function(){
        //console.log($(this).filter(".active"));
        if($(this).hasClass("active")){
            $(navItem).removeClass("active");
        }else{
            $(navItem).not($(this).addClass("active")).removeClass("active");
        }
    });
    $(".nav-tree .tree li.active > ul").show();

    var selectItem = $(".search-help-box .span-select-item").mouseleave(function(){
        $(selectItem).hide();
    });


    //搜索类型选择初始化
    $(".span-select").hover(function(){
        selectItem.show();
    });
    var hideCateId = $('[name="cateId"]');
    var selectItems = $(".search-help-box .span-select-item a");
    var defCateName = $.grep(selectItems,function(o){
        if($(o).data("id")==hideCateId.val()){
            return o;
        }
    });

    $(".span-select").text(defCateName[0] ? $(defCateName).html() : "所有");

    $(selectItems).click(function(){
        $(".span-select").text($(this).text());
        $(hideCateId).val($(this).data("id"));
        $(selectItem).hide();
    });


    /*//头部规则中心下拉
     $(".rules-center").hover(function(){
     $(this).find(".items").show();
     },function(){
     $(this).find(".items").hide();
     }) ;
     *//*隔行变色*//*
     $(".result li:even").addClass("even");
     *//*slider*//*
     $(".level-1").click(function(){
     $(".level-1").removeClass("active");
     $(this).toggleClass("active");
     *//*
     var scope = this;
     var ul = $(this).find('ul');
     if(ul.is(":hidden")){
     $(".slider .active ul").hide();
     $(".slider .active").removeClass("active");
     ul.slideDown();
     $(this).addClass("active");
     }else{
     ul.slideUp(function(){
     $(scope).removeClass("active");
     });
     }
     *//*
     });

     $('#searchTop #sub-btn').on('click', function (){
     var key = $.trim($('#searchTop #keyWord').val());
     if("请输入您要搜索的关键字" == key){
     return ;
     }
     window.location.href = "/help/list?" + "keyWord=" + encodeURIComponent(key) ;
     });

     $('#searchTop #keyWord').keypress(function (e){
     var isFocus = $('#searchTop #keyWord').is(":focus");
     if(isFocus && (e.which == 13 || e.which == 10)) {
     var key = $.trim($('#searchTop #keyWord').val());
     if("请输入您要搜索的关键字" == key){
     return ;
     }
     window.location.href = "/help/list?" + "keyWord=" + encodeURIComponent($.trim(key)) ;
     }
     });

     */
});