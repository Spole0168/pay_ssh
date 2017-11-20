package com.ibs.common.module.frameworkimpl.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ibs.common.module.frameworkimpl.security.service.IMenuService;
import com.ibs.portal.framework.server.context.BeanHolder;

/**
 * menu set tag
 * @author 
 * $Id: MenuSetTag.java 24768 2011-01-08 07:33:18Z  $
 */
public class MenuSetTag extends TagSupport {

	private static final long serialVersionUID = 5939137143641908877L;

	protected String var;
	protected String menuName;

	public int doStartTag() throws JspException {

		IMenuService menuService = (IMenuService) BeanHolder.getBean("menuService");
		pageContext.setAttribute(var, menuService.getMenuLocation(menuName));
		
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
