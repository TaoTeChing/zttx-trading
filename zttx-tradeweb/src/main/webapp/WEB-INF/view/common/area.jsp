<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<select data-placeholder="请选择" class="${style!=''&&style!=null ? style : 'chosen-select province'}" id="${sale}province" name="province" reqmsg="请选择省" ${required == true ? 'required data-display="地区"':''} ${status?'disabled="disabled"':'' }></select>
<select data-placeholder="市区1" class="${style!=''&&style!=null ? style : 'chosen-select city'}" id="${sale}city" name="city" reqmsg="请选择市"  ${status?'disabled="disabled"':'' }></select>
<select data-placeholder="县区2" class="${style!=''&&style!=null ? style : 'chosen-select county'}" id="${sale}county" name="county" reqmsg="请选择区"  data-display="地区/市"  ${status?'disabled="disabled"':'' }></select>
${status?'<input name="province" type="hidden" value="" id="_province">':''}
${status?'<input name="city" type="hidden" value="" id="_city">':''}
${status?'<input name="county" type="hidden" value="" id="_county">':''}
					
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${src}/common/area.js"></script>
<script type="text/javascript">
	$(function()
	{
		var province = "${province}";
		var city = "${city}";
		var county = "${county}";
		
		var areaNo = "${areaNo}";
		if(areaNo != "" && areaNo.length == 6)
		{
			province = areaNo.substring(0, 2) + "0000";
			city = areaNo.substring(0, 4) + "00";
			county = areaNo;
		}
	
		var provinceObj = $("#"+"${sale}"+"province");
		var cityObj = $("#"+"${sale}"+"city");
		var countyObj = $("#"+"${sale}"+"county");
		
		function toggleCounty(obj,toggleCounty){
			var countyObjID = obj.attr("id");
			if(toggleCounty == 0){
				obj.show();
				$("#"+countyObjID+"_div").show();
			}
			if(toggleCounty == 1){
				obj.hide();
				$("#"+countyObjID+"_div").hide();
			}
		}

		selProCity=function(provinceObj, cityObj, countyObj, province, city, county)
		{
			selInit(provinceObj);
			
		    $.each(area,function(p){
		    	var strs = p.split(" ");
		    	provinceObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
		    });
		    
		    provinceObj.change(function(){
		    	selCity(provinceObj, cityObj, countyObj, null, null);
		    	$('#city_div span').html('请选择市');
				$('#county_div span').html('请选择区');
		    });
		    if(undefined!=province && null!=province && ""!=province)
		    {
		    	provinceObj.val(province);
		    	${status?'$("#_province").val(province)':''};
		    	provinceObj.find(" option[value="+province+"]").attr("selected","selected");
		    	
		    }
		    selCity(provinceObj, cityObj, countyObj, city, county);
            if(window.renderSelect){
                renderSelect(provinceObj);
            }
		}
		selProCity(provinceObj, cityObj, countyObj, province, city, county);

		function selInit(obj)
		{
			obj.children().remove();
			obj.append("<option value='' selected='selected'>"+obj.attr("reqmsg")+"</option>");
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

                    if(cityObj.children().length < 2){
                    	toggleCounty(cityObj, 1);
                    	toggleCounty(countyObj, 1);
                    }else{
                        toggleCounty(cityObj, 0);
                        toggleCounty(countyObj, 0);
                    }
	                cityObj.change(function(){
	                	selCounty(cityObj, countyObj, x, null);
	        			$('#county_div span').html('请选择区');
	    		    });
	                countyX = x;
	                return true;
	            }
	        });
	        if(undefined!=city && null!=city && ""!=city)
	        {
	        	cityObj.val(city);
	        	${status?'$("#_city").val(city)':''};
	        }
            if(window.renderSelect){
                renderSelect(cityObj);
            }
            if(county%100==0){
            	selInit(countyObj);
    			$('#county_div span').html('请选择区');
                selCounty(cityObj, countyObj, countyX, county);
            }else{
            	selCounty(cityObj, countyObj, countyX, county);
            }
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
                            if(strs[1] == undefined || strs[0]==""){
                                return;
                            }
	                    	countyObj.append("<option value='"+strs[1]+"'>"+strs[0]+"</option>");
	                    });
                        if(countyObj.children().length < 2){
                            toggleCounty(countyObj, 1);
                        }else{
                            toggleCounty(countyObj, 0);
                        }
	                }
	            });
				if(undefined!=county && null!=county && ""!=county)
				{
					countyObj.val(county);
					${status?'$("#_county").val(county)':''};
				}
			}
            if(window.renderSelect){
                renderSelect(countyObj);
            }
		}
	});
 
	// ================================================================================

	
</script>
