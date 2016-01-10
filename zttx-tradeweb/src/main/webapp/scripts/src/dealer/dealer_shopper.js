/**
 * Created by 逊 on 2014/5/27.
 */
var myShopper_index = {
    init: function () {
        this.initCheckbox();
        this.initEntryNumber();
        this.initFavorite();
        this.initDelete();
        this.SmellSOA();
        this.SettlementOfAccounts();
        this.initQuickOrder();
        this.initClearCart();
        this.initSubmit();
        this.initTable();
        this.initAccordion();

        //操作栏浮动
        seajs.use(['sticky'], function (sticky) {
            sticky('#settle_accounts');
        });

    },
    initCheckbox: function () {
        var me = this;
        //全选
        checkAll("#CheckAll", '.brand-list .ui-checkbox', function () {
            me.SettlementOfAccounts();
        });

        // 多选删除
        $("#DeleteAll").click(function () {


            var ids = [];
            $(".s-chk:checked").each(function (i, o) {
                ids.push($(o).attr("pid"));
            });

            if (ids.length < 1) {
                remind("error", '至少勾选一个产品');
                return false;
            }

            confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                $.post(G_PATH+"/dealer/shoper/deleteAll", { ids: ids.join(",") }, function (data) {
                    if (data != null) {
                        if (data.code == 111000) {
                            remind("success", "成功移除");
                            window.location.reload();
                        } else {
                            remind("error", data.message);
                        }
                    }
                }, "json");
            });

        });


        $(".b-chk").change(function () {
            var $this = this;
            $(this).parent().next().find(".s-chk").each(function (i, o) {
                o.checked = $this.checked;
            });
            me.SettlementOfAccounts();
        });

        /*$(":checkbox").change(function(){
         myShopper_index.SettlementOfAccounts();
         });*/
    },
    initEntryNumber: function () {
        var me = this;
        //初始化数量输入 :not(:disabled)
        var item_amount = $(".item-amount").cartInit({
            priceTag: ".price",
            totalTag: ".total",
            maxerror: "超过库存值!"   //zxb 0514
        }, function(){
        	$(".js-clearing").data({"issubmit":false});
        },function (data,tr) {
            me._updateSingle(tr);
            me.SettlementOfAccounts();
        });
    },
    initFavorite: function () {
        //添加到收藏
        $(".addColl").click(function () {
            var productId = $(this).attr("pid");
            $.post(G_PATH+"/dealer/favorite/save", { "productId": productId }, function (data) {
                data = $.parseJSON(data);
                if (data != null) {
                    if (data.code == 126000) {
                        remind("success", "产品收藏成功");
                    } else {
                        remind("error", data.message);
                    }
                }
            });
        });
    },
    initDelete: function () {
        /**删除单个*/
        $(".delOne").click(function () {
            var productId = $(this).attr("pid");
            confirmDialog({ title: "提示", content: "确定移除吗" }, function () {
                $.post(G_PATH+"/dealer/shoper/delete", { "id": productId }, function (data) {
                    if (data != null) {
                        if (data.code == 111000) {
                            remind("success", "移除成功");
                            window.location.reload();
                        } else {
                            remind("error", data.message);
                        }
                    }
                }, "json");
            });
        });
    },
    initSubmit: function () {
        //表单提交
        $(".js-clearing").on("click", function (eventObject) {

            var isSubmit = $(".js-clearing").data("issubmit");
            if(!isSubmit)
            { return false; }

            var bol = true;
            if ($(".s-chk:checked").length < 1) {
                remind("error", '至少勾选一个产品');
                bol = false;
                return;
            }
            $("._hidDiv").each(function () {
                if ($(this).parent().find(".s-chk").prop("checked")) {
                    $(this).removeAttr("disabled");
                } else {
                    $(this).attr("disabled", false);
                }
            });
            var products=[];
            $(".s-chk:checked").each(function(){
            	products.push($(this).attr("pid"));
            })
            
           
            
            //判断产品最小购买量
            var pNos = "";
            
            $(".stNumClass").each(function () {
                if ($(this).next().prop("checked")) {
                    var startNum = $(this).val();
                    
                    
                    var total = 0;
                    var pNo = $(this).parents(".goods-item").find(".pNoClass").html();
                    $(this).parents(".goods-item").find(".txt-buyNum").each(function () {
                        total += parseInt($(this).val());
                    });
                    if (startNum != null) {
                        if (total < startNum) {
                            pNos += pNo;
                            pNos += ",";
                        }
                    }
                }
            });

            if (pNos != "") {
                var pos = pNos.lastIndexOf(",");
                pNos = pNos.substring(0, pos);
                var arr = pNos.split(",");
                var len = arr.length;
                var message = "产品 " + pNos + " 没有达到起批量的要求，请检查后再次进行结算";
                if (len > 2) {
                    pNos = arr[0] + "," + arr[1];
                    message = pNos + " 等商品没有达到起批量的要求，请检查后再次进行结算";
                }
                confirmDialog({ 'title': "提示", 'content': message }, function () { }, false, function () { }, true);
                bol = false;
                return;
            }

            if (bol) {
            	$('#_productIds').val(products.join(','));
                $("#myForm").submit();
            }
        });

    },
    SmellSOA: function () {
        $(".item-amount").each(function (i, o) {
            var price = $(o).parent().parent().find('.price').text();
            var total = (Number(price) * $(o).find('.txt-amount').val()).toFixed(2);
            $(o).parent().parent().find('.total').text(total);
        });
    },
    //结算方法
    SettlementOfAccounts: function () {

        var allcount = 0;
        var allamount = 0;
        //数量小计
        var mycount = $(".goods-main-count").each(function (i, o) {

            var countItem = $(o).prev().find(".txt-amount");
            var count = 0;
            for (var i = 0; i < countItem.length; i++) {
                count += Number(countItem[i].value);
            }
            $(o).find(".buycountXj").text(count);

            if ($(o).parent().find('input:checked').size() > 0) {
                allcount += Number($(o).find(".buycountXj").text());
            }
        });

        //金额小计
        var myamount = $(".goods-main-amount").each(function (i, o) {
            var amountItem = $(o).prev().prev().find(".total");
            var amount = 0;
            for (var i = 0; i < amountItem.length; i++) {
                amount += Number(amountItem[i].innerHTML);
            }
            $(o).text(amount.toFixed(2));
            if ($(o).parent().find('input:checked').length > 0) {
                allamount += Number($(o).text());
            }
            //$(o).find(".moneyXj").text(count);
            //allcount += Number($(o).find(".moneyXj").text());
        });

        //总进货数
        //console.log("共" + allcount + "件", "共" + allamount + "元")

        //console.log(allcount);
        $("#AllCount").text(allcount);

        //总金额
        $("#AllAmount").text(allamount.toFixed(2));
    },
    _updateSingle: function (params) {
        var _paramsArry = [];
        $(".js-ischange").each(function(i,o){
            var productId = $(o).find("#productId").val();
            var bigImage = $(o).find("#bigImage").val();
            var price = $(o).find("#price").val();
            var vid = $(o).find("#vid").val();
            var vidSon = $(o).find("#vidSon").val();
            var buyNum = $(o).find("#vidSon").parent().parent().find("#buyNum").val();
            var _params = { "productId": productId, "bigImage": bigImage, "price":parseFloat( price), "vid": vid, "vidSon": vidSon, "buyNum": parseInt(buyNum) };
            _paramsArry.push(_params);
        });
        dialogMask(function(dd){
            $.ajax({type:'POST',url:G_PATH+"/dealer/shoper/updates",
                data:JSON.stringify(_paramsArry),
                contentType:'application/json',
                dataType:'json',
                success:function(data){
                    $(".js-clearing").data({"issubmit":true});
                    $(".js-ischange").removeClass("js-ischange");
                    dd.hide();
                }
            });
        });


        //更新一条明细
//        $.post(G_PATH+"/dealer/shoper/update", _paramsArry, function (data) {
//            if(data!=null){
//             var obj=JSON.parse(data);
//             }
//            $(".js-clearing").data({"issubmit":true});
//            $(".js-ischange").removeClass("js-ischange");
//        });
    },

    initQuickOrder: function () {
        /**快速下单 事件*/
        var monitor = 0;
        $('#key').keyup(function () {
            clearTimeout(monitor);
            var key = $(this).val();
            if (key.length > 0) {
                monitor = setTimeout(function () {
                    loadData(key);
                }, 618);
            }
        }).focus(function(){
            $(this).trigger("keyup"); //0624 zxb add
        });

        function loadData(key) {
            $.post(G_PATH+"/dealer/shopper/quick", { keyWord: key }, function (remote) {
                if (remote.code == 126000) {
                    var data = remote.rows;
                    if (data.length > 0 && key.length > 0) {
                        var result = $(".search-result").html("<ul></ul>").show().hover(null, function () { $(this).hide(); }); //<i class='close'>
                        for (var i in data) {
                            var _id = data[i].id;
                            var _text = data[i].text.replace(new RegExp(key, 'gi'), "<b>" + key + "</b> ");
                            var _isAdd = data[i].isAdd;
                            var li = $("<li>").appendTo($(result).find("ul"));
                            var div = $("<div>").appendTo(li);
                            var a = $(_isAdd ? '<a href="javascript:void(0)" class="disable">已加入进货单</a>' : '<a href="javascript:quickFormSubmit(\'' + _id + '\')">加入进货单</a>').appendTo(div);
                            var span = $("<span>").html(_text).appendTo(div);
                        }
                        $(result).find("div").hover(function () {
                            $(this).toggleClass("on");
                        });
                    }
                    else {
                        //搜索结果框鼠标移出事件
                        $(".search-result").hide();
                    }
                }
            }, "json");
        }
    },
    initClearCart: function () {
        /**清空购物车*/
        $("#clearCart").click(function () {
            confirmDialog({ title: "提示", content: "确定清空吗" }, function () {
                $.post(G_PATH+"/dealer/shoper/deleteAll", { ids: null }, function (data) {
                    if (data != null) {
                        if (data.code == 111000) {
                            remind("success", "成功清除");
                            window.location.reload();
                        } else {
                            remind("error", data.message);
                        }
                    }
                }, "json");

            });
        });
    },
    initTable: function () {
        $(".goods-attribute tr.goods-color:odd").css({ "background-color": "#f9f9f9" });
        $(".goods thead th").css({ "background-color": "#f5f5f5" });
    },
    initAccordion: function () {

        //初始化部分
        var unfold = "<div class='unfold c-h'></div>";
        $(".goods-attribute .attributes").prepend(unfold);//.find(".unfold").first().hide();
        //$(".attributes table").not(":first").find("tr").hide();
        _unfold($(".unfold").first());

        //展开点击触发
        $(document).on("click", '.unfold', function () {
            //展开详细列表
            $(this).hide().next().find("tr").show();
            //收起其他列表
            _unfold(this);

        });

        function _unfold(selector) {
            //var $this = selector ? $(".txt-amount").not($(selector).next().find(".txt-amount")):$(".txt-amount");
            var $this = $(".unfold").not($(selector));

            $($this).each(function (i, o) {
                var tr = $(o).next().find("tr");
                var notnull = $(o).next().find("tr").not(".js-notnull").hide();

                //条数为1的处理
                if (tr.length == 1) {
                    $(o).hide()
                    $(tr).show();
                }
                else if (notnull.length == 0) {
                    //全部等于0的处理
                    $(o).hide();
                }
                else {
                    //部分等于0的处理
                    $(o).html('<a href="javascript:;">未选' + $(o).next().find("tr").not(".js-notnull").length
                    + '条 <i class="iconfont">&#xe60d;</i></a>').show();
                }



            });



            /*$($this).each(function(i,obj){
        
             if(Number($(obj).val())>0)
             {
             var tr =  $(obj).parent().parent().parent().show();
             tr.parent().parent().prev().hide();
             }
             else{
             var $thisTable = $(obj).parentsUntil('table');
             if($($thisTable).find("tr").length>1)
             {
             var tr =  $(obj).parent().parent().parent().hide();
             var unfoldbar = tr.parent().parent().prev();
        
             $(unfoldbar).show();
             unfoldbar.html('<a href="javascript:;">未选'+$(unfoldbar).next().find("tr:hidden").length
             +'条 <i class="iconfont">&#xe60d;</i></a>');
             }
             }
             });*/
        }

    }
};
var quickFormSubmit = function (productId) {
    $('#addlist-form-quick').append($('<input type="hidden">').attr('name', 'products').val(productId)).submit();
};

myShopper_index.init();
