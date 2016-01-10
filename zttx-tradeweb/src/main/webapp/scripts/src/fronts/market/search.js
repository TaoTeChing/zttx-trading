/**
 * Created by yefeng on 2014/4/29.
 */
seajs.use(['tabs','$'], function (Tabs,$) {
    var tabs = new Tabs({
        element: '#tab-view',
        triggers: '#tab-view .hot-nav li',
        panels: '#tab-view .content ul',
        activeTriggerClass:'active'

    });
    $('[data-deftxt]').css({ color: '#bbb' }).focus(function () {
        if ($(this).val() == $(this).data("deftxt")) {
            $(this).val("").css({ color: '#666' });
        }
    }).blur(function () {
        if ($(this).val() == '') {
            $(this).val($(this).data("deftxt")).css({ color: '#bbb' });
        }
    })
});