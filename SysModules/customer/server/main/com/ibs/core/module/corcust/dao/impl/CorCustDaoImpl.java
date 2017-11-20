/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.corcust.dao.impl;

import java.util.List;

import com.ibs.core.module.corcust.dao.ICorCustDao;
import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.domain.CorCustPersonal;
import com.ibs.core.module.corcust.dto.Cor;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustDaoImpl extends BaseEntityDao<CorCust> implements
		ICorCustDao {

	public IPage<CorCust> findCorCustByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustDaoImpl.findCorCustByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorCust load(String id) {
		logger.info("entering action::CorCustDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorCust corCust) {
		logger.info("entering action::CorCustDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corCust);
	}

	@Override
	public CorCust getByCustCode(String custCode)  {
		return super.loadBy("custCode", custCode);
	}
	
	
	//根据id查询单条数据
		public Cor load1(String id) {
			String hql = "select c.id as id,c.custCode as custCode,c.custType as custType,"
					+ "c.country as country,c.localName as localName,c.realNameLeve as realNameLeve,"
					+ "c.firstName as firstName,c.lastName as lastName,c.custLevel as custLevel,"
					+ "c.certType as certType,c.certNum as certNum,c.certExpireDate as certExpireDate,"
					+ "c.status as status,c.regTime as regTime,c.dataSource as dataSource,"
					+ "c.isValid as isValid,c.creator as creator,c.updator as updator,"
					+ "c.createTime as createTime,c.updateTime as updateTime,c.certCopy as certCopy,"
					+ "x.tcid as tcid,x.companyCode as companyCode,x.unitProperty as unitProperty,"
					+ "x.corporateName as corporateName,x.corporateCountryCode as corporateCountryCode,"
					+ "x.corporateCertType as corporateCertType,x.corporateCertNum as corporateCertNum,"
					+ "x.corporateCertCopy as corporateCertCopy,x.corporateCertExpireDate as corporateCertExpireDate,"
					+ "x.corporatePhonenum as corporatePhonenum,x.businessScope as businessScope,x.industry as industry,"
					+ "x.companyRegAddr as companyRegAddr,x.companyFax as companyFax,x.companyWebsite as companyWebsite,"
					+ "x.email as email,d.phoneType as phoneType,d.phoneNum as phoneNum,d.isDefault as isDefault,"
					+ "z.addrType as addrType,z.addr as addr,z.provience as provience,z.city as city,z.district as district,"
					+ "z.postcode as postcode  from CorCust  c,CorCustCompany x,CorCustPhones d,CorCustAddr z  "
					+ "  where c.custCode=x.custCode and x.custCode=d.custCode and x.custCode = z.custCode and c.id='"+id+"'";
				
				List<Cor> list = super.findByHql(hql,null,null,Cor.class);
				return list.isEmpty()?null:list.get(0);
			 
		}

		
		//查询所有
		
		public IPage<Cor> findCorCustByPageAndMoreTable(QueryPage queryPage) {
			String condition = queryPage.getHqlString();
			StringBuffer s = new StringBuffer("select c.id as id,c.custCode as custCode,c.custType as custType,c.country as country,c.localName as localName,c.realNameLeve as realNameLeve,c.firstName as firstName,c.lastName as lastName,c.custLevel as custLevel,c.certType as certType,c.certNum as certNum,c.certExpireDate as certExpireDate,c.status as status,c.regTime as regTime,c.dataSource as dataSource,c.isValid as isValid,c.creator as creator,c.updator as updator,c.createTime as createTime,c.updateTime as updateTime,c.certCopy as certCopy,")
					.append("x.tcid as tcid,x.companyCode as companyCode,x.unitProperty as unitProperty,x.corporateName as corporateName,x.corporateCountryCode as corporateCountryCode,x.corporateCertType as corporateCertType,x.corporateCertNum as corporateCertNum,x.corporateCertCopy as corporateCertCopy,x.corporateCertExpireDate as corporateCertExpireDate,x.corporatePhonenum as corporatePhonenum,x.businessScope as businessScope,x.industry as industry,x.companyRegAddr as companyRegAddr,x.companyFax as companyFax,x.companyWebsite as companyWebsite,x.email as email,  ")
					.append("d.phoneType as phoneType,d.phoneNum as phoneNum,d.isDefault as isDefault   ")
					.append("  from CorCust c,CorCustCompany x,CorCustPhones d  ").append("  where c.custCode=x.custCode and x.custCode=d.custCode  ").append(condition);

			String hql = s.toString();
			System.out.println("hql--------------------" + hql);
			queryPage.setHqlString(hql);
			IPage<Cor> page = findPageByHql(queryPage, Cor.class); 
			
			return page;
		}

		@Override
		public CorCust liad2(String custCode) {
			return super.loadBy("custCode", custCode);
		}

		/**
		 * 使用ID查询对应的custCode
		 */
		@Override
		public String getCustCodeById(String id) {
			return super.load(id).getCustCode();
		}

}
