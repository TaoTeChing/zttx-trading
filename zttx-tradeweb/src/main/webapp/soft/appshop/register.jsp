<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0,user-scalable=no">
    <title></title>
    <link href="/styles/soft/appshop/bootstrap.min.css" rel="stylesheet" />
    <link href="/styles/soft/appshop/weshop-register.css" rel="stylesheet" />
</head>
<body>
<form>
<div class="shop-register">
    <h3 class="tac" style="margin-top: 20px;margin-bottom: 20px;">欢迎使用微逛产品</h3>
    <div class="register-box container">
        <div class="row">
            <div class="col-xs-12 col-sm-12">
                <div class="input-group">
                    <span class="input-group-addon"><i class="icon icon-phone"></i></span>
                    <input class="form-control" type="number" required placeholder="手机号码">
                    <span class="input-group-addon" style="padding: 6px"><a class="btn-lg" href="javascript:;" style="padding: 10px 0px;font-size:16px;">获取验证码</a></span>
                </div>
                <hr />
                <div class="input-group">
                    <span class="input-group-addon"><i class="icon icon-setting"></i></span>
                    <input class="form-control" type="password" required placeholder="登录密码">
                </div>
                <hr />
                <div class="input-group">
                    <span class="input-group-addon"><i class="icon icon-lock"></i></span>
                    <input class="form-control" type="password" required placeholder="重复密码">
                </div>
            </div>

        </div>
    </div>
    <div class="container">
        <h5 style="margin-top: 20px;margin-bottom: 20px;">密码为6~16位数字或字母</h5>
        <button class="btn btn-lg btn-success btn-block" id="btnSubmit" type="button">立即注册</button>
        <h5 style="margin-top: 20px;margin-bottom: 20px;">点击上面的"注册"按钮,即表示你同意《<a href="#">微逛使用条例</a>》</h5>
    </div>
</div>
</form>
<!--<script src="js/jquery.js"></script>-->
<script>
    document.getElementById("btnSubmit").onclick = function(){

        if(confirm("确认手机号码\r我们将发送验证码短信到这个号码:\r18088888888"))
        {
            window.location.href="registerCheck.jsp";
        }

    }
</script>
</body>
</html>
