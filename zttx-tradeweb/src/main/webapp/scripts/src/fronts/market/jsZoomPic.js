//图片展示3D轮换图片
$(function () {
    var positions = [
        {width: "450px",height: "450px",top: "25px",left: "362px"},
        {width: "225px",height: "225px",top: "140px",left: "305px"},
        {width: "225px",height: "225px",top: "140px",left: "645px"},
        {width: "150px",height: "150px",top: "175px",left: "230px"},
        {width: "150px",height: "150px",top: "175px",left: "795px"},
        {width: "100px",height: "100px",top: "200px",left: "175px"},
        {width: "100px",height: "100px",top: "200px",left: "900px"}
    ];
    var cssZindex=[
        {zIndex: 7},
        {zIndex: 6},
        {zIndex: 6},
        {zIndex: 5},
        {zIndex: 5},
        {zIndex: 4},
        {zIndex: 4}
    ];

    var _lis = $(".c-slide li")
        //.each(function (i, o) { $(o).data({ index: i }); })
        .click(function () {
            return;
            showImg($(_lis).index(this));
        });
    var _len = _lis.length;

    var _idxs = [];
    showImg(0);
    function showImg(idx) {
        getidxs(idx);
        for (var i in _idxs) {
            if (i >= positions.length) {
                $(_lis).eq(_idxs[i]).hide();
            }
            else {
                $(_lis).eq(_idxs[i]).stop().animate(positions[i], 500, function () {
                    $(this).show();
                }).css(cssZindex[i]).find("img").stop().animate({opacity:0.3});
                $(_lis).eq(_idxs[0]).find("img").stop().animate({opacity:1});
            }
        }
    }
    function getidxs(idx) {
        _idxs = [];
        var baseI = 0;
        for (var i = 0; i < _len; i++) {
            if (i % 2 == 0) {
                var r = idx + baseI;
                r = r >= _len ? Math.abs(_len - r) : r;
                _idxs.push(r);
                baseI++;
            }
            else {
                var r = idx - baseI;
                r = r < 0 ? _len + r : r;
                _idxs.push(r);
            }
        }
    }

    var clickTime = 0;//全局的点击增加值
    function clickSpace(){//点击两次之间时间间隔
        if(_len == 1){
            return;
        }
        var spaceTime = 618;
        clickTime++;
        var _timer = setTimeout(function(){
            clickTime = 0;
        },spaceTime);
        if(clickTime !== 1){
            clearTimeout(_timer);
        }
        return clickTime;
    }

    $("#c-prev").click(function () {
        var _thisNum = clickSpace();
        if(_thisNum == 1){
            showImg(_idxs[1]);
        }
    });

    $("#c-next").click(function () {
        var _thisNum = clickSpace();
        if(_thisNum == 1){
            if(_len == 2){
                showImg(_idxs[1]);
                return;
            }
            showImg(_idxs[2]);
        }
    });
    setTimeout(function(){
        var _wid,_hei,_top,_left,_tInd,_tTndex;//初次移上去的宽度和高度以及zIndex
        $(_lis).hover(function(){
            _wid = $(this).width();
            _hei = $(this).height();
            _top = $(this).css("top");
            _left = parseInt($(this).css("left"));
            _tInd = $(this).css("zIndex");
            _tTndex = $(_lis).index(this);
            if(_idxs[0]!=_tTndex){
                $(this).find("img").stop().animate({opacity:1});
                $(this).stop().animate({
                    width:300,
                    height:300,
                    top:100,
                    left:_left-60
                },200).css({zIndex:8});
            }
        },function(){
            /*var _wid = $(this).width();
             var _hei = $(this).height();*/
            if(_idxs[0]!=_tTndex){
                $(this).find("img").stop().animate({opacity:0.3});
                $(this).stop().animate({
                    width:_wid,
                    height:_hei,
                    top:_top,
                    left:_left
                }).css({zIndex:_tInd});
            }
        });
    },618);
});