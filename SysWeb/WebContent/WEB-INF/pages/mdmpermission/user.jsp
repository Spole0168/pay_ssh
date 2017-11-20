<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/common/metas.jsp"%>
<script type="text/javascript">
$().ready(function() {
	var id = $("#user_id").val();
	if(id != null && id != ""){
		$("#userName").attr("disabled","disabled");
	}
	$("#userSaveOrUpdateForm").validate( {
		rules : {
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
//			document.appSaveOrUpdateForm.submit();
			saveUser();
		},
		messages : {
			"userName":{required: "必填字段",remote:"该用户登录名称已被使用"}
		}
	});
	
	$("#save").click(function() {
		//$("#userSaveOrUpdateForm").appendPostData({id:$("#id").val(),currentOrgId:$("#currentOrgId").val()});
		$("#userSaveOrUpdateForm").submit();
		//saveUser();
	});
	$("#reset").click(function() {
		$("#userName").val("");
		$("#userCode").val("");
		$("#status").val("");
		$("#description").val("");
	});
	$("#exit").click(function(){
	//	$("#userCode").removeAttrs("class");
	//	$("#userSaveOrUpdateForm").appendPostData({currentOrgId:$("#currentOrgId").val()});
	//	$('#userSaveOrUpdateForm').attr('action','userMain.action');
	//	document.userSaveOrUpdateForm.submit(); 
	//window.close();
		$("#dialog-ajax-user-edit").dialog('close');
	});
});
function saveUser(){
	var id=$("#user_id").val();
	var userName = $("#userName").val();
	var userPwd = $("#userPwd").val();
	if(userPwd != "" && userPwd.length<4){
		$.boxUtil.alert("用户密码小于4位，请重新设置！");
		 
		return;
	}
	var status = $("#status").val();
	var description = $("#description").val();
	if(userName == ""){
		return ;
	}
	$.ajax({
		async:true, 
		type:"post", 
		dataType:"json", 
		url:"userSaveOrUpdate.action",
		data:"userName="+encodeURI(userName)
		+"&userPwd="+encodeURI(userPwd)
		+"&status="+status
		+"&description="+encodeURI(description)
		+"&id="+id, 
		success:function(msg) {
			if(msg.message == 'OK'){
				$.boxUtil.alert("保存成功!");
			}else{
				$.boxUtil.error("保存出错，请联系管理员");
			}
			$("#userList").trigger("reloadGrid");
			$("#dialog-ajax-user-edit").dialog('close');
	}
	});
}
</script>

<form method="post" action="userSaveOrUpdate.action" name="userSaveOrUpdateForm" id="userSaveOrUpdateForm">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input type="hidden" name="user_id" id="user_id" value="${user.id}" />
<input type="hidden" name="currentOrgId" id="currentOrgId" value="${currentOrgId}" />
<div class="container">
<div class="layout">
<div class="block m-b">
<div id="userInfo">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td width="10%" align="right"><label for="userName"><em style="color:red">*</em>用户登录名:</label></td>
		<td width="35%"><input type="text" name="userName" id="userName"
			size="25" maxlength="100" value="${user.userName}" 
			class="{codeExpValidator: [], required : true, remote: {url: 'checkUniqueUserName.action', type: 'post', dataType: 'json', data: {formerName:'${user.userName}'}}}"/></td>
	</tr>
	<tr>
		<td width="10%" align="right"><label for="userPwd"><em style="color:red">*</em>用户密码:</label></td>
		<td width="35%"><input type="password" name="userPwd"
			id="userPwd" size="25" maxlength="50" value="" class="{minlength:4}"/></td>
	</tr>
	<tr>
		<td width="10%" align="right"><label for="status">是否生效:</label></td>
		<td width="35%"><select id="status" name="status">
			<c:forEach items="${usedList}" var="item">
				<option value="${item.key }" <c:if test="${item.key eq user.used}">selected</c:if>>${item.value }</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="description">描述:</label></td>
		<td width="65%">
		<textarea class="textarea" id="description" name="description" style="width: 200px" rows="2" >${user.description }</textarea>
		</td>
	</tr>

</table>

</div>
</div>
</div>
<fieldset class="action">
	<a id="save"
	class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span
	class="l-btn-text icon-save">保 存</span></span></a>  
	<a id="exit"
	class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span
	class="l-btn-text icon-undo">返 回</span></span></a></fieldset>


</div>
</div>

</form>
