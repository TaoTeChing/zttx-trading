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
    				var _uuid = $(this).data("uuid");
    				confirmDialog("是否删除",function(){
    					$.post("/brand/bank/delete","uuid="+_uuid,function(data){
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
    				var _uuid = $(this).data("uuid");
    				$.post("/brand/bank/default","uuid="+_uuid,
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
            var d1 = new Dialog({
                //trigger: '#add_card',
                content: "#choose_bank",
                width: 682
            });
            $(".my_card_list").on("click","#add_card",function(){
                d1.show();
                $("input[name=updateId]").val("");
                $("#choose_bank input[name=bank_name]").prop("checked",false);
                $("#add_form #bankNo").val("");
                $("#add_form #subBank").val("");
            });
            $(".my_card_list").on("click",".edit",function(){
                d1.show();
                var _id = $(this).data("uuid");
                $("input[name=updateId]").val(_id);
                $("#choose_bank input[value=0403]").prop("checked",true);
                $("#add_form #bankNo").val("XXXX");
                $("#add_form #subBank").val("json");
                /*$.post(url,data,function(json){
                 if(json.code == 0 ){
                 remind("error","数据返回错误");
                 }
                 $("#choose_bank input[value="+json.XXXX+"]").prop("checked",true);
                 $("#add_form #bankNo").val(json.XXXX);
                 $("#add_form #subBank").val(json.XXXX);
                 });*/
            })
            d1.after('show', function() {
                $('#choose_bank').show();
            });
            var d2 = new Dialog({
                content: "#add_form",
                width: 508
            });
            d2.after('show', function() {
            	$("#add_form").show();
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
                content: "目前仅支持：工行、招行、建行、中行、农行、交行、浦发、广发、中信、光大、兴业、民生、平安、杭州银行等22家银行",
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
    	seajs.use(['validator', '$','ajaxFileUpload'], function (Validator, $) {
    		
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
                    $.post("/brand/bank/save",_data,function(data){
                    	
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
                            if($("#pepole_bankCate").prop("checked") && $("input[name=attachment]").val() != ""){
                                sumbitFunction();
                            }else if($("#pepole_bankCate").prop("checked") && $("input[name=attachment]").val() == ""){
                                remind("error","请上传附件");
                            }
                            if($("#copy_bankCate").prop("checked")){
                                sumbitFunction();
                            }
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
                    element: '[name=bankNo]',
                    rule:'maxlength{max:21} number',
                    required: true,
                    display: '银行卡号',
                    errormessage:'请输入正确的银行卡号'
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
                        $(".js-fujian").show();
                        $("#fujian").attr("disabled",false);
                        $("input[name=attachment]").attr("disabled",false);
                    	validator.addItem({
                            element: '[id=userInfo_id] [id=userName_copy]',
                            required: true,
                            display: '真实姓名'
                        });/*.addItem({
                            element: '[id=userInfo_id] [id=userId_copy]',
                            rule:'userId',
                            required: true,
                            display: '身份证号'
                        });*/
                   	}
                   	else
                   	{
                   		$("#copy_bankCate").prop("checked",true);
                   		$("#userInfo_id").append($("#hidden_copy").html());
                        $(".js-fujian").hide();
                        $("#fujian").attr("disabled",true);
                        $("input[name=attachment]").attr("disabled",true);
                    	validator.addItem({
                            element: '[id=userInfo_id] [id=userName_pepole]',
                            required: true,
                            display: '公司名称'
                        }).addItem({
                            element: '[id=userInfo_id] [id=userId_pepole]',
                            //rule:'number maxlength{max:13} minlength{min:13}',
                            required: true,
                            display: '营业执照'
                        });
                   	}
                	
            	};
                
            	setBankCate(1);//初始化类型

            	$(document).on("click", "#id_bankCate .js-state", function () {
            		var _cate = $(this).find("input").val();
            		setBankCate(_cate);
                    //$(this).find("input").prop("checked", true);
            	});
                $("#fujian").change(function(){
                    uploadImage("fujian");
                });
                function uploadImage(uploadId){
                    $.ajaxFileUpload({
                        url: '/common/showImg',
                        secureuri: false,
                        fileElementId: uploadId,
                        dataType: 'json',
                        success: function(data){
                            //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                            $('#'+uploadId).bind('change', function(){
                                uploadImage(uploadId);
                            });
                            if(data.code == zttx.SUCCESS){
                                $("input[name=attachment]").val(data.message);
                                $(".js-upload-name").val(data.object);
                            }else{
                                remind("error",data.message);
                            }
                        }
                    });
                }
            });
            
        });
    }
    

};
myBrankBank.init();