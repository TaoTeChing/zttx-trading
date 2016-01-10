<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 鼠标点击备注时的模板 -->
<script type="text/html" id="dialogRemark">
    <div class="ui-head">
        <h3>备注</h3>
    </div>
    <div id="remark_dialog_{{id}}" class="remark_dialog">
        <div class="hd" id="levelMark">
            <label for="">
                <input name="leven" value="1" type="radio" {{ if colorIndex == 1 }} checked {{/if}}  /><em style="color: #ff0000">★</em>
            </label>
            <label for="">
                <input name="leven" value="2" type="radio" {{ if colorIndex == 2 }} checked {{/if}}   /><em style="color: #d603f1">★</em>
            </label>
            <label for="">
                <input name="leven" value="3" type="radio" {{ if colorIndex == 3 }} checked {{/if}}  /><em style="color: #0082cc">★</em>
            </label>
            <label for="">
                <input name="leven" value="4" type="radio" {{ if colorIndex == 4 }} checked {{/if}}  /><em style="color: #009944">★</em>
            </label>
            <label for="">
                <input name="leven" value="5" type="radio" {{ if colorIndex == 5 }} checked {{/if}}  /><em style="color: #996c33">★</em>
            </label>
        </div>
        <div class="bd">
            <div class="info">
                <textarea id="remark">{{text}}</textarea>
            </div>
            <div class="operate">
                <button class="confirm_btn simple_button" type="button">确定</button>
                <button class="cancel_btn simple_button ml5" type="button">取消</button>
            </div>
        </div>
    </div>
</script>