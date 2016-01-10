var arr =[];//全局价格缓存数组
var publishGoods = {
    init:function(datacate,dataAfter){
        this.baseValidator();//验证
        this.countListen();//产品标题字数监听
        //this.itemCheck();//初始化选中状态
        this.itemClick();//单个点击时切换状态
        this.checkAll();//全选
        //this.initOtherData();//假定数据
        this.setPrice();//批量设为本价格
        this.selectClass(datacate,dataAfter);//商品分类改变ajax请求
        this.deleteImg();//依次删除“产品主图”图片
        this.deleteColorImg();//颜色分类图片
    },
    baseValidator:function(){
        /*baseFormValidator({
            selector:".publishForm",
            isAjax: true,
            editor:{
                id:"umeditor",
                width:680,
                height:300
            },
            addElemFn:function(Core,Validator){
                Validator.addRule('pTitle',function(options){
                    var v = options.element.val();
                    var len = getCharLength(v);
                    if(len<1||len>60){
                        return false;
                    }else{
                        return true;
                    }
                },'标题必须在1-60个字符内');
                Core.addItem({
                    element: '.publishTitle',
                    required: true,
                    rule: 'pTitle',
                    errormessageRequired: '标题必须在1-60个字符内'
                });
            },
            beforeSubmitFn:function(){
                //前台校验通过后，此处发请求
                //console.log("校验通过");
				$.ajax({
					type : "post",
					url : "/dealer/weshop/product/saveOrUpdate",
					dataType : "json",
					data : $('.publishForm').serialize(),
					success : function(data) {
						if(data.code == 121000){
                            remind("success","保存成功");
                        }else{
                            remind("error",data.message);
                        }
					}
				});
            }
        });*/
        createEditor("umeditor",680,300);
        seajs.use(["validator"],function(Validator){
            //console.dir(Validator);
            var validator = new Validator({
                element:'.publishForm',
                failSilently:true
            });
            validator.set("autoSubmit",false);
            Validator.addRule('pTitle',function(options){
                var v = options.element.val();
                var len = getCharLength(v);
                if(len<1||len>60){
                    return false;
                }else{
                    return true;
                }
            },'标题必须在1-60个字符内');
            validator.addItem({
                element:'input[name=productTitle]',
                required:true,
                rule:'pTitle',
                errormessageRequired: '标题必须在1-60个字符内'
            }).addItem({
                element:'input[name=costPrice]',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'input[name=marketPrice]',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'input[name=salePrice]',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'input[name=productNo]',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'.select-category',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'.select-class',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'input[name=brandsName]',
                required:true,
                rule:'minlength{"min":0}'
            }).addItem({
                element:'input[name=productStore]',
                required:true,
                rule:'minlength{"min":0}'
            });
            validator.on('formValidated',function(error, message, elem){
                if(!error){
                    var UMcontent = UM.getEditor('umeditor').getContent();
                    if($(".choose_result  #show_img li").length !== 0){
                        if(UMcontent !== ""){
                            $.ajax({
                                type : "post",
                                url : "/dealer/weshop/product/saveOrUpdate",
                                dataType : "json",
                                data : $('.publishForm').serialize(),
                                success : function(data) {
                                    if(data.code == 121000){
                                        remind("success","保存成功");
                                    }else{
                                        remind("error",data.message);
                                    }
                                }
                            });
                        }else{
                            remind("error","请填写产品描述");
                        }
                    }else{
                        remind("error","请上传产品主图");
                    }
                }
            })
        });

    },
    countListen:function(){
        $(".publishTitle").on("keyup paste copy",function(){
            var maxCount = 60;//最大数
            var len = getCharLength(this.value);
            if (len > maxCount) {
                // 输入的数字大于规定的最大数时，不计算
            } else {
                $("#count").html(maxCount - len);
            }
        });
    },
    itemCheck:function(){
        $(".js-itemsCheck").each(function(){
            var _parents = $(this).parents(".item-classes-items");
            if($(this).prop("checked")){
                _parents.addClass("edit");
            }else{
                _parents.removeClass("edit");
            }
        });
    },
    itemClick:function(){
        var _self = this;
        $("#color-other").on("click",".js-itemsCheck",function(){
        	_self.globleArr();
            _self.itemCheck();
            _self.initOtherData();
        });
    },
    checkAll:function(){
        var _self = this;
        $("#color-other").on("click",".js-checkall",function(){
            var _parents = $(this).parents(".item-classes");
            _parents.find(".js-itemsCheck").prop("checked",this.checked);
            _self.globleArr();
            _self.itemCheck();
            _self.initOtherData();
        });
    },
    initOtherData:function(){
    	function initData(item){//得到需要的数据
            var _init_data = [];
            $(item).each(function(){
                var _parents = $(this).parents(".item-classes-items");       
                if($(this).prop("checked")){
                	var arr ={};
                    var _text = _parents.find(".itemattr").text();
                    var _id = _parents.find(".refrenceId").val();
                    var _color = _parents.find(".colorarea").data("color");
                    var _p = _parents.find(".colorarea").data("price");
                    if(_p == "" || _p == undefined){
                        _p = 0;
                    }
                    arr.v = _text;
                    arr.vid = _id;
                    if(_color == "" || _color == undefined){
                        arr.color = 0;
                    }else{
                        arr.color = _color;
                    }
                    arr.p = _p;
                    _init_data.push(arr);
                }
            });
            return _init_data;
        }
        
        var _data_color = initData("#colorClass .js-itemsCheck");//颜色
        if($("#otherClass").length > 0){
            var _data_other = initData("#otherClass .js-itemsCheck");//其
            var _len_color = _data_color.length;
            var _len_other = _data_other.length;

            var _data = '[';
            $.each(_data_color,function(i,color){
                _data += '{"v":"'+color.v+'","vid":"'+color.vid+'","val":[';
                $.each(_data_other,function(ind,other){
                    _data += '{"p":"'+other.p+'","v":"'+other.v+'","vid":"'+other.vid+'","cid":"'+color.vid+'"}';
                    if(ind !== (_len_other-1)){
                        _data += ',';
                    }
                });
                _data += ']}';
                if(i !== (_len_color-1)){
                    _data += ',';
                }
            });
            _data += ']';
            //console.log(_data);
            var _data_mix = eval('('+_data+')');
            //console.log(_data_color);//假定的颜色数据
            //console.log(_data_other);//假定的其他数据
            //console.log(_data_mix);//假定颜色和其他数据的组合
            this.initItems(_data_color);
            this.initOtherItems(_data_mix);
        }else{
            //this.initOtherItems(_data_color);
            this.initAloneColor(_data_color);
        }
        this.setArr(arr);
        /*
        后台输出数据格式
        [
            {
                "a":"293","v":"肤色","vid":"04648C670A934245B81811F630D45C63,
                    "val":[
                        {"a":"294","p":"21222","v":"36","vid":"3E4A2723EB9F4129BCD7E92693C09517"},
                        {"a":"294","p":"21222","v":"37","vid":"8B94326449FB4D92BBF1C18088E85CFF"},
                        {"a":"294","p":"21221","v":"40","vid":"0E281F53C4D24B61941FB545C5D24F6B"},
                        {"a":"294","p":"21222","v":"47","vid":"2191F0E52D5F4095A25541AAA79DD0BF"}
                    ]
            },
            {
                "a":"293","v":"酒红色","vid":"BE8DC01438A84E48A171899179BD5E18",
                    "val":[
                        {"a":"294","p":"21222","v":"36","vid":"3E4A2723EB9F4129BCD7E92693C09517"},
                        {"a":"294","p":"21222","v":"37","vid":"8B94326449FB4D92BBF1C18088E85CFF"},
                        {"a":"294","p":"21222","v":"40","vid":"0E281F53C4D24B61941FB545C5D24F6B"},
                        {"a":"294","p":"21222","v":"47","vid":"2191F0E52D5F4095A25541AAA79DD0BF"}
                    ]
            }
        ]*/

    },
    initItems:function(data){//颜色模块初始化数据
        /*seajs.use(["template"],function(template){
            var html = template.render("colortable", data);    
            $(".js-colortable tbody").empty().append(html);//数据拼接模版以后先清空再插入
            if($(".js-colortable tbody").text() == ""){
                $(".js-colortable").hide();
            }else{
                $(".js-colortable").show();
            }
        });*/
    	//console.log(data);
    	var _html;
    	$.each(data,function(i,t){
    		_html += '<tr>';
    		_html += '<td>';
    		if(t.color.toString().length > 6){
    			_html += '<div class="inline-block colorarea" data-color="'+t.color+'">';
    			_html += '<img src="'+t.color+'" width="13" height="13">';
                _html += '</div>';
    		}else if(t.color.toString().length < 6 || t.color.toString().length > 1){
    			_html += '<div class="inline-block colorarea" style="background:#'+t.color+';" data-color="'+t.color+'"></div>';
    		}else if(t.color.toString().length == 1){
    			
    		}
    		_html += '<span>'+t.v+'</span>';
    		_html += '</td><td>';
    		_html += '<div class="file_wrap inline-block">';
    		_html += '<a class="ui-button ui-button-lwhite">文件上传</a>';
    		_html += '<input type="file" value="文件上传" name="file" class="input_file" id="colorImg_'+t.vid+'" onchange="publishGoods.uploadImage(\'colorImg_'+t.vid+'\');">';
    		_html += '</div>';
    		_html += '<a href="javascript:;" class="link colorImg-delete" style="margin-left:10px;">删除</a>';
    		_html += '</td></tr>';
    	});
    	$(".js-colortable tbody").empty().append(_html);//数据拼接模版以后先清空再插入
        if($(".js-colortable tbody").text() == ""){
            $(".js-colortable").hide();
        }else{
            $(".js-colortable").show();
        }
    },
    initOtherItems:function(data){//颜色模块初始化数据
        //console.log(data);
        //console.log(data[0].val);
        seajs.use(["template"],function(template){
            var html = template.render("othertable", data);
            //console.log(html);
            $(".js-othertable").remove();
            $(".js-classtip").before(html);//数据拼接模版以后先清空再插入
            var _checkSize = $("#otherClass .js-itemsCheck:checked").size();
            if($(".js-colortable tbody").length > 0){
                if($(".js-colortable tbody").text() == "" || _checkSize == 0){
                    $(".js-othertable").hide();
                    $(".js-classtip").show();
                }else{
                    $(".js-othertable").show();
                    $(".js-classtip").hide();
                }
            }else if($(".js-colortable tbody").length == 0){
                if($(".js-othertable tbody").text() == "" || _checkSize == 0){
                    $(".js-othertable").hide();
                    $(".js-classtip").show();
                }else{
                    $(".js-othertable").show();
                    $(".js-classtip").hide();
                }
            }
            $(".js-number").isPrice(false);
        });
    },
    initAloneColor:function(data){
        //数据格式：{"a":"294","p":"21222","v":"36","vid":"3E4A2723EB9F4129BCD7E92693C09517"}
        //console.log(data);
        seajs.use(["template"],function(template){
            var html = template.render("coloralonetable", data);
            var _checkSize = $("#colorClass .js-itemsCheck:checked").size();
            $(".js-othertable").remove();
            $(".js-classtip").before(html);//数据拼接模版以后先清空再插入
            if($(".js-othertable tbody").text() == "" || _checkSize == 0){
                $(".js-othertable").hide();
                $(".js-classtip").show();
            }else{
                $(".js-othertable").show();
                $(".js-classtip").hide();
            }
        });
    },
    setPrice:function(){
    	var _self = this;
        $("#color-other").on("change",".priceinput",function(){
        	_self.globleArr();
        });
        $("#color-other").on("click",".js-setprice",function(){
            var _parents = $(this).parents("tr");
            var _price = _parents.find(".priceinput").val();
            $(".js-othertable .priceinput").val(_price);
            _self.globleArr();
        });
    },
    globleArr:function(){//价格缓存数组
    	$(".js-othertable input[id^=priceIuput_]").each(function(){
            var _data = {};
            var _id = $(this).attr("id");
            //console.log(_id);
            var _price = $(this).val();
            _data.id = _id;
            _data.p = _price;
            arr.push(_data);
        });
        //console.log(arr);
    },
    setArr:function(arr){
        var _arr = arr;
        //console.log(_arr);
        $.each(_arr,function(i,t){
            $("#"+ t.id).val(t.p);
        });
    },
    selectClass:function(datacate,dataAfter){
    	var _self = this;
        function appendCate(data,cate){//主、子分类拼接函数
            $.each(data,function(i,t){
                var _html = '<option value="'+ t.id +'">'+ t.item +'</option>';
                $(_html).appendTo(cate);
            });
        }
        appendCate(datacate,".select-category");//主分类
        $(".publishForm").on("change",".select-category",function(){
            var _i = $(this).find("option:selected").index() - 1;
            //console.log(_i);
            $(".select-class").html('<option value="">请选择</option>');
            if(_i >= 0){
                appendCate(datacate[_i].childs,".select-class");//子分类
            }
        });

        var CateNumber = $("#selectCateNumber").text();//子分类value
        var Cate = CateNumber.substring(0,3) + "000000";//主分类value
        if(!isNaN(CateNumber) && CateNumber.length == 9){
        	var _i = $(".select-category option[value="+Cate+"]").attr("selected",true).index() - 1;
        	appendCate(datacate[_i].childs,".select-class");
        	$(".select-class option[value="+CateNumber+"]").attr("selected",true);
        	var a = $('.publishForm input[name="refrenceId"]').val();
        	$('#color-other').load('/dealer/weshop/product/attr', {dealNo:CateNumber, productId : a},function(){
        		_self.itemCheck();
        		_self.initOtherData();
                if($("#otherClass").length == 0){
                    _self.initAloneColor(dataAfter);
                }else{
                    _self.initOtherItems(dataAfter);
                }
        	});
    	}
        $(".publishForm").on("change",".select-class",function(){
        	var val = $(this).val();
            $('#color-other').load('/dealer/weshop/product/attr', {dealNo:val});
        });
    },
    uploadImage : function (uploadId){
    	var _self = this;
    	seajs.use(["$","ajaxFileUpload"],function($){
			$.ajaxFileUpload({
    			url: '/dealer/weshop/upload',
    			secureuri: false,
                fileElementId: uploadId,
                dataType: 'json',
                success: function(data)
                {
                	//发送ajaxFileUpload请求后，上传元素的change事件会解绑，所以再绑定一下
                	$(".publishForm").on('change','#' + uploadId, function(){
                		_self.uploadImage(uploadId);
            		});
                	if(data.code != 121000){
    					console.log(data.code);
                	}else{
                		if(uploadId == "uploadProductImg"){
                			showProductImg(data.rows[0].path, data.rows[0].origName);
                		}else{
                			colorImg(uploadId,data.rows[0].path);
                		}
                	}
                }
    		});
	    });
    	function showProductImg(url,name){
            var html = '<li class="item"><img src="' + window.IMAGE_DOMIAN + url + '" style="width:100%;height:100%;" />'+'<a class="close_btn iconfont" href="javascript:;">&#xe612;</a>';
            html += '<input type="hidden" name="images" value='+url+' />';
            html += '<input type="hidden" name="imagesName" value='+name+' /></li>';
            var aLi = $('#show_img');
            var _liLength = $('#show_img li').length;
            if(_liLength < 6){
                aLi.append(html);
            }else{
                remind("error","产品主图最多六张，请先删除再上传");
            }
    	}
    	function colorImg(uploadId,url){
    		var parentsTr = $("#"+uploadId).parents("tr");
    		if(url.toString().length>6){
    			parentsTr.find(".colorarea").html('<img src="'+window.IMAGE_DOMIAN + url+'" width="13" height="13"/>');
    			var arrId = uploadId.split("_");
    			$("input[value="+arrId[1]+"]").next().val(window.IMAGE_DOMIAN + url);
    		}else{
    			parentsTr.find(".colorarea").css("background",url);
    		}
    	}
    },
    deleteImg:function(){
    	$("#show_img").on("click",".close_btn",function(){
    		var _parent = $(this).parents(".item");
    		_parent.find("img").attr("src","");
    		_parent.remove();
    	})
    },
    deleteColorImg:function(){
    	$("#color-other").on("click",".colorImg-delete",function(){
    		var _parent = $(this).parents("tr");
    		confirmDialog("确认要删除颜色吗？",function(){
    			_parent.find(".colorarea img").remove();
    			var uploadId = _parent.find(".input_file").attr("id");
    			var arrId = uploadId.split("_");
    			$("input[value="+arrId[1]+"]").next().val("");
    		});
    	});
    }
}