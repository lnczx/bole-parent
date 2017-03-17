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
	<h1 class="demos-title">充值操作</h1>
	</header>
	<form:form id="recharge-form" modelAttribute="contentModel" action="/bole/kefu/rechargeForm">
		<form:hidden path="userIdTo" />
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell weui-cell_warn">
				<div class="weui-cell__hd">
					<label class="weui-label"></label>
				</div>
				<div class="weui-cell__bd">
					<form:errors path="userIdFrom"></form:errors>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">游戏ID</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="gameIdTo" class="weui-input" maxLength="20" placeholder="请输入游戏ID" />
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">钻石数</label>
				</div>
				<div class="weui-cell__bd">
					<form:input path="score" class="weui-input" placeholder="请输入密码" maxLength="20" />
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="name" class="weui-label">充值类型</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="scoreTypeSelect" name="scoreTypeSelect"  type="text" value="">
					<input type="hidden" name="scoreType"/>
				</div>
			</div>
			<div class="weui-cells__title"></div>
			<div class="weui-cells weui-cells_form">
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<form:textarea path="remarks" class="weui-textarea" rows="3" placeholder="备注一下" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="weui-btn-area">
				<input type="submit" class="weui-btn weui-btn_primary" value="保存">
			</div>
		</div>
	</form:form>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<script src="<c:url value='/static/js/lib/jquery.validation/1.14.0/jquery.validate.min.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bole/kefu/rechargeForm.js'/>"></script>
	<!--script for this page-->
</body>
</html>
