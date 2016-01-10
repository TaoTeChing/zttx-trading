<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>8637品牌超级代理-店铺装修-产品展示</title>
    <meta name="keywords" content="8637品牌超级代理-店铺装修" />
    <meta name="description" content="8637品牌超级代理-店铺装修" />
    <link rel="stylesheet" href="${res}/styles/market/brandviewbase.css" />
    <link rel="stylesheet" href="${res}/styles/market/prodisshow.css" />
    
</head>
<body>
	<!---------------------------------     top     --------------------------------->
	<jsp:include page="${ctx}/market/viewTop"/>
	
	<!---------------------------------     head    --------------------------------->
	<jsp:include page="${ctx}/market/viewHead">
		<jsp:param name="m" value="1" /> 
		<jsp:param name="brandesId" value="${brandesId}"/>
	</jsp:include>
    
    <!---------------------------------   主体内容      --------------------------------->
    <div class="all-prodisshow">
        <div class="prodisshow clear">
        	<!--------------------------------- 品牌库存信息栏 --------------------------------->
            <div class="comsite">
                <div class="comlogobox f-l">
                    <img src="${ctx}/images/market/temp/duo.jpg" />
                </div>
                <div class="purchase">
                    <a class="" href="#" title="" target="">快速采购<span>清爽列表+货号</span></a>
                    <p class="font">我的收藏：10件 我的进货单：10件</p>
                </div>
                <div class="comname">朵彩/DOCARE<span>中国十大内衣品牌</span></div>
                <div class="comclassic"><a class="selected" href="#" title="" target=""><span>总款式191件</span></a><a class="selected" href="#" title="" target=""><span>2013款（51件）</span></a><a class="selected" href="#" title="" target=""><span>2012款（51件）</span></a><a class="selected" href="#" title="" target=""><span>2011款（51件）</span></a><a href="#" title="" target=""><span>更早>></span></a></div>
            </div>
            <!-----------------------------  end：品牌库存信息栏    ------------------------------>
            <div class="showbody font">
            	<!---------------------------------    查询条件     --------------------------------->
                <div class="storeclass">
                    <h3 class="title f-l">店内分类</h3>
                    <ul class="storeclassul">
                        <li><a href="#" title="" target=""><span>新款保暖内衣</span></a></li>
                        <li><a href="#" title="" target=""><span>男士内衣</span></a></li>
                        <li><a href="#" title="" target=""><span>女士内衣</span></a></li>
                        <li><a href="#" title="" target=""><span>男士内裤</span></a></li>
                        <li><a href="#" title="" target=""><span>女士内裤</span></a></li>
                    </ul>
                </div>
                <div class="sizeclass clear">
                    <h3 class="title2 f-l">尺码分类:</h3>
                    <form>
                        <ul class="sizeclassul">
                            <li><a href="#" title="" target=""><span>X</span><i></i></a></li>
                            <li><a href="#" title="" target=""><span>L</span><i></i></a></li>
                            <li><a href="#" title="" target=""><span>XL</span><i></i></a></li>
                            <li><a href="#" title="" target=""><span>XXL</span><i></i></a></li>
                        </ul>
                        <div class="ready-cancel">
                            <input type="button" value="确定" class="ready r-cbtn" /><input type="button" value="取消" class="cancel r-cbtn" />
                        </div>
                    </form>
                    <span class="more">+多选</span>
                </div>
                <div class="selectbar clear">
                    <ul>
                        <li><a href="#" title="" target="">按进货量排 ↓</a></li>
                        <li><a href="#" title="" target="">按进上架时间排序 ↓</a></li>
                        <li><a href="#" title="" target="">按进产品价格排序 ↓</a></li>
                        <li>
                            <span class="f-l">价位：</span>
                            <input type="text" data-deftxt="1" value="1" class="text1 f-l" />
                            <input type="text" data-deftxt="100" value="100" class="text1 f-l" />
                        </li>
                        <li>
                            <form>
                                <span class="f-l">商品名或货号：</span>
                                <input type="text" data-deftxt="商品名或货号" value="商品名或货号" class="text2 f-l" />
                                <input type="button" value="搜索" class="button f-l" />
                            </form>
                        </li>
                        <li><a href="#" title="" target=""><span class="more f-r">更多选项>></span></a></li>
                    </ul>
                </div>
                <!--------------------------------- end：查询条件  --------------------------------->
                
                <div class="products">
                	<!---------------------------------    产品目录     --------------------------------->
                    <jsp:include page="${ctx}/market/viewProductList">
            			<jsp:param value="${brandesId}" name="brandesId"/>
            		</jsp:include>
                    <!--------------------------------- end：产品目录  --------------------------------->
                    
                    <!---------------------------------   我的进货单    --------------------------------->
                    <div class="myorder f-l">
                        <div class="title"><span class="f-l">我的进货单</span></div>
                        <span class="qingkong f-r">清空</span>
                        <div class="goods">
                            <dl class="goodsdl">
                                <dd><span class="close"></span>
                                    <div class="imgbox f-l">
                                        <img src="${ctx}/images/market/temp/big.jpg" class="image0" width="50" height="50" />
                                    </div>
                                </dd>
                                <dt><a href="#" title="" target="">朵彩1</a></dt>
                                <dd><a href="#" title="" target="">DS11099</a></dd>
                            </dl>
                            <dl class="goodsdl clear">
                                <dd><span class="close"></span>
                                    <div class="imgbox f-l">
                                        <img src="${ctx}/images/market/temp/big.jpg" class="image0" width="50" height="50" />
                                    </div>
                                </dd>
                                <dt><a href="#" title="" target="">朵彩2</a></dt>
                                <dd><a href="#" title="" target="">DS11099</a></dd>
                            </dl>
                        </div>
                        <a class="clickorder clear" href="#" title="" target="">点击结算</a>
                    </div>
                    <!--------------------------------- end：我的进货单--------------------------------->
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
    <!--------------------------------- end:主体内容  --------------------------------->
    
    <!---------------------------------    footer   --------------------------------->
	<jsp:include page="${ctx}/market/viewFooter"></jsp:include>
    
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/market/seajs_config.js"></script>
    <script src="${res}/scripts/market/jquery.ad-gallery.js" type="text/javascript"></script>
	<script src="${res}/scripts/plugin/template-simple.js" type="text/javascript"></script>
    <script>
        $(function(){
        	var pageList = {};
        	
        	template.helper('$formatMoney', function (s) {
        		if(s == null || s == "")
        		{
        			s = "0";
        		}
        		var num = parseFloat(s);
                return num.toFixed(2);
            });
        	
    		seajs.use(["pagination"], function (Pagination){
    			$(".ajax-templage").each(function(index){
    				var $this = $(this);
    				var tempId = "templage" + index;
    				$this.attr("id", tempId);
    				pageList[tempId] = new Pagination({
	                    url: $this.attr("_url"),
	                    elem: $this.attr("_page"),
	                    handleData: function (data) {
	                    	var html = template.render(tempId, data);
	                        $($this.attr("_pid")).html(html);
	    	            	$('.ad-gallery').adGallery({ effect: 'fade' });
	                    }
	                });    
    			});
    		});
    	});
    </script>
    <script src="${res}/scripts/market/last.js" type="text/javascript"></script>
    <script src="${res}/scripts/market/ZeroClipboard.min.js" type="text/javascript"></script>
    <!-- 点击复制插件-->
    <script type="text/javascript">
        // 定义一个新的复制对象
        var clip = new ZeroClipboard(document.getElementById("d_clip_button"), {
            moviePath: "../images/market/zd.jpg"
        });
        // 复制内容到剪贴板成功后的操作
        clip.on('complete', function (client, args) {
            alert("复制成功，复制内容为：" + args.text);
        });
    </script>
</body>
</html>
