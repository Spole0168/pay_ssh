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
		<th>${app:i18n('cnlReqTrans.msgTime')}:</th>
		<td><s:date name="cnlReqTransDto.msgTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.recieveTime')}:</th>
		<td><s:date name="cnlReqTransDto.recieveTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.handleTime')}:</th>
		<td><s:date name="cnlReqTransDto.HandleTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTransDtl.cnlCustStatus')}:</th>
		<td>${cnlReqTransDto.custStatus }</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.createCustTime')}:</th>
		<td><s:date name="cnlReqTransDto.createCustTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	
		<th>${app:i18n('cnlReqTrans.updateCustTime')}:</th>
		<td><s:date name="cnlReqTransDto.updateCustTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.logoutTime')}:</th>
		<td><s:date name="cnlReqTransDto.logoutTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<th>${app:i18n('cnlReqTrans.cnlLocalName')}:</th>
		<td>${cnlReqTransDto.localName}</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.birthday')}:</th>
		<td><s:date name="cnlReqTransDto.birthday" format="yyyy-MM-dd" /></td>
		
		<th>${app:i18n('cnlReqTrans.gender')}:</th>
		<td>${cnlReqTransDto.gender}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.isMarried')}:</th>
		<td>${cnlReqTransDto.isMarried}</td>
	
		<th>${app:i18n('cnlReqTrans.country')}:</th>
		<td>${cnlReqTransDto.country}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.provience')}:</th>
		<td>${cnlReqTransDto.provience}</td>
		
		<th>${app:i18n('cnlReqTrans.city')}:</th>
		<td>${cnlReqTransDto.city}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.district')}:</th>
		<td>${cnlReqTransDto.district}</td>
	
		<th>${app:i18n('cnlReqTrans.addr')}:</th>
		<td>${cnlReqTransDto.addr}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.postcode')}:</th>
		<td>${cnlReqTransDto.postcode}</td>
	
		<th>${app:i18n('cnlReqTrans.highestEdu')}:</th>
		<td>${cnlReqTransDto.highestEdu }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.industry')}:</th>
		<td>${cnlReqTransDto.industry}</td>
		
		<th>${app:i18n('cnlReqTrans.jobTitle')}:</th>
		<td>${cnlReqTransDto.jobTitle}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.employer')}:</th>
		<td>${cnlReqTransDto.employer }</td>
	
		<th>${app:i18n('cnlReqTrans.phonenum')}:</th>
		<td>${cnlReqTransDto.phonenum}</td>
	</tr>
	<tr>	
		<th>${app:i18n('cnlReqTrans.workTel')}:</th>
		<td>${cnlReqTransDto.workTel}</td>
		
		<th>${app:i18n('cnlReqTrans.email')}:</th>
		<td>${cnlReqTransDto.email }</td>
	</tr>
	<tr>
		<th>${app:i18n('cnlReqTrans.cnlCnlCodeMessage')}:</th>
		<td>${cnlReqTransDto.cnlCnlCode}</td>
	
		<th>${app:i18n('cnlReqTrans.spouseLocalName')}:</th>
		<td>${cnlReqTransDto.spouseLocalName}</td>
	</tr>
	<tr>		
		<th>${app:i18n('cnlReqTrans.spousePhonenum')}:</th>
		<td>${cnlReqTransDto.spousePhonenum }</td>
	
		<th>${app:i18n('cnlReqTrans.realNameLevel')}:</th>
		<td>${cnlReqTransDto.realNameLevel}</td>
	</tr>
</table>
</center>
</div>