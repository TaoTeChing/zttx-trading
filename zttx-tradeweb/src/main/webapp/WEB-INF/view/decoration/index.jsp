<%@ page import="com.zttx.web.consts.DecorateHeaderConst" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:set var="SHOWCATE_DEFAULT" value="<%=DecorateHeaderConst.SHOWCATE_DEFAULT%>"/>
<c:set var="SHOWCATE_CUSTOM" value="<%=DecorateHeaderConst.SHOWCATE_CUSTOM%>"/>
<c:set var="SHOWCATE_MARKET" value="<%=DecorateHeaderConst.SHOWCATE_MARKET%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />

    <link href="${res}/styles/fronts/market/btnico.css" rel="stylesheet" type="text/css" />
    <link href="${res}/styles/fronts/market/stylemanage.css" rel="stylesheet" />
    <!--[if lte IE 9]>
    <link href="${res}/scripts/gallery/editor/1.0.0/themes/default/css/umeditor.min.css" rel="stylesheet" type="text/css" />
    <![endif]-->
  <%@include file="../fronts/market/common/view_header_css.jsp"%>
</head>
<body id="allbody">
<input id="brandsId" value="${brandsId}" type="hidden">
<div class="top">
    <div class="top-center">
        <img src="${res}/images/fronts/market/top.png" />
    </div>
</div>
<!--// top end-->
<jsp:include page="edit_content_header.jsp"/>
<!--// header end-->
<div class="do-body" style="">
<div class="listbody clear">
    <style>
        .listbody .sidebar-l .content{border: 0;background: none;}
    </style>
<div class="sidebar-l">

    <c:if test="${configList!=null}">
        <c:forEach items="${configList}" var="dConfig">
            <div class="k-movecontent" id="${dConfig.refrenceId}" data-id="${dConfig.refrenceId}" data-type="${dConfig.configType}">
                <div class="conbar">
                    <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
                </div>
                <h3 class="title" <c:if test="${!dConfig.showTitle}"> style="display: none;" </c:if>>${dConfig.title}</h3>
                <div class="content">
                        ${dConfig.showTextUnescape}
                </div>
            </div>
        </c:forEach>
    </c:if>

    <div class="lastk-m">
        <div class="btn" id="addmodlebtn">添加模块</div>
        <span class="ml10 font fontcolor6">(请选择需要添加的模块)</span></div>
</div>
    <!--// sidebar-l -->
   <%-- <div class="sidebar-r">
        <!-- 关注度信息 -->
        <jsp:include page="${ctx}/market/brand/attentionNum" >
            <jsp:param name="brandesId"  value="${brandsId}" />
        </jsp:include>

        <!-- 品牌信息 -->
        <jsp:include page="${ctx}/market/brand/brandesInfo" >
            <jsp:param name="brandesId"  value="${brandsId}" />
        </jsp:include>

        <!-- 品牌商基本信息 -->
        <jsp:include page="${ctx}/market/brand/brandInfo" >
            <jsp:param name="brandId"  value="${brandId}" />
            <jsp:param name="brandesId"  value="${brandsId}" />
        </jsp:include>

        <!-- 品牌商旗下所有品牌信息 -->
        <jsp:include page="${ctx}/market/brand/brandesList">
            <jsp:param name="brandId" value="${brandId}"/>
        </jsp:include>

        <!-- 在线联系信息 -->
        &lt;%&ndash;  <jsp:include page="/contactOnline" >
             <jsp:param name="brandesId"  value="${brandsId}" />
         </jsp:include> &ndash;%&gt;
    </div>--%>

    <jsp:include page="${ctx}/market/brand/rightSidebar">
        <jsp:param name="brandId" value="${brandId}"/>
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
    <!--// sidebar-r -->
<div class="ratebar font" style="display: none;">
    <div class="guanzhudu">
        <p>关注度</p>
        <p>124</p>
        <img src="/images/market/guanzhudu.gif" />
    </div>
    <div class="guanzhuwm">
        <p>关注我们</p>
        <img src="/images/market/qrin.gif" />
    </div>
    <div class="online">
        <p>在线资讯</p>
        <p>8:30-17:00</p>
        <img src="/images/market/qq.gif" />
    </div>
    <a href="#" title="" target="" class="joinus">
        <img src="/images/market/joinus.jpg" /></a>
</div>
<div class="hideover"></div>
</div>
</div><!--// do-body -->
<!--// listbody -->
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<!-- // footer -->
<!-- header编辑区域 2014-03-24 -->
<%--edit_content_header.jsp--%>
<!-- nav编辑区域 2014-03-24 -->
<%--edit_content_header.jsp--%>
<!-- 自定义弹窗 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-DIY-boxes">
        <div class="ui-head">
            <h3 class="">自定义模块</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:;"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li class="current">自定义模块</li>
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact">
                    <div class="tips font">
                        自定义标题：<input class="ui-input mr5 js-diyname" style="padding: 0 5px" />
                        显示：<input type="checkbox" checked="checked" class="ui-checkbox mr5 js-diytg" />
                    </div>
                    <script id="myEditordiy" name="intro" type="text/plain"></script>
                    <div class="ui-bottom">
                        <a href="javascript:;" class="edit-common-conserve js-editdiy-save">保存</a>
                        <a href="javascript:;" class="edit-common-cancel js-editdiy-cansle">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input id="diyId" value="" type="hidden"/>
<!-- 添加模块弹窗 -->
<div class="edit-common-style">
    <div class="ui-tanchuang js-addmodle-boxes">
        <div class="ui-head">
            <h3 class="">添加新模块</h3>
            <a class="icon-question-sign" title="查看帮助" href="#"></a>
            <a class="ui-close" href="javascript:;"></a>
        </div>
        <div class="edit-common-content">
            <ul class="jsedit-tabs-menus">
                <li class="current">添加新模块</li>
            </ul>
            <div class="jsedit-tabs-tabcons clear">
                <div class="jsedit-contact">
                    <div class="addmodle-layout font f-l">
                        <span class="addmodle-tips">新模块宽度(885PX)</span>
                        <!-- 用前台的结构，后期要跟前台统一 -->
                        <div class="edit-model-body">
                            <div class="edit-model-header edit-model-bbm">头部导航</div>
                            <div class="edit-model-lsidebar">
                                <div class="edit-model-left edit-model-bbm">左上模块</div>
                                <div class="edit-model-left edit-model-bbm">左中模块</div>
                                <div class="edit-model-left edit-model-bbm">左下模块</div>
                            </div>
                            <div class="edit-model-rsidebar edit-model-bbm">右侧模块</div>
                            <div class="edit-model-footer edit-model-bbm clear">底部模块</div>
                        </div>
                    </div>
                    <div class="addmodle-additem font f-l">
                        <dl class="addmodle-dlitem clear">
                            <dt class="addmodle-dt addmodle-dt-in"></dt>
                            <dd class="addmodle-ddtit">品牌介绍模块</dd>
                            <dd class="addmodle-dddes">说明：添加自己的品牌介绍，文字、图片都可编辑状态图片都可编辑状态</dd>
                        </dl>
                        <%--<dl class="addmodle-dlitem clear">
                            <dt class="addmodle-dt addmodle-dt-show"></dt>
                            <dd class="addmodle-ddtit">品牌介绍模块</dd>
                            <dd class="addmodle-dddes">说明：添加自己的品牌介绍，文字、图片都可编辑状态图片都可编辑状态</dd>
                        </dl>--%>
                        <!--<dl class="addmodle-dlitem clear">
                            <dt class="addmodle-dt addmodle-dt-apply"></dt>
                            <dd class="addmodle-ddtit">品牌介绍模块</dd>
                            <dd class="addmodle-dddes">说明：添加自己的品牌介绍，文字、图片都可编辑状态图片都可编辑状态</dd>
                        </dl>-->
                        <dl class="addmodle-dlitem clear">
                            <dt class="addmodle-dt addmodle-dt-diy"></dt>
                            <dd class="addmodle-ddtit">自定义模块</dd>
                            <dd class="addmodle-dddes">说明：添加自己的品牌介绍，文字、图片都可编辑状态图片都可编辑状态</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<div id="delConfigData" style="display: none;">

</div>--%>
<input id="delConfigUrl" type="hidden" value="/decoration/ajax_delConfig/${brandsId}/">
<input id="updBrandConfigUrl" type="hidden" value="/decoration/ajax_showBrand/${brandsId}/">
<%--品牌介绍隐藏div--%>
<div style="display: none;" id="brandInfoConfigLog_div">
    <div class="k-movecontent_temp" data-id="${brandInfoConfigLog.refrenceId}" data-type="${brandInfoConfigLog.configType}">
        <div class="conbar">
            <div class="btn-group" style="position: absolute; top: 3px; right: 3px"><span class="add-modol btn btn-small btn-inverse">编辑模块</span><span class="btn btn-small btn-inverse" id="k-up">上移</span><span class="btn btn-small btn-inverse" id="k-down">下移</span><span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span></div>
        </div>
        <h3 class="title" <c:if test="${!brandInfoConfigLog.showTitle}"> style="display: none;" </c:if>>${brandInfoConfigLog.title}</h3>
        <div class="content">
            ${brandInfoConfigLog.showTextUnescape}
        </div>
    </div>
</div>
<script type="text/javascript" src="${res}/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${src}/plugin/jqueryui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${src}/fronts/market/jquery.elastislide.js"></script>
<script type="text/javascript" src="${src}/fronts/market/jquery.colorpicker.js"></script>
<script type="text/javascript" src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script type="text/javascript" src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script type="text/javascript" src="${res}/scripts/seajs_config.js"></script>
<script type="text/javascript" src="${res}/scripts/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    var headeredit = {//头部编辑
        init:function(){
            this.headall();
            this.headsave();
            this.editcove();//头部编辑模式覆盖20140421
//            this.imghave();
        },
        headall:function(){
            //百度编辑器
            seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
                UM.getEditor('myEditor2', {
                    initialFrameWidth: 540,
                    initialFrameHeight: 335
                });
            });

            //初始LOGO里面的内容
            /*$(".js-logoname").val($(".logo h2").text());
            $(".js-mainpro").val($(".logo h3").text());*/

            //基础版tab切换
            $(".jsedit-tabs-menus").on("click","li",function(){
                var index = $(this).index();
                $(this).parent().find("li").removeClass("current").eq(index).addClass("current");
                $(this).parent().next().find(".jsedit-contact").hide().eq(index).show();
            })
        },
        headsave:function(){

            //var heardHtml = $(".header-cen").html();
            $(".js-logoname-tg").on("click",function(){
                if($(".js-logoimg-tg").prop("checked")){
                    alert("请先取消“店铺logo设置”的选中状态");
                    return false;
                }
            });
            $(".js-logoimg-tg").on("click",function(){
                if($(".js-logoname-tg").prop("checked")){
                    alert("请先取消“店铺名称”的选中状态");
                    return false;
                }
            });


            $(".js-header-boxes").on("click",".js-header-save",function(){

                var $form = $(this).parents("form");
                var showCate = $form.find("input[name='showCate']").val();

                if ($form.find("input[name='comName']").val() == "") {
                    alert("请填写店铺名称");
                    return;
                }
                var url = "/decoration/ajax_saveHeader/${brandsId}";
                saveHeader($form, showCate,url);

            });
        },
        //所有上传后图片的地址设置
        imghave:function(){
            //图片上传预览的功能推荐用ajax，返回一个链接，再给img的src

            function getObjectURL(file) {
                var url = null ;
                if (window.createObjectURL!=undefined) { // basic
                    url = window.createObjectURL(file) ;
                } else if (window.URL!=undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file) ;
                } else if (window.webkitURL!=undefined) { // webkit or chrome
                    url = window.webkitURL.createObjectURL(file) ;
                }
                return url ;
            }

            $(".file_wrap .input_file").change(function(){
                var objUrl = getObjectURL(this.files[0]) ;
                //alert($(this).val());
                //console.log("objUrl = "+objUrl) ;
                if (objUrl) {
                    //$(".js-logoimgset").attr("src", objUrl) ;
                    $(this).parents(".js-this-fileuploads").find(".edit-form-imgboxs img").attr("src", objUrl) ;
                }
            });
        },
        editcove:function(){
            $(".js-editcove-save").click(function(){
                var $form = $(this).parents("form");
                var showCate = $form.find("input[name='showCate']").val();
                var url = "/decoration/ajax_saveHeader/${brandsId}";
                saveHeader($form, showCate,url);
            });


        }
    };
    headeredit.init();
    var navedit = {//导航编辑
        init:function(){
            //this.navall();
            this.addnav();
            this.navsetcolor();
        },
        navall:function(){
            seajs.use(["jscroll","$"],function(Jscroll,$){
                $('.js_scllobox').jscrollbar({
                    width:8,
                    color:'#ccc',
                    opacity:0.7,
                    position:'inner',
                    mouseScrollDirection:'horizontal'
                });
            })
        },
        addnav:function(){

            //添加导航项
            $("#addnav").on("click",function(){
                $(this).parent().append(
                        '<div class="js-addnavedit" style="border:1px solid #ddd;margin: 0 10px;padding: 5px;">' +
                                '<div style="margin-bottom: 5px;">链接名称： <input class="ui-input js-addnavedit-name" style="width: 300px; padding: 0 5px;" /></div>' +
                                '<div>链接地址： <input class="ui-input js-addnavedit-url" style="width: 300px; padding: 0 5px;" /></div>' +
                                '<div style="margin-top: 5px;">' +
                                '<a href="javascript:;" class="btn btn-mini js-saveaddnav" style="margin-left: 150px;" >保存</a>' +
                                '<a href="javascript:;" class="btn btn-mini js-canceladdnav" style="margin-left: 10px;" >取消</a>' +
                                '</div>'+
                                ''+
                                '</div>');
                $(this).hide();
            });
            $(".js-nav-all").on("click",".js-saveaddnav",function(){//保存
                var $save=$(this);
                var name = $(this).parents(".js-addnavedit").find(".js-addnavedit-name").val();
                var url = $(this).parents(".js-addnavedit").find(".js-addnavedit-url").val();
                if(name=="" ||url==""){
                    alert("请填写‘链接名称’、‘链接地址’");
                    return;
                }
                var order = $("#navUl li").size();
                $save.parents(".js-nav-all").find(".nav-edit-ul").append(
                        '<li class="menu movenav">' +
                                '<a class="menu-link"  href="' + url + '" title="" target="">' + name + '</a>' +
                                '<div class="nav-edit-listate">显示</div>' +
                                '<div class="btn-group f-r">' +
                                '<span class="btn btn-small" id="up">上移</span>' +
                                '<span class="btn btn-small" id="down">下移</span>' +
                                '<span class="btn btn-small addnavedit" id="addnavedit">编辑</span>' +
                                '<span class="btn btn-small navremove" id="remove">删除</span></div>' +
                                '</li>');

                $("#addnav").show();
                $save.parents(".js-addnavedit").hide();
                resert();

            });
            $(".js-nav-all").on("click",".js-canceladdnav",function(){//取消
                $("#addnav").show();
                $(this).parents(".js-addnavedit").hide();
            });

            //编辑导航项
            $(".nav-edit-ul").on("click","#addnavedit",function(){

                var name = $(this).parents(".menu").find(".menu-link").text();
                var url = $(this).parents(".menu").find(".menu-link").attr("href");
//                console.log(name +':'+url);

                $(this).parents(".menu").find("div.js-navamend").remove();//点之前先移除

                $(this).parents(".nav-edit-ul").find("div.js-navamend").remove();//点之前先移除

                $(this).parents(".menu").append(
                        '<div class="js-navamend" style="text-align: center; padding: 5px; color: #999;">' +
                                '<div>链接名称： <input class="ui-input js-navamend-name" style="width: 300px; padding: 0 5px;" /></div>' +
                                '<div style="margin-top: -5px;">链接地址： <input class="ui-input js-navamend-url" style="width: 300px; padding: 0 5px;" /></div>' +
                                '<div style="margin-top: -5px;">' +
                                '<a href="javascript:;" class="btn btn-mini js-amendnavsave">保存</a>' +
                                '<a href="javascript:;" class="btn btn-mini js-amendnavcan" style="margin-left: 10px;">取消</a>' +
                                '</div>'+
                                '</div>');
                $(".js-navamend-name").val(name);
                $(".js-navamend-url").val(url);

            });
            $(".js-nav-all").on("click",".js-amendnavsave",function(){//保存
                var name = $(this).parents(".js-navamend").find(".js-navamend-name").val();
                var url = $(this).parents(".js-navamend").find(".js-navamend-url").val();
                if(name=="" ||url==""){
                    alert("请填写‘链接名称’、‘链接地址’");
                    return;
                }
                var menuId = $(this).parents("li.menu").data("id");
                var $save = $(this);
                $save.parents(".menu").find(".menu-link").text(name);
                $save.parents(".menu").find(".menu-link").attr("href", url);

                $save.parents(".js-navamend").remove();
                $save.parents(".menu").find(".btn-group").show();


            });
            $(".js-nav-all").on("click",".js-amendnavcan",function(){//取消
                $(this).parents(".js-navamend").remove();
                $(this).parents(".menu").find(".btn-group").show();
            });
        },
        navsetcolor:function(){
            /*导航背景颜色*/
            $(".js-nav-boxes").on("click",".js-editnav-save",function(){
                setMenuData();
                $.ajax({
                    type: "POST",
                    url: "/decoration/ajax_saveMenu/${brandsId}",
                    data: $("#navForm").serialize(),
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

                            resetHeader(result,"menu");
                        }
                    }
                })

            });

        }
    };
    navedit.init();
    var DIYedit = {//自定义
        init:function(){
            //this.DIYeditall();
            this.seajsUM();
            this.diysave();
        },
        DIYeditall:function(){

        },
        seajsUM:function(){
            seajs.use(["umeditor","umeditor_config","umdeitor_style"],function(){
                UM.getEditor('myEditordiy', {
                    initialFrameWidth: 540,
                    initialFrameHeight: 335
                });
            });
        },
        diysave:function(){
            $(".js-DIY-boxes .js-editdiy-save").click(function(){
                var divId = $("#diyId").val();
                changeConfig(divId);
            });
        }
    };
    DIYedit.init();
    var addmodleedit = {//添加新模块
        init:function(){
            //this.DIYeditall();
            this.addmodle();
        },
        addmodle:function(){
            function movecontent(){
                $(".sidebar-l .k-movecontent").removeClass("last");
                $(".sidebar-l .k-movecontent:last").addClass("last");
            }
            $('.addmodle-dt-in').click(function(){//品牌介绍添加
                if($("div.sidebar-l div.k-movecontent[data-type='1']").size()>0){
                    alert("品牌介绍模块已存在");
                    return;
                }
                //todo 品牌介绍
                var brandDiv = $("#brandInfoConfigLog_div div.k-movecontent_temp").clone();
                var id = brandDiv.data("id");
                brandDiv.attr("id",id).addClass("k-movecontent");
                var url = $("#updBrandConfigUrl").val()+id;
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    cache: false,
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert(errorThrown);
                    },
                    success: function (result){
                        $('.lastk-m').before(brandDiv);
                        $(".js-addmodle-boxes").hide();
                        $(".lig").hide();
                        movecontent();
                    }
                })

            });
            /* $('.addmodle-dt-show').click(function(){//产品展示添加
             $('.lastk-m').before('<div class="k-movecontent"><div class="conbar"></div><h3 class="title clear">产品展示</h3><div class="productshow"><div id="carousel"class="es-carousel-wrapper"><div class="es-carousel"><ul class="productshowul mainlist piclist"><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li><li><a href=""title=""target=""><img src="/images/market/temp/productshow.jpg"width="230"height="259"/></a></li></ul></div></div></div></div>');
             });*/
            $('.addmodle-dt-diy').click(function(){//自定义模块
                var id = new Date().getTime()+""+Math.floor(Math.random()*100);
                $('.lastk-m').before('<div class="k-movecontent" id="'+id+'">'+
                        '<div class="conbar">'+
                        '<div class="btn-group" style="position: absolute; top: 3px; right: 3px">' +
                        '<span class="add-modol btn btn-small btn-inverse">编辑模块</span>' +
                        '<span class="btn btn-small btn-inverse" id="k-up">上移</span>' +
                        '<span class="btn btn-small btn-inverse" id="k-down">下移</span>' +
                        '' +
                        '<span class="delet-modol btn btn-small btn-inverse" id="k-delet">删除</span>' +
                        '</div>'+
                        '</div>'+
                        '<h3 class="title" >自定义模块</h3>'+
                        '<div class="content" >自定义模块</div>'+
                        '</div>');
                $(".js-addmodle-boxes").hide();
                $(".lig").hide();
                movecontent();
            });
        }
    };
    addmodleedit.init();
});

(function($) {//弹出框，包含触发元素和需要显示的元素
    $.fn.extend({
        "pageDialog":function(options){

            options = $.extend({
                tagger:'.edit',
                showBox:'.js-header-boxes',//需要现实的box
                saveBtn:'.edit-common-conserve',//保存按钮
                cancelBtn:'.edit-common-cancel',//关闭按钮
                closeBtn:'.ui-close'//关闭按钮
            },options);

            $(this).on("click",options.tagger,function(){
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
                if($(".lig").length < 0){
                    $("body").append('<div class="lig"></div>');
                }else{
                    $('.lig').show();
                }

                $(options.showBox).show();
                var divId = $(this).parents("div.k-movecontent").attr("id");
                if(divId!=undefined){
                    $("#diyId").val(divId);
                    $(options.showBox).find("input.js-diyname").val($("#"+divId+" .title").text());
                    if($("#"+divId+" .title:hidden").size()==0){
                        $(options.showBox).find("input.js-diytg").prop("checked",true);
                    }else{
                        $(options.showBox).find("input.js-diytg").prop("checked",false);
                    }
                    UM.getEditor("myEditordiy").setContent($("#"+divId+" .content").html());

                }

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

//调用弹出窗
$(".header-cen").pageDialog({
    tagger:'.edit'
});//头部编辑
$(".nav-cen").pageDialog({//导航编辑
    tagger:'.navedit',
    showBox:'.js-nav-boxes'
});
$(".sidebar-l").pageDialog({//自定义模块
    tagger:'.add-modol',
    showBox:'.js-DIY-boxes'
});
$(".sidebar-l").pageDialog({//底部添加模块
    tagger:'#addmodlebtn',
    showBox:'.js-addmodle-boxes'
});
$(".header-cen").pageDialog({//头部添加模块
    tagger:'.add',
    showBox:'.js-addmodle-boxes'
});
$(".nav-cen").pageDialog({//导航添加模块
    tagger:'.navadd',
    showBox:'.js-addmodle-boxes'
});

//通过jqueryUI设置可拖动模块
$( ".ui-tanchuang").draggable({
    handle:'div.ui-head'
});
//调用颜色选取
$(".js-colorpicker").colorpicker({
    fillcolor:true
});

//【导航移动】设置上下移动时的背景变化
function resert(){
    if($(".js-nav-all .nav-edit-ul").find("li.movenav").size()==1)
    {
        $("li.movenav").removeClass("normal").removeClass("last").removeClass("first").addClass("only");
        $("li.movenav").find("span#up").addClass('disabled');
        $("li.movenav").find("span#sown").addClass('disabled');
    }
    if($(".js-nav-all .nav-edit-ul").find("li.movenav").size()==2)
    {
        $("li.movenav:eq(0)").removeClass("only").removeClass("normal").removeClass("last").addClass("first");
        $("li.movenav:eq(1)").removeClass("only").removeClass("normal").removeClass("first").addClass("last");
        $("li.movenav:eq(0)").find('#up').addClass('disabled');
        $("li.movenav:eq(1)").find('#down').addClass('disabled');
    }
    if($(".js-nav-all .nav-edit-ul").find("li.movenav").size()>=3)
    {
        $("li.movenav:gt(0)").removeClass("only").removeClass("first").removeClass("last").addClass("normal");
        $("li.movenav:first").removeClass("only").removeClass("normal").removeClass("last").addClass("first");
        $("li.movenav:last").removeClass("only").removeClass("normal").removeClass("first").addClass("last");
    }
}
resert();
$('.js-nav-all .nav-edit-ul').each(function(){
    $(document).on("click","#up",function(){
        var onthis = $(this).parent().parent();
        var getup = $(this).parent().parent().prev();
        if($(this).parent().parent().hasClass('first')){
            return false;
        }
        $(getup).before(onthis);
        resert();
    });
    $(document).on("click","#down",function(){
        var onthis = $(this).parent().parent();
        var getdown = $(this).parent().parent().next();
        $(getdown).after(onthis);
        resert();
    });
    $(document).on("click","#remove",function(){
        var onthis = $(this).parent().parent();
        var menuId = onthis.data("id");
        var btnName = onthis.find(".navremove").text();
        if(btnName.indexOf("隐藏")!=-1){
            onthis.find(".navremove").text("显示");
            onthis.find(".nav-edit-listate").text("隐藏");
        }else if(btnName.indexOf("显示")!=-1){
            onthis.find(".navremove").text("隐藏");
            onthis.find(".nav-edit-listate").text("显示");
        }else{
            onthis.remove();
        }
        resert();

    });
});
function changeConfig(divId){
    var diyname = $(".js-diyname").val();//自定义的名称
    var html = UM.getEditor("myEditordiy").getContent();
    var showTitle = $(".js-diytg").prop("checked");
    var configId = $("#"+divId).data("id");
    if(!configId){
        configId="";
    }
    var preIds = [];
    var nextIds = [];
    var $preDiv = $("#"+divId).prevAll(".k-movecontent");
    var preSize = $preDiv.size();
    if(preSize!=0){
        for(var i=preSize-1;i>=0;i--){
            var dataId = $preDiv.eq(i).data("id");
            if(dataId){
                preIds.push(dataId);
            }
        }
    }
    $("#"+divId).nextAll(".k-movecontent").each(function(){
        var dataId = $(this).data("id");
        if(dataId){
            nextIds.push(dataId);
        }
    });

    $.ajax({
        type: "POST",
        url: "/decoration/ajax_saveConfig/${brandsId}",
        data: {title:diyname,showTitle:showTitle,showText:html,refrenceId:configId,preIds:preIds,nextIds:nextIds},
        dataType: "json",
        traditional:true,
        cache: false,
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(errorThrown);
        },
        success: function (result){
            if(result.code  != 126000)
            {
//                remind("error",result.message);
                alert(result.message);
            }else{
                $("#"+divId).attr("data-id",result.object.refrenceId);
                $("#"+divId+" .title").text(diyname);
                $("#"+divId+" .content").html(html);
                if(showTitle==true){
                    $("#"+divId+" .title").show();
                }else{
                    $("#"+divId+" .title").hide();
                }
            }
        }
    })
}
function configSort(){
    var ids = [];
    $(".k-movecontent").each(function(){
        var dataId = $(this).data("id");
        if(dataId){
            ids.push(dataId);
        }
    });
    $.ajax({
        type: "POST",
        url: "/decoration/ajax_configSort/${brandsId}",
        data: {preIds:ids},
        dataType: "json",
        traditional:true,
        cache: false,
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(errorThrown);
        },
        success: function (result){
            if(result.code  != 126000)
            {
//                remind("error",result.message);
                alert(result.message);
            }
        }
    })


}
</script>
<script src="${src}/fronts/market/brandviewindex.js?v=1" type="text/javascript"></script>
<script src="${src}/fronts/decoration/index.js?v=2" type="text/javascript"></script>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
</body>
</html>