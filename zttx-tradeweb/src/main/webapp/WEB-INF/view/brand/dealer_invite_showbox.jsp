<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<style>
    .tip_text {
        color: #cc0000;
    }
</style>
<div style="display: none"><!--弹窗内容-->
    <div class="js-invite-show info-invite-showbox">
        <form:form action="" method="post" id="showboxForm">
            <input type="hidden" name="dealerId" id="dealerId"/>

            <div class="ui-head">
                <h3>邀请加盟</h3>
            </div>
            <div class="info-invite-select mt15">
                <label for="">品牌选择</label>

                <div class="inline-block">

                    <select class="js-select invite-select" name="brandsId" id="brandsId" style="width:150px;">
                        <option value="">请选择品牌</option>
                            <c:forEach items="${brandsInfos}" var="brandsInfo">
                                <option value="${brandsInfo.refrenceId}">${brandsInfo.brandsName }</option>
                            </c:forEach>
                    </select>
                </div>
            </div>
            <div id="joinDiv" class="joindealer">
                <div class="info-invite-textarea">
                    <textarea placeholder="诚邀您的加盟，携手共创未来！" class="ui-textarea" id='inviteText'
                              name="inviteText"></textarea>
                </div>
                <div class="ta-c operate">
                    <a href="javascript:sendInvite();" class="simple_button confirm_btn">确认</a>
                    <a href="javascript:;" class="simple_button cancel_btn ml5">取消</a>
                </div>
            </div>
            <div style="display: none" id="auditDiv" class="joindealer">
                <div class="mt10 tip_text">此终端商已向您提交加盟申请</div>
                <div class="info-invite-textarea">
                    <textarea placeholder="请输入拒绝理由！" style="margin-top: 10px;" class="ui-textarea" id='auditMark'
                              name="auditMark"></textarea>
                </div>
                <div class="ta-c operate">
                    <a href="javascript:agreeJoin();" class="simple_button confirm_btn">同意</a>
                    <a href="javascript:rejectJoin();" class="simple_button ml5">拒绝</a>
                    <a href="javascript:;" class="simple_button cancel_btn ml5">取消</a>
                </div>
            </div>
            <div style="display: none" id="sendDiv" class="joindealer mt15">
                <div class="tip_text ta-c">您已向此终端商发送邀请</div>
                <div class="ta-c operate">
                    <a style="vertical-align: middle" href="javascript:;" class="simple_button cancel_btn ml5">取消</a>
                </div>
            </div>
            <div style="display: none" id="coopDiv" class="joindealer mt15">
                <div class="tip_text ta-c">正在和此终端商合作中</div>
                <div class="ta-c operate">
                    <a style="vertical-align: middle" href="javascript:;" class="simple_button cancel_btn ml5">取消</a>
                </div>
            </div>
            <div style="display: none" id="endDiv" class="joindealer mt15">
                <div class="tip_text ta-c">已终止的品牌，不能邀请</div>
                <div class="ta-c operate">
                    <a style="vertical-align: middle" href="javascript:;" class="simple_button cancel_btn ml5">取消</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script>
    var invitEditDiv;
    $("#showboxForm #brandsId").on("change", function () {
        if ($(this).val() == "") {
            $(".joindealer").hide();
            $("#joinDiv").show();
            return;
        }
        $.post("${ctx}/brand/dealer/listDealerGroom/selBrands", $("#showboxForm").serialize(), function (data) {
            if (data.code == zttx.SUCCESS) {
                $(".joindealer").hide();
                if (undefined == data.object)
                    $("#joinDiv").show();
                else {
                    if (data.object.applyState == 0)
                        $("#auditDiv").show();
                    else if (data.object.applyState == 1)
                        $("#coopDiv").show();
                    else if (data.object.applyState == 3)
                        $("#sendDiv").show();
                    else if (data.object.applyState == 4||data.object.applyState == 6)
                        $("#endDiv").show();
                    else
                        $("#joinDiv").show();
                }
            } else {
                remind("error", data.message);
            }
        }, "json");
    });
    function sendInvite() {
        var brandsId = $.trim($("#brandsId").val());
        if (brandsId == "") {
            remind("error", "请选择一个品牌");
            return;
        }
        var inviteText = $.trim($("#inviteText").val());
        if (inviteText == "") {
            $("#inviteText").val("诚邀您的加盟，携手共创未来！");
        }
        $.post("${ctx}/brand/brandInvite/invite/join", $("#showboxForm").serialize(), function (data) {
            if (data.code == zttx.SUCCESS) {
                remind("success", "您的邀请已成功发送");
                invitEditDiv.hide();
                inviteJoinCallback(data.object);
            } else if (data.code == 116022) {
//                alert( data.message);
                remind("error", data.message);
                invitEditDiv.hide();
            } else {
//                alert( data.message);
                remind("error", data.message);
            }
        }, "json");
    }
    ;
    function agreeJoin() {
        var brandsId = $.trim($("#brandsId").val());
        if (brandsId == "") {
            remind("error", "请选择一个品牌");
            return;
        }
        confirmDialog("是否允许终端商加盟？", function () {
            $.post("${ctx}/brand/dealer/agree", $("#showboxForm").serialize(), function (data) {
                if (data.code == zttx.SUCCESS) {
                    remind("success", "终端商已成功加盟");
                    invitEditDiv.hide();
                } else {
                    remind("error", data.message);
                }
            }, "json");
        });
    }
    ;
    function rejectJoin() {
        var brandsId = $.trim($("#brandsId").val());
        if (brandsId == "") {
            remind("error", "请选择一个品牌");
            return;
        }
        var auditMark = $.trim($("#auditMark").val());
        if (auditMark == "") {
            remind("error", "请输入拒绝信息");
            return;
        }
        $.post("${ctx}/brand/dealer/reject", $("#showboxForm").serialize(), function (data) {
            if (data.code == zttx.SUCCESS) {
                remind("success", "终端商加盟申请已拒绝");
                invitEditDiv.hide();
            } else {
                remind("error", data.message);
            }
        }, "json");
    }
    function inviteJoinCallback() {
    }
    ;
</script>