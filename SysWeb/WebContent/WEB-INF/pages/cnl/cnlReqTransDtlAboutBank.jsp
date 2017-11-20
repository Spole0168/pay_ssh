<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="UTF-8">
	/* $(document).ready(function(){
		var judge_custpersonal = ${judge_custpersonal };
		if(judge_custpersonal == false){
			$("#custpersonalinfo tr:lt(8)").hide();
			$("#custpersonalinfo tr:eq(8)").show();
		}else{
			$("#custpersonalinfo tr:lt(8)").show();
			$("#custpersonalinfo tr:eq(8)").hide();
		} 
	}) */
</script>
<style>
	#custpersonalinfo{
		font-family:隶书;
	}
	#custpersonalinfo td{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:left;
	}
	#custpersonalinfo th{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:right;
	}
</style>
<div id="editblockidiv" class="layout block m-b">
<center>
<table width="100%" border="0" cellspacing="5" cellpadding="2" id="custpersonalinfo">
	<tr width="100%">
		<th width="15%">${app:i18n('cnlReqTrans.reqInnerNum.cyy')}:</th>
		<td width="17%">${cnlReqTransDto.reqNum }</td>
		
		<th width="17%">${app:i18n('cnlReqTrans.cnlCustCode')}:</th>
		<td width="17%">${cnlReqTransDto.cnlCustCode }</td>
	</tr>
	<tr>	
		<th width="17%">${app:i18n('cnlReqTrans.reqType')}:</th>
		<td width="17%">${cnlReqTransDto.reqType }</td>
		
		<th>${app:i18n('cnlReqTrans.reqStatus.cyy')}:</th>
		<td>${cnlReqTransDto.reqStatus }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.msgTime.cyy')}:</th>
		<td><s:date name="cnlReqTransDto.msgTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.recieveTime.cyy')}:</th>
		<td><s:date name="cnlReqTransDto.recieveTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.handleTime.cyy')}:</th>
		<td><s:date name="cnlReqTransDto.recieveTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.bankAccepteTime')}:</th>
		<td><s:date name="cnlReqTransDto.bankAccepteTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.bankTransNum')}:</th>
		<td>${cnlReqTransDto.bankTransNum }</td>
	
		<th>${app:i18n('cnlReqTrans.isinGl')}:</th>
		<td>${cnlReqTransDto.isinGl }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.bankReturnTime')}:</th>
		<td><s:date name="cnlReqTransDto.bankReturnTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.transType')}:</th>
		<td>${cnlReqTransDto.transType}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.transDc')}:</th>
		<td>${cnlReqTransDto.transDc}</td>
	
		<th>${app:i18n('cnlReqTrans.transCurrency')}:</th>
		<td>${cnlReqTransDto.transCurrency}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.transAmount')}:</th>
		<td>${cnlReqTransDto.transAmount}</td>
	
		<th>${app:i18n('cnlReqTrans.transStatus')}:</th>
		<td>${cnlReqTransDto.transStatus}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.transDate')}:</th>
		<td><s:date name="cnlReqTransDto.transDate" format="yyyy-MM-dd" /></td>
		
		<th>${app:i18n('cnlReqTrans.TransTime')}:</th>
		<td><s:date name="cnlReqTransDto.TransTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.transComments')}:</th>
		<td>${cnlReqTransDto.transComments}</td>
		
		<th>${app:i18n('cnlReqTrans.bankReturnResult')}:</th>
		<td>${cnlReqTransDto.bankReturnResult}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.bankPmtCnlType')}:</th>
		<td>${cnlReqTransDto.bankPmtCnlType }</td>
	
		<th>${app:i18n('cnlReqTrans.bankPmtCnlCode')}:</th>
		<td>${cnlReqTransDto.bankPmtCnlCode}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.bankCreditName')}:</th>
		<td>${cnlReqTransDto.bankCreditName}</td>
		
		<th>${app:i18n('cnlReqTrans.bankCreditCustName')}:</th>
		<td>${cnlReqTransDto.bankCreditCustName }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.bankCreditAcntCode')}:</th>
		<td>${cnlReqTransDto.bankCreditAcntCode}</td>
		
		<th>${app:i18n('cnlReqTrans.bankDebitName')}:</th>
		<td>${cnlReqTransDto.bankDebitName}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.bankDebitCustName')}:</th>
		<td>${cnlReqTransDto.bankDebitCustName }</td>
	
		<th>${app:i18n('cnlReqTrans.bankDebitAcntCode')}:</th>
		<td>${cnlReqTransDto.bankDebitAcntCode}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.reqBankReqTransDate')}:</th>
		<td><s:date name="cnlReqTransDto.bankReqTransDate" format="yyyy-MM-dd" /></td>
		
		<th>${app:i18n('cnlReqTrans.bankServiceFeeAct')}:</th>
		<td>${cnlReqTransDto.bankServiceFeeAct }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.bankHandLePriority')}:</th>
		<td>${cnlReqTransDto.bankHandLePriority}</td>
		
	</tr>
</table>
</center>
</div>