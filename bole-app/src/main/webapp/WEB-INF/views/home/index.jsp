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
	<h1 class="demos-title">代理专区</h1>
	<p class='demos-sub-title'>博乐广西麻将代理专区</p>
	</header>
	<div class="weui-grids">
		<c:forEach items="${menus}" var="item">
		<a href="#" onclick="btn_link('${item.linkUrl}')" class="weui-grid js_grid">
			<div class="weui-grid__icon">
					<img src="<c:url value='${item.iconUrl}'/>" alt="">
				</div>
			<p class="weui-grid__label">${item.name }</p>
		</a>
		</c:forEach>

	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
</body>
</html>
