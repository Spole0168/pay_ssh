<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metas.jsp"%>

<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	var pwdStatus = $("#pwdStatus").val();
	if(typeof(pwdStatus) != 'undefined' && pwdStatus != ''){
		if(pwdStatus=='success'){
			alert("密码修改成功，请重新登录");
			this.location.href="<c:url value='/logout.action'/>";
		}else{
			alert("密码修改失败，请重新修改密码或联系管理员");
		}
	}
    jQuery.validator.addMethod("unequalTo",function(value,element,param){
    	var target = $(param).unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
            $(element).valid();
        });
        return value !== target.val();
    });

    jQuery.validator.addMethod("stringCheck", function(value, element) {
    	return this.optional(element) || /^[u0391-uFFE5w]+$/.test(value);
    	}, "只能包括中文字、英文字母、数字和下划线");
     
    var validate = $("#modifyPasswordForm").validate({
        rules:{
            'oripassword':{required:true,remote:'checkUserPwd.action'},
            'newpassword':{required:true,minlength:4,unequalTo:'#oripassword',stringCheck:'#newpassword'},
            'newpassword1':{required:true,equalTo: "#newpassword"}
        },
        submitHandler:function(form) {
        	document.modifyPasswordForm.submit();
        },
        messages:{
            'oripassword':{remote:'输入用户密码错误'},
            'newpassword':{minlength:'密码长度至少为4位',unequalTo:'新密码与原先密码相同'}
        }
});
    $("#save").click(function(){
    	$("#modifyPasswordForm").submit();
    });
    $("#reset").click(function(){
    	$("#oripassword").val('');
    	$("#newpassword").val('');
    	$("#newUserPwd1").val('');
    });
});
</script>

<div class="layout">
    <div class="block m-b">
        <div class="block_title">
            <h3>密码修改</h3>
        </div>
    <div  class="block_container">
<c:if test="hasActionError">
	<s:actionerror/>
</c:if>
<form id="modifyPasswordForm" name="modifyPasswordForm" action="modifyPasswordPost.action" method="post">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td width="15%" align="right"><label for="bizCode">原密码：</label></td>
		<td width="35%"><input type="password" name="oripassword" id="oripassword"/></td>
	</tr>
	<tr>
		<td width="15%" align="right"><label for="bizCode">新密码：</label></td>
		<td width="35%"><input type="password" name="newpassword" id="newpassword"/></td>
	</tr>
	<tr>
		<td width="15%" align="right"><label for="bizCode">新密码确认：</label></td>
		<td width="35%"><input type="password" name="newpassword1" id="newUserPwd1"/></td>
	</tr>
</table>
<br/>
<fieldset class="action">
<input type="hidden" id="pwdStatus" name="pwdStatus" value="${pwdStatus }"/>
<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">保存</span></span></a>
<a id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">重置</span></span></a>
</fieldset>
</form>
</div>
</div></div>