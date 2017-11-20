<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#corBankAcntTransDtlEditForm").validate({
		rules: {
			"corBankAcntTransDtl.id": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.bankCreditAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.bankCreditCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.bankDebitAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.bankDebitCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.transType": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.direction": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.amount": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTransDtl.transCurrency": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.transTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTransDtl.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"corBankAcntTransDtl.transComments": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTransDtl.transStatus": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.bankMsgCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTransDtl.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTransDtl.errCode": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.errDesc": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTransDtl.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTransDtl.operator": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.handleDesc": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTransDtl.handleStatus": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTransDtl.orgReqNum": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.bankBalanceAfterTrans": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTransDtl.bankFrozenAmoumt": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTransDtl.bankAvaliableAmount": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTransDtl.transRate": {required: true,stringMaxLength:4,isLegal: true},
			"corBankAcntTransDtl.bankNum": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTransDtl.isValid": {required: true,stringMaxLength:6,isLegal: true},
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
		window.location = "corBankAcntTransDtl/corBankAcntTransDtlList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corBankAcntTransDtlEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corBankAcntTransDtlEditForm").submit(); 
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

<s:form id="corBankAcntTransDtlEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/corBankAcntTransDtl">
<s:hidden name="isModify"/>
<s:hidden name="corBankAcntTransDtl.id" id="corBankAcntTransDtlId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corBankAcntTransDtl.corBankAcntTransDtlEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.id') }：</th>
								<td><input name="corBankAcntTransDtl.id" id="id" class="width_c" value="${corBankAcntTransDtl.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankTransNum') }：</th>
								<td><input name="corBankAcntTransDtl.bankTransNum" id="bankTransNum" class="width_c" value="${corBankAcntTransDtl.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankCreditAcntCode') }：</th>
								<td><input name="corBankAcntTransDtl.bankCreditAcntCode" id="bankCreditAcntCode" class="width_c" value="${corBankAcntTransDtl.bankCreditAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankCreditName') }：</th>
								<td><input name="corBankAcntTransDtl.bankCreditName" id="bankCreditName" class="width_c" value="${corBankAcntTransDtl.bankCreditName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankCreditCardNum') }：</th>
								<td><input name="corBankAcntTransDtl.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${corBankAcntTransDtl.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankDebitAcntCode') }：</th>
								<td><input name="corBankAcntTransDtl.bankDebitAcntCode" id="bankDebitAcntCode" class="width_c" value="${corBankAcntTransDtl.bankDebitAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankDebitName') }：</th>
								<td><input name="corBankAcntTransDtl.bankDebitName" id="bankDebitName" class="width_c" value="${corBankAcntTransDtl.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankDebitCardNum') }：</th>
								<td><input name="corBankAcntTransDtl.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${corBankAcntTransDtl.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transType') }：</th>
								<td><input name="corBankAcntTransDtl.transType" id="transType" class="width_c" value="${corBankAcntTransDtl.transType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.direction') }：</th>
								<td><input name="corBankAcntTransDtl.direction" id="direction" class="width_c" value="${corBankAcntTransDtl.direction}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.amount') }：</th>
								<td><input name="corBankAcntTransDtl.amount" id="amount" class="width_c" value="${corBankAcntTransDtl.amount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transCurrency') }：</th>
								<td><input name="corBankAcntTransDtl.transCurrency" id="transCurrency" class="width_c" value="${corBankAcntTransDtl.transCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transTime') }：</th>
								<td><input name="corBankAcntTransDtl.transTime" id="transTime" class="width_c" value="${corBankAcntTransDtl.transTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transDate') }：</th>
								<td><input name="corBankAcntTransDtl.transDate" id="transDate" class="width_c" value="${corBankAcntTransDtl.transDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transComments') }：</th>
								<td><input name="corBankAcntTransDtl.transComments" id="transComments" class="width_c" value="${corBankAcntTransDtl.transComments}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transStatus') }：</th>
								<td><input name="corBankAcntTransDtl.transStatus" id="transStatus" class="width_c" value="${corBankAcntTransDtl.transStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankMsgCode') }：</th>
								<td><input name="corBankAcntTransDtl.bankMsgCode" id="bankMsgCode" class="width_c" value="${corBankAcntTransDtl.bankMsgCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.createTime') }：</th>
								<td><input name="corBankAcntTransDtl.createTime" id="createTime" class="width_c" value="${corBankAcntTransDtl.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.updateTime') }：</th>
								<td><input name="corBankAcntTransDtl.updateTime" id="updateTime" class="width_c" value="${corBankAcntTransDtl.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.errCode') }：</th>
								<td><input name="corBankAcntTransDtl.errCode" id="errCode" class="width_c" value="${corBankAcntTransDtl.errCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.errDesc') }：</th>
								<td><input name="corBankAcntTransDtl.errDesc" id="errDesc" class="width_c" value="${corBankAcntTransDtl.errDesc}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.handleTime') }：</th>
								<td><input name="corBankAcntTransDtl.handleTime" id="handleTime" class="width_c" value="${corBankAcntTransDtl.handleTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.operator') }：</th>
								<td><input name="corBankAcntTransDtl.operator" id="operator" class="width_c" value="${corBankAcntTransDtl.operator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.handleDesc') }：</th>
								<td><input name="corBankAcntTransDtl.handleDesc" id="handleDesc" class="width_c" value="${corBankAcntTransDtl.handleDesc}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.handleStatus') }：</th>
								<td><input name="corBankAcntTransDtl.handleStatus" id="handleStatus" class="width_c" value="${corBankAcntTransDtl.handleStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.orgReqNum') }：</th>
								<td><input name="corBankAcntTransDtl.orgReqNum" id="orgReqNum" class="width_c" value="${corBankAcntTransDtl.orgReqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankBalanceAfterTrans') }：</th>
								<td><input name="corBankAcntTransDtl.bankBalanceAfterTrans" id="bankBalanceAfterTrans" class="width_c" value="${corBankAcntTransDtl.bankBalanceAfterTrans}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankFrozenAmoumt') }：</th>
								<td><input name="corBankAcntTransDtl.bankFrozenAmoumt" id="bankFrozenAmoumt" class="width_c" value="${corBankAcntTransDtl.bankFrozenAmoumt}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankAvaliableAmount') }：</th>
								<td><input name="corBankAcntTransDtl.bankAvaliableAmount" id="bankAvaliableAmount" class="width_c" value="${corBankAcntTransDtl.bankAvaliableAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.transRate') }：</th>
								<td><input name="corBankAcntTransDtl.transRate" id="transRate" class="width_c" value="${corBankAcntTransDtl.transRate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.bankNum') }：</th>
								<td><input name="corBankAcntTransDtl.bankNum" id="bankNum" class="width_c" value="${corBankAcntTransDtl.bankNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTransDtl.isValid') }：</th>
								<td><input name="corBankAcntTransDtl.isValid" id="isValid" class="width_c" value="${corBankAcntTransDtl.isValid}" maxlength="225"/></td>
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
