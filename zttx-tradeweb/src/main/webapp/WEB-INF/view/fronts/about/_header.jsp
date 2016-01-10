<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="header">
    <div class="navigation">
        <div class="ts-container">
            <div class="logo">
                <a href="${ctx}/" title="8637品牌超级代理" target="_blank">
                    <img src="${res}/images/common/logo-old.png" alt="8637品牌超级代理">
                </a>
            </div>
            <div class="main-nav">
                <ul class="inline-float">
                    <li>
                        <a href="${ctx }/">首页</a>
                    </li>
                    <li <c:if test="${param.m=='1'}"> class="hover"</c:if>>
                        <a href="${ctx }/about">关于我们</a>
                    </li>
                    <li <c:if test="${param.m=='2'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/superiority">模式与优势</a>
                    </li>
                    <li <c:if test="${param.m=='3'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/progress">企业历程</a>
                    </li>
                    <li <c:if test="${param.m=='4'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/joinus">加入我们</a>
                    </li>
                    <li <c:if test="${param.m=='5'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/contactus">联系我们</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%--下个版本的内容
<div class="header">
    <div class="navigation">
        <div class="ts-container">
            <div class="logo">
                <a href="${ctx}/" title="8637品牌超级代理" target="_blank">
                    <img src="${res}/images/common/logo-old.png" alt="8637品牌超级代理">
                </a>
            </div>
            <div class="main-nav">
                <ul class="inline-float">
                    <li <c:if test="${param.m=='1'}"> class="hover"</c:if>>
                        <a href="${ctx }/about">产品与服务</a>
                    </li>
                    <li <c:if test="${param.m=='2'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/superiority">关于我们</a>
                    </li>
                    <li <c:if test="${param.m=='3'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/joinus">加入我们</a>
                    </li>
                    <li <c:if test="${param.m=='4'}"> class="hover"</c:if>>
                        <a href="${ctx }/about/contactus">联系我们</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    &lt;%&ndash;<div style="background: red;height: 2px;width: 100%"></div>&ndash;%&gt;
</div>
<div class="float-list">
    <div class="float-list-con">
        <div class="fr">
            <ul style="width: 140px;margin-right: 10px" <c:if test="${param.n=='1'}"> class="hover"</c:if>>
                <li <c:if test="${param.k=='1'}"> class="this"</c:if>><a href="/" target="_blank">8637品牌超级代理</a></li>
                <li <c:if test="${param.k=='2'}"> class="this"</c:if>><a href="${ctx}/factory/introduce" target="_blank">8637品牌工厂店</a></li>
                <li <c:if test="${param.k=='3'}"> class="this"</c:if>><a href="${ctx}/soft/erp/" target="_blank">智慧门店管理系统</a></li>
                <li <c:if test="${param.k=='4'}"> class="this"</c:if>><a href="http://app${zttx}" target="_blank">约逛手机APP</a></li>
                <li <c:if test="${param.k=='5'}"> class="this"</c:if>><a href="${ctx}/soft/drp/" target="_blank">智慧品牌总部系统</a></li>
            </ul>
            <ul style="width: 100px;margin-right: 40px"<c:if test="${param.n=='2'}"> class="hover"</c:if>>
                <li <c:if test="${param.k=='1'}"> class="this"</c:if>><a href="" target="_blank">企业简介</a></li>
                <li <c:if test="${param.k=='2'}"> class="this"</c:if>><a href="" target="_blank">企业历程</a></li>
                <li <c:if test="${param.k=='3'}"> class="this"</c:if>><a href="" target="_blank">业务团队</a></li>
                <li <c:if test="${param.k=='4'}"> class="this"</c:if>><a href="" target="_blank">资质介绍</a></li>
                <li <c:if test="${param.k=='5'}"> class="this"</c:if>><a href="" target="_blank">模式与优势</a></li>
            </ul>
            <ul style="width: 100px;margin-right: 30px"<c:if test="${param.n=='3'}"> class="hover"</c:if>>
                <li <c:if test="${param.k=='1'}"> class="this"</c:if>><a href="" target="_blank">职位信息</a></li>
                <li <c:if test="${param.k=='2'}"> class="this"</c:if>><a href="" target="_blank">培训发展</a></li>
                <li <c:if test="${param.k=='3'}"> class="this"</c:if>><a href="" target="_blank">文化生活</a></li>
            </ul>
            <ul style="width: 100px"<c:if test="${param.n=='4'}"> class="hover"</c:if>>
                <li <c:if test="${param.k=='1'}"> class="this"</c:if>><a href="" target="_blank">联系我们</a></li>
                <li <c:if test="${param.k=='2'}"> class="this"</c:if>><a href="" target="_blank">留言反馈</a></li>
                <li <c:if test="${param.k=='3'}"> class="this"</c:if>><a href="${ctx}/common/login" target="_blank">交易平台</a></li>
            </ul>
        </div>
    </div>
</div>
<script>
    seajs.use(['popup'], function(Popup) {
        new Popup({
            trigger: '.main-nav',       // 触发器
            element: '.float-list',    // 提示框显示的内容
            align: {
                baseElement: '.header',
                baseXY: [0, 91]
            },
            effect: 'fade' //fade slide (fade slide)
        });
    });
</script>--%>
