<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

 
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
 
 
 
 
	<table cellpadding="5" cellspacing="5"  class="layout block m-b"  width="100%">
	 
		<tbody  id="custpersonalinfo">
		<tr>
		
			<th width="25%">${app:i18n('cnlCustCode')}：</th>
			<td>${cnlComment.cnlCustCompany.cnlCustCode}</td>
			
			<th width="25%">${app:i18n('cnlComment.cnlCnlCode') }：</th>
			<td>${cnlComment.cnlCnlCfg.cnlCnlCode}</td>
		</tr>
		<tr>
			<th width="25%">${app:i18n('cnlComment.cnlSysName') }：</th>
			<td>${cnlComment.cnlCnlCfg.cnlSysName}</td>
		 
		
			<th width="25%">${app:i18n('cnlComment.cnlDesc') }：</th>
			<td>${cnlComment.cnlCnlCfg.cnlDesc}</td>
			
		</tr>
		
		 
		<tr>
		
			<th width="25%">${app:i18n('cnlComment.addr') }：</th>
			<td>${cnlComment.cnlCustCompany.addr}</td>
			
			
			<th width="25%">${app:i18n('cnlComment.country') }：</th>
			<td>
				<s:iterator  value="countryList" var="country">
					<c:if test="${country.key==cnlComment.cnlCustCompany.country}">
						${country.value}
					</c:if>
				</s:iterator>
			</td>
		</tr>
		 
			
		<tr>
		 <th width="25%">${app:i18n('cnlComment.contractEffectDate') }：</th>
			<td>
			<s:date format="yyyy-MM-dd HH:mm:ss" name="cnlComment.cnlCnlCfg.contractEffectDate"></s:date> 
			</td>  
			
			<th width="25%">${app:i18n('cnlComment.contractExpireDate') }：</th>
			<td>
			<s:date format="yyyy-MM-dd HH:mm:ss" name="cnlComment.cnlCnlCfg.contractExpireDate"></s:date> 
			</td>
		 
		</tr>
		<tr>
		
			<th width="25%">${app:i18n('cnlComment.accessKey') }：</th>
			<td>${cnlComment.cnlSysIntf.accessKey}</td>
		
			<th width="25%">${app:i18n('cnlComment.accessType') }：</th>
			<td>
				<s:iterator  value="typeList" var="type">
					<c:if test="${type.key==cnlComment.cnlSysIntf.accessType}">
						${type.value}
					</c:if>
				</s:iterator>
			</td>
		</tr>

			<tr>
			<th width="25%">${app:i18n('cnlComment.sendSms') }：</th>
			<td>
			
			${cnlComment.cnlSysIntf.sendSms}
		 
			
			</td>
			
			
			<th width="25%">${app:i18n('cnlComment.sendEmail') }：</th>
			<td>
			${cnlComment.cnlSysIntf.sendEmail}
			
			</td>
		
		</tr>
			<tr>
			<th width="25%">${app:i18n('cnlComment.techSupportName') }：</th>
			<td>${cnlComment.cnlSysIntf.techSupportName}</td>
			
			<th width="25%">${app:i18n('cnlComment.businessSupportName') }：</th>
			<td>${cnlComment.cnlSysIntf.businessSupportName}</td>
		
		</tr>
		
		 
			<tr>
			
			
			<th width="25%">${app:i18n('cnlComment.techSupportPhonenum') }：</th>
			<td>${cnlComment.cnlSysIntf.techSupportPhonenum}</td>
			
			<th width="25%">${app:i18n('cnlComment.businessSupportPhonenum') }：</th>
			<td>${cnlComment.cnlSysIntf.businessSupportPhonenum}</td>
		
		</tr>
		
		 <tr>
			<th width="25%">${app:i18n('cnlComment.techSupportEmail') }：</th>
			<td>${cnlComment.cnlSysIntf.techSupportEmail}</td>
			
			<th width="25%">${app:i18n('cnlComment.businessSupportEmail') }：</th>
			<td>${cnlComment.cnlSysIntf.businessSupportEmail}</td>
			 
		</tr>
		<tr>
			<th width="25%">${app:i18n('cnlComment.comments') }：</th>
			<td width="25%">
			${cnlComment.cnlSysIntf.comments}
			</td>
		</tr>
		
		</tbody>
	</table>
