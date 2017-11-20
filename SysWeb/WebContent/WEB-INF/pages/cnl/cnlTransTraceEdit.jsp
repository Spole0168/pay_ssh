<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlTransTraceEditForm").validate({
		rules: {
			"cnlTransTrace.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reqInnerNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.reqNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.reqBatch": {required: true,stringMaxLength:22,isLegal: true},
			"cnlTransTrace.transType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.transDc": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.tradeSubType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.transCurrency": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.transAmount": {required: true,stringMaxLength:22,isLegal: true},
			"cnlTransTrace.trnasStatus": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.trnasTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.transRate": {required: true,stringMaxLength:10,isLegal: true},
			"cnlTransTrace.transComments": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.bankAccepteTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankHandleNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReturnTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankReturnResult": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.bankPmtCnlType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.bankPmtCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.isinGl": {required: true,stringMaxLength:2,isLegal: true},
			"cnlTransTrace.isPrinted": {required: true,stringMaxLength:2,isLegal: true},
			"cnlTransTrace.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReqTrnasDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.bnakServiceFeeAct": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReqTransTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bnakHandlePriority": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.errType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.errCode": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.errMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleStatus": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.handleMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.operator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlTransTrace.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"cnlTransTrace.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.creator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlTransTrace.updator": {required: true,stringMaxLength:40,isLegal: true},
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
		window.location = "cnlTransTrace/cnlTransTraceList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlTransTraceEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlTransTraceEditForm").submit(); 
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

<s:form id="cnlTransTraceEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlTransTrace">
<s:hidden name="isModify"/>
<s:hidden name="cnlTransTrace.id" id="cnlTransTraceId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTraceEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlTransTrace.id') }：</th>
								<td><input name="cnlTransTrace.id" id="id" class="width_c" value="${cnlTransTrace.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reqInnerNum') }：</th>
								<td><input name="cnlTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlTransTrace.reqInnerNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.custCode') }：</th>
								<td><input name="cnlTransTrace.custCode" id="custCode" class="width_c" value="${cnlTransTrace.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCustCode') }：</th>
								<td><input name="cnlTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlTransTrace.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reqNum') }：</th>
								<td><input name="cnlTransTrace.reqNum" id="reqNum" class="width_c" value="${cnlTransTrace.reqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reqBatch') }：</th>
								<td><input name="cnlTransTrace.reqBatch" id="reqBatch" class="width_c" value="${cnlTransTrace.reqBatch}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transType') }：</th>
								<td><input name="cnlTransTrace.transType" id="transType" class="width_c" value="${cnlTransTrace.transType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transDc') }：</th>
								<td><input name="cnlTransTrace.transDc" id="transDc" class="width_c" value="${cnlTransTrace.transDc}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.tradeSubType') }：</th>
								<td><input name="cnlTransTrace.tradeSubType" id="tradeSubType" class="width_c" value="${cnlTransTrace.tradeSubType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transCurrency') }：</th>
								<td><input name="cnlTransTrace.transCurrency" id="transCurrency" class="width_c" value="${cnlTransTrace.transCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transAmount') }：</th>
								<td><input name="cnlTransTrace.transAmount" id="transAmount" class="width_c" value="${cnlTransTrace.transAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.trnasStatus') }：</th>
								<td><input name="cnlTransTrace.trnasStatus" id="trnasStatus" class="width_c" value="${cnlTransTrace.trnasStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transDate') }：</th>
								<td><input name="cnlTransTrace.transDate" id="transDate" class="width_c" value="${cnlTransTrace.transDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.trnasTime') }：</th>
								<td><input name="cnlTransTrace.trnasTime" id="trnasTime" class="width_c" value="${cnlTransTrace.trnasTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transRate') }：</th>
								<td><input name="cnlTransTrace.transRate" id="transRate" class="width_c" value="${cnlTransTrace.transRate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transComments') }：</th>
								<td><input name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankAccepteTime') }：</th>
								<td><input name="cnlTransTrace.bankAccepteTime" id="bankAccepteTime" class="width_c" value="${cnlTransTrace.bankAccepteTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
								<td><input name="cnlTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransTrace.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankHandleNum') }：</th>
								<td><input name="cnlTransTrace.bankHandleNum" id="bankHandleNum" class="width_c" value="${cnlTransTrace.bankHandleNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReturnTime') }：</th>
								<td><input name="cnlTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${cnlTransTrace.bankReturnTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReturnResult') }：</th>
								<td><input name="cnlTransTrace.bankReturnResult" id="bankReturnResult" class="width_c" value="${cnlTransTrace.bankReturnResult}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlType') }：</th>
								<td><input name="cnlTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${cnlTransTrace.bankPmtCnlType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlCode') }：</th>
								<td><input name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCnlCode') }：</th>
								<td><input name="cnlTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransTrace.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.isinGl') }：</th>
								<td><input name="cnlTransTrace.isinGl" id="isinGl" class="width_c" value="${cnlTransTrace.isinGl}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.isPrinted') }：</th>
								<td><input name="cnlTransTrace.isPrinted" id="isPrinted" class="width_c" value="${cnlTransTrace.isPrinted}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditName') }：</th>
								<td><input name="cnlTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransTrace.bankCreditName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCustName') }：</th>
								<td><input name="cnlTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransTrace.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditAcntCode') }：</th>
								<td><input name="cnlTransTrace.bankCreditAcntCode" id="bankCreditAcntCode" class="width_c" value="${cnlTransTrace.bankCreditAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitName') }：</th>
								<td><input name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCustName') }：</th>
								<td><input name="cnlTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransTrace.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitAcntCode') }：</th>
								<td><input name="cnlTransTrace.bankDebitAcntCode" id="bankDebitAcntCode" class="width_c" value="${cnlTransTrace.bankDebitAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTrnasDate') }：</th>
								<td><input name="cnlTransTrace.bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" value="${cnlTransTrace.bankReqTrnasDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakServiceFeeAct') }：</th>
								<td><input name="cnlTransTrace.bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" value="${cnlTransTrace.bnakServiceFeeAct}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTransTime') }：</th>
								<td><input name="cnlTransTrace.bankReqTransTime" id="bankReqTransTime" class="width_c" value="${cnlTransTrace.bankReqTransTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakHandlePriority') }：</th>
								<td><input name="cnlTransTrace.bnakHandlePriority" id="bnakHandlePriority" class="width_c" value="${cnlTransTrace.bnakHandlePriority}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.errType') }：</th>
								<td><input name="cnlTransTrace.errType" id="errType" class="width_c" value="${cnlTransTrace.errType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.errCode') }：</th>
								<td><input name="cnlTransTrace.errCode" id="errCode" class="width_c" value="${cnlTransTrace.errCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.errMsg') }：</th>
								<td><input name="cnlTransTrace.errMsg" id="errMsg" class="width_c" value="${cnlTransTrace.errMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleStatus') }：</th>
								<td><input name="cnlTransTrace.handleStatus" id="handleStatus" class="width_c" value="${cnlTransTrace.handleStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleMsg') }：</th>
								<td><input name="cnlTransTrace.handleMsg" id="handleMsg" class="width_c" value="${cnlTransTrace.handleMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleTime') }：</th>
								<td><input name="cnlTransTrace.handleTime" id="handleTime" class="width_c" value="${cnlTransTrace.handleTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.operator') }：</th>
								<td><input name="cnlTransTrace.operator" id="operator" class="width_c" value="${cnlTransTrace.operator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.isValid') }：</th>
								<td><input name="cnlTransTrace.isValid" id="isValid" class="width_c" value="${cnlTransTrace.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.createTime') }：</th>
								<td><input name="cnlTransTrace.createTime" id="createTime" class="width_c" value="${cnlTransTrace.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.updateTime') }：</th>
								<td><input name="cnlTransTrace.updateTime" id="updateTime" class="width_c" value="${cnlTransTrace.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.creator') }：</th>
								<td><input name="cnlTransTrace.creator" id="creator" class="width_c" value="${cnlTransTrace.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.updator') }：</th>
								<td><input name="cnlTransTrace.updator" id="updator" class="width_c" value="${cnlTransTrace.updator}" maxlength="225"/></td>
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
