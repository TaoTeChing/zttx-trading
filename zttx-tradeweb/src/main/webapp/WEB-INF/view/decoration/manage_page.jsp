<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>8637品牌超级代理-店铺装修-资料下载</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修"/>
    <meta name="description" content="8637品牌超级代理-店铺装修"/>
    <link href="${res}/styles/fronts/market/kuang.css" rel="stylesheet" type="text/css"/>
    <style>
        <!--
        html{ overflow:hidden}
        -->
    </style>
</head>
<body>
<%--/decoration/index--%>
<div class="k-top">
    <div class="k-logo"><a href="#" title="" target=""><img src="${res}/images/fronts/market/k-logo.gif" height="56"/></a></div>
    <ul class="k-top-cen">
        <li>
            <a href="#" title="" target="" class="k-bet">装修<i class="k-down"></i></a>
            <dl class="k-dropmenu">
                <%--<dd><a href="Pages.jsp" title="" target="">页面管理</a></dd>--%>
                <dd><a href="javascript:" class="js-style-manage">风格模板</a></dd>
                <%--<!--<dd><a href="#" title="" target="">模板管理</a></dd>
                <dd><a href="#" title="" target="">模块管理</a></dd>
                <dd><a href="#" title="" target="">装修分析</a></dd>-->--%>
            </dl>
        </li>
        <%--<li>
            <a href="#" title="" target="" class="k-bet">产品<i class="k-down"></i></a>
            <dl class="k-dropmenu">
                <dd><a href="Categories.jsp" title="" target="">分类管理</a></dd>
                <dd><a href="#" title="" target="">宝贝管理</a></dd>
            </dl>
        </li>--%>
        <%--<li><a href="#" title="" target="" class="k-bet">营销</a></li>--%>
        <li><a href="#" title="" target="" class="k-bet js-global-attr">全局属性</a></li>
    </ul>
    <div class="k-shopername">
        <p><a href="${ctx}/brand/center"><span class="k-shoperwelcome">欢迎您：${brandUserm.comName}</span></a><a href="javascript:document.getElementById('logout-form').submit();"><span class="k-shoperexit">退出登录</span></a></p>

        <p>
            <a href="${ctx}/help/index"><span class="k-shoperonhelp f-r"><i class="k-shoperonh"></i>在线帮助</span></a><span class="k-shopermessage f-r"><i class="k-shoperm"></i>消息<a href="${ctx}/brand/message/list?msgStatus=2">${count}</a>条</span>
        </p>
        <form:form id="logout-form" action="${ctx}/common/logout/" method="post" cssStyle="display: none;"></form:form>
    </div>
</div>
<div class="k-body">
    <div class="k-left f-l">
        <h1 class="k-title">页面管理</h1>

        <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
        <ul class="k-leftul">
            <li class="selected"><a href="/decoration/index/${brandsId}" title="" target="mainframe">首页</a></li>
            <%--<li><a href="BrandRecruitment.jsp" title="" target="mainframe">品牌招募书</a></li>
            <li><a href="#" title="" target="mainframe">企业展示</a></li>
            <li><a href="/装修模块/品牌新闻.jsp" title="" target="mainframe">品牌新闻</a></li>
            <li><a href="/装修模块/品牌视觉-图片展示.jsp" title="" target="mainframe">品牌视觉</a></li>
            <li><a href="/装修模块/经销网络.jsp" title="" target="mainframe">经销网络</a></li>--%>
        </ul>
        <%--<h2 class="k-lefth2"><i class="k-hico"></i>列表页</h2>
        <ul class="k-leftul">
            <li><a href="/装修模块/首页.jsp" title="" target="mainframe">默认列表页</a></li>
            <li><a href="/装修模块/首页.jsp" title="" target="mainframe">产品展示列表页</a></li>
        </ul>
        <h2 class="k-lefth2"><i class="k-hico"></i>详情页</h2>
        <ul class="k-leftul">
            <li><a href="/装修模块/首页.jsp" title="" target="mainframe">默认详情页面</a></li>
            <li><a href="/装修模块/首页.jsp" title="" target="mainframe">产品展示详情页</a></li>
        </ul>
        <h2 class="k-lefth2"><i class="k-hico"></i>自定义页面</h2>
        <ul class="k-leftul">
            <li><a href="首页.jsp" title="" target="mainframe">默认自定义页面</a></li>
        </ul>--%>
    </div>
    <div class="k-right f-l">
        <div class="shousuo"></div>
        <div class="k-method">
            <a href="/decoration/index/${brandsId}" target="mainframe" title="页面编辑"><span class="k-edit selected">页面编辑</span></a>
            <!--<a href="#" title=""><span class="k-cantrl">布局管理</span></a>-->
            <!--<a href="javascript:;"><span class="k-line js-global-attr">全局属性</span></a>-->
            <%--<a href="pageattr.jsp" target="mainframe"><span class="k-line js-page-attr">页面属性</span></a>--%>
            <!--<a href="javascript:;" title="" target="mainframe"><span class="k-delete">删除页面</span></a>-->
            <a href="javascript:releaseView();" ><span class="k-saveall">预览效果</span></a>
            <a href="javascript:releaseNow();" ><span class="k-saveall">立即发布</span></a>
        </div>
        <iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="/decoration/index/${brandsId}" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
<form:form action="/decoration/release/${brandsId}" method="post"  id="releaseFrom" target="_blank">
    <div style="display: none;" id="reSaveData">

    </div>
    <div style="display: none;" id="reDelData">

    </div>
</form:form>
<!-- 全局设置弹窗 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-overall-boxes">
        <div class="ui-head">
            <h3 class="">全局设置</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li class="current">全局设置</li>
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact">
                    <form:form class="ui-form edit-common-form" id="globalForm">
                        <div class="ui-form-item">
                            <label class="ui-label">全局字体颜色：</label>
                            <input type="text" name="fontCcolor" class="ui-input js-all-color js-colorpicker" style="width:55px" value="${dGlobal.fontCcolor}" />
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">全局链接字体颜色：</label>
                            <div class="inline-block">
                                <span>默认颜色：</span><input type="text" class="ui-input js-all-dfcolor js-colorpicker" style="width:55px" name="urlFontColor" value="${dGlobal.urlFontColor}" />
                                <span class="ml15">移动变化：</span><input type="text" class="ui-input js-all-dfhover js-colorpicker"
                                                                      style="width:55px" name="urlChangeColor" value="${dGlobal.urlChangeColor}" />
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">全局背景颜色：</label>
                            <div class="inline-block">
                                <input type="text" class="ui-input js-all-bgc js-colorpicker" style="width:55px" name="backColor" value="${dGlobal.backColor}" />
                                <span class="ml5">显示：</span><input type="checkbox" name="showBackColor"
                                                                   <c:if test="${dGlobal.showBackColor}"> checked="checked" </c:if> class="ui-checkbox js-bgc-tg" />
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label mt10">全局背景图片：</label>
                            <div class="inline-block js-this-fileuploads">
                                <div class="file_wrap inline-block f-l">
                                    <div class="edit-form-uploads inline-block font">上传</div>
                                    <input class="input_file" type="file" name="photo" id="filebackUrlImg" data-img="backUrlImg">
                                </div>
                                <div class="edit-form-imgboxs inline-block ml10 f-l">
                                    <input type="hidden" name="backUrl" value="${dGlobal.backUrl}">
                                    <img id="backUrlImg" class="js-all-img" src="${res}${dGlobal.backUrl}" width="30" height="30" alt="全局背景图片">
                                </div>
                                <div class="edit-form-toshow inline-block f-l"><span class="ml5">显示：</span><input type="checkbox" name="showBackUrl"
                                        <c:if test="${dGlobal.showBackUrl}"> checked="checked" </c:if>  class="ui-checkbox js-img-tg" /></div>
                            </div>
                        </div>
                        <div class="ui-form-item">
                            <label class="ui-label">全局背景图片位置：</label>
                            <div class="inline-block font">
                                <select class="js-bg-position" name="backRepeat">
                                    <option <c:if test="${dGlobal.backRepeat=='repeat'}"> selected="selected" </c:if> value="repeat">平铺</option>
                                    <option <c:if test="${dGlobal.backRepeat=='no-repeat'}"> selected="selected" </c:if> value="no-repeat">不平铺</option>
                                    <option <c:if test="${dGlobal.backRepeat=='repeat-x'}"> selected="selected" </c:if> value="repeat-x">横向平铺</option>
                                    <option <c:if test="${dGlobal.backRepeat=='repeat-y'}"> selected="selected" </c:if> value="repeat-y">纵向平铺</option>
                                </select>
                                <select class="js-bg-norepeat" name="backPosition">
                                    <option <c:if test="${dGlobal.backPosition=='top center'}"> selected="selected" </c:if> value="top center">背景居顶居中</option>
                                    <option <c:if test="${dGlobal.backPosition=='top left'}"> selected="selected" </c:if> value="top left">背景居顶居左</option>
                                    <option <c:if test="${dGlobal.backPosition=='top right'}"> selected="selected" </c:if> value="top right">背景居顶居右</option>
                                </select>
                            </div>
                        </div>
                        <div class="ui-bottom">
                            <a href="javascript:" class="edit-common-conserve js-header-save">保存</a>
                            <a href="javascript:" class="edit-common-cancel js-header-cansle">取消</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 风格模板弹窗 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-touch-boxes touch-style">
        <div class="ui-head">
            <h3 class="">风格模板</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li class="current">风格默认区</li>
                <%--<li>自定义风格</li>--%>
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact">
                    <ul class="touch-style-ul font" id="themeUl">
                        <li class="items" id="default">
                            <div class="imgbox <c:if test="${dGlobal.skinName=='default'}"> select</c:if>"  >
                                <img src="${res}/uploads/defult.png" alt="" width="130" height="160" />
                            </div>
                            <div class="tit">默认主题</div>
                        </li>
                        <li class="items <c:if test="${dGlobal.skinName=='blue'}"> select</c:if>" id="blue">
                            <div class="imgbox">
                                <img src="${res}/uploads/blue.png" alt="" width="130" height="160" />
                            </div>
                            <div class="tit">蓝色主题</div>
                        </li>
                        <li class="items <c:if test="${dGlobal.skinName=='gray'}"> select</c:if>" id="gray">
                            <div class="imgbox">
                                <img src="${res}/uploads/gray.png" alt="" width="130" height="160" />
                            </div>
                            <div class="tit">灰色主题</div>
                        </li>
                    </ul>
                    <div class="ui-bottom">
                        <a href="javascript:" class="edit-common-conserve js-header-save">保存</a>
                        <a href="javascript:" class="edit-common-cancel js-header-cansle">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form:form id="themeForm">
    <input type="hidden" name="skinName" value="${dGlobal.skinName}">
</form:form>

<div class="lig"></div>
<script type="text/javascript" src="${res}/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${src}/plugin/jqueryui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${src}/fronts/market/jquery.elastislide.js"></script>
<script type="text/javascript" src="${src}/fronts/market/jquery.colorpicker.js"></script>
<script type="text/javascript" src="${src}/fronts/market/kuang.js"></script>
<script type="text/javascript" src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script type="text/javascript" src="${res}/scripts/seajs_config.js"></script>
<script type="text/javascript" src="${res}/scripts/common.js"></script>
<script type="text/javascript">
    var global = {//全局属性
        init:function(){
            this.globalattr();
            this.saveTheme();//风格模板
        },
        globalattr:function(){
            $(".js-overall-boxes").on("click",".js-header-save",function(){
                $.ajax({
                    type: "POST",
                    url: "/decoration/ajax_saveGlobal/${brandsId}",
                    data: $("#globalForm").serialize(),
                    dataType: "json",
                    cache: false,
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(errorThrown);
                    },
                    success: function (result){
                        if(result.code  != 126000)
                        {
//                            remind("error",result.message);
                            alert(result.message);
                        }else{
                            var allcolor = $(".js-all-color").val();//全局字体颜色
                            var alldf = $(".js-all-dfcolor").val();//全局链接字体颜色
                            var alldh = $(".js-all-dfhover").val();
                            //var allbgc = $(".js-all-bgc").val();//全局背景颜色
                            //var allimg = $(".js-all-img").attr("src");//全局背景图片

                            var repeatstyle = $(".js-bg-position").val();
                            var posstyle = $(".js-bg-norepeat").val();

                            if($(".js-bgc-tg").prop("checked")==true){
                                var allbgc = $(".js-all-bgc").val();//全局背景颜色
                            }else{
                                var allbgc = '';
                            }

                            if($(".js-img-tg").prop("checked")==true){
                                var allimg = window.IMAGE_DOMIAN + result.object.backUrl;//全局背景图片
                            }else{
                                var allimg = '';
                            }

                            $("#testIframe").contents().find("head").append(
                                    '<style>'+
                                    '.do-body{ color:' + allcolor + ' ;/*全局字体颜色*/ background:' + allbgc + ' url("' + allimg + '") '+ repeatstyle +' ' + posstyle + ' ;/*全局背景颜色*/}' +
                                            '.do-body a{ color:' + alldf + ';}/*全局链接字体颜色*/' +
                                            '.do-body a:hover{ color:' + alldh + ';}</style>');
//                            alert("修改成功，‘立即发布’后生效");
                        }
                    }
                })


            });
        },
        saveTheme:function(){
            $(".js-touch-boxes").on("click",".js-header-save",function(){
                var themeName = $("#themeUl li.select").attr("id");
                $("#themeForm input").val(themeName);

                if(themeName==""){
//                                remind("error","请选择风格模板");
                    alert("请选择风格模板");
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/decoration/ajax_saveTheme/${brandsId}",
                    data: $("#themeForm").serialize(),
                    dataType: "json",
                    cache: false,
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(errorThrown);
                    },
                    success: function (result){
                        if(result.code  != 126000)
                        {
                            alert(result.message);
                        }else{
                            var themeUrl = "${res}/styles/fronts/market/decoration_edit.css";
                            if(themeName!="default"){
                                themeUrl = "${res}/styles/fronts/market/"+themeName+"/decoration_edit.css";
                            }
                            $("#testIframe").contents().find("head").append("<link type='text/css' rel='stylesheet' href='"+themeUrl+"'>");
                        }
                    }
                });
                $(".js-touch-boxes .js-header-cansle").click(function(){
                    var themeName = $("#themeForm input").val();
                    $("#themeUl li.select").removeClass("select");
                    $("#"+themeName).addClass("select")
                });
            });
        }

    };
    global.init();
    var touch = {
        init:function(){
            this.stylesetect();
        },
        stylesetect:function(){
            $(".js-touch-boxes").on("click",".items",function(){
                $(".js-touch-boxes .items").removeClass("select");
                $(this).addClass("select");
            });
        }
    };
    touch.init();
    (function($) {//弹出框，包含触发元素和需要显示的元素
        $.fn.extend({
            "pageDialog":function(options){

                options = $.extend({
                    /*tagger:'.edit',*/
                    showBox:'.js-header-boxes',//需要现实的box
                    saveBtn:'.edit-common-conserve',//保存按钮
                    cancelBtn:'.edit-common-cancel',//关闭按钮
                    closeBtn:'.ui-close'//关闭按钮
                },options);

                $(this).click(function(){

                    /*设置弹出元素居中*/
                    var top = ($(window).height() - $(options.showBox).height())/2;
                    var left = ($(window).width() - $(options.showBox).width())/2;
                    var scrollTop = $(document).scrollTop();
                    var scrollLeft = $(document).scrollLeft();

                    if(top < 0){//top为负数的时候
                        top = 10;
                    }

                    $(options.showBox).css({
                        position : 'absolute',
                        top : top + scrollTop,
                        left : left + scrollLeft
                    }).show();
                    $('.lig').show();
                    $(options.showBox).show();

                });

                function clickBtn(BTN){//取消保存
                    $(options.showBox).on("click",BTN,function(){
                        $('.lig').hide();
                        $(options.showBox).hide();
                    });
                }
                clickBtn(options.saveBtn);//保存
                clickBtn(options.cancelBtn);//取消

                function close(close){//关闭
                    $(options.showBox).on("click",close,function(){
                        $('.lig').hide();
                        $(options.showBox).hide();
                    });
                }
                close(options.closeBtn);//关闭

                return this;
            }
        });
    })(jQuery);
    //调用弹出框
    $(".js-global-attr").pageDialog({
        showBox:'.js-overall-boxes'
    });
    $(".js-style-manage").pageDialog({
        showBox:'.js-touch-boxes'
    });

    //基础版tab切换 可以通用
    $(".jsedit-tabs-menus").on("click","li",function(){
        var index = $(this).index();
        $(this).parent().find("li").removeClass("current").eq(index).addClass("current");
        $(this).parent().next().find(".jsedit-contact").hide().eq(index).show();
    });

    //调用颜色选取
    $(".js-colorpicker").colorpicker({
        fillcolor:true
    });

    function uploadImage(fileElementId,callback){
        seajs.use(["$","ajaxFileUpload"],function($){
            $.ajaxFileUpload({
                url: "${ctx}/common/showImg",
                secureuri: false,
                fileElementId: fileElementId,
                dataType: 'json',
                success: function(data)
                {

                    if(data.code  != 126000)
                    {
//                        remind("error",data.message);
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

    $(document).on("change", "#filebackUrlImg", function () {
        uploadImage(this.id,showImage);
    });
    function showImage(fileElementId,url,name){
        var srcId = $("#"+fileElementId).data("img");
        $("#"+srcId).attr("src",window.IMAGE_DOMIAN + url);
        $("#"+srcId).siblings("input").eq(0).val(url);
    }

    //预览
    function releaseView() {
        var viewForm = $("#releaseFrom");
        var formAction = viewForm.attr("action");
//        var formTarget = viewForm.attr("target");
        viewForm.attr("action", "/market/brand/preview/${brandsId}");
//        viewForm.attr("target", "_blank");
        viewForm.submit();
        viewForm.attr("action", formAction);
//        viewForm.attr("target", formTarget);

    }
    //发布
    function releaseNow() {
        $("#releaseFrom").submit();

    }
</script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
</body>
</html>
