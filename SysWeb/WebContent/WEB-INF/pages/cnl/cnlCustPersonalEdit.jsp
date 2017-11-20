<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlCustPersonalEditForm").validate({
		rules: {
			"cnlCustPersonal.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustPersonal.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustPersonal.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCustPersonal.isSmsVerified": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCustPersonal.name": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustPersonal.regTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustPersonal.email": {required: true,stringMaxLength:80,isLegal: true},
			"cnlCustPersonal.country": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.provience": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.city": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.district": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.addr": {required: true,stringMaxLength:200,isLegal: true},
			"cnlCustPersonal.employer": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustPersonal.jobTitle": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustPersonal.workTel": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustPersonal.gender": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.birthday": {required: true,stringMaxLength:7,isLegal: true},
			"cnlCustPersonal.industry": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.highestEdu": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.phonenum": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustPersonal.postcode": {required: true,stringMaxLength:10,isLegal: true},
			"cnlCustPersonal.isMarried": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCustPersonal.spouseCountry": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.spouseLocalName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustPersonal.spouseEnglishName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCustPersonal.spouseCertType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCustPersonal.spouseCertNum": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustPersonal.spouseCertExpireDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlCustPersonal.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCustPersonal.spousePhonenum": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCustPersonal.creator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCustPersonal.updator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCustPersonal.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCustPersonal.updateTime": {required: true,stringMaxLength:11,isLegal: true},
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
		window.location = "cnlCustPersonal/cnlCustPersonalList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlCustPersonalEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlCustPersonalEditForm").submit(); 
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

<s:form id="cnlCustPersonalEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlCustPersonal">
<s:hidden name="isModify"/>
<s:hidden name="cnlCustPersonal.id" id="cnlCustPersonalId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustPersonal.cnlCustPersonalEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlCustPersonal.id') }：</th>
								<td><input name="cnlCustPersonal.id" id="id" class="width_c" value="${cnlCustPersonal.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.cnlCustCode') }：</th>
								<td><input name="cnlCustPersonal.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlCustPersonal.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.custCode') }：</th>
								<td><input name="cnlCustPersonal.custCode" id="custCode" class="width_c" value="${cnlCustPersonal.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.isSmsVerified') }：</th>
								<td><input name="cnlCustPersonal.isSmsVerified" id="isSmsVerified" class="width_c" value="${cnlCustPersonal.isSmsVerified}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.name') }：</th>
								<td><input name="cnlCustPersonal.name" id="name" class="width_c" value="${cnlCustPersonal.name}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.regTime') }：</th>
								<td><input name="cnlCustPersonal.regTime" id="regTime" class="width_c" value="${cnlCustPersonal.regTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.email') }：</th>
								<td><input name="cnlCustPersonal.email" id="email" class="width_c" value="${cnlCustPersonal.email}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.country') }：</th>
								<td><input name="cnlCustPersonal.country" id="country" class="width_c" value="${cnlCustPersonal.country}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.provience') }：</th>
								<td><input name="cnlCustPersonal.provience" id="provience" class="width_c" value="${cnlCustPersonal.provience}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.city') }：</th>
								<td><input name="cnlCustPersonal.city" id="city" class="width_c" value="${cnlCustPersonal.city}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.district') }：</th>
								<td><input name="cnlCustPersonal.district" id="district" class="width_c" value="${cnlCustPersonal.district}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.addr') }：</th>
								<td><input name="cnlCustPersonal.addr" id="addr" class="width_c" value="${cnlCustPersonal.addr}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.employer') }：</th>
								<td><input name="cnlCustPersonal.employer" id="employer" class="width_c" value="${cnlCustPersonal.employer}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.jobTitle') }：</th>
								<td><input name="cnlCustPersonal.jobTitle" id="jobTitle" class="width_c" value="${cnlCustPersonal.jobTitle}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.workTel') }：</th>
								<td><input name="cnlCustPersonal.workTel" id="workTel" class="width_c" value="${cnlCustPersonal.workTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.gender') }：</th>
								<td><input name="cnlCustPersonal.gender" id="gender" class="width_c" value="${cnlCustPersonal.gender}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.birthday') }：</th>
								<td><input name="cnlCustPersonal.birthday" id="birthday" class="width_c" value="${cnlCustPersonal.birthday}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.industry') }：</th>
								<td><input name="cnlCustPersonal.industry" id="industry" class="width_c" value="${cnlCustPersonal.industry}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.highestEdu') }：</th>
								<td><input name="cnlCustPersonal.highestEdu" id="highestEdu" class="width_c" value="${cnlCustPersonal.highestEdu}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.phonenum') }：</th>
								<td><input name="cnlCustPersonal.phonenum" id="phonenum" class="width_c" value="${cnlCustPersonal.phonenum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.postcode') }：</th>
								<td><input name="cnlCustPersonal.postcode" id="postcode" class="width_c" value="${cnlCustPersonal.postcode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.isMarried') }：</th>
								<td><input name="cnlCustPersonal.isMarried" id="isMarried" class="width_c" value="${cnlCustPersonal.isMarried}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseCountry') }：</th>
								<td><input name="cnlCustPersonal.spouseCountry" id="spouseCountry" class="width_c" value="${cnlCustPersonal.spouseCountry}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseLocalName') }：</th>
								<td><input name="cnlCustPersonal.spouseLocalName" id="spouseLocalName" class="width_c" value="${cnlCustPersonal.spouseLocalName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseEnglishName') }：</th>
								<td><input name="cnlCustPersonal.spouseEnglishName" id="spouseEnglishName" class="width_c" value="${cnlCustPersonal.spouseEnglishName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseCertType') }：</th>
								<td><input name="cnlCustPersonal.spouseCertType" id="spouseCertType" class="width_c" value="${cnlCustPersonal.spouseCertType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseCertNum') }：</th>
								<td><input name="cnlCustPersonal.spouseCertNum" id="spouseCertNum" class="width_c" value="${cnlCustPersonal.spouseCertNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spouseCertExpireDate') }：</th>
								<td><input name="cnlCustPersonal.spouseCertExpireDate" id="spouseCertExpireDate" class="width_c" value="${cnlCustPersonal.spouseCertExpireDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.isValid') }：</th>
								<td><input name="cnlCustPersonal.isValid" id="isValid" class="width_c" value="${cnlCustPersonal.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.spousePhonenum') }：</th>
								<td><input name="cnlCustPersonal.spousePhonenum" id="spousePhonenum" class="width_c" value="${cnlCustPersonal.spousePhonenum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.creator') }：</th>
								<td><input name="cnlCustPersonal.creator" id="creator" class="width_c" value="${cnlCustPersonal.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.updator') }：</th>
								<td><input name="cnlCustPersonal.updator" id="updator" class="width_c" value="${cnlCustPersonal.updator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.createTime') }：</th>
								<td><input name="cnlCustPersonal.createTime" id="createTime" class="width_c" value="${cnlCustPersonal.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCustPersonal.updateTime') }：</th>
								<td><input name="cnlCustPersonal.updateTime" id="updateTime" class="width_c" value="${cnlCustPersonal.updateTime}" maxlength="225"/></td>
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
