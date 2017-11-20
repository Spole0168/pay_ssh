<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
$().ready(function() {
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
		window.location = "corCust/corCustList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corCustPersonalEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corCustPersonalEditForm").submit(); 
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

<s:form id="corCustPersonalEditForm" method="post" action="corCustPersonalVerify.action?loadPageCache=true" namespace="/corCustPersonal">
<s:hidden name="isModify"/>
<s:hidden name="corCustPersonal.id" id="corCustPersonalId"/>
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
								<th><em>*</em>${app:i18n('corCustPersonal.custCode') }：</th>
								<td><input name="id" id="id" type="hidden"  value="${corCust.id}" maxlength="225"/>
								<input name="corCustCompound.custCode" disabled="disabled" id="custCode" class="width_c" value="${corCustCompound.custCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.localName') }：</th>
								<td><input name="corCustCompound.localName" disabled="disabled" id="localName" class="width_c" value="${corCustCompound.localName}" maxlength="225"/></td>
							</tr>
							
					
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.status') }：</th>
								<td>
									<select name = "status" id="status">
										<!-- 用户是否已验证： 01：未通过 02：已通过 03：待审核 04：无效  -->
										<option <s:if test="${'01' eq corCompound.status}"> selected="selected"</s:if> value="01">已通过</option>
										<option <s:if test="${'02' eq corCompound.status}"> selected="selected"</s:if> value="02">未通过</option>
										<option <s:if test="${'03' eq corCompound.status}"> selected="selected"</s:if> value="03">待审核</option>
										<option <s:if test="${'04' eq corCompound.status}"> selected="selected"</s:if> value="04">无效</option>
									</select>
									
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
