/**
 * Created by yefeng on 2014/4/29.
 */
/*倒计时跳转到首页*/
var s= 0;
setInterval(function(){
    s=s+1;
    if(s>5){
        location.href="/";
        return;
    }
    o = document.getElementById("second");
    o.innerHTML=(5-s);
},1000);
