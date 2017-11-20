<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$().ready(
			function() {

				$("#save").click(
						function() {
							var ok = true;
							$("div.warning span").html("");
							$("div.warning").hide();
							if ($("#bankReturnTime").val() == "") {
								$("div.warning span").html("到账时间为必填项");
								$("div.warning").show();
								ok = false;
							}
							if ($("#handleMsg").val() == ""
									|| $("#handleMsg").val() == null) {
								$("div.warning span").html("说明信息必填");
								$("div.warning").show();
								ok = false;
							}
							if (ok) {

								$.boxUtil.confirm('请确认是否保存信息？', null,
										function() {

											doSave();

										}, function() {
											//return false;
										});
								return false;
							}

						});

				$("#undo").click(function() {
					window.location = "cnlTransTracePendingList.action";
				});

				// 初始化所有只读字段的样式
				$("* [readonly]").addClass("read_only");
				$("input[readonly]").input().disableBackSpace();

			});

	function doSave() {
		<c:if test="${isModify}">
		alert(1111);
		$("#cnlTransTraceEditForm").submit();
		</c:if>
		<c:if test="${isModify == false}">
		$("#cnlTransTraceEditForm").submit();
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

<s:form id="cnlTransTraceEditForm" method="post"
	action="modifyAddHelp.action?loadPageCache=true" namespace="/cnl"
	enctype="multipart/form-data">
	<s:hidden name="isModify" />
	<s:hidden name="cnlTransTrace.id" id="cnlTransTraceId" />
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('cnlTransTrace.addHelp')}</h3>
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

									<!-- 银行到账时间 -->
									<tr>
										<th><em>*</em>${app:i18n('cnlTransTrace.bankReturnTime') }：</th>
										<td><input name="id" id="id"
											value="<s:property value="id"/>" hidden="true" />
											<input style="width:200px" name="bankReturnTime" id="bankReturnTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
											</td>
									</tr>
									<!-- 支付通道类型 -->
									<tr>
										<th>${app:i18n('cnlTransTrace.bankPmtCnlType') }：</th>
										<td><select style="width:200px" name="bankPmtCnlType" id="bankPmtCnlType">
												<s:iterator value="bankPmtCnlTypeList">
													<option value="<s:property value="key"/>"
														<s:if test="key==cnlTransTrace.bankPmtCnlType">selected="selected"</s:if>>
														<s:property value="value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>

									<!-- 摘要 -->
									<tr>
										<th><em>*</em>${app:i18n('cnlTransTrace.handleMsgs') }：</th>
										<td><input style="width:200px" name="handleMsg" id="handleMsg"
											class="width_c" value="${handleMsg}" maxlength="225" /></td>
									</tr>

									<!-- 银行流水号 -->
									<tr>
										<th>${app:i18n('cnlTransTrace.bankTransNum') }：</th>
										<td><input style="width:200px" name="bankTransNum" id="bankTransNum"
											class="width_c" value="${bankTransNum}" maxlength="225" /></td>
									</tr>
									<!-- 凭证号 -->
									<tr>
										<th>${app:i18n('cnlTransTrace.voucherNum') }：</th>
										<td><input style="width:200px" name="voucherNum" id="voucherNum"
											class="width_c" value="${voucherNum}" maxlength="225" /></td>
									</tr>
									<!-- 凭证上传 -->
									<tr>
										<th>${app:i18n('cnlTransTrace.voucher') }：</th>
										<td><input id="file" type="file" name="file" /></td>
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
