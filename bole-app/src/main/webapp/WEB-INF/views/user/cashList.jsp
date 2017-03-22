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
	<header class='demos-header-top'>
	<h1 class="demos-title">
		<c:if test="${userType == 0}">申请返利</c:if>
		<c:if test="${userType != 0}">返利审核</c:if>
	</h1>
	<p class='demos-sub-title'>
		总返利：<font color="red">${userScoreCashTotalVo.totalScore }</font>, 已领取: <font color="red">${userScoreCashTotalVo.totalCash }</font>,
		未领取返利:<font color="red">${userScoreCashTotalVo.totalStore }</font>
	</p>
	</header>
	<c:if test="${ userType == 0 }">

		<div class="weui-cell__bd weui-cell__ft">
		
			<a href="#" onclick="btn_link('/user/cashForm?id=0')" class="weui-btn weui-btn_mini weui-btn_plain-primary">申请返利</a>
		</div>
	</c:if>

	<form:form modelAttribute="searchModel" id="search-form" method="GET">
		<form:hidden path="searchDate" />
		<form:hidden path="status" />
		<div class="weui-cells weui-cells_form">
			<c:if test="${userType != 0 }">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label for="date" class="weui-label">游戏ID</label>
					</div>
					<div class="weui-cell__bd">
						<form:input path="gameId" class="weui-input" />
					</div>
					<div class="weui-cell__bd"></div>
				</div>
			</c:if>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input type="text" id="date" class="weui-input" value="${searchModel.searchDate }" />
				</div>
				<div class="weui-cell__bd"></div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">状态</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="statusSelect" name="statusSelect" type="text" value="">
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
					<th width="35">游戏ID</th>
					<th width="35">数量</th>
					<th width="30">状态</th>
					<c:if test="${userType == 1 }">
						<th width="35">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contentModel.list}" var="item">
					<tr class="text-c">
						<td>${ item.addTimeStr }</td>
						<td>${ item.gameId }</td>
						<td>${ item.scoreCash }</td>
						<td>${ item.statusName }</td>
						<c:if test="${ userType == 1 }">
							<td><c:if test="${item.status == 0 }">
									<a href="#" onclick="btn_link('/kefu/cashView?id=${item.id}')" class="weui-btn weui-btn_mini weui-btn_primary">审核</a>
								</c:if></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../shared/paging.jsp">
			<c:param name="pageModelName" value="contentModel" />
			<c:param name="urlAddress" value="/user/cashList" />
		</c:import>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script type="text/javascript" src="<c:url value='/static/js/lib/datatables/1.10.0/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/static/js/bole/table.js'/>"></script>
	<script src="<c:url value='/static/js/bole/user/cashList.js'/>"></script>
</body>
</html>
