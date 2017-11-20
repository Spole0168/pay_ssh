<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	$("#undo").click(function(){
		
		closeDialog();
		refreshGrid();
	});
});	
</script>

<div id="editblockidiv" class="layout block m-b">
<div id="alertDialog"></div>

<s:form id="cnlTransTraceDetailForm" method="post" action="#" namespace="/cnlTransTrace">
<s:hidden name="isModify"/>
<s:hidden name="cnlTransTrace.id" id="cnlTransTraceId"/>
<div class="layout">
	<div class="block m-b">
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
					
							<!-- 渠道申请单号 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.reqInnerNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${cnlTransTrace.reqInnerNum}" maxlength="225"/></td>
						
							<!-- 系统申请单流水号-->
							
								<th>${app:i18n('ccnlTransTrace.cnlCustCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlTransTrace.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<!-- 交易币种 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.transCurrency') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transCurrency" id="transCurrency" class="width_c" value="${cnlTransTrace.transCurrency}" maxlength="225"/></td>
							
							<!-- 金额 -->
								<th>${app:i18n('ccnlTransTrace.transAmount') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transAmount" id="transAmount" class="width_c" value="${cnlTransTrace.transAmount}" maxlength="225"/></td>
							</tr>
							<!-- 交易方向-->
							<tr>
								<th>${app:i18n('ccnlTransTrace.transDc') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transDc" id="transDc" class="width_c" value="${cnlTransTrace.transDc}" maxlength="225"/></td>
							<!-- 交易类型-->
								<th>${app:i18n('ccnlTransTrace.transType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transType" id="transType" class="width_c" value="${cnlTransTrace.transType}" maxlength="225"/></td>
							</tr>
							<!-- 交易摘要 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.transComments') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
							<!-- 交易时间 -->
								<th>${app:i18n('ccnlTransTrace.transTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transTime" id="transTime" class="width_c" value="${cnlTransTrace.transTime}" maxlength="225"/></td>
							</tr>
							<!-- 银行处理优先级 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bnakHandlePriority') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakHandlePriority" id="bnakHandlePriority" class="width_c" value="${cnlTransTrace.bnakHandlePriority}" maxlength="225"/></td>
							<!-- 要求转账日期 -->
								<th>${app:i18n('ccnlTransTrace.bankReqTrnasDate') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" value="${cnlTransTrace.bankReqTrnasDate}" maxlength="225"/></td>
							</tr>
							<!-- 要求转账时间 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankReqTransTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReqTransTime" id="bankReqTransTime" class="width_c" value="${cnlTransTrace.bankReqTransTime}" maxlength="225"/></td>
							<!-- 支付转账手续费账号 -->
								<th>${app:i18n('ccnlTransTrace.bnakServiceFeeAct') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" value="${cnlTransTrace.bnakServiceFeeAct}" maxlength="225"/></td>
							</tr>
							
							<!-- 银行交易流水号-->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankTransNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransTrace.bankTransNum}" maxlength="225"/></td>
							<!-- 收款银行-->
								<th>${app:i18n('ccnlTransTrace.bankDebitName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransTrace.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款户名-->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankDebitCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransTrace.bankDebitCustName}" maxlength="225"/></td>
							<!-- 收款卡号 -->
								<th>${app:i18n('ccnlTransTrace.bankDebitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransTrace.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<!-- 付款银行 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankCreditName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${cnlTransTrace.bankCreditName}" maxlength="225"/></td>
							<!-- 付款户名 -->
								<th>${app:i18n('ccnlTransTrace.bankCreditCustName') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${cnlTransTrace.bankCreditCustName}" maxlength="225"/></td>
							</tr>
							<!-- 付款卡号 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankCreditCardNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${cnlTransTrace.bankCreditCardNum}" maxlength="225"/></td>
							<!-- 支付通道 -->
								<th>${app:i18n('ccnlTransTrace.bankPmtCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" value="${cnlTransTrace.bankPmtCnlCode}" maxlength="225"/></td>
							</tr>
							<!-- 渠道名称 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.cnlCnlCode') }：</th>
								<td><input disabled="true" name="cnlTransTrace.cnlCnlCode" id="cnlCnlCode" class="width_c" value="${cnlTransTrace.cnlCnlCode}" maxlength="225"/></td>
							<!-- 交易终端类型 -->
								<th>${app:i18n('ccnlTransTrace.termialType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.termialType" id="termialType" class="width_c" value="${cnlTransTrace.termialType}" maxlength="225"/></td>
							</tr>
							
							<!-- 交易状态-->
							<tr>
								<th>${app:i18n('ccnlTransTrace.transStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transStatus" id="transStatus" class="width_c" value="${cnlTransTrace.transStatus}" maxlength="225"/></td>
							
							
							<!-- 处理状态 -->
								<th>${app:i18n('ccnlTransTrace.handleStatus') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleStatus" id="handleStatus" class="width_c" value="${cnlTransTrace.handleStatus}" maxlength="225"/></td>
							</tr>
							<!-- 到帐时间-->
							
							<tr>
								<th>${app:i18n('ccnlTransTrace.bankReturnTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${cnlTransTrace.bankReturnTime}" maxlength="225"/></td>
							<!-- 支付通道类型-->
								<th>${app:i18n('ccnlTransTrace.bankPmtCnlType') }：</th>
								<td><input disabled="true" name="cnlTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${cnlTransTrace.bankPmtCnlType}" maxlength="225"/></td>
							</tr>
							
							<!-- 摘要 -->
							
							<tr>
								<th>${app:i18n('ccnlTransTrace.Comments') }：</th>
								<td><input disabled="true" name="cnlTransTrace.transComments" id="transComments" class="width_c" value="${cnlTransTrace.transComments}" maxlength="225"/></td>
							
							    <!-- 请求人 -->
								<th>${app:i18n('ccnlTransTrace.operator') }：</th>
								<td><input disabled="true" name="cnlTransTrace.operator" id="operator" class="width_c" value="${cnlTransTrace.operator}" maxlength="225"/></td>
						
							</tr>
							
							<!-- 请求时间 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.handleTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleTime" id="handleTime" class="width_c" value="${cnlTransTrace.handleTime}" maxlength="225"/></td>
							<!--请求说明 -->
								<th>${app:i18n('ccnlTransTrace.handleMsg') }：</th>
								<td><input disabled="true" name="cnlTransTrace.handleMsg" id="handleMsg" class="width_c" value="${cnlTransTrace.handleMsg}" maxlength="225"/></td>
							</tr>
							<!-- 审核人 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.reviewer') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewer" id="reviewer" class="width_c" value="${cnlTransTrace.reviewer}" maxlength="225"/></td>
							<!-- 审核时间-->
								<th>${app:i18n('ccnlTransTrace.reviewTime') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewTime" id="reviewTime" class="width_c" value="${cnlTransTrace.reviewTime}" maxlength="225"/></td>
							</tr>
							<!--审核失败原因 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.reviewResult') }：</th>
								<td><input disabled="true" name="cnlTransTrace.reviewResult" id="reviewResult" class="width_c" value="${cnlTransTrace.reviewResult}" maxlength="225"/></td>
							
							    
						        <!-- 凭证号-->
								<th>${app:i18n('ccnlTransTrace.voucherNum') }：</th>
								<td><input disabled="true" name="cnlTransTrace.voucherNum" id="voucherNum" class="width_c" value="${cnlTransTrace.voucherNum}" maxlength="225"/></td>
							
							
							</tr>
							<!--凭证图片 -->
							<tr>
								<th>${app:i18n('ccnlTransTrace.voucherLocation') }：</th>
								<td><img src="cnlTransTrace.voucherLocation"  height="50" width="180"></td>
							</tr>
							
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
</div>