<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlTransRefundEditForm").validate({
		rules: {
			"cnlTransRefund.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransRefund.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransRefund.reqNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransRefund.refundContact": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundContactTel": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundReason": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransRefund.refundTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransRefund.refundCurrency": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundAmount": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransRefund.refundDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransRefund.refundStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.voucherNum": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransRefund.voucherLocation": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransRefund.refundFailReason": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransRefund.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransRefund.bankCreditCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankCreditCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankCreditCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.handler": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransRefund.reviewer": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.reviewResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.reviewErrCode": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransRefund.reviewTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransRefund.reviewMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransRefund.isValid": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransRefund.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransRefund.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransRefund.creator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.updator": {required: true,stringMaxLength:50,isLegal: true},
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
		window.location = "cnlTransRefund/cnlTransRefundList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlTransRefundEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlTransRefundEditForm").submit(); 
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

<s:form id="cnlTransRefundEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlTransRefund">
<s:hidden name="isModify"/>
<s:hidden name="cnlTransRefund.id" id="cnlTransRefundId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.cnlTransRefundEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlTransRefund.id') }：</th>
								<td><input name="cnlTransRefund.id" id="id" class="width_c" value="${cnlTransRefund.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundCode') }：</th>
								<td><input name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.cnlCnlCode') }：</th>
								<td><input name="cnlTransRefund.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransRefund.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reqNum') }：</th>
								<td><input name="cnlTransRefund.reqNum" id="reqNum" class="width_c" value="${cnlTransRefund.reqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundContact') }：</th>
								<td><input name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundContactTel') }：</th>
								<td><input name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundReason') }：</th>
								<td><input name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundTime') }：</th>
								<td><input name="cnlTransRefund.refundTime" id="refundTime" class="width_c" value="${cnlTransRefund.refundTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundCurrency') }：</th>
								<td><input name="cnlTransRefund.refundCurrency" id="refundCurrency" class="width_c" value="${cnlTransRefund.refundCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundAmount') }：</th>
								<td><input name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundDate') }：</th>
								<td><input name="cnlTransRefund.refundDate" id="refundDate" class="width_c" value="${cnlTransRefund.refundDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundStatus') }：</th>
								<td><input name="cnlTransRefund.refundStatus" id="refundStatus" class="width_c" value="${cnlTransRefund.refundStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.voucherNum') }：</th>
								<td><input name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.voucherLocation') }：</th>
								<td><input name="cnlTransRefund.voucherLocation" id="voucherLocation" class="width_c" value="${cnlTransRefund.voucherLocation}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundFailReason') }：</th>
								<td><input name="cnlTransRefund.refundFailReason" id="refundFailReason" class="width_c" value="${cnlTransRefund.refundFailReason}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankTransNum') }：</th>
								<td><input name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankCreditCustName') }：</th>
								<td><input name="cnlTransRefund.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransRefund.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankCreditName') }：</th>
								<td><input name="cnlTransRefund.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransRefund.bankCreditName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankCreditCode') }：</th>
								<td><input name="cnlTransRefund.bankCreditCode" id="bankCreditCode" class="width_c" value="${cnlTransRefund.bankCreditCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankCreditCardNum') }：</th>
								<td><input name="cnlTransRefund.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransRefund.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitCustName') }：</th>
								<td><input name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitName') }：</th>
								<td><input name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitCode') }：</th>
								<td><input name="cnlTransRefund.bankDebitCode" id="bankDebitCode" class="width_c" value="${cnlTransRefund.bankDebitCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitCardNum') }：</th>
								<td><input name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.handler') }：</th>
								<td><input name="cnlTransRefund.handler" id="handler" class="width_c" value="${cnlTransRefund.handler}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.handleTime') }：</th>
								<td><input name="cnlTransRefund.handleTime" id="handleTime" class="width_c" value="${cnlTransRefund.handleTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reviewer') }：</th>
								<td><input name="cnlTransRefund.reviewer" id="reviewer" class="width_c" value="${cnlTransRefund.reviewer}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reviewResult') }：</th>
								<td><input name="cnlTransRefund.reviewResult" id="reviewResult" class="width_c" value="${cnlTransRefund.reviewResult}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reviewErrCode') }：</th>
								<td><input name="cnlTransRefund.reviewErrCode" id="reviewErrCode" class="width_c" value="${cnlTransRefund.reviewErrCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reviewTime') }：</th>
								<td><input name="cnlTransRefund.reviewTime" id="reviewTime" class="width_c" value="${cnlTransRefund.reviewTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.reviewMsg') }：</th>
								<td><input name="cnlTransRefund.reviewMsg" id="reviewMsg" class="width_c" value="${cnlTransRefund.reviewMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.isValid') }：</th>
								<td><input name="cnlTransRefund.isValid" id="isValid" class="width_c" value="${cnlTransRefund.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.createTime') }：</th>
								<td><input name="cnlTransRefund.createTime" id="createTime" class="width_c" value="${cnlTransRefund.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.updateTime') }：</th>
								<td><input name="cnlTransRefund.updateTime" id="updateTime" class="width_c" value="${cnlTransRefund.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.creator') }：</th>
								<td><input name="cnlTransRefund.creator" id="creator" class="width_c" value="${cnlTransRefund.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.updator') }：</th>
								<td><input name="cnlTransRefund.updator" id="updator" class="width_c" value="${cnlTransRefund.updator}" maxlength="225"/></td>
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
