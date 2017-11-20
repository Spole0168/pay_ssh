<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#groupEditForm").validate({
		rules: {
			"group.name": {required: true},
			"group.type": {required: true},
			"group.flag": {required: true},
			"group.isPublic": {required: true},
			"group.description": {required: false}
		},
		invalidHandler: function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = "请正确填写表单信息！";
				$("div.warning span").html(message);
				$("div.warning").show();
			} else {
				$("div.warning").hide();
			}
		}
	});
	
	$("#save").click(function(){
		if (!$("#groupEditForm").validate().form()) {
			return false;
		} else {
			$.ajax({
			 	type: "POST",
			 	url: "../mdmgrouputil/groupSaveOrUpdate.action",
			 	dataType: "json",
		 	   	data: $('#groupEditForm').serializeObject(),
		 	   	success: function(result){
			 		//$('#deliveryReportDiv').unblock();
					$.boxUtil.success('保存成功');
					doGroupSearch();
					$('#dialog-edit-ajax').dialog('close');
					return false;
				},
		   	   	error: function(err) {
		 	   		//$('#deliveryReportDiv').unblock();
		 		  	$.boxUtil.warn('${app:i18n("motor.exception")}', {title: '${app:i18n("global.jsp.attention")}'}, function(){});
		 		  	return false;
		   	   	}
		 	});
		}
		return false;
	});

	$("#undo").click(function(){
		$('#dialog-edit-ajax').dialog('close');
	});
	
	$("* [readonly]").addClass("read_only");
	
});

</script>


<div class="block">
	<div class="block_title">
		<h3>${groupTypeName}组信息</h3>
	</div>
	<div class="block_container">
		<div class="m-b">
			<form id="groupEditForm" method="post" action="return false;">
				<s:hidden name="isModify"/>
				<s:hidden name="group.id" id="groupId"/>
				<div class="warning" style="display:none;">
					<span></span>
				</div>
				<table cellpadding="0" cellspacing="0" class="table_form">
					<thead>
					</thead>
					<tfoot>
					</tfoot>
					<tbody>
						<tr>
							<th><em>*</em>组名：</th>
							<td>
								<input name="group.name" class="width_c" value="${group.name}" />
								<input name="group.userCode" type="hidden" value="${group.userCode}" />
								<input name="group.status" type="hidden" value="${group.status}" />
							</td>
						</tr>
						<tr style="display: none;">
							<th><em>*</em>类型：</th>
							<td>
								<input name="group.type" class="width_c" value="${group.type}" readonly/>
							</td>
						</tr>
						<tr style="display: none;">
							<th><em>*</em>标志：</th>
							<td>
								<input name="group.flag" class="width_c" value="${group.flag}" readonly/>
							</td>
						</tr>
						<tr style="display: none;">
							<th><em>*</em>是否公用：</th>
							<td>
								<input name="group.isPublic" class="width_c" value="${group.isPublic}" readonly/>
								<%-- <label><input type="radio" id="isPublic_yes" value="true" name="group.isPublic" <c:if test="${group.isPublic == true}">checked</c:if> />是</label>
								<label><input type="radio" id="isPublic_no" value="false" name="group.isPublic" <c:if test="${group.isPublic == false}">checked</c:if>/>否</label> --%>
							</td>
						</tr>
						<tr>
							<th>描述：</th>
							<td colspan="5"><textarea class="textarea" name="group.description" id="description" cols="120" rows="6">${group.description}</textarea></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div>
			<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
			<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
		</div>
	</div>
</div>
