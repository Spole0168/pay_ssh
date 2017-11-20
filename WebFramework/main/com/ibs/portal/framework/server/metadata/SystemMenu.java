package com.ibs.portal.framework.server.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 菜单,需要根据不同的项目和菜单组件定制
 * 
 * @author luoyue
 * 
 */
public class SystemMenu implements Serializable, Comparable<SystemMenu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3620025987504103145L;

	private String id;
	private String name;
	private String title; // TODO i18n
	private String location;// url或js方法
	private Integer level; // 0-一级菜单，1-二级菜单
	private Integer displayOrder;
	private String target;
	private String parentId;// 上级菜单ID
	private List<SystemMenu> subMenus;// 下级菜单
	
	private String appId;
	private String type;
	
	public SystemMenu() {

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<SystemMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SystemMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 增加子菜单
	 * 
	 * @param subMenu
	 */
	public void addSubMenu(SystemMenu subMenu) {
		if (null == subMenus) {
			subMenus = new ArrayList<SystemMenu>();
		}

		subMenus.add(subMenu);
	}

	public int compareTo(SystemMenu target) {
		if (null == target)
			return 1;

		if (null == this.getDisplayOrder() || null == target.getDisplayOrder())
			return (this.getId().compareTo(target.getId()));

		return this.getDisplayOrder().compareTo(target.getDisplayOrder());
	}

	@Override
	public String toString() {
		return "SystemMenu [id=" + id + ", name=" + name + ", title=" + title
				+ ", parentId=" + parentId ;
	}

	public SystemMenu getSubMenu(List<SystemMenu> subMenus,String menuId) {
		SystemMenu sysmenu = null;
		if (null != subMenus && !subMenus.isEmpty()) {
			for (SystemMenu menu : subMenus) {
				if (null != menu.getSubMenus() && !menu.getSubMenus().isEmpty()) {
					sysmenu = getSubMenu(menu.getSubMenus(),menuId);
					if(sysmenu != null){
						return sysmenu;
					}
				}else{
					if( menuId.equalsIgnoreCase(menu.getId())){
						sysmenu = menu;
						break;
					}
				}
			}
		}
		return sysmenu;
	}

}
