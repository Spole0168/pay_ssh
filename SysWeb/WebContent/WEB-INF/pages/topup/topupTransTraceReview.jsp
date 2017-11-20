<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
$().ready(function() {
	
	$("#transStatus").val("${topupTransTrace.transStatus}");
	$("#bankPmtCnlType").val("${topupTransTrace.bankPmtCnlType}");
	$("#handleResult").val("${topupTransTrace.handleResult}");
	$("#handleStatus").val("${topupTransTrace.handleStatus}");
	
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
		var msg = "";
		//校验表单必输项
		if($("#handleStatus").val()==''){
			return "处理状态必须输入";
		} else {
			var file = $("#file").val();
			var oldfile = $("#voucherLocation").val();
			if((null==oldfile||oldfile=="")&&(null==file||file=="")){
				return alert("请选收款择凭证图片！");
			}else if(null!=file&&file!=""){
				if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)){
		        	return alert("图片类型必须是.gif,jpg,png中的一种")
		        }
			}
			if($("#handleStatus").val()=='05'){
				if($("#handleResult").val()==""){
					msg = "复核失败的场合下，失败原因必须输入";
				}
			}
		}
		return msg;
	}
	$("#back").click(function(){
		window.location = "topupTransTrace/topupTransTraceList.action?loadPageCache=true";
	});
	
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
// //复核失败，失败原因必输
// function handleStateEvent(){
// 	if($("#handleStatus").val()=='04'){//复核通过
// 		$("#transStatus").val("03");
// 		$("#transStatus").attr("disabled", "disabled");
// 		$("#bankReturnResultdiv").hide();
// 	}else if($("#handleStatus").val()=='05'){//审核失败
// 		// 交易状态 交易失败
// 		$("#transStatus").val("01");
// 		$("#transStatus").attr("disabled", "disabled");
// 		//失败原因 设为必输项
// 		$("#bankReturnResultdiv").show();
// 	}
// }
//复核失败，失败原因必输
function handleStateEvent(){
	if($("#handleStatus").val()=='04'){//复核通过
		$("#handleResult").attr("value", "");
		$("#handleResult").attr("disabled", "disabled");
		$("#handleResultdiv").hide();
	}else if($("#handleStatus").val()=='05'){//审核失败
 		// 交易状态 交易失败
		//失败原因 设为必输项
		$("#handleResult").attr("disabled", "");
		$("#handleResultdiv").show();
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
<s:hidden name="topupTransTrace.id" id="topupTransTrace.id"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('topupTransTrace.topupTransTraceReviewJsp.title')}</h3>
		</div>
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
								<%-- <td hidden="true"><input name="topupTransTrace.id" id="id" class="width_c" value="${topupTransTrace.id}" maxlength="225"/></td> --%>
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
									<td><input name="topupTransTrace.transCurrency" id="transCurrency" class="width_c" value="${topupTransTrace.transCurrency }" maxlength="225" readonly="readonly"/></td>
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
								<tr>
									<th>${app:i18n('topupTransTrace.transStatus') }：</th>
									<td>
										<select name="topupTransTrace.transStatus" id="transStatus" style="width:160px" value="${topupTransTrace.transStatus}" disabled="disabled">
												<s:iterator value="%{transStatusList}" id="transStatusItem">
											        <option value="<s:property value="#transStatusItem.key" />">
											        	<s:property value="#transStatusItem.value" />
											        </option>
												</s:iterator>
									    </select>
									</td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankPmtCnlType') }：</th>
									<td>
										<select name="topupTransTrace.bankPmtCnlType" id="bankPmtCnlType" style="width:160px" value="${topupTransTrace.bankPmtCnlType}" disabled="disabled">
												<s:iterator value="%{bankPmtCnlTypeList}" id="bankPmtCnlTypeItem">
											        <option value="<s:property value="#bankPmtCnlTypeItem.key" />">
											        	<s:property value="#bankPmtCnlTypeItem.value" />
											        </option>
												</s:iterator>
									   	 </select>
									</td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankTransNum') }：</th>
									<td><input name="topupTransTrace.bankTransNum" id="bankTransNum" class="width_c" value="${topupTransTrace.bankTransNum}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.termialType') }：</th>
									<td><input name="topupTransTrace.termialType" id="termialType" class="width_c" value="${topupTransTrace.termialType}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<!-- 银行摘要 -->
								<tr>
									<th>${app:i18n('topupTransTrace.transBankSummary') }：</th>
									<td><input name="topupTransTrace.transBankSummary" id="transBankSummary" class="width_c" value="${topupTransTrace.transBankSummary}" maxlength="225" readonly/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.operator') }：</th>
									<td><input name="topupTransTrace.operator" id="operator" class="width_c" value="${topupTransTrace.operator}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.handleTime') }：</th>
									<td><input name="topupTransTrace.handleTime" id="handleTime" class="width_c" value="${topupTransTrace.handleTime}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.transLatestAmount') }：</th>
									<td><input name="topupTransTrace.transLatestAmount" id="transLatestAmount" class="width_c" value="${topupTransTrace.transLatestAmount}" maxlength="225" readonly="readonly"/></td>
								</tr>
								<tr>
									<th>${app:i18n('topupTransTrace.bankReturnTime') }：</th>
									<td>
									<input style="width:160px" name="topupTransTrace.bankReturnTime" id="bankReturnTime" class="width_c" value="${bankReturnTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
									
									</td>
								</tr>
								<tr>
		                            <th>${app:i18n('topupTransTrace.voucherLocation') }：</th>
									<td>
										<input type="hidden" name="topupTransTrace.voucherLocation" id="voucherLocation" class="width_c" value="${topupTransTrace.voucherLocation}" />
										<s:if test="topupTransTrace.voucherLocation!=null">
											<a href="${topupTransTrace.voucherLocation}" target="_blank">${app:i18n('topupTransTrace.voucherLocation') }</a>
										</s:if>
										<s:if test="topupTransTrace.voucherLocation==null">
											<input id="file" type="file" accept="image/png,image/gif,image/JPEG" name="file" />
										</s:if>
									</td>
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
								<th><em id="handleResultdiv" style="display: none">*</em>${app:i18n('topupTransTrace.handleResult') }：</th>
								<td>
									<select name="topupTransTrace.handleResult" id="handleResult" style="width:160px" value="${topupTransTrace.handleResult}" disabled="disabled">
											<option value="" selected>请选择</option>
											<s:iterator value="%{handleResultList}" id="handleResultItem">
										        <option value="<s:property value="#handleResultItem.key" />">
										        	<s:property value="#handleResultItem.value" />
										        </option>
											</s:iterator>
								   	 </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('topupTransTrace.handleStatus') }：</th>
								<td>
<%-- 									<select name="topupTransTrace.handleStatus" id="handleStatus" style="width:160px" value="${topupTransTrace.handleStatus}" onchange="handleStateEvent()"> --%>
<!-- 											<option value="" selected>请选择</option> -->
<!-- 									        <option value="04">复核通过</option> -->
<!-- 									        <option value="05">复核失败</option> -->
<%-- 								   	 </select> --%>
									<select name="topupTransTrace.handleStatus" id="handleStatus" style="width:80px" onchange="handleStateEvent()">
										<option value="" selected>${app:i18n('topupTransTrace.review.handleStatusList.default')}</option>
										<s:iterator value="%{handleStatus2ndList}" id="handleStatus">
									        <option value="<s:property value="#handleStatus.key" />">
									        	<s:property value="#handleStatus.value" />
									        </option>
										</s:iterator>
							         </select>
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
