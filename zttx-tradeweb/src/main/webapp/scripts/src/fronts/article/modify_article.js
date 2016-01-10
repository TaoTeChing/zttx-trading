seajs.use(['$', 'widget', "dialog", "ajaxFileUpload", "umeditor", "umeditor_config", "umdeitor_style", "ztree"], function ($, Widget, Validator, Dialog) {

    var um = UM.getEditor('articleText', {
        initialFrameWidth: 676,
        initialFrameHeight: 500
    });

    baseFormValidator({
        selector: '#modify_help_form',
        isAjax: true,
        beforeSubmitFn: function(){
            $.post("/fronts/articleInfo/save", $("#modify_help_form").serialize(), function (retData) {
                if (retData.code == 126000) {
                    window.location.href = "/fronts/articleInfo/list";
                }else {
                    remind("error", "编辑失败");
                }
            }, 'json');
        }
    });

    var zTree;
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "refrenceId",
                pIdKey: "parentId",
                rootPId: ""
            },
            key: {
                name: "cateName"
            }

        },
        callback: {
            onClick: onClick
        }
    };

    $("#selected_cate").click(function(){
        showMenu();
    });

    function onClick(e, treeId, treeNode) {
        $("#selected_cate").val(treeNode.cateName).trigger("blur");
        $("#cateId").val(treeNode.refrenceId);
    }

    function showMenu() {
        $("#tree_menu_con").show();
        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        $("#tree_menu_con").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "selected_cate" || event.target.id == "tree_menu_con" || $(event.target).parents("#tree_menu_con").length>0)) {
            hideMenu();
        }
    }

    $.post("/fronts/articleCate/tree", null, function (retData) {

        if (retData.code == 126000) {
            $.fn.zTree.init($("#tree"), setting, retData.rows);
             zTree = $.fn.zTree.getZTreeObj("tree");

            if($("#cateId").val() !== ""){
                //编辑的时候初始化类别的值
                var id = $("#cateId").val();
                var getTreeName = zTree.getNodeByParam("refrenceId", id).cateName;
                $("#selected_cate").val(getTreeName).trigger("blur");
            }
        }
    }, 'json');
});
