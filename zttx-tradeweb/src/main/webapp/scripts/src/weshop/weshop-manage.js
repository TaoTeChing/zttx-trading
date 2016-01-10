$.fn.offButton = function (callback) {
    this.each(function (i, o) {
        var btn = $("<div class='ui-offbutton'><span></span></div>")
                .click(function () {
                    $(o).trigger("click");
                }).addClass(o.checked ? "active" : "");
        $(o).change(function () {
            btn.toggleClass("active", o.checked);
            callback(o, btn);
        }).hide().after(btn);
    })
}

var weshopManage = {
    init: function () {
        this.initChangeMail(); //选择邮箱
        this.initChangeMobile(); //选择手机
        this.initChangeLOGO(); //选择LOGO
        this.initChangeBG(); //选择背景
        this.initSelectMoreQRSize(); //二维码 	
        this.initSelectPrivacy(); //隐私
        this.initChangeIntroduce(); //介绍
        this.initChangeAddress(); //地址
        //this.initChangeWaterMark();//水印
    },
    initChangeMail: function () {

        seajs.use(["dialog"], function (Dialog) {

            var cm2 = new Dialog({
                content: "#dialogChangeMail-step2"
            }).after("show", function () {
                cm1.hide();
            });

            //修改邮箱
            var cm1 = new Dialog({
                trigger: "#ChangeMail",
                content: "#dialogChangeMail-step1"
            }).after("render", function () {
                $(this.element[0]).find("#btnMailStep1").bind("click", function () {

                    var $validatePas = $("input[name='validatePas']").val();
                    var $emailAddress = $("input[name='emailAddress']").val();

                    if ($validatePas == null || "" == $validatePas) {
                        remind("error", "密码不能为空！")
                    }

                    if (!(/^[a-z]([a-z0-9]*[-_]?[a-z0-9])*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i).test($emailAddress)) {

                        remind("error", "邮箱格式不正确！")
                        return false;
                    }

                    var $shopId = $("input[name='shopId']").val();
                    $.ajax({
                        type: "POST",
                        url: "/weshop/config/changeEmail",
                        data: { "validatePas": $validatePas, "newEmail": $emailAddress, "shopId": $shopId },
                        dataType: "json",
                        success: function (data) {
                            if (data.code == zttx.SUCCESS) {
                                remind('success', "验证成功!");
                                cm1.hide();
                                $('.userEmail').text($emailAddress);
                                $('.newUserMail').text($emailAddress);
                                cm3.show();
                            } else {

                                remind('error', "验证失败！" + zttx.SUCCESS);

                            }
                        }
                    });

                });
            })



            var cm3 = new Dialog({
                content: "#dialogChangeMail-step3"
            }).after("show", function () {
                cm2.hide();
            });


            $("#reSend").click(function () {
                cm3.hide();
                cm1.show();
            });

            $("#btnMailStep3").click(function () {

                cm3.hide();

            });
            //修改邮箱
        });
    },

    initChangeMobile: function () {

        seajs
                .use(
                        ["dialog"],
                        function (Dialog) {
                            // 修改电话
                            var cm1 = new Dialog({
                                trigger: "#ChangeMobile",
                                content: "#dialogChangeMobile-step1"
                            }).after("render", function () {
                                $(this.element[0]).find('button[name="firstValidation"]').click(
                                                function () {
                                                    var urlAddr = "/common/sendvcode";
                                                    var userMobile = $("#oldTelephone").val();
                                                    changeMobile(urlAddr, { "userMobile": userMobile });
                                                });
                                $(this.element[0]).find("button[name='secondValidation']").click(function () {
                                    var userMobile = $("#newTelephone").val();

                                    if (null == userMobile || "" == userMobile) {
                                        remind("error", "请输入新的手机号！");
                                        return false;
                                    };

                                    if (!(/^1[3|4|5|8]\d{9}$/.test(userMobile))) {
                                        remind("error", "请输入有效的手机号！")
                                        return false;
                                    }
                                    var urlAddr = "/common/sendvcode";
                                    changeMobile(urlAddr, { "userMobile": userMobile });
                                });
                                $(this.element[0]).find("#btnMobileStep1").click(
                                                function () {
                                                    var oldUserMobile = $("#oldTelephone").val();
                                                    var newUserMobile = $("#newTelephone").val();
                                                    var $firstValM = $("input[name='firstValM']").val();
                                                    var $secondValM = $("input[name='secondValM']").val();
                                                    if (null == newUserMobile || "" == newUserMobile) {
                                                        remind("error", "请输入新的手机号！");
                                                        return false;
                                                    };
                                                    if (!(/^1[3|4|5|8]\d{9}$/.test(newUserMobile))) {
                                                        remind("error", "请输入有效的手机号！")
                                                        return false;
                                                    }
                                                    if ($secondValM.length != 6 || $firstValM.length != 6) {
                                                        remind("error", "请输入手机上的6位验证码！");
                                                        return false;
                                                    };


                                                    var urlAddr = "/weshop/config/changeMobile";
                                                    var data = changeMobile(
                                                            urlAddr,
                                                            {
                                                                oldUserMobile: oldUserMobile,
                                                                newUserMobile: newUserMobile,
                                                                firstValM: $firstValM,
                                                                secondValM: $secondValM,
                                                                shopId: $('input[name="shopId"]').val()
                                                            });
                                                    $('.userMobile').text(newUserMobile);
                                                    cm1.hide();

                                                });

                            });
                        });
    },

    initChangeLOGO: function () {

        seajs.use(["dialog", "Jcrop", "ajaxFileUpload"], function (Dialog) {
            //修改邮箱
            var cm1 = new Dialog({
                trigger: "#ChangeLOGO",
                content: "#dialogChangeLOGO"
            });

            var jcrop_api, boundx, boundy, $preview = $('.preview-logo'), $pcnt = $(
                    $preview)
                    .find('.preview-container'), $pimg = $(
                    $pcnt).find('img'), xsize = $pcnt
                    .width(), ysize = $pcnt.height();
            $('.changelogo-img').Jcrop(
                    {
                        boxWidth: 320,
                        boxHeight: 320,
                        minSize: [160, 160],
                        onChange: updatePreview,
                        onSelect: updatePreview,
                        aspectRatio: 1 / 1
                    },
                    function () {
                        var bounds = this.getBounds();
                        boundx = bounds[0];
                        boundy = bounds[1];
                        jcrop_api = this;
                        jcrop_api.animateTo([0, 0,
                                160, 160]);
                        //$preview.appendTo(jcrop_api.ui.holder);
                    });

            var cut = {};
            function updatePreview(c) {
                c.h = (c.h).toFixed(0);
                c.w = (c.w).toFixed(0);
                c.x = (c.x).toFixed(0);
                c.y = (c.y).toFixed(0);
                c.x2 = (c.x2).toFixed(0);
                c.y2 = (c.y2).toFixed(0);
                cut = c;
                if (parseInt(c.w) > 0) {
                    var rx = xsize / c.w;
                    var ry = ysize / c.h;
                    $pimg.css({
                        width: Math.round(rx * boundx) + 'px',
                        height: Math.round(ry * boundy) + 'px',
                        marginLeft: '-' + Math.round(rx * c.x) + 'px',
                        marginTop: '-' + Math.round(ry * c.y) + 'px'
                    });
                }
            }
            ;

            //修改LOGO 上传图片功能呢
            $("#dialogChangeLOGO #LogoUpload").click(function () {



                $.ajaxFileUpload({
                    url: '/dealer/weshop/upload',
                    secureuri: false,
                    fileElementId: "LogoBJ",
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == zttx.SUCCESS) {
                            $('#dialogChangeLOGO .changelogo-img').attr("src", "${res}" + data.rows[0].path);
                            $('#dialogChangeLOGO input[name="logo_img"]').val(data.rows[0].path);
                            $('#dialogChangeLOGO input[name="logo_imgName"]').val(data.rows[0].origName);
                            $('#dialogChangeLOGO .jcrop-preview').attr("src", "${res}" + data.rows[0].path);

                        } else {
                            remind("error", data.message);
                        }
                    }
                });
            });

            //修改LOGO  图片保存功能
            $("#dialogChangeLOGO #SaveLOGO").click(function () {

                var img = $('#dialogChangeLOGO input[name="logo_img"]').val();
                var imgName = $('#dialogChangeLOGO input[name="logo_imgName"]').val();
                if (img == '' || imgName == '') {

                    remind("error", "请先上传图片");
                    return false;
                }
                cut.img = img;
                cut.imgName = imgName;
                cut.shopId = $.trim($('input[name="shopId"]').val());
                $.ajax({
                    type: "POST",
                    url: "/weshop/config/saveLogo",
                    data: cut,
                    dataType: "json",
                    success: function (
                            data) {
                        if (data.code == zttx.SUCCESS) {

                            var img = $('#dialogChangeLOGO input[name="logo_img"]').val();
                            $('.LogoImg img').attr("src", '${res}' + img);
                            remind("success", "图片保存成功!")
                            cm1.hide();
                        } else {

                            remind("error", data.message);
                        }
                    }
                });
            });

        });
    },
    initChangeBG: function () {

        seajs
                .use(
                        ["dialog", "Jcrop"],
                        function (Dialog) {
                            //修改背景
                            var cm1 = new Dialog({
                                trigger: "#ChangeBG",
                                content: "#dialogChangeBG"
                            });

                            var jcrop_api, boundx, boundy, $preview = $('.preview-bg'), $pcnt = $(
                                    $preview)
                                    .find('.preview-container'), $pimg = $(
                                    $pcnt).find('img'), xsize = $pcnt
                                    .width(), ysize = $pcnt.height();
                            $('.changelogo-bg').Jcrop({
                                boxWidth: 160,
                                boxHeight: 160,
                                minSize: [320, 110],
                                onChange: updatePreview,
                                onSelect: updatePreview,
                                aspectRatio: 160 / 55
                            }, function () {
                                var bounds = this.getBounds();
                                boundx = bounds[0];
                                boundy = bounds[1];
                                jcrop_api = this;
                                jcrop_api.animateTo([0, 0, 160, 55]);
                                //$preview.appendTo(jcrop_api.ui.holder);
                            });
                            var cut = {};
                            function updatePreview(c) {
                                c.h = (c.h).toFixed(0);
                                c.w = (c.w).toFixed(0);
                                c.x = (c.x).toFixed(0);
                                c.y = (c.y).toFixed(0);
                                c.x2 = (c.x2).toFixed(0);
                                c.y2 = (c.y2).toFixed(0);
                                cut = c;
                                if (parseInt(c.w) > 0) {
                                    var rx = xsize / c.w;
                                    var ry = ysize / c.h;

                                    $pimg.css({
                                        width: Math.round(rx * boundx)
                                                + 'px',
                                        height: Math
                                                .round(ry * boundy)
                                                + 'px',
                                        marginLeft: '-'
                                                + Math.round(rx * c.x)
                                                + 'px',
                                        marginTop: '-'
                                                + Math.round(ry * c.y)
                                                + 'px'
                                    });
                                }
                            };


                            //图片上传
                            $('#uploadBG').click(function () {
                                $.ajaxFileUpload({
                                    url: '/dealer/weshop/upload',
                                    secureuri: false,
                                    fileElementId: "LogoBG",
                                    dataType: 'json',
                                    success: function (data) {
                                        if (data.code == zttx.SUCCESS) {
                                            $('#dialogChangeBG .changelogo-bg').attr("src", "${res}" + data.rows[0].path);
                                            $('#dialogChangeBG input[name="logo_img"]').val(data.rows[0].path);
                                            $('#dialogChangeBG input[name="logo_imgName"]').val(data.rows[0].origName);
                                            $('#dialogChangeBG .jcrop-preview').attr("src", "${res}" + data.rows[0].path);
                                        } else {

                                            remind("error", data.message);
                                        }
                                    }
                                });
                            });


                            //背景上传

                            $("#SaveBG").click(function () {

                                var img = $('#dialogChangeBG input[name="logo_img"]').val();
                                var imgName = $('#dialogChangeBG input[name="logo_imgName"]').val();
                                if (img == '' || imgName == '') {

                                    remind("error", "请先上传图片");
                                    return false;
                                }

                                cut.img = img;
                                cut.imgName = imgName;
                                cut.shopId = $.trim($('input[name="shopId"]').val());
                                $.ajax({
                                    type: "POST",
                                    url: "/weshop/config/saveBG",
                                    data: cut,
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.code == zttx.SUCCESS) {

                                            var img = $('#dialogChangeBG input[name="logo_img"]').val();
                                            $('.bgImage img').attr("src", '${res}' + img);
                                            remind("success", "图片保存成功!")
                                            cm1.hide();
                                        } else {
                                            remind("error", data.message)
                                        }
                                    }
                                });
                            });
                        });

    },
    /*下载更多二维码*/
    initSelectMoreQRSize: function () {
        seajs.use(["dialog", "qrcode"], function (Dialog, Qrcode) {

            var qr = new Qrcode({
                text: "hello world!!",
                width: 120,
                height: 120
            });
            $(".myQRCode").append(qr);

            var my = new Dialog({
                trigger: "#QRSize",
                content: "#dialogQRSize"
            }).after("render", function () {
                $(this.element[0]).find("button").click(function () {
                    my.hide();
                });
            });
        });
    },
    initSelectPrivacy: function () {
        //开关按钮
        $("#checkPrivacy").offButton(function (a, b) {

            $("#LabelPrivacy").text(a.checked ? "已允许" : "不允许");

        });
    },
    initChangeIntroduce: function () {

        seajs.use(["dialog"], function (Dialog) {
            //更新店铺介绍
            var my = new Dialog({
                trigger: "#ChangeIntroduce",
                content: "#dialogChangeIntroduce"
            }).after("render", function () {
                $(this.element[0]).find("button").bind("click", function () {
                    var introduction_val = $("#introduceText").val();

                    if (introduction_val.length > 120 || introduction_val.length < 4) {
                        alert("介绍长度为4-120个字！");
                        return false;
                    }

                    $.ajax({
                        type: "POST",
                        url: "/weshop/config/changeIntroduce",
                        data: {
                            "introduction_val": introduction_val,
                            "shopId": $('input[name="shopId"]').val()
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.code == zttx.SUCCESS) {
                                alert("更新介绍成功！");
                                $("#showIntroduce").text(introduction_val);
                            } else {
                                alert(data.message);
                            }
                            my.hide();
                        }
                    });
                });

            });
        });
    },
    initChangeAddress: function () {

        seajs.use(["dialog"], function (Dialog) {
            //修改店铺地址
            var my = new Dialog({
                trigger: "#ChangeAddress",
                content: "#dialogChangeAddress"
            }).after("render", function () {
                $(this.element[0]).find("button").bind("click", function () {

                    var $province = $("#test1province option:selected").val();
                    var $city = $("#test1city option:selected").val();
                    var $county = $("#test1county option:selected").val();
                    var $areaNo = "";
                    var $dealerAddress = $("input[name='dealerAddress']").val();

                    if ($county != "") {
                        $areaNo = $county;
                    } else if ($city != "") {
                        $areaNo = $county;
                    } else if ($province != "") {
                        $areaNo = $county;
                    } else {
                        alert("请在下拉框中选择地址！");
                        return false;
                    }

                    $("#changeAddress input[name='provinceName']").val($province);
                    $("#changeAddress input[name='cityName']").val($city);
                    $("#changeAddress input[name='areaName']")
                            .val($county);
                    $("#changeAddress input[name='areaNo']")
                            .val($areaNo);
                    $("#changeAddress input[name='dealerAddress']")
                            .val($dealerAddress);

                    $.ajax({
                        type: "POST",
                        url: "/weshop/config/changeAddress",
                        data: $("#changeAddress").serialize(),
                        dataType: "json",
                        success: function (data) {
                            if (data.code == zttx.SUCCESS) {
                                alert("更新地址成功！");
                                var $provinceName = data.object.provinceName;
                                var $cityName = data.object.cityName;
                                var $areaName = data.object.areaName;
                                var $dealerAddress = data.object.address;

                                //装值倒装，从后往前
                                //问题：若重复提交会页面信息累计
                                $("#showAddress").text("");

                                var addre = $provinceName + $cityName + $areaName + $dealerAddress;
                                var showAddress = $("#showAddress").text(addre);


                            } else {
                                alert(data.message);
                            }
                            my.hide();
                        }
                    });
                });
            });
        });
    },
    initChangeWaterMark: function () {
        seajs.use(["dialog"], function (Dialog) {
            //修改邮箱
            var my = new Dialog({
                trigger: "#ChangeWaterMark",
                content: "#dialogChangeWaterMark"
            }).after("render", function () {
                $(this.element[0]).find("button").click(function () {
                    my.hide();
                });
            });
        });
    }

};

//自定义AJAX跳转方法
function changeMobile(urlAddr, dataString) {
    $.ajax({
        type: "POST",
        url: urlAddr,
        data: dataString,
        dataType: "json",
        success: function (data) {
            if (data.code == zttx.SUCCESS) {
                remind('success', "信息发送成功!");
                return data;
            } else {
                remind('error', "验证失败！");
            }
        }
    });

};

weshopManage.init();