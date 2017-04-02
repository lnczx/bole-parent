
$(function() {
	$("#kefu-form").validate({
		rules : {
			
			gameId : {
				required : true,
				digits:true ,
				minlength:6 
			},
			
			password : {
				required : true,
				minlength:6 
			},
			
			password2 : {
				required : true,
				equalTo:"#password",
				minlength:6 
			},
			
			payAccount : {
				required : true,
			},
		},
		
		messages : {
			
			gameId : {
				required : "请输入游戏ID",
				digits:"请输入正确的游戏ID",
				minlength:"请输入正确的游戏ID"
			},
			
			password : {
				required : "请输入密码",
				minlength: "密码为六位以上"
			},
			
			password2 : {
				required : "请再次输入密码",
				equalTo: "两次密码不相同",
				minlength: "密码为六位以上"
			},
			
			payAccount : {
				required : "请输入支付宝账户.",
			},
		},
				
		onkeyup : false,
		focusCleanup : true,

		errorPlacement: function (label, element) {
			$.toptip($(label).text());
			$(element).parent().parent().addClass("weui-cell_warn");
        },

		submitHandler : function(form) {
			var pGameId = $("#pGameId").val();
			var gameId = $("#gameId").val();
			if (pGameId == gameId) {
				$.toptip("上级会员ID和游戏ID不能相同.");
				return false;
			}
			form.submit();
		}
	});
	
});


