package com.ibs.portal.framework.browser.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.security.UserSecurity;

/**
 * 页面权根控制标签<br/> 使用如:<br/> &lt;%@ taglib prefix="app" uri="/app-tags"%&gt;<br/>
 * &lt;app:permission resource="xxx"&gt;&lt;input
 * name="edit"/>&lt;/app:permission&gt;<br/> 当前用户有key所指资源的访问权限时输出标签体,
 * 否则越过输出标签体.<br/>
 * 
 * @author luoyue
 * 
 */

public class PermissionTag extends BodyTagSupport {
	private static final long serialVersionUID = -516656449548427206L;

	private String resource;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public int doStartTag() throws JspException {
		
		if (hasPermission()) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected boolean hasPermission() {
		if (null == resource)
			return true;
		return UserSecurity.hasPrivilege(UserContext.getUserContext()
				.getCurrentUser(), resource, UserSecurity.FORM_SECURITY_TYPE);

	}
}
