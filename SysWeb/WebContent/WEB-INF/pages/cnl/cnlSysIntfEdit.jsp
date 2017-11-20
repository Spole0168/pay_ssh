<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlSysIntfEditForm").validate({
		rules: {
			"cnlSysIntfCfg.currency": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.perTransLimit": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.dayLimit": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.weekLimit": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.monthLimit": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.yearLimit": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.lmtOpt": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfCfg.lmtOptTime": {required: true,stringMaxLength:32,isLegal: true},
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
		$(".warning").hide();
		var perTransLimit = $("#perTransLimit").val();
		var dayLimit = $("#dayLimit").val();
		var weekLimit = $("#weekLimit").val();
		var monthLimit = $("#monthLimit").val();
		var yearLimit = $("#yearLimit").val();
		
		if(perTransLimit == "" || perTransLimit == null){
			$(".warning span").html("单笔支付限额不能为空！");
			$(".warning").show();
		}else if(dayLimit == "" || dayLimit == null){
			$(".warning span").html("每日支付限额不能为空！");
			$(".warning").show();
		}else if(weekLimit == "" || weekLimit == null){
			$(".warning span").html("每周支付限额不能为空！");
			$(".warning").show();
		}else if(monthLimit == "" || monthLimit == null){
			$(".warning span").html("每月支付限额不能为空！");
			$(".warning").show();
		}else if(yearLimit == "" || yearLimit == null){
			$(".warning span").html("每年支付限额不能为空！");
			$(".warning").show();
		}else{
			$.boxUtil.confirm(
				'请确认是否保存信息？', 
				null, 
				function(){
					$.ajax({
						type:'POST',
						dataType:'text',
						data:$('#cnlSysIntfEditForm').serialize(),
						url:'saveOrUpdateSysIntf.action?loadPageCache=true',
						success:function(msg){
							if(msg == "1"){
								$.growlUI('成功信息！', '修改成功！');
								location.href="/system/channel/cnlSysIntfList.action?loadPageCache=true";
							}
						}
					})
				}, 
				function(){
					//return false;
				});
			return false;
		}
	});
	

	$("#undo").click(function(){
		window.location = "cnlSysIntf/cnlSysIntfList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#cnlSysIntfEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlSysIntfEditForm").submit(); 
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

<s:form id="cnlSysIntfEditForm" method="post" namespace="/channel">
<s:hidden name="isModify"/>
<s:hidden name="cnlSysIntf.id" id="cnlSysIntfId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlSysIntf.cnlSysIntfEditJsp.title')}</h3>
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
							<tr style="display:none;">
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.id') }：</th>
								<td><input name="cnlSysIntfCfg.id" id="id" class="width_c" value="${cnlSysIntfCfg.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.cnlCustCode') }：</th>
								<td><input name="cnlSysIntfCfg.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlSysIntfCfg.cnlCustCode}" readonly="readonly"/></td>
								<td style="display:none;"><input name="cnlSysIntfCfg.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlSysIntfCfg.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.accessCode') }：</th>
								<td><input name="cnlSysIntfCfg.accessCode" id="accessCode" class="width_c" value="${cnlSysIntfCfg.accessCode}" maxlength="225" readonly="readonly"/></td>
								<td style="display:none;"><input name="cnlSysIntfCfg.accessCode" id="accessCode" class="width_c" value="${cnlSysIntfCfg.accessCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.currency') }：</th>
								<td>
									<select name="cnlSysIntfCfg.currency" id="currency" style="width:100%;">
										<s:iterator value="currencyList" var="currency">
											<s:if test="#currency.key == cnlSysIntfCfg.currency">
												<option value="${currency.key}" selected="selected">${currency.value}</option>
											</s:if>
											<s:else>
												<option value="${currency.key}">${currency.value}</option>
											</s:else>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.perTransLimit') }：</th>
								<td><input name="cnlSysIntfCfg.perTransLimit" id="perTransLimit" class="width_c" value="${cnlSysIntfCfg.perTransLimit}" maxlength="23" onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.dayLimit') }：</th>
								<td><input name="cnlSysIntfCfg.dayLimit" id="dayLimit" class="width_c" value="${cnlSysIntfCfg.dayLimit}" maxlength="23" onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.weekLimit') }：</th>
								<td><input name="cnlSysIntfCfg.weekLimit" id="weekLimit" class="width_c" value="${cnlSysIntfCfg.weekLimit}" maxlength="23" onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.monthLimit') }：</th>
								<td><input name="cnlSysIntfCfg.monthLimit" id="monthLimit" class="width_c" value="${cnlSysIntfCfg.monthLimit}" maxlength="23" onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfCfg.yearLimit') }：</th>
								<td><input name="cnlSysIntfCfg.yearLimit" id="yearLimit" class="width_c" value="${cnlSysIntfCfg.yearLimit}" maxlength="23" onkeyup="value=value.replace(/[^\d.]/g,'')"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlSysIntfCfg.comments') }：</th>
								<td><input name="cnlSysIntfCfg.comments" id="comments" class="width_c" value="${cnlSysIntfCfg.comments}" maxlength="1000"/></td>
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
