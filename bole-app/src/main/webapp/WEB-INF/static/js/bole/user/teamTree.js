//此为在jsp中需要设置，作为根节点和团队参数使用.
var userId = $("#userId").val();
var name = $("#name").val();

var setting = {
	view : {
		dblClickExpand : dblClickExpand,
		// addHoverDom : addHoverDom,
		// removeHoverDom : removeHoverDom,
		selectedMulti : false,
		fontCss: getFont,
		nameIsHTML: true
	},
	
	edit : {
		enable : false
	},
	
	data : { // 必须使用data
		simpleData : {
			enable : true,
			idKey : "id", // id编号命名 默认
			pIdKey : "pId", // 父id编号命名 默认
			rootPId : 0
		// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
		}
	},
	
	async : {
		type : 'GET',
		enable : true,
		url : appRootUrl + '/user/teamSubs.json',
		dataType : "json",
		autoParam : [ "id" ],
		otherParam : {

		},
	
	},
	
	callback : {
		onAsyncSuccess : zTreeOnAsyncSuccess,
		onClick : onClick
	}

};

var zNodes = [ {
	name : name,
	id : userId,
	pId : userId,
	isParent : true,
	open : "true",
	font:{'font-weight':'bold'}
} ];

function getFont(treeId, node) {
	return node.font ? node.font : {};
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	
	var data = msg.data;
	var subTreeNodes = [];
	
	var clickUrl = appRootUrl +"/user/agentView?userId=";
	var item;
	for (i = 0; i < data.length; i++) {
		item = data[i];
		var tmpNode = {
			id : item.id,// 节点id
			name : item.name,// 节点名
			pId : item.id,
			isParent : item.is_parent,
//			url: clickUrl + item.id,
//			target:"_self",
		};
		console.log(item.name + "--- active = " + item.active);
		if (item.active == 0) {
			tmpNode = {
					id : item.id,// 节点id
					name : item.name,// 节点名
					pId : item.id,
					isParent : item.is_parent,
					font:{'color':'red'},
//					url: clickUrl + item.id,
//					target:"_self",
				};
		}
		
		subTreeNodes.push(tmpNode);
	}
	
	var zTree = $.fn.zTree.getZTreeObj("teamTree");
	zTree.removeChildNodes(treeNode);
	zTree.addNodes(treeNode, subTreeNodes);
	
};

function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	alert("数据出现异常。");
}

function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}

var newCount = 1;

function onClick(event, treeId, treeNode, clickFlag) {
	
	$("#userId").val(treeNode.id);
//	btn_link("/user/agentView?userId="+treeNode.id);
	
}

$(document).ready(function() {
	$.fn.zTree.init($("#teamTree"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("teamTree");
	var nodes = zTree.getNodes()[0];
	zTree.expandNode(nodes);
});
