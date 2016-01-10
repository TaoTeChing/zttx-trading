<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0,user-scalable=no">
    <title></title>
    <link href="/styles/soft/appshop/bootstrap.min.css" rel="stylesheet" />
    <link href="/styles/soft/appshop/weshop-cart.css" rel="stylesheet" />
</head>
<body>
    <%--<div class="shop-search">
        <div class="container">
         <input class="form-control" speech x-webkit-speech placeholder="搜 索" />
        </div>
    </div>--%>
    <div class="shop-content">
        <dl class="container shop-list">
            <dt class="row">
                <div class="col-xs-12 plr5">
                    <a href="index.jsp">
                        <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                        hongdou红豆旗舰店
                    </a>
                </div>
            </dt>
            <dd class="row">
                    <div class="col-xs-3 plr5">
                        <a href="detail.jsp"><img class="img-responsive" src="/images/soft/appshop/160-160.gif"></a>
                    </div>
                    <div class="col-xs-6 plr5">
                        <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                        <span class="help-block">颜色:7条装,精美礼盒装</span>
                        <span class="help-block">尺码:165</span>
                    </div>
                    <div class="col-xs-3 plr5 text-center"><h5 class="text-danger">￥360.00</h5></div>

            </dd>
        </dl>
        <dl class="container shop-list">
            <dt class="row">
                <div class="col-xs-12 plr5">
                    <a href="index.jsp">
                        <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                        hongdou红豆旗舰店
                    </a>
                </div>
            </dt>
            <dd class="row">
                <div class="col-xs-3 plr5 text-center">
                    <a href="detail.jsp"><img class="img-responsive" src="/images/soft/appshop/temp/240x240.jpg"></a>
                </div>
                <div class="col-xs-6 plr5">
                    <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                    <span class="help-block">颜色:7条装,精美礼盒装</span>
                    <span class="help-block">尺码:165</span>
                </div>
                <div class="col-xs-3 plr5 text-center"><h5 class="text-danger">￥360.00</h5></div>
            </dd>
        </dl>
        <div class="shop-more">
            <button class="btn btn-block" id="btn_more">
                加载更多...
            </button>
        </div>
    </div>

    <script src="/scripts/soft/appshop/jquery.min.js"></script>
    <script>
        $("#btn_more").click(function(){
            /*$.get("",{pn:pn++},function(data){
             $(this).before(data);
             })*/
            $(this).before($("#myTemplate").html());
        });
    </script>
    <script id="myTemplate" type="text/html">
        <dl class="container shop-list">
            <dt class="row">
                <div class="col-xs-12 plr5">
                    <a href="index.jsp">
                        <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                        hongdou红豆旗舰店
                    </a>
                </div>
            </dt>
            <dd class="row">
                <div class="col-xs-3 plr5 text-center">
                    <a href="detail.jsp"><img class="img-responsive" src="/images/soft/appshop/temp/240x240.jpg"></a>
                </div>
                <div class="col-xs-6 plr5">
                    <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                    <span class="help-block">颜色:7条装,精美礼盒装</span>
                    <span class="help-block">尺码:165</span>
                </div>
                <div class="col-xs-3 plr5 text-center"><h5 class="text-danger">￥360.00</h5></div>
            </dd>
        </dl>
    </script>
</body>
</html>
