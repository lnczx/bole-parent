
$(function() {
	$("#login-form").validate({
		rules : {
			username : {
				required : true,
				digits:true 
			},
			
			password : {
				required : true,
			},
		},
		
		messages : {
			username : {
				required : "请输入游戏ID",
				digits: "请输入正确的游戏ID" 
			},
			
			password : {
				required : "请输入密码",
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
			form.submit();
		}
	});
	
});


