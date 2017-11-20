<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	//
	if($("#isModify").val() == "true") {
		$("div.editVisible").show();
	}else{
		$("div.editVisible").hide();
	}
	
	$("#cnlTransRefundEditForm").validate({
		rules: {
			//必输项校验
			"cnlTransRefund.refundContact": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundContactTel": {required: true,stringMaxLength:50,isLegal: true,number:true},
			"cnlTransRefund.refundReason": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransRefund.bankDebitCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransRefund.refundAmount": {required: true,stringMaxLength:20,isLegal: true,number:true},
			//选输项
			"cnlTransRefund.bankTransNum": {required: false,stringMaxLength:32,isLegal: false},
			"cnlTransRefund.voucherNum": {required: false,stringMaxLength:200,isLegal: false},
			
			//修改显示不可改部分
			"cnlTransRefund.reqNum": {required: false,stringMaxLength:32,isLegal: false},
			"cnlTransRefund.reviewer": {required: false,stringMaxLength:50,isLegal: false},
			"cnlTransRefund.reviewTime": {required: false,stringMaxLength:11,isLegal: false},
			"cnlTransRefund.reviewMsg": {required: false,stringMaxLength:1000,isLegal: false},
			"cnlTransRefund.refundStatus": {required: false,stringMaxLength:50,isLegal: false},
			"cnlTransRefund.refundFailReason": {required: false,stringMaxLength:1000,isLegal: false},
			accept: "image/png,image/gif,image/JPEG" 
		},
		invalidHandler: function(e, validator) {
			//必输项
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
		var file = $("#file").val();
// 		var voucherNum1 = $("#voucherNum").val();
// 		if(		(null!=file&&file!=""&&voucherNum1=="")||
// 				(null==file||file=="")&&(voucherNum1!="")){
// 			return alert("凭证文件文件和凭证号必须同时输入或者同时不输入！");
// 		}else if((null!=file&&file!="") && $.trim(voucherNum1)==""){
// 			return alert("凭证号不能为空");
// 		}
		if(null==file||""==file){
			return alert("请选收款择凭证图片！");
		}else{
			if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)){
	        	return alert("图片类型必须是.gif,jpg,png中的一种")
	        }
		}
		
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
		window.location = "refundTransRefund/refundTransList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	$("#cnlTransRefundEditForm").submit(); 
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

<s:form id="cnlTransRefundEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlTransRefund" enctype="multipart/form-data">

<s:hidden name="addFlag" value=""/>
<s:hidden name="isModify" value="false"/>
<s:hidden name="cnlTransRefund.id" id="cnlTransRefundId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.refundTransListJsp.title')}</h3>
		</div>
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
						<div class="warning" style="display:none;">
							<span></span>
						</div>
						<div id="edit_test" class="editVisible" style="display:none;">
						<table cellpadding="0" cellspacing="0" class="table_form" >
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
							<tr>
								<th>${app:i18n('cnlTransRefund.reqNum') }：</th>
								<td><input name="cnlTransRefund.reqNum" id="refundCode" class="width_c" value="${cnlTransRefund.reqNum}" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.reviewer') }：</th>
								<td><input name="cnlTransRefund.reviewer" id="bankTransNum" class="width_c" value="${cnlTransRefund.reviewer}" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.reviewTime') }：</th>
								<td><input name="cnlTransRefund.reviewTime" id="bankHandleNum" class="width_c" value="${cnlTransRefund.reviewTime }" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.reviewMsg') }：</th>
								<td><input name="cnlTransRefund.reviewMsg" id="bankReturnTime" class="width_c" value="${cnlTransRefund.reviewMsg }" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.refundStatus') }：</th>
								<td><input name="cnlTransRefund.refundStatus" id="bankReturnResult" class="width_c" value="${cnlTransRefund.refundStatus }" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.refundFailReason') }：</th>
								<td><input name="cnlTransRefund.refundFailReason" id="bankPmtCnlType" class="width_c" value="${cnlTransRefund.refundFailReason }" maxlength="225" readonly="readonly"/></td>
							</tr>
							</tbody>
						</table>
						<hr>
						</div>
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundContact') }：</th>
								<td><input name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundContactTel') }：</th>
								<td><input name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundReason') }：</th>
								<td><input name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.refundAmount') }：</th>
								<td><input name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitCardNum') }：</th>
								<td><input name="cnlTransRefund.bankDebitCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitCustName') }：</th>
								<td><input name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransRefund.bankDebitName') }：</th>
								<td><input name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							</tr>
							<tr>
	                            <th><em>*</em>${app:i18n('cnlTransRefund.voucherLocation') }：</th>
								<td>
									<!--<img src="cnlTransRefund.voucherLocation" height="50" width="180">-->
									<input id="file" type="file"  name="file" />
								</td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.bankTransNum') }：</th>
								<td><input name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.voucherNum') }：</th>
								<td><input name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.sumit')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.cancel')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
