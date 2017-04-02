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
	<h1 class="demos-title">等级说明</h1>
	</header>
	
	<article class="weui-article">
	<section>
        <h2 class="title">【设计原则】</h2>
        <p>博乐广西麻将会员采用复利返利原则，推荐的会员越多，并且层级的会员越多，返利越多.</p>
        <p>等级设计上，共分为6级会员，会员默认为一级，当推荐直属会员达到三人，则自动升一级，最高为6级.<p>
         <p>注：会员推荐没有上限.<p>
        <h2 class="title">【升级说明】</h2>
        <p>你的升级的原则跟直属的升级密切相关，当M1级有直属3个激活会员达到同级，则你会自动升为M2.<p>
        <p>M2,M3,M4,M5, 每下级有直属2个激活会员，则你会自动升级.<p>
        <p>注：必须在系统中邀请码注册激活后，才能算一个有效的会员.</p>
        
        
        <h2 class="title">【等级表格】</h2>
        <div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="20">级别</th>
					<th width="30">升级条件</th>
					<th width="80">等级返利</th>
				</tr>
			</thead>
			<tbody>

					<tr class="text-c">
						<td>M1</td>
						<td>新开通的会员</td>
						<td>1代充值数的5%</td>
					</tr>
					
					<tr class="text-c">
						<td>M2</td>
						<td>直接推荐的激活会员达到3人</td>
						<td>1代充值数的5%<br>
							2代充值数的4%<br>
						</td>
					</tr>
					
					<tr class="text-c">
						<td>M3</td>
						<td>直接推荐的激活会员中有2人达到M2</td>
						<td>1代充值数的5%<br>
							2代充值数的4%<br>
							3代充值数的3%<br>
						</td>
					</tr>
					
					<tr class="text-c">
						<td>M4</td>
						<td>直接推荐的激活会员中有2人达到M3</td>
						<td>1代充值数的5%<br>
							2代充值数的4%<br>
							3代充值数的3%<br>
							4代充值数的2%<br>
						</td>
					</tr>
					
					<tr class="text-c">
						<td>M5</td>
						<td>直接推荐的激活会员中有2人达到M4</td>
						<td>1代充值数的5%<br>
							2代充值数的4%<br>
							3代充值数的3%<br>
							4代充值数的2%<br>
							5代充值数的1%<br>
						</td>
					</tr>
					
					<tr class="text-c">
						<td>M6</td>
						<td>直接推荐的激活会员中有2人达到M5</td>
						<td>1代充值数的5%<br>
							2代充值数的4%<br>
							3代充值数的3%<br>
							4代充值数的2%<br>
							5代充值数的1%<br>
							6代充值数的0.5%<br>
						</td>
					</tr>

			</tbody>
		</table>

	</div>
		
	<h2 class="title">【满级返利说明，每人充300】</h2>
        <div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="20">级别</th>
					<th width="30">人数</th>
					<th width="30">比例</th>
					<th width="80">返利</th>
				</tr>
			</thead>
			<tbody>

					<tr class="text-c">
						<td>第1层</td>
						<td>3人</td>
						<td>5%</td>
						<td>45</td>
					</tr>
					
					<tr class="text-c">
						<td>第2层</td>
						<td>9人</td>
						<td>4%</td>
						<td>108</td>
					</tr>
					
					<tr class="text-c">
						<td>第3层</td>
						<td>27人</td>
						<td>3%</td>
						<td>243</td>
					</tr>
					
					<tr class="text-c">
						<td>第4层</td>
						<td>81人</td>
						<td>2%</td>
						<td>486</td>
					</tr>
					
					<tr class="text-c">
						<td>第5层</td>
						<td>243人</td>
						<td>1%</td>
						<td>729</td>
					</tr>
					
					<tr class="text-c">
						<td>第6层</td>
						<td>729人</td>
						<td>0.5%</td>
						<td>1093</td>
					</tr>
					
					<tr class="text-c">
						<td>总共</td>
						<td>1092人</td>
						<td></td>
						<td>2704.5</td>
					</tr>
					
					

			</tbody>
		</table>

	</div>
	
	
	<h2 class="title">【一人充300各等级返利说明】</h2>
        <div id="DataTables_Table_0_wrapper " class="dataTables_wrapper no-footer">
		<table id="DataTables_Table_0" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="20">级别</th>
					<th width="30">比例</th>
					<th width="80">返利</th>
				</tr>
			</thead>
			<tbody>

					<tr class="text-c">
						<td>第1层</td>
						
						<td>5%</td>
						<td>15</td>
					</tr>
					
					<tr class="text-c">
						<td>第2层</td>
						
						<td>4%</td>
						<td>12</td>
					</tr>
					
					<tr class="text-c">
						<td>第3层</td>
						
						<td>3%</td>
						<td>9</td>
					</tr>
					
					<tr class="text-c">
						<td>第4层</td>
						
						<td>2%</td>
						<td>6</td>
					</tr>
					
					<tr class="text-c">
						<td>第5层</td>
						
						<td>1%</td>
						<td>3</td>
					</tr>
					
					<tr class="text-c">
						<td>第6层</td>
						
						<td>0.5%</td>
						<td>1.5</td>
					</tr>
					
					
					
					

			</tbody>
		</table>

	</div>
	
	
    <h2 class="title">【返利提现】</h2>
    <p>必须累积返利满<font color="red">100</font>以上才能领取，每次领取为<font color="red">100</font>的整数倍.申请领取返利后需隔<font color="red">7</font>天方可再次领取.<p>
    
    <h2 class="title">【用户协议】</h2>
    <p>广西博乐麻将用户协议<p>
    <p>抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防上当受骗。</p>
    <p>适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。</p>
    <p>1.您充分理解并同意：广西博乐麻将会按照国家相关要求将您的实名注册信息运用于防沉迷系统之中，即广西博乐麻将可能会根据您的实名注册信息判断您是否年满18周岁，从而决定是否对您的游戏账号予以防沉迷限制。并且，若您未满18周岁，广西博乐麻将将会根据有关规定及您家长的要求对您的账号进行限制。</p>
    <p>2.如果您长期连续未登陆，您在游戏内的游戏数据可能会由于技术原因被删除，对此广西博乐麻将不承担任何责任。</p>
    <p>3.本游戏不支持任何的虚拟货币、虚拟物品等。</p>
    <p>4.广西博乐麻将有权根据需要不时修订本协议条款。上述内容已经正式公布即生效。您可以在广西博乐麻将的相关页面查阅最新版本的协议条款。</p>
    <p>5.本解释权归广西博乐麻将所有.</p>
    </section>
	
	</article>
	
	<%@ include file="../shared/pageFooter.jsp"%>
	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>
</body>
</html>
