<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ include file="/common/metas.jsp"%>
<title>
    ${systemMenu.title}
</title>
</head>
<body>
<div class="main">
	<div class="head">
		<div class="head_right">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
	</div>
	<div class="menu">
		<tiles:insertAttribute name="topbar"></tiles:insertAttribute>
	</div>
	<div class="navigation">
		${currentSit}
	</div>
	<div class="container">
		<tiles:insertAttribute name="contant"></tiles:insertAttribute>
	</div>
	<div class="footer">
		本系统推荐使用Mozilla Firefox浏览器、1024x768分辨率以获得最佳使用体验。点击<a href="http://download.mozilla.org/?product=firefox-3.6.13&os=win&lang=zh-CN">此处</a>下载Firefox
		<!-- <p>圆通速递公司总部：上海市青浦区华新镇华徐公路3029弄28号 邮政编码:201705</p> -->
		<p>Copyright© 2000 &mdash; 2009 All Right Reserved </p>
	</div>
</div>
<s:if test="notifyEnabled">
<%@ include file="notify.jsp"%>
</s:if>
</body>
</html>
