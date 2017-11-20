<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" charset="UTF-8">

function goBack() {
	if($.browser.mozilla) {
		window.location.href = "${header.Referer}";
		
	} else {
		history.go(-1);
	}
	return false;
}

</script>

<div class="wrong_page">
	<ul>
		<li class="wrong_icon"></li>
		<li class="administrator"><s:actionerror cssClass="administrator" theme="yto" /><p><a class="a1" href="#" onclick="javascript:goBack();">返回</a></p></li>

	</ul>
	<div class="clear"></div>	
</div>
