<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	var bankInnerCode = $("#bankInnerCode").val();
	$("#corReservedFundAcntEditForm").validate({
		rules: {
			"corReservedFundAcnt.id": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.custName": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.certType": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.certNum": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.bankCardNum": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.currency": {required: true,stringMaxLength:50,isLegal: true},
			"corReservedFundAcnt.acntCategory": {required: true,stringMaxLength:50,isLegal: true},
		},
		invalidHandler: function(e, validator) {
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
		
	$("#save").click(function(){
		$(".warning").hide();
		var custName = $("#custName").val();
		var certNum = $("#certNum").val();
		var bankCardNum = $("#bankCardNum").val();
		var certType = $("#certType").val();
		var currency = $("#currency").val();
		var acntCategory = $("#acntCategory").val();
		if(custName == "" || custName == null){
			$(".warning span").html("开户名不能为空！");
			$(".warning").show();
		}else if(certType == "" || certType == null){
			$(".warning span").html("证件类型不能为空！");
			$(".warning").show();
		}else if(certNum == "" || certNum == null){
			$(".warning span").html("证件号码不能为空！");
			$(".warning").show();
		}else if(bankCardNum == "" || bankCardNum == null){
			$(".warning span").html("备付金账号不能为空！");
			$(".warning").show();
		}else if(currency == "" || currency == null){
			$(".warning span").html("币种不能为空！");
			$(".warning").show();
		}else if(acntCategory == "" || acntCategory == null){
			$(".warning span").html("备付金类型不能为空！");
			$(".warning").show();
		}else{
			$.boxUtil.confirm(
				'请确认是否保存信息？', 
				null, 
				function(){
					$.ajax({
						type:"POSt",
						dateType:"text",
						url:"saveCheckBankNum.action?bankInnerCode="+bankInnerCode+"&bankCardNum="+$("#bankCardNum").val(),
						success:function(msg){
							if(msg == "-1"){
								$("div.warning span").html("此备付金账号已经存在！");
								$("div.warning").show();
							}else{
								$.ajax({
									type:"POST",
									dateType:"text",
									data:$("#corReservedFundAcntEditForm").serialize(),
									url:"saveOrUpdate.action",
									success:function(msg){
										if(msg == "1"){
											$.growlUI('成功信息！', '添加成功！');
											location.href="bankNum.action?bankInnerCode="+bankInnerCode;
										}
									}
								})
							} 
						}
					}) 
				}, 
				function(){
					//return false;
				});
			return false;  
			}
	});

	$("#undo").click(function(){
		location.href="bankNum.action?bankInnerCode="+bankInnerCode;
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corReservedFundAcntEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corReservedFundAcntEditForm").submit(); 
	</c:if>
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

<s:form id="corReservedFundAcntEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/bank">
<s:hidden name="isModify"/>
<s:hidden name="corReservedFundAcnt.id" id="corReservedFundAcntId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corReservedFundAcnt.corReservedFundAcntCreatJsp.title')}</h3>
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
							<tr hidden="true">
								<th><em>*</em>${app:i18n('corReservedFundAcnt.bankAcntCode') }：</th>
								<td><input name="bankInnerCode" id="bankInnerCode" value="${bankInnerCode }" class="width_c" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corReservedFundAcnt.custName') }：</th>
								<td><input name="corReservedFundAcnt.custName" id="custName" class="width_c" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corReservedFundAcnt.certType') }：</th>
								<td>
									<select name="corReservedFundAcnt.certType" id="certType" style="width:100%;">
										<option value="">请选择</option>
										<s:iterator value="certTypeList" var="certType">
											<option value="${certType.key }">${certType.value }</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th ><em>*</em>${app:i18n('corReservedFundAcnt.certNum') }：</th>
								<td ><input name="corReservedFundAcnt.certNum" id="certNum" class="width_c" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corReservedFundAcnt.bankCardNum') }：</th>
								<td><input name="corReservedFundAcnt.bankCardNum" id="bankCardNum" class="width_c" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corReservedFundAcnt.currency') }：</th>
								<td>
									<select name="corReservedFundAcnt.currency" id="currency" style="width:100%;">
										<option value="">请选择</option>
										<s:iterator value="currencyList" var="currency">
											<option value="${currency.key }">${currency.value }</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corReservedFundAcnt.acntCategor') }：</th>
								<td>
									<select name="corReservedFundAcnt.acntCategory" id="acntCategory" style="width:100%;">
										<option value="">请选择</option>
										<s:iterator value="reservedTypeList" var="reservedType">
											<option value="${reservedType.key }">${reservedType.value }</option>
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
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
