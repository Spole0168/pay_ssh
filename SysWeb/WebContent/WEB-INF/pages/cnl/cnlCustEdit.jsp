<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlCustEditForm").validate({
		rules: {
			"cnlCust.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCust.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCust.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCust.localName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCust.englishName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCust.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlCust.cnlCustType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCust.custStatus": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCust.country": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCust.regTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCust.certType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCust.certNum": {required: true,stringMaxLength:20,isLegal: true},
			"cnlCust.certCopy": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCust.certExpireDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlCust.custLevel": {required: true,stringMaxLength:6,isLegal: true},
			"cnlCust.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"cnlCust.realNameInfoCnl": {required: true,stringMaxLength:50,isLegal: true},
			"cnlCust.creator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCust.updator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlCust.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlCust.updateTime": {required: true,stringMaxLength:11,isLegal: true},
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
		window.location = "cnlCust/cnlCustList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlCustEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlCustEditForm").submit(); 
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

<s:form id="cnlCustEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlCust">
<s:hidden name="isModify"/>
<s:hidden name="cnlCust.id" id="cnlCustId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCust.cnlCustEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlCust.id') }：</th>
								<td><input name="cnlCust.id" id="id" class="width_c" value="${cnlCust.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.cnlCustCode') }：</th>
								<td><input name="cnlCust.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlCust.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.custCode') }：</th>
								<td><input name="cnlCust.custCode" id="custCode" class="width_c" value="${cnlCust.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.localName') }：</th>
								<td><input name="cnlCust.localName" id="localName" class="width_c" value="${cnlCust.localName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.englishName') }：</th>
								<td><input name="cnlCust.englishName" id="englishName" class="width_c" value="${cnlCust.englishName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.cnlCnlCode') }：</th>
								<td><input name="cnlCust.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlCust.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.cnlCustType') }：</th>
								<td><input name="cnlCust.cnlCustType" id="cnlCustType" class="width_c" value="${cnlCust.cnlCustType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.custStatus') }：</th>
								<td><input name="cnlCust.custStatus" id="custStatus" class="width_c" value="${cnlCust.custStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.country') }：</th>
								<td><input name="cnlCust.country" id="country" class="width_c" value="${cnlCust.country}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.regTime') }：</th>
								<td><input name="cnlCust.regTime" id="regTime" class="width_c" value="${cnlCust.regTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.certType') }：</th>
								<td><input name="cnlCust.certType" id="certType" class="width_c" value="${cnlCust.certType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.certNum') }：</th>
								<td><input name="cnlCust.certNum" id="certNum" class="width_c" value="${cnlCust.certNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.certCopy') }：</th>
								<td><input name="cnlCust.certCopy" id="certCopy" class="width_c" value="${cnlCust.certCopy}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.certExpireDate') }：</th>
								<td><input name="cnlCust.certExpireDate" id="certExpireDate" class="width_c" value="${cnlCust.certExpireDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.custLevel') }：</th>
								<td><input name="cnlCust.custLevel" id="custLevel" class="width_c" value="${cnlCust.custLevel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.isValid') }：</th>
								<td><input name="cnlCust.isValid" id="isValid" class="width_c" value="${cnlCust.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.realNameInfoCnl') }：</th>
								<td><input name="cnlCust.realNameInfoCnl" id="realNameInfoCnl" class="width_c" value="${cnlCust.realNameInfoCnl}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.creator') }：</th>
								<td><input name="cnlCust.creator" id="creator" class="width_c" value="${cnlCust.creator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.updator') }：</th>
								<td><input name="cnlCust.updator" id="updator" class="width_c" value="${cnlCust.updator}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.createTime') }：</th>
								<td><input name="cnlCust.createTime" id="createTime" class="width_c" value="${cnlCust.createTime}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlCust.updateTime') }：</th>
								<td><input name="cnlCust.updateTime" id="updateTime" class="width_c" value="${cnlCust.updateTime}" maxlength="225"/></td>
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
