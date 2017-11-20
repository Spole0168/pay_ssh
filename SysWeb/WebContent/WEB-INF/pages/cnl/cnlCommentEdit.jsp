<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
 
 $("#cnlCommentEditForm").validate({
		rules: {
			
			"cnlComment.cnlCustCompany.cnlCustCode": {
				required: true,
				stringMaxLength:32,
				isLegal: true,
				remote: {
					url: 'checkCnlCustCode.action',
					type: 'post', 
					dataType: 'json', 
					data: {
					 cnlCustCode:function(){
						 return $('#cnlCustCode').val();
						 }
					}
				}
			
			},
			"cnlComment.cnlCnlCfg.cnlCnlCode": {
				required: true,
				stringMaxLength:32,
				isLegal: true,

				remote: {
					url: 'cnlCnlCfgIsSame.action',
					type: 'post', 
					dataType: 'json', 
					data: {
					 cnlcnlcode:function(){
						 return $('#cnlCnlCode').val();
						 }
					}
				}
			},
			 
			"cnlComment.cnlCnlCfg.cnlSysName": {required: true,stringMaxLength:32,isLegal: true},
			"cnlComment.cnlSysIntf.accessType": {required: true,stringMaxLength:32,isLegal: true},
			"cnlComment.cnlSysIntf.accessKey":{required: true,stringMaxLength:32,isLegal: true},
			"cnlComment.cnlSysIntf.techSupportPhonenum": {isTelNo: true},
			"cnlComment.cnlSysIntf.techSupportEmail": {email: true},
			"cnlComment.cnlSysIntf.businessSupportPhonenum":{isTelNo:true},
			"cnlComment.cnlSysIntf.businessSupportEmail":{email:true}
			
		 
		},
		invalidHandler: function(e, validator) {
			var errors = validator.numberOfInvalids();
			//获取渠道接入类型的val
			var accessType=$("#accessType").val();
			console.info(accessType);
			if (errors) {
				var message = "请输入必填信息！";
				$("div.warning span").html(message);
				$("div.warning").show();
			} else {
				$("div.warning").hide();
			}
		},
		onkeyup : false,
		submitHandler : function() {
		
			document.cnlCommentEditForm.submit();
			//window.returnValue  =$("#id").val();
		 
		},
		messages : {
			"cnlComment.cnlCustCompany.cnlCustCode":{required: "必填字段",remote:"该客户渠道编码不存在"},
			"cnlComment.cnlCnlCfg.cnlCnlCode":{required: "必填字段",remote:"该渠道编码已存在"}
		}
	});
 	
	//短信  邮件  初始化
	$("input[type=radio][name='cnlComment.cnlSysIntf.sendSms']:eq(0)").attr("checked",true);
	
	$("input[type=radio][name='cnlComment.cnlSysIntf.sendEmail']:eq(0)").attr("checked",true);
	 
	 
	 
 
	
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

		
		return  false;
	});
	 
	
	$("#undo").click(function(){
		window.location = "channel/cnlCnlCfgList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	


function doSave(){	
	
	<c:if test="${isModify}">
		$("#cnlCommentEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlCommentEditForm").submit(); 
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

<s:form id="cnlCommentEditForm" method="post" action="saveOrUpdate.action" namespace="/channel" name="cnlCommentEditForm" >
<s:hidden name="cnlComment.cnlCustCompany.custCode" id="custCode"/>
<s:hidden name="cnlComment.cnlSysIntf.id" id="csiid"/>
<s:hidden name="cnlComment.cnlCnlCfg.id" id="id"/>
<s:hidden name="cnlComment.cnlCustCompany.id" id="cccid"/>
<s:hidden name="isModify"/>
 
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlComment.cnlCommentEditJsp.title')}</h3>
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
							<input type="hidden"   id="companyCnlCustCode">
							   <!-- 校验渠道客户编码是否存在 -->
								<th width="10%"><em>*</em>${app:i18n('cnlCustCode')}：</th>
								<%-- <td width="30%"><input  name="cnlComment.cnlCustCompany.cnlCustCode" value="${cnlComment.cnlCustCompany.cnlCustCode}" class="width_c" id="cnlCustCode" /></td> --%>
								<td width="30%"><input type="text" name="cnlComment.cnlCustCompany.cnlCustCode" id="cnlCustCode" value="${cnlComment.cnlCustCompany.cnlCustCode}" 
								 size="25" maxlength="100"
								 class="width_c"/>
								 </td>
								 <!-- 校验渠道编码是否存在 -->
								<th width="10%"><em>*</em>${app:i18n('cnlComment.cnlCnlCode') }：</th>
								<%-- <input name="cnlComment.cnlCnlCfg.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlComment.cnlCnlCfg.cnlCnlCode}" maxlength="225"/> --%>
								<td width="30%"><input type="text" name="cnlComment.cnlCnlCfg.cnlCnlCode" id="cnlCnlCode" value="${cnlComment.cnlCustCompany.cnlCnlCode}" 
								 size="25" maxlength="100"
								 class="width_c"/>
								
								</td>
							</tr>
							<tr>
								<th width="10%"><em>*</em>${app:i18n('cnlComment.cnlSysName') }：</th>
								<td width="30%"><input name="cnlComment.cnlCnlCfg.cnlSysName" id="cnlSysName" class="width_c" value="${cnlComment.cnlCnlCfg.cnlSysName}" maxlength="225"/></td>
							 
							
								<th width="10%">${app:i18n('cnlComment.cnlCnlCfg.cnlDesc') }：</th>
								<td width="30%"><input name="cnlComment.cnlCnlCfg.cnlDesc" id="cnlDesc" class="width_c" value="${cnlComment.cnlCnlCfg.cnlDesc}" maxlength="225"/></td>
								
							</tr>
							
							<%--  
							<tr>
							
								<th width="10%">${app:i18n('cnlComment.addr') }：</th>
								<td width="30%"><input name="cnlComment.cnlCustCompany.addr" id="addr" class="width_c" value="${cnlComment.cnlCustCompany.addr}" maxlength="225" disabled="disabled"/></td>
								
								
								<th width="10%">${app:i18n('cnlComment.country') }：</th>
								<td width="30%">
								 
							 	<input type="hidden" value="${cnlComment.cnlCustCompany.country}"  id="cnlCustCompanyCountry"/> 
								<select name="cnlComment.cnlCustCompany.country" id="country" class="width_c"  disabled="disabled" style="width:190px;">
								<option value="">请选择</option>
										<s:iterator  value="countryList" var="country">
											<option value="${country.key}">${country.value}</option>
										</s:iterator>
								</sel ect>
								</td>
							</tr> --%>
							 
								
							<tr>
								<th width="10%">${app:i18n('cnlComment.cnlCnlCfg.contractEffectDate') }：</th>
								<td width="30%">
								<input class="width_c" name="cnlComment.cnlCnlCfg.contractEffectDate"  id="contractEffectDate"  type="text" value="${cnlComment.cnlCnlCfg.contractEffectDate}" onFocus="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'contractExpireDate\')}'})">
								<%-- <input id="contractEffectDate" class="width_c" name="cnlComment.cnlCustCompany.contractEffectDate" type="text"  value="${cnlComment.cnlCustCompany.contractEffectDate}" maxlength="225" readonly="readonly"/> --%>
								</td>
								
								<th width="10%">${app:i18n('cnlComment.contractExpireDate') }：</th>
								<td width="30%">
								<%-- <input id="contractExpireDate" class="width_c" name="cnlComment.cnlCustCompany.contractExpireDate" type="text"  value="${cnlComment.cnlCustCompany.contractExpireDate}" maxlength="225" readonly="readonly"/> --%>
								
								<input class="width_c" name="cnlComment.cnlCnlCfg.contractExpireDate"  id="contractExpireDate"  type="text" value="${cnlComment.cnlCnlCfg.contractExpireDate}" onFocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'contractEffectDate\')}'})">
								</td>
							 
							</tr>
							<tr>
							
								<th width="10%"><em>*</em>${app:i18n('cnlComment.accessKey') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.accessKey" id="accessKey" class="width_c" value="${cnlComment.cnlSysIntf.accessKey}" maxlength="225"/></td>
							
								<th width="10%"><em>*</em>${app:i18n('cnlComment.accessType') }：</th>
								<td width="30%">
							 	<input type="hidden"  value="${cnlComment.cnlSysIntf.accessType}"  id="cnlSysIntfAccessType"> 
								<select name="cnlComment.cnlSysIntf.accessType" id="accessType" class="width_c"  value="${cnlComment.cnlSysIntf.accessType}" style="width:190px;">
								<option value="">请选择</option>
										<s:iterator  value="typeList" var="type">
										<c:if test="${type.key!='01'}">
											<option value="${type.key}">${type.value}</option>
										</c:if>
										</s:iterator>
								</select>
						 	
								
								</td>
							</tr>
							
								<tr>
								<th width="10%">${app:i18n('cnlComment.sendSms') }：</th>
								<td width="30%">
								
								
								<input name="cnlComment.cnlSysIntf.sendSms"  type="radio" value="是" class="width_a">是
								<input name="cnlComment.cnlSysIntf.sendSms"  type="radio" value="否" class="width_a">否
								
								<input type="hidden"  value="${cnlComment.cnlSysIntf.sendSms}"  id="cnlSysIntfSendSms"/> 
							 
								
								</td>
								
								
								<th width="10%">${app:i18n('cnlComment.sendEmail') }：</th>
								<td width="30%">
									<input type="hidden"  value="${cnlComment.cnlSysIntf.sendEmail}"  id="cnlSysIntfSendEmail"/> 
								<input name="cnlComment.cnlSysIntf.sendEmail"  type="radio" value="是" class="width_a">是
								<input name="cnlComment.cnlSysIntf.sendEmail"  type="radio" value="否" class="width_a">否
								
								</td>
							
							</tr>
								<tr>
								<th width="10%">${app:i18n('cnlComment.techSupportName') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.techSupportName" id="techSupportName" class="width_c" value="${cnlComment.cnlSysIntf.techSupportName}" maxlength="225"/></td>
								
								<th width="10%">${app:i18n('cnlComment.businessSupportName') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.businessSupportName" id="businessSupportName" class="width_c" value="${cnlComment.cnlSysIntf.businessSupportName}" maxlength="225"/></td>
								
								
								
							
							</tr>
							
								<tr>
								<th width="10%">${app:i18n('cnlComment.techSupportPhonenum') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.techSupportPhonenum" id="techSupportPhonenum" class="width_c" value="${cnlComment.cnlSysIntf.techSupportPhonenum}" maxlength="225"/></td>
								<th width="10%">${app:i18n('cnlComment.businessSupportPhonenum') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.businessSupportPhonenum" id="businessSupportPhonenum" class="width_c" value="${cnlComment.cnlSysIntf.businessSupportPhonenum}" maxlength="225"/></td>
								
								
							
							</tr>
								 
							
							 <tr>
								<th width="10%">${app:i18n('cnlComment.businessSupportEmail') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.businessSupportEmail" id="businessSupportEmail" class="width_c" value="${cnlComment.cnlSysIntf.businessSupportEmail}" maxlength="225"/></td>
								<th width="10%">${app:i18n('cnlComment.techSupportEmail') }：</th>
								<td width="30%"><input name="cnlComment.cnlSysIntf.techSupportEmail" id="techSupportEmail" class="width_c" value="${cnlComment.cnlSysIntf.techSupportEmail}" maxlength="225"/></td>
							
							</tr>
							<tr>
							<th width="10%">${app:i18n('cnlComment.comments') }：</th>
								<td width="30%">
								<input  type="hidden"  value="${cnlComment.cnlSysIntf.comments}" id="textarea">
								<textarea name="cnlComment.cnlSysIntf.comments" id="comments" class="width_c"   maxlength="225" style="width:190px"></textarea>
								
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
