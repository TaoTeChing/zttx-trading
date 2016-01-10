<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 发站内信 -->
<script type="text/html" id="message_tpl">
    <div class="message_dialog">
        <div class="ui-head">
            <h3>发站内信</h3>
        </div>
        <form class="ui-form message-form" action="#">
            <div class="ui-form-item mt15">
                <textarea placeholder="请输入您的留言..." class="ui-textarea" id="message_text"></textarea>
            </div>
            <div class="ta-c">
                <button type="button" class="simple_button confirm_btn">提&nbsp;交</button>
                <button type="button" class="simple_button ml5 cancel_btn">取消</button>
            </div>
        </form>
    </div>
</script>