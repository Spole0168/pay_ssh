/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.corcust.biz.ICorCustAddrBiz;
import com.ibs.core.module.corcust.biz.ICorCustBiz;
import com.ibs.core.module.corcust.biz.ICorCustCompanyBiz;
import com.ibs.core.module.corcust.biz.ICorCustPhonesBiz;
import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.domain.CorCustAddr;
import com.ibs.core.module.corcust.domain.CorCustCompany;
import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.core.module.corcust.dto.Cor;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.DateUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CorCustManagerAction extends CrudBaseAction {

	private ICorCustBiz corCustBiz;
	private ICorCustCompanyBiz corCustCompanyBiz;
	private ICorCustAddrBiz corCustAddrBiz;
	private ICorCustPhonesBiz corCustPhonesBiz;
	private CorCust corCust;
	private String stats;
	private String id;
	private Cor cor;
	
	private IDataDictBiz dataDictBiz;
	
	private List<OptionObjectPair> custTypeList;
	private List<OptionObjectPair> certTypeList;
	private List<OptionObjectPair> statusList;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CorCustManagerAction.list()...");
		load();
		return SUCCESS;
	}
	private void load() {
		custTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		certTypeList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		statusList = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CUST_STATUS);
	}
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CorCustManagerAction.create()...");
		return SUCCESS;
	}
	
/*	public String handleAudit() {
		logger.info("entering action::CorCustManagerAction.handleAudit()...");
		corCustBiz.getCorCustById(id);
		return SUCCESS;
	}*/

	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CorCustManagerAction.modify()...");
		// corCust = corCustBiz.getCorCustById(id);
		cor = corCustBiz.load1(id);
		return SUCCESS;
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CorCustManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		// setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		/*
		 * Page<CorCust> result = (Page<CorCust>)
		 * corCustBiz.findCorCustByPage(queryPage); setResult(result);
		 */
		// 添加条件
		String searchCondition = getQueryCondition();
		queryPage.setHqlString(searchCondition);
		// queryPage.setHqlString(getQueryCondition());
		Page<Cor> result = (Page<Cor>) corCustBiz.findCorCustByPageAndMoreTable(queryPage);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	public String findById(){
		
		corCust = corCustBiz.getCorCustById(id);
		
		return SUCCESS;
	}
	
	public String examine(){

		corCust = corCustBiz.getCorCustById(id);


		corCust.setStatus(stats);		
		corCust=corCustBiz.updateCorCust(corCust);
		if(corCust.getStatus().equals(stats)){
			
			return SUCCESS;
		}else{
			
			return ERROR;
		}
		
	}
	

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CorCustManagerAction.saveOrUpdate()...");
		

		// 如果是新增
		if (this.getIsModify() == false) {
			CorCust corCust = new CorCust();
			CorCustCompany corCustCompany = new CorCustCompany();
			CorCustPhones corCustPhones = new CorCustPhones();
			CorCustAddr corCustAddr = new CorCustAddr();

			// 用户主表
			corCust.setCertCopy(cor.getCertCopy());
			corCust.setCertExpireDate(cor.getCertExpireDate());
			corCust.setCertNum(cor.getCertNum());
			corCust.setCertType(cor.getCertType());
			corCust.setCountry(cor.getCountry());
			corCust.setCreateTime(cor.getCreateTime());
			corCust.setCreator(cor.getCreator());
			corCust.setCustCode(cor.getCustCode());
			corCust.setCustLevel(cor.getCustLevel());
			corCust.setCustType(cor.getCustType());
			corCust.setDataSource(cor.getDataSource());
			corCust.setFirstName(cor.getFirstName());
			corCust.setId(null);
			corCust.setIsValid(cor.getIsValid());
			corCust.setLastName(cor.getLastName());
			corCust.setLocalName(cor.getLocalName());
			corCust.setRealNameLeve(cor.getRealNameLeve());
			corCust.setRegTime(cor.getRegTime());
			corCust.setStatus("03");
			corCust.setUpdateTime(cor.getUpdateTime());
			corCust.setUpdator(cor.getUpdator());

			corCustBiz.saveCorCust(corCust);
			// 企业用户
			corCustCompany.setBusinessScope(cor.getBusinessScope());
			corCustCompany.setCompanyCode(cor.getCompanyCode());
			corCustCompany.setCompanyFax(cor.getCompanyFax());
			corCustCompany.setCompanyRegAddr(cor.getCompanyRegAddr());
			corCustCompany.setCompanyWebsite(cor.getCompanyWebsite());
			corCustCompany.setCorporateCertCopy(cor.getCorporateCertCopy());
			corCustCompany.setCorporateCertExpireDate(cor.getCorporateCertExpireDate());
			corCustCompany.setCorporateCertNum(cor.getCorporateCertNum());
			corCustCompany.setCorporateCertType(cor.getCorporateCertType());
			corCustCompany.setCorporateCountryCode(cor.getCorporateCountryCode());
			corCustCompany.setCorporateName(cor.getCorporateName());
			corCustCompany.setCorporatePhonenum(cor.getCorporatePhonenum());
			corCustCompany.setCountry(cor.getCountry());
			corCustCompany.setCreateTime(cor.getCreateTime());
			corCustCompany.setCreator(cor.getCreator());
			corCustCompany.setCustCode(cor.getCustCode());
			corCustCompany.setEmail(cor.getEmail());
			corCustCompany.setId(null);
			corCustCompany.setIndustry(cor.getIndustry());
			corCustCompany.setIsValid(cor.getIsValid());
			corCustCompany.setRegTime(cor.getRegTime());
			corCustCompany.setTcid(cor.getTcid());
			corCustCompany.setUnitProperty(cor.getUnitProperty());
			corCustCompany.setUpdateTime(cor.getUpdateTime());
			corCustCompany.setUpdator(cor.getUpdator());

			corCustCompanyBiz.saveCorCustCompany(corCustCompany);
			// 电话表
			corCustPhones.setCreateTime(cor.getCreateTime());
			corCustPhones.setCreator(cor.getCreator());
			corCustPhones.setCustCode(cor.getCustCode());
			corCustPhones.setId(null);
			corCustPhones.setIsDefault("1");
			corCustPhones.setIsValid(cor.getIsValid());
			corCustPhones.setPhoneNum(cor.getPhoneNum());
			corCustPhones.setPhoneType(cor.getPhoneType());
			corCustPhones.setUpdateTime(cor.getUpdateTime());
			corCustPhones.setUpdator(cor.getUpdator());

			corCustPhonesBiz.saveCorCustPhones(corCustPhones);

			// 地址表
			corCustAddr.setAddr(cor.getAddr());
			corCustAddr.setAddrType(cor.getAddrType());
			corCustAddr.setCity(cor.getCity());
			corCustAddr.setCountry(cor.getCountry());
			corCustAddr.setCreateTime(cor.getCreateTime());
			corCustAddr.setCreator(cor.getCreator());
			corCustAddr.setCustCode(cor.getCustCode());
			corCustAddr.setDistrict(cor.getDistrict());
			corCustAddr.setId(null);
			corCustAddr.setIsDefault("1");
			corCustAddr.setIsValid(cor.getIsValid());
			corCustAddr.setPostcode(cor.getPostcode());
			corCustAddr.setProvience(cor.getProvience());
			corCustAddr.setUpdateTime(cor.getUpdateTime());
			corCustAddr.setUpdator(cor.getUpdator());

			corCustAddrBiz.saveCorCustAddr(corCustAddr);

		}
		// 如果是修改
		else {
			// 用户主表
			CorCust corCust = corCustBiz.load2(cor.getCustCode());
			
			corCust.setId(cor.getId());
			corCust.setCustCode(cor.getCustCode());
			corCust.setLocalName(cor.getLocalName());
			corCust.setCertType(cor.getCertType());
			corCust.setCertNum(cor.getCertNum());
			corCust.setCertExpireDate(cor.getCertExpireDate());
			corCust.setCountry(cor.getCountry());
			
			corCustBiz.updateCorCust(corCust);
			
			// 企业用户
			CorCustCompany corCustCompany = corCustCompanyBiz.load1(cor.getCustCode());
			
			corCustCompany.setUnitProperty(cor.getUnitProperty());
			corCustCompany.setCorporateName(cor.getCorporateName());
			corCustCompany.setCorporateCertType(cor.getCorporateCertType());
			corCustCompany.setCorporateCertNum(cor.getCorporateCertNum());
			corCustCompany.setCountry(cor.getCountry());
			corCustCompany.setRegTime(cor.getRegTime());
			corCustCompany.setBusinessScope(cor.getBusinessScope());
			corCustCompany.setIndustry(cor.getIndustry());
			
			corCustCompanyBiz.updateCorCustCompany(corCustCompany);
			
			// 电话表
			CorCustPhones corCustPhones = corCustPhonesBiz.load1(cor.getCustCode());
			
			corCustPhones.setPhoneNum(cor.getPhoneNum());
			
			corCustPhonesBiz.updateCorCustPhones(corCustPhones);
			
			// 地址表
			CorCustAddr corCustAddr = corCustAddrBiz.load1(cor.getCustCode());
			
			corCustAddr.setProvience(cor.getProvience());
			corCustAddr.setCity(cor.getCity());
			corCustAddr.setAddr(cor.getAddr());
			corCustAddr.setPostcode(cor.getPostcode());
			
			corCustAddrBiz.updateCorCustAddr(corCustAddr);
			
		}

		return SUCCESS;
	}

	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::CorCustManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CorCustManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		corCustBiz.exportCorCust(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CorCustManagerAction.setQueryCondition()...");

		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}

		// CUST_CODE
		String custCode = getSearchFields().get("custCode");
		if (StringUtils.isNotEmpty(custCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custCode", "%" + custCode + "%");
				queryPage.addLikeSearch("custCode", custCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custCode", "%" + custCode + "%");
				exportSetting.addLikeSearch("custCode", custCode);
			}
		}

		// CUST_TYPE
		String custType = getSearchFields().get("custType");
		if (StringUtils.isNotEmpty(custType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("custType", "%" + custType + "%");
				queryPage.addLikeSearch("custType", custType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("custType", "%" + custType + "%");
				exportSetting.addLikeSearch("custType", custType);
			}
		}

		// COUNTRY
		String country = getSearchFields().get("country");
		if (StringUtils.isNotEmpty(country)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("country", "%" + country + "%");
				queryPage.addLikeSearch("country", country);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("country", "%" + country + "%");
				exportSetting.addLikeSearch("country", country);
			}
		}

		// LOCAL_NAME
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("localName", "%" + localName + "%");
				queryPage.addLikeSearch("localName", localName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("localName", "%" + localName + "%");
				exportSetting.addLikeSearch("localName", localName);
			}
		}

		// ENGLISH_NAME
		String englishName = getSearchFields().get("englishName");
		if (StringUtils.isNotEmpty(englishName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("englishName", "%" + englishName + "%");
				queryPage.addLikeSearch("englishName", englishName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("englishName", "%" + englishName + "%");
				exportSetting.addLikeSearch("englishName", englishName);
			}
		}

		// CERT_TYPE
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certType", "%" + certType + "%");
				queryPage.addLikeSearch("certType", certType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certType", "%" + certType + "%");
				exportSetting.addLikeSearch("certType", certType);
			}
		}

		// CERT_NUM
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certNum", "%" + certNum + "%");
				queryPage.addLikeSearch("certNum", certNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certNum", "%" + certNum + "%");
				exportSetting.addLikeSearch("certNum", certNum);
			}
		}

		// CERT_EXPIRE_DATE
		String certExpireDate = getSearchFields().get("certExpireDate");
		if (StringUtils.isNotEmpty(certExpireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certExpireDate", DateUtils.convert(certExpireDate, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certExpireDate",
						DateUtils.convert(certExpireDate, DateUtils.DATE_FORMAT));
			}
		}

		// STATUS
		String status = getSearchFields().get("status");
		if (StringUtils.isNotEmpty(status)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("status", "%" + status + "%");
				queryPage.addLikeSearch("status", status);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("status", "%" + status + "%");
				exportSetting.addLikeSearch("status", status);
			}
		}

		// REG_TIME
		String regTime = getSearchFields().get("regTime");
		if (StringUtils.isNotEmpty(regTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("regTime", DateUtils.convert(regTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("regTime", DateUtils.convert(regTime, DateUtils.DATE_FORMAT));
			}
		}

		// DATA_SOURCE
		String dataSource = getSearchFields().get("dataSource");
		if (StringUtils.isNotEmpty(dataSource)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("dataSource", "%" + dataSource + "%");
				queryPage.addLikeSearch("dataSource", dataSource);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("dataSource", "%" + dataSource + "%");
				exportSetting.addLikeSearch("dataSource", dataSource);
			}
		}

		// IS_VALID
		String isValid = getSearchFields().get("isValid");
		if (StringUtils.isNotEmpty(isValid)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isValid", "%" + isValid + "%");
				queryPage.addLikeSearch("isValid", isValid);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isValid", "%" + isValid + "%");
				exportSetting.addLikeSearch("isValid", isValid);
			}
		}

		// CREATOR
		String creator = getSearchFields().get("creator");
		if (StringUtils.isNotEmpty(creator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("creator", "%" + creator + "%");
				queryPage.addLikeSearch("creator", creator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("creator", "%" + creator + "%");
				exportSetting.addLikeSearch("creator", creator);
			}
		}

		// UPDATOR
		String updator = getSearchFields().get("updator");
		if (StringUtils.isNotEmpty(updator)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updator", "%" + updator + "%");
				queryPage.addLikeSearch("updator", updator);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updator", "%" + updator + "%");
				exportSetting.addLikeSearch("updator", updator);
			}
		}

		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime", DateUtils.convert(createTime, DateUtils.DATE_FORMAT));
			}
		}

		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime", DateUtils.convert(updateTime, DateUtils.DATE_FORMAT));
			}
		}

		// CERT_COPY
		String certCopy = getSearchFields().get("certCopy");
		if (StringUtils.isNotEmpty(certCopy)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("certCopy", "%" + certCopy + "%");
				queryPage.addLikeSearch("certCopy", certCopy);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("certCopy", "%" + certCopy + "%");
				exportSetting.addLikeSearch("certCopy", certCopy);
			}
		}
	}

	public ICorCustBiz getCorCustBiz() {
		logger.info("entering action::CorCustManagerAction.getCorCustBiz()...");
		return corCustBiz;
	}

	public void setCorCustBiz(ICorCustBiz corCustBiz) {
		logger.info("entering action::CorCustManagerAction.setCorCustBiz()...");
		this.corCustBiz = corCustBiz;
	}

	public ICorCustCompanyBiz getCorCustCompanyBiz() {
		return corCustCompanyBiz;
	}

	public void setCorCustCompanyBiz(ICorCustCompanyBiz corCustCompanyBiz) {
		this.corCustCompanyBiz = corCustCompanyBiz;
	}

	public ICorCustAddrBiz getCorCustAddrBiz() {
		return corCustAddrBiz;
	}

	public void setCorCustAddrBiz(ICorCustAddrBiz corCustAddrBiz) {
		this.corCustAddrBiz = corCustAddrBiz;
	}

	public ICorCustPhonesBiz getCorCustPhonesBiz() {
		return corCustPhonesBiz;
	}

	public void setCorCustPhonesBiz(ICorCustPhonesBiz corCustPhonesBiz) {
		this.corCustPhonesBiz = corCustPhonesBiz;
	}
	
	public CorCust getCorCust() {
		return corCust;
	}

	public void setCorCust(CorCust corCust) {
		this.corCust = corCust;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getId() {
		logger.info("entering action::CorCustManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CorCustManagerAction.setId()...");
		this.id = id;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}
	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}
	public List<OptionObjectPair> getCustTypeList() {
		return custTypeList;
	}
	public void setCustTypeList(List<OptionObjectPair> custTypeList) {
		this.custTypeList = custTypeList;
	}
	public List<OptionObjectPair> getCertTypeList() {
		return certTypeList;
	}
	public void setCertTypeList(List<OptionObjectPair> certTypeList) {
		this.certTypeList = certTypeList;
	}
	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}
	/**
	 * @return
	 * @author user 查询条件
	 */
	@SuppressWarnings("unused")
	public String getQueryCondition() {
		Map<String, String> searchFields2 = getSearchFields();
		StringBuffer condition = new StringBuffer();
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();

		// custType
		String custType = getSearchFields().get("custType");
		if (StringUtils.isNotEmpty(custType)) {
			condition.append(" and c.custType like ?");
			queryPage.addQueryCondition("custType", "%" + custType + "%");
		}

		// custCode
		String custCode = getSearchFields().get("custCode");
		if (StringUtils.isNotEmpty(custCode)) {
			condition.append(" and c.custCode like ?");
			queryPage.addQueryCondition("custCode", "%" + custCode + "%");
		}
		// certType
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			condition.append(" and c.certType = '" + certType + "'");
			// queryPage.addQueryCondition("certType", "'"+certType+"'");
		}

		// status
		String status = getSearchFields().get("status");
		if (StringUtils.isNotEmpty(status)) {
			condition.append(" and c.status = '" + status + "'");
			// queryPage.addQueryCondition("status", "'"+status+"'");
		}

		// certNum
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			condition.append(" and c.certNum like ?");
			queryPage.addQueryCondition("certNum", "%" + certNum + "%");
		}

		// kaiTime
		String kaiTime = getSearchFields().get("kaiTime");
		if (StringUtils.isNotEmpty(kaiTime)) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date sTime = sim.parse(kaiTime);
				condition.append(" and c.createTime >= ?");
				queryPage.addQueryCondition("createTime", sTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// jieTime
		String jieTime = getSearchFields().get("jieTime");

		if (StringUtils.isNotEmpty(jieTime)) {

			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
			try {
				Date eTime = sim.parse(jieTime);

				condition.append(" and c.createTime <= ?");
				queryPage.addQueryCondition("createTime", eTime);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// localName
		String localName = getSearchFields().get("localName");
		if (StringUtils.isNotEmpty(localName)) {
			condition.append(" and c.localName like ?");
			queryPage.addQueryCondition("localName", "%" + localName + "%");
		}

		return condition.toString();
	}

}
