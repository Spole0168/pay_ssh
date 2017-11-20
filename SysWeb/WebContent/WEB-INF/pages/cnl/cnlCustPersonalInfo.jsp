<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="js/My97DatePicker-4.7PR2/WdatePicker.js"></script>

<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		var judge_custpersonal = ${judge_custpersonal };
		if(judge_custpersonal == false){
			$("#pointOut").css("display","block");
		}else{
			$("#info").css("display","block");
		}
	})
</script>
<style>
	#custpersonalinfo,#pointOut{
		
	}
	#pointOut{
		width:700px;
		height:200px;
		text-align:center;
	}
	.tdleft{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:left;
	}
	.tdRight{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:right;
	}
</style>
<div id="editblockidiv" class="layout block m-b">
<center>
<div id="info" style="display:none;">
<table width="100%" border="0" cellspacing="5" cellpadding="2" id="custpersonalinfo">
	<tr width="100%">
		<td class="tdRight" width="15%">${app:i18n('custPersonal.cnlCnlCode')}:</td>
		<td class="tdleft" width="17%">${custPersonalInfo.cnlCnlCode }</td>
		
		<td class="tdRight" width="15%">${app:i18n('custPersonal.cnlSysName')}:</td>
		<td class="tdleft" width="17%">${custPersonalInfo.cnlSysName }</td>
	
		<td class="tdRight" width="17%">${app:i18n('custPersonal.cnlCustCode')}:</td>
		<td class="tdleft" width="17%">${custPersonalInfo.cnlCustCode }</td>
	</tr>
	<tr>	
		<td class="tdRight" width="17%">${app:i18n('custPersonal.name')}:</td>
		<td class="tdleft" width="17%">${custPersonalInfo.name }</td>
	
		<td class="tdRight">${app:i18n('custPersonal.custLevel')}:</td>
		<td class="tdleft">${custLevel }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.custStatus')}:</td>
		<td class="tdleft">${custStatus }</td>
	</tr>
	<tr>
		<td class="tdRight" width="17%">${app:i18n('custPersonal.cnlCustType')}:</td>
		<td class="tdleft" width="17%">个人客户</td>
	
		<td class="tdRight">${app:i18n('custPersonal.firstName')}:</td>
		<td class="tdleft">${custPersonalInfo.firstName }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.lastName')}:</td>
		<td class="tdleft">${custPersonalInfo.lastName }</td>
	</tr>
	<tr>	
		<td class="tdRight">${app:i18n('custPersonal.realNamelevel')}:</td>
		<td class="tdleft">${realname }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.employer')}:</td>
		<td class="tdleft">${custPersonalInfo.employer }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.workTel')}:</td>
		<td class="tdleft">${custPersonalInfo.workTel }</td>
	</tr>
	<tr>	
		<td class="tdRight">${app:i18n('custPersonal.jobTitle')}:</td>
		<td class="tdleft">${custPersonalInfo.jobTitle }</td>
	
		<td class="tdRight">${app:i18n('custPersonal.localName')}:</td>
		<td class="tdleft">${custPersonalInfo.localName }</td>
	
		<td class="tdRight">${app:i18n('custPersonal.gender')}:</td>
		<td class="tdleft">${custPersonalInfo.gender }</td>
	</tr>
	<tr>	
		<td class="tdRight">${app:i18n('custPersonal.birthday')}:</td>
		<td class="tdleft"><s:date name="custPersonalInfo.birthday" format="yyyy-MM-dd" /></td>
	
		<td class="tdRight">${app:i18n('custPersonal.country')}:</td>
		<td class="tdleft">${country }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.provience')}:</td>
		<td class="tdleft">${custPersonalInfo.provience }</td>
	</tr>
	<tr>	
		<td class="tdRight">${app:i18n('custPersonal.city')}:</td>
		<td class="tdleft">${custPersonalInfo.city }</td>
	
		<td class="tdRight">${app:i18n('custPersonal.highestEdu')}:</td>
		<td class="tdleft">${custPersonalInfo.highestEdu }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.bankCardNum')}:</td>
		<td class="tdleft">${custPersonalInfo.bankCardNum }</td>
	</tr>
	<tr>
		<td class="tdRight">${app:i18n('custPersonal.isMarried')}:</td>
		<td class="tdleft">${custPersonalInfo.isMarried }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.spouseLocalName')}:</td>
		<td class="tdleft">${custPersonalInfo.spouseLocalName }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.spouseCertNum')}:</td>
		<td class="tdleft">${custPersonalInfo.spouseCertNum }</td>
	</tr>
	<tr>
		<td class="tdRight">${app:i18n('custPersonal.email')}:</td>
		<td class="tdleft">${custPersonalInfo.email }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.phonenum')}:</td>
		<td class="tdleft">${custPersonalInfo.phonenum }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.industry')}:</td>
		<td class="tdleft">${custPersonalInfo.industry }</td>
	</tr>
	<tr>
		<td class="tdRight">${app:i18n('custPersonal.certType')}:</td>
		<td class="tdleft">${certType }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.certNum')}:</td>
		<td class="tdleft">${custPersonalInfo.certNum }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.certExpireDat')}:</td>
		<td class="tdleft"><s:date name="custPersonalInfo.certExpireDate" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td class="tdRight">${app:i18n('custPersonal.postcode')}:</td>
		<td class="tdleft">${custPersonalInfo.postcode }</td>
		
		<td class="tdRight">${app:i18n('custPersonal.createTime')}:</td>
		<td class="tdleft"><s:date name="custPersonalInfo.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
		
		<td class="tdRight">${app:i18n('custPersonal.creator')}:</td>
		<td class="tdleft">${custPersonalInfo.creator }</td>
	</tr>
	<tr>	
		<td class="tdRight">${app:i18n('custPersonal.addr')}:</td>
		<td class="tdleft" colspan="5">${custPersonalInfo.addr }</td>
	</tr>
</table>
</div>
<div id="pointOut" style="display:none;">
	<table width="100%" height="100%">
		<tr>
			<td align="center"><font size="5" color="#141D28">无  详  细  信  息</font></td>
		</tr>
	</table>
</div>
</center>
</div>