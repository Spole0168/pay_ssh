<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#corBankAcntTransEditForm").validate({
		rules: {
			"corBankAcntTrans.id": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTrans.transNum": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTrans.bankCreditAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTrans.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTrans.bankCreditCardNum": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTrans.bankDebitAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTrans.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"corBankAcntTrans.bankDebitCardNum": {required: true,stringMaxLength:20,isLegal: true},
			"corBankAcntTrans.transType": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.direction": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.amount": {required: true,stringMaxLength:22,isLegal: true},
			"corBankAcntTrans.transCurrency": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.transTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTrans.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"corBankAcntTrans.transComments": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTrans.transStatus": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTrans.bankMsgCode": {required: true,stringMaxLength:32,isLegal: true},
			"corBankAcntTrans.returnCode": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.errMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTrans.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTrans.operator": {required: true,stringMaxLength:40,isLegal: true},
			"corBankAcntTrans.handleMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"corBankAcntTrans.handleStatus": {required: true,stringMaxLength:6,isLegal: true},
			"corBankAcntTrans.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"corBankAcntTrans.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTrans.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"corBankAcntTrans.creator": {required: true,stringMaxLength:40,isLegal: true},
			"corBankAcntTrans.updator": {required: true,stringMaxLength:40,isLegal: true},
			"corBankAcntTrans.bankBalanceAfterTrans": {required: true,stringMaxLength:22,isLegal: true},
			"corBankAcntTrans.bankFrozenAmoumt": {required: true,stringMaxLength:22,isLegal: true},
			"corBankAcntTrans.bankAvaliableAmount": {required: true,stringMaxLength:22,isLegal: true},
			"corBankAcntTrans.transRate": {required: true,stringMaxLength:4,isLegal: true},
			"corBankAcntTrans.bankNum": {required: true,stringMaxLength:32,isLegal: true},
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
		window.location = "corBankAcntTrans/corBankAcntTransList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corBankAcntTransEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corBankAcntTransEditForm").submit(); 
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

<s:form id="corBankAcntTransEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/corBankAcntTrans">
<s:hidden name="isModify"/>
<s:hidden name="corBankAcntTrans.id" id="corBankAcntTransId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corBankAcntTrans.corBankAcntTransEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('corBankAcntTrans.id') }：</th>
								<td><input name="corBankAcntTrans.id" id="id" class="width_c" value="${corBankAcntTrans.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transNum') }：</th>
								<td><input name="corBankAcntTrans.transNum" id="transNum" class="width_c" value="${corBankAcntTrans.transNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankCreditAcntCode') }：</th>
								<td><input name="corBankAcntTrans.bankCreditAcntCode" id="bankCreditAcntCode" class="width_c" value="${corBankAcntTrans.bankCreditAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankCreditName') }：</th>
								<td><input name="corBankAcntTrans.bankCreditName" id="bankCreditName" class="width_c" value="${corBankAcntTrans.bankCreditName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankCreditCardNum') }：</th>
								<td><input name="corBankAcntTrans.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${corBankAcntTrans.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankDebitAcntCode') }：</th>
								<td><input name="corBankAcntTrans.bankDebitAcntCode" id="bankDebitAcntCode" class="width_c" value="${corBankAcntTrans.bankDebitAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankDebitName') }：</th>
								<td><input name="corBankAcntTrans.bankDebitName" id="bankDebitName" class="width_c" value="${corBankAcntTrans.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankDebitCardNum') }：</th>
								<td><input name="corBankAcntTrans.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${corBankAcntTrans.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transType') }：</th>
								<td><input name="corBankAcntTrans.transType" id="transType" class="width_c" value="${corBankAcntTrans.transType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.direction') }：</th>
								<td><input name="corBankAcntTrans.direction" id="direction" class="width_c" value="${corBankAcntTrans.direction}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.amount') }：</th>
								<td><input name="corBankAcntTrans.amount" id="amount" class="width_c" value="${corBankAcntTrans.amount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transCurrency') }：</th>
								<td><input name="corBankAcntTrans.transCurrency" id="transCurrency" class="width_c" value="${corBankAcntTrans.transCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transTime') }：</th>
								<td><input name="corBankAcntTrans.transTime" id="transTime" class="width_c" value="${corBankAcntTrans.transTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transDate') }：</th>
								<td><input name="corBankAcntTrans.transDate" id="transDate" class="width_c" value="${corBankAcntTrans.transDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transComments') }：</th>
								<td><input name="corBankAcntTrans.transComments" id="transComments" class="width_c" value="${corBankAcntTrans.transComments}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transStatus') }：</th>
								<td><input name="corBankAcntTrans.transStatus" id="transStatus" class="width_c" value="${corBankAcntTrans.transStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankTransNum') }：</th>
								<td><input name="corBankAcntTrans.bankTransNum" id="bankTransNum" class="width_c" value="${corBankAcntTrans.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankMsgCode') }：</th>
								<td><input name="corBankAcntTrans.bankMsgCode" id="bankMsgCode" class="width_c" value="${corBankAcntTrans.bankMsgCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.returnCode') }：</th>
								<td><input name="corBankAcntTrans.returnCode" id="returnCode" class="width_c" value="${corBankAcntTrans.returnCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.errMsg') }：</th>
								<td><input name="corBankAcntTrans.errMsg" id="errMsg" class="width_c" value="${corBankAcntTrans.errMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.handleTime') }：</th>
								<td><input name="corBankAcntTrans.handleTime" id="handleTime" class="width_c" value="${corBankAcntTrans.handleTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.operator') }：</th>
								<td><input name="corBankAcntTrans.operator" id="operator" class="width_c" value="${corBankAcntTrans.operator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.handleMsg') }：</th>
								<td><input name="corBankAcntTrans.handleMsg" id="handleMsg" class="width_c" value="${corBankAcntTrans.handleMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.handleStatus') }：</th>
								<td><input name="corBankAcntTrans.handleStatus" id="handleStatus" class="width_c" value="${corBankAcntTrans.handleStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.isValid') }：</th>
								<td><input name="corBankAcntTrans.isValid" id="isValid" class="width_c" value="${corBankAcntTrans.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.createTime') }：</th>
								<td><input name="corBankAcntTrans.createTime" id="createTime" class="width_c" value="${corBankAcntTrans.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.updateTime') }：</th>
								<td><input name="corBankAcntTrans.updateTime" id="updateTime" class="width_c" value="${corBankAcntTrans.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.creator') }：</th>
								<td><input name="corBankAcntTrans.creator" id="creator" class="width_c" value="${corBankAcntTrans.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.updator') }：</th>
								<td><input name="corBankAcntTrans.updator" id="updator" class="width_c" value="${corBankAcntTrans.updator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankBalanceAfterTrans') }：</th>
								<td><input name="corBankAcntTrans.bankBalanceAfterTrans" id="bankBalanceAfterTrans" class="width_c" value="${corBankAcntTrans.bankBalanceAfterTrans}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankFrozenAmoumt') }：</th>
								<td><input name="corBankAcntTrans.bankFrozenAmoumt" id="bankFrozenAmoumt" class="width_c" value="${corBankAcntTrans.bankFrozenAmoumt}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankAvaliableAmount') }：</th>
								<td><input name="corBankAcntTrans.bankAvaliableAmount" id="bankAvaliableAmount" class="width_c" value="${corBankAcntTrans.bankAvaliableAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.transRate') }：</th>
								<td><input name="corBankAcntTrans.transRate" id="transRate" class="width_c" value="${corBankAcntTrans.transRate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corBankAcntTrans.bankNum') }：</th>
								<td><input name="corBankAcntTrans.bankNum" id="bankNum" class="width_c" value="${corBankAcntTrans.bankNum}" maxlength="225"/></td>
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
