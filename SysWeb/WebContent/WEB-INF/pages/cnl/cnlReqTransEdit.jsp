<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlReqTransEditForm").validate({
		rules: {
			/* "cnlReqTrans.id": {required: true,stringMaxLength:32,isLegal: true}, */
			"cnlReqTrans.reqInnerNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlReqTrans.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlReqTrans.reqNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlReqTrans.reqBatch": {required: true,stringMaxLength:22,isLegal: true},
			"cnlReqTrans.reqType": {required: true,stringMaxLength:6,isLegal: true},
			"cnlReqTrans.reqStatus": {required: true,stringMaxLength:6,isLegal: true},
			"cnlReqTrans.timeZone": {required: true,stringMaxLength:6,isLegal: true},
			"cnlReqTrans.msgTime": {required: true,stringMaxLength:30,isLegal: true},
			"cnlReqTrans.recieveTime": {required: true,stringMaxLength:30,isLegal: true},
			"cnlReqTrans.handleTime": {required: true,stringMaxLength:30,isLegal: true},
			"cnlReqTrans.msgCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlReqTrans.errCode": {required: true,stringMaxLength:6,isLegal: true},
			"cnlReqTrans.errMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlReqTrans.isValid": {required: true,stringMaxLength:2,isLegal: true},
			"cnlReqTrans.createTime": {required: true,stringMaxLength:30,isLegal: true},
			"cnlReqTrans.updateTime": {required: true,stringMaxLength:30,isLegal: true},
			"cnlReqTrans.creator": {required: true,stringMaxLength:40,isLegal: true},
			"cnlReqTrans.updator": {required: true,stringMaxLength:40,isLegal: true},
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
		window.location = "cnlReqTrans/cnlReqTransList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlReqTransEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlReqTransEditForm").submit(); 
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

<s:form id="cnlReqTransEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlReqTrans">
<s:hidden name="isModify"/>
<s:hidden name="cnlReqTrans.id" id="cnlReqTransId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlReqTrans.cnlReqTransEditJsp.title')}</h3>
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

							<%-- 
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.id') }：</th>
								<td><input name="cnlReqTrans.id" id="id" class="width_c" value="${cnlReqTrans.id}" maxlength="225"/></td>
							</tr> 
							--%>
							
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.reqInnerNum') }：</th>
								<td><input name="cnlReqTrans.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlReqTrans.reqInnerNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.cnlCnlCode') }：</th>
								<td><input name="cnlReqTrans.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlReqTrans.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.reqNum') }：</th>
								<td><input name="cnlReqTrans.reqNum" id="reqNum" class="width_c" value="${cnlReqTrans.reqNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.reqBatch') }：</th>
								<td><input name="cnlReqTrans.reqBatch" id="reqBatch" class="width_c" value="${cnlReqTrans.reqBatch}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.reqType') }：</th>
								<td><input name="cnlReqTrans.reqType" id="reqType" class="width_c" value="${cnlReqTrans.reqType}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.reqStatus') }：</th>
								<td><input name="cnlReqTrans.reqStatus" id="reqStatus" class="width_c" value="${cnlReqTrans.reqStatus}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.timeZone') }：</th>
								<td><input name="cnlReqTrans.timeZone" id="timeZone" class="width_c" value="${cnlReqTrans.timeZone}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.msgTime') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.msgTime" id="msgTime" class="width_c" value="${cnlReqTrans.msgTime}" maxlength="225"/> --%>
									<input name="cnlReqTrans.msgTime" id="msgTime" class="width_c" value="${cnlReqTrans.msgTime}" maxlength="225" onclick="WdatePicker()" readonly="readonly"
									onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.recieveTime') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.recieveTime" id="recieveTime" class="width_c" value="${cnlReqTrans.recieveTime}" maxlength="225"/> --%>
									<input name="cnlReqTrans.recieveTime" id="recieveTime" class="width_c" value="${cnlReqTrans.recieveTime}" maxlength="225" onclick="WdatePicker()" readonly="readonly"
									onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.handleTime') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.handleTime" id="handleTime" class="width_c" value="${cnlReqTrans.handleTime}" maxlength="225"/> --%>
									<input name="cnlReqTrans.handleTime" id="handleTime" class="width_c" value="${cnlReqTrans.handleTime}" maxlength="225" onclick="WdatePicker()" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.msgCode') }：</th>
								<td><input name="cnlReqTrans.msgCode" id="msgCode" class="width_c" value="${cnlReqTrans.msgCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.errCode') }：</th>
								<td><input name="cnlReqTrans.errCode" id="errCode" class="width_c" value="${cnlReqTrans.errCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.errMsg') }：</th>
								<td><input name="cnlReqTrans.errMsg" id="errMsg" class="width_c" value="${cnlReqTrans.errMsg}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.isValid') }：</th>
								<td><input name="cnlReqTrans.isValid" id="isValid" class="width_c" value="${cnlReqTrans.isValid}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.createTime') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.createTime" id="createTime" class="width_c" value="${cnlReqTrans.createTime}" maxlength="225"/> --%>
									<input name="cnlReqTrans.createTime" id="createTime" class="width_c" value="${cnlReqTrans.createTime}" maxlength="225" onclick="WdatePicker()" readonly="readonly"
									onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.updateTime') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.updateTime" id="updateTime" class="width_c" value="${cnlReqTrans.updateTime}" maxlength="225"/> --%>
									<input name="cnlReqTrans.updateTime" id="updateTime" class="width_c" value="${cnlReqTrans.updateTime}" maxlength="225" onclick="WdatePicker()" readonly="readonly"
									onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.creator') }：</th>
								<td>
									<%-- <input name="cnlReqTrans.creator" id="creator" class="width_c" value="${cnlReqTrans.creator}" maxlength="225"/> --%>
									<input name="cnlReqTrans.creator" id="creator" class="width_c" value="${cnlReqTrans.creator}" maxlength="225" onclick="WdatePicker()" readonly="readonly"
									onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlReqTrans.updator') }：</th>
								<td><input name="cnlReqTrans.updator" id="updator" class="width_c" value="${cnlReqTrans.updator}" maxlength="225"/></td>
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
