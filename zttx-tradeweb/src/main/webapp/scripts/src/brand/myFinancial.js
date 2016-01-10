var myFinancial = {
    init: function(){
        this.showTip(); //显示冻结余额的提示
        this.showCalendar();
        this.cardOperate();
    },
    showTip: function(){
        seajs.use(["tip"],function(Tip){
            var t = new Tip({
                trigger: $(".freeze_cash"),
                width: 250,
                theme: 'yellow',
                inViewport: true,
                arrowPosition: 11
            });
            t.before('show', function() {
            	var id_amount = $("#id_amount").val();
            	var content = "暂无提现处理…";
            	if(parseFloat(id_amount)>0){
            		content="您的<span id='id_amount_change'>"+id_amount + "</span>元提现金额申请正在处理中…";
            	}
            	this.set('content', content);
            });
        });
    },
    showCalendar: function(){
       /* if($("#start-cal").length == 0){
            return;
        }
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
        })*/
        rangeCalendar('start-cal','end-cal')
    },
    
    cardOperate: function(){
    	
    	$(".my_card_list").on("mouseenter mouseleave",".item",function(ev){
            if(ev.type == "mouseenter"){
                $(this).addClass("item_hover");
            }else if(ev.type == "mouseleave"){
                $(this).removeClass("item_hover");
            }
        })
        
        var onOff = true;
    	
    	//
    	$(".hidedn_item").hide();

        //显示全部
        $(".toggle").on("click",function(ev){
        	
        	

            if(onOff){
                //显示全部
                $(this).find(".js_show_more").hide();
                $(this).find(".js_hide_more").show();
                $(".hidedn_item").show();
                
                
            }else{
                //隐藏部分
                $(this).find(".js_show_more").show();
                $(this).find(".js_hide_more").hide();
                $(".hidedn_item").hide();
                
            }

            onOff = !onOff;

        })

        
        seajs.use(['dialog',"tip"], function(Dialog,Tip) {
            var d1 = new Dialog({
                trigger: '#add_card',
                content: "#choose_bank",
                width: 682
            });
            d1.after('show', function() {
                $('#choose_bank').show();
            });
            /*
             *20140419 弹出层多了一层，先强制清除[冯唐路]
             * */
            $(document).on("click",".ui-dialog-close",function(){
            	$(".ui-mask").hide();
            	$(".ui-dialog").hide();
            })
            /*结束*/
            var d2 = new Dialog({
                content: "#add_form",
                width: 508
            });
            
            var t = new Tip({
                trigger: $(".support_info strong"),
                content: "目前仅支持：工行、招行、建行、中行、农行、交行、浦发、广发、中信、光大、兴业、民生、平安、杭州银行等18家银行",
                width: 330,
                theme: 'yellow',
                inViewport: true,
                arrowPosition: 11,
                zIndex: 9999
            });

            $(document).on("click","#next_step",function(){
                var num = 0;
                $(".bank_list li").each(function(){
                    var flag = $(this).find("input").prop("checked");
                    if(flag == true){
                        num++;
                    }
                });
                if(num == 0){
//                    alert("请至少选择一个银行");
                }else{
                    d1.hide();
                    d2.show();
                    $("#add_form").show();
                }
            })

        });
        
        $(document).on("click",".bank_list li",function(){
            $(".bank_list li").find("input").prop("checked",false);
            $(".bank_list li").find(".bank_img").removeClass("hasChecked");
            $(this).find("input").prop("checked",true);
            $(this).find(".bank_img").addClass("hasChecked");
        })

    }

}
