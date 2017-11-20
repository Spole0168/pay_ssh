package com.ibs.common.module.frameworkimpl.security.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 用户
 * 
 * @author luoyue
 * 
 */
/**
 * @author huolang
 *
 */
public class User extends BaseEntity {

	public static final String USER_STATUS_INVALID = "00";
	public static final String USER_STATUS_INVALID_VALUE = "禁用";
	public static final String USER_STATUS_VALID = "01";
	public static final String USER_STATUS_VALID_VALUE = "启用";

	/**
	 * 员工
	 */
	public static final String USER_TYPE_EMPLOYEE = "employee";
	
	/**
	 * 客户
	 */
	public static final String USER_TYPE_CUSTOMER = "customer";
	
	/**
	 * 航空货代
	 */
	public static final String USER_TYPE_AGENT = "fltagent";
	/**
	 * 国际件货代
	 */
	public static final String USER_TYPE_INTAGENT = "intagent";
	//public static final String USER_TYPE_EMPLOYEE_VALUE = "员工";
	public static final String USER_TYPE_EMPLOYEE_VALUE = "一般用户";
	public static final String USER_TYPE_CUSTOMER_VALUE = "客户";
	public static final String USER_TYPE_AGENT_VALUE = "航空货代";
	public static final String USER_TYPE_INTAGENT_VALUE = "国际件货代";
	
	public static final String USER_TYPE_ADMIN = "admin";
	public static final String USER_TYPE_ADMIN_VALUE = "管理员";
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507738010618964550L;

	private String id;
	private String userName; // 唯一
	private String userCode; // 考虑从遗留系统导入数据
	private String description;
	private String userPwd;
	private Date lastLoginTm; // 分配用户时为空,用户登录时更新
	private String lastLoginIP; // 分配用户时为空,用户登录时更新
	private String defaultLocate; // 缺省为zh_CN
	private String status; // 0-失效;1-生效.缺省为1.用于临时禁止用户
	private Date createTm; // 分配用户时设置
	private String creator; // User.userName
	private String createId; // User.id
	private String createCode; // User.code
	private Date lastUpdateTm; // 更新用户时设置
	private String lastUpdator; // User.userName
	private String lastUpdateId; // User.id
	private String lastUpdateCode; // User.code
	private String userType;
	private String displayName; // 员工/客户/货代的名字
	private String email;
	private String phonenum;
	
	/**
	 * 数据版本
	 */
	private Long versionNo;

//	private String used;
	
	// add 2011-03-16 for record password date
	private Date pwdUpdateDate; // 最后一次更新用户密码时间

	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

//	public String getUsed() {
//		return used;
//	}
//
//	public void setUsed(String used) {
//		this.used = used;
//	}

	private Set<Role> assignedRoles = new HashSet<Role>(0);

	public String getDisplayName() {
		return displayName;
	}

	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	public String getLastUpdateCode() {
		return lastUpdateCode;
	}

	public void setLastUpdateCode(String lastUpdateCode) {
		this.lastUpdateCode = lastUpdateCode;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Set<Role> getAssignedRoles() {
		return assignedRoles;
	}

	public void setAssignedRoles(Set<Role> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	public User() {

	}

	public User(String id) {
		this.id = id;
	}

	@Override
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Date getLastLoginTm() {
		return lastLoginTm;
	}

	public void setLastLoginTm(Date lastLoginTm) {
		this.lastLoginTm = lastLoginTm;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getDefaultLocate() {
		return defaultLocate;
	}

	public void setDefaultLocate(String defaultLocate) {
		this.defaultLocate = defaultLocate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getLastUpdateTm() {
		return lastUpdateTm;
	}

	public void setLastUpdateTm(Date lastUpdateTm) {
		this.lastUpdateTm = lastUpdateTm;
	}

	public String getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(String lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

	public String getLastUpdateId() {
		return lastUpdateId;
	}

	public void setLastUpdateId(String lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void assignRole(Role role) {
		if (!this.assignedRoles.contains(role)) {
			this.assignedRoles.add(role);
			role.getIncludedUsers().add(this);
		}
	}

	public void unassignRole(Role role) {
		if (this.assignedRoles.contains(role)) {
			this.assignedRoles.remove(role);
			role.getIncludedUsers().remove(this);
		}
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	
}
