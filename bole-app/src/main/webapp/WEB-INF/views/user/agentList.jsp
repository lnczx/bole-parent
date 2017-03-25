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
	<h1 class="demos-title">代理列表</h1>
	<p class='demos-sub-title '><font color="red">红色代表未激活</font></p>
	</header>
	<c:if test="${ userType != 0 }">

		<div class="weui-cell__bd weui-cell__ft">
		
			<a href="#" onclick="btn_link('/kefu/agentForm?userId=0')" class="weui-btn weui-btn_mini weui-btn_plain-primary">邀请代理</a>
		</div>
		<form:form modelAttribute="searchModel" id="search-form"  method="GET">
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="date" class="weui-label">游戏ID</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="gameId" class="weui-input"/>
				</div>
				<div class="weui-cell__bd weui-cell__ft">
					<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_plain-primary " id="btn-search">查询</a>
				</div>
			</div>
		</div>
		</form:form>
		
		<br>

	</c:if>
	<div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="40">游戏ID</th>
					<th width="20">级别</th>
					<th width="40" >钻石</th>
					<th width="35">时间</th>
					<c:if test="${ userType == 1 }">
						<th width="35">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contentModel.list}" var="item">
					<c:if test="${item.active == 0 }">
						<tr class="text-c danger">
					</c:if>
					<c:if test="${item.active == 1 }">
						<tr class="text-c">
					</c:if>
						<td><a href="#" onclick="btn_link('/user/agentView?userId=${item.userId}')">${ item.gameId }</a></td>
						<td>${ item.level }</td>
						<td class="text-r">${ item.score }</td>
						<td>${ item.scoreLastTimeStr }</td>
						<c:if test="${ userType == 1 }">
							<td>
								<c:if test="${ item.active == 1 }">
									<a href="#" onclick="btn_link('/kefu/rechargeForm?userIdTo=${item.userId}')"
										class="weui-btn weui-btn_mini weui-btn_primary">充值</a>
								</c:if>
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
