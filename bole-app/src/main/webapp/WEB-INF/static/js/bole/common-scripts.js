//Global host+url
var host = window.location.host;
var appName = "bole";

var appRootUrl = "http://" + host + "/" + appName + "/";

//按钮时间
function btn_link(path) {
	location.href = appRootUrl + path;
}

//删除按钮事件
function btn_del(path) {
	var statu = confirm("确定要删除吗?");
    if(!statu){
        return false;
    }
    location.href = appRootUrl + path;
}

//统一返回
function btn_back() {
	history.back(-1);
}
