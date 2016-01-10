<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-域名设置</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
    <style>
        .main .main_con .inner{
            background: none;
        }
    </style>
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
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">域名设置</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <!-- 面包屑结束 -->
    <div class="inner">
    <!-- 内容都放这个地方  -->
        <div class="set-domin">
            <%--设置过域名--%>
            <div class="set">
                <p class="title"><span>您设置过的品牌和域名有</span></p>
                <table class="table">
                    <thead>
                        <tr>
                            <th>品牌</th>
                            <th>域名</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${brandsDomains}" var="bd">
                    	<tr>
                            <td>${bd.brandedName}</td>
                            <td><input class="ui-input js-domin-name" type="text" style="width: 70px;" value="${bd.domain}" data-id="${bd.brandsId}"/> .8637.com</td>
                            <td>
                                <a class="ui_button ui_button_lblue js-save" href="javascript:;">保存</a><br />
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--未设置过域名--%>
            <div class="query">
                <p class="title"><span>查询可用域名</span></p>
                <div class="tips"><i class="v2-icon-explain"></i>暂时不提供六位以内的二级域名，开放时间另行通知</div>
                <form:form class="ui-form selectDominName clearfix">
                    <div class="ui-form-item">
                        <input type="text" class="ui-input js-domin-input" style="width: 70px;" />
                        <span>.8637.com</span>
                    </div>
                    <div class="ui-form-item">
                        <button type="button" class="simple_button js-select-domin"/>查询</button>
                    </div>
                </form:form>
                <div class="set-domin-tip mt10"></div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script>
    var setDominName ={
        init:function(){
            this.saveDominName();
            this.selectDominName();
        },
        saveDominName:function(){
            var _this = this;
            $(".table").on("click",".js-save",function(){
                var _parent = $(this).parents("tr");
                var _val = _parent.find(".js-domin-name").val();
                var brandsId = _parent.find(".js-domin-name").data("id");

                if(!_this.dominReg(_val)){
                    remind("error","域名长度为6-16位，不能含有特殊字符,且不能为中文");
                    return;
                }else{
                    /* 发送请求 保存_val*/
                    $.ajax({
                    	type:"post",
                    	url:"/brand/brands/updateDomain",
                    	data:{domain:_val,brandsId:brandsId},
                    	dataType:"json",
                    	success:function(data){
                    		if(data.code=='126000'){
                    			 remind("success","域名修改成功");
                    		}else if(data.code=='126016'){
                    			remind("error","参数验证失败");
                    		}else{
                    			remind("error","域名重复");
                    		}
                    	}
                    });
                }
            });
        },
        selectDominName:function(){
            var _this = this;
            $(".selectDominName").on("click",".js-select-domin",function(){
                var _val = $.trim($(".js-domin-input").val());
                if(!_this.dominReg(_val)){
                    remind("error","域名长度为6-16位，不能含有特殊字符,且不能为中文");
                    return;
                }else{
                    var flag = true;
                    //查询的时候还存在一种情况，当前查询的域名和之前查询过的域名一样,以下是页面处理这种情况的方法
                    $(".set-domin-tip p").each(function(){
                        var _domin = $(this).find(".js-domin").text();
                        if(_val == _domin){
                            remind("error","您已经查询过这个域名");
                            flag = false;
                        }
                    });
                    if(flag == true){
                        $.ajax({
                        	type:"post",
                            url:"/brand/brands/searchDomain/"+_val,
                            dataType:"json",
                            success:function(json){
                           		if(json.code=='126000'){
                           			var _html = '<p class="icon_success"><span class="js-domin">'+_val+'</span>.8637.com <span class="success">可以使用</span>';
                           			$(".set-domin-tip").append(_html);
                           		}else{
                           			var _html = '<p class="icon_error"><span class="js-domin">'+_val+'</span>.8637.com <span class="danger">已被使用</span></p>';
                           			$(".set-domin-tip").append(_html);
                           		}
                            }
                        });
                    }
                }
            });
        },
        dominReg:function(val){
            return /^[A-Za-z0-9]{6,16}$/g.test(val);
        }
    };
    $(function(){
        setDominName.init();
    });
</script>
</body>
</html>