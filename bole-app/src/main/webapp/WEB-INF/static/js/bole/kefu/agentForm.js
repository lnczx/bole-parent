
$(function() {
	$("#agent-form").validate({
		rules : {
			
			pGameId : {
				required : true,
				digits:true ,
				minlength:1 
			},
			
			gameId : {
				required : true,
				digits:true ,
				minlength:6 
			},
			

		},
		
		messages : {
			
			pGameId : {
				required : "请输入上级会员ID,没有请输入0",
				digits:"请输入正确的游戏ID" ,
				minlength:"请输入正确的游戏ID" 	
			},
			
			gameId : {
				required : "请输入游戏ID",
				digits:"请输入正确的游戏ID",
				minlength:"请输入正确的游戏ID"
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


