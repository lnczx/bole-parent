
$(function() {
	$("#cash-form").validate({
		submitHandler : function(form) {
			
			$.confirm("您确定要审核通过吗? 通过后系统会自动给代理返利.", "确认?", function() {
				var minScoreCash = $("#minScoreCash").val();
				var totalStore = $("#totalStore").val();
				var totalScore = $("#totalScore").val();
				var scoreCash = $("#scoreCash").val();
				
				if (parseFloat(totalScore) < parseFloat(minScoreCash)) {
					$.toptip("总返利必须满" + minScoreCash+ "才能领取." );
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


