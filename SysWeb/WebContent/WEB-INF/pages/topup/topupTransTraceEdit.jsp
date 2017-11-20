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
			"cnlTransTrace.stlNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.transType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transDc": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transSubType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transOrderNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.transCurrency": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transAmount": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transLatestAmount": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.transTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.transRate": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transComments": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.termialType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankReqTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankAccepteTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankHandleNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReturnTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankReturnResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankPmtCnlType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankPmtCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.isinGl": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.inglTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.printedTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.isPrinted": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.bankCreditAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditBranchName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditBranchCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitBranchName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitBranchCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankReqTrnasDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.bnakServiceFeeAct": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReqTransTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bnakHandlePriority": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.returnUrl": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransTrace.errType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.errCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.errMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.handleResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.handleMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.operator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewer": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.reviewStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.voucherNum": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransTrace.voucherLocation": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransTrace.isValid": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.creator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.updator": {required: true,stringMaxLength:50,isLegal: true},
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
		window.location = "topupTransTrace/topupTransTraceList.action?loadPageCache=true";
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
			<h3>${app:i18n('topupTransTrace.topupTransTraceEditJsp.title')}</h3>
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
							<!-- 
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.id') }：</th>
								<td><input name="cnlTransTrace.id" id="id" class="width_c" value="${cnlTransTrace.id}" maxlength="225"/></td>
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
								<th><em>*</em>${app:i18n('cnlTransTrace.reqBatch') }：</th>
								<td><input name="cnlTransTrace.reqBatch" id="reqBatch" class="width_c" value="${cnlTransTrace.reqBatch}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.stlNum') }：</th>
								<td><input name="cnlTransTrace.stlNum" id="stlNum" class="width_c" value="${cnlTransTrace.stlNum}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transSubType') }：</th>
								<td><input name="cnlTransTrace.transSubType" id="transSubType" class="width_c" value="${cnlTransTrace.transSubType}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transOrderNum') }：</th>
								<td><input name="cnlTransTrace.transOrderNum" id="transOrderNum" class="width_c" value="${cnlTransTrace.transOrderNum}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transDate') }：</th>
								<td><input name="cnlTransTrace.transDate" id="transDate" class="width_c" value="${cnlTransTrace.transDate}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transRate') }：</th>
								<td><input name="cnlTransTrace.transRate" id="transRate" class="width_c" value="${cnlTransTrace.transRate}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTime') }：</th>
								<td><input name="cnlTransTrace.bankReqTime" id="bankReqTime" class="width_c" value="${cnlTransTrace.bankReqTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankAccepteTime') }：</th>
								<td><input name="cnlTransTrace.bankAccepteTime" id="bankAccepteTime" class="width_c" value="${cnlTransTrace.bankAccepteTime}" maxlength="225"/></td>
							</tr>
	
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankHandleNum') }：</th>
								<td><input name="cnlTransTrace.bankHandleNum" id="bankHandleNum" class="width_c" value="${cnlTransTrace.bankHandleNum}" maxlength="225"/></td>
							</tr>

														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlCode') }：</th>
								<td><input name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							</tr>

							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.isinGl') }：</th>
								<td><input name="cnlTransTrace.isinGl" id="isinGl" class="width_c" value="${cnlTransTrace.isinGl}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.inglTime') }：</th>
								<td><input name="cnlTransTrace.inglTime" id="inglTime" class="width_c" value="${cnlTransTrace.inglTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.printedTime') }：</th>
								<td><input name="cnlTransTrace.printedTime" id="printedTime" class="width_c" value="${cnlTransTrace.printedTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.isPrinted') }：</th>
								<td><input name="cnlTransTrace.isPrinted" id="isPrinted" class="width_c" value="${cnlTransTrace.isPrinted}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditAcntCode') }：</th>
								<td><input name="cnlTransTrace.bankCreditAcntCode" id="bankCreditAcntCode" class="width_c" value="${cnlTransTrace.bankCreditAcntCode}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCode') }：</th>
								<td><input name="cnlTransTrace.bankCreditCode" id="bankCreditCode" class="width_c" value="${cnlTransTrace.bankCreditCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditBranchName') }：</th>
								<td><input name="cnlTransTrace.bankCreditBranchName" id="bankCreditBranchName" class="width_c" value="${cnlTransTrace.bankCreditBranchName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditBranchCode') }：</th>
								<td><input name="cnlTransTrace.bankCreditBranchCode" id="bankCreditBranchCode" class="width_c" value="${cnlTransTrace.bankCreditBranchCode}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitAcntCode') }：</th>
								<td><input name="cnlTransTrace.bankDebitAcntCode" id="bankDebitAcntCode" class="width_c" value="${cnlTransTrace.bankDebitAcntCode}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCode') }：</th>
								<td><input name="cnlTransTrace.bankDebitCode" id="bankDebitCode" class="width_c" value="${cnlTransTrace.bankDebitCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitBranchName') }：</th>
								<td><input name="cnlTransTrace.bankDebitBranchName" id="bankDebitBranchName" class="width_c" value="${cnlTransTrace.bankDebitBranchName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitBranchCode') }：</th>
								<td><input name="cnlTransTrace.bankDebitBranchCode" id="bankDebitBranchCode" class="width_c" value="${cnlTransTrace.bankDebitBranchCode}" maxlength="225"/></td>
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
								<th><em>*</em>${app:i18n('cnlTransTrace.returnUrl') }：</th>
								<td><input name="cnlTransTrace.returnUrl" id="returnUrl" class="width_c" value="${cnlTransTrace.returnUrl}" maxlength="225"/></td>
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
								<th><em>*</em>${app:i18n('cnlTransTrace.handleResult') }：</th>
								<td><input name="cnlTransTrace.handleResult" id="handleResult" class="width_c" value="${cnlTransTrace.handleResult}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleMsg') }：</th>
								<td><input name="cnlTransTrace.handleMsg" id="handleMsg" class="width_c" value="${cnlTransTrace.handleMsg}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reviewMsg') }：</th>
								<td><input name="cnlTransTrace.reviewMsg" id="reviewMsg" class="width_c" value="${cnlTransTrace.reviewMsg}" maxlength="225"/></td>
							</tr>
														<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.voucherNum') }：</th>
								<td><input name="cnlTransTrace.voucherNum" id="voucherNum" class="width_c" value="${cnlTransTrace.voucherNum}" maxlength="225"/></td>
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
							<tr>
								<th>${app:i18n('topupTransTrace.reviewStatus') }：</th>
								<td><input name="topupTransTrace.reviewStatus" id="reviewStatus" class="width_c" value="${cnlTransTrace.reviewStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.reviewResult') }：</th>
								<td><input name="topupTransTrace.reviewResult" id="reviewResult" class="width_c" value="${cnlTransTrace.reviewResult}" maxlength="225"/></td>
							</tr>
							 -->
							 <tr>
								<th>${app:i18n('topupTransTrace.reqNum') }：</th>
								<td><input name="topupTransTrace.reqNum" id="reqNum" class="width_c" value="${topupTransTrace.reqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.reqInnerNum') }：</th>
								<td><input name="topupTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${topupTransTrace.reqInnerNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transCurrency') }：</th>
								<td><input name="topupTransTrace.transCurrency" id="transCurrency" class="width_c" value="${topupTransTrace.transCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transAmount') }：</th>
								<td><input name="topupTransTrace.transAmount" id="transAmount" class="width_c" value="${topupTransTrace.transAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transLatestAmount') }：</th>
								<td><input name="topupTransTrace.transLatestAmount" id="transLatestAmount" class="width_c" value="${topupTransTrace.transLatestAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankReturnTime') }：</th>
								<td><input name="topupTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${topupTransTrace.bankReturnTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transDc') }：</th>
								<td><input name="topupTransTrace.transDc" id="transDc" class="width_c" value="${topupTransTrace.transDc}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transType') }：</th>
								<td><input name="topupTransTrace.transType" id="transType" class="width_c" value="${topupTransTrace.transType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transComments') }：</th>
								<td><input name="topupTransTrace.transComments" id="transComments" class="width_c" value="${topupTransTrace.transComments}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transTime') }：</th>
								<td><input name="topupTransTrace.transTime" id="transTime" class="width_c" value="${topupTransTrace.transTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitName') }：</th>
								<td><input name="topupTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${topupTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitCustName') }：</th>
								<td><input name="topupTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${topupTransTrace.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitCardNum') }：</th>
								<td><input name="topupTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${topupTransTrace.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
														<tr>
								<th>${app:i18n('topupTransTrace.bankCreditName') }：</th>
								<td><input name="topupTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${topupTransTrace.bankCreditName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankCreditCustName') }：</th>
								<td><input name="topupTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${topupTransTrace.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankCreditCardNum') }：</th>
								<td><input name="topupTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${topupTransTrace.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.cnlCnlCode') }：</th>
								<td><input name="topupTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${topupTransTrace.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transStatus') }：</th>
								<td><input name="topupTransTrace.transStatus" id="transStatus" class="width_c" value="${topupTransTrace.transStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankTransNum') }：</th>
								<td><input name="topupTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${topupTransTrace.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankPmtCnlType') }：</th>
								<td><input name="topupTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${topupTransTrace.bankPmtCnlType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.termialType') }：</th>
								<td><input name="topupTransTrace.termialType" id="termialType" class="width_c" value="${topupTransTrace.termialType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.voucherLocation') }：</th>
								<td><input name="topupTransTrace.voucherLocation" id="voucherLocation" class="width_c" value="${topupTransTrace.voucherLocation}" maxlength="225"/></td>
							</tr>				
							<tr>
								<th>${app:i18n('topupTransTrace.handleStatus') }：</th>
								<td><input name="topupTransTrace.handleStatus" id="handleStatus" class="width_c" value="${topupTransTrace.handleStatus}" maxlength="225"/></td>
							</tr>					
							<tr>
								<th>${app:i18n('topupTransTrace.bankReturnResult') }：</th>
								<td><input name="topupTransTrace.bankReturnResult" id="bankReturnResult" class="width_c" value="${topupTransTrace.bankReturnResult}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.operator') }：</th>
								<td><input name="topupTransTrace.operator" id="operator" class="width_c" value="${topupTransTrace.operator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.handleTime') }：</th>
								<td><input name="topupTransTrace.handleTime" id="handleTime" class="width_c" value="${topupTransTrace.handleTime}" maxlength="225"/></td>
							</tr>

							<tr>
								<th>${app:i18n('topupTransTrace.reviewer') }：</th>
								<td><input name="topupTransTrace.reviewer" id="reviewer" class="width_c" value="${topupTransTrace.reviewer}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.reviewTime') }：</th>
								<td><input name="topupTransTrace.reviewTime" id="reviewTime" class="width_c" value="${topupTransTrace.reviewTime}" maxlength="225"/></td>
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
