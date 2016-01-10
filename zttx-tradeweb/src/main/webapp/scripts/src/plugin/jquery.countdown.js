/**
 * jquery 倒计时插件, 提供按钮点击倒计时功能, 适用于发送手机验证码这种场景
 * 
 * @(#)jquery.countdown.js 2014年3月7日 上午9:13:32
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
(function($) {
	$.fn.extend({
		countdown: function(options) {
			options = $.extend({
				time: 60, // 倒计时描述
				text: ' 秒后重新获取', // 倒计时描述字符
				callback: null
			}, options);

			return this.each(function(index, element) {
				var $this = $(this);
				var origText = $this.text();
				var start = null;
				var intervalId = null;
				var countdown = function() {
					$this.text(--start + options.text);
					if (start <= 0) {
						clearInterval(intervalId);
						reset();
					}
				};
				var clickFnc = function(eventObj) {
					eventObj.stopPropagation();
					$this.attr('disabled', true);
					if ($.isFunction(options.callback)) {
						options.callback();
					}
					$this.text(start + options.text);
					intervalId = setInterval(countdown, 1000);
				};
				var reset = function() {
					start = options.time;
					$this.text(origText);
					$this.one('click', clickFnc);
					$this.attr('disabled', false);
				};
				reset();
			});
		}
	});
})(jQuery);