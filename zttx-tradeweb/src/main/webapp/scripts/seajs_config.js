window.DOMIN = "";

//图片域名
window.IMAGE_DOMIAN = "http://172.16.1.8:81";

//图库选择开关。默认开启使用
window.IMAGE_SWITCH = true;

//编辑器
window.UMEDITOR_HOME_URL = window.IMAGE_DOMIAN + "/scripts/gallery/editor/1.0.0/";

//swfload的目录
window.SWF_UPLOAD_URL = window.DOMIN + "/assets/swfupload.swf";

//剪切的flash目录
window.SWF_ZeroClipboard_URL = window.DOMIN + "/assets/ZeroClipboard.swf";

window.SWF_Video_URL = window.DOMIN + "/assets/CuPlayerMiniV4.swf";

window.SWF_Video_File = window.IMAGE_DOMIAN + "/scripts/gallery/swfobject/2.2.1/data.xml";

window.SWF_IMAGE_FILE = window.IMAGE_DOMIAN + "/uploads/start.jpg";

//图片上传URL
window.UPLOAD_IMAGE_PATH = window.DOMIN + "/common/editor/upfile";

//uploadify上传图片SWF文件
window.UPLOADIFY_SWF_URL = window.DOMIN + "/assets/uploadify.swf";

seajs.config({
    base: window.IMAGE_DOMIAN + '/scripts',
    alias: {
        '$': 'jquery/jquery/1.7.2/jquery',
        'widget': 'arale/widget/1.1.1/widget',
        'sticky': "arale/sticky/1.3.1/sticky",
        'qrcode': "arale/qrcode/1.0.3/qrcode",
        'templatable': 'arale/templatable/0.9.2/templatable',
        'slide': 'arale/switchable/1.0.1/slide',
        'autocomplete': "arale/autocomplete/1.3.0/autocomplete",
        'carousel': 'arale/switchable/1.0.2/carousel',
        'validator': 'arale/validator/0.9.6/validator',
        'calendar': 'arale/calendar/1.0.0/calendar',
        'calendar_style': 'arale/calendar/1.0.0/calendar.css',
        'dialog': 'arale/dialog/1.2.5/dialog',
        'dialog_style': 'arale/dialog/1.2.5/dialog.css',
        'confirmbox': 'arale/dialog/1.2.5/confirmbox',
        'popup': 'arale/popup/1.1.5/popup',
        'tip': 'arale/tip/1.2.1/tip',
        'accordion': 'arale/switchable/1.0.1/accordion',
        'select': 'arale/select/0.9.9/select',
        'placeholders': 'gallery/placeholders/3.0.2/placeholders',
        'moment': 'gallery/moment/2.0.0/moment',
        'umeditor': 'gallery/editor/1.0.0/umeditor',
        'umeditor_config': 'gallery/editor/1.0.0/umeditor.config',
        'umdeitor_style': 'gallery/editor/1.0.0/themes/default/css/umeditor.min.css',
        'jscroll': 'gallery/jsrollbar/1.0.2/jscrollbar',
        'ztree': 'gallery/ztree/3.5.3/ztree',
        'pagination': 'gallery/pagination/0.1.1/pagination',
        'underscore': 'gallery/underscore/1.6.0/underscore',
        'ajaxFileUpload': 'gallery/ajaxFileUpload/0.1.1/ajaxFileUpload',
        'swfupload': 'gallery/swfupload/0.1.1/jquery.swfu',
        'zeroclipboard': 'gallery/zeroclipboard/1.2.2/zeroclipboard',
        'swfobject': 'gallery/swfobject/2.2.0/swfobject',
        'video': 'gallery/swfobject/2.2.1/video',
        'template': 'gallery/template/1.0.1/template',
        'lightbox': 'gallery/lightbox/1.0.0/lightbox',
        'my97DatePicker': "gallery/my97DatePicker/1.0.0/WdatePicker",
        "pcas": 'gallery/area/1.0.0/PCAS.js',
        "validateForm": "gallery/validateForm/1.0.0/validateForm",
        'Jcrop': "gallery/Jcrop/0.9.12/Jcrop",
        'introjs': "gallery/introjs/0.9.0/intro",
        'morris': "gallery/morris/0.5.0/morris",
        'masonry': "gallery/masonry/3.1.5/masonry",
        'tool': "gallery/tool/1.0.0/tool",
        'dnd': 'arale/dnd/1.0.0/dnd',
        'turn': 'gallery/turn/0.0.1/turn',
        'cookie': 'arale/cookie/1.0.2/cookie',
        'tabs': 'arale/switchable/1.0.1/tabs',
        'overlay': 'arale/overlay/1.1.4/overlay',
        'glasses': 'gallery/glasses/0.1.2/glasses',
        'uploadify': 'gallery/uploadify/3.2.1/uploadify',
        'djmenu': 'gallery/djmenu/0.0.1/djmenu',
        'setNumber': 'gallery/setNumber/0.0.1/setNumber'
    }
});
