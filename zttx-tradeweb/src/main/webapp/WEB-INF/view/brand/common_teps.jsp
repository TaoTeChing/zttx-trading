<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="res" value="<%=ZttxConst.FILEAPI_WEBURL%>"/>
<c:set var="imgTotal" value="${empty param.imgTotal ? 6:param.imgTotal}"/>
<style>
    .choose_result .close_btn {
        color: #B4B4B4;
        cursor: pointer;
        font-size: 18px;
        line-height: 1;
        position: absolute;
        right: 0;
        top: 0;
    }

    .uploadfile_contain .ui_tab_items {
        border-top: 0;
        border-right: 0;
        border-bottom: 0;
        background: #FFF;
    }

    .uploadfile_contain .ui_tab .ui_tab_item {
        margin-left: -1px;
        border-top: 1px solid #DDD;
    }

    .uploadfile_contain .ui_tab .ui_tab_item a {
        min-width: 92px;
    }

    .uploadfile_contain .ui_tab_items .current {
        border-top: 2px solid #1D7AD9;
    }

    <c:choose>
    <c:when test="${param.imgRowTotal==5}">
    .uploadfile_contain {
        width: 577px;
    }

    .uploadfile_contain .choose_con .choose_area {
        width: 400px;
    }

    </c:when>
    <c:otherwise>
    .uploadfile_contain .choose_con .choose_area .items {
        margin-left: 42px;
    }

    .productadd .productadd-form .uploadfile_contain .choose_area .items .item {
        margin: 10px 18px 0 0;
    }

    </c:otherwise>
    </c:choose>
</style>

<!-- 通用上传图片容器（本地上传、图库选择） -->
<div style="margin-top: 0;" class="uploadfile_contain js_uploadfile">
    <div class="ui_tab">
        <ul class="ui_tab_items clearfix">
            <li class="ui_tab_item current">
                <a href="javascript:;">本地上传</a>
            </li>
            <li class="ui_tab_item">
                <a href="javascript:;">图库选择</a>
            </li>
        </ul>
    </div>
    <div class="bd">
        <div class="file_tab_con local_con" style="display: block;">
            <label class="" for=""> 选择本地图片：&nbsp;&nbsp;</label>

            <div class="inline-block">
                <div class="file_wrap">
                    <p class="replace_text">文件上传</p>
                    <input type="file" value="文件上传" name="photo" id="galleryPhoto" class="input_file"/>
                </div>
            </div>
            <div class="tips_images">
                <p class="tip">提示：1.本地上传图片大小不能超过<strong>1M</strong>。</p>

                <p class="tip tipint">2.本类目下您最多可以上传<strong>${imgTotal}</strong>张图片。</p>

                <div class="uploadimages">

                </div>
                <c:choose>
                    <c:when test="${param.imgType==1}">
                        <p class="tip">建议上传<strong>900 * 500</strong>尺寸的图片，以达到最好的展示效果</p>
                    </c:when>
                    <c:otherwise>
                        <p class="tip">最少上传一张，建议像素为<strong>800 * 800</strong>的图可以在详情页主图提供图片放大功能（最低不小于400 * 400）</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <jsp:include page="${ctx}/brand/brandImage/image/gallery"/>
        <div class="choose_result">
            <div style="margin: 9px -20px 0 15px;*margin-bottom: 10px;">
            <ul class="items inline-float" id="show_img">
                <c:forEach begin="0" end="${imgTotal-1}" varStatus="status">
                    <li class="item pr">
                        <c:if test="${status.index < brandAlbums.size()}">
                            <c:set value="${brandAlbums.get(status.index).domainName}${brandAlbums.get(status.index).imageName}"
                                   var="url"></c:set>
                            <c:choose>
                                <c:when test="${!empty brandAlbums.get(status.index).changeImagePath}">
                                    <c:set value="${brandAlbums.get(status.index).changeImagePath}" var="url"/>
                                </c:when>
                                <c:otherwise>
                                    <%--<c:set value="${fns:getImageDomainPath(url, 160, 160)}" var="url"/>
                                --%></c:otherwise>
                            </c:choose>
                            <img src="${res}${url}" style="width:90px;height:90px; display:block;">
                            <a class="close_btn iconfont" href="javascript:;">&#xe612;</a>
                            <input type="hidden" name="images" value="${brandAlbums.get(status.index).imageName}">
                            <input type="hidden" name="photoName" value="${brandAlbums.get(status.index).photoName}">
                            <input type="hidden" name="brandAlbumIds"
                                   value="${brandAlbums.get(status.index).refrenceId}">
                        </c:if>
                        <c:if test="${empty brandAlbums || status.index >= brandAlbums.size()}">
                            <span class="no_upload_font">暂未上传</span>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
    	loadImageTree();
        //修改为多图上传
        seajs.use(['uploadify'], function(){

            var imgTotal = ${imgTotal};
            //var imgSizeTotal = $("#show_img li").length;
            var canUploadImg = $("#show_img li").length - $("#show_img li img").length;
            //console.log(imgSizeTotal)
            //console.log(canUploadImg)

            var photoUpload = $('#galleryPhoto').uploadify({
                'formData'     : {
                    fSize: '7',
                    csrfToken: $("input[name=csrfToken]").val()
                },
                'swf'      : window.UPLOADIFY_SWF_URL,
                'uploader' : '${ctx}/common/showImg',
                'fileObjName': 'photo',
                'method': 'post',
                'buttonClass':'buttonClass',
                'buttonText': '上传文件',
                'width': 78,
                'height': 30,
                'queueID'  : 'some_file_queue',
                'uploadLimit': canUploadImg,
                'queueSizeLimit': 6,
                'dataType': 'json',
                //选择上传文件后调用
                'onSelect' : function(file) {
                    //console.log(file);
                },
                //返回一个错误，选择文件的时候触发
                'onSelectError':function(file, errorCode, errorMsg){
                    switch(errorCode) {
                        case -100:
                            alert("上传的文件数量已经超出系统限制的" + imgTotal + "个文件！");
                            break;
                        case -110:
                            alert("文件 ["+file.name+"] 大小超出系统限制的"+ photoUpload.uploadify('settings','fileSizeLimit')+"大小！");
                            break;
                        case -120:
                            alert("文件 ["+file.name+"] 大小异常！");
                            break;
                        case -130:
                            alert("文件 ["+file.name+"] 类型不正确！");
                            break;
                    }
                },
                //检测FLASH失败调用
                'onFallback':function(){
                    alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
                },
                'onUploadSuccess': function(file, data, response){
                    //console.log(file);
                    //console.log(data);
                    data = $.parseJSON(data);
                    galleryShowImg(data.message, data.object);
                    //console.log(response);
                }
            });

            $('#show_img li').on('click', ".close_btn", function () {
                var html = '<span class="no_upload_font">暂未上传</span>';
                html += '<input type="hidden" name="images" value="" />';
                html += '<input type="hidden" name="photoName" value="" />';
                var id = $(this).parents("li").find('input[name="brandAlbumIds"]').attr('value');
                if (id) {
                    html += '<input type="hidden" name="brandAlbumIds" value=' + id + ' />';
                    $(this).parents("li").html(html);
                } else {
                    html += '<input type="hidden" name="brandAlbumIds" value="" />';
                    $(this).parents("li").html(html);
                }

                canUploadImg ++;

                photoUpload.uploadify('settings', 'uploadLimit', canUploadImg);
                photoUpload.uploadify('settings', 'queueSizeLimit', canUploadImg);
                countProductImg();
                changeProductPicPlace();
            });

            //配合表单图片校验部分 fixed 2015-06-15
            //说明：有其他地方上传图片需要校验的，可以增加一个<input type="hidden" id="imgValidate" data-widget-cid="widget-130" data-explain="" value="">再加表单校验代码即可
            function countProductImg(){
                if($("#imgValidate").length > 0){
                    var productImgNum = 0;
                    $("#show_img li").each(function(){
                        if($(this).find("img").length > 0){
                            productImgNum += 1;
                        }
                    });
                    if(productImgNum <= 0){
                        $("#imgValidate").val("");
                    }else{
                        $("#imgValidate").val("Give this value,is important");
                    }
                }
            }
            countProductImg();
            //fixed end

            //bug #5467 产品主图删除移动
            function changeProductPicPlace(){
                var aLi = $('#show_img li');
                var len = aLi.length;
                $("#show_img li").each(function(){
                    if($(this).find("img").length <= 0){
                        aLi.eq(len-1).after($(this));
                    }
                });
            }
            //bug #5467 end

            window.galleryShowImg = function(url, name) {
                setTimeout(function () {
                    var html = '<img src="${res}' + url + '" style="width:100%;height:100%;display:block;" />' + '<a class="close_btn iconfont" href="javascript:;">&#xe612;</a>';
                    html += '<input type="hidden" name="images" value=' + url + ' />';
                    html += '<input type="hidden" name="photoName" value=' + name + ' />';
                    html += '<input type="hidden" name="brandAlbumIds" value="" />';
                    var aLi = $('#show_img li');

                    //配合表单图片校验部分 fixed 2015-06-15
                    if($("#imgValidate").length > 0){
                        $("#imgValidate").val("Give this value,is important").trigger('blur');
                    }
                    //fixed end

                    for (var i = 0; i < aLi.size(); i++) {
                        if ($(aLi[i]).find('img').attr('src')) {

                        } else {
                            if ($(aLi[i]).find('input[name="brandAlbumIds"]').attr('value')) {
                                var img = '<img src="${res}' + url + '" style="width:100%;height:100%;display: block;" />';
                                if ($(aLi[i]).find("img").length > 0) {
                                    $(aLi[i]).find("img").attr("src", '${res}' + url);
                                } else {
                                    img += '<a class="close_btn iconfont" href="javascript:;">&#xe612;</a>';
                                    $(aLi[i]).append(img);
                                }
                                $(aLi[i]).find(".no_upload_font").remove();
                                $(aLi[i]).find('input[name="images"]').val(url);
                                $(aLi[i]).find('input[name="photoName"]').val(name);
                                break;
                            } else {
                                $(aLi[i]).html("");
                                $(aLi[i]).append(html);
                                break;
                            }
                        }
                    }
                }, 0);
            }
        });
    });
</script>