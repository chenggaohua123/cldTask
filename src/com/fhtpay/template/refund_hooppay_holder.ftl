<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

    </style>
</head>
<body>
<div style="margin:20px;width:80%;">
  <h2>Dear " ${email}":</h2>
  <p>We are the online payment platform, and we are glad to inform you that your following payment have just been refunded by us .</p>
  <p>It will be arrived at your bank account in about  10 to 30 business days which also depends on your issuing bank's refund cycle.</p>
  <p style="font-weight:bold;">
		-------------------------------------------------------------------------------------------------------------------<BR/>
		Refund details:<BR/>
		-------------------------------------------------------------------------------------------------------------------
  </p>
		<p>Order No.: ${order}</p>
		<p>Seller website: <a href="${merwebsite}" target=_blank>${merwebsite}</a></p>
		<BR/>
		<BR/>
		<p>Payment No.:${TradeNo}</p>
		<p>Payment Date&Time: ${tradeDateTime}</p>
		<p>Amount: ${amount} ${Currency}</p>
		<p>Refund Amount : ${iew_refundamount} ${view_currency}</p>
		<BR/>
  <p>Should you require any further assistance,please don't hesitate to contact our Customer Services department at <a href="mailto:${contactEmail}">${contactEmail}</a></p>
  <p>with the transaction details listed above or visit our knowledgebase at <a href="${helpwebsite}" target="_blank">${helpwebsite}</a></p>
  <p>Thank you for your attention and wish you have a good day. For more information and further assistance,
    please feel free to contact us by <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a>. </p>
	 <p>-------------------------------------------------------------------------------------------------------------------<BR/>
  <ul>
    <li>Tel: ${tel}</li>
    <li>Fax: ${Fax}</li>
    <li>E-mail: <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a></li>
    <li>Website:<a href="${helpwebsite}" target="_blank">${helpwebsite}</a></li>
  </ul>
  <p>-------------------------------------------------------------------------------------------------------------------<BR/>
    Notes:Please don't reply this e-mail directly as it was sent
  automatically!</p>
</div>
</body>
</html>
