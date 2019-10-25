<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Confirm Goods</title>

    <style type="text/css">
        body {
			font:12px/1.2 Arial,Helvetica,sans-serif;
            font-size: 12px;
            font-weight: normal;
            font-style: normal;
        }

        body, h1, h2, h3, p, ul, li, cite {
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

    .STYLE1 {color: #FF0000}
    </style>
</head>
<body>
<div style="margin:20px;width:80%;">
    <h2>Dear ${email}:</h2>
		<br/>
		Thank you for choosing BringAllPay as the payment service. <br>
		  Your transaction is pending. 
		<br>
		  <span class="STYLE1">We will notify you of the final payment status within 24 hours via email to</span> <a href="mailto:${replayEmail}" target="_blank">${email}</a>. 
		
		<p>-------------------------------------------------------------------------------------------------------------------<br>
		  Order No.: ${orderNo}<br /> 
		  Payment No.: ${tradeNo}<br />
		  Payment Date&amp;Time : ${tradeDateTime}<br /> 
		  Amount : ${sourceAmount} ${currency}</p>
		<p>-------------------------------------------------------------------------------------------------------------------
        </p>
		<p>Should you require any further assistance, please don't hesitate to contact our Customer Services department at <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a> with the transaction details listed above. or visit our knowledgebase <u><a href="${helpwebsite}" target=_blank>${helpwebsite}</a></u>. </p>
		<p><br>
		  Tel: ${tel}<br>
		  Fax: ${Fax}<br>
  E-mail: <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a> </p>
		<p>Notes:Please don't reply this e-mail directly as it was sent
		automatically!</p>
		<p><br>
  </p>
</div>
</body>
</html>
