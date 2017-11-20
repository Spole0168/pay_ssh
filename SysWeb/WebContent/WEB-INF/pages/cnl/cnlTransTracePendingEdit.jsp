<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#cnlTransTracePendingEditForm").validate({
		rules: {
			/* "cnlTransTrace.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.cnlCustCode": {required: false,stringMaxLength:32,isLegal: true},//渠道订单号
			"cnlTransTrace.reqInnerNum": {required: false,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.transCurrency": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transAmount": {required: false,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transDc": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transType": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transComments": {required: false,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.transTime": {required: false,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.bnakHandlePriority": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankReqTrnasDate": {required: false,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.bankReqTransTime": {required: false,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.bnakServiceFeeAct": {required: false,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankDebitName": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCustName": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCardNum": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditName": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCustName": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCardNum": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankPmtCnlCode": {required: false,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.cnlCnlCode": {required: false,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.termialType": {required: false,stringMaxLength:50,isLegal: true}, */
			
			
			
			"cnlTransTrace.bankReturnTime": {required: false,stringMaxLength:30,isLegal: true},//到账时间
			"cnlTransTrace.bankPmtCnlType": {required: false,stringMaxLength:50,isLegal: true},//支付通道类型
			"cnlTransTrace.transComments": {required: false,stringMaxLength:1000,isLegal: true},
			
			"cnlTransTrace.bankTransNum": {required: false,stringMaxLength:25,isLegal: true},
			"cnlTransTrace.voucherNum": {required: false,stringMaxLength:25, isLegal: true},
			"cnlTransTrace.voucherLocation": {required: false,stringMaxLength:100,isLegal: true},
			
/* 			
			
			"cnlTransTrace.operator": {required: false,stringMaxLength:50,isLegal: true},//请求人
			"cnlTransTrace.handleTime": {required: false,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.handleMsg": {required: false,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.reviewer": {required: false,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewTime": {required: false,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.reviewResult": {required: false,stringMaxLength:50,isLegal: true}, */
			
			
			
			
			
			
			
     	/*     "cnlTransTrace.id": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reqInnerNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.custCode": {required: true,stringMaxLength:32,isLegal: true}, 
			"cnlTransTrace.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.reqNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.reqBatch": {required: true,stringMaxLength:22,isLegal: true},
			"cnlTransTrace.stlNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.transType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transDc": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transSubType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transOrderNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.transCurrency": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transAmount": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transLatestAmount": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.transDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.transTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.transRate": {required: true,stringMaxLength:20,isLegal: true},
			"cnlTransTrace.transComments": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.termialType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankReqTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankAccepteTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankTransNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankHandleNum": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReturnTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bankReturnResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankPmtCnlType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankPmtCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.cnlCnlCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.isinGl": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.printedTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.isPrinted": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.bankCreditAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankCreditName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditBranchName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditBranchCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankCreditCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitAcntCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankDebitName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitBranchName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitBranchCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCustName": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.bankDebitCardNum": {required: true,stringMaxLength:50,isLegal: true},
、、			"cnlTransTrace.bankReqTrnasDate": {required: true,stringMaxLength:7,isLegal: true},
			"cnlTransTrace.bnakServiceFeeAct": {required: true,stringMaxLength:32,isLegal: true},
			"cnlTransTrace.bankReqTransTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.bnakHandlePriority": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.returnUrl": {required: true,stringMaxLength:200,isLegal: true},
			"cnlTransTrace.errType": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.errCode": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.errMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleStatus": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.handleMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.handleTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.operator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.isValid": {required: true,stringMaxLength:6,isLegal: true},
			"cnlTransTrace.createTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.updateTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.creator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.updator": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewer": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewMsg": {required: true,stringMaxLength:1000,isLegal: true},
			"cnlTransTrace.reviewResult": {required: true,stringMaxLength:50,isLegal: true},
			"cnlTransTrace.reviewTime": {required: true,stringMaxLength:11,isLegal: true},
			"cnlTransTrace.voucherNum": {required: true,stringMaxLength:100,isLegal: true},
			"cnlTransTrace.voucherLocation": {required: true,stringMaxLength:100,isLegal: true}, */
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
		window.location = "cnlTransTracePendingList.action";
		//history.back();
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){

		$("#cnlTransTracePendingEditForm").submit(); 

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

<s:form id="cnlTransTracePendingEditForm" method="post" action="cnlTransTracePendingUpdate" namespace="/cnl" enctype="multipart/form-data">
<s:hidden name="isModify"/>
<!--<s:hidden name="cnlTransTrace.id" id="cnlTransTraceId"/>-->
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTracePendingDetail.title')}</h3>
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
							
							 <td hidden="true"><input name="cnlTransTrace.id" id="id" class="width_c" value="${cnlTransTrace.id}" maxlength="225"/></td>
						
                              <!--渠道订单号  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reqInnerNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlTransTrace.reqInnerNum}" maxlength="225"/></td>
							</tr>
							<!--系统申请单流水号  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCustCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlTransTrace.cnlCustCode}" maxlength="225"/></td>
							</tr>
							
							<!--  交易币种  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transCurrency') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transCurrency" id="transCurrency" class="width_c" value="${cnlTransTrace.transCurrency}" maxlength="225"/></td>
							</tr>
							
							<!--金额  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transAmount') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transAmount" id="transAmount" class="width_c" value="${cnlTransTrace.transAmount}" maxlength="225"/></td>
							</tr>
							
							<!--交易方向  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transDc') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transDc" id="transDc" class="width_c" value="${cnlTransTrace.transDc}" maxlength="225"/></td>
							</tr>
							<!-- 交易类型 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transType') }：</th>
								<td><input disabled="true" name="transType" id="trans" class="width_c" value="${cnlTransTrace.transType}" maxlength="225"/></td>
							</tr>
							<!-- 交易摘要 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transComments') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
							</tr>
							<!--  交易时间-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transTime" id="transTime" class="width_c" value="${cnlTransTrace.transTime}" maxlength="225"/></td>
							</tr>
							<!--银行处理优先级  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakHandlePriority') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakHandlePriority" id="bnakHandlePriority" class="width_c" value="${cnlTransTrace.bnakHandlePriority}" maxlength="225"/></td>
							</tr>
							<!--要求转账日期  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTrnasDate') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" value="${cnlTransTrace.bankReqTrnasDate}" maxlength="225"/></td>
							</tr>
							<!-- 要求转账时间 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTransTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTransTime" id="bankReqTransTime" class="width_c" value="${cnlTransTrace.bankReqTransTime}" maxlength="225"/></td>
							</tr>
							
							
							
							<!-- 支付转账手续费账号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakServiceFeeAct') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" value="${cnlTransTrace.bnakServiceFeeAct}" maxlength="225"/></td>
							</tr>
							
							<!-- 银行交易流水号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							
							
							<!-- 收款银行 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款户名 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransTrace.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<!-- 收款卡号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransTrace.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<!-- 付款银行 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransTrace.bankCreditName}" maxlength="225"/></td>
							</tr>
							<!-- 付款户名 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransTrace.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<!-- 付款卡号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransTrace.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<!--  支付通道-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							</tr>
							<!-- 渠道来源标识 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransTrace.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<!--  交易终端类型-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.termialType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.termialType" id="termialType" class="width_c" value="${cnlTransTrace.termialType}" maxlength="225"/></td>
							</tr>
								
							
							<!--  到账时间-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReturnTime') }：</th>
								<td><input name="cnlTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${cnlTransTrace.bankReturnTime}" onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="225"/></td>
							</tr>
							
							
					
							<!-- 支付通道类型 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlType') }：</th>
								<td><select name="cnlTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${cnlTransTrace.bankPmtCnlType}" maxlength="225">
								<s:iterator value="bankPmtCnlTypeList" var="pmtList">
				            <option value="${pmtList.key}">${pmtList.value }</option>
				                 </s:iterator>
								</select></td>
							</tr>
							
							<!-- 摘要 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.Comments') }：</th>
								<td><input name="cnlTransTrace.transBankSummary" id=transBankSummary class="width_c" value="${cnlTransTrace.transBankSummary}" maxlength="225"/></td>
						
							</tr>
							
							
							<!-- 交易状态 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transStatus" id="transStatus" class="width_c" value="${cnlTransTrace.transStatus}" maxlength="225"/></td>
							</tr>
							
							<!--  处理状态-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleStatus" id="handleStatus" class="width_c" value="${cnlTransTrace.handleStatus}" maxlength="225"/></td>
							</tr>
							<!-- 银行流水号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
								<td><input name="cnlTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransTrace.bankTransNum}" maxlength="225"/></td>
						
							</tr>
							<!--  凭证号-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.voucherNum') }：</th>
								<td><input name="cnlTransTrace.voucherNum" id="voucherNum" class="width_c" value="${cnlTransTrace.voucherNum}" maxlength="225"/></td>
				
							</tr>
							
							<!--  凭证-->
							<tr>
								<th><em>*</em>${app:i18n('JcnlTransTrace.voucherLocation') }：</th>
								<td><input id="file" type="file" name="file" /></td>
							</tr>

							<!-- 审核人 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reviewer') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewer" id="reviewer" class="width_c" value="${cnlTransTrace.reviewer}" maxlength="225"/></td>
							</tr>
							<!-- 审核时间 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reviewTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewTime" id="reviewTime" class="width_c" value="${cnlTransTrace.reviewTime}" maxlength="225"/></td>
							</tr>
							<!-- 审核失败原因 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reviewResult') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewResult" id="reviewResult" class="width_c" value="${cnlTransTrace.reviewResult}" maxlength="225"/></td>
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
