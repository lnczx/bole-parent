function rechargeSubmit(){
	$.confirm("您确定要充值吗?", "确认?", function() {
		$("#recharge-confirm-form").submit();
	}, function() {
		// 取消操作
	});
}