<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"/> 
      
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/js/jquerymobile/jquery.mobile-1.2.0.min.css" />
    <link rel="apple-touch-icon" href="img/tutsTouchIcon.png" />
    <script src="${ctx}/js/jquery/jquery-1.8.2.min.js"></script>
    <script src="${ctx}/js/jquerymobile/jquery.mobile-1.2.0.min.js"></script>
    <script type="text/javascript">
        $(document).bind('pageinit'){
    	    
        }
    </script>
</head>
<body>
	<div data-role="page">
		<div data-role="header" id="home">
			<h1>测试页面</h1>
		</div>
		<div data-role="content">
			<p>Hello!</p>
		</div>
		<div data-role="footer">
			<h1>Copyright © 2012 Cabletech, LLC</h1>
		</div>
	</div>
</body>
</html>