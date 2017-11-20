package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibs.portal.framework.server.domain.BaseEntity;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

/**
 * 菜单
 * 
 * @author luoyue
 *
 */
public class Menu extends BaseEntity {
//	public static final String RESOURCE_TYPE_URL_KEY = "url";
//	public static final String RESOURCE_TYPE_URL_NAME = "链接";
//	public static final String RESOURCE_TYPE_BUTTON_KEY = "button";
//	public static final String RESOURCE_TYPE_BUTTON_NAME = "按钮";
//	public static final String RESOURCE_TYPE_CONTROL_KEY = "control";
//	public static final String RESOURCE_TYPE_CONTROL_NAME = "控制权限";
	
	public static final String MENU_TYPE_MENU = "menu";
	public static final String MENU_TYPE_URL = "url";
	public static final String MENU_TYPE_BUTTON = "button";
	public static final String MENU_TYPE_GUI_MENU = "guimenu";
	public static final String MENU_TYPE_PDA_MENU = "pdamenu";
	public static final String MENU_TYPE_CONTROL = "control";
	public static final String MENU_TYPE_MENU_VALUE = "WEB菜单";
	public static final String MENU_TYPE_URL_VALUE = "URL链接";
	public static final String MENU_TYPE_BUTTON_VALUE = "按钮";
	public static final String MENU_TYPE_GUI_MENU_VALUE = "GUI菜单";
	public static final String MENU_TYPE_PDA_MENU_VALUE = "PDA菜单";
	public static final String MENU_TYPE_CONTROL_VALUE = "控制权限";
	
	public static final String MENU_TARGET_MAC = "MAC";
	
	public static String[] OPER_TYPE_ALL = new String[] { MENU_TYPE_URL,
			MENU_TYPE_BUTTON ,MENU_TYPE_CONTROL};

	public static final String menuTypeColumnRender = MENU_TYPE_MENU + ":"
			+ MENU_TYPE_MENU_VALUE + ";" + MENU_TYPE_URL + ":"
			+ MENU_TYPE_URL_VALUE + ";" + MENU_TYPE_BUTTON + ":"
			+ MENU_TYPE_BUTTON_VALUE + ";" + MENU_TYPE_GUI_MENU + ":"
			+ MENU_TYPE_GUI_MENU_VALUE + ";" + MENU_TYPE_PDA_MENU + ":"
			+ MENU_TYPE_PDA_MENU_VALUE + ";" + MENU_TYPE_CONTROL + ":"
			+ MENU_TYPE_CONTROL_VALUE;
	
	public static final List<OptionObjectPair> operTypePairs = new ArrayList<OptionObjectPair>();
	
	static {
		operTypePairs.add(new OptionObjectPair(MENU_TYPE_URL, MENU_TYPE_URL_VALUE));
		operTypePairs.add(new OptionObjectPair(MENU_TYPE_BUTTON, MENU_TYPE_BUTTON_VALUE));
		operTypePairs.add(new OptionObjectPair(MENU_TYPE_CONTROL, MENU_TYPE_CONTROL_VALUE));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3881847768158928055L;

	private String id;
	private String menuName;
	private String menuTitle;
	private String menuTitleEn;
	private String location;
	private String description;
	private Integer displayOrder;
	private String target; // IFrame
	private Menu parentMenu;
	private Integer menuLevel;
	private Set<Role> includedRoles = new HashSet<Role>(0);
	private Application application;
	private String menuType;
	
	private Integer subMenuCount;

	public Menu() {

	}

	public String getMenuTitleEn() {
		return menuTitleEn;
	}

	public void setMenuTitleEn(String menuTitleEn) {
		this.menuTitleEn = menuTitleEn;
	}

	public Menu(String id) {
		this.id = id;
	}

	public Set<Role> getIncludedRoles() {
		return includedRoles;
	}

	public void setIncludedRoles(Set<Role> includedRoles) {
		this.includedRoles = includedRoles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getSubMenuCount() {
		return subMenuCount;
	}

	public void setSubMenuCount(Integer subMenuCount) {
		this.subMenuCount = subMenuCount;
	}
	
}
