<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${brandesInfo.brandsName}-${brandInfo.comName } 品牌首页8637品牌超级代理</title>
    <meta name="keywords" content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
    <meta name="description" content=""/>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/market/btnico.css" rel="stylesheet" type="text/css"/>
    <link href="${res}/styles/fronts/market/stylemanage.css" rel="stylesheet" type="text/css"/>

    <jsp:include page="/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>

</head>
<body id="allbody">
<%--<!-- top -->--%>
<jsp:include page="${ctx}/common/top"/>
<%--<!-- head -->--%>
<jsp:include page="${ctx}/market/header">
    <jsp:param name="brandesId" value="${brandesId}"/>
    <jsp:param name="url" value="/index"/>
</jsp:include>
<div class="do-body" style="">
    <div class="listbody clear">
        <style>
            .listbody .sidebar-l .content{border: 0;background: none;}
        </style>
        <div class="sidebar-l">
            <div>${configsHTml}</div>
            <%--
             <c:if test="${_brandId==false}">
             <h3 class="title clear mt10">在线留言</h3>
            <div class="content">
                <form class="ui-form leaveMessage" data-widget="validator">
                	<input type="hidden" value="${brandId }" name='brandId'>
                	<input type="hidden" value="${dealerInfo.refrenceId}" name='dealerId'>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            <span class="ui-form-required">*</span>姓名：
                        </label>
                        <input class="ui-input" type="text" name="userName" value="${dealerInfo.dealerUser }" required data-display="姓名"/>
                        <div class="inline-block">
                            <label>
                                <input type="radio" class="ui-radio" name="userGender"  value="1" ${dealerInfo.dealerGender==1?'checked':''}/>先生 
                            </label>
                            <label>
                                <input type="radio" class="ui-radio" name="userGender"  value="2" ${dealerInfo.dealerGender==2?'checked':''}/>女士
                            </label>
                        </div>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            <span class="ui-form-required">*</span>电话：
                        </label>
                        <input class="ui-input" type="text" name="userMobile" value="${dealerUserm.userMobile }" required data-display="电话"/>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            QQ/Email：
                        </label>
                        <input class="ui-input" type="text" name="userContact"/>
                    </div>
                    <div class="ui-form-item">
                        <style>
                            #test1province{margin-right: 5px;}
                            #test1city{width:94px;margin-right: 5px;}
                            #test1county{margin-right: 5px;}
                        </style>
                        
                        <label class="ui-label"><span class="ui-form-required">*</span>加盟地区：</label>
                        <jsp:include page="${ctx}/client/Regional/searchaAll">
                            <jsp:param value="${dealerInfo.province}" name="regProvince" />
                            <jsp:param value="${dealerInfo.city}" name="regCity" />
                            <jsp:param value="${dealerInfo.county}" name="regCounty" />
                            <jsp:param value="test1" name="sale" />
                            <jsp:param value="ui-select seeking-select-width" name="style" />
                        </jsp:include>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            <span class="ui-form-required">*</span>留言：
                        </label>
                        <textarea class="ui-textarea js-leaveMessage-textarea" readonly style="width: 350px; height: 100px;" name="msgText" required data-display="留言信息"></textarea>
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label"></label>
                        <input class="submit-button" type="submit" value="提交留言" />
                    </div>
                    <div class="check-message js-check-message">
                        <h3>您可以根据下列意向选择快捷留言</h3>
                        <p>我对《${brandesInfo.brandName}》很感兴趣，请尽快寄资料给我！</p>
                        <p>请问我所在的地区有加盟商了吗？</p>
                        <p>我想加盟《${brandesInfo.brandName}》，请寄送相关资料给我，谢谢！</p>
                        <p>我想详细了解《${brandesInfo.brandName}》的加盟流程，请与我联系！</p>
                        <p>做为《${brandesInfo.brandName}》的代理加盟商能得到哪些支持？</p>
                        <p>请问投资《${brandesInfo.brandName}》所需要的费用有哪些？</p>
                        <p>我想加盟《${brandesInfo.brandName}》，请电话联系我。</p>
                        <i class="arrow"></i>
                    </div>
                </form>
            </div>
            </c:if>
             --%>
        </div>
        <%--<!--// sidebar-l -->--%>
        <%--<%@ include file="right_sidebar.jsp" %>--%>
        <jsp:include page="${ctx}/market/brand/rightSidebar">
            <jsp:param name="brandId" value="${brandId}"/>
            <jsp:param name="brandesId" value="${brandesId}"/>
        </jsp:include>
        <%--<!--// sidebar-r -->--%>
    </div>
    <%--<!--// listbody -->--%>
</div>
<%--<!-- footer -->--%>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<%--<!-- 结束 -->--%>
<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js" type="text/javascript"></script>
<script src="${src}/fronts/market/jquery.elastislide.js" type="text/javascript"></script>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        if($(".leaveMessage").length > 0){
            baseFormValidator({
                selector:".leaveMessage",
                isAjax:true,
                beforeSubmitFn:function(){
                    if($("#test1province").val() == "" || $("#test1province").val() == "undefined"){
                        remind("error","请选择省");
                        return;
                    }
                    var _areaNo;
                    if($("#test1province").val() != ""){
                        if($("#test1city").val() != ""){
                            if($("#test1county").val() != ""){
                                _areaNo = $.trim($("#test1county").val());
                            }else{
                                _areaNo = $.trim($("#test1city").val());
                            }
                        }else{
                            _areaNo = $.trim($("#test1province").val());
                        }
                    }

                    $.post("${ctx}/market/brand/message/submit",$(".leaveMessage").serialize()+"&areaNo="+_areaNo,function(data){
                        if(data.code==126000){
                            remind("success","留言成功!");
                            $(".js-leaveMessage-textarea").html('');
                        }else{
                            remind("error",data.message);
                        }
                    },"json");
                }
            });
        }
    	   
   	   $(".js-check-message").on("click","p",function(){
              var _text = $(this).html();
              $(".js-leaveMessage-textarea").html(_text);
       });
    	
        var pageList = {};
        seajs.use(["pagination", 'template', 'moment'], function (Pagination, template, moment) {
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("yyyy-MM-dd");
            });

            $(".ajax-templage").each(function (index) {
                var $this = $(this);
                var tempId = $this.attr("id");
                if (tempId == null || tempId == "") {
                    tempId = "templage" + index;
                    $this.attr("id", tempId);
                }
                pageList[tempId] = new Pagination({
                    url: $this.attr("_url"),
                    elem: $this.attr("_page"),
                    method: "post",
                    handleData: function (data) {
                        var html = template.render(tempId, data);
                        $($this.attr("_pid")).html(html);
                    }
                });
            });

            //品牌新闻js
            /*$(".comnews .comnewsul a").bind("click", function () {
                //点击最新发布和热点资讯触发的事件
                var $this = $(this);
                $this.parent().addClass("selected").siblings().removeClass("selected");
                var page = pageList["ajax-templage-comnews"];
                page.data = {newsType: $this.attr("_newsType")};
                page.goTo(1);
            });*/

            //资料下载js
            /*$(".datadownload .datadownloadul a").bind("click", function () {
                //点击下载分类触发的事件
                var $this = $(this);
                $this.parent().addClass("selected").siblings().removeClass("selected");
                var page = pageList["ajax-templage-datadownload"];
                page.data = {cateId: $this.attr("_cateId")};
                page.goTo(1);
            });*/

            //收藏品牌
            $(".attention").on('click', '.js-collected-brands', function () {
                $.post("${ctx}/market/brand/attentionNum/add", {brandesId: "${brandesId}"}, function (data) {
                    switch (data.code) {
                        case zttx.SUCCESS:
                            remind("success", "收藏成功", 1000, function () {
                                var _value = parseInt($("em.gz-num").text());
                                $(".attention span.weiguanzhu").addClass("yiguanzhu").removeClass("weiguanzhu").next().text("已收藏").removeClass("js-collected-brands");
                                $("em.gz-num").text(_value+1);
                            });
                            break;
                        case zttx.NOT_LOGIN:
                            var currentUrl = location.href;
                            location.href = '${ctx}/common/login?loginType=1&redirect=' + encodeURIComponent(currentUrl);
                            break;
                        default :
                            remind("error", data.message);
                    }
                }, "json");
            });
        });

        setTimeout(function() {
            $.post('${ctx}/market/user_rtk', {
                id: '${brandesId}',
                code: 1
            });
        }, 2000);
    });
</script>
<%--视频主持人--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>
--%><%-- 结束 --%>
</body>
</html>
