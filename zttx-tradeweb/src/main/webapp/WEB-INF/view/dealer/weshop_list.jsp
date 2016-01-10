<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-开通微店</title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
         <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
           <jsp:include page="${ctx}/dealer/dealermenu"  />
                <div class="main-right">
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span class="bb">服务管理</span>
                            <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="main-grid mb40 clearfix">
                        <div class="weshop-list">
                            <h2>分店授权</h2>
                            <div class="hr-dashed">
                            </div>
                            <div class="clearfix">
                                <div class="mt20">
                                    <div class="ui-form-item">
                                        <label class="ui-label">终端商名:</label>
                                        <span class="ui-form-text">${weshopInfo.object.userName }</span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">终端商登录账号:</label>
                                        <span class="ui-form-text">${weshopInfo.object.userAccount }</span>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label"> 约逛账号:</label>
                                        <span class="ui-form-text">${weshopInfo.object.userCode} 登录号(${weshopInfo.object.userAccount})</span>
                                    </div>
                                </div>
                                <div class="ui-table-container">
                                    <div class="mb10 ta-r">
                                    <%--
                                        <button class="ui-button ui-button-mred" id="newShop" type="button">新增分店</button>
                                     --%>
                                    </div>
                                    <table class="ui-table">
                                        <thead>
                                            <tr>
                                                <th>分店名称</th>
                                                <th>约逛号</th>
                                                <th>历史访问人数</th>
                                                <th>负责人</th>
                                                <th>在售商品</th>
                                                <th>累积售出</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${dealerShopInfos }" var="item">
                                        		<tr>
                                                <td>${item.shopName}</td>
                                                <td>
                                                    ${item.userCode}<br>
                                                </td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>
                                                    <a class="link" href="${ctx}/dealer/weshop/config?shopId=${item.shopId }">设置微店</a>
                                                </td>
                                            </tr>
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="hr mt10 mb10">
                            </div>
                            <div class="m020">
                                <a class="ui-button ui-button-lred mr10" href="" target="_blank">前往下载约逛</a>
                                <a class="link" href="" target="_blank">了解约逛</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <div class="hide">
        <div id="dialogNewShop">
            <div class="ui-head">
                <h3>新增分店</h3>
            </div>
            <div class="mt20">
                <form class="ui-form">
                    <div class="ui-form-item">
                        <label class="ui-label">
                            分店名称:
                        </label>
                        <input class="ui-input" name="a">
                        <input type="hidden" name="id">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            绑定约逛号:
                        </label>
                        <input class="ui-input" name="b">
                    </div>
                    <div class="ui-form-item">
                        <label class="ui-label">
                            产品授权:
                        </label>
                        <span class="ui-form-text">暂未授权任何商品 <a class="link" id="author" href="javascript:;">点击授权</a></span>
                    </div>
                    <div class="ui-form-item">
                        <button type="submit" class="ui-button ui-button-mred js-submit">设为分店</button>
                        <button type="button" class="ui-button ui-button-morange js-cancel">取消</button>
                    </div>
                </form>
            </div>
        </div>
        <div id="dialogAuthor">
            <div class="ui-head">
                <h3>产品部分授权选择</h3>
            </div>
            <div class="mt20 ui-table-container">
                <table class="ui-table">
                    <thead>
                        <tr>
                            <th>
                                <input class="ui-checkbox check-a" type="checkbox">
                            </th>
                            <th>商品名称</th>
                            <th>货单号</th>
                            <th>产品图片</th>
                            <th>品牌商</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input class="ui-checkbox check-b" type="checkbox">
                            </td>
                            <td>
                                朵唯钢圈女性内衣
                            </td>
                            <td>D548422</td>
                            <td>
                                <div class="js-img-center">
                                    <img src="">
                                </div>
                            </td>
                            <td>杭州朵朵</td>
                        </tr>
                        <tr>
                            <td>
                                <input class="ui-checkbox check-b" type="checkbox">
                            </td>
                            <td>
                                朵唯钢圈女性内衣
                            </td>
                            <td>D548422</td>
                            <td>
                                <div class="js-img-center">
                                    <img src="">
                                </div>
                            </td>
                            <td>杭州朵朵</td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination mt20 mb20">

                </div>
                <div class="ta-r">
                    <button type="submit" class="ui-button ui-button-mred js-submit">确定授权</button>
                    <button type="submit" class="ui-button ui-button-morange js-submit-All">全部授权本店商品</button>
                    <button type="button" class="ui-button ui-button-morange js-cancel">取消授权</button>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
        seajs.use(["dialog"],function(Dialog){
            //新增
            new Dialog({
                trigger:"#newShop",
                content:"#dialogNewShop"
            }).after("show",function(){
                var me = this;
                $(this.element[0]).find(".js-cancel").click(function(){
                    me.hide();
                });
            });

            //编辑
            new Dialog({
                trigger:".js-editAuthor",
                content:"#dialogNewShop"
            }).after("show",function(){
                var me = this;
                var trigger = me.activeTrigger[0];
                var container = me.element[0];
                //取值&赋值
                if($(trigger).data("item"))
                {
                    var item = $(trigger).data("item");
                    $(container).find("[name=]").val(item.a);
                    $(container).find("[name=]").val(item.b);
                }
                else{
                    var _p = {
                       id:$(trigger).data("id")
                    }
                    $.get("",_p,function(item){
                        $(trigger).data({"item":item});
                        $(container).find("[name=a]").val(item.a);
                        $(container).find("[name=b]").val(item.b);
                    });
                }

                $(container).find(".ui-head h3").text("修改产品授权");

                $(container).find(".js-cancel").click(function(){
                    me.hide();
                });
            });

            //授权
            new Dialog({
                trigger:"#author",
                content:"#dialogAuthor",
                width:700
            }).after("show",function(){

            }).after("render",function(){
                var me = this;
                $(this.element[0]).find(".js-cancel").click(function(){
                    me.hide();
                });
            });

            checkAll(".check-a",".check-b");

            $("#dialogNewShop .js-submit").click(function(){
                //添加

                //编辑
            });
        });
    </script>
</body>
</html>
