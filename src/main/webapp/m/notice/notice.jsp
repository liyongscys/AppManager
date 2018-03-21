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
    <title>公告信息</title>
    <link rel="stylesheet" href="${ctx}/js/jquerymobile/jquery.mobile-1.2.0.min.css" />
    <link rel="apple-touch-icon" href="img/tutsTouchIcon.png" />
    <script src="${ctx}/js/jquery/jquery-1.8.2.min.js"></script>
    <script src="${ctx}/js/jquerymobile/jquery.mobile-1.2.0.min.js"></script>
    <script type="text/javascript">
        var url="${ctx}/m/notice/${notice.id}/hasRead.do?userId=${userId}";
        $(document).bind("pageinit", function(event, ui) {
        	  $("#hasRead").bind("click", function() {        		  
        		  $.ajax({
					  type: "POST",
					  url: url,
					  cache: false,
					  dataType: "text",
					  success: onSuccess
					});
        		  
        	  });
        	  
        	  function onSuccess(data)
              {
        		  $("#hasRead").checkboxradio('disable');
              }
        	  
        });        
        
    </script>
</head>
<body>
	<div data-role="page">
		<div data-role="content" id="content">
            <h1>${notice.title}</h1>
                              发布时间：${notice.issueDate} 发布人：${notice.issuePerson} <br/>
			<p>${notice.content}</p>
			<div>
			 <c:forEach var="fileItem" items="${fileItems}" varStatus="status">
                <div>
                <a href="${fileItem.url}&name=${fileItem.name}" data-transition="fade">
                          ${fileItem.name}
                </a>
                </div>

             </c:forEach> 
			</div>
						 
			<form>
			    <input type="hidden" id="hasReadTag" value="${notice.isRead}"/>
			    <c:if test="${notice.isRead == '1'}" > 
                    <label><input type="checkbox"  id="hasRead" />已阅知</label>
                </c:if>
			    <c:if test="${notice.isRead == '0'}" > 
                    <label><input type="checkbox" id="hasRead" checked disabled="disabled" />已阅知</label>
                </c:if>			    
            </form>
                       
             
		</div>
	</div>
</body>
</html>