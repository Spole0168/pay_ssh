<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function() {
	var id = $("#id").val();
	if(id != ""){
	//	$("#operName").attr("readonly","readonly");
	//	$("#operName").removeAttr("class");
		$("#operType").attr("readonly","readonly");
		$("#operType").removeAttr("class");
	}
	$("#operSaveOrUpdateForm").validate( {
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
			saveOper();
		},
		messages : {
			operName:{required: "必填字段",remote:"该类型和名称的操作已存在！"}
		}
	});

	$("#save").click(function() {
		
		$("#operSaveOrUpdateForm").submit();
	});
	$("#exit").click(function(){
		$(gridId).trigger("reloadGrid");
		$(dlgId).dialog('close');
	});
});

function saveOper(){
	var id=$("#id").val();
	var operType = $("#operType").val();
	var operName = $("#operName").val();
	var operValue = $("#operValue").val();
	var description = $("#description").val();
	if(operName == ""){
		return ;
	}
	$.ajax({
		async:true, 
		type:"post", 
		dataType:"html", 
		url:"operSaveOrUpdate.action",
		data:"operType="+operType
		+"&operName="+encodeURI(operName)
		+"&operValue="+encodeURI(operValue)
		+"&description="+encodeURI(description)
		+"&id="+id, 
		success:function(data) {
		$(gridId).trigger("reloadGrid");
		$(dlgId).dialog('close');
	}
	});
}


</script>

<form method="post" action="operSaveOrUpdate.action" name="operSaveOrUpdateForm" id="operSaveOrUpdateForm">
<div class="layout">
<div class="block m-b">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input type="hidden" name="id" id="id" value="${operation.id}" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td width="35%" align="right"><label for="operType"><em style="color:red">*</em>操作类型：</label></td>
		<td width="65%">
			<c:if test="${operation.id eq null}">
			<select id="operType" name="operType"
			class='{required : true, remote: {url: "validateOperName.action", type: "post", dataType: "json", data: {operName:$("#operName").val() }}}'>
				<c:forEach items="${operTypePairs}" var="item">
					<c:if test="${item.key eq operType}"><option value="${item.key }" selected>${item.value }</option></c:if>
					<c:if test="${item.key ne operType}"><option value="${item.key }">${item.value }</option></c:if>
				</c:forEach>
			</select></c:if>
			<c:if test="${operation.id ne null}">
				<c:forEach items="${operTypePairs}" var="item">
					<c:if test="${item.key eq operType}">
					<input type="text" name="operType" id="operType" size="25" maxlength="100" value="${item.value }"/>
					</c:if>
				</c:forEach>
			</c:if>
			</td>
	</tr>		

	<tr>
		<td width="35%" align="right"><label for="operName"><em style="color:red">*</em>操作名称：</label></td>
		<td width="65%"><input type="text" name="operName" id="operName" size="25" maxlength="100" value="${operation.menuName}" 
		class='{required : true, remote: {url: "validateOperName.action", type: "post", dataType: "json", data: {operType:$("#operType").val() }}}'/></td>
	</tr>
	
	<tr>
		<td width="35%" align="right"><label for="operValue">操作值：</label></td>
		<td width="65%"><input type="text" name="operValue" id="operValue" class="width_d"
			size="25" maxlength="100" value="${operation.location}" /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="description">${app:i18n('sample.sampleItem.description') }：</label></td>
		<td width="65%"><input type="text" name="description" id="description"  class="width_d"
			size="25" maxlength="100" value="${operation.description}" /></td>
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
