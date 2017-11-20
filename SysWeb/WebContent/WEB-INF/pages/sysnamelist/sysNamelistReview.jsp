<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	
	$("#sysNamelistEditForm").validate({
		rules: {
// 			"sysNamelist.id": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.nlType": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.nlId": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.nlDesc": {required: true,stringMaxLength:200,isLegal: true},
// 			"sysNamelist.accessType": {required: true,stringMaxLength:50,isLegal: true},
			"sysNamelist.status": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.reviewer": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.reviewTime": {required: true,stringMaxLength:20,isLegal: true},
// 			"sysNamelist.createTime": {required: true,stringMaxLength:11,isLegal: true},
// 			"sysNamelist.updateTime": {required: true,stringMaxLength:11,isLegal: true},
// 			"sysNamelist.creator": {required: true,stringMaxLength:50,isLegal: true},
// 			"sysNamelist.updator": {required: true,stringMaxLength:50,isLegal: true},
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
		var status=$("#sysNamelistStatus").val();
	 
		 
		if(!status){
			$("div.warning span").html("请选择审批状态!");
			$("div.warning").show();
		 
		}else{
			$("div.warning").hide();
			
			$.boxUtil.confirm(
				'请确认是否保存信息？', 
				null, 
				function(){
					doReview();
					//doSave(); 
				}, 
				function(){
					//return false;
				});
			return false;
	
	}
	});
	$("#undo").click(function(){
		closeDialog();
		refreshGrid();
		});
	
	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();
	
	
	
	//类型
	var  type=$("#sysNamelistNlType").val();
	if(type){
 
 		if(type=="00"){
 			
 			$("#nlt").val("全部");
 			
 		}else  if(type=="01"){
 			
 			$("#nlt").val("电子邮件");
 			
 		}else if(type=="02"){
 		 
 			$("#nlt").val("手机号码");
 		 
 		}else if(type=="03"){
 			
 			$("#nlt").val("银行卡号");
 			
 		}else if(type="04"){
 			
 			$("#nlt").val("高风险国家");
 		}
		
		
	}
	
	
	//大文本框
	var nldesc=$("#sysNameNlDesc").val();
	if(nldesc){
		$("#nlDesc").html(nldesc);
	}
	

});	

function doReview(){
	
	  $.ajax({
           type: "post",
           url: "sysNamelist/sysNamelistDoReviewByAjax.action",
           data: $('#sysNamelistEditForm').serialize(),
           dataType: "json",
           async:false,
           success: function(data){
          	 
          	 var msg=data.message;
          	$.growlUI('审批信息！', msg);
          	closeDialog();
          	console.info(msg+"========"+data);
//          	 window.location = "sysNamelist/sysNamelistList.action?loadPageCache=true&message="+msg;
//          	 $.growlUI('审批信息！', msg);
          	refreshGrid();
				
           }
         
       });
	 
	  
}



function doSave(){
	<c:if test="${isModify}">
		$("#sysNamelistEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#sysNamelistEditForm").submit(); 
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

<s:form id="sysNamelistEditForm" method="post" action="doReview.action?loadPageCache=true" namespace="/sysNamelist">
<s:hidden name="isModify"/>
<s:hidden name="sysNamelist.id" id="sysNamelistId"/>
<div class="layout">
	<div class="block m-b">
	 
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
 
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.nlType') }：</th>
								<td>
								<input  type="hidden"  value="${sysNamelist.nlType}"  id="sysNamelistNlType"> 
								
								<input id="nlt" class="width_c"   readonly="readonly"  /> 
								
								</td>
							</tr>
							 	 
							 
							 <tr>
								<th><em>*</em>${app:i18n('sysNamelist.nlId') }：</th>
								<td><input name="sysNamelist.nlId" id="nlId" class="width_c" value="${sysNamelist.nlId}" maxlength="225"  readonly="readonly"/></td>
							</tr>	
							
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.reviewer') }：</th>
								<td><input name="sysNamelist.reviewer" id="reviewer" class="width_c" value="${sysNamelist.reviewer}" maxlength="225"  readonly="readonly"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.reviewTime') }：</th>
								<td><input name="sysNamelist.reviewTime" id="reviewTime" class="width_c" value="${sysNamelist.rtime}" maxlength="225" readonly="readonly"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.status') }：</th>
								<td>
								 
									<select name="sysNamelist.status" id="sysNamelistStatus" class="width_c"  >
									 
										<option value="">请选择</option>
										<s:iterator  value="statusList" var="status">
										<c:if test="${status.key!='04'&&status.key!='01'}">
											<option value="${status.key}">${status.value}</option>
											</c:if>
										</s:iterator>
								</select>
							</td>
							</tr>
							<tr>
								<th>${app:i18n('sysNamelist.nlDesc') }：</th>
								<td>
								<input type="hidden" id="sysNameNlDesc" class="width_c" value="${sysNamelist.nlDesc}" maxlength="225"/>
								
								<textarea rows="" cols="" name="sysNamelist.nlDesc" id="nlDesc" class="width_c"></textarea>
								
								
								</td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.review')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#" ><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
