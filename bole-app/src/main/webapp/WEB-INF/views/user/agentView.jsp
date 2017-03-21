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
				<label class="weui-form-preview__label">邀请码</label>
				<em class="weui-form-preview__value">${contentModel.inviteCode }</em>
			</div>
		</div>
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
				<em class="weui-form-preview__value">${contentModel.totalPayBack }人</em>
			</div>
		</div>
		
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">未领取的返利</label>
				<em class="weui-form-preview__value">${contentModel.totalStore }人</em>
			</div>
		</div>
		
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="btn_back()" class="weui-btn weui-btn_primary">返回</a>
			</p>
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="btn_link('/home/logout')" class="weui-btn weui-btn_primary">退出登录</a>
			</p>
		</div>
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	
</body>
</html>
