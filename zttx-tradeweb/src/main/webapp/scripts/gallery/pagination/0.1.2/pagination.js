/*
    分页模块
    author: 张谱（675924798@qq.com / Henry_zp@163.com）
    适合场景：ajax，非ajax的分页都可以
    友情提示：非ajax的分页需要自己重新实现pageClick这个fn，在里面location.href跳转即可
    较1相比
    1、新增goFirst、goLast方法，另外实现上更为精简
    2、新增模板的依赖，给模板添加常用的过滤器（如常用的日期）
 */
define(function(require, exports, module){


    var template = require("template");

    var moment = require("moment");

    /*
        参数配置说明：
        elem: 分页的HTML插入的元素，最好是一个ID，有唯一性，并且这个元素内不能同时插入两个分页,
        url: ajax请求的地址
        total: 总页数
        totalPage: 同total，考虑到第一期没处理好，未来用totalPage
        totalNum: 总条数
        currentPage： 当前显示的是第几页
        pageSize: 可传可不传，传的话，后台必须要有对应的key，才能生效
        showSingle: 1页的时候，是否显示，默认为false
        showTotal： 是否显示 当前页码/总条数
        skin: 上一页、下一页的文字显示，之所以保留，为了向后兼容,默认为default
        preText: 上一页的文字显示
        nextText: 下一页的文字显示（特殊情况下才可能会用得到preText nextText）
        loadingFn：loading的函数，默认显示“图标加正在加载中...”，出现在page层中
        beforeAjax: 在ajax之前做一些操作，如显示正在加载中...
        handleData：如果ajax请求，那么执行完后的回调函数
        pageClick： 非ajax的点击事件
        form: 需要序列化的表单selector
        data: ajax时需要传递的data
        method: ajax时的type类型，默认为"get"
        isAjax: 是否走ajax，默认为true，该值可以不指定，只要有pageClick，就会对该值设置为false
        addFilter: function，给template添加过滤器
        tplId: 模板的ID，如果有模板ID，就直接进行渲染
        tplContain: 模板放置的selector，通常tplId和tplContain会同时存在

        new出Pagination对象后能调用的方法说明：

        render(boolean): 重新渲染页码，如进行搜索后，需要执行该方法，参数表示是否执行该方法后跳回第一页，不传的话，不会跳
        goTo(Number): 跳到指定页
        goPrev(): 上一页
        goNext(): 下一页
        goFirst(): 第一页
        goLast(): 最后一页

    */
    function Pagination(config){

        var default_config = {
            currentPage: 1,
            elem: "body",
            skin: "default",
            showSingle: false
        }

        var config = $.extend(default_config,config);

        for(var prop in config){
            this[prop] = config[prop];
        }

        this.totalPage = this.totalPage || this.total;

        this.current = this.currentPage;

        this.loadingFn = this.loadingFn ||  function(){

            if($("#loading-dialog").length == 0){
                $('<div id="loading-dialog" style="border: #bababa solid 1px;background: #fff;box-shadow: 0 0 3px rgba(0,0,0,.4);width: 174px;height: 48px;position: absolute;left: 50%;top: 50%;margin-left: -87px;margin-top: -24px;text-align: center;"> \
                    <img style="vertical-align: middle" src="' + window.IMAGE_DOMIAN ? window.IMAGE_DOMIAN : "" + '/images/common/loading_dialog.gif" alt=""/> \
                    <span style="font: 16px/48px Arial;color: #0282cd;margin-left: 5px;display: inline-block;vertical-align: middle;">正在加载中…</span> \
                </div>').appendTo("body");
            }else{
                $("#loading-dialog").show();
            }

        };

        this.init();

    }

    Pagination.prototype = {
        constructor: Pagination,
        init: function(){

            if(typeof this.totalPage != "undefined"){

                this.generalLinks(this.totalPage);

            }else{

                this._addFilter(template);

                this.render();
            }
        },
        _addFilter: function(template){
            //日期格式
            template.helper('$formatDateFn', function (millsec,format) {
                var format = format || "YYYY-MM-DD";
                return moment(millsec).format(format);
            });
            //图片拼接
            template.helper('$getImageDomainPathFn', function (url,width,height) {
                if(width && height){
                    return url+"_"+parseInt(width)+"x"+parseInt(height);
                }else{
                    return url;
                }
            });
        },
        render: function(flag){
            var _this = this;
            $(".pagination",$(this.elem)).remove();
            this.loadingFn();
            if(this.beforeAjax && $.isFunction(this.beforeAjax)){
                this.beforeAjax();
            }
            this.current = !!flag ? 1: this.current;
            var data = $.extend({currentPage: this.current, _t: Math.random()},this.data);
            if(this.form){
                var form_str = $(this.form).serialize();
                data = $.extend(data,this._parseForm(form_str));
            }
            if(this.pageSize){
                data = $.extend(data,{pageSize:this.pageSize});
            }

            var ajax_config = {
                url: this.url,
                dataType: 'json',
                data:  data,
                type: this.method,
                success: function (data){

                    if($("#loading-dialog").length > 0){
                        $("#loading-dialog").hide();
                    }

                    _this.generalLinks(data.page ? data.page.totalPage : data.totalPage);
                    _this.totalPage = data.page ? data.page.totalPage : data.totalPage;
                    if(_this.addFilter && $.isFunction(_this.addFilter)){
                        _this.addFilter(template);
                    }

                    if(_this.tplId){
                        var html = template.render(_this.tplId,data);
                        $(_this.tplContain).html(html);
                    }

                    if(_this.handleData && $.isFunction(_this.handleData)){
                        _this.handleData(data,template);
                    }
                },
                error: function(){
                    if($("#loading-dialog").length > 0){
                        $("#loading-dialog").hide();
                    }
                }
            }
            $.ajax(ajax_config);
        },
        generalLinks: function(totalPage){

        	if(totalPage == undefined){
        		return;
        	}
        	
            if(!this.showSingle){
                if(totalPage <= 1)return;
            }

            var html = '<div class="pagination">';
            this.prev = this.current - 1;
            this.next = this.current + 1;

            if(this.current > 1){

                var prev_text = this.prevText ? this.prevText : (this.skin == "default" ? "上一页" : "<");

                html += '<a class="prev" href="javascript:;">'+prev_text+'</a>';

            }

            if (totalPage > 7) {

                if (this.current >= 5) {

                    html += '<a class="page" href="javascript:void(0);">1</a><span class="ellipsis">…</span>';
                    if (totalPage - this.current > 4) {
                        var prv_prv = this.prev - 1;
                        var next_next = this.next + 1;
                        html += '<a class="page" href="javascript:;">'+prv_prv+'</a>';
                        html += '<a class="page" href="javascript:;">'+this.prev+'</a>';
                        html += '<a class="page current" href="javascript:;">'+this.current+'</a>';
                        html += '<a class="page" href="javascript:;">'+this.next+'</a>';
                        html += '<a class="page" href="javascript:;">'+next_next+'</a>';
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">'+totalPage+'</a>';
                    } else {
                        for(var j = totalPage - 5; j <= totalPage; j++) {
                            if (j == this.current)
                                html += '<a class="page current" href="javascript:;">'+this.current+'</a>';
                            else
                                html += '<a class="page" href="javascript:;">'+j+'</a>';
                        }
                    }

                } else { //当前页在前5页时

                    if (this.current == 1) {
                        html += '<a class="page current" href="javascript:;">1</a>';
                        for(var j = 2; j < 7; j ++) {
                            html += '<a class="page" href="javascript:;">'+j+'</a>';
                        }
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">'+totalPage+'</a>';
                    } else { //不是第一页
                        for(var j = 1; j < 7; j++) {
                            if (j == this.current)
                                html += '<a class="page current" href="javascript:;">'+this.current+'</a>';
                            else
                                html += '<a class="page" href="javascript:;">'+j+'</a>';
                        }
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">'+totalPage+'</a>';

                    }

                }

            }else{
                if (this.current == 1) {
                    html += '<a class="page current" href="javascript:;">1</a>';
                    for(var j = 2; j <= totalPage; j ++) {
                        html += '<a class="page" href="javascript:;">'+j+'</a>';
                    }
                } else {
                    for(var j = 1; j <= totalPage; j ++) {
                        if (j == this.current){
                            html += '<a class="page current" href="javascript:;">'+this.current+'</a>';
                        }
                        else{
                            html += '<a class="page" href="javascript:;">'+j+'</a>';
                        }
                    }
                }
            }

            if (this.current != totalPage && totalPage > 1){

                var next_text = this.nextText ? this.nextText : (this.skin == "default" ? "下一页" : ">");

                html += '<a class="next" href="javascript:;">'+next_text+'</a>';

            }

            if(this.showTotal){
                html += '<span class="total">'+this.current+'/'+totalPage+'页</span>';
            }

            html += '</div>';

            $(html).appendTo($(this.elem));

            this.clickEvent();
        },
        clickEvent: function(){
            if(this.pageClick && $.isFunction(this.pageClick)){
                this.isAjax = false;
            }else{
                this.isAjax = true;
            }
            this._elemClick();
        },
        _elemClick: function(){
            var _this = this;
            var elem = $(this.elem);
            $(".pagination",elem).on("click",".prev",function(){
                _this.current --;
                _this.isAjax ? _this.render() : _this.pageClick(_this.current)
            });
            $(".pagination",elem).on("click",".next",function(){
                _this.current ++;
                _this.isAjax ? _this.render() : _this.pageClick(_this.current)
            });
            $(".pagination",elem).on("click",".page",function(){
                _this.current = parseInt($(this).html());
                _this.isAjax ? _this.render() : _this.pageClick(_this.current)
            });
        },
        _parseForm: function(str){
            var vars = {}, hash, i,
                urlParams = str.indexOf('?') > -1 ? str.split('?')[1] : str
                ;
            var hashes = urlParams.split('&');
            for(i = 0; i < hashes.length; i++){
                hash = hashes[i].split('=');
                vars[hash[0]] = decodeURIComponent(hash[1]).replace(/\+/g, ' ');
            }
            return vars;
        },
        goTo: function(page){
            this.current = page;
            this.isAjax ? this.render() : this.pageClick(this.current)
        },
        goPrev: function(){
            this.current --;
            if(this.current == 0){
                if(window.ZTTX && ZTTX.remind){
                    ZTTX.remind("error","已经是第一页了！");
                }else {
                    alert("已经是第一页了！");
                }
                return;
            }
            this.isAjax ? this.render() : this.pageClick(this.current);
        },
        goNext: function(){
            this.current ++;
            if(this.totalPage && this.current > this.totalPage){
                if(window.ZTTX && ZTTX.remind){
                    ZTTX.remind("error","已经是最后一页了！");
                }else{
                    alert("已经是最后一页了！");
                }
                return;
            }
            this.isAjax ? this.render() : this.pageClick(this.current);
        },
        goFirst: function(){
            this.goTo(1);
        },
        goLast: function(){
            this.goTo(this.totalPage);
        }
    }

    module.exports = Pagination;

});