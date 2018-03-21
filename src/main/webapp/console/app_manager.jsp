<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/inc/taglibs_and_ctx.jsp" %>

<html>
<head>
    <c:set var="uploadify" value="${js}/uploadify"/>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>APP管理</title>

    <link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
    <link rel="stylesheet" href="${uploadify}/uploadify.css" type="text/css" media="all"/>
    <style type="text/css">
        body {
            font-family: "微软雅黑", "宋体", Arial, sans-serif;
            font-size: 12px;
        }

        label {
            width: 120px;
            display: block;
        }
    </style>
    <!-- 加载jquery -->
    <script type="text/javascript" src="${jqueryjs}"></script>

    <!-- 加载jquery-easyui -->
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${uploadify}/jquery.uploadify.js"></script>


    <script type="text/javascript" language="JavaScript">

        $(function () {
            var appName = "";
            var versionName = "";
            var versionCode = "";
            var forceUpdate = false;
            var desc = "";
            var host = window.location.host;

            $("#upload_dialog").dialog({
                buttons: [{
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var isValid = $("#apkInfo").form('validate');
                        if (isValid) {
                            saveApp();

                        }
                    }
                }, {
                    text: '取消',
                    handler: function () {
                        $("#upload_dialog").dialog('close');
                    }
                }]
            });


            $('#test').datagrid({

                iconCls: 'icon-save',
                width: '100%',
                height: '600',
                nowrap: false,
                striped: true,
                singleSelect: true,
                method: 'GET',
                url: '${ctx}/listApp.do',
                queryParams: {pageNumber: 1, pageSize: 10},
                remoteSort: true,
                idField: 'id',
                columns: [[
                    {field: 'appKey', title: 'APPKey'},
                    {field: 'appName', title: '版本号'},
                    {field: 'desc', title: '说明',formatter:function (val, rec, index) {
                        return val?val.replace("\n","<br>"):"";
                    }},
                    {
                        field: 'opt', title: '操作', width: 140, align: 'center',
                        formatter: function (value, rec, index) {
                            var deleteLink = "<a href='javascript:void(0)' onclick='deleteApk(" + rec.id + ")'>删除</a> ";
                            var downloadUrl = "${ctx}/downloadapk.do?deviceId=&type=RMS_CLIENT_APK&id=" + rec.id;
                            var downloadLink = "<a href='" + downloadUrl + "'>下载</a>";
                            var qrcodeContent = "http://" + host + downloadUrl;
                            var barcodeLink = "<a href='javascript:void(0)' onclick='showBarcode(\"" + qrcodeContent + "\")'>二维码</a> ";
                            return deleteLink;
                        }
                    }
                ]],
                pagination: true,
                pageNumber: 1,
                pageSize: 10,
                toolbar: [{
                    id: 'add',
                    text: '新增',
                    iconCls: 'icon-add',
                    handler: function () {
                        $('#upload_dialog').dialog('open');
                    }
                }]
            });
            var p = $('#test').datagrid('getPager');
            $(p).pagination({
                onBeforeRefresh: function () {
                    //alert('before refresh');
                }
            });
        });

        function saveApp() {
            $.ajax({
                type: "POST",
                url: '${ctx}/saveApp.do',
                cache: false,
                dataType: "text",
                data: {
                    "appKey": $("#appKey").val(),
                    "appName": $("#appName").val(), "desc": $("#desc").val()
                },

                success: function () {
                    $.messager.alert("提示", "保存成功！", "info", function () {
                        $('#upload_dialog').dialog('close');
                        refreshGrid();
                    });

                }
            });
        }


        function getSelected() {
            var selected = $('#test').datagrid('getSelected');
            if (selected) {
                alert(selected.code + ":" + selected.name + ":" + selected.addr + ":" + selected.col4);
            }
        }

        function getSelections() {
            var ids = [];
            var rows = $('#test').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].code);
            }
            alert(ids.join(':'));
        }
        function refreshGrid() {
            $('#test').datagrid('reload');
        }

        function clearSelections() {
            $('#test').datagrid('clearSelections');
        }
        function selectRow() {
            $('#test').datagrid('selectRow', 2);
        }
        function selectRecord() {
            $('#test').datagrid('selectRecord', '002');
        }
        function unselectRow() {
            $('#test').datagrid('unselectRow', 2);
        }

        function showBarcode(qrcodeContent) {
            var encodeContent = encodeURIComponent(qrcodeContent);
            var newImgSrc = "${ctx}/barcode/encode.do?content=" + encodeContent;
            $('#QRCodeImage').attr('src', newImgSrc);
            $('#barcode_dialog').dialog({
                title: 'QR码',
                width: 350,
                height: 350,
                closed: true,
                cache: false,
                modal: true
            });
            $('#barcode_dialog').dialog("open");
        }
        function deleteApk(id) {
            $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (data) {
                if (data) {
                    $.ajax({
                        type: "POST",
                        url: '${ctx}/deleteApp.do?id=' + id,
                        cache: false,
                        dataType: "text",
                        success: function () {
                            $.messager.alert("提示", "删除成功！", "info", function () {
                                refreshGrid();
                            });

                        }
                    });
                }
            });

        }


    </script>

</head>
<body>
<table id="test"></table>
<div id="upload_dialog" data-options="iconCls:'icon-save'" title="上传apk"
     style="background:#fafafa;padding:5px;width:450px;height:300px;" closed="true">
    <form id="apkInfo" method="post" enctype="multipart/form-data">

        <div>
            <label for="appKey">AppKey:</label>
            <input class="easyui-validatebox" type="text" id="appKey" data-options="required:true"/>
        </div>
        <div>
            <label for="appName">App名称:</label>
            <input class="easyui-validatebox" type="text" id="appName" data-options="required:true"/>
        </div>

        <div>
            <label for="desc">说明:</label>
            <textarea class="easyui-validatebox" id="desc" data-options="required:true"></textarea>
        </div>
    </form>
</div>

<div id="barcode_dialog" class="easyui-window" closed="true">
    <img id="QRCodeImage" src="#" alt="QRCode Image"/>
</div>
<div style="color: #ff0000;">呵呵</div>
</body>
</html>
