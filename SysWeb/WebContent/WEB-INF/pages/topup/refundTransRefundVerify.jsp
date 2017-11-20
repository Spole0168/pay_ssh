<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<style type="text/css">
	img{height: auto; width: auto\9; width:100%;} 
</style>
<script type="text/javascript">
var verifyUrl = "verifyStatusSuccess.action";
var noVerifyUrl ="verifyStatusFail.action";

$().ready(function() {
	
	$("#refundStatus").val("${cnlTransRefund.refundStatus }");
	
	$("#verify").click(function(){
		var reviewMsg = $("#reviewMsg").val();
		if(reviewMsg!=null&&reviewMsg!=""){
			$("div.warning span").html("审核通过不能填写审核失败原因");
			$("div.warning").show();
			return;
		}
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
		var reviewMsg = $("#reviewMsg").val();
		if(reviewMsg==null||reviewMsg==""){
			$("div.warning span").html("请填写审核失败原因");
			$("div.warning").show();
			return;
		}
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

<s:form id="cnlTransTracePendingVerifyForm" method="post" action=""  namespace="/refundTransRefund">
<s:hidden name="isModify"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.title')}</h3>
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
                              <td hidden="true"><input name="cnlTransRefund.id" id="id" class="width_c" value="${cnlTransRefund.id}" maxlength="225"/></td>
							
                              <!--退款单号  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.Id') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td></td>
							</tr>
							<!--申请人  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.ReqPerson') }：</th>
								<td><input disabled="true" name="cnlTransRefund.creator" id="creator" class="width_c" value="${cnlTransRefund.creator}" maxlength="225"/></td>
							     <td hidden="true"><input name="cnlTransRefund.creator" id="creator" class="width_c" value="${cnlTransRefund.creator}" maxlength="225"/></td>
							</tr>
							
							<!--  申请时间  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.ReqTime') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundTime" id="refundTime" class="width_c" value="${cnlTransRefund.refundTime}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundTime" id="refundTime" class="width_c" value="${cnlTransRefund.refundTime}" maxlength="225"/></td>
							</tr>
							
							<!-- 退款状态 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.RefundStatus') }：</th>
<%-- 								<td><input disabled="true" name="cnlTransRefund.refundStatus" id="refundStatus" class="width_c" value="${cnlTransRefund.refundStatus}" maxlength="225"/></td> --%>
								<td>
									<select name="refundStatus" id="refundStatus" style="width:190px" disabled="true">
										<option value="" selected>${app:i18n('cnlTransRefund.refundSelected')}</option>
										<s:iterator value="%{refundStatusList}" id="refundStatusItem">
									        <option value="<s:property value="#refundStatusItem.key" />">
									        	<s:property value="#refundStatusItem.value" />
									        </option>
										</s:iterator>
							         </select>
								</td>
								<td hidden="true"><input name="cnlTransRefund.refundStatus" id="refundStatus2" class="width_c" value="${cnlTransRefund.refundStatus}" maxlength="225"/></td>
							</tr>
							
							<!-- 退款失败原因  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.RefundFailureReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundFailReason" id="refundFailReason" class="width_c" value="${cnlTransRefund.refundFailReason}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundFailReason" id="refundFailReason" class="width_c" value="${cnlTransRefund.refundFailReason}" maxlength="225"/></td>
							</tr>
							<!-- 请求退款联系人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.ReqContacts') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
							</tr>
							<!-- 请求退款联系人电话 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.ReqContactsPhone') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
							</tr>
							<!-- 退款原因  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.RefundReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
							</tr>
							<!-- 收款方名称  -->
<!-- 							<tr> -->
<%-- 								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.BankName') }：</th> --%>
<%-- 								<td><input disabled="true" name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td> --%>
<%-- 							    <td hidden="true"><input name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td> --%>
<!-- 							</tr> -->
							<!-- 收款方开户名  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.CustName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行名称 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.BankBranchName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行卡号 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.DebitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							
							<!-- 退款金额 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.TransAmount') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							</tr>
							
							<!-- 银行交易流水号 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.RefundCode') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							</tr>
							<!-- 凭证号 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.voucherNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							</tr>
							<!--  凭证图片-->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.voucherLocation') }：</th>
<!--  								<td><img src="cnlTransRefund.voucherLocation" height="50" width="180"></td>  -->
								<td>
<%-- 									<img src="${cnlTransRefund.voucherLocation}" > --%>
									<s:if test="cnlTransRefund.voucherLocation!=null">
										<a href="${cnlTransRefund.voucherLocation}" target="_blank">${app:i18n('cnlTransRefund.voucherLocation') }</a>
									</s:if>
								</td>
							<td hidden="true"><input name="cnlTransRefund.voucherLocation" id="voucherLocation" class="width_c" value="${cnlTransRefund.voucherLocation}" maxlength="225"/></td>
							</tr>
							
							<!-- 审核信息 失败原因-->
							<tr id="result">
                            <th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransVerifyJsp.verifyFailedReason') }：</th>
							<td><input id="reviewMsg" class="width_c"  maxlength="225"/></td>
							</tr>
							</tbody>
						</table>
					</form>
					
				</div>
			</div>
			<div id="tabs-1">
				<a id="verify" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('verifyPass')}</span></span></a>
				<a id="noVerify" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('VerifyFailure')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
