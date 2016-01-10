<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<style>
    .updateAddress-form{
        margin-top: 20px;
    }
    .updateAddress-form .ui-select{
        width: 80px;
        height: 28px;
    }
    .updateAddress-form .ui-textarea{
        width: 300px;
        height: 100px;
    }
    .updateAddress-form .ui-input{
        width: 160px;
    }
</style>

<!-- 修改收货地址  -->
<div id="updateAddress_tpl" style="display: none;">
    <div class="updateAddress_dialog">
        <div class="ui-head">
            <h3>修改收货地址</h3>
        </div>
        <form class="ui-form updateAddress-form vertical-tip" id="updateAddress-form" action="#">
            <div class="ui-form-item">
                <label for="" class="ui-label">
                    选择所在地：
                </label>
                <div class="inline-block">
                    <jsp:include page="${ctx}/common/regional/searchAllArea">
						<jsp:param value="" name="dealerAddrProvince" />
						<jsp:param value="" name="dealerAddrCity" />
						<jsp:param value="" name="dealerAddrArea" />
						<jsp:param value="address" name="sale" />
						<jsp:param value="ui-select js-select" name="style" />
					</jsp:include>
                </div>
            </div>
            <div class="ui-form-item">
                <label for="" class="ui-label">
                    街道地址：
                </label>
                <textarea class="ui-textarea" id="dealerAddress">${param.dealerAddress}</textarea>
            </div>
            <div class="separate_line"></div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    收货人姓名：
                </label>
                <input type="text" class="ui-input" value="${param.shipName}" id="shipName"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    电话：
                </label>
                <input type="text" class="ui-input" value="${param.dealerTel}" id="dealerTel"/>
            </div>
            <div class="ui-form-item">
                <label class="ui-label" for="">
                    手机：
                </label>
                <input type="text" class="ui-input" value="${param.dealerMobile}" id="dealerMobile"/>
            </div>
            <div class="ui-form-item">
                <button class="confirm_btn simple_button" type="button">确定</button>
                <button class="cancel_btn simple_button ml10" type="button">取消</button>
            </div>
        </form>
    </div>
</div>

