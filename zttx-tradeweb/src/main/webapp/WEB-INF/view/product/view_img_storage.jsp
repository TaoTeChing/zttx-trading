<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<!-- 图库上传单独 -->
<div class="cantrol-upload" style="display: none;" id="color_img_storage" >
    <div class="uploadfile_contain js-selectgroup-box">
        <div class="ui-head"><h3>图库选择</h3></div>
        <div class="bd">
            <%--<jsp:include page="${ctx}/brand/image/gallery" />--%>
            <div id="storage_div">
                <div class="choose_con clearfix">
                    <div name="tree"
                         id="colorTree"
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
                                            name="colorSearch"
                                            >搜&nbsp;索</button>
                                </div>
                            </div>
                        </div>
                        <div class="list imgAnotherPage">
                            <ul class="items inline-float js-Color-Url"
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
            </div>
        </div>
        <div class="ta-c">
            <a href="javascript:;" class="ui_button ui_button_mgray js-selectgroup-cansole">取消</a>
        </div>
     </div>
</div>
<script type="text/javascript">

//关于上面点击图片会加到下面的choose_result里面，
//解决方法，imgPage全部换成imgAnotherPage，包括下面的脚本方法。
// 如果有错误，可以换回来试试。
// 暂未发现问题

    function colorImgStorage(){
//        $("div.file_tab_con.choose_con").clone().appendTo($("#storage_div"));
//        $("#color_img_storage div.file_tab_con").removeClass("file_tab_con");

        seajs.use(["pagination","template","ztree"],function(Pagination,template){

            template.helper('$repalce',function (url){
                return  url.replace(/\\/g,'/');

            });
            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });

            $('button[name="colorSearch"]').bind('click',function (){
                if(!flag){
                    return ;
                }
                var imgName = $(this).parent().find("input[name='imgName']").val();
                new Pagination({
                    url: "${ctx}/brand/brandImage/showImgLib?imageName="+imgName,
                    elem: ".list.imgAnotherPage", //插入的容器元素
                    handleData: function(data){
                        if(data.code == zttx.SUCCESS){
                            var html = template.render('address-page', data);
                            $("ul.js-Color-Url").html(html);
                        }
                    }
                });
            });
            var setting = {
                view: {
                    showIcon: false
                },
                callback: {
                    beforeClick: beforeCloClickZtree
                }
            };
            var zNodes = getImageData();
            if(zNodes == -1){
                $('.list.imgAnotherPage .items').html("<font style='color:red;'>您还没有创建属于自己的图片</font>");
                flag = false;
            }else{
                $.fn.zTree.init($("#colorTree"), setting, zNodes);
                var data = getPageData();
                var html = template.render('address-page', data);
                $("ul.js-Color-Url").html(html);
                var p =new Pagination({
                    url: '${ctx}/brand/image/showImgLib',
                    elem: '.list.imgAnotherPage',
                    pageSize:8,
                    total: data.totalPage,
                    handleData: function(data){
                        if (data.code == zttx.SUCCESS) {
                            var html = template.render('address-page', data);
                            $("ul.js-Color-Url").html(html);
                        }
                    }
                });
            }

        })
       $("#color_img_storage div.list.imgAnotherPage").on("click",".item",function(){
            var url = $(this).find("img").data("icon");
            var name = $(this).find("img").attr("name");
            var domainName = $(this).find("img").data("domain");
            showColorImag(url,name,domainName);
        })
    }
    $(function(){
        colorImgStorage();
    });

    function showColorImag(url,name,domainName){
        var html = '<img src='+url+' style="width:90px;height:90px;">'+'</img>';
        html += '<input type="hidden" name="colorImageUrl" value='+url+' />';
        html += '<input type="hidden" name="colorImageOldName" value='+name+' />';
        html += '<input type="hidden" name="colorImgDomainName" value='+domainName+' />';

        $('#show_color_img li').html(html);
    }
    function beforeCloClickZtree(treeId, treeNode){
        seajs.use(["pagination","template"],function(Pagination,template){

            template.helper('$repalce',function (url){
                return  url.replace(/\\/g,'/');

            });
            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });

            new Pagination({
                url: "${ctx}/brand/brandImage/showImgLib?brandImgcateId="+treeNode.id,
                elem: ".list.imgAnotherPage", //插入的容器元素
                pageSize:8,
                handleData: function(data){
                    if (data.code == zttx.SUCCESS) {
                        var html = template.render('address-page', data);
                        $("ul.js-Color-Url").html(html);
                        $('input[name="brandImgcateId"]').eq(0).val(treeNode.id);//此处未修改
                    }
                }
            });
        })
    }
</script>