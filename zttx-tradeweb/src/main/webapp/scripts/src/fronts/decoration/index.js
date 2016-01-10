function uploadImage(fileElementId,callback){
    seajs.use(["$","ajaxFileUpload"],function($){
        $.ajaxFileUpload({
            url: "/common/showImg",
            secureuri: false,
            fileElementId: fileElementId,
            dataType: 'json',
            success: function(data)
            {

                if(data.code  != 126000)
                {
                    //remind("error",data.message);
                    alert(data.message);
                }
                else
                {
                    if ($.isFunction(callback)) {
                        callback(fileElementId,data.message,data.object);
                    }
                }
            }
        })
    })
}

$(document).on("change", "input[type='file']", function () {
    uploadImage(this.id,showImage);
});
function showImage(fileElementId,url,name){
    var srcId = $("#"+fileElementId).data("img");
    $("#"+srcId).attr("src",window.IMAGE_DOMIAN + url);
    $("#"+srcId).siblings("input").eq(0).val(url);
}
function saveHeader($form,showCate,url){
    $.ajax({
        type: "POST",
        url: url,
        data: $form.serialize(),
        dataType: "json",
        cache: false,
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(errorThrown);
        },
        success: function (result){

            if(result.code  != 126000)
            {
                //remind("error",result.message);
                alert(result.message);
            }else{
                $("#header-navcen div[id^='headnav-']").hide();

                if(1==showCate){

                    resetHeader(result,"header");
                }else if(2==showCate){
                    $("#headnav-custom div.customContent").html(UM.getEditor("myEditor2").getContent());

                    $("#headnav-custom").show();
                    if($("#custom_style").size()==0){
                        $("#headnav-custom").append('<style id="custom_style">.header-nav {background: #fff;background-image: none;}.header-cen {background: #fff;height: 100%;}</style>');
                    }
                }else{

                }
            }
        }
    })
}

function setMenuData(){
//    menuData
    $("#menuData").empty();
    $("#navUl li.movenav").each(function(index){
        var prefix = "menus["+index+"]";
        var menuId = $(this).data("id");
        var showTest = $(this).find("div.nav-edit-listate").text();
        var show = 1;
        var name = $(this).find("a.menu-link").text();
        var url = $(this).find("a.menu-link").attr("href");
        if(showTest.indexOf("显示")==-1){
            show = 0;
        }
        if(menuId && menuId!=""){
            $("#menuData").append("<input type='hidden' value='"+menuId+"' name='"+prefix+".id'> ");
        }
        $("#menuData").append("<input type='hidden' value='"+show+"' name='"+prefix+".s'> ");
        $("#menuData").append("<input type='hidden' value='"+name+"' name='"+prefix+".n'> ");
        $("#menuData").append("<input type='hidden' value='"+url+"' name='"+prefix+".url'> ");

    })
}
/**
 * 重新设置头部图片url
 */
function resetHeader(json,tag){
    var url = "/decoration/ajax_header/"+$("#brandsId").val();
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        cache: false,
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(errorThrown);
        },
        success: function (result){

            if(result.code  != 126000)
            {
                //remind("error",result.message);
                alert(result.message);
            }else{
                var header = result.object;
                if(tag=="header"){
                    $("#logoNameImg").attr("src",window.IMAGE_DOMIAN + header.logoName);
                    $("#logoNameImg").siblings("input").val(header.logoName);
                    $("#headerUrlImg").attr("src",window.IMAGE_DOMIAN + header.outBackUrl);
                    $("#headerUrlImg").siblings("input").val(header.outBackUrl);
                    $("#neibeiUploadImg").attr("src",window.IMAGE_DOMIAN + header.inBackUrl);
                    $("#neibeiUploadImg").siblings("input").val(header.inBackUrl);

                    var logoname = $(".js-logoname").val();//店铺名称
                    var mainpro = $(".js-mainpro").val();//主营产品
                    var color = $(".js-header-color").val();//logo字体颜色
                    var size = $(".js-header-size option:selected").text();//logo字体大小
                    $("#headnav-defualt .logo h2").css({
                        "color":color,
                        "font-size":size
                    });
                    $("#headnav-defualt .logo h2").text(logoname);
                    $("#headnav-defualt .logo h3").text(mainpro);

                    var logobg = $(".js-logoimgset").attr("src");//logo
                    var headall = $(".js-headall").attr("src");//通栏外背景图
                    var headinner= $(".js-headinner").attr("src");//通栏内背景图
                    /*seajs.importStyle('' +
                     '.header-nav{background:#BA2926 url(" ' + headall + '")}' +
                     '.header-navcen{background:url(" ' + headinner + '")}' +
                     '.logo{background:url(" ' + logobg + '");.header-cen {background: #fff;}');*/

                    var _inner = headinner ? headinner : "/images/market/temp/banar.jpg"; //第一个为传的图片，第二个为默认图片

                    $("head").append('<style>.header-nav{background:#BA2926 url(" ' + headall + '")}' +
                        '.header-navcen{background:url(" ' + _inner + '")}' +
                        '.logo{background:url(" ' + logobg + '");.header-cen {background: #fff;}</style>');


                    if($(".js-logoname-tg").prop("checked")){
                        $("#headnav-defualt .logo").show();
                        $("#headnav-defualt .logo h2").show();
                    }

                    if($(".js-logoimg-tg").prop("checked")){
                        $("#headnav-defualt .logo").show();
                        $("#headnav-defualt .logo h2").hide();
                    }

                    $("#headnav-defualt").show();
                    $("#custom_style").remove();
                }else{
                    //saveMenu
                    $("#navDefaultUrlImg").attr("src",window.IMAGE_DOMIAN + header.navDefaultUrl);
                    $("#navDefaultUrlImg").siblings("input").val(header.navDefaultUrl);
                    $("#navChangeUrlImg").attr("src",window.IMAGE_DOMIAN + header.navChangeUrl);
                    $("#navChangeUrlImg").siblings("input").val(header.navChangeUrl);

                    var navbg1 = $(".js-nav-bgcolor").val();//导航背景色
                    var ftc = $(".js-nav-color").val();
                    var fhc = $(".js-nav-hovercolor").val();
                    var navbg = $(".js-navbg").attr("src");//导航图片设置
                    var nachoverbg = $(".js-navhoverbg").attr("src");//导航移动变化
                    var nachovercolor =  $(".js-nav-hoverbgcolor").val();//导航移动颜色
                    var selectcolor = $(".js-navselect-color").val();//导航选中背景色

                    $(".nav-cen").css("backgroundColor",navbg1);

                    seajs.importStyle('' +
                        '.nav-cen{background: ' + navbg1 + ' url(" ' + navbg + '");}' +
                        'a.menu-link{color:' + ftc + '}'+
                        'a.menu-link:hover{color:' + fhc + '}' +
                        'a.menu:hover{background:' + nachovercolor + ' url("' + nachoverbg + '");}' +
                        'a.menu-link.selected{ background:'+ selectcolor +';}');
                    //替换导航 menu-list
                    var index = $("#menu-list li").eq(0);
                    $("#menu-list").empty();
                    $("#menu-list").append(index);
                    $.each(json.rows,function(index,data){
                        if(data.s==1){
                            $("#menu-list").append("<li class='menu'><a class='menu-link' href='"+data.url+"' title='"+data.n+"' ><span class='title'>"+data.n+"</span></a></li>");
                        }
                    });
                }

            }
        }
    })
}
