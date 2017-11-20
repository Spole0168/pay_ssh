package com.ibs.core.module.permission.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.ibs.common.module.frameworkimpl.security.constant.PermissionConstant;
import com.ibs.common.module.frameworkimpl.security.domain.Role;
import com.ibs.common.module.frameworkimpl.security.domain.User;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.permission.biz.IPermissionBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.Md5Encoder;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

public class UserManagerAction extends CrudBaseAction {

	private static final long serialVersionUID = 2813373227347384697L;

	private IPermissionBiz permissionBiz;
	private String id;
	private String userName;
	private String userCode;
	private String userPwd;
	private String description;
	private String status;
	private String userType;
	private String userDisplayName;
	private String phonenum;
	private String email;
	private User user;
	private List<OptionObjectPair> statusList;
	private List<OptionObjectPair> typeList;
	private List<OptionObjectPair> usedList;
	private String statusColumnRender;
	private String typeColumnRender;
	private String usedColumnRender;
	private String currentPageAllRoleIds;
	private String currentPageSelectedRoleIds;
	private String assignedRoleIdString;
	private String nodeId;
	private String formerName;
	private String roleScope;
	private String appId;
	private IDataDictService dataDictService;
	private List<OptionObjectPair> scopeList;
	private List<OptionObjectPair> appList;
	private List<OptionObjectPair> groupList;
	private Integer pageSize;
	private Integer pageIndex;
	private  String currentUserId;
	private boolean detailFlag;

	// 页面初期显示
	public String list() {
		
		initialPage();
		
		IUser user = null;
		try{
			user = getCurrentUser();
		}catch(Exception e){
			addActionError("无法找到当前用户信息");
			return ERROR;
		}
		if(null == user){
			addActionError("无法找到当前用户信息");
			return ERROR;
		}
		return SUCCESS;
	}

	// 查询按钮
	@Override
	public String search() {
		logger.debug("into " + this.getClass().getSimpleName()
				+ " method search()");
		try {
			String userCode = this.getSearchFields().get("userCodeSearch");
			String userName = this.getSearchFields().get("userNameSearch");
//			String description = this.getSearchFields().get("descriptionSearch");
//			String userDisplayName = this.getSearchFields().get("userDisplayNameSearch");
			
			userCode = (null == userCode)?null:userCode.trim();
			userName = (null == userName)?null:userName.trim();
//			description = (null == description)?null:description.trim();
//			userDisplayName = (null == userCode)?null:userDisplayName.trim();

			String userType = this.getSearchFields().get("userTypeSearch");
//			String districtId = this.getSearchFields().get("districtId");
			
			if (StringUtils.isNotEmpty(userCode)) {
				if (null != queryPage) {
					queryPage.addQueryCondition("userCode", "%" + userCode + "%");
					queryPage.addLikeSearch("userCode", userCode);
				}
			}
			if (StringUtils.isNotEmpty(userName)) {
				if (null != queryPage) {
					queryPage.addQueryCondition("userName", "%" + userName + "%");
					queryPage.addLikeSearch("userName", userName);
				}
			}
			if (StringUtils.isNotEmpty(userType)) {
				if (null != queryPage) {
					queryPage.addQueryCondition("userType", "%" + userType + "%");
					queryPage.addLikeSearch("userType", userType);
				}
			}
			
			queryPage.addEqualSearch("status", Constants.IS_VALID_VALID);
			
//			Page<UserDto> result = (Page<UserDto>) permissionBiz.getUserListByPage(
//					userName, userCode, null, userType, null,
//					null, null,null, queryPage.getPageSize(),
//					queryPage.getPageIndex(),queryPage.getSortMap());
			
			Page<User> result = (Page<User>)permissionBiz.getUsersByPage(queryPage);
			
			this.setResult(result);
			
		} catch (Exception e) {
			addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return AJAX_RETURN_TYPE;
	}
	
	// 新增按钮
	@Override
	public String create() {
		
		setDetailFlag(false);
		
//		prepareStatusList(false);
		prepareTypeList(false);
//		prepareUsedList(false);
		
		return SUCCESS;
	}

	@Override
	public String modify() {
		
		setDetailFlag(false);
		
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		user = permissionBiz.getUserById(id);
		user.setUserPwd("******");
		prepareTypeList(false);
		return SUCCESS;
		
	}
	
	public String detail() {
		setDetailFlag(true);
		
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		user = permissionBiz.getUserById(id);
		user.setUserPwd("******");
		prepareTypeList(false);
		return SUCCESS;
	}
	
	public String saveOrUpdate() {
		boolean f = getIsModify();
		logger.debug("getIsModify=" + f);
		
		// 新增的情况下，首先检测是否已经存在相同的用户名或者登录名
		if(!f) {
			List<User> userTemp;
			if(StringUtils.isNotEmpty(userName)) {
				userTemp = permissionBiz.checkUniqueUserName(userName);
				if(userTemp != null && userTemp.size() > 0){
					message = "该用户已存在！";
					return AJAX_RETURN_TYPE;
				}
			} 
			if(StringUtils.isNotEmpty(userCode)) {
				userTemp = permissionBiz.checkUniqueUserName(userCode);
				if(userTemp != null && userTemp.size() > 0){
					message = "该用户已存在！";
					return AJAX_RETURN_TYPE;
				}
			} 
		}
		
		IUser self = getCurrentUser();
		if (null != id && !"".equals(id.trim())) {
			// 修改的情况下，首先从DB中查出要修改的用户信息
			user = permissionBiz.getUserById(id);
		}else {
			// 新增的情况下，新建一个空的用户信息
			user = new User();
		}
		// 修改者的信息同时插入用户信息中
		if (null != self) {
			user.setLastUpdateId(self.getUserId());
			user.setLastUpdateTm(new Date());
			user.setLastUpdator(self.getDisplayName());
			user.setLastUpdateCode(self.getUserCode());
		}
		
		// 下面一段是新增和修改都要保存的用户信息
		if(StringUtils.isNotEmpty(userCode)) {
			user.setUserCode(userCode);
		} else {
			user.setUserCode(userName);
		}
		if(StringUtils.isNotEmpty(phonenum)) {
			user.setPhonenum(phonenum);
		}
		if(StringUtils.isNotEmpty(email)) {
			user.setEmail(email);
		}
		if (null != description && !"".equals(description)) {
			user.setDescription(description);
		}
		if (null != userType && !"".equals(userType)) {
			user.setUserType(userType);
		}
		
		if (null != userPwd && !"".equals(userPwd)) {
			user.setPwdUpdateDate(new Date());
			user.setUserPwd(new Md5Encoder().encode(userPwd));
		}

		// 下面一段是只有新增才会添加的用户信息
		if(!getIsModify()) {
			if (null != userName && !"".equals(userName)) {
				user.setUserName(userName);
			}
			user.setCreator(self.getUserName());
			user.setCreateTm(new Date());
			
			user.setStatus(User.USER_STATUS_VALID);
			user.setDefaultLocate("zh_CN");
		}
		logger.debug("uId=" + id + "---userName=" + userName + "---userPwd=" + userPwd + "---userType=" + userType + "---status=" + status + "---description=" + description);
		try{
			permissionBiz.saveOrUpdateUser(user);
			message = "OK";
		}catch(Exception e){
			message = "保存失败！";
			addActionError(e.getMessage());
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	public String delete() {
		try {
			if (null != id) {
				permissionBiz.deleteUser(id);
			}
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			message = e.getMessage();
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}


	public String toSaveOrUpdate() throws Exception {
		return SUCCESS;
	}
	
	public String user() {
		prepareStatusList(false);
		prepareTypeList(false);
		prepareUsedList(false);
		return SUCCESS;
	}
	
	@Override
	public String export() {
		return null;
	}

	// 初期化页面
	public void initialPage() {
		prepareStatusList(false);
		prepareTypeList(false);
		prepareUsedList(false);
	}
	
	public String userAssignRoles()	{
		try {
			if (null != id && null != currentPageAllRoleIds) {
				permissionBiz.grantRolesToUser(id,
						currentPageAllRoleIds,
						currentPageSelectedRoleIds);
				try{
					// 刷新Cache
					String userCache = com.ibs.common.module.frameworkimpl.common.Constants.USER_CACHE;
					CacheManager.getInstance().refresh(userCache, id);
				}catch(Exception e){
					logger.error("刷新缓存失败 : "+e.getMessage());
					e.printStackTrace();
				}
				
				
				user = permissionBiz.getUserCascadeById(id);
				
				Set<Role> assignedRoles = user.getAssignedRoles();

				if (null != assignedRoles && !assignedRoles.isEmpty()) {
					StringBuilder tmpAssignedRoleIdString = new StringBuilder(50);

					for (Role role : assignedRoles) {
						tmpAssignedRoleIdString.append(role.getId()).append(",");
					}

					tmpAssignedRoleIdString.deleteCharAt(tmpAssignedRoleIdString
							.length() - 1);

					message = tmpAssignedRoleIdString.toString();
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
	
	public String toUserAssignRoles() {
		String[] idStringArray = getSelectIds();
		if (idStringArray != null && idStringArray.length > 0) {
			id = idStringArray[0];
		}
		if (null == id || "".equals(id)) {
			return ERROR;
		}
		user = permissionBiz.getUserCascadeById(id);
		
		if (null != user) {
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
		initOptionPairs();
		return SUCCESS;
	}
	
	public String userRoleList(){
		queryPage = new QueryPage(this.getRows(),this.getPage(),this.getSidx(),this.getSord());
		Page<Role> result = (Page<Role>)permissionBiz.getRoleListByPage(queryPage);
		this.setResult(result);
		return AJAX_RETURN_TYPE;
	}
	
	public String userMain(){
		
		initialPage();
		
		IUser user = null;
		try{
			user = getCurrentUser();
			
		}catch(Exception e){
			addActionError("无法找到当前用户信息");
			return ERROR;
		}
		
		if(null == user){
			addActionError("无法找到当前用户信息");
			return ERROR;
		}
		
		currentUserId = user.getUserId();
		
		return SUCCESS;
	}
	
	public String leftTree() {
		return SUCCESS;
	}
	
	public String initUserPwd(){
//		if(null == pageSize || pageSize.intValue()<0){
//			pageSize = 10;
//		}
//		
//		if(null == pageIndex || pageIndex.intValue()<0){
//			pageIndex = 0;
//		}
//		permissionBiz.initUserPwd(pageSize, pageIndex);
//		System.out.println("success");
		return null;
	}
	
	private void prepareStatusList(boolean needDefault) {
		statusList = new ArrayList<OptionObjectPair>();

		if (needDefault)
			statusList.add(OptionObjectPair.getDefaultOptionObjectPair());

		statusList.add(new OptionObjectPair(User.USER_STATUS_VALID,
				User.USER_STATUS_VALID_VALUE));
		statusList.add(new OptionObjectPair(User.USER_STATUS_INVALID,
				User.USER_STATUS_INVALID_VALUE));

		statusColumnRender = SelectRenderUtils.toRenderString(statusList);
	}
	
	private void prepareUsedList(boolean needDefault) {
		usedList = new ArrayList<OptionObjectPair>();
		
		if (needDefault)
			usedList.add(OptionObjectPair.getDefaultOptionObjectPair());
		
		usedList.add(new OptionObjectPair(PermissionConstant.USER_USED,
				PermissionConstant.USER_USED_VALUE));
		usedList.add(new OptionObjectPair(PermissionConstant.USER_INUSED,
				PermissionConstant.USER_INUSED_VALUE));
		
		usedColumnRender = SelectRenderUtils.toRenderString(usedList);
		
	}

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
// 		typeList.add(new OptionObjectPair(User.USER_TYPE_AGENT,
// 				User.USER_TYPE_AGENT_VALUE));
// 		 typeList.add(new OptionObjectPair(User.USER_TYPE_INTAGENT,
//				 User.USER_TYPE_INTAGENT_VALUE));
		
		typeColumnRender = SelectRenderUtils.toRenderString(typeList);
	}
	public String checkUniqueUserName(){
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(userName)) {
			if (userName.equals(formerName)) {
				sBuffer.append(true);
			} else {
				List<User> userList = permissionBiz
						.checkUniqueUserName(userName);
				if (userList != null && userList.size() > 0) {
					sBuffer.append(false);
				} else {
					sBuffer.append(true);
				}
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

	private void initOptionPairs() {
		if (null == scopeList || scopeList.size() <= 0) {
			scopeList = new ArrayList<OptionObjectPair>();
			scopeList.add(OptionObjectPair.getDefaultOptionObjectPair());
			scopeList.add(new OptionObjectPair(PermissionConstant.ORG_TYPE_HEADQUARTER, PermissionConstant.ORG_TYPE_HEADQUARTER_VALUE));
			scopeList.add(new OptionObjectPair(PermissionConstant.ORG_TYPE_MANAGE_AREA, PermissionConstant.ORG_TYPE_MANAGE_AREA_VALUE));
			scopeList.add(new OptionObjectPair(PermissionConstant.TYPE_REGION_MANAGE, PermissionConstant.TYPE_REGION_MANAGE_VALUE));
			scopeList.add(new OptionObjectPair(PermissionConstant.ORG_TYPE_TRANSFER_CENTER, PermissionConstant.ORG_TYPE_TRANSFER_CENTER_VALUE));
			scopeList.add(new OptionObjectPair(PermissionConstant.ORG_TYPE_SUB_BRANCH, PermissionConstant.ORG_TYPE_SUB_BRANCH_VALUE));
			scopeList.add(new OptionObjectPair(PermissionConstant.ORG_TYPE_SUB_DEPARTMENT, PermissionConstant.ORG_TYPE_SUB_DEPARTMENT_VALUE));
		}
		if (null == appList || appList.size() <= 0) {
			appList = permissionBiz.getAppPairs(true);
		}
//		if (null == groupList || groupList.size() <= 0) {
//			groupList = permissionBiz.getRoleGroupPairs(true, null, 99999);
//		}

	}
	
	/**
	 * get and set variables
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatusColumnRender() {
		return statusColumnRender;
	}

	public void setStatusColumnRender(String statusColumnRender) {
		this.statusColumnRender = statusColumnRender;
	}

	public String getTypeColumnRender() {
		return typeColumnRender;
	}

	public void setTypeColumnRender(String typeColumnRender) {
		this.typeColumnRender = typeColumnRender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}

	public List<OptionObjectPair> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<OptionObjectPair> typeList) {
		this.typeList = typeList;
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getNode() {
		return null;
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

	public String getUsedColumnRender() {
		return usedColumnRender;
	}

	public void setUsedColumnRender(String usedColumnRender) {
		this.usedColumnRender = usedColumnRender;
	}

	public List<OptionObjectPair> getUsedList() {
		return usedList;
	}

	public void setUsedList(List<OptionObjectPair> usedList) {
		this.usedList = usedList;
	}
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRoleScope() {
		return roleScope;
	}

	public void setRoleScope(String roleScope) {
		this.roleScope = roleScope;
	}

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}
	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}

	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public boolean isDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(boolean detailFlag) {
		this.detailFlag = detailFlag;
	}


}
