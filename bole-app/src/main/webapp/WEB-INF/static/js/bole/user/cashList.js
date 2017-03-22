// 日期查询
$("#date").calendar({
	onChange : function(p, values, displayValues) {
		$("#searchDate").val(values);
	}
});

$("#statusSelect").select({
	title : "选择状态",
	items : [ 
	{
		title : "全部",
		value : "",
	},      
	{
		title : "审核中",
		value : "0",
	}, {
		title : "已通过",
		value : "1",
	}],

	beforeClose : function(values, titles) {

		$("#status").val(values);
		return true;
	},

});