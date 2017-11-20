<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	$("input").attr("readonly","true");
	$("#exit").click(function(){
		
		$('#menuSaveOrUpdateForm').attr('action','menu.action');
		document.menuSaveOrUpdateForm.submit();
	});
});

</script>

<form method="post" action="menuSaveOrUpdate.action" name="menuSaveOrUpdateForm" id="menuSaveOrUpdateForm">
<div class="layout">
<div class="block m-b">
<div class="block_title">
<h3>菜单查看</h3>
</div>
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input id="id" name="id" type="hidden" value="${menu.id}" />
<input id="appId" name="appId" type="hidden" value="${appId}" />
<input id="parentMenuId" name="parentMenuId" type="hidden" value="${parentMenuId}" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
				

	<tr>
		<td width="35%" align="right"><label for="menuName">名称:</label></td>
		<td width="65%"><input type="text" name="menuName" id="menuName" class="text required"
			size="25" maxlength="100" value="${menu.menuName}" readonly /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="menuTitle">显示名称:</label></td>
		<td width="65%"><input type="text" name="menuTitle" id="menuTitle"
			size="25" maxlength="100" value="${menu.menuTitle}" readonly /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="menuTitle">英文显示名称:</label></td>
		<td width="65%"><input type="text" name="menuTitleEn" id="menuTitleEn"
			size="25" maxlength="100" value="${menu.menuTitleEn}" readonly /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="location">路径:</label></td>
		<td width="65%"><input type="text" name="location" id="location"
			size="25" maxlength="100" value="${menu.location}"  readonly/></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="target">打开目标:</label></td>
		<td width="65%"><input type="text" name="target" id="target"
			size="25" maxlength="100" value="${menu.target}"  readonly/></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="appName">所属应用:</label></td>
		<td width="65%"><input type="text" name="appName" id="appName"
			size="25" maxlength="100" value="${appName}" readonly />
			</td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="parentMenuTitle">上级菜单:</label></td>
		<td width="65%"><input type="text" name="parentMenuTitle" id="parentMenuTitle"
			size="25" maxlength="100" value="${parentMenuName}" readonly/>
			</td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="displayOrder">排序:</label></td>
		<td width="65%"><input type="text" name="displayOrder" id="displayOrder" class="digits"
			size="25" maxlength="9" value="${menu.displayOrder}"  readonly/></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="description">描述:</label></td>
		<td width="65%" colspan="3"><input type="text" name="description"
			id="description" size="100" maxlength="200" value="${menu.description}" readonly/></td>
	</tr>
</table>
<fieldset class="action">
<a id="exit" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
</fieldset>
</div>


</div>
</div>

</form>

