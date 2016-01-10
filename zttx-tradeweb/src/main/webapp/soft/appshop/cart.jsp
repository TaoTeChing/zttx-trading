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
            <dt class="row clearfix">
                <div class="col-xs-2 plr5 text-center"><input class="check-a" type="checkbox"></div>
                <div class="col-xs-10 plr5">
                    <a class="block" href="index.jsp">
                        <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                        hongdou红豆旗舰店
                    </a>
                </div>
            </dt>
            <dd class="row clearfix">
                <div class="col-xs-2 plr5 text-center"><input class="check-b" type="checkbox" data-refrenceid="11223321"></div>
                <div class="col-xs-3 plr5 text-center">
                    <img class="img-responsive" src="/images/soft/appshop/160-160.gif">
                    <h5 class="text-danger">￥<span class="mytotal">360.00</span></h5>
                </div>
                <div class="col-xs-7 plr5">
                    <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                    <span class="help-block">颜色:7条装,精美礼盒装</span>
                    <span class="help-block">尺码:165</span>
                </div>
            </dd>
        </dl>
        <dl class="container shop-list">
            <dt class="row">
                <div class="col-xs-2 plr5 text-center"><input class="check-a" type="checkbox"></div>
                <div class="col-xs-10 plr5">
                    <a href="index.jsp">
                        <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                        hongdou红豆旗舰店
                    </a>
                </div>
            </dt>
            <dd class="row">
                <div class="col-xs-2 plr5 text-center"><input class="check-b" type="checkbox" data-refrenceid="11223321"></div>
                <div class="col-xs-3 plr5 text-center">
                    <img class="img-responsive" src="/images/soft/appshop/160-160.gif">
                    <h5 class="text-danger">￥<span class="mytotal">360.00</span></h5>
                </div>
                <div class="col-xs-7 plr5">
                    <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                    <span class="help-block">颜色:7条装,精美礼盒装</span>
                    <span class="help-block">尺码:165</span>
                </div>
            </dd>
            <dd class="row">
                <div class="col-xs-2 plr5 text-center"><input class="check-b" type="checkbox" data-refrenceid="11223321"></div>
                <div class="col-xs-3 plr5 text-center">
                    <img class="img-responsive" src="/images/soft/appshop/temp/240x240.jpg">
                    <h5 class="text-danger">￥<span class="mytotal">360.00</span></h5>
                </div>
                <div class="col-xs-7 plr5">
                    <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                    <span class="help-block">颜色:7条装,精美礼盒装</span>
                    <span class="help-block">尺码:165</span>
                </div>
            </dd>
        </dl>
        <div class="shop-more">
            <button id="btn_more" class="btn btn-block">
                加载更多...
            </button>
        </div>
    </div>
    <div class="shop-footer">
        <div class="row">
            <div class="col-xs-5 col-sm-3 col-md-3">
                <div class="check-all-box"><input class="check-all" type="checkbox"> 全选</div>
            </div>
            <div class="col-xs-2 col-sm-5 col-md-5">
                <%--<h5 class="text-danger">合计:<span id="myTotal">0.00</span>元</h5>--%>
            </div>
            <div class="col-xs-5 col-sm-4 col-md-4">
                <bottom class="btn btn-danger btn-block" id="submitCart" disabled>语音结算</bottom><%--btn-danger--%>
            </div>
        </div>
    </div>
    <script src="/scripts/soft/appshop/jquery.min.js"></script>
    <script>
        var pn = 1;

        $.fn.offButton = function(setting,callback){

            if($.isFunction(setting))
            {
                callback = setting;
            }
            else {
                switch (setting) {
                    case "destroy":
                        $(".my-checkbox").remove();
                        $(this).unbind("change");
                    break;
                }
                return this;
            }

            var me = this.each(function(i,o){
                var btn = $('<div class="my-checkbox"><span class="glyphicon glyphicon-ok"></span></div>')
                        .click(function(){$(o).trigger("click");})
                        .addClass(o.checked?"checked":"");
                $(o).change(function(){
                    btn.toggleClass("checked", o.checked);
                    if($.isFunction(callback))callback(o,btn,me);
                }).hide().after(btn);
            })
            return me;
        };

        $("input[type=checkbox]").offButton(total);
        $(document).on("click",".check-all",function(){
            var me = this;
            var chk = [];
            $(".check-a").each(function(i,o){
                if(o.checked != me.checked)
                {
                    chk.push(o);
                }
            });
            $(chk).trigger("click");
        });

        $(document).on("click",".check-a",function(){
            var me = this;
            $(me).parents(".shop-list").find(".check-b").each(function(i,o){
                o.checked = me.checked;
                $(o).trigger("change");
            });
            var checka = $(".check-a");
            var checka_checked = $(".check-a:checked");
            $(".check-all")[0].checked = (checka.length == checka_checked.length)
            $(".check-all").trigger("change");
        });

        $(document).on("click",".check-b",function(){
            var me = this;
            var shopList = $(me).parents(".shop-list");
            var checka = $(shopList).find(".check-a");
            var checkb = $(shopList).find(".check-b");
            var checkb_checked = $(shopList).find(".check-b:checked");
            checka[0].checked = (checkb.length == checkb_checked.length);
            $(checka).trigger("change");
            //---------------------------
            var checka = $(".check-a");
            var checka_checked = $(".check-a:checked");
            $(".check-all")[0].checked = (checka.length == checka_checked.length)
            $(".check-all").trigger("change");
        });

        $("#btn_more").click(function(){

            /*$.get("",{pn:pn++},function(data){
                $(this).before(data);
            })*/

            $(this).before($("#myTemplate").html());
            $("input[type=checkbox]").offButton("destroy").offButton(total);
        });


        $("#submitCart").click(function(){
            var checked = $(".check-b").filter(":checked");

            var data = {
                list:[],
                userID:"1111"
            };
            $(checked).map(function(){
                data.list.push({
                    refrenceId:$(this).data("refrenceid")
                });
            });
            console.log(data);

        });

        function total(a,b,c){
            var allchecked = $(c).filter(":checked");
            if($(allchecked).length==0)
                $("#submitCart").attr("disabled","");
            else
                $("#submitCart").removeAttr("disabled");

            /*var money = 0;
            $(allchecked).each(function(i,o){
                var m = $(o).parents("dd").find(".mytotal").text();
                money += Number(m);
            });
            $("#myTotal").text(money);*/
        }
    </script>
<script id="myTemplate" type="text/html">
    <dl class="container shop-list">
        <dt class="row">
            <div class="col-xs-2 plr5 text-center"><input class="check-a" type="checkbox"></div>
            <div class="col-xs-10 plr5">
                <a href="index.jsp">
                    <i class="glyphicon glyphicon-chevron-right pull-right"></i>
                    hongdou红豆旗舰店
                </a>
            </div>
        </dt>
        <dd class="row">
            <div class="col-xs-2 plr5 text-center"><input class="check-b" type="checkbox" data-refrenceid="1122332"></div>
            <div class="col-xs-3 plr5 text-center">
                <img class="img-responsive" src="/images/soft/appshop/temp/240x240.jpg">
                <h5 class="text-danger">￥<span class="mytotal">360.00</span></h5>
            </div>
            <div class="col-xs-7 plr5">
                <h5 class="goods-title"><a href="detail.jsp">红豆女士内裤女中腰蕾丝莫代尔质感性感</a></h5>
                <span class="help-block">颜色:7条装,精美礼盒装</span>
                <span class="help-block">尺码:165</span>
            </div>
        </dd>
    </dl>
</script>
</body>
</html>
