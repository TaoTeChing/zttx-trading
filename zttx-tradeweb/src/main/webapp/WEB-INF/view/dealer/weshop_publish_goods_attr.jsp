<%--
  微店发布产品颜色其他选择
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="color-size-box">
	
	<c:set value="0" var="count"></c:set>
	<c:forEach items="${attr.rows }" var="att" varStatus="sta">
		 <div class="item-classes" id="${sta.index % 2 != 0 ?   'otherClass' : 'colorClass'}">
		        <div class="item-classes-checkall">
		            ${att.attributeName }:
		            <label>
		                <input type="checkbox" class="ui-checkbox js-checkall"/>
		                全选
		            </label>
		        </div>
		       
		        <c:set value="${empty index ? 0 : index }" var="index"></c:set>
		        <ul class="item-classes-ul clearfix">
		        	<c:forEach items="${att.valueList }" var="value"  varStatus="status">
			        	<li class="item-classes-items">
			                <label>
			                    <input type="checkbox" name="attr[${index}].checked" class="ui-checkbox js-itemsCheck" value="1" ${value.selected ? "checked" :"" } />
			                </label>

			                <c:if test="${att.isImgAttr }">
			                	<c:choose>
			                		<c:when test="${fn:length(value.attributeIcon) > 6 }">
			                			<div class="inline-block colorarea" data-color="${value.attributeIcon}">
			                				<img src="${res }${value.attributeIcon}" width="13" height="13">
					                	</div>
			                		</c:when>
			                		<c:otherwise>
				                		<div class="inline-block colorarea" style="background-color: #${value.attributeIcon}" data-color="${value.attributeIcon}">
					                	</div>
			                		</c:otherwise>
			                	</c:choose>
			                </c:if>
			                <input type="hidden" value="${value.refrenceId }" name="attr[${index}].salerRefrenceId" class="refrenceId">
			                <input type="hidden" value="" name="attr[${index}].salerAtttrIcon">
			                <span class="itemattr">${value.attributeValue }</span><input class="editinput" name="attr[${index}].getext" value="${value.attributeValue }"/>
			            </li>
		        		<c:set value="${index + 1}" var="index"></c:set>
		        	</c:forEach>
		        	
		        	
		        </ul>
		    </div>
		    <%--颜色设置table--%>
		    <c:if test="${att.isImgAttr }">
		    	 <table class="ui-table ${att.isImgAttr ? 'js-colortable' : 'js-othertable'}" style="width: 445px;display:none;margin-bottom: 15px;">
			        <thead>
			        <tr>
						<th>${att.attributeName }</th>
	            		<th>图片（无图片可不填）</th>
			        </tr>
			        </thead>
			        <tbody>
			        
	
			        <%--<tr>
			            <td>
			                <div class="inline-block colorarea"></div><span>军绿色</span>
			            </td>
			            <td>
			                <div class="file_wrap inline-block">
			                    <a class="ui-button ui-button-lorange">文件上传</a>
			                    <input type="file" value="文件上传" name="" class="input_file">
			                </div>
			                <a href="javascript:;" class="ui-button ui-button-lorange">图库选择</a>
			                <a href="javascript:;" class="link">删除</a>
			            </td>
			        </tr>--%>
			        </tbody>
			    </table>
		    </c:if>
	</c:forEach>


   
    <%--尺码分类
    <div class="item-classes" id="otherClass" style="margin-top: 10px;">
        <div class="item-classes-checkall">
            尺码分类:
            <label>
                <input type="checkbox" class="ui-checkbox js-checkall"/>
                全选
            </label>
        </div>
        <ul class="item-classes-ul clearfix">
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" checked />
                </label>
                <span class="itemattr">4XL</span><input class="editinput" value="4XL"/>
            </li>
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" />
                </label>
                <span class="itemattr">5XL</span><input class="editinput" value="5XL"/>
            </li>
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" />
                </label>
                <span class="itemattr">XXS</span><input class="editinput" value="XXS"/>
            </li>
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" />
                </label>
                <span class="itemattr">XS</span><input class="editinput" value="XS"/>
            </li>
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" />
                </label>
                <span class="itemattr">S</span><input class="editinput" value="S"/>
            </li>
            <li class="item-classes-items">
                <label>
                    <input type="checkbox" class="ui-checkbox js-itemsCheck" />
                </label>
                <span class="itemattr">M</span><input class="editinput" value="M"/>
            </li>
        </ul>
    </div>
    <table class="ui-table js-othertable">
        <thead>
        <tr>
            <th>颜色分类</th>
            <th>尺码分类</th>
            <th>价格</th>
            <th>批量操作</th>
        </tr>
        </thead>
        <tbody>
        <%--<tr>
            <td>
                <span>军绿色</span>
            </td>
            <td>
                <span>4XL</span>
            </td>
            <td>
                <input class="ui-input"/>
            </td>
            <td>
                <a href="javascript:;" class="link">批量设为本价格</a>
            </td>
        </tr>
        </tbody>
    </table>--%>
    <div class="item-classes-tip js-classtip">
        您需要选择所有的销售属性，才能组合成完整的规格信息。
    </div>
    <div class="item-classes-tip js-baseother" style="display: none;">
        请选择色彩尺码
    </div>
</div>