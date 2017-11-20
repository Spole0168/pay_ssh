package com.ibs.core.module.mdmbasedata.service;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

/**
 * 数据字典对外服务类接口
 * 
 * @author 
 * 
 */
public interface IDataDictService {
	
	/** 卡片帐**********start**************************** */
	/** 交易方向 */
	public static final String DATA_DICT_TYPE__TRANS_DC = "TRANS_DC";
	public static final String DATA_DICT_TYPE__TRANS_DC_VALUE = "交易方向";

	/** 交易类型 */
	public static final String DATA_DICT_TYPE__TRANS_TYPE = "TRANS_TYPE";
	public static final String DATA_DICT_TYPE__TRANS_TYPE_VALUE = "交易类型";

//	/** 银行交易状态 */
//	public static final String DATA_DICT_TYPE__BANK_TRANS_STATUS = "BANK_TRANS_STATUS";
//	public static final String DATA_DICT_TYPE__BANK_TRANS_STATUS_VALUE = "银行交易状态";

	/** 银行处理优先级 */
	public static final String DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY = "BNAK_HANDLE_PRIORITY";
	public static final String DATA_DICT_TYPE__BNAK_HANDLE_PRIORITY_VALUE = "银行处理优先级";
	
	/** 入账标志 */
	public static final String DATA_DICT_TYPE__ISIN_GL = "ISIN_GL";
	public static final String DATA_DICT_TYPE__ISIN_GL_VALUE = "入账标志";
	
	/** 打印标志 */
	public static final String DATA_DICT_TYPE__IS_PRINTED = "IS_PRINTED";
	public static final String DATA_DICT_TYPE__IS_PRINTED_VALUE = "打印标志";
	
	/** 客户类型 */
	public static final String DATA_DICT_TYPE__CUST_TYPE = "CUST_TYPE";
	public static final String DATA_DICT_TYPE__CUST_TYPE_VALUE = "客户类型";
	
	/** 帐类 */
	public static final String DATA_DICT_TYPE__ACNT_TYPE = "ACNT_TYPE";
	public static final String DATA_DICT_TYPE__ACNT_TYPE_VALUE = "帐类";
	
	/** 请求类型 */
	public static final String DATA_DICT_TYPE__REQ_TYPE = "REQ_TYPE";
	public static final String DATA_DICT_TYPE__REQ_TYPE_VALUE = "请求类型";
	
	/** 记帐类型 */
	public static final String DATA_DICT_TYPE__ACCOUNT_TYPE = "ACCOUNT_TYPE";
	public static final String DATA_DICT_TYPE__ACCOUNT_TYPE_VALUE = "记帐类型";
	
	/** 失败申请单处理状态 */
	public static final String DATA_DICT_TYPE__REQ_STATUS = "REQ_STATUS";
	public static final String DATA_DICT_TYPE__REQ_STATUS_VALUE = "失败申请单处理状态";
	

	/** 交易终端类型 */
	public static final String DATA_DICT_TYPE__TERMIAL_TYPE = "TERMIAL_TYPE";
	public static final String DATA_DICT_TYPE__TERMIAL_TYPE_VALUE = "交易终端类型";
	/** 卡片帐**********end**************************** */

	
	/** 客户管理**********start**************************** */
	/** 证件类型 */
	public static final String DATA_DICT_TYPE__CERT_TYPE = "CERT_TYPE";
	public static final String DATA_DICT_TYPE__CERT_TYPE_VALUE = "证件类型";

	/** 客户状态 */
	public static final String DATA_DICT_TYPE__CUST_STATUS = "CUST_STATUS";
	public static final String DATA_DICT_TYPE__CUST_STATUS_VALUE = "客户状态";
	
	/** 国家 */
	public static final String DATA_DICT_TYPE__COUNTRY = "COUNTRY";
	public static final String DATA_DICT_TYPE__COUNTRY_VALUE = "国家";
	
//	/** 省份*/
//	public static final String DATA_DICT_TYPE__DISTRICT = "DISTRICT";
//	public static final String DATA_DICT_TYPE__DISTRICT_VALUE = "省份";
//	
//	/** 城市*/
//	public static final String DATA_DICT_TYPE__CITY = "CITY";
//	public static final String DATA_DICT_TYPE__CITY_VALUE = "城市";
	
	/** 客户级别*/
	public static final String DATA_DICT_TYPE__CUST_LEVEL = "CUST_LEVEL";
	public static final String DATA_DICT_TYPE__CUST_LEVEL_VALUE = "客户级别";
	
	/** 性别*/
	public static final String DATA_DICT_TYPE__GENDER = "GENDER";
	public static final String DATA_DICT_TYPE__GENDER_VALUE = "性别";
	
	/** 是否已婚*/
	public static final String DATA_DICT_TYPE__IS_MARIED = "IS_MARIED";
	public static final String DATA_DICT_TYPE__IS_MARIED_VALUE = "是否已婚";
	
	/** 银行卡类型*/
	public static final String DATA_DICT_TYPE__BANK_CARD_TYPE = "BANK_CARD_TYPE";
	public static final String DATA_DICT_TYPE__BANK_CARD_TYPE_VALUE = "银行卡类型";
	
	/** 客户实名认证级别*/
	public static final String DATA_DICT_TYPE__CERTIFICATION_LEVEL = "CERTIFICATION_LEVEL";
	public static final String DATA_DICT_TYPE__CERTIFICATION_LEVEL_VALUE = "客户实名认证级别";
	
	/** 最高学历*/
	public static final String DATA_DICT_TYPE__EDUCATION_LEVEL = "EDUCATION_LEVEL";
	public static final String DATA_DICT_TYPE__EDUCATION_LEVEL_VALUE = "最高学历";
	
	/** 职务*/
	public static final String DATA_DICT_TYPE__POSITION = "POSITION";
	public static final String DATA_DICT_TYPE__POSITION_VALUE = "职务";
	
	/** 职称*/
	public static final String DATA_DICT_TYPE__TITLE = "TITLE";
	public static final String DATA_DICT_TYPE__TITLE_VALUE = "职称";
	
	/** 企业性质 */
	public static final String DATA_DICT_TYPE__UNIT_PROPERTY = "UNIT_PROPERTY";
	public static final String DATA_DICT_TYPE__UNIT_PROPERTY_VALUE = "企业性质";

	/** 行业类别 */
	public static final String DATA_DICT_TYPE__INDUSTRY = "INDUSTRY";
	public static final String DATA_DICT_TYPE__INDUSTRY_VALUE = "行业类别";
	
	/** 客户管理**********end**************************** */

	/** 接口+资金汇划**********START**************************** */
	/** 申请处理状态 */
	public static final String DATA_DICT_TYPE__REQ_DO_STATUS = "REQ_DO_STATUS";
	public static final String DATA_DICT_TYPE__REQ_DO_STATUS_VALUE = "申请处理状态";
	
	/** 交易失败原因 */
	public static final String DATA_DICT_TYPE__TRADE_FAIL_REASON = "TRADE_FAIL_REASON";
	public static final String DATA_DICT_TYPE__TRADE_FAIL_REASON_VALUE = "交易失败原因";
	/** 接口+资金汇划**********END**************************** */

	/** 公共+运维管理**********START**************************** */
	/** 渠道接入类型 */
	public static final String DATA_DICT_TYPE__ACCESS_TYPE = "ACCESS_TYPE";
	public static final String DATA_DICT_TYPE__ACCESS_TYPE_VALUE = "渠道接入类型";
	
	/** 渠道状态 */
	public static final String DATA_DICT_TYPE__CNL_STATUS = "CNL_STATUS";
	public static final String DATA_DICT_TYPE__CNL_STATUS_VALUE = "渠道状态";
	
	/** 银行名称 */
	public static final String DATA_DICT_TYPE__BANK_NAME = "BANK_NAME";
	public static final String DATA_DICT_TYPE__BANK_NAME_VALUE = "银行名称";
	
	/** 联行号 */
	public static final String DATA_DICT_TYPE__BANK_NUM = "BANK_NUM";
	public static final String DATA_DICT_TYPE__BANK_NUM_VALUE = "联行号";
	/** 公共+运维管理**********END**************************** */
	
	/** 备付金**********START**************************** */
	/** 账户状态 */
	public static final String DATA_DICT_TYPE__ACNT_STATUS = "ACNT_STATUS";
	public static final String DATA_DICT_TYPE__ACNT_STATUS_VALUE = "账户状态";
	/** 备付金**********END**************************** */
	
	/** 客户端**********START**************************** */
	/** 支付方式 */
	public static final String DATA_DICT_TYPE__PAY_WAY = "PAY_WAY";
	public static final String DATA_DICT_TYPE__PAY_WAY_VALUE = "支付方式";
	
	/** 手机号注册 */
	public static final String DATA_DICT_TYPE__TELNO_REGIST = "TELNO_REGIST";
	public static final String DATA_DICT_TYPE__TELNO_REGIST_VALUE = "手机号注册";
	
	/** 密码设置 */
	public static final String DATA_DICT_TYPE__SET_PASSWORD = "SET_PASSWORD";
	public static final String DATA_DICT_TYPE__SET_PASSWORD_VALUE = "密码设置";
	
	/** 邮箱注册 */
	public static final String DATA_DICT_TYPE__MAIL_REGIST = "MAIL_REGIST";
	public static final String DATA_DICT_TYPE__MAIL_REGIST_VALUE = "邮箱注册";
	
	/** 充值转帐失败原因 */
	public static final String DATA_DICT_TYPE__IN_TRANS_FAIL_REASON = "IN_TRANS_FAIL_REASON";
	public static final String DATA_DICT_TYPE__IN_TRANS_FAIL_REASON_VALUE = "充值转帐失败原因";
	
	/** 支付密码 */
	public static final String DATA_DICT_TYPE__PAY_PASSWORD = "PAY_PASSWORD";
	public static final String DATA_DICT_TYPE__PAY_PASSWORD_VALUE = "支付密码";
	
	/** 实名级别 */
	public static final String DATA_DICT_TYPE__REAL_LEVEL = "REAL_LEVEL";
	public static final String DATA_DICT_TYPE__REAL_LEVEL_VALUE = "实名级别";
	
	/** 实名认证的渠道 */
	public static final String DATA_DICT_TYPE__REAL_CERT_CNL = "REAL_CERT_CNL";
	public static final String DATA_DICT_TYPE__REAL_CERT_CNL_VALUE = "实名认证的渠道";
	
	/** 电话类型 */
	public static final String DATA_DICT_TYPE__TEL_TYPE = "TEL_TYPE";
	public static final String DATA_DICT_TYPE__TEL_TYPE_VALUE = "电话类型";
	
	/** 客户端**********END**************************** */

	/** 币种 */
	public static final String DATA_DICT_TYPE__CURRENCY_TYPE = "CURRENCY_TYPE";
	public static final String DATA_DICT_TYPE__CURRENCY_TYPE_VALUE = "币种";
	
	/** 民族 */
	public static final String DATA_DICT_TYPE__NATION = "NATION";
	public static final String DATA_DICT_TYPE__NATION_VALUE = "民族";
	
	/** 时区 */
	public static final String DATA_DICT_TYPE__ZONE = "ZONE";
	public static final String DATA_DICT_TYPE__ZONE_VALUE = "时区";
	
	/** 交易状态 */
	public static final String DATA_DICT_TYPE__TRANS_STATUS = "TRANS_STATUS";
	public static final String DATA_DICT_TYPE__TRANS_STATUS_VALUE = "交易状态";
	
	/** 处理状态 */
	public static final String DATA_DICT_TYPE__HANDLE_STATUS = "HANDLE_STATUS";
	public static final String DATA_DICT_TYPE__HANDLE_STATUS_VALUE = "处理状态";
	
	/** 错误类型 */
	public static final String DATA_DICT_TYPE__ERROR_TYPE = "ERROR_TYPE";
	public static final String DATA_DICT_TYPE__ERROR_TYPE_VALUE = "错误类型";
	
	/** 黑名单类型 */
	public static final String DATA_DICT_TYPE__BLACKLIST_TYPE = "BLACKLIST_TYPE";
	public static final String DATA_DICT_TYPE__BLACKLIST_TYPE_VALUE = "黑名单类型";
	
	/** 黑名单状态 */
	public static final String DATA_DICT_TYPE__BLCAKLIST_STATUS = "BLCAKLIST_STATUS";
	public static final String DATA_DICT_TYPE__BLCAKLIST_STATUS_VALUE = "黑名单状态";
	
	/** 总帐户/分帐户 */
	public static final String DATA_DICT_TYPE__GENERAL_DETAIL = "GENERAL_DETAIL";
	public static final String DATA_DICT_TYPE__GENERAL_DETAIL_VALUE = "总帐户/分帐户";
	
	/** 有效/无效 */
	public static final String DATA_DICT_TYPE__IS_VALID = "IS_VALID";
	public static final String DATA_DICT_TYPE__IS_VALID_VALUE = "有效/无效";
	
	/** 异常补救申请单的处理状态 */
	public static final String DATA_DICT_TYPE__PENDING_HANDLE_STATUS = "PENDING_HANDLE_STATUS";
	public static final String DATA_DICT_TYPE__PENDING_HANDLE_STATUS_VALUE = "异常补救申请单的处理状态";
	
	/** 分行 */
	public static final String DATA_DICT_TYPE__BANK_BRANCH = "BANK_BRANCH";
	public static final String DATA_DICT_TYPE__BANK_BRANCH_VALUE = "分行";
	
	/** 支行 */
	public static final String DATA_DICT_TYPE__BANK_SUB_BRANCH = "BANK_SUB_BRANCH";
	public static final String DATA_DICT_TYPE__BANK_SUB_BRANCH_VALUE = "支行";
	
	/** 报文处理状态 */
	public static final String DATA_DICT_TYPE__MSG_HANDLE_STATUS = "MSG_HANDLE_STATUS";
	public static final String DATA_DICT_TYPE__MSG_HANDLE_STATUS_VALUE = "报文处理状态";
	
	/** 交易币种 */
	public static final String DATA_DICT_TYPE__TRANS_CURRENCY = "TRANS_CURRENCY";
	public static final String DATA_DICT_TYPE__TRANS_CURRENCY_VALUE = "交易币种";
	
	/** 收款银行 */
	public static final String DATA_DICT_TYPE__BANK_DEBIT_NAME = "BANK_DEBIT_NAME";
	public static final String DATA_DICT_TYPE__BANK_DEBIT_NAME_VALUE = "收款银行";
	
	/** 付款银行 */
	public static final String DATA_DICT_TYPE__BANK_CREFIT_NAME = "BANK_CREFIT_NAME";
	public static final String DATA_DICT_TYPE__BANK_CREFIT_NAME_VALUE = "付款银行";
	
	/** 申请单数据异常 */
	public static final String DATA_DICT_TYPE__REQ_DATA_ERR = "REQ_DATA_ERR";
	public static final String DATA_DICT_TYPE__REQ_DATA_ERR_VALUE = "申请单数据异常";
	
	/** 交易数据异常 */
	public static final String DATA_DICT_TYPE__TRANS_DATA_ERR = "TRANS_DATA_ERR";
	public static final String DATA_DICT_TYPE__TRANS_DATA_ERR_VALUE = "交易数据异常";
	
	/** 异常类型 */
	public static final String DATA_DICT_TYPE__CHECKING_EXCEPTION_TYPE = "CHECKING_EXCEPTION_TYPE";
	public static final String DATA_DICT_TYPE__CHECKING_EXCEPTION_TYPE_VALUE = "异常类型";
	
	/** 对账处理状态 */
	public static final String DATA_DICT_TYPE__CHECKING_HANDLE_STATUS = "CHECKING_HANDLE_STATUS";
	public static final String DATA_DICT_TYPE__CHECKING_HANDLE_STATUS_VALUE = "对账处理状态";
	
	/** 接口类型：银行接口-交易类型 */
	public static final String DATA_DICT_TYPE__INTERFACE_TYPE = "INTERFACE_TYPE";
	public static final String DATA_DICT_TYPE__INTERFACE_TYPE_VALUE = "接口类型";
	
	/** 错误代码 */
	public static final String DATA_DICT_TYPE__ERROR_CODE = "ERROR_CODE";
	public static final String DATA_DICT_TYPE__ERROR_CODE_VALUE = "错误代码";
	
	/** 事件来源 */
	public static final String DATA_DICT_TYPE__VIOLATION_TYPE = "VIOLATION_TYPE";
	public static final String DATA_DICT_TYPE__VIOLATION_TYPE_VALUE = "事件来源";
	
	/** 渠道客户状态 */
	public static final String DATA_DICT_TYPE__CNL_CUST_STATUS = "CNL_CUST_STATUS";
	public static final String DATA_DICT_TYPE__CNL_CUST_STATUS_VALUE = "渠道客户状态";
	
	/** 卡片账申请单状态 */
	public static final String DATA_DICT_TYPE__TRANS_REQ_STATUS = "TRANS_REQ_STATUS";
	public static final String DATA_DICT_TYPE__TRANS_REQ_STATUS_VALUE = "卡片账申请单状态";
	
	/** 支付通道 */
	public static final String DATA_DICT_TYPE__PAYMENT_CNL = "PAYMENT_CNL";
	public static final String DATA_DICT_TYPE__PAYMENT_CNL_VALUE = "支付通道";
	
	/** 异常申请操作 */
	public static final String DATA_DICT_TYPE__REQ_VIOLATION_TYPE = "REQ_VIOLATION_TYPE";
	public static final String DATA_DICT_TYPE__REQ_VIOLATION_TYPE_VALUE = "异常申请操作";
	
	/** 资金汇划失败代码 */
	public static final String DATA_DICT_TYPE__FAIL_CODE = "FAIL_CODE";
	public static final String DATA_DICT_TYPE__FAIL_CODE_VALUE = "资金汇划失败代码";
	
	/** 交易失败错误代码 */
	public static final String DATA_DICT_TYPE__BANK_TRANS_ERROR_CODE = "BANK_TRANS_ERROR_CODE";
	public static final String DATA_DICT_TYPE__BANK_TRANS_ERROR_CODE_VALUE = "交易失败错误代码";
	
	/** 钱宝银行 */
	public static final String DATA_DICT_TYPE__BANK_CODE = "BANK_CODE";
	public static final String DATA_DICT_TYPE__BANK_CODE_VALUE = "钱宝银行";
	
	/** 时间类型 */
	public static final String DATA_DICT_TYPE__TIME_TYPE = "TIME_TYPE";
	public static final String DATA_DICT_TYPE__TIME_TYPE_VALUE = "时间类型";
	
	/** 退款状态 */
	public static final String DATA_DICT_TYPE__REFUND_STATUS = "REFUND_STATUS";
	public static final String DATA_DICT_TYPE__REFUND_STATUS_VALUE = "退款状态";
	
	/** 入账处理状态 */
	public static final String DATA_DICT_TYPE__RECORD_HANDLE_STATUS = "RECORD_HANDLE_STATUS";
	public static final String DATA_DICT_TYPE__RECORD_HANDLE_STATUS_VALUE = "入账处理状态";
	
	/** 银行级别 */
	public static final String DATA_DICT_TYPE__BANK_LEVEL = "BANK_LEVEL";
	public static final String DATA_DICT_TYPE__BANK_LEVEL_VALUE = "银行级别";
	
	/** 企业开户申请 */
	public static final String DATA_DICT_TYPE__CUST_TRACE_STATUS = "CUST_TRACE_STATUS";
	public static final String DATA_DICT_TYPE__CUST_TRACE_STATUS_VALUE = "企业开户申请";
	
	/** 凭证类型 */
	public static final String DATA_DICT_TYPE__WARRANT_TYPE = "WARRANT_TYPE";
	public static final String DATA_DICT_TYPE__WARRANT_TYPE_VALUE = "凭证类型";
	
	/** 审核失败原因 */
	public static final String DATA_DICT_TYPE__FAIL_REASON = "FAIL_REASON";
	public static final String DATA_DICT_TYPE__FAIL_REASON_VALUE = "审核失败原因";
	
	/** 银行返回状态 */
	public static final String DATA_DICT_TYPE__BANK_RETURN_RESULT = "BANK_RETURN_RESULT";
	public static final String DATA_DICT_TYPE__BANK_RETURN_RESULT_VALUE = "银行返回状态";
	
	/** 备付金类型 */
	public static final String DATA_DICT_TYPE__BANK_RESERVED_TYPE = "BANK_RESERVED_TYPE";
	public static final String DATA_DICT_TYPE__BANK_RESERVED_TYPE_VALUE = "备付金类型";
	
	/** 申请单错误代码 */
	public static final String DATA_DICT_TYPE__REQ_TRANS_ERROR_CODE = "REQ_TRANS_ERROR_CODE";
	public static final String DATA_DICT_TYPE__REQ_TRANS_ERROR_CODE_VALUE = "申请单错误代码";
	
	/**
	 * 加载数据字典信息
	 * 
	 * @param id
	 *            主键 数据ID
	 * @return DataDict
	 */

	public DataDict load(String id);

	/**
	 * 查看所有的有效的数据字典数据，根据用户的语言，返回数据会有不同。
	 * 
	 * @param type
	 *            数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
	 * @return DataDict
	 */
	public List<DataDict> list(String type);

	public List<DataDict> list(String type, String language);

	/**
	 * 查看所有的有效的数据字典数据，根据用户的语言，返回数据会有不同。
	 * 
	 * @param type
	 *            数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
	 * @return List<OptionObjectPair> 用在下拉列表框
	 */
	public List<OptionObjectPair> listOptions(String type);

	/**
	 * 查看所有的有效的数据字典数据
	 * 
	 * @param type
	 *            数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
	 * @return List<OptionObjectPair> 用在下拉列表框
	 */
	public List<OptionObjectPair> listOptionsByLanguage(String type,
			String language);

	/**
	 * 根据类型和字典代码查询名称
	 * 
	 * @param type
	 *            字典类型
	 * @param code
	 *            代码
	 * @return
	 */
	public String getCodeName(String type, String code);

	/**
	 * 查看所有的有效的数据字典数据,根据用户的语言，返回数据会有不同。
	 * 
	 * @param type
	 *            数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
	 * @param defaultValue
	 *            默认值，例如“请选择” ，如果传入该参数，则会在返回结果中加入key=“” value=defaultValue的数据
	 * @return List<OptionObjectPair> 可用在下拉列表框
	 */
	public List<OptionObjectPair> listOptions(String type, String defaultValue);

	/**
	 * 查询系统支持的语言种类 key 装code ；extend装ID ；value 装name
	 * 
	 * @return
	 */
	public List<OptionObjectPair> findSupportLangeage();

	/**
	 * @param type
	 *            数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
	 * @return key 为Code value 为DataDict 的map
	 */
	public Map<String, DataDict> listMap(String type);

	public DataDict getObject(String type, String code, String language);

}
