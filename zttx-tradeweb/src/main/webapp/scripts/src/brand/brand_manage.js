//新增品牌
var add_brand = {
    init: function(){
        this.initUM();  //初始化编辑器
        this.handleCertificate();  //处理证书
        //this.handleCategory();  //处理主营产品
    },
    initUM: function(){
        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor1', {
                initialFrameWidth: 676,
                initialFrameHeight: 500
            });
        })
     },
    handleCertificate: function(){
        if($(".certificate_box").length == 0){
            return;
        }
        $(".certificate_box").on("mouseenter mouseleave",".item",function(ev){
            if(ev.type == "mouseenter"){
                $(this).find(".close").show();
            }else if(ev.type == "mouseleave"){
                $(this).find(".close").hide();
            }
        });
        $(".certificate_box").on("click",".close",function(ev){
            var display = $(this).data("display") || "";
            var confirm = window.confirm("确定要删除吗？");
            if(!confirm){
                return;
            }
            $(this).parents(".item").remove();
        })
    },
    handleCategory: function(){
        if($("#select_category").length == 0){
            return;
        }
        var _this = this;
        //这里的数据也可以异步请求
        var categoryUrl = "/client/DealDic/search";
        $.post(categoryUrl, {parentId: ""}, function(data){
        	var category_arr = data.rows;
        	var category_obj = _this.createItem(category_arr);
            category_obj.appendTo($("#select_category .select_inner"));
            $("#select_category").on("click",".item",function(){
                $("#select_category .item").removeClass("current");
                $(this).addClass("current");
                //发起请求，得到二级类目，这里未做数据缓存，如果需要，则要一个cache变量来保存请求完的数据
                $("#select_class .select_inner").empty();
                $.post(categoryUrl, {parentId: $(this).attr('id').replace('item_', '')}, function(data){
                	var class_arr = data.rows;
                    var class_obj = _this.createItem(class_arr);
                    class_obj.appendTo($("#select_class .select_inner"));
                }, "json");

            });
            $("#select_class").on("click",".item",function(){
                //如果已经点击过了一次，不再处理
                if($(this).attr("clicked") == "true"){
                    return;
                }
                $("#select_class .item").removeClass("current");
                $(this).addClass("current");
                $(this).attr("clicked","true");
                var product_obj = _this.createItem([{
                    "id" : $(this).attr("id").replace('item_', ''),
                    "item": $(this).find("span").html(),
                    "no": $(this).attr("no")
                }],"close");
                product_obj.appendTo($("#select_product .select_inner"));
            });
            $("#select_product").on("mouseenter mouseleave click",".item",function(ev){
                switch (ev.type){
                    case "mouseenter":
                        $(this).find(".close_icon").show();
                        break;
                    case "mouseleave":
                        $(this).find(".close_icon").hide();
                        break;
                    case "click":
                        var id = $(this).data("id");
                        $($("#item_"+id)).removeClass("current");
                        $("#item_"+id).attr("clicked","false");
                        $(this).remove();
                }
            })
        }, "json");
    },
    /**
     *
     * @param arr
     * @param type  默认为choose
     * @returns {*|jQuery|HTMLElement}
     */
    createItem: function(arr,type){
        var item_li = [];
        type = !type ? "choose": type;
        $.each(arr,function(index,obj){
            if(type == "choose"){
            	obj.id = obj.refrenceId;
            	obj.item = obj.dealName;
            	obj.no = obj.dealNo;
                item_li.push("<li id='item_"+obj.id+"' no='"+obj.no+"' class='item'><span>"+obj.item+"</span><i class='iconfont'>&#xe60f;</i></li>")
            }else if(type == "close"){
                item_li.push("<li data-id='"+obj.id+"' no='"+obj.no+"' class='item'><span>"+obj.item+"</span><i class='iconfont close_icon'>&#xe602;</i><input type='hidden' name='deals' value='"+obj.no+"' /></li>");
            }
        })
        return $(item_li.join(""));
    }
}

//品牌资料完善
var data_perfect = {
    init: function(){
        this.initUM()
        this.tabDistribution(); //经销网络选项卡内的tab切换
        this.addNetTab();  //新增网络选项卡
        this.area();
        add_brand.handleCertificate();
    },
    initUM: function(){
        var um_config = {
            initialFrameWidth: 676,
            initialFrameHeight: 500
        };
        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor2', um_config);
            UM.getEditor('myEditor3', um_config);
            UM.getEditor('myEditor4', um_config);
        })
    },
    tabDistribution: function(){
        $(".distribution_net").on("click",".inner_tab li",function(){
            var index = $(this).index();
            $(".distribution_net .inner_tab li").removeClass("current").eq(index).addClass("current");
            $(".distribution_net .inner_tab_con_box .inner_tab_con").hide().eq(index).show();
        });
    },
    addNetTab: function(){
        if($("#new_add_radio").prop("checked")){
            $(".choose_from").hide();
            $(".new_add").show();
        }
        if($("#choose_from_radio").prop("checked")){
            $(".new_add").hide();
            $(".choose_from").show();
        }
        $("#new_add_radio").on("click",function(){
             $(".choose_from").hide();
             $(".new_add").show();
        });
        $("#choose_from_radio").on("click",function(){
            $(".new_add").hide();
            $(".choose_from").show();
        });
    },
    area: function(){
        if(typeof PCAS == "undefined" || $("#province").length == 0){
            return;
        }
        var hiddenProvince	= $("#hiddenProvince").val() ? $("#hiddenProvince").val() : '浙江';
        var hiddenCity		= $("#hiddenCity").val() ? $("#hiddenCity").val() : '宁波';
        var hiddenCounty	= $("#hiddenCounty").val() ? $("#hiddenCounty").val() : '';
        new PCAS("province","city","county",hiddenProvince,hiddenCity,hiddenCounty);
    }
}

//新增会议
var add_meeting = {
    init: function(){
        /*
    	seajs.use(["calendar","calendar_style"],function(Calendar){
            $("#start-cal,#end-cal").addClass("hasDatepicker");
            var c1 = new Calendar({trigger: '#start-cal'})
            var c2 = new Calendar({trigger: '#end-cal'});
            c1.on('selectDate', function(date) {
                c2.range([date, null]);
            });

            c2.on('selectDate', function(date) {
                c1.range([null, date]);
            });
        })
        */

        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor', {
                initialFrameWidth: 676,
                initialFrameHeight: 300
            });
        })

    }
}

//新增新闻
var add_news = {
    init: function(){

    	seajs.use(["calendar","calendar_style"],function(Calendar){
            $("#clock_time").addClass("hasDatepicker");
            new Calendar({trigger: '#clock_time'})
        })

        seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
            UM.getEditor('myEditor', {
                initialFrameWidth: 676,
                initialFrameHeight: 300
            });
        })

        //添加option数据
        for(var i = 1;i<=24;i++){

        }
    }
}

