/**
 * Created by yefeng on 2014/4/24.
 */
$(function(){
   //头部规则中心下拉
   $(".rules-center").hover(function(){
       $(this).find(".items").show();
   },function(){
       $(this).find(".items").hide();
   }) ;

   //头部搜索下拉
   $(".search-select").hover(function(){
       $(this).find(".rules-type").show();
   },function(){
       $(this).find(".rules-type").hide();
   });

   //头部搜索下拉选择事件
   $(".rules-type a").click(function(){
       $(".rules-type a").removeClass("select");
       $(this).addClass("select");
       var type= $(this).attr("data-val");
       var txt = $(this).text();
       $(".search-select span").text(txt);
       $("#rtype").val(type);
       $(".rules-type").hide();
   });

    /*隔行变色*/
    $(".result li:even").addClass("even");
    
 	$('#searchTop #sub-btn').on('click', function (){
 		var key = $.trim($('#searchTop #keyWord').val());
 		if("请输入您要搜索的关键字" == key){
 			return ;
 		}
		window.location.href = "/common/rules/list?" + "keyWord=" + encodeURIComponent(key) ;
	});

 	$('#searchTop #keyWord').keypress(function (e){
 		 var isFocus = $('#searchTop #keyWord').is(":focus");
	     if(isFocus && (e.which == 13 || e.which == 10)) {
    		var key = $.trim($('#searchTop #keyWord').val());
    		if("请输入您要搜索的关键字" == key){
     			return ;
     		}
	       window.location.href = "/common/rules/list?" + "keyWord=" + encodeURIComponent($.trim(key)) ;
	     }
 	});
 	
    
});
