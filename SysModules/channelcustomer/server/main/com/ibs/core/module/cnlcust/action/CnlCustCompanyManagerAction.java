/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibs.core.module.account.biz.ICnlTrans3mBiz;
import com.ibs.core.module.cnlcust.biz.ICnlCustCompanyBiz;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyAndOtherDto;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.core.module.cnlcust.utils.CnlCustCompanyDtoUtil;
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
 * @comments	: code generated based on database T_CNL_CUST_COMPANY
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustCompanyManagerAction extends CrudBaseAction {

	private ICnlCustCompanyBiz cnlCustCompanyBiz;
	
	private String id;
	private CnlCustCompany cnlCustCompany;
	private CnlCustCompanyAndOtherDto cnlCustCompanyAndOtherDto;
	private ICnlTrans3mBiz cnlTrans3mBiz;

	private IDataDictService dataDictService;
	private IDataDictBiz dataDictBiz;
	private List<OptionObjectPair> custStatuss;
	private List<OptionObjectPair> cnlCnlCodes;
	/**
	 * 企业客户信息明细
	 */
	public String detail(){
		logger.info("entering action::CnlCustCompanyManagerAction.detail()...");
		//获取参数
		String cnlCustCode = request.getParameter("cnlCustCode").trim();
		cnlCustCompanyAndOtherDto = cnlCustCompanyBiz.findDetail(cnlCustCode);
		//得到明细数据集合
		//List<String> messageList = CnlCustCompanyDtoUtil.getDetailList(dto, dataDictService);
		//生成table代码
		//String form = DtoUtils.listToForm(messageList);
		//使用数据子典,将部分编码转为实际的内容
		Collection<CnlCustCompanyAndOtherDto> c =new ArrayList<CnlCustCompanyAndOtherDto>();
		c.add(cnlCustCompanyAndOtherDto);
		CnlCustCompanyDtoUtil.changeToContent(c, dataDictService);
		
		return "cnlCustCompanyAndOther";
	}
	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCustCompanyManagerAction.list()...");
		custStatuss = dataDictBiz.listOptions(IDataDictService.DATA_DICT_TYPE__CNL_CUST_STATUS);
		cnlCnlCodes= cnlTrans3mBiz.getCnlSourceList();
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustCompanyManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustCompanyManagerAction.modify()...");
		cnlCustCompany = cnlCustCompanyBiz.getCnlCustCompanyById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlCustCompanyManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
//		Page<CnlCustCompany> result = (Page<CnlCustCompany>) cnlCustCompanyBiz
//				.findCnlCustCompanyByPage(queryPage);
		//得到hql的条件拼接的语句
		String searchCondition = getSearchCondition();
		//查询
		Page<CnlCustCompanyAndOtherDto> result = (Page<CnlCustCompanyAndOtherDto>) cnlCustCompanyBiz
				.findCnlCustCompanyByMoreTable(queryPage,searchCondition);
		Collection<CnlCustCompanyAndOtherDto> c=result.getRows();
		//使用数据子典,将部分编码转为实际的内容
		CnlCustCompanyDtoUtil.changeToContent(c, dataDictService);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::CnlCustCompanyManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustCompanyBiz.saveCnlCustCompany(cnlCustCompany);
		}
		// 如果是修改
		else {
			cnlCustCompanyBiz.updateCnlCustCompany(cnlCustCompany);
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
		logger.info("entering action::CnlCustCompanyManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustCompanyManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustCompanyBiz.exportCnlCustCompany(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlCustCompanyManagerAction.setQueryCondition()...");


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


		// CNL_CUST_CODE
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				queryPage.addLikeSearch("cnlCustCode", cnlCustCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("cnlCustCode", "%" + cnlCustCode + "%");
				exportSetting.addLikeSearch("cnlCustCode", cnlCustCode);
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


		// IS_SMS_VERIFIED
		String isSmsVerified = getSearchFields().get("isSmsVerified");
		if (StringUtils.isNotEmpty(isSmsVerified)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isSmsVerified", "%" + isSmsVerified + "%");
				queryPage.addLikeSearch("isSmsVerified", isSmsVerified);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isSmsVerified", "%" + isSmsVerified + "%");
				exportSetting.addLikeSearch("isSmsVerified", isSmsVerified);
			}
		}


		// COMPANY_TYPE
		String companyType = getSearchFields().get("companyType");
		if (StringUtils.isNotEmpty(companyType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyType", "%" + companyType + "%");
				queryPage.addLikeSearch("companyType", companyType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyType", "%" + companyType + "%");
				exportSetting.addLikeSearch("companyType", companyType);
			}
		}


		// COMPANY_NAME
		String companyName = getSearchFields().get("companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyName", "%" + companyName + "%");
				queryPage.addLikeSearch("companyName", companyName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyName", "%" + companyName + "%");
				exportSetting.addLikeSearch("companyName", companyName);
			}
		}


		// COMPANY_CODE
		String companyCode = getSearchFields().get("companyCode");
		if (StringUtils.isNotEmpty(companyCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyCode", "%" + companyCode + "%");
				queryPage.addLikeSearch("companyCode", companyCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyCode", "%" + companyCode + "%");
				exportSetting.addLikeSearch("companyCode", companyCode);
			}
		}


		// CONTRACT_EFFECT_DATE
		String contractEffectDate = getSearchFields().get("contractEffectDate");
		if (StringUtils.isNotEmpty(contractEffectDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("contractEffectDate",
						DateUtils.convert(contractEffectDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("contractEffectDate",
						DateUtils.convert(contractEffectDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// CONTRACT_EXPIRE_DATE
		String contractExpireDate = getSearchFields().get("contractExpireDate");
		if (StringUtils.isNotEmpty(contractExpireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("contractExpireDate",
						DateUtils.convert(contractExpireDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("contractExpireDate",
						DateUtils.convert(contractExpireDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// REG_TIME
		String regTime = getSearchFields().get("regTime");
		if (StringUtils.isNotEmpty(regTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("regTime",
						DateUtils.convert(regTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("regTime",
						DateUtils.convert(regTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// WEBSITE
		String website = getSearchFields().get("website");
		if (StringUtils.isNotEmpty(website)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("website", "%" + website + "%");
				queryPage.addLikeSearch("website", website);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("website", "%" + website + "%");
				exportSetting.addLikeSearch("website", website);
			}
		}


		// EMAIL
		String email = getSearchFields().get("email");
		if (StringUtils.isNotEmpty(email)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("email", "%" + email + "%");
				queryPage.addLikeSearch("email", email);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("email", "%" + email + "%");
				exportSetting.addLikeSearch("email", email);
			}
		}


		// COMPANY_TEL
		String companyTel = getSearchFields().get("companyTel");
		if (StringUtils.isNotEmpty(companyTel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyTel", "%" + companyTel + "%");
				queryPage.addLikeSearch("companyTel", companyTel);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyTel", "%" + companyTel + "%");
				exportSetting.addLikeSearch("companyTel", companyTel);
			}
		}


		// COMPANY_REG_ADDR
		String companyRegAddr = getSearchFields().get("companyRegAddr");
		if (StringUtils.isNotEmpty(companyRegAddr)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyRegAddr", "%" + companyRegAddr + "%");
				queryPage.addLikeSearch("companyRegAddr", companyRegAddr);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyRegAddr", "%" + companyRegAddr + "%");
				exportSetting.addLikeSearch("companyRegAddr", companyRegAddr);
			}
		}


		// FINANCE_CONTACT
		String financeContact = getSearchFields().get("financeContact");
		if (StringUtils.isNotEmpty(financeContact)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("financeContact", "%" + financeContact + "%");
				queryPage.addLikeSearch("financeContact", financeContact);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("financeContact", "%" + financeContact + "%");
				exportSetting.addLikeSearch("financeContact", financeContact);
			}
		}


		// FINANCE_TEL
		String financeTel = getSearchFields().get("financeTel");
		if (StringUtils.isNotEmpty(financeTel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("financeTel", "%" + financeTel + "%");
				queryPage.addLikeSearch("financeTel", financeTel);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("financeTel", "%" + financeTel + "%");
				exportSetting.addLikeSearch("financeTel", financeTel);
			}
		}


		// FINNACE_EMAIL
		String finnaceEmail = getSearchFields().get("finnaceEmail");
		if (StringUtils.isNotEmpty(finnaceEmail)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("finnaceEmail", "%" + finnaceEmail + "%");
				queryPage.addLikeSearch("finnaceEmail", finnaceEmail);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("finnaceEmail", "%" + finnaceEmail + "%");
				exportSetting.addLikeSearch("finnaceEmail", finnaceEmail);
			}
		}


		// REG_CAPITAL
		if(StringUtils.isNotEmpty(getSearchFields().get("regCapital"))){
			Long regCapital =Long.parseLong(getSearchFields().get("regCapital"));
			if (null != regCapital) {
				if (null != queryPage) {
					queryPage.addQueryCondition("regCapital", regCapital);
					queryPage.addEqualSearch("regCapital", regCapital);
				}
				if (null != exportSetting) {
					queryPage.addQueryCondition("regCapital", regCapital);
					queryPage.addEqualSearch("regCapital", regCapital);
				}
			}
		}


		// REG_CAPITAL_CURRENCY
		String regCapitalCurrency = getSearchFields().get("regCapitalCurrency");
		if (StringUtils.isNotEmpty(regCapitalCurrency)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("regCapitalCurrency", "%" + regCapitalCurrency + "%");
				queryPage.addLikeSearch("regCapitalCurrency", regCapitalCurrency);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("regCapitalCurrency", "%" + regCapitalCurrency + "%");
				exportSetting.addLikeSearch("regCapitalCurrency", regCapitalCurrency);
			}
		}


		// COMPANY_FAX
		String companyFax = getSearchFields().get("companyFax");
		if (StringUtils.isNotEmpty(companyFax)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("companyFax", "%" + companyFax + "%");
				queryPage.addLikeSearch("companyFax", companyFax);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("companyFax", "%" + companyFax + "%");
				exportSetting.addLikeSearch("companyFax", companyFax);
			}
		}


		// CORPORATE_NAME
		String corporateName = getSearchFields().get("corporateName");
		if (StringUtils.isNotEmpty(corporateName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateName", "%" + corporateName + "%");
				queryPage.addLikeSearch("corporateName", corporateName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateName", "%" + corporateName + "%");
				exportSetting.addLikeSearch("corporateName", corporateName);
			}
		}


		// CORPORATE_CERT_TYPE
		String corporateCertType = getSearchFields().get("corporateCertType");
		if (StringUtils.isNotEmpty(corporateCertType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateCertType", "%" + corporateCertType + "%");
				queryPage.addLikeSearch("corporateCertType", corporateCertType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateCertType", "%" + corporateCertType + "%");
				exportSetting.addLikeSearch("corporateCertType", corporateCertType);
			}
		}


		// CORPORATE_CERT_NUM
		String corporateCertNum = getSearchFields().get("corporateCertNum");
		if (StringUtils.isNotEmpty(corporateCertNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateCertNum", "%" + corporateCertNum + "%");
				queryPage.addLikeSearch("corporateCertNum", corporateCertNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateCertNum", "%" + corporateCertNum + "%");
				exportSetting.addLikeSearch("corporateCertNum", corporateCertNum);
			}
		}


		// CORPORATE_CERT_COPY
		String corporateCertCopy = getSearchFields().get("corporateCertCopy");
		if (StringUtils.isNotEmpty(corporateCertCopy)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateCertCopy", "%" + corporateCertCopy + "%");
				queryPage.addLikeSearch("corporateCertCopy", corporateCertCopy);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateCertCopy", "%" + corporateCertCopy + "%");
				exportSetting.addLikeSearch("corporateCertCopy", corporateCertCopy);
			}
		}


		// CORPORATE_COUNTRY
		String corporateCountry = getSearchFields().get("corporateCountry");
		if (StringUtils.isNotEmpty(corporateCountry)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateCountry", "%" + corporateCountry + "%");
				queryPage.addLikeSearch("corporateCountry", corporateCountry);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateCountry", "%" + corporateCountry + "%");
				exportSetting.addLikeSearch("corporateCountry", corporateCountry);
			}
		}


		// CORPORATE_CERT_EXPIRE_DATE
		String corporateCertExpireDate = getSearchFields().get("corporateCertExpireDate");
		if (StringUtils.isNotEmpty(corporateCertExpireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateCertExpireDate",
						DateUtils.convert(corporateCertExpireDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateCertExpireDate",
						DateUtils.convert(corporateCertExpireDate,
								DateUtils.DATE_FORMAT));
			}
		}


		// CORPORATE_TEL
		String corporateTel = getSearchFields().get("corporateTel");
		if (StringUtils.isNotEmpty(corporateTel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("corporateTel", "%" + corporateTel + "%");
				queryPage.addLikeSearch("corporateTel", corporateTel);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("corporateTel", "%" + corporateTel + "%");
				exportSetting.addLikeSearch("corporateTel", corporateTel);
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


		// PROVIENCE
		String provience = getSearchFields().get("provience");
		if (StringUtils.isNotEmpty(provience)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("provience", "%" + provience + "%");
				queryPage.addLikeSearch("provience", provience);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("provience", "%" + provience + "%");
				exportSetting.addLikeSearch("provience", provience);
			}
		}


		// CITY
		String city = getSearchFields().get("city");
		if (StringUtils.isNotEmpty(city)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("city", "%" + city + "%");
				queryPage.addLikeSearch("city", city);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("city", "%" + city + "%");
				exportSetting.addLikeSearch("city", city);
			}
		}


		// DISTRICT
		String district = getSearchFields().get("district");
		if (StringUtils.isNotEmpty(district)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("district", "%" + district + "%");
				queryPage.addLikeSearch("district", district);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("district", "%" + district + "%");
				exportSetting.addLikeSearch("district", district);
			}
		}


		// ADDR
		String addr = getSearchFields().get("addr");
		if (StringUtils.isNotEmpty(addr)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("addr", "%" + addr + "%");
				queryPage.addLikeSearch("addr", addr);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("addr", "%" + addr + "%");
				exportSetting.addLikeSearch("addr", addr);
			}
		}


		// POSTCODE
		String postcode = getSearchFields().get("postcode");
		if (StringUtils.isNotEmpty(postcode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("postcode", "%" + postcode + "%");
				queryPage.addLikeSearch("postcode", postcode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("postcode", "%" + postcode + "%");
				exportSetting.addLikeSearch("postcode", postcode);
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


		// INDUSTRY
		String industry = getSearchFields().get("industry");
		if (StringUtils.isNotEmpty(industry)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("industry", "%" + industry + "%");
				queryPage.addLikeSearch("industry", industry);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("industry", "%" + industry + "%");
				exportSetting.addLikeSearch("industry", industry);
			}
		}


		// CREATE_TIME
		String createTime = getSearchFields().get("createTime");
		if (StringUtils.isNotEmpty(createTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("createTime",
						DateUtils.convert(createTime,
								DateUtils.DATE_FORMAT));
			}
		}


		// UPDATE_TIME
		String updateTime = getSearchFields().get("updateTime");
		if (StringUtils.isNotEmpty(updateTime)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("updateTime",
						DateUtils.convert(updateTime,
								DateUtils.DATE_FORMAT));
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

	}

	public ICnlCustCompanyBiz getCnlCustCompanyBiz() {
		logger.info("entering action::CnlCustCompanyManagerAction.getCnlCustCompanyBiz()...");
		return cnlCustCompanyBiz;
	}

	public void setCnlCustCompanyBiz(ICnlCustCompanyBiz cnlCustCompanyBiz) {
		logger.info("entering action::CnlCustCompanyManagerAction.setCnlCustCompanyBiz()...");
		this.cnlCustCompanyBiz = cnlCustCompanyBiz;
	}

	public CnlCustCompany getCnlCustCompany() {
		logger.info("entering action::CnlCustCompanyManagerAction.getCnlCustCompany()...");
		return cnlCustCompany;
	}

	public void setCnlCustCompany(CnlCustCompany cnlCustCompany) {
		logger.info("entering action::CnlCustCompanyManagerAction.setCnlCustCompany()...");
		this.cnlCustCompany = cnlCustCompany;
	}

	public String getId() {
		logger.info("entering action::CnlCustCompanyManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustCompanyManagerAction.setId()...");
		this.id = id;
	}
	/**
	 * 为hql语句生成查询条件
	 */
	public String getSearchCondition(){
		StringBuffer condition = new StringBuffer();
		//清除condition
		queryPage.clearQueryCondition();
		queryPage.clearSortMap();
		//cnlCustType
		String cnlCustType = getSearchFields().get("cnlCustType");
		if (StringUtils.isNotEmpty(cnlCustType)) {
			condition.append(" and c.cnlCustType = '01' ");
		}
		//cnlCnlCode
		String cnlCnlCode = getSearchFields().get("cnlCnlCode");
		if (StringUtils.isNotEmpty(cnlCnlCode)) { 
			condition.append(" and c.cnlCnlCode = ? ");
			queryPage.addQueryCondition("cnlCnlCode", cnlCnlCode);
		}
		//custCode
		String cnlCustCode = getSearchFields().get("cnlCustCode");
		if (StringUtils.isNotEmpty(cnlCustCode)) { 
			condition.append(" and p.cnlCustCode = ? ");
			queryPage.addQueryCondition("cnlCustCode", cnlCustCode);
		}
		//certType
		String certType = getSearchFields().get("certType");
		if (StringUtils.isNotEmpty(certType)) {
			condition.append(" and c.certType='").append(certType).append("' ");
		}
		//certNum
		String certNum = getSearchFields().get("certNum");
		if (StringUtils.isNotEmpty(certNum)) {
			condition.append(" and c.certNum='").append(certNum).append("' ");
		}
		//companyName
		String companyName = getSearchFields().get("companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			condition.append(" and p.companyName like ? ");
			queryPage.addQueryCondition("companyName", "%"+companyName+"%");
		}
		//custStatus
		String custStatus = getSearchFields().get("custStatus");
		if (StringUtils.isNotEmpty(custStatus)) {
			condition.append(" and c.custStatus = ? ");
			queryPage.addQueryCondition("custStatus", custStatus);
		}
		//createTime
		//startTime
		String startTime = getSearchFields().get("createStartTime");
		if (StringUtils.isNotEmpty(startTime)) {
			condition.append(" and p.createTime >= ? ");
			queryPage.addQueryCondition("startTime", DateUtils.convert(startTime, DateUtils.DATE_TIME_FORMAT));
		}
		
		//endTime
		String endTime = getSearchFields().get("createEndTime");
		if (StringUtils.isNotEmpty(endTime)) {
			condition.append(" and p.createTime <= ? ");
			queryPage.addQueryCondition("endTime",DateUtils.convert(endTime, DateUtils.DATE_TIME_FORMAT));
		}
		return condition.toString();
	}
	
	
	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getCustStatuss() {
		return custStatuss;
	}

	public void setCustStatuss(List<OptionObjectPair> custStatuss) {
		this.custStatuss = custStatuss;
	}
	public CnlCustCompanyAndOtherDto getCnlCustCompanyAndOtherDto() {
		return cnlCustCompanyAndOtherDto;
	}
	public void setCnlCustCompanyAndOtherDto(CnlCustCompanyAndOtherDto cnlCustCompanyAndOtherDto) {
		this.cnlCustCompanyAndOtherDto = cnlCustCompanyAndOtherDto;
	}
	public ICnlTrans3mBiz getCnlTrans3mBiz() {
		return cnlTrans3mBiz;
	}
	public void setCnlTrans3mBiz(ICnlTrans3mBiz cnlTrans3mBiz) {
		this.cnlTrans3mBiz = cnlTrans3mBiz;
	}
	public List<OptionObjectPair> getCnlCnlCodes() {
		return cnlCnlCodes;
	}
	public void setCnlCnlCodes(List<OptionObjectPair> cnlCnlCodes) {
		this.cnlCnlCodes = cnlCnlCodes;
	}
	
}
