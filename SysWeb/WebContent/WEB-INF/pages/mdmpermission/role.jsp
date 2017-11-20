<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function() {
	var id = $("#role_id").val();
	if(id != null && id != ""){
		$("#r_roleCode").attr("readonly","readonly");
		$("#r_roleCode").removeAttr("class");
	} 
	$("#roleSaveOrUpdateForm").validate( {
		rules : {
			r_roleName : {
				required : true
			}
		},
		invalidHandler : function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = "请正确填写信息！";
				$("div.warning span").html(message);
				$("div.warning").show();
			} else {
				$("div.warning").hide();
			}
		},
		onkeyup : false,
		submitHandler : function() {
			//document.roleSaveOrUpdateForm.submit();
			saveRole();
		},
		messages : {
			"r_roleCode":{required: "必填字段",remote:"该角色名称已被使用"}
	}
	});
	$("#save").click(function() {
		$("#roleSaveOrUpdateForm").submit();
	});
	$("#reset").click(function() {
		$("#r_roleCode").val("");
		$("#r_description").val("");
	});
	$("#exit").click(function(){
		$(gridId).trigger("reloadGrid");
		$(dlgEditId).dialog('close');
	});
});

function saveRole(){
	var id=$("#role_id").val();
	var roleCode = $("#r_roleCode").val();
	var roleName = $("#r_roleName").val();
	var description = $("#r_description").val();
	if(roleName == ""){
		return ;
	}
	$.ajax({
		async:true, 
		type:"post", 
		dataType:"html", 
		url:"roleSaveOrUpdate.action",
		data:"roleCode="+encodeURI(roleCode)+"&roleName="+encodeURI(roleName)+"&description="+encodeURI(description)+"&id="+id, 
		success:function(data) {
		$(gridId).trigger("reloadGrid");
		$(dlgEditId).dialog('close');
	}
	});
}
</script>

<form method="post" action="roleSaveOrUpdate.action" name="roleSaveOrUpdateForm" id="roleSaveOrUpdateForm">
<div class="layout">
<div class="block m-b">

<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input type="hidden" name="role_id" id="role_id" value="${role.id}" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
				

	<tr>
		<td width="10%" align="right"><label for="r_roleCode"><em style="color: red">*</em>角色代码：</label></td>
		<td width="35%"><input type="text" name="r_roleCode" id="r_roleCode" class="{required : true, remote: {url: 'checkRoleCode.action', type: 'post', dataType: 'json', data: {}}}"
			size="25" maxlength="100" value="${role.roleCode}" /></td>
			
	</tr>
	<tr>
		<td width="10%" align="right"><label for="r_roleName"><em style="color:red">*</em>角色名称：</label></td>
		<td width="35%"><input type="text" name="r_roleName" id="r_roleName" class="required"
			size="25" maxlength="100" value="${role.roleName}" /></td>
	</tr>
	<tr>
		<td width="10%" align="right"><label for="r_description">描述：</label></td>
		<td width="35%"><input type="text" name="r_description" id="r_description" 
			size="25" maxlength="100" value="${role.description}" /></td>
	</tr>
</table>
<fieldset class="action">
<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">保 存</span></span></a>
<a id="exit" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
</fieldset>
</div>


</div>
</div>


</form>
