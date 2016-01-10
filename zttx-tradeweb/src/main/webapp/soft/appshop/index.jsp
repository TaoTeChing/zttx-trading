<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0,user-scalable=no,target-densitydpi=device-dpi">
    <title></title>
    <link href="/styles/soft/appshop/bootstrap.min.css" rel="stylesheet" />
    <link href="/styles/soft/appshop/weshop.css" rel="stylesheet" />
</head>
<body>
    <div class="shop-logo" style="background-image: url(/images/soft/appshop/temp/dsafasfsagas111`1.jpg)">
        <span class="badge pull-right"><i class="glyphicon glyphicon-heart"></i> 59</span> <%--点击后 i.active--%>
        <div class="logo-box">
            <img class="img-thumbnail" src="/images/soft/appshop/temp/APP_LOGO_96.jpg" />
        </div>
        <h1>ONLY官方旗舰店</h1>
    </div>
    <div class="shop-content">
        <ul class="list-group">
            <li class="list-group-item">
                <div class="btn-group btn-group-justified">
                    <a class="btn" role="button">收藏店铺</a>
                    <a class="btn" role="button" href="tel:15888190257">拨打电话</a>
                    <a class="btn" role="button">查看路线</a>
                </div>
            </li>
            <li class="list-group-item clearfix">
               <i class="glyphicon glyphicon-map-marker"></i> <strong>0.18Km</strong><span class="pull-right">地址:宁波市海曙区丽园北路755号宁波市海曙区丽园北路755号</span>
            </li>
        </ul>
        <div class="panel panel-default">

            <div class="panel-body">
                <table class="nav nav-justified" id="mytab">
                    <tr>
                        <td data-type="0"><a role="tab" href="javascript:;">热卖商品</a></td>
                        <td data-type="1"><a role="tab" href="javascript:;">最新商品</a></td>
                        <td data-type="2"><a role="tab" href="javascript:;">特价推荐</a></td>
                    </tr>
                </table>
                <%--<ul class="nav nav-pills nav-justified"  role="tablist">
                    <li data-type="0"><a role="tab" href="javascript:;">热卖商品</a></li>
                    <li data-type="1"><a role="tab" href="javascript:;">最新商品</a></li>
                    <li data-type="2"><a role="tab" href="javascript:;">特价推荐</a></li>
                </ul>--%>
            </div>
        </div>

        <div class="container tab-pane " id="myList">
            <div class="row">
                <div class="col-xs-6 col-sm-4 pl5 pr5">
                            <a href="detail.jsp" class="thumbnail">
                                <img class="img-responsive" src="/images/soft/appshop/temp/temp2.jpg" />
                                <div>
                                    <h5>夏新品宽松男友风BF中腰牛仔七分裤女
                                    </h5>
                                    <h5 class="money">￥399.00 <small><del>￥599</del></small>
                                    </h5>
                                </div>
                            </a>
                </div>
                <div class="col-xs-6 col-sm-4 pl5 pr5">
                    <a href="detail.jsp" class="thumbnail">
                        <img class="img-responsive" src="/images/soft/appshop/temp/temp2.jpg" />
                        <div>
                            <h5>夏新品宽松男友风BF中腰牛仔七分裤女
                            </h5>
                            <h5 class="money">￥399.00 <small><del>￥599</del></small>
                            </h5>
                        </div>
                    </a>
                </div>
                <div class="col-xs-6 col-sm-4 pl5 pr5">
                    <a href="detail.jsp" class="thumbnail">
                        <img class="img-responsive" src="/images/soft/appshop/temp/temp2.jpg" />
                        <div>
                            <h5>夏新品宽松男友风BF中腰牛仔七分裤女
                            </h5>
                            <h5 class="money">￥399.00 <small><del>￥599</del></small>
                            </h5>
                        </div>
                    </a>
                </div>
                <div class="col-xs-6 col-sm-4 pl5 pr5">
                    <a href="detail.jsp" class="thumbnail">
                        <img class="img-responsive" src="/images/soft/appshop/temp/temp2.jpg" />
                        <div>
                            <h5>夏新品宽松男友风BF中腰牛仔七分裤女
                            </h5>
                            <h5 class="money">￥399.00 <small><del>￥599</del></small>
                            </h5>
                        </div>
                    </a>
                </div>
            </div>
            <div style="padding-bottom:20px;">
                <button id="myMore" class="btn btn-block">点击加载更多</button>
            </div>
        </div>
    </div>

    <script src="/scripts/soft/appshop/jquery.min.js"></script>
    <script src="/scripts/soft/appshop/bootstrap.min.js"></script>
    <script>
        var myList = $("#myList .row");
        var mytab =  $("#mytab td a").click(function(){
            $(mytab).removeClass("active");
            var type = $(this).addClass("active").data("type");
            //异步
            //$.get("",function(data){
            $(myList).html("");
            getdata();
            //});

        });
        var myMore = $("#myMore").click(function(){
            getdata();
        });
        function getdata()
        {
            var data = [1,2,3,4,5,6];
            $.each(data,function(i,o){
                $(myList).append($("#myModel")[0].innerHTML);
            });
        }
    </script>
<script type="text/html" id="myModel">
    <div class="col-xs-6 col-sm-4 pl5 pr5">
        <a href="detail.jsp" class="thumbnail">
            <img class="img-responsive" src="/images/soft/appshop/temp/temp2.jpg" />
            <div>
                <h5>夏新品宽松男友风BF中腰牛仔七分裤女</h5>
                <h5 class="money">￥399.00 <small><del>￥599</del></small></h5>
            </div>
        </a>
    </div>
</script>
</body>
</html>
