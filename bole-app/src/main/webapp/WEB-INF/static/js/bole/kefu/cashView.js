
$(function() {
	$("#cash-form").validate({
		submitHandler : function(form) {
			
			$.confirm("您确定要审核通过吗? ", "确认?", function() {
				var minScoreMoneyCash = $("#minScoreMoneyCash").val();
				var totalStore = $("#totalStore").val();
				var totalScore = $("#totalScore").val();
				var scoreCash = $("#scoreCash").val();
				
				if (parseFloat(totalScore) < parseFloat(minScoreMoneyCash)) {
					$.toptip("总返利必须满" + minScoreMoneyCash+ "才能领取." );
					return false;
				}
				
				if (parseFloat(scoreCash) > parseFloat(totalStore)) {
					$.toptip("可领取数不足" );
					return false;
				}
				form.submit();
			})
		}
	});
	
});


