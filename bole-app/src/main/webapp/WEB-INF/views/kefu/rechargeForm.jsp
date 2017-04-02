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
	<h1 class="demos-title">充值</h1>
	</header>
	<form:form id="recharge-form" modelAttribute="contentModel" action="/bole-test/kefu/rechargeForm">
		<form:hidden path="userIdTo" />
		<form:hidden path="scoreType" />
		<form:hidden path="scoreMoney" value="0" />
		<form:hidden path="score" value="0" />
		<input type="hidden" id="addTimeStrFull" name="addTimeStrFull" value=""/>
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
					<label for="name" class="weui-label">充值类型</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="scoreTypeSelect" name="scoreTypeSelect" type="text" value="">
				</div>
			</div>
			<div class="weui-cell" id="divScoreMoneySelect" style="display: none">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">金额</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" id="scoreMoneySelect" name="scoreMoneySelect" type="text" value="">
					</div>
				</div>
			</div>
			<div class="weui-cell" id="divScoreSelect" style="display: none">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">钻石数</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" id="scoreSelect" name="scoreSelect" type="text" value="" readonly="true" />
					</div>
				</div>
			</div>
			<div class="weui-cell" id="divScoreInput" style="display: block">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">钻石数</label>
					</div>
					<div class="weui-cell__bd">
						<input type="text" class="weui-input" id="scoreInput" name="scoreInput" maxLength="20" placeholder="请输入钻石数" />
					</div>
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
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="time-format" class="weui-label">充值时间</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="time-format" type="text" value="${addTimeStrFull }">
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
