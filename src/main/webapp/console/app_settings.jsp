<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/inc/taglibs_and_ctx.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>系统配置 </title>
        
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
        function saveChanges(){
            var params = '';
            var rows = $('#pg').propertygrid('getChanges');
            for(var i=0; i<rows.length; i++){
                params += rows[i].field +'=' + rows[i].value + '&';
            }        
            $.post(
                 '${ctx}/updateSettings.do', 
                 params,                
                 function(data) {
                     alert(data);
                 },
                 'json'
             );
        }
		
	</script>

</head>
<body>
    <div style="margin:10px 0;">       
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveChanges()">保存设置</a>
    </div>
	 <table id="pg" class="easyui-propertygrid" style="width:600px"
            data-options="url:'${ctx}/loadSettings.do',showGroup:true,scrollbarSize:0,columns: mycolumns"></table>
  <script>
        var mycolumns = [[
            {field:'name',title:'配置项',width:200,resizable:true},
            {field:'value',title:'值',width:400,resizable:true}
        ]];
    </script>

</body>
</html>
