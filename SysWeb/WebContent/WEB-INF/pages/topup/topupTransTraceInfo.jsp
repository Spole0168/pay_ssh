<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	
	$("#undo").click(function(){
		window.location = "topupTransTrace/topupTransTraceList.action?loadPageCache=true";
	});
	// 交易状态
	var transStatusRender = $("#transStatusRender").attr('value');
	var transStatus = getValue(transStatusRender,'${topupTransTrace.transStatus}');
	$("#transStatus").val(transStatus);
	
	// 交易币种
	var transCurrencyRender = $('#transCurrencyRender').attr('value');
	var transCurrency = getValue(transCurrencyRender,'${topupTransTrace.transCurrency}');
	$("#transCurrency").val(transCurrency);
	
	// 交易方向
	var transDcRender = $('#transDcRender').attr('value');
	var transDc = getValue(transDcRender,'${topupTransTrace.transDc}');
	$("#transDc").val(transDc);
	
	// 交易类型
	var transTypeRender = $('#transTypeRender').attr('value');
	var transType = getValue(transTypeRender,'${topupTransTrace.transType}');
	$("#transType").val(transType);
	
	// 处理状态
	var handleStatusRender = $("#handleStatusRender").attr('value');
	var handleStatus = getValue(handleStatusRender,'${topupTransTrace.handleStatus}');
	$("#handleStatus").val(handleStatus);
	
	// 支付通道
	var bankPmtCnlTypeRender = $('#bankPmtCnlTypeRender').attr('value');
	var bankPmtCnlType = getValue(bankPmtCnlTypeRender,'${topupTransTrace.bankPmtCnlType}');
	$("#bankPmtCnlType").val(bankPmtCnlType);
	
	// 交易终端类型
	var termialTypeRender = $('#termialTypeRender').attr('value');
	var termialType = getValue(termialTypeRender,'${topupTransTrace.termialType}');
	$("#termialType").val(termialType);
	
	// 失败原因
	var handleResultRender = $('#handleResultRender').attr('value');
	var handleResult = getValue(handleResultRender,'${topupTransTrace.handleResult}');
	$("#handleResult").val(handleResult);
	
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

<s:form id="cnlTransTraceEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/cnlTransTrace">
<s:hidden name="isModfy"/>
<s:hidden name="topupTransTrace.id" id="topupTransTraceId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('topupTransTrace.topupTransTraceEditJsp.title')}</h3>
		</div>
		<input id="transStatusRender" name="transStatusRender" type="hidden" value="${transStatusRender}" />
		<input id="handleStatusRender" name="handleStatusRender" type="hidden" value="${handleStatusRender}" />
		<input id="transCurrencyRender" name="transCurrencyRender" type="hidden" value="${transCurrencyRender}" />
		<input id="transDcRender" name="transDcRender" type="hidden" value="${transDcRender}" />
		<input id="transTypeRender" name="transTypeRender" type="hidden" value="${transTypeRender}" />
		<input id="bankPmtCnlTypeRender" name="bankPmtCnlTypeRender" type="hidden" value="${bankPmtCnlTypeRender}" />
		<input id="termialTypeRender" name="termialTypeRender" type="hidden" value="${termialTypeRender}" />
		<input id="handleResultRender" name="handleResultRender" type="hidden" value="${handleResultRender}" />
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
							<!-- ID -->
							<tr hidden="true">
								<th>${app:i18n('topupTransTrace.id') }：</th>
								<td><input name="topupTransTrace.id" id="id" class="width_c" value="${topupTransTrace.id}" maxlength="225"/></td>
							</tr>
							<!-- 渠道订单号 -->
							<tr>
								<th>${app:i18n('topupTransTrace.reqNum') }：</th>
								<td><input name="topupTransTrace.reqNum" id="reqNum" class="width_c" value="${topupTransTrace.reqNum}" maxlength="225" readonly/></td>
							</tr>
							<!-- 系统申请单流水号 -->
							<tr>
								<th>${app:i18n('topupTransTrace.reqInnerNum') }：</th>
								<td><input name="topupTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${topupTransTrace.reqInnerNum}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易币种 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transCurrency') }：</th>
								<td><input name="topupTransTrace.transCurrency" id="transCurrency" class="width_c" value="${transCurrency}" maxlength="225" readonly/></td>
							</tr>
							<!-- 充值金额 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transAmount') }：</th>
								<td><input name="topupTransTrace.transAmount" id="transAmount" class="width_c" value="${topupTransTrace.transAmount}" maxlength="225" readonly/></td>
							</tr>
							<!-- 实际到账金额 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transLatestAmount') }：</th>
								<td><input name="topupTransTrace.transLatestAmount" id="transLatestAmount" class="width_c" value="${topupTransTrace.transLatestAmount}" maxlength="225" readonly/></td>
							</tr>
							<!-- 到账时间 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankReturnTime') }：</th>
								<td><input name="topupTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${topupTransTrace.bankReturnTime}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易方向 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transDc') }：</th>
								<td><input name="topupTransTrace.transDc" id="transDc" class="width_c" value="${transDc}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易类型 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transType') }：</th>
								<td><input name="topupTransTrace.transType" id="transType" class="width_c" value="${transType}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易摘要 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transComments') }：</th>
								<td><input name="topupTransTrace.transComments" id="transComments" class="width_c" value="${topupTransTrace.transComments}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易时间 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transTime') }：</th>
								<td><input name="topupTransTrace.transTime" id="transTime" class="width_c" value="${topupTransTrace.transTime}" maxlength="225" readonly/></td>
							</tr>
							<!-- 收款银行 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitName') }：</th>
								<td><input name="topupTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${topupTransTrace.bankDebitName}" maxlength="225" readonly/></td>
							</tr>
							<!-- 收款户名 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitCustName') }：</th>
								<td><input name="topupTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${topupTransTrace.bankDebitCustName}" maxlength="225" readonly/></td>
							</tr>
							<!-- 收款卡号 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankDebitCardNum') }：</th>
								<td><input name="topupTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${topupTransTrace.bankDebitCardNum}" maxlength="225" readonly/></td>
							</tr>
							<!-- 付款银行 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankCreditName') }：</th>
								<td><input name="topupTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${topupTransTrace.bankCreditName}" maxlength="225" readonly/></td>
							</tr>
							<!-- 付款户名-->
							<tr>
								<th>${app:i18n('topupTransTrace.bankCreditCustName') }：</th>
								<td><input name="topupTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${topupTransTrace.bankCreditCustName}" maxlength="225" readonly/></td>
							</tr>
							<!-- 付款卡号 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankCreditCardNum') }：</th>
								<td><input name="topupTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${topupTransTrace.bankCreditCardNum}" maxlength="225" readonly/></td>
							</tr>
							<!-- 渠道名称 -->
							<tr>
								<th>${app:i18n('topupTransTrace.cnlSysName') }：</th>
								<td><input name="topupTransTrace.cnlSysName" id="cnlSysName" class="width_c" value="${cnlSysName}" maxlength="225" readonly="readonly"/></td>
							</tr>
							<!-- 交易状态 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transStatus') }：</th>
								<td><input name="topupTransTrace.transStatus" id="transStatus" class="width_c" value="${transStatus}" maxlength="225" readonly/></td>
							</tr>
							<!-- 银行交易流水号 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankTransNum') }：</th>
								<td><input name="topupTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${topupTransTrace.bankTransNum}" maxlength="225" readonly/></td>
							</tr>
							<!-- 支付通道 -->
							<tr>
								<th>${app:i18n('topupTransTrace.bankPmtCnlType') }：</th>
								<td><input name="topupTransTrace.bankPmtCnlType" id="bankPmtCnlType" class="width_c" value="${bankPmtCnlType}" maxlength="225" readonly/></td>
							</tr>
							<!-- 交易终端类型 -->
							<tr>
								<th>${app:i18n('topupTransTrace.termialType') }：</th>
								<td><input name="topupTransTrace.termialType" id="termialType" class="width_c" value="${termialType}" maxlength="225" readonly/></td>
							</tr>
							<!-- 银行摘要 -->
							<tr>
								<th>${app:i18n('topupTransTrace.transBankSummary') }：</th>
								<td><input name="topupTransTrace.transBankSummary" id="transBankSummary" class="width_c" value="${topupTransTrace.transBankSummary}" maxlength="225" readonly/></td>
							</tr>
							<!-- 凭证图片 -->
															<tr>
		                            <th>${app:i18n('topupTransTrace.voucherLocation') }：</th>
									<td>
										<s:if test="topupTransTrace.voucherLocation!=null">
											<a href="${topupTransTrace.voucherLocation}" target="_blank">${app:i18n('topupTransTrace.voucherLocation') }</a>
										</s:if>
									</td>
								</tr>
							<!-- 处理状态 -->			
							<tr>
								<th>${app:i18n('topupTransTrace.handleStatus') }：</th>
								<td><input name="topupTransTrace.handleStatus" id="handleStatus" class="width_c" value="${handleStatus}" maxlength="225" readonly/></td>
							</tr>
							<!-- 失败原因 -->				
							<tr>
								<th>${app:i18n('topupTransTrace.handleResult') }：</th>
								<td><input name="topupTransTrace.handleResult" id="handleResult" class="width_c" value="${handleResult}" maxlength="225" readonly/></td>
							</tr>
							<!-- 审核人 -->
							<tr>
								<th>${app:i18n('topupTransTrace.operator') }：</th>
								<td><input name="topupTransTrace.operator" id="operator" class="width_c" value="${topupTransTrace.operator}" maxlength="225" readonly/></td>
							</tr>
							<!-- 审核时间 -->
							<tr>
								<th>${app:i18n('topupTransTrace.handleTime') }：</th>
								<td><input name="topupTransTrace.handleTime" id="handleTime" class="width_c" value="${topupTransTrace.handleTime}" maxlength="225" readonly/></td>
							</tr>
							<!-- 复核人 -->
							<tr>
								<th>${app:i18n('topupTransTrace.reviewer') }：</th>
								<td><input name="topupTransTrace.reviewer" id="reviewer" class="width_c" value="${topupTransTrace.reviewer}" maxlength="225" readonly/></td>
							</tr>
							<!-- 复核时间 -->
							<tr>
								<th>${app:i18n('topupTransTrace.reviewTime') }：</th>
								<td><input name="topupTransTrace.reviewTime" id="reviewTime" class="width_c" value="${topupTransTrace.reviewTime}" maxlength="225" readonly/></td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
		</div>
	</div>
</div>
</s:form>
