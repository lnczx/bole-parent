
$(function() {
	$("#login-form").validate({
		rules : {
			pGameId : {
				required : true,
				digits:true 
			},
			
			gameId : {
				required : true,
				digits:true 
			},
			
			password : {
				required : true,
			},
			
			password2 : {
				required : true,
				equalTo:"#password",
			},
		},
		
		messages : {
			pGameId : {
				required : "请输入上级代理游戏ID",
				digits:"请输入正确的游戏ID" 
			},
			
			gameId : {
				required : "请输入游戏ID",
				digits:"请输入正确的游戏ID"
			},
			
			password : {
				required : "请输入密码",
			},
			
			password2 : {
				required : "请再次输入密码",
				equalTo: "两次密码不相同",
			},
		},
				
		onkeyup : false,
		focusCleanup : true,

		errorPlacement: function (label, element) {
			$.toptip($(label).text());
			$(element).parent().parent().addClass("weui-cell_warn");
        },
//        highlight: function(element, errorClass) {
////        	console.log("highlight");
////        	console.log($(element).parent().parent());
////            $(element).parent().parent().next().removeClass("weui-cell_warn");
//        	
//        	$(element).parent().parent().next().addClass("weui-cell_warn");
//        },
//        unhighlight: function(element, errorClass) {  
//        	console.log("unhighlight");
//        	console.log($(element));
//        	$(element).parent().parent().next().removeClass("weui-cell_warn");
//        },
//        success: function(label) {
//			label.parent().parent("div").parent("div").removeClass("weui-cell_warn");
//		},
		submitHandler : function(form) {
			var pGameId = $("#pGameId").val().trim();
			var gameId = $("#gameId").val().trim();
			if (pGameId == gameId) {
				$.toptip("上级代理ID和游戏ID不能相同.");
				return false;
			}
			form.submit();
		}
	});
	
});


