<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>

<%@ include file="/common/metas.jsp"%>
<script type="">
</script>
</head>
<body>
<div class="main">
	<div class="block m-b">
		<tiles:insertAttribute name="guitopbar"></tiles:insertAttribute>
	</div>
	<div id="container">
		<tiles:insertAttribute name="contant"></tiles:insertAttribute>
	</div>
</div>
</body>
</html>
