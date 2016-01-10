<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>联系我们-留言反馈</title>
    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/about/index.css" rel="stylesheet"/>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
    <style>
        .contactform-main #contactFrom .button span{
            clear: both;
            width: 560px;
        }
        .Validform_checktip{*zoom:1;display:block;width:90%;background:#fff;position: absolute;top:50px;left: 0px;}
        .Validform_checktip:before,.Validform_checktip:after{content:"";display:table;font:0/0 a}
        .Validform_checktip:after{clear:both}
        .confirm .Validform_checktip{top:20px}
        .Validform_checktip:hover{text-decoration:none}
        .Validform_wrong,.Validform_right{height:18px;background:#fff url("${res}/images/common/error.png") no-repeat;color:#e00;padding-left:20px;z-index:2}
        .Validform_right{background:#fff url("${res}/images/common/right.png") no-repeat;color:#87C749}
        .error-box{padding-left:80px;height:35px}
        #message .Validform_checktip{top:240px;left: 0px;}
    </style>
</head>
<body>
<div class="container ">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/fronts/about/_header.jsp">
        <jsp:param value="4" name="m"/>
        <jsp:param value="4" name="n"/>
        <jsp:param value="2" name="k"/>
    </jsp:include>
    <div class="main">
        <div class="contactform-pic"></div>
        <div class="contactform-main">
            <form id="contactFrom" action="" method="post">
                <div style="position: relative;overflow: hidden;padding-bottom: 30px"><input  id='username' placeholder="名字" type="text" datatype="isNull" errormsg="请输入名字"></div>
                <div style="position: relative;overflow: hidden;padding-bottom: 30px"><input id="userMobile" placeholder="电话" type="text" datatype="m,isNull" errormsg="请输入正确电话号码"></div>
                <div style="position: relative;overflow: hidden;padding-bottom: 30px"><input id='email' placeholder="邮箱：" type="text" datatype="e,isNull" errormsg="请输入正确邮箱"></div>
                <div id="message" style="position: relative;overflow: hidden;padding-bottom: 30px"><textarea id="content" placeholder="内容" datatype="isNull" errormsg="请输入內容" ></textarea></div>
                <div class="button ta-c" style="width: 560px">
                    <span><button id="submit" type="submit">提交</button></span>
                    <p>感谢您的反馈，我们将与您联系尽快解决！</p>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
</div>
</body>

<script src="${res}/scripts/common.js"></script>
<script src="${src}/common/base-init.js"></script>
<script src="${src}/plugin/Validform_v5.3.2_min.js"></script>

<script>

    $(function(){
        $("#contactFrom").Validform({
            tiptype: 4,
            postonce: true,
            datatype:{
                ispassword:function(gets,obj,curform,regxp){
                    var _reg =/^[\_\@\.\-a-zA-Z0-9]{6,16}$/;
                    return _reg.test(gets);
                },
                isNull:function(gets, obj, curform, regxp){
                    $(obj).val($.trim(gets.replace(/\s/g, "")));
                    return $.trim(gets) != "";
                }
            },
            ajaxPost:true,
            callback:function(data){
                //alert("提交成功！");
            }
        });
    });
</script>
</html>