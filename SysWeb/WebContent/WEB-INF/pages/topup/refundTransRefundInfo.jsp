<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<style type="text/css">
	img{height: auto; width: auto\9; width:100%;} 
</style>
<script type="text/javascript">

$().ready(function() {
	$("#cancel").click(function(){
		window.location="refundTransList.action";
	});
	
	// 处理状态
	var refundStatusRender = $("#refundStatusRender").attr('value');
	var refundStatus = getValue(refundStatusRender,'${cnlTransRefund.refundStatus}');
	$("#refundStatus").val(refundStatus);
	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function getValue(render,key) {
	var arr=render.split(/:|;/);
	for (i=0;i<arr.length;) {
		if (arr[i]==key) {
			return arr[i+1];
		}
		i=i+2;
	}
	return key;
}
</script>

<div id="alertDialog"></div>

<%-- <s:form id="cnlTransTracePendingVerifyForm" method="post" action=""  namespace="/refundTransRefund"> --%>
<s:form id="refundTransRefundInfoForm" method="post" action="" namespace="/refundTransRefund" enctype="multipart/form-data">
<s:hidden name="isModify"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.refundTransInfoJsp.title')}</h3>
		</div>
		<input id="refundStatusRender" name="refundStatusRender" type="hidden" value="${refundStatusRender}" />
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
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.id') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td>
							</tr>
							<!-- 退款金额  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.transAmount') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							</tr>		
							<!-- 请求退款联系人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqContacts') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
							</tr>
							
							<!-- 请求退款联系人电话  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqContactsPhone') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
							</tr>
							<!-- 退款原因 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
							</tr>
							<!-- 收款方开户名  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.custName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行名称  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.bankBranchName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行卡号  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.debitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<!-- 申请人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqPerson') }：</th>
								<td><input disabled="true" name="cnlTransRefund.handler" id="handler" class="width_c" value="${cnlTransRefund.handler}" maxlength="225"/></td>
							</tr>
							<!-- 申请时间 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqTime') }：</th>
								<td><input disabled="true" name="cnlTransRefund.handleTime" id="handleTime" class="width_c" value="${cnlTransRefund.handleTime}" maxlength="225"/></td>
							</tr>
							<!-- 审核人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reviewer') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewer" id="reviewer" class="width_c" value="${cnlTransRefund.reviewer}" maxlength="225"/></td>
							</tr>
							<!-- 审核时间 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reviewTime') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewTime" id="reviewTime" class="width_c" value="${cnlTransRefund.reviewTime}" maxlength="225"/></td>
							</tr>
							<!-- 失败原因 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.failureReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewMsg" id="reviewMsg" class="width_c" value="${cnlTransRefund.reviewMsg}" maxlength="225"/></td>
							</tr>
							<!-- 退款状态  -->
							<tr>
                           		<th>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundStatus') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundStatus" id="refundStatus" class="width_c" value="${refundStatus}" maxlength="225"/></td>
							</tr>
							<!-- 退款失败原因  -->
							<tr>
                            <th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundfFaillureReason') }：</th>
							<td><input disabled="true" name="refundFailReason" id="refundFailReason" class="width_c"  maxlength="225" value="${cnlTransRefund.refundFailReason}" /></td>
							</tr>
							<!--  凭证图片-->
							<tr>
								<th>${app:i18n('cnlTransRefund.voucherNum') }：</th>
								<td><input disabled="true"  name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.voucherLocation') }：</th>
								
								<td>
									<s:if test="cnlTransRefund.voucherLocation!=null">
										<a href="${cnlTransRefund.voucherLocation}" target="_blank">${app:i18n('cnlTransRefund.voucherLocation') }</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.refundVoucherNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundVoucherNum" id="refundVoucherNum" class="width_c" value="${cnlTransRefund.refundVoucherNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('cnlTransRefund.refundVoucherLocation') }：</th>
								<td>
									<s:if test="cnlTransRefund.refundVoucherLocation!=null">
										<a href="${cnlTransRefund.refundVoucherLocation}" target="_blank">${app:i18n('cnlTransRefund.refundVoucherLocation') }</a>
									</s:if>
								</td>
							</tr>
						</table>
					</form>
					
				</div>
			</div>
			<div id="tabs-1">
				<a id="cancel" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
