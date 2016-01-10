var order_index = {
        init:function(){
            //this.initSticky();
            this.initPagination();
            this.initDialog();
            this.initPopup();
            this.initCheckbox();


            // 选择高亮
            var orderType = $('select[name="orderType"]').val();
            var orderStatus = $('select[name="orderStatus"]').val();
            var $lis = $('li.js-order-status');
            if (orderType == '' && orderStatus == '') {
                $lis.filter('[data-status="all"]').addClass('on');
            } else if (orderStatus != '') {
                $lis.filter('[data-status="' + orderStatus + '"]').addClass('on');
            } else if (orderType != '' && orderStatus == '') {
                $lis.filter('[data-type="' + orderType + '"]').addClass('on');
            }

            //提醒品牌商修改运费
            $("[data-remind]").click(function () {
                $.post("/dealer/order/freight/notice",{brandId:$(this).data('brandid'),orderId:$(this).data('orderid')},function(data){
                    if(data.code==126000){
                        remind('success', "运费修改已提醒!");
                    }else{
                        remind('error', "运费提醒失败!");
                    }
                },"json");

            });

        },
        initSticky:function(){

            seajs.use(["sticky"], function (sticky) {
                sticky(".main>.main-left", 0);
            });
        },
        initCheckbox:function(){
            //全选操作
            checkAll('#checkAll', '.order-list .ui-checkbox');
        },
        initPagination:function(){
            seajs.use(["$", "pagination"], function ($, Pagination) {
                new Pagination({
                    url: '',
                    elem: '#pagination',
                    total: totalPage,
                    currentPage: currentPage,
                    pageClick: function (page) {
                        $('#search-form').append($('<input name="currentPage">').val(page)).submit();
                        window.event.returnValue = false;
                    }
                });
            });
        },
        initPopup:function(){
            seajs.use(['template','tip'], function (template,Tip) {
        	 template.helper('$trimLongString', function (string,num) {
                 return trimLongString(string,num);
             });
              template.helper("formatNumber",function(data){
                  if(isNaN(data)){
                      return data;
                  }else{
                      return parseFloat(data).toFixed(2);
                  }
              });
            //单独产品鼠标经过
            var Tip = new Tip({
                trigger: ".order-list .product a",
                element: "#detailPanel",
                //arrowPosition:6,
                inViewport:true,
                //delay: 200//,
                align:{
                    baseXY:[100,-70]
                }
            }).after('show', function (a) {
                    var $this = a.activeTrigger[0];
                    var $element = a.element[0];
                    var _data = $($this).data("detail");
                    if (!_data) {
                        /*if ($this.attr('loading') != '') {
                         $this.attr('loading', 'true');*/
                        var id =  $($this).data('product-id');
                        var ptitle =  $($this).data('ptitle');
                        var orderId =  $($this).data('order-refrenceid');
                        $.ajax({
                            url:'/dealer/order/attribute.json?productId=' + id+'&orderId='+orderId,
                            method:"get",
                            cache:false,
                            success:function (data, status, jqXHR) {

                            if (data.code === zttx.SUCCESS) {
                                data.ptitle = ptitle;
                                data.orderId=orderId;
                                $($this).data({ "detail": data });
                                $($element).html(template.render('detail-template',data));
                               
                            }
                        }});
                        //}

                    }
                    else
                    {
                        $($element).html(template.render('detail-template', _data));
                    }
                    //$this.next('.detailPanel').show();
                });
            });
        },
        initDialog:function(){
            seajs.use(['dialog'], function (Dialog) {
                var $complainForm = $('#complain-form');
                $complainForm.submit(function (event) {
                    event.preventDefault(); // 阻止表单默认提交;
                    var id = $complainForm.find('input[name="orderId"]').val();
                    var complaintName = $('#complaintname_id').val();
                    if(complaintName!=null&&complaintName.length>128){
                        remind('error', "输入的内容太长!");
                        return;
                    }
                    $.post('/dealer/dealerComplaint/save', $complainForm.serialize(), function (data, status) {
                        if (data.code === zttx.SUCCESS) {
                            remind('success', '投诉已提交');
                            $complainForm[0].reset();
                            var html = '';
                            switch (data.object.state) {
                                case 0:
                                    html = '<a href="/dealer/dealerComplaint/process/' + data.object.dealerComplaint.refrenceId + '" class="link"><i class="icon i-clock"></i>已递交投诉，等待处理</a>';
                                    break;
                                case 1:
                                    html = '<a href="/dealer/dealerComplaint/process/' + data.object.dealerComplaint.refrenceId + '" class="link"><i class="icon i-clock"></i>已递交投诉，等待处理</a>';
                                    break;
                                case 2:
                                    html = '<a href="/dealer/dealerComplaint/process/' + data.object.dealerComplaint.refrenceId + '" class="link"><i class="icon i-clock"></i>投诉处理完成</a>'
                                    break;
                                case 3:
                                    html = '<a href="/dealer/dealerComplaint/process/' + data.object.dealerComplaint.refrenceId + '" class="link"><i class="icon i-clock"></i>终端商撤销投诉</a>'
                                    break;
                                default:
                                    break;
                            }
                            $('ul.handle').find('a').filter('[data-complaint-id=' + id + ']').replaceWith(html);
                        } else {
                            remind('error', data.message);
                        }
                        d1.hide();
                    }, 'json');
                });
                //投诉
                var d1Trigger = {};
                var d1 = new Dialog({
                    content: "#complaintPanel",
                    hasMask: false,
                    width: 570
                }).after('show', function (o) {
                        o.activeTrigger = d1Trigger;
                        /*var _left = $(o.activeTrigger).offset().left - $(o.element).outerWidth() + $(o.activeTrigger).outerWidth();
                        var _top = $(o.activeTrigger).offset().top + $(o.activeTrigger).outerHeight() + 10;
                        var btns = $(o.element).css({ left: _left, top: _top }).find('.complaint-operate .ui-button');*/
                        var btns = $(o.element).find('.complaint-operate .ui-button');
                        $(btns).eq(1).unbind('click').click(function () {
                            d1.hide();
                        });
                        // 获得订单ID
                        var $trigger = $(o.activeTrigger);
                        $complainForm.find('input[name="orderId"]').val($trigger.data('complaint-id'));
                        $complainForm.find('#serial').text('' + $trigger.data('serial'));
                        $complainForm.find('#brand-name').text('' + $trigger.data('brand-name'));
                    });

                $(document).on("click", "a[data-complaint-id]", function () {
                    d1Trigger = this;
                    d1.show();
                });
                var $closeForm = $('#close-form');
                $closeForm.submit(function (event) {
                    event.preventDefault() // 阻止表单默认提交;
                    var id = $closeForm.find('input[name="orderId"]').val();
                    var brandName = $closeForm.find('input[name="brandName"]').val();
                    var number = $closeForm.find('input[name="number"]').val();
                    $.post('/dealer/dealerOrder/close', $closeForm.serialize(), function (data, status) {
                        if (data.code === zttx.SUCCESS) {
                            remind('success', '订单已关闭');
                            $closeForm[0].reset();
                            var complain='<li><a class="link" data-brand-name="'+brandName+'" data-serial="'+number+'" data-complaint-id="'+id+'" href="javascript:;" tabindex="-1">投诉</a></li>';
                            $('ul.handle').filter('[data-id=' + id + ']').html(complain+'<li><span class="c-hh">交易关闭</span></li><li><a href="/dealer/dealerOrder/orderDetail/' + id + '" target="_blank" class="link">查看订单详情</a></li>');
                        } else {
                            remind('error', data.message);
                        }
                        d3.hide();
                    }, 'json');
                });
                //关闭交易
                var d3 = new Dialog({
                    trigger: '[data-close-id]',
                    content: "#closePanel",
                    width: 300,
                    hasMask: false
                }).after('show', function (o) {
                        var _left = $(o.activeTrigger).offset().left - $(o.element).outerWidth() + $(o.activeTrigger).outerWidth();
                        var _top = $(o.activeTrigger).offset().top + $(o.activeTrigger).outerHeight() + 10;
                        var btns = $(o.element).css({ left: _left, top: _top }).find('.close-operate .ui-button');
                        $closeForm.find('input[name="orderId"]').val($(o.activeTrigger).data('close-id'));
                        $closeForm.find('input[name="brandName"]').val($(o.activeTrigger).data('close-brandname'));
                        $closeForm.find('input[name="number"]').val($(o.activeTrigger).data('close-number'));
                        $(btns).eq(1).click(function () {
                            d3.hide();
                        });
                    });

                //支付欠款
                var d4 = new Dialog({
                    trigger: '.fun-goon-pay',
                    content: "#payArrears",
                    width: 415,
                    hasMask: false
                }).after('show', function (o) {

                    var canPay = parseFloat($(o.activeTrigger).data('order-rest'));

                	$('#distriOrderAmountsId').val($(o.activeTrigger).data('order-adjustfreight'));
                	$('#distriOrderAmountsId').data({
                        "max":canPay
                    }).attr({
                        "data-price":$(o.activeTrigger).data('order-adjustfreight')
                    });

                	$('#dlg_payment_rest').html(canPay);
                	$('#distriOrderIds').val($(o.activeTrigger).data('order-id'));
                        $(".js-pay-close").click(function(){
                            d4.hide();
                        })
                });
                //支付欠款的表单验证

                if($("#payArrearsFor").length >0) {
                    baseFormValidator({
                        //isAjax:true,
                        selector: "#payArrearsForm",
                        addElemFn: function (Core, Validator) {
                            Validator.addRule('iPrice', function (a) {
                                var inputPrice = $(".self-ui-input").val();

                                if (isNaN(inputPrice)) {
                                    return false;
                                } else if (parseFloat(inputPrice) <= 0) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }, '请输入正确的{{display}}');

                            Validator.addRule('minFare', function (a) {
                                var inputPrice = parseFloat($(".self-ui-input").val());
                                var minPrice = parseFloat($(".self-ui-input").attr("data-price"));

                                if (inputPrice < minPrice) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }, '{{display}}必须大于或等于运费');

                            Core.addItem({
                                element: 'input[name=distriOrderAmounts]',
                                rule: "min{target: 'input[name=distriOrderAmounts]', min: '0.01'} iPrice minFare",
                                required: true,
                                display: "支付金额"
                            });

                        }
                    });
                }
            });
        },
        copyOrderIntoShopper:function(orderId){
        	$.post("/dealer/dealerOrder/copyOrder/"+orderId,function(data){
        		if(data.code==126000){
        			remind("success",data.message);
        		}else{
        			remind("error",data.message);
        		}
        	},"json");
        }
}
