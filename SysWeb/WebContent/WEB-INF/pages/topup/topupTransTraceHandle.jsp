<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
$().ready(function() {
	$("#save").click(function(){
		clearWarning();
		var flag = checkForm();
		if(flag!=""){
			$("div.warning span").html(flag);
			$("div.warning").show();
			return;
		}
		$.boxUtil.confirm(
				'请确认是否保存信息？', 
				null, 
				function(){
					doSave();
				}, 
				function(){
					//return false;
				});
			return false;
		});
	function checkForm(){
		//校验表单必输项
		if($("#handleStatus").val()==''){
			return "处理状态必须输入";
		} else {
			var file = $("#file").val();
			if(null!=file&&file!=""){
				if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)){
		        	return alert("图片类型必须是.gif,jpg,png中的一种")
		        }
			}
			if($("#handleStatus").val()=='03'){
				if($("#handleResult").val()!="01"&&$("#handleResult").val()!="02"&&$("#handleResult").val()!="03"){
					return "审核失败的场合下，失败原因必须输入";
				}
				if($("#handleResult").val()=="03"&&$("#transLatestAmount").val()==""){
					return "审核失败的场合下，当失败原因为金额不一致时，实际到账金额必须输入";
				}
			}else if($("#handleStatus").val()=='02'){
				if($("#transLatestAmount").val()==""){
					return "审核通过的场合下，实际到帐金额必须输入";
				}
				if($("#bankReturnTime").val()==""){
					return "审核通过的场合下，到账时间必须输入";
				}
			}
		}
		return "";
	}
	$("#back").click(function(){
		window.location = "topupTransTrace/topupTransTraceList.action?loadPageCache=true";
	});
	
	// 交易币种
	var transCurrencyRender = $('#transCurrencyRender').attr('value');
	var transCurrency = getValue(transCurrencyRender,'${topupTransTrace.transCurrency}');
	$("#transCurrency").val(transCurrency);
	
	// 交易方向
	var transDcRender = $('#transDcRender').attr('value');
	var transDc = getValue(transDcRender,'${topupTransTrace.transDc}');
	$("#transDc").val(transDc);
	
	// 交易类型
	var transTypeRender = $('#transTypeRender').attr('value');
	var transType = getValue(transTypeRender,'${topupTransTrace.transType}');
	$("#transType").val(transType);

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
// 	var id = $("#id").val();
// 	$("#cnlTransTracePendingVerifyForm").attr("action","topupTransTraceHandleOrReview.action"+"?id="+id);
	$("#cnlTransTracePendingVerifyForm").submit();
}

function handleStatusEvent(){
	if($("#handleStatus").val()=='02'){//审核通过
		$("#handleResultDiv").hide();
		$("#bankReturnTimeDiv").show();
		$("#transLatestAmountDiv").show();
		$("#transLatestAmount").attr("readonly","");
		$("#transLatestAmount").removeClass("read_only");
		// 该场合下，失败原因不可输入，并清空已经选择的内容
		$("#handleResult").attr("disabled","disabled");
		$("#handleResult").attr("value","");
	}else if($("#handleStatus").val()=='03'){//审核失败
		//失败原因 设为必输项
		$("#handleResultDiv").show();
		$("#bankReturnTimeDiv").hide();
		// 该场合下，失败原因必须输入
		$("#handleResult").attr("disabled","");
	}
}
function handleResultEvent(){
	//填写审核失败原因为 金额不一致时，实际到账金额必须输入
	if($("#handleResult").val()=='03'){//金额不一致时
		$("#transLatestAmountDiv").show();
		$("#transLatestAmount").attr("disabled","");
	}else{
		$("#transLatestAmountDiv").hide();
		$("#transLatestAmount").attr("readonly","readonly");
		$("#transLatestAmount").addClass("read_only");
		$("#transLatestAmount").val("");
	}
}

//alert框
function showAlertDialog(alertTitle, alertContent){
	$('#alertDialog').dialog('destroy');
    $('#alertDialog').show();
    $('#alertDialog').html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+ alertContent +'</p>');
    
    $("#alertDialog").dialog({
        resizable: false,
        modal: true,
        overlay: {
            backgroundColor: '#000',
            opacity: 0.9
        },
        title:alertTitle,
        buttons: {
        	'${app:i18n("global.jsp.ok")}': function() {
            	$('#alertDialog').dialog('close');
            }
        }
    });

}
// 转换下拉框的值
function getValue(render,key) {
	var arr=render.split(/:|;/);
	for (i=0;i<arr.length;) {
		if (arr[i]==key) {
			return arr[i+1];
		}
		i=i+2;
	}
	return key;
}

function clearWarning() {
	$("div.warning span").html("");
	$("div.warning").hide();
}
</script>

<div id="alertDialog"></div>

<s:form id="cnlTransTracePendingVerifyForm" method="post" action="topupTransTraceHandleOrReview.action?loadPageCache=true"  namespace="/topupTransTrace" enctype="multipart/form-data">
<s:hidden name="isModify"/>
<s:hidden name="topupTransTrace.id" id="topupTransTrace.id" />
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('topupTransTrace.topupTransTraceHandleJsp.title')}</h3>
		</div>
		<input id="transCurrencyRender" name="transCurrencyRender" type="hidden" value="${transCurrencyRender}" />
		<input id="transDcRender" name="transDcRender" type="hidden" value="${transDcRender}" />
		<input id="transTypeRender" name="transTypeRender" type="hidden" value="${transTypeRender}" />
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
						<div class="warning" style="display:none;">
							<span></span>
						</div>
						<div id="edit_test" class="editVisible" style="display:block;">
							<table cellpadding="0" cellspacing="0" class="table_form" >
								<thead>
								</thead>
								<tfoot>
								</tfoot>
								<tbody>
<%-- 								<td hidden="true"><input name="topupTransTrace.id" id="id" class="width_c" value="${topupTransTrace.id}" maxlength="225"/></td> --%>
								<tr>
									<th>${app:i18n('topupTransTrace.reqNum') }：</th>
									<td><input name="topupTransTrace.reqNum" id="reqNum" class="width_c" value="${topupTransTrace.reqNum}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.reqInnerNum') }：</th>
									<td><input name="topupTransTrace.reqInnerNum" id="reqInnerNum" class="width_c" value="${topupTransTrace.reqInnerNum}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transCurrency') }：</th>
									<td><input name="topupTransTrace.transCurrency" id="transCurrency" class="width_c" value="${transCurrency}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transAmount') }：</th>
									<td><input name="topupTransTrace.transAmount" id="transAmount" class="width_c" value="${topupTransTrace.transAmount }" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transDc') }：</th>
									<td><input name="topupTransTrace.transDc" id="transDc" class="width_c" value="${transDc}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transType') }：</th>
									<td><input name="topupTransTrace.transType" id="transType" class="width_c" value="${transType}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transComments') }：</th>
									<td><input name="topupTransTrace.transComments" id="transComments" class="width_c" value="${topupTransTrace.transComments}" maxlength="225" readonly="readonly"/></td>
								</tr>
								
								<tr>
									<th>${app:i18n('topupTransTrace.transTime') }：</th>
									<td><input name="topupTransTrace.transTime" id="transTime" class="width_c" value="${topupTransTrace.transTime}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankDebitName') }：</th>
									<td><input name="topupTransTrace.bankDebitName" id="bankDebitName" class="width_c" value="${topupTransTrace.bankDebitName}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankDebitCustName') }：</th>
									<td><input name="topupTransTrace.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${topupTransTrace.bankDebitCustName}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankDebitCardNum') }：</th>
									<td><input name="topupTransTrace.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${topupTransTrace.bankDebitCardNum}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankCreditName') }：</th>
									<td><input name="topupTransTrace.bankCreditName" id="bankCreditName" class="width_c" value="${topupTransTrace.bankCreditName}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankCreditCustName') }：</th>
									<td><input name="topupTransTrace.bankCreditCustName" id="bankCreditCustName" class="width_c" value="${topupTransTrace.bankCreditCustName}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankCreditCardNum') }：</th>
									<td><input name="topupTransTrace.bankCreditCardNum" id="bankCreditCardNum" class="width_c" value="${topupTransTrace.bankCreditCardNum}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.cnlSysName') }：</th>
									<td><input name="topupTransTrace.cnlSysName" id="cnlSysName" class="width_c" value="${cnlSysName}" maxlength="225" readonly="readonly"/></td>
								</tr>
								</tbody>
							</table>
							<hr>
							</div>
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
							<tr>
								<th>${app:i18n('topupTransTrace.transStatus') }：</th>
								<td>
								    <input name="topupTransTrace.transStatus" id="transStatusName" class="width_c" value="${transStatusName}" maxlength="225" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankPmtCnlType') }：</th>
								<td>
									<select name="topupTransTrace.bankPmtCnlType" id="bankPmtCnlType" style="width:160px" value="${topupTransTrace.bankPmtCnlType}">
											<s:iterator value="%{bankPmtCnlTypeList}" id="refundStatusItem">
										        <option value="<s:property value="#refundStatusItem.key" />">
										        	<s:property value="#refundStatusItem.value" />
										        </option>
											</s:iterator>
								   	 </select>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.bankTransNum') }：</th>
								<td><input name="topupTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${topupTransTrace.bankTransNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.termialType') }：</th>
								<td><input name="topupTransTrace.termialType" id="termialType" class="width_c" value="${topupTransTrace.termialType}" maxlength="225"/></td>
							</tr>
							<tr><!-- 审核通过时必填,失败原因是金额不一致时必填。 -->
								<th><em id="transLatestAmountDiv" style="display: none">*</em>${app:i18n('topupTransTrace.transLatestAmount') }：</th>
								<td><input name="topupTransTrace.transLatestAmount" id="transLatestAmount" class="width_c" value="${topupTransTrace.transLatestAmount}" maxlength="225"/></td>
								
							</tr>
							<tr>
								<th><em id="bankReturnTimeDiv" style="display: none">*</em>${app:i18n('topupTransTrace.bankReturnTime') }：</th>
								<td>
									<input style="width:160px" name="topupTransTrace.bankReturnTime" id="bankReturnTime" class="Wdate" value="${bankReturnTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
								</td>
							</tr>
							<tr>
								<th><em id="handleResultDiv" style="display: none">*</em>${app:i18n('topupTransTrace.handleResult') }：</th>
								<td>
									<select name="topupTransTrace.handleResult" id="handleResult" style="width:160px" value="${topupTransTrace.handleResult}" onchange="handleResultEvent()">
										<option value="" selected>请选择</option>
										<s:iterator value="%{handleResultList}" id="refundStatusItem">
									        <option value="<s:property value="#refundStatusItem.key" />">
									        	<s:property value="#refundStatusItem.value" />
									        </option>
										</s:iterator>
								   	 </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('topupTransTrace.handleStatus') }：</th>
								<td>
<%-- 									<select name="topupTransTrace.handleStatus" id="handleStatus" style="width:160px" value="${topupTransTrace.handleStatus}" onchange="handleStatusEvent()"> --%>
<!-- 										<option value="" selected>请选择</option> -->
<!-- 								        <option value="02">审核通过</option> -->
<!-- 								        <option value="03">审核失败</option> -->
<%-- 								   	 </select> --%>
									<select name="topupTransTrace.handleStatus" id="handleStatus" style="width:80px" onchange="handleStatusEvent()">
										<option value="" selected>${app:i18n('topupTransTrace.handle.handleStatusList.default')}</option>
										<s:iterator value="%{handleStatus1stList}" id="handleStatus">
									        <option value="<s:property value="#handleStatus.key" />">
									        	<s:property value="#handleStatus.value" />
									        </option>
										</s:iterator>
							         </select>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('topupTransTrace.transBankSummary') }：</th>
								<td>
									<input  name="topupTransTrace.transBankSummary" id="transBankSummary" class="width_c" value="${topupTransTrace.transBankSummary}"/>
								</td>
							</tr>
							<tr>
	                            <th>${app:i18n('topupTransTrace.voucherLocation') }：</th>
								<td>
									<s:if test="topupTransTrace.voucherLocation!=null">
										<a href="${topupTransTrace.voucherLocation}" target="_blank">${app:i18n('topupTransTrace.voucherLocation') }</a>
										<br>
									</s:if>
									<input id="file" type="file" accept="image/png,image/gif,image/JPEG" name="file" />
								</td>
							</tr>
							</tbody>
						</table>
					</form>
					
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.sumit')}</span></span></a>
				<a id="back" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.close')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
