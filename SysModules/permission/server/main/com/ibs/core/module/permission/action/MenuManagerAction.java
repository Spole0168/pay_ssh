package com.ibs.core.module.permission.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ibs.common.module.frameworkimpl.common.Constants;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.domain.MacUser;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.core.module.mdmbasedata.dto.JsonVO;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.metadata.JsonContainer;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.Tree;
import com.ibs.portal.framework.util.StringUtils;

import edu.emory.mathcs.backport.java.util.Arrays;

public class MenuManagerAction extends CrudBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5068672022826349921L;

	private IPermissionBiz permissionBiz;

	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

	private String id;
	private String menuName;
	private String menuTitle;
	private String menuTitleEn;
	private String location;
	private String description;
	private Integer displayOrder;
	private String target;
	private String parentMenuId;
	private String parentMenuName;
	private String parentMenuTitle;
	private String appId;
	private String appName;
	private Menu menu;
	private String parm;
	private String menuTypeValue;
	
	private String menuType;

	private List<Tree> menuTree;
	
	private List<OptionObjectPair> menuTypeList;
	
	private String nodeId;
	private JsonContainer treedata;
	
	private String search_string;
	
	private List<OptionObjectPair> appList = new ArrayList<OptionObjectPair>(0);	
	private List<OptionObjectPair> targetList = new ArrayList<OptionObjectPair>(0);
	
	private List<Mac> macIdList = new ArrayList<Mac>();
	private String macIdJson = "";
	private String macId = "";
	
	private Map<String, Object> jsonResult = new HashMap<String, Object>();
	
	public List<OptionObjectPair> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<OptionObjectPair> targetList) {
		this.targetList = targetList;
	}

	public List<OptionObjectPair> getAppList() {
		return appList;
	}

	public void setAppList(List<OptionObjectPair> appList) {
		this.appList = appList;
	}

	public String menu() {
		return SUCCESS;
	}
	
	/**
	 * 异步验证菜单名称是否存在--此处只是验证button 类型的菜单名称
	 * @return
	 */
	public String validValue() {
		logger.trace("enter" + this.getClass().getSimpleName()+ "-->method: validValue() ");
		try {
			JsonVO jsonvo = new JsonVO();
			int result = permissionBiz.checkValid(id,menuName,menuType);
			jsonvo.setCode(result + "");
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSONObject.fromObject(jsonvo).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return AJAX_RETURN_TYPE; 
	}

	public String getNode() {
		logger.trace("entering " + this.getClass().getSimpleName()
				+ " method getNode(), and node id " + nodeId);
		
		if(null != parm && !"".equals(parm)){
			nodeId = nodeId.replaceFirst(parm, "");
		}
		menuTree = permissionBiz.expendMenuTree(nodeId);
		StringBuffer sBuffer = new StringBuffer();
		if (null != menuTree && menuTree.size() > 0) {
			for (int i = 0; i < menuTree.size(); i++) {
				Tree tree = menuTree.get(i);
				if(null != parm && !"".equals(parm)){
					String tmp = tree.getId();
					tree.setId(parm+tmp);
				}
				sBuffer.append(tree.toString(i));
			}
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				this.response.setContentType("text/html;charset=UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}
	
	public String getManageNode() {
		logger.trace("entering " + this.getClass().getSimpleName()
				+ " method getNode(), and node id " + nodeId);
		
		if(null != parm && !"".equals(parm)){
			nodeId = nodeId.replaceFirst(parm, "");
		}
		menuTree = permissionBiz.expendManagedMenuTree(nodeId);
		StringBuffer sBuffer = new StringBuffer();
		if (null != menuTree && menuTree.size() > 0) {
			for (int i = 0; i < menuTree.size(); i++) {
				Tree tree = menuTree.get(i);
				if(null != parm && !"".equals(parm)){
					String tmp = tree.getId();
					tree.setId(parm+tmp);
				}
				sBuffer.append(tree.toString(i));
			}
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				this.response.setContentType("text/html;charset=UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}
	public String toSaveOrUpdate() {
		if (null != id) {
			menu = permissionBiz.getMenuCascadeById(id);

			if (null != menu.getParentMenu()) {
				parentMenuId = menu.getParentMenu().getId();
				parentMenuName = menu.getParentMenu().getMenuName();
			}

		} else if (null != parentMenuId && !"".equals(parentMenuId)) {
			Menu parentMenu = permissionBiz.getMenuById(parentMenuId);
			parentMenuName = parentMenu.getMenuName();
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		try{
		if(StringUtils.isEmpty(menuType)){
			menuType = menuTypeValue;
		}
		id = permissionBiz.saveOrUpdateMenu(id, menuName, menuTitle,
				menuTitleEn, location, description, displayOrder, target,
				parentMenuId, appId, menuType);
		message = "保存成功";
		}catch(Exception e){
			addActionError(e.getMessage());
			return ERROR;
		}
		
		// 保存完毕后，需要找到分配了该菜单的全部用户，并刷新缓存
		try{
			List<String> userIds = permissionBiz.getUserIdListByMenuId(id);
			CacheManager.getInstance().refresh(Constants.USER_CACHE, userIds);
			if (Menu.MENU_TYPE_BUTTON.equals(menuType)) {	// 刷新button cache
				List<String> buttonKeys = new ArrayList<String>();
				buttonKeys.add(menuName);
				CacheManager.getInstance().refresh(Constants.BUTTON_RESOURCE_CACHE, buttonKeys);
			} else if (Menu.MENU_TYPE_MENU.equals(menuType)) {	// 刷新menu cache
				List<String> urlKeys = new ArrayList<String>();
				urlKeys.add(location);
				CacheManager.getInstance().refresh(Constants.URL_RESOURCE_CACHE, urlKeys);
			}
		}catch(Exception e){
			logger.error("修改菜单后，刷新用户缓存失败，原因：" + e.getMessage());
		}
		//return modify();
 	   return AJAX_RETURN_TYPE;
	}

	public String delete() {
		List<String> userIds = null;
		try {
			if (null != id) {
				userIds = permissionBiz.getUserIdListByMenuId(id);
				permissionBiz.deleteMenu(id);
			}
			message = "删除菜单成功";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		
		// 删除完毕后，需要找到分配了该菜单的全部用户，并刷新缓存
		try{
			CacheManager.getInstance().refresh(Constants.USER_CACHE, userIds);
		}catch(Exception e){
			logger.error("删除菜单后，刷新用户缓存失败，原因：");
			e.printStackTrace();
		}
		
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 跳转到mac与菜单绑定页面
	 * 1、通过menuId获取macId的list
	 * @return
	 */
	public String toBindingMenuToMac(){
		logger.trace("entering action...");
		
		try {
			macIdList = permissionBiz.getMacIdsByMenuId(menuId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("查询失败");
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * Mac列表查询
	 * @return
	 */
	public String searchMac(){
		logger.trace("entering action...");
		
		try {
			Page<MacDto> result = (Page<MacDto>) permissionBiz.findMacByPage(queryPage, getSearchFields());
			setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("查询失败");
			return ERROR;
		}
		
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 跳转到mac绑定的用户列表页面
	 * @return
	 */
	public String toMacUsers(){
		logger.trace("entering action...");
		System.out.println("MacID:" + macId);	
		
		return SUCCESS;
	}
	
	/**
	 * MAC地址已绑定的用户列表
	 * @return
	 */
	public String searchMacUsers(){
		logger.trace("entering action...");
		
		try {
			System.out.println("MacID:" + macId);	
			Page<MacUser> result = (Page<MacUser>) permissionBiz.findMacUsersByPage(queryPage, getSearchFields());
			setResult(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("查询失败");
			return ERROR;
		}
		
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存菜单和mac的绑定
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String saveMenuBindingMac(){
		logger.trace("entering action...");
		
		try {
			JSONArray jsonArray = JSONArray.fromObject(macIdJson);
			List<String> list = Arrays.asList((String[]) JSONArray.toArray(jsonArray, String.class));
			Set<String> macSet = new HashSet<String>();
			for(String s: list){
				macSet.add(s);
			}
	
			permissionBiz.saveMenuBindingMac(menuId, macSet);
			
			// refresh cache
			List<String> menuIds = new ArrayList<String>();
			menuIds.add(menuId);
			CacheManager.getInstance().refresh(Constants.MAC_CACHE, menuIds);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("保存失败");
			return ERROR;
		}
		
		return AJAX_RETURN_TYPE;
	}

	public String getId() {
		return id;
	}

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public List<Tree> getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(List<Tree> menuTree) {
		this.menuTree = menuTree;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMenuTitleEn() {
		return menuTitleEn;
	}

	public void setMenuTitleEn(String menuTitleEn) {
		this.menuTitleEn = menuTitleEn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getParentMenuTitle() {
		return parentMenuTitle;
	}

	public void setParentMenuTitle(String parentMenuTitle) {
		this.parentMenuTitle = parentMenuTitle;
	}

	public JsonContainer getTreedata() {
		return treedata;
	}

	public void setTreedata(JsonContainer treedata) {
		this.treedata = treedata;
	}

	public String getSearch_string() {
		return search_string;
	}

	public void setSearch_string(String searchString) {
		search_string = searchString;
	}

	@Override
	public String create() {
		if (null != parentMenuId && !"".equals(parentMenuId.trim())) {
			Menu parentMenu = permissionBiz.getMenuCascadeById(parentMenuId);
			if (null != parentMenu) {
				parentMenuName = parentMenu.getMenuName();
				parentMenuTitle = parentMenu.getMenuTitle();
				if (null != parentMenu.getApplication()) {
					appId = parentMenu.getApplication().getId();
					appName = parentMenu.getApplication().getAppName();
				}
			}
		}
		
		initPairs(menuType);
		return SUCCESS;
	}

	@Override
	public String export() {
		return null;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String modify() {
		logger.trace("enter "+this.getClass().getSimpleName()+" method modify");
		if (null != id) {
			logger.trace("info: id = "+ id);
			menu = permissionBiz.getMenuCascadeById(id);
			if (null != menu.getParentMenu()) {
				parentMenuId = menu.getParentMenu().getId();
				parentMenuName = menu.getParentMenu().getMenuName();
				parentMenuTitle = menu.getParentMenu().getMenuTitle();
				
			}
			if( null != menu.getApplication()){
				appId = menu.getApplication().getId();
				appName = menu.getApplication().getAppName();
			}

		} else if (null != parentMenuId && !"".equals(parentMenuId)) {
			Menu parentMenu = permissionBiz.getMenuById(parentMenuId);
			parentMenuName = parentMenu.getMenuName();
			parentMenuTitle = parentMenu.getMenuTitle();
			
		}
		initPairs(null);
		return SUCCESS;
	}
	
	public String searchRoleByMenu(){

		Page<RoleDto> result = (Page<RoleDto>)permissionBiz.getRoleByMenu(id, 
				queryPage.getPageSize(), queryPage.getPageIndex(), queryPage.getSortMap());
		setResult(result);
		return AJAX_RETURN_TYPE;
	}
	
	public String view() {
		if (null != id) {
			menu = permissionBiz.getMenuCascadeById(id);
			if (null != menu.getParentMenu()) {
				parentMenuId = menu.getParentMenu().getId();
				parentMenuName = menu.getParentMenu().getMenuName();
				
			}
			if( null != menu.getApplication()){
				appId = menu.getApplication().getId();
				appName = menu.getApplication().getAppName();
			}

		} else if (null != parentMenuId && !"".equals(parentMenuId)) {
			Menu parentMenu = permissionBiz.getMenuById(parentMenuId);
			parentMenuName = parentMenu.getMenuName();
		}

		return SUCCESS;
	}

	@Override
	public String search() {
		return null;
	}
	
	public String tree() {
		menuTree = permissionBiz.buildMenuTree();
		return SUCCESS;
	}

	public String menuSelect() {
		return AJAX_RETURN_TYPE;
	}
	
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public List<OptionObjectPair> getMenuTypeList() {
		return menuTypeList;
	}

	public void setMenuTypeList(List<OptionObjectPair> menuTypeList) {
		this.menuTypeList = menuTypeList;
	}

	public String menuApp(){
		
		return SUCCESS;
	}
	
	public String getMenuTypeValue() {
		return menuTypeValue;
	}

	public void setMenuTypeValue(String menuTypeValue) {
		this.menuTypeValue = menuTypeValue;
	}

	private void initPairs(String parentType) {
//		UserContext uc = UserContext.getUserContext();
//		Locale local = uc.getLocale();
//		if (local == null) {
//			local = Locale.getDefault();
//		}
//		if (local.equals(Locale.SIMPLIFIED_CHINESE)) {
//			if (null == menuTypeList) {
//				menuTypeList = new ArrayList<OptionObjectPair>();
//				menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_MENU,
//						"WEB菜单"));
//				menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_GUI_MENU,
//						"GUI菜单"));
//			}
//		} else {
//			if (null == menuTypeList) {
//				menuTypeList = new ArrayList<OptionObjectPair>();
//				menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_MENU,
//						"WEB Menu"));
//				menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_GUI_MENU,
//						"GUI Menu"));
//			}
//		}
		menuTypeList = new ArrayList<OptionObjectPair>();
		if (StringUtils.isEmpty(parentType)) {
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_MENU,
					Menu.MENU_TYPE_MENU_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_GUI_MENU,
					Menu.MENU_TYPE_GUI_MENU_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_PDA_MENU,
					Menu.MENU_TYPE_PDA_MENU_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_URL,
					Menu.MENU_TYPE_URL_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_BUTTON,
					Menu.MENU_TYPE_BUTTON_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_CONTROL,
					Menu.MENU_TYPE_CONTROL_VALUE));
		} else if (Menu.MENU_TYPE_MENU.equals(parentType)) {
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_MENU,
					Menu.MENU_TYPE_MENU_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_URL,
					Menu.MENU_TYPE_URL_VALUE));
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_BUTTON,
					Menu.MENU_TYPE_BUTTON_VALUE));
		} else if (Menu.MENU_TYPE_GUI_MENU.equals(parentType)) {
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_GUI_MENU,
					Menu.MENU_TYPE_GUI_MENU_VALUE));
		} else if (Menu.MENU_TYPE_PDA_MENU.equals(parentType)) {
			menuTypeList.add(new OptionObjectPair(Menu.MENU_TYPE_PDA_MENU,
					Menu.MENU_TYPE_PDA_MENU_VALUE));
		}
		;

		if (null == appList || appList.size() <= 0) {
			appList = permissionBiz.getAppPairs(true);
		}
		
		if (null == targetList || targetList.size() <= 0) {
			targetList = new ArrayList<OptionObjectPair>();
			targetList.add(OptionObjectPair.getDefaultOptionObjectPair());
			targetList.add(new OptionObjectPair(Menu.MENU_TARGET_MAC,
					Menu.MENU_TARGET_MAC));
		}
	}
	/*
	private String parseTree2String(List<Tree> trees){
		StringBuilder sb = new StringBuilder("");
		if(null!=trees && trees.size()>0){
			for(Tree tree:trees){
				sb.append(tree.toString());
			}
		}
		return sb.toString();
	}*/
	
	public String searchMenu() {
		StringBuffer sBuffer = new StringBuffer();
		List<Menu> sms;
		if (null != search_string && !"".equals(search_string)) {
//			try {
//				search_string = java.net.URLDecoder.decode(search_string,"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			System.out.println(search_string);
			sBuffer.append("[");
			sms = permissionBiz.searchMenuByTitleWithParent(search_string
					.trim().toUpperCase());
			if (null != sms && sms.size() > 0) {
				for (Menu m : sms) {
					if (null != m) {
						sBuffer.append("\"#" + m.getId() + "\",");
					}
				}
			}
			if (sBuffer.length() > 1) {
				sBuffer.deleteCharAt(sBuffer.length() - 1);
			}
			sBuffer.append("]");
			System.out.println(sBuffer);
		}else{
			sBuffer.append("[]");
		}
		PrintWriter pw = null;
		try {
			System.out.println(response.getCharacterEncoding());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.print(sBuffer.toString());
		pw.flush();
		pw.close();
		return null;
	}

	public List<Mac> getMacIdList() {
		return macIdList;
	}

	public void setMacIdList(List<Mac> macIdList) {
		this.macIdList = macIdList;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public String getMacIdJson() {
		return macIdJson;
	}

	public void setMacIdJson(String macIdJson) {
		this.macIdJson = macIdJson;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}
	
	public String checkMenuName() {
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(menuName)) {
			Menu menu = permissionBiz.getMenuByMenuName(menuName);
			if (menu != null) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}
	
	public String checkMenuTitle() {
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(menuTitle)) {
			Menu menu = permissionBiz.getMenuByMenuTitle(menuTitle);
			if (menu != null) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.print(sBuffer.toString());
			pw.flush();
			pw.close();
		}
		return null;
	}
}
