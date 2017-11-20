<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlCustCompanyEditForm").validate({
		rules: {
			"cnlCustCompany.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustCompany.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustCompany.isSmsVerified": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustCompany.companyType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.companyName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.companyCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.contractEffectDate": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustCompany.contractExpireDate": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustCompany.regTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustCompany.website": {required: true,stringMaxLength:100,isLegal: true},
			"cnlCustCompany.email": {required: true,stringMaxLength:100,isLegal: true},
			"cnlCustCompany.companyTel": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.companyRegAddr": {required: true,stringMaxLength:200,isLegal: true},
			"cnlCustCompany.financeContact": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.financeTel": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.finnaceEmail": {required: true,stringMaxLength:100,isLegal: true},
			"cnlCustCompany.regCapital": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustCompany.regCapitalCurrency": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.companyFax": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateCertType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateCertNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateCertCopy": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateCountry": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.corporateCertExpireDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlCustCompany.corporateTel": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.country": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.provience": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.city": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.district": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.addr": {required: true,stringMaxLength:200,isLegal: true},
			"cnlCustCompany.postcode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.isValid": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustCompany.industry": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustCompany.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustCompany.creator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustCompany.updator": {required: true,stringMaxLength:50,isLegal: true},
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
		window.location = "cnlCustCompany/cnlCustCompanyList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlCustCompanyEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlCustCompanyEditForm").submit(); 
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

<s:form id="cnlCustCompanyEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlCustCompany">
<s:hidden name="isModify"/>
<s:hidden name="cnlCustCompany.id" id="cnlCustCompanyId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustCompany.cnlCustCompanyEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlCustCompany.id') }：</th>
								<td><input name="cnlCustCompany.id" id="id" class="width_c" value="${cnlCustCompany.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.cnlCustCode') }：</th>
								<td><input name="cnlCustCompany.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlCustCompany.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.custCode') }：</th>
								<td><input name="cnlCustCompany.custCode" id="custCode" class="width_c" value="${cnlCustCompany.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.isSmsVerified') }：</th>
								<td><input name="cnlCustCompany.isSmsVerified" id="isSmsVerified" class="width_c" value="${cnlCustCompany.isSmsVerified}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyType') }：</th>
								<td><input name="cnlCustCompany.companyType" id="companyType" class="width_c" value="${cnlCustCompany.companyType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyName') }：</th>
								<td><input name="cnlCustCompany.companyName" id="companyName" class="width_c" value="${cnlCustCompany.companyName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyCode') }：</th>
								<td><input name="cnlCustCompany.companyCode" id="companyCode" class="width_c" value="${cnlCustCompany.companyCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.contractEffectDate') }：</th>
								<td><input name="cnlCustCompany.contractEffectDate" id="contractEffectDate" class="width_c" value="${cnlCustCompany.contractEffectDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.contractExpireDate') }：</th>
								<td><input name="cnlCustCompany.contractExpireDate" id="contractExpireDate" class="width_c" value="${cnlCustCompany.contractExpireDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.regTime') }：</th>
								<td><input name="cnlCustCompany.regTime" id="regTime" class="width_c" value="${cnlCustCompany.regTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.website') }：</th>
								<td><input name="cnlCustCompany.website" id="website" class="width_c" value="${cnlCustCompany.website}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.email') }：</th>
								<td><input name="cnlCustCompany.email" id="email" class="width_c" value="${cnlCustCompany.email}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyTel') }：</th>
								<td><input name="cnlCustCompany.companyTel" id="companyTel" class="width_c" value="${cnlCustCompany.companyTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyRegAddr') }：</th>
								<td><input name="cnlCustCompany.companyRegAddr" id="companyRegAddr" class="width_c" value="${cnlCustCompany.companyRegAddr}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.financeContact') }：</th>
								<td><input name="cnlCustCompany.financeContact" id="financeContact" class="width_c" value="${cnlCustCompany.financeContact}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.financeTel') }：</th>
								<td><input name="cnlCustCompany.financeTel" id="financeTel" class="width_c" value="${cnlCustCompany.financeTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.finnaceEmail') }：</th>
								<td><input name="cnlCustCompany.finnaceEmail" id="finnaceEmail" class="width_c" value="${cnlCustCompany.finnaceEmail}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.regCapital') }：</th>
								<td><input name="cnlCustCompany.regCapital" id="regCapital" class="width_c" value="${cnlCustCompany.regCapital}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.regCapitalCurrency') }：</th>
								<td><input name="cnlCustCompany.regCapitalCurrency" id="regCapitalCurrency" class="width_c" value="${cnlCustCompany.regCapitalCurrency}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.companyFax') }：</th>
								<td><input name="cnlCustCompany.companyFax" id="companyFax" class="width_c" value="${cnlCustCompany.companyFax}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateName') }：</th>
								<td><input name="cnlCustCompany.corporateName" id="corporateName" class="width_c" value="${cnlCustCompany.corporateName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateCertType') }：</th>
								<td><input name="cnlCustCompany.corporateCertType" id="corporateCertType" class="width_c" value="${cnlCustCompany.corporateCertType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateCertNum') }：</th>
								<td><input name="cnlCustCompany.corporateCertNum" id="corporateCertNum" class="width_c" value="${cnlCustCompany.corporateCertNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateCertCopy') }：</th>
								<td><input name="cnlCustCompany.corporateCertCopy" id="corporateCertCopy" class="width_c" value="${cnlCustCompany.corporateCertCopy}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateCountry') }：</th>
								<td><input name="cnlCustCompany.corporateCountry" id="corporateCountry" class="width_c" value="${cnlCustCompany.corporateCountry}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateCertExpireDate') }：</th>
								<td><input name="cnlCustCompany.corporateCertExpireDate" id="corporateCertExpireDate" class="width_c" value="${cnlCustCompany.corporateCertExpireDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.corporateTel') }：</th>
								<td><input name="cnlCustCompany.corporateTel" id="corporateTel" class="width_c" value="${cnlCustCompany.corporateTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.country') }：</th>
								<td><input name="cnlCustCompany.country" id="country" class="width_c" value="${cnlCustCompany.country}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.provience') }：</th>
								<td><input name="cnlCustCompany.provience" id="provience" class="width_c" value="${cnlCustCompany.provience}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.city') }：</th>
								<td><input name="cnlCustCompany.city" id="city" class="width_c" value="${cnlCustCompany.city}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.district') }：</th>
								<td><input name="cnlCustCompany.district" id="district" class="width_c" value="${cnlCustCompany.district}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.addr') }：</th>
								<td><input name="cnlCustCompany.addr" id="addr" class="width_c" value="${cnlCustCompany.addr}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.postcode') }：</th>
								<td><input name="cnlCustCompany.postcode" id="postcode" class="width_c" value="${cnlCustCompany.postcode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.isValid') }：</th>
								<td><input name="cnlCustCompany.isValid" id="isValid" class="width_c" value="${cnlCustCompany.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.industry') }：</th>
								<td><input name="cnlCustCompany.industry" id="industry" class="width_c" value="${cnlCustCompany.industry}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.createTime') }：</th>
								<td><input name="cnlCustCompany.createTime" id="createTime" class="width_c" value="${cnlCustCompany.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.updateTime') }：</th>
								<td><input name="cnlCustCompany.updateTime" id="updateTime" class="width_c" value="${cnlCustCompany.updateTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.creator') }：</th>
								<td><input name="cnlCustCompany.creator" id="creator" class="width_c" value="${cnlCustCompany.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustCompany.updator') }：</th>
								<td><input name="cnlCustCompany.updator" id="updator" class="width_c" value="${cnlCustCompany.updator}" maxlength="225"/></td>
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
