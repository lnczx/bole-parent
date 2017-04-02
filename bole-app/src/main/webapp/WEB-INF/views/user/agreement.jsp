<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/pageHeader.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/js/lib/h-ui/css/H-ui.min.css'/>" />
</head>
<body>
<body ontouchstart>
	<header class='demos-header'>
	<h1 class="demos-title">用户协议</h1>
	</header>
	<article class="weui-article"> <section>
	<p>广西博乐麻将用户协议
	<p>
	<p>抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防上当受骗。</p>
	<p>适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。</p>
	<p>1.您充分理解并同意：广西博乐麻将会按照国家相关要求将您的实名注册信息运用于防沉迷系统之中，即广西博乐麻将可能会根据您的实名注册信息判断您是否年满18周岁，从而决定是否对您的游戏账号予以防沉迷限制。并且，若您未满18周岁，广西博乐麻将将会根据有关规定及您家长的要求对您的账号进行限制。</p>
	<p>2.如果您长期连续未登陆，您在游戏内的游戏数据可能会由于技术原因被删除，对此广西博乐麻将不承担任何责任。</p>
	<p>3.本游戏不支持任何的虚拟货币、虚拟物品等。</p>
	<p>4.广西博乐麻将有权根据需要不时修订本协议条款。上述内容已经正式公布即生效。您可以在广西博乐麻将的相关页面查阅最新版本的协议条款。</p>
	<p>5.本解释权归广西博乐麻将所有.</p>
	</section> </article>
	<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" onclick="javascript:history.back(-1);return false;" class="weui-btn weui-btn_primary">返回</a>
			</p>
		</div>
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
</body>
</html>
