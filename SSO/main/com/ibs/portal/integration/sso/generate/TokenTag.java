package com.ibs.portal.integration.sso.generate;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.integration.sso.ConfigFactory;

public class TokenTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			logger.debug("token:" + ConfigFactory.TOKEN_PARAMETER_KEY + "=" + TokenUrlRewriter.generate(pageContext.getSession()));
			pageContext.getOut().print(ConfigFactory.TOKEN_PARAMETER_KEY + "=" + TokenUrlRewriter.generate(pageContext.getSession()));
		} catch (Exception e) {
			logger.debug("generate token error:", e);
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}
