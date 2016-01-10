var myBrankBank = {
    init: function(){
    	this.formOperate();
    	this.cardListOperate();
    	this.cardDialogOperate();
        this.cardOperate();
    },
    cardListOperate:function()
    {
    	//列表效果加载
    	$(".my_card_list").on("mouseenter mouseleave",".item",function(ev){
            if(ev.type == "mouseenter"){
                $(this).addClass("item_hover");
            }else if(ev.type == "mouseleave"){
                $(this).removeClass("item_hover");
            }
        });
    	//事件
    	$(function () {
    		$(".my_card_list li.item").each(function(index,el){
    			//删除
    			$(this).find("a.del").click(function(){
    				var refrenceId = $(this).data("uuid");
    				confirmDialog("是否删除",function(){
    					$.post("/dealer/bank/del","refrenceId="+refrenceId,function(data){
    						if(data.code == 0)
        	        		{
        	        			window.location.reload();
        	        		}
        	        		else
        	        		{
        	        			remind("error","删除失败！");
        	        		}
    					},"json");
    				});
    			});
    			//设置默认
    			$(this).find("a.set").click(function(){
    				var refrenceId = $(this).data("uuid");
    				$.post("/dealer/bank/setCardDefault","refrenceId="+refrenceId,
    						function(data){
    					if(data.code == 0)
    	        		{
    	        			window.location.reload();
    	        		}
    	        		else
    	        		{
    	        			remind("error","设置失败！");
    	        		}
    				},"json");
    			});
    			
    		});
    	});
    },
    cardDialogOperate:function()
    {
    	seajs.use(['dialog'], function(Dialog) {
            var idCardDialog = new Dialog({
                content: "#id_card",
                width: 400,
                zIndex:1000
            });
            $(document).on("click",".js-idcard-cansole",function(){
                idCardDialog.hide();
            });

            var d1 = new Dialog({
                trigger: '#add_card',
                content: "#choose_bank",
                width: 682
            });
            d1.after('show', function() {
                $('#choose_bank').show();
            });
            var d2 = new Dialog({
                content: "#add_form",
                width: 508
            });
            d2.after('show', function() {
            	$("#add_form").show();
                //身份证为空的时候，给提示
                if($(".js-idcard-view").val() == ""){
                    idCardDialog.show();
                }
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
                    remind("error","请至少选择一个银行！");
                }else{
                    d1.hide();
                    d2.show();
                }
            });
        });
    	
    	$(document).on("click",".bank_list li",function(){
            $(".bank_list li").find("input").prop("checked",false);
            $(".bank_list li").find(".bank_img").removeClass("hasChecked");
            $(this).find("input").prop("checked",true);
            $(this).find(".bank_img").addClass("hasChecked");
        });
    },
    
    cardOperate: function(){
    	seajs.use(["tip"],function(Tip){
    		var t = new Tip({
                trigger: $(".support_info strong"),
                content: "目前仅支持：工行、招行、建行、中行、农行、交行、浦发、广发、中信、光大、兴业、民生、平安、杭州银行等18家银行",
                width: 330,
                theme: 'yellow',
                inViewport: true,
                arrowPosition: 11,
                zIndex: 9999
            });
    	});
    },
    formOperate:function()
    {
    	seajs.use(['validator', '$'], function (Validator, $) {
    		
            $(function () {
            	Validator.addRule('userId',
            			/^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}[0-9A-Za-z]{1})?$/,
            					'请输入正确的{{display}}');
            	var sumbitFunction = function(){
            		var _data = $("#cardForm").serialize();
                    $(".bank_list li").each(function(){
                        var flag = $(this).find("input").prop("checked");
                        if(flag == true){
                        	_data += "&bankId=" + $(this).find("input").val();
                        }
                    });
                    $("#submitSaveButton").prop("disabled",true);
                    $.post("/dealer/bank/save",_data,function(data){
                    	
                    	$("#submitSaveButton").removeAttr("disabled");
    					if (data.code == 0) {
    						$("#bankNo").val("");
    						$("#subBank").val("");
    						
    						$("#province").val("");
    						$("#city").val("");
    						$("#county").val("");
    						
    						window.location.reload();
    					}
    					else if(data.code == 3)
   						{	
    						remind("error","银行卡号不能重复添加！");
   						}
    					else
    					{
    						remind("error","保存失败！");
    					}
                    },"json");
                    
            	};
            	Validator.addRule('select',/\d{5,}/,'请选择{{display}}下拉列表');
            	var validator = new Validator({
                    element: '#cardForm',
                    autoSubmit:false,
                    failSilently:true,
                    onFormValidated:function(error,results,element)
                    {
                    	if(!error)
                    	{
                    		sumbitFunction();
                    	}
                    }
                });

                validator.addItem({
                	element: '#province',
                    rule:'select',
                    required: true,
                    display:'省份',
                    errormessageRequired:'请选择省份下拉列表'
                }).addItem({
                	element: '#city',
                    rule:'select',
                    required: true,
                    display:'城市',
                    errormessage:'请选择城市下拉列表'
                }).addItem({
                	element: '#county',
                    rule:'select',
                    required: true,
                    display:'区域',
                    errormessage:'请选择区域下拉列表'
                }).addItem({
                    element: '[name=bankNo]',
                    rule:'maxlength{max:21} number',
                    required: true,
                    display: '银行卡号'
                }).addItem({
                    element: '[name=subBank]',
                    required: true,
                    display: '开户银行'
                });
                
                var setBankCate = function(_cate)
            	{
            		validator.removeItem("[id=userInfo_id] [id=userName_copy]");
                	validator.removeItem("[id=userInfo_id] [id=userId_copy]");
                	validator.removeItem("[id=userInfo_id] [id=userName_pepole]");
                	validator.removeItem("[id=userInfo_id] [id=userId_pepole]");
                	$("#id_bankCate").find("input").prop("checked",false);
                   	$("#userInfo_id").empty();
                   	if(_cate == 1)
                   	{
                   		$("#pepole_bankCate").prop("checked",true);
                   		$("#userInfo_id").append($("#hidden_pepole").html());
                    	validator.addItem({
                            element: '[id=userInfo_id] [id=userName_copy]',
                            required: true,
                            display: '真实姓名'
                        }).addItem({
                            element: '[id=userInfo_id] [id=userId_copy]',
                            rule:'userId',
                            required: true,
                            display: '身份证号'
                        });
                   	}
                   	else
                   	{
                   		$("#copy_bankCate").prop("checked",true);
                   		$("#userInfo_id").append($("#hidden_copy").html());
                    	validator.addItem({
                            element: '[id=userInfo_id] [id=userName_pepole]',
                            required: true,
                            display: '公司名称'
                        }).addItem({
                            element: '[id=userInfo_id] [id=userId_pepole]',
//                            rule:'number maxlength{max:13} minlength{min:13}',
                            required: true,
                            display: '营业执照'
                        });
                   	}
                	
            	};
                
            	setBankCate(1);//初始化类型
            	
            	$(document).on("click", "#id_bankCate label", function () {
            		var _cate = $(this).find("input").val();
            		setBankCate(_cate);
                    //$(this).find("input").prop("checked", true);
            	});
            	
            	
            });
            
        });
    }
    

};
myBrankBank.init();

//城市联动取值 
$('#province').on('change',function (){
		var province = this.options[this.selectedIndex].text;
		$('input[name="provinceName"]').val(province);
		$('input[name="cityName"]').val('');
		$('input[name="areaName"]').val('');
	});
	
	$('#city').on('change',function (){
		var city = this.options[this.selectedIndex].text;
		$('input[name="cityName"]').val(city);
		$('input[name="areaName"]').val('');
	});
 
 $('#county').on('change', function (){
 	var county = this.options[this.selectedIndex].text;
 	$('input[name="areaName"]').val(county);
 });