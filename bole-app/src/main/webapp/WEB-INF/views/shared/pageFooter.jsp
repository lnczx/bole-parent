<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="weui-tabbar" style="position:fixed; bottom: 0; ">
	<a href="#" onclick="btn_link('/home/index')" class="weui-tabbar__item weui-bar__item--on">
		<div class="weui-tabbar__icon">
			<img src="<c:url value='/static/images/icon_nav_button.png'/>" alt="">
		</div>
		<p class="weui-tabbar__label">首页</p>
	</a>
	
	<a href="#" onclick="btn_link('/user/agentView')" class="weui-tabbar__item weui-bar__item--on">
		<div class="weui-tabbar__icon">
			<img src="<c:url value='/static/images/icon_nav_cell.png'/>" alt="">
		</div>
		<p class="weui-tabbar__label">我的</p>
	</a>
</div>
</div>
