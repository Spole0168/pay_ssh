<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	$("#dialog-print-form").dialog({
		autoOpen: false,
		width: 600,
		modal:true
	});
	
	$("#printOption").click(function(){
		var url = '<c:url value='/forWardPage.action'/>';
		$("#dialog-print-form").load(url).dialog('open'); 
	});

})

</script>
<ul>
	<li style="margin-bottom: 5px;">
		<a class="icon-edit" href="<c:url value='/modifyPassword.action'/>" >修改密码</a>
		<a href="javascript:void(0)" class="icon-help" id="printOption">打印功能说明</a>
		<a class="icon-out" href="<c:url value='/logout.action'/>">注销</a>
	</li>
	<li>
		<%-- <a href="<c:url context="/" value='/webstart/exp.jnlp'/>" class="icon-down">快件操作客户端下载/更新</a> --%>
		<a href="<c:url context="/" value='/common/controls/IBSSecurityPlugin_V1.0.msi'/>" class="icon-down">安全控件下载</a>
	</li>
	<li>
		<span style="margin-top:20px; " class="icon-user">${currentUser.displayName} ${currentUser.userCode}</span>
	</li>
</ul>

<div id="dialog-print-form" title="打印功能说明">
正在加载...
</div>

<%@ include file="/common/headerhook.jsp"%>
