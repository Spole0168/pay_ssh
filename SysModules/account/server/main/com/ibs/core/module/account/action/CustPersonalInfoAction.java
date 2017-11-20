package com.ibs.core.module.account.action;


import com.ibs.core.module.account.biz.impl.CustPersonalInfoBizImpl;
import com.ibs.core.module.account.domain.CustPersonalInfo;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
@SuppressWarnings("serial")
public class CustPersonalInfoAction extends CrudBaseAction{

	private CustPersonalInfoBizImpl custPersonalInfoBizImpl;
	private CustPersonalInfo custPersonalInfo;
	private boolean judge_custpersonal;
	private String id;
	private IDataDictService dataDictService;
	private String realname;
	private String custLevel;
	private String custStatus;
	private String country;
	private String certType;
	
	public String findByCustid(){
		
		custPersonalInfo = custPersonalInfoBizImpl.findByCustid(id);
		if(custPersonalInfo != null){
			realname = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__CERTIFICATION_LEVEL, custPersonalInfo.getRealNamelevel());
			custLevel = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__CUST_LEVEL, custPersonalInfo.getCustLevel());
			custStatus = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__CNL_CUST_STATUS, custPersonalInfo.getCustStatus());
			country = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__COUNTRY, custPersonalInfo.getCountry());
			certType = dataDictService.getCodeName(IDataDictService.DATA_DICT_TYPE__CERT_TYPE, custPersonalInfo.getCertType());
			judge_custpersonal = true;
		}else{
			judge_custpersonal = false;
		}
		return SUCCESS;
	}


	@Override
	public String create() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String saveOrUpdate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String search() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String export() {
		// TODO Auto-generated method stub
		return null;
	}
	public CustPersonalInfoBizImpl getCustPersonalInfoBizImpl() {
		return custPersonalInfoBizImpl;
	}
	public void setCustPersonalInfoBizImpl(CustPersonalInfoBizImpl custPersonalInfoBizImpl) {
		this.custPersonalInfoBizImpl = custPersonalInfoBizImpl;
	}
	public CustPersonalInfo getCustPersonalInfo() {
		return custPersonalInfo;
	}
	public void setCustPersonalInfo(CustPersonalInfo custPersonalInfo) {
		this.custPersonalInfo = custPersonalInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isJudge_custpersonal() {
		return judge_custpersonal;
	}
	public void setJudge_custpersonal(boolean judge_custpersonal) {
		this.judge_custpersonal = judge_custpersonal;
	}
	public IDataDictService getDataDictService() {
		return dataDictService;
	}
	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustStatus() {
		return custStatus;
	}
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


	public String getCertType() {
		return certType;
	}


	public void setCertType(String certType) {
		this.certType = certType;
	}
	
	
}
