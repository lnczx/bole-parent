<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/pageHeader.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/js/lib/zTree/css/zTreeStyle/metro.css'/>" />
</head>
<body>
<body ontouchstart>
	<header class='demos-header'>
	<h1 class="demos-title">我的团队</h1>
	</header>
	<input type="hidden" id="userId" value="${userId }"/>
	<input type="hidden" id="name" value="${name }"/>
	<ul id="teamTree" class="ztree"></ul>

	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
	<script type="text/javascript" src="<c:url value='/static/js/lib/zTree/js/jquery.ztree.all-3.5.min.js'/>"></script>
	<script src="<c:url value='/static/js/bole/user/teamTree.js'/>"></script>
</body>
</html>
