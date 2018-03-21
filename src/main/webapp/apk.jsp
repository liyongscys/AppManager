<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'apk.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="down">
		<div class="info">
			<p>版本信息：V2.2.22</p>
			<p>发布日期：2012年9月24日</p>
			<p>文件大小：6.2M</p>
			<p>系统要求Android2.0以上</p>
			<p>兼容分辨率：</p>
			<p>320*480</p>
			<p>480*800</p>
			<p>480*854</p>
			<p>540*960</p>
			<p>640*960</p>
			<p>720*1280</p>
			<p>800*1280</p>
		</div>

		<div class="btn_qr">
			<a class="btn"
				href="#">点击下载APK文件</a>
			<a class="x360"
				href="#">点击下载APK文件</a>

			<div class="qr">
				<img src="/mini/qr_v2.2.png">
			</div>
		</div>
	</div>
</body>
</html>
