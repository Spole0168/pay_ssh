package com.ibs.portal.framework.browser.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ibs.portal.framework.server.metadata.SystemMenu;
import com.ibs.portal.framework.util.StringUtils;

public class MenuBarTag extends BodyTagSupport{
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648626426376286574L;
	
	private List<SystemMenu> menus;
	
	private List<SystemMenu> currMenus;
	
	private String id;
	
	private String className;
	
	private String setting;
	
	private String menuId;

	public List<SystemMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SystemMenu> menus) {
		this.menus = menus;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	@Override
	public int doStartTag() throws JspException {
		if (null == menus || menus.isEmpty()) 
			return SKIP_BODY;
		
		// 获取current节点
		currMenus = new ArrayList<SystemMenu>();
		
		for (SystemMenu menu : menus) {
			List<SystemMenu> tranMenus = new ArrayList<SystemMenu>();
			tranMenus.add(menu);
			if (getCurrentMenuTree(menu, tranMenus)) {
				currMenus = tranMenus;
				break;
			}
		}
		ServletActionContext.getRequest().setAttribute("currentMenuList", currMenus);
		
		StringBuilder sb = new StringBuilder(1000);

		sb.append("<ul class='sf-menu sf-navbar'>");

		for(SystemMenu menu : menus) {
			buildSubMenuTree(sb, menu, Integer.valueOf(60));
		}

		sb.append("</ul>");

		try {
			pageContext.getOut().print(sb.toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return EVAL_BODY_INCLUDE;
	}
	
	private boolean getCurrentMenuTree(SystemMenu menu, List<SystemMenu> tranMenus) {

		tranMenus.add(menu.getLevel() - 1, menu);
		
		if(this.menuId.equals(menu.getId())) {
			for (int i = tranMenus.size() - 1; i >= menu.getLevel(); --i) {
				tranMenus.remove(i);
			}
			return true;
		}
		
		List<SystemMenu> subs = menu.getSubMenus();
		
		if (subs != null) {
			for (SystemMenu sub : subs) {
				if(getCurrentMenuTree(sub, tranMenus))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @param sb
	 * @param menu
	 * @param index 解决IE6/7层遮盖问题
	 */
	private void buildSubMenuTree(StringBuilder sb, SystemMenu menu, Integer index) {
		String href = "javascript:void(0)";
		String indicator = "";
		String clz = "";
		if (StringUtils.isNotEmpty(menu.getLocation())) {
			href = locationWithId(menu.getLocation(), menu.getId());
		}
		if (currMenus.contains(menu))
			clz = " class='current'";

		List<SystemMenu> subMenus = menu.getSubMenus();
		if (subMenus != null && subMenus.size() > 0) {
			indicator = "<span class='sf-sub-indicator'> &#187;</span>";
		}
		if (menu.getLevel() == 2)
			sb.append("<li" + clz + " style='z-index:" + index + ";'" + "><a class='sf-with-ul' href='" + href + "'>" + "<nobr>" + menu.getTitle() + "</nobr>" + indicator + "</a>");
		else
			sb.append("<li" + clz + "><a class='sf-with-ul' href='" + href + "'>" + menu.getTitle() + indicator + "</a>");
		
		if (subMenus != null && subMenus.size() > 0) {
			sb.append("<ul>");
			for(SystemMenu subMenu : subMenus) {
				buildSubMenuTree(sb, subMenu, index);
				if(subMenu.getLevel() == 2)
					index--;
			}
			sb.append("</ul>");
		}
	}
	
	private String locationWithId(String location,String id){
		StringBuffer newLocation = new StringBuffer();
		
		if(StringUtils.contains(location, '?')){
			newLocation.append(location).append("&menuId=").append(id);
		}else{
			newLocation.append(location).append("?menuId=").append(id);
		}
		
		return newLocation.toString();
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
