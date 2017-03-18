<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<!-- taglib for this page -->
<%@ taglib prefix="timestampTag" uri="/WEB-INF/tags/timestamp.tld"%>
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/pageHeader.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/js/lib/h-ui/css/H-ui.min.css'/>" />
</head>
<body>
<body ontouchstart>
	<header class='demos-header'>
	<h1 class="demos-title">代理列表</h1>
	</header>
	<c:if test="${ userType != 0 }">
		<div class="weui-cell__ft">
			<a href="#" onclick="btn_link('/kefu/agentForm?userId=0')" class="weui-btn weui-btn_mini weui-btn_primary">邀请代理</a>
		</div>
		<br>
		<div class="weui-search-bar" id="searchBar">
			<form:form modelAttribute="searchModel" id="search-form" class="weui-search-bar__form" method="GET">
				<div class="weui-search-bar__box">
					<i class="weui-icon-search"></i>
					<input type="text" class="weui-search-bar__input" id="searchInput" name="gameIdTo" placeholder="搜索" required="">
					<a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
				</div>
				<label class="weui-search-bar__label" id="searchText"
					style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
					<i class="weui-icon-search"></i>
					<span>搜索</span>
				</label>
			</form:form>
			<a href="javascript:" class="weui-search-bar__cancel-btn" id="btn-search">搜索</a>
			<a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
		</div>
	</c:if>
	<div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="50">游戏ID</th>
					<th width="20">级别</th>
					<th width="35">共充值</th>
					<th width="50">充值时间</th>
					<c:if test="${ userType != 0 }">
						<th width="35">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contentModel.list}" var="item">
					<tr class="text-c">
						<td><a href="#" onclick="btn_link('/user/agentView?userId=${item.userId}')">${ item.gameId }</a></td>
						<td>${ item.level }级</td>
						<td>${ item.score }</td>
						<td><c:if test="${item.scoreLastTime > 0 }">
								<timestampTag:timestamp patten="MM-dd" t="${item.scoreLastTime * 1000}" />
							</c:if></td>
						<c:if test="${ userType != 0 }">
							<td>
							<a href="#" onclick="btn_link('/kefu/rechargeForm?userIdTo=${item.userId}')"
								class="weui-btn weui-btn_mini weui-btn_primary">充值</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../shared/paging.jsp">
			<c:param name="pageModelName" value="contentModel" />
			<c:param name="urlAddress" value="/user/agentList" />
		</c:import>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script type="text/javascript" src="<c:url value='/static/js/lib/datatables/1.10.0/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/static/js/bole/table.js'/>"></script>
	<script src="<c:url value='/static/js/bole/user/agentList.js'/>"></script>
</body>
</html>
