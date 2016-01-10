<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-开通微店成功</title>
    <link href="${res }/styles/dealer/global.css" rel="stylesheet"/>
    <link href="${res }/styles/dealer/weshop.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
           <jsp:include page="${ctx}/dealer/dealermenu"/>
            <div class="main-right">
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span
                            class="bb">服务管理</span>
                        <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb40 clearfix">
                    <div class="weshop-publish-goods">
                        <h2>我要发布产品</h2>
                        <jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp" >
                        	<jsp:param value="2" name="tab"/>
                        </jsp:include>
                        <div class="hr-dashed">
                        </div>
                        <%--页面开始--%>
                        <form:form class="ui-form publishForm" action="${ctx }/dealer/weshop/product/saveOrUpdate">
                        	<input type="hidden" name="refrenceId" value="${json.object.productInfo.refrenceId }">
                        	<input type="hidden" name="shopId" value="${shopId }">
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    标题:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input publishTitle" name="productTitle" value="${json.object.productInfo.productTitle }" data-display="标题" style="width: 410px;"/>
                                <span class="ui-form-other">还能输入<em class="bluefont" id="count">60</em>字符</span>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    成本价格:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input"  name="costPrice" value="${json.object.productInfo.costPrice }" data-display="成本价格"/>
                                <span class="ui-form-other">元</span>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    市场价格:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input" name="marketPrice" value="${json.object.productInfo.marketPrice }" data-display="市场价格"/>
                                <span class="ui-form-other">元</span>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    销售价格:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input" name="salePrice" value="${json.object.productInfo.salePrice }" data-display="销售价格"/>
                                <span class="ui-form-other">元</span>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    货号:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input" name="productNo" value="${json.object.productInfo.productNo }" data-display="货号"/>
                            </div>
                            <div class="ui-form-item">
                            	<div id="selectCateNumber" style="display:none">${json.object.productInfo.dealNo }</div>
                                <label class="ui-label">
                                    商品分类:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <div class="inline-block">
                                    <select class="ui-select select-category" name="" data-display="商品分类">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                                <div class="inline-block">
                                    <select class="ui-select select-class" name="dealNo" data-display="商品分类">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    品牌:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input" name="brandsName" value="${json.object.productInfo.brandsName }"  style="width: 190px;" data-display="品牌"/>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    库存量:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <input class="ui-input" name="productStore" value="${json.object.productInfo.productStore }" data-display="库存量"/>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    色彩尺码:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <div id="color-other">
                                
                                </div>
                               <%--  <jsp:include page="color-other.jsp" /> --%>
                            </div>
                    
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    产品主图:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <%--通用上传图片容器（本地上传、图库选择） 品牌商后台有这个控件 --%>
                                <div class="uploadfile_contain js_uploadfile">
                                    <div class="ui_tab clearfix">
                                        <ul class="ui_tab_items">
                                            <li class="ui_tab_item current">
                                                <a href="javascript:;">本地上传</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="bd">
                                        <div class="file_tab_con local_con" style="display: block;">
                                            <label class="" for="">
                                                选择本地图片：&nbsp;&nbsp;
                                            </label>
                                            <div class="inline-block">
                                                <div class="file_wrap">
                                                    <p class="replace_text">文件上传</p>
                                                    <input type="file" value="文件上传" name="file" class="input_file" id="uploadProductImg" onchange="publishGoods.uploadImage('uploadProductImg');" />
                                                </div>
                                            </div>
                                            <div class="tips_images">
                                                <p class="tip">提示：1.单张图片大小不超过<strong>500K</strong>。</p>
                                                <p class="tip tipint">2.至少上传<strong>1</strong>张图片，最多可上传<strong>6</strong>张图片。</p>
                                                <p class="tip tipint">3. 建议图片分辨率在<strong>400*400</strong>以上。</p>
                                            </div>
                                        </div>
                                        <style>
                                            .uploadfile_contain .choose_result{height: 112px;}
                                        	.choose_result .item{position:relative;}
                                        	.choose_result .item .close_btn{ position:absolute;top: 1px;right: 5px;cursor: pointer;}
                                        	.choose_result .item .close_btn:hover{text-decoration: none;}
                                        </style>
                                        <div class="choose_result">
                                            <ul class="items inline-float" id="show_img">
                                                <c:forEach begin="0" end="6" var="image" items="${json.object.productImageList}" varStatus="sta">
                                                    <li class="item">
                                                        <div class="img_contain">
                                                            <img src="${weshop }${image.imageName}" alt="${ image.imageMark}" style="width:90px;height:90px;"/>
                                                            <a href="javascript:;" class="close_btn iconfont"></a>
                                                            <input type="hidden" name="imagesName" value="${image.photoName }">
                                                            <input type="hidden" name="images" value="${image.imageName }">
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                                <%--<li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                         <input type="hidden" name="images" value="">
                                                     </div>
                                                 </li>--%>
                                                <%--<li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                     </div>
                                                 </li>
                                                <li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                     </div>
                                                 </li>
                                                <li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                     </div>
                                                 </li>
                                                <li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                     </div>
                                                 </li>
                                                <li class="item">
                                                     <div class="img_contain">
                                                         <img src="" alt=""/>
                                                     </div>
                                                 </li>--%>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <%-- 品牌商后台调用链接 <jsp:include page="/WEB-INF/view/brand/common_teps.jsp" />--%>
                            </div>
                            <div class="ui-form-item">
                                <label class="ui-label">
                                    产品描述:
                                    <span class="ui-form-required">*</span>
                                </label>
                                <textarea class="js-umeditor"  name="productMark"  id="umeditor" data-display="产品价格">${json.object.productCount.productMark }</textarea>
                            </div>
                            <div class="ui-form-item">
                                <input type="submit" class="ui-button ui-button-lred mr10" value="立即发布"/>
                                <%--<button class="ui-button ui-button-lred mr10" type="button">立即发布</button>--%>
                                <%-- <a href="javascript:;" class="ui-button ui-button-lorange">预 览</a> --%>
                            </div>
                        </form:form>
                        <%--页面结束--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script src="${res }/scripts/weshop/weshop-goods.js"></script>
<%-- <script id="colortable" type="text/html">颜色模版
    {{each}}
    <tr>
        <td>
            {{if $value.color.length > 6}}
            <div class="inline-block colorarea" data-color="{{$value.color}}">
                <img src="{{$value.color}}" width="13" height="13">
            </div>
            {{else if $value.color.toString().length < 6 ||  $value.color.toString().length > 1 }}
            <div class="inline-block colorarea" style="background:#{{$value.color}};" data-color="{{$value.color}}"></div>
            {{else if $value.color.length == 1}}
            {{/if}}
            <span>{{$value.v}}</span>
        </td>
        <td>
            <div class="file_wrap inline-block">
                <a class="ui-button ui-button-lorange">文件上传</a>
                <input type="file" value="文件上传" name="file" class="input_file" id="colorImg_{{$value.vid}}" onchange="publishGoods.uploadImage('colorImg_{{$value.vid}}');">
            </div>
            <a href="javascript:;" class="link colorImg-delete">删除</a>
        </td>
    </tr>
    {{ /each}}
</script> --%>
<script id="othertable" type="text/html"><%--其他模版--%>
<table class="ui-table js-othertable" style="display:none;">
    <thead>
        <tr>
            <th>颜色分类</th>
            <th>尺码分类</th>
            <th>价格</th>
            <th>批量操作</th>
        </tr>
    </thead>
    <tbody>
    {{each}}
    <tr>
        <td rowspan="{{$value.val.length + 1}}" style="border-right:1px solid #D9D9D9;">{{$value.v}}</td>
            {{if $value.val != null}}
                {{each $value.val}}
                <tr>
                    <td>
                        <span>{{$value.v}}</span>
                    </td>
                    <td>
                        <input class="priceinput js-number" name="price" value="{{$value.p}}" id="priceIuput_{{$value.vid}}_{{$value.cid}}"/>
                    </td>
                    <td>
                        <a href="javascript:;" class="link js-setprice">批量设为本价格</a>
                    </td>
                </tr>
                {{/each}}
            {{/if}}
    </tr>
    {{ /each}}
	</tbody>
</table>
</script>
<script id="coloralonetable" type="text/html"><%--单独只有一个的时候的模版--%>
    <table class="ui-table js-othertable">
        <thead>
        <tr>
            <th>颜色分类</th>
            <th>价格</th>
            <th>批量操作</th>
        </tr>
        </thead>
        <tbody>
        {{each}}
        <tr>
            <td>
                <span>{{ $value.v}}</span>
            </td>
            <td>
                <input class="priceinput js-number" name="price" value="{{$value.p}}" id="priceIuput_{{$value.vid}}"/>
            </td>
            <td>
                <a href="javascript:;" class="link js-setprice">批量设为本价格</a>
            </td>
        </tr>
        {{ /each}}
        </tbody>
    </table>
</script>
<script>
    $(function(){
	    //图片上传URL
		window.UPLOAD_IMAGE_PATH = "/dealer/weshop/editor/upload";
		window.IMAGE_SWITCH = false;
		window.IMAGE_DOMIAN="${weshop}";
    	var _datalist = ${json.object.dealDicJsonString};//分类数据
    	//var _data ${ json.object.productInfo.attributePrice != null ? "=" : ""} ${ json.object.productInfo.attributePrice} ;
    	/* console.log(typeof _data);
    	if(_data!==){
    		_data = [];
    	} */
    	var attrView ${attrView == null ? "" : "="} ${attrView};
    	//console.log(attrView);
       	publishGoods.init(_datalist,attrView);
    });
    
</script>

</body>
</html>