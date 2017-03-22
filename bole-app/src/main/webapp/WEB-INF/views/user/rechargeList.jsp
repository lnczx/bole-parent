<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<!-- taglib for this page -->
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/pageHeader.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/js/lib/h-ui/css/H-ui.min.css'/>" />
</head>
<body>
<body ontouchstart>
	<header class='.demos-header-top'>
	<h1 class="demos-title">充值记录</h1>
	<p class='demos-sub-title'>总充值：<font color="red">${totalScore }</font>. </p>
	</header>
	<form:form modelAttribute="searchModel" id="search-form" method="GET">
		<form:hidden path="searchDate"/>
		<form:hidden path="scoreType"/>
		<div class="weui-cells weui-cells_form">
			<c:if test="${userType != 0 }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">游戏ID</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="gameIdTo" class="weui-input"/>
				</div>
				<div class="weui-cell__bd"></div>
			</div>
			</c:if>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input type="text" id="date" class="weui-input" value="${searchModel.searchDate }"/>
					
				</div>
				<div class="weui-cell__bd"></div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">充值类型</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="scoreTypeSelect" type="text" value="">
					
					
				</div>
				<div class="weui-cell__ft">
					<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_plain-primary " id="btn-search">查询</a>
				</div>
				
				
			</div>
		</div>
	</form:form>
	<div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="50">时间</th>
					<th width="35">充值ID</th>
					<th width="35" class="text-r">钻石数</th>
					<th width="35">类型</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contentModel.list}" var="item">
					<tr class="text-c">
						<td>${ item.addTimeStr }</td>
						<td>${ item.gameIdTo }</td>
						<td>${ item.score }</td>
						<td>${ item.scoreTypeName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../shared/paging.jsp">
			<c:param name="pageModelName" value="contentModel" />
			<c:param name="urlAddress" value="/user/rechargeList" />
		</c:import>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script type="text/javascript" src="<c:url value='/static/js/lib/datatables/1.10.0/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/static/js/bole/table.js'/>"></script>
	<script src="<c:url value='/static/js/bole/user/rechargeList.js'/>"></script>
</body>
</html>
