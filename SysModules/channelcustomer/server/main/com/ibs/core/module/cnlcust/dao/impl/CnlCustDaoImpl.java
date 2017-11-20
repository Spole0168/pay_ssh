/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.cnlcust.dao.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.account.domain.CustPersonal;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.exception.DaoException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlCustDaoImpl extends BaseEntityDao<CnlCust> implements
		ICnlCustDao {
	

	@Override
	public void deleteByCnlCustCode(String cnlCustCode) {
		CnlCust cnlCust = super.loadBy("cnlCustCode", cnlCustCode);
		if(cnlCust != null)
			cnlCust.setCustStatus("4");
		super.flush();
	}

	public IPage<CustPersonal> findCnlCustByPage(QueryPage queryPage) {
		logger.info("entering action::CnlCustDaoImpl.findCnlCustByPage()...");
		
		
		//StringBuffer hql = new StringBuffer("select c.cnlCnlCode,c.cnlCustCode,c.cnlCustType,c.custStatus,c.localName,c.englishName,c.country,c.certCopy,c.certNum,c.certExpireDate,c.createTime,c.creator,c.realNameInfoCnl,c.custLevel,cp.phonenum from CnlCust c,CnlCustPersonal cp where c.id='1'");
		String hql = "select c.cnlCnlCode as cnlCnlCode,c.cnlCustCode as cnlCustCode,c.cnlCustType as cnlCustType,"
				+ "c.custStatus as custStatus,c.localName as localName,c.firstName as firstName,c.lastName as lastName,c.country as country,"
				+ "c.certType as certType,c.certNum as certNum,c.certExpireDate as certExpireDate,c.createTime as createTime," 
				+ "c.creator as creator,c.realNameLevel as realNamelevel,c.custLevel as custLevel," 
				+ "cp.phonenum as phonenum from CnlCust as c,CnlCustPersonal as cp where c.cnlCustCode=cp.cnlCustCode";
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		queryPage.setHqlString(hql);
		return super.findPageByHql(queryPage,CustPersonal.class);
	}
	
	
	/*
	 * 根据检索条件得到ipage数据
	 */
	public IPage<CustPersonal> findCnlCustBylike(QueryPage queryPage,String cnlCustCode,String cnlSysName,String cnlCustType,String custStatus,
			String certType,String certNum,String realNamelevel,String custLevel,String createStartTime,String createEndTime) {
		logger.info("entering action::CnlCustDaoImpl.findCnlCustByPage()...");
		StringBuffer sql = new StringBuffer("select c.id as id,c.cnl_cnl_code as cnlCnlCode,c.cnl_cust_code as cnlCustCode,c.cnl_cust_type as cnlCustType,"
				+ "c.cust_status as custStatus,c.local_name as localName,c.first_name as firstName,c.last_name as lastName,c.country as country,"
				+ "c.cert_type as certType,c.cert_num as certNum,c.cert_expire_date as certExpireDate,c.create_time as createTime," 
				+ "c.creator as creator,c.real_name_level as realNamelevel,c.cust_level as custLevel," 
				+ "cp.phonenum as phonenum,ccf.cnl_sys_name as cnlSysName from cnl.t_cnl_cust c,cnl.t_cnl_cust_personal cp,cnl.t_cnl_cnl_cfg ccf "
				+ "where ccf.cnl_cnl_code=c.cnl_cnl_code and c.cnl_cust_code = cp.cnl_cust_code and c.IS_VALID = '"+Constants.IS_VALID_VALID+"' and cp.IS_VALID = '"+Constants.IS_VALID_VALID+"' "
				+ "and ccf.IS_VALID = '"+Constants.IS_VALID_VALID+"' and c.cnl_cust_type = '"+cnlCustType+"'");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			sql.append(" and c.cnl_cust_code = '").append(cnlCustCode).append("'");
		}
		if (StringUtils.isNotEmpty(cnlSysName)) {
			sql.append(" and ccf.cnl_sys_name like '%").append(cnlSysName).append("%'");
		}
		if (StringUtils.isNotEmpty(custStatus)) {
			sql.append(" and c.cust_status = '").append(custStatus).append("'");
		}
		if (StringUtils.isNotEmpty(certType)) {
			sql.append(" and c.cert_type = '").append(certType).append("'");
		}
		if (StringUtils.isNotEmpty(certNum)) {
			sql.append(" and c.cert_num = '").append(certNum).append("'");
		}
		if (StringUtils.isNotEmpty(realNamelevel)) {
			sql.append(" and c.real_name_level = '").append(realNamelevel).append("'");
		}
		if (StringUtils.isNotEmpty(custLevel)) {
			sql.append(" and c.cust_level = '").append(custLevel).append("'");
		}
		if (StringUtils.isNotEmpty(createStartTime)) {
			sql.append(" and to_char(").append("c.create_time").append(",'yyyy-mm-dd hh24:mi:ss')").append(" >= '").append(createStartTime).append("'");
		}
		if (StringUtils.isNotEmpty(createEndTime)) {
			sql.append(" and to_char(").append("c.create_time").append(",'yyyy-mm-dd hh24:mi:ss')").append(" <= '").append(createEndTime).append("'");
		}
		queryPage.clearSortMap();
		queryPage.clearQueryCondition();
		queryPage.setSqlString(sql.toString());
		StringType s = new StringType();
		DbTimestampType d = new DbTimestampType(); 
		queryPage.addScalar("id",s);
		queryPage.addScalar("cnlCnlCode",s);
		queryPage.addScalar("cnlCustCode",s);
		queryPage.addScalar("cnlCustType",s);
		queryPage.addScalar("custStatus",s);
		queryPage.addScalar("localName",s);
		queryPage.addScalar("firstName",s);
		queryPage.addScalar("lastName",s);
		queryPage.addScalar("country",s);
		queryPage.addScalar("certType",s);
		queryPage.addScalar("certNum",s);
		queryPage.addScalar("certExpireDate",d);
		queryPage.addScalar("createTime",d);
		queryPage.addScalar("creator",s);
		queryPage.addScalar("realNamelevel",s);
		queryPage.addScalar("custLevel",s);
		queryPage.addScalar("phonenum",s);
		queryPage.addScalar("cnlSysName",s);
		return super.findPageBySql(queryPage,CustPersonal.class);
	}
	

	public CnlCust load(String id) {
		logger.info("entering action::CnlCustDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CnlCust cnlCust) {
		logger.info("entering action::CnlCustDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(cnlCust);
	}

	/**
	 * 验证渠道客户在此渠道内的姓名、证件号唯一性
	 * 在满足不为无效用户和已注销用户的情况下
	 * @author kaijiezhao
	 */
	@Override
	public List<CnlCust> validateCustInCnl(String cnlCnlCode, String localName, String certNum) {
		logger.info("entering action::CnlCustDaoImpl.validateCustInCnl()...");
		String hql = "from CnlCust cnl where cnl.isValid != ? and cnl.custStatus != ? and cnl.cnlCnlCode = ? and cnl.localName = ? and cnl.certNum = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(Constants.IS_VALID_NOT_VALID);
		args.add(Constants.CNL_CUST_STATUS_WRITTEN_OFF);
		args.add(cnlCnlCode);
		args.add(localName);
		args.add(certNum);
		return super.find(hql, args);
	}

	/**
	 * 根据渠道编号和渠道客户码返回唯一客户
	 * @author kaijiezhao
	 */
	@Override
	public CnlCust findByCnlAndCode(String cnlCustCode, String cnlCnlCode) {
		logger.info("entering action::CnlCustDaoImpl.findByCnlAndCode()...");
		String hql = "from CnlCust cnl where cnl.cnlCustCode = ? and cnl.cnlCnlCode = ? and cnl.isValid=?";
		List<Object> args = new ArrayList<Object>();
		args.add(cnlCustCode);
		args.add(cnlCnlCode);
		args.add(Constants.IS_VALID_VALID);
		List<CnlCust> list = super.find(hql, args);
		if(list.size() == 1)
			return list.get(0);
		return null;
	}

	/**
	 * 根据渠道号和渠道客户证件号码返回唯一客户
	 * @author kaijiezhao
	 */
	@Override
	public CnlCust findByCnlAndCert(String certNum, String cnlCnlCode) {
		logger.info("entering action::CnlCustDaoImpl.findByCnlAndCert()...");
		String hql = "from CnlCust cnl where cnl.certNum = ? and cnl.cnlCnlCode = ? and cnl.custStatus = ?";
		List<Object> args = new ArrayList<Object>();
		args.add(certNum);
		args.add(cnlCnlCode);
		args.add(Constants.CNL_CUST_STATUS_PASSED);
		List<CnlCust> list = super.find(hql, args);
		if(list.size() == 1)
			return list.get(0);
		return null;
	}
	
	
	/**
	 * @author jicheng
	 * 根据渠道客户编号查询客户信息
	 */
	public CnlCust loadByCnlCustCode(String cnlCustCode) {
		  CnlCust cnlCust = super.loadBy("cnlCustCode", cnlCustCode);
		 return cnlCust;
	}
	/**
	 * 渠道业务跟踪中关于客户的查询
	 */
	@Override
	public CnlReqTransDto findPersonAboutCustPersonal(String cnlCustCode) {
		logger.info("entering action::CnlCustDaoImpl.findPersonAboutCustPersonal()...");
		//创建hql语句,查询cnlreqTrans和cnltranstrace
		StringBuffer hql = new StringBuffer();
		//查询客户信息
		hql=new StringBuffer();
		hql.append("select c.custCode as CustCode,c.custStatus as custStatus,c.localName as localName,c.realNameLevel as realNameLevel,p.isValid as isValid,p.regTime as createCustTime,p.updateTime as updateCustTime,p.birthday as birthday")
			.append(",p.gender as gender,p.provience as provience,p.city as city,p.district as district,p.addr as addr,p.postcode as postcode,p.highestEdu as highestEdu,p.industry as industry,p.isMarried as isMarried")
			.append(",p.country as country,p.jobTitle as jobTitle,p.employer as employer,p.phonenum as phonenum,p.workTel as workTel,p.email as email,p.spouseLocalName as spouseLocalName,p.spousePhonenum as spousePhonenum ")
			.append(" from CnlCustPersonal p,CnlCust c where p.cnlCustCode = ? and p.cnlCustCode =c.cnlCustCode");
		
		Query query = getSession().createQuery(hql.toString());
		//设置参数
		query.setString(0, cnlCustCode);
		//查询
		List<CnlReqTransDto> list = query.setResultTransformer(Transformers.aliasToBean(CnlReqTransDto.class)).list();
		if(list==null||list.size()<1){
			return null;
		}
		return list.get(0);
	}

	@Override
	public CnlReqTransDto findCompanyAboutCustCompany(String cnlCustCode) {
		logger.info("entering action::CnlCustDaoImpl.findCompanyAboutCustCompany()...");
		//查询T_CNL_CUST、T_CNL_CUST_COMPANY
		StringBuffer hql = new StringBuffer();
		//查询语句
		hql=new StringBuffer();
		hql.append("select c.custStatus as custStatus,c.custCode as custCode,c.createTime as createTime,c.updateTime as updateTime,cc.companyName as companyName,c.isValid as isValid")
			.append(",cc.unitProperty as unitProperty,c.certType as certType,c.certNum as certNum,c.certExpireDate as certExpireDate,cc.country as country")
			.append(",cc.provience as provience,cc.city as city,cc.district as district,cc.regTime as companyRegTime,cc.corporateName as corporateName")
			.append(",cc.corporateCertType as corporateCertType,cc.companyTel as companyTel,cc.companyFax as companyFax,cc.website as website,cc.postcode as postcode")
			.append(",cc.companyTel as companyTel,cc.companyRegAddr as companyRegAddr,cc.addr as addr,cc.businessScope as businessScope,cc.industry as industry")
			.append(",cc.corporateCertNum as corporateCertNum,cc.regCapital as regCapital,cc.regCapitalCurrency as regCapitalCurrency,cc.financeContact as financeContact,cc.finnaceEmail as finnaceEmail")
			.append(",cc.financeTel as financeTel from CnlCust c,CnlCustCompany cc where c.cnlCustCode=? and c.cnlCustCode=cc.cnlCustCode");
		Query query = getSession().createQuery(hql.toString());
		//添加参数
		query.setString(0, cnlCustCode);
		List<CnlReqTransDto> list = query.setResultTransformer(Transformers.aliasToBean(CnlReqTransDto.class)).list();
		//如果dto2为空,返回dto
		if(list==null||list.size()<1){
			return null;
		}
		return list.get(0);
	}
}
