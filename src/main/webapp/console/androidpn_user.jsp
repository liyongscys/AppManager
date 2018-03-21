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
     label{
			width:120px;
			display:block;
		}
    </style>
 	<script> 	    
 	    $(function(){
 	        var barcodeContent="";
	        $('#test').datagrid({
				
				iconCls:'icon-save',
				width:700,
				height:350,
				nowrap: false,
				striped: true,
				singleSelect:true,				
				method:'GET',
				url:'/rms/apn/listuser.do',
				queryParams:{ pageNumber:1,pageSize:10},
				remoteSort: true,
				idField:'username',			
				columns:[[
	              {field:'username',title:'username',width:60}
				]],
				pagination:true,
				pageNumber:1,
				pageSize:10
			});
		
	    });

		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			if (selected){
				alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			}
		}
		
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function refreshGrid(){
			$('#test').datagrid('reload');
		}		
		
		function clearSelections(){
			$('#test').datagrid('clearSelections');
		}
		function selectRow(){
			$('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#test').datagrid('unselectRow',2);
		}		

	</script>

</head>
<body>
	<table id="test"></table>
</body>
</html>
