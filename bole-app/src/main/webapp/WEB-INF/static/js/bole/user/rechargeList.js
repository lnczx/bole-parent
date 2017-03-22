// 日期查询
$("#date").calendar({
	onChange : function(p, values, displayValues) {
//		console.log(values, displayValues);
		console.log(values[0]);
//		$("#searchDate").val(values);
	}
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