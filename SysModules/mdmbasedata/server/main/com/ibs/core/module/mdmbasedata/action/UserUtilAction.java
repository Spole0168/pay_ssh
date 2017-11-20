package com.ibs.core.module.mdmbasedata.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.security.dto.UserSimpleInfo;
import com.ibs.core.module.mdmbasedata.biz.UserQueryBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.util.StringUtils;

public class UserUtilAction extends CrudBaseAction {


	private static final long serialVersionUID = -3342933906069355842L;
	private UserQueryBiz userQueryBiz;
	private Map<String, Object> jsonResult = new HashMap<String, Object>();
//	private String orgCode;
//	private String needSubOrg;
	private String userTypes;
	private String userStatus;
	private Integer recordsSize;
	private String searchVar;
 

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

//	public String getOrgCode() {
//		return orgCode;
//	}
//
//	public void setOrgCode(String orgCode) {
//		this.orgCode = orgCode;
//	}

//	public String getNeedSubOrg() {
//		return needSubOrg;
//	}
//
//	public void setNeedSubOrg(String needSubOrg) {
//		this.needSubOrg = needSubOrg;
//	}

 

	public Integer getRecordsSize() {
		return recordsSize;
	}

	public void setRecordsSize(Integer recordsSize) {
		this.recordsSize = recordsSize;
	}

	public String getSearchVar() {
		return searchVar;
	}

	public void setSearchVar(String searchVar) {
		this.searchVar = searchVar;
	}

	@Override
	public String create() {
		return null;
	}

	@Override
	public String delete() {
		return null;
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
		return null;
	}

	@Override
	public String saveOrUpdate() {
		return null;
	}

	@Override
	public String search() {
		return null;
	}
 

	public String autoCompleteUser() {
		logger.trace("enter " + this.getClass().getSimpleName()
				+ " method autoCompleteEmployee");
		IUser user;
		String[] typeArray = {};
		String[] statusArray = {};
		Boolean needsub = false;
		try {
			user = getCurrentUser();
		} catch (Exception e) {
			logger.error("无法找到用户信息");
			return ERROR;
		}
//		if (null == orgCode || "".equals(orgCode)) {
//			orgCode = user.getBelongToOrgCode();
//		}

		if (null == recordsSize || recordsSize.intValue() <= 0) {
			recordsSize = new Integer(10); // 默认为10个每次
		}

		if (null != userTypes && userTypes.length() > 0) {
			typeArray = StringUtils.split(userTypes, Constants.INFOR_SPLIT);
		}
		if (null != userStatus && userStatus.length() > 0) {
			statusArray = StringUtils.split(userStatus, Constants.INFOR_SPLIT);
		}

//		if (null != needSubOrg && "Y".equalsIgnoreCase(needSubOrg)) {
//			needsub = true;
//		}

		List<UserSimpleInfo> users = userQueryBiz
				.listUser(typeArray,statusArray,
						searchVar, recordsSize);
		jsonResult.put("users", users);
		return AJAX_RETURN_TYPE;
	}

	public UserQueryBiz getUserQueryBiz() {
		return userQueryBiz;
	}

	public void setUserQueryBiz(UserQueryBiz userQueryBiz) {
		this.userQueryBiz = userQueryBiz;
	}

	public String getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
