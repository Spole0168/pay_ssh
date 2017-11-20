<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	var id = $("#appId").val();
	if(id != ""){
		$("#appName").attr("readonly","readonly");
		$("#appName").removeAttr("class");
	}
	
	$("#appSaveOrUpdateForm").validate( {
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
			saveApp();
		},
		messages : {
			"appName":{required: "必填字段",remote:"该应用名称已被使用"}
		}
	});
	$("#save").click(function() {
	//	var appName = $("#appName").val();
	//	 var appId = $("#appId").val();
	//	 if(appId == '' && appName != ''){
	//		var check = checkAppName(appName);
	//		if(check == '0'){
	//			return ;
	//		}
	//	 }
		$("#appSaveOrUpdateForm").submit();
	});
	$("#reset").click(function() {
		$("#appName").val("");
		$("#description").val("");
	});
	$("#exit").click(function(){
		$(gridId).trigger("reloadGrid");
		$(dlgId).dialog('close');
	});
/*
	$("#appName").blur(function(){
		 var appName = $("#appName").val();
		 var appId = $("#appId").val();
		 
		 if(appId =="" && appName != ""){
			checkAppName(appName);
		 }
	});*/
});
function saveApp(){
	var id=$("#appId").val();
	var appName = $("#appName").val();
	var description = $("#description").val();
	if(appName == ""){
		return ;
	}
	$.ajax({
		async:true, 
		type:"post", 
		dataType:"html", 
		url:"appSaveOrUpdate.action",
		data:"appName="+encodeURI(appName)+"&description="+encodeURI(description)+"&appId="+id, 
		success:function(data) {
		$(gridId).trigger("reloadGrid");
		$(dlgId).dialog('close');
	}
	});
}
function checkAppName(appName){
	var res = '0';
	$.ajax({
		async:false, 
		type:"post", 
		dataType:"json", 
		url:"checkAppName.action?appName="+appName+"&time="+ new Date().getTime(),
		data:"", 
		success:function(data) {
			if(data.message!=""){
				var message = "该应用名称已存在！";
				$("div.warning span").html(message);
				$("div.warning").show(); 
				res = '0';
			}
			else{
				$("div.warning").hide();
				res = '1'; 
			}
	    }
	});
	return res;
}
</script>

<form method="post" action="appSaveOrUpdate.action" name="appSaveOrUpdateForm" id="appSaveOrUpdateForm">
<div class="layout">
<div class="block m-b">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input type="hidden" name="appId" id="appId" value="${app.id}" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
				

	<tr>
		<td width="35%" align="right"><label for="appName"><em style="color:red">*</em>应用名称:</label></td>
		<td width="65%"><input type="text" name="appName" id="appName" class="{codeExpValidator: [], required : true, remote: {url: 'checkAppName.action', type: 'post', dataType: 'json', data: {}}}"
			size="25" maxlength="50" value="${app.appName}" /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="description">${app:i18n('sample.sampleItem.description') }:</label></td>
		<td width="65%"><input type="text" name="description" id="description" 
			size="25" maxlength="100" value="${app.description}" /></td>
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
