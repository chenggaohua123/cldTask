<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Confirm Goods</title>

<style type="text/css">
body {
	font: 12px/1.2 Arial, Helvetica, sans-serif;
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
	margin: 6px auto;
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

ul {
	margin-left: 1.5em;
}

li {
	list-style: square;
	line-height: 24px;
}
</style>
</head>
<body>
	<div style="margin:0px 20px;width:80%;">
		<h2>Dear ${email}:</h2>
		<p>
			We are glad to inform you that your merchandise has been shipped out
			by our merchant to you through (<span>${Iogistics}</span>) . The
			tracking No. is <span>${trackno}</span>. please check the status
			through the link of
		</p>
		<p>
			<span>${Iogistics}</span> <a href="${IogisticsUrl}" target="_blank">${IogisticsUrl}</a>
		</p>
		<p>You can see the picture of shipping document.</p>

		<p>
			-----------------------------------------------------------------------------------------------------------------------------------------------<BR />
			The order details are as follows:<BR />
			-----------------------------------------------------------------------------------------------------------------------------------------------
		</p>
		<p>Seller Order No.:${order}</p>
		<p>Seller Website:&nbsp;<a href="${merwebsite}" target=_blank>${merwebsite}</a></p>
		<p>&nbsp;</p>
		<p>Payment No.:${TradeNo}</p>
		<p>Payment Date&Time: ${tradeDateTime}</p>
		<p>Amount:${sourceamount} ${Currency}</p>
		<p>&nbsp;</p>
		<p>
			The shipment information we will inform you by email after shipped out, please update the information by the track link.
			Any queries about the shipment please contact the merchant by the contact us subpage of online  site . <em></em>
		</p>
		<p>&nbsp;</p>
		-----------------------------------------------------------------------------------------------------------------------------------------------<BR />
		<p>Thank you for your attention and wish you have a good day. 		</p>
		<ul>
			<li>Tel: ${tel}</li>
			<li>E-mail: <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a>			</li>
			<li>Website:<a href="${helpwebsite}" target="_blank">${helpwebsite}</a>			</li>
		</ul>
		<div class="footer">
			<p>Notes:Please don't reply this e-mail directly as it was sent
				automatically!</p>
		</div>
	</div>
</body>
</html>