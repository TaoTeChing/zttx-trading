/*var csrfToken = $('#_csrf-form').find('input[name="csrfToken"]').val();
$(document).on('ajaxSend', function(elem, xhr, s) {
    if (s.type.toUpperCase() == 'POST') {
        xhr.setRequestHeader('csrfToken', csrfToken);
    }
});*/
//全选
$(".checkallbox").on("click",".js-chkall",function () {
    $(this).parents(".checkallbox").next().find(".js_chk").prop("checked",this.checked);
});
var mycate = [];
function setMyCate(brandsId){//设置店内类目
    var catalogSuper = $("#initCatalogSuper").val();
    $.ajax({
        type: "POST",
        url: "/brand/product/catalogTree",
        data: {"brandesId": brandsId},
        dataType: "json",
        cache: false,
        success: function (result) {
            var html = "<option value=''>全部分类</option>";
            var superSelectedName = "全部分类";
            $.each(result,function(index,data){
                if(catalogSuper==data.id){
                    html +="<option selected='selected' value='"+data.id+"'>"+data.name+"</option>";
                    superSelectedName = data.name;
                }else{
                    html +="<option value='"+data.id+"'>"+data.name+"</option>";
                }
                mycate[data.id]=data.children;
            });
            $("#catalogSuper").html(html);
            $("#catalogSuper_div span").text(superSelectedName);
            $("#catalogSuper").change();
            /*$("#catalogSub").html("");
             $("#catalogSub_div span").text("");*/
        }
    })
}
$(document).on("change", "#catalogSuper", function () {
    $("#catalogSub").html("");
    $("#catalogSub_div span").html("");
    var catalogSub = $("#initCatalogSub").val();
    var subData = mycate[this.value];
    if (subData && subData.length != 0) {
        var html = "";
        var subSelectedName = "";
        $.each(subData, function (index, data) {
            if(catalogSub==data.id){
                html += "<option selected='selected' value='" + data.id + "'>" + data.name + "</option>";
                subSelectedName = data.name;
            }else{
                html += "<option value='" + data.id + "'>" + data.name + "</option>";
            }

        });
        $("#catalogSub").html(html);
        if(subSelectedName==""){
            subSelectedName = $("#catalogSub option").eq(0).text();
        }
        $("#catalogSub_div span").text(subSelectedName);
    }
});
$(document).on("change", "#brandsId", function () {
    setMyCate(this.value);
});
$(function(){
	setMyCate($("#brandsId").val());
});
function setAjaxFormData(ids){
    if(!ids){
        ids = new Array();
        $("#list-table input.js_chk:checked").each(function(){
            ids.push($(this).attr("id"));
        });
        if(ids.length<=0){
            remind("error","请选择一个产品。");
            return false;
        }
    }else{
        ids = [ids];
    }
    $("#ajaxForm input[name='productIds']").remove();
    $.each(ids,function(index,data){
        $("#ajaxForm").append('<input type="hidden" name="productIds" value="' + data + '">');
    });
    return true;
}
function delProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否删除",function(){
        $.ajax({
            url:"/brand/product/ajax_delete",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(zttx.SUCCESS==json.code){
                    remind("success","操作成功！");
                    refresh();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}
function returnProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否还原",function(){
        $.ajax({
            url:"/brand/product/ajax_return",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","还原产品成功");
                    refresh();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function batchDelete(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否删除",function(){
        $.ajax({
            url:"/brand/product/batch_delete",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","删除产品成功");
                    refresh();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function realDeleteProduct(ids){
	var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否永久删除",function(){
        $.ajax({
            url:"/brand/product/ajax_delete_real",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","删除产品成功");
                    refresh();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}
function groomProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否设置为推荐产品",function(){
        $.ajax({
            url:"/brand/product/ajax_groom",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(zttx.SUCCESS==json.code){
                    remind("success","推荐产品成功");
                    refresh();
                }else
                    remind("error",json.message);
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}
function ungroomProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否取消推荐产品",function(){
        $.ajax({
            url:"/brand/product/ajax_ungroom",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","取消推荐成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}
function downProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否下架所选产品",function(){
        $.ajax({
            url:"/brand/product/ajax_down",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(zttx.SUCCESS==json.code){
                    remind("success","下架成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}
function upProduct(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否上架所选产品",function(){
        $.ajax({
            url:"/brand/product/ajax_up",
            type:"post",
            data:$("#ajaxForm").serialize(),
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","上架成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function upTabShow(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否前台显示所选产品",function(){
        $.ajax({
            url:"/brand/product/setShow",
            type:"post",
            data:$("#ajaxForm").serialize()+"&show=true&upTab=true",
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","设置前台显示成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function upTabUnshow(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否前台不显示所选产品",function(){
        $.ajax({
            url:"/brand/product/setShow",
            type:"post",
            data:$("#ajaxForm").serialize()+"&show=false&upTab=true",
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","设置前台不显示成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function downTabShow(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否前台显示所选产品",function(){
        $.ajax({
            url:"/brand/product/setShow",
            type:"post",
            data:$("#ajaxForm").serialize()+"&show=true&upTab=false",
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","设置前台显示成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function downTabUnshow(ids){
    var mark = setAjaxFormData(ids);
    if(!mark){
        return;
    }
    confirmDialog("是否前台不显示所选产品",function(){
        $.ajax({
            url:"/brand/product/setShow",
            type:"post",
            data:$("#ajaxForm").serialize()+"&show=false&upTab=false",
            dataType: "json",
            success:function(json){
                if(126000==json.code){
                    remind("success","设置前台不显示成功");
                }else
                    remind("error",json.message);
                refresh();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                remind("error",errorThrown);
            }
        });
    });
}

function refresh(){
    /*var currentPage = $("a.page.current");
    if(currentPage.size()!=0){
        currentPage.click();
    }else{
        $("#search-form").submit();
    }*/
	page.render();
}
function tabSearch(type){
    $("#tabSearch").val(type);
    $("#search-form").submit();
}
function exportExcel(){
    var url = "/brand/product/exportExcel?"+$("#search-form").serialize();
    window.open(url);
}