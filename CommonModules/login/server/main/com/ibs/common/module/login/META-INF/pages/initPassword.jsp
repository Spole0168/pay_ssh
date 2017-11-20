<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<title>${systemMenu.title}</title>
<%@ include file="/common/metas.jsp"%>

<script type="text/javascript">
$().ready(function(){
	var pwdStatus = $("#pwdStatus").val();
	if(typeof(pwdStatus) != 'undefined' && pwdStatus != ''){
		if(pwdStatus=='success'){
			alert("密码修改成功，请重新登录");
			this.location.href="<c:url value='/logout.action'/>";
		}else{
			alert("密码修改失败，请重新修改密码或联系管理员");
		}
	}
    $("#dialog-form").dialog({
        autoOpen: false,
        bgiframe:true,
        heigh: 600,
        width: 700,
        modal: true
        });
    $("#dialog-form .legend_title a").toggle( function(){
        $(this).parent().parent().children(".fieldset_container").show();
        $(this).removeClass("container_show");
        $(this).addClass("container_hide");
        return false;
    }, function(){
        $(this).parent().parent().children(".fieldset_container").hide();
        $(this).removeClass("container_hide");
        $(this).addClass("container_show");
        return false;
    });

    $("#dialog-form .fieldset_container").css("display", "none");

    $("#dialog-form .fieldset_container .container_show").bind("click", function(){
        $(this).next("div.warning_container").toggle();
    });

    jQuery.validator.addMethod("unequalTo",function(value,element,param){
    	var target = $(param).unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
            $(element).valid();
        });
        return value !== target.val();
    });
    
    var validate = $("#initPasswordForm").validate({
        rules:{
            'oripassword':{required:true,remote:'checkUserPwd.action?userId='+$('#userId').val()},
            'newpassword':{required:true,minlength:4,unequalTo:'#oripassword'},
            'newpassword1':{required:true,equalTo: "#newpassword"}
        },
        submitHandler:function(form) {
        	document.initPasswordForm.submit();
        },
        messages:{
            'oripassword':{remote:'输入用户密码错误'},
            'newpassword':{minlength:'密码长度至少为4位',unequalTo:'新密码与原先密码相同'}
        }
	});
	
    $("#save").click(function(){
    	$("#initPasswordForm").submit();
    });
    $("#reset").click(function(){
    	$("#oripassword").val('');
    	$("#newpassword").val('');
    	$("#newUserPwd1").val('');
    });
});
   
</script>

</head>

<body>
<div class="main">
	<div class="head">
		<!-- 欢迎用户 -->
		<div class="head_right">
			<a class="icon-out" href="<c:url value='/logout.action'/>">注销</a>
        </div>
	</div>
	<!-- 欢迎用户 结束-->
		<div class="menu">
			
		</div>
	<div id="container">
		<div class="layout">
    <div class="block m-b">
        <div class="block_title">
            <h3>密码修改</h3>
        </div>
    <div  class="block_container">
	<div class="warning" style="display: block;"><span>您的密码为初始密码或已过期，请首先更改您的密码</span></div>
	<form id="initPasswordForm" name="initPasswordForm" action="initPasswordPost.action" method="post">
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
	<fieldset class="action">
	<input type="hidden" id="userId" name="userId" value="${userId }"/>
	<input type="hidden" id="pwdStatus" name="pwdStatus" value="${pwdStatus }"/>
	<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">保存</span></span></a>
	<a id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">重置</span></span></a>
	</fieldset>
	</form>
</div>
</div>
</div>
	</div>
	<div class="footer">
		<p>Copyright© 2000 &mdash; 2009 All Right Reserved </p>
	</div>
</div>
</body>
</html>
