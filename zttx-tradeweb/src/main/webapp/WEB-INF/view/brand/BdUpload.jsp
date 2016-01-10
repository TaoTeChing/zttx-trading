<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<style>
    /*@charset "UTF-8";*/
    /*
    ** 单独图库样式
    */
    .uploadfile-con-alone .ui_tab .ui_tab_item a {
        min-width: 92px;
    }
    .uploadfile-con-alone .uploadfile-alone-bd {
        border: #d9d9d9 solid 1px;
        min-height: 200px;
        _height: 200px;
        background: #fff;
        position: relative;
        margin-top: -1px;
        z-index: 22;
    }
    .uploadfile-con-alone .file_tab_con {
        display: none;
    }
    .uploadfile-con-alone .local_con {
        padding: 20px;
        height: 200px;
    }
    .uploadfile-con-alone .local_con .file_wrap {
        width: 76px;
        height: 28px;
        line-height: 28px;
        border-radius: 3px;
        background: #f5f5f5;
        border: #b7b4b2 solid 1px;
    }
    .uploadfile-con-alone .local_con .file_wrap .input_file {
        width: 76px;
        height: 28px;
    }
    .uploadfile-con-alone .local_con .tips_images {
        margin: 10px 0 0 103px;
    }
    .uploadfile-con-alone .local_con .tip {
        line-height: 23px;
    }
    .uploadfile-con-alone .local_con .tip strong {
        color: #e60110;
        font-weight: normal;
        font-family: "Arial";
    }
    .uploadfile-con-alone .local_con .tipint {
        text-indent: 36px;
    }
    .uploadfile-con-alone .local_con .uploadimages {
        margin: 20px 0 10px 0;
    }
    .uploadfile-con-alone .local_con .uploadimages li {
        width: 90px;
        height: 90px;
        border: 1px solid #ccc;
        margin-right: 10px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .tree_contain {
        float: left;
        display: inline;
        width: 158px;
        border-right: #d9d9d9 solid 1px;
        height: 274px;
        overflow: auto;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area {
        _position: relative;
        margin-left: 159px;
        height: 274px;
        overflow: auto;
        overflow-x: hidden;
        padding-left: 10px;
        _padding-left: 7px;
        width: 475px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .items {
        margin-right: -10px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .item {
        width: 90px;
        height: 90px;
        border: #ccc solid 1px;
        margin: 10px 10px 0 0;
        position: relative;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .item i {
        position: absolute;
        left: -1px;
        top: -1px;
        background: url("/images/brand/common_icon.png") -40px -24px no-repeat;
        width: 20px;
        height: 20px;
        display: none;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .current {
        border-color: #64a5ff;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .current i {
        display: block;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .uploadfile-alone-area .img_contain {

    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_bar {
        margin-top: 10px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_bar .operate {
        float: left;
        margin-top: 5px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form {
        float: right;
        height: 22px;
        background: #fff;
        position: relative;
        border: #e0e0e0 solid 1px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form .search_box {
        height: 22px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form .iconfont {
        position: absolute;
        top: 0px;
        left: 4px;
        font-size: 16px;
        color: #d9d9d9;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form .search_input {
        margin-left: 22px;
        height: 22px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form .search_input input {
        border: 0 none;
        padding: 3px 0;
        width: 142px;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form button {
        border: 0 none;
        background: #ff8900;
        font: 12px "微软雅黑";
        height: 24px;
        width: 58px;
        color: #fff;
        margin: -1px -1px 0 0;
    }
    .uploadfile-con-alone .uploadfile-alone-choose .search_form button:hover {
        background: #f57503;
    }
    .uploadfile-con-alone .choose_result {
        clear: both;
        height: 110px;
        border-top: #d9d9d9 solid 1px;
        background: #f8f8f8;
    }
    .uploadfile-con-alone .choose_result .items {
        margin: 9px -20px 0 15px;
    }
    .uploadfile-con-alone .choose_result .item {
        width: 90px;
        height: 90px;
        border: #ccc solid 1px;
        margin-right: 20px;
    }
    .uploadfile-con-alone .choose_result .item .img_contain {
        padding: 1px;
    }
    .edui-image-searchRes{ display: none;}
    .uploadfile-con-alone .pagination{ padding-top: 10px;}
</style>
<!-- 百度编辑器图片内嵌图库 -->
<div class="cantrol-upload" id="BD_img_storage" >
    <div class="uploadfile-con-alone">
        <div class="uploadfile-alone-bd">
            <%--<jsp:include page="${ctx}/brand/image/gallery" />--%>
            <div id="storage_div<%-- uploadfile-alone-storage--%>">
                <div class="clearfix uploadfile-alone-choose">
                    <div name="tree" id="BDTree" class="tree_contain" style="width:150px;">

                    </div>
                    <div class="uploadfile-alone-area">
                        <div class="search_bar clearfix">
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
                                        name="BDsearch"
                                        >搜&nbsp;索</button>
                            </div>
                        </div>
                        <div class="entry imgBDPage js-entry">
                            <ul class="items inline-float js-items"
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
    </div>
</div>
<script type="text/javascript">
    //此处缺少数据来源 getImageData() 和 getPageData() 来源于当前页面其他地方
    //function BDimgData(){}
    //function BDpageData(){}
    var imgTreeNode="";
	function getImageData(){
          return ${menuTree};
      }
      function getPageData(){
          return ${page};
      }
    var flag = true;
    seajs.use(["pagination","template"],function(Pagination,template){
        template.helper('$repalce',function (url){
            return  url.replace(/\\/g,'/');

        });
        template.helper('$getImageDomainPathFn',function (url, width, height){
            return getImageDomainPath(url, width, height);
        });
    });

    function BDImgStorage(){//此处为 搜索 、 ztree 、分页、以及单击图片的事件

        $(".ztreeP span.toggleH").click(function(){
            $(".zatree").toggle();
        });
        $('input[name="imgName"]').bind('keypress',function (e){
            var event = window.event || e;
            if(event.keyCode == 13){
                return false;
            }
        });

        $("#BD_img_storage").on("click","button[name='BDsearch']",function(){//搜索
            if(!flag){
                return ;
            }
            var imgName = $(this).parent().find("input[name='imgName']").val();

            seajs.use(["pagination","template"],function(Pagination,template){
                var p = new Pagination({
                    //url: "${ctx}/brand/image/showImgLib?imageName="+imgName,
                    url: "${ctx}/brand/image/showImgLib?imageName="+imgName+"&brandImgcateId="+imgTreeNode,
                    elem: ".js-entry.imgBDPage", //插入的容器元素
                    handleData: function(data){
                        if(data.code == zttx.SUCCESS){
                            var html = template.render('address-BDpage', data);
                            $("ul.js-items").html(html);
                        }
                    }
                });
            });
        });
        seajs.use(["$","ztree"],function($,_){
            var setting = {
                view: {
                    showIcon: false
                },
                callback: {
                    beforeClick: beforeClickBDZtree
                }
            };

            var zNodes = getImageData();
            if(zNodes == -1){

                $('.js-entry.imgBDPage .js-items').html("<font style='color:red;'>您还没有创建属于自己的图片</font>");
                flag = false;
            }else{
                $.fn.zTree.init($("#BDTree"), setting, zNodes);
                seajs.use(["pagination","template"],function(Pagination,template){
                    var data = getPageData();
                    var html = template.render('address-BDpage', data);
                    $("ul.js-items").html(html);
                    var p =new Pagination({
                        url: '${ctx}/brand/image/showImgLib',
                        elem: '.js-entry.imgBDPage',
                        page:12,
                        total: data.totalPage,
                        handleData: function(data){
                            if (data.code == zttx.SUCCESS) {
                                var html = template.render('address-BDpage', data);
                                //alert(html);
                                $("ul.js-items").html(html);
                            }
                        }
                    });
                });
            }

        });

        $("#BD_img_storage div.js-entry.imgBDPage").on("click",".item",function(){
            if($(this).hasClass("current")){
                $(this).removeClass("current").find("i").remove();
            }else{
                $(this).addClass("current").append("<i></i>");
            }
        });
    }
    $(function(){
        BDImgStorage();
    });

    function beforeClickBDZtree(treeId, treeNode){//此处为单击 ztree 项的时候执行的函数
        imgTreeNode=treeNode.id;
        seajs.use(["pagination","template"],function(Pagination,template){
            var p = new Pagination({
                url: "${ctx}/brand/image/showImgLib?brandImgcateId="+treeNode.id,
                elem: ".js-entry.imgBDPage", //插入的容器元素
                handleData: function(data){
                    if (data.code == zttx.SUCCESS) {
                        var html = template.render('address-BDpage', data);
                        $("ul.js-items").html(html);
                        $('input[name="brandImgcateId"]').eq(0).val(treeNode.id);//此处未修改和原来的一样，不知道有何作用
                    }
                }
            });
        })
    }
</script>
<%-- 下面是图片显示的模版 --%>
<script id="address-BDpage" type="text/html">
    {{each rows}}
    <li class="item">
        <div class="img_contain">
            <img style="width:90px;height:90px;" src="${res}{{$getImageDomainPathFn $value.domainName+$value.imageName 90 90}}" name="{{$value.photoName}}" data-icon="${res}{{$value.imageName}}" data-domain="{{$value.domainName}}" />
        </div>
    </li>
    {{/each}}
</script>
