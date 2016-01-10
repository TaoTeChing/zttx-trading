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
<form action="registerOK.jsp">
<div class="shop-register tac">
    <h4>我们已发送<span class="red">验证码短信</span>到这个号码</h4>
    <h3>180 8888 2222</h3>
    <div class="register-box mb20">
        <div class="container">
            <div class="input-group">
                <span class="input-group-addon"><i class="icon icon-setting"></i></span>
                <input class="form-control" type="number" required placeholder="请输入验证码" value="1">
            </div>
        </div>
    </div>
    <div class="container">
        <h5 class="mb20">接受短信大约需要<span id="second" class="red">60</span>秒</h5>
        <button class="btn btn-lg btn-success btn-block" id="btnSubmit" type="submit">完成验证</button>
    </div>
</div>
</form>
<script>
var second = document.getElementById("second");
var sec = 60;
    setInterval(function(){
        sec--;
        second.innerHTML = sec;
        if(sec<=0)
        {
            //todo
        }
    },1000)

</script>
</body>
</html>
