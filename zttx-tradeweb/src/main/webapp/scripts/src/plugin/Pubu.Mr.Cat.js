/// <reference path="jquery-1.10.2.min.js" />

function PubuLiu(options, callback) {
    var defaults = {
        trigger: null,     //子元素名
        autoWidth: true,     //是否自适应宽度
        width: 200,          // 列宽
        margin: 2,           // 间距
        column: 4,           // 列数
        childItem: "li",     //子元素名
        autoFill: true,        //自动铺底
        boxClass: ""         //box附加样式
    };
    //合并
    var opts = $.extend(defaults, options);
    var $this = opts.trigger;
    //容器强制样式
    $($this).css({ position: "relative" });

    //获得容器宽度
    var _fw = $($this).outerWidth();
    //获得列宽度
    var _itemWidth = opts.autoWidth ? _fw / opts.column - opts.margin : opts.width;
    //获得间距
    var _margin = opts.margin;
    //获得列数
    var _column = opts.column;
    //获得子元素名
    var _childItem = opts.childItem;
    //获得子元素名
    var _boxClass = opts.boxClass;
    //获得所有子元素并赋值宽度样式
    var _lis = $($this).find(_childItem).addClass(_boxClass).css({ position: "absolute", width: _itemWidth }).toArray();
    //起点位置
    var _left = 0;
    //定义列高合计数组
    var _top = new Array();
    for (var i = 0; i < _column; i++) _top.push(0);

    //计算位置
    for (var i in _lis) {
        //console.log(_top);
        //获得内容最短的列的对象Index
        var min = getMin();

        //求宽,计算左空宽度
        _left = (_itemWidth + _margin) * min;
        //求高,赋值前一个高度,并增加新的高度
        //console.log($(_lis[i]).css({ left: _left, top: _top[min] }).outerHeight());
        _top[min] += $(_lis[i]).css({ left: _left, top: _top[min] }).innerHeight() + _margin;

    }

    ////必然会有一排是最高的
    //var maxheight = getMax();
    //for (var i = 0; i < _column - 1; i++) {
    //    var minid = getMin();
    //    //console.log(_top[minid]); //最少高度
    //    var cha = maxheight - _top[minid];//铺底高度
    //    _left = (_itemWidth + _margin) * minid;
    //    $($this).append($("<" + _childItem + ">").css({ left: _left, top: _top[minid], position: "absolute", width: _itemWidth, height: cha }));
    //    _top[minid] += cha + _margin;
    //}


    //获得最小高度列的index
    function getMin() {
        var ret = 0;
        var temp = _top[0];
        for (var i in _top) {
            if (_top[i] < temp) {
                temp = _top[i];
                ret = i;
            }
        }
        return ret;
    }
    //获取最大高度
    function getMax() {
        var ret = 0;
        var temp = _top[0];
        for (var i in _top) {
            if (_top[i] > temp) {
                temp = _top[i];
                ret = temp;
            }
        }
        return ret;
    }
    //console.log(getMax());
    //给调用的元素设置高度
    $($this).css({ height: getMax() });
    return this;
}

PubuLiu.prototype.addItems = function (items) {
    //console.log(items);
}