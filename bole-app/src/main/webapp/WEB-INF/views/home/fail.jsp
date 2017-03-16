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
	<div class="weui-msg">
		<div class="weui-msg__icon-area">
			<i class="weui-icon-cancel weui-icon_msg"></i>
		</div>
		<div class="weui-msg__text-area">
			<h2 class="weui-msg__title">操作失败</h2>
			
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="${nextUrl}" class="weui-btn weui-btn_primary">返回</a>
			</p>
		</div>
		
	</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
	<!--script for this page-->
</body>
</html>
