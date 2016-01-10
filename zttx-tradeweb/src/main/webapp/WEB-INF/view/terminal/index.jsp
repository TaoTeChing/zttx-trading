<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <c:set var="regionalAreaName" value=""></c:set>
    <c:set var="tradeName" value=""></c:set>
    <c:set var="modelName" value=""></c:set>
    <c:if test="${dealerShopEnv.trade==1 }"><c:set var="tradeName" value="男装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==2 }"><c:set var="tradeName" value="女装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==3 }"><c:set var="tradeName" value="童装"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==4 }"><c:set var="tradeName" value="内衣"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==5 }"><c:set var="tradeName" value="家居"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==6 }"><c:set var="tradeName" value="鞋类"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==7 }"><c:set var="tradeName" value="箱包"></c:set></c:if>
    <c:if test="${dealerShopEnv.trade==8 }"><c:set var="tradeName" value="其他"></c:set></c:if>


    <c:if test="${dealerShopEnv.model==1 }"><c:set var="modelName" value="专卖店"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==2 }"><c:set var="modelName" value="混合店"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==3 }"><c:set var="modelName" value="专卖加混合"></c:set></c:if>
    <c:if test="${dealerShopEnv.model==4 }"><c:set var="modelName" value="其他"></c:set></c:if>


    <c:forEach items="${regionals }" var="regional">
        <c:if test="${dealerShopEnv.areaNo==regional.areaNo }"> <c:set var="regionalAreaName"
                                                                       value="${regional.areaName}"></c:set> </c:if>
    </c:forEach>
    <meta name="keywords"
          content="	${cityName} ${regionalAreaName} ${tradeName} ${modelName}招商加盟, ${cityName} ${regionalAreaName} ${tradeName}批发, ${cityName} ${regionalAreaName} ${tradeName} ${modelName} 品牌代理"/>
    <meta name="description"
          content="8637品牌超级代理帮助品牌厂商最低成本、最快速度拓展渠道。${cityName} ${regionalAreaName} ${tradeName} ${modelName} 招商加盟，全国百万家实体门店数据，覆盖全国的服务机构上门推广无缝对接。"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${cityName} ${regionalAreaName} ${tradeName} ${modelName} 实体店分类信息_招商加盟-8637品牌超级代理</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <style>
        .nav {
            border-bottom: 2px solid #ed0100;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp">
        <jsp:param value="" name="m"/>
    </jsp:include>
    <div class="ts-container">
        <div class="row clearfix">
            <form:form id="dealerShopEnvForm" method="get">
                <div class="g-leftside">
                    <div class="m-breadnav">
                        <a href="${ctx}/">首页</a>
                        <span>&gt;&gt;</span>
                        <span>终端商名录</span>
                    </div>
                    <div style="height:20px;line-height:20px;"><strong class="text-red">${cityName}</strong> [<a
                            class="link" href="${ctx }/shop/city">切换城市</a>]
                    </div>
                    <div class="panel mt10">
                        <%--<div class="m-selected">
                            <span>已选条件：</span>
                            <a href="#">箱包<i>×</i></a>
                            <a href="#">江东<i>×</i></a>
                            <a href="#">清空<i>×</i></a>
                        </div>--%>
                        <div class="m-condition panel-body">
                            <dl class="select">
                                <dt>行业分类：</dt>
                                <dd>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==0||dealerShopEnv.trade==null||dealerShopEnv.trade==''  }">class="active"</c:if> data-id="0">不限</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==1 }">class="active"</c:if> data-id="1">男装</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==2 }">class="active"</c:if> data-id="2">女装</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==3 }">class="active"</c:if> data-id="3">童装</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==4 }">class="active"</c:if> data-id="4">内衣</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==5 }">class="active"</c:if> data-id="5">家居</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==6 }">class="active"</c:if> data-id="6">鞋类</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.trade==7 }">class="active"</c:if> data-id="7">箱包</a>
                                    <a href="javascript:;"　<c:if test="${dealerShopEnv.trade==8 }">class="active"</c:if> data-id="8">其他</a>
                                </dd>
                            </dl>
                            <dl class="select">
                                <dt>所在地区：</dt>
                                <dd>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.areaNo==null||dealerShopEnv.areaNo==''||dealerShopEnv.areaNo==0}">class="active"</c:if> data-id="0">不限</a>
                                    <c:forEach items="${regionals }" var="regional">
                                        <a href="javascript:;" <c:if test="${dealerShopEnv.areaNo==regional.areaNo }">class="active"</c:if> data-id="${regional.areaNo}">${regional.areaName}</a>
                                    </c:forEach>
                                </dd>
                            </dl>
                            <dl class="select last">
                                <dt>销售模式：</dt>
                                <dd>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.model==0||dealerShopEnv.model==null||dealerShopEnv.model=='' }">class="active"</c:if> data-id="0">不限</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.model==1 }">class="active"</c:if> data-id="1">专卖店</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.model==2 }">class="active"</c:if> data-id="2">混合店</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.model==3 }">class="active"</c:if> data-id="3">专卖加混合</a>
                                    <a href="javascript:;" <c:if test="${dealerShopEnv.model==4 }">class="active"</c:if> data-id="4">其他</a>
                                </dd>
                            </dl>
                            <div class="mt10">
                                <div class="search-box">
                                    <input type="text" class="text1" placeholder="请输入您想找的商家信息，如杭州服装、上海耳环" name="shopName"
                                           value="${dealerShopEnv.shopName}"/>
                                    <button class="btn1">搜 索</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="main-list mt10">
                        <div class="m-tab list-head clearfix">
                            <ul class="tab-dealer inline-float">
                                <li <c:if test="${dealerShopEnv.shape==0||dealerShopEnv.shape==null||dealerShopEnv.shape=='' }">class="active"</c:if>>
                                    <a onclick="setShape(0)" href="javascript:;">全 部</a></li>
                                <li <c:if test="${dealerShopEnv.shape==1 }">class="active"</c:if>>
                                    <a onclick="setShape(1)" href="javascript:;">街 铺</a></li>
                                <li <c:if test="${dealerShopEnv.shape==2 }">class="active"</c:if>>
                                    <a onclick="setShape(2)" href="javascript:;">市场/商场</a></li>
                                <li <c:if test="${dealerShopEnv.shape==3 }">class="active"</c:if>>
                                    <a onclick="setShape(3)" href="javascript:;">集散市场</a></li>
                            </ul>
                            <%--<ul class="tab-order inline-float">
                                <li><a><input class="ui-checkbox" name="onlyShowPic" type="checkbox" id="showPic"
                                              onclick="$('.search-box button').trigger('click');"
                                              <c:if test="${dealerShopEnv.onlyShowPic==true}">checked</c:if> > 只看有图</a>
                                </li>
                                <li><a class="active"><i class="shop-icon shop-icon-grid" title="大图展示">1</i></a></li>
                                <li><a><i class="shop-icon shop-icon-list" title="列表展示">2</i></a></li>
                            </ul>--%>
                        </div>
                        <div class="m-dirlist list-dealer mt10">
                            <ul class="order-grid">
                                <c:if test="${fn:length(results1.list)==0}">
                                    <li style="font-size: 14px;text-align: center;">暂无搜索结果，请尝试使用其他关键词进行搜索</li>
                                </c:if>
                                <c:forEach items="${results1.list}" var="item">
                                    <li>
                                        <div class="pic">
                                            <%--<a class="js-img-center" href="${ctx}/shop/details/${item.refrenceId}" target="_blank">--%>
                                            <a class="js-img-center" target="_blank">
                                                <c:if test="${fn:length(item.dealerImages)>0}">
                                                    <img src="${res}${fns:getImageDomainPath(item.dealerImages[0].imageName, 400, 400)}"/>
                                                </c:if>
                                                <c:if test="${fn:length(item.dealerImages)==0}">
                                                    <img src="${ctx}/images/common/nopic-400.gif">
                                                </c:if>
                                            </a>
                                        </div>
                                        <div class="detail">
                                            <h3>${fns:trimLongText(item.shopName,8)}&nbsp;</h3>
                                            <p>
                                                <span>
                                                    主营类目：
                                                    <c:choose>
                                                    <c:when test="${item.trade==1}">男装 </c:when>
                                                    <c:when test="${item.trade==2}">女装 </c:when>
                                                    <c:when test="${item.trade==3}">童装 </c:when>
                                                    <c:when test="${item.trade==4}">内衣 </c:when>
                                                    <c:when test="${item.trade==5}">家居 </c:when>
                                                    <c:when test="${item.trade==6}">鞋类 </c:when>
                                                    <c:when test="${item.trade==7}">箱包 </c:when>
                                                    <c:when test="${item.trade==8}">其他 </c:when>
                                                    <c:otherwise>未知 </c:otherwise>
                                                    </c:choose></span>
                                                <span>销售模式：专卖店</span>
                                                <span>所在地区：${item.provinceName}-${item.cityName}-${item.areaName}</span></p>
                                            <p>
                                                <span>联系人：${item.username}</span>
                                                <c:choose>
                                                    <c:when test="${hasLogon}">
                                                        <c:choose>
                                                            <c:when test="${item.userType == 1}"> <!--经销商-->
                                                                <span>联系电话：${fns:hideTelNumber(item.mobile)}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${isExits}">
                                                                        ${userMobile}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span>联系电话：<label id="phone_num_${item.dealerId}">${fns:hideTelNumber(item.mobile)}&nbsp;&nbsp;
                                                                            <a class="js-look t-d-look" href="javascript:;"
                                                                               data-id="${item.dealerId}">立即查看</a>
                                                                        </label>
                                                                        </span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span>联系电话：<a href="${ctx}/common/login">[请登录]</a></span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <%--登录后 当号码获取过， 再刷新的时候号码 应该是显示出来的。。。 如不确认和产品沟通一下--%>
                                                <%--<span>联系电话：<em>133********</em><a href="javascript:;" class="js-look-photonum" data-id="111">[查看详情]</a></span>--%>
                                            </p>
                                                <%--<p class="times">累计进货次数：2</p>--%>
                                        </div>
                                        <div class="address">
                                            <c:choose>
                                                <c:when test="${hasLogon}">
                                                    <!-- 收藏 -->
                                                    <c:choose>
                                                        <c:when test="${item.collectedState}">
                                                            <a class="btn1 js-collect" href="javascript:;"
                                                               data-id="${item.dealerId}">已收藏</a><br/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:if test="${item.userType == 0}"> <!--品牌商-->
                                                                <a class="btn1 js-collect" href="javascript:;"
                                                                   data-id="${item.dealerId}">收藏店铺</a><br/>
                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!-- 加盟-->
                                                    <c:if test="${item.userType == 0}"> <!--品牌商-->
                                                        <a class="btn1 js-invita-join" href="javascript:;"
                                                           data-id="${item.dealerId}" data-haslogon="${hasLogon}">邀请加盟</a>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="btn1 js-collect" href="javascript:;"
                                                       data-id="${item.dealerId}">收藏店铺</a><br/>
                                                    <a class="btn1 js-invita-join" href="javascript:;"
                                                       data-id="${item.dealerId}">邀请加盟</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <tags:pageext form="dealerShopEnvForm" page="${results1.page}"/>
                        <c:if test="${results2!=null}">
                            <div class="directory_tips">
                                <span class="fl">以上同类终端商较少，其他终端商不妨考虑一下：</span>
                                <a class="fr" href="javascript:;">搜更多周边终端上&gt;&gt;</a>
                            </div>
                            <div class="m-dirlist list-dealer mt10">
                                <ul class="order-grid">
                                    <c:forEach items="${results2.list}" var="item">
                                        <li>
                                            <div class="pic">
                                                <%--<a class="js-img-center" href="${ctx}/shop/details/${item.refrenceId}" target="_blank">--%>
                                                <a class="js-img-center" href="javascript:;" target="_blank">
                                                    <c:if test="${fn:length(item.dealerImages)>0}">
                                                        <img src="${res}${fns:getImageDomainPath(item.dealerImages[0].imageName, 400, 400)}"/>
                                                    </c:if>
                                                    <c:if test="${fn:length(item.dealerImages)==0}">
                                                        <img src="${ctx}/images/common/nopic-400.gif">
                                                    </c:if>
                                                </a>
                                            </div>
                                            <div class="detail">
                                                <h3>${fns:trimLongText(item.shopName,8)}&nbsp;</h3>
                                                <p>
                                                <span>
                                                    主营类目：
                                                    <c:choose>
                                                        <c:when test="${item.trade==1}">男装 </c:when>
                                                        <c:when test="${item.trade==2}">女装 </c:when>
                                                        <c:when test="${item.trade==3}">童装 </c:when>
                                                        <c:when test="${item.trade==4}">内衣 </c:when>
                                                        <c:when test="${item.trade==5}">家居 </c:when>
                                                        <c:when test="${item.trade==6}">鞋类 </c:when>
                                                        <c:when test="${item.trade==7}">箱包 </c:when>
                                                        <c:when test="${item.trade==8}">其他 </c:when>
                                                        <c:otherwise>未知 </c:otherwise>
                                                    </c:choose></span>
                                                    <span>销售模式：专卖店</span>
                                                    <span>所在地区：${item.provinceName}-${item.cityName}-${item.areaName}</span></p>
                                                <p class="times">累计进货次数：2</p>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                            <%--
                            <div class="pagination ta-c mt10">
                                <a class="prev">&lt;</a>
                                <a class="page current">1</a><a class="page">2</a>
                                <a class="next">&gt;</a>
                            </div>
                             --%>
                    </div>
                </div>
                <input type="hidden" name="trade" id="trade"
                       value="<%=request.getParameter("trade")==null?0:request.getParameter("trade")%>"/>
                <input type="hidden" name="areaNo" id="areaNo"
                       value="<%=request.getParameter("areaNo")==null?0:request.getParameter("areaNo")%>"/>
                <input type="hidden" name="model" id="model"
                       value="<%=request.getParameter("model")==null?0:request.getParameter("model")%>"/>
                <input type="hidden" name="shape" id="shape"
                       value="<%=request.getParameter("shape")==null?0:request.getParameter("shape")%>"/>
            </form:form>
            <div class="g-rightside">
                <div class="m-collect">
                    <%--<a class="collect" href="javascript:;" onclick="AddFavorite(document.title,location.href);">添加到浏览器收藏夹</a>--%>
                    <%--<a class="fr" href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&amp;key=XzkzODAwMTU5Nl8xOTE0MjNfNDAwMTExODYzN18yXw"><img src="/images/fronts/market/qq-service.png" alt=""/></a>--%>
                </div>
                <div>
                    <%--<a class="register_shop" href="${ctx}/shop/collect"><i class="reg_collect"></i>免费登记店铺信息</a>--%>
                </div>
                <div class="m-rightcommon" style="margin-top: 30px;">
                    <div class="title"><h4>为什么要登记店铺信息？</h4></div>
                    <div class="panel-body">
                        <ul class="list_1">
                            <li><span>1.</span><a>快速采购更低的品牌进货价格</a></li>
                            <li><span>2.</span><a>及时掌握最全面的品牌信息</a></li>
                            <li><span>3.</span><a>少批量多批次，资金周转快</a></li>
                            <li><span>4.</span><a>退货无忧，货款先赔</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt10"></div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/src/common/base-init.js"></script>
<!--[if lt IE 9]>
<script src="/scripts/terminal/css3-mediaqueries.js"></script>
<![endif]-->
<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp" />
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script>
    //切换Tab
    function setShape(v) {
        $('#shape').val(v);
        $('#dealerShopEnvForm')[0].submit();
    }

    seajs.use(['dialog'], function(Dialog){
        var tabOrder = $(".tab-order a:gt(0)").click(function () {
            tabOrder.not($(this).addClass("active")).removeClass("active");
            var idx = tabOrder.index(this);
            var listDealer = $(".list-dealer ul");
            if (idx == 0) {
                /*grid*/
                listDealer.removeClass().addClass("order-grid");
            }
            else {
                /*list*/
                listDealer.removeClass().addClass("order-list");
            }
        });

        var select = $(".select").each(function (i, o) {
            var dd = $(o).find("a").click(function () {
                dd.not($(this).addClass("active")).removeClass("active");
                $(".search-box button").trigger('click');
            });
        });

        $(".search-box button").click(function () {
            var hy = $(select[0]).find(".active").data("id");
            var dq = $(select[1]).find(".active").data("id");
            var xsms = $(select[2]).find(".active").data("id");
            var showpic = $('#showPic').prop("checked");
            var key = $(this).prev().val();
            $('#trade').val(hy);
            $('#areaNo').val(dq);
            $('#model').val(xsms);
        });

        //收藏店铺
        $(".main-list").on("click", ".js-collect", function(){
            var $this = $(this);
            var _id = $this.data("id");
            if ($.trim($this.html()) != "已收藏") {
                $.ajax({
                    url: "${ctx}/brand/dealer/collectDealer",
                    type: "post",
                    data: {dealerId: _id},
                    dataType: "json",
                    success: function(data){
                        if(data.code == 126000){
                            $this.html("已收藏");
                        }else if(data.code == 126013){
                            jumpToLogin();
                        }else{
                            remind("error", data.message);
                        }
                    }
                });
            }
        });

        //邀请加盟
        var invitedio = new Dialog({//邀请加盟按钮
            //trigger: $('.js-invite-join'),
            effect: 'fade',
            hasMask: false,
            content: $(".js-invite-show"),
            width: 385
        }).after("show",function(){
                    $("#inviteText").val("");
                    var target = this.activeTrigger;
                    if($.isFunction(inviteJoin)){
                        inviteJoin(target,invitedio);
                    }
                }).before("show",function(){
                    $("#brandsId").val("");
                    $("#brandsId_div span").html("请选择品牌");
                    $(".joindealer").hide();
                    $("#joinDiv").show();
                });
        $(".js-invite-show").on("click", ".cancel_btn", function () {
            invitedio.hide();
        });

        function inviteJoin(obj, invitedio) {
            //$("#dealerId").val($.trim(obj.attr("data-id")));
            invitEditDiv = invitedio;
        }

        $(".main-list").on("click", ".js-invita-join", function(){
            var $this = $(this);
            var hasLogon = $this.data("haslogon");
            if(hasLogon){
                invitedio.show();
            }else{
                jumpToLogin();
            }
            var id = $this.data("id");
            $("#dealerId").val($.trim(id));
        });

        <!--查看电话号码-->
        $(".js-look").click(function () {
            $.ajax({
                type: "post",
                url: "/shop/viewCount",
                dataType: "json",
                success: function (data) {
                    $("#chance").text("[" + data.object + "]");
                    confirmDialog(
                        "您当前还剩余 <span style='color:#e00;'>["+ data.object +"]</span> 次机会<br />本次操作会扣除 <span style='color:#e00;'>[1]</span> 次查看机会，再次对该终端商操作将不再扣除。",
                        function () {
                            var dealerId = $(".js-look").data("id");
                            $.post("/brand/dealer/viewContact", {dealerId: dealerId, isShow: true}, function (data) {
                                if (data.code == zttx.SUCCESS) {
                                    $("#phone_num_" + dealerId).html(data.object.userMobile);
                                } else {
                                    remind("error", data.message);
                                }
                            }, 'json');
                        }
                    );
                }
            })
        });
    });
</script>
</body>
</html>