package com.ibs.portal.integration.sso.generate;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 令版URL标签, 使用如:
 * &lt;sso:url&gt;http://xxx:8080/xxx.jsp&lt;/sso:url&gt;
 *
 * @author luoyue
 *
 */
public class TokenUrlTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print(TokenUrlRewriter.rewrite(getBodyContent().getString(), pageContext.getSession()));
		} catch (Exception e) {
			logger.debug("TokenUrlTag error!!!!", e);
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}
