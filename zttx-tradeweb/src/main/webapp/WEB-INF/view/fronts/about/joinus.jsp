<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>加入我们 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637,品牌超级代理,品牌招商,天下商邦"/>
    <meta name="description"
          content="8637品牌超级代理以O2O(Online to Offline)模式构建零中间商的“电子商务直供平台”，利用互联网技术服务品牌商及终端销售商，改变传统品牌供应链（专卖店、批发商（代理商）、品牌商），将通过互联网直连方式减少中间供应环节，打造从厂家到店铺的较短供应链。"/>
    <link href="${res}/styles/fronts/about/base.css" rel="stylesheet"/>
    <link href="${res}/styles/fronts/about/joinus.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/top"/>
    <jsp:include page="_header.jsp">
        <jsp:param value="4" name="m"/>
    </jsp:include>
    <div class="main">
        <div class="ts-container">
            <div class="ts-mb-sm ts-mt-sm">
                <span>当前所在页</span> :
                <a class="text-primary" href="/">首页</a> >
                <a class="text-primary" href="/about/">关于我们</a> >
                <span class="text-danger">加入我们</span>
            </div>
            <div class="hr-dashed ts-mb-sm"></div>
            <div class="ts-mb-lg">
                <img src="${res}/images/fronts/about/about-index-pic4.jpg">
            </div>
            <div id="tabs-panel">
                <div class="ts-mb-sm">
                    <ul class="about-tab text-yahei text-md inline-float">
                        <li><a class="ui-switchable-active" href="javascript:;">加入我们</a></li>
                    </ul>
                </div>
                <div class="hr-dashed ts-mb-sm"></div>
                <div class="index-content">
                    <div class="about-tab-item content-a">
                        <table class="ui-table">
                            <thead>
                            <tr>
                                <th>职位名称</th>
                                <th>部门名称</th>
                                <th>工作地点</th>
                                <th>招聘人数</th>
                                <th>更新时间</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div class="contactus ts-mt-sm">
                            <p>∷HR人事部联系方式:0574-87217777 按3</p>

                            <p>∷邮箱地址:hr@8637.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["template", "dialog"], function (template, Dialog) {

        var joinus = {
            rows: [{
                title: "项目总监/经理", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                content: "1. 计算机或相关专业毕业，大学本科及以上学历；<br />\
                2. 5年以上互联网系统/软件(Java)开发经验，2年以上项目管理经验；<br />\
                3. 精通Jsp、Java、Servlet等语言及Struts、Spring、Hibernate、Ibatis等各种常用的应用开发框架，具备一定Java系统架构经验；<br />\
                4. 熟悉JavaScript、JQuery、AJAX、HTML、CSS等语言；<br />\
                5. 熟练使用SQL和存储过程，熟悉SQL Server、MySQL、或Oracle等商业数据库，对数据库管理、大数据处理及性能调优有经验者优先；<br />\
                6. 熟悉Tomcat、websphere、weblogic、jboss等应用服务器，并熟练掌握至少一种应用服务器的部署和使用。对网站架构、高并发及性能调优有经验者优先；<br />\
                7. 对于不熟悉的领域，具有较强的学习能力，并能熟练阅读英文技术文档；<br />\
                8. 具备良好的表达能力和沟通能力；能够承受一定的工作压力；<br />\
                9. 对工作认真负责，遵守开发规范，有强烈的责任感，具有良好的团队合作精神；<br />\
                10. 能力优秀者可应聘项目总监。"
            },
                {
                    title: "JAVA架构师/经理", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 6年以上Java、J2EE开发工作经验，2年以上系统设计、架构设计工作经验；<br />\
                2. 编程基础扎实，熟悉io、网络socket、多线程、集合等基础框架；<br />\
                3. 熟悉分布式、缓存、消息、搜索、数据分析等工作机制；<br />\
                4. 有大型分布式、高并发、高负载、高可用性系统设计开发经验；<br />\
                5. 学习能力强，适应能力好；良好的团队合作精神和承受压力的能力；<br />\
                6. 有iOS客户端开发、Android客户端开发经验者优先考虑；<br />\
                7. 有LVS、Heartbeat+DRBD、Linux内核调优经验者优先考虑；<br />\
                8. 熟悉JVM内存回收机制，有JVM调优相关经验者优先考虑；<br />\
                9. 有大型电子商务平台开发经验者优先；<br />\
                10. 能力优秀者可应聘项目总监。"
                },
                {
                    title: "JAVA工程师/经理", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 计算机相关专业，本科以上学历；<br />\
                2. 良好的学习能力和逻辑思维能力，热爱技术开发工作；<br />\
                3. 强烈的责任感和事业心，能够在压力下高效的完成工作；<br />\
                4. 3年以上J2EE或J2SE项目开发经验，熟悉常用的设计模式；<br />\
                5. 熟悉常用框架：Spring、SpringMVC、Hibernate、iBatis等；<br />\
                6. 熟练掌握关系型数据库（Oracle、MySql等）编程，能够独立撰写复杂的SQL语句；<br />\
                7. 英语读写能力较好，能够阅读理解英文技术文档；<br />\
                8. 有大型J2EE项目的部署、负载均衡、调优经验者优先。"
                },
                {
                    title: "产品经理", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 本科以上学历，专业不限，计算机、信息技术、设计等相关专业优先；<br />\
                2. 三年以上互联网行业工作经验，至少一年以上产品设计经验；<br />\
                3. 较强的英文阅读能力、语言表达能力、逻辑思维能力；<br />\
                4. 良好的沟通、协调能力和团队协作精神；<br />\
                5. 对视觉设计、用户体验、搜索引擎优化等有深刻理解；<br />\
                6. 有技术开发工作经验优先。"
                },
                {
                    title: "UI设计师", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 3年以上互联网产品用户体验设计经验，美术、平面设计、心理学、社会学、信息学、人机交互、计算机等专业大学专科及以上学历；<br />\
                2. 具备一定的心理学专业知识，熟悉可用性（易用性）实验的流程与方法；熟悉各类用户体验测试技术；须有良好的视觉创意和网页美工<br />经验（请附上个人案例）；<br />\
                3. 分析能力出色，了解用户习惯，较强的信息资料的收集和整理分析，具有较强的互联网产品的可用性分析及用户行为分析能力；<br />\
                4. 具有良好的沟通交流能力；能够独立完成专题页的设计与制作，良好的布局，和谐的色彩，大胆的创意；<br />\
                5. 较好的写作能力和书面表达能力，可以很好地表达和传递自己的观察及研究结果,配和项目负责人制订UE/UI设计规范及流程。"
                },
                {
                    title: "前端工程师", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 熟练使用DIV+CSS 和XHTML(HTML)+CSS架构页面代码技术, 熟悉IE6789 /Chrome/Firefox浏览器兼容，保证页面支持多浏览器使用；<br />\
                2. 至少有1到2年以上网页设计制作的经验,有自己的作品,对待工作很有热情,积极,有过硬的网页设计的功底；<br />\
                3. 熟练操作Dreamweaver、Flash、Photoshop、Firework等网页制作相关软件;熟练使用css、JavaScripts；<br />\
                4. 熟练和精通CSS3/jquery/ JavaScripts的优先；<br />\
                5. 熟练掌握各种图形图像与动画处理软件，曾经有实际的项目经历者或者产品化开发优先；<br />\
                6. 对工作认真负责，有上进心，善于沟通，学习和独立思考能力强，能按时完成各项工作任务。"
                },
                {
                    title: "网络工程师", department: "开发部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 掌握计算机网络技术，熟悉TCP/UDP通信原理、HTTP协议及Web安全；<br />\
                2. 熟悉交换机、负载均衡、防火墙等设备的功能和性能,能够安装调试CISCO、华为等网络设备；<br />\
                3. 熟悉IP网络技术、广域网接入技术，能够完成网络工程设计；<br />\
                4. 熟悉主流配置管理工具，如：VSS、SVN、StarTeam、ClearCase、Hudson(jenkins)之一者优先；<br />\
                5. 熟悉Linux/Unix操作系统管理、内核调优及Shell编程者优先。"
                },
                {
                    title: "文案策划", department: "企划部", address: "宁波", people: "2", updatetime: "2014-06-01",
                    content: "1. 中文及相关专业，有深厚的文学功底；<br />\
                    2. 有较强的文字编辑与方案处理能力；思维活跃，思路开阔；<br />\
                    3. 文字能力强，头脑缜密，有逻辑性；文笔流畅，有扎实的应用文写作能力；思维深刻，文笔流畅；<br />\
                    4. 擅长各种文体写作，有大型会议活动文案和网络推广活动文案撰写经验者优先考虑；<br />\
                    5. 具有良好的敬业精神，有较强的创新意识，能够在较大压力下完成工作；<br />\
                    6. 勤奋、踏实，有团队协作精神；<br />\
                    7. 大专及以上，工作经验两年以上；<br />\
                    8. 能力稍弱者，具备较强学习能力者也可考虑。"
                }]
        };

        var html = template.render("tabTemplate", joinus);
        $(".ui-table tbody").html(html);


        $(document).on("click", 'a[data-index]', function () {
            var html = template.render("jobTemplate", joinus.rows[Number($(this).data("index"))]);

            new Dialog({
                content: html
            }).show();
        });
    });

</script>
<script type="text/html" id="tabTemplate">
    {{each rows}}
    <tr>
        <td>
            <a data-index="{{$index}}" href="javascript:;" class="link">{{$value.title}}</a> <i
                class="icon i-urgent"></i><i class="icon i-new"></i>
        </td>
        <td>{{$value.department}}
        </td>
        <td>{{$value.address}}
        </td>
        <td>{{$value.people}}
        </td>
        <td>{{$value.updatetime}}
        </td>
        <td>
            <i class="iconfont">&#xe60e;</i>
        </td>
    </tr>
    {{/each}}
</script>
<script type="text/html" id="jobTemplate">
    <div class="ui-head">
        <h3>{{title}}</h3>
    </div>
    <div class="ui-box-content">
        <div class="cont">

            <h3 style="color: #f8970a; font-size: 14px;">岗位描述：
            </h3>

            <p>
                {{#content}}
            </p>
        </div>
        <div class="btns">
            <a href="http://search.51job.com/list/co,c,3174891,000000,10,1.html" target="_blank"
               class="ui-button ui-button-lorange">申请岗位</a>
        </div>
    </div>
</script>
</body>
</html>
