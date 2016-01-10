<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<script id="applyDialog" type="text/html">
    <div class="ui-head">
        <h3>填写申请</h3>
    </div>
    <form:form id="fillAppleForm" data-widget="validator" action="">
        <div class="ui-form-item">
            <label class="ui-label">联系人：<span class="ui-form-required">*</span></label>
            <input type="text" class="ui-input" name="userName" required data-display="用户名"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">性别：<span class="ui-form-required">*</span></label>
            <div class="radio-field inline-block">
                <label>
                    <input type="radio" value="1" name="userGender" class="ui-radio" checked required data-errormessage-required="请选择您的性别"/>先生
                </label>
                <label>
                    <input type="radio" value="0" name="userGender" class="ui-radio"/>女士
                </label>
            </div>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">邮箱：</label>
            <input type="text" class="ui-input" name="userMail" data-rule="email" data-display="电子邮箱"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">手机号码：<span class="ui-form-required">*</span></label>
            <input type="text" class="ui-input" name="userMobile" required data-rule="mobile" data-display="手机号码"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">联系电话：</label>
            <input type="text" class="ui-input" name="userTelphone"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">传真号码：</label>
            <input type="text" class="ui-input" name="userFax"/>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">地址：<span class="ui-form-required">*</span></label>
            <div class="radio-field inline-block">
                <select required data-errormessage="请选择省" id="p" class="ui_select" name="province"></select>
                <select data-errormessage="请选择市" id="c" class="ui_select" name="city"></select>
                <select data-errormessage="请选择区" id="a" class="ui_select" name="county"></select>
            </div>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">留言：</label>
            <textarea name="joinMark" class="ui-textarea" style="width: 335px;height: 70px;"></textarea>
        </div>
        <div class="ui-form-item">
            <label class="ui-label">加盟类型：<span class="ui-form-required">*</span></label>
            <div class="radio-field inline-block">
                <label>
                    <input type="radio" value="0" name="joinType" class="ui-radio" required data-errormessage-required="请选择加盟类型"/>服务商
                </label>
                <label>
                    <input type="radio" value="1" name="joinType" class="ui-radio" checked/>工厂店
                </label>
            </div>
        </div>
        <div class="ui-form-item">
            <label class="ui-label"></label>
            <input class="subBtn" type="submit" value="提交"/>
        </div>
    </form:form>
</script>