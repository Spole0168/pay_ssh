<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	$("ul.sf-menu").superfish({
		pathClass: 'current'
	}).find('ul').bgIframe({opacity:false});

	var ht = $(".sf-menu > li.sfHover > ul").height() - 30;
	if (ht > 0)
		$(".navigation").css("margin-top", ht + 10);
});
</script>
<app:menuBar menus="${menus}" menuId="${menuId}" id="menu" className="sf-menu sf-navbar" setting="width=\"1024\""/>
