<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
$().ready(function() {

	$("#corCustEditForm").validate({
		rules: {
			"cor.id": {required: false,stringMaxLength:50,isLegal: true},
			"cor.custCode": {required: true,stringMaxLength:32,isLegal: true},
			"cor.localName": {required: true,stringMaxLength:50,isLegal: true},
			"cor.unitProperty": {required: true,stringMaxLength:6,isLegal: true},
			"cor.certType": {required: true,stringMaxLength:6,isLegal: true},
			"cor.certNum": {required: true,stringMaxLength:20,isLegal: true},
			"cor.certExpireDate": {required: true,stringMaxLength:20,isLegal: true},
			"cor.corporateName": {required: true,stringMaxLength:50,isLegal: true},
			"cor.corporateCertType": {required: true,stringMaxLength:6,isLegal: true},
			"cor.corporateCertNum": {required: true,stringMaxLength:20,isLegal: true},
			"cor.country": {required: true,stringMaxLength:6,isLegal: true},
			"cor.provience": {required: true,stringMaxLength:6,isLegal: true},
			"cor.city": {required: true,stringMaxLength:6,isLegal: true},
			"cor.regTime": {required: true,stringMaxLength:20,isLegal: true},
			"cor.companyRegAddr": {required: true,stringMaxLength:200,isLegal: true}, 
			"cor.businessScope": {required: true,stringMaxLength:1000,isLegal: true},
			"cor.industry": {required: true,stringMaxLength:6,isLegal: true},
			"cor.addr": {required: true,stringMaxLength:200,isLegal: true},
			"cor.phoneNum": {required: true,stringMaxLength:20,isLegal: true},
			"cor.companyWebsite": {required: true,stringMaxLength:100,isLegal: true},
			"cor.postcode": {required: true,stringMaxLength:40,isLegal: true},
			
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
		window.location = "cor/corCustList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corCustEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corCustEditForm").submit(); 
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

<s:form id="corCustEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cor">
<s:hidden name="isModify"/>
<s:hidden name="corCust.id" id="corCustId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corCust.corCustEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('corCust.custCode') }：</th>
								<td><input name="cor.custCode" id="custCode" class="width_c" value="${Cor.custCode}" maxlength="225" /></td>
							</tr>
						
							<tr>
								<th><em>*</em>${app:i18n('corCust.localName') }：</th>
								<td><input name="cor.localName" id="localName" class="width_c" value="${Cor.localName}" maxlength="225" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.unitProperty') }：</th>
								<td><select name="cor.unitProperty" id="unitProperty" class="width_c" maxlength="225">
									<option value="${Cor.unitProperty}">${Cor.unitProperty}</option>
									<option value="1"><--"国企"--></option>
									<option value="2"><--民营--></option>
									<option value="3"><--外企--></option>
									<option value="4"><--私企--></option>
									<option value="5"><--合资--></option>
									</select>
								
								<%-- <input name="cor.unitProperty" id="unitProperty" class="width_c" value="${Cor.unitProperty}" maxlength="225" /> --%></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCust.certType') }：</th>
								<td><select name="cor.certType" id="certType" class="width_c" maxlength="225">
									<option value="${Cor.certType}">${Cor.certType}</option>
									<option value="01"><--"营业执照"--></option>
									<option value="02"><--批文--></option>
									<option value="03"><--身份证--></option>
									<option value="04"><--军官证--></option>
									<option value="05"><--士兵证--></option>
									<option value="06"><--户口簿--></option>
									<option value="07"><--护照--></option>
									<option value="08"><--临时身份证--></option>
									<option value="09"><--港澳同胞回乡证--></option>
									<option value="10"><--警官证--></option>
									<option value="11"><--其它--></option>
									
									</select>
								<%-- <input name="cor.certType" id="certType" class="width_c" value="${Cor.certType}" maxlength="225" /> --%></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCust.certNum') }：</th>
								<td><input name="cor.certNum" id="certNum" class="width_c" value="${Cor.certNum}" maxlength="225" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCust.certExpireDate') }：</th>
								<td><input name="cor.certExpireDate" id="certExpireDate" class="width_c" readonly="true"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="225" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.corporateName') }：</th>
								<td><input name="cor.corporateName" id="corporateName" class="width_c" value="${Cor.corporateName}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.corporateCertType') }：</th>
								<td><select name="cor.corporateCertType" id="corporateCertType" maxlength="225">
									<option value="${cor.corporateCertType}">${cor.corporateCertType }</option>
									<option value="01"><--"营业执照"--></option>
									<option value="02"><--批文--></option>
									<option value="03"><--身份证--></option>
									<option value="04"><--军官证--></option>
									<option value="05"><--士兵证--></option>
									<option value="06"><--户口簿--></option>
									<option value="07"><--护照--></option>
									<option value="08"><--临时身份证--></option>
									<option value="09"><--港澳同胞回乡证--></option>
									<option value="10"><--警官证--></option>
									<option value="11"><--其它--></option>
									
									</select>
								<%-- <input name="cor.corporateCertType" id="corporateCertType" class="width_c" value="${Cor.corporateCertType}" maxlength="225" /> --%></td>
							</tr>
						
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.corporateCertNum') }：</th>
								<td><input name="cor.corporateCertNum" id="corporateCertNum" class="width_c" value="${Cor.corporateCertNum}" maxlength="225" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCust.country') }：</th>
								<td><select name="cor.country" id="country" class="width_c" maxlength="225">
									<option value="${Cor.country}">${Cor.country}</option>
									<option value="1"><--中国--></option>
									<option value="1"><--华夏--></option>
									<option value="1"><--中华--></option>
									</select></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustAddr.provience') }：</th>
								<td><select name="cor.provience" id="provience" class="width_c" maxlength="225">
									<option value="${Cor.provience}">${Cor.provience}</option>
									<option value="1"><--北京--></option>
									<option value="2"><--上海--></option>
									<option value="3"><--广州--></option>
									<option value="4"><--重庆--></option>
									<option value="5"><--河北--></option>
									
								</select></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustAddr.city') }：</th>
								<td><select name="cor.city" id="city" class="width_c" maxlength="225">
									<option value="${Cor.provience}">${Cor.provience}</option>
									<option value="1"><--"昌平区"--></option>
									<option value="2"><--浦东新区--></option>
									<option value="5"><--邯郸市--></option>
									<option value="4"><--永川区--></option>
									<option value="1"><--海淀区--></option>
									</select></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCust.regTime') }：</th>
								<td><input name="cor.regTime" id="regTime" class="width_c" readonly="true"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'jieTime\')||\'%y-%M-%d 23:59:59\'}'})" maxlength="225" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.companyRegAddr') }：</th>
								<td><input name="cor.companyRegAddr" id="companyRegAddr" class="width_c" value="${Cor.companyRegAddr}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.businessScope') }：</th>
								<td><input name="cor.businessScope" id="businessScope" class="width_c" value="${Cor.businessScope}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.industry') }：</th>
								<td><select name="cor.industry" id="industry" class="width_c" maxlength="225">
									<option value="${Cor.industry }">${Cor.industry }</option>
									<option value="01"><--"餐饮类"--></option>
									<option value="02"><--建工类--></option>
									<option value="03"><--租赁类--></option>
									<option value="04"><--服务业--></option>
									<option value="05"><--商贸类--></option>
									</select>
								<%-- <input name="cor.industry" id="industry" class="width_c" value="${Cor.industry}" maxlength="225"/> --%></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustAddr.addr') }：</th>
								<td><input name="cor.addr" id="addr" class="width_c" value="${Cor.addr}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustPhones.phoneNum') }：</th>
								<td><input name="cor.phoneNum" id="phoneNum" class="width_c" value="${Cor.phoneNum}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustCompany.companyFax') }：</th>
								<td><input name="cor.companyFax" id="companyFax" class="width_c" value="${Cor.companyFax}" maxlength="225"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corCustAddr.postcode') }：</th>
								<td><input name="cor.postcode" id="postcode" class="width_c" value="${Cor.postcode}" maxlength="225"/></td>
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
