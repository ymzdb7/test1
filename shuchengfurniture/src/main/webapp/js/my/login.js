$(function(){
	$('#loginBtn').click(function() { 
		if($('#name').val()==''||$('#pass').val()==''){
			$("#msg").slideDown("slow").delay(5000).slideUp("slow");
			$('#msg').html("请输入用户名和密码！");
			return false;
		} 
		$.ajax({ type: 'POST',
			   async: true,
			   cache: false,
			   url: basePath+'login',
			   data: $('#loginform').serialize(),
			   dataType: 'text',
			   error:function (data, textStatus) { 
				     $("#msg").slideDown("slow").delay(5000).slideUp("slow");
				     $('#msg').html("data.message");
					 return;
			   },success:function (data,textStatus){ 
			      var json =   $.parseJSON(data); 
				  if(json.status==1){ 
				        window.location= basePath+"index"; 
					    return false;
				  }else{ 
					    $('#msg').html(json.message);
					    $("#msg").slideDown("slow").delay(5000).slideUp("slow");
				  }
			   }      
		});
	}); 
	
	
	$("#loginform").bind("keydown",function(e){
        // 兼容FF和IE和Opera    
		var theEvent = e || window.event;    
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
		if (code == 13) {    
		    //回车执行查询
	            $("#loginBtn").click();
	    }    
	});
	
	 
});

