
$(function() {
	$("#cash-form").validate({
		rules : {
			
			scoreCash : {
				required : true,
				digits:true ,
				minlength:1 
			},
		},
		
		messages : {
			
			scoreCash : {
				required : "请输入本次领取数",
				digits:"请输入正确的领取数字" ,
				minlength:"领取数必须大于0" 	
			},

		},
				
		onkeyup : false,
		focusCleanup : true,

		errorPlacement: function (label, element) {
			$.toptip($(label).text());
			$(element).parent().parent().addClass("weui-cell_warn");
        },

		submitHandler : function(form) {
			
			$.confirm("您确定要申请返利吗?", "确认?", function() {
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


