<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-品牌管理-下载资料管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/brand_manage.css" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/>
<div class="main layout">
<jsp:include page="/common/menuInfo/sidemenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
            <span class="arrow">&gt;</span>
            <span class="current">下载资料管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <div class="ui_tab main_ui_tab">
            <ul class="js_tab_box ui_tab_items clearfix">
                <li class="ui_tab_item">
                    <a href="${ctx}/brand/brandDocument">资料上传</a>
                </li>
                <li class="ui_tab_item">
                    <a href="${ctx}/brand/brandDocument/list">资料管理</a>
                </li>
                <li class="ui_tab_item current">
                    <a href="${ctx}/brand/brandDocument/sort">分类管理</a>
                </li>
            </ul>
        </div>
        <div class="js_tab_con_box tab_con_box">
            <div class="tab_con" style="display: block;">
            <div class="tips">
                <i class="v2-icon-explain"></i>
                说明：针对资料类型，创建分类，有效管理资料
            </div>
            <form:form action="111" method="post" id="doccateForm" name="doccateForm" data-widget="validator" autocomplete="off" >
               
                    <div class="classfication">
                        <div class="ui-form-item" style="margin-bottom: 15px;">
                            <label for="">
                                品牌选择：<span class="ui-form-required">*</span>
                            </label>
                            <div class="inline-block">
                                <select required="" name="brandsId" id="brandsId"  class="ui-select js_select" data-errormessage-required="请选择品牌">
                                     <c:forEach items="${brandesInfoList}" var="info">
                        				<option value="${info.refrenceId}" <c:if test="${info.refrenceId==brandsId}">selected</c:if>>${info.brandsName}</option>
                        			</c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="newclass">
                            <i class="iconfont">&#xe611;</i>
                            <span class="newclasspan">新建分类</span>
                        </div>
                        <a class="deletepic bluefont inline-block" href="javascript:;">批量删除</a>
                        <table class="table">
                            <colgroup>
                                <col width="50">
                                <col width="510">
                                <col width="202">
                                <col width="202">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>
                                    <input type="checkbox" id="checkAll" />
                                </th>
                                <th>分类名称</th>
                                <th>移动</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${brandDoccates}" var="brandDoccate" varStatus="status">
                            	<tr class="maintree" id="tr${catalog.id}">
                                	<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value="${brandDoccate.refrenceId}"/>
                            		<td>
                                     <input type="checkbox"   class="ui-checkbox newclasschk mainclasschk" name="chkRefrenceId"  value="${brandDoccate.refrenceId}"/>
                                </td>
                                <td>
                                    <div class="ui-form-item">
                                        <input autocomplete="off" id="cateNameAry${status.index}" required data-display="分类名称" type="text" class="newclasstext" name="cateNameAry"  value="${brandDoccate.cateName}" >
                                    </div>
                                </td>
                                <td>
                                   
                                    	<div class="movebox">
                                            
                                            <span title="上移" class="moveup js_moveup" data="${brandDoccate.refrenceId}"></span>
               								<span title="下移" class="movedown js_movedown"  data="${brandDoccate.refrenceId}"></span>
                                        </div>
                                        
                                </td>
                                <td>
                                    <a href="javascript:;" class="link">删除</a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- 灰色的，不可点 -->
                        <button type="button" class="ui_button ui_button_sgray disabled_button">保存分类</button>
                        <!-- 黄色的，可点 -->
                        <button type="submit" class="ui_button ui_button_sblue enable_button" id="sortSave">保存分类</button>
                    </div>
             
                </form:form>
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="bottom.jsp" %>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script type="text/html" id="sort_tpl">
    <tr class="maintree">
		<input type="hidden" name="refrenceIdAry" id="refrenceIdAry" value=""/>
        <td>
            <input type="checkbox" class="ui-checkbox">
        </td>
        <td>
            <div class="ui-form-item">
				<input required data-display="分类名称" type="text" class="newclasstext" name="cateNameAry" />
            </div>
        </td>
        <td>
            <div class="movebox">
                <span title="上移" class="moveup js_moveup"></span>
                <span title="下移" class="movedown js_movedown"></span>
            </div>
        </td>
        <td>
            <a href="javascript:;" class="link">删除</a>
        </td>
    </tr>
</script>
<script>
	var down_sort = {
        init: function(){
            this.checkBox();
            this.formValidate(); //操作表单
            this.editInput();    //操作input
            this.num = $(".maintree").length+1;        //当前共有几条数据+1的数值
            this.exchangeSort();     //交换排序
        },
        checkBox: function(){
            $("#checkAll").on("click",function(){
                var checked = $(this).prop("checked");
                $(".table tbody input[type='checkbox']").prop("checked",checked);
            })
        },
        editInput: function(){
            var _this = this;
            $(".classfication").on("change",".table .newclasstext",function(){
                _this.changeButton();
            })
            $(".classfication .js_select").on("change",function(){
                _this.changeButton();
            })
        },
        changeButton: function(){
            if($(".disabled_button")[0]) {
                $(".disabled_button").remove();
                $(".enable_button").show();
            }
        },
        formValidate: function(){
            var _this = this;
            seajs.use(['validator', 'widget'],function(Validator, Widget){
                Widget.autoRenderAll();
                var Core = Validator.query("#doccateForm");
                Core.set("autoSubmit", false);
                //新增
                $(".newclass").on("click",function(){
                    $('.table tbody').append($("#sort_tpl").html());
                    $('.table tr:last').find(".newclasstext").attr("id","cateNameAry"+_this.num);
                    _this.changeButton();
                    Core.addItem({
                        element: '#cateNameAry'+_this.num,
                        required: true,
                        display: "分类名称"
                    })
                    _this.num ++;
                });
                
                //删除
                $(".table").on("click",".link",function(){
                	var confirm = window.confirm("是否删除该分类？");
                    if(!confirm){
                        return;
                    }
                    _this.changeButton();
                    var op = $(this).parents("tr");
                    $(this).parents("tr").remove();
                    Core.removeItem('#cateNameAry'+_this.num);
                });

                //批量删除
                $(".deletepic").on("click",function(){

                    //未选择分类的时候
                    if($(".mainclasschk:checked").length == 0){
                        alert("请选择要删除的分类");
                        return;
                    }

                    var confirm = window.confirm("是否批量删除这些分类？");
                    if(!confirm){
                        return;
                    }
                    $("input:checkbox:checked").not("#checkAll").each(function(){
                                _this.changeButton();
                                var op = $(this).parents("tr");

                                var name = op.find(".newclasstext").attr("name");
                                $(this).parents("tr").remove();
                                Core.removeItem('[name="'+name+'"]');

                            }
                    );
                });
                
                $('#brandsId').change(function (e) {
	            	var id = $(this).val();
	            	window.location.href = "${ctx}/brand/brandDocument/sort?brandsId=" + id;
       		 	});
                
                Core.on('formValidated', function(error, message, elem) {
                    if(!error){
                    	$("#sortSave").attr("disabled", true);
                        _this.saveCatalog();

                    }
                });
            })
        },
        exchangeSort: function(){
            var _this = this;
            $(document).on("click",".js_moveup",function(){
            	_this.changeButton();
                var $tr = $(this).parents("tr");
                if(!$tr.prev()[0]){
                    alert("已经是第一个，无法上移");
                }else{
                    $tr.prev().before($tr);
                }
            })
            $(document).on("click",".js_movedown",function(){
            	_this.changeButton();
                var $tr = $(this).parents("tr");
                if(!$tr.next()[0]){
                    alert("已经是最后一个，无法下移");
                }else{
                    $tr.next().after($tr);
                }
            })
        },
        saveCatalog: function(){
        	 $.ajax({
	   			url:"${ctx}/brand/brandDocument/sort/save",
	   			type:"post",
	   			data:$("#doccateForm").serialize(),
	   			dataType: "json",
	   			success:function(json){
                    $("#sortSave").attr("disabled", false);
	   				if(zttx.SUCCESS==json.code){
	   					remind("success","分类保存成功",function(){
	   						location.href = location.href;
	   					});
	   				}else
						remind("error",json.message);
	   			},
				error:function(XMLHttpRequest, textStatus, errorThrown){
                    $("#sortSave").attr("disabled", false);
					remind("error",errorThrown);
				}
   			}); 
        }
    }
   

    down_sort.init();
    
    

</script>
</body>
</html>