define(function(require){
    var $ = require("$"),
        _ = require("underscore");

    require("./swfupload.js");

    var SWFU_CONFIG = {
        file_size_limit: '5MB',
        file_upload_limit: 50,
        file_queue_limit: 50,
        flash_url: window.SWF_UPLOAD_URL || "",
        button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
        file_dialog_complete_handler: function (selected, queued, inqueue) {
            this.startUpload();
        },
        file_queue_error_handler: function (file, err, msg) {
            switch (err) {
                case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                    //remind('error', '上传文件大小超过限制');
                    break;
                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                    //remind('error', '上传文件类型不符合要求');
                    break;
            }
        }
    };

    $.fn.swfu = function (config) {
        return this.each(function () {
            var id = $(this).attr('id');
            if (_.isUndefined(id)) {
                id = _.uniqueId('swfu_');
                $(this).attr('id', id);
            }
            new SWFUpload($.extend(SWFU_CONFIG, config, { button_placeholder_id: id }));
        });
    };

})

