<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-反馈信息</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet"/>
    <link href="${res}/styles/dealer/feedback.css" rel="stylesheet"/>
    <style type="text/css">
    	.ui-form-explain{display: block;}
    </style>
</head>
<body>
<div class="container">
   	<jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <jsp:include page="${ctx}/dealer/dealermenu"/>
            <div class="main-right">
               	<jsp:include page="/WEB-INF/view/dealer/agency_header_message.jsp" />
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <p>
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> &gt;&gt; <span class="bb">反馈信息</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </p>
                    </div>
                </div>
		<div class="inner">
                <div class="main-grid clearfix">
                    <div class="panel-form">
                        <form:form id="Form_FeedBack" data-widget="validator" class="ui-form" action="${ctx}/dealer/feedback" method="post">
                            <div class="ui-form-item" style="padding-bottom: 5px;">
                                <label class="ui-label">选择品牌:</label>
				<div class="inline-block pr">
                                <select id="select_brand" class="ui-select js_select" name="dealerJoinId"  data-display="品牌" >
                                    <option value="">请选择...</option>
                                    <c:forEach items="${brandsList}" var="brands">
                                        <option value="${brands.refrenceId}">${brands.brandsName}</option>
                                    </c:forEach>
                                </select>
				</div>
                                <div class="ui-form-explain">&nbsp;</div>
                            </div>
                            <div class="ui-form-item"  style="padding-bottom: 5px;">
                                <label class="ui-label">填写反馈:</label>
                                <textarea class="ui-textarea" name="contents" explainClass="show-none"  data-display="反馈内容" placeholder="请输入反馈信息" maxlength="5120" style="height: 100px;"></textarea>
                            	<div class="ui-form-explain">&nbsp;</div>
                            </div>
                            <div class="ui-form-item">
                                <input type="submit" class="ui-button ui-button-lred" value="提 交"/>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="main-grid clearfix" style="margin-bottom:0px; ">
                    <div class="ui-table-container" style='height: 100%;'>
                        <h3><span>历史反馈</span></h3>
                        <table class="ui-table ui-table-inbox">
                            <thead>
	                            <tr>
	                                <th class="cell-1">时间</th>
	                                <th class="cell-2">品牌</th>
	                                <th class="cell-3">评价内容</th>
	                                <th class="cell-4">反馈状态</th>
	                            </tr>
                            </thead>
                            <tbody id="tbody"> </tbody>
                        </table>
                        <div id="pagination"></div>
                    </div>
                </div>
		</div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="feedback-template" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$formatDate $value.createTime}}</td>
        <td>{{$value.brandsName}}</td>
        <td><span title="{{$value.feedText}}">{{$trimLongString $value.feedText 30}}</span></td>
        <td>
            {{if $value.replyText && $value.replyText != ''}} 品牌商已回复{{else}} 已发送{{/if}}
        </td>
    </tr>
    {{/each}}
</script>
<script type="text/javascript">
	/** 初始表单验证*/
    baseFormValidator({
        selector:'#Form_FeedBack',isAjax:true,
        addElemFn:function(Core,validator){
            Core.addItem({
                element : '[name=contents]',
                display : '反馈信息',
                required : true,
                rule : 'maxlength{"max":5120}'
            }).addItem({
                element : '#select_brand',
                required : true
            });
        },
        beforeSubmitFn:function(){
            $.post('${ctx}/dealer/feedback/save', $('#Form_FeedBack').serialize(), function(data) {
                if (data.code == 126000) {
                    remind("success", "感谢您的反馈!");
                    g_pagination.goTo(1);
                    $('#Form_FeedBack')[0].reset();
                } else {
                    remind("error", data.message);
                    // confirmDialog({'title':"操作提示",'content':data.message},function(){},false,function(){},true);
                }
            }, "json");
        }
    });

    seajs.use(['template', 'pagination', 'moment'], function (template, Pagination, moment) {
        template.helper('$formatDate', function (millsec) {
            return moment(millsec).format(" YYYY-MM-DD HH:mm:ss");
        });
        template.helper('$trimLongString', trimLongString);
        
       window['g_pagination']= new Pagination({
            url: '${ctx}/dealer/feedback/list',
            elem: '#pagination',
            handleData: function (data) {
                var html = template.render('feedback-template', data);
                $('#tbody').empty().append(html);
            }
        });
    });
</script>
</body>
</html>
