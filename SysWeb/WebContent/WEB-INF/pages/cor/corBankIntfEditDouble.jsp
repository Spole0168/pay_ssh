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
		<th><em></em>${app:i18n('bankInfoDTO.bankInnerCode') }：</th>
		<td>${bankInfoDTO.bankInnerCode}</td>
		
		<th>${app:i18n('corBankInfoDTO.bankNum') }：</th>
		<td>${bankInfoDTO.bankNum}</td>
	</tr>
	
	<tr>
		<!-- 银行层级 -->
		<th>${app:i18n('corBankInfoDTO.bankLevel') }：</th>
		<td>
			<s:iterator value="bankLevel"  var="level">
			<c:if test="${level.key==bankInfoDTO.bankLevel}">
				${level.value}
				</c:if>
			</s:iterator>
	 </td>
		<!-- 国家 -->
		<th>${app:i18n('corBankInfoDTO.country') }：</th>
		<td>
			<s:iterator value="countryList" var="country">
				<c:if test="${country.key==bankInfoDTO.country}">${country.value}</c:if>
			</s:iterator>
			</td>
	</tr>
	<tr>
	 	<!-- 银行名称  -->
		<s:iterator value="bankNameList" var="nameList">
			<c:if test="${nameList.key==bankInfoDTO.bankName}">
				<th>${app:i18n('bankInfoDTO.bankName') }：</th>
					<td>${nameList.value}</td>
			</c:if>
		</s:iterator>
		<!-- 银行地址 -->
		<th>${app:i18n('corBankInfoDTO.bankAddr') }：</th>
		<td>${bankInfoDTO.bankAddr}</td>
	</tr>

	<tr>
		
		<th>${app:i18n('corBankInfoDTO.contractEffectDate') }：</th>
		<td>${bankInfoDTO.contractEffectDate}</td>
		<th>${app:i18n('corBankInfoDTO.contractExpireDate') }：</th>
		<td>${bankInfoDTO.contractExpireDate}</td>
	</tr>
	<tr>
	
		
		<th>${app:i18n('corBankInfoDTO.techSupportName') }：</th>
		<td>${bankInfoDTO.techSupportName}</td>
		<th>${app:i18n('corBankInfoDTO.businessSupportName') }：</th>
		<td>${bankInfoDTO.businessSupportName}</td>
	</tr>
 
 	<tr>
 		<th>${app:i18n('corBankInfoDTO.techSupportPhonenum') }：</th>
		<td>${bankInfoDTO.techSupportPhonenum}</td>
		<th>${app:i18n('corBankInfoDTO.businessSupportPhonenum') }：</th>
		<td>${bankInfoDTO.businessSupportPhonenum}</td>
 	</tr>
 
	<tr>
		
		
		<th>${app:i18n('corBankInfoDTO.techSupportEmail') }：</th>
		<td>${bankInfoDTO.techSupportEmail}</td>

		<th>${app:i18n('corBankInfoDTO.businessSupportEmail') }：</th>
		<td>${bankInfoDTO.businessSupportEmail}</td>
	</tr>
 
 
	<tr>
		<th>${app:i18n('corBankInfoDTO.swiftCode') }：</th>
		<td>${bankInfoDTO.swiftCode}</td>
		
		<th><em></em>${app:i18n('corBankInfoDTO.desc') }：</th>
		<td>${bankInfoDTO.desc}</td>
	</tr>
	<tr>
		<th><em></em>${app:i18n('corBankInfoDTO.branchCode') }：</th>
		<td>${bankInfoDTO.branchCode}</td>
		
		<th><em></em>${app:i18n('corBankInfoDTO.ngsnCode') }：</th>
		<td>${bankInfoDTO.ngsnCode}</td>
	</tr>

 
	</tbody>
</table>

