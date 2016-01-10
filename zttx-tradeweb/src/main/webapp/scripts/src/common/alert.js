/**
 * Created by 逊 on 2014/9/28.
 */
(function(){

    $("head").append("<style>.ts-mask{position:fixed;top:0;left:0;right:0;bottom:0;z-index: 999;text-align: center}.ts-alert{ display: inline-block;background-color:rgba(0,0,0,.7);border-radius: 5px;padding:10px;margin-top: 200px;min-width:200px;max-width:250px; }.ts-alert p{color:#fff;word-break: break-all}.ts-alert-ok,.ts-alert-cancel {display: inline-block;width:40%;height:30px;line-height:30px;background-color: #fff;border-radius: 5px;color:#666;font-size:14px;}.ts-alert-ok:active,.ts-alert-cancel:active{background-color: #eee;} </style>");

    window.alert = function(text)
    {
        var html = $("<div class='ts-mask'><div class='ts-alert'><p>{{text}}</p><div><a class='ts-alert-ok'>确定</a></div></div></div>".replace("{{text}}",text));
        $("body").append(html);
        $(html).find(".ts-alert-ok").bind("click",function(){
            html.remove();
        });
    }

    window.remind = function(text)
    {
        var html = $("<div class='ts-mask'><div class='ts-alert'><p>{{text}}</p></div></div>".replace("{{text}}",text));
        $("body").append(html);
        setTimeout(function(){
            html.remove();
        },3000);


    }

    window.confirm = function(text,callback){
        var html = $("<div class='ts-mask'><div class='ts-alert'><p>{{text}}</p><div><a class='ts-alert-ok'>确定</a> <a class='ts-alert-cancel'>取消</a></div></div></div>".replace("{{text}}",text));
        $("body").append(html);
        $(html).find(".ts-alert-ok").bind("click",function(){
            html.remove();
            callback(true);
        });
        $(html).find(".ts-alert-cancel").bind("click",function(){
            html.remove();
        });
    }
    window.loading = function(text,callback){
        var html = $("<div class='ts-mask'><div class='ts-alert'><p><img src='/images/common/loading.gif'></p><p>{{text}}</p></div></div>".replace("{{text}}",text));
        $("body").append(html);
        callback(html);
    }

})();