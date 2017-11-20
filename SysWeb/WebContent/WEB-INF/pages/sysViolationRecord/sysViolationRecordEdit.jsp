<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#sysViolationRecordEditForm").validate({
		rules: {
			"sysViolationRecord.id": {required: true,stringMaxLength:50,isLegal: true},
			"sysViolationRecord.reqNum": {required: true,stringMaxLength:32,isLegal: true},
			"sysViolationRecord.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"sysViolationRecord.cnlIntfCode": {required: true,stringMaxLength:32,isLegal: true},
			"sysViolationRecord.violationType": {required: true,stringMaxLength:50,isLegal: true},
			"sysViolationRecord.violationId": {required: true,stringMaxLength:50,isLegal: true},
			"sysViolationRecord.violationDesc": {required: true,stringMaxLength:200,isLegal: true},
			"sysViolationRecord.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"sysViolationRecord.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"sysViolationRecord.creator": {required: true,stringMaxLength:50,isLegal: true},
			"sysViolationRecord.updator": {required: true,stringMaxLength:50,isLegal: true},
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
		$.boxUtil.confirm(
			'请确认是否保存信息？', 
			null, 
			function(){
				doSave();
			}, 
			function(){
				//return false;
			});
		return false;
	});

	$("#undo").click(function(){
		window.location = "sysViolationRecord/sysViolationRecordList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#sysViolationRecordEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#sysViolationRecordEditForm").submit(); 
	</c:if>
}
//alert框
function showAlertDialog(alertTitle, alertContent){
	$('#alertDialog').dialog('destroy');
    $('#alertDialog').show();
    $('#alertDialog').html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+ alertContent +'</p>');
    
    $("#alertDialog").dialog({
        resizable: false,
        modal: true,
        overlay: {
            backgroundColor: '#000',
            opacity: 0.9
        },
        title:alertTitle,
        buttons: {
        	'${app:i18n("global.jsp.ok")}': function() {
            	$('#alertDialog').dialog('close');
            }
        }
    });

}

</script>

<div id="alertDialog"></div>

<s:form id="sysViolationRecordEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/sysViolationRecord">
<s:hidden name="isModify"/>
<s:hidden name="sysViolationRecord.id" id="sysViolationRecordId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('sysViolationRecord.sysViolationRecordEditJsp.title')}</h3>
		</div>
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
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
								<th><em>*</em>${app:i18n('sysViolationRecord.id') }：</th>
								<td><input name="sysViolationRecord.id" id="id" class="width_c" value="${sysViolationRecord.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.reqNum') }：</th>
								<td><input name="sysViolationRecord.reqNum" id="reqNum" class="width_c" value="${sysViolationRecord.reqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.cnlCnlCode') }：</th>
								<td><input name="sysViolationRecord.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${sysViolationRecord.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.cnlIntfCode') }：</th>
								<td><input name="sysViolationRecord.cnlIntfCode" id="cnlIntfCode" class="width_c" value="${sysViolationRecord.cnlIntfCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.violationType') }：</th>
								<td><input name="sysViolationRecord.violationType" id="violationType" class="width_c" value="${sysViolationRecord.violationType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.violationId') }：</th>
								<td><input name="sysViolationRecord.violationId" id="violationId" class="width_c" value="${sysViolationRecord.violationId}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.violationDesc') }：</th>
								<td><input name="sysViolationRecord.violationDesc" id="violationDesc" class="width_c" value="${sysViolationRecord.violationDesc}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.createTime') }：</th>
								<td><input name="sysViolationRecord.createTime" id="createTime" class="width_c" value="${sysViolationRecord.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.updateTime') }：</th>
								<td><input name="sysViolationRecord.updateTime" id="updateTime" class="width_c" value="${sysViolationRecord.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.creator') }：</th>
								<td><input name="sysViolationRecord.creator" id="creator" class="width_c" value="${sysViolationRecord.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysViolationRecord.updator') }：</th>
								<td><input name="sysViolationRecord.updator" id="updator" class="width_c" value="${sysViolationRecord.updator}" maxlength="225"/></td>
							</tr>


							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
