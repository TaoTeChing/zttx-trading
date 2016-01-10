//基本资料
var Basic_Information = {
    init: function () {
        var me = this;
        me.initSticky();
        me.initSMSValidator();
        me.initValidator();
        me.initSubmit();
        me.initArea();
        baseCalendar("#foundtime_id");
        $('input[name="photo"]').bind('change', function () { me.uploadImage($(this).attr('id')); });
    },
    initSticky: function () {
        seajs.use(["sticky"], function (sticky) {
            sticky(".main>.main-left", 0);
        });
    },
    sendMail: function () {
        var userMail = $.trim($("#From_BaseInfo input[name='userMail']").val());
        if (userMail == '') {

            remind("error", "邮箱不能为空！");
            return;
        }
        var reg = /^([A-Za-z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!reg.exec(userMail)) {
            remind("error", "邮箱格式不正确！");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/common/emailValidate/create",
            data: { userCate: 1, emailAddr: userMail },
            dataType: "json",
            success: function (json) {
                if (json.code == zttx.SUCCESS) {
                    remind("success", "认证邮件发送成功！");
                    $("#sendMail").hide();
                } else {
                    remind("error", json.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });



        //TODO 调用发邮件的接口


        // 倒计时：	remind("success","邮件已经发送，请到指定的邮箱中点击认证");
        //shoTime($('#sendMail'), 60,"sendMail()", "马上认证");


    },
    getPhoneCaptcha: function () {
        var me = this;
        /** 发送手机验证码 */
        var mobileInput = $("#formMo input[name='userMobile']");
        var userMobile = $.trim(mobileInput.val());
        var reg = /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/;
        if (!reg.exec(userMobile)) {
            remind("error", "手机格式不正确！");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/common/phoneVerify",
            data: { userMobile: userMobile, userType: 1 },
            dataType: "json",
            success: function (json) {
                if (json.code == 0) {
                    var getCaptchaBtn = $('#yanzm');
                    me.shoTime(getCaptchaBtn, 60, "getPhoneCaptcha()", "点击获取验证码");
                } else {
                    remind("error", "获取验证码失败！");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    },
    shoTime: function (o, wait, functionName, mesg) {
        var me = this;
        if (wait == 0) {
            o.attr("href", "javascript:" + functionName + ";");
            o.text(mesg);
        } else {

            o.attr("href", "javascript:;");
            o.text("重新发送(" + wait + ")");
            wait--;
            setTimeout(function () {
                    me.shoTime(o, wait, functionName, mesg);
            },
                1000);
        }
    },
    initSMSValidator: function () {
        //短信后台校验
        $('#duanxin').on('click', function () {
            var mobileInput = $("#formMo input[name='userMobile']");
            var userMobile = $.trim(mobileInput.val());
            var reg = /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/;
            if (!reg.exec(userMobile)) {
                remind("error", "手机格式不正确！");
                return;
            }
            var verifyCode = $("#formMo input[name='verifyCode']");
            if ($.trim(verifyCode) == '') {
                remind("error", "验证码不能为空！");
                return;
            }
            $.ajax({
                type: "post",
                url: "/dealer/info/mobile",
                data: $('#formMo').serialize(),
                traditional: true,
                dataType: "json",
                success: function (json) {
                    if (json.code == zttx.SUCCESS) {
                        remind("success", "认证成功！");
                        $('.mobile a').remove();
                        var html = '<input type="hidden" name="userMobile" value=' + userMobile + ' />';
                        html += '<span class="err">·手机已认证</span>';
                        $('.mobile input[name="dealerMobile"]').attr('readonly', 'readonly').val(userMobile).after(html);
                    } else {
                        remind("error", json.message);
                    }
                }
            });

        });
    },
    initValidator: function () {
        seajs.use(["$", 'validator', 'widget', "umeditor", "umeditor_config", "umdeitor_style"], function ($, Validator, Widget) {

            Widget.autoRenderAll();

            var _editor = UM.getEditor('myEditor1', { initialFrameWidth: 676, initialFrameHeight: 300 });
            _editor.ready(function () {
                // _editor.setContent('');
            });

            Validator.addRule('dianhua', /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/, '请输入正确的{{display}}格式');
            Validator.addRule('province', validatePro, '请选择相应的省市区');
            Validator.addRule('city', validateCity, '请选择相应的省市区');
            Validator.addRule('area', validateCounty, '请选择相应的省市区');
            Validator.addRule('monNum', monNumValida, '格式不正确或者销售额过大');
            Validator.addRule('empNum', empNumValida, '格式不正确或者销售额过大');
            Validator.addRule('shopNum', shopNumValida, '格式不正确或者销售额过大');

            Validator.query("#From_BaseInfo").addItem({
                element: '[name=userMobile]',
                required: function () {
                    var value = $.trim($('#tel').val());
                    return value == "" ? true : false;
                }
            }).addItem({
                element: '[name=dealerTel]',
                required: function () {
                    var value = $.trim($('#mobile').val());
                    return value == "" ? true : false;
                },
                rule: 'dianhua'
            }).addItem({
                element: '[name=dealerFax]',
                rule: 'dianhua'
            }).addItem({
                element: '[name=provinceName]',
                rule: 'province'
            }).addItem({
                element: '[name=cityName]',
                rule: 'city'
            }).addItem({
                element: '[name=areaName]',
                rule: 'area'
            }).addItem({
                element: '[name=monNum]',
                rule: 'monNum'
            }).addItem({
                element: '[name=empNum]',
                required: true,
                rule: 'empNum'
            }).addItem({
                element: '[name=shopNum]',
                required: true,
                rule: 'shopNum'
            });

            Validator.query("#From_BaseInfo").on('formValidated', function (error) {
                if (!error) {
                    var pro = $('#test1province :selected').text();
                    var city = $('#test1city :selected').text();
                    var county = $('#test1county :selected').text();
                    if (pro != '请选择省') {
                        $('input[name="provinceName"]').val(pro);
                    }
                    if (city != '请选择市') {
                        $('input[name="cityName"]').val(city);
                    }
                    if (county != '请选择区') {
                        $('input[name="areaName"]').val(county);
                    }

                }
            });


            function shopNumValida() {
                var shopNum = $('input[name="shopNum"]').val();
                var reg = /^[0-9]*$/;
                if (!reg.test(shopNum)) {
                    return false;
                }
                if (Number(shopNum) > 10000) {
                    return false;
                }
                return true;
            }

            function empNumValida() {
                var empNum = $('input[name="empNum"]').val();
                var reg = /^[0-9]*$/;
                if (!reg.test(empNum)) {
                    return false;
                }
                if (Number(empNum) > 10000) {
                    return false;
                }
                return true;
            }

            function monNumValida() {
                var monNum = $('input[name="monNum"]').val();
                var reg = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
                if (!reg.test(monNum)) {
                    return false;
                }
                if (Number(monNum) > 10000) {
                    return false;
                }
                return true;
            }

            function validatePro() {
                var pro = $('input[name="provinceName"]').val();
                if (pro == '' || pro == '请选择省') {
                    return false;
                }
                return true;
            }
            function validateCity() {
                var city = $('input[name="cityName"]').val();
                if (city == '' || city == '请选择市') {
                    return false;
                }
                return true;
            }
            function validateCounty() {
                var county = $('input[name="areaName"]').val();
                if (county == '' || county == '请选择区') {
                    return false;
                }
                return true;
            }

            $(".department-items li,.role-items li").hover(function () {
                $(this).toggleClass("on");
            })

            seajs.use(["dialog"], function (Dialog) {
                var o = new Dialog({
                    trigger: ".clickatur",
                    content: '#newChildrenID',
                    width: 400
                }).after("show", function () {
                    $(".btn-saveid").click(function () {
                        o.hide()
                    });

                });
            });

            if ($(".certificate_box").length == 0) {
                return;
            }

            $(".certificate_box").on("mouseenter mouseleave", ".item", function (ev) {
                if (ev.type == "mouseenter") {
                    $(this).find(".close").show();
                } else if (ev.type == "mouseleave") {
                    $(this).find(".close").hide();
                }
            });

            $(".certificate_box").on("click", ".close", function (ev) {
                var confirm = window.confirm("确定要删除该证书么？");
                if (!confirm) {
                    return;
                }
                $(this).parents(".item").remove();
            });

            if ($("#select_category").length == 0) {
                return;
            }
        });
    },
    initSubmit: function () {
        $('#btn_save').click(function () {
            $.post('/dealer/account/infor2', $('#From_BaseInfo').serialize(), function (data) {
                if (data.code == 126000) {
                    remind("success", "保存成功！");
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    remind("error", data.message);
                }
            }, "json");
        });
    },
    initArea: function () {
        $('#test1province').on('change', function () {
            var province = this.options[this.selectedIndex].text;
            if (province == '请选择省') {
                $('input[name="cityName"]').val("请选择市");
                $('input[name="areaName"]').val("请选择区");
            }
            $('input[name="provinceName"]').val(province);
        });
        $('#test1city').on('change', function () {
            var city = this.options[this.selectedIndex].text;
            if (city == '请选择市') {
                $('input[name="areaName"]').val("请选择区");
            }
            $('input[name="cityName"]').val(city);
        });
        $('#test1county').on('change', function () {
            var county = this.options[this.selectedIndex].text;
            $('input[name="areaName"]').val(county);
        });
    },
    showImage: function (uploadId, url) {
        var html = '<img src="' + url + '" style="width:100%;height:100%;"  alt=""/>';
        html += '<input type="hidden" name="' + uploadId + '" value="' + url + '" />';
        $('#' + uploadId).prev().text('').html(html);
    },
    uploadImage: function (uploadId) {
        var me = this;
        dialogLoading(function (d) {
            seajs.use(["$", "ajaxFileUpload"], function ($) {
                $.ajaxFileUpload({
                    url: '/common/showImg',
                    secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function (data) {
                        //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                        $('#' + uploadId).bind('change', function () {
                            me.uploadImage(uploadId);
                        });

                        if (data.code == 126000) {
                            me.showImage(uploadId, data.message);
                        }
                        d.hide();
                    }
                });
            });
        });
    },
}


//认证资料
var Basic_Information_Check = {

    init: function () {
        var me = this;
        me.initSticky();
        me.initValidator();
        me.initCategory();
        me.initSubmit();

        $('input[name="photo"]').bind('change', function () { me.uploadImage($(this).attr('id')); });

    },
    initSticky: function () {
        seajs.use(["sticky"], function (sticky) {
            sticky(".main>.main-left", 0);
        });
    },
    initValidator: function () {
        var me = this;
        seajs.use(["widget", "validator"], function (Widget, Validator) {
            Widget.autoRenderAll();
            Validator.addRule('IDCard', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|x|X)$)/, '请输入正确的{{display}}格式');
            Validator.addRule('faren', me.vidata, '请上传法人的证件照');
            Validator.query("#From_BaseInfo").addItem({
                element: '[name=cardId]',
                required: true,
                rule: 'IDCard'
                // }) .addItem({
                //    element: '[name=legalImgz]',
                //     rule: 'faren'
                // }).addItem({
                //    element: '[name=legalImgf]',
                //     rule: 'faren'
            });
        });
    },
    initCategory: function () {
        /*2014-04-11*/
        $.each(_dealList, function (ind, item) {//主分类
            var html = '<li id="' + item.id + '" class="item" name="' + ind + '"><span>' + item.item + '</span><i class="iconfont">&#xe60f;</i></li>';
            $("#select_category .select_inner").append(html);
        })

        $("#select_category .select_inner").on("click", ".item", function () {
            $("#select_category .item").removeClass("current");
            $(this).addClass("current");
            var i = parseFloat($(this).attr("name"));
            $("#select_class .select_inner").empty();
            /*$("#select_product .select_inner").empty(); */
            $.each(_dealList[i].childs, function (ind, item) {//子分类
                var html = '<li id="' + item.id + '" class="item" name="' + ind + '"><span>' + item.item + '</span><i class="iconfont">&#xe60f;</i></li>';
                $("#select_class .select_inner").append(html);
            });
        });

        $("#select_class").on("click", ".item", function () {

            var i = parseFloat($(this).attr("id"));
            var no = [];
            $("#select_product .select_inner li").each(function () {
                var num = parseFloat($(this).attr("no"));
                no.push(num);
            });
            var product_obj = $(this).find("span").text();
            $(this).siblings().removeClass("current");
            $(this).addClass("current");
            var flag = true;
            $.each(no, function (ide, item) {
                if (i == item) {
                    flag = false;
                    return false;
                }
            })
            if (flag) {
                var prehtml = '<i style="display: none;" class="account-icon i-cancel close_icon"></i>';
                $("#select_product .select_inner").append('<li no="' + i + '" class="item"><span>' + product_obj + '</span>' + prehtml + '<input type="hidden" name="dealNos" value="' + i + '"  /></li>');
            };
        });

        $("#select_product").on("mouseenter mouseleave click", ".item", function (ev) {
            switch (ev.type) {
                case "mouseenter":
                    $(this).find(".close_icon").show();
                    break;
                case "mouseleave":
                    $(this).find(".close_icon").hide();
                    break;
                case "click":
                    var id = $(this).attr("id");
                    $($("#" + id)).removeClass("current");
                    $("#" + id).attr("clicked", "false");
                    $(this).remove();
            }
        });
        /*2014-04-11 结束*/


    },
    showImage: function (uploadId, url) {
        var html = '<img src="' + url + '" style="width:100%;height:100%;"  alt=""/>';
        html += '<input type="hidden" name="' + uploadId + '" value="' + url + '" />';
        $('#' + uploadId).prev().text('').append(html);
        $("#photo_hidden").val("xxxx");
    },
    uploadImage: function (uploadId) {
        var me = this;
        dialogLoading(function (dialog) {
            seajs.use(["$", "ajaxFileUpload"], function ($) {
                $.ajaxFileUpload({
                    url: '/common/showImg?fSize=5',
                    secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function (data) {
                        //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                        $('#' + uploadId).bind('change', function () {
                            me.uploadImage(uploadId);
                        });

                        if (data.code != 126000) {
                            alert(data.message);
                        }
                        else {
                            me.showImage(uploadId, data.message);
                        }
                        dialog.hide();
                    }
                });
            });
        });
    },
    vidata: function () {
        /*校验法人图片的方法*/
        var imgz = $('#Imgz img').attr('src');
        var imgf = $('#Imgf img').attr('src');
        if (imgz != null && imgf != null) {
            return true;
        } else {
            $('#imgError i').html('');
            $('#imgError i').html("<i style='color:red;'>法人身份证必须要上传</i>");
            return false;
        }
    },
    initSubmit: function () {
        $('#wancheng').click(function () {
            $.post('/dealer/account/check2', $('#From_BaseInfo').serialize(), function (data) {
                if (data.code == 126000) {
                    remind("success", "保存成功！");
                } else {
                    remind("error", data.message);
                }
            }, "json");
        });
    }
}


