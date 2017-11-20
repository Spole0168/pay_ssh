<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	$("#cnlSysIntfIpLimitEditForm").validate({
		//debug:true,
		rules: {
			"cnlSysIntfIpLimitDto.cnlCustCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfIpLimitDto.cnlIntfCode": {required: true,stringMaxLength:32,isLegal: true},
			"cnlSysIntfIpLimitDto.ipRangeFrom": {required: true,stringMaxLength:50,isLegal: true},
			"cnlSysIntfIpLimitDto.ipRangeTo": {required: true,stringMaxLength:50,isLegal: true},
			"cnlSysIntfIpLimitDto.comments": {required: false,stringMaxLength:1000,isLegal: true},
			
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
		},
		
	});

	 $("#undo").click(function(){
		window.location = "cnlSysIntfIpLimitList.action?loadPageCache=true";
		//window.history.go(-1);
	}); 
	

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

	//  check
	$("#save").click(function(){
		 $("div.warning").hide();
		 var _cnlCustCode = $("#cnlCustCode").val().trim();
		 var _cnlIntfCode = $("#cnlIntfCode").val().trim();
		 var ipRangeFrom = $("#ipRangeFrom").val().trim();
		 var ipRangeTo = $("#ipRangeTo").val().trim();
		 var pattern = new RegExp("[0-9]{3}\.[0-9]{3}\.[0-9]{3}\.[0-9]{3}");
		 if(_cnlCustCode == null || _cnlCustCode == ""){
			 $("div.warning span").html("请输入正确的商户号");
			 $("div.warning").show();
			 return false;
		 }else if(_cnlIntfCode == null || _cnlIntfCode == ""){
			 $("div.warning span").html("请输入正确的服务号");
			 $("div.warning").show();
			return false;		 
		 }else if(pattern.test(ipRangeFrom) == false || pattern.test(ipRangeTo) == false){
			 $("div.warning span").html("请输入正确的IP地址格式!");
			 $("div.warning").show();
			 return false;
		 }else{
			 $.get("cnlSysIntfIpLimitCheck.action",{cnlCustCode:_cnlCustCode, cnlIntfCode:_cnlIntfCode},function(data){
				 var ajax = eval("("+data+")");
				 //test
				 //alert(ajax);
				 if(ajax.message == "01"){
					 $("div.warning span").html("商户号或者服务号不存在!");
					 $("div.warning").show();
					 return false; 
				//当进行修改时，不进行判断
				<c:if test="${isModify == false}">	 
				 }else if(ajax.message == "02"){
					 $("div.warning span").html("此商户号的服务号已存在IP限制!");
					 $("div.warning").show();
					 return false;
				</c:if> 
				 }else{
					 $.boxUtil.confirm(
						'请确认是否保存信息？', 
						null, 
						function(){
							doSave();
						}, 
						function(){
							return false;
						});
				 };
				 return false;
			 });
		 };
	});
});	





function doSave(){
	<c:if test="${isModify}">
		$("#cnlSysIntfIpLimitEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#cnlSysIntfIpLimitEditForm").submit(); 
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

<s:form id="cnlSysIntfIpLimitEditForm" method="post" action="cnlSysIntfIpLimitSaveOrUpdate.action?loadPageCache=true" namespace="/channel">
<s:hidden name="isModify"/>
<%-- <s:hidden name="cnlSysIntf.id" id="cnlSysIntfId"/> --%>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlSysIntfIpLimit.cnlSysIntfIpLimitEditJsp.title')}</h3>
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
								<th><em>*</em>${app:i18n('cnlSysIntfIpLimit.id') }：</th>
								<td><input name="cnlSysIntfIpLimitDto.id" id="id" class="width_c" value="${cnlSysIntfIpLimitDto.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfIpLimit.cnlCustCode') }：</th>
								<td><input <c:if test="${!empty requestScope.cnlSysIntfIpLimitDto}">readonly="readonly"</c:if> 
									name="cnlSysIntfIpLimitDto.cnlCustCode" id="cnlCustCode" class="width_c" value="${cnlSysIntfIpLimitDto.cnlCustCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfIpLimit.cnlIntfCode') }：</th>
								<td><input <c:if test="${!empty requestScope.cnlSysIntfIpLimitDto}">readonly="readonly"</c:if> 
									name="cnlSysIntfIpLimitDto.cnlIntfCode" id="cnlIntfCode" class="width_c" value="${cnlSysIntfIpLimitDto.cnlIntfCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfIpLimit.ipRangeFrom') }：</th>
								<td><input name="cnlSysIntfIpLimitDto.ipRangeFrom" id="ipRangeFrom" class="width_c" value="${cnlSysIntfIpLimitDto.ipRangeFrom}" maxlength="225"/>
									<scan style="color:red;">说明：IP输入格式：***.***.***.***</scan>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlSysIntfIpLimit.ipRangeTo') }：</th>
								<td><input name="cnlSysIntfIpLimitDto.ipRangeTo" id="ipRangeTo" class="width_c" value="${cnlSysIntfIpLimitDto.ipRangeTo}" maxlength="225"/>
									<scan style="color:red;">说明：IP输入格式：***.***.***.***</scan>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('cnlSysIntfIpLimit.comments') }：</th>
								<td>
								<input name="cnlSysIntfIpLimitDto.comments" id="comments" class="width_c" 
									value="${cnlSysIntfIpLimitDto.comments}" maxlength="225"/>
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
