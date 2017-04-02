$(function() {
	$("#recharge-form").validate({
		rules : {
			
			gameIdTo : {
				required : true,
				digits : true,
				minlength : 6
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
				
				var scoreType = $("#scoreType").val();
				
				if (scoreType == 0) {
					var scoreInput = $("#scoreInput").val();
					if (scoreInput == undefined || scoreInput == '' || scoreInput <= 0) {
						$.toptip("请输入钻石数.");
						return false;
					}
					$("#score").val(scoreInput);
				}
				
				if (scoreType == 1) {
					var scoreMoney = $("#scoreMoney").val();
					if (scoreMoney == undefined || scoreMoney == '' || scoreMoney <= 0) {
						$.toptip("请选择充值金额.");
						return false;
					}
					
					var score = $("#score").val();
					if (score == undefined || score == '' || score <= 0) {
						$.toptip("请选择充值金额.");
						return false;
					}
				}
				
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
		var scoreType = $("#scoreType").val();
		if (scoreType == 0) {
			$("#divScoreMoneySelect").css("display", "none");
			$("#divScoreSelect").css("display", "none");
			$("#divScoreInput").css("display", "block");
		}
		
		if (scoreType == 1) {
			$("#divScoreMoneySelect").css("display", "block");
			$("#divScoreSelect").css("display", "block");
			$("#divScoreInput").css("display", "none");
		}
		
		return true;
	},

});

$("#scoreMoneySelect").select({
	title : "选择充值金额",
	items : [ {
		title : "300",
		value : "300",
	}, {
		title : "500",
		value : "500",
	}, {
		title : "1000",
		value : "1000",
	}, {
		title : "2000",
		value : "2000",
	} ],
	
	beforeClose : function(values, titles) {
		
		$("#scoreMoney").val(values);
		
		if (values == "300") {
			$("#scoreSelect").val("6500");
			$("#score").val("6500");
		}
		
		if (values == "500") {
			$("#scoreSelect").val("11700");
			$("#score").val("11700");
		}
		
		if (values == "1000") {
			$("#scoreSelect").val("24700");
			$("#score").val("24700");
		}
		
		if (values == "2000") {
			$("#scoreSelect").val("52000");
			$("#score").val("52000");
		}
		return true;
	},

});

$("#time-format").datetimePicker({
	title : '选择充值时间',
	yearSplit : '年',
	monthSplit : '月',
	dateSplit : '日',
	times : function() {
		return [ // 自定义的时间
		{
			values : (function() {
				var hours = [];
				for (var i = 0; i < 24; i++)
					hours.push(i > 9 ? i : '0' + i);
				return hours;
			})()
		}, {
			divider : true, // 这是一个分隔符
			content : '时'
		}, {
			values : (function() {
				var minutes = [];
				for (var i = 0; i < 59; i++)
					minutes.push(i > 9 ? i : '0' + i);
				return minutes;
			})()
		}, {
			divider : true, // 这是一个分隔符
			content : '分'
		} ];
	},
	onChange : function(picker, values, displayValues) {
//		console.log(values);
		var y = values[0];
		var m = values[1];
		var d = values[2];
		var h = values[3];
		var min = values[4];
		
		var selectDateStr = y + "-" + m + "-" + d + " " + h + ":" + m + ":00";
		$("#addTimeStrFull").val(selectDateStr);
//		console.log("addTimeStr = " + $("#addTimeStr").val());
	}
});
