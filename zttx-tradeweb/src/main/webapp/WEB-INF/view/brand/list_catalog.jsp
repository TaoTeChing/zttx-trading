<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-分类管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
    <style>
        .ztree li a span{
            overflow: hidden;
            text-overflow: ellipsis;
            width: 156px;
            display: inline-block;
        }
    </style>
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">分类管理</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
            </div>
        </div>
        <div class="inner" style="padding-top: 1px;">
            <div class="tips">
                <i class="v2-icon-explain"></i>
                说明：您可根据产品材质、用途等信息设置不同的分类
            </div>
            <!-- 选择品牌 -->
            <form:form action="" method="post" id="catelogForm" name="catelogForm">
                <input type="hidden" id="imgRefrenceId"/>
                <div class="selectbar">
                    <span>选择品牌:</span>
                    <div class="inline-block">
                        <select class="js_select" name="brandsId" id="brandsId" onchange="$('#catelogForm').submit();">
                            <c:forEach items="${brandesInfoList}" var="info">
                                <option value="${info.refrenceId}" <c:if test="${info.refrenceId==brandsId}">selected</c:if>>${info.brandsName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form:form>
            <!-- tabs切换 -->
            <div class="jstabs_t">
                <div class="jstabsmenu_t clearfix">
                    <ul class="clearfix">
                        <li ><a href="${ctx}/common/productCatalog?menuId=<%=request.getParameter("menuId")%>&subMenuId=<%=request.getParameter("subMenuId")%>">分类设置</a></li>
                        <li class="selected"><a href="${ctx}/common/productCatalog/list?menuId=<%=request.getParameter("menuId")%>&subMenuId=<%=request.getParameter("subMenuId")%>">产品分类</a></li>
                    </ul>
                </div>
                <div class="tabsbox_t">
                    <div class="productsmanage clearfix">
                        <div class="productsmanage-classic fl"><!-- tree 里面的东西 -->
                            <ul id="tree"></ul>
                            <c:if test="${brandesInfoList.size() == '0'}">
                                <div style="margin: 80px 0 0 80px;">暂无数据</div>
                            </c:if>
                        </div>
                        <div class="productsmanage-classic-body">
                            <form:form id="catelogSearchForm">
                                <%--<input type="hidden" name="menuId" value="<%=request.getParameter("menuId")%>"/>
                                <input type="hidden" name="subMenuId" value="<%=request.getParameter("subMenuId")%>"/>
                                --%><input type="hidden" name="brandsId" value="${brandsId}"/>
                                <input type="hidden" name="cateId" id="cateId"/>
                                <div class="checkdel clearfix">
                                    <div class="fl">
                                        <input type="checkbox" class="js_chk ui-checkbox mr5 chkall" id="setdef21">
                                        <label class="checkall" for="setdef21">全选</label>
                                        <a href="javascript:void(0);" class="delallspan js-sel-toclass">批量分类</a>
                                        <a href="javascript:delCatalog('0');" class="delallspan">批量清空</a>
                                    </div>
                                    <div class="fr">
                                        <input type="text" placeholder="请输入产品货号..." class="ui-input" name="productNo"/>
                                        价格
                                        <input type="text" class="ui-input short-width" name="minPrice" />
                                        -
                                        <input type="text" class="ui-input short-width" name="maxPrice"/>
                                        <a href="javascript:search();" class="simple_button">搜索</a>
                                    </div>
                                </div>
                            </form:form>


                            <div id="pagination" class="mt10"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<input type="hidden" id="selProductId" value=""/>
<!-- 批量分类弹出层 -->
<div class="toclass-edit-area batch-showbox js-toclass-showbox" style="display: none">
    <div class="toclass-edit-area-arrow">
        <em></em>
        <span></span>
    </div>
    <c:choose>
        <c:when test="${cataMap.size() > 0 }">
            <c:forEach items="${cataMap}" var="catalog">
                <label class="fn-text-overflow" title="${catalog.value.cateName}">
                    <input type="checkbox" class="ui-checkbox" value="${catalog.key}"/>${catalog.value.cateName}
                </label>
            </c:forEach>
            <a href="javascript:addCate('0','.js-toclass-showbox',true);" class="simple_button">应用</a>
        </c:when>
        <c:otherwise>
            <p class="ta-c">请先添加分类</p>
        </c:otherwise>
    </c:choose>

</div>
<!-- 批量分类弹出层 结束 -->
<!-- 添加分类弹出层  -->
<div class="toclass-edit-area js-addclass-showbox" style="display: none">
    <div class="toclass-edit-area-arrow">
        <em></em>
        <span></span>
    </div>

    <c:choose>
        <c:when test="${cataMap.size() > 0 }">
            <c:forEach items="${cataMap}" var="catalog">
                <label class="fn-text-overflow" title="${catalog.value.cateName}">
                    <input type="checkbox" class="ui-checkbox" value="${catalog.key}"/>${catalog.value.cateName}
                </label>
            </c:forEach>
            <a href="javascript:addCate('1','.js-addclass-showbox');" class="simple_button">应用</a>
        </c:when>
        <c:otherwise>
            <p class="ta-c">请先添加分类</p>
        </c:otherwise>
    </c:choose>



</div>
<!-- 添加分类弹出层  -->
<script id="feedback-templage" type="text/html">
    <table class="productsmanage-classic-table">
        <colgroup>
            <col width="375" />
            <col width="160" />
            <col width="100" />
            <col width="140" />
        </colgroup>
        <thead>
        <tr>
            <th>产品信息</th>
            <th>所属分类</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{each rows}}
        <tr>
            <td>
                <div class="inline-block fl"><input type="checkbox" class="js_chk" value="{{$value.refrenceId}}" /></div>
                <div class="inline-block fl"><img src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}" style="width:70px;height:70px;" alt="" class="imgbox" /></div>
                <div class="inline-block fl classic-td-titwid">
                    <p>货号：{{$value.productNo}}</p>
                    <p>{{$value.productTitle}}</p>
                </div>
            </td>
            <td>
                <div class="scllobarbox jscrollbar">
                    <ul>
                        {{each $value.productCatalogList}}
                        <li class="fn-text-overflow" title="{{$value.cateName}}" data_id="{{$value.refrenceId}}">{{$value.cateName}}</li>
                        {{/each}}
                    </ul>
                </div>
            </td>
            <td class="classictd-tc">
                <strong>{{formatNumber $value.productPrice}}</strong>
            </td>
            <td class="classictd-tc">
                <a href="javascript:void(0);" class="bluefont js-addclass-triggerbtn" data_id="{{$value.refrenceId}}">添加分类</a>
                <a href="javascript:delCatalog('1','{{$value.refrenceId}}');" class="bluefont">移除分类</a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/productmanage.js"></script>
<script>

    productmanageclass.jstoclass();

    productmanageclass.setCatalogFn=function(obj){
        $("#selProductId").val($.trim(obj.attr("data_id")));
        var trObj = obj.parents("tr");
        var ckxObj = $(".js-addclass-showbox input:checkbox");
        ckxObj.removeAttr("checked");
        trObj.find(".scllobarbox li").each(function(){;
            var catalogId=$.trim($(this).attr("data_id"));
            ckxObj.each(function(){
                if(catalogId==$.trim($(this).val())){
                    $(this).prop("checked","true");
                }
            });
        });
    };

    var zTreeObj,zNodes;
    var page;
    $(function () {
        seajs.use(["pagination", "template", "jscroll"], function (Pagination,template) {

            template.helper('$getImageDomainPathFn',function (url, width, height){
                return getImageDomainPath(url, width, height);
            });
            template.helper('formatNumber',function (price){
                if(isNaN(price)){
                    return price;
                }else{
                    return parseFloat(price).toFixed(2);
                }
            });


            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/common/productCatalog/data",
                    elem: "#pagination",
                    form: "#catelogSearchForm",
                    method:"post",
                    handleData: function (json) {
                        var html = template.render("feedback-templage", json);
                        $(".productsmanage-classic-table").remove();
                        $("#pagination").before($(html));
                        productmanageclass.init();
                        productmanageclass.jstoclass();
                        setTimeout(function(){

                            $('.jscrollbar ul').css("height",function(){
                                return $(this).height();
                            });
                            $('.jscrollbar').jscrollbar({
                                "width": "6"
                            });
                        },0);
                    }
                });
            };
            renderPagination();
        });

        seajs.use(['$', 'ztree'], function ($, ztree) {
            var setting = {
                callback: {
                    onClick: zTreeOnClick
                }
            };
            zNodes =${catalogList};
            $(document).ready(function () {
                zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
                zTreeObj.expandAll(true);
                var newNode = {name:"未分类",id:"-1"};
                zTreeObj.addNodes(null,newNode);
            });
        });
    });
    function search(){
        page.render();
    };
    function zTreeOnClick(event, treeId, treeNode) {
        $("#catelogSearchForm")[0].reset();
        $("#cateId").val(treeNode.id);
        page.current=1;
        search();
    };
    function addCate(t,chkName,flag){
        var cateIdAry = new Array();
        $(chkName+" input:checkbox:checked").each(function (index, data){
            cateIdAry[index] = $(this).val();
        });
        if(cateIdAry.length <= 0){
            remind("error","请选择一个分类");
            return;
        };
        var id;
        if(t==1)
            id=$.trim($("#selProductId").val());

        //如果是批量设置
        if(flag){
            setCatalog(t,cateIdAry,id,flag);
        }else{
            setCatalog(t,cateIdAry,id);
        }
    };
    function delCatalog(t, id){
        confirmDialog("确定是否要移除？",function(){
            var productIdAry = new Array();
            if(t==1)
                productIdAry[0]=id;
            else{
                $('.productsmanage-classic-table input:checkbox:checked').each(function (index, data){
                    productIdAry[index] = $(this).val();
                });
            }
            if(productIdAry.length <= 0){
                remind("error","请选择一个商品");
                return;
            }
            var data={productIdAry:productIdAry};
            catalogAjax(data,"产品分类移除成功");
        });
    };
    function setCatalog(t, cate, id,flag){
        var productIdAry = new Array();
        if(t==1)
            productIdAry[0]=id;
        else{
            $('.productsmanage-classic-table input:checkbox:checked').each(function (index, data){
                productIdAry[index] = $(this).val();
            });
        }
        if(productIdAry.length <= 0){
            remind("error","请选择一个商品");
            return;
        }
        var data={productIdAry:productIdAry,catelogIdAry:cate};
        if(flag){
            catalogAjax(data,"产品分类设置成功",flag);
        }else{
            catalogAjax(data,"产品分类设置成功");
        }
    };
    function catalogAjax(data, msg,flag)
    {
        $.ajax({
            type : "POST",
            url : "/common/productCatalog/setProduct",
            data : data,
            dataType: "json",
            traditional:true,
            success : function(json) {
                if(flag){
                    $(".batch-showbox input").removeAttr("checked");
                }
                if(zttx.SUCCESS==json.code){
                    remind("success",msg);
                    page.render();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(flag){
                    $(".batch-showbox input").removeAttr("checked");
                }
                remind("error",errorThrown);
            }
        });
    }
    $(function(){
        $(".chkall").click(function(){
            $(".productsmanage-classic-table .js_chk").prop("checked",this.checked);
        });
    });
</script>
<!--
    另外加载当前页面需要的js路径，或者使用
    seajs.use("./xxx.js")
 -->
</body>
</html>