var register = {
		phoneVerify:function(name, userType){
			var obj = $("#"+name);
			var isCheck = regForm.check(false,$("#"+name));
			if(!isCheck) return;
			var userMobile = $.trim(obj.val());
			$.ajax({
				type : "POST",
				url : "phoneVerify",
				data : {userMobile:userMobile,userType:userType},
				dataType: "json",
				success : function(json) {
					var validObj = obj.parent().find(".Validform_checktip");
					if(json.code!=0 && json.code != 126138){
						validObj.text(json.message);
						obj.addClass("Validform_error");
						validObj.attr("class","Validform_checktip Validform_wrong");
					}else if(json.code == 126138){
                        validObj.html("您的手机号已被收录/注册，<a href='/common/login' class='link'>立即登录</a>平台采购下单！ <a href='/common/forgotpass' class='link'>忘记密码？</a>");
                        obj.addClass("Validform_error");
                        validObj.attr("class","Validform_checktip Validform_wrong");
                    }else{
						validObj.text("手机号可用！");
						obj.removeClass("Validform_error");
						validObj.attr("class","Validform_checktip Validform_right");
						//$REG.shoTime($(".btn-getcode"), 60);
                        getCodeFn($(".btn-getcode"),60);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert(errorThrown);
				}
			});
		}/*,*/

		/**
		 * 邮箱验证
		 * @param userType
		 */
//		emailVerify:function(name, userType)
//		{
//			var userMail = $.trim($("#"+name).val());
//			$.ajax({
//				type : "POST",
//				url : "emailVerify",
//				data : {userMail:userMail,userType:userType},
//				dataType: "json",
//				success : function(json) {
//					alert(json.object);
//				},
//				error:function(XMLHttpRequest, textStatus, errorThrown){
//				}
//			}); 
//		},
		
		/**
		 * 加载地区数据
		 * @param provinceName		省份下拉框ID
		 * @param cityName			城市下拉框ID
		 * @param countyName		地区下拉框ID
		 * @param province			省份默认值
		 * @param city				城市默认值
		 * @param county			地区默认值
		 */
		/*selProCity:function(provinceName, cityName, countyName, province, city, county){
			$.fn.selInit = function(){return $(this).html("<option value='0'>"+$(this).attr("reqmsg")+"</option>");};
			var provinceObj = $("#"+provinceName);
			console.dir(provinceObj);
			provinceObj.selInit();
			
		    $.each(area,function(p){
		    	var strs = p.split(" ");
		    	provinceObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
		    });
		    
		    provinceObj.change(function(){
		    	$REG.selCity(provinceName, cityName, countyName);
		    });
		    if(undefined!=province && null!=province && ""!=province)
		    {
		    	provinceObj.val(province);
		    }
		    $REG.selCity(provinceName, cityName, countyName, city, county);
		},
		
		selCity:function(provinceName, cityName, countyName, city, county){
			var cityObj = $("#"+cityName);
			cityObj.selInit();
	        var countyX;
	        $.each(area,function(p,x){
	        	var pstrs = p.split(" ");
	            if($("#"+provinceName+" option:selected").val() == pstrs[1])
	            {
	                $.each(x,function(c,cx){
	                	var strs = c.split(" ");
	                	cityObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
	                });
	                cityObj.change(function(){
	                	$REG.selCounty(cityName, countyName, x);
	    		    });
	                countyX = x;
	                return true;
	            }
	        });
	        if(undefined!=city && null!=city && ""!=city)
	        {
	        	cityObj.val(city);
	        }
	        $REG.selCounty(cityName, countyName, countyX, county);
		},
		
		selCounty:function(cityName, countyName, x, county){
			var countyObj = $("#"+countyName);
			countyObj.selInit();
			if(undefined!=x)
			{
				$.each(x,function(c,cx){
	            	var cstrs = c.split(" ");
	                if($("#"+cityName+" option:selected").val() == cstrs[1])
	                {
	                    $.each(cx.split(","),function(){
	                    	var strs = this.split(" ");
	                    	countyObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
	                    });
	                }
	            });
				if(undefined!=county && null!=county && ""!=county)
				{
					countyObj.val(county);
				}
			}
		}*/
        /*,shoTime:function(o,wait)
		{
			if (wait == 0) {
				o.attr("disabled",  false);			
				o.val("免费获取验证码");
			} else {
				o.attr("disabled", true);
				o.val("重新发送(" + wait + ")");
				wait--;
				setTimeout(function() {
					$REG.shoTime(o, wait);
				},
				1000);
			}
		}*/
};
window.register = window.$REG = register;