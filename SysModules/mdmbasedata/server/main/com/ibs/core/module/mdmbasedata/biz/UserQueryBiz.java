package com.ibs.core.module.mdmbasedata.biz;

import java.util.List;

import com.ibs.common.module.frameworkimpl.security.dto.UserSimpleInfo;

public interface UserQueryBiz {
	
	/**
	 * 查询用户信息 
	 * @param orgCode 员工所属组织机构code
	 * @param needSubOrg  包含下属组织用户
	 * @param  userTypes[] 用户类型
	 * @param  status[] 状态
	 * @param userCodeOrNameOrPinYin 用户登录名或者名字或者拼音
	 * @param recordsSize 需要返回列表的记录数，默认为10
	 * @return   List<UserSimpleInfo>  
	 */
  public List<UserSimpleInfo> listUser(String[] userTypes, String[] status,
			String userCodeOrNameOrPinYin, Integer recordsSize);
}
