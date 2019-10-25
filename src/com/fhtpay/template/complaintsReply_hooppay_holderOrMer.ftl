<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Protest trade</title>

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
			padding-top:10px;
			padding-bottom:10px;
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
  <h2>
	Hello!</h2>
  <h2>The complaint of order ${TradeNo} has got a new reply.</h2>
  <h2>The details are as follows:</h2>
  <h2>${ReplyTable}</h2> 
 
 	 <p>-------------------------------------------------------------------------------------------------------------------<BR/>
  <p>
			-----------------------------------------------------------------------------------------------------------------------------------------------<BR />
			The order details are as follows:<BR />
			-----------------------------------------------------------------------------------------------------------------------------------------------
  </p>
		<p>Seller Order No.:${order}</p>
		<p>Seller Website:${merwebsite}&nbsp;</p>
		<p>&nbsp;</p>
		<p>Payment No.:${TradeNo}</p>
		<p>Payment Date&Time: ${tradeDateTime}</p>
		<p>Amount:${sourceamount} ${Currency}</p>
 
 	 <p>-------------------------------------------------------------------------------------------------------------------<BR/>
	 If you have any question, please don't hesitate to contact us!</p>
  <ul>
    <li>Tel: ${tel}</li>
    <li>Fax: ${Fax}</li>
    <li>E-mail: <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a></li>
    <li>Website:<a href="${helpwebsite}" target="_blank">${helpwebsite}</a></li>
  </ul>
  <p>Notes:Please don't reply this e-mail directly as it was sent automatically!		
  </p>
</div>
</body>
</html>