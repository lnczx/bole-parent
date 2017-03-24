$(function() {
	$("#recharge-form").validate({
		rules : {
			
			gameIdTo : {
				required : true,
				digits : true,
				minlength : 6
			},
			
			scoreMoneySelect : {
				required : true,
			
			},
			
			score : {
				required : true,
				digits : true,
				min : 0,
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
			
			scoreMoneySelect : {
				required : "请选择充值金额",
			},
			
			score : {
				required : "请输入钻石数",
				digits : "请输入正确钻石数",
				min : "钻石数不正确",
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

$("#scoreMoneySelect").select({
	title : "选择充值金额",
	items : [ {
		title : "300",
		value : "300",
	}, {
		title : "500",
		value : "500",
	},
	{
		title : "1000",
		value : "1000",
	},
	{
		title : "2000",
		value : "2000",
	}],

	beforeClose : function(values, titles) {

		$("#scoreMoney").val(values);
		return true;
	},

});

$("#scoreTypeSelect").select({
	title : "选择充值类型",
	items : [ {
		title : "赠送",
		value : "0",
	}, {
		title : "付款",
		value : "1",
	}],

	beforeClose : function(values, titles) {

		$("#scoreType").val(values);
		return true;
	},

});
