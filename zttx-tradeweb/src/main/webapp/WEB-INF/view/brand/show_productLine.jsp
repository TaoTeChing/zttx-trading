<%--
  @(#)list_ProductLine.jsp 14-3-18 下午2:50
  Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品线管理-查看产品线</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/prolinemaneg.css"/>
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
                <a class="link" href="${ctx}/brand/line/execute">产品线管理</a>
                <span class="arrow">&gt;</span>
                <span class="current">查看产品线</span>
            </div>
            <div class="fr">
                <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                    <jsp:param value="0" name="isShow"/>
                </jsp:include>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <div class="proline-contant" style="overflow: visible;">
                <form:form class="ui-form proline-amend-form">
                    <a href="${ctx}/brand/line/execute?id=${line.refrenceId}"
                       class="proline-amend-xiugaibtn ui_button ui_button_lblue">修改</a>

                    <div class="ui-form-item">
                        <label class="ui-label" for="">品牌名称：</label>

                        <div class="inline-block pro-amend-already">${line.brandsName}</div>
                    </div>
                    <div class="ui-form-item">
		                <label class="ui-label" for="">用户分类：</label>
		                <div class="radio_box inline-block">
		                    <input class="ui-radio js-pt-user" disabled="disabled" name="lineType" type="radio" value="0"  <c:if test="${empty line.lineType || line.lineType==0}">checked</c:if>/>
		                    <label for="owner1">普通用户</label>
		                    <input class="ui-radio ml5 js-sq-user" disabled="disabled" name="lineType" type="radio" value="1" <c:if test="${line.lineType==1}">checked</c:if>/>
		                    <label for="owner2">加盟用户</label>
		                </div>
		            </div>
		            <div class="ui-form-item js-usertype-box">
		                <label class="ui-label">
		                    &nbsp;
		                </label>
		                <div>
		                    <input type="checkbox" disabled="disabled" name="openUserAry" value="1" <c:if test="${openTypeAry[0]==1}">checked</c:if>>游客　
		                    <input type="checkbox" disabled="disabled" name="openUserAry" value="2" <c:if test="${openTypeAry[1]==1}">checked</c:if>>注册会员　
		                    <input type="checkbox" disabled="disabled" name="openUserAry" value="3" <c:if test="${openTypeAry[2]==1}">checked</c:if>>认证会员
		                </div>
		             </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">产品线名称：</label>

                        <div class="inline-block pro-amend-already">${line.lineName}</div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">产品线折扣率：</label>

                        <div class="inline-block pro-amend-already">
                            <span>吊牌价&nbsp;x</span>&nbsp; ${agio}折
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            产品线采购时间：
                        </label>

                        <c:set var="TIME_UNLIMITED" value="<%=com.zttx.web.consts.ProductLineConst.TIME_UNLIMITED%>"/>
                        <c:set var="TIME_RANGE" value="<%=com.zttx.web.consts.ProductLineConst.TIME_RANGE%>"/>
                        <c:choose>
                            <c:when test="${line.buyType == TIME_UNLIMITED}"><span class="ui-form-noEdit">时间不限</span></c:when>
                            <c:when test="${line.buyType == TIME_RANGE}">
                                <div class="inline-block pro-amend-already">
                                        ${fns:getTimeFromLong(line.buyStart, "yyyy.MM.dd")}-${fns:getTimeFromLong(line.buyEnd, "yyyy.MM.dd")}
                                </div>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </div>
                    <div class="ui-form-item" style="display:none;">
                        <label class="ui-label" for="">该产品线起批量：</label>

                        <div class="inline-block pro-amend-already">${line.buyNum == null ? "":line.buyNum}件 / ${line.buyMoney == null ? "":line.buyMoney}元</div>
                    </div>
                    <c:if test="${line.appointType==1}">
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            指定终端商：
                        </label>

                        <div class="inline-block pro-amend-dealer">
                            <table class="pro-amend-dealer-table">
                                <colgroup>
                                    <col width="190" />
                                    <col width="190" />
                                    <col width="190" />
                                    <col width="190" />
                                </colgroup>
                                <c:forEach items="${productDealers}" var="dealers">
                                    <thead class="thead">
                                    <tr>
                                        <th colspan="4">
                                            <div class="cityname">
                                                    ${fns:getAreaName(dealers.key)}
                                                <em class="bluefont">(${fn:length(dealers.value)})</em>

                                                <div class="city-bodytoggle">
                                                    <i class="iconfont">&#xe605;</i>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${dealers.value}" var="dealer" varStatus="status">
                                        <c:if test="${status.index % 4 == 0}">
                                            <tr>
                                        </c:if>
                                        <td>${dealer.dealerName}</td>
                                        <c:if test="${status.last}">
                                        	<c:set value="${4 - dealers.value.size() % 4}" var="max"/>
                                        	<c:if test="${max >0}">
	                                        	<c:choose>
	                                        		<c:when test="${max > 1}">
	                                        		<td colspan="${max}">&nbsp;</td>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		<td>&nbsp;</td>
	                                        		</c:otherwise>
	                                        	</c:choose>
                                        	</c:if>
                                        </c:if>
                                        <%-- <c:forEach  begin="1" end="${max}">
                                        <td>&nbsp;</td>
                                        </c:forEach> --%>
                                        <c:if test="${status.index % 4 == 3 or status.last}">
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${empty line.appointType || line.appointType==0}">
                    	<div class="ui-form-item js_area_box">
			                <label class="ui-label" for=""> 选择地区：</label>
			                 <div class="mt5 inline-block">
			                     <div id="js-showArea" class="inline-block" style="width: 770px;">
			                         <div class="ts-select-city">
			                             <div class="title">
			                                 请选择区域：全选 <input disabled="disabled" type="checkbox" class="js-select-all"/>
			                             </div>
			                             <div class="ts-sc-content clearfix">
			
			                             </div>
			                         </div>
			                     </div>
			                 </div>
			             </div>
                    </c:if>
                    <div class="ui-form-item">
                        <label class="ui-label" for="">
                            产品：
                        </label>

                        <div class="inline-block">
                            <table class="proline-amend-table">
                                
                            </table>
                            <div id="pagination"></div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script src="${res}/scripts/brand/prolinemanage.js"></script>
<script src="${res}/scripts/common/area.js"></script>
<script id="product-template" type="text/html">
<colgroup>
	<col width="355"/>
	<col width="190"/>
	<col width="90"/>
	<col width="90"/>
</colgroup>
<thead>
	<tr>
		<th><span>产品信息</span></th>
		<th><span>已加入的产品线</span></th>
		<th><span>折扣率</span></th>
		<th><span>折后价格</span></th>
	</tr>
</thead>
<tbody>
    {{each rows}}
    <tr>
        <td class="td-000">
            <img src="${res}{{$getImageDomainPathFn $value.domainName+$value.productImage 160 160}}" width="70" height="70" alt="" class="list-TD-imgs fl"/>

            <div class="inline-block fl list-TD-des">
                <p class="number fn-text-overflow">货号：{{$value.productNo}}</p>
                <h4 class="title">{{$value.productTitle}}</h4>
            </div>
        </td>
        <td class="td-001">
		<ul>
			{{each $value.addedLines}}
				<li>{{$value.lineName}}</li>
			{{/each}}
		</ul>
        </td>
        <td class="td-002">
            {{$value.agio}}折
        </td>
        <td class="td-003">
            <strong class="pro-price">{{formatNumber $value.realPrice}}</strong>
        </td>
    </tr>
    {{/each}}
</tbody>
</script>
<script>
  addlineONE.init();
  var page;
  $(function () {
	  productLine.init();
    seajs.use(["pagination","template"], function (Pagination,template) {
    	
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
             url: "${ctx}/brand/line/${line.refrenceId}/products",
             elem: "#pagination",
             method: "post",
             handleData: function (data) {
                 var html = template.render('product-template', data);
                 $(".proline-amend-table").html(html);
             }
         });
         };
         renderPagination();
       });
     });
  
//新增代码20140913
  var productLine = {
      init:function(){
    	  // 选中地区
      	  showArea();
    	
          this.userType();//用户分类初始化
          this.selectArea();//选择区域
      },
      userType:function(){
          if($(".js-pt-user").prop("checked")){
              $(".js-usertype-box").show();
          }
          if($(".js-sq-user").prop("checked")){
              $(".js-usertype-box").hide();
          }
      },
      selectArea:function(){

          //初始化省的状态
          var initNumber = function(){
              $(".ts-sc-content .province-box").each(function(){
                  var _num = $(this).find(".city-box input[type=checkbox]:checked").length;
                  if(_num > 0 ){
                      $(this).find(".province input[type=checkbox]").prop("checked",true);
                      $(this).find(".num").html("("+_num+")");
                  }
                  if(_num == 0 ){
                      $(this).find(".num").html("");
                  }
              });
          };
          initNumber();

          var countNumber = function(self){
              var _num = self.find(".city-box input[type=checkbox]:checked").length;
              if(_num == 0){
                  self.find(".num").html("");
                  return;
              }
              self.find(".num").html("("+_num+")");
          };

          $(".province-box").hover(function(){
              $(this).addClass("hover");
          },function(){
              $(this).removeClass("hover");
              countNumber($(this));
          });

          $(".province input[type=checkbox]").click(function(){
              var _parents = $(this).parents(".province-box");
              _parents.find(".city-box input[type=checkbox]").prop("checked",this.checked);
          });

          $(".js-select-all").click(function(){
              $(".ts-sc-content input[type=checkbox]").prop("checked",this.checked);
              initNumber();
          })
      }
  }
  
  function showArea()
  {

      var s = "";
      for(var p in area)
      {
          var pArr = p.split(" ");
          s += '<div class="province-box" style="z-index: '+(1000000-parseInt(pArr[1]))+'">';
          s += '<div class="province"><input disabled="disabled" value="' + pArr[1] + '" type="checkbox"/>'+ pArr[0] +'<span class="num"></span><i class="arrow"></i></div>';
          s += '<div class="city-box">';
          if(pArr[1] in {"710000":"", "810000":"", "820000":""})
          {
              s += '<label><input disabled="disabled" value="' + pArr[1] + '" type="checkbox" name="areaAry"/>'+ pArr[0] +'</label>';
          }
          for(var c in area[p])
          {
              var cArr = c.split(" ");
              s += '<label><input disabled="disabled" value="' + cArr[1] + '" type="checkbox" name="areaAry"/>'+ cArr[0] +'</label>';
          }
          s += '</div>';
          s += '</div>';
      }
      $("#js-showArea .ts-select-city .ts-sc-content").html(s);

  	<c:forEach var="obj" items="${regionalList}" varStatus="status">
  		$(":input[name='areaAry'][value='${obj.areaNo}']").attr("checked", true);
  	</c:forEach>
  }
</script>
</body>
</html>
