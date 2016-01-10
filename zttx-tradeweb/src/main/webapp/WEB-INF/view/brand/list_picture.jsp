<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-图片管理-图片管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/classification.css" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">图片管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 图片管理  -->
            <div class="main_classification">
                <div class="ui_tabs">
                    <div class="ui_tabsmenu">
                        <ul class="clearfix">
                            <li class="selected"><a href="${ctx}/brand/brandImage/image">图片管理</a>
                            </li>
                            <li><a href="${ctx}/brand/brandImage/image/upload_picture">图片上传</a>
                            </li>
                            <li><a href="${ctx}/brand/brandImgcate/imgcate">分类管理</a>
                            </li>
                            <li><a href="${ctx}/brand/brandImage/image/recycle_picture">回收站</a>
                            </li>
                        </ul>
                    </div>
                    <form:form id="imageForm" method="post" action="${ctx}/brand/brandImage/image">
                        <div class="ui_tabsbox">
                            <!-- 图片管理：图片管理 -->
                            <div class="picmanage">
                                <c:set var="condition" value="${conditions}"/>
                                <div class="search">
                                    <span class="picmanageTit">分类：</span>
                                    <div class="inline-block">
                                        <select id="category" name="cateId" class="ui-select js_select">
                                            <option value="">全部</option>
                                            <c:forEach var="obj" items="${listCate}">
                                                <option value="${obj.id}" ${condition.cateId==obj.id?"selected":""}>${obj.name}</option>
                                                <c:forEach var="obj" items="${obj.children}">
                                                    <option value="${obj.id}" ${condition.cateId==obj.id?"selected":""}>┠┄┄${obj.name}</option>
                                                </c:forEach>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="inline-block">
                                        <span class="picmanageTit updata">上传日期：</span>
                                        <input class="text date str-date hasDatepicker" id="startCal" readonly="readonly" name="startTimeStr"
                                               value='${fns:getStringTime(condition.startTime,"yyyy-MM-dd") }' />
                                        - <input class="text date end-date hasDatepicker" id="endCal"
                                                 readonly="readonly" name="endTimeStr"
                                                 value='${fns:getStringTime(condition.endTime,"yyyy-MM-dd") }' />
                                    </div>
                                    <div class="inline-block">
                                        <span class="picmanageTit">图片名：</span>
                                        <input type="text" value="${condition.photoName}" class="textLar" placeholder="输入文本" name="photoName"/>

                                    </div>
                                    <a class="searchbtn simple_button" onclick="goSubmit();">搜索</a>
                                </div>
                                <div class="checkallbox">
                                    <div class="inline-block fl">
                                        <label>
                                            <input type="checkbox" class="ui-checkbox chkALL" name="allchecked" >
                                            全选
                                        </label>
                                    </div>
                                    <input id="copy_links_hidden" value="" type="hidden" />
                                    <input id="copy_codes_hidden" value="" type="hidden" />
                                    <span class="checkstyle fl copythislink" data-clipboard-target="copy_links_hidden" >复制链接</span>
                                    <span class="checkstyle fl copythiscode" data-clipboard-target="copy_codes_hidden">复制代码</span>
                                    <a class="checkstyle fl js-move">移动</a>
                                    <a class="checkstyle fl" id="delselect">删除</a>
                                        <%--<a class="checkstyle fr" href="javascript:$Z.goNext(imageForm)">下一页</a>
                                        <a class="checkstyle fr" href="javascript:$Z.goPrevious(imageForm);">上一页</a>--%>
                                </div>

                                <c:choose>
                                    <c:when test="${!empty pageResult.list}">
                                        <ul class="contant contant1 clearfix">
                                            <c:forEach var="image" items="${pageResult.list}" varStatus="sta">
                                                <li class="contantli">
                                                    <input id="copy_link_${image.refrenceId}_hidden" value='${res}${image.domainName}${image.imageName}' type="hidden" />
                                                    <input id="copy_code_${image.refrenceId}_hidden" value='<img src="${res}${image.domainName}${image.imageName}" />' type="hidden" />
                                                    <div class="imgbox">
                                                        <a target="_blank" href="${res}${image.domainName}${image.imageName}">
                                                            <c:set value="${image.domainName}${image.imageName}" var="url"></c:set>
                                                            <img src="${res}${fns:getImageDomainPath(url, 175, 175)}" style="width:175px;height:175px;" id="pic_${image.refrenceId}" delValue="${image.refrenceId}"/>
                                                        </a>
                                                        <p class="title">
                                                            <a id="title_${image.refrenceId}" target="_blank" href="${res}${image.domainName}${image.imageName}">${fn:substring(image.photoName,0,10)}</a>
                                                        </p>
                                                    </div>
                                                    <div class="fn">
                                                        <p>
                                                            复制：<span class="bluefont flash_copy"  data-clipboard-target="copy_code_${image.refrenceId}_hidden">代码</span>|<span class="bluefont flash_copy" data-clipboard-target="copy_link_${image.refrenceId}_hidden">链接</span>
                                                        </p>
                                                        <div class="inline-block">
                                                            <input type="checkbox" class="ui-checkbox newclasschk" name="piclist" value="${image.refrenceId}">
                                                        </div>
                                                        <div class="file_wrap inline-block">
                                                            <p class="replac bluefont">替换</p>
                                                            <input type="file" value="文件上传" name="photo" class="input_file" id="${image.refrenceId}">
                                                        </div>
                                                        <span class="bluefont  deleteFile" revenId="${image.refrenceId}">删除</span>
                                                        <a class="detail" target="_blank" href="${res}${image.domainName}${image.imageName}"><span class="bluefont">详情</span></a>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="ta-c">暂无数据</div>
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${!empty pageResult.list}">
                                    <div class="pagination">
                                        <tags:page form="imageForm" page="${pageResult.page}"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<div style="display: none;">
    <div class="js-setmover">
        <div class="ui-head">
            <h3>移动</h3>
        </div>
        <div>
            <div class="ui-form-item">
                <label>移动到：</label>
                <select id="js-move-cate" style="width: 160px;">
                    <c:forEach var="obj" items="${listCate}">
                        <option value="${obj.id}" ${condition.cateId==obj.id?"selected":""}>${obj.name}</option>
                        <c:forEach var="obj" items="${obj.children}">
                            <option value="${obj.id}" ${condition.cateId==obj.id?"selected":""}>┠┄┄${obj.name}</option>
                        </c:forEach>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="ta-c">
            <a class="simple_button confirm_btn" href="javascript:void(0);">确定</a>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/classification.js"></script>
<script>
    pictureManagement1.init();
</script>
<script>
    /**
     绑定替换图片事件
     **/
    $(function (){
        $('input[name="photo"]').bind('change', function(){uploadImage($(this).attr('id'));});
        seajs.use(['dialog'],function(Dialog){
            var dialog = new Dialog({
                trigger:'.js-move',
                effect: 'fade',
                hasMask:false,
                content: $(".js-setmover"),
                width:280
            })
        });

        $(document).on('click','.js-setmover .ta-c a',function(){
            var ids = getSelectedIds();
            if(ids.length == 0)
            {
                remind("error","请选择要移动的图片");
                return;
            }
            var newCateId = $("#js-move-cate").val();
            $.post("${ctx}/brand/brandImage/image/move", $.param({ids: ids, newCateId: newCateId}, true), function(data){
                if(data.code == zttx.SUCCESS){
                    window.location.reload();
                }else{
                    remind("error",data.message, function(){
                        window.location.reload();
                    });
                }
            }, "json");
        });
    });


    rangeCalendar("startCal","endCal");

    function showImage(uploadId, url){
        var pic=$("#pic_"+uploadId);
        pic.attr("src","${res}"+url);
        pic.parent().attr("href","${res}"+url);
        pic.parents(".contantli").find(".detail").attr("href","${res}"+url);
        $("#copy_link_"+uploadId+"_hidden").val("${res}${image.domainName}"+url);
        $("#copy_code_"+uploadId+"_hidden").val('<img src="${res}${image.domainName}'+url+'" />');
        $("#title_"+uploadId).attr("href","${res}"+url);
    }
    /**
     上传图片
     **/
    function uploadImage(uploadId){
        seajs.use(["$","ajaxFileUpload"],function($){
            $.ajaxFileUpload({
                type: "post",
                url: '${ctx}/brand/brandImage/image/replace?imgId='+uploadId,
                secureuri: false,
                fileElementId: uploadId,
                dataType: 'json',
                success: function(data)
                {
                    //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                    $('#' + uploadId).bind('change', function(){
                        uploadImage(uploadId);
                    });
                    if(data.code == zttx.SUCCESS){
                        showImage(uploadId,data.message);
                    }else{
                        remind("error",data.message);
                    }
                }
            });
        });
    }

    $(function (){
        //删除
        $("span.deleteFile").on('click', function(){
            var url="${ctx}/brand/brandImage/image/delete";
            var uuid=$(this).attr("revenId");
            var jsonStr='["'+uuid+'"]';
            var obj={title:"提示",content:"确定删除吗"};
            confirmDialog(obj,function(){
                $.ajax({
                    type:"post",
                    url:url,
                    data:jsonStr,
                    dataType: "json",
                    contentType:"application/json;charset=UTF-8",
                    success:function(data){
                        if(data!=null){
                            remind("success",data.message,function(){
                                location.href = location.href;
                            });
                        }
                    }
                });
            });
        });
    });
    //搜索提交表单
    function goSubmit(){

        $("#imageForm").submit();
    }
    // 批量删除
    var strs="[";
    $("#delselect").click(function(){
        strs="[";
        $('[name=piclist]:checkbox:checked').each(function(index){

            index++;
            strs+='"'+$(this).val()+'"';
            if(index!= $('[name=piclist]:checkbox:checked').length){
                strs+=","
            }

        })
        strs+= "]"
        //ajax 提交
        var url="${ctx}/brand/brandImage/image/delete";
        var jsonStr=strs;
        if(jsonStr.indexOf("[]")!=-1){
            remind("error","请选择要删除的项");
            return;
        }
        var obj={title:"提示",content:"确定批量删除吗"};
        confirmDialog(obj,function(){
            $.ajax({
                type:"post",
                url:url,
                data:jsonStr,
                contentType:"application/json;charset=UTF-8",
                dataType: "json",
                success:function(data){
                    if(data!=null){
                        remind("success", data.message,function(){
                            location.href = location.href;
                        });
                    }
                }
            });
        });
    })

    function getSelectedIds(){
        var ids = [];
        $('[name=piclist]:checkbox:checked').each(function(index){
            ids[ids.length] = $(this).val();
        })
        return ids;
    }
</script>
</body>
</html>