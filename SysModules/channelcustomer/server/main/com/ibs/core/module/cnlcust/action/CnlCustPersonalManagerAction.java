/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnlcust.action;

import com.ibs.portal.framework.util.DateUtils;
import com.ibs.core.module.cnlcust.biz.ICnlCustPersonalBiz;
import com.ibs.core.module.cnlcust.domain.CnlCustPersonal;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CnlCustPersonalManagerAction extends CrudBaseAction {

	private ICnlCustPersonalBiz cnlCustPersonalBiz;
	
	private String id;
	private CnlCustPersonal cnlCustPersonal;

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::CnlCustPersonalManagerAction.list()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::CnlCustPersonalManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::CnlCustPersonalManagerAction.modify()...");
		cnlCustPersonal = cnlCustPersonalBiz.getCnlCustPersonalById(id);
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::CnlCustPersonalManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<CnlCustPersonal> result = (Page<CnlCustPersonal>) cnlCustPersonalBiz
				.findCnlCustPersonalByPage(queryPage);
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
		logger.info("entering action::CnlCustPersonalManagerAction.saveOrUpdate()...");

		// 如果是新增
		if (this.getIsModify() == false) {
			cnlCustPersonalBiz.saveCnlCustPersonal(cnlCustPersonal);
		}
		// 如果是修改
		else {
			cnlCustPersonalBiz.updateCnlCustPersonal(cnlCustPersonal);
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
		logger.info("entering action::CnlCustPersonalManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::CnlCustPersonalManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		cnlCustPersonalBiz.exportCnlCustPersonal(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::CnlCustPersonalManagerAction.setQueryCondition()...");


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


		// NAME
		String name = getSearchFields().get("name");
		if (StringUtils.isNotEmpty(name)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("name", "%" + name + "%");
				queryPage.addLikeSearch("name", name);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("name", "%" + name + "%");
				exportSetting.addLikeSearch("name", name);
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


		// EMPLOYER
		String employer = getSearchFields().get("employer");
		if (StringUtils.isNotEmpty(employer)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("employer", "%" + employer + "%");
				queryPage.addLikeSearch("employer", employer);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("employer", "%" + employer + "%");
				exportSetting.addLikeSearch("employer", employer);
			}
		}


		// JOB_TITLE
		String jobTitle = getSearchFields().get("jobTitle");
		if (StringUtils.isNotEmpty(jobTitle)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("jobTitle", "%" + jobTitle + "%");
				queryPage.addLikeSearch("jobTitle", jobTitle);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("jobTitle", "%" + jobTitle + "%");
				exportSetting.addLikeSearch("jobTitle", jobTitle);
			}
		}


		// WORK_TEL
		String workTel = getSearchFields().get("workTel");
		if (StringUtils.isNotEmpty(workTel)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("workTel", "%" + workTel + "%");
				queryPage.addLikeSearch("workTel", workTel);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("workTel", "%" + workTel + "%");
				exportSetting.addLikeSearch("workTel", workTel);
			}
		}


		// GENDER
		String gender = getSearchFields().get("gender");
		if (StringUtils.isNotEmpty(gender)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("gender", "%" + gender + "%");
				queryPage.addLikeSearch("gender", gender);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("gender", "%" + gender + "%");
				exportSetting.addLikeSearch("gender", gender);
			}
		}


		// BIRTHDAY
		String birthday = getSearchFields().get("birthday");
		if (StringUtils.isNotEmpty(birthday)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("birthday",
						DateUtils.convert(birthday,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("birthday",
						DateUtils.convert(birthday,
								DateUtils.DATE_FORMAT));
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


		// HIGHEST_EDU
		String highestEdu = getSearchFields().get("highestEdu");
		if (StringUtils.isNotEmpty(highestEdu)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("highestEdu", "%" + highestEdu + "%");
				queryPage.addLikeSearch("highestEdu", highestEdu);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("highestEdu", "%" + highestEdu + "%");
				exportSetting.addLikeSearch("highestEdu", highestEdu);
			}
		}


		// PHONENUM
		String phonenum = getSearchFields().get("phonenum");
		if (StringUtils.isNotEmpty(phonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("phonenum", "%" + phonenum + "%");
				queryPage.addLikeSearch("phonenum", phonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("phonenum", "%" + phonenum + "%");
				exportSetting.addLikeSearch("phonenum", phonenum);
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


		// IS_MARRIED
		String isMarried = getSearchFields().get("isMarried");
		if (StringUtils.isNotEmpty(isMarried)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("isMarried", "%" + isMarried + "%");
				queryPage.addLikeSearch("isMarried", isMarried);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("isMarried", "%" + isMarried + "%");
				exportSetting.addLikeSearch("isMarried", isMarried);
			}
		}


		// SPOUSE_COUNTRY
		String spouseCountry = getSearchFields().get("spouseCountry");
		if (StringUtils.isNotEmpty(spouseCountry)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseCountry", "%" + spouseCountry + "%");
				queryPage.addLikeSearch("spouseCountry", spouseCountry);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseCountry", "%" + spouseCountry + "%");
				exportSetting.addLikeSearch("spouseCountry", spouseCountry);
			}
		}


		// SPOUSE_LOCAL_NAME
		String spouseLocalName = getSearchFields().get("spouseLocalName");
		if (StringUtils.isNotEmpty(spouseLocalName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseLocalName", "%" + spouseLocalName + "%");
				queryPage.addLikeSearch("spouseLocalName", spouseLocalName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseLocalName", "%" + spouseLocalName + "%");
				exportSetting.addLikeSearch("spouseLocalName", spouseLocalName);
			}
		}


		// SPOUSE_ENGLISH_NAME
		String spouseEnglishName = getSearchFields().get("spouseEnglishName");
		if (StringUtils.isNotEmpty(spouseEnglishName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseEnglishName", "%" + spouseEnglishName + "%");
				queryPage.addLikeSearch("spouseEnglishName", spouseEnglishName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseEnglishName", "%" + spouseEnglishName + "%");
				exportSetting.addLikeSearch("spouseEnglishName", spouseEnglishName);
			}
		}


		// SPOUSE_CERT_TYPE
		String spouseCertType = getSearchFields().get("spouseCertType");
		if (StringUtils.isNotEmpty(spouseCertType)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseCertType", "%" + spouseCertType + "%");
				queryPage.addLikeSearch("spouseCertType", spouseCertType);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseCertType", "%" + spouseCertType + "%");
				exportSetting.addLikeSearch("spouseCertType", spouseCertType);
			}
		}


		// SPOUSE_CERT_NUM
		String spouseCertNum = getSearchFields().get("spouseCertNum");
		if (StringUtils.isNotEmpty(spouseCertNum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseCertNum", "%" + spouseCertNum + "%");
				queryPage.addLikeSearch("spouseCertNum", spouseCertNum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseCertNum", "%" + spouseCertNum + "%");
				exportSetting.addLikeSearch("spouseCertNum", spouseCertNum);
			}
		}


		// SPOUSE_CERT_EXPIRE_DATE
		String spouseCertExpireDate = getSearchFields().get("spouseCertExpireDate");
		if (StringUtils.isNotEmpty(spouseCertExpireDate)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spouseCertExpireDate",
						DateUtils.convert(spouseCertExpireDate,
								DateUtils.DATE_FORMAT));
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spouseCertExpireDate",
						DateUtils.convert(spouseCertExpireDate,
								DateUtils.DATE_FORMAT));
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


		// SPOUSE_PHONENUM
		String spousePhonenum = getSearchFields().get("spousePhonenum");
		if (StringUtils.isNotEmpty(spousePhonenum)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("spousePhonenum", "%" + spousePhonenum + "%");
				queryPage.addLikeSearch("spousePhonenum", spousePhonenum);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("spousePhonenum", "%" + spousePhonenum + "%");
				exportSetting.addLikeSearch("spousePhonenum", spousePhonenum);
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

	}

	public ICnlCustPersonalBiz getCnlCustPersonalBiz() {
		logger.info("entering action::CnlCustPersonalManagerAction.getCnlCustPersonalBiz()...");
		return cnlCustPersonalBiz;
	}

	public void setCnlCustPersonalBiz(ICnlCustPersonalBiz cnlCustPersonalBiz) {
		logger.info("entering action::CnlCustPersonalManagerAction.setCnlCustPersonalBiz()...");
		this.cnlCustPersonalBiz = cnlCustPersonalBiz;
	}

	public CnlCustPersonal getCnlCustPersonal() {
		logger.info("entering action::CnlCustPersonalManagerAction.getCnlCustPersonal()...");
		return cnlCustPersonal;
	}

	public void setCnlCustPersonal(CnlCustPersonal cnlCustPersonal) {
		logger.info("entering action::CnlCustPersonalManagerAction.setCnlCustPersonal()...");
		this.cnlCustPersonal = cnlCustPersonal;
	}

	public String getId() {
		logger.info("entering action::CnlCustPersonalManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::CnlCustPersonalManagerAction.setId()...");
		this.id = id;
	}

}
