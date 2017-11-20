<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#demoEditForm").validate({
		rules: {
			"demo.demoName": {required: true,stringMaxLength:50,isLegal: true},
			"demo.demoDescription": {required: true,stringMaxLength:225,isLegal: true}
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
		window.location = "demo/demoList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#demoEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#demoEditForm").submit(); 
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

<s:form id="demoEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/demo">
<s:hidden name="isModify"/>
<s:hidden name="demo.id" id="demoId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('demo.demoEditJsp.title')}</h3>
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
									<th><em>*</em>${app:i18n('demo.demoName') }：</th>
									<td><input name="demo.demoName" id="demoName" class="width_c" value="${demo.demoName}" maxlength="50"/></td>
								</tr>
								<tr>
									<th><em>*</em>${app:i18n('demo.demoDescription') }：</th>
									<td><input name="demo.demoDescription" id="demoDescription" class="width_c" value="${demo.demoDescription}" maxlength="225"/></td>
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
