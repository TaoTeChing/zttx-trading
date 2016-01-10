<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<select data-placeholder="请选择" class="${style!=''&&style!=null ? style : 'chosen-select select'}" id="${sale}province" name="province" reqmsg="请选择省"></select>
<select data-placeholder="市区" class="${style!=''&&style!=null ? style : 'chosen-select select'}" id="${sale}city" name="city" reqmsg="请选择市"></select>

<script src="${res}/scripts/jquery.min.js"></script>
<script type="text/javascript">

    $(function()
	{
		var area = ${regionalMap};
		var province = "${province}";
		var city = "${city}";
		var county = "${county}";
	
		var provinceObj = $("#"+"${sale}"+"province");
		var cityObj = $("#"+"${sale}"+"city");
		var countyObj = $("#"+"${sale}"+"county");
		selProCity(provinceObj, cityObj, countyObj, province, city, county);
		
		function selInit(obj)
		{
			obj.children().remove();
			obj.append("<option value='0' selected='selected'>"+obj.attr("reqmsg")+"</option>");
		}
	
		function selProCity(provinceObj, cityObj, countyObj, province, city, county)
		{
			selInit(provinceObj);
			
		    $.each(area,function(p){
		    	var strs = p.split(" ");
		    	provinceObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
		    });
		    
		    provinceObj.change(function(){
		    	selCity(provinceObj, cityObj, countyObj, null, null);
		    });
		    if(undefined!=province && null!=province && ""!=province)
		    {
		    	provinceObj.val(province);
		    }
		    selCity(provinceObj, cityObj, countyObj, city, county);
		}
			
		function selCity(provinceObj, cityObj, countyObj, city, county)
		{
			selInit(cityObj);
	        var countyX;
	        $.each(area,function(p, x){
	        	var pstrs = p.split(" ");
	            if(provinceObj.children(":selected").val() == pstrs[1])
	            {
	                $.each(x,function(c,cx){
	                	var strs = c.split(" ");
	                	cityObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
	                });
	                cityObj.change(function(){
	                	selCounty(cityObj, countyObj, x, null);
	    		    });
	                countyX = x;
	                return true;
	            }
	        });
	        if(undefined!=city && null!=city && ""!=city)
	        {
	        	cityObj.val(city);
	        }
	        selCounty(cityObj, countyObj, countyX, county);
		}
		
		function selCounty(cityObj, countyObj, x, county)
		{
			selInit(countyObj);
			if(undefined!=x)
			{
				$.each(x,function(c,cx){
	            	var cstrs = c.split(" ");
	                if(cityObj.children(":selected").val() == cstrs[1])
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
		}
	});
 
	// ================================================================================

	
</script>
