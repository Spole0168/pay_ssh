<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>	

<script type="text/javascript">
	function jump(count) {
	    window.setTimeout(function() {
	        count--;
	        if(count > 0) {
	        	$('#jumpSeconds').text(count);
	            jump(count);
	        } else {
	        	$('#successForm').submit();
	        }
	    }, 1000);
	}

	$(document).ready(function() {
	    jump(<s:property value="jumpSeconds" default="10"/>);

		$('#nextAction').click(function(){
			$('#successForm').submit();
		});
	});
</script>

<form id="successForm" name="successForm" method="post" action="${nextAction}">
<div class="right_page">
		<ul>
			<li class="righticon"></li>
			 <li class="administrator">操作成功！${successMessage}<br /><br />
			 		  <em style="font-weight:lighter; display:none">系统会在0秒后，自动跳转到如下网址：</em><br />
					  <em><span id="jumpSeconds"><s:property value="jumpSeconds" default="10"/></span>秒后自动跳转,或者点击&nbsp;
					  	<a id="nextAction" class="a1" href="javascript:void(0)">此处</a>
					  </em><br/>
				</li>
			</ul>
		<div class="clear"></div>
</div>
</form>

