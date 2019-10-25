<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Single notice of investigation</title>

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

    .STYLE1 {color: #FF0000}
</style>
</head>
<body>
<div style="margin:20px;width:80%;">
  <h2>
	尊敬的<span class="STYLE1">${merNo}</span>商户:</h2>
  <h2>您好！</h2>
  <h2>流水订单号为${tradeNo}的持卡人对该单有异议，向银行发起调查。调查原因是:${reason}。</h2>
  <p>请注意：</p>
  <p>调查单是正式拒付通知前的征兆，建议商户收到调查单之后及时与持卡人联系，沟通问题，解决问题，避免转换为拒付通知。.</p>
  <p>为避免调查单转换为拒付，建议商户直接发起全额退款。.</p>
  <p>另针对调查单中的拒付预警订单或调查单中状态直接显示退款的订单，银行为避免该订单转换为拒付，在接到持卡人投诉时根据情况将此类订单直接进行了全额退款处理，请知悉。.</p>
  <p>-------------------------------------------------------------------------------------------------------------------<BR/>
    恭祝商祺！
  <ul>
    <li>深圳市铂赢奥科技服务有限公司</li>
    <li>电话：${tel}</li>
    <li>邮箱：${replayEmail}<a href="${website}" target="_blank"></a></li>
  </ul>
  <p>系统邮件 无需回复。有任何问题请联系我司客服处理。</p>
</div>
</body>
</html>