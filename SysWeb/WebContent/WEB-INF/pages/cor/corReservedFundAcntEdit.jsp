<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="js/My97DatePicker-4.7PR2/WdatePicker.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {

						$("#corReservedFundAcntEditForm").validate({
							rules : {
								"corReservedFundAcnt.id" : {
									required : true,
									stringMaxLength : 50,
									isLegal : true
								},
								"corReservedFundAcnt.bankAcntCode" : {
									required : true,
									stringMaxLength : 32,
									isLegal : true
								},
								"corReservedFundAcnt.cnlCustCode" : {
									required : true,
									stringMaxLength : 32,
									isLegal : true
								},
								"corReservedFundAcnt.country" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.custName" : {
									required : true,
									stringMaxLength : 50,
									isLegal : true
								},
								"corReservedFundAcnt.certType" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.certNum" : {
									required : true,
									stringMaxLength : 20,
									isLegal : true
								},
								"corReservedFundAcnt.bankName" : {
									required : true,
									stringMaxLength : 50,
									isLegal : true
								},
								"corReservedFundAcnt.bankCode" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.bankBranchCode" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.bankCardNum" : {
									required : true,
									stringMaxLength : 20,
									isLegal : true
								},
								"corReservedFundAcnt.bankCardType" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.status" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.currency" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.acntCategory" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.acntType" : {
									required : true,
									stringMaxLength : 6,
									isLegal : true
								},
								"corReservedFundAcnt.createTime" : {
									required : true,
									stringMaxLength : 11,
									isLegal : true
								},
								"corReservedFundAcnt.updateTime" : {
									required : true,
									stringMaxLength : 11,
									isLegal : true
								},
								"corReservedFundAcnt.isValid" : {
									required : true,
									stringMaxLength : 2,
									isLegal : true
								},
								"corReservedFundAcnt.creator" : {
									required : true,
									stringMaxLength : 40,
									isLegal : true
								},
								"corReservedFundAcnt.updator" : {
									required : true,
									stringMaxLength : 40,
									isLegal : true
								},
								"corReservedFundAcnt.lastTransTime" : {
									required : true,
									stringMaxLength : 11,
									isLegal : true
								},
								"corReservedFundAcnt.frozenAmoumt" : {
									required : true,
									stringMaxLength : 22,
									isLegal : true
								},
								"corReservedFundAcnt.avaliableAmount" : {
									required : true,
									stringMaxLength : 22,
									isLegal : true
								},
								"corReservedFundAcnt.bankNum" : {
									required : true,
									stringMaxLength : 32,
									isLegal : true
								},
							},
							invalidHandler : function(e, validator) {
								var errors = validator.numberOfInvalids();
								if (errors) {
									var message = "请正确填写表单信息！";
									$("div.warning span").html(message);
									$("div.warning").show();
								} else {
									$("div.warning").hide();
								}
							}
						});

						$("#save").click(function() {
							$.boxUtil.confirm('请确认是否保存信息？', null, function() {
								doSave();
							}, function() {
								//return false;
							});
							return false;
						});

						$("#undo")
								.click(
										function() {
											window.location = "corReservedFundAcnt/corReservedFundAcntList.action?loadPageCache=true";
										});

						// 初始化所有只读字段的样式
						$("* [readonly]").addClass("read_only");
						$("input[readonly]").input().disableBackSpace();

					});

	function doSave() {
		<c:if test="${isModify}">
		$("#corReservedFundAcntEditForm").submit();
		</c:if>
		<c:if test="${isModify == false}">
		$("#corReservedFundAcntEditForm").submit();
		</c:if>
	}
	//alert框
	function showAlertDialog(alertTitle, alertContent) {
		$('#alertDialog').dialog('destroy');
		$('#alertDialog').show();
		$('#alertDialog')
				.html(
						'<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'
								+ alertContent + '</p>');

		$("#alertDialog").dialog({
			resizable : false,
			modal : true,
			overlay : {
				backgroundColor : '#000',
				opacity : 0.9
			},
			title : alertTitle,
			buttons : {
				'${app:i18n("global.jsp.ok")}' : function() {
					$('#alertDialog').dialog('close');
				}
			}
		});

	}
</script>

<div id="alertDialog"></div>

<s:form id="corReservedFundAcntEditForm" method="post"
	action="saveOrUpdate.action?loadPageCache=true"
	namespace="/corReservedFundAcnt">
	<s:hidden name="isModify" />
	<s:hidden name="corReservedFundAcnt.id" id="corReservedFundAcntId" />
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corReservedFundAcnt.corReservedFundAcntEditJsp.title')}</h3>
			</div>
			<div class="block_container">
				<div class="fieldset_border fieldset_bg m-b">
					<div class="fieldset_container">
						<form id="validate_form">
							<div class="warning" style="display: none;">
								<span></span>
							</div>
							<table cellpadding="0" cellspacing="0" class="table_form">
								<thead>
								</thead>
								<tfoot>
								</tfoot>
								<tbody>
                                  
									<tr hidden="ture">
										<th><em>*</em>${app:i18n('corReservedFundAcnt.id') }：</th>
										<td><input name="corReservedFundAcnt.id" id="id"
											class="width_c" value="${corReservedFundAcnt.id}"
											maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankAcntCode') }：</th>
										<td><input name="corReservedFundAcnt.bankAcntCode"
											id="bankAcntCode" class="width_c"
											value="${corReservedFundAcnt.bankAcntCode}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.cnlCustCode') }：</th>
										<td><input name="corReservedFundAcnt.cnlCustCode"
											id="cnlCustCode" class="width_c"
											value="${corReservedFundAcnt.cnlCustCode}" maxlength="225" />
											</td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.country') }：</th>
										<td>
											<select name="corReservedFundAcnt.country"
											id="country" class="width_c"
											value="${corReservedFundAcnt.country}" maxlength="225">
											<option value="请选择">请选择</option>
											<option value="美国">美国</option>
											<option value="法国">法国</option>
											<option value="中国">中国</option>
											<option value="德国">德国</option>
											</select>
											</td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.custName') }：</th>
										<td><input name="corReservedFundAcnt.custName"
											id="custName" class="width_c"
											value="${corReservedFundAcnt.custName}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.certType') }：</th>
										<td><input name="corReservedFundAcnt.certType"
											id="certType" class="width_c"
											value="${corReservedFundAcnt.certType}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.certNum') }：</th>
										<td><input name="corReservedFundAcnt.certNum"
											id="certNum" class="width_c"
											value="${corReservedFundAcnt.certNum}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankName') }：</th>
										<td><input name="corReservedFundAcnt.bankName"
											id="bankName" class="width_c"
											value="${corReservedFundAcnt.bankName}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankCode') }：</th>
										<td><input name="corReservedFundAcnt.bankCode"
											id="bankCode" class="width_c"
											value="${corReservedFundAcnt.bankCode}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankBranchCode') }：</th>
										<td><input name="corReservedFundAcnt.bankBranchCode"
											id="bankBranchCode" class="width_c"
											value="${corReservedFundAcnt.bankBranchCode}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankCardNum') }：</th>
										<td><input name="corReservedFundAcnt.bankCardNum"
											id="bankCardNum" class="width_c"
											value="${corReservedFundAcnt.bankCardNum}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankCardType') }：</th>
										<td><input name="corReservedFundAcnt.bankCardType"
											id="bankCardType" class="width_c"
											value="${corReservedFundAcnt.bankCardType}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.status') }：</th>
										<td><input name="corReservedFundAcnt.status" id="status"
											class="width_c" value="${corReservedFundAcnt.status}"
											maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.currency') }：</th>
										<td><input name="corReservedFundAcnt.currency"
											id="currency" class="width_c"
											value="${corReservedFundAcnt.currency}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.acntCategory') }：</th>
										<td><input name="corReservedFundAcnt.acntCategory"
											id="acntCategory" class="width_c"
											value="${corReservedFundAcnt.acntCategory}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.acntType') }：</th>
										<td><input name="corReservedFundAcnt.acntType"
											id="acntType" class="width_c"
											value="${corReservedFundAcnt.acntType}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.createTime') }：</th>
										<td><input class="Wdate	width_time1" onclick="WdatePicker()"/>
											</td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.updateTime') }：</th>
										<td><input name="corReservedFundAcnt.updateTime"
											id="updateTime" class="Wdate	width_time1" onclick="WdatePicker()"
											value="${corReservedFundAcnt.updateTime}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.isValid') }：</th>
										<td><input name="corReservedFundAcnt.isValid"
											id="isValid" class="width_c"
											value="${corReservedFundAcnt.isValid}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.creator') }：</th>
										<td><input name="corReservedFundAcnt.creator"
											id="creator" class="width_c"
											value="${corReservedFundAcnt.creator}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.updator') }：</th>
										<td><input name="corReservedFundAcnt.updator"
											id="updator" class="width_c"
											value="${corReservedFundAcnt.updator}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.lastTransTime') }：</th>
										<td><input name="corReservedFundAcnt.lastTransTime"
											id="lastTransTime" class="Wdate	width_time1" onclick="WdatePicker()"
											value="${corReservedFundAcnt.lastTransTime}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.frozenAmoumt') }：</th>
										<td><input name="corReservedFundAcnt.frozenAmoumt"
											id="frozenAmoumt" class="width_c"
											value="${corReservedFundAcnt.frozenAmoumt}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.avaliableAmount') }：</th>
										<td><input name="corReservedFundAcnt.avaliableAmount"
											id="avaliableAmount" class="width_c"
											value="${corReservedFundAcnt.avaliableAmount}"
											maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corReservedFundAcnt.bankNum') }：</th>
										<td><input name="corReservedFundAcnt.bankNum"
											id="bankNum" class="width_c"
											value="${corReservedFundAcnt.bankNum}" maxlength="225" /></td>
									</tr>


								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div id="tabs-1">
					<a id="save" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
					<a id="undo" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</s:form>
