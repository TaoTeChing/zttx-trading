//品牌管理中心首页的js
var TradePurorder = {
    init: function(){
    	this.initCalendar();
        this.tabsMenu();
        this.extensionTime();   //延长收货期限
        this.updateCash();      //修改金额
        this.handleRemark();    //处理备注信息
        this.closeTrade();      //关闭交易
        this.sendMessage();     //发站内信
        this.remindDealer();    //提醒经销商
        this.handleShipping();  //批量免邮
    },
    tabsMenu:function(){
        var $div_li =$("div.js_trador_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_trador_con > div.js_trador_conbox").eq(index).show().siblings().hide();
        });
    },
    initCalendar:function(){//时间拾取
        if($("#start-cal").length > 0 && $("#end-cal").length > 0){
            rangeCalendar("start-cal","end-cal");
        }
    },
    extensionTimeFn: function(orderId,dialog){
        dialog.hide();
    },
    extensionTime: function(){

        var _this = this;

        var extension_dialog = null,
            onOff = 0;

        var orderId = "";

        seajs.use(["dialog","template"],function(Dialog,template){
        	
            extension_dialog = new Dialog({
                //"trigger": ".js_extension_time",
                "content": $("#extension_tpl").html(),
                "width": 256
            }).after('show', function() {
            	
            	if(!onOff){
                    $(".extension_select").SelectBox();
                    console.log(onOff);
                }
                onOff ++;
            })
            
            $(document).on("click",".js_extension_time",function(){
            	var _This = this;
            	extension_dialog.before("show",function(){
            		var trigger = $(_This);
                    orderId = trigger.data("orderid");
            	});
            	extension_dialog.after("show",function(){
            		var trigger = $(_This);
            		var outActTime = $(_This).parents(".orderbox").find('input[name="outActTime"]').val();
            		/*if(outActTime=="") outActTime=30
            		else outActTime = parseInt(outActTime)+30;*/
            		if(!outActTime || outActTime != ''){
            			var html = '收货期限:   '+outActTime+"点之前";
                		$('.qixian_text').text(html);
            		}
            	});
            	extension_dialog.show();
            })
            
        });


        $(document).on("click",".extension_dialog .confirm_btn",function(){
            _this.extensionTimeFn(orderId,extension_dialog);
        });

        $(document).on("click",".extension_dialog .cancel_btn",function(){
            extension_dialog.hide();
        });

    },

    beforeUpdatePop: function(orderID,type){
        //alert("我弹出来了:"+orderID);
    },

    confirmUpdatePop: function(orderID,type,dialog, discount){
        dialog.hide();
    },

    updateCash: function(){

        var update_dialog = null,
            click_num = 0;
        var _this = this;
        var orderId = "";
        var dealerOrderType = "";
        var type = "";       
        var discount ;
        var payState;

        var flag = 1;

        seajs.use(["dialog","template"],function(Dialog,template){

            template.helper('$formatNumber', function (price) {
                if(isNaN(price)){
                    return price;
                }else{
                    return parseFloat(price).toFixed(2);
                }
            });

            update_dialog = new Dialog({
                //"trigger": ".js_update_cash",
                "width": 726
            }).after('show',function(){
                $(".discount,.wuliuprice,.nprice").isPrice();
                $("#privilege_select").SelectBox();
                _this.beforeUpdatePop(orderId,type);
                if($("#privilege_select").val() == 1){
                    flag = -1;
                }
            })
            
            $(document).on("click",".js_update_cash",function(){
            	var _this = this;

                update_dialog.before("show",function(){
            		var trigger = $(_this);
                    orderId = trigger.data("orderid");
                    brandsId = trigger.data("brandsid");
                    dealerOrderType = trigger.data("ordertype");
                    type = trigger.data("type");
                    payState = trigger.attr("data-payState")
                    var op = trigger.parents("tr"),
                        info = op.find(".product_info").html(),
                        num = op.find(".product_num").html(),
                        price = op.find(".product_price").html(),
                        brand = op.find(".brand_name").html();
                    var orderbox = op.parents(".orderbox"),
                        adjustFreight = orderbox.find('[name="adjustFreight"]').val(),
                        oldAdjustFreight = adjustFreight;
                        adjustFreight = adjustFreight == "" ? "0.00" : adjustFreight;

                    var oldAdjustPrice = orderbox.find('[name="adjustPrice"]').val(),
                        adjustPrice = Math.abs(orderbox.find('[name="adjustPrice"]').val()).toFixed(2);
                        
                    var data = {};
                  
                    if(type == "price"){
                        data = {
                            type: "price",
                            title: "修改金额",
                            info: info,
                            brand: brand,
                            num: num,
                            price: parseFloat(price).toFixed(2),
                            totalprice: (parseFloat(price)+parseFloat(oldAdjustPrice)+parseFloat(adjustFreight)).toFixed(2),
                            oldAdjustPrice:oldAdjustPrice,
                            adjustPrice:adjustPrice,
                            address:$.trim(orderbox.find('[name="address"]').val()),
                            adjustFreight:adjustFreight,
                            oldAdjustFreight:oldAdjustFreight,
                            dealerOrderType: dealerOrderType
                        }
                    }else if(type == "fare"){
                        data = {
                            type: "fare",
                            title: "修改运费",
                            info: info,
                            brand: brand,
                            num: num,
                            price: parseFloat(price).toFixed(2),
                            discount: (function(){
                            	if(oldAdjustPrice>0)
                            	{
                            		return "";
                            	}else if(oldAdjustPrice==0)
                            	{
                            		return 0;
                            	}
                            	else
                            	{
                            		var str = (parseFloat(price)+parseFloat(oldAdjustPrice))/price * 10; //计算折扣价
                            		return str.toFixed(2);
                            	}
                                /*if(adjustPrice == 0){
                                    return 0;
                                }else{


                                	var str = (parseFloat(price)+parseFloat(oldAdjustPrice))/price * 10; //计算折扣价
                                	alert(str);
                                	if(str < 0){
                                		return 0;
                                	}else{
                                		return str.toFixed(2);
                                	}
                                }*/
                            })(),
                            /*totalprice: (parseFloat(price) - parseFloat(adjustPrice) + parseFloat(adjustFreight)).toFixed(2),*/
                            totalprice: (parseFloat(price)+parseFloat(oldAdjustPrice)+parseFloat(adjustFreight)).toFixed(2),
                            oldAdjustPrice:oldAdjustPrice,
                            adjustFreight: adjustFreight,
                            adjustPrice: adjustPrice,
                            payState:payState,
                            address:$.trim(orderbox.find('[name="address"]').val()),
                            oldAdjustFreight:oldAdjustFreight,
                            dealerOrderType: dealerOrderType
                        }
                    }
                    var html = template.render('update_cash_tpl', data);
                    this.element.html(html);
            	})
            	update_dialog.show();
            })

        });
        //点击对其授信
        /*
        $(document).on("click",".js_credit",function(){
            $(".credit_module").show();
        })

        $(document).on("click",".credit_module .credit_cancel",function(){
            $(".credit_module").hide();
        })
        */
       
        $(document).on("click",".cash_info .free_shipping",function(){
            $(".wuliuprice").val("0");
            $(".wuliuprice").trigger("keyup");
         
            if(_this.handleShippingFn)
            {
            	//_this.handleShippingFn([orderId], update_dialog);
            }
        })

        $(document).on("click",".cash_info .confirm",function(){
             _this.confirmUpdatePop(orderId,type,update_dialog,discount,payState,brandsId);
        })

        $(document).on("click",".cash_info .cancel",function(){
            update_dialog.hide();
        })

        //优惠和加价的切换
        $(document).on("click","#privilege_select",function(){

        	var _this = this;

        	flag = $(this).val() == 1 ? -1: 1;


            if(flag == -1){
                $("#updatePrice .discount").attr("readonly","readonly");
            }else{
                $("#updatePrice .discount").removeAttr("readonly");
            }

        	$(".cash_info .symbol").html(function(){
        		return $(_this).val() == 1 ? "+": "-";
        	})

        	$(".cash_info .symbol").html(function(){
        		return $(_this).val() == 1 ? "+": "-";
        	})

        	$(".cash_info .symbol_text").html(function(){
        		return $(_this).val() == 1 ? "加价": "优惠";
        	})

            var price = parseFloat($(".cash_info .price").html());

        	var total_discount = parseFloat($(".cash_info .total_discount").html());

        	var total_wuliu = parseFloat($(".cash_info .total_wuliu").html());

        	var total_price = $(".cash_info .total_price");

        	total_price.html(parseFloat(price - total_discount*flag + total_wuliu).toFixed(2));

        })

        $(document).on("change","#privilege_select",function(){
            $(".discount").val("");
        })



        //更新优惠值
        $(document).on("keyup keydown",".discount,.wuliuprice,.nprice",function(ev){
            if(ev.keyCode == "13"){
                ev.preventDefault();
            }
            var flag_discount = $(ev.target).hasClass("discount");
            var flag_nprice = $(ev.target).hasClass("nprice");
            var op = $(this).parents("tr");
            var yprice = parseFloat(op.find(".yprice").html());
            var nprice = op.find(".nprice");
            discount = op.find(".discount").val();
            var wuliuprice = op.find(".wuliuprice");
            var total_discount = $(".cash_info .total_discount");
            var price = $(".cash_info .price");
            var total_price = $(".cash_info .total_price");
            var total_wuliu = $(".cash_info .total_wuliu");
            var dis_value = 0;
            total_discount.html("0");
            if(flag_nprice){
                op.find(".discount").val("");
            }
            if(!isNaN($.trim(discount)) && (discount != "") && flag_discount){
            	var num = 0;
            	if(discount != 0){
            		num = yprice*discount/10 - yprice; //计算优惠价
            	}
            	dis_value = Math.abs(num).toFixed(2);
                nprice.val(dis_value);
                total_discount.html(dis_value);
            }
            if(!isNaN(nprice.val()) && nprice.val() != ""){
                var value = parseFloat(nprice.val());
                dis_value = value;
                total_discount.html(value);
            }
            if(discount == "" && nprice.val() == ""){
                dis_value = 0;
                total_discount.html(0);
            }
            if(total_wuliu.html() == ""){
                total_wuliu.html("0");
            }
            if(!isNaN(wuliuprice.val()) && wuliuprice.val() != ""){
                total_wuliu.html(parseFloat(wuliuprice.val()).toFixed(2));
            }
            if(wuliuprice.val() == ""){
            	total_wuliu.html("0");
            }
            price = parseFloat(price.html());
            total_wuliu = parseFloat(total_wuliu.html());
            total_price.html(parseFloat(price - dis_value*flag + total_wuliu).toFixed(2));
        })

    },
    handleRemarkFn: function(current_obj,orderIds,dialog){
        console.log(orderIds);
    },
    handleRemark: function(){

        var _this = this;

        var remark_dialog = null;
        
        var current_obj = null;

        var orderIds = [];

        function hanleRemarkDialog(html){
            remark_dialog.set("content",html);
            remark_dialog.show();
        }

        seajs.use(['popup',"template","dialog"], function(Popup,template,Dialog){

            remark_dialog = new Dialog({
                "width": 500
            });

            $(document).on("click",".remarks",function(){
                var id = $(this).data("id");
                var colorIndex = $(this).data("colorindex");
                var text = $(this).data("text");
                var op = $(this).parents(".orderbox");
                var orderId = op.find('[name="refrenceId"]').val();
                orderIds = [];
                orderIds.push(orderId);
                var data = {
                    id: id,
                    colorIndex: colorIndex,
                    text: text
                };
                //模板渲染
                var html = template.render('dialogRemark', data);
                hanleRemarkDialog(html);
                current_obj = $(this);
            })
             
            
            $(".batch_remark").on("click",function(){
	         	var len = $(".orderbox .js_chk:checked").length;
	            orderIds = [];
	            if(len > 0){
	                 $(".orderbox .js_chk:checked").each(function(){
	                     var op = $(this).parents(".orderbox");
	                     var orderId = op.find('[name="refrenceId"]').val();
	                     orderIds.push(orderId);
	                 })
	                 var data = {
	                     id: "batch"
	                 };
	                 var html = template.render('dialogRemark', data);
	                 hanleRemarkDialog(html);
	             }else{
	                 alert("请先选择交易的订单");
	             }
	         })
            
            //修改
            $(document).on("click",".remark_pop .update_btn",function(){
                var id = $(this).data("id");
                var colorIndex = $(this).data("colorindex");
                var text = $(this).data("text");
                var data = {
                    id: id,
                    colorIndex: colorIndex,
                    text: text
                };
                //模板渲染
                var html = template.render('dialogRemark', data);
                hanleRemarkDialog(html);
            })

            $(document).on("click",".remark_dialog .confirm_btn",function(){
            	_this.handleRemarkFn(current_obj,orderIds,remark_dialog);
	        })
	
	        $(document).on("click",".remark_dialog .cancel_btn",function(){
	            remark_dialog.hide();
	        })

        });

    },
    closeTradeFn: function(orderId, dialog, brandsId, brandsName){
        alert(orderId);
    },
    closeTrade: function(){

        var _this = this;

        var close_dialog = null,
            onOff = 0;

        var orderId = "";
        var brandsId = "";
        var brandsName = "";

        seajs.use(["dialog"],function(Dialog){

            close_dialog = new Dialog({
                //"trigger": ".js_close_order",
                "content": $("#closePanel_tpl").html(),
                "width": 455
            }).after('show', function() {
                if(!onOff){
                    $(".reason-select").SelectBox();
                }
                onOff ++;
            })
        });
        
        $(document).on("click",".js_close_order",function(){
        	
        	var _this = this;
        	close_dialog.before("show",function(){
        		var trigger = $(_this);
                orderId = trigger.data("orderid");
                if(trigger.data("brandsid")){
                    brandsId = trigger.data("brandsid");
                }
                if(trigger.data("brandsname")){
                    brandsName = trigger.data("brandsname");
                }
        	});
        	
        	close_dialog.show();
        	
        })

        $(document).on("click",".closePanel .confirm_btn",function(){
            _this.closeTradeFn(orderId,close_dialog, brandsId, brandsName);
        })

        $(document).on("click",".closePanel .cancel_btn",function(){
            close_dialog.hide();
        })

    },

    sendMessageFn: function(dealerId,name,dialog){
        alert("发送");
        //dialog.hide();
    },

    sendMessage: function(){

        var _this = this;

        var message_dialog = null;

        var name = "";
        var orderId = "";

        seajs.use(["dialog"],function(Dialog){

            message_dialog = new Dialog({
                //trigger: $(".js_message"),
                content: $("#message_tpl").html(),
                width: 300
            })

            
        });
        
        $(document).on("click",".js_message",function(){
        	
        	var _this = this;
        	
        	message_dialog.before("show",function(){
                var trigger = $(_this);
                orderId = trigger.data("dealerid");
                name = trigger.data("name");
            })
            
            message_dialog.show();

        })

        $(document).on("click",".message_dialog .cancel_btn",function(){
            message_dialog.hide();
        })

        $(document).on("click",".message_dialog .confirm_btn",function(){
            _this.sendMessageFn(orderId,name,message_dialog);
        })

    },
    remindDealer: function(){

        $(document).on("click",".js_remind_dealer",function(){
            remind("success","提醒已发送");
        })

    },
    handleShippingFn: function(orderId,dialog){
        console.log(orderId);
        //alert("我要关闭了");
        dialog.hide();
        //alert("----啊,我走了");
    },
    handleShipping: function(){
        var _this = this;
        $(".betch_freeshipping").on("click",function(){
            var len = $(".orderbox .js_chk:checked").length;
            var orderIds = [];
            if(len > 0){
                $(".orderbox .js_chk:checked").each(function(){
                    var op = $(this).parents(".orderbox");
                    var orderId = op.find('[name="refrenceId"]').val();
                    orderIds.push(orderId);
                })
                confirmDialog("是否免邮",function(d){
                    _this.handleShippingFn(orderIds,d);
                },"false");
            }else{
                alert("请先选择交易的订单");
            }
        })
    },

    handleAddressFn: function(dialog,orderid,areano){
        dialog.hide();
    },

    handleAddress: function(){
    	
        var _this = this;

        var updateAddress_dialog = null;

        seajs.use(["dialog"],function(Dialog){
        	
            updateAddress_dialog = new Dialog({
                content: "#updateAddress_tpl",
                width: 720
            })

        });

        $(document).on("click",".js_updateAddress",function(){
        	var _This = this;
        	updateAddress_dialog.before("show",function(){
                var trigger = $(_This);
                orderId = trigger.data("orderid");
            })
        	updateAddress_dialog.after("show",function(){
        		$("#updateAddress_tpl").show();
        	})
        	
        	updateAddress_dialog.show();

            $(document).on("click",".updateAddress_dialog .cancel_btn",function(){
                updateAddress_dialog.hide();
            })

            $(document).on("click",".updateAddress_dialog .confirm_btn",function(){
                _this.handleAddressFn(updateAddress_dialog,orderId);
            })

            var code_country = $(this).data('areano');
			var code_provice = parseInt(code_country / 10000) * 10000;
			var code_city = parseInt(code_country / 100) * 100;
			selProCity($("#addressprovince"), $("#addresscity"),$("#addresscounty"), code_provice, code_city, code_country);
			renderSelect(["#addressprovince","#addresscity","#addresscounty"]);
        })
    }

}
