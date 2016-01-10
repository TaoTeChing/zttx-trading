define(function(require){

    require("./swfobject.js");
    
    var Video = function(config){
    	
    	var width = config.width || 200;
    	var height = config.height || 200;
    	var url = config.url;
    	var element = config.element;
    	var isAutoPlay = config.isAutoPlay || "no";
    	var CuPlayerWidth = config.CuPlayerWidth || "800";
    	var CuPlayerHeight = config.CuPlayerHeight || "600";
    	
    	 var so = new SWFObject(window.SWF_Video_URL,"CuPlayerV4",width,height,"9","#000000");
    	 so.addParam("allowfullscreen","true");
    	 so.addParam("allowscriptaccess","always");
    	 so.addParam("wmode","opaque");
    	 so.addParam("quality","high");
    	 so.addParam("salign","lt");
		 so.addVariable("CuPlayerSetFile",SWF_Video_File);  
		 so.addVariable("CuPlayerImage",SWF_IMAGE_FILE);//视频略缩图,本图片文件必须正确
		 so.addVariable("CuPlayerAutoPlay",isAutoPlay); //是否自动播放
		 so.addVariable("CuPlayerFile",url); //视频文件地址
		 so.addVariable("CuPlayerWidth",CuPlayerWidth); //视频内置的宽
		 so.addVariable("CuPlayerHeight",CuPlayerHeight); //视频内置的高
		 so.addVariable("CuPlayerPosition","top-right"); //
		 so.addVariable("CuPlayerLogo","images/Logo.png"); //
 		 so.write(element);
		 
		 return so;   
    }
    
    return Video;

})
