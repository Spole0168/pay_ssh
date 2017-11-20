package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.ibs.common.module.frameworkimpl.security.dao.IMacUserDao;
import com.ibs.common.module.frameworkimpl.security.domain.MacUser;
import com.ibs.common.module.frameworkimpl.security.dto.UserDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class MacUserDao extends BaseEntityDao<MacUser> implements IMacUserDao{

	public Page<MacUser> findMacUsersByPage(QueryPage queryPage, Map<String, String> searchFields) {
		logger.trace("entering dao...");
		StringBuffer sqlBuffer = new StringBuffer();
		List<Object> queryList = new ArrayList<Object>();
		
		sqlBuffer.append("select ID as id");
		sqlBuffer.append("		,MAC_ID as macId");
		sqlBuffer.append("		,USER_CODE as userCode");
		sqlBuffer.append("		,USER_NAME as userName");
		sqlBuffer.append("		,ORG_CODE as orgCode");
		sqlBuffer.append("		,ORG_NAME as orgName");
		sqlBuffer.append("	from pbl.T_PBL_MAC_USER");
		sqlBuffer.append("	  where 1=1 ");
		
		String macId = searchFields.get("macId");
		if(StringUtils.isNotEmpty(macId)){
			queryList.add(macId);
			queryPage.addQueryCondition("macId", macId);
			sqlBuffer.append(" and MAC_ID = ?");
		}
		queryPage.addScalar("id")
				 .addScalar("macId")
				 .addScalar("userCode")
				 .addScalar("userName")
				 .addScalar("orgCode")
				 .addScalar("orgName");
	
		queryPage.setSqlString(sqlBuffer.toString());
		
		return (Page<MacUser>) super.findPageBySql(queryPage, MacUser.class);
	}

	public List<MacUser> getUsersByMacId(String macId) {
		logger.trace("entering dao...");
		
		List<Object> args = new ArrayList<Object>();
		List<ColumnType> scalarList = new ArrayList<ColumnType>();
		args.add(macId);
		
		String sql = "select USER_CODE as userCode from pbl.T_PBL_MAC_USER where MAC_ID = ? ";
		
		scalarList.add(new ColumnType("userCode", Hibernate.STRING));
		
		List<MacUser> list = super.findBySql(sql, args, null, scalarList, MacUser.class);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public Page<UserDto> findUsersByPage(QueryPage queryPage, Map<String, String> searchFields) {
		logger.trace("entering dao...");
		
		List<Object> args = new ArrayList<Object>();
		
		String sql = "select D.CODE as id" +
	      			  "     ,A.USER_NAME as userName" +
	      			  "     ,D.NAME as displayName" +
				      "     ,C.NAME as orgName" +
				      "     ,C.CODE as orgCode" +
				      " from pbl.T_MDM_USER A" +
				      "     ,pbl.T_MDM_USER_ORG B" +
				      "     ,pbl.T_MDM_ORG C" +
				      "     ,pbl.T_MDM_EMPLOYEE D" +
				      "  where A.USER_ID = B.USER_ID" +
				      "    and B.ORG_ID = C.ID" +
	      			  "    and A.USER_ID = D.ID(+)" +
	      			  "    and B.STATUS = 'VALID' and D.STATUS = 'VALID'";
		
		String userName = StringUtils.trim(searchFields.get("userName"));
		if(StringUtils.isNotEmpty(userName)){
			args.add("%" + userName + "%");
			queryPage.addQueryCondition("userName", "%" + userName + "%");
			sql += " and A.USER_NAME like ?";
		}
		
		String orgName = StringUtils.trim(searchFields.get("orgName"));
		if(StringUtils.isNotEmpty(orgName)){
			args.add("%" + orgName + "%");
			queryPage.addQueryCondition("orgName", "%" + orgName + "%");
			sql += " and C.NAME like ?";
		}
		
		String orgCode = StringUtils.trim(searchFields.get("orgCode"));
		if(StringUtils.isNotEmpty(orgCode)){
			args.add("%" + orgCode + "%");
			queryPage.addQueryCondition("orgCode", "%" + orgCode + "%");
			sql += " and C.CODE like ?";
		}
		
		queryPage.addScalar("id", Hibernate.STRING)
				 .addScalar("userName", Hibernate.STRING)
				 .addScalar("displayName", Hibernate.STRING)
		         .addScalar("orgName", Hibernate.STRING)
		         .addScalar("orgCode", Hibernate.STRING);
		
		queryPage.setSqlString(sql);
		return (Page<UserDto>) super.findPageBySql(queryPage, UserDto.class);
	}

	public void deleteAllBindingUserByMacId(final String macId) {
		logger.trace("Entering dao...");
		
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				String sql = "delete from pbl.T_PBL_MAC_USER where MAC_ID = ?";
				session.createSQLQuery(sql).setString(0, macId).executeUpdate();
				return null;
			}
		});
	}
	
	public String save(MacUser macUser){
		logger.trace("Entering dao...");
		
		return (String) super.save(macUser);
	}

	public MacUser getUserByUserId(String userId) {
		logger.trace("entering dao...");
		
		List<Object> args = new ArrayList<Object>();
		List<ColumnType> scalarList = new ArrayList<ColumnType>();
		args.add(userId);
		
		String sql = "select D.CODE as userCode" +
				      "     ,A.USER_NAME as userName" +
				      "     ,C.NAME as orgName" +
				      "     ,C.CODE as orgCode" +
				      " from pbl.t_MDM_USER A" +
				      "     ,pbl.t_MDM_USER_ORG B" +
				      "     ,pbl.t_MDM_ORG C" +
				      "     ,pbl.t_MDM_EMPLOYEE D" +
				      "  where A.USER_ID = B.USER_ID" +
				      "    and B.ORG_ID = C.ID" +
	      			  "    and A.USER_ID = D.ID(+)" +
	      			  "    and B.STATUS = 'VALID' and D.STATUS = 'VALID'" +
				      "    and D.CODE = ?";
		
		scalarList.add(new ColumnType("userCode", Hibernate.STRING));
		scalarList.add(new ColumnType("userName", Hibernate.STRING));
		scalarList.add(new ColumnType("orgName", Hibernate.STRING));
		scalarList.add(new ColumnType("orgCode", Hibernate.STRING));
		
		List<MacUser> list = super.findBySql(sql, args, null, scalarList, MacUser.class);
		
		if(null == list){
			return new MacUser();
		}else if(list.size() == 1){
			return list.get(0);
		}else{
			return new MacUser();
		}
	}

	public MacUser getMacUserBy(String macId, String userCode) {
		logger.trace("entering dao...");
		
		String hql = "from MacUser where macId = ? and userCode = ?";

		List<Object> args = new ArrayList<Object>();
		args.add(macId);
		args.add(userCode);
		
		List<MacUser> list = super.findByValue(hql, new Object[]{macId, userCode});
		
		if (list == null || list.size() == 0)
			return null;
		
		return list.get(0);
	}
}