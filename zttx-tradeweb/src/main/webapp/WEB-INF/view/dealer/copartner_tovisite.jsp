<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-加盟管理-我浏览过的品牌</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet"/>
    <%--<link href="${res}/styles/dealer/global.css" rel="stylesheet"/>--%>
    <link href="${res}/styles/dealer/brandjoin.css" rel="stylesheet"/>
</head>
<body>
<!--完成-->
<div class="container">
     <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/common/menuInfo/sidemenu">
					<jsp:param value="4" name="openId"/>
				</jsp:include>
            <!--主体内容-->
            <div class="main-right">
                <!--提示框-->
                <!--<div class="main-grid mb10">
                    <div class="alert c-w warning">
                        <b class="icon i-close">X</b><i class="icon"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
                    </div>
                </div>-->
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/copartner/brandes" title="">加盟管理</a> >
                        <span class="bb">我浏览过的品牌</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <div class="panel-tabs">
                        <jsp:include page="/WEB-INF/view/dealer/copartner_header.jsp">
                            <jsp:param value="0" name="m"/>
                        </jsp:include>
                        <div class="panel-content">
                            <div class="tab-item">
                                    <div class="panel-table-content">
                                    	<table class="ui-table ui-table-inbox">
									        <thead>
									            <tr>
									                <th class="tb25">品牌LOGO</th>
									                <th class="tb20">品牌商</th>
									                <th class="tb15">公司规模</th>
									                <th class="tb15">年营业额</th>
									                <th class="tb25">操作</th>
									            </tr>
									        </thead>
									        <tbody id="copartner_tbl_body">
									        
									        </tbody>
									    </table>
                                    	
                                        <div class="mt10" id="pagination"></div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<div class="hide">
    <div id="add_brand">
        <div class="ui-head">
            <h3>申请加盟</h3>
        </div>
        <form:form class="ui-form mt20" id="joinForm">
            <input type="hidden" id="brandsId"/>

            <div class="ui-form-item">
                <label class="ui-label">联 系 人：</label>
                <span class="ui-form-text">${dealerInfo.dealerUser}</span>
            </div>

            <div class="ui-form-item">
                <label class="ui-label">联系方式：</label>
                <input type="text" class="ui-input" id="telphone"  value="${dealerUserm.userMobile}"/>
            </div>

            <div class="ui-form-item">
                <label class="ui-label">申请信息：</label>
                <textarea class="ui-textarea" id="applyText" style="height:60px;width:250px;"></textarea>
            </div>

            <div class="ui-form-item">
                <a href="javascript:void(0);" id="submit" class="ui-button ui-button-mred">申请提交</a>
            </div>
        </form:form>
    </div>
</div>

<script id="copartner-template" type="text/html">
   {{each rows}}
        <tr>
            <td>
			<a target="_blank" href='http://{{$value.domain}}${zttx}'>
                <div class="js-img-center l-100x50 " style="text-align: center;"><img src="${res}{{$getImageDomainPathFn $value.domainName+$value.logoName 100 50}}" /></div>
                <h3>&nbsp;{{$value.brandsName}}</h3>
			</a>
            </td>
            <td>
                <p><span>{{$value.brandName}}</span></p>
                {{ if $value.factoryStore==true }}<p> <img src="/images/common/factory-brand.jpg" alt=""/></p>{{ /if }}
            </td>
            <td> {{$value.emploeeNumName}}</td>
            <td> {{$value.moneyNumName}}</td>
            <td data_id="{{$value.brandsId}}">
                {{if $value.applyState==0}}
                    <a href="javascript:updateApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb10">撤销申请</a><br />
                    <span class="c-r">正在审核您的申请</span>
                {{/if}}
                {{if $value.applyState==1}}
                    <a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="link">查看招募书</a> <%--<a href="#" class="ml10 link">查看品牌证书</a>--%>
                {{/if}}
                {{if $value.applyState==3}}
                	<a href="javascript:updateInvitedApple('{{$value.brandsId}}');" class="ui-button ui-button-sorange mb10"><i class="iconfont">&#xe620;</i> 接受邀请</a>
                    <br/>
                    <a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a><%--<a href="../market/BrandRecruit.jsp" class="ml20 c-b">查看品牌证书</a>--%>
                {{/if}}
                {{if $value.applyState==2 || $value.applyState==4 || $value.applyState==5 || $value.applyState==6 || $value.applyState==null}}
                    {{if $value.brandRecruit==true}}
                        <a href="javascript:void(0);" class="ui-button ui-button-sorange btn_apply mb10"><i class="iconfont">&#xe618;</i>申请加盟</a><br/>
                        <a href="http://{{$value.domain}}${zttx}/recruit" target="_blank" class="c-b">查看招募书</a>
                    {{/if}}
                    {{if $value.brandRecruit==false}}
                        招募书未开启
                    {{/if}}
                	<%--<a href="../market/BrandRecruit.jsp"class="ml20 c-b">查看品牌证书</a>--%>
                {{/if}}
            </td>
        </tr>
        {{/each}}
			{{ if rows.length == 0 }}
				<tr>
					<td colspan="5">暂无数据</td>
				</tr>
			{{ /if }}
</script>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script>
    function doCopartnerSearch() {
    	g_pagination.goTo(1);
    }
    
    function joinApple(obj) {
        var brandsId = $.trim($("#brandsId").val());
        var applyText = $.trim($("#applyText").val());
        var telphone = $.trim($("#telphone").val()); 
        $.post("${ctx}/dealer/joinManage/apply/join", {brandsId: brandsId, inviteText: applyText,telphone:telphone}, function (data) {
            if (data.code == 126000) {
                remind("success", "成功提交申请!");
                obj.hide();
                $("#applyText").val('');
                g_pagination.goTo(1);
            } else {
                remind("error", data.message);
            }
        }, "json");
    }

    function updateApple(brandsId) {
        confirmDialog("确定是否要撤销？", function () {
            $.post("${ctx}/dealer/joinManage/apply/update", {brandsId: brandsId}, function (data) {
                if (data.code == 126000) {
                    remind("success", "成功撤销申请!");
                    g_pagination.goTo(1);
                } else {
                    remind("error", data.message + "，不能撤销申请");
                }
            }, "json");
        });
    }
    ;
    function updateInvitedApple(brandsId) {
        $.post("${ctx}/dealer/invited/accept", {brandsId: brandsId}, function (data) {
            if (data.code == 126000) {
                remind("success", "成功接受邀请!");
                g_pagination.goTo(1);
            } else {
                remind("error", data.message + "，不能授受邀请");
            }
        }, "json");
    }
    
      seajs.use(["pagination","template","dialog"], function (Pagination,template,Dialog) {
    	  template.helper('$getImageDomainPathFn',function (url, width, height){
              return getImageDomainPath(url, width, height);
          });
      	 window['g_pagination'] = new Pagination({
               url: "${ctx}/dealer/dealerJoinManage/visitedBrands/data",
               elem: "#pagination",
               form: $("#copartner_form"),
              // method: "post",
               handleData: function (json) {
                   var html = template.render("copartner-template", json);
                   $("#copartner_tbl_body").empty().append(html);
                   //让图片在一个容器里面垂直居中
                   $(".js_img_center,.js-img-center").each(function () {
                       $("<span></span>").appendTo($(this));
                   });
                   var dlg = new Dialog({
                       trigger: 'td a.btn_apply',
                       content: "#add_brand",
                       width: 400
                   }).after('show', function () {
                       var btn = this.activeTrigger;
                       $("#brandsId").val($.trim(btn.parent("td").attr("data_id")));
                       $(this.element).find('#submit').unbind('click').click(function () {
                           joinApple(dlg);
                       });
                   });
               }
           });
      });
</script>


</body>
</html>
