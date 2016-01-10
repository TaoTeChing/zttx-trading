function searchMe(key){
		$("#search_form input[id=q]").val(key);		
		window.location.href = "/market/brands/search?" + "q=" + encodeURIComponent($.trim(key)) ;
	}

$(function() {
	
	    /**暂时不用seajs**/ 
		//seajs.use(['autocomplete'], function(AutoComplete) {
		  function AutoComplete() {
	        var ac = new AutoComplete({
	            trigger: '#q',
	            classPrefix: "ui-autoComplete",	            
	            html: '<strong>{{name}}</strong><span>{{count}}</span>',
	            dataSource: function(value,done){
	                $.ajax({
	                    url: '/autocomplete',
	                    data: {
	                        key:$("#q")[0].value,
	                        key_type:$("#key_type")[0].value
	                    },
	                    dataType: "json",
	                    success: function(data){
	                        console.dir(data.rows);
	                        done(data.rows);
	                    }
	                })
	                return false;
	            },
	            filter: "stringMatch",
	            width: 150
	        }).render();
	        ac.on("itemSelected",function(data,item){
	            console.log(data.id);
	        })
	    }
//		});

		  /***Enter方法***/
        $(document).keypress(function(e){
            var isFocus = $("#q").is(":focus");
            if(isFocus && (e.which == 13 || e.which == 10)) {
            	search();
            }
        });
		
        
        $("#btn_header_search").click(function () {
       	 search();
        })
        
        function search(){
        	var keyWord = encodeURIComponent($.trim($("#search_form input[id=q]").val()));
        	/**
        	 if(('' == keyWord) || ('品牌/资讯' == keyWord)){
        		return false;
        	}	
        	 */
        	var key_type = $('#key_type').val();
        	if(key_type == 0){//品牌
        		window.location.href = "/market/brands/search?" + "q=" + keyWord ;
        	}else if(key_type == 1){//资讯
        		window.location.href = "/article/search?" + "q=" + keyWord;
        	}
        	return ;
        }
        
        
        
        function loadHostList(){
			/*$.ajax({
				type : "GET",
				url : "/search/loadHostList",
				dataType: "html",
				success : function(data) {
					$("#hostList").html(data);
				}
			});*/
		}
        loadHostList();
});