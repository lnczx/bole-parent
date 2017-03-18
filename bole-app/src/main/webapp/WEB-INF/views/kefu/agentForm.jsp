<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/pageHeader.jsp"%>
</head>
<body>
<body ontouchstart>
	<header class='demos-header'>
	<h1 class="demos-title">代理信息</h1>
	</header>
	<form:form id="agent-form" modelAttribute="contentModel" action="/bole/kefu/agentForm">
		<form:hidden path="userId"/>
		<form:hidden path="userType"/>
		<div class="weui-cells weui-cells_form">
			
			<div class="weui-cell weui-cell_warn">
				<div class="weui-cell__hd">
					<label class="weui-label"></label>
				</div>
				<div class="weui-cell__bd">
					<form:errors path="gameId" ></form:errors>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">上级代理ID</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="pGameId" class="weui-input"  maxLength="20" placeholder="请输入上级ID"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">游戏ID</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="gameId" class="weui-input"  maxLength="20" placeholder="请输入游戏ID"/>
				</div>
			</div>
			
			
			<div class="weui-btn-area">
				<input type="submit" class="weui-btn weui-btn_primary" value="保存并生成邀请码">
			</div>

		</div>
	</form:form>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	
	<%@ include file="../shared/importJs.jsp"%>
	<script src="<c:url value='/static/js/lib/jquery.validation/1.14.0/jquery.validate.min.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bole/kefu/agentForm.js'/>"></script>
	<!--script for this page-->
</body>
</html>
