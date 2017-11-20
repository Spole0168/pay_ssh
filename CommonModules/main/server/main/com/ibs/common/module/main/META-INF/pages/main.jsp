<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" charset="UTF-8">

	function pluginLoaded() {
		//alert("加载安全控件!");
	}

	<%-- $(function() {
		
		// set mac address to cookie
		setTimeout(function(){
			try {
				var jingangPlugin = document.getElementById('jingangPlugin');
				
				if (jingangPlugin == null) {
					throw "error";
				}
				
				var macAddress = jingangPlugin.macAddress;
				var cpuId = jingangPlugin.cpuId;
				//var macAddress = "02-50-F2-00-00-05;00-1E-65-C6-25-48";
				//var macAddress = "0";
				//var cpuId = "_cpuid";
				
				if ( macAddress.lastIndexOf(";") == macAddress.length - 1)
					macAddress = macAddress.slice(0, macAddress.length - 1)
				var macs = macAddress.split(';');
				
				var client = new Object();
				client["macs"] = macs;
				client["cpuId"] = cpuId;
				$.cookie('mac', JSON.stringify(client), {path: '/'});
				//alert(macAddress);
			} catch(e) {
				$.boxUtil.info('您尚未安装金刚系统安全控件，点击 <a href="/common/controls/IBSSecurityPlugin_V1.0.msi"><font color="red">此处</font></a>下载！');
			}
		}, 500); --%>
		
		
		// message inform
		$.growl.settings.displayTimeout = 0;
		$.growl.settings.dockCss.width = '300px';
		$.growl.settings.noticeCss = {opacity: 1};
		$.growl.settings.noticeTemplate = '' + 
			'<div class="growl_body">' +
			'	<div class="growl_title">' +
			'	<div class="growl_title_content">' +
			'		<h3 class="%priority%">%title%</h3>' +
			'		<a class="close" title="关闭" href="###" onclick="return false;" rel="close">X</a>' +
			'	</div>' +
			'	</div>' +
			'	<div class="growl_container">' +
			'		<div class="growl_container_content">%message%</div>' +
			'	</div>' +
			'</div>';

		var growfunc = $.growl;

		<s:iterator value="messageInforms" var="messageInform" status="status">
			$.ajax({
				type: "POST",
				url: "<s:property value='#messageInform.informUrl' escape='false'/>",
				dataType: "json",
				cache: false,
				success: function(msgs) {
					if (msgs == null || msgs.length == 0)
						return;
					
					var result = "<ul>";
					$.each(msgs, function(i, n){
						result += "<li>" + n + "</li>";
					});
					result += "</ul>";
					
					growfunc("<s:property value='#messageInform.informName' escape='false'/>", result, "", "<s:property value='#messageInform.informLevel'/>");
				}
			});
		</s:iterator>

		// mainpage inform
		<s:iterator value="mainInforms" var="mainInform" status="status">
			$("#mainPage").children("div:eq(<s:property value='#status.index'/>)").load("<s:property value='#mainInform.informUrl' />", {rand: Math.random()});
		</s:iterator>
	});
	
</script>

<div class="layout" id="mainPage">
	<s:iterator value="mainInforms" var="mainInform" status="status">
		<div class='block m-b'>
			<div class="loading_background loading_span" style="text-align:	center;">
				<span class="loading_span">正在加载<s:property value='#mainInform.informName' />，请稍候...</span> <br/>
				<img src="/common/images/framework/ajax-loader.gif"	/>
			</div>
		</div>
	</s:iterator>
</div>
<%----%>

<object name="jingangPlugin" id="jingangPlugin" type="application/x-ytosecurityplugin" width="0" height="0">
	<param name="onload" value="pluginLoaded" />
</object>

