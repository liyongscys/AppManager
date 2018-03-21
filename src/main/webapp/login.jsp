<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/inc/taglibs_and_ctx.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-store"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
		<title>消息控制台</title>
        <link type="text/css" rel="stylesheet" href="css/login.css" />
        <script type="text/javascript">
           if (window!=top) // 判断当前的window对象是否是top对象
                top.location.href =window.location.href; // 如果不是，将top对象的网址自动导向被嵌入网页的网址
        </script>
	</head>
	<body onload="document.getElementById('username').focus()">
		<div id="infoBox">
			<div id="info">
				<form name="loginform" action="" method="post">

					<table id="tnnd" border="0" cellspacing="0" cellpadding="3">
						<tr>
							<td colspan="2" align="left" id="title">
							    <div style="padding:2px 2px;border-bottom:1px solid #ccc">登录消息控制台</div> 
							</td>
						</tr>
						<tr>
							<td align="right">用户名：</td>
							<td align="left">
								<input type="text" name="username" id="username"  
								    maxlength="30" width="200" value="admin"/>
							</td>
						</tr>
						<tr>
							<td align="right">密码：</td>
							<td align="left">
								<input type="password" name="password" maxlength="16" width="200" />
							</td>
						</tr>
						<tr>
						    <td align="right">&nbsp;</td>
							<td align="left">
								<input type="checkbox" name="rememberMe" />
								<font size="2">Remember Me</font>
							</td>
						</tr>
						<tr height="30">
							<td colspan="2" align="right" id="loginButton">
								<input type="submit" name="submit" value="登录" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
