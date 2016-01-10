<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0,user-scalable=no">
    <title></title>
    <link href="/styles/soft/appshop/bootstrap.min.css" rel="stylesheet" />
    <link href="/styles/soft/appshop/weshop-message.css" rel="stylesheet" />
</head>
<body>
    <div class="shop-app-download">
        <a href="#" title="微逛下载">
            <div class="">
                <strong>180 8888 2222</strong><br />
                发起聊天
            </div>
        </a>
    </div>
    <div class="shop-app-message">
        <div class="container">
            <ul class="messagebox">
                <li class="from">
                    <dl>
                        <dt>
                            <img class="img-responsive" src="/soft/appshop/scrollbar-bg.png">
                            <span></span>
                        </dt>
                        <dd>我是朱朱,最近怎样?我是朱朱,最近怎样?我是朱朱,最近怎样?我是朱朱,最近怎样?</dd>
                    </dl>
                </li>
                <li class="my">
                    <dl>
                        <dt>
                            <img class="img-responsive" src="/soft/appshop/scrollbar-bg.png"><span></span></dt>
                        <dd>11" <span></span></dd>
                    </dl>
                </li>
                <li class="from">
                    <dl>
                        <dt>
                            <img class="img-responsive" src="/soft/appshop/scrollbar-bg.png"><span></span></dt>
                        <dd>30" <span></span></dd>
                    </dl>
                </li>
                <li class="my">
                    <dl>
                        <dt>
                            <img class="img-responsive" src="/soft/appshop/scrollbar-bg.png"><span></span>
                        </dt>
                        <dd>我是朱朱,最近怎样?我是朱朱,最近怎样?我是朱朱,最近怎样?我是朱朱,最近怎样?</dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="shop-app-message-send">
        <div class="container">
            <div class="input-group">
                <input class="form-control" id="myText" value="" placeholder="输入文字" />
                <span class="input-group-btn">
                    <button class="btn btn-primary" id="btnSend" type="button">发送</button>
                </span>
            </div>
        </div>
    </div>

    <script>
        var index = 0;//标记
        setInterval(function () {
            //异步获取得到Data
            var Data = {
                type: "from",
                photo: "/soft/appshop/scrollbar-bg.png",
                isText: true,
                text: "朱朱,最近怎样?我是君君" + Math.random() * 1000
            };
            AddList(Data);
        }, 15000);

        var btnSend = document.getElementById("btnSend").onclick = function () {
            var myText = document.getElementById("myText");
            var myData = {
                type: "my",
                photo: "/soft/appshop/scrollbar-bg.png",
                isText: true,
                text: myText.value
            };

            AddList(myData, function () {
                myText.value = "";
            });
        };

        function AddList(fromData, callback) {
            fromData.index = ++index;
            var messagebox = document.getElementsByClassName("messagebox")[0];
            var html = '<li class="' + fromData.type + '" id="' + fromData.index + '"><dl><dt><img class="img-responsive" src="' + fromData.photo + '"><span></span></dt><dd>' + fromData.text + (fromData.isText ? '' : '<span></span>') + '</dd></dl></li>';
            messagebox.innerHTML += html;
            window.location.hash = "#" + index;
            //index++;
            if (callback) callback();
        }
    </script>

</body>
</html>
