<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>服务管理-约逛管理-约逛仓库</title>
    <link href="/styles/dealer/global.css" rel="stylesheet"/>
    <link href="/styles/dealer/weshop.css" rel="stylesheet"/>
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
                        <i class="icon i-setting"></i><a href="/dealer/center">管理中心</a> &gt;&gt; <span
                            class="bb">服务管理</span>
                        <a class="panel-crumbs-help" href="/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb40 clearfix">
                    <div class="weshop-trends-publish">
                        <h2>我的约逛管理</h2>
                        <jsp:include page="/WEB-INF/view/dealer/weshop_navi.jsp" >
                        	<jsp:param value="4" name="tab"/>
                        </jsp:include>
                        <div class="trends-publish clearfix">
                            <dl class="trends-cover">
                            	<c:if test="${fn:length(newses)==0}">
                            		<dd class="trends-image active">
		                                	<h3>标题</h3>
		                                    <div class="js-img-center">
		                                        <img src="/images/dealer/weshop/cover.jpg">
		                                    </div>
		                                </dd>
                            	</c:if>
                            	<c:forEach var="news" items="${newses}" varStatus="status">
                            		<c:if test="${status.index==0}">
                            			<dd class="trends-image active">
		                                	<h3>${news.title }</h3>
		                                    <div class="js-img-center">
		                                        <img src="${weshop }${news.newsImg}" style="width:290px;height:180px;">
		                                    </div>
		                                </dd>
                            		</c:if>
                            		<c:if test="${status.index!=0}">
                            			 <dd class="trends-cont">
		                                    <div class="cont fl">
		                                        <h3 class="lh2">${news.title}</h3>
		                                        <p>${fns:trimLongString(fns:cleanHtmlElems(news.newMark),500) } </p>
		                                    </div>
		                                    <div class="js-img-center fr">
		                                        <img src="${weshop }${news.newsImg}" style="width:80px;height:80px;">
		                                    </div>
		                                </dd>
                            		</c:if>
                            	</c:forEach>
                                <dt class="trends-other">
                                    <a class="new-other" href="javascript:;">
                                        <i class="iconfont">&#xe615;</i>
                                    </a>
                                </dt>
                            </dl>
                            <div class="trends-content">
                            	<input type="hidden" value='${groupId }' id="groupId">
                                <form:form class="ui-form" id="trendsForm" data-widget="validator">
                                    <div class="content-form">
                                        <div class="content-form-item ui-form-item">
                                            <label>标题 </label>
                                            <input class="ui-input" name="title" required value="${currnews.title }" >
                                        </div>
                                        <div class="content-form-item ui-form-item">
                                            <label>作者(选填)</label>
                                            <input class="ui-input" name="author" value='${currnews.author }'>
                                        </div>
                                        <div class="content-form-item ui-form-item">
                                            <label><span class="fr">大图片建议尺寸:360像素*200像素</span>
                                                封面
                                            </label>
                                            <input class="ui-input mb5" type="file" name='file' id='fileImageId'>
                                            <input class="ui-input mb5" type="hidden" name='newsImg' value='${currnews.newsImg }'>
                                            <label for="isshow"><input type="checkbox" class="ui-checkbox" id="isshow" ${currnews.joinMark==true?'checked':'' }> <span>封面图片显示在文章中</span></label>
                                        </div>
                                        
                                        <div class="content-form-item ui-form-item">
                                            <label>
                                             <%--
                                                    <span class="fr">
                                                    已载入2014/06/30 17:07:44的草稿
                                                        <a class="link" href="javascript:;" tabindex="-1">取消</a>
                                                    </span>
                                              --%>      
                                                正文
                                            </label>
                                            <textarea class="js-umeditor" id="umeditor">${currnews.newMark }</textarea>
                                            <%--
                                            <div class="mt10">
                                                <a class="link fs14 yahei">添加原文链接</a>
                                            </div>
                                             --%>
                                        </div>
                                    </div>
                                    <div class="mt10">
                                        <button class="ui-button ui-button-lred mr10">立即发布</button> <button class="ui-button ui-button-lorange">预 览</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    <script>
   
    </script>
    
<script>
    //,isAjax,editor[display,id,width,height],beforeSubmitFn,addElemFn
    
    
    var trends={};
    
    var initData=[];
    <c:forEach var="news" items="${newses}" >
    initData.push({title: '${news.title}', author: '${news.author}', newsImg: '${news.newsImg}',newMark: '${news.newMark}',joinMark: '${news.joinMark}' });
	</c:forEach>
    baseFormValidator({
    	isAjax:true,
        selector:"#trendsForm",
        editor:{
            id:"umeditor",
            width:618,
            height:250
        },
        beforeSubmitFn:function(){
        	trends.saveActiveItemData();
        	var groupId=$('#groupId').val();
        	var obj=[];
        	$('dl.trends-cover dd').each(function(){
        		var _data=$(this).data('form');
        		if(_data){
        			obj.push(_data);
        		}
        	});
            $.ajax({type:'POST',url:"${ctx}/dealer/weshop/trends/publish/submit?shopId=${shopId}&groupId="+groupId,
	        	data:JSON.stringify(obj),
	        	contentType:'application/json',
	        	dataType:'json',
	        	success:function(data){
	        		if(data.code==121000){
	        			$('#groupId').val(data.object.groupId);
	        			remind('success','保存成功');
	        		}
	        	}
        	});
        }
    });

    $("dl.trends-cover").on('click','div.trends-delete i.iconfont',function(){
    	$(this).parents('dd').slideUp(300,function(){

            if($(this).prev())$(this).prev().trigger("click");
    		this.remove();
            if($("dd.trends-image").length==0)
            {
                var first = $(".trends-cover dd:eq(0)").removeClass("trends-cont").addClass("trends-image").trigger("click");
                first.find(".fl").removeClass("fl");
                first.find(".fr").removeClass(" fr");
                first.find("p").hide();
            }
    	});

        if($(".trends-cover dd").length<=6)
        {
            $(".trends-other").slideDown(300);
        }

    });

    $(".trends-other").click(function(){
        var html = $('<dd class="trends-cont"><div class="cont fl"><h3 class="lh2">标题</h3><p></p></div>\
                      <div class="js-img-center fr"><img src="/images/dealer/weshop/sm-pic.jpg"></div>\
                      <div class="trends-arrow"></div><div class="trends-delete"><i class="iconfont">&#xe619;</i></div></dd>');
        if($(".trends-cover dd").length==0)
        {
            html= $('<dd class="trends-image"><h3>标题</h3><div class="js-img-center"><img src="/images/dealer/weshop/cover.jpg"></div>\
                    <div class="trends-arrow"></div><div class="trends-delete"><i class="iconfont">&#xe619;</i></div></dd>');
        }

        $(this).before(html.hide());
        html.slideDown(300).trigger("click");

        if($(".trends-cover dd").length>5)
        {
            $(this).slideUp(300);
        }
    });
    /**保存当前编辑框数据*/
    trends.saveActiveItemData=function(){
    	var title=$("#trendsForm").find("[name=title]").val();
    	var author=$("#trendsForm").find("[name=author]").val();
    	var newsImg=$("#trendsForm").find("[name=newsImg]").val();
    	var newMark=UM.getEditor('umeditor').getContent();
    	var joinMark=$("#trendsForm").find(":checkbox").prop("checked");
    	$(".trends-cover dd.active").data('form',{title: title, author: author, newsImg: newsImg,newMark: newMark,joinMark:joinMark });
    };
    /**载入数据到当前编辑框*/
    trends.loadItemDataToActive=function(data){
    	 $("#trendsForm").find("[name=title]").val(data==null?'':data.title);
	     $("#trendsForm").find("[name=author]").val(data==null?'':data.author);
	     $("#trendsForm").find("[name=newsImg]").val(data==null?'':data.newsImg);
	     UM.getEditor('umeditor').setContent(data==null?'':data.newMark);
	     $("#trendsForm").find(":checkbox").prop("checked",data==null?false: data.joinMark);
    };
    /**异步文件上传*/
    trends.uploadImage=function(){
    	dialogLoading(function (d) {
            seajs.use(["$", "ajaxFileUpload"], function ($) {
                $.ajaxFileUpload({
                    url: '${ctx}/dealer/weshop/upload',
                    secureuri: false,
                    fileElementId: 'fileImageId',
                    dataType: 'json',
                    success: function (data) {
                    	 $("#fileImageId").bind("change",function(){
                	    	 trends.uploadImage();
                	    });
                        if (data.code == 121000) {
                        	$("#trendsForm").find("[name=newsImg]").val(data.rows[0].path);
                            $(".trends-cover .active img")[0].src="${weshop}" + data.rows[0].path;
                        }
                        d.destroy();
                    }
                });
            });
        });
    };
    
    /**切换条目事件*/
    $(document).on("click",".trends-cover dd",function() {
    	trends.saveActiveItemData(); 
        var items = $(".trends-cover dd").removeClass("active");
        $(this).addClass("active");
        $(items).find(".trends-delete i").not($(this).find(".trends-delete i")).animate({right:-60},328);
        $(this).find(".trends-delete i").animate({right:0},328);
        trends.loadItemDataToActive($(this).data("form"));
    });
    
    $("#fileImageId").bind("change",function(){
    	 trends.uploadImage();
    });
    
    $(".trends-cover dd").each(function(i,o){
        $(o).append('<div class="trends-arrow"></div><div class="trends-delete"><i class="iconfont">&#xe619;</i></div>');
    });
    
    (function(){
    	$('dl.trends-cover dd').each(function(i,d){
    		$(this).data('form',initData[i]);
    	});
    })();


    $("input[name=title]").keyup(function(){
        $(".trends-cover .active h3").text($(this).val());
    });


</script>
</body>
</html>
