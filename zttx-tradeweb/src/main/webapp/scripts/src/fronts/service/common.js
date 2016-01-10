//搜索类型选择框 操作
var select_t = $(".search-select");
$(select_t).hover(function () {
    $(this).find("span").next().show();
}, function () {
    $(this).find("span").next().hide();
}).find("dl").find("dd").click(function () {
    var typeid = $(this).attr("value");
    $(select_t).find("input[type=hidden]").val(typeid).next().text($(this).text());

    $(".search-box").toggleClass("search-box-select",typeid==2);

    $(this).parent().hide();
});

var option = {
    "0" : "品牌",
    "1" : "商品"
}
var _val = $("#key_type").val();
$(".search-select span").text( option[_val] );