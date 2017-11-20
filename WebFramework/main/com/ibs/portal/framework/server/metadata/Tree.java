package com.ibs.portal.framework.server.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibs.portal.framework.util.StringUtils;

public class Tree implements Serializable, Comparable<Tree> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6388149316660793801L;

	private String id;
	private String extendId; // 解决id(Integer)不够用的情况
	private String name;
	private String extraValue; // 其他属性,放代码里需要取的值
	private Integer displayOrder;
	private List<Tree> subTree;// 下级节点集合,已取到的.有一种情况是存在下级节点,但不立即加载,需要用户点击时动态加载
	private Integer subCount = 0; // 下级节点数

	public Tree() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<Tree> getSubTree() {
		return subTree;
	}

	public void setSubTree(List<Tree> subTree) {
		this.subTree = subTree;
	}

	public boolean hasSubTree() {
		if (null != subTree && !subTree.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * 增加子节点
	 * 
	 * @param subTree
	 */
	public void addSubTree(Tree tree) {
		if (null == subTree) {
			subTree = new ArrayList<Tree>();
		}

		subTree.add(tree);
	}

	public int compareTo(Tree target) {
		if (null == target)
			return 1;

		if (null == this.getDisplayOrder() || null == target.getDisplayOrder())
			return (this.getExtendId().compareTo(target.getExtendId()));

		return this.getDisplayOrder().compareTo(target.getDisplayOrder());
	}

	// 用于生成jquery.treeview标签,根据不同的树组件重写
	public String toString(int index) {
		if (null == this)
			return null;

		StringBuilder sb = new StringBuilder(100);
		if (hasSubTree()) {
//			sb.append("<li id=\"li_").append(getExtendId()).append(
//					"\" class=\"open\" ti=\"").append(getExtendId()).append("\" tn=\"")
//					.append(name).append("\" loaded=\"true\" subCount=\"")
//					.append(subCount).append("\" extra=\"").append(extraValue)
//					.append("\" ><a href=\"#\" id=\"tree_style_loading[")
//					.append(index).append("]\"><ins>&nbsp;</ins>").append(name);
			sb.append("<li class='jstree-closed' id=\"").append(getId()).append("\" class=\"open\" ti=\"")
			.append(getExtendId()).append("\" tn=\"")
			.append(name).append("\" loaded=\"true\" subCount=\"")
			.append(subCount).append("\" extra=\"").append(extraValue)
			.append("\" >").append("<a href=\""+extraValue+"\" ").append("id=\"tree_style_loading[")
			.append(index).append("]\"><ins>&nbsp;</ins>").append(name);			
			if (subCount > 0) {
				sb.append(",&nbsp;&nbsp;(").append(subCount).append(")");
			}

			sb.append("</a>");

			buildSubTreeString(sb, subTree, id);

			sb.append("</li>");
		} else {
			buildTreeString(sb, this, index);
		}

		return sb.toString();
	}

	private void buildSubTreeString(StringBuilder sb, List<Tree> subTree,String parentId) {
		if (null != subTree && !subTree.isEmpty()) {
			sb.append("<ul>");

			for (Tree tree : subTree) {
				if (tree.hasSubTree()) {
					// 有下级节点
					sb
							.append("<li class='jstree-closed' id=\"")
							.append(tree.getId())
							.append("\" ti=\"")
							.append(tree.getExtendId())
							.append("\" tn=\"")
							.append(tree.getName())
							.append(
									"\" loaded=\"true\" class=\"open\" subCount=\"")
							.append(tree.getSubCount()).append("\" extra=\"")
							.append(tree.getExtraValue()).append(
									"\" ><a href=\""+tree.getExtraValue()+"\"><ins>&nbsp;</ins>")
							.append(tree.getName());

					if (subCount > 0) {
						sb.append(",&nbsp;&nbsp;(").append(tree.getSubCount())
								.append(")");
					}

					sb.append("</a>");

					buildSubTreeString(sb, tree.getSubTree(), tree.getId());

					sb.append("</li>");

				} else {
					buildTreeString(sb, tree, -1);
				}
			}

			sb.append("</ul>");
		}
	}

	/**
	 * 构造叶子节点
	 * 
	 * @param sb
	 * @param tree
	 * @param index
	 *            一级节点的序号
	 */
	private void buildTreeString(StringBuilder sb, Tree tree, int index) {
		sb.append("<li ").append(tree.getSubCount() == 0 ? ("class='jstree-leaf'"):("class='jstree-closed'")).append(" id=\"").append(tree.getId()).append("\" ti=\"")
				.append(tree.getExtendId()).append("\" tn=\"").append(tree.getName())
				.append("\" loaded=\"false\" subCount=\"").append(
						tree.getSubCount()).append("\" extra=\"").append(
						tree.getExtraValue()).append("\" ><a href=\""+tree.getExtraValue()+"\"");

		if (index >= 0) {
			sb.append(" id=\"tree_style_loading[").append(index).append("]\"");
		}

		sb.append(" ><ins>&nbsp;</ins>").append(tree.getName());

		if (tree.getSubCount() > 0) {
			sb.append(",&nbsp;&nbsp;(").append(tree.getSubCount()).append(
					")</a></li>");
		} else {
			sb.append("</a></li>");
		}

	}

	public Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}

	public String getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(String extraValue) {
		this.extraValue = extraValue;
	}

	public String getExtendId() {
		if (StringUtils.isEmpty(extendId)) {
			return id.toString();
		}
		return extendId;
	}

	public void setExtendId(String extendId) {
		this.extendId = extendId;
	}

}
