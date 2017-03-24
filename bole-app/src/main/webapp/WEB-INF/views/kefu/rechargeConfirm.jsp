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
	<h1 class="demos-title">充值确认</h1>
	</header>
	<form:form id="recharge-confirm-form" modelAttribute="contentModel" action="/bole-app/kefu/rechargeConfirm">
		<form:hidden path="userIdTo" />
		<form:hidden path="gameIdTo" />
		<form:hidden path="score" />
		<form:hidden path="scoreMoney" />
		<form:hidden path="scoreType" />
		<form:hidden path="remarks" />
	</form:form>
	<div class="weui-form-preview">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">游戏ID</label>
				<em class="weui-form-preview__value">${contentModel.gameIdTo }</em>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">金额</label>
				<em class="weui-form-preview__value"><font color="red">${contentModel.scoreMoney }</font></em>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">钻石数</label>
				<em class="weui-form-preview__value"><font color="red">${contentModel.score }</font></em>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">充值前</label>
				<em class="weui-form-preview__value">${contentModel.scorePre }</em>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">充值后</label>
				<em class="weui-form-preview__value">${contentModel.scoreAfter }</em>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">备注</label>
				<em class="weui-form-preview__value">${contentModel.remarks }</em>
			</div>
		</div>
		<c:if test="${userToUserType == 0 }">
			<div class="weui-form-preview__ft">
				<a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">返利情况</a>
			</div>

			
			<c:if test="${empty payBacks  }">
				<c:forEach items="${paybacks}" var="item">
					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">上级游戏ID</label>
							<em class="weui-form-preview__value">${item.gameIdTo } &nbsp;&nbsp;&nbsp;   ${item.linkBackLevel}层返利=<font color="red">${item.scoreMoney }.</font></em>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${!empty payBacks  }">
				<div class="weui-form-preview__bd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">无返利</label>
						<em class="weui-form-preview__value"></em>
					</div>
				</div>
			</c:if>
		</c:if>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="rechargeSubmit()" class="weui-btn weui-btn_primary">确认充值</a>
			</p>
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="btn_link('/kefu/rechargeForm?userIdTo=${contentModel.userIdTo}')" class="weui-btn weui-btn_primary">返回</a>
			</p>
		</div>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<script type="text/javascript" src="<c:url value='/static/js/bole/kefu/rechargeConfirm.js'/>"></script>
</body>
</html>
