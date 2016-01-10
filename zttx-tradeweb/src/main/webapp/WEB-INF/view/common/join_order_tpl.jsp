<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zttx.web.module.brand.model.BrandesAuthUserModel" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set value="<%=BrandesAuthUserModel.CASH_AUTH%>" var="cash"/>
<c:set value="<%=BrandesAuthUserModel.CREDIT_AUTH%>" var="credit"/>
<script id="joinOrderTpl" type="text/html">
    <div>
        <div class="ui-head">
            <h3>请选择加入进货单产品的数量</h3>
        </div>
        <form id="joinOrderForm" action="">
            <input type="hidden" name="productId" value="{{productId}}"/>
            <table class="order-table" style="margin-top: 20px;">
                <thead>
                <tr>
                    <th width="75px">颜色/尺码</th>
                    <th width="80px">吊牌价</th>
                    {{if isPoint}}
                    <th width="75px">返点价</th>
                    {{else}}
                        {{if (isAuth == ${cash} || isAuth == ${credit})}}
                        <th width="75px">现款价</th>
                        {{/if}}
                        {{if isAuth == ${credit} }}
                        <th width="75px">授信价</th>
                        {{/if}}
                    {{/if}}
                    <th width="90px">库存</th>
                    <th width="152px">数量</th>
                </tr>
                </thead>
            </table>
            <div class="" style="max-height: 300px;margin-top:-1px;margin-bottom:20px;border-bottom:1px solid #d9d9d9;overflow-y: auto;">
                <table class="order-table">
                    <thead>
                    <tr>
                        <th width="80" style="padding: 0;"></th>
                        <th width="90" style="padding: 0;"></th>
                        {{if isPoint}}
                        <th width="85" style="padding: 0;"></th>
                        {{else}}
                            {{if (isAuth == ${cash} || isAuth == ${credit})}}
                            <th width="85" style="padding: 0;"></th>
                            {{/if}}
                            {{if isAuth == ${credit} }}
                            <th width="85" style="padding: 0;"></th>
                            {{/if}}
                        {{/if}}
                        <th width="90" style="padding: 0;"></th>
                        <th width="152" style="padding: 0;"></th>
                    </tr>
                    </thead>
                    <tbody id="orderTableBody">
                    {{each productSkuMap as value index}}
                    {{each value}}
                    <input type="hidden" name="skuIds" value="{{$value.productSkuId}}"/>
                    <tr>
                        <td>{{$value.productSkuName}}</td>
                        <td>{{$formatPrice $value.productSkuPrice}}</td>
                        {{if isPoint}}
                        <td><span class="js-fd-price">{{$formatPrice $value.productSkuPointPrice}}</span> ({{productPointPercent*100}}%)</td>
                        {{else}}
                            {{if (isAuth == ${cash} || isAuth == ${credit})}}
                            <td><span class="js-xk-price">{{$formatPrice $value.productSkuDirPrice}}</span></td>
                            {{/if}}
                            {{if isAuth == ${credit} }}
                            <td><span class="js-sx-price">{{$formatPrice $value.productSkuCreditPrice}}</span></td>
                            {{/if}}
                        {{/if}}
                        <td>{{$value.quantity}}</td>
                        <td>
                            <button type="button" class="num-minus" {{$value.quantity<=0?'disabled':'' }}>-</button>
                            <input type="text" class="num-amount js-price" name="purchaseNum" value="0" data-max="{{$value.quantity}}" />
                            <button type="button" class="num-plus" {{$value.quantity<=0?'disabled':'' }}>+</button>
                        </td>
                    </tr>
                    {{/each}}
                    {{/each}}
                    </tbody>
                </table>
            </div>
            <div style="margin-bottom: 20px;">
                {{if isPoint}}
                    <label><input type="radio" name="productType" value="3" checked /> 返点价：<strong id="fd_price">0.00</strong></label>
                {{else}}
                    {{if (isAuth == ${cash} || isAuth == ${credit})}}
                    <label><input type="radio" name="productType" value="0" {{isAuth == ${credit} ? '': 'checked'}}/> 现款价：<strong id="xk_price">0.00</strong></label>
                    {{/if}}
                    {{if isAuth == ${credit} }}
                    <label class="ml10"><input type="radio" name="productType" value="1" {{isAuth == ${credit} ? 'checked': ''}}/> 授信价：<strong id="sx_price">0.00</strong></label>
                    {{/if}}
                {{/if}}
            </div>
            <div class="ta-r">
                <input type="button" class="set_comfirm" value="确定"/>
            </div>
        </form>
    </div>
</script>