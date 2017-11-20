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
		<th width="15%">${app:i18n('cnlCustCompany.cnlCnlCode')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.cnlCnlCode }</td>
		
		<th width="15%">${app:i18n('cnlCustCompany.cnlCustCode')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.cnlCustCode }</td>
	</tr>
	<tr width="100%">
		<th width="15%">${app:i18n('cnlCustCompany.cnlCustType')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.cnlCustType }</td>
	
		<th width="15%">${app:i18n('cnlCustCompany.custStatus')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.custStatus }</td>
	</tr>
	<tr>	
		<th width="17%">${app:i18n('cnlCustCompany.companyName')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.companyName }</td>
		
		<th width="17%">${app:i18n('cnlCustCompany.unitProperty')}:</th>
		<td width="17%">${CnlCustCompanyAndOtherDto.unitProperty }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.certType')}:</th>
		<td>${CnlCustCompanyAndOtherDto.certType }</td>
	
		<th>${app:i18n('cnlCustCompany.certNum')}:</th>
		<td>${CnlCustCompanyAndOtherDto.certNum }</td>	
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.certExpireDate')}:</th>
		<td><s:date name="CnlCustCompanyAndOtherDto.certExpireDate" format="yyyy-MM-dd" /></td>
		
		<th>${app:i18n('cnlCustCompany.corporateName')}:</th>
		<td>${CnlCustCompanyAndOtherDto.corporateName }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlCustCompany.corporateCertType')}:</th>
		<td>${CnlCustCompanyAndOtherDto.corporateCertType }</td>
		
		<th>${app:i18n('cnlCustCompany.corporateCertNum')}:</th>
		<td>${CnlCustCompanyAndOtherDto.corporateCertNum }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlCustCompany.country')}:</th>
		<td>${CnlCustCompanyAndOtherDto.country }</td>
	
		<th>${app:i18n('cnlCustCompany.provience')}:</th>
		<td>${CnlCustCompanyAndOtherDto.provience }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.city')}:</th>
		<td>${CnlCustCompanyAndOtherDto.city }</td>
	
		<th>${app:i18n('cnlCustCompany.regTime')}:</th>
		<td><s:date name="CnlCustCompanyAndOtherDto.regTime" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.companyRegAddr')}:</th>
		<td>${CnlCustCompanyAndOtherDto.companyRegAddr}</td>
	
		<th>${app:i18n('cnlCustCompany.businessScope')}:</th>
		<td>${CnlCustCompanyAndOtherDto.businessScope}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlCustCompany.industry')}:</th>
		<td>${CnlCustCompanyAndOtherDto.industry}</td>
	
		<th>${app:i18n('cnlCustCompany.regCapital')}:</th>
		<td>${CnlCustCompanyAndOtherDto.regCapital}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.regCapitalCurrency')}:</th>
		<td>${CnlCustCompanyAndOtherDto.regCapitalCurrency}</td>
		
		<th>${app:i18n('cnlCustCompany.bankCardNum')}:</th>
		<td>${CnlCustCompanyAndOtherDto.bankCardNum}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.financeContact')}:</th>
		<td>${CnlCustCompanyAndOtherDto.financeContact}</td>
	
		<th>${app:i18n('cnlCustCompany.financeTel')}:</th>
		<td>${CnlCustCompanyAndOtherDto.financeTel}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.finnaceEmail')}:</th>
		<td>${CnlCustCompanyAndOtherDto.finnaceEmail}</td>
	
		<th>${app:i18n('cnlCustCompany.addr')}:</th>
		<td>${CnlCustCompanyAndOtherDto.addr }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlCustCompany.companyTel')}:</th>
		<td>${CnlCustCompanyAndOtherDto.companyTel}</td>
		
		<th>${app:i18n('cnlCustCompany.companyFax')}:</th>
		<td>${CnlCustCompanyAndOtherDto.companyFax}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlCustCompany.postcode')}:</th>
		<td>${CnlCustCompanyAndOtherDto.postcode }</td>
	
		<th>${app:i18n('cnlCustCompany.website')}:</th>
		<td>${CnlCustCompanyAndOtherDto.website}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlCustCompany.createTime')}:</th>
		<td>${CnlCustCompanyAndOtherDto.createTime }</td>
	
		<th>${app:i18n('cnlCustCompany.creator')}:</th>
		<td>${CnlCustCompanyAndOtherDto.creator}</td>
	</tr>
</table>
</center>
</div>