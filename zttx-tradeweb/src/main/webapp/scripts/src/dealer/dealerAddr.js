var dealerAddr=
{/*
		labelFunction:function(addr,split)
		{
			var result = '';
			var province = addr.substring(0,2)+"0000";
			var city = addr.substring(0,4)+"00";
			var county = addr;
			$.each(area,function(p,x){
		    	var strs = p.split(" ");
		    	if(strs[1] == province)
		    	{
		    		result += strs[0];
		    		$.each(x,function(c,cx){
	                	var strs2 = c.split(" ");
	                	if(strs2[1] == city)
	                	{
	                		result += split;
	                		result += strs2[0];
	                		$.each(cx.split(","),function(){
		                    	var strs3 = this.split(" ");
		                    	if(strs3[1] == county)
		                    	{
		                    		result += split;
		                    		result += strs3[0];
		                    	}
		                    });
	                	}
	                });
		    	}
		    });
			return result;
		},
		*/
		labelFunction:function(addr,split)
		{
			var result = addr.provinceName+split+addr.cityName+split+addr.areaName;
			return result;
		},
		/**
		 * 加载地区数据
		 * @param provinceName		省份下拉框ID
		 * @param cityName			城市下拉框ID
		 * @param countyName		地区下拉框ID
		 * @param province			省份默认值
		 * @param city				城市默认值
		 * @param county			地区默认值
		 */
		selProCity:function(provinceName, cityName, countyName, province, city, county)
		{
			$.fn.selInit = function(){return $(this).html("<option value='0'>"+$(this).attr("reqmsg")+"</option>");};
			var provinceObj = $("#"+provinceName);
			provinceObj.selInit();
		    $.each(area,function(p){
		    	var strs = p.split(" ");
		    	provinceObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
		    });
		    provinceObj.change(function(){
		    	$ADDR.selCity(provinceName, cityName, countyName,0,0);
		    });
		    if(undefined!=province && null!=province && ""!=province)
		    {
		    	provinceObj.val(province);
		    	$("#"+provinceName+" option[value="+province+"]").attr("selected","selected");
		    	$("[id="+provinceName+"_div]")
		    		.html($("#"+provinceName+" option[value="+province+"]").html());
		    	//<div id="province_div" class="selectalready">内蒙古自治区</div>
		    }
		    $ADDR.selCity(provinceName, cityName, countyName, city, county);
		},
		selCity:function(provinceName, cityName, countyName, city, county)
		{
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
	                	$ADDR.selCounty(cityName, countyName, x,0);
	    		    });
	                countyX = x;
	                return true;
	            }
	        });
	        if(undefined!=city && null!=city && ""!=city)
	        {
	        	cityObj.val(city);
	        	$("#"+cityName+" option[value="+city+"]").attr("selected","selected");
	        	$("[id="+cityName+"_div]")
	    		.html($("#"+cityName+" option[value="+city+"]").html());
	        }else if(undefined!=city && null!=city){
	        	$("#"+cityName+" option[value=0]").attr("selected","selected");
	        	$("[id=city_div]").html($("#"+cityName+" option[selected=selected]").html());
	        }
	        
	        $ADDR.selCounty(cityName, countyName, countyX, county);
		},
		selCounty:function(cityName, countyName, x, county)
		{
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
				}else if(undefined!=county && null!=county){
					$("#"+countyName+" option[value=0]").attr("selected","selected");
		        	$("[id=county_div]").html($("#"+countyName+" option[selected=selected]").html());
				}
			}else{
				
			}
		}
};
window.dealerAddr = window.$ADDR = dealerAddr;