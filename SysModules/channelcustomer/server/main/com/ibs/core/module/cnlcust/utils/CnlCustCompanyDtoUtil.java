package com.ibs.core.module.cnlcust.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.account.utils.DtoUtils;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.util.CollectionUtils;

public class CnlCustCompanyDtoUtil {
	/**
	 * 生成明细数据集合
	 */
	public static List<String> getDetailList(CnlCustCompanyDto dto,IDataDictService dataDictService){
		if(dto==null){
			return null;
		}
		//封装
		Collection<CnlCustCompanyDto> c = new ArrayList<CnlCustCompanyDto>();
		c.add(dto);
		//数据字典转换
		DtoUtils.changeToContent(c, dataDictService);
		List<String> list = new ArrayList<String>();
		list.add("客户编号：");
		list.add(DtoUtils.getNotNullString(dto.getCustCode()));
		list.add("企业名称：");
		list.add(DtoUtils.getNotNullString(dto.getCompanyName()));
		list.add("单位属性：");
		list.add(DtoUtils.getNotNullString(dto.getUnitProperty()));
		list.add("证件类型：");
		list.add(DtoUtils.getNotNullString(dto.getCertType()));
		list.add("证件号码：");
		list.add(DtoUtils.getNotNullString(dto.getCertNum()));
		list.add("证件有效期：");
		list.add(DtoUtils.getNotNullString(dto.getCertExpireDate()));
		list.add("法人代表姓名：");
		list.add(DtoUtils.getNotNullString(dto.getCorporateName()));
		list.add("法人证件类型：");
		list.add(DtoUtils.getNotNullString(dto.getCorporateCertType()));
		list.add("证件号码：");
		list.add(DtoUtils.getNotNullString(dto.getCorporateCertNum()));
		list.add("国家：");
		list.add(DtoUtils.getNotNullString(dto.getCountry()));
		list.add("省份：");
		list.add(DtoUtils.getNotNullString(dto.getProvience()));
		list.add("城市：");
		list.add(DtoUtils.getNotNullString(dto.getCity()));
		list.add("公司注册日期：");
		list.add(DtoUtils.getNotNullString(dto.getRegTime()));
		list.add("公司注册地址：");
		list.add(DtoUtils.getNotNullString(dto.getCompanyRegAddr()));
		list.add("经营范围：");
		list.add(DtoUtils.getNotNullString(dto.getBusinessScope()));
		list.add("行业类别：");
		list.add(DtoUtils.getNotNullString(dto.getIndustry()));
		list.add("注册资金：");
		list.add(DtoUtils.getNotNullString(dto.getRegCapital()));
		list.add("注册币种：");
		list.add(DtoUtils.getNotNullString(dto.getRegCapitalCurrency()));
		list.add("财务联系人：");
		list.add(DtoUtils.getNotNullString(dto.getFinanceContact()));
		list.add("财务联系人电话：");
		list.add(DtoUtils.getNotNullString(dto.getFinanceTel()));
		list.add("财务联系人邮箱：");
		list.add(DtoUtils.getNotNullString(dto.getFinnaceEmail()));
		list.add("地址：");
		list.add(DtoUtils.getNotNullString(dto.getAddr()));
		list.add("电话：");
		list.add(DtoUtils.getNotNullString(dto.getCompanyTel()));
		list.add("传真号码：");
		list.add(DtoUtils.getNotNullString(dto.getCompanyFax()));
		list.add("邮编：");
		list.add(DtoUtils.getNotNullString(dto.getPostcode()));
		list.add("公司网址：");
		list.add(DtoUtils.getNotNullString(dto.getWebsite()));
		return list;
		
	}
	/**
	 * 将代码转为汉字
	 */
	public static void changeToContent(Collection c,IDataDictService dataDictService){
		List<DataDict> col;
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("cnlCnlCode", "cnlCnlCode");
		map.put("transCurrency", IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		map.put("custType", IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		map.put("cnlCustType", IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		map.put("acntType", IDataDictService.DATA_DICT_TYPE__ACNT_TYPE);
		map.put("currency", IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		map.put("direction", IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		map.put("isPrinted", IDataDictService.DATA_DICT_TYPE__IS_PRINTED);
		map.put("custType", IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
		map.put("reqType", IDataDictService.DATA_DICT_TYPE__REQ_TYPE);
		map.put("reqStatus", IDataDictService.DATA_DICT_TYPE__REQ_STATUS);
		map.put("isinGl", IDataDictService.DATA_DICT_TYPE__ISIN_GL);
		map.put("custStatus", IDataDictService.DATA_DICT_TYPE__CNL_CUST_STATUS);
		map.put("gender", IDataDictService.DATA_DICT_TYPE__GENDER);
		map.put("isMarried", IDataDictService.DATA_DICT_TYPE__IS_MARIED);
		map.put("country", IDataDictService.DATA_DICT_TYPE__COUNTRY);
		map.put("highestEdu", IDataDictService.DATA_DICT_TYPE__EDUCATION_LEVEL);
		map.put("industry", IDataDictService.DATA_DICT_TYPE__INDUSTRY);
		map.put("realNameLevel", IDataDictService.DATA_DICT_TYPE__CERTIFICATION_LEVEL);
		map.put("transType", IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		map.put("transDc", IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		map.put("bankCreditName", IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		map.put("bankDebitName", IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		map.put("bankHandLePriority", IDataDictService.DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY);
		map.put("bankCreditName", IDataDictService.DATA_DICT_TYPE__BANK_NAME);
		map.put("certType", IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		map.put("CorporateCertType", IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		map.put("RegCapitalCurrency", IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		map.put("certType", IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		map.put("certType", IDataDictService.DATA_DICT_TYPE__CERT_TYPE);
		map.put("unitProperty", IDataDictService.DATA_DICT_TYPE__UNIT_PROPERTY);
		Set<String> keySet = map.keySet();
		for(String key:keySet){
			try{
				String value = map.get(key);
				col = dataDictService.list(value);
				CollectionUtils.transformCollection(c, key, key, col, "code", "name");
			}catch(Exception e){
				
			}
		}
	}
}
