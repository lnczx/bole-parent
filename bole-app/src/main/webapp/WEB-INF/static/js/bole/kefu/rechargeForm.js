$(function() {
	$("#recharge-form").validate({
		rules : {
			
			gameIdTo : {
				required : true,
				digits : true,
				minlength : 6
			},
			
			score : {
				required : true,
				digits : true,
				min : 1,
			},
			
			scoreTypeSelect : {
				required : true,
			
			},
		
		},
		
		messages : {
			
			gameIdTo : {
				required : "请输入游戏ID",
				digits : "请输入正确的游戏ID",
				minlength : "请输入正确的游戏ID"
			},
			
			score : {
				required : "请输入钻石数",
				digits : "请输入正确钻石数",
				min : "钻石数不能为0",
			},
			
			scoreTypeSelect : {
				required : "请选择充值类型",
			},
		},
		
		onkeyup : false,
		focusCleanup : true,
		
		errorPlacement : function(label, element) {
			$.toptip($(label).text());
			$(element).parent().parent().addClass("weui-cell_warn");
		},
		
		submitHandler : function(form) {
			
			$.confirm("您确定要充值吗?", "确认?", function() {
				form.submit();
			}, function() {
				// 取消操作
			});
		}
	});
	
});

$("#scoreTypeSelect").select({
	title : "选择充值类型",
	items : [ {
		title : "赠送",
		value : "0",
	}, {
		title : "付款",
		value : "1",
	} ],

	beforeClose : function(values, titles) {

		$("#scoreType").val(values);
		return true;
	},

});
