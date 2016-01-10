define(function (require, exports, module) {

    /**
     *
     * @param config
     * @constructor
     *
     * currentPage: 当前页码
     * page: 一页显示几条
     * elem: 分页的HTML插入的元素，最好是一个ID，有唯一性，并且这个元素内不能同时插入两个分页,
     * beforeAjax: 在ajax之前显示一些东西
     * handleData: 操作数据
     * total: 总页数
     *
     */

    var Util = {
        parseForm: function (url) {
            var vars = {}, hash, i,
                urlParams = url.indexOf('?') > -1 ? url.split('?')[1] : url;
            var hashes = urlParams.split('&');
            for (i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars[hash[0]] = decodeURIComponent(hash[1]).replace(/\+/g, ' ');
            }
            return vars;
        }
    }

    var Pagination = function (config) {

        var default_config = {
            currentPage: 1,
            elem: "body",
            skin: "default",
            showTotal: true
        };

        this.config = $.extend(default_config, config);

        this.beforeAjax = this.config.beforeAjax;

        this.current = this.config.currentPage;

        this.pageClick = this.config.pageClick;

        this.data = this.config.data || {};

        this.method = this.config.method || "get";

        this.form = this.config.form;

        this.flag = true;

        this.skin = this.config.skin;

        this.showTotal = this.config.showTotal;

        this.pageSize = this.config.pageSize;

        this.loadingFn = this.config.loadingFn || function () {

                if ($("#loading-dialog").length == 0) {
                    $('<div id="loading-dialog" style="border: #bababa solid 1px;background: #fff;box-shadow: 0 0 3px rgba(0,0,0,.4);width: 174px;height: 48px;position: absolute;left: 50%;top: 50%;margin-left: -87px;margin-top: -24px;text-align: center;"> \
                    <img style="vertical-align: middle" src="' + window.IMAGE_DOMIAN + '/images/common/loading_dialog.gif" alt=""/> \
                    <span style="font: 16px/48px Arial;color: #0282cd;margin-left: 5px;display: inline-block;vertical-align: middle;">正在加载中…</span> \
                </div>').appendTo("body");
                } else {
                    $("#loading-dialog").show();
                }

            };

        this.init();

    }

    Pagination.prototype = {
        init: function () {

            if (typeof this.config.total != "undefined") {
                this.generalLinks(this.config.total);
            } else {
                this.render();
            }
        },
        render: function (flag) {

            var _this = this;

            $(".pagination", $(this.config.elem)).remove();

            this.loadingFn();

            if (this.beforeAjax && $.isFunction(this.beforeAjax)) {
                this.beforeAjax();
            }

            this.current = !!flag ? 1 : this.current;

            var data = $.extend({currentPage: this.current, _t: Math.random()}, this.data);

            if (this.form) {
                //fix: ie8下 value 取到placeholder 问题
                $("input[type='text']").each(function () {
                    var value = $(this).val();
                    var placeholder = $(this).attr("placeholder");
                    if (value == placeholder) {
                        $(this).val("");
                    }
                });
                var form_str = "";
                if ($(this.form)[0].tagName.toLowerCase() == "form") {
                    form_str = $(this.form).serialize();
                } else {
                    $("input[type='text'],input[type='hidden']", this.form).each(function (index) {
                        if (index > 0) {
                            form_str += "&";
                        }
                        form_str += $(this).attr("name") + "=" + $(this).val();
                    });
                }
                data = $.extend(data, Util.parseForm(form_str));
            }

            if (this.pageSize) {
                data = $.extend(data, {pageSize: this.pageSize});
            }

            $.ajax(this.config.url, {
                dataType: 'json',
                data: data,
                type: this.method,
                success: function (data) {

                    if ($("#loading-dialog").length > 0) {
                        $("#loading-dialog").hide();
                    }

                    //json字符串转化
                    if ($.type(data) === "string") {
                        data = $.parseJSON(data);
                    }

                    _this.generalLinks(data.page ? data.page.totalPage : data.totalPage);
                    _this.totalPage = data.page ? data.page.totalPage : data.totalPage;
                    _this.handleData(data);

                },
                error: function (data) {

                    if ($("#loading-dialog").length > 0) {
                        $("#loading-dialog").hide();
                    }

                }
            })
        },
        generalLinks: function (total) {

            if (total == undefined) return;

            if (total <= 1) return;

            var html = '<div class="pagination">';
            this.prev = this.current - 1;
            this.next = this.current + 1;

            if (this.current > 1) {
                var prev_text = this.skin == "default" ? "上一页" : "<";
                html += '<a class="prev" href="javascript:;">' + prev_text + '</a>';
            }

            if (total > 7) {

                if (this.current >= 5) {

                    html += '<a class="page" href="javascript:void(0);">1</a><span class="ellipsis">…</span>';
                    if (total - this.current > 4) {
                        var prv_prv = this.prev - 1;
                        var next_next = this.next + 1;
                        html += '<a class="page" href="javascript:;">' + prv_prv + '</a>';
                        html += '<a class="page" href="javascript:;">' + this.prev + '</a>';
                        html += '<a class="page current" href="javascript:;">' + this.current + '</a>';
                        html += '<a class="page" href="javascript:;">' + this.next + '</a>';
                        html += '<a class="page" href="javascript:;">' + next_next + '</a>';
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">' + total + '</a>';
                    } else {
                        for (var j = total - 5; j <= total; j++) {
                            if (j == this.current)
                                html += '<a class="page current" href="javascript:;">' + this.current + '</a>';
                            else
                                html += '<a class="page" href="javascript:;">' + j + '</a>';
                        }
                    }

                } else { //当前页在前5页时

                    if (this.current == 1) {
                        html += '<a class="page current" href="javascript:;">1</a>';
                        for (var j = 2; j < 7; j++) {
                            html += '<a class="page" href="javascript:;">' + j + '</a>';
                        }
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">' + total + '</a>';
                    } else { //不是第一页
                        for (var j = 1; j < 7; j++) {
                            if (j == this.current)
                                html += '<a class="page current" href="javascript:;">' + this.current + '</a>';
                            else
                                html += '<a class="page" href="javascript:;">' + j + '</a>';
                        }
                        html += '<span class="ellipsis">…</span><a class="page" href="javascript:;">' + total + '</a>';

                    }

                }

            } else {
                if (this.current == 1) {
                    html += '<a class="page current" href="javascript:;">1</a>';
                    for (var j = 2; j <= total; j++) {
                        html += '<a class="page" href="javascript:;">' + j + '</a>';
                    }
                } else {
                    for (var j = 1; j <= total; j++) {
                        if (j == this.current) {
                            html += '<a class="page current" href="javascript:;">' + this.current + '</a>';
                        }
                        else {
                            html += '<a class="page" href="javascript:;">' + j + '</a>';
                        }
                    }
                }
            }

            if (this.current != total && total > 1) {
                var next_text = this.skin == "default" ? "下一页" : ">";
                html += '<a class="next" href="javascript:;">' + next_text + '</a>';
            }

            if (this.showTotal) {
                html += '<span class="total">' + this.current + '/' + total + '页</span>';
            }

            html += '</div>';

            $(html).appendTo($(this.config.elem));

            this.clickEvent();
        },
        clickEvent: function () {
            var _this = this;

            if (_this.pageClick && $.isFunction(_this.pageClick)) {

                this.flag = false;

                _this._elemClick(false);

            } else {

                this.flag = true;

                _this._elemClick(true);

            }
        },
        _elemClick: function (flag) {

            var _this = this;
            var elem = $(this.config.elem);
            $(".pagination", elem).on("click", ".prev", function () {
                _this.current--;
                flag ? _this.render() : _this.pageClick(_this.current)
            });
            $(".pagination", elem).on("click", ".next", function () {
                _this.current++;
                flag ? _this.render() : _this.pageClick(_this.current)
            });
            $(".pagination", elem).on("click", ".page", function () {
                _this.current = parseInt($(this).html());
                flag ? _this.render() : _this.pageClick(_this.current)
            });

        },
        handleData: function (arr) {
            if (this.config.handleData && $.isFunction(this.config.handleData)) {
                this.config.handleData(arr);
            }
        },
        goTo: function (page) {
            this.current = page;
            this.flag ? this.render() : this.pageClick(this.current)
        },
        goNext: function () {
            this.current++;
            if (this.totalPage && this.current > this.totalPage) {
                alert("已经是最后一页了！");
                return;
            }
            this.flag ? this.render() : this.pageClick(this.current);
        },
        goPrev: function () {
            this.current--;
            if (this.current == 0) {
                alert("已经是第一页了！");
                return;
            }
            this.flag ? this.render() : this.pageClick(this.current);
        }
    }

    module.exports = Pagination;
});