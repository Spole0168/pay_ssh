<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IBS支付核心管理系统</title>

<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/css/login/css.css'/>" />
<%@ include file="/common/metas.jsp"%>

<script language="javascript">

function pluginLoaded() {
	//alert("加载安全控件!");
}

$(document).ready(function(){
	<%----%>
	// set mac address to cookie
	setTimeout(function(){
		try {
			var jingangPlugin = document.getElementById('jingangPlugin');
			
			if (jingangPlugin == null) {
				throw "error";
			}
			
			var macAddress = jingangPlugin.macAddress;
			var cpuId = jingangPlugin.cpuId;
			
			if ( macAddress.lastIndexOf(";") == macAddress.length - 1)
				macAddress = macAddress.slice(0, macAddress.length - 1)
			var macs = macAddress.split(';');
			
			var client = new Object();
			client["macs"] = macs;
			client["cpuId"] = cpuId;
			$.cookie('mac', JSON.stringify(client), {path: '/'});
		} catch(e) { }
	}, 500);
	
    $(".login_but").hover(function(){
        $(this).addClass("login_but_active");
    },function(){
        $(this).removeClass("login_but_active");
    });
    $("#modify_but").click(function(){
        url="${pageContext.request.contextPath}/toModifyPassword.action?KeepThis=true&TB_iframe=true&height=370&width=510";
        tb_show("修改密码",url,false);
    });
    $("#loginForm").validate( {
        rules : {
            userName : {
                required : true
            },
            password : {
                required : true
            },
            varification:{
                required : true
            }
        },
        invalidHandler : function(e, validator) {
            var errors = validator.numberOfInvalids();
            if (errors) {
                var message = "请正确填写信息！";
                $("div.warning span").html(message);
                $("div.warning").show();
            } else {
                $("div.warning").hide();
            }
        },
        onkeyup : false,
        submitHandler : function() {
            $("div.warning").hide();
            document.loginForm.submit();
        },
        messages : {
            // 自定义错误描述，可以忽略
        level : "请选择紧急程度",
        userName:{
            required:"请输入用户名"
        },
        password:{
            required:"请输入密码"
        },
        varification:"请输入验证码"
    }
    });

    $("#submitbtn").click(function() {
        $("#loginForm").submit();
    });
    
});
$(document).ready(function(){
    
    $(".login_but").hover(function(){
        $(this).addClass("login_but_active");
    },function(){
        $(this).removeClass("login_but_active");
    });
    $("#modify_but").click(function(){
        url="${pageContext.request.contextPath}/toModifyPassword.action?KeepThis=true&TB_iframe=true&height=370&width=510";
        tb_show("修改密码",url,false);
    });
    
    $("#loginForm").validate({
        rules:{
            'userName':"required",
            'password':"required"
        }
      });
    var random = Math.floor(Math.random()*24);//生成随机数
    var scrStr = "${pageContext.request.contextPath}/rand.action?random="+random;
    $("#vildateImg").attr("src",scrStr);
});
</script>
</head>
<body>
<div id="header">
 <ul>
  <li>&nbsp;&nbsp;&nbsp;</li>
 </ul>
</div>

<form id="loginForm" name="loginForm" action="${pageContext.request.contextPath}/login.action" method="post">
<div id="contenter"> 
  <ul class="contenttop"></ul>
  <div class="contleft_left">
   
  </div>
  <div class="contleft_right">
    <s:actionerror cssClass="errorMessage" theme="yto" />
    <ul>
     <li><span>用户名</span><input class="loginform" id="userName" name="userName" type="text" /></li>
     <li><span>密&nbsp;&nbsp;&nbsp;码</span><input class="loginform" id="password" name="password" type="password" /></li>
     <li style="height:55px"><span>验证码</span><input class="yzmloginform" name="varification" type="text" />
      <a href="#"><img id="vildateImg" style="float:left; width:66px; margin-left:10px"  width="66" height="27" border="0" /></a>
     </li>
     <li><input class="login" type="submit" value="登录" /><span><a class="a2" href="#">忘记密码</a></span></li>
    </ul>
  </div>
  <div class="clear"></div>
  <ul class="contentbottom"></ul>
</div>
</form>

<div id="footer">
  <ul>
    <li></li>
    <li class="copyright"> </li>
  </ul>
</div>
</body>
</html>

<object name="jingangPlugin" id="jingangPlugin" type="application/x-ytosecurityplugin" width="0" height="0">
	<param name="onload" value="pluginLoaded" />
</object>