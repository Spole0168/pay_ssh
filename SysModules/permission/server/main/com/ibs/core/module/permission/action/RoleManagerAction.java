package com.ibs.core.module.permission.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.domain.Menu;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.common.module.frameworkimpl.security.dto.RoleDto;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
//import com.ibs.core.module.mdmbasedata.common.OrgTypeConst;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.Tree;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

public class RoleManagerAction extends CrudBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045708292936502483L;

	private IPermissionBiz permissionBiz;

	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

	private String id;
	private String roleName;
	private String roleCode;
	private String r_roleCode; // 做校验用
	private String description;
	private Role role;
	private List<Tree> menuTree;
	private String grantedMenuIdString;

	private List<String> scopes;
	private List<String> manageScopes;
	private List<String> appIds;
	private List<String> groupIds;
	private List<String> rejectRoleIds;

	private List<OptionObjectPair> scopeList;
	//管理范围
	private List<OptionObjectPair> manageScopeList;
	private List<OptionObjectPair> userTypeList; 
	private List<OptionObjectPair> appList;
	private List<OptionObjectPair> groupList;

	private String currentPageAllOperIds;
	private String currentPageSelectedOperIds;
	private String grantedOperIdString;

	private String userId;
	private User user;
	private String currentPageAllRoleIds;
	private String currentPageSelectedRoleIds;
	private String assignedRoleIdString;

	private String isAdmin;

	private String appId;
	private String[] checkedScopes;
	private String[] checkedUserType; 
	//管理范围
	private String[] checkedManageScopes;
	private String checkedScopeStr;
	private boolean editMenu;

	private String assignedMenus;

	private String menuTypeColumnRender;

	private String userName;
	private String userCode;
	private String status;
	private String userType;
	private String userDisplayName;
	private String saveResult;
	private String type;
	private String nodeId;
	private String divId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}

	private List<OptionObjectPair> operTypePairs;

	public String getCheckedScopeStr() {
		return checkedScopeStr;
	}

	public void setCheckedScopeStr(String checkedScopeStr) {
		this.checkedScopeStr = checkedScopeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public boolean isEditMenu() {
		return editMenu;
	}

	public void setEditMenu(boolean editMenu) {
		this.editMenu = editMenu;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String[] getCheckedScopes() {
		return checkedScopes;
	}

	public void setCheckedScopes(String[] checkedScopes) {
		this.checkedScopes = checkedScopes;
	}

	public List<OptionObjectPair> getOperTypePairs() {
		return operTypePairs;
	}

	public void setOperTypePairs(List<OptionObjectPair> operTypePairs) {
		this.operTypePairs = operTypePairs;
	}

	public String getMenuTypeColumnRender() {
		return menuTypeColumnRender;
	}

	public void setMenuTypeColumnRender(String menuTypeColumnRender) {
		this.menuTypeColumnRender = menuTypeColumnRender;
	}

	public String getAssignedMenus() {
		return assignedMenus;
	}

	public void setAssignedMenus(String assignedMenus) {
		this.assignedMenus = assignedMenus;
	}

	public List<Tree> getMenuTree() {
		return menuTree;
	}

	public String getCurrentPageAllOperIds() {
		return currentPageAllOperIds;
	}

	public void setCurrentPageAllOperIds(String currentPageAllOperIds) {
		this.currentPageAllOperIds = currentPageAllOperIds;
	}

	public String getCurrentPageSelectedOperIds() {
		return currentPageSelectedOperIds;
	}

	public void setCurrentPageSelectedOperIds(String currentPageSelectedOperIds) {
		this.currentPageSelectedOperIds = currentPageSelectedOperIds;
	}

	public String getGrantedOperIdString() {
		return grantedOperIdString;
	}

	public void setGrantedOperIdString(String grantedOperIdString) {
		this.grantedOperIdString = grantedOperIdString;
	}

	public void setMenuTree(List<Tree> menuTree) {
		this.menuTree = menuTree;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}

	public List<String> getAppIds() {
		return appIds;
	}

	public void setAppIds(List<String> appIds) {
		this.appIds = appIds;
	}

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public List<String> getRejectRoleIds() {
		return rejectRoleIds;
	}

	public void setRejectRoleIds(List<String> rejectRoleIds) {
		this.rejectRoleIds = rejectRoleIds;
	}

	public List<OptionObjectPair> getScopeList() {
		return scopeList;
	}

	public void setScopeList(List<OptionObjectPair> scopeList) {
		this.scopeList = scopeList;
	}

	public List<OptionObjectPair> getAppList() {
		return appList;
	}

	public void setAppList(List<OptionObjectPair> appList) {
		this.appList = appList;
	}

	public List<OptionObjectPair> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<OptionObjectPair> groupList) {
		this.groupList = groupList;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String role() {
		return SUCCESS;
	}

	/**
	 * 转向角色列表方法
	 * 
	 * @return 角色列表页面
	 * @throws Exception
	 */
	public String list() {
		initOptionPairs( true);
		return SUCCESS;
	}

	/**
	 * 
	 * @return 新建/维护角色页面
	 * @throws Exception
	 */
	public String toSaveOrUpdate() {
		if (logger.isDebugEnabled()) {
			logger.debug("into " + this.getClass().getSimpleName()
					+ " method toSaveOrUpdate()");
		}

		if (null != id) {
			if (logger.isDebugEnabled()) {
				logger.debug("角色id的值为：" + id);
			}
			role = permissionBiz.getRoleById(id, false);
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		if (logger.isDebugEnabled()) {
			logger.debug("into " + this.getClass().getSimpleName()
					+ " method saveOrUpdate()");
		}
		message = "";
		Role role = new Role();
		if (null != roleName && !"".equals(roleName)) {

			role.setRoleName(roleName);
			if(roleCode != null) {
				role.setRoleCode(roleCode.toUpperCase());
			}
			role.setDescription(description);
			role.setAppId(appId);
//			if (null != checkedScopes && checkedScopes.length > 0) {
//				
//				StringBuffer sb = new StringBuffer();
//				sb.append(Role.WHOLE_SCOPE_SEPARATOR);
//				for (int i = 0; i < checkedScopes.length; i++) {
//					sb.append(checkedScopes[i] + Role.WHOLE_SCOPE_SEPARATOR);
//				}
//				role.setScope(sb.toString());
//			}
			
//			if(checkedUserType!=null&&checkedUserType.length>0)
//			{
//				StringBuffer sb = new StringBuffer();
//				sb.append(Role.WHOLE_SCOPE_SEPARATOR);
//				for (int i = 0; i < checkedUserType.length; i++) {
//					sb.append(checkedUserType[i] + Role.WHOLE_SCOPE_SEPARATOR);
//				}
//				role.setUserScope(sb.toString());
//			}
			
//			if (null != checkedManageScopes && checkedManageScopes.length > 0) {
//				
//				StringBuffer sb = new StringBuffer();
//				sb.append(Role.WHOLE_SCOPE_SEPARATOR);
//				for (int i = 0; i < checkedManageScopes.length; i++) {
//					sb.append(checkedManageScopes[i] + Role.WHOLE_SCOPE_SEPARATOR);
//				}
//				
//				role.setManageScope(sb.toString());
//			}
			
//			role.setIsAdmin(isAdmin);
			if (null != id && !"".equals(id)) {
				role.setId(id);
			}
			try {
				// permissionBiz.saveOrUpdateRole(role);
				permissionBiz.saveOrUpdateRole(role, editMenu,
						grantedMenuIdString);
			} catch (Exception e) {
				e.printStackTrace();
				super.addActionError(e.getMessage());
				return ERROR;
			}
//			message = "OK";
//			return AJAX_RETURN_TYPE;
		}
		// return AJAX_RETURN_TYPE;
		id = role.getId();
		if (editMenu == true && StringUtils.isNotEmpty(id)) {
			// if (null != id && null != grantedMenuIdString) {
			// permissionBiz.grantMenusToRole(id, grantedMenuIdString);
			// }

			Role myrole = permissionBiz.getRoleById(id, true);

			// 刷新缓存
			Set<User> userList = myrole.getIncludedUsers();

			if (CollectionUtils.isNotEmpty(userList)) {
				List<String> userIds = new ArrayList<String>();
				for (User user : userList) {
					if (null != user && null != user.getId()) {
						userIds.add(user.getId());
					}
				}

				try {
					// 刷新Cache
					CacheManager
							.getInstance()
							.refresh(
									com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE,
									userIds);
				} catch (Exception e) {
					logger.error("刷新缓存失败 : \n" + e.getMessage());
				}
			}
		}
		saveResult = "success";
		return modify();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		String[] idStringArray = getSelectIds();
		try {
			if (null != idStringArray) {
				List<String> userIds = new ArrayList<String>();
				if (null != idStringArray && idStringArray.length > 0) {
					for (int i = 0; i < idStringArray.length; i++) {
						IPage<UserDto> users = permissionBiz.getUserListByPage(
								null, null, null, null, null, null,
								"", null, Integer.MAX_VALUE, 0, null);
						if (null != users) {
							Collection<UserDto> uc = users.getRows();
							for (UserDto u : uc) {
								userIds.add(u.getId());
							}
						}
					}
				}
				permissionBiz.deleteRoles(idStringArray);
				try {
					if (userIds.size() > 0) {
						System.out.println(userIds.size());
						String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
						CacheManager.getInstance().refresh(userCache, userIds);
					}
				} catch (Exception e) {
					logger.error("删除角色刷新Cache时失败，原因：\n" + e);
					e.printStackTrace();
				}
				permissionBiz.deleteRoles(idStringArray);
			}
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			message = e.getMessage();
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	/**
	 * 
	 * @return
	 */
	public String toGrantMenus() throws Exception {
		if (null != id) {
			// role = permissionService.getRoleCascadeById(id);

			Set<Menu> grantedMenus = role.getGrantedMenus();

			if (null != grantedMenus && !grantedMenus.isEmpty()) {
				StringBuilder tmpGrantedMenuIdString = new StringBuilder(50);
				Map<String, Object> tmpNotLeafMenuId = new HashMap<String, Object>();

				for (Menu menu : grantedMenus) {
					if (null != menu.getParentMenu()) {
						tmpNotLeafMenuId
								.put(menu.getParentMenu().getId(), null);
					}
				}

				for (Menu menu : grantedMenus) {
					if (!tmpNotLeafMenuId.containsKey(menu.getId()))
						tmpGrantedMenuIdString.append(menu.getId()).append(",");
				}

				tmpGrantedMenuIdString.deleteCharAt(tmpGrantedMenuIdString
						.length() - 1);

				grantedMenuIdString = tmpGrantedMenuIdString.toString();
			}
		}

		// List<Menu> menus = permissionService.getAllMenu();
		// menuTree = permissionService.buildMenuTree(menus);

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String grantMenus() throws Exception {
		if (null != id && null != grantedMenuIdString) {
			// Role role = permissionService.getRoleById(id);
			// permissionService.grantMenu2Role(role, grantedMenuIdString);
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String toAssignedRoles() throws Exception {
		if (null != userId) {
			// user = permissionService.getUserCascadeById(userId);

			Set<Role> assignedRoles = user.getAssignedRoles();

			if (null != assignedRoles && !assignedRoles.isEmpty()) {
				StringBuilder tmpAssignedRoleIdString = new StringBuilder(50);

				for (Role role : assignedRoles) {
					tmpAssignedRoleIdString.append(role.getId()).append(",");
				}

				tmpAssignedRoleIdString.deleteCharAt(tmpAssignedRoleIdString
						.length() - 1);

				assignedRoleIdString = tmpAssignedRoleIdString.toString();
			}
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String assignRoles() throws Exception {
		try {
			if (null != userId && null != currentPageAllRoleIds) {
				// permissionService.grantRolesToUser(userId,
				// currentPageAllRoleIds, currentPageSelectedRoleIds);
				//
				// user = permissionService.getUserCascadeById(userId);

				Set<Role> assignedRoles = user.getAssignedRoles();

				if (null != assignedRoles && !assignedRoles.isEmpty()) {
					StringBuilder tmpAssignedRoleIdString = new StringBuilder(
							50);

					for (Role role : assignedRoles) {
						tmpAssignedRoleIdString.append(role.getId())
								.append(",");
					}

					tmpAssignedRoleIdString
							.deleteCharAt(tmpAssignedRoleIdString.length() - 1);

					message = tmpAssignedRoleIdString.toString();
				} else {
					message = "";
				}

			} else {
				message = "error";
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			// message = e.getMessage();
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getR_roleCode() {
		return r_roleCode;
	}

	public void setR_roleCode(String rRoleCode) {
		r_roleCode = rRoleCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrantedMenuIdString() {
		return grantedMenuIdString;
	}

	public void setGrantedMenuIdString(String grantedMenuIdString) {
		this.grantedMenuIdString = grantedMenuIdString;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCurrentPageAllRoleIds() {
		return currentPageAllRoleIds;
	}

	public void setCurrentPageAllRoleIds(String currentPageAllRoleIds) {
		this.currentPageAllRoleIds = currentPageAllRoleIds;
	}

	public String getCurrentPageSelectedRoleIds() {
		return currentPageSelectedRoleIds;
	}

	public void setCurrentPageSelectedRoleIds(String currentPageSelectedRoleIds) {
		this.currentPageSelectedRoleIds = currentPageSelectedRoleIds;
	}

	public String getAssignedRoleIdString() {
		return assignedRoleIdString;
	}

	public void setAssignedRoleIdString(String assignedRoleIdString) {
		this.assignedRoleIdString = assignedRoleIdString;
	}

	@Override
	public String create() {
		initOptionPairs(true);
//		checkedScopes = new String[0];
//		checkedUserType = new String[0];
//		checkedManageScopes = new String[0];
		return SUCCESS;
	}

	@Override
	public String export() {
		return null;
	}

	@Override
	public String modify() {
		// if (null != id) {
		// role = permissionBiz.getRoleById(id, false);
		// }
		initOptionPairs(true);
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		role = permissionBiz.getRoleById(id, true);
//		checkedScopeStr = role.getScope();
//		if (StringUtils.isNotEmpty(checkedScopeStr)) {
//			checkedScopes = checkedScopeStr.split(Role.WHOLE_SCOPE_SEPARATOR);			
//		} else {
//			checkedScopes = new String[0];
//		}
//		String checkedUserTypeStr=role.getUserScope(); 
//		if (StringUtils.isNotEmpty(checkedUserTypeStr)) {
//			checkedUserType = checkedUserTypeStr.split(Role.WHOLE_SCOPE_SEPARATOR);			
//		} else {
//			checkedUserType = new String[0];
//			 
//		}
//		   
//		String checkedManageScopeStr=role.getManageScope();
//		if (StringUtils.isNotEmpty(checkedUserTypeStr)) {
//			checkedManageScopes = checkedManageScopeStr.split(Role.WHOLE_SCOPE_SEPARATOR);			
//		} else {
//			checkedManageScopes = new String[0];
//		}

		Set<Menu> grantedMenus = role.getGrantedMenus();
		Set<Menu> grantedGuiMenus = role.getGrantedGuiMenus();
		Set<Menu> grantedOpers = role.getGrantedOpers();
		
		if (null == type || "".equals(type)) {
			assignedMenus = "";
			if (null != grantedMenus && !grantedMenus.isEmpty()) {
				for (Menu menu : grantedMenus) {
					assignedMenus += menu.getId() + "\",\"";
				}
			}
			if (null != grantedGuiMenus && !grantedGuiMenus.isEmpty()) {
				for (Menu menu : grantedGuiMenus) {
					assignedMenus += menu.getId() + "\",\"";
				}
			}
			if (null != grantedOpers && !grantedOpers.isEmpty()) {
				for (Menu menu : grantedOpers) {
					assignedMenus += menu.getId() + "\",\"";
				}
			}
			if (assignedMenus.length() > 3) {
				assignedMenus = assignedMenus.substring(0,
						assignedMenus.length() - 3);
			}
			menuTree = permissionBiz.buildMenuTree();
			return SUCCESS;
		} else {
			ArrayList<Menu> allMenus = new ArrayList<Menu>();
			if (grantedMenus != null) {
				allMenus.addAll(grantedMenus);
			}
			if (grantedGuiMenus != null) {
				allMenus.addAll(grantedGuiMenus);
			}
			if (grantedOpers != null) {
				allMenus.addAll(grantedOpers);
			}
			
			//过滤菜单中的地址信息
			for(Menu list:allMenus){
				list.setLocation("");
			}
			menuTree = permissionBiz.buildMenuTree(allMenus);
			return DONE;
		}

	}

	@Override
	public String search() {
		logger.debug("into " + this.getClass().getSimpleName()
				+ " method list()");

		try {
//			scopes = new ArrayList<String>();
//			String roleScope = this.getSearchFields().get("roleScope");
//			if (null != roleScope && !"".equals(roleScope.trim())) {
//				scopes.add(roleScope);
//			} 
			
//			manageScopes = new ArrayList<String>();
//			String roleManageScopes = this.getSearchFields().get("manageScopes");
//			if (null != roleManageScopes && !"".equals(roleManageScopes.trim())) {
//				manageScopes.add(roleManageScopes);
//			} 
			
			String roleApp = this.getSearchFields().get("roleApp");
			if (null != roleApp && !"".equals(roleApp.trim())) {
				appIds = new ArrayList<String>();
				appIds.add(roleApp);
			}
			// String roleGroup = this.getSearchFields().get("roleGroup");
			// if(null != roleGroup && !"".equals(roleGroup.trim())){
			// groupIds = new ArrayList<String>();
			// groupIds.add(roleGroup);
			// }
			String userId = this.getSearchFields().get("userId");
			Page<RoleDto> result = (Page<RoleDto>) permissionBiz
					.getRolePageBySql(roleName, roleCode, description, null,null,
							appIds, null, null, userId, null,
							queryPage.getPageSize(), queryPage.getPageIndex(),
							queryPage.getSortMap());
			this.setResult(result);
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	public String toAssignedUser() {
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		
		role = permissionBiz.getRoleById(id, false);
		prepareTypeList(false);

		IUser user = null;
		try {
			user = getCurrentUser();
		} catch (Exception e) {
			addActionError("无法找到当前用户信息");
			return ERROR;
		}

		if (null == user) {
			addActionError("无法找到当前用户信息");
			return ERROR;
		}

		return SUCCESS;
	}

	public String searchAssignedUser() {
		logger.debug("into " + this.getClass().getSimpleName()
				+ " method list()");
		// this.populateJqGridData();
		//
		// //创建QueryPage
		// queryPage = new
		// QueryPage(this.getRows(),this.getPage(),this.getSidx(),this.getSord());
		// queryPage.setSortMap(this.getSearchFields());
		try {
			if (null != id && !"".equals(id)) {
				// Page<User> result = (Page<User>)
				// permissionBiz.getUsersByRoleByPage(id, queryPage);
				String userCode = this.getSearchFields().get("userCodeSearch");
				String userName = this.getSearchFields().get("userNameSearch");
//				String description = this.getSearchFields().get(
//						"descriptionSearch");
//				String userDisplayName = this.getSearchFields().get(
//						"userDisplayNameSearch");

				userCode = (null == userCode) ? null : userCode.trim();
				userName = (null == userName) ? null : userName.trim();
//				description = (null == description) ? null : description.trim();
//				userDisplayName = (null == userCode) ? null : userDisplayName
//						.trim();

				String userType = this.getSearchFields().get("userTypeSearch");
//				String districtId = this.getSearchFields().get("districtId");
				Page<UserDto> result = (Page<UserDto>) permissionBiz
						.getUserListByPage(userName, userCode, null,
								userType, null, null,
								id,
								null, queryPage.getPageSize(),
								queryPage.getPageIndex(),
								queryPage.getSortMap());
				this.setResult(result);
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return AJAX_RETURN_TYPE;
	}

	public String toAssignedOper() {
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		role = permissionBiz.getRoleById(id, true);
		Set<Menu> grandedOpers = role.getGrantedOpers();
		if (null != grandedOpers && grandedOpers.size() > 0) {
			StringBuilder tempGrandedOpersSb = new StringBuilder(50);

			for (Menu oper : grandedOpers) {
				tempGrandedOpersSb.append(oper.getId()).append(",");
			}

			tempGrandedOpersSb.deleteCharAt(tempGrandedOpersSb.length() - 1);

			grantedOperIdString = tempGrandedOpersSb.toString();
		}
		menuTypeColumnRender = Menu.menuTypeColumnRender;
		operTypePairs = Menu.operTypePairs;
		return SUCCESS;
	}

	public String searchAssignedOper() {
		logger.debug("into " + this.getClass().getSimpleName()
				+ " method assignedOperSearch()");
		// this.populateJqGridData();
		//
		// //创建QueryPage
		// queryPage = new
		// QueryPage(this.getRows(),this.getPage(),this.getSidx(),this.getSord());
		// queryPage.setSortMap(this.getSearchFields());
		try {
			Page<Menu> result = (Page<Menu>) permissionBiz
					.getOperListByPage(queryPage);
			this.setResult(result);
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return AJAX_RETURN_TYPE;
	}

	public String assignOper() {
		try {
			if (null != id && null != currentPageAllOperIds) {
				permissionBiz.grantOpersToRole(id, currentPageAllOperIds,
						currentPageSelectedOperIds);

				role = permissionBiz.getRoleById(id, true);

				Set<Menu> assignedOpers = role.getGrantedOpers();

				if (null != assignedOpers && !assignedOpers.isEmpty()) {
					StringBuilder tmpGrantedResourceIdString = new StringBuilder(
							50);

					for (Menu resource : assignedOpers) {
						tmpGrantedResourceIdString.append(resource.getId())
								.append(",");
					}

					tmpGrantedResourceIdString
							.deleteCharAt(tmpGrantedResourceIdString.length() - 1);

					message = tmpGrantedResourceIdString.toString();
				} else {
					message = "";
				}

			} else {
				message = "error";
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			message = e.getMessage();
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	/**
	 * 查找某一角色可以管理的范围，该角色必须是管理员
	 * 
	 * @return
	 */
	public String toAssignedMenu() {
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		role = permissionBiz.getRoleById(id, false);
		//
		// Set<Menu> grantedMenus = role.getGrantedMenus();
		// Set<Menu> grantedGuiMenus = role.getGrantedGuiMenus();
		// Set<Menu> grantedOpers = role.getGrantedOpers();
		//
		// assignedMenus = "";
		// if (null != grantedMenus && !grantedMenus.isEmpty()) {
		// for (Menu menu : grantedMenus) {
		// assignedMenus += menu.getId()+"\",\"";
		// }
		// }
		// if (null != grantedGuiMenus && !grantedGuiMenus.isEmpty()) {
		// for (Menu menu : grantedGuiMenus) {
		// assignedMenus += menu.getId()+"\",\"";
		// }
		// }
		// if (null != grantedOpers && !grantedOpers.isEmpty()) {
		// for (Menu menu : grantedOpers) {
		// assignedMenus += menu.getId()+"\",\"";
		// }
		// }
		assignedMenus = "";
		List<Menu> menus = permissionBiz.getManageMenus(role.getRoleCode());
		if (null != menus && !menus.isEmpty()) {
			for (Menu menu : menus) {
				assignedMenus += menu.getId() + "\",\"";
			}
		}
		if (assignedMenus.length() > 3) {
			assignedMenus = assignedMenus.substring(0,
					assignedMenus.length() - 3);
		}

		menuTree = permissionBiz.buildMenuTree();

		return SUCCESS;

	}

	public String assignMenu() throws Exception {
		try {
			if (null != id && null != grantedMenuIdString) {
				permissionBiz.grantMenusToRole(id, grantedMenuIdString);
			}
			Role role = permissionBiz.getRoleById(id, true);

			// 刷新缓存
			Set<User> userList = role.getIncludedUsers();

			if (CollectionUtils.isNotEmpty(userList)) {
				List<String> userIds = new ArrayList<String>();
				for (User user : userList) {
					if (null != user && null != user.getId()) {
						userIds.add(user.getId());
					}
				}

				try {
					// 刷新Cache
					CacheManager
							.getInstance()
							.refresh(
									com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE,
									userIds);
				} catch (Exception e) {
					logger.error("刷新缓存失败 : \n" + e.getMessage());
				}
			}
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			message = e.getMessage();
			return ERROR;
		}
		// return AJAX_RETURN_TYPE;
		return toAssignedMenu();
	}

	public String assignManageMenu() throws Exception {
		try {
			if (null != id && null != grantedMenuIdString) {
				permissionBiz.grantManageMenusToRole(id, grantedMenuIdString);
			}
			message = "保存成功";
		} catch (Exception e) {
			addActionError(e.getMessage());
			message = e.getMessage();
			return ERROR;
		}
		// return AJAX_RETURN_TYPE;
		return toAssignedMenu();
	}

	public String checkRoleCode() {
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(roleCode)) {
			Role role = permissionBiz.getRoleByCode(roleCode.toUpperCase());
			if (role != null) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				// System.out.println(response.getCharacterEncoding());
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

	public String checkRoleName() {
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(roleName)) {
			Role role = permissionBiz.getRoleByName(roleName);
			if (role != null) {
				sBuffer.append(false);
			} else {
				sBuffer.append(true);
			}
			PrintWriter pw = null;
			try {
				// System.out.println(response.getCharacterEncoding());
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
	
	/*
	 * private Set<Menu> menuFilter(Set<Menu> ori, String type) { Set<Menu> res
	 * = new HashSet<Menu>(); if (null == type || "".equals(type)) { return res;
	 * } if (null == ori || ori.size() <= 0) { return res; }
	 * 
	 * Iterator<Menu> iter = ori.iterator(); while (iter.hasNext()) { Menu menu
	 * = iter.next(); String menuType = menu.getMenuType(); if
	 * ("menu".equals(type) && Menu.MENU_TYPE_MENU.equals(menuType)) {
	 * res.add(menu); } else if ("oper".equals(type) &&
	 * (Menu.MENU_TYPE_BUTTON.equals(menuType) || Menu.MENU_TYPE_URL
	 * .equals(menuType))) { res.add(menu); } }
	 * 
	 * return res; }
	 */

	private void initOptionPairs(boolean needAppDf) {
//		if (null == scopeList || scopeList.size() <= 0) {
//			scopeList = new ArrayList<OptionObjectPair>();
//			manageScopeList = new ArrayList<OptionObjectPair>();
//			userTypeList =new ArrayList<OptionObjectPair>();
//			if (needScopeDf) {
//				scopeList.add(OptionObjectPair.getDefaultOptionObjectPair());
//				manageScopeList.add(OptionObjectPair.getDefaultOptionObjectPair());
//			}
//			if (needScopeDf) {
//				userTypeList.add(OptionObjectPair.getDefaultOptionObjectPair());
//			}
//			
//			try {
//				int j = 0;
//				for (; j < PermissionConstant.USER_TYPE_PRIORITY.length; j++) {
//					userTypeList.add(PermissionConstant.USER_TYPE_PRIORITY[j]);
//				}
//		 
//			} catch (Exception e) {
//				addActionError("无法找到用户信息！");
//			}

//		}
		if (null == appList || appList.size() <= 0) {
			appList = permissionBiz.getAppPairs(needAppDf);
		}
		// if (null == groupList || groupList.size() <= 0) {
		// groupList = permissionBiz.getRoleGroupPairs(needGroupDf, null,
		// 99999);
		// }

	}

	private List<OptionObjectPair> typeList;
	
	private String typeColumnRender;

	public List<OptionObjectPair> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<OptionObjectPair> typeList) {
		this.typeList = typeList;
	}

	public String getTypeColumnRender() {
		return typeColumnRender;
	}

	public void setTypeColumnRender(String typeColumnRender) {
		this.typeColumnRender = typeColumnRender;
	}
	//设置用户类型
	private void prepareTypeList(boolean needDefault) {
		typeList = new ArrayList<OptionObjectPair>();

		if (needDefault)
			typeList.add(OptionObjectPair.getDefaultOptionObjectPair());
		
		typeList.add(new OptionObjectPair(User.USER_TYPE_EMPLOYEE,
				User.USER_TYPE_EMPLOYEE_VALUE));
		typeList.add(new OptionObjectPair(User.USER_TYPE_ADMIN,
				User.USER_TYPE_ADMIN_VALUE));
//		typeList.add(new OptionObjectPair(User.USER_TYPE_CUSTOMER,
//				User.USER_TYPE_CUSTOMER_VALUE));
//		 typeList.add(new OptionObjectPair(User.USER_TYPE_AGENT,
//		 User.USER_TYPE_AGENT_VALUE));
//		 typeList.add(new OptionObjectPair(User.USER_TYPE_INTAGENT,
//				 User.USER_TYPE_INTAGENT_VALUE));

		typeColumnRender = SelectRenderUtils.toRenderString(typeList);
	}

	public String removeUserFromRole() {
		List<String> userIdList = new ArrayList<String>(0);

		try {// 执行删除动作
				// 检查输入参数是否为空
			if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(id)) {
				return AJAX_RETURN_TYPE;
			}
			// 检查是否能正确解析
			String[] users = userId.split(",");
			if (null == users || users.length <= 0) {
				return AJAX_RETURN_TYPE;
			}
			for (int i = 0; i < users.length; i++) {
				userIdList.add(users[i]);
			}
			permissionBiz.removeUserFromRole(userIdList, id);
		} catch (Exception e) {
			message = e.getMessage();
			return ERROR;
		}

		refreshUserCache(userIdList);
		message = "success";
		return AJAX_RETURN_TYPE;
	}

	private void refreshUserCache(List<String> userIds) {
		try {
			CacheManager
					.getInstance()
					.refresh(
							com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE,
							userIds);
		} catch (Exception e) {
			logger.error("刷新用户缓存失败！");
			e.printStackTrace();
		}
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDivId() {
		return divId;
	}

	public List<OptionObjectPair> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<OptionObjectPair> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public String[] getCheckedUserType() {
		return checkedUserType;
	}

	public void setCheckedUserType(String[] checkedUserType) {
		this.checkedUserType = checkedUserType;
	}

	public String[] getCheckedManageScopes() {
		return checkedManageScopes;
	}

	public void setCheckedManageScopes(String[] checkedManageScopes) {
		this.checkedManageScopes = checkedManageScopes;
	}

	public List<OptionObjectPair> getManageScopeList() {
		return manageScopeList;
	}

	public void setManageScopeList(List<OptionObjectPair> manageScopeList) {
		this.manageScopeList = manageScopeList;
	}

	public List<String> getManageScopes() {
		return manageScopes;
	}

	public void setManageScopes(List<String> manageScopes) {
		this.manageScopes = manageScopes;
	}
}
