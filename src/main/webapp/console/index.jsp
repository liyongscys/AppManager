<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/inc/taglibs_and_ctx.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>APP管理</title>
        
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/> 
    
    <!-- 加载jquery -->  
    <script type="text/javascript" src="${jqueryjs}"></script>  
    
    <!-- 加载jquery-easyui -->
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        // 判断当前的window对象是否是top对象   
        if (window!=top){
        	// 如果不是，将top对象的网址自动导向被嵌入网页的网址
        	top.location.href =window.location.href; 
        } 
        
        function showTopWindow(adname, id) {
            top.showMyWindow("修改密码", 'news.sina.com.cn', 707, 440);
        }
                
    </script>
    
    <style type="text/css"> 
     body{font-family: "微软雅黑", "宋体", Arial, sans-serif; font-size: 12px; }
    /* 顶部 */ 
     .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    
    .l-topmenu{
        background: #102c4a url(${ctx}/images/header_bg.png) repeat-x;
    }
    .l-topmenu-logo{ color:#4F4F4F;font-size: 18px; padding-left:320px; line-height:40px;background:url('${ctx}/images/logo.png') no-repeat;}
    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;right:30px; top:2px;color:#070A0C;}
    .l-topmenu-welcome a{ color:#4F4F4F; text-decoration:underline} 


    </style>
    <script type="text/javascript">
        var initChangePasswordDialog=false;
        var changePasswordUrl="${ctx}/changePassword.do";
        //在右边center区域打开菜单，新增tab
		function addTab(title, url) {
			if ($("#tabs").tabs('exists', title)) {
				$("#tabs").tabs('select', title);
			} else {
				var content = '<iframe scrolling="yes" frameborder="0"  src="${ctx}'+url+'" style="width:100%;height:100%;"></iframe>'; 
				$("#tabs").tabs('add', {
					title : title,
					content : content,
					closable : true
				});
			}
		}
        
	    $(function () {
	    	//实例化树形菜单
	    	$("#tree").tree({
	    		url:'${ctx}/console/tree_data.json',
	    		onClick : function (node) {
	    			if (node.attributes&&node.attributes.url) {
	    				addTab(node.text, node.attributes.url);
	    			}
	    		}
	    	});
	    	
	    	$.extend($.fn.validatebox.defaults.rules, {  
                equals: {  
                    validator: function(value,param){  
                          return value == $(param[0]).val();  
                    },  
                message: '两次输入的新密码不一致。'  
             } 
	    	});
	    });
	    
	    function showChangePasswordDialog() {
            $("#changePasswordDialog").window("open");
            $("#changePasswordForm").form("clear");
        }
	    
	    function submitForm(){
	    	 $("#changePasswordForm").form("submit", {
                 url: changePasswordUrl,
                 onsubmit: function () {
                 	var isValid = $(this).form('validate');
     				if (!isValid){
     					$.messager.progress('close');	// hide progress bar while the form is invalid
     				}
     				return isValid;	// return false will stop the form submission
                 },
                 success: function (result) {                	
                 	var data = eval('(' + result + ')');  // change the JSON string to javascript object  
                    if (data.success){ 
                    	$('#changePasswordDialog').window('close');
                    }                    
                	$.messager.alert("操作提示", data.message,"info");
                    
                 }
           });
	    }
	    
        
    </script>

</head>
<body class="easyui-layout"> 

    <div id="topmenu" class="l-topmenu" data-options="region:'north',border:false" style="height:60px;padding:10px">
		<div class="l-topmenu-logo">&nbsp;APP管理</div>
	    <div class="l-topmenu-welcome">
	        <a href="javascript:void(0)" onclick="javascript:top.showChangePasswordDialog()" class="l-link2">修改密码</a>
	        <span class="space">|</span>
	        <a href="${ctx}/logout" class="l-link2">切换用户</a> 
	        <span class="space">|</span>
	        <a href="${ctx}/logout" class="l-link2">注销</a>
	    </div>
	</div>
	
	<div data-options="region:'west',split:true" title="功能" style="width:200px;padding1:1px;overflow:hidden;">
		<ul id="tree" class="easyui-tree" ></ul>
	</div>	
	<div data-options="region:'south',border:false" style="height:40px;background:#D2E0F2;padding:10px;">
	    <span>鑫干线（北京）科技股份公司&trade; Copyright &copy; 2018, Version:1.0</span>
	</div>
	<div data-options="region:'center',title:''">
	    <div id="tabs" class="easyui-tabs" fit="true" border="false" >
            <div title="首页" data-options="closable:true" ></div>
        </div>
	</div>
	
	<div id="changePasswordDialog" class="easyui-window" style="width:300px" 
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,title:'修改密码'" >
	     <div style="padding:10px 0 10px 10px">
	     <form id="changePasswordForm" method="post" >
            <table>
                <tr>
                    <td>旧密码:</td>
                    <td>
                    <input id="oldPlainPassword" name="oldPlainPassword" 
                          type="password" class="easyui-validatebox"  
                          data-options="required:true,missingMessage:'旧密码不能为空'" /></td>
                </tr>
                <tr>
                    <td>新密码:</td>
                    <td><input id="newPlainPassword" name="newPlainPassword" type="password" class="easyui-validatebox" 
                    data-options="required:true,missingMessage:'新密码不能为空'"/></td>
                </tr>
                <tr>
                    <td>再次输入新密码:</td>
                    <td><input id="newPlanPasswordConfirm" name="newPlanPasswordConfirm" type="password" class="easyui-validatebox" 
                    data-options="required:true,missingMessage:'新密码不能为空',invalidMessage:'两次输入的密码不一致'" validType="equals['#newPlainPassword']" /></td>
                </tr>
            </table>
        </form>
        </div>
        <div style="text-align:center;padding:5px">

              <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:submitForm()" iconcls="icon-save">提交</a>
              <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#changePasswordDialog').window('close')" iconcls="icon-cancel">取消</a>
        </div>
	 </div>
</body>
</html>
