<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!-- 注意：在实际使用过程中，此样式代码不能复制，不然会导致样式冲突 -->
<style>
    .uploadfile_contain{
        margin-top: 20px;
    }
    .pagination{
        margin-top: 20px;;
    }
</style>

<div class="file_tab_con choose_con clearfix">
    <div name="tree"
         id="tree"
         class="tree_contain" style="width:150px;">
    </div>
    <div class="choose_area">
        <div class="search_bar clearfix">
            <div class="search_form">
                <div class="search_box fl">
                    <i class="iconfont">&#xe600;</i>
                    <div class="search_input fl">
                        <input placeholder="输入关键字" type="text"
                        <%--id="imgName"--%> name="imgName"
                                />
                    </div>
                    <input name="brandImgcateId" type="hidden" value=""/>
                    <button type="button"
                    <%--id="search"--%>
                            name="search"
                            >搜&nbsp;索</button>
                </div>
            </div>
        </div>
        <div class="list imgPage">
            <ul class="items inline-float"
            <%--id="imgList"--%>
                name="imgList">
                <li class="item"></li>
                <li class="item"></li>
                <li class="item"></li>
                <li class="item"></li>
                <li class="item"></li>
                <li class="item"></li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    var imgTreeNode="";
    function getImageData(){
        return ${menuTree};
    }
    function getPageData(){
        return ${page};
    }

    var flag = true;
    function loadImageTree(){
        $(".ztreeP span.toggleH").click(function(){
            $(".zatree").toggle();
        });
        $('input[name="imgName"]').bind('keypress',function (e){
            var event = window.event || e;
            if(event.keyCode == 13){
                return false;
            }
        });
        seajs.use(["pagination","template","ztree"],function(Pagination,template){

            template.helper('$repalce',function (url){
                return  url.replace(/\\/g,'/');

            });
            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });

            $('button[name="search"]').bind('click',function (){ //此处为之前的绑定方法，下面为后来的绑定方法
                //$(".productadd").on("click","button[name='search']",function(){
                if(!flag){
                    return ;
                }
                var imgName = $(this).parent().find("input[name='imgName']").val();
                new Pagination({
                    url: "${ctx}/brand/brandImage/showImgLib?imageName="+imgName+"&brandImgcateId="+imgTreeNode,
                    elem: ".list.imgPage", //插入的容器元素
                    pageSize: 8,
                    handleData: function(data){
                        if(data.code == zttx.SUCCESS){
                            var html = template.render('address-page', data);
                            $("ul[name='imgList']").html(html);
                        }
                    }
                });

            });

            var setting = {
                view: {
                    showIcon: false
                },
                /* async: {
                 enable: true,
                 url:"getZtreeData",
                 autoParam:["id"]
                 },*/
                callback: {
                    beforeClick: beforeClickZtree
                }

            };

            var zNodes = getImageData();
            if(zNodes == -1){
                $('.list.imgPage .items').html("<font style='color:red;'>您还没有创建属于自己的图片</font>");
                flag = false;
            }else{
                $.fn.zTree.init($("#tree"), setting, zNodes);
                var data = getPageData();
                var html = template.render('address-page', data);
                $("ul[name='imgList']").html(html);

                new Pagination({
                    url: "${ctx}/brand/brandImage/showImgLib",
                    elem: ".list.imgPage", //插入的容器元素
                    pageSize: 8,
                    handleData: function(data){
                        if(data.code == zttx.SUCCESS){
                            var html = template.render('address-page', data);
                            $("ul[name='imgList']").html(html);
                        }
                    }
                });
                
            }

        })

        $(".list.imgPage").on("click",".item",function(){
            var url = $(this).find("img").data("icon");
            var name = $(this).find("img").attr("name");
            var domainName = $(this).find("img").data("domain");
            galleryShowImg(domainName+url, name);
        })
    }
        
    function galleryShowImg(url, name) {
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


    //单击事件
    function beforeClickZtree(treeId, treeNode){
        imgTreeNode=treeNode.id
        seajs.use(["pagination","template"],function(Pagination,template){
            template.helper('$repalce',function (url){
                return  url.replace(/\\/g,'/');

            });
            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });

            var p = new Pagination({
                url: "${ctx}/brand/brandImage/showImgLib?brandImgcateId="+treeNode.id,
                elem: ".list.imgPage", //插入的容器元素
                pageSize:8,
                handleData: function(data){
                    if (data.code == zttx.SUCCESS) {
                        var html = template.render('address-page', data);
                        $("ul[name='imgList']").html(html);
                        $('input[name="brandImgcateId"]').eq(0).val(treeNode.id);
                    }
                }
            });
        });
        //清空搜索框
        $("input[name=imgName]").val("");
    }

    function address_page(data){

    }
</script>
<script id="address-page" type="text/html">

    {{each rows}}
    <li class="item">
        <div class="img_contain">
            <img style="width:88px;height:88px;" src="${res}{{$getImageDomainPathFn $value.domainName+$value.imageName 90 90}}" name="{{$value.photoName}}" data-icon="{{$value.imageName}}" data-domain="{{$value.domainName}}" />
        </div>
    </li>
    {{/each}}
</script>