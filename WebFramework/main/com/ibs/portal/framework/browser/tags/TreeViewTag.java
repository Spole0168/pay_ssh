package com.ibs.portal.framework.browser.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.portal.framework.server.metadata.Tree;

public class TreeViewTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4120804672718441257L;
	protected final Log log = LogFactory.getLog(getClass());

	private String treeId;
	private String treeClass = "jstree";
	private String treeStyle = "clear:both;";
	private List<Tree> tree;

	public List<Tree> getTree() {
		return tree;
	}

	public void setTree(List<Tree> tree) {
		this.tree = tree;
	}

	@Override
	public int doStartTag() throws JspException {
		if (null != tree && !tree.isEmpty()) {
			StringBuilder sb = new StringBuilder(100);
			sb.append("<div id=\"").append(treeId).append("\" class=\"")
					.append(treeClass).append("\" style=\"").append(treeStyle)
					.append("\" ><ul>");

			for (int i=0; i< tree.size(); i++) {
				sb.append(tree.get(i).toString(i));
			}

			sb.append("</ul></div>");

			try {
				pageContext.getOut().print(sb.toString());
			} catch (IOException e) {
				log.error("build tree error! Tree[:" + sb.toString() + "]");
				e.printStackTrace();
			}
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeClass() {
		return treeClass;
	}

	public void setTreeClass(String treeClass) {
		this.treeClass = treeClass;
	}

	public String getTreeStyle() {
		return treeStyle;
	}

	public void setTreeStyle(String treeStyle) {
		this.treeStyle = treeStyle;
	}
}
