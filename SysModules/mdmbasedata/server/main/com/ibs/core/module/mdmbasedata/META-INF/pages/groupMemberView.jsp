<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<ul>
	<s:if test="groupMembers.size > 0">
		<s:iterator value="groupMembers" var="member" status="status">
			<li>
				<s:property value='#member.memberCode'/>
				<s:property value='#member.memberName'/>
			</li>
		</s:iterator>
	</s:if>
	<s:else>
		无
	</s:else>
</ul>

