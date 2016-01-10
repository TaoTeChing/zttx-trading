<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>管理中心-首页</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/index.css"/>
    <style>
        .simple_tab {
            position: relative;
        }

        .simple_tab .more {
            position: absolute;
            right: 10px;
            top: 12px;
        }

        .simple_tab .link {
            color: #0082CC;
        }

        .simple_tab .link:hover {
            color: #FF8800;
        }

        .main .main_con .inner {
            background: none;
        }
    </style>
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <div class="inner">
            <!-- 内容都放这个地方
            <div class="security_tip">
                <i class="icon"></i>账号安全评分：您的账号安全评分为 50 分，请点此提升分数。 <a
                    class="close iconfont" href="javascript:;">&#xe602;</a>
            </div>  -->

            <div class="layer1 clearfix">
                <div class="workbench fl">
                    <div class="title clearfix">
                        <span class="t1">交易前<i></i></span>
                        <span class="t2">交易中<i></i></span>
                        <span class="t3">交易后<i></i></span>
                    </div>
                    <dl class="list clearfix">
                        <dd class="t1">
                            <div class="trade">
                                <span class="fname">供应</span>

                                <div class="items">
                                    <span class="item">邀请中的终端商
                                        <a href="${ctx}/brand/dealer/invite"
                                           class="num">（${empty brandCount ? 0 : brandCount.inviteCount}）</a>
                                    </span>
                                    <span class="item">申请中的终端商
                                        <a href="${ctx}/brand/dealer/listDealerApply"
                                           class="num">（${empty brandCount ? 0 : brandCount.applyCount}）</a>
                                    </span>
                                </div>
                            </div>
                        </dd>
                        <dd class="t2">
                            <div class="trade">

                                <span class="item">合作中的终端商
                                    <a href="${ctx}/brand/join/list"
                                       class="num">（${empty brandCount ? 0 : (brandCount.cooperCount)}）</a>
                                </span>
                            </div>
                        </dd>
                        <dd class="t3">
                            <div class="trade">
                                <span class="item">合作终止
                                    <a href="${ctx}/brand/join/list?state=2"
                                       class="num">（${empty stopJoinCount ? 0 : stopJoinCount}）</a>
                                </span>
                            </div>
                        </dd>
                    </dl>
                    <dl class="list clearfix">
                        <dd class="t1">
                            <div class="trade">
                                <span class="fname">订单</span>

                                <div class="items">
                                    <span class="item">待付款
                                        <a href="${ctx}/brand/order/purorder/1"
                                           class="num">（${empty brandCount ? 0 : brandCount.waitPayCount}）</a>
                                    </span>
                                </div>
                            </div>
                        </dd>
                        <dd class="t2">
                            <div class="trade">
                                <span class="item">待发货
                                    <a href="${ctx}/brand/order/purorder/2"
                                       class="num">（${empty brandCount ? 0 : brandCount.waitSendCount}）</a>
                                </span>
                                <span class="item">已发货
                                    <a href="${ctx}/brand/order/purorder/3"
                                       class="num">（${empty brandCount ? 0 : brandCount.waitConfirmCount}）</a>
                                </span>
                                <span class="item">退款中
                                    <a href="${ctx}/brand/order/purorder/7"
                                       class="num">（${(empty brandCount || (brandCount.refundCount)<0)
                                            ? 0 : (brandCount.refundCount)}）</a>
                                </span>
                            </div>
                        </dd>
                        <dd class="t3">
                            <div class="trade">
                                <span class="item">投诉管理<a href="javascript:void(0);" class="num">（0）</a></span>
                            </div>
                        </dd>
                    </dl>
                    <dl class="list clearfix" style="border-bottom: 0;">
                        <dd class="t1">
                            <div class="trade">
                                <span class="fname">产品</span>

                                <div class="items">
                                    <span class="item">下架的
                                        <a href="${ctx}/brand/product/list?tabSearch=1"
                                           class="num">（${empty brandCount ? 0 : brandCount.waitPublishCount}）</a>
                                    </span>
                                    <span class="item">库存缺货
                                        <a href="${ctx}/brand/product/list?tabSearch=6"
                                           class="num">（${empty brandCount ? 0 : brandCount.shortageCount}）</a>
                                    </span>
                                </div>
                            </div>
                        </dd>
                        <dd class="t2">
                            <div class="trade">
                                <span class="item">上架的
                                    <a href="${ctx}/brand/product/list?tabSearch=0"
                                       class="num">（${empty brandCount ? 0 : brandCount.publishedCount}）</a>
                                </span>
                                <%--<span class="item">预订的
                                    <a href="${ctx}/brand/product/list?tabSearch=3"
                                       class="num">（${empty brandCount ? 0 : brandCount.prePublishedCount}）</a>
                                </span>
                                --%><span class="item">库存紧张
                                    <a href="${ctx}/brand/product/list?tabSearch=5"
                                       class="num">（${empty brandCount ? 0 : brandCount.tightInventoryCount}）</a>
                                </span>
                            </div>
                        </dd>
                        <dd class="t3">
                            <div class="trade">
                                <%--<span class="item">需要优化的产品
                                    <a href="javascript:void(0);" class="num">（0）</a>
                                </span>
                            --%></div>
                        </dd>
                    </dl>
                </div>

                <div class="info_area fr">
                    <div class="infoCenter">
                        <div class="common_hd">
                            <h3>官方信息中心</h3>
                        </div>
                        <div class="bd list">
                            <ul>
                                <c:set value="${fns:findSimpleByInfo( terrace_Inform, 3)}" var="info"></c:set>
                                <c:forEach var="obj" items="${info.getList()}" varStatus="status">
                                    <li>
                                        <a target="_blank" class="fl fn-text-overflow"
                                           href="${ctx}/info/detail/${obj.refrenceId}">${obj.articleTitle}</a>
                                        <span class="fr time">${fns:getTimeFromLong(obj.createTime,"yyyy.MM.dd")}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="customerCenter mt10">
                        <div class="common_hd">
                            <h3>客服中心</h3>
                        </div>
                        <c:choose>
                            <c:when test="${brandService != null }">
                                <div class="bd">
                                    <div class="photo fl">
                                        头像放这里
                                        <img src="${res}${brandService.serviceImage}" alt="" width="70" height="70"/>
                                    </div>
                                    <div class="detail">
                                        <p>客服工号：${brandService.jobNum}</p>

                                        <p>客服名字：${brandService.serviceName}</p>

                                        <p>服务电话：0574-87217777-${brandService.serviceTel}</p>
                                    </div>
                                    <div class="tel">
                                        400-111-8637
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="no_service">
                                    尊敬的 ${brandUserm.userName} 用户，您还没有客服，请联系客服热线 400-111-8637
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="layer3">
                <div class="common_hd">
                    <h3>渠道搭建流程</h3>
                </div>
                <ul class="inline-float">
                    <li>
                        <a href="${ctx}/brandJoin">
                            <i class="process_icon i1"></i>

                            <p>加盟合作</p>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/brand/brandesInfo/brandes"><i class="process_icon i2"></i>

                            <p>填写品牌资料</p></a>
                    </li>
                    <li>
                        <a href="${ctx}/brand/product/execute"><i class="process_icon i3"></i>

                            <p>发布产品</p></a>
                    </li>
                    <li>
                        <a href="${ctx}/brand/brandRecruit/execute"><i class="process_icon i5"></i>

                            <p>发布招募书</p></a>
                    </li>
                    <%-- <li>
                        <a href="${ctx}/brand/dealer/listDealerGroom"><i class="process_icon i6"></i>

                            <p>推荐终端商</p></a>
                    </li> --%>
                    <li>
                        <a href="${ctx}/brand/dealer/listDealerApply"><i class="process_icon i7"></i>

                            <p>终端商申请</p></a>
                    </li>
                    <li>
                        <a href="${ctx}/brand/brandDocument/list"><i class="process_icon i8"></i>

                            <p>进货&培训</p></a>
                    </li>
                </ul>
            </div>
            <div class="layer4" style="display:none;">
                <div class="common_hd clearfix">
                    <h3>数据图</h3>

                    <p id="myLine"></p>
                </div>
                <div class="bd">
                    <div id="myLineChart" class="line-chart"></div>
                    <div id="myDonutChart">

                    </div>
                </div>
            </div>
            <div class="layer5">
                <div class="bd js_tab">
                    <div class="simple_tab">
                        <ul class="clearfix">
                            <li class="current" _tempId="ajax-templage-myVisited">我浏览过的</li>
                            <li _tempId="ajax-templage-visitedMe">浏览过我的</li>
                        </ul>
                        <div class="more">
                            <a id="view_more_url" href="${ctx}/brand/dealer/brandVisited" class="link">查看更多</a>
                        </div>
                    </div>
                    <div class="tab_con">
                        <table id="myVisited" class="list_table">

                        </table>
                        <div id="pagination1" class="mt10"></div>
                    </div>
                    <div class="tab_con" style="display: none;">
                        <table id="visitedMe" class="list_table">
                        </table>
                        <div id="pagination2" class="mt10"></div>
                    </div>
                    <div id="music"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<jsp:include page="/WEB-INF/view/brand/dealer_invite_showbox.jsp"/>
<script src="${src}/brand/agencymanag.js"></script>
<script src="${src}/brand/index.js"></script>

<script id="ajax-templage-myVisited" _url="${ctx}/brand/dealer/brandVisited/data?pageSize=6" _page="#pagination1" _pid="#myVisited" type="text/html">
<colgroup>
    <col width="160"/>
    <col width="90"/>
    <col width="90"/>
    <col width="120"/>
    <col width="160"/>
    <col width="120"/>
    <col width="140"/>
    <col width="130"/>
</colgroup>
<thead>
	<tr>
	<th>终端商名</th>
	<th>实体店数量</th>
	<th>员工数量</th>
	<th>月销售额（万元）</th>
	<th>所在地区</th>
	<th>经营品牌</th>
	<th>成立时间</th>
	<th class="last">操 作</th>
	</tr>
</thead>
<tbody>
{{each rows}}
<tr>
	<td align="left"><a class="link" href="/brand/dealer/info/{{$value.id}}" target="_blank" title="{{$value.dealerName}}">{{$value.dealerName}}</a></td>
	<td>{{$value.shopNum}}</td>
	<td>{{$value.empNum}}</td>
	<td>{{if $value.monNum==null}}--{{else}}{{$value.monNum}}{{/if}}</td>
	<td align="left" title="{{$value.areaName}}">{{$value.areaName}}</td>
	<td align="left" title="{{$value.brandName}}">{{$value.brandName}}</td>
	<td>{{if $value.foundTime==null || $value.foundTime<=0}}--{{else}}{{$formatDate $value.foundTime}}{{/if}}</td>
	<td class="last">
		<a class="link js-invite-join" href="javascript:;" data-dealerid="{{$value.id}}">诚邀加盟</a>
	</td>
</tr>
{{/each}}
{{if rows==null}}
<tr>
    <td class="ta-c" colspan="8">暂无数据</td>
</tr>
{{/if}}
</tbody>
</script>
<script id="ajax-templage-visitedMe" _url="${ctx}/brand/dealer/dealerVisited/data?pageSize=6" _page="#pagination2" _pid="#visitedMe" type="text/html">
<colgroup>
    <col width="140"/>
    <col width="80"/>
    <col width="80"/>
    <col width="110"/>
    <col width="100"/>
    <col width="160"/>
    <col width="120"/>
    <col width="90"/>
    <col width="130"/>
</colgroup>
<thead>
	<tr>
	<th>终端商名</th>
	<th>实体店数量</th>
	<th>员工数量</th>
	<th>月销售额（万元）</th>
	<th>浏览品牌</th>
	<th>所在地区</th>
	<th>经营品牌</th>
	<th>成立时间</th>
	<th class="last">操 作</th>
	</tr>
</thead>
<tbody>
{{each rows}}
<tr>
	<td align="left"><a class="link" href="/brand/dealer/info/{{$value.id}}" target="_blank" title="{{$value.dealerName}}">{{$value.dealerName}}</a></td>
	<td>{{$value.shopNum}}</td>
	<td>{{$value.empNum}}</td>
	<td>{{if $value.monNum==null}}--{{else}}{{$value.monNum}}{{/if}}</td>
	<td>{{$value.brandsName}}</td>
	<td align="left" title="{{$value.areaName}}">{{$value.areaName}}</td>
	<td align="left" title="{{$value.brandName}}">{{$value.brandName}}</td>
	<td>{{if $value.foundTime==null || $value.foundTime<=0}}--{{else}}{{$formatDate $value.foundTime}}{{/if}}</td>
	<td class="last">
		<a class="link js-invite-join" href="javascript:;" data-dealerid="{{$value.id}}">诚邀加盟</a>
	</td>
</tr>
{{/each}}
{{if rows==null}}
<tr>
    <td class="ta-c" colspan="9">暂无数据</td>
</tr>
{{/if}}
</tbody>
</script>
<script>
    function inviteJoin(obj, invitedio) {
        $("#dealerId").val($.trim(obj.attr("data-dealerid")));
        invitEditDiv = invitedio;
    }
</script>
<script type="text/javascript">
    $(function () {
        $('#js-brand-photo').bind('change', function () {
            uploadImage($(this).attr('id'));
        });

        function uploadImage(uploadId) {
            seajs.use(["$", "ajaxFileUpload"], function ($) {
                $.ajaxFileUpload({
                    url: '${ctx}/common/showImg?fSize=1',
                    secureuri: false,
                    fileElementId: uploadId,
                    dataType: 'json',
                    success: function (data) {
                        //发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                        $('#' + uploadId).bind('change', function () {
                            uploadImage(uploadId);
                        });
                        if (data.code == zttx.SUCCESS) {
                            updatePhoto(data.message);
                        }
                        else {
                            remind("error", data.message);
                        }
                    }
                });
            });
        }

        function updatePhoto(userPhoto) {
            $.post("${ctx}/brand/contact/updatePhoto", {userPhoto: userPhoto}, function (data) {
                if (data.code == zttx.SUCCESS) {
                    $('#js-photo-img').attr("src", "${res}" + getImageDomainPath(data.object, 120, 120));
                    remind("success", "保存成功");
                }
                else {
                    remind("error", data.message);
                }
            }, "json");
        }

        var pageTemp = {};
        seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {

            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD");
            });

            function ajaxTemplate(tempId) {
                var $this = $("#" + tempId);
                pageTemp = new Pagination({
                    url: $this.attr("_url"),
                    elem: $this.attr("_page"),
                    method: "post",
                    handleData: function (data) {
                        var html = template.render(tempId, data);
                        $($this.attr("_pid")).html(html);
                        agencyApply.init();
                    }
                });
            }

            $(".js_tab").on("click", ".simple_tab li", function () {
                var index = $(this).index();
                $(".simple_tab li").removeClass("current").eq(index).addClass("current");
                $(".js_tab .tab_con").hide().eq(index).show();
                $("#view_more_url").attr("href", index != 1 ? "${ctx}/brand/dealer/brandVisited" : "${ctx}/brand/dealer/dealerVisited");
                if (index == 2) {
                    $("#view_more_url").attr("href", "${ctx}/brand/dealer/listDealerGroom");
                }
                var tempId = $(this).attr("_tempId");
                ajaxTemplate(tempId);
            });

            $(".js_tab .simple_tab li")[0].click();
        });

        //线图与饼图
        /*
         seajs.use("morris",function(){
         var day_data = [
         { "period": "2014-04-06", "店铺PV": 70, "店铺UV": 40 },
         { "period": "2014-04-07", "店铺PV": 100, "店铺UV": 60 },
         { "period": "2014-04-08", "店铺PV": 120, "店铺UV": 80 },
         { "period": "2014-04-09", "店铺PV": 10, "店铺UV": 3 },
         { "period": "2014-04-15", "店铺PV": 200, "店铺UV": 160  },
         { "period": "2014-04-20", "店铺PV": 120, "店铺UV": 80  },
         { "period": "2014-04-25", "店铺PV": 200, "店铺UV": 160 },
         { "period": "2014-05-01", "店铺PV": 100, "店铺UV": 60 },
         { "period": "2014-05-05", "店铺PV": 0, "店铺UV": 0}
         ];
         var line_obj =  Morris.Line({
         element: 'myLineChart',
         data: day_data,
         lineColors: ["#1D7AD9","#FF9A00","#4da74d"],
         xkey: 'period',
         ykeys: ['店铺PV', '店铺UV'],
         labels: ['店铺PV', '店铺UV']

         });

         for (var i in line_obj.options.labels) {
         $("#myLine").append('<span style="background-color:'+line_obj.options.lineColors[i]+'"></span> '+line_obj.options.labels[i]);
         }

         });
         */

    });

</script>
<script>

    $(document).on("click", ".music_link", function (ev) {
        ev.preventDefault();
        var current_music = $(this);
        stopPlayer("music");
        $("[name='music']").not(current_music).each(function () {
            $(this).removeClass("music_pause").addClass("music_play");
            $(this).html("播放录音");
        });
        if (current_music.hasClass("music_pause")) {
            stopPlayer("music");
            current_music.removeClass("music_pause").addClass("music_play");
            $(this).html("播放录音");
        } else if (current_music.hasClass("music_play")) {
            showPlayer("music", current_music.data("href"));
            current_music.removeClass("music_play").addClass("music_pause");
            $(this).html("暂停录音");
        }
    });

    function _start(songUrl) {


    }
    function showPlayer(id, url) {
        var vhtml = '<object id="wmp"';
        var userAg = navigator.userAgent;
        if (-1 != userAg.indexOf("MSIE")) {
            vhtml += ' classid="clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6"';
        }
        else if (-1 != userAg.indexOf("Firefox") || -1 != userAg.indexOf("Chrome") || -1 != userAg.indexOf("Opera") || -1 != userAg.indexOf("Safari")) {
            vhtml += ' type="application/x-ms-wmp"';
        }
        vhtml += ' width="0" height="0">';
        vhtml += '<param name="URL" value="' + url + '"/>';
        vhtml += '<param name="autoStart" value="true" />';
        vhtml += '<param name="invokeURLs" value="false">';
        vhtml += '<param name="playCount" value="100">';
        vhtml += '<param name="Volume" value="100">';
        vhtml += '<param name="defaultFrame" value="datawindow">';
        vhtml += '</object>';
        $("#" + id).html(vhtml);
    }

    function stopPlayer(id) {
        $("#" + id).html("");
    }

</script>
</body>
</html>