<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>交易预警-返回原因</title>
<style type="text/css">
body{
	background:#f5f5f5;
	font: 14px "Arial",sans-serif;
}
#main{
	width:90%;
	margin:60px auto;
	padding:20px;
	border:1px solid #f2f1f1;
	border-radius: 5px;
	background:#fff;
	}
#transactions{
	font: 14px "Arial",sans-serif;
	line-height:25px;
    margin: 20px 10px 10px 0;  
}
table #transactions {
    clear: both;
    margin-bottom: 10px;
    text-align: left;
}
table {
    margin-bottom: 10px;
    border: 1px solid #DDD;
	width: 95%;
	border-collapse: collapse;
    border-spacing: 0;
}
td{
	border: 1px solid #ddd;
	padding:10px;
	}

</style>
</head>

<body>
   <div id="main">
    <h2>亲爱的 ${userNme}:</h2>
    <p id="transactions">${warnInfo}</p><br/> 
    <h4>提示:</h4> 
    <ul>
        <li>如果你想知道更多的交易信息，请登录管理系统查看</li><br/>
    </ul>
    <p>谢谢!</p>
     <h4>警告:此邮件为系统邮件,请不要回复邮件!</h4> 
  </div>
    
</body>
</html>