/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import java.util.List;

import org.hibernate.hql.ast.SqlASTFactory;

import com.ibs.core.module.corcust.dao.ICorCustPersonalDao;
import com.ibs.core.module.corcust.domain.CorCustPersonal;
import com.ibs.core.module.corcust.dto.CorCompound;
import com.ibs.core.module.corcust.dto.CorCondition;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPersonalDaoImpl extends BaseEntityDao<CorCustPersonal> implements
		ICorCustPersonalDao {

	/**
	 * findCorCustPersonalByPage
	 */
	public IPage<CorCustPersonal> findCorCustPersonalByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustPersonalDaoImpl.findCorCustPersonalByPage()...");
		return super.findPageBy(queryPage);
	}

	/**
	 * 根据id获取单条记录
	 */
	public CorCustPersonal load(String id) {
		logger.info("entering action::CorCustPersonalDaoImpl.load()...");
		return super.load(id);
	}

	/**
	 * 执行保存
	 */
	public void saveOrUpdate(CorCustPersonal corCustPersonal) {
		logger.info("entering action::CorCustPersonalDaoImpl.saveOrUpdate()...");
		
		super.saveOrUpdate(corCustPersonal);
	}

	/**
	 * 根据custCode查询单条记录
	 */
	@Override
	public CorCustPersonal getByCustCode(String custCode) {
		return super.loadBy("custCode", custCode);
	}

	/**
	 * 使用联合条件，进行联合查询，将查询记录放入CorCompound对象中
	 * 
	 */
	@Override
	public IPage<CorCompound> findCorCompoundByPage(QueryPage queryPage, CorCondition corCondition) {
		StringBuffer hql = new StringBuffer("");
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		hql.append("select cor.id as id, cor.custCode as custCode, cor.localName as localName,cor.firstName as firstName,cor.lastName as lastName, "
				+ "cor.country as country,cor.certType as certType,cor.certNum as certNum,cor.certExpireDate as certExpireDate, "
				+ "cor.createTime as createTime,cor.creator as creator,cor.dataSource as dataSource,cor.realNameLeve as realNameLeve, "
				+ "cor.custLevel as custLevel,cor.status as status,pho.phoneNum as phoneNum ");
		hql.append("from CorCust cor,CorCustPhones pho ");
		hql.append("where cor.custCode = pho.custCode and pho.isDefault = '1' ");
		if(corCondition.getCustType() != null && corCondition.getCustType() != "")
			//queryPage.addQueryCondition("cor.custType", corCondition.getCustType());
			hql.append(" and cor.custType = " + corCondition.getCustType());
		
		if(corCondition.getCustCode() != null && corCondition.getCustCode() != "")
			//queryPage.addQueryCondition("cor.custCode", corCondition.getCustCode());
			hql.append(" and cor.custCode = " + corCondition.getCustCode());
		
		if(corCondition.getLocalName()!= null && corCondition.getLocalName() != "")
			//queryPage.addQueryCondition("cor.localName", corCondition.getLocalName());
			hql.append(" and cor.localName = " + corCondition.getLocalName());
		
		if(corCondition.getCertType() != null && corCondition.getCertType() != "")
			//queryPage.addQueryCondition("cor.certType", corCondition.getCertType());
			hql.append(" and cor.certType = " + corCondition.getCertType());
		
		if(corCondition.getCertNum() != null && corCondition.getCertNum() != "")
			
			hql.append(" and cor.certNum = " + corCondition.getCertNum());
		
		if(corCondition.getStatus() != null && corCondition.getStatus() != "")
			hql.append(" and cor.status = " + corCondition.getStatus());
		
		if(corCondition.getRealNameLeve() != null && corCondition.getRealNameLeve() != "")
			hql.append(" and cor.realNameLeve = " + corCondition.getRealNameLeve());
		
		if(corCondition.getCustLevel() != null && corCondition.getCustLevel() != "")
			hql.append(" and cor.custLevel = " + corCondition.getCustLevel());
		
		if(corCondition.getPhoneNum() != null && corCondition.getPhoneNum() != "")
			hql.append(" and pho.phoneNum = " + corCondition.getPhoneNum());
		
		if(corCondition.getCreateTimeStart() != null && corCondition.getCreateTimeStart() != "")
			hql.append(" and cor.createTime >= to_date('" + corCondition.getCreateTimeStart() + "','yyyy-mm-dd hh24:mi:ss')");
		
		if(corCondition.getCreateTimeEnd() != null && corCondition.getCreateTimeEnd() != "")
			hql.append(" and cor.createTime <= to_date('" + corCondition.getCreateTimeEnd() + "','yyyy-mm-dd hh24:mi:ss')");
		
		
		System.out.println(hql);
		queryPage.setHqlString(hql.toString());
		queryPage.setDtoClass(CorCompound.class);
		
		IPage<CorCompound> page = findPageByHql(queryPage);
		return page;
	}


}
