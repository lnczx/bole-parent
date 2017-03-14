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
	<h1 class="demos-title">会员专区</h1>
	<p class='demos-sub-title'>bole会员专区</p>
	</header>
	<div class="weui-grids">
		<a href="buttons.html" class="weui-grid js_grid">
			<div class="weui-grid__icon">
				<img src="<c:url value='/static/images/icon_nav_button.png'/>" alt="">
			</div>
			<p class="weui-grid__label">Button</p>
		</a>
		<a href="cell.html" class="weui-grid js_grid">
			<div class="weui-grid__icon">
				<img src="<c:url value='/static/images/icon_nav_cell.png'/>" alt="">
			</div>
			<p class="weui-grid__label">List</p>
		</a>
		<a href="form.html" class="weui-grid js_grid">
			<div class="weui-grid__icon">
				<img src="<c:url value='/static/images/icon_nav_cell.png'/>" alt="">
			</div>
			<p class="weui-grid__label">Form</p>
		</a>
	</div>
	<style>
	.weui-footer {
		margin: 25px 0 10px 0;
	}
</style>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/pageFooter.jsp"%>
	<!--script for this page-->
</body>
</html>
