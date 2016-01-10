/**
 * Created by 逊 on 2014/7/31.
 */
define(function(r,e,m){
    var $ = r("$");
    m.exports = function(callback){
        var orderItem = $("[data-order]").each(function(i,o){
            var type = $(o).append("<i>↓</i>").data("isorder");
            if(type)
            {
                $(o).find("i").text("↑");
            }
            else
            {
                $(o).data({"isorder":false});
            }

            $(o).css({cursor:"pointer"}).click(function(){
                var me = this;
                $(orderItem).not(me).data({"isorder":false}).find("i").text("↓");
                //目前
                type = $(me).data("isorder");
                //改变 type
                type = !type;
                $(me).data({"isorder":type});
                $(me).find("i").text(type?"↑":"↓");
                callback({type:type?"desc":"asc",order:$(me).data("order")});
            });
        });
    }
});