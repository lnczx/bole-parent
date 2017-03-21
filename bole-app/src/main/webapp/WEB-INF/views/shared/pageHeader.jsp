<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="bole game">
<link rel="stylesheet" href="<c:url value='/static/css/weui.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/jquery-weui.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/app.css'/>" />
<script type="text/javascript">  
// 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器  
var useragent = navigator.userAgent;  
if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {  
    // 这里警告框会阻塞当前页面继续加载  
    alert('请使用微信进行访问');  
    // 以下代码是用javascript强行关闭当前页面  
    var opened = window.open('about:blank', '_self');  
    opened.opener = null;  
    opened.close();  
}  
</script> 