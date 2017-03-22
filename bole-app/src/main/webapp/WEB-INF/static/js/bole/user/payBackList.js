// 日期查询
$("#date").calendar({
	onChange : function(p, values, displayValues) {
		$("#searchDate").val(values);
	}
});