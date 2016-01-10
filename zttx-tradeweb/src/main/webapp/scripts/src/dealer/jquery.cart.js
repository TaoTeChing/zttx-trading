/// <reference path="jquery-1.10.2.min.js" />
(function ($) {
	$.fn.extend({
		cartInit: function (options,beforeFn,afterFn) {
		    var defaults = {
		        priceTag: ".price",
		        totalTag: ".total",
                maxerror:"购买量太大",
                minerror:"商品没有达到起批量的要求"
		    };
			var opts = $.extend(defaults, options);

			var me = $(this);
            var time = 0;
			$(me).each(function (i, o) {

                var txt = $(o).find(".txt-amount").click(function(){
                   $(this).select();
                });

                //6-12
                var $kucun = $(o).parent().prev();
                //6-12
                //console.log(txt.value);
                /*if(Number(txt.value)>0)
                {
                    $(txt).parent().parent().parent().addClass("js-notnull");
                }*/
				var minus = $('<a href="javascript:void(0)" class="minus disable">-</a>'); //$(o).find(".minus");
				var plus = $('<a href="javascript:void(0)" class="plus">+</a>'); //$(o).find(".plus");

                //数量增减
				txt.parent().append(plus).prepend(minus);
				var max = Number($(txt).data("max"));
				var min = Number($(txt).data("min"));
				var now = Number(txt.val());

                if($(o).has(":disabled").length==0){
                    $(minus).click(function (ev) {
                        ev.preventDefault();
                        now = now - 1;
                            changeVal();
                    });

                    $(plus).click(function (ev) {
                        ev.preventDefault();
                        now = now + 1;
                            changeVal();
                    });

                    $(txt).keyup(function(){
                        now = Number($(this).val());
                            changeVal();
                    });

                    function changeVal() {

                        if (now > 0) {
                            if ($(minus).hasClass("disable")) {
                                $(minus).removeClass("disable");
                            }
                        }
                        else {
                            $(minus).addClass("disable");
                            now = 0;
                        }

                        if (now <= max) {
                            if ($(plus).hasClass("disable")) {
                                $(plus).removeClass("disable");
                                //6-12
                                $kucun.removeClass("c-r");
                                //6-12
                            }

                        }
                        else {
                            $(plus).addClass("disable");
                            now = max;
                            //6-12
                            $(".price + td").removeClass("c-r");
                            $kucun.addClass("c-r");
                            //6-12
                            remind("error",opts.maxerror,function(){});
                        }

                        //起拍数不足
                        /*if(now<min)
                        {
                            remind("error",opts.minerror,function(){});
                        }*/

                        $(txt).val(now);


                        if(now==0){
                            $(txt).parent().parent().parent().removeClass("js-notnull");
                        }
                        else{
                            $(txt).parent().parent().parent().addClass("js-notnull");
                        }
                        $(txt).parent().parent().parent().addClass("js-ischange");

                        var price = $(txt).parent().parent().parent().find(opts.priceTag).text();
                        var total = (Number(price) * now).toFixed(2);
                        $(txt).parent().parent().parent().find(opts.totalTag).text(total);
                        clearTimeout(time);
                        time = setTimeout(function(){
                           afterFn(now,$(txt).parent().parent().parent());
                        },618);
                        beforeFn();

				    }
                }
			});

		}
	})
})(jQuery);