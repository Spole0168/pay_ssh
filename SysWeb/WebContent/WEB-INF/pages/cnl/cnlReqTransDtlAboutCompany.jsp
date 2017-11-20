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
		
		<th>${app:i18n('cnlReqTrans.reqStatus')}:</th>
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
		
		<th>${app:i18n('cnlReqTransDtl.custStatus')}:</th>
		<td>${cnlReqTransDto.custStatus }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.regTime')}:</th>
		<td><s:date name="cnlReqTransDto.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	
		<th>${app:i18n('cnlReqTrans.updateTime')}:</th>
		<td><s:date name="cnlReqTransDto.updateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.logoutTime')}:</th>
		<td><s:date name="cnlReqTransDto.logoutTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.companyName')}:</th>
		<td>${cnlReqTransDto.companyName}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.unitProperty')}:</th>
		<td>${cnlReqTransDto.unitProperty}</td>
		
		<th>${app:i18n('cnlReqTrans.certType')}:</th>
		<td>${cnlReqTransDto.certType}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.certNum')}:</th>
		<td>${cnlReqTransDto.certNum}</td>
	
		<th>${app:i18n('cnlReqTrans.certExpireDate')}:</th>
		<td><s:date name="cnlReqTransDto.certExpireDate" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.corporateName')}:</th>
		<td>${cnlReqTransDto.corporateName}</td>
	
		<th>${app:i18n('cnlReqTrans.corporateCertType')}:</th>
		<td>${cnlReqTransDto.corporateCertType}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.corporateCertNum')}:</th>
		<td>${cnlReqTransDto.corporateCertNum}</td>
	
		<th>${app:i18n('cnlReqTrans.country')}:</th>
		<td>${cnlReqTransDto.country}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.provience')}:</th>
		<td>${cnlReqTransDto.provience}</td>
	
		<th>${app:i18n('cnlReqTrans.city')}:</th>
		<td>${cnlReqTransDto.city }</td>
		
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.district')}:</th>
		<td>${cnlReqTransDto.district}</td>
	
		<th>${app:i18n('cnlReqTrans.companyRegDate')}:</th>
		<td><s:date name="cnlReqTransDto.companyRegTime" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>		
		<th>${app:i18n('cnlReqTrans.companyRegAddr')}:</th>
		<td>${cnlReqTransDto.companyRegAddr}</td>
	
		<th>${app:i18n('cnlReqTrans.businessScope')}:</th>
		<td>${cnlReqTransDto.businessScope}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.industry2')}:</th>
		<td>${cnlReqTransDto.industry}</td>
		
		<th>${app:i18n('cnlReqTrans.regCapital')}:</th>
		<td>${cnlReqTransDto.regCapital }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.regCapitalCurrency')}:</th>
		<td>${cnlReqTransDto.regCapitalCurrency}</td>
		
		<th>${app:i18n('cnlReqTrans.financeContact')}:</th>
		<td>${cnlReqTransDto.financeContact}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.financeTel')}:</th>
		<td>${cnlReqTransDto.financeTel }</td>
		
		<th>${app:i18n('cnlReqTrans.finnaceEmail')}:</th>
		<td>${cnlReqTransDto.finnaceEmail}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.addr')}:</th>
		<td>${cnlReqTransDto.addr}</td>
		
		<th>${app:i18n('cnlReqTrans.companyTel')}:</th>
		<td>${cnlReqTransDto.companyTel}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.companyFax')}:</th>
		<td>${cnlReqTransDto.companyFax}</td>
		
		<th>${app:i18n('cnlReqTrans.postcode')}:</th>
		<td>${cnlReqTransDto.postcode }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.website')}:</th>
		<td>${cnlReqTransDto.website}</td>
		
		<th>${app:i18n('cnlReqTrans.cnlCnlCodeMessage')}:</th>
		<td>${cnlReqTransDto.cnlCnlCode }</td>
	</tr>
</table>
</center>
</div>