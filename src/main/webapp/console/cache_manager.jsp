<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/inc/taglibs_and_ctx.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>消息控制台</title>
        
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>  
    
    <!-- 加载jquery -->  
    <script type="text/javascript" src="${jqueryjs}"></script>  
    
    <!-- 加载jquery-easyui -->
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>  
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>    
 
    
    <style type="text/css"> 
     body{font-family: "微软雅黑", "宋体", Arial, sans-serif; font-size: 12px; } 
    </style>
    <script type="text/javascript">
    $(function () {   
 
    });
        function clearCache(cacheName){
		    $.ajax({
					  type: "POST",
					  url: '${ctx}/cache/clear.do?cacheName='+cacheName,
					  cache: false,
					  dataType: "text",
					  success: onSuccess
					});	
		}
		
		function onSuccess(data)
              {
        		  $.messager.alert('Warning','清除成功'); 
              }
    </script>

</head>
<body> 

<table class="easyui-datagrid" data-options="singleSelect:true, striped:true">  
    <thead>  
        <tr>  
            <th data-options="field:'code',width:150">缓存名</th>  
            <th data-options="field:'name',width:150">说明</th>  
            <th data-options="field:'operation',width:150">操作</th>  
        </tr>  
    </thead>  
    <tbody>  
        <tr>  
            <td>terminalCache</td><td>设备信息缓存</td><td><a href="#" onclick="clearCache('terminalCache')" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:false">清空缓存</a></td>  
        </tr>  
        <tr>  
            <td>userCache</td><td>人员信息缓存</td><td><a href="#" onclick="clearCache('userCache')" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:false">清空缓存</a></td>  
        </tr>  
        <tr>  
            <td>sdeInfoCache</td><td>Sde信息缓存</td><td><a href="#" onclick="clearCache('sdeInfoCache')" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:false">清空缓存</a></td>  
        </tr> 
    </tbody>  
</table>
</body>
</html>
