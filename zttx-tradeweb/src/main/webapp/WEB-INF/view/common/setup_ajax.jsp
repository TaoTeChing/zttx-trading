<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<form:form id="_csrf-form" cssStyle="display: none">
</form:form>
<script type="text/javascript">
    $(function(){
        setCsrfToken("_csrf-form");
        window['G_PATH']="${ctx}";
    });
    
    function setCsrfToken(formId) {
        var csrfToken = $('#' + formId).find('input[name="csrfToken"]').val();
        $(document).on('ajaxSend', function (elem, xhr, s) {
            if (s.type.toUpperCase() == 'POST') {
                if (xhr.setRequestHeader) {
                    xhr.setRequestHeader('csrfToken', csrfToken);
                } else {
                    s.url += (s.url.indexOf("?") == -1) ? "?" : "&";
                    s.url += "csrfToken=" + csrfToken;
                }
            }
        });
    }
</script>
