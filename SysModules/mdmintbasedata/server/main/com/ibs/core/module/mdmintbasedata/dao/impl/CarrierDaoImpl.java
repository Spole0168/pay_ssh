package com.ibs.core.module.mdmintbasedata.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.ClientUpdateDataPack;
import com.ibs.core.module.mdmintbasedata.dao.ICarrierDao;
import com.ibs.core.module.mdmintbasedata.domain.Carrier;
import com.ibs.core.module.mdmintbasedata.domain.CarrierSimple;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class CarrierDaoImpl extends BaseEntityDao<Carrier> implements
		ICarrierDao {

	public List<Carrier> listAll(ClientUpdateDataPack object) { 
	 
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		hql.append(" from Carrier where code<>? ");  
		args.add(Constants.NO_CARRIER_CODE);
		if (object.getVersionNo() != null) {
			hql.append(" and versionNo > ?") ;
			args.add(object.getVersionNo()); 
		} 
		return super.find(hql.toString(), args); 
	}

	public List<Carrier> findAll() {
		logger.trace("enter carrierDao...");
		return super.findAll();
	}

	public Carrier getCarrierByName(String carrierName) {
		logger.trace("enter carrierDao...");
		StringBuffer hql = new StringBuffer();
		hql.append("from Carrier where name=? and status='").append(Constants.STATUS_VALID).append("'");
		List<Carrier>  list=super.findByValue(hql.toString(), carrierName);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public Carrier getCarrierByCode(String carrierCode) {
		logger.trace("enter carrierDao...");
		StringBuffer hql = new StringBuffer();
		hql.append("from Carrier where code=? and status='").append(Constants.STATUS_VALID).append("'");
		List<Carrier>  list=super.findByValue(hql.toString(), carrierCode);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public List<Carrier> list(String carrierCode,String carrierName,String status) {
		logger.trace("entering dao...");
		StringBuffer hql = new StringBuffer();
		hql.append(" from Carrier where 1=1 ") ;
		List<Object> args = new ArrayList<Object>(0);

		if (!StringUtils.isEmpty(status)) {
			hql.append(" and status = ?");
			args.add(status );
		}
		if (!StringUtils.isEmpty(carrierCode)) {
			hql.append(" and code like ?");
			args.add("%" + carrierCode + "%");
		}
		if (!StringUtils.isEmpty(carrierName)) {
			hql.append(" and name like ?");
			args.add("%" + carrierName + "%");
		}
		return super.findByValue(hql.toString(), args.toArray());
	}

	public String save(Carrier carrier) {
		logger.trace("enter carrierDao...");
		carrier.setVersionNo(System.currentTimeMillis());
		return (String) super.save(carrier);
	}

	public void saveOrUpdate(Carrier carrier) {
		logger.trace("enter carrierDao...");
		carrier.setVersionNo(System.currentTimeMillis());
		super.saveOrUpdate(carrier);
	}

	public Carrier load(String id) {
		logger.trace("enter carrierDao...");
		return super.load(id);
	}

	public void delete(String id) {
		logger.trace("enter carrierDao...");
		super.remove(id);
	}

	public List<Carrier> listAll() {
		logger.trace("enter carrierDao...");
		StringBuffer hql = new StringBuffer();
		hql.append(" from Carrier where status='")
				.append(Constants.STATUS_VALID).append("' order by name");
		return super.find(hql.toString());
	}

	public IPage<Carrier> findCarrierByPage(QueryPage queryPage) {
		logger.trace("enter carrierDao...");
		return super.findPageBy(queryPage);
	}

	public List<CarrierSimple> findUniversal(String orgCodeOrNameOrPinYin,
			Integer records, String statusValid) {

		if (logger.isTraceEnabled()) {
			logger.trace("entering dao...");
		} 
		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<Object>(0);
		sql
				.append("select ID as id ,CODE as code,NAME as name   "
						+ " from pbl.t_MDM_CARRIERS where 1=1 ");

		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		ColumnType col0 = new ColumnType("id", Hibernate.STRING);
		ColumnType col1 = new ColumnType("code", Hibernate.STRING);
		ColumnType col2 = new ColumnType("name", Hibernate.STRING);
	 
		if (!StringUtils.isEmpty(statusValid)) {
			sql.append(" and status = ?");
			args.add(  statusValid  );
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
		 
		args.add(records);
		return super.findBySql("select * from (" + sql.toString()
				+ ")   where rownum <= ?", args, null, scallarList,
				CarrierSimple.class );
	}

	 
	public IPage<Carrier> findCarrierNotInGroup(String operateOrgCode,
			String priceType, int pageSize, int pageIndex,
			Map<String, String> sortMap) {


		List<Object> args = new ArrayList<Object>(0);

		StringBuffer sql = new StringBuffer();
		sql
				.append("select  o.id as id,o.code as code ,o.name as name  from pbl.t_mdm_carriers o left join  "
						+ " (select distinct ogo.carrier_id fid from pbl.t_mdm_carrier_group_carrier ogo "
						+ "left join pbl.t_mdm_carrier_group og on ogo.carrier_group_id=og.id  where "
						+ "og.price_type_id=? and og.org_code=? and  "
						+ " ogo.status=? and og.status=? ) n   on o.id = n.fid where n.fid is null and o.status=? and o.used=?  ");

		args.add(priceType);
		args.add(operateOrgCode);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.STATUS_VALID);
		args.add(Constants.INUSED); 
		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		ColumnType col0 = new ColumnType("id", Hibernate.STRING);
		ColumnType col1 = new ColumnType("code", Hibernate.STRING);
		ColumnType col2 = new ColumnType("name", Hibernate.STRING); 
		scallarList.add(col0);
		scallarList.add(col1);
		scallarList.add(col2); 
		return super.findPageBySql(sql.toString(), args, pageSize, pageIndex,
				sortMap, scallarList, Carrier.class);
	
	}

}
