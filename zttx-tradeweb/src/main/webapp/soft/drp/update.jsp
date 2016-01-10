<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能介绍-智慧门店管理系统-8637品牌超级代理产品中心</title>
    <link rel="stylesheet" href="${res}/styles/fronts/soft/erp/update.css"/>
</head>
<body>
<jsp:include page="_header.jsp" />
<div class="contanier">
    <div class="tab-content">
        <!--android日志更新-->
        <div class="content-box box1">
            <h3 class="title">更新日志</h3>
            <div class="content">
            <div class="block">
                    <div class="year">2015年</div>
                    <div class="year-line"></div>
                    <div class="log-content">
                        <div class="logs clearfix new">
                            <div class="date fl">
                                <div class="dd">7.17</div>
                                <div class="yy">2015</div>
                            </div>
                            <dl class="fl">
                                <dt>智慧品牌总部系统 软件1.1.0.0版本</dt>
                                <dd>系统性能优化，使用更顺畅</dd>
                                <dd>新安装包，安装流程更清晰更友好</dd>
                                <dd>产品管理功能使用平台数据源</dd>
                                <dd>实时查询工厂店销售数据和库存数据</dd>
                            </dl>
                        </div>
                        <div class="logs clearfix">
                            <div class="date fl">
                                <div class="dd">4.23</div>
                                <div class="yy">2015</div>
                            </div>
                            <dl class="fl">
                                <dt>智慧品牌总部系统 软件1.0.1.1版本</dt>
                                <dd>智慧品牌总部系统</dd>
                                <dd>支持RFID芯片读取</dd>
                                <dd>支持查看退货单信息</dd>
                                <dd>查看店铺销售、库存、采购等信息</dd>
                                <dd>增加财务账，应收应付明细信息</dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--iphone日志更新-->
        
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp" />
<a class="ui-scrolltop" title="返回顶部">返回顶部</a>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/fronts/soft/erp/index.js"></script>
<script>
    $(function(){
        //更新日志年切换
        $(".year").click(function(){
            var logContent = $(this).parent().find('.log-content');
            var bl = logContent.is(":hidden");
            if(bl){
                $(this).parents('.content-box').find('.log-content').hide();
                logContent.show();
                if($(this).parent().hasClass('last')){
                    $(this).next().show();
                }else{
                    $(this).parents('.content-box').find('.last .year-line').hide();
                }
            }
        });
    });
</script>
</body>
</html>
