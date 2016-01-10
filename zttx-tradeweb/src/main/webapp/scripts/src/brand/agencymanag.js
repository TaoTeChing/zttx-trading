//同意申请
function agreeApply(){
    /*$(document).on("click",".js_agree",function(){
        remind("success","已同意申请，您可以在已合作的经销商列表中查看");
    });*/
}
//拒绝申请
function rejectApply(){
    var target = null;
    seajs.use(['dialog'], function(Dialog) {
        var invitedio = new Dialog({
            trigger: '.js_reject',
            effect: 'fade',
            hasMask:false,
            content: $(".js-reject-show"),
            width:385
        }).after("show",function(){
            $("#auditMark").val("");
        	target = this.activeTrigger;
            if($.isFunction(showReject)){
            	showReject(target);
            }
        })
        $(".js-reject-show").on("click",".confirm_btn",function(){
        	if($.isFunction(rejectJoin)){
        		rejectJoin(invitedio);
            }
        })
        $(".js-reject-show").on("click",".cancel_btn",function(){
            invitedio.hide();
        })
    });
}
//邀请加入
function invitationJoin(){
    var target = null;
    var invitedio = null;
    seajs.use(['dialog'], function(Dialog) {
        invitedio = new Dialog({//邀请加盟按钮
            trigger: '.js-invite-join',
            effect: 'fade',
            hasMask: false,
            content: $(".js-invite-show"),
            width: 385
        }).after("show",function(){
                $("#inviteText").val("");
                target = this.activeTrigger;
                if($.isFunction(inviteJoin)){
                    inviteJoin(target,invitedio);
                }
            }).before("show",function(){
                $("#brandsId").val("");
                $("#brandsId_div span").html("请选择品牌");
                $(".joindealer").hide();
                $("#joinDiv").show();
            });
        $(".js-invite-show").on("click", ".cancel_btn", function () {
            invitedio.hide();
        })
    });
}


//申请中的经销商
var agencyApply = {
    init:function(){
        //this.tabSmenu();
        this.handleApply(); // 操作申请的经销商
        invitationJoin(); //申请加盟
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.js_agency_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_agency_con > div").eq(index).show().siblings().hide();
        });
    },
    handleApply: function(){
        agreeApply();
        rejectApply();
    }
}

//经销商管理详细信息
var agencyInfo = {
    init:function(){
        this.tabSmenu();
        this.dialogSea();
        agreeApply();
        rejectApply();
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.js_agency_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_agency_con > div").eq(index).show().siblings().hide();
        });
    },
    dialogSea:function(){
        seajs.use(['dialog'], function(Dialog) {
            var d = new Dialog({//联系方式区域
                //trigger: '#trigger',
                effect: 'fade',
                hasMask:false,
                content: $(".ui-tanchuang"),
                align: {
                    baseElement: '#trigger',
                    baseXY: [0, 0],
                    selfXY: [0, 0]
                },
                width:325,
                height:200
            });

            $(document).on("click","#trigger",function(){

                if($(".js-notip").prop("checked")){
                    inviteActive();
                    return false;
                }else{//未选中不再提示
                    d.show();
                }

            });

            $(document).on("click",".ui-tanchuang .js-confirm-dealerid",function(){
                inviteActive();
            });

            function inviteActive(){
                var dealerId=$.trim($(".js-confirm-dealerid").data("dealerid"));
                var productCate = $("input[name='setdef1']:checked").length;
                $.post("/brand/dealer/viewContact",{dealerId:dealerId,isShow:productCate},function (data){
                    if(data.code==zttx.SUCCESS){
                        d.hide();
                        $(".contact-warring").hide();
                        inviteJoinCallback(data.object);
                    }else{
                        remind("error",data.message);
                    }
                },'json');
            }

            $(document).on("click",".ui-tanchuang .cancel_btn",function(){
                d.hide();
            })

            invitationJoin();

        });
    }
}

//平台推荐的经销商
var agencyRecommend = {
    init:function(){
        this.recommendClick();
    },
    recommendClick:function(){
        var Rstate = ".agency-recommend-td-state";
        var editline = ".apply-editline";
        $("td").on("click",Rstate,function(){
            var Remove = $("tbody tr td").find(".agency-recommend-td-state");
            Remove.find("div.apply-editline").remove();//执行添加之前先移除
            $(this).append('<div class="apply-editline" style=""><a>点击操作</a><a class="not-state">邀请洽谈</a><a class="not-state">放弃</a></div>');
            return false;
        });
        $(Rstate).mouseleave(function(){
            $(editline).remove();
        })
    }
}

//已合作的经销商
var agencyteamed = {
    init:function(){
        //this.tabSmenu();
        this.seajsuseData();
        this.termination();//点击终止合作
        //this.selpplevel();//品牌-经销商等级下拉框设置
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.js_agency_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_agency_con > div").eq(index).show().siblings().hide();
        });
    },
    seajsuseData:function(){
        baseCalendar("#date",{dateFmt: 'yyyy年MM月dd日 HH时mm分ss秒'})
        rangeCalendar("start-cal","end-cal");
    },
    termination:function(){//终止合作操作
        seajs.use(['dialog', 'template'],function(Dialog, Template){
        	var dialog = new Dialog({//终止合作操作
        		content: $(".js-ending-teamboxshow"),
        		width:285,
        		/*align: {
                    baseElement: '.js-ending-team',
                    baseXY: [-189, 30],
                    selfXY: [0, 0]
                },*/
        		hasMask:false
        	}).after("show",function(){
               $("#endMark").val("");
               target = this.activeTrigger;
            });
        	var dialog_b = new Dialog({
                content: $("#beforeEndTpl").html(),
                width: 300
            });
        	function beforeDialogB(dealerJoinId,max){
	            var endObj = {};
	            endObj.id = dealerJoinId;
	            endObj.max = max;
	            var htm = template.render("beforeEndTpl",endObj);
	            dialog_b.element.html(htm);
	            dialog_b.show();
	            baseFormValidator({
	                selector: "#beforeEndForm",
	                isAjax: true,
	                beforeSubmitFn:function(){
	                    $.ajax({
	                        url: '/brand/payApi/depositTransfer',
	                        method: 'post',
	                        data: $("#beforeEndForm").serialize(),
	                        dataType: 'json',
	                        success: function (data) {
	                            if (data.code === zttx.SUCCESS) {
	                            	dialog_b.hide();
	                            	//dialog.show();
	                            	window.location.reload();
	                            }else{
	                                remind("error",data.message);
	                                dialog_b.hide();
	                            }
	                        }
	                    });
	                }
	            });
        	}
            //终止合作后期增加
        	$(document).on("click",".js-ending-team",function(){//触发方式
        		$.ajax({
    	    		cache : true,
    	    		type:'POST',
    	    		url:"/brand/join/getDealerJoin",
    	    		data:{dealerJoinId:$(this).attr("dealerJoinId")},
    	    		dataType:'json',
    	    		async : false,
    				error : function(request) {
    					remind("error","访问失败");
    				},
    	    		success:function(data){
    	    			if (data.code === zttx.SUCCESS) {
//    	    				confirmDialog("确定要终止合同吗？", function(){
//    	                	});
    	    				if(data.object.paidAmount){
    	    					if(data.object.paidAmount >0){
    	    						beforeDialogB(data.object.refrenceId,data.object.paidAmount);
    	    						dialog_b.show();
    	    					}else{
    	    						remind("error","终端商已缴纳的押金未全部结算，无法终止合作");
    	    					}
    	    				}else{
    	    					dialog.show();
    	    				}
    	    			}else{
    	    				remind("error",data.message);
    	    			}
    	    		}
    			});
        	});

        	$(document).on("click",".js-tosure-btn",function(){//确认
        		//alert("确认！");
        		//dialog.hide();
        		if($.isFunction(stopTemd)){
                    stopTemd(target,dialog);
                }
        	});
        	$(document).on("click",".js-tocansole-btn",function(){//取消
        		dialog.hide();
        	});
            $(document).on("click",".js-beforeEndcansole-btn",function(){//取消
                dialog_b.hide();
            });
        	
        	
        	var leaveMessage = new Dialog({//留言
                //trigger: '.js-leave-message',
        		content: $(".js-leave-messagebox"),
        		width:385,
        		hasMask:false
        	}).after("show",function(){
               target = this.activeTrigger;
            });

        	$(document).on("click",".js-leave-message",function(){//触发方式
        		leaveMessage.show();
        	});
        	$(document).on("click",".js-lemsgsure-btn",function(){//确认
                if($.isFunction(leaMsg)){
                    leaMsg(target,leaveMessage);
                }
        	});
        	$(document).on("click",".js-lemsgsurecansole-btn",function(){//取消
        		leaveMessage.hide();
        	});
        	
        	var tojoin = new Dialog({//诚邀加盟
                //trigger: '.js-leave-message',
        		content: $(".js-invite-show"),
        		width:385,
        		hasMask:false
        	}).after("show",function(){
               target = this.activeTrigger;
            });

        	$(document).on("click",".js-tojoin",function(){//触发方式
        		tojoin.show();
        	});
        	$(document).on("click",".js-confirm-btn",function(){//确认
                if($.isFunction(join)){
                    join(target,tojoin);
                }
        	});
        	$(document).on("click",".js-cancel-btn",function(){//取消
        		tojoin.hide();
        	});
        });
    },
    selpplevel:function(){//品牌-经销商等级
    	
    	$(".js-setcom-tobe").attr("disabled",true);//初始设置禁用
    	
    	$(document).ready(function(){
    		$("#Ateamed-form-class_div").css("background","#f0f0f0");//设置初始禁用背景色
    	})
    	
    	$(".js-setcom-as").click(function(){
    		
    		if($(".js-setcom-as option:selected").text() == "全部品牌"){
    			$(".js-setcom-tobe").attr("disabled",true);
    			$("#Ateamed-form-class_div").css("background","#f0f0f0");
    		}else{
    			$(".js-setcom-tobe").attr("disabled",false);
    			$("#Ateamed-form-class_div").css("background","#fff");
    		}
    		
    	})
    	
    }
}
//已合作的经销商info
var agencyteamedinfo = {
    init:function(){
        //this.tabSmenu();
        this.infosetlevelTab();
        this.seajsdialog();//留言弹窗
    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.js_teamedinfo_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_teamedinfo_con > div").eq(index).show().siblings().hide();
        });
    },
    infosetlevelTab:function(){
        /*var $divli =$("div.infosetlevel-menu ul li");
        $divli.click(function(){
            $divli.find(".arrow").hide();
            $(this).addClass("current").siblings().removeClass("current");
            var index =  $divli.index(this);
            $("div.infosetlevel-con > .infosetlevel-con-box").eq(index).show().siblings().hide();
            $divli.find(".arrow").eq(index).show().siblings().hide();
        });*/
        var $divli =$("div.infosetlevel-menu ul li");
        $divli.click(function(){
        	$divli.find(".arrow").hide();
            $(this).addClass("current").siblings().removeClass("current");
            var index =  $divli.index(this);
            $divli.find(".arrow").eq(index).show().siblings().hide();
            if($.isFunction(selectProCate)){
                selectProCate(index);
            }
        });
    },
    seajsdialog:function(){
    	seajs.use(['dialog'],function(Dialog){
        	var leaveMessage = new Dialog({//留言
        		content: $(".js-leave-messagebox"),
        		width:385,
        		hasMask:false
        	}).after("show",function(){
               target = this.activeTrigger;
            });
            
        	$(document).on("click",".js-leave-message",function(){//触发方式
        		leaveMessage.show();
        	});
        	$(document).on("click",".js-lemsgsure-btn",function(){//确认
                if($.isFunction(leaMsg)){
                    leaMsg(target,leaveMessage);
                }
        	});
        	$(document).on("click",".js-lemsgsurecansole-btn",function(){//取消
        		leaveMessage.hide();
        	});
        	
        });
    }
};

//经销商等级管理
var grademanage = {
    init:function(){
        //this.tabSmenu();
        this.setlevelTab();
        this.setlevel();//设置等级
    },
    setLevevlFn:function(obj){

    },
    tabSmenu:function(){//选项表切换
        var $div_li =$("div.js_grademanage_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected").siblings().removeClass("selected");
            var index =  $div_li.index(this);
            $("div.js_grademanage_con > div").eq(index).show().siblings().hide();
        });
    },
    setlevelTab:function(){
        var $divli =$("div.setlevel-menu ul li");
        $divli.click(function(){
            $divli.find(".arrow").hide();
            $(this).addClass("current").siblings().removeClass("current");
            var index =  $divli.index(this);
            $("div.setlevel-con > .setlevel-con-box").eq(index).show().siblings().hide();
            $divli.find(".arrow").eq(index).show().siblings().hide();
        });
    },
    setlevel:function(){//设置等级
        var _this = this;
        seajs.use(['popup'],function(Popup){
        	var seltosetlelpop = new Popup({//批量设置等级
    			trigger: '.js-seltosetlel-btn',
                element: '.js-seltosetlel-showbox',
                align: {
                    baseXY: [-10, 26]
                }
    		});
            $(".js-seltosetlel-btn").hover(function(){
                $(".js-seltosetlel-showbox").find(".ui-checkbox").prop("checked",false);
            });
        	//批量设置等级-确定
        	$(".js-seltosetlel-showbox").on("click",".js-fn-sure",function(){
        		seltosetlelpop.hide();
        	});
        	//批量设置等级-取消
        	$(".js-seltosetlel-showbox").on("click",".js-fn-cancel",function(){
        		seltosetlelpop.hide();
        	});
        	
    		var setlevelpop = new Popup({//设置等级
    			trigger: '.js-setlevel-btn',
                element: '.js-setlevel-showbox',
                align: {
                    baseXY: [-25, 25]
                }
    		}).after("show",function(){
                 _this.setLevevlFn(this.activeTrigger);
            })
    	})
    }
}
var credit = {
	init:function(){
		this.haveallchk();//全选
        this.unitecredit();//授信pop提示框
        this.seajsuseData();//时间选择
   },
	haveallchk:function(){
        $(".havecheckall").click(function(){//已授信的全选
            $('.haveitemchk:checkbox').prop("checked", this.checked );
        });

        $(".nocheckall").click(function(){//已授信的全选
            $('.noitemchk:checkbox').prop("checked", this.checked );
        });
   },
   unitecredit:function(){
   		seajs.use(['popup'],function(Popup){
        	var seltosetlelpop = new Popup({//批量设置等级
    			trigger: '.js-unitecredit-btn',
                element: '.js-unitecredit-showbox',
                width:157,
                align: {
                    baseXY: [-10, 26]
                }
    		})
        	$(".js-unitecredit-showbox").on("click",".js-setNum-sure",function(){//确定
	    		var val = $(this).parents(".js-unitecredit-showbox").find(".js-setnumber").val();//获取pop框内input的value
	    		console.log(val);
	    		$(".agency-teamed-table").find(".js-toboset-number").val(val);
	    	})
	    	$(".js-unitecredit-showbox").on("click",".js-setNum-cancel",function(){//取消
	    		seltosetlelpop.hide();
	    	})
    	})
   },
   seajsuseData:function(){
       baseCalendar("#date",{dateFmt: 'yyyy年MM月dd日 HH时mm分ss秒'})
       rangeCalendar("start-cal","end-cal");
    }
}
