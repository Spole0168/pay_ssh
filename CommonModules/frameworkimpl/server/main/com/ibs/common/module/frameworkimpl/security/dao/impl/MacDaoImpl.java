package com.ibs.common.module.frameworkimpl.security.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;

import com.ibs.common.module.frameworkimpl.security.dao.IMacDao;
import com.ibs.common.module.frameworkimpl.security.domain.Mac;
import com.ibs.common.module.frameworkimpl.security.dto.MacDto;
import com.ibs.common.module.frameworkimpl.security.dto.MacInfoDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class MacDaoImpl extends BaseEntityDao<Mac> implements IMacDao{

	public Page<MacDto> findMacByPage(QueryPage queryPage, Map<String, String> searchFields) {
		logger.trace("entering dao...");
		
		StringBuffer sqlBuffer = new StringBuffer();
		List<Object> queryList = new ArrayList<Object>();
		
		sqlBuffer.append("select a.MAC_ID as id,a.MAC as mac,a.CPU_ID as cpuId,a.REMARK as remark,STATUS as status, a.RECENTLY_USED_TIME as recentlyUsedTime ");
		sqlBuffer.append("  from pbl.T_PBL_MAC a where 1 = 1 ");
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("mac")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("mac")) + "%");
			queryPage.addQueryCondition("mac", "%" + StringUtils.trim(searchFields.get("mac")) + "%");
			sqlBuffer.append(" and a.MAC like ?");
		}
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("cpuId")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			queryPage.addQueryCondition("cpuId", "%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			sqlBuffer.append(" and a.CPU_ID like ?");
		}
		
		String status = StringUtils.trim(searchFields.get("status"));
		if(StringUtils.isNotEmpty(status)){
			queryList.add(status);
			queryPage.addQueryCondition("status", status);
			sqlBuffer.append(" and a.STATUS = ?");
		}
		
		queryPage.addScalar("id")
				 .addScalar("mac")
				 .addScalar("cpuId")
				 .addScalar("remark")
			     .addScalar("status", Hibernate.BOOLEAN)
			     .addScalar("recentlyUsedTime", Hibernate.DATE);
	
		queryPage.setSqlString(sqlBuffer.toString());
		
		return (Page<MacDto>) super.findPageBySql(queryPage, MacDto.class);
	}

	public Page<MacDto> findMacMenuByPage(QueryPage queryPage,
			Map<String, String> searchFields) {
		logger.trace("entering dao...");
		
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		
		StringBuffer sqlBuffer = new StringBuffer();
		List<Object> queryList = new ArrayList<Object>();
		
		sqlBuffer.append("select a.MAC_ID as id,a.MAC as mac,a.CPU_ID as cpuId,a.REMARK as remark,STATUS as status, a.RECENTLY_USED_TIME as recentlyUsedTime, B.MENU_ID as menuId ");
		sqlBuffer.append("  from pbl.T_PBL_MAC a, (select * from pbl.T_PBL_MAC_MENU where MENU_ID = ?) b where A.MAC_ID = B.MAC_ID(+) ");
		
		queryPage.addQueryCondition("menuId", searchFields.get("menuId"));
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("mac")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("mac")) + "%");
			queryPage.addQueryCondition("mac", "%" + StringUtils.trim(searchFields.get("mac")) + "%");
			sqlBuffer.append(" and a.MAC like ?");
		}
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("cpuId")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			queryPage.addQueryCondition("cpuId", "%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			sqlBuffer.append(" and a.CPU_ID like ?");
		}
		
		String status = StringUtils.trim(searchFields.get("status"));
		if(StringUtils.isNotEmpty(status)){
			queryList.add(status);
			queryPage.addQueryCondition("status", status);
			sqlBuffer.append(" and a.STATUS = ?");
		}
		
		sqlBuffer.append(" order by B.menu_ID");
		
		queryPage.addScalar("id")
				 .addScalar("mac")
				 .addScalar("cpuId")
				 .addScalar("remark")
			     .addScalar("status", Hibernate.BOOLEAN)
			     .addScalar("recentlyUsedTime", Hibernate.DATE)
			     .addScalar("menuId");
	
		queryPage.setSqlString(sqlBuffer.toString());
		
		return (Page<MacDto>) super.findPageBySql(queryPage, MacDto.class);
	}

	public Page<MacDto> findMacUserByPage(QueryPage queryPage,
			Map<String, String> searchFields) {
		logger.trace("entering dao...");
		
		StringBuffer sqlBuffer = new StringBuffer();
		List<Object> queryList = new ArrayList<Object>();
		
		sqlBuffer.append("select distinct a.MAC_ID as id,a.MAC as mac,a.CPU_ID as cpuId,a.REMARK as remark,STATUS as status, a.RECENTLY_USED_TIME as recentlyUsedTime, (select count(MAC_ID) from pbl.T_PBL_MAC_USER where MAC_ID = a.MAC_ID) as userCount ");
		sqlBuffer.append("  from pbl.T_PBL_MAC a, pbl.T_PBL_MAC_USER b where a.MAC_ID=b.MAC_ID(+)");
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("mac")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("mac")) + "%");
			queryPage.addQueryCondition("mac", "%" + StringUtils.trim(searchFields.get("mac")) + "%");
			sqlBuffer.append(" and a.MAC like ?");
		}
		
		if(StringUtils.isNotEmpty(StringUtils.trim(searchFields.get("cpuId")))){
			queryList.add("%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			queryPage.addQueryCondition("cpuId", "%" + StringUtils.trim(searchFields.get("cpuId")) + "%");
			sqlBuffer.append(" and a.CPU_ID like ?");
		}
		
		String status = StringUtils.trim(searchFields.get("status"));
		if(StringUtils.isNotEmpty(status)){
			queryList.add(status);
			queryPage.addQueryCondition("status", status);
			sqlBuffer.append(" and a.STATUS = ?");
		}
		
		String userName = StringUtils.trim(searchFields.get("userName"));
		if(StringUtils.isNotEmpty(userName)){
			queryList.add("%" + userName + "%");
			queryList.add("%" + userName + "%");
			queryPage.addQueryCondition("userName", "%" + userName + "%");
			queryPage.addQueryCondition("userCode", "%" + userName + "%");
			sqlBuffer.append(" and (b.USER_NAME like ? OR b.USER_CODE like ?)");
		}
		
		sqlBuffer.append(" order by userCount desc");
		
		queryPage.addScalar("id")
				 .addScalar("mac")
				 .addScalar("cpuId")
				 .addScalar("remark")
			     .addScalar("status", Hibernate.BOOLEAN)
			     .addScalar("recentlyUsedTime", Hibernate.DATE)
			     .addScalar("userCount", Hibernate.INTEGER);
		
		queryPage.setSqlString(sqlBuffer.toString());
		queryPage.clearSortMap();
		
		return (Page<MacDto>) super.findPageBySql(queryPage, MacDto.class);
	}

	public Mac getMacById(String id) {
		logger.trace("entering dao...");
		
		return super.load(id);
	}

	public Mac getMacByAddress(String mac) {
		logger.trace("entering dao...");
		
		List<Mac> macs = super.findByValue("from Mac t where t.status = 1 and t.mac = ?", new Object[]{mac});
		if (macs != null)
			return macs.get(0);
		
		return null;
	}

	public Mac getMacBy(String mac, String cpuId) {
		logger.trace("entering dao...");
		
		List<Mac> macs = super.findByValue("from Mac t where t.mac = ? and t.cpuId = ?", new Object[]{mac, cpuId});
		if (macs != null && macs.size() > 0)
			return macs.get(0);
		
		return null;
	}
	
	public String saveMac(Mac mac) {
		logger.trace("entering dao...");
		return (String)super.save(mac);
	}

	public void updateMac(Mac mac) {
		logger.trace("entering dao...");
		super.update(mac);
	}

	public void delete(String id) {
		logger.trace("entering dao...");
		
		super.remove(id);
	}

	public List<Mac> getAllValidMacAddress() {
		logger.trace("entering dao...");
		
		List<Mac> list = super.find("from Mac t where t.status = 1");
		
		return list;
	}

	public List<Mac> getMacIdsByMenuId(String menuId) {
		logger.trace("entering dao...");
		
		List<Object> args = new ArrayList<Object>();
		List<ColumnType> scalarList = new ArrayList<ColumnType>();
		args.add(menuId);
		
		String sql = "select A.MAC_ID as id, A.MAC as mac, A.CPU_ID as cpuId from pbl.T_PBL_MAC A, pbl.T_PBL_MAC_MENU B where A.MAC_ID = B.MAC_ID and B.MENU_ID = ? ";
		
		scalarList.add(new ColumnType("id", Hibernate.STRING));
		scalarList.add(new ColumnType("mac", Hibernate.STRING));
		scalarList.add(new ColumnType("cpuId", Hibernate.STRING));
		
		List<Mac> list = super.findBySql(sql, args, null, scalarList, Mac.class);
		
		return list;
	}

	public List<MacInfoDto> getMacInfosByMenuId(String menuId) {
		logger.trace("entering dao...");
		
		List<Object> args = new ArrayList<Object>();
		args.add(menuId);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select B.MAC_ID as macId, B.MAC as mac, B.CPU_ID as cpuId, C.USER_CODE as userCode, C.ORG_CODE as orgCode ");
		sql.append(" from pbl.t_pbl_mac_menu A, pbl.t_pbl_mac B, pbl.t_pbl_mac_user C");
		sql.append(" where A.MAC_ID = B.MAC_ID and B.MAC_ID = C.MAC_ID(+) and B.STATUS = 1");
		sql.append(" and A.menu_id = ?");
		
		List<ColumnType> scalarList = new ArrayList<ColumnType>();
		scalarList.add(new ColumnType("macId", Hibernate.STRING));
		scalarList.add(new ColumnType("mac", Hibernate.STRING));
		scalarList.add(new ColumnType("cpuId", Hibernate.STRING));
		scalarList.add(new ColumnType("userCode", Hibernate.STRING));
//		scalarList.add(new ColumnType("userName", Hibernate.STRING));
		scalarList.add(new ColumnType("orgCode", Hibernate.STRING));
//		scalarList.add(new ColumnType("orgName", Hibernate.STRING));
		
		List<MacInfoDto> list = super.findBySql(sql.toString(), args, null, scalarList, MacInfoDto.class);
		
		return list;
	}

}
