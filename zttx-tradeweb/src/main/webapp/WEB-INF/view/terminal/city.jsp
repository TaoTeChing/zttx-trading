<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>8637品牌超级代理 – 终端商名录</title>
    <link href="${res}/styles/common/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/index/index.css" rel="stylesheet"/>
    <style>.nav{border-bottom: 2px solid #ed0100;}</style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="/WEB-INF/view/include/search.jsp"/>
    <jsp:include page="/WEB-INF/view/include/nav.jsp">
        <jsp:param value="" name="m"/>
    </jsp:include>
    <div class="em100 mt10">
        <div class="city">
            <div class="city-top clearfix">
                <a href="${ctx }/shop/${currcity}" class="entrance inline-block fr">
                    <b>进入终端商版块</b>
                </a>
                 <div class="around" style="display: block;">
                     <span class="gray"><b>搜索城市推荐：</b></span>
                     <c:set value="${fns:getAreaObjects(recity)}" var="citys"></c:set>
                     <c:forEach items="${citys }" var="infos">
                         <a href="${ctx }/shop/${infos['zipCode']}">${fn:replace(infos['areaName'],'市','')}</a>
                     </c:forEach>
                 </div>
            </div>
            <div class="city-search">
                <form>
                    <ul class="inline-float">
                        <li>
                            <span id="searchspan"><b>按照省份选择</b></span>
                        </li>
                        <li>
                            <select name="province" id="p">
                                <option value="">请选择省份</option>
                                <option value="370000">山东</option>
                                <option value="320000">江苏</option>
                                <option value="330000">浙江</option>
                                <option value="340000">安徽</option>
                                <option value="440000">广东</option>
                                <option value="350000">福建</option>
                                <option value="450000">广西</option>
                                <option value="460000">海南</option>
                                <option value="410000">河南</option>
                                <option value="420000">湖北</option>
                                <option value="430000">湖南</option>
                                <option value="360000">江西</option>
                                <option value="210000">辽宁</option>
                                <option value="230000">黑龙江</option>
                                <option value="220000">吉林</option>
                                <option value="510000">四川</option>
                                <option value="530000">云南</option>
                                <option value="520000">贵州</option>
                                <option value="540000">西藏</option>
                                <option value="130000">河北</option>
                                <option value="140000">山西</option>
                                <option value="150000">内蒙古</option>
                                <option value="610000">陕西</option>
                                <option value="650000">新疆</option>
                                <option value="620000">甘肃</option>
                                <option value="640000">宁夏</option>
                                <option value="630000">青海</option>
                            </select>
                            <select name="city" id="c">
                                <option value="">请选择城市</option>
                            </select>
                        </li>
                        <li>
                            <button type="button" class="btn1" id="selectCity">确定</button>
                        </li>
                        <li>
                            <span id="popularspan"><b>或者</b></span>
                        </li>
                        <li>
                            <input type="text" class="ui-input fl" autocomplete="off" class="searchTxt"
                                   placeholder="城市名,拼音,电话区号" id="cityInfo">
                            <button type="button" class="btn1 fl" id="goToCity">进入城市</button>
                        </li>
                    </ul>
                </form>
            </div>
            <div class="city-list">
                <div class="area">
                    <h3 class="name"><strong>华东</strong></h3>
                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">山东</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(370000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">江苏</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(320000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">浙江</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(330000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">安徽</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(340000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>华南</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">广东</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(440000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">福建</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(350000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">广西</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(450000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">海南</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(460000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>中南</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">河南</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(410000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">湖北</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(420000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">湖南</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(430000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">江西</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(360000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>东北</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">辽宁</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(210000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">黑龙江</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(230000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">吉林</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(220000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>西南</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">四川</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(510000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">云南</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(530000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">贵州</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(520000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">西藏</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(540000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>华北</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">河北</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(130000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">山西</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(140000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">内蒙古</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(150000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <div class="area">
                    <h3 class="name"><strong>西北</strong></h3>

                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">陕西</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(610000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">新疆</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(650000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">甘肃</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(620000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">宁夏</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(640000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <dl class="item clearfix">
                    <dd class="province">青海</dd>
                    <dd>
                        <c:set value="${fns:getAreaObjects(630000)}" var="citys"></c:set>
                        <c:forEach items="${citys }" var="infos">
                            <a href="${ctx }/shop/${infos['zipCode']}">${infos['areaName']}</a>
                        </c:forEach>
                    </dd>
                </dl>
                <%-- 数据没有 --%>
                <%--
                <div class="area">
                    <h3 class="name"><strong>其他</strong></h3>
                    <p class="line"></p>
                </div>
                <dl class="item clearfix">
                    <dd class="province">其他</dd>
                    <dd>
                        <a href="http://hk.8637.com/">香港</a><a href="http://am.8637.com/">澳门</a><a href="http://tw.8637.com/">台湾</a><a href="http://diaoyudao.8637.com/">钓鱼岛</a><a href="http://cn.8637.com/">其他</a>
                    </dd>
                </dl>
                 --%>
            </div>
        </div>
    </div>
    <div class="mt10"></div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/src/common/base-init.js"></script>
<%--<script>
    seajs.use(["pcas"],function(PCAS){
        var pcas = new PCAS({
            province: "#p",
            city: "#c"
            //area: "#a",
        });
    })
</script>--%>
<script>
    //选择省份级联城市
    $("#p").change(function(){
        var val = $(this).val();
        if(val != null && val != "")
        {
            $.ajax({
                url: "${ctx}/shop/citysInProvince",
                type: "get",
                data: {areaNo: val},
                dataType: "json",
                success: function (data) {
                    $("#c").empty();
                    $("#c").append("<option value=''>请选择城市</option>");
                    for(var i=0;i<data.length;i++)
                    {
                        $("#c").append("<option value='" + data[i].zipCode + "'>"+data[i].areaName+"</option>");
                    }
                }
            });
        }
    });

    $("#selectCity").click(function(){
       var city = $("#c").val();
        if(city == null || city == "")
        {
            remind("error","请选择城市");
            return;
        }
        $(this).attr("disabled",true);
        window.location.href = "${ctx }/shop/" + city;
    });

    $("#goToCity").click(function () {
        var cityInfo = $("#cityInfo").val();
        if (cityInfo == null || cityInfo == "") {
            remind("error", "请填写城市名,拼音或电话区号");
            return;
        }
        remind("success", "正在查找....",1000);
        $.ajax({
            url: "${ctx}/shop/goToCity",
            type: "get",
            data: {cityInfo: cityInfo},
            dataType: "json",
            success: function (data) {
                if(null != data) window.location.href = "${ctx }/shop/" + data.zipCode;
            },
            error:function()
            {
                remind("error", "查找不到指定的城市");
            }
        });
    });
</script>
</body>
</html>
