var roleManage=
{
		insertSumUserm:function()
		{
			var jsonData = $("#Form_AddChildren").serialize();
			$.ajax({
				type : "POST",
				url : "insertSumUserm",
				data : jsonData,
				dataType: "json",
				success : function(json) {
					if(0==json.code){
						window.location.reload();
					}else if(-1==json.code)
					{
						remind("error",json.message);
					}else
						setErrMsg("#Form_AddChildren",json.object);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert(errorThrown);
				}
			}); 
		},
		updateSumUserm:function()
		{
			var jsonData = $("#Form_updateChildren").serialize();
			$.ajax({
				type : "POST",
				url : "updateSumUserm",
				data : jsonData,
				dataType: "json",
				success : function(json) {
					if(0==json.code){
						window.location.reload();
					}else if(-1==json.code)
					{
						remind("error",json.message);
					}
					else{
						alert(json.object);
						setErrMsg("#Form_updateChildren",json.object);
					}
						
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					remind("error",errorThrown);
				}
			}); 
		},
		updateDealerDept:function(refrenceId)
		{
			var deptId = $.trim($("#deptSelect"+refrenceId).val());  
			if(deptId!=null && deptId!="")
			{
				$.ajax({
					type : "POST",
					url : "updateDealerDept",
					data : {refrenceId:refrenceId,deptId:deptId},
					dataType: "json",
					success : function(json) {
						if(0!=json.code){
							remind("error",json.message);
						}	
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown);
					}
				}); 
			}else{
				remind("error","请选择部门");
			}
		},
		updateDealerRole:function(refrenceId)
		{
			var roleId = $.trim($("#roleSelect"+refrenceId).val());  
			if(roleId!=null && roleId!="")
			{
				$.ajax({
					type : "POST",
					url : "updateDealerRole",
					data : {refrenceId:refrenceId,roleId:roleId},
					dataType: "json",
					success : function(json) {
						if(0!=json.code){
							remind("error",json.message);
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						remind("error",errorThrown);
					}
				}); 
			}else{
				remind("error","请选择角色");
			}
		},
		updateDealerAccountType:function(refrenceId)
		{ 
			if(refrenceId!=null && refrenceId!="")
			{
				$.ajax({
					type : "POST",
					url : "upDealerAccountType",
					data : {refrenceId:refrenceId},
					dataType: "json",
					success : function(json) {
						if(0!=json.code){
							alert(json.message);
						}else{
//							var obj = $("#u"+refrenceId);
//							if("1"==json.object)
//								obj.html('<i class="icon i-play"></i>使用中');
//							else if("2"==json.object)
//								obj.html('<i class="icon i-pause"></i>已冻结'); 
							window.location.reload();
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