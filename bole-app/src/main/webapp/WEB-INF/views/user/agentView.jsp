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
	<div class="weui-form-preview">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">游戏ID</label>
				<em class="weui-form-preview__value">${contentModel.gameId }</em>
			</div>
		</div>
		<c:if test="${contentModel.userType == 1 }">
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">余额</label>
					<em class="weui-form-preview__value">${contentModel.scoreMoney }</em>
				</div>
			</div>
		</c:if>
		<c:if test="${contentModel.userType == 0 }">
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">代理等级</label>
					<em class="weui-form-preview__value">${contentModel.level }</em>
				</div>
			</div>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">上级ID</label>
					<em class="weui-form-preview__value">${contentModel.pGameId }</em>
				</div>
			</div>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">支付宝账户</label>
					<em class="weui-form-preview__value">${contentModel.payAccount }</em>
				</div>
			</div>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">邀请码</label>
					<em class="weui-form-preview__value">${contentModel.inviteCode }</em>
				</div>
			</div>
			<c:if test="${userType != 0 }">
				<div class="weui-form-preview__bd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">复制下面网址发给代理</label>
						<em class="weui-form-preview__value"></em>
					</div>
				</div>
				<div class="weui-cells">
					<div class="weui-cell">
						<div class="weui-cell__bd">
							<input class="weui-input" type="text" value="${requestUrl }/home/reg?q=${contentModel.inviteCode}">
						</div>
					</div>
				</div>
			</c:if>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">状态</label>
					<em class="weui-form-preview__value"> <c:if test="${contentModel.active == 0 }">
							<font color="red">未激活</font>
						</c:if> <c:if test="${contentModel.active == 1 }">
							<font color="red">已激活</font>
						</c:if>
					</em>
				</div>
			</div>
			<div class="weui-form-preview__ft">
				<a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">返利情况</a>
			</div>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">总返利</label>
					<em class="weui-form-preview__value">${userScoreCashTotalVo.totalScore }</em>
				</div>
			</div>
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">未领取的返利</label>
					<em class="weui-form-preview__value">${userScoreCashTotalVo.totalStore }</em>
				</div>
			</div>
			
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">审核中的返利</label>
					<em class="weui-form-preview__value">${userScoreCashTotalVo.totalCashing }</em>
				</div>
			</div>
		</c:if>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="javascript:history.back(-1);return false;" class="weui-btn weui-btn_primary">返回</a>
			</p>
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="btn_link('/home/logout')" class="weui-btn weui-btn_warn">退出登录</a>
			</p>
		</div>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
</body>
</html>
