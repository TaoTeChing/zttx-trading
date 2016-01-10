(function () {
	var flag = window.IMAGE_SWITCH ;				//全局的  访问图库的开关建    鲍建明
	
	//var staticHtml = typeof showImgKu == 'function' ? "<%=lang_tab_imgSearch%>" : "<%=lang_tab_imgNull%>";
	var staticHtml = "<%=lang_tab_imgSearch%>";
	
	
    var utils = UM.utils,
        browser = UM.browser,
        Base = {
        checkURL: function (url) {
            if(!url)    return false;
            url = utils.trim(url);
            if (url.length <= 0) {
                return false;
            }
            if (url.search(/http:\/\/|https:\/\//) !== 0) {
                url += 'http://';
            }

            url=url.replace(/\?[\s\S]*$/,"");

            if (!/(.gif|.jpg|.jpeg|.png)$/i.test(url)) {
                return false;
            }
            return url;
        },
        getAllPic: function (sel, $w, editor) {
            var me = this,
                arr = [],
                $imgs = $(sel, $w);


            $.each($imgs, function (index, node) {
            	
                $(node).removeAttr("width").removeAttr("height");

                if (node.width > editor.options.initialFrameWidth) {
                    me.scale(node, editor.options.initialFrameWidth -
                        parseInt($(editor.body).css("padding-left"))  -
                        parseInt($(editor.body).css("padding-right")));
                }
                return arr.push({				
                    width: node.width,
                    height: node.height,
                    _src: node.src,
                    src: node.src
                });
            });

            return arr;
        },
        
        /**
         * 
         * 拼装图库图片的方法
         * 鲍建明
         * 
         */
        getImgKu : function getImgKu(){
          	 var me = this,
               arr = [];
               var aImg = $('#edui-image-JimgSearch .js-entry li.current img');
   	     	$.each(aImg, function (index, node){
   	     		arr.push({
                       //width: node.width,
                       //height: node.height,
                       //_src: node.src,
                       src: $(node).data("icon")
                   });
   	     	})
   	     	return arr;
          },
        
        scale: function (img, max, oWidth, oHeight) {
            // 冯唐路
            // 图片上传后高度和宽度问题的修复
            /*
            *此处为百度编辑器原来的处理方法
            var width = 0, height = 0, percent, ow = img.width || oWidth, oh = img.height || oHeight;
            if (ow > max || oh > max) {
                if (ow >= oh) {
                    if (width = ow - max) {
                        percent = (width / ow).toFixed(2);
                        img.height = oh - oh * percent;
                        img.width = max;
                    }
                } else {
                    if (height = oh - max) {
                        percent = (height / oh).toFixed(2);
                        img.width = ow - ow * percent;
                        img.height = max;
                    }
                }
            }

            return this;*/

            /*
             *此处为修改后的图片原图不做处理
             */

            var width = 0, height = 0, percent, ow = img.width || oWidth, oh = img.height || oHeight;

            return this;
        },
        close: function ($img) {
            $img.css({
                top: ($img.parent().height() - $img.height()) / 2,
                left: ($img.parent().width()-$img.width())/2
            }).prev().on("click",function () {

                if ( $(this).parent().remove().hasClass("edui-image-upload-item") ) {
                    //显示图片计数-1
                    Upload.showCount--;
                    Upload.updateView();
                }

            });

            return this;
        },
        createImgBase64: function (img, file, $w) {
            if (browser.webkit) {
                //Chrome8+
                img.src = window.webkitURL.createObjectURL(file);
            } else if (browser.gecko) {
                //FF4+
                img.src = window.URL.createObjectURL(file);
            } else {
                //实例化file reader对象
                var reader = new FileReader();
                reader.onload = function (e) {
                    img.src = this.result;
                    $w.append(img);
                };
                reader.readAsDataURL(file);
            }
        },
        callback: function (editor, $w, url, state) {
        	//鲍建明
           if(state == 'error'){		
        	   		//错误信息转成JSON输出
        	   		var json = eval( "(" + url + ")" );
            		 currentDialog.showTip( json.message );
                     window.setTimeout( function () {

                         currentDialog.hideTip();

                     }, 3000 );
            		
            	}else{
            		
            		 //显示图片计数+1
                    Upload.showCount++;
                    //var $img = $("<img src='" + editor.options.imagePath + url + "' class='edui-image-pic' />"),
                    //冯唐路
                    //上面是原放法，下面是修改后的
                    var $img = $("<img src='" + window.IMAGE_DOMIAN + url + "' class='edui-image-pic' width='84' height='120' />"),
                        $item = $("<div class='edui-image-item edui-image-upload-item'><div class='edui-image-close'></div></div>").append($img);
                    //if ($("#edui-image-Jupload2", $w).length < 1) {
                        $("#edui-image-Jcontent", $w).find("#edui-image-Jupload2").before($item);

                        /*Upload.render("#edui-image-Jcontent", 2)
                            .config("#edui-image-Jupload2");*/
                    //} else {
                        /*$("#edui-image-Jupload2", $w).before($item).show();*/
                    //}

                    //添加Close方法
                    $img.on("load", function () {
                        Base.scale(this, 120);
                        Base.close($(this));
                    });
            		
            	}
            	
               

      

            Upload.toggleMask();

        }
    };

    /*
     * 本地上传
     * */
    var Upload = {
        showCount: 0,
        uploadTpl: '<div class="edui-image-upload%%" id="edui-image-Jupload%%" style="position: relative;">' +
            '<span class="edui-image-icon"></span>' +
            '<form class="edui-image-form" method="post" enctype="multipart/form-data" target="up">' +
            '<input style=\"filter: alpha(opacity=0);\" class="edui-image-file" id="edui-image-file" type="file" hidefocus name="upfile" accept="image/gif,image/jpeg,image/png,image/jpg,image/bmp"/>' +
            '</form>' +
            '<iframe name="up" style="display: none"></iframe>' +
            '</div>',
        init: function (editor, $w) {
            var me = this;

            me.editor = editor;
            me.dialog = $w;

            //me.render("#edui-image-Jlocal", 1);
            me.render("#edui-image-Jcontent", 2);
            me.config("#edui-image-Jupload2");
            me.submit();
            me.drag();

            $("#edui-image-Jupload1").hover(function () {
                $(".edui-image-icon", this).toggleClass("hover");
            });
            /*
             * 百度编辑器原来的代码
             if (!(UM.browser.ie && UM.browser.version <= 9)) {
             $("#edui-image-JdragTip", me.dialog).css("display", "block");
             }
             */
            //对IE不显示“点击上传您的文件”的处理 去掉判断
            $("#edui-image-JdragTip", me.dialog).css("display", "block");

            return me;
        },
        render: function (sel, t) {
            var me = this;

            $(sel, me.dialog).append($(me.uploadTpl.replace(/%%/g, t)));

            return me;
        },
        config: function (sel) {
            var me = this,
                url=me.editor.options.imageUrl;

            var newurl = "";

            url=window.UPLOAD_IMAGE_PATH + (window.UPLOAD_IMAGE_PATH.indexOf("?") == -1 ? "?" : "&") + "editorid="+me.editor.id;//初始form提交地址;

            if(url.indexOf("csrfToken")> 0){
                newurl = url;
            }else{
                var csrfToken = $("[name='csrfToken']");
                if(csrfToken.length > 0){
                    newurl = url.indexOf("?") > 0 ? (url+"&csrfToken="+csrfToken.val()): (url+"?csrfToken="+csrfToken.val());
                }else{
                    newurl = url;
                }
            }

            $("form", $(sel, me.dialog)).attr("action", newurl);

            return me;
        },
        submit: function (callback) {

            var me = this,
                input = $( '<input style="filter: alpha(opacity=0);" class="edui-image-file" type="file" hidefocus="" name="upfile" accept="image/gif,image/jpeg,image/png,image/jpg,image/bmp">'),
                input = input[0];

            //百度单独上传的方法，先注释掉
            /*$(me.dialog).delegate( ".edui-image-file", "change", function ( e ) {
                if ( !this.parentNode ) {
                    return;
                }

                $(this).parent()[0].submit();
                
                Upload.updateInput( input );
                me.toggleMask("Loading....");
                callback && callback();			//没用

            });*/

            //扩展多图上传的方法， 依赖于uoloadify
            seajs.use(['uploadify'], function() {
                var photoUpload = $('#edui-image-file').uploadify({
                    'formData': {
                        fSize: '7'
                    },
                    'swf': window.UPLOADIFY_SWF_URL,
                    'uploader': '/common/showImg',
                    'fileObjName': 'photo',
                    'method': 'post',
                    //'buttonClass': 'buttonClass',
                    'buttonText': ' ',
                    'width': 120,
                    'height': 120,
                    'queueID': 'some_file_queue',
                    //'uploadLimit': canUploadImg,
                    //'queueSizeLimit': 6,
                    'dataType': 'json',
                    //选择上传文件后调用
                    'onSelect': function (file) {
                        //console.log(file);
                    },
                    //返回一个错误，选择文件的时候触发
                    'onSelectError': function (file, errorCode, errorMsg) {
                        switch (errorCode) {
                            case -100:
                                alert("上传的文件数量已经超出系统限制的" + imgTotal + "个文件！");
                                break;
                            case -110:
                                alert("文件 [" + file.name + "] 大小超出系统限制的" + photoUpload.uploadify('settings', 'fileSizeLimit') + "大小！");
                                break;
                            case -120:
                                alert("文件 [" + file.name + "] 大小异常！");
                                break;
                            case -130:
                                alert("文件 [" + file.name + "] 类型不正确！");
                                break;
                        }
                    },
                    //检测FLASH失败调用
                    'onFallback': function () {
                        alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
                    },
                    'onUploadSuccess': function (file, data, response) {
                        //console.log(file);
                        //console.log(data);
                        //console.log(response);
                        data = $.parseJSON(data);
                        //console.log(data);
                        Base.callback(me.editor, me.dialog, data.message, "SUCCESS");
                    }
                });
            });
            //end

            return me;
        },
        //更新input
        updateInput: function ( inputField ) {

            $( ".edui-image-file", this.dialog ).each( function ( index, ele ) {

                ele.parentNode.replaceChild( inputField.cloneNode( true ), ele );

            } );

        },
        //更新上传框
        updateView: function () {

            if ( Upload.showCount !== 0 ) {
                return;
            }
            $(".edui-image-upload2", this.dialog).hide();
            $(".edui-image-dragTip", this.dialog).show();
            $(".edui-image-upload1", this.dialog).show();

        },
        drag: function () {
            var me = this;
            //做拽上传的支持
            if (!UM.browser.ie9below) {
                me.dialog.find('#edui-image-Jcontent').on('drop',function (e) {

                    //获取文件列表
                    var fileList = e.originalEvent.dataTransfer.files;
                    var img = document.createElement('img');
                    var hasImg = false;
                    $.each(fileList, function (i, f) {
                        if (/^image/.test(f.type)) {
                            //创建图片的base64
                            Base.createImgBase64(img, f, me.dialog);

                            var xhr = new XMLHttpRequest();
                           
                            xhr.open("post", window.UPLOAD_IMAGE_PATH + "?type=ajax", true);
                            xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
                            //模拟数据
                            var fd = new FormData();
                            fd.append(me.editor.getOpt('imageFieldName'), f);

                            xhr.send(fd);
                            xhr.addEventListener('load', function (e) {
                                Base.callback(me.editor, me.dialog, e.target.response, "SUCCESS");
                                /**
                                 * 判断拖拽引起的response错误代码的处理
                                 */
                                
                                if (i == fileList.length - 1) {
                                    $(img).remove()
                                }
                            });
                            hasImg = true;
                        }
                    });
                    if (hasImg) {
                        e.preventDefault();
                        me.toggleMask("Loading....");
                    }

                }).on('dragover', function (e) {
                        e.preventDefault();
                    });
            }
        },
        toggleMask: function (html) {
            var me = this;
            //电脑图像还显示的问题
            var $mask = $("#edui-image-Jmask", me.dialog);
            if (html) {
                /*
                * 百度编辑器原来的代码
                if (!(UM.browser.ie && UM.browser.version <= 9)) {
                    $("#edui-image-JdragTip", me.dialog).css( "display", "none" );
                }*/
                //对IE不显示“点击上传您的文件”的处理
                $("#edui-image-Jupload1", me.dialog).css( "display", "none" );
                if(UM.browser.ie && UM.browser.version >= 9){
                    $("#edui-image-Jupload1", me.dialog).css( "display", "block" );
                }
                $("#edui-image-JdragTip", me.dialog).css( "display", "none" );

                $mask.addClass("active").html(html);
            } else {

                $mask.removeClass("active").html();

                if ( Upload.showCount > 0 ) {
                    return me;
                }
                /*
                 * 百度编辑器原来的代码
                 if (!(UM.browser.ie && UM.browser.version <= 9) ){
                 $("#edui-image-JdragTip", me.dialog).css("display", "block");
                 }
                 */
                //对IE不显示“点击上传您的文件”的处理
                $("#edui-image-JdragTip", me.dialog).css("display", "block");

                $("#edui-image-Jupload1", me.dialog).css( "display", "block" );
            }

            return me;
        }
    };

    /*
     * 网络图片
     * */
    var NetWork = {
        init: function (editor, $w) {
            var me = this;

            me.editor = editor;
            me.dialog = $w;

            me.initEvt();
        },
        initEvt: function () {
            var me = this,
                url,
                $ele = $("#edui-image-JsearchTxt", me.dialog);
            	//flag  = true;
            
            //$()
            

          /*  $("#edui-image-JsearchAdd", me.dialog).on("click", function () {
                url = Base.checkURL($ele.val());

                if (url) {

                    $("<img src='" + url + "' class='edui-image-pic' />").on("load", function () {



                        var $item = $("<div class='edui-image-item'><div class='edui-image-close'></div></div>").append(this);

                        $("#edui-image-JsearchRes", me.dialog).append($item);

                        Base.scale(this, 120);

                        $item.width($(this).width());

                        Base.close($(this));

                        $ele.val("");
                    });
                }
            })
                .hover(function () {
                    $(this).toggleClass("hover");
                });*/
        }
    };

    var $tab = null,
        currentDialog = null;

    UM.registerWidget('image', {
        tpl: "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=image_url%>image.css\">" +
            "<div id=\"edui-image-Jwrapper\" class=\"edui-image-wrapper\">" +
            "<ul class=\"edui-tab-nav\">" +
            "<li class=\"edui-tab-item active\"><a href=\"#edui-image-Jlocal\" name=\"this_img\" class=\"edui-tab-text\"><%=lang_tab_local%></a></li>" +
            //"<li  class=\"edui-tab-item\"><a href=\"#edui-image-JimgSearch\" name=\"search_img\" class=\"edui-tab-text\">"+staticHtml+"</a></li>" +
            "</ul>" +
            "<div class=\"edui-tab-content\">" +
            "<div id=\"edui-image-Jlocal\" class=\"edui-tab-pane active\">" +
            "<div class=\"edui-image-content\" id=\"edui-image-Jcontent\"></div>" +
            "<div class=\"edui-image-mask\" id=\"edui-image-Jmask\"></div>" +
            //"<div id=\"edui-image-JdragTip\" class=\"edui-image-dragTip\"><%=lang_input_dragTip%></div>" +
            "</div>" +
            "<div id=\"edui-image-JimgSearch\" class=\"edui-tab-pane\">" +
            "<div class=\"edui-image-searchBar\">" +
           /* "<table><tr><td><input class=\"edui-image-searchTxt\" id=\"edui-image-JsearchTxt\" type=\"text\"></td>" +
            "<td><div class=\"edui-image-searchAdd\" id=\"edui-image-JsearchAdd\"><%=lang_btn_add%></div></td></tr></table>" +*/
            "</div>" +
            "<div class=\"edui-image-searchRes\" id=\"edui-image-JsearchRes\"></div>" +
            "</div>" +
            "</div>" +
            "</div>",
            
            //#edui-image-JsearchRes .edui-image-pic
        initContent: function (editor, $dialog) {
            var lang = editor.getLang('image')["static"],
                opt = $.extend({}, lang, {
                    image_url: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + 'dialogs/image/'
                });

            Upload.showCount = 0;

            if (lang) {
                var html = $.parseTmpl(this.tpl, opt);
            }

            currentDialog = $dialog.edui();

            this.root().html(html);

        },
        initEvent: function (editor, $w) {
        	
        	
        	/**
        	 * 已经修改过了
        	 */
        	$tab = $.eduitab({selector: "#edui-image-Jwrapper"})
            .edui().on("beforeshow", function (e) {
            	if(flag){
            		$('.edui-image-searchBar').load("/brand/bdUpload");
            	}
            	//flag = false;
                e.stopPropagation();
            });
        	
        	
    /*    	
        	if(typeof showImgKu == 'function'){
        		*//**
            	 * 鲍建明
            	 * TAB切换
            	 *//*
            	$tab = $.eduitab({selector: $('#edui-image-Jwrapper')})
                .edui().on('beforeshow', function (e){
                	if(flag){
                		//showImgKu($('.edui-image-searchBar'));
                		$('.edui-image-searchBar').load("/brand/bdUpload");
                	}
                	flag = false;
            		e.stopPropagation();
            	});
        	}else {
        		$tab = $.eduitab({selector: "#edui-image-Jwrapper"})
                .edui().on("beforeshow", function (e) {
                    e.stopPropagation();
                });
        		
        		
        	}  */  
        	

            Upload.init(editor, $w);

            NetWork.init(editor, $w);
        },
        buttons: {
            'ok': {
                exec: function (editor, $w) {
                    var sel = "",
                        index = $tab.activate();
                    if (index == 0) {
                        sel = "#edui-image-Jcontent .edui-image-pic";
                        var list = Base.getAllPic(sel, $w, editor);
                    } else if (index == 1) {								//图库
                       // sel = "#edui-image-JsearchRes .edui-image-pic";
                    	/**
                    	 * 获取选中的图库信息
                    	 */
                        var list = Base.getImgKu();
                    }
                    if (index != -1) {
                        editor.execCommand('insertimage', list);
                    }
                }
            },
            'cancel': {}
        },
       
        
        
        width: 700,
        height: 408
    }, function (editor, $w, url, state) {
        Base.callback(editor, $w, url, state)
    })
})();





