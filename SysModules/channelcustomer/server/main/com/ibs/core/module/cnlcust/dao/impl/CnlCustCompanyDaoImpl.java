/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnlcust.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.ibs.core.module.cnlcust.dao.ICnlCustCompanyDao;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyAndOtherDto;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustCompanyDaoImpl extends BaseEntityDao<CnlCustCompany> implements
		ICnlCustCompanyDao {

	public IPage<CnlCustCompany> findCnlCustCompanyByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustCompanyDaoImpl.findCnlCustCompanyByPage()...");
		return super.findPageBy(queryPage);
	}

	public CnlCustCompany load(String id) {
		logger.info("entering action::CnlCustCompanyDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlCustCompany cnlCustCompany) {
		logger.info("entering action::CnlCustCompanyDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlCustCompany);
	}

	/**
	 * 根据客户唯一编码获取entity
	 * @param custCode
	 * @return
	 */
	public  CnlCustCompany  getInfoByCnlCustCode(String cnlCustCode){
		
		String  hql="select ccc from  CnlCustCompany ccc where ccc.cnlCustCode=? and ccc.isValid=?";
		
		List<Object> obj=new ArrayList<Object>();
		obj.add(cnlCustCode);
		obj.add(Constants.IS_VALID_VALID);
		
		if("".equals(cnlCustCode)){
			return  null;
		}else{
			List<CnlCustCompany> list=super.find(hql,obj);
			
			
			if(list.size()>0){
				return list.get(0);
			}else{
				
				return  null;
			}
			
		}
	}
	/**
	 * 多表查询返回查询结果（T_CNL_CUST_COMPANY、T_CNL_CUST）
	 */
	@Override
	public IPage<CnlCustCompanyAndOtherDto> findCnlCustCompanyByMoreTable(QueryPage queryPage, String hqlCondition) {
		//生成hql语句
		StringBuffer s = new StringBuffer("select g.cnlSysName as cnlCnlCode,p.cnlCustCode as cnlCustCode,c.cnlCustType as cnlCustType,p.companyName as companyName,p.country as country,c.certType as certType")
				.append(",c.certNum as certNum,c.certExpireDate as certExpireDate,p.createTime as createTime,p.creator as creator,c.custStatus as custStatus")
				.append(",p.companyTel as companyTel from CnlCustCompany p,CnlCust c,CnlCnlCfg g where c.cnlCustCode=p.cnlCustCode and c.cnlCnlCode=g.cnlCnlCode and c.cnlCustType='").append(Constants.CUST_TYPE_ENTERPRISE).append("' and c.isValid='")
				.append(Constants.IS_VALID_VALID).append("' and c.certType='").append(Constants.CERT_TYPE_MERCHANT_LICENSE).append("' ").append(hqlCondition);
		String hql = s.toString();
		//添加hql语句
		queryPage.setHqlString(hql);
		//设置排序条件（按创建时间降序）
		queryPage.addSort("p.createTime", DESC);
		IPage<CnlCustCompanyAndOtherDto> page = findPageByHql(queryPage,CnlCustCompanyAndOtherDto.class);
		return page;	
	}
	/**
	 * 企业客户的明细查询
	 */
	@Override
	public CnlCustCompanyAndOtherDto findDetail(String cnlCustCode) {
		//生成hql语句
		StringBuffer s= new StringBuffer("select g.cnlSysName as cnlCnlCode,c.cnlCustType as cnlCustType,c.cnlCustCode as cnlCustCode,c.createTime as createTime,c.creator as creator,cc.custCode as custCode,c.custStatus as custStatus,cc.companyName as companyName,cc.unitProperty as unitProperty")
				.append(",c.certExpireDate as certExpireDate,cc.corporateName as corporateName,cc.corporateCertType as corporateCertType,cc.corporateCertNum as corporateCertNum,c.certNum as certNum")
				.append(",cc.country as country,cc.provience as provience,cc.city as city,cc.regTime as regTime,cc.companyRegAddr as companyRegAddr,cc.businessScope as businessScope,c.certType as certType")
				.append(",cc.industry as industry,cc.regCapital as regCapital,cc.regCapitalCurrency as regCapitalCurrency,cc.financeContact as financeContact,cc.financeTel as financeTel")
				.append(",cc.finnaceEmail as finnaceEmail,cc.addr as addr,cc.companyTel as companyTel,cc.companyFax as companyFax,cc.postcode as postcode,cc.website as website")
				.append(",c.createTime as createTime")
				.append(" from CnlCustCompany cc,CnlCust c,CnlCnlCfg g where cc.cnlCustCode=? and cc.cnlCustCode=c.cnlCustCode and c.cnlCnlCode=g.cnlCnlCode ");
		String hql = s.toString();
		//创建查询
		//super.getHibernateTemplate().getSessionFactory().openSession().cancelQuery();
		Query query = getSession().createQuery(hql);
				query.setResultTransformer(Transformers.aliasToBean(CnlCustCompanyAndOtherDto.class));
		query.setString(0, cnlCustCode);
		List<CnlCustCompanyAndOtherDto> list = query.list();
		if(list==null||list.size()<1){
			return null;
		}
		CnlCustCompanyAndOtherDto dto = list.get(0);
		//得到银行卡号
		s=new StringBuffer("select b.BANK_CARD_NUM as bankCardNum from COR.T_COR_BANK_ACNT b where b.CNL_CUST_CODE='")
				.append(cnlCustCode).append("'");
		SQLQuery query2 = getSession().createSQLQuery(s.toString());
		List<String> list1 = query2.list();
		if(list1!=null&&list1.size()>0){
			String bankCardNum = list1.get(0);
			//添加银行卡数据
			dto.setBankCardNum(bankCardNum);
		}
		return dto;
	}
	@Override
	public List<CnlCustCompany> getAll() {
		// TODO Auto-generated method stub
		String  hql="from CnlCustCompany  ccc where ccc.isValid=?";
		
		return super.findByValue(hql, Constants.IS_VALID_VALID);
	}
}
