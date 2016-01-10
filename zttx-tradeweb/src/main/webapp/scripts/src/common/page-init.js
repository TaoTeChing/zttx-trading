var PageControl = {
	/**
	 * Ajax 分页初始化
	 * 
	 * @path:"${ctx}/brand/card/list/"+currentPage+"/"+pageSize
	 * @template_page:"#template_page" 翻页模板
	 * @page_datas:"#page_datas" 显示翻页容器
	 * @template:#template 数据模板
	 * @datas:#datas 数据容器 6
	 * @rowRenderer:function(index,el,data)
	 * @pageLoaded:function(data);//页面加载完成事件
	 * @pageSize: 页面大小
	 * @currentPage: 当前页
	 * @totalPage 总页数
	 */
	init : function(_params) {
		if (_params != null) {
			$.extend($PAGE.params, _params);
		}
		this.goPage(_params.currentPage, _params.pageSize);
	},
	params : {
		/**
		 *  get : /list/{currentPage}/{pageSize}
		 *  post : 
		 * */
		method:"get",
		form:"#pageForm",
		template_page : "#template_page",// 翻页按钮模板
		page_datas : "#page_datas",// 显示翻页按钮容器
		template : "#template",// 行数据模板
		scriptTemplate : null,
		datas : "#datas",// 数据容器
		pageSize : 5,// 页面大小
		currentPage : 1,// 当前页
		totalPage : 0
	// 总页数
	},
	refrech : function() {// 刷新当前页数据
		this.goPage($PAGE.params.currentPage, $PAGE.params.pageSize);
	},
	goPrevious : function() {// 上一页
		if (this.params.currentPage > 1) {
			this.goPage(this.params.currentPage - 1, this.params.pageSize);
		}
	},
	goNext : function() {// 下一页
		if (this.params.currentPage < this.params.totalPage) {
			this.goPage(this.params.currentPage + 1, this.params.pageSize);
		}
	},
	goFirst : function() {// 第一页
		this.goPage(1, this.params.pageSize);
	},
	goLast : function() {// 最后一页
		if (this.params.totalPage
				&& this.params.currentPage != this.params.totalPage) {
			this.goPage(this.params.totalPage, this.params.pageSize);
		}
	},
	goPage : function(currentPage, pageSize) {
		var _path = this.params.path ;
		if(this.params.method == "post")
		{
			this.postSend(_path,currentPage,pageSize);
		}
		else if(this.params.method == "get")
		{
			_path= _path + "/" + currentPage + "/" + pageSize;
			this.getSend(_path);
		}
	},
	getSend : function(url) {
		var _this = this;
		$.getJSON(url, function(data) {
			var obj = data;
			if (data.code == 0) {
				_this.showDatas(data);
				_this.showPages(data);
				if (_this.params.pageLoaded) {
					_this.params.pageLoaded(data);
				}
			} else {
				alert("查询失败！");
			}
		});
	},
	postSend : function(url,currentPage,pageSize) {
		var _this = this;
		var _data = $(_this.params.form).serialize();
		_data += "&currentPage="+currentPage;
		_data += "&pageSize="+pageSize;
		$.post(url,_data,function(data){
			if (data.code == 0) {
				_this.showDatas(data);
				_this.showPages(data);
				/*
				if (_this.params.pageLoaded) {
					_this.params.pageLoaded(data);
				}
				 */
			} else {
				alert("查询失败！");
			}
		},"json");
	},
	showPages : function(obj) {
		$("[id=readyPage]").remove();
		var _this = this;
		var pages = _this.getPages(obj);
		var item;
		for (i = 0; i < pages.length; i++) {
			item = pages[i];
			if(item.type == "S"){
				but = $("<span class=\"ellipsis\">…</span>");
			}else{
				//but = $(_this.params.template_page).clone();
				but = $("<a href=\"#\"></a>");
				but.text(item.label);
			}
			if (item.display == true) {
				but.on("click", item.index, function(event) {
					_this.goPage(event.data, _this.params.pageSize);
				});
			}
			if (item.type == "N") {
				but.addClass("page");
			} else if (item.type == "Z") {
				but.addClass("prev");
			}
			if (obj.currentPage == item.index && item.type == "N") {
				but.addClass("current");
			}
			but.attr("id", "readyPage");// 改变绑定好数据的行的id
			but.appendTo(_this.params.page_datas);
		}
		$(_this.params.template_page).css("display", "none");
		$("[id=readyPage]").show();
	},
	showDatas : function(obj) {
		//scriptTemplate
		if(this.params.scriptTemplate!=null&&this.params.scriptTemplate.length>0)
		{
			this._scriptTemplateShowDatas(obj);
		}
		else
		{
			this._templateShowDatas(obj);
		}
	},
	_templateShowDatas:function(obj){
		$("[id=ready]").remove();
		var _this = this;
		if( obj.rows != null)
		{
			for ( var i = 0; i < obj.rows.length; i++) {
				var row = $(_this.params.template).clone();
				if (_this.params.rowRenderer) {
					_this.params.rowRenderer(i, row, obj.rows[i]);
				}
				row.attr("id", "ready");// 改变绑定好数据的行的id
				row.appendTo(_this.params.datas);
			}
		}
		$(_this.params.template).css("display", "none");
		_this.params.currentPage = obj.currentPage;
		_this.params.totalPage = obj.totalPage;
		$("[id=ready]").show();
		
		if (_this.params.pageLoaded) {
			_this.params.pageLoaded(obj);
		}
	},
	_scriptTemplateShowDatas:function(obj){
		var _this = this;
		$(_this.params.datas).empty();
		_this.params.currentPage = obj.currentPage;
		_this.params.totalPage = obj.totalPage;
		seajs.use(['template'],function(template){
			var _html = "";
			if( obj.rows != null)
			{
				for ( var i = 0; i < obj.rows.length; i++) {
					var row = $(template.render(_this.params.scriptTemplate,obj.rows[i]));
					if (_this.params.rowRenderer) {
						_this.params.rowRenderer(i, row, obj.rows[i]);
					}
					row.appendTo(_this.params.datas);
				}
			}
			if (_this.params.pageLoaded) {
				_this.params.pageLoaded(obj);
			}
        });
		
	},
	
	clearDatas:function(){
		if(this.params.scriptTemplate!=null&&this.params.scriptTemplate.length>0)
		{
			$(this.params.datas).empty();
		}
		else
		{
			$("[id=ready]").remove();
		}
		$("[id=readyPage]").remove();
	},
	getPages : function(page) {
		var result = [];
		var item;
		if (page.hasPrevious) {
			item = {
				index : page.currentPage - 1,
				display : true,
				label : "上一页",
				type : "Z"
			};
		} else {
			item = {
				index : 1,
				display : false,
				label : "上一页",
				type : "Z"
			};
		}
		result.push(item);
		if (page.currentPage == 1) {
			item = {
				index : 1,
				display : false,
				label : "1",
				type : "N"
			};
		} else {
			item = {
				index : 1,
				display : true,
				label : "1",
				type : "N"
			};
		}
		result.push(item);
		var iLeftNum = 0;
		var iRightNum = 0;
		if (page.totalPage > 2) {
			if (page.currentPage <= 5 && page.currentPage >= page.totalPage - 4) {
				iLeftNum = 2;
				iRightNum = (page.totalPage > 7 ? page.totalPage - 2
						: page.totalPage - 1);
			} else if (page.currentPage <= 5
					&& page.currentPage <= page.totalPage - 4) {
				iLeftNum = 2;
				iRightNum = (page.totalPage <= 7 ? page.totalPage - 1 : 6);
			} else if (page.currentPage >= page.totalPage - 4) {
					//6   >= 9-4
				iLeftNum = ((page.totalPage - 5) == 1 ? '2'
						: page.totalPage - 5);
				iRightNum = (page.totalPage - 1);
			} else {
				if (page.totalPage <= 7) {
					iLeftNum = 2;
					iRightNum = (page.totalPage - 1);
				} else {
					if (page.currentPage - 3 > 1) {
						iLeftNum = page.currentPage - 2;
						
					} else {
						iLeftNum = 2;
					}
					if (page.currentPage + 3 < page.totalPage) {
						iRightNum = page.currentPage + 2;
					} else {
						iRightNum = page.totalPage - 1;
					}
				}
			}
			if (page.currentPage > 5 && page.totalPage > 7) {
				item = {
					index : i,
					display : false,
					label : "...",
					type : "S"
				};
				result.push(item);
			}
			// alert("iLeftNum:"+iLeftNum+",iRightNum:"+iRightNum);
			for (i = iLeftNum; i <= iRightNum; i++) {
				if (page.currentPage == i) {
					item = {
						index : i,
						display : false,
						label : i,
						type : "N"
					};
				} else {
					item = {
						index : i,
						display : true,
						label : i,
						type : "N"
					};
				}
				result.push(item);
			}
			if ((page.currentPage < page.totalPage - 4 && page.totalPage > 7)
					|| ((page.currentPage <= 5 && page.currentPage >= page.totalPage - 4)
							&& (page.totalPage > 7))) {
				item = {
					index : i,
					display : false,
					label : "...",
					type : "S"
				};
				result.push(item);
			}
		}
		if (page.totalPage > 1) {
			if (page.currentPage == page.totalPage) {
				item = {
					index : page.totalPage,
					display : false,
					label : page.totalPage,
					type : "N"
				};
			} else {
				item = {
					index : page.totalPage,
					display : true,
					label : page.totalPage,
					type : "N"
				};
			}
			result.push(item);
		}

		if (page.hasNext) {
			item = {
				index : page.currentPage + 1,
				display : true,
				label : "下一页",
				type : "Z"
			};
		} else {
			item = {
				index : page.currentPage,
				display : false,
				label : "下一页",
				type : "Z"
			};
		}
		result.push(item);
		return result;
	}
};
window.PAGE = window.$PAGE = PageControl;