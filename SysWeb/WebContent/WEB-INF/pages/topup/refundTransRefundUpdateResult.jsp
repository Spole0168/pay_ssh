<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<style type="text/css">
	img{height: auto; width: auto\9; width:100%;} 
</style>
<script type="text/javascript">
var verifyUrl = "updateResultAndUpload.action";
var noVerifyUrl ="verifyStatusFail.action";

$().ready(function() {
	
	$("#refundStatus").val("${cnlTransRefund.refundStatus }");
	$("#verify").click(function(){
		
		var refundStatus = $("#refundStatus").val();
		var refundFailReason = $("#refundFailReason").val();
		var file = $("#file").val();
		if(null==refundStatus||""==refundStatus){
			return alert("请选择退款状态！");
		}
		if(refundStatus==07){
			if(null==refundFailReason||""==refundFailReason){
				return alert("请输入退款失败原因！");
			}
		}else{
			if(null!=refundFailReason&&""!=refundFailReason){
				return alert("非退款失败状态不需要输入退款失败原因！");
			}
		}
// 		var file = $("#file").val();
// 		var voucherNum1 = $("#refundVoucherNum").val();
// 		if(		(null!=file&&file!=""&&voucherNum1=="")||
// 				(null==file||file=="")&&(voucherNum1!="")){
// 			return alert("凭证文件文件和凭证号必须同时输入或者同时不输入！");
// 		}else if((null!=file&&file!="") && $.trim(voucherNum1)==""){
// 			return alert("凭证号不能为空");
// 		}
		if(null==file||""==file){
			return alert("请选择退款凭证图片！");
		}else if(null!=file&&""!=file && !/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)){
        	return alert("图片类型必须是.gif,jpg,png中的一种")
		}
		
		$.boxUtil.confirm(
			'请确认是否更新结果？', 
			null, 
			function(){
				doVerify();
			}, 
			function(){
				//return false;
			});
		return false;
	});

	$("#cancel").click(function(){
		window.location="refundTransList.action";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doVerify(){
	var id = $("#id").val();
	$("#cnlTransTracePendingVerifyForm").attr("action",verifyUrl+"?id="+id);
	$("#cnlTransTracePendingVerifyForm").submit();
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

</script>

<div id="alertDialog"></div>

<%-- <s:form id="cnlTransTracePendingVerifyForm" method="post" action=""  namespace="/refundTransRefund"> --%>
<s:form id="cnlTransTracePendingVerifyForm" method="post" action="updateResultAndUpload.action" namespace="/refundTransRefund"
	enctype="multipart/form-data">
<s:hidden name="isModify"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.title')}</h3>
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
                              <td hidden="true"><input name="cnlTransRefund.id" id="id" class="width_c" value="${cnlTransRefund.id}" maxlength="225"/></td>
							
                              <!--退款单号  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.id') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.refundCode" id="refundCode" class="width_c" value="${cnlTransRefund.refundCode}" maxlength="225"/></td></td>
							</tr>
							<!--申请人  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqPerson') }：</th>
								<td><input disabled="true" name="cnlTransRefund.creator" id="creator" class="width_c" value="${cnlTransRefund.creator}" maxlength="225"/></td>
							     <td hidden="true"><input name="cnlTransRefund.creator" id="creator" class="width_c" value="${cnlTransRefund.creator}" maxlength="225"/></td>
							</tr>
							
							<!--  申请时间  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqTime') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundTime" id="refundTime" class="width_c" value="${cnlTransRefund.refundTime}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundTime" id="refundTime" class="width_c" value="${cnlTransRefund.refundTime}" maxlength="225"/></td>
							</tr>
							
							<!-- 请求退款联系人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqContacts') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundContact" id="refundContact" class="width_c" value="${cnlTransRefund.refundContact}" maxlength="225"/></td>
							</tr>
							
							<!-- 请求退款联系人电话  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reqContactsPhone') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundContactTel" id="refundContactTel" class="width_c" value="${cnlTransRefund.refundContactTel}" maxlength="225"/></td>
							</tr>
							<!-- 退款原因 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.refundReason" id="refundReason" class="width_c" value="${cnlTransRefund.refundReason}" maxlength="225"/></td>
							</tr>
							<!-- 收款方名称 -->
<!-- 							<tr> -->
<%-- 								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.bankName') }：</th> --%>
<%-- 								<td><input disabled="true" name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td> --%>
<%-- 								<td hidden="true"><input name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td> --%>
<!-- 							</tr> -->
							<!-- 收款方开户名  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.custName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
								<td hidden="true"><input name="cnlTransRefund.bankDebitCustName" id="bankDebitCustName" class="width_c" value="${cnlTransRefund.bankDebitCustName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行名称  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.bankBranchName') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							    <td hidden="true"><input name="cnlTransRefund.bankDebitName" id="bankDebitName" class="width_c" value="${cnlTransRefund.bankDebitName}" maxlength="225"/></td>
							</tr>
							<!-- 收款方银行卡号  -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.debitCardNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankDebitCardNum" id="bankDebitCardNum" class="width_c" value="${cnlTransRefund.bankDebitCardNum}" maxlength="225"/></td>
							</tr>
							<!-- 银行交易流水号 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.bankTransNum') }：</th>
								<td><input disabled="true" name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.bankTransNum" id="bankTransNum" class="width_c" value="${cnlTransRefund.bankTransNum}" maxlength="225"/></td>
							</tr>
							<!-- 凭证号 -->
							
							
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.voucherNum') }：</th>
								<td ><input disabled="true" name="cnlTransRefund.voucherNum" id="voucherNum" class="width_c" value="${cnlTransRefund.voucherNum}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.voucherLocation') }：</th>
								<td>
									<s:if test="cnlTransRefund.voucherLocation!=null">
										<a href="${cnlTransRefund.voucherLocation}" target="_blank">${app:i18n('cnlTransRefund.voucherLocation') }</a>
									</s:if>
								</td>
							</tr>
							
							<!-- 退款金额 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.transAmount') }：</th>
								<td><input disabled="true" name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							<td hidden="true"><input name="cnlTransRefund.refundAmount" id="refundAmount" class="width_c" value="${cnlTransRefund.refundAmount}" maxlength="225"/></td>
							</tr>
							

							<!-- 审核人 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reviewer') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewer" id="reviewer" class="width_c" value="${cnlTransRefund.reviewer}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransRefund.reviewer" id="reviewer" class="width_c" value="${cnlTransRefund.reviewer}" maxlength="225"/></td>
							</tr>
							<!-- 审核时间 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.reviewTime') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewTime" id="reviewTime" class="width_c" value="${cnlTransRefund.reviewTime}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransRefund.reviewTime" id="reviewTime" class="width_c" value="${cnlTransRefund.reviewTime}" maxlength="225"/></td>
							</tr>
							<!-- 失败原因 -->
							<tr>
								<th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.failureReason') }：</th>
								<td><input disabled="true" name="cnlTransRefund.reviewMsg" id="reviewMsg" class="width_c" value="${cnlTransRefund.reviewMsg}" maxlength="225"/></td>
							<td hidden="true"><input disabled="true" name="cnlTransRefund.reviewMsg" id="reviewMsg" class="width_c" value="${cnlTransRefund.reviewMsg}" maxlength="225"/></td>
							</tr>
							<!-- 退款状态  -->
							<tr>
                            <th><em>*</em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundStatus') }：</th>
<!-- 							<td><input  name="reviewMsg" id="reviewMsg" class="width_c"  maxlength="225"/></td> -->
<%-- 							<td><select id="refundStatus" name="refundStatus" class="width_c" value="${cnlTransRefund.refundStatus }"> --%>
<!-- 									<option value="">--请选择--</option> -->
<!-- 									<option value="01">待审核</option> -->
<!-- 									<option value="02">审核成功</option> -->
<!-- 									<option value="03">审核失败</option> -->
<!-- 									<option value="04">未退款</option> -->
<!-- 									<option value="05">退款中</option> -->
<!-- 									<option value="06">退款成功</option> -->
<!-- 									<option value="07">退款失败</option> -->
<%-- 								</select> --%>
<!-- 							</td> -->
							<td>
								<select name="refundStatus" id="refundStatus" style="width:190px" >
									<option value="" selected>${app:i18n('cnlTransRefund.refundSelected')}</option>
									<s:iterator value="%{refundStatusList}" id="refundStatusItem">
								        <option value="<s:property value="#refundStatusItem.key" />">
								        	<s:property value="#refundStatusItem.value" />
								        </option>
									</s:iterator>
						         </select>
							</td>
							</tr>
							
							<!-- 退款失败原因  -->
							<tr>
                            <th><em></em>${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.refundfFaillureReason') }：</th>
							<td><input  name="refundFailReason" id="refundFailReason" class="width_c"  maxlength="225" value="${cnlTransRefund.refundFailReason}" /></td>
							</tr>
							<!-- 退款凭证  -->
							<tr>
                           	 	<th><em></em>${app:i18n('cnlTransRefund.refundVoucherNum') }：</th>
								<td><input name="cnlTransRefund.refundVoucherNum" id="refundVoucherNum" class="width_c" value="${cnlTransRefund.refundVoucherNum}"/></td>
							</tr>
							<tr>
                           	 	<th><em>*</em>${app:i18n('cnlTransRefund.refundVoucherLocation') }：</th>
								<td><input id="file" type="file" accept="image/png,image/gif,image/JPEG" name="file" /></td>
							</tr>
							
							<!-- 凭证上传 -->
<!-- 							<tr> -->
<%-- 								<th>${app:i18n('cnlTransRefund.voucher') }：</th> --%>
<!-- 								<td><input id="file" type="file" accept="image/png,image/gif,image/JPEG" name="file" /></td> -->
<!-- 							</tr> -->
									
						</table>
					</form>
					
				</div>
			</div>
			<div id="tabs-1">
				<a id="verify" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.updateResult')}</span></span></a>
				<a id="cancel" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('cnlTransRefund.cnlRefundTransUpdateResultJsp.cancel')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
