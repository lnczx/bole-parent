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
	<header class='demos-header'>
	<h1 class="demos-title">客服管理</h1>
	</header>
	<div class="weui-cell__ft">
		<a href="#" onclick="btn_link('/admin/kefuForm?userId=0')" class="weui-btn weui-btn_mini weui-btn_primary">添加客服</a>
	</div>
	<br>
	<div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="40">游戏ID</th>
					<th width="40">钻石数</th>
					<th width="50">最后充值时间</th>
					<th width="40">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contentModel.list}" var="item">
					
					<tr class="text-c">
						<td><a href="#" onclick="btn_link('/user/rechargeList?userIdTo=${item.userId}')">${ item.gameId }</a></td>
						<td>${ item.score }</td>
						<td>${ item.scoreLastTimeStr }</td>
						<td><a href="#" onclick="btn_link('/kefu/rechargeForm?userIdTo=${item.userId}')"
								class="weui-btn weui-btn_mini weui-btn_primary">充值</a></td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../shared/paging.jsp">
			<c:param name="pageModelName" value="contentModel" />
			<c:param name="urlAddress" value="/project/list" />
		</c:import>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script type="text/javascript" src="<c:url value='/static/js/lib/datatables/1.10.0/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/static/js/bole/table.js'/>"></script>
</body>
</html>
