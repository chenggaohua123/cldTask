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
  <h2>尊敬的<span class="STYLE1">${merNo} -- ${terNo}</span>商户:</h2>
  <h2>您好！</h2>
  <h2>流水单号为: ${tradeNo}的持卡人对该订单有异议，向银行发起拒付。 </h2>
  <h2>订单号为: ${order} </h2>
  <h2>拒付原因为: <span class="STYLE1">${reason}</span>。 </h2>
  <h2>请注意:</h2>
  <p>1、商户有权申述该拒付单，申诉资料需在${deadline}之前通过商户后台提供给我司。<br />
    2、申诉资料送呈后，银行会预先审核资料。对于没有按要求提供的资料和证据不足的资料，银行将无法向国际卡组织申诉，拒付成立。所以您在提交申诉资料的时候请仔细检查资料，确保资料的完整性。在申诉过程中，如国际卡组织判定我方胜诉，发卡行将承担全部损失，如国际卡组织判定我方败诉，商户需承担交易损失，在申诉过程中不产生任何额外费用。申诉失败后，商户有权发起预仲裁申请，但如果预仲裁申诉失败，商户除承担交易损失外，需要额外承担如下费用：<br />
    国际组织仲裁/依从手续费：USD250<br />
    国际组织仲裁/依从裁决费：USD250<br />
    合计USD500<br />
    3、在规定的时间之前没有提供资料或者没给与我方回应的，视为默认接受拒付。<br />
  4、建议与客人联系，争取持卡人取消拒付。提醒：有时可能是因为持卡人不能识别账单造成拒付，烦请联系持卡人的时候提示账单显示。<br />
  5、在接到拒付通知后主动退款，也认为默认接受拒付，所以请商户不要主动发起退款。</p>
  <p>-------------------------------------------------------------------------------------------------------------------<BR/>  
  恭祝商祺！
  <p>
  <ul>
    <li>深圳市铂赢奥科技服务有限公司</li>
    <li>客服电话：${tel}</li>
    <p>系统邮件 无需回复。有任何问题请联系我司客服处理。</p>
  </ul>
</div>
</body>
</html>