define(function(require, exports, module){
    //多级菜单下拉，没有子项的时候，会做一个ajax请求
    $.djMenu = function(obj, settings){
        if (obj.length < 1) {return};

        settings = $.extend({}, $.djMenu.defaults, settings);

        obj.on(settings.events, 'li', function(e){
            //e.stopPropagation();
            var li = $(this).closest('li');

            if (li.children('ul').length > 0) {
                var liSiblings = li.siblings();

                if (settings.only) {
                    liSiblings.removeClass(settings.selectedClass);
                    liSiblings.find('li').removeClass(settings.selectedClass);
                    liSiblings.find('ul').slideUp(settings.speed);
                }

                if (li.hasClass(settings.selectedClass)) {
                    li.removeClass(settings.selectedClass);
                    li.find('ul').slideUp(settings.speed);
                    li.find('li').removeClass(settings.selectedClass);
                    return false;
                }

                li.toggleClass(settings.selectedClass);
                li.children('ul').slideToggle(settings.speed);
                return false;
            }else{
                var id = $(this).data(settings.val);
                if(!id || $.trim(id) == ""){
                    //没有id 页面直接跳转
                    var _href = $(this).find("a").attr("href");
                    location.href = _href;
                }else{
                    $.ajax({
                        url: settings.url,
                        data: "?" + settings.parameter + "=" + id,
                        dataType: "json",
                        method: "post",
                        success: function(data){
                            var htm = Template.render(settings.templateId, data);
                            li.append(htm);
                        }
                    });
                }
            }
        });
    };

    // 默认值
    $.djMenu.defaults = {
        events: 'click',			// 按钮事件
        selectedClass: 'selected',	// 展开时增加的 Class
        speed: 600,					// 切换速度
        only: true					// 同时只展开一个导航
    };

    $.fn.djMenu = function(settings){
        if (this.length === 1) {
            $.djMenu(this, settings);
        } else if (this.length > 1) {
            this.each(function(i){
                $.djMenu($(this), settings);
            });
        }
        return this;
    };
});
