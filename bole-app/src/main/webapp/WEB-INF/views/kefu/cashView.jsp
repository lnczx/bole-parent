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
	<h1 class="demos-title">返利审核</h1>
	</header>
	<form:form id="cash-form" modelAttribute="contentModel" action="/bole-app/kefu/cashView">
		<form:hidden path="userId" />
		<form:hidden path="id" />
		<input type="hidden" id="minScoreMoneyCash" value="${minScoreMoneyCash }" />
		<input type="hidden" id="totalStore" value="${userScoreCashTotalVo.totalStore }" />
		<input type="hidden" id="totalScore" value="${userScoreCashTotalVo.totalScore }" />
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell weui-cell_warn">
				
				<div class="weui-cell__bd">
					<form:errors path="userId"></form:errors>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label weui-cell_warn">说明</label>
				</div>
				<div class="weui-cell__bd">
					必须累积返利满<font color="red">${minScoreMoneyCash }</font>以上才能领取，每次领取为<font color="red">${minScoreMoneyCash }</font>的整数倍.申请领取返利后需隔<font
						color="red">${minCashDate }</font>天方可再次领取.
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">游戏ID</label>
				</div>
				<div class="weui-cell__bd">${contentModel.gameId }</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总返利数</label>
				</div>
				<div class="weui-cell__bd">${userScoreCashTotalVo.totalScore }</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">正在审核</label>
				</div>
				<div class="weui-cell__bd">${userScoreCashTotalVo.totalCashing }</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">可领取数据</label>
				</div>
				<div class="weui-cell__bd">${userScoreCashTotalVo.totalStore }</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label weui-cell_warn">本次领取*</label>
				</div>
				<div class="weui-cell__bd">${contentModel.scoreCash }</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label weui-cell_warn">支付宝账户*</label>
				</div>
				<div class="weui-cell__bd">${contentModel.payAccount }</div>
			</div>
			<div class="weui-btn-area">
				<input type="submit" class="weui-btn weui-btn_primary" value="审批通过">
			</div>
			<div class="weui-msg__opr-area">
				<p class="weui-btn-area">
					<a href="#" onclick="btn_link('/user/cashList')" class="weui-btn weui-btn_primary">返回</a>
				</p>
			</div>
		</div>
	</form:form>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script src="<c:url value='/static/js/lib/jquery.validation/1.14.0/jquery.validate.min.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bole/kefu/cashView.js'/>"></script>
</body>
</html>
