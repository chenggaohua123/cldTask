<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Confirm Goods</title>

<style type="text/css">
body {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 12px;
	font-weight: normal;
	font-style: normal;
}

body,h1,h2,h3,p,ul,li,cite {
	margin: 0;
	padding: 0;
}

h1 {
	
	width: 960px;
	height: 80px;
}

h1 img {
	margin-top: 20px;
	margin-left: 6px;
}

h2 {
	font-size: 14px;
	font-weight: bold;
	height: 36px;
	line-height: 36px;
	text-indent: 1em;
	background-color: #F3F3F3;
}

p {
	margin: 12px auto;
	text-indent: 1em;
	line-height: 1.5em;
}

span {
	color: #F00;
}

strong {
	color: #090;
}

em {
	color: #06C;
	font-style: normal;
	text-decoration: underline;
}

a {
	text-decoration: underline;
	color: #06C;
}

a:hover {
	text-decoration: underline;
}

table {
	width: 90%;
	line-height: 28px;
	border: 1px solid #E7E7E7;
	border-bottom: none;
	margin-left: 1em;
}

table caption {
	text-align: left;
	text-indent: 2em;
	font-family: Verdana, Geneva, sans-serif;
	font-size: 12px;
}

table caption span {
	display: block;
	color: #000000
}

table tbody {
	font-size: 11px;
}

table tbody th {
	width: 180px;
	font-weight: normal;
	text-align: right;
	padding-right: 8px;
	border-bottom: 1px solid #E7E7E7;
	border-right: 1px solid #E7E7E7;
}

table tbody td {
	border-bottom: 1px solid #E7E7E7;
	padding-left: 8px;
}

ul {
	margin-left: 4em;
}

li {
	list-style: square;
	line-height: 24px;
}

.footer {
	margin-top: 24px;
	color: #666;
	padding-left: 12px;
	padding-top: 8px;
	padding-bottom: 12px;
	background-color: #F7F7F7;
}

.footer h3 {
	font-weight: normal;
	text-indent: 20px;
	font-size: 12px;
	color: red;
}

.footer ul {
	margin-left: 0em;
	margin-top: 8px;
	margin-bottom: 8px;
	color: #666;
	font-size: 11px;
}

.footer ul li {
	list-style: none;
	line-height: 18px;
}
.STYLE1 {
	color: #FF0000;
	font-weight: bold;
}
</style>
</head>
<body>
	<div>
	  <h2>尊敬的${merNo}商户:</h2>
		<p align="left">您好!		</p>
		<p align="left">感谢您使用铂赢奥支付服务，您有一笔成功订单来自网站<a href="${merwebsite}" target=_blank>${merwebsite}</a>.
		</p>
		<table border="0" cellspacing="0" cellpadding="0">
			<caption>
				<font color="#FF6600">订单信息:</font>
			</caption>
			<tbody>
				<tr>
					<th>订单号:</th>
					<td>${order}</td>
				</tr>
				<tr>
					<th>交易流水号:</th>
					<td>${TradeNo}</td>
				</tr>
				<tr>
					<th>交易时间:</th>
					<td>${tradeDatetime}</td>
				</tr>
				<tr>
					<th>交易金额:</th>
					<td>${currency} ${sourceamount}</td>
				</tr>
				<tr>
					<th>持卡人邮箱:</th>
					<td>${cardHolderEmail}</td>
				</tr>
			</tbody>
		</table>

		<p>请注意:</p>
		<ul>
			<li>请及时联系持卡人并提供货运信息，请将货运单号上传到我司商户系统。</li>
			<li>预知该笔订单详情，请登录我司商户系统查询。</li>
			<li>该笔订单的消费信息
				:<span class="STYLE1">&quot;${acquirer}&quot;</span>将会在持卡人的信用卡上账单上显示。</li>
			<li>E-mail:<a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a></li>
		</ul>
		<p>恭祝商祺！</p>
		<p>&nbsp;</p>		<ul>
			<li>深圳市铂赢奥科技服务有限公司。</li>
			<li>客服电话：${tel}</li>
			<li>客服邮箱：<a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a></li>
			</ul>
		<div class="footer">
			<h3>系统邮件 无需回复。有任何问题请联系我司客服处理。</h3>
	  </div>
</div>
</body>
</html>
