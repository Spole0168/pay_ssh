package com.ibs.core.module.mdmintbasedata.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.dao.IForwarderDao;
import com.ibs.core.module.mdmintbasedata.domain.Forwarder;
import com.ibs.core.module.mdmintbasedata.domain.ForwarderSimple;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class ForwarderDaoImpl extends BaseEntityDao<Forwarder> implements
		IForwarderDao {
	final static String checkData = "select lpad((substr(nvl(max(CODE),'IA999999000000'),9,6)+1),6,'0') "
			+ "as code FROM pbl.t_MDM_FORWARDER  where  ORG_CODE=?";

	public List<Forwarder> listAll(ClientUpdateDataPack object) {
		
		if (object == null||StringUtils.isEmpty(object.getOrgCode())) {
			return new ArrayList<Forwarder>();
		}
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		hql.append(" from Forwarder where orgCode =?"); 
		args.add(object.getOrgCode()); 
		if (object.getVersionNo() != null) {
			hql.append(" and versionNo > ?") ;
			args.add(object.getVersionNo()); 
		} 
		return super.find(hql.toString(), args); 
	}

	public String save(Forwarder forwarder) {
		logger.trace("enter Dao...");
		forwarder.setVersionNo(System.currentTimeMillis());
		return (String) super.save(forwarder);
	}

	public void saveOrUpdate(Forwarder forwarder) {
		logger.trace("enter Dao...");
		forwarder.setVersionNo(System.currentTimeMillis());
		super.update(forwarder);
	}

  
	public Forwarder getForwarderByCode(String forwarderCode) {
		logger.trace("enter Dao...");
		StringBuffer hql = new StringBuffer();
		hql.append("from Forwarder where code=? and status='").append(
				Constants.STATUS_VALID).append("' order by name");
		List<Forwarder> list = super.findByValue(hql.toString(), forwarderCode);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Forwarder> list(String orgCode,String forwarderCode, String forwarderName,
			String status,String used) {
		logger.trace("entering dao...");
		StringBuffer hql = new StringBuffer();
		 
		hql.append("from Forwarder where 1=1");
		List<Object> args = new ArrayList<Object>(0);
		if (!StringUtils.isEmpty(orgCode)) {
			hql.append(" and orgCode = ?");
			args.add(  orgCode  );
		}
		if (!StringUtils.isEmpty(status)) {
			hql.append(" and status = ?");
			args.add(status);
		}
		if (!StringUtils.isEmpty(used)) {
			hql.append(" and used = ?");
			args.add(used);
		}
		
		if (!StringUtils.isEmpty(forwarderCode)) {
			hql.append(" and code like ?");
			args.add("%" + forwarderCode + "%");
		}
		if (!StringUtils.isEmpty(forwarderName)) {
			hql.append(" and name like ?");
			args.add("%" + forwarderName + "%");
		}
		return super.findByValue(hql.toString(), args.toArray());
	}

	public Forwarder load(String id) {
		logger.trace("enter Dao...");
		return super.load(id);
	}

	public void delete(String id) {
		logger.trace("enter Dao...");
		super.remove(id);
	}

	public List<Forwarder> listAll() {
		logger.trace("enter Dao...");
		StringBuffer hql = new StringBuffer();
		hql.append(" from Forwarder where status = '").append(
				Constants.STATUS_VALID).append("' order by name");
		return super.find(hql.toString());
	}

	public IPage<Forwarder> findForwarderByPage(QueryPage queryPage) {
		logger.trace("entering Dao...");
		return super.findPageBy(queryPage);
	}

	public String genUserCode(String orgCode) { 
		List<Object> args1 = new ArrayList<Object>();
		args1.add(orgCode);
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		ColumnType col0 = new ColumnType("code", Hibernate.STRING);
		scallarList.add(col0);
		List<Forwarder> alist = super.findBySql(checkData, args1, null,
				scallarList, Forwarder.class);
		if (alist == null || alist.size() == 0) {
			return null;
		} else {
			Forwarder object = alist.get(0);
			return "IA"+orgCode+object.getCode();

		}
	}

	public  List<ForwarderSimple> findUniversal(
			  String orgCode,
			String orgCodeOrNameOrPinYin, Integer records, String statusValid,
			String inused) {

		if (logger.isTraceEnabled()) {
			logger.trace("entering dao...");
		}
 
		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		sql
				.append("select ID as id ,CODE as code,NAME as name,ORG_CODE as orgCode  "
						+ " from pbl.t_MDM_FORWARDER where 1=1 ");

		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		ColumnType col0 = new ColumnType("id", Hibernate.STRING);
		ColumnType col1 = new ColumnType("code", Hibernate.STRING);
		ColumnType col2 = new ColumnType("name", Hibernate.STRING);
		ColumnType col3 = new ColumnType("orgCode", Hibernate.STRING);
		if (!StringUtils.isEmpty(statusValid)) {
			sql.append(" and status = ?");
			args.add(  statusValid  );
		}
		
		if (!StringUtils.isEmpty(inused)) {
			sql.append(" and used = ?");
			args.add(  inused  );
		}
		
		if (!StringUtils.isEmpty(orgCode)) {
			sql.append(" and ORG_CODE = ?");
			args.add(  orgCode  );
		}
		
		 
		if (StringUtils.isNotEmpty(orgCodeOrNameOrPinYin)) {
			sql
					.append(" and (code like ? or name like ? )");
			args.add("%" + orgCodeOrNameOrPinYin.toUpperCase().trim() + "%");
			args.add("%" + orgCodeOrNameOrPinYin.trim() + "%");  
		} 
		sql.append(" order by code asc"); 
		scallarList.add(col0);
		scallarList.add(col1);
		scallarList.add(col2);
		scallarList.add(col3);  
		args.add(records);
		return super.findBySql("select * from (" + sql.toString()
				+ ")   where rownum <= ?", args, null, scallarList,
				ForwarderSimple.class );
	}
	
	public IPage<Forwarder> findForwarderNotInGroup(String operateOrgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap) {

		List<Object> args = new ArrayList<Object>(0);

		StringBuffer sql = new StringBuffer();
		sql
				.append("select  o.id as id,o.code as code ,o.name as name  from pbl.t_mdm_forwarder o left join  "
						+ " (select distinct ogo.forwarder_id fid from pbl.t_mdm_forwarder_group_forwarde ogo "
						+ "left join pbl.t_mdm_forwarder_group og on ogo.forwarder_group_id=og.id  where "
						+ "og.price_type_id=? and og.org_code=? and  "
						+ " ogo.status=? and og.status=? ) n   on o.id = n.fid where n.fid is null and o.status=? and o.used=? and o.org_code=? ");

		args.add(priceType);
		args.add(operateOrgCode);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.INUSED);
		args.add(operateOrgCode);
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		ColumnType col0 = new ColumnType("id", Hibernate.STRING);
		ColumnType col1 = new ColumnType("code", Hibernate.STRING);
		ColumnType col2 = new ColumnType("name", Hibernate.STRING); 
		scallarList.add(col0);
		scallarList.add(col1);
		scallarList.add(col2); 

		return super.findPageBySql(sql.toString(), args, pageSize, pageIndex,
				sortMap, scallarList, Forwarder.class);

	 

	}

}
