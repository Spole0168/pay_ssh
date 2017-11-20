<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function() {

/* 	$("#cnlCustAcntDtlEditForm").validate({
		rules: {
			"cnlCustAcntDtl.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustAcntDtl.acntDtlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.cnlAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.transNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.acntType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustAcntDtl.currency": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustAcntDtl.amount": {required: true,stringMaxLength:22,isLegal: true},
			"cnlCustAcntDtl.direction": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustAcntDtl.balance": {required: true,stringMaxLength:22,isLegal: true},
			"cnlCustAcntDtl.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlCustAcntDtl.transTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustAcntDtl.isPrinted": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCustAcntDtl.voucherNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustAcntDtl.comments": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlCustAcntDtl.transType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustAcntDtl.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCustAcntDtl.creator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCustAcntDtl.updator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCustAcntDtl.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustAcntDtl.updateTime": {required: true,stringMaxLength:11,isLegal: true},
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
	}); */
		
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
		window.location = "cnlCustAcntDtl/cnlCustAcntDtlList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlCustAcntDtlEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlCustAcntDtlEditForm").submit(); 
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

<s:form id="cnlCustAcntDtlEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlCustAcntDtl">
<s:hidden name="isModify"/>
<s:hidden name="cnlCustAcntDtl.id" id="cnlCustAcntDtlId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustAcntDtl.cnlCustAcntDtlEditJsp.title')}</h3>
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

							<%-- <tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.id') }：</th>
								<td><input name="cnlCustAcntDtl.id" id="id" class="width_c" value="${cnlCustAcntDtl.id}" maxlength="225"/></td>
							</tr> --%>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.acntDtlCode') }：</th>
								<td><input name="cnlCustAcntDtl.acntDtlCode" id="acntDtlCode" class="width_c" value="${cnlCustAcntDtl.acntDtlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.cnlAcntCode') }：</th>
								<td><input name="cnlCustAcntDtl.cnlAcntCode" id="cnlAcntCode" class="width_c" value="${cnlCustAcntDtl.cnlAcntCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.cnlCustCode') }：</th>
								<td><input name="cnlCustAcntDtl.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlCustAcntDtl.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.custCode') }：</th>
								<td><input name="cnlCustAcntDtl.custCode" id="custCode" class="width_c" value="${cnlCustAcntDtl.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.transNum') }：</th>
								<td><input name="cnlCustAcntDtl.transNum" id="transNum" class="width_c" value="${cnlCustAcntDtl.transNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.acntType') }：</th>
								<td><input name="cnlCustAcntDtl.acntType" id="acntType" class="width_c" value="${cnlCustAcntDtl.acntType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.currency') }：</th>
								<td><input name="cnlCustAcntDtl.currency" id="currency" class="width_c" value="${cnlCustAcntDtl.currency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.amount') }：</th>
								<td><input name="cnlCustAcntDtl.amount" id="amount" class="width_c" value="${cnlCustAcntDtl.amount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.direction') }：</th>
								<td><input name="cnlCustAcntDtl.direction" id="direction" class="width_c" value="${cnlCustAcntDtl.direction}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.balance') }：</th>
								<td><input name="cnlCustAcntDtl.balance" id="balance" class="width_c" value="${cnlCustAcntDtl.balance}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.transDate') }：</th>
								<td>
									<input name="cnlCustAcntDtl.transDate" id="transDate" class="width_c" value="${cnlCustAcntDtl.transDate}" maxlength="225" onclick="WdatePicker()" readonly="readonly"/>
									<%-- <input name="cnlCustAcntDtl.transDate" id="transDate" class="width_c" value="${cnlCustAcntDtl.transDate}" maxlength="225"/> --%>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.transTime') }：</th>
								<td>
									<input name="cnlCustAcntDtl.transTime" id="transTime" class="width_c" value="${cnlCustAcntDtl.transDate}" maxlength="225" onclick="WdatePicker()" readonly="readonly"/>
									<%-- <input name="cnlCustAcntDtl.transTime" id="transTime" class="width_c" value="${cnlCustAcntDtl.transTime}" maxlength="225"/> --%>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.isPrinted') }：</th>
								<td><input name="cnlCustAcntDtl.isPrinted" id="isPrinted" class="width_c" value="${cnlCustAcntDtl.isPrinted}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.voucherNum') }：</th>
								<td><input name="cnlCustAcntDtl.voucherNum" id="voucherNum" class="width_c" value="${cnlCustAcntDtl.voucherNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.comments') }：</th>
								<td><input name="cnlCustAcntDtl.comments" id="comments" class="width_c" value="${cnlCustAcntDtl.comments}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.transType') }：</th>
								<td><input name="cnlCustAcntDtl.transType" id="transType" class="width_c" value="${cnlCustAcntDtl.transType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.isValid') }：</th>
								<td><input name="cnlCustAcntDtl.isValid" id="isValid" class="width_c" value="${cnlCustAcntDtl.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.creator') }：</th>
								<td><input name="cnlCustAcntDtl.creator" id="creator" class="width_c" value="${cnlCustAcntDtl.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.updator') }：</th>
								<td><input name="cnlCustAcntDtl.updator" id="updator" class="width_c" value="${cnlCustAcntDtl.updator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.createTime') }：</th>
								<td>
									<input name="cnlCustAcntDtl.createTime" id="createTime" class="width_c" value="${cnlCustAcntDtl.transDate}" maxlength="225" onclick="WdatePicker()" readonly="readonly"/>
									<%-- <input name="cnlCustAcntDtl.createTime" id="createTime" class="width_c" value="${cnlCustAcntDtl.createTime}" maxlength="225"/> --%>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustAcntDtl.updateTime') }：</th>
								<td>
									<input name="cnlCustAcntDtl.updateTime" id="updateTime" class="width_c" value="${cnlCustAcntDtl.transDate}" maxlength="225" onclick="WdatePicker()" readonly="readonly"/>
									<%-- <input name="cnlCustAcntDtl.updateTime" id="updateTime" class="width_c" value="${cnlCustAcntDtl.updateTime}" maxlength="225"/> --%>
								</td>
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
