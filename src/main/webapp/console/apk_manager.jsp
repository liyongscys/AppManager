<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/inc/taglibs_and_ctx.jsp" %>

<html>
<head>
    <c:set var="uploadify" value="${js}/uploadify"/>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>APK管理</title>

    <link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
    <link rel="stylesheet" href="${uploadify}/uploadify.css" type="text/css" media="all"/>
    <style type="text/css">
        body {
            font-family: "微软雅黑", "宋体", Arial, sans-serif;
            font-size: 12px;
        }

        label {
            /*width: 120px;*/
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
                    text: '上传APK',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var isValid = $("#apkInfo").form('validate');
                        if (isValid) {
                            $("#file_upload").uploadify('upload', '*');
                        }
                    }
                }, {
                    text: '取消',
                    handler: function () {
                        $("#upload_dialog").dialog('close');
                    }
                }]
            });

            initUploadify();

            $('#test').datagrid({

                iconCls: 'icon-save',
                width: '100%',
                height: '600',
                nowrap: false,
                striped: true,
                singleSelect: true,
                method: 'GET',
                url: '${ctx}/listapk.do',
                queryParams: {pageNumber: 1, pageSize: 10},
                remoteSort: true,
                idField: 'id',
                columns: [[
                    {field: 'appKey', title: 'APPKey'},
                    {field: 'versionCode', title: '版本号'},
                    {field: 'versionName', title: '版本名'},
                    {field: 'forceUpdate', title: '是否強制更新'},
                    {field: 'fileName', title: '文件名'},
                    {field: 'uploadTime', title: '发布时间', width: 160},
                    {field: 'desc', title: '说明'},
                    {
                        field: 'opt', title: '操作', width: 140, align: 'center',
                        formatter: function (value, rec, index) {
                            var deleteLink = "<a href='javascript:void(0)' onclick='deleteApk(" + rec.id + ")'>删除</a> ";
                            var downloadUrl = "${ctx}/downloadapk.do?deviceId=&type=RMS_CLIENT_APK&id=" + rec.id;
                            var downloadLink = "<a href='" + downloadUrl + "'>下载</a>";
                            var qrcodeContent = "http://" + host + downloadUrl;
                            var barcodeLink = "<a href='javascript:void(0)' onclick='showBarcode(\"" + qrcodeContent + "\")'>二维码</a> ";
                            return downloadLink + " | " + barcodeLink + " | " + deleteLink;
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
                        initApp();
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

        function initApp() {
            $.ajax({
                type: "GET",
                url: '${ctx}/listApp.do',
                cache: true,
                dataType: "json",
                success: function (page) {
                    console.log(page);
                    debugger;
                    var rows = page.rows;
                    var appSelect = $("#appKey");
                    appSelect.empty();
                    for (var i=0;i<rows.length;i++){
                        appSelect.append("<option value='"+rows[i].appKey+"'>"+rows[i].appName+"</option>");
                    }

                }
            });
        }

        function initUploadify() {
            const apk_max_size = "40MB";
            $("#file_upload").uploadify({
                'fileObjName': 'file',
                'buttonText': '选择APK文件',
                'multi': false,
                'queueSizeLimit': 1,
                'fileSizeLimit': apk_max_size,
                'auto': false,
                preventCaching: true,
                'swf': '${uploadify}/uploadify.swf',
                'uploader': '${ctx}/uploadapk.do',
                'fileTypeDesc': 'APK文件',
                'fileTypeExts': '*.apk',
                'onFallback': function () {
                    alert('未安装Flash插件');
                },
                //选择上传文件后调用
                'onSelect': function (file) {
                    filename = file.name;
                    s = filename.split("_");
                    versionName = s[1].substring(0, 6);
                    versionCode = s[2];
                },
                'onSelectError': function (file, errorCode, errorMsg) {
                    if (errorCode == -100) {
                        this.queueData.errorMsg = "一次只能上传一个文件";
                    }
                    else if (errorCode == -110) {
                        this.queueData.errorMsg = "超过了" + apk_max_size + "限制";
                    }
                    else if (errorCode == -120) {
                        this.queueData.errorMsg = "文件" + file.name + "为空";
                    }
                    else if (errorCode == -130) {
                        this.queueData.errorMsg = "文件" + file.name + "引发了错误,错误原因:文件类型错误";
                    }
                },
                'onUploadStart': function (file) {
                    appName = $("#appKey").val();
                    versionName = $("#versionName").val();
                    versionCode = $("#versionCode").val();
                    desc = $("#desc").val();
                    forceUpdate = $("#forceUpdate").is(":checked");

                    $("#file_upload").uploadify("settings", "formData",
                            {
                                'appKey': appName,
                                'versionName': versionName,
                                'versionCode': versionCode,
                                'forceUpdate': forceUpdate,
                                'desc': desc
                            });

                },
                'onUploadSuccess': function (file, data, response) {
                    $("#upload_dialog").dialog("close");
                    refreshGrid();
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
                        url: '${ctx}/deleteapk.do?id=' + id,
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
     style="background:#fafafa;padding:5px;width:750px;height:500px;" closed="true">
    <form id="apkInfo" method="post" enctype="multipart/form-data">
        <div>
            <label for="apkNamingRule">apk命名规则:</label>
            <img id="apkNamingRule" name="apkNamingRule" src="${ctx}/images/apk_naming_rule.png" alt="apk命合规则"
                 width="600" height="160"/>
        </div>
        <div>
            <br/>
            <input type="file" name="file_upload" id="file_upload" data-options="required:true"/>
        </div>
        <div style="display: inline-block">
            <label for="appKey">终端程序类型:</label>
            <select id="appKey">
            </select>
        </div>
        <div style="display: inline-block">
            <label for="versionName">版本号(VersionName):</label>
            <input class="easyui-validatebox" type="text" id="versionName" data-options="required:true"/>
        </div>
        <div style="display: inline-block">
            <label for="versionCode">内部版本号(VersionCode):</label>
            <input class="easyui-numberbox" type="text" id="versionCode" data-options="required:true"/>
        </div>
        <div>
           是否強制更新:
            <input class="easyui-validatebox" type="checkbox" id="forceUpdate" value="1" data-options="required:true"/>
        </div>
        <div>
            <label for="desc">说明:</label>
            <textarea style="width: 400px;height: 200px;;" class="easyui-validatebox" id="desc" data-options="required:true"></textarea>
        </div>
    </form>
</div>

<div id="barcode_dialog" class="easyui-window" closed="true">
    <img id="QRCodeImage" src="#" alt="QRCode Image"/>
</div>

</body>
</html>
