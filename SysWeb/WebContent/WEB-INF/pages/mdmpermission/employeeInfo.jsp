<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>

<table id="tbEmployeeInfo" width="100%" border="0" cellspacing="2"
	cellpadding="2">
	<tr>
		<td width="10%" align="right"><label for="inService">用户编号:</label></td>
		<td width="35%"><input type="text" name="inService" id="inService"
			class="width_d" size="25" maxlength="100" value="${user.userCode}" /></td>
	</tr>
</table>
