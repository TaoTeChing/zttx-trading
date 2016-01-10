<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的询价单</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/inquiry.css" rel="stylesheet" />
</head>
<body>

<div class="container">
<jsp:include page="${ctx}/dealer/mainmenu"/>
<div class="em100">
<div class="main clearfix pr">
<!--侧边导航-->
	<jsp:include page="${ctx}/dealer/dealermenu"/>
			<form:form id="ui_form" method="get" class="ui-form" onsubmit="return false">
            <div class="main-right">
            <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a>
                        >>
                        <span class="bb">我的询价单</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <div class="panel-filtrate clearfix">
                            <div class="ui-form-row">
                                <label>产品名称：</label>
                                <input type="text" name="productName" class="ui-input long" value="" maxlength="128"/>
                                <label>询价单号：</label>
                                <input type="text" name="queryNo" class="ui-input" value="" maxlength="20"/>
                                <label>品牌：</label>
                                <input type="text" name="brandesName" class="ui-input" value="" maxlength="20"/>
                            </div>
                            <div class="ui-form-row">
                                <label>询价时间：</label>
                                <input type="text" name="beginTimeStr" class="ui-input date" id="start-cal" value="" readonly="true" />
                                -
                                <input type="text" name="endTimeStr" class="ui-input date" id="end-cal" value="" readonly="true"/>

                                <label>询价状态：</label>
                                <div class="inline-block pr">
                                    <select class="ui-select js-select" name="state" id="state_select">
                                        <option value="">请选择 </option>
                                        <option value="0">申请中 </option>
                                        <option value="1">同意加盟 </option>
                                        <option value="2">拒绝加盟 </option>
                                        <option value="3">已放弃 </option>
                                    </select>
                                </div>
                                <div class="inline-block pr ml10">
                                    <select class="ui-select js-select" name="queryState">
                                        <option value="">请选择</option>
                                        <option value="0">未报价</option>
                                        <option value="1">已报价</option>
                                    </select>
                                </div>
                                <input class="ui-button ui-button-lwhite ml10" style="vertical-align: top;" type="submit" id="doSearch" value="搜 索"/>
                            </div>
                    </div>
                    <div class="panel-tabs">
                        <div class="panel-head">
                            <ul class="clearfix">
                                <li class="js-order-status"><a class="active" href="#" onclick="inquiry.queryAll(this)">所有询价单</a></li>
                                <li class="js-order-status"><a href="#" onclick="inquiry.queryAll(this,0)" >申请中</a></li>
                                <li class="js-order-status"><a href="#" onclick="inquiry.queryAll(this,1)" >同意加盟</a></li>
                                <li class="js-order-status"><a href="#" onclick="inquiry.queryAll(this,2)">拒绝加盟</a></li>
                            </ul>
                        </div>
                        <div class="panel-content">
                            <div class="order-list">
                                <ul id="datas">
                                <%--<c:if test="${fn:length(pageResult.list)==0}">
                                    <li style="text-align: center"><img src="${res}/images/common/null.png"></li>
                                </c:if>--%>

                                </ul>
                            </div>
                            <div class="mt10">
                                <div class="pagination" id="pagination"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
</div>
<script id="query_template" type="text/html">
{{each rows}}
<li class="order-list-item">
<div class="ui-box">
   <div class="ui-box-head"> 
      <div class="ui-box-head-text">
       <span class="c-h">询价单号：{{$value.queryNo}}</span>
       <span class="ml20 c-h">日期：{{ $formatDate $value.createTime}}</span>
       <span class="ml20 c-h">品牌：{{$value.brandesName}}</span>
        </div>
         <div class="ui-box-head-more">
			<%--
             <a class="progress c-o"><i class="iconfont">&#xe601;</i> 联系客服</a>
             --%>
          </div>
         </div>
          <div class="ui-box-container">
             <div class="ui-box-content clearfix">
               <div class="inquiry-pro">
                <div class="box-padd">
                  <a class="js-img-center" href="${ctx}/market/product/{{$value.productId}}">
                    <img src=${res}{{$value.productImage}}>
                      <span></span>
                       </a>
                     <div class="word">
                  <a class="link"  data-qorder-refrenceid="{{$value.refrenceId}}" href="${ctx}/market/product/{{$value.productId}}"><span title="{{$value.productName}}">{{$trimTitle $value.productName}}</span><br>{{$value.productNo}}</a>
                 </div>
                </div>
               </div>
              <div class="inquiry-state">
               <div class="box-padd">
                 <div class="word">
                   <p class="c-hh" id="havegiveup_{{$value.refrenceId}}">询价件数：<span class="c-h">{{$value.purchaseNum}}件</span></p>
                      <p>
						{{if $value.state==0}}
							<span class="c-o"><i class="icon i-clock"></i>申请中</span>
						{{/if}}
						{{if $value.state==1}}
							<span class="c-g"><i class="icon i-right"></i>同意加盟</span>
							{{if $value.purchasePrice==null||$value.purchasePrice=="" ||$value.purchasePrice == 0}}
								<span class="c-r" href="#">等待报价</span>
							{{/if}}
							{{if $value.purchasePrice!=null}}
								<span class="c-r" href="#">报价:{{$formatNumber $value.purchasePrice}}</span>
							{{/if}}
						{{/if}}
						{{if $value.state==2}}
							<span class="c-r"><i class="icon i-wrong"></i>拒绝加盟</span>
						{{/if}}
						{{if $value.state==3}}
							<span class="c-hh"><i class="icon i-wrong"></i>已放弃</span>
						{{/if}}
						{{if $value.state==4}}
							<span class="c-hh"><i class="icon i-wrong"></i>已放弃</span>
						{{/if}}
                     </p>
                  </div>
             </div>
         </div>
        <div class="inquiry-message">
          <div class="box-padd">
             <div class="word"><span class="c-h" style="display:inline-block;word-break: break-all;" title="{{$value.purchaseMark}}">{{$trimLongString $value.purchaseMark}}</span></div>
         </div>
          </div>
           <div class="inquiry-operate">
            <div class="box-padd">
             <div class="word">
				<a class="link" href="${ctx}/dealer/inquiry/detail?refrenceId={{$value.refrenceId}}">查看详情</a><br />
			{{if $value.state==0}}
				<a class="link" href="javascript:;" data-closeid="" data-giveupid ="{{$value.refrenceId}}">放弃</a>
			{{/if}}
			{{if $value.state==1}}
					{{if ($value.purchasePrice!=null) || $value.lineId }}   
								<a class="link" href="#" onclick="inquiry.joininShopper('{{$value.productId}}','{{$value.purchaseNum}}','{{$value.refrenceId}}')">加入进货单</a><br/>
					{{/if}}
			{{/if}}
			{{if $value.state==2}}
			{{/if}}
            {{if $value.state==3}}
			{{/if}}
            </div>
           </div>
      </div>
    </div>
    </div>
</div>
</li>
{{/each}}
{{if rows.length==0}}
	<li style="text-align: center"><img src="/images/common/null.png"></li>
{{/if}}
</script>
<div id="detailPanel" class="detailPanel hide clearfix"></div>
<script id="detail-template" type="text/html">
    <div class="panel-table" style="background: #fff;">
        <div class="panel-table-content" style="max-height:175px;_height:175px;overflow:auto;">
           <table class="ui-table">
                    <thead>
                            <tr>
                                <th width="100" class="ta-c">颜色</th>
                                <th width="100">尺寸</th>
                                <th width="100">吊牌价</th>
                                <th width="100">报价</th>
                            </tr>
                    </thead>
                    <tbody>
							{{each productInfo.priceModels }}
		                       <tr>
		                         <td>
                                    {{if $value.z[0].icon}}
									<img src=" ${res}{{$getImageDomainPathFn $value.z[0].icon 25 25}}" width="25" height="25"> 
                                    {{/if}}
                                       {{$value.z[0].v=='0'?'默认':$value.z[0].v}} 
		                         </td>
                                <td >
                                   {{if $value.z.length==1}}--{{/if}}
  									{{if $value.z.length==2}}{{$value.z[1].v}}{{/if}}
                                </td>
								 <td >
                                    {{$formateNumber $value.p}}
                                </td>
 								<td >
									{{if productAgio!=null}}{{$value.p*productAgio}} {{/if}}
                                    {{if productAgio==null}}{{$formateNumber purchasePrice}}{{/if}}
                                </td>
                            </tr>	
                          {{/each}}
                    </tbody>
            </table>
        </div>
    </div>
</script>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script>
    var inquiry  = {
        init:function(){
            this.initPopup();
            this.initDialog();
        },
        initPopup:function(){
            seajs.use(['template','tip'], function (template,Tip) {
                //单独产品鼠标经过
                template.helper('$getImageDomainPathFn',function (url, width, height){
                    return getImageDomainPath(url, width, height);
                });
                template.helper("$formateNumber",function(price){
            	    if(isNaN(price)){
                        return price;
                    }else{
                        return parseFloat(price).toFixed(2);
                    }
              });
                new Tip({
                    trigger: ".order-list .inquiry-pro .word a",
                    element: "#detailPanel",
                    inViewport:true,
                    align:{ baseXY:[100,-70]}
                }).after('show', function (a) {
                            //这里有待后端修改(后端看到后删除词句)
                            var $this = a.activeTrigger[0];
                            var $element = a.element[0];
                            var _data = $($this).data("detail");
                            if (!_data) {
                                var qorderId =  $($this).data('qorder-refrenceid');
                                $.get('${ctx}/dealer/inquiry/detail/json?refrenceId='+qorderId, function (data) {
                                    if (data.code === 126000) {
                                        $($this).data({ "detail": data.object });
                                        $($element).html(template.render('detail-template',data.object));
                                    } 
                                },"json");
                            }else{
                                $($element).html(template.render('detail-template', _data));
                            }
                        });
            });
        },
        initDialog:function(){
            $(document).on("click","[data-closeid]",function(){
                var me = this;
                confirmDialog("放弃后,客服人员将不再为您跟踪此询价单。<br>您确定放弃本次询价吗？",function(){
                    var id = $(me).data("giveupid");
                    $.post('${ctx}/dealer/inquiry/giveup?refrenceId='+id,function(data){
                        if(data.code=='126000'){
                            $("#havegiveup_"+id).next().html('<span class="c-hh"><i class="icon i-wrong"></i>已放弃</span>');
                            $(me).remove();
                        }else{
                            remind("！抱歉，操作失败了，请联系客服人员，谢谢");
                        }
                    },'json');
                });
            });
        }
    };
    inquiry.init();
    
    /**查询切换*/
    inquiry.queryAll=function(ele,val){
        $('#state_select').val(val);
        $(ele).parent().parent().find('a').removeClass('active');
        $(ele).addClass('active');
        page.goTo(1);
    };

    inquiry.joininShopper=function(productId,number,qid){
        $.post("${ctx}/dealer/shoper/purchase/"+productId+"/"+number+"_"+qid,function(data){
            if(data.code==126000){
                remind("success", "此产品已成功加入进货单");
                g_pagination.goTo(1);
            }else if(data.code==111048){
                seajs.use(["dialog"],function(Dialog){
                    var sc =  new Dialog({content:$("#serviceCost"), width:320}).show();
                    $("#serviceCost .js-cancel").click(function(){ sc.hide(); });
                });
            }else if(data.code==111018){
                remind("error","产品未授权,无法加入进货单!");
            }else{
                remind("error",data.message);
            }
        },"json");
    };
    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
        });
        template.helper('$trimTitle', function (str) {
            return trimLongString(str, "10", "...");
        });
        template.helper('$trimLongString', function (str) {
            return trimLongString(str, "55", "...");
        });
        template.helper('$formatNumber', function (price) {
            if(isNaN(price)){
                return price;
            }else{
                return parseFloat(price).toFixed(2);
            }
        });
        page = new Pagination({
            url: "${ctx}/dealer/inquiry/json",
            elem: "#pagination",
            form: $("#ui_form"),
            method: "post",
            handleData: function (json) {
                var html = template.render("query_template", json);
                $("#datas").html(html);
                inquiry.initPopup();
            }
        });
    });
    $("#doSearch").click(function () {
        page.goTo(1);
    });
</script>
</body>
</html>
