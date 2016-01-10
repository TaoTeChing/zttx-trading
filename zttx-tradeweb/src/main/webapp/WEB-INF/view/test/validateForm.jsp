<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>会员注册</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
    <!--样式-->
    <link href="${res}/styles/common/reset.css" rel="stylesheet" />
    <link href="${res}/styles/common/global.css" rel="stylesheet" />
    <link href="${res}/styles/common/index.css" rel="stylesheet" />
</head>
<body>
    <div class="container register">
        <div class="header mb20">
            <div class="px1000">
                <div class="logo">
                    <a>
                        <h1>8637品牌超级代理</h1>
                    </a>
                </div>
                <div class="column">
                    <h2>会员中心</h2>
                    <span>MEMBER CENTER</span>
                </div>
                <div class="helper">
                    <div class="link"><a href="#">网站首页</a> <a href="#">帮助中心</a></div>
                    <div class="phone">客服热线: 0574-87217777</div>
                </div>
            </div>
        </div>
        <div class="main px1000 mb20">
            <div class="border block p15 bgc-w">
                <div class="fl main-left">
                    <div class="head">
                        <h2 class="c-r-on">会员注册</h2>
                    </div>
                    <div class="hr mb40"></div>
                    <div class="tabs clearfix">
                        <ul>
                            <li><a href="javascript:void(0)" class="on">我是品牌商<em class="b-san"></em></a>
                            </li>
                            <li><a href="javascript:void(0)">我是终端商<em class="b-san"></em></a>
                            </li>
                        </ul>
                    </div>
                    <div class="hr"></div>
                    <div class="content ">
                        <div class="tabbody">
                            <div class="form ">
                                <form action="注册成功.html">
                                    <dl>
                                        <dt>
                                            <label>手机号码:</label></dt>
                                        <dd>
                                            <input type="text" class="txt" deftxt='例如:130****1111' datatype="m" value="例如:130****1111" />
                                            <a href="#" class="btn">免费获取验证码</a>
                                        </dd>
                                        <dd><i>若当前号码已不用/丢失，或无法收到验证码？提交申请单由客服帮助处理。</i></dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>验证码:</label></dt>
                                        <dd>
                                            <input type="text" class="txt short" datatype="*" value="" />
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>电子邮箱:</label></dt>
                                        <dd>
                                            <input type="text" deftxt="例如:youname@163.com" value="例如:youname@163.com" class="txt long" datatype="e" nullmsg="请输入邮箱！" />
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>登录密码:</label>
                                        </dt>
                                        <dd>
                                            <input name="userpassword" class="txt" type="password" value="" plugin="passwordStrength" datatype="*6-18" />
                                            <span class="help">6-16位数字、字母和符号，区分大小写</span>
                                            <p id="passwordStrength" class="passwordStrength"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></p>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>重复密码:</label></dt>
                                        <dd>
                                            <input class="txt" type="password" value="" datatype="*6-15" recheck="userpassword" errormsg="您两次输入的账号密码不一致！" />
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>您的姓名:</label></dt>
                                        <dd>
                                            <input class="txt" type="text" datatype="zh2-8" value="" />
                                        </dd>
                                        <dt>
                                            <label>地址:</label></dt>
                                        <dd>
                                            <input class="txt" type="text" datatype="*6-16" value="" />
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>所在地区:</label></dt>
                                        <dd>
                                            <select data-placeholder="请选择" class="chosen-select select">
                                                <option value="0">浙江</option>
                                                <option value="1">福建</option>
                                                <option value="2">广东</option>
                                                <option value="3">江苏</option>
                                                <option value="4">安徽</option>
                                            </select>

                                            <select data-placeholder="市区" class="chosen-select select">
                                                <option value="0">宁波</option>
                                                <option value="1">杭州</option>
                                                <option value="2">温州</option>
                                                <option value="3">湖州</option>
                                                <option value="4">绍兴</option>
                                            </select>

                                            <select data-placeholder="县区" class="chosen-select select">
                                                <option value="0">海曙</option>
                                                <option value="1">鄞州</option>
                                                <option value="2">江东</option>
                                                <option value="3">江北</option>
                                                <option value="4">镇海</option>
                                            </select>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd>
                                            <input type="checkbox" id="remember" class="chk" /><label for="remember" class="fs14 ml5">我已阅读</label>
                                            <a href="${ctx}/common/rules/info/4F04A3B075184BA8B28321BC02B8498E" class="fs14" target="_blank" >《8637品牌超级代理服务协议》</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd>
                                            <input type="submit" class="btn bgc-r c-w yahei fs16" value="点击注册" /><a></a><span></span>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd><span class="fl fs14">如登录遇到困难,请</span><a class="fs14" href="#">联系我们 :)</a>
                                        </dd>
                                    </dl>
                                </form>
                            </div>
                        </div>
                        <div class="tabbody hide">
                            <div class="form">
                                <form action="注册成功.html">
                                    <dl>
                                        <dt>
                                            <label>手机号码:</label></dt>
                                        <dd>
                                            <input type="text" class="txt" deftxt='例如:130****1111' value="例如:130****1111" datatype="m" />
                                            <a href="#" class="btn">免费获取验证码</a>
                                            <a class="err">
                                                <!--*输入的手机号不符合-->
                                            </a>
                                            <i>若当前号码已不用/丢失，或无法收到验证码？提交申请单由客服帮助处理。</i>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>验证码:</label></dt>
                                        <dd>
                                            <input type="text" class="txt short" value="" />
                                            <a class="err">*输入的验证码不正确</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>电子邮箱:</label></dt>
                                        <dd>
                                            <input type="text" deftxt="例如:youname@163.com" value="例如:youname@163.com" class="txt long" datatype="e" ajaxurl="valid.php?myparam1=value1&myparam2=value2" sucmsg="邮箱格式验证成功！" nullmsg="请输入邮箱！" errormsg="请用邮箱注册！" />
                                            <a class="err">*输入的Email格式不正确</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>登录密码:</label>
                                        </dt>
                                        <dd>
                                            <input name="Password1" class="txt" type="password" value="" plugin="passwordStrength" datatype="*6-15" errormsg="密码范围在6~15位之间！" />
                                            <!--<a class="err">*密码不能为空</a>-->
                                            <div class="passwordStrength" style="display: none;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>重复密码:</label></dt>
                                        <dd>
                                            <input id="Password2" class="txt" type="password" value="" datatype="*" recheck="Password1" errormsg="您两次输入的账号密码不一致！" />
                                            <a class="err">*输入的密码不一致</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>您的姓名:</label></dt>
                                        <dd>
                                            <input class="txt" type="text" value="" />
                                            <a class="err">*输入的姓名不能是数字</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>
                                            <label>所在地区:</label></dt>
                                        <dd>
                                            <select data-placeholder="请选择" class="chosen-select select">
                                                <option value="0">浙江</option>
                                                <option value="1">福建</option>
                                                <option value="2">广东</option>
                                                <option value="3">江苏</option>
                                                <option value="4">安徽</option>
                                            </select>

                                            <select data-placeholder="市区" class="chosen-select select">
                                                <option value="0">宁波</option>
                                                <option value="1">杭州</option>
                                                <option value="2">温州</option>
                                                <option value="3">湖州</option>
                                                <option value="4">绍兴</option>
                                            </select>

                                            <select data-placeholder="县区" class="chosen-select select">
                                                <option value="0">海曙</option>
                                                <option value="1">鄞州</option>
                                                <option value="2">江东</option>
                                                <option value="3">江北</option>
                                                <option value="4">镇海</option>
                                            </select>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd>
                                            <input type="checkbox" id="remember2" class="chk" /><label for="remember2" class="fs14">我已阅读</label>
                                            <a href="${ctx}/common/rules/info/4F04A3B075184BA8B28321BC02B8498E" class="fs14" target="_blank" >《8637品牌超级代理服务协议》</a>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd>
                                            <input type="submit" class="btn bgc-r c-w yahei fs16" value="点击注册" /><a></a><span></span>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt></dt>
                                        <dd><span class="fl fs14">如登录遇到困难,请</span><a class="fs14" href="#">联系我们 :)</a>
                                        </dd>
                                    </dl>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main-right">
                    <div class="head">
                    </div>
                    <div class="content">
                        <h3>已经是8637品牌超级代理会员?</h3>
                        <a class="btn" href="login">立即登录</a><br />
                        <a href="#">8637品牌超级代理是什么?</a><br />
                        <a href="#">为何我们需要8637品牌超级代理?</a><br />
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="service block bgc-w">
                <div class="px1000">
                    <ul>
                        <li class="ser-phone"></li>
                        <li class="ser-ppbz">
                            <dl>
                                <dt>品牌保障</dt>
                                <dd><a>正品授权</a></dd>
                                <dd><a>线下实体</a></dd>
                                <dd><a>合同制</a></dd>
                            </dl>
                        </li>
                        <li class="ser-xsbz">
                            <dl>
                                <dt>新手帮助</dt>
                                <dd><a>注册账号</a></dd>
                                <dd><a>认证管理</a></dd>
                                <dd><a>帮助中心</a></dd>
                            </dl>
                        </li>
                        <li class="ser-qdgl">
                            <dl>
                                <dt>渠道管理</dt>
                                <dd><a>ERP系统帮助</a></dd>
                                <dd><a>进货系统帮助</a></dd>
                            </dl>
                        </li>
                        <li class="ser-ppzq">
                            <dl>
                                <dt>品牌专区</dt>
                                <dd><a>申请入驻</a></dd>
                                <dd><a>入驻帮助</a></dd>
                                <dd><a>品牌商管理</a></dd>
                            </dl>
                        </li>
                        <li class="ser-jxszq">
                            <dl>
                                <dt>终端商专区</dt>
                                <dd><a>申请经销</a></dd>
                                <dd><a>经销帮助</a></dd>
                                <dd><a>终端商管理</a></dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="copyright">
                <div>
                    <a href="#" title="" target="">关于8637品牌超级代理</a> | 
                    <a href="#" title="" target="">帮助中心</a> | 
                    <a href="#" title="" target="">诚聘英才</a> | 
                    <a href="#" title="" target="">联系我们</a> | 
                    <a href="#" title="" target="">版权说明</a>
                    <a href="#" title="" target="">浙江天下商邦股份有限公司</a>
                    <span>客服热线：0574-87217777</span>
                </div>
                <span>Copyright©2003-2012，版权所有8637.com  增值电信业务经营许可证：浙B2-20130224</span>
            </div>
        </div>
    </div>
    <script src="${res}/scripts/jquery-1.11.0.min.js"></script>
    <script src="${res}/scripts/passwordStrength-min.js"></script>
    <script src="${res}/scripts/Validform_v5.3.2_min.js"></script>
    <script>
        //输入样式
        $('input[deftxt]').css({ color: '#bbb' }).focus(function () {
            if ($(this).val() == $(this).attr("deftxt")) {
                $(this).val("").css({ color: '#666' });
            }
        }).blur(function () {
            if ($(this).val() == '') {
                $(this).val($(this).attr("deftxt")).css({ color: '#bbb' });
            }
        });

        //标签切换
        var tabs = $(".tabs li a").click(function () {
            $(".tabbody").hide().eq($(tabs).removeClass('on').index($(this).addClass('on'))).fadeIn(300);
        });

        $("input.txt,select").focus(function () {
            $(this).css({ 'border-color': '#FC595A' });
        }).blur(function () {
            $(this).not('.Validform_error').css({ 'border-color': '#999' });
        });

        //$(".Validform_checktip").load(function () {
        //    var _left = $(this).parent().find('.txt').outerWidth();
        //    $(this).css({ left: _left + 10 }).animate({ left: _left, }, 300);
        //});

        //$('#userpassword').pwdstr('#passwordStrength');
        $('input.txt').after('<a class="Validform_checktip">');

        $(".form form").Validform({
            tiptype: 4,
            usePlugin: {
                passwordstrength: {
                    minLen: 6,
                    maxLen: 18
                }
            }
        });


    </script>
</body>
</html>
                 