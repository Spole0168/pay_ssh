package com.ibs.core.module.account.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ibs.core.module.account.domain.CnlReqTransDto;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

public class CnlReqTransDtoUtil {
	/**
	 * 将dto里关于个人用户部分的数据转为list
	 * 
	 * @param dto
	 * @return
	 */
	public static List<String> CnlReqTransDtoByPersonCustToList(CnlReqTransDto dto, IDataDictService dataDictService) {
		if (dto == null) {
			return null;
		}
		// 封装
		Collection<CnlReqTransDto> c = new ArrayList<CnlReqTransDto>();
		c.add(dto);
		// 数据字典转换
		DtoUtils.changeToContent(c, dataDictService);
		List<String> list = new ArrayList<String>();
		list.add("申请单流水号：");
		list.add(DtoUtils.getNotNullString(dto.getReqInnerNum()));
		list.add("渠道客户编码：");
		list.add(DtoUtils.getNotNullString(dto.getCnlCustCode()));
		list.add("申请单类别：");
		list.add(DtoUtils.getNotNullString(dto.getReqType()));
		list.add("申请单状态：");
		list.add(DtoUtils.getNotNullString(dto.getReqStatus()));
		list.add("报文时间：");
		list.add(DtoUtils.getNotNullString(dto.getMsgTime()));
		list.add("报文接收时间：");
		list.add(DtoUtils.getNotNullString(dto.getRecieveTime()));
		list.add("报文处理时间：");
		list.add(DtoUtils.getNotNullString(dto.getHandleTime()));
		list.add("渠道客户状态：");
		list.add(DtoUtils.getNotNullString(dto.getCustStatus()));
		list.add("开户时间：");
		list.add(DtoUtils.getNotNullString(dto.getCreateCustTime()));
		list.add("更新时间：");
		list.add(DtoUtils.getNotNullString(dto.getUpdateCustTime()));
		list.add("注销时间：");
		if ("2".equals(dto.getIsValid().trim())) {
			list.add(DtoUtils.getNotNullString(dto.getCnlCustCode()));
		} else {
			list.add("无");
		}
		list.add("渠道客户姓名：");
		list.add(DtoUtils.getNotNullString(dto.getLocalName()));
		list.add("出生日期：");
		list.add(DateUtils.convert(dto.getBirthday(), "yyyy年MM月dd日"));
		list.add("性别：");
		list.add(DtoUtils.getNotNullString(dto.getGender()));
		list.add("银行卡号：");
		list.add(DtoUtils.getNotNullString(dto.getBankCardNum()));
		list.add("是否已婚：");
		list.add(DtoUtils.getNotNullString(dto.getIsMarried()));
		list.add("国籍：");
		list.add(DtoUtils.getNotNullString(dto.getCountry()));
		list.add("省份：");
		list.add(DtoUtils.getNotNullString(dto.getProvience()));
		list.add("城市：");
		list.add(DtoUtils.getNotNullString(dto.getCity()));
		list.add("区：");
		list.add(DtoUtils.getNotNullString(dto.getDistrict()));
		list.add("地址：");
		list.add(DtoUtils.getNotNullString(dto.getAddr()));
		list.add("邮编：");
		list.add(DtoUtils.getNotNullString(dto.getPostcode()));
		list.add("最高学历：");
		list.add(DtoUtils.getNotNullString(dto.getHighestEdu()));
		list.add("所属行业：");
		list.add(DtoUtils.getNotNullString(dto.getIndustry()));
		list.add("职务：");
		list.add(DtoUtils.getNotNullString(dto.getJobTitle()));
		list.add("工作单位：");
		list.add(DtoUtils.getNotNullString(dto.getEmployer()));
		list.add("手机号码：");
		list.add(DtoUtils.getNotNullString(dto.getPhonenum()));
		list.add("工作电话：");
		list.add(DtoUtils.getNotNullString(dto.getWorkTel()));
		list.add("电子邮件地址：");
		list.add(DtoUtils.getNotNullString(dto.getEmail()));
		list.add("渠道信息：");
		list.add(DtoUtils.getNotNullString(dto.getCnlCnlCode()));
		list.add("配偶姓名：");
		list.add(DtoUtils.getNotNullString(dto.getSpouseLocalName()));
		list.add("配偶联系电话：");
		list.add(DtoUtils.getNotNullString(dto.getSpousePhonenum()));
		list.add("是否实名认证：");
		list.add(DtoUtils.getNotNullString(dto.getIsRealName()));
		list.add("客户实名级别：");
		list.add(DtoUtils.getNotNullString(dto.getRealNameLevel()));
		return list;
	}

	/**
	 * 将dto里关于银行明细数据转为list
	 * 
	 * @param dto
	 * @return
	 */
	public static List<String> CnlReqTransDtoByBankToList(CnlReqTransDto dto, IDataDictService dataDictService) {
		if (dto == null) {
			return null;
		}
		// 封装
		Collection<CnlReqTransDto> c = new ArrayList<CnlReqTransDto>();
		c.add(dto);
		// 数据字典转换
		DtoUtils.changeToContent(c, dataDictService);
		List<String> list = new ArrayList<String>();
		list.add("申请单流水号：");
		list.add(DtoUtils.getNotNullString(dto.getReqInnerNum()));
		list.add("渠道客户编码：");
		list.add(DtoUtils.getNotNullString(dto.getCnlCustCode()));
		list.add("申请单类别：");
		list.add(DtoUtils.getNotNullString(dto.getReqType()));
		list.add("申请单状态：");
		list.add(DtoUtils.getNotNullString(dto.getReqStatus()));
		list.add("报文时间：");
		list.add(DtoUtils.getNotNullString(dto.getMsgTime()));
		list.add("报文接收时间：");
		list.add(DtoUtils.getNotNullString(dto.getRecieveTime()));
		list.add("报文处理时间：");
		list.add(DtoUtils.getNotNullString(dto.getHandleTime()));
		list.add("接入银行网关时间：");
		list.add(DtoUtils.getNotNullString(dto.getBankAccepteTime()));
		list.add("银行交易流水号：");
		list.add(DtoUtils.getNotNullString(dto.getBankTransNum()));
		list.add("入账标记：");
		list.add(DtoUtils.getNotNullString(dto.getIsinGl()));
		list.add("银行到账时间：");
		list.add(DtoUtils.getNotNullString(dto.getBankReturnTime()));
		list.add("是否打印：");
		list.add(DtoUtils.getNotNullString(dto.getIsPrinted()));
		list.add("打印时间：");
		list.add(DtoUtils.getNotNullString(dto.getPrintedTime()));
		list.add("交易类型：");
		list.add(DtoUtils.getNotNullString(dto.getTransType()));
		list.add("交易方向：");
		list.add(DtoUtils.getNotNullString(dto.getTransDc()));
		list.add("交易币种：");
		list.add(DtoUtils.getNotNullString(dto.getTransCurrency()));
		list.add("交易金额：");
		list.add(DtoUtils.getNotNullString(dto.getTransAmount()));
		list.add("交易状态：");
		if (StringUtils.isNotEmpty(dto.getTransStatus())) {
			dto.setTransStatus(dto.getTransStatus().trim());
			if ("01".equals(dto.getTransStatus())) {
				list.add("已受理");
			} else if ("02".equals(dto.getTransStatus())) {
				list.add("处理中");
			} else if ("03".equals(dto.getTransStatus())) {
				list.add("成功");
			} else if ("04".equals(dto.getTransStatus())) {
				list.add("失败");
			} else {
				list.add("其他");
			}
		} else {
			list.add("其他");
		}
		list.add("交易日期：");
		list.add(DateUtils.convert(dto.getTransDate(), "yyyy年MM月dd日"));
		list.add("交易时间：");
		list.add(DtoUtils.getNotNullString(dto.getTransTime()));
		list.add("交易说明：");
		list.add(DtoUtils.getNotNullString(dto.getTransComments()));
		list.add("银行返回结果：");
		list.add(DtoUtils.getNotNullString(dto.getBankReturnResult()));
		list.add("支付通道类型：");
		list.add(DtoUtils.getNotNullString(dto.getBankPmtCnlType()));
		list.add("支付通道编码：");
		list.add(DtoUtils.getNotNullString(dto.getBankPmtCnlCode()));
		list.add("付款银行名称：");
		list.add(DtoUtils.getNotNullString(dto.getBankCreditName()));
		list.add("付款行开户名：");
		list.add(DtoUtils.getNotNullString(dto.getBankCreditCustName()));
		list.add("付款银行账户编码：");
		list.add(DtoUtils.getNotNullString(dto.getBankCreditAcntCode()));
		list.add("收款银行名称：");
		list.add(DtoUtils.getNotNullString(dto.getBankDebitName()));
		list.add("收款行开户名：");
		list.add(DtoUtils.getNotNullString(dto.getBankDebitCustName()));
		list.add("收款银行账户编码：");
		list.add(DtoUtils.getNotNullString(dto.getBankDebitAcntCode()));
		list.add("要求的转账日期：");
		list.add(DtoUtils.getNotNullString(DateUtils.convert(dto.getBankReqTransDate(), "yyyy年MM月dd日")));
		list.add("支付手续费费用账号：");
		list.add(DtoUtils.getNotNullString(dto.getBankServiceFeeAct()));
		list.add("银行处理优先级：");
		list.add(DtoUtils.getNotNullString(dto.getBankHandLePriority()));
		return list;
	}

	/**
	 * 将dto里关于企业用户部分的数据转为list
	 * 
	 * @param dto
	 * @return
	 */
	public static List<String> CnlReqTransDtoByCompanyCustToList(CnlReqTransDto dto, IDataDictService dataDictService) {
		if (dto == null) {
			return null;
		}
		// 封装
		Collection<CnlReqTransDto> c = new ArrayList<CnlReqTransDto>();
		c.add(dto);
		List<String> list = new ArrayList<String>();
		// 数据字典转换
		list.add("申请单流水号：");
		list.add(DtoUtils.getNotNullString(dto.getReqInnerNum()));
		list.add("渠道客户编码：");
		list.add(DtoUtils.getNotNullString(dto.getCnlCustCode()));
		list.add("申请单类型：");
		list.add(DtoUtils.getNotNullString(dto.getReqType()));
		list.add("申请单状态：");
		list.add(DtoUtils.getNotNullString(dto.getReqStatus()));
		list.add("报文时间：");
		list.add(DtoUtils.getNotNullString(dto.getMsgTime()));
		list.add("报文接收时间：");
		list.add(DtoUtils.getNotNullString(dto.getRecieveTime()));
		list.add("报文处理时间：");
		list.add(DtoUtils.getNotNullString(dto.getHandleTime()));
		list.add("渠道客户状态：");
		list.add(DtoUtils.getNotNullString(dto.getCustStatus()));
		list.add("开户时间：");
		list.add(DtoUtils.getNotNullString(dto.getRegTime()));
		list.add("更新时间：");
		list.add(DtoUtils.getNotNullString(dto.getUpdateTime()));
		list.add("注销时间：");
		if (dto.getIsValid() == "01") {
			list.add(DtoUtils.getNotNullString(dto.getUpdateTime()));
		} else {
			list.add(DtoUtils.getNotNullString("未注销"));
		}
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
		list.add(DtoUtils.getNotNullString(dto.getCorporateNum()));
		list.add("银行卡号：");
		list.add(DtoUtils.getNotNullString(dto.getBankCardNum()));
		list.add("国家：");
		list.add(DtoUtils.getNotNullString(dto.getCountry()));
		list.add("省份：");
		list.add(DtoUtils.getNotNullString(dto.getProvience()));
		list.add("城市：");
		list.add(DtoUtils.getNotNullString(dto.getCity()));
		list.add("区：");
		list.add(DtoUtils.getNotNullString(dto.getDistrict()));
		list.add("公司注册日期：");
		list.add(DtoUtils.getNotNullString(dto.getCompanyRegTime()));
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
		return list;
	}
	
	/**
	 * 将代码转为汉字
	 */
	public static void changeToContent(Collection c,IDataDictService dataDictService){
		List<DataDict> col;
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("cnlCnlCode", "cnlCnlCode");
		map.put("trans_status", IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		map.put("custType", IDataDictService.DATA_DICT_TYPE__CUST_TYPE);
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
		map.put("bankPmtCnlType", IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
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
