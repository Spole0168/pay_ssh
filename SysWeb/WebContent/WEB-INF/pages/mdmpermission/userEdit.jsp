<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	.readOnly {
		border:1px solid #ccc; line-height:18px; height:18px; padding-left:0px; padding-right:0px; color:#aca899; background:#ebebe4
	}
</style>
<script type="text/javascript">

$().ready(function() {
	
	jQuery.validator.addMethod("isPhonenum", function(value, element) { 
        var tel = /^\d{3,4}-\d{7,9}$/; //电话号码格式010-12345678 
        var phone = /^1\d{10}$/;
        return this.optional(element) || (tel.test(value)) || (phone.test(value)); 
    }, "请输入正确格式的电话号码!");
	

	if($("#isModify").val() == "true") {
// 		$("#userCode").addClass("read_only");
		$("#userName").attr("readonly",true);
		$("#userName").addClass("readOnly");
// 		$("#userPwd").addClass("read_only");
//		$("#userPwd").attr("disabled",true);
	}
	
	$("#userSaveOrUpdateForm").validate({
		rules: {
			"userName": {required: true,stringMaxLength:50,isLegal: true},
			"userCode": {stringMaxLength:50,isLegal: true},
			"userPwd": {required: true,stringMaxLength:225,isLegal: true},
			"phonenum": {isPhonenum: true},
			"email": {email: true}
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
		submitHandler : function() {
			$.ajax({  
	            type : "post",  
	            url : "userSaveOrUpdate.action",  
	            data : $("#userSaveOrUpdateForm").serialize(),  
	            cache : false,  
	            dataType : "json",  
	            success : function(data) {  
	            	var flag=data.message; 
	            	if(flag=="OK"){
	            		closeDialog();
					    $.growlUI('成功信息！', '保存数据成功！');
	                    refreshGrid();
					}else{
						$.boxUtil.error(data.message);
					}
	            },
	             error : function(data) {  
	                 alert("保存失败...");  
	             }  
	        });
		}
	});
		
	$("#save").click(function(){
		doSave();
	});

	$("#undo").click(function(){
		closeDialog();
	});

});	

function doSave(){
	$("#userSaveOrUpdateForm").submit(); 
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

<div id="editblockidiv" class="layout block m-b">
<div id="alertDialog"></div>

<form id="userSaveOrUpdateForm"  method="post" action="userSaveOrUpdate">
<s:hidden name="isModify"/>
<s:hidden name="id"/>
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
										<th><em>*</em>登录名:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="userName" disabled="disabled" id="userName" style="width:170px" value="${user.userName }"/>
												</c:when>
												 <c:otherwise>
												 	<input name="userName" id="userName" style="width:170px" value="${user.userName }" maxlength="50"/>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th>用户名:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="userCode" disabled="disabled" id="userCode" style="width:170px" value="${user.userCode }"/>
												</c:when>
												 <c:otherwise>
												 	<input name="userCode" id="userCode" style="width:170px" value="${user.userCode }" maxlength="50"/>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th><em>*</em>密码:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="userPwd" id="userPwd" style="width:170px" value="${user.userPwd }"/>
												</c:when>
												 <c:otherwise>
												 	<input name="userPwd" id="userPwd" style="width:170px" value="${user.userPwd }"  maxlength="50"/>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th>用户类型:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<select id="userType" name="userType" style="width:170px;background:#ebebe4" disabled="disabled">
														<c:forEach items="${typeList }" var="item">
															<option value="${item.key }" <c:if test="${user.userType eq item.key }">selected</c:if>>${item.value }</option>
														</c:forEach>
													</select>
												</c:when>
												 <c:otherwise>
												 	<select id="userType" name="userType" style="width:170px">
														<c:forEach items="${typeList }" var="item">
															<option value="${item.key }" <c:if test="${user.userType eq item.key }">selected</c:if>>${item.value }</option>
														</c:forEach>
													</select>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th>电话:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="phonenum" maxlength="15" id="phonenum" style="width:170px" value="${user.phonenum }" disabled="disabled"/>
												</c:when>
												 <c:otherwise>
												 	<input name="phonenum" maxlength="15" id="phonenum" style="width:170px" maxlength="50" value="${user.phonenum }"/>
												 </c:otherwise>
											</c:choose>
											
										</td>
									</tr>
									<tr>
										<th>Email:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="email" id="email" style="width:170px" value="${user.email }" disabled="disabled"/>
												</c:when>
												 <c:otherwise>
												 	<input name="email" id="email" style="width:170px" maxlength="50" value="${user.email }"/>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th>备注:</th>
										<td>
											<c:choose>
												<c:when test="${detailFlag}">
													<input name="description" id="description" style="width:170px" value="${user.description }" disabled="disabled"/>
												</c:when>
												 <c:otherwise>
												 	<input name="description" id="description" style="width:170px" value="${user.description }"  maxlength="50"/>
												 </c:otherwise>
											</c:choose>
										</td>
									</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<c:if test="${not detailFlag}">
					<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				</c:if>
				
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</form>
</div>