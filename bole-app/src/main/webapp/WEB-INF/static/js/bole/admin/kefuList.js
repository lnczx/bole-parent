
function doKefuAction(userId) {

	$.modal({
        title: "操作",
        text: "请选择下面的操作",
        buttons: [
          { text: "充值", onClick: function(){ btn_link('/kefu/rechargeForm?userIdTo=' + userId) } },
          { text: "充值记录", onClick: function(){ btn_link('/user/rechargeList?userIdTo=' + userId) } },
          { text: "查看", onClick: function(){ btn_link('/user/agentView?userId=' + userId) } },
          { text: "修改", onClick: function(){ btn_link('/admin/kefuForm?userId=' + userId) } },
        ]
      });
}