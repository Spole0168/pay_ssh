<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
var verifyUrl = "verifyStatusSuccess.action";
var noVerifyUrl ="verifyStatusFail.action";

$().ready(function() {
	
	
	
	$("#verify").click(function(){
		$.boxUtil.confirm(
			'请确认是否审核通过？', 
			null, 
			function(){
				doVerify();
			}, 
			function(){
				//return false;
			});
		return false;
	});

	$("#noVerify").click(function(){
		$.boxUtil.confirm(
				'请确认是否审核不通过？', 
				null, 
				function(){
					doNoVerify();
				}, 
				function(){
					//return false;
				});
			return false;
	});
	
	//下载凭证文件
	
	$("#download").click(function(){
		
	var fileId = $("#voucherLocation").val();
	if(fileId==null||fileId==""){
		$("div.warning span").html("没有可供下载的资源");
		$("div.warning").show();
		return;
	}
	$("div.warning span").html("");
	$("div.warning").hide();
	window.location = "downloadFile.action?fileId="+fileId;
		
	});
	

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doVerify(){
	var id = $("#id").val();
	

	$("#cnlTransTracePendingVerifyForm").attr("action",verifyUrl+"?id="+id);
	$("#cnlTransTracePendingVerifyForm").submit();
}

function doNoVerify(){
	var id = $("#id").val();
	var reviewMsg = $("#reviewMsg").val();
	
	$("#result").removeAttr("hidden"); 
	if(reviewMsg==null||reviewMsg==""){
		$("div.warning span").html("请填写审核失败原因");
		$("div.warning").show();
		return;
	}
	
	$("div.warning span").html("");
	$("div.warning").hide();
	$("#cnlTransTracePendingVerifyForm").attr("action",noVerifyUrl+"?id="+id+"&reviewMsg="+reviewMsg);
	$("#cnlTransTracePendingVerifyForm").submit();
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

<s:form id="cnlTransTracePendingVerifyForm" method="post" action=""  namespace="/cnlTransTracePending" enctype="multipart/form-data">
<s:hidden name="isModify"/>
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
                              <td hidden="true"><input name="cnlTransTrace.id" id="id" class="width_c" value="${cnlTransTrace.id}" maxlength="225"/></td>
							
                              <!--渠道订单号  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.reqInnerNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlTransTrace.reqInnerNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlTransTrace.reqInnerNum}" maxlength="225"/></td></td>
							</tr>
							<!--系统申请单流水号  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCustCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlTransTrace.cnlCustCode}" maxlength="225"/></td>
							     <td hidden="true"><input name="cnlTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlTransTrace.cnlCustCode}" maxlength="225"/></td>
							</tr>
							
							<!--  交易币种  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transCurrency') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transCurrency" id="transCurrency" class="width_c" value="${cnlTransTrace.transCurrency}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transCurrency" id="transCurrency" class="width_c" value="${cnlTransTrace.transCurrency}" maxlength="225"/></td>
							</tr>
							
							<!--金额  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transAmount') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transAmount" id="transAmount" class="width_c" value="${cnlTransTrace.transAmount}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transAmount" id="transAmount" class="width_c" value="${cnlTransTrace.transAmount}" maxlength="225"/></td>
							</tr>
							
							<!--交易方向  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transDc') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transDc" id="transDc" class="width_c" value="${cnlTransTrace.transDc}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transDc" id="transDc" class="width_c" value="${cnlTransTrace.transDc}" maxlength="225"/></td>
							</tr>
							<!-- 交易类型 -->
							<tr>
								<th><em>*</em>${app:i18n('transType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transType" id="transType" class="width_c" value="${cnlTransTrace.transType}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transType" id="transType" class="width_c" value="${cnlTransTrace.transType}" maxlength="225"/></td>
							</tr>
							<!-- 交易摘要 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transComments') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
							</tr>
							<!--  交易时间-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transTime" id="transTime" class="width_c" value="${cnlTransTrace.transTime}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransTrace.transTime" id="transTime" class="width_c" value="${cnlTransTrace.transTime}" maxlength="225"/></td>
							</tr>
							<!--银行处理优先级  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakHandlePriority') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakHandlePriority" id="bnakHandlePriority" class="width_c" value="${cnlTransTrace.bnakHandlePriority}" maxlength="225"/></td>
							    <td hidden="true"><input name="cnlTransTrace.bnakHandlePriority" id="bnakHandlePriority" class="width_c" value="${cnlTransTrace.bnakHandlePriority}" maxlength="225"/></td>
							</tr>
							<!--要求转账日期  -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTrnasDate') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" value="${cnlTransTrace.bankReqTrnasDate}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" value="${cnlTransTrace.bankReqTrnasDate}" maxlength="225"/></td>
							</tr>
							<!-- 要求转账时间 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReqTransTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTransTime" id="bankReqTransTime" class="width_c" value="${cnlTransTrace.bankReqTransTime}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankReqTransTime" id="bankReqTransTime" class="width_c" value="${cnlTransTrace.bankReqTransTime}" maxlength="225"/></td>
							</tr>
							<!-- 支付转账手续费账号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bnakServiceFeeAct') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" value="${cnlTransTrace.bnakServiceFeeAct}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" value="${cnlTransTrace.bnakServiceFeeAct}" maxlength="225"/></td>
							</tr>
							
							<!-- 银行交易流水号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							
							
							<!-- 收款银行 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款户名 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransTrace.bankDebitCustName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransTrace.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<!-- 收款卡号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankDebitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransTrace.bankDebitCardNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransTrace.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<!-- 付款银行 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransTrace.bankCreditName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransTrace.bankCreditName}" maxlength="225"/></td>
							</tr>
							<!-- 付款户名 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransTrace.bankCreditCustName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransTrace.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<!-- 付款卡号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankCreditCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransTrace.bankCreditCardNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransTrace.bankCreditCardNum}" maxlength="225"/></td>
							</tr>
							<!--  支付通道-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							</tr>
							<!-- 渠道来源标识 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.cnlCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransTrace.cnlCnlCode}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransTrace.cnlCnlCode}" maxlength="225"/></td>
							</tr>
							<!--  交易终端类型-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.termialType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.termialType" id="termialType" class="width_c" value="${cnlTransTrace.termialType}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.termialType" id="termialType" class="width_c" value="${cnlTransTrace.termialType}" maxlength="225"/></td>
							</tr>
								
							
							<!--  到账时间-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankReturnTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${cnlTransTrace.bankReturnTime}" maxlength="225" /></td>
							
							<td hidden="true"><input name="cnlTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${cnlTransTrace.bankReturnTime}" maxlength="225"/></td>
							</tr>
							
							<!-- 支付通道类型 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankPmtCnlType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${cnlTransTrace.bankPmtCnlType}" maxlength="225"></td>
								<td hidden="true"><input name="cnlTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${cnlTransTrace.bankPmtCnlType}" maxlength="225"/></td>
								
							</tr>
							
							<!--银行摘要 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.Comments') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transBankSummary" id="transBankSummary" class="width_c" value="${cnlTransTrace.transBankSummary}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.transBankSummary" id="transBankSummary" class="width_c" value="${cnlTransTrace.transBankSummary}" maxlength="225"/></td>
							</tr>
							
							
							<!-- 交易状态 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.transStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transStatus" id="transStatus" class="width_c" value="${cnlTransTrace.transStatus}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.transStatus" id="transStatus" class="width_c" value="${cnlTransTrace.transStatus}" maxlength="225"/></td>
							</tr>
							
							<!--  处理状态-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleStatus" id="handleStatus" class="width_c" value="${cnlTransTrace.handleStatus}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.handleStatus" id="handleStatus" class="width_c" value="${cnlTransTrace.handleStatus}" maxlength="225"/></td>
							</tr>
							<!-- 银行流水号 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransTrace.bankTransNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransTrace.bankTransNum}" maxlength="225"/></td>
							</tr>
							<!--  凭证号-->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.voucherNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.voucherNum" id="voucherNum" class="width_c" value="${cnlTransTrace.voucherNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransTrace.voucherNum" id="voucherNum" class="width_c" value="${cnlTransTrace.voucherNum}" maxlength="225"/></td>
							</tr>
							
							<!--  凭证-->
							
							<tr>
								<th><em></em>${app:i18n('JcnlTransTrace.voucherLocation') }：</th>
								<td><a id="download"  href="#"><span class="l-btn-text icon-down">${app:i18n('download')}</span></a></td>
							<td hidden="true"><input name="cnlTransTrace.voucherLocation" id="voucherLocation" class="width_c" value="${cnlTransTrace.voucherLocation}" maxlength="225"/></td>
							</tr>

							<!-- 请求人 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.operator') }：</th>
								<td><input disabled="true" name="cnlTransTrace.operator" id="operator" class="width_c" value="${cnlTransTrace.operator}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransTrace.operator" id="operator" class="width_c" value="${cnlTransTrace.operator}" maxlength="225"/></td>
							</tr>
							<!-- 请求时间 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleTime" id="handleTime" class="width_c" value="${cnlTransTrace.handleTime}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransTrace.handleTime" id="handleTime" class="width_c" value="${cnlTransTrace.handleTime}" maxlength="225"/></td>
							</tr>
							<!-- 请求说明 -->
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleMsg') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleMsg" id="handleMsg" class="width_c" value="${cnlTransTrace.handleMsg}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransTrace.handleMsg" id="handleMsg" class="width_c" value="${cnlTransTrace.handleMsg}" maxlength="225"/></td>
							</tr>
							<!-- 审核信息 失败原因-->
							<tr hidden="true" id="result">
                            <th><em>*</em>${app:i18n('cnlTransTrace.reviewMsg') }：</th>
							<td><input name="reviewMsg" id="reviewMsg" class="width_c"  maxlength="225"/></td>
							</tr>
							</tbody>
						</table>
					</form>
					
				</div>
			</div>
			<div id="tabs-1">
				<a id="verify" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-ok">${app:i18n('verifyPass')}</span></span></a>
				<a id="noVerify" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-error">${app:i18n('VerifyFailure')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
