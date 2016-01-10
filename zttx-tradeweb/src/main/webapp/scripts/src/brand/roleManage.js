var roleManage=
{
		insertSumUserm:function()
		{
			var jsonData = $("#newAccountForm").serialize();
			$.ajax({
				type : "POST",
				url : "insertSumUserm",
				data : jsonData,
				dataType: "json",
				success : function(json) {
					
					if(zttx.SUCCESS==json.code){
						window.location.reload();
					}else if(-1==json.code)
					{
						remind("error",json.message);
					}
					else{
						setErrMsg("#newAccountForm",json.rows);
					}
						
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					remind("error",errorThrown);
				}
			}); 
		},
		updateSumUserm:function()
		{
			var jsonData = $("#updateAccountForm").serialize();
			$.ajax({
				type : "POST",
				url : "updateSumUserm",
				data : jsonData,
				dataType: "json",
				success : function(json) {
					if(zttx.SUCCESS==json.code){
						window.location.reload();
					}else if(-1==json.code)
					{
						remind("error",json.message);
					}
					else{
						setErrMsg("#updateAccountForm",json.rows);
					}
						
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					remind("error",errorThrown);
				}
			}); 
		},
		updateBrandDept:function(refrenceId)
		{
			var deptId = $.trim($("#deptSelect"+refrenceId).val());  
			if(deptId!=null && deptId!="")
			{
				$.ajax({
					type : "POST",
					url : "updateBrandDept",
					data : {refrenceId:refrenceId,deptId:deptId},
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS!=json.code){
							remind("error",json.message);
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown);
					}
				}); 
			}
		},
		updateBrandRole:function(refrenceId)
		{
			var roleId = $.trim($("#roleSelect"+refrenceId).val());  
			if(roleId!=null && roleId!="")
			{
				$.ajax({
					type : "POST",
					url : "updateBrandRole",
					data : {refrenceId:refrenceId,roleId:roleId},
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS!=json.code){
							remind("error",json.message);
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown);
					}
				}); 
			}
		},
		updateBrandAccountType:function(refrenceId)
		{ 
			if(refrenceId!=null && refrenceId!="")
			{
				$.ajax({
					type : "POST",
					url : "upBrandAccountType",
					data : {refrenceId:refrenceId},
					dataType: "json",
					success : function(json) {
						if(zttx.SUCCESS!=json.code){
							alert(json.message);
						}else{
							var obj = $("#u"+refrenceId);
							if("1"==json.object)					
								obj.html('<i class="iconfont">&#xe623;</i><span>使用中</span>');
							else if("2"==json.object)								
								obj.html('<i class="iconfont">&#xe624;</i><span>冻结</span>'); 
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown);
					}
				}); 
			}
		}
};
window.roleManage = window.$ROL = roleManage;