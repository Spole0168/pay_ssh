<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#sysSysCfgEditForm").validate({
		rules: {
			"sysSysCfg.id": {required: true,stringMaxLength:50,isLegal: true},
			"sysSysCfg.category": {required: true,stringMaxLength:40,isLegal: true},
			"sysSysCfg.code": {required: true,stringMaxLength:40,isLegal: true},
			"sysSysCfg.name": {required: true,stringMaxLength:50,isLegal: true},
			"sysSysCfg.value": {required: true,stringMaxLength:50,isLegal: true},
			"sysSysCfg.version": {required: true,stringMaxLength:10,isLegal: true},
			"sysSysCfg.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"sysSysCfg.creator": {required: true,stringMaxLength:40,isLegal: true},
			"sysSysCfg.updator": {required: true,stringMaxLength:40,isLegal: true},
			"sysSysCfg.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"sysSysCfg.updateTime": {required: true,stringMaxLength:11,isLegal: true},
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
		window.location = "sysSysCfg/sysSysCfgList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#sysSysCfgEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#sysSysCfgEditForm").submit(); 
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

<s:form id="sysSysCfgEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/sysSysCfg">
<s:hidden name="isModify"/>
<s:hidden name="sysSysCfg.id" id="sysSysCfgId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('sysSysCfg.sysSysCfgEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('sysSysCfg.id') }：</th>
								<td><input name="sysSysCfg.id" id="id" class="width_c" value="${sysSysCfg.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.category') }：</th>
								<td><input name="sysSysCfg.category" id="category" class="width_c" value="${sysSysCfg.category}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.code') }：</th>
								<td><input name="sysSysCfg.code" id="code" class="width_c" value="${sysSysCfg.code}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.name') }：</th>
								<td><input name="sysSysCfg.name" id="name" class="width_c" value="${sysSysCfg.name}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.value') }：</th>
								<td><input name="sysSysCfg.value" id="value" class="width_c" value="${sysSysCfg.value}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.version') }：</th>
								<td><input name="sysSysCfg.version" id="version" class="width_c" value="${sysSysCfg.version}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.isValid') }：</th>
								<td><input name="sysSysCfg.isValid" id="isValid" class="width_c" value="${sysSysCfg.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.creator') }：</th>
								<td><input name="sysSysCfg.creator" id="creator" class="width_c" value="${sysSysCfg.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.updator') }：</th>
								<td><input name="sysSysCfg.updator" id="updator" class="width_c" value="${sysSysCfg.updator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.createTime') }：</th>
								<td><input name="sysSysCfg.createTime" id="createTime" class="width_c" value="${sysSysCfg.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysSysCfg.updateTime') }：</th>
								<td><input name="sysSysCfg.updateTime" id="updateTime" class="width_c" value="${sysSysCfg.updateTime}" maxlength="225"/></td>
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
