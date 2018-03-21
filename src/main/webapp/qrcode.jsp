<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/inc/taglibs_and_ctx.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>    
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>QRCode</title>
        
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
    <!-- 加载jquery -->  
    <script type="text/javascript" src="${jqueryjs}"></script>  
    
    <!-- 加载jquery-easyui -->
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>  
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script> 
 	<script> 
 
		$(function(){			
            var host=window.location.host;            
            showBarcode("http://"+host+"/rms");
		});
		
		function showBarcode(qrcodeContent){
			var encodeContent=encodeURIComponent(qrcodeContent);
			var newImgSrc="${ctx}/barcode/encode.do?content="+encodeContent;
			$('#QRCodeImage').attr('src', newImgSrc);
		}		
		
	</script>

</head>
<body>
	<img id="QRCodeImage" src="#" alt="QRCode Image" />
</body>
</html>
