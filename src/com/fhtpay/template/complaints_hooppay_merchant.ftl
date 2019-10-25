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
	尊敬的${merNo}商户:
  </h2>
  <h2>您好！我司接到订单号：${tradeNo} 持卡人投诉，请及时安排沟通处理。</h2>
  <p>
  投诉原因是 ${reason}, <br/>
  持卡人要求${complaintsDesc},<br/>
  请核实情况并回复，投诉处理截止日期为 ${deadline}, <br/>
  </p>
 
  <p>恭祝商祺！</p>
  <p>-------------------------------------------------------------------------------------------------------------------<BR/>
  <ul>
	<li>深圳市铂赢奥科技服务有限公司</li>
    <li>客服邮箱：<a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a></li>
    <li>客服电话：${tel}</li>
    <li>网址：<a href="http://www.bringallpay.com" target="_blank">http://www.bringallpay.com</a></li>
  </ul>
  <p>请注意：系统邮件，无需回复，有问题请联系我司客服人员处理。</p>
</div>
</body>
</html>