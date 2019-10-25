<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">
			* {margin: 0;padding: 0;margin-left:auto;margin-right:auto;}
			.body {
				font-size: 14px;
				width:100%;text-align:center;
				margin-left:auto;margin-right:auto;
			}
			p{margin: 6px auto;line-height: 1.5em;}
			.logo{text-align:left;margin-top:10px;margin-left:10px;}
			.headline{height:50px;width:100%;background: url(bg.gif) repeat;}
			.table{width:85%;border-collapse: collapse;border: 1px solid #E6E6FA;}
			.registertip{text-align:center;height:50px;font-weight: bold;font-size:18px;margin-top:0px;color:#804040;border: 1px solid #E6E6FA;}
			.tdtext{font-size:13px;padding-left:15px;padding-top:5px;text-align:left;}
			.tdbottom{font-size:13px;text-align:center;padding-top:30px;padding-bottom:5px;border-top:1px solid #E6E6FA;}
		</style>
	</head>
	<body class="body">
		<div class="logo">
			<b><font face=times color="#c41200" size=10>BringAll</font><font face=times color="#f3c518" size=10>PAY</font></b>		</div>
		<div style="height:5px;background:#0039b6;width:100%;margin-bottom:20px;"></div>
		<div class="context">
			<table class="table">
				<tr>
					<td class="registertip" colspan="2"><strong>${tradeNo}  Had you satisfied with the item that you received?</strong></td>
				</tr>
				<tr>
					<td class="tdtext" colspan="2" style="padding-bottom:5px;height:250px;">
						Hello,
						<p>This is Chara  from <a href="${helpwebsite}" target="_blank">${helpwebsite}</a>. How are you recently?</p>
						<p>Here I just mail to make a simple survey about the service of ${website} , where you placed an order a few days ago. 

It will take you a few minutes.<br/>If you're seriously  answer to this question will have the chance to get the same value  gifts  as the previous order.</p>
						<p>1) Do you think they reply your mail or left message very fast? (   )</p>
						<p>A.Very fast&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   B.Normal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   C.Never reply</p>
						<p>2) Is the brand and style of the item you received same as described on his web? If not, what is the difference? (   

)</p>
						<p>A.Good quality&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   B.The brand is fake&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   C.The item not as 

the website described   D.Poor quality</p>
						<p>3) Are you satisfied with the quality of the item? Why?</p>
						<p>4) Any suggestion or advice will be welcome.</p>
						<p>Thanks for your time; your specific answer will be much appreciated. We and our merchant will try our best to 

improve the service. Wait for your feedback.</p>
				  </td>
				</tr>
				<tr>
					<td height="50">
						<span style="margin-left:200px;">Have a nice day!</span>
					</td>
				</tr>
				<tr>
					<td class="tdbottom" colspan="2">E-mail: <a href="mailto:${replayEmail}" target="_blank">${replayEmail}</a><br/>
					<br/>
				  </td>
				</tr>
			</table>
		</div>
	</body>
</html>