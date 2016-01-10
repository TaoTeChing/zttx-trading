$(function(){
    seajs.use(["pagination","template","dialog"],function(Pagination,template,Dialog){


        template.helper('formatNumberTwo',function(price) {
            if (isNaN(price)) {
                return price;
            } else {
                return parseFloat(price).toFixed(2);
            }
        });
        template.helper('formatNumberThree',function(price) {
            if (isNaN(price)) {
                return price;
            } else {
                return parseFloat(price).toFixed(3);
            }
        });

        var isRecommendHide = $("#isRecommendHide").val();
        //渲染数据
        var page = new Pagination({

            url: "/brand/freightTemplate/listData",
            elem: "#pagination",
            data: {pageSize: 5,isRecommend:isRecommendHide},
            method: "post",
            handleData: function (json) {
                if(json.rows && json.rows.length > 0){
                    console.log(json);
                    var html = template.render("diyTplTemplate", json);
                    $("#diyTplCon").html(html);
                }else{
                    $("#diyTplCon").html("<div style='text-align: center;margin-top: 20px;'>暂无数据</div>");
                }
                $("#diyTplCon").html(html);

            }
        });

        var tplCommonFn = function(elem,url,callback){
            var id = elem.parent().data("id");
            $.ajax({
                url:url,
                data:{templateId:id},
                method:"post",
                dataType:"json",
                success:function(data){
                    if(callback && $.isFunction(callback)){
                        callback(data);
                        return;
                    }
                    if(data.code != zttx.SUCCESS){
                        remind("error",data.message);
                    }else{
                        window.location.reload();
                    }
                }
            });
        };

        //设为默认
        $("#fareTplList").on("click",".js-add-default",function(){
            tplCommonFn($(this),"/brand/freightTemplate/setDefault");
        });
        //取消默认
        $("#fareTplList").on("click",".js-remove-default",function(){
            tplCommonFn($(this),"/brand/freightTemplate/cancelDefault");
        });
        //删除
        $("#fareTplList").on("click",".js-delete-fare",function(){
            var _self = $(this);
            confirmDialog({
                title:"提示",
                content:"是否确认删除此模板？"
            },function(){
                tplCommonFn(_self,"/brand/freightTemplate/delete");
            });
        });
        //复制模板
        $("#fareTplList").on("click",".js-copy-fare",function(){
            var _val = $(this).parents(".fare-tpl-hd").find(".fl strong").text();
            if(getCharLength(_val) > 50){
                remind("error","模版名称超过50个字符，无法复制");
            }else{
                tplCommonFn($(this),"/brand/freightTemplate/copy");
            }
        });
        //应用该模板
        $("#fareTplList").on("click",".js-use-fare",function(){
            var productIds = $("#productIds").val();
            tplCommonFn($(this),"/brand/freightTemplate/apply?productIds="+productIds,function(data){
                if(data.code != zttx.SUCCESS){
                    remind("error",data.message);
                }else{
                    var d = new Dialog({
                        content:$("#useThisTpl").html(),
                        hasMask:false,
                        width:200
                    }).show();
                }
            });
        });
    });
});