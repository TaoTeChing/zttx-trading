<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我要找店铺</title>
    <meta name="keywords" content="我要找店铺" />
    <meta name="description" content="我要找店铺" />
    <!--样式-->
     <link href="${res}/styles/common/base.css" rel="stylesheet" />
    <link href="${res}/styles/common/account.css" rel="stylesheet" />
    <style>
        .ui-form-item{
            padding-left: 95px;
        }
        .ui-form-explain{
            display: inline-block;
            *display: inline;
            *zoom: 1;
            padding: 0;
        }
        .radio-group{
            float: left;
            margin-top: 6px;
        }
        .radio-group input[type=radio]{
            margin-left: 5px;
        }
        .city-list label{
            float: left;
            width: 100px;
            height: 30px;
        }
        .city-list label input[type=checkbox]{
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container register">
        <div class="header mb20">
            <div class="px1000">
                <div class="logo">
                    <a href="${ctx}/">
                        <h1>8637品牌超级代理</h1>
                    </a>
                </div>
                <div class="column">
                    <h2>我要找店铺</h2>
                    <span>I WANT TO FIND A SHOP</span>
                </div>
                <div class="helper">
                    <div class="link"><a href="${ctx}/">网站首页</a> <a href="${ctx}/help/index">帮助中心</a></div>
                    <div class="phone">客服热线: 0574-87217777</div>
                </div>
            </div>
        </div>
        <div class="main px1000 mb20">
            <div class="border block p15 bgc-w">
                <div class="fl main-left">
                    <div class="content ">      
                        <div class="tabbody ${dealerHide}">
                            <div class="form">
                                <form:form id="dealerForm" class="ui-form" method="post" data-widget="validator">
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            店铺所在地区：
                                        </label>
                                        <div class="radio-group city-list clearfix">
                                            <label><input type="checkbox" class=""/>直辖市</label>
                                            <label><input type="checkbox" class=""/>河北</label>
                                            <label><input type="checkbox" class=""/>山西</label>
                                            <label><input type="checkbox" class=""/>内蒙古</label>
                                            <label><input type="checkbox" class=""/>辽宁</label>
                                            <label><input type="checkbox" class=""/>吉林</label>
                                            <label><input type="checkbox" class=""/>黑龙江</label>
                                            <label><input type="checkbox" class=""/>江苏</label>
                                            <label><input type="checkbox" class=""/>浙江</label>
                                            <label><input type="checkbox" class=""/>安徽</label>
                                            <label><input type="checkbox" class=""/>福建</label>
                                            <label><input type="checkbox" class=""/>江西</label>
                                            <label><input type="checkbox" class=""/>山东</label>
                                            <label><input type="checkbox" class=""/>河南</label>
                                            <label><input type="checkbox" class=""/>湖北</label>
                                            <label><input type="checkbox" class=""/>湖南</label>
                                            <label><input type="checkbox" class=""/>广东</label>
                                            <label><input type="checkbox" class=""/>广西</label>
                                            <label><input type="checkbox" class=""/>海南</label>
                                            <label><input type="checkbox" class=""/>四川</label>
                                            <label><input type="checkbox" class=""/>贵州</label>
                                            <label><input type="checkbox" class=""/>云南</label>
                                            <label><input type="checkbox" class=""/>西藏</label>
                                            <label><input type="checkbox" class=""/>陕西</label>
                                            <label><input type="checkbox" class=""/>甘肃</label>
                                            <label><input type="checkbox" class=""/>青海</label>
                                            <label><input type="checkbox" class=""/>宁夏</label>
                                            <label><input type="checkbox" class=""/>新疆</label>
                                            <label><input type="checkbox" class=""/>台湾</label>
                                            <label><input type="checkbox" class=""/>香港</label>
                                            <label><input type="checkbox" class=""/>澳门</label>
                                            <label><input type="checkbox" class=""/>海外</label>
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            周边环境：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="environment" value="1"  checked="checked"> 很繁荣
                                            <input type="radio" name="environment" value="2"> 较繁荣
                                            <input type="radio" name="environment" value="3"> 一般
                                            <input type="radio" name="environment" value="4"> 较偏
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            店铺形式：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="shape" value="1"  checked="checked"> 沿街店
                                            <input type="radio" name="shape" value="2"> 商超百货
                                            <input type="radio" name="shape" value="3"> 集散市场
                                            <input type="radio" name="shape" value="4"> 其他
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            经营行业：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="trade" value="1"  checked="checked"> 男装
                                            <input type="radio" name="trade" value="2"> 女装
                                            <input type="radio" name="trade" value="3"> 童装
                                            <input type="radio" name="trade" value="4"> 内衣
                                            <input type="radio" name="trade" value="5"> 家居
                                            <input type="radio" name="trade" value="6"> 鞋类
                                            <input type="radio" name="trade" value="7"> 箱包
                                            <input type="radio" name="trade" value="8"> 其他
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            销售模式：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="model" value="1" checked="checked"> 专卖店
                                            <input type="radio" name="model" value="2"> 混合店
                                            <input type="radio" name="model" value="3"> 专卖+混合
                                            <input type="radio" name="model" value="4"> 其他
                                        </div>
                                    </div>

                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            年销售额：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="" value="1" checked="checked"> 5W以下
                                            <input type="radio" name="" value="2"> 5~10W
                                            <input type="radio" name="" value="3"> 10~30W
                                            <input type="radio" name="" value="4"> 30W以上
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            开店时间：
                                        </label>
                                        <div class="radio-group">
                                            <input type="radio" name="" value="1" checked="checked"> 不到一年
                                            <input type="radio" name="" value="2"> 1~3年
                                            <input type="radio" name="" value="3"> 3~5年
                                            <input type="radio" name="" value="4"> 5年以上
                                        </div>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            您的手机号： <span class="ui-form-required">*</span>
                                        </label>
                                        <input autocomplete="off" type="text" class="ui-input" name="mobile" placeholder="例如:130****111122" value="${dealerUserm.userMobile}" required data-display="手机号码"/>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            您的称呼：<span class="ui-form-required">*</span>
                                        </label>
                                        <input class="ui-input" name="username" required  data-display="您的姓名"/>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                            您的单位：<span class="ui-form-required">*</span>
                                        </label>
                                        <input class="ui-input" name="username" required  data-display="您的姓名"/>
                                    </div>
                                    <div class="ui-form-item">
                                        <label class="ui-label">
                                        </label>
                                        <div>
                                            <input id="checkin" type="submit" class="ui-button ui-button-lred" value="提交" />
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 栏目：没有8637品牌超级代理账户? -->
                <div class="main-right">
                    <div class="head">
                    </div>
                    <div class="content" style="line-height: 30px;font-size: 14px;">
                        <p>免费查找店铺</p>
                        <p>通过审核之后</p>
                        <p>将会有专业的人员</p>
                        <p>为您提供最符合您要求的店铺</p>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/common/component/footer.jsp" />
    </div>

	<script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <script type="text/javascript">
        //校验
        baseFormValidator({
            selector:"#dealerForm",
            isAjax:true,
            addElemFn:function(Core,Validator){
                Core.addItem({
                    element: 'input[name=mobile]',
                    rule: "mobile"
                });
            },
            beforeSubmitFn:function(){
                alert("前台验证完毕,请删除此处");
                //前台验证完成以后，在此处提交
                /*$.post("${ctx}/shop/collect/save",$('#dealerForm').serialize()+"&areaNo="+$('#test2county').val(),function(data){
                    if(data.code==126000){
                    	remind('success','登记成功',function(){
                            window.location.href="${ctx}/shop/default";
                        });
                    }else{
                    	remind('error',data.message);
                    }
                },"json");*/
            }
        });
    </script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp" />
</body>
</html>