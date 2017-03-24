// 日期查询
$("#date").calendar({
	onChange : function(p, values, displayValues) {
		$("#searchDate").val(values);
	}
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
	items : [ 
	{
		title : "全部",
		value : "",
	},      
	{
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