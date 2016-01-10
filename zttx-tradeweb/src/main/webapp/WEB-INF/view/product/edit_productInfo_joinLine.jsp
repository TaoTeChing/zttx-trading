<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-发布新产品-发布成功</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css" />
</head>
<body>
 <jsp:include page="${ctx}/brand/mainmenu"/>
 <div class="main layout">
     <jsp:include page="${ctx}/brand/brandmenu"/>

<div class="main_con">
    <!-- 面包导航，并不是每个页面都有 -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/product/execute">发布新产品</a>
            <span class="arrow">&gt;</span>
            <span class="current">发布成功</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
  <form:form action="" method="post" id="linelistForm">
      <input type="hidden" name="productId" id="productId" value="${product.refrenceId}">
        <div class="productadd">
            <%--第二版步骤条 第四步--%>
            <div class="v2-steps">
                <span class="text1 current">选择类目</span>
                <span class="text2 current">添加资料</span>
                <span class="text3">发布成功</span>
                <span class="text4">加入产品线</span>
                <div class="steps v2-steps-4"></div>
            </div>
            <div class="productadd-joinproline">

                <div class="line-item">产品信息：</div>

                <div class="inline-block">
                    <%--<img src="/${fns:getFormateURL(product.productImage)}" alt="" class="imgbox"/>--%>
                </div>
                <div class="inline-block line-item-lwidth">
                    <p><strong>货号：${product.productNo}</strong></p>
                    <p>${product.productTitle}</p>
                </div>
                <div class="inline-block line-item-swidth">品牌：${product.brandsName}</div>
                <div class="inline-block line-item-swidth">价格：<strong>${product.productPrice}</strong></div>
                <div class="inline-block line-item-swidth">库存数：${product.productStore}</div>

                <div class="line-item line-item-bbl">加入以下产品线：</div>
                <div class="line-itemcheckall clearfix">
                    <div class="fl">
                        <input type="checkbox" class="js_chk" id="setdef9">
                        <label for="setdef9">全选</label>
                        <a href="javascript:joinLine();" class="line-itemselect-style inline-block">批量加入</a>
                    </div>
                    <%--<div class="fr">
                        <a href="" class="line-itemselect-style inline-block">上一页</a>
                        <a href="" class="line-itemselect-style inline-block">下一页</a>
                    </div>--%>
                </div>
                <table class="joinproline-table">
                    <colgroup>
                        <col width="35" />
                        <col width="195" />
                        <col width="105" />
                        <%--<col width="160" />--%>
                        <col width="110" />
                        <col width="110" />
                        <col width="110" />
                        <col width="110" />
                    </colgroup>
                    <thead>
                    <tr>
                        <th></th>
                        <th>产品线名称</th>
                        <th>折扣率（折）</th>
                        <%--<th>面对对象</th>--%>
                        <th>已加入产品（款）</th>
                        <th>已有终端商（个）</th>
                        <th>所在地区（个）</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageResult.list}" var="line">
                        <tr>
                            <td><input type="checkbox" class="js_chk" name="lineId" value="${line.id}" id="${line.id}"/></td>
                            <td class="proline-td-tl">${line.lineName}</td>
                            <td><fmt:formatNumber value="${line.lineAgio}" type="number" pattern="##.00" />  折</td>
                            <%--<td>特级终端商</td>--%>
                            <td>
                                <strong class="bluefont" id="joinProId_${line.id}">${line.productsCount}</strong>
                            </td>
                            <td>
                                <strong class="bluefont">${line.dealersCount}</strong>
                            </td>
                            <td>
                                <strong class="bluefont">${line.areaCount}</strong></td>
                            <td id="joinTd_${line.id}">
                                <c:choose>
                                    <c:when test="${empty line.isJoin}">
                                        <a href="javascript:joinLine('${line.id}');" class="bluefont">加入</a>
                                    </c:when>
                                    <c:otherwise>已加入</c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="pagination">
                    <tags:page form="linelistForm" page="${pageResult.page}"/>
                </div>
            </div>
        </div>
  </form:form>

    </div>
</div>
</div>
 <jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />

<script type="text/javascript">

    $(document).on("click", "#setdef9", function () {
        $(this).parents(".productadd-joinproline").find(".joinproline-table .js_chk").prop("checked",this.checked);
    });

    function joinLine(lineId){
        var lineIds = "";
        var idArr = new Array();
        var isAll = true;
        if(lineId){
            lineIds = lineId;
            idArr.push(lineId);
            isAll = false;
        }else{
            var $sel = $("input[name='lineId']:checked");
            if($sel.size()==0){
                remind("error","请选择记录");
                return;
            }

            $sel.each(function(index){
                idArr.push($(this).attr("id"));
            });
            lineIds = idArr.join(",");
        }
        var productId = $("#productId").val();
        if(lineIds && lineIds!="" && productId!=""){
            $.ajax({
                type: "POST",
                url: "${ctx}/brand/product/ajax_joinline",
                data: {"lineIds": lineIds,"productId":productId},
                dataType: "json",
                cache: false,
                success: function (result){
                    if(result.code==zttx.SUCCESS){
                        if(isAll){
                        	remind("success","加入产品线成功。",function(){
                        		window.location.reload();
                        	});
                        }else{
                        	remind("success","加入产品线成功。");
	                        for(var index in idArr){
	                            $("#joinTd_"+idArr[index]).html("已加入");
	                            var count = parseInt($("#joinProId_"+idArr[index]).text())+1;
	                            $("#joinProId_"+idArr[index]).text(count);
	                        }
                        }
                    }else{
                        remind("error","加入产品线失败，"+result.message);
                    }
                }
            })
        }


    }
/*    function checkData(){
        var lineIds = $("input[name='lineId']:checked");
        if(lineIds.size()==0){
            remind("error","请选择要加入的产品线");
            return false;
        }
        return true;
    }
    */
</script>


</body>
</html>