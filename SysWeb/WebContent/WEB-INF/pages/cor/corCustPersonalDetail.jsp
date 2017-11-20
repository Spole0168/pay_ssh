<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">

</script>
<div id="alertDialog"></div>

<s:form id="corCustPersonalEditForm" method="post" action="#" namespace="/corCustPersonal">
<s:hidden name="isModify"/>
<s:hidden name="corCustPersonal.id" id="corCustPersonalId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corCustPersonal.corCustPersonalEditJsp.title')}</h3>
		</div>
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
							<!-- 添加，修改用户信息 -->
							<!-- ID -->
							<tr hidden="true">
								<th><em>*</em>${app:i18n('corCustPersonal.id') }：</th>
								<td><input name="corCompound.id" id="id" class="width_c" value="${corCompound.id}" maxlength="225"/></td>
							</tr>
							<!-- 客户姓名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.localName') }：</th>
								<td><input disabled="true" name="corCompound.localName" id="localName" class="width_c" value="${corCompound.localName}" maxlength="225"/></td>
							</tr>
							<!-- 英文名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.firstName') }：</th>
								<td><input disabled="true" name="corCompound.firstName" id="firstName" class="width_c" value="${corCompound.firstName}" maxlength="225"/></td>
							</tr>
							<!-- 英文姓 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.lastName') }：</th>
								<td><input disabled="true" name="corCompound.lastName" id="lastName" class="width_c" value="${corCompound.lastName}" maxlength="225"/></td>
							</tr>
							<!-- 出生日期 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.birthday') }：</th>
								<td>
								<input disabled="true" name="corCompound.birthday" id="birthday" class="width_c" value="${corCompound.birthday}" maxlength="225"/></td>
							</tr>
							<!-- 性别-->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.gender') }：</th>
								<td>
								<input disabled="true" name="corCompound.gender" id="gender" class="width_c" value="${corCompound.gender}" maxlength="225"/></td>
							</tr>
							<!-- 是否已婚-->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.isMarried') }：</th>
								<td>
								<input disabled="true" name="corCompound.isMarried" id="isMarried" class="width_c" value="${corCompound.isMarried}" maxlength="225"/></td>
							</tr>
							<!-- 国籍-->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.country') }：</th>
								<td>
								<input disabled="true" name="corCompound.country" id="country" class="width_c" value="${corCompound.country}" maxlength="225"/></td>
							</tr>
							<!-- 省份 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.provience') }：</th>
								<td><input disabled="true" name="corCompound.provience" id="provience" class="width_c" value="${corCompound.provience}" maxlength="225"/></td>
							</tr>
							<!-- 城市 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.city') }：</th>
								<td><input disabled="true" name="corCompound.city" id="city" class="width_c" value="${corCompound.city}" maxlength="225"/></td>
							</tr>
							<!-- 区域 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.district') }：</th>
								<td><input disabled="true" name="corCompound.district" id="district" class="width_c" value="${corCompound.district}" maxlength="225"/></td>
							</tr>
							<!-- 地址 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.addr') }：</th>
								<td><input disabled="true" name="corCompound.addr" id="addr" class="width_c" value="${corCompound.addr}" maxlength="225"/></td>
							</tr>
							<!-- 邮编 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.postcode') }：</th>
								<td><input disabled="true" name="corCompound.postcode" id="postcode" class="width_c" value="${corCompound.postcode}" maxlength="225"/></td>
							</tr>
							<!-- 最高学历 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.highestEdu') }：</th>
								<td><input disabled="true" name="corCompound.highestEdu" id="highestEdu" class="width_c" value="${corCompound.highestEdu}" maxlength="225"/></td>
							</tr>
							<!-- 所属行业 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.occupation') }：</th>
								<td><input disabled="true" name="corCompound.occupation" id="occupation" class="width_c" value="${corCompound.occupation}" maxlength="225"/></td>
							</tr>
							<!-- 职务 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.jobTitle') }：</th>
								<td><input disabled="true" name="corCompound.jobTitle" id="jobTitle" class="width_c" value="${corCompound.jobTitle}" maxlength="225"/></td>
							</tr>
							<!-- 工作单位 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.employer') }：</th>
								<td><input disabled="true" name="corCompound.employer" id="employer" class="width_c" value="${corCompound.employer}" maxlength="225"/></td>
							</tr>
							<!-- 手机号码 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.phoneNum') }：</th>
								<td>
									<input disabled="true" name="corCompound.phoneNum" id="phoneNum" class="width_c" value="${corCompound.phoneNum}" maxlength="225"/>
								</td>
							</tr>
							<!-- 工作电话 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.workTel') }：</th>
								<td><input disabled="true" name="corCompound.workTel" id="workTel" class="width_c" value="${corCompound.workTel}" maxlength="225"/></td>
							</tr>
							<!-- 电子邮件 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.email') }：</th>
								<td><input disabled="true" name="corCompound.email" id="email" class="width_c" value="${corCompound.email}" maxlength="225"/></td>
							</tr>
							<!-- 渠道信息 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.dataSource') }：</th>
								<td><input disabled="true" disabled="true" name="corCompound.dataSource" id="dataSource" class="width_c" value="${corCompound.dataSource}" maxlength="225"/></td>
							</tr>
							<!-- 配偶姓名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.spouseLocalName') }：</th>
								<td><input disabled="true" name="corCompound.spouseLocalName" id="spouseLocalName" class="width_c" value="${corCompound.spouseLocalName}" maxlength="225"/></td>
							</tr>
							<!-- 配偶联系电话 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.spousePhoneNum') }：</th>
								<td><input disabled="true" name="corCompound.spousePhonenum" id="spousePhonenum" class="width_c" value="${corCompound.spousePhonenum}" maxlength="225"/></td>
							</tr>
							<!-- 客户实名级别 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.realNameLeve') }：</th>
								<td><input disabled="true"  name="corCompound.realNameLeve" id="realNameLeve" class="width_c" value="${corCompound.realNameLeve}" maxlength="225"/></td>
							</tr>
							<!-- 客户等级 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.custLevel') }：</th>
								<td><input disabled="true" name="corCompound.custLevel" id="custLevel" class="width_c" value="${corCompound.custLevel}" maxlength="225"/></td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="undo" class="easyui-linkbutton l-btn" href="corCustPersonalList.action"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
