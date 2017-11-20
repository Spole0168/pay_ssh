<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    $("#dialog_form .legend_title a").toggle( function(){
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

    $("#dialog_form .fieldset_container").css("display", "none");

    $("#dialog_form .fieldset_container .container_show").bind("click", function(){
        $(this).next("div.warning_container").toggle();
    });

</script>
</head>
<body>
<!--  打印安装 begin -->
<div id='dialog_form'>
			<!--  获取下载begin -->
            <div class="fieldset_border fieldset_bg m-b">
                <div class="legend_title">
                  <a href="####" class="container_show">
                     <b>获取下载</b>
                  </a>
                </div>
                <div class="fieldset_container" style="display: none;font-family: Arial,Verdana,Sans-serif;font-size: 15px;margin-left:10px">点击<a href="<c:url context='/common' value='/controls/prd/jatoolsPrinter.rar'/>"><font color="red">此处</font></a>下载</div>
            </div>
            
			<!--  打印安装 begin -->
            <div class="fieldset_border fieldset_bg m-b">
                <div class="legend_title">
                  <a href="####" class="container_show">
                     <b>打印安装</b>
                  </a>
                </div>
                    <div class="fieldset_container" style="display: none;font-family: Arial,Verdana,Sans-serif;font-size: 15px;margin-left:10px">
					删除旧版本打印控件：<br/>
					 * 打开控制面板，删除jatoolsPrinter plugin<br/>
					安装新版打印控件：<br/>
					 * 关闭所有浏览器，包括IE/Firefox/Chrome等<br/>
					 * 安装jatoolsPrinter.msi<br/>
					 * 重新访问金刚系统首页<br/>
                    IE安全设置方法如下：<br/>
                     * 打开IE 浏览器选择"工具"菜单 ；<br/>
                     * 选择"internet 选项"子菜单；<br/>
                     * 选择"安全"标签；<br/>
                     * 点击"自定义级别"；<br/>
                     * 设置下载未签名的 ActiveX为启用状态。
                    </div>
            </div>
            
            <%--
            <!--  打印安装 begin -->
            <div class="fieldset_border fieldset_bg m-b">
                <div class="legend_title">
                  <a href="####" class="container_show">
                     <b>打印安装</b>
                  </a>
                </div>
                    <div class="fieldset_container" style="display: none;font-family: Arial,Verdana,Sans-serif;font-size: 15px;margin-left:10px">系统打印功能只支持 internet exploer 5.5 + 以上版本，客户的操作系统必须 windows系列。在第一次访问打印界面的时候会提示下载打印控件到本地，并且自动安装，如果浏览器安全级别过高会无法下载打印控件，建议IE浏览器安全等级调低。
                    IE安全设置方法如下：<br>
                     * 打开IE 浏览器选择"工具"菜单 ；<br>
                     * 选择"internet 选项"子菜单；<br>
                     * 选择"安全"标签；<br>
                     * 点击"自定义级别"；<br>
                     * 设置下载未签名的 ActiveX为启用状态。
                    </div>
            </div>
            <!--  打印安装 end -->
            <!--  旧版本删除方法 begin -->
            <div class="fieldset_border fieldset_bg m-b">
                <div class="legend_title">
                  <a href="####" class="container_show">
                     <b>旧版本删除方法</b>
                  </a>
                </div>
                <div class="fieldset_container" style="display: none;font-family: Arial,Verdana,Sans-serif;font-size: 15px;margin-left:10px">删除旧版本需要进行以下几个步骤：<br>
                  * 关闭所有需要打印的IE浏览器；<br>
                  * 重新打开一个新的IE浏览器，选择"工具"菜单-->"常规"；<br>
                  * 选择internet选项，点击"设置"；<br>
                  * 查看所打开的目录中，把jatoolsPrint class 删除，既可。</div>
            </div>
            <!--  旧版本删除方法 end -->
            --%>
  </div>
</body>
</html>