<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/js/jstree-1.0rc2/themes/default/style.css'/>" />
<script type="text/javascript"
	src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>">
</script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
var editMenu = false;
$().ready(function() {
	$(".legend_title a").toggle(function() {
		$(this).parent().next(".fieldset_container:first").hide();
		$(this).removeClass("container_show");
		$(this).addClass("container_hide");
	}, function() {
		$(this).parent().next(".fieldset_container:first").show();
		$(this).removeClass("container_hide");
		$(this).addClass("container_show");
	});
	var id = $("#id").val();
	if(id != null && id != ""){
		$("#roleCode").attr("readonly","readonly");
		$("#roleCode").removeAttr("class");
	} 
 
	$("#menuTree").jstree({
		 "core" : {  "initially_open" : ['1']  },		 
		"plugins" : [ "themes", "html_data" ,"ui" ,"crrm" ]
	}); 
	
	var divId =  $("#divId").val();
	$("#role-OK-back").click(function(){ 
		$('#'+divId).dialog('close');
	});
	
});
	
</script>
</head>
<form id="roleEditForm" name="roleEditForm" action="roleSaveOrUpdate.action" method="post">
<div class="layout">
<div class="block m-b">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<div class="fieldset_border fieldset_bg m-b">
<div class="legend_title"><a href="#" class="container_show">角色信息</a></div>
<div class="fieldset_container">
<input type="hidden" name="id" id="id" value="${role.id}" /> 
<input type="hidden" name="divId" id="divId" value="${divId}" /> 
<input type="hidden" name="grantedMenuIdString" id="grantedMenuIdString" />
<input type="hidden" name="checkedScopeStr" id="checkedScopeStr" />

<input type="hidden" name="saveResult" id="saveResult" value="${saveResult }"/>
 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td align="right"><label for="roleCode">代码:</label></td>
		<td ><input type="text" name="roleCode" id="roleCode" value="${role.roleCode }" size="25" maxlength="100" style="width:160px"
			class="{required : true, remote: {url: 'checkRoleCode.action', type: 'post', dataType: 'json', data: {}}}" disabled/></td>
	</tr>
	<tr>
		<td align="right"><label for="roleName">名称:</label></td>
		<td ><input type="text" name="roleName" id="roleName" value="${role.roleName }" style="width:160px" size="25" maxlength="100" class="{required : true}" disabled/></td>
	</tr>
	<tr>
		<td align="right">
				<label for="appId">所属应用:</label>
			</td>
			<td>
				<select id="appId" name="appId" disabled="disabled" style="width:160px">
					<c:forEach items="${appList }" var="item">
						<option value="${item.key }" <c:if test="${role.appId eq item.key }">selected</c:if>>${item.value }</option>
					</c:forEach>
				</select>
			</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td align="right"><label for="checkedScopes"><em style="color:red">*</em>适用范围:</label></td> -->
<!-- 		<td> -->
<%-- 			<s:checkboxlist  name="checkedScopes" list="%{scopeList }" listKey="key" listValue="value" onclick="this.checked=!this.checked"></s:checkboxlist> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!--    <tr> -->
<!-- 		<td align="right"><label for="checkedUserType"><em style="color:red">*</em>用户类型适用范围:</label></td> -->
<!-- 		<td> -->
<%-- 			<s:checkboxlist name="checkedUserType" list="%{userTypeList }" listKey="key" listValue="value" onclick="this.checked=!this.checked" ></s:checkboxlist> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td align="right"><label for="description">描述:</label></td>
		<td colspan='3'><textarea class="textarea" style="width:300px" rows="3" id="description" name="description" disabled="disabled">${role.description }</textarea>
		</td>
	</tr>
</table>
<div class="btn_layout">
	<a id="role-OK-back" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返回</span></span></a></div>
</div>
</div> 
	<div class="tabs ui-tabs ui-widget ui-widget-content ui-corner-all leftOrgTree ui-tabs-collapsible"
		id="tabs2" style="height: auto; background: #ffffee"> 
		<div class="jstree11" >
		 	<app:treeView treeId="menuTree" tree='${menuTree}'/> 
		</div>
	</div>
</div>
</div>
</div>
</form>