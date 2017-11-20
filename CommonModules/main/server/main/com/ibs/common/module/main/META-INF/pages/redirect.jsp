<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<s:url var="redirectUrl" namespace="%{redirectNamespace}" action="%{redirectActionName}"
	 includeParams="all" escapeAmp="false">
</s:url>

<script type="text/javascript">
	//var url = '<s:property value="redirectUrl"/>?${pageContext.request.queryString}';
	var url = '<s:property value="redirectUrl"/>';
	url = url.replace(/&amp;/g, "&");
	top.location.href = url;
</script>

</head>

<body>

</body>

</html>
