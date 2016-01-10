seajs.use(['$', "template", "pagination", "moment"], function ($, Template, Pagination, moment) {

    Template.helper('$formatDate', function (millsec) {
        return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
    });

    var formObj = $("#mainForm");

    var renderPage = new Pagination({
        url: "/fronts/helpCate/data",
        elem: "#pagination",
        form: formObj,
        method: "post",
        handleData: function (data) {
            var html = Template.render('dataList', data);
            $("#dataPagination").html(html);
        }
    });

    //查询按钮
    $("#searchs").click(function () {
        renderPage.goTo(1);
    });

    var msg = {
        title: "提示",
        content: "你确定要删除吗？"
    };

    $(document).on("click", ".js-delete", function(){
        var _refrenceId = $(this).data("id");
        confirmDialog(msg, function(){
            $.post("/fronts/helpCate/delete", {refrenceId: _refrenceId}, function (retData) {
                if (retData.code == 126000) {
                    renderPage.goTo(renderPage.current);
                }else {
                    remind("error", "删除失败");
                }
            }, 'json');
        });

    });
});