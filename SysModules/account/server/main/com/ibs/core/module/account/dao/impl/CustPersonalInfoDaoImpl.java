package com.ibs.core.module.account.dao.impl;

import java.util.List;

import com.ibs.core.module.account.dao.ICustPersonalInfoDao;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class CustPersonalInfoDaoImpl extends BaseEntityDao<CustPersonalInfo> implements
							ICustPersonalInfoDao {
	/*
	 * 
	 * 根据cnl_cust的ID查看渠道个人客户详情
	 * */
	public CustPersonalInfo findByCustid(String id){
		logger.debug("entering dao::CustPersonalInfoDaoImpl.findByCustid(String id)..."+id);
		CustPersonalInfo custlist = null;
		String hql = "select c.cnlCustCode as cnlCustCode,c.localName as localName,cp.birthday as birthday,cp.gender as gender,"
				+ "cp.isMarried as isMarried,c.country as country,cp.provience as provience,cp.city as city,"
				+ "cp.addr as addr,cp.postcode as postcode,cp.highestEdu as highestEdu,cp.industry as industry,"
				+ "cp.jobTitle as jobTitle,cp.employer as employer,cp.phonenum as phonenum,cp.workTel as workTel,"
				+ "cp.email as email,c.cnlCnlCode as cnlCnlCode,cp.spouseLocalName as spouseLocalName,cp.spouseCertNum as spouseCertNum,"
				+ "c.realNameLevel as realNamelevel,c.custLevel as custLevel,c.custStatus as custStatus,cba.bankCardNum as bankCardNum,ccf.cnlSysName as cnlSysName,c.certType as certType,"
				+ "c.certNum as certNum,c.certExpireDate as certExpireDate,c.createTime as createTime,c.creator as creator,cp.name as name,c.firstName as firstName,c.lastName as lastName "
				+ "from CnlCust as c,CnlCnlCfg as ccf,CnlCustPersonal as cp,CorBankAcnt as cba where c.cnlCnlCode = ccf.cnlCnlCode and c.cnlCustCode=cp.cnlCustCode "
				+ "and cba.cnlCustCode = c.cnlCustCode and cba.isValid = '"+Constants.IS_VALID_VALID+"' "
				+ "and cp.isValid = '"+Constants.IS_VALID_VALID+"' and ccf.isValid = '"+Constants.IS_VALID_VALID+"' and c.isValid = '"+Constants.IS_VALID_VALID+"' and c.id='"+id+"'";
		
		List<CustPersonalInfo> list = super.findByHql(hql,null,null,CustPersonalInfo.class);
		if(list.size() > 0){
			for(CustPersonalInfo cust : list){
				custlist = new CustPersonalInfo(cust.getCnlCustCode(),cust.getLocalName(),cust.getBirthday(),
						cust.getGender(),cust.getIsMarried(),cust.getCountry(),cust.getProvience(),cust.getCity(),
						cust.getAddr(),cust.getPostcode(),cust.getHighestEdu(),cust.getIndustry(),cust.getJobTitle(),
						cust.getEmployer(),cust.getPhonenum(),cust.getWorkTel(),cust.getEmail(),cust.getCnlCnlCode(),
						cust.getSpouseLocalName(),cust.getSpouseCertNum(),cust.getRealNamelevel(),cust.getCustLevel(),
						cust.getCustStatus(),cust.getBankCardNum(),cust.getCnlSysName(),cust.getCertType(),cust.getCertNum(),
						cust.getCertExpireDate(),cust.getCreateTime(),cust.getCreator(),cust.getName(),cust.getFirstName(),cust.getLastName());
			}
		}
		return custlist;
	}
}
