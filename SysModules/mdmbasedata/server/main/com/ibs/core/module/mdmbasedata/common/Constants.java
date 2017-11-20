package com.ibs.core.module.mdmbasedata.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.portal.framework.server.metadata.OptionObjectPair;


/**
 * 全局使用的常量定义
 * @author 
 *
 */
public class Constants {
	
	public final static String ACCESS_TYPE_URL= "01";//客户管理/渠道接入类型
	public final static String ACCESS_TYPE_URL_VALUE= "URL";
	public final static String ACCESS_TYPE_SDK= "02";//客户管理/渠道接入类型
	public final static String ACCESS_TYPE_SDK_VALUE= "SDK";
	public final static String ACCESS_TYPE_WEB_SERVICE= "03";//客户管理/渠道接入类型
	public final static String ACCESS_TYPE_WEB_SERVICE_VALUE= "Web 服务";
	
	public final static String ACNT_STATUS_NORMAL= "01";//备付金/账户状态
	public final static String ACNT_STATUS_NORMAL_VALUE= "正常";
	public final static String ACNT_STATUS_ABNORMAL= "02";//备付金/账户状态
	public final static String ACNT_STATUS_ABNORMAL_VALUE= "不正常";
	
	public final static String ACNT_TYPE_TRANSACTION_FEE= "01";//卡片账/帐类
	public final static String ACNT_TYPE_TRANSACTION_FEE_VALUE= "交易费";
	public final static String ACNT_TYPE_LISTING_FEE= "02";//卡片账/帐类
	public final static String ACNT_TYPE_LISTING_FEE_VALUE= "挂牌服务费";
	public final static String ACNT_TYPE_TRANSACTION_SERVICE_FEEE= "03";//卡片账/帐类
	public final static String ACNT_TYPE_TRANSACTION_SERVICE_FEEE_VALUE= "交易服务费";
	
	public final static String ADMIN_CUST_TYPE_REGULAR_USER= "01";//运维管理/用户类型
	public final static String ADMIN_CUST_TYPE_REGULAR_USER_VALUE= "一般用户";
	public final static String ADMIN_CUST_TYPE_ADMINISTRATOR= "02";//运维管理/用户类型
	public final static String ADMIN_CUST_TYPE_ADMINISTRATOR_VALUE= "管理员用户";
	
	public final static String BANK_CARD_TYPE_DEBIT_CARD= "01";//客户端/银行卡类型
	public final static String BANK_CARD_TYPE_DEBIT_CARD_VALUE= "借记卡";
	public final static String BANK_CARD_TYPE_CREDIT_CARD= "02";//客户端/银行卡类型
	public final static String BANK_CARD_TYPE_CREDIT_CARD_VALUE= "信用卡";
	
	public final static String BANK_NAME_BANK_OF_CHINA= "01";//公共管理/银行名称
	public final static String BANK_NAME_BANK_OF_CHINA_VALUE= "中国银行";
	public final static String BANK_NAME_INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA= "02";//公共管理/银行名称
	public final static String BANK_NAME_INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA_VALUE= "工商银行";
	public final static String BANK_NAME_AGRICULTURAL_BANK_OF_CHINA= "03";//公共管理/银行名称
	public final static String BANK_NAME_AGRICULTURAL_BANK_OF_CHINA_VALUE= "农业银行";
	public final static String BANK_NAME_CHINA_CONSTRUCTION_BANK= "04";//公共管理/银行名称
	public final static String BANK_NAME_CHINA_CONSTRUCTION_BANK_VALUE= "建设银行";
	public final static String BANK_NAME_PINGAN_BANK= "05";//公共管理/银行名称
	public final static String BANK_NAME_PINGAN_BANK_VALUE= "平安银行";
	public final static String BANK_NAME_CHINA_MERCHANTS_BANK= "06";//公共管理/银行名称
	public final static String BANK_NAME_CHINA_MERCHANTS_BANK_VALUE= "招商银行";
	public final static String BANK_NAME_CHINA_MINSHENG_BANK= "07";//公共管理/银行名称
	public final static String BANK_NAME_CHINA_MINSHENG_BANK_VALUE= "民生银行";
	public final static String BANK_NAME_CHINA_GUANGFA_BANK= "08";//公共管理/银行名称
	public final static String BANK_NAME_CHINA_GUANGFA_BANK_VALUE= "广发银行";
	
	public final static String BANK_NUM_INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA= "102";//公共管理/联行号
	public final static String BANK_NUM_INDUSTRIAL_AND_COMMERCIAL_BANK_OF_CHINA_VALUE= "工商银行";
	public final static String BANK_NUM_AGRICULTURAL_BANK_OF_CHINA= "103";//公共管理/联行号
	public final static String BANK_NUM_AGRICULTURAL_BANK_OF_CHINA_VALUE= "农业银行";
	public final static String BANK_NUM_BANK_OF_CHINA= "104";//公共管理/联行号
	public final static String BANK_NUM_BANK_OF_CHINA_VALUE= "中国银行";
	public final static String BANK_NUM_CHINA_CONSTRUCTION_BANK= "105";//公共管理/联行号
	public final static String BANK_NUM_CHINA_CONSTRUCTION_BANK_VALUE= "建设银行";
	public final static String BANK_NUM_CHINA_GUANGFA_BANK= "206";//公共管理/联行号
	public final static String BANK_NUM_CHINA_GUANGFA_BANK_VALUE= "广发银行";
	public final static String BANK_NUM_CHINA_MINSHENG_BANK= "305";//公共管理/联行号
	public final static String BANK_NUM_CHINA_MINSHENG_BANK_VALUE= "民生银行";
	public final static String BANK_NUM_CHINA_MERCHANTS_BANK= "308";//公共管理/联行号
	public final static String BANK_NUM_CHINA_MERCHANTS_BANK_VALUE= "招商银行";
	public final static String BANK_NUM_PINGAN_BANK= "XXX";//公共管理/联行号
	public final static String BANK_NUM_PINGAN_BANK_VALUE= "平安银行";
	
	public final static String BANK_RESPONSE_CODE_01= "01";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_01_VALUE= "B001=OK";
	public final static String BANK_RESPONSE_CODE_02= "02";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_02_VALUE= "B002=成功,未完";
	public final static String BANK_RESPONSE_CODE_03= "03";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_03_VALUE= "B003=交易数为0";
	public final static String BANK_RESPONSE_CODE_04= "04";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_04_VALUE= "B999=交易失败";
	public final static String BANK_RESPONSE_CODE_05= "05";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_05_VALUE= "B998=数据格式有误";
	public final static String BANK_RESPONSE_CODE_06= "06";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_06_VALUE= "B005=登录失败";
	public final static String BANK_RESPONSE_CODE_07= "07";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_07_VALUE= "B006=用户名或密码无效";
	public final static String BANK_RESPONSE_CODE_08= "08";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_08_VALUE= "B007=用户权限无效";
	public final static String BANK_RESPONSE_CODE_09= "09";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_09_VALUE= "B008=新密码为空，与旧密码相同或不符合格式要求";
	public final static String BANK_RESPONSE_CODE_10= "10";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_10_VALUE= "B009=更新密码失败";
	public final static String BANK_RESPONSE_CODE_11= "11";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_11_VALUE= "B010=报文版本错误";
	public final static String BANK_RESPONSE_CODE_12= "12";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_12_VALUE= "B011=分行地址有误";
	public final static String BANK_RESPONSE_CODE_13= "13";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_13_VALUE= "B012=与分行连接失败";
	public final static String BANK_RESPONSE_CODE_14= "14";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_14_VALUE= "B013=与分行通信故障";
	public final static String BANK_RESPONSE_CODE_15= "15";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_15_VALUE= "B014=分行非联机行";
	public final static String BANK_RESPONSE_CODE_16= "16";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_16_VALUE= "B015=分行返回结果为空";
	public final static String BANK_RESPONSE_CODE_17= "17";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_17_VALUE= "B016=分行返回数据格式错误";
	public final static String BANK_RESPONSE_CODE_18= "18";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_18_VALUE= "B020=查询余额故障";
	public final static String BANK_RESPONSE_CODE_19= "19";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_19_VALUE= "B021=查询交易故障";
	public final static String BANK_RESPONSE_CODE_20= "20";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_20_VALUE= "B022=到账查询故障,或数据库数据错误";
	public final static String BANK_RESPONSE_CODE_21= "21";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_21_VALUE= "B023=交易状态查询故障";
	public final static String BANK_RESPONSE_CODE_22= "22";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_22_VALUE= "B024=账户不存在";
	public final static String BANK_RESPONSE_CODE_23= "23";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_23_VALUE= "B025=账户已销户";
	public final static String BANK_RESPONSE_CODE_24= "24";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_24_VALUE= "B026=账户余额不足";
	public final static String BANK_RESPONSE_CODE_25= "25";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_25_VALUE= "B027=账户已冻结";
	public final static String BANK_RESPONSE_CODE_26= "26";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_26_VALUE= "B028=账户无交易";
	public final static String BANK_RESPONSE_CODE_27= "27";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_27_VALUE= "B029=会计系统错误";
	public final static String BANK_RESPONSE_CODE_28= "28";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_28_VALUE= "B030=付款成功未到账";
	public final static String BANK_RESPONSE_CODE_29= "29";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_29_VALUE= "B031=到账查询中国银行处理完毕，请查询收款行是否到账";
	public final static String BANK_RESPONSE_CODE_30= "30";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_30_VALUE= "B032=到账查询收款人账号或收款人联行号错误，不能查询";
	public final static String BANK_RESPONSE_CODE_31= "31";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_31_VALUE= "B033=到账查询金额不符";
	public final static String BANK_RESPONSE_CODE_32= "32";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_32_VALUE= "B034=收款账号错误无法入账";
	public final static String BANK_RESPONSE_CODE_33= "33";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_33_VALUE= "B050=付款账号未在网银系统维护,请修改";
	public final static String BANK_RESPONSE_CODE_34= "34";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_34_VALUE= "B051=交易已存在,请修改insid";
	public final static String BANK_RESPONSE_CODE_35= "35";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_35_VALUE= "B052=交易不存在或银行未处理";
	public final static String BANK_RESPONSE_CODE_36= "36";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_36_VALUE= "B053=交易失败被银行退回";
	public final static String BANK_RESPONSE_CODE_37= "37";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_37_VALUE= "B054=交易处理中";
	public final static String BANK_RESPONSE_CODE_38= "38";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_38_VALUE= "B055=请求交易重发";
	public final static String BANK_RESPONSE_CODE_39= "39";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_39_VALUE= "B056=数字签名校验失败";
	public final static String BANK_RESPONSE_CODE_40= "40";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_40_VALUE= "B057=数据库访问失败";
	public final static String BANK_RESPONSE_CODE_41= "41";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_41_VALUE= "B058=更新数据库失败";
	public final static String BANK_RESPONSE_CODE_42= "42";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_42_VALUE= "B059=交易提交成功";
	public final static String BANK_RESPONSE_CODE_43= "43";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_43_VALUE= "B060=查询不到付款联行号对应的省行联行号,请修改";
	public final static String BANK_RESPONSE_CODE_44= "44";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_44_VALUE= "B061=付款账号或者手续费账号有误";
	public final static String BANK_RESPONSE_CODE_45= "45";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_45_VALUE= "B062=付款账号或者手续费账号币种与交易币种不一致,请修改";
	public final static String BANK_RESPONSE_CODE_46= "46";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_46_VALUE= "B063=收款账号币种与交易币种不一致,请修改";
	public final static String BANK_RESPONSE_CODE_47= "47";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_47_VALUE= "B064=收款账号在收款人记录中不存在,请修改";
	public final static String BANK_RESPONSE_CODE_48= "48";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_48_VALUE= "B065=收款行名称为空且无法查询,请修改";
	public final static String BANK_RESPONSE_CODE_49= "49";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_49_VALUE= "B066=交易金额辅币位数与交易货币不一致,请修改";
	public final static String BANK_RESPONSE_CODE_50= "50";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_50_VALUE= "B067=指定付款日期不能早于系统当前日期且不能晚于系统当前时间一个月";
	public final static String BANK_RESPONSE_CODE_51= "51";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_51_VALUE= "B068=开始和截止日期的间隔不能超过一个月,请修改";
	public final static String BANK_RESPONSE_CODE_52= "52";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_52_VALUE= "B069=查询不到收款联行号对应的省行联行号,请修改";
	public final static String BANK_RESPONSE_CODE_53= "53";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_53_VALUE= "B070=收款人维护操作类型错误";
	public final static String BANK_RESPONSE_CODE_54= "54";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_54_VALUE= "B071=收款人已存在";
	public final static String BANK_RESPONSE_CODE_55= "55";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_55_VALUE= "B072=收款人编号已存在";
	public final static String BANK_RESPONSE_CODE_56= "56";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_56_VALUE= "B073=收款人记录不存在";
	public final static String BANK_RESPONSE_CODE_57= "57";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_57_VALUE= "B074=操作员对付款账号无此功能权限,请修改";
	public final static String BANK_RESPONSE_CODE_58= "58";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_58_VALUE= "B075=支付金额超出转账日限额(或市场细分日限额),请修改";
	public final static String BANK_RESPONSE_CODE_59= "59";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_59_VALUE= "B076=操作员对该账号无此功能权限,请修改";
	public final static String BANK_RESPONSE_CODE_60= "60";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_60_VALUE= "B077=操作员未开通此功能权限,请修改";
	public final static String BANK_RESPONSE_CODE_61= "61";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_61_VALUE= "B078=根据payeeid无法查询确定收款人";
	public final static String BANK_RESPONSE_CODE_62= "62";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_62_VALUE= "B079=支付金额超出转账周期额度,请修改";
	public final static String BANK_RESPONSE_CODE_63= "63";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_63_VALUE= "B080=支付金额超出转账笔额度,请修改";
	public final static String BANK_RESPONSE_CODE_64= "64";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_64_VALUE= "B081=填写中行收款标志时需填写中行联行号,填写他行收款标志时需填写";
	public final static String BANK_RESPONSE_CODE_65= "65";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_65_VALUE= "B082=手续费账号未在网银系统维护,请修改";
	public final static String BANK_RESPONSE_CODE_66= "66";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_66_VALUE= "B083=操作员对手续费账号无此功能权限,请修改";
	public final static String BANK_RESPONSE_CODE_67= "67";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_67_VALUE= "B084=填写的cnaps号与收款行名不一致,请修改";
	public final static String BANK_RESPONSE_CODE_68= "68";//银行返回代码/银行返回代码
	public final static String BANK_RESPONSE_CODE_68_VALUE= "B085=统一对外支付暂停";
	
	public final static String BANK_TRANS_ERROR_CODE_01= "01";//卡片账/交易失败错误代码
	public final static String BANK_TRANS_ERROR_CODE_01_VALUE= "00001";
	public final static String BANK_TRANS_ERROR_CODE_02= "02";//卡片账/交易失败错误代码
	public final static String BANK_TRANS_ERROR_CODE_02_VALUE= "00002";
	public final static String BANK_TRANS_ERROR_TYPE_BENEFICIARY_ACCOUNT_NOT_EXIST= "01";//资金汇划/资金汇划错误类型
	public final static String BANK_TRANS_ERROR_TYPE_BENEFICIARY_ACCOUNT_NOT_EXIST_VALUE= "收款账户不存在";
	public final static String BANK_TRANS_ERROR_TYPE_PAYING_ACCOUNT_NO_EXIST= "02";//资金汇划/资金汇划错误类型
	public final static String BANK_TRANS_ERROR_TYPE_PAYING_ACCOUNT_NO_EXIST_VALUE= "付款账户不存在";
	public final static String BANK_TRANS_ERROR_TYPE_PAYING_ACCOUNT_BALANCE_NOT_ENOUGH= "03";//资金汇划/资金汇划错误类型
	public final static String BANK_TRANS_ERROR_TYPE_PAYING_ACCOUNT_BALANCE_NOT_ENOUGH_VALUE= "付款账户余额不足";
	public final static String BANK_TRANS_ERROR_TYPE_ABNORMAL_PAYING_ACCOUNT_= "04";//资金汇划/资金汇划错误类型
	public final static String BANK_TRANS_ERROR_TYPE_ABNORMAL_PAYING_ACCOUNT__VALUE= "付款账户状态不正常";
	public final static String BANK_TRANS_ERROR_TYPE_ABNORMAL_BENEFICIARY_ACCOUNT_= "05";//资金汇划/资金汇划错误类型
	public final static String BANK_TRANS_ERROR_TYPE_ABNORMAL_BENEFICIARY_ACCOUNT__VALUE= "收款账户状态不正常";
	
	public final static String BLACKLIST_TYPE_EMAIL= "01";//风控/黑名单类型
	public final static String BLACKLIST_TYPE_EMAIL_VALUE= "电子邮件";
	public final static String BLACKLIST_TYPE_CELLPHONE= "02";//风控/黑名单类型
	public final static String BLACKLIST_TYPE_CELLPHONE_VALUE= "手机号码";
	public final static String BLACKLIST_TYPE_BANK_CARD_NO= "03";//风控/黑名单类型
	public final static String BLACKLIST_TYPE_BANK_CARD_NO_VALUE= "银行卡号";
	public final static String BLACKLIST_TYPE_HIGH_RISK_COUNTRY= "04";//风控/黑名单类型
	public final static String BLACKLIST_TYPE_HIGH_RISK_COUNTRY_VALUE= "高风险国家";
	
	public final static String BLCAKLIST_STATUS_WAITING= "01";//风控/黑名单状态
	public final static String BLCAKLIST_STATUS_WAITING_VALUE= "待审核";
	public final static String BLCAKLIST_STATUS_CHECKED= "02";//风控/黑名单状态
	public final static String BLCAKLIST_STATUS_CHECKED_VALUE= "已审核";
	public final static String BLCAKLIST_STATUS_REJECT= "03";//风控/黑名单状态
	public final static String BLCAKLIST_STATUS_REJECT_VALUE= "拒绝";
	
	public final static String BNAK_HANDLE_PRIORITY_URGENT= "01";//卡片账/银行处理优先级
	public final static String BNAK_HANDLE_PRIORITY_URGENT_VALUE= "加急";
	public final static String BNAK_HANDLE_PRIORITY_REGULAR= "02";//卡片账/银行处理优先级
	public final static String BNAK_HANDLE_PRIORITY_REGULAR_VALUE= "普通";
	
	public final static String CERTIFICATION_LEVEL_UNAUTHENTICATED= "01";//渠道客户管理/实名级别
	public final static String CERTIFICATION_LEVEL_UNAUTHENTICATED_VALUE= "未实名";
	public final static String CERTIFICATION_LEVEL_LEVEL_1= "02";//渠道客户管理/实名级别
	public final static String CERTIFICATION_LEVEL_LEVEL_1_VALUE= "一级";
	public final static String CERTIFICATION_LEVEL_LEVEL_2= "03";//渠道客户管理/实名级别
	public final static String CERTIFICATION_LEVEL_LEVEL_2_VALUE= "二级";
	public final static String CERTIFICATION_LEVEL_LEVEL_3= "04";//渠道客户管理/实名级别
	public final static String CERTIFICATION_LEVEL_LEVEL_3_VALUE= "三级";
	
	public final static String CERT_TYPE_ID= "01";//客户端/证件类型
	public final static String CERT_TYPE_ID_VALUE= "身份证";
	public final static String CERT_TYPE_OFFICER_LICENSE= "02";//客户端/证件类型
	public final static String CERT_TYPE_OFFICER_LICENSE_VALUE= "军官证";
	public final static String CERT_TYPE_CIVIL_OFFICER_LICENSE= "03";//客户端/证件类型
	public final static String CERT_TYPE_CIVIL_OFFICER_LICENSE_VALUE= "解放军文职干部证";
	public final static String CERT_TYPE_POLICE_OFFICER_LICENSE= "04";//客户端/证件类型
	public final static String CERT_TYPE_POLICE_OFFICER_LICENSE_VALUE= "警官证";
	public final static String CERT_TYPE_SOLDIER_LICENSE= "05";//客户端/证件类型
	public final static String CERT_TYPE_SOLDIER_LICENSE_VALUE= "解放军士兵证";
	public final static String CERT_TYPE_RESIDENCE_LICENSE= "06";//客户端/证件类型
	public final static String CERT_TYPE_RESIDENCE_LICENSE_VALUE= "户口簿";
	public final static String CERT_TYPE_HONGKONG_MACAULAISSEZ_PASSER= "07";//客户端/证件类型
	public final static String CERT_TYPE_HONGKONG_MACAULAISSEZ_PASSER_VALUE= "(港澳)回乡证-通行证";
	public final static String CERT_TYPE_TAIWAN_PASSER= "08";//客户端/证件类型
	public final static String CERT_TYPE_TAIWAN_PASSER_VALUE= "台通行证-其他有效旅行证";
	public final static String CERT_TYPE_PASSPORT_FOREIGN= "09";//客户端/证件类型
	public final static String CERT_TYPE_PASSPORT_FOREIGN_VALUE= "(外国)护照";
	public final static String CERT_TYPE_PASSPORT_CHINA= "10";//客户端/证件类型
	public final static String CERT_TYPE_PASSPORT_CHINA_VALUE= "(中国)护照";
	public final static String CERT_TYPE_POLICE_CIVIL_LICENSE= "11";//客户端/证件类型
	public final static String CERT_TYPE_POLICE_CIVIL_LICENSE_VALUE= "武警文职干部证";
	public final static String CERT_TYPE_POLICE_LICENSE= "12";//客户端/证件类型
	public final static String CERT_TYPE_POLICE_LICENSE_VALUE= "武警士兵证";
	public final static String CERT_TYPE_INSTITUTION_CODE= "13";//客户端/证件类型
	public final static String CERT_TYPE_INSTITUTION_CODE_VALUE= "全国组织机构代码";
	public final static String CERT_TYPE_FOREIGN_CUSTOMER_CODE= "14";//客户端/证件类型
	public final static String CERT_TYPE_FOREIGN_CUSTOMER_CODE_VALUE= "海外客户编号";
	public final static String CERT_TYPE_MERCHANT_LICENSE= "15";//客户端/证件类型
	public final static String CERT_TYPE_MERCHANT_LICENSE_VALUE= "营业执照号码";
	public final static String CERT_TYPE_PUBLIC_MERCHANT_LICENSE= "16";//客户端/证件类型
	public final static String CERT_TYPE_PUBLIC_MERCHANT_LICENSE_VALUE= "对公营业执照号码";
	public final static String CERT_TYPE_OTHERS= "17";//客户端/证件类型
	public final static String CERT_TYPE_OTHERS_VALUE= "其他";
	
	public final static String CHECKING_EXCEPTION_TYPE_AMOUNT_NOT_EQUAL= "01";//资金汇划/异常类型
	public final static String CHECKING_EXCEPTION_TYPE_AMOUNT_NOT_EQUAL_VALUE= "金额不一致";
	public final static String CHECKING_EXCEPTION_TYPE_BANK_TRANSACTION_RECORD_MISSING= "02";//资金汇划/异常类型
	public final static String CHECKING_EXCEPTION_TYPE_BANK_TRANSACTION_RECORD_MISSING_VALUE= "银行交易流水缺失";
	public final static String CHECKING_EXCEPTION_TYPE_PLATFORM_TRANSACTION_RECORD_MISSING= "03";//资金汇划/异常类型
	public final static String CHECKING_EXCEPTION_TYPE_PLATFORM_TRANSACTION_RECORD_MISSING_VALUE= "平台交易流水缺失";
	
	public final static String CHECKING_HANDLE_STATUS_UNPROCESSED= "01";//资金汇划/对账异常处理状态
	public final static String CHECKING_HANDLE_STATUS_UNPROCESSED_VALUE= "未处理";
	public final static String CHECKING_HANDLE_STATUS_PROCESSED= "02";//资金汇划/对账异常处理状态
	public final static String CHECKING_HANDLE_STATUS_PROCESSED_VALUE= "已处理";
	
	public final static String CLIENT_TRANS_FAIL_REASON_BALANCE_NOT_ENOUGHTOP_UP_FAILED= "01";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_BALANCE_NOT_ENOUGHTOP_UP_FAILED_VALUE= "卡内余额不足，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_02= "02";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_02_VALUE= "充值金额超过银行单笔限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_03= "03";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_03_VALUE= "充值金额超过银行单日限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_04= "04";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_04_VALUE= "充值金额超过银行单月限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_05= "05";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_05_VALUE= "转帐金额超过银行单笔限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_06= "06";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_06_VALUE= "转帐金额超过银行单日限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_07= "07";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_07_VALUE= "转帐金额超过银行单月限额，充值失败";
	public final static String CLIENT_TRANS_FAIL_REASON_08= "08";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_08_VALUE= "银行卡信息无效，转帐失败";
	public final static String CLIENT_TRANS_FAIL_REASON_09= "09";//客户端/充值转帐失败原因
	public final static String CLIENT_TRANS_FAIL_REASON_09_VALUE= "银行卡不支持所选币种，充值失败";
	
	public final static String CNL_CUST_STATUS_PASSED= "01";//卡片账/渠道客户状态
	public final static String CNL_CUST_STATUS_PASSED_VALUE= "已通过";
	public final static String CNL_CUST_STATUS_WRITTEN_OFF= "02";//卡片账/渠道客户状态
	public final static String CNL_CUST_STATUS_WRITTEN_OFF_VALUE= "已注销";
	
	public final static String CNL_STATUS_VALID= "01";//公共管理/渠道状态
	public final static String CNL_STATUS_VALID_VALUE= "有效";
	public final static String CNL_STATUS_NOT_VALID= "02";//公共管理/渠道状态
	public final static String CNL_STATUS_NOT_VALID_VALUE= "无效";
	
	public final static String COUNTRY_01= "01";//国家/国家
	public final static String COUNTRY_01_VALUE= "安哥拉";
	public final static String COUNTRY_02= "02";//国家/国家
	public final static String COUNTRY_02_VALUE= "阿富汗";
	public final static String COUNTRY_03= "03";//国家/国家
	public final static String COUNTRY_03_VALUE= "阿尔巴尼亚";
	public final static String COUNTRY_04= "04";//国家/国家
	public final static String COUNTRY_04_VALUE= "阿尔及利亚";
	public final static String COUNTRY_05= "05";//国家/国家
	public final static String COUNTRY_05_VALUE= "安道尔共和国";
	public final static String COUNTRY_06= "06";//国家/国家
	public final static String COUNTRY_06_VALUE= "安圭拉岛";
	public final static String COUNTRY_07= "07";//国家/国家
	public final static String COUNTRY_07_VALUE= "安提瓜和巴布达";
	public final static String COUNTRY_08= "08";//国家/国家
	public final static String COUNTRY_08_VALUE= "阿根廷";
	public final static String COUNTRY_09= "09";//国家/国家
	public final static String COUNTRY_09_VALUE= "亚美尼亚";
	public final static String COUNTRY_10= "10";//国家/国家
	public final static String COUNTRY_10_VALUE= "阿森松";
	public final static String COUNTRY_100= "100";//国家/国家
	public final static String COUNTRY_100_VALUE= "澳门";
	public final static String COUNTRY_101= "101";//国家/国家
	public final static String COUNTRY_101_VALUE= "马达加斯加";
	public final static String COUNTRY_102= "102";//国家/国家
	public final static String COUNTRY_102_VALUE= "马拉维";
	public final static String COUNTRY_103= "103";//国家/国家
	public final static String COUNTRY_103_VALUE= "马来西亚";
	public final static String COUNTRY_104= "104";//国家/国家
	public final static String COUNTRY_104_VALUE= "马尔代夫";
	public final static String COUNTRY_105= "105";//国家/国家
	public final static String COUNTRY_105_VALUE= "马里";
	public final static String COUNTRY_106= "106";//国家/国家
	public final static String COUNTRY_106_VALUE= "马耳他";
	public final static String COUNTRY_107= "107";//国家/国家
	public final static String COUNTRY_107_VALUE= "马里亚那群岛";
	public final static String COUNTRY_108= "108";//国家/国家
	public final static String COUNTRY_108_VALUE= "马提尼克";
	public final static String COUNTRY_109= "109";//国家/国家
	public final static String COUNTRY_109_VALUE= "毛里求斯";
	public final static String COUNTRY_11= "11";//国家/国家
	public final static String COUNTRY_11_VALUE= "澳大利亚";
	public final static String COUNTRY_110= "110";//国家/国家
	public final static String COUNTRY_110_VALUE= "墨西哥";
	public final static String COUNTRY_111= "111";//国家/国家
	public final static String COUNTRY_111_VALUE= "摩尔多瓦";
	public final static String COUNTRY_112= "112";//国家/国家
	public final static String COUNTRY_112_VALUE= "摩纳哥";
	public final static String COUNTRY_113= "113";//国家/国家
	public final static String COUNTRY_113_VALUE= "蒙古";
	public final static String COUNTRY_114= "114";//国家/国家
	public final static String COUNTRY_114_VALUE= "蒙特塞拉特岛";
	public final static String COUNTRY_115= "115";//国家/国家
	public final static String COUNTRY_115_VALUE= "摩洛哥";
	public final static String COUNTRY_116= "116";//国家/国家
	public final static String COUNTRY_116_VALUE= "莫桑比克";
	public final static String COUNTRY_117= "117";//国家/国家
	public final static String COUNTRY_117_VALUE= "纳米比亚";
	public final static String COUNTRY_118= "118";//国家/国家
	public final static String COUNTRY_118_VALUE= "瑙鲁";
	public final static String COUNTRY_119= "119";//国家/国家
	public final static String COUNTRY_119_VALUE= "尼泊尔";
	public final static String COUNTRY_12= "12";//国家/国家
	public final static String COUNTRY_12_VALUE= "奥地利";
	public final static String COUNTRY_120= "120";//国家/国家
	public final static String COUNTRY_120_VALUE= "荷属安的列斯";
	public final static String COUNTRY_121= "121";//国家/国家
	public final static String COUNTRY_121_VALUE= "荷兰";
	public final static String COUNTRY_122= "122";//国家/国家
	public final static String COUNTRY_122_VALUE= "新西兰";
	public final static String COUNTRY_123= "123";//国家/国家
	public final static String COUNTRY_123_VALUE= "尼加拉瓜";
	public final static String COUNTRY_124= "124";//国家/国家
	public final static String COUNTRY_124_VALUE= "尼日尔";
	public final static String COUNTRY_125= "125";//国家/国家
	public final static String COUNTRY_125_VALUE= "尼日利亚";
	public final static String COUNTRY_126= "126";//国家/国家
	public final static String COUNTRY_126_VALUE= "朝鲜";
	public final static String COUNTRY_127= "127";//国家/国家
	public final static String COUNTRY_127_VALUE= "挪威";
	public final static String COUNTRY_128= "128";//国家/国家
	public final static String COUNTRY_128_VALUE= "阿曼";
	public final static String COUNTRY_129= "129";//国家/国家
	public final static String COUNTRY_129_VALUE= "巴基斯坦";
	public final static String COUNTRY_13= "13";//国家/国家
	public final static String COUNTRY_13_VALUE= "阿塞拜疆";
	public final static String COUNTRY_130= "130";//国家/国家
	public final static String COUNTRY_130_VALUE= "巴拿马";
	public final static String COUNTRY_131= "131";//国家/国家
	public final static String COUNTRY_131_VALUE= "巴布亚新几内亚";
	public final static String COUNTRY_132= "132";//国家/国家
	public final static String COUNTRY_132_VALUE= "巴拉圭";
	public final static String COUNTRY_133= "133";//国家/国家
	public final static String COUNTRY_133_VALUE= "秘鲁";
	public final static String COUNTRY_134= "134";//国家/国家
	public final static String COUNTRY_134_VALUE= "菲律宾";
	public final static String COUNTRY_135= "135";//国家/国家
	public final static String COUNTRY_135_VALUE= "波兰";
	public final static String COUNTRY_136= "136";//国家/国家
	public final static String COUNTRY_136_VALUE= "法属玻利尼西亚";
	public final static String COUNTRY_137= "137";//国家/国家
	public final static String COUNTRY_137_VALUE= "葡萄牙";
	public final static String COUNTRY_138= "138";//国家/国家
	public final static String COUNTRY_138_VALUE= "波多黎各";
	public final static String COUNTRY_139= "139";//国家/国家
	public final static String COUNTRY_139_VALUE= "卡塔尔";
	public final static String COUNTRY_14= "14";//国家/国家
	public final static String COUNTRY_14_VALUE= "巴哈马";
	public final static String COUNTRY_140= "140";//国家/国家
	public final static String COUNTRY_140_VALUE= "留尼旺";
	public final static String COUNTRY_141= "141";//国家/国家
	public final static String COUNTRY_141_VALUE= "罗马尼亚";
	public final static String COUNTRY_142= "142";//国家/国家
	public final static String COUNTRY_142_VALUE= "俄罗斯";
	public final static String COUNTRY_143= "143";//国家/国家
	public final static String COUNTRY_143_VALUE= "圣卢西亚";
	public final static String COUNTRY_144= "144";//国家/国家
	public final static String COUNTRY_144_VALUE= "圣文森特岛";
	public final static String COUNTRY_145= "145";//国家/国家
	public final static String COUNTRY_145_VALUE= "东萨摩亚(美)";
	public final static String COUNTRY_146= "146";//国家/国家
	public final static String COUNTRY_146_VALUE= "西萨摩亚";
	public final static String COUNTRY_147= "147";//国家/国家
	public final static String COUNTRY_147_VALUE= "圣马力诺";
	public final static String COUNTRY_148= "148";//国家/国家
	public final static String COUNTRY_148_VALUE= "圣多美和普林西比";
	public final static String COUNTRY_149= "149";//国家/国家
	public final static String COUNTRY_149_VALUE= "沙特阿拉伯";
	public final static String COUNTRY_15= "15";//国家/国家
	public final static String COUNTRY_15_VALUE= "巴林";
	public final static String COUNTRY_150= "150";//国家/国家
	public final static String COUNTRY_150_VALUE= "塞内加尔";
	public final static String COUNTRY_151= "151";//国家/国家
	public final static String COUNTRY_151_VALUE= "塞舌尔";
	public final static String COUNTRY_152= "152";//国家/国家
	public final static String COUNTRY_152_VALUE= "塞拉利昂";
	public final static String COUNTRY_153= "153";//国家/国家
	public final static String COUNTRY_153_VALUE= "新加坡";
	public final static String COUNTRY_154= "154";//国家/国家
	public final static String COUNTRY_154_VALUE= "斯洛伐克";
	public final static String COUNTRY_155= "155";//国家/国家
	public final static String COUNTRY_155_VALUE= "斯洛文尼亚";
	public final static String COUNTRY_156= "156";//国家/国家
	public final static String COUNTRY_156_VALUE= "所罗门群岛";
	public final static String COUNTRY_157= "157";//国家/国家
	public final static String COUNTRY_157_VALUE= "索马里";
	public final static String COUNTRY_158= "158";//国家/国家
	public final static String COUNTRY_158_VALUE= "南非";
	public final static String COUNTRY_159= "159";//国家/国家
	public final static String COUNTRY_159_VALUE= "西班牙";
	public final static String COUNTRY_16= "16";//国家/国家
	public final static String COUNTRY_16_VALUE= "孟加拉国";
	public final static String COUNTRY_160= "160";//国家/国家
	public final static String COUNTRY_160_VALUE= "斯里兰卡";
	public final static String COUNTRY_161= "161";//国家/国家
	public final static String COUNTRY_161_VALUE= "圣卢西亚";
	public final static String COUNTRY_162= "162";//国家/国家
	public final static String COUNTRY_162_VALUE= "圣文森特";
	public final static String COUNTRY_163= "163";//国家/国家
	public final static String COUNTRY_163_VALUE= "苏丹";
	public final static String COUNTRY_164= "164";//国家/国家
	public final static String COUNTRY_164_VALUE= "苏里南";
	public final static String COUNTRY_165= "165";//国家/国家
	public final static String COUNTRY_165_VALUE= "斯威士兰";
	public final static String COUNTRY_166= "166";//国家/国家
	public final static String COUNTRY_166_VALUE= "瑞典";
	public final static String COUNTRY_167= "167";//国家/国家
	public final static String COUNTRY_167_VALUE= "瑞士";
	public final static String COUNTRY_168= "168";//国家/国家
	public final static String COUNTRY_168_VALUE= "叙利亚";
	public final static String COUNTRY_169= "169";//国家/国家
	public final static String COUNTRY_169_VALUE= "台湾省";
	public final static String COUNTRY_17= "17";//国家/国家
	public final static String COUNTRY_17_VALUE= "巴巴多斯";
	public final static String COUNTRY_170= "170";//国家/国家
	public final static String COUNTRY_170_VALUE= "塔吉克斯坦";
	public final static String COUNTRY_171= "171";//国家/国家
	public final static String COUNTRY_171_VALUE= "坦桑尼亚";
	public final static String COUNTRY_172= "172";//国家/国家
	public final static String COUNTRY_172_VALUE= "泰国";
	public final static String COUNTRY_173= "173";//国家/国家
	public final static String COUNTRY_173_VALUE= "多哥";
	public final static String COUNTRY_174= "174";//国家/国家
	public final static String COUNTRY_174_VALUE= "汤加";
	public final static String COUNTRY_175= "175";//国家/国家
	public final static String COUNTRY_175_VALUE= "特立尼达和多巴哥";
	public final static String COUNTRY_176= "176";//国家/国家
	public final static String COUNTRY_176_VALUE= "突尼斯";
	public final static String COUNTRY_177= "177";//国家/国家
	public final static String COUNTRY_177_VALUE= "土耳其";
	public final static String COUNTRY_178= "178";//国家/国家
	public final static String COUNTRY_178_VALUE= "土库曼斯坦";
	public final static String COUNTRY_179= "179";//国家/国家
	public final static String COUNTRY_179_VALUE= "乌干达";
	public final static String COUNTRY_18= "18";//国家/国家
	public final static String COUNTRY_18_VALUE= "白俄罗斯";
	public final static String COUNTRY_180= "180";//国家/国家
	public final static String COUNTRY_180_VALUE= "乌克兰";
	public final static String COUNTRY_181= "181";//国家/国家
	public final static String COUNTRY_181_VALUE= "阿拉伯联合酋长国";
	public final static String COUNTRY_182= "182";//国家/国家
	public final static String COUNTRY_182_VALUE= "英国";
	public final static String COUNTRY_183= "183";//国家/国家
	public final static String COUNTRY_183_VALUE= "美国";
	public final static String COUNTRY_184= "184";//国家/国家
	public final static String COUNTRY_184_VALUE= "乌拉圭";
	public final static String COUNTRY_185= "185";//国家/国家
	public final static String COUNTRY_185_VALUE= "乌兹别克斯坦";
	public final static String COUNTRY_186= "186";//国家/国家
	public final static String COUNTRY_186_VALUE= "委内瑞拉";
	public final static String COUNTRY_187= "187";//国家/国家
	public final static String COUNTRY_187_VALUE= "越南";
	public final static String COUNTRY_188= "188";//国家/国家
	public final static String COUNTRY_188_VALUE= "也门";
	public final static String COUNTRY_189= "189";//国家/国家
	public final static String COUNTRY_189_VALUE= "南斯拉夫";
	public final static String COUNTRY_19= "19";//国家/国家
	public final static String COUNTRY_19_VALUE= "比利时";
	public final static String COUNTRY_190= "190";//国家/国家
	public final static String COUNTRY_190_VALUE= "津巴布韦";
	public final static String COUNTRY_191= "191";//国家/国家
	public final static String COUNTRY_191_VALUE= "扎伊尔";
	public final static String COUNTRY_192= "192";//国家/国家
	public final static String COUNTRY_192_VALUE= "赞比亚";
	public final static String COUNTRY_20= "20";//国家/国家
	public final static String COUNTRY_20_VALUE= "伯利兹";
	public final static String COUNTRY_21= "21";//国家/国家
	public final static String COUNTRY_21_VALUE= "贝宁";
	public final static String COUNTRY_22= "22";//国家/国家
	public final static String COUNTRY_22_VALUE= "百慕大群岛";
	public final static String COUNTRY_23= "23";//国家/国家
	public final static String COUNTRY_23_VALUE= "玻利维亚";
	public final static String COUNTRY_24= "24";//国家/国家
	public final static String COUNTRY_24_VALUE= "博茨瓦纳";
	public final static String COUNTRY_25= "25";//国家/国家
	public final static String COUNTRY_25_VALUE= "巴西";
	public final static String COUNTRY_26= "26";//国家/国家
	public final static String COUNTRY_26_VALUE= "文莱";
	public final static String COUNTRY_27= "27";//国家/国家
	public final static String COUNTRY_27_VALUE= "保加利亚";
	public final static String COUNTRY_28= "28";//国家/国家
	public final static String COUNTRY_28_VALUE= "布基纳法索";
	public final static String COUNTRY_29= "29";//国家/国家
	public final static String COUNTRY_29_VALUE= "缅甸";
	public final static String COUNTRY_30= "30";//国家/国家
	public final static String COUNTRY_30_VALUE= "布隆迪";
	public final static String COUNTRY_31= "31";//国家/国家
	public final static String COUNTRY_31_VALUE= "喀麦隆";
	public final static String COUNTRY_32= "32";//国家/国家
	public final static String COUNTRY_32_VALUE= "加拿大";
	public final static String COUNTRY_33= "33";//国家/国家
	public final static String COUNTRY_33_VALUE= "开曼群岛";
	public final static String COUNTRY_34= "34";//国家/国家
	public final static String COUNTRY_34_VALUE= "中非共和国";
	public final static String COUNTRY_35= "35";//国家/国家
	public final static String COUNTRY_35_VALUE= "乍得";
	public final static String COUNTRY_36= "36";//国家/国家
	public final static String COUNTRY_36_VALUE= "智利";
	public final static String COUNTRY_37= "37";//国家/国家
	public final static String COUNTRY_37_VALUE= "中国";
	public final static String COUNTRY_38= "38";//国家/国家
	public final static String COUNTRY_38_VALUE= "哥伦比亚";
	public final static String COUNTRY_39= "39";//国家/国家
	public final static String COUNTRY_39_VALUE= "刚果";
	public final static String COUNTRY_40= "40";//国家/国家
	public final static String COUNTRY_40_VALUE= "库克群岛";
	public final static String COUNTRY_41= "41";//国家/国家
	public final static String COUNTRY_41_VALUE= "哥斯达黎加";
	public final static String COUNTRY_42= "42";//国家/国家
	public final static String COUNTRY_42_VALUE= "古巴";
	public final static String COUNTRY_43= "43";//国家/国家
	public final static String COUNTRY_43_VALUE= "塞浦路斯";
	public final static String COUNTRY_44= "44";//国家/国家
	public final static String COUNTRY_44_VALUE= "捷克";
	public final static String COUNTRY_45= "45";//国家/国家
	public final static String COUNTRY_45_VALUE= "丹麦";
	public final static String COUNTRY_46= "46";//国家/国家
	public final static String COUNTRY_46_VALUE= "吉布提";
	public final static String COUNTRY_47= "47";//国家/国家
	public final static String COUNTRY_47_VALUE= "多米尼加共和国";
	public final static String COUNTRY_48= "48";//国家/国家
	public final static String COUNTRY_48_VALUE= "厄瓜多尔";
	public final static String COUNTRY_49= "49";//国家/国家
	public final static String COUNTRY_49_VALUE= "埃及";
	public final static String COUNTRY_50= "50";//国家/国家
	public final static String COUNTRY_50_VALUE= "萨尔瓦多";
	public final static String COUNTRY_51= "51";//国家/国家
	public final static String COUNTRY_51_VALUE= "爱沙尼亚";
	public final static String COUNTRY_52= "52";//国家/国家
	public final static String COUNTRY_52_VALUE= "埃塞俄比亚";
	public final static String COUNTRY_53= "53";//国家/国家
	public final static String COUNTRY_53_VALUE= "斐济";
	public final static String COUNTRY_54= "54";//国家/国家
	public final static String COUNTRY_54_VALUE= "芬兰";
	public final static String COUNTRY_55= "55";//国家/国家
	public final static String COUNTRY_55_VALUE= "法国";
	public final static String COUNTRY_56= "56";//国家/国家
	public final static String COUNTRY_56_VALUE= "法属圭亚那";
	public final static String COUNTRY_57= "57";//国家/国家
	public final static String COUNTRY_57_VALUE= "加蓬";
	public final static String COUNTRY_58= "58";//国家/国家
	public final static String COUNTRY_58_VALUE= "冈比亚";
	public final static String COUNTRY_59= "59";//国家/国家
	public final static String COUNTRY_59_VALUE= "格鲁吉亚";
	public final static String COUNTRY_60= "60";//国家/国家
	public final static String COUNTRY_60_VALUE= "德国";
	public final static String COUNTRY_61= "61";//国家/国家
	public final static String COUNTRY_61_VALUE= "加纳";
	public final static String COUNTRY_62= "62";//国家/国家
	public final static String COUNTRY_62_VALUE= "直布罗陀";
	public final static String COUNTRY_63= "63";//国家/国家
	public final static String COUNTRY_63_VALUE= "希腊";
	public final static String COUNTRY_64= "64";//国家/国家
	public final static String COUNTRY_64_VALUE= "格林纳达";
	public final static String COUNTRY_65= "65";//国家/国家
	public final static String COUNTRY_65_VALUE= "关岛";
	public final static String COUNTRY_66= "66";//国家/国家
	public final static String COUNTRY_66_VALUE= "危地马拉";
	public final static String COUNTRY_67= "67";//国家/国家
	public final static String COUNTRY_67_VALUE= "几内亚";
	public final static String COUNTRY_68= "68";//国家/国家
	public final static String COUNTRY_68_VALUE= "圭亚那";
	public final static String COUNTRY_69= "69";//国家/国家
	public final static String COUNTRY_69_VALUE= "海地";
	public final static String COUNTRY_70= "70";//国家/国家
	public final static String COUNTRY_70_VALUE= "洪都拉斯";
	public final static String COUNTRY_71= "71";//国家/国家
	public final static String COUNTRY_71_VALUE= "香港";
	public final static String COUNTRY_72= "72";//国家/国家
	public final static String COUNTRY_72_VALUE= "匈牙利";
	public final static String COUNTRY_73= "73";//国家/国家
	public final static String COUNTRY_73_VALUE= "冰岛";
	public final static String COUNTRY_74= "74";//国家/国家
	public final static String COUNTRY_74_VALUE= "印度";
	public final static String COUNTRY_75= "75";//国家/国家
	public final static String COUNTRY_75_VALUE= "印度尼西亚";
	public final static String COUNTRY_76= "76";//国家/国家
	public final static String COUNTRY_76_VALUE= "伊朗";
	public final static String COUNTRY_77= "77";//国家/国家
	public final static String COUNTRY_77_VALUE= "伊拉克";
	public final static String COUNTRY_78= "78";//国家/国家
	public final static String COUNTRY_78_VALUE= "爱尔兰";
	public final static String COUNTRY_79= "79";//国家/国家
	public final static String COUNTRY_79_VALUE= "以色列";
	public final static String COUNTRY_80= "80";//国家/国家
	public final static String COUNTRY_80_VALUE= "意大利";
	public final static String COUNTRY_81= "81";//国家/国家
	public final static String COUNTRY_81_VALUE= "科特迪瓦";
	public final static String COUNTRY_82= "82";//国家/国家
	public final static String COUNTRY_82_VALUE= "牙买加";
	public final static String COUNTRY_83= "83";//国家/国家
	public final static String COUNTRY_83_VALUE= "日本";
	public final static String COUNTRY_84= "84";//国家/国家
	public final static String COUNTRY_84_VALUE= "约旦";
	public final static String COUNTRY_85= "85";//国家/国家
	public final static String COUNTRY_85_VALUE= "柬埔寨";
	public final static String COUNTRY_86= "86";//国家/国家
	public final static String COUNTRY_86_VALUE= "哈萨克斯坦";
	public final static String COUNTRY_87= "87";//国家/国家
	public final static String COUNTRY_87_VALUE= "肯尼亚";
	public final static String COUNTRY_88= "88";//国家/国家
	public final static String COUNTRY_88_VALUE= "韩国";
	public final static String COUNTRY_89= "89";//国家/国家
	public final static String COUNTRY_89_VALUE= "科威特";
	public final static String COUNTRY_90= "90";//国家/国家
	public final static String COUNTRY_90_VALUE= "吉尔吉斯坦";
	public final static String COUNTRY_91= "91";//国家/国家
	public final static String COUNTRY_91_VALUE= "老挝";
	public final static String COUNTRY_92= "92";//国家/国家
	public final static String COUNTRY_92_VALUE= "拉脱维亚";
	public final static String COUNTRY_93= "93";//国家/国家
	public final static String COUNTRY_93_VALUE= "黎巴嫩";
	public final static String COUNTRY_94= "94";//国家/国家
	public final static String COUNTRY_94_VALUE= "莱索托";
	public final static String COUNTRY_95= "95";//国家/国家
	public final static String COUNTRY_95_VALUE= "利比里亚";
	public final static String COUNTRY_96= "96";//国家/国家
	public final static String COUNTRY_96_VALUE= "利比亚";
	public final static String COUNTRY_97= "97";//国家/国家
	public final static String COUNTRY_97_VALUE= "列支敦士登";
	public final static String COUNTRY_98= "98";//国家/国家
	public final static String COUNTRY_98_VALUE= "立陶宛";
	public final static String COUNTRY_99= "99";//国家/国家
	public final static String COUNTRY_99_VALUE= "卢森堡";
	
	public final static String COUNT_TYPE_AVAILABLE_BALANCE= "01";//卡片账/统计类型
	public final static String COUNT_TYPE_AVAILABLE_BALANCE_VALUE= "可用余额";
	public final static String COUNT_TYPE_FROZEN_BALANCE= "02";//卡片账/统计类型
	public final static String COUNT_TYPE_FROZEN_BALANCE_VALUE= "冻结余额";
	public final static String COUNT_TYPE_BALANCE= "03";//卡片账/统计类型
	public final static String COUNT_TYPE_BALANCE_VALUE= "余额";
	
	public final static String CURRENCY_TYPE_50= "50";//币种/币种
	public final static String CURRENCY_TYPE_50_VALUE= "CNY";
	
	public final static String CUST_LEVEL_LEVEL_II= "02";//客户端/客户级别
	public final static String CUST_LEVEL_LEVEL_II_VALUE= "II级";
	public final static String CUST_LEVEL_LEVEL_I= "01";//客户管理/客户等级
	public final static String CUST_LEVEL_LEVEL_I_VALUE= "I级";
	public final static String CUST_LEVEL_LEVEL_III= "03";//客户管理/客户等级
	public final static String CUST_LEVEL_LEVEL_III_VALUE= "III级";
	
	public final static String CUST_STATUS_NOT_PASSED= "01";//客户端/客户端客户状态
	public final static String CUST_STATUS_NOT_PASSED_VALUE= "未通过";
	public final static String CUST_STATUS_PASSED= "02";//客户端/客户端客户状态
	public final static String CUST_STATUS_PASSED_VALUE= "已通过";
	public final static String CUST_STATUS_WAITING= "03";//客户端/客户端客户状态
	public final static String CUST_STATUS_WAITING_VALUE= "待审核";
	public final static String CUST_STATUS_WRITTEN_OFF= "04";//客户端/客户端客户状态
	public final static String CUST_STATUS_WRITTEN_OFF_VALUE= "已注销";
	public final static String CUST_STATUS_NOT_VALID= "05";//客户端/客户端客户状态
	public final static String CUST_STATUS_NOT_VALID_VALUE= "无效";
	
	public final static String CUST_TYPE_INDIVIDUAL= "01";//卡片账/客户类型
	public final static String CUST_TYPE_INDIVIDUAL_VALUE= "个人";
	public final static String CUST_TYPE_ENTERPRISE= "02";//卡片账/客户类型
	public final static String CUST_TYPE_ENTERPRISE_VALUE= "企业";
	
	public final static String DAILY_TRANS_TYPE_TOPUP= "01";//任务管理/日结交易类型
	public final static String DAILY_TRANS_TYPE_TOPUP_VALUE= "充值";
	public final static String DAILY_TRANS_TYPE_WITHDRAW= "02";//任务管理/日结交易类型
	public final static String DAILY_TRANS_TYPE_WITHDRAW_VALUE= "提现";
	public final static String DAILY_TRANS_TYPE_CLEARING_AND_= "03";//任务管理/日结交易类型
	public final static String DAILY_TRANS_TYPE_CLEARING_AND__VALUE= "清结算";
	
	public final static String EDUCATION_LEVEL_POST= "01";//客户端/最高学历
	public final static String EDUCATION_LEVEL_POST_VALUE= "博士后";
	public final static String EDUCATION_LEVEL_DOCTOR= "02";//客户端/最高学历
	public final static String EDUCATION_LEVEL_DOCTOR_VALUE= "博士研究生";
	public final static String EDUCATION_LEVEL_MASTER= "03";//客户端/最高学历
	public final static String EDUCATION_LEVEL_MASTER_VALUE= "硕士研究生";
	public final static String EDUCATION_LEVEL_BACHELOR= "04";//客户端/最高学历
	public final static String EDUCATION_LEVEL_BACHELOR_VALUE= "大学本科";
	public final static String EDUCATION_LEVEL_COLLEGE= "05";//客户端/最高学历
	public final static String EDUCATION_LEVEL_COLLEGE_VALUE= "普通专科";
	public final static String EDUCATION_LEVEL_HIGH_SCHOOL= "06";//客户端/最高学历
	public final static String EDUCATION_LEVEL_HIGH_SCHOOL_VALUE= "高中";
	public final static String EDUCATION_LEVEL_MIDDLE_SCHOOL= "07";//客户端/最高学历
	public final static String EDUCATION_LEVEL_MIDDLE_SCHOOL_VALUE= "初中";
	public final static String EDUCATION_LEVEL_PRIMARY_SCHOOL= "08";//客户端/最高学历
	public final static String EDUCATION_LEVEL_PRIMARY_SCHOOL_VALUE= "小学";
	
	public final static String ERROR_TYPE_AMOUT_NOT_EQUAL= "02";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_AMOUT_NOT_EQUAL_VALUE= "金额不一致";
	
	public final static String ERROR_TYPE_03= "03";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_03_VALUE= "银行交易流水缺失";
	public final static String ERROR_TYPE_04= "04";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_04_VALUE= "平台交易流水缺失";
	public final static String ERROR_TYPE_05= "05";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_05_VALUE= "收款账户不存在";
	public final static String ERROR_TYPE_06= "06";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_06_VALUE= "付款账户不存在";
	public final static String ERROR_TYPE_07= "07";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_07_VALUE= "付款账户余额不足";
	public final static String ERROR_TYPE_08= "08";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_08_VALUE= "付款账户状态不正常";
	public final static String ERROR_TYPE_09= "09";//资金汇划/对账错误类型
	public final static String ERROR_TYPE_09_VALUE= "收款账户状态不正常";
	
	public final static String FAIL_CODE_01= "01";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_01_VALUE= "用户不存在";
	public final static String FAIL_CODE_02= "02";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_02_VALUE= "用户已存在／重复";
	public final static String FAIL_CODE_03= "03";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_03_VALUE= "会员已注销";
	public final static String FAIL_CODE_04= "04";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_04_VALUE= "银行卡号与系统中不符";
	public final static String FAIL_CODE_05= "05";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_05_VALUE= "订单号已存在";
	public final static String FAIL_CODE_06= "06";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_06_VALUE= "清算总额不为零";
	public final static String FAIL_CODE_07= "07";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_07_VALUE= "清算数据总批数有误";
	public final static String FAIL_CODE_08= "08";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_08_VALUE= "清算数据总条数有误";
	public final static String FAIL_CODE_09= "09";//资金汇划/资金汇划失败代码
	public final static String FAIL_CODE_09_VALUE= "实名验证失败";
	
	public final static String FAIL_REASON_01= "01";//入账管理/审核失败原因
	public final static String FAIL_REASON_01_VALUE= "未到账";
	public final static String FAIL_REASON_02= "02";//入账管理/审核失败原因
	public final static String FAIL_REASON_02_VALUE= "非绑定银行卡";
	public final static String FAIL_REASON_03= "03";//入账管理/审核失败原因
	public final static String FAIL_REASON_03_VALUE= "金额不一致";
	
	public final static String GENDER_MALE= "01";//客户端/性别
	public final static String GENDER_MALE_VALUE= "男";
	public final static String GENDER_FEMALE= "02";//客户端/性别
	public final static String GENDER_FEMALE_VALUE= "女";
	public final static String GENDER_CONFIDENCIAL= "03";//客户端/性别
	public final static String GENDER_CONFIDENCIAL_VALUE= "保密";
	public final static String GENDER_OTHER= "04";//客户端/性别
	public final static String GENDER_OTHER_VALUE= "其他";
	
	public final static String GIRO_ERROR_CODE_01= "01";//资金汇划/对帐错误代码
	public final static String GIRO_ERROR_CODE_01_VALUE= "0001";
	public final static String GIRO_ERROR_CODE_02= "02";//资金汇划/对帐错误代码
	public final static String GIRO_ERROR_CODE_02_VALUE= "0002";
	public final static String GIRO_ERROR_CODE_03= "03";//资金汇划/对帐错误代码
	public final static String GIRO_ERROR_CODE_03_VALUE= "0003";
	
	public final static String GLOBAL_ERROR_101= "101";//报错接口/报错接口
	public final static String GLOBAL_ERROR_101_VALUE= "商户号错误或者为空";
	public final static String GLOBAL_ERROR_102= "102";//报错接口/报错接口
	public final static String GLOBAL_ERROR_102_VALUE= "交易应答查询的订单号为空";
	public final static String GLOBAL_ERROR_103= "103";//报错接口/报错接口
	public final static String GLOBAL_ERROR_103_VALUE= "交易日期为空";
	public final static String GLOBAL_ERROR_104= "104";//报错接口/报错接口
	public final static String GLOBAL_ERROR_104_VALUE= "请求交易类型错误";
	public final static String GLOBAL_ERROR_105= "105";//报错接口/报错接口
	public final static String GLOBAL_ERROR_105_VALUE= "商户号长度错误";
	public final static String GLOBAL_ERROR_106= "106";//报错接口/报错接口
	public final static String GLOBAL_ERROR_106_VALUE= "订单号长度错误";
	public final static String GLOBAL_ERROR_107= "107";//报错接口/报错接口
	public final static String GLOBAL_ERROR_107_VALUE= "交易日期长度错误";
	public final static String GLOBAL_ERROR_108= "108";//报错接口/报错接口
	public final static String GLOBAL_ERROR_108_VALUE= "交易类型为空或者长度不正确";
	public final static String GLOBAL_ERROR_109= "109";//报错接口/报错接口
	public final static String GLOBAL_ERROR_109_VALUE= "版本号不能为空";
	public final static String GLOBAL_ERROR_110= "110";//报错接口/报错接口
	public final static String GLOBAL_ERROR_110_VALUE= "版本号不正确";
	public final static String GLOBAL_ERROR_111= "111";//报错接口/报错接口
	public final static String GLOBAL_ERROR_111_VALUE= "商户请求非法";
	public final static String GLOBAL_ERROR_112= "112";//报错接口/报错接口
	public final static String GLOBAL_ERROR_112_VALUE= "交易类型不合法";
	public final static String GLOBAL_ERROR_116= "116";//报错接口/报错接口
	public final static String GLOBAL_ERROR_116_VALUE= "金额为空";
	public final static String GLOBAL_ERROR_117= "117";//报错接口/报错接口
	public final static String GLOBAL_ERROR_117_VALUE= "商户私有域段长度出错";
	public final static String GLOBAL_ERROR_118= "118";//报错接口/报错接口
	public final static String GLOBAL_ERROR_118_VALUE= "签名域段出错";
	public final static String GLOBAL_ERROR_121= "121";//报错接口/报错接口
	public final static String GLOBAL_ERROR_121_VALUE= "退款金额不等于原支付订单金额";
	public final static String GLOBAL_ERROR_122= "122";//报错接口/报错接口
	public final static String GLOBAL_ERROR_122_VALUE= "退款金额应该小于原支付订单金额";
	public final static String GLOBAL_ERROR_123= "123";//报错接口/报错接口
	public final static String GLOBAL_ERROR_123_VALUE= "总退款金额大于了原支付订单金额";
	public final static String GLOBAL_ERROR_200= "200";//报错接口/报错接口
	public final static String GLOBAL_ERROR_200_VALUE= "签名数据不存在";
	public final static String GLOBAL_ERROR_201= "201";//报错接口/报错接口
	public final static String GLOBAL_ERROR_201_VALUE= "明文数据为空";
	public final static String GLOBAL_ERROR_202= "202";//报错接口/报错接口
	public final static String GLOBAL_ERROR_202_VALUE= "签名不合法";
	public final static String GLOBAL_ERROR_203= "203";//报错接口/报错接口
	public final static String GLOBAL_ERROR_203_VALUE= "验签失败";
	public final static String GLOBAL_ERROR_204= "204";//报错接口/报错接口
	public final static String GLOBAL_ERROR_204_VALUE= "验签数据不合法";
	public final static String GLOBAL_ERROR_205= "205";//报错接口/报错接口
	public final static String GLOBAL_ERROR_205_VALUE= "重复提交该笔交易";
	public final static String GLOBAL_ERROR_301= "301";//报错接口/报错接口
	public final static String GLOBAL_ERROR_301_VALUE= "查询的交易不存在";
	public final static String GLOBAL_ERROR_302= "302";//报错接口/报错接口
	public final static String GLOBAL_ERROR_302_VALUE= "查询数据库出错";
	public final static String GLOBAL_ERROR_303= "303";//报错接口/报错接口
	public final static String GLOBAL_ERROR_303_VALUE= "应答数据封装出错";
	public final static String GLOBAL_ERROR_304= "304";//报错接口/报错接口
	public final static String GLOBAL_ERROR_304_VALUE= "应答数据字符串转换出错";
	public final static String GLOBAL_ERROR_305= "305";//报错接口/报错接口
	public final static String GLOBAL_ERROR_305_VALUE= "超出流量控制范围";
	public final static String GLOBAL_ERROR_307= "307";//报错接口/报错接口
	public final static String GLOBAL_ERROR_307_VALUE= "未查到匹配的数据";
	public final static String GLOBAL_ERROR_404= "404";//报错接口/报错接口
	public final static String GLOBAL_ERROR_404_VALUE= "其它内部错误";
	
	public final static String INDUSTRY_ELECTRONIC_INFO= "01";//客户端/行业类别
	public final static String INDUSTRY_ELECTRONIC_INFO_VALUE= "电子信息";
	public final static String INDUSTRY_CHEMICAL= "02";//客户端/行业类别
	public final static String INDUSTRY_CHEMICAL_VALUE= "化工";
	public final static String INDUSTRY_AUTOMOBILE= "03";//客户端/行业类别
	public final static String INDUSTRY_AUTOMOBILE_VALUE= "汽车";
	public final static String INDUSTRY_MECHANIC= "04";//客户端/行业类别
	public final static String INDUSTRY_MECHANIC_VALUE= "机械工业";
	public final static String INDUSTRY_INTRUMENT= "05";//客户端/行业类别
	public final static String INDUSTRY_INTRUMENT_VALUE= "仪器仪表";
	public final static String INDUSTRY_GENERATING= "06";//客户端/行业类别
	public final static String INDUSTRY_GENERATING_VALUE= "发电与输变电设备 ";
	public final static String INDUSTRY_BIOLOGICAL= "07";//客户端/行业类别
	public final static String INDUSTRY_BIOLOGICAL_VALUE= "生物技术";
	public final static String INDUSTRY_PHARMACY= "08";//客户端/行业类别
	public final static String INDUSTRY_PHARMACY_VALUE= "制药";
	public final static String INDUSTRY_MATERIAL= "09";//客户端/行业类别
	public final static String INDUSTRY_MATERIAL_VALUE= "新材料";
	public final static String INDUSTRY_PHOELECTRON= "10";//客户端/行业类别
	public final static String INDUSTRY_PHOELECTRON_VALUE= "光电子";
	public final static String INDUSTRY_NANO= "11";//客户端/行业类别
	public final static String INDUSTRY_NANO_VALUE= "纳米";
	public final static String INDUSTRY_NEW_ENERGY= "12";//客户端/行业类别
	public final static String INDUSTRY_NEW_ENERGY_VALUE= "新能源";
	public final static String INDUSTRY_ENVIRONMENTAL= "13";//客户端/行业类别
	public final static String INDUSTRY_ENVIRONMENTAL_VALUE= "环保";
	public final static String INDUSTRY_PRINTING= "14";//客户端/行业类别
	public final static String INDUSTRY_PRINTING_VALUE= "印刷与包装";
	public final static String INDUSTRY_FOOD_AGRICULTURE= "15";//客户端/行业类别
	public final static String INDUSTRY_FOOD_AGRICULTURE_VALUE= "食品和农产品精加工";
	public final static String INDUSTRY_JEWELRY= "16";//客户端/行业类别
	public final static String INDUSTRY_JEWELRY_VALUE= "钻石珠宝";
	public final static String INDUSTRY_COSMETIC= "17";//客户端/行业类别
	public final static String INDUSTRY_COSMETIC_VALUE= "化妆品及洗涤用品";
	public final static String INDUSTRY_CLOTHING= "18";//客户端/行业类别
	public final static String INDUSTRY_CLOTHING_VALUE= "服装服饰";
	public final static String INDUSTRY_TEXTILE= "19";//客户端/行业类别
	public final static String INDUSTRY_TEXTILE_VALUE= "纺织";
	public final static String INDUSTRY_LIGHT_INDUSTRY= "20";//客户端/行业类别
	public final static String INDUSTRY_LIGHT_INDUSTRY_VALUE= "轻工";
	public final static String INDUSTRY_CPG= "21";//客户端/行业类别
	public final static String INDUSTRY_CPG_VALUE= "商业零售业";
	
	public final static String INTERFACE_FAIL_REASON_01= "01";//接口数据管理/接口失败原因
	public final static String INTERFACE_FAIL_REASON_01_VALUE= "密匙不存在";
	public final static String INTERFACE_FAIL_REASON_02= "02";//接口数据管理/接口失败原因
	public final static String INTERFACE_FAIL_REASON_02_VALUE= "签名字符串长度为空";
	public final static String INTERFACE_FAIL_REASON_03= "03";//接口数据管理/接口失败原因
	public final static String INTERFACE_FAIL_REASON_03_VALUE= "环境变量未设置";
	public final static String INTERFACE_FAIL_REASON_04= "04";//接口数据管理/接口失败原因
	public final static String INTERFACE_FAIL_REASON_04_VALUE= "密匙错误";
	public final static String INTERFACE_FAIL_REASON_05= "05";//接口数据管理/接口失败原因
	public final static String INTERFACE_FAIL_REASON_05_VALUE= "加密不成功";
	
	public final static String ISIN_GL_UNRECORDED= "01";//卡片账/入账标志
	public final static String ISIN_GL_UNRECORDED_VALUE= "未入账";
	public final static String ISIN_GL_RECORDED= "02";//卡片账/入账标志
	public final static String ISIN_GL_RECORDED_VALUE= "已入账";
	
	public final static String IS_PRINTED_NOT_PRINTED= "01";//资金汇划/打印标志
	public final static String IS_PRINTED_NOT_PRINTED_VALUE= "未打印";
	public final static String IS_PRINTED_PRINTED= "02";//资金汇划/打印标志
	public final static String IS_PRINTED_PRINTED_VALUE= "已打印";
	
	public final static String IS_VALID_VALID= "01";//全局/是否有效
	public final static String IS_VALID_VALID_VALUE= "有效";
	public final static String IS_VALID_NOT_VALID= "02";//全局/是否有效
	public final static String IS_VALID_NOT_VALID_VALUE= "无效";
	
	public final static String MAIL_REGIST_PLEASE_ENTERED_CORRECT_MAIL_ADDRESS= "01";//客户端/邮箱注册
	public final static String MAIL_REGIST_PLEASE_ENTERED_CORRECT_MAIL_ADDRESS_VALUE= "请输入正确的邮箱";
	public final static String MAIL_REGIST_ALREADY_REGISTER= "02";//客户端/邮箱注册
	public final static String MAIL_REGIST_ALREADY_REGISTER_VALUE= "该邮箱已注册";
	
	public final static String MENU_LEVEL_LEVEL_1= "01";//运维管理/菜单类型
	public final static String MENU_LEVEL_LEVEL_1_VALUE= "一级菜单";
	public final static String MENU_LEVEL_LEVEL_2= "02";//运维管理/菜单类型
	public final static String MENU_LEVEL_LEVEL_2_VALUE= "二级菜单";
	public final static String MENU_LEVEL_LEVEL_3= "03";//运维管理/菜单类型
	public final static String MENU_LEVEL_LEVEL_3_VALUE= "三级菜单";
	
	public final static String MENU_URL_01= "01";//运维管理/菜单URL
	public final static String MENU_URL_01_VALUE= "wwww.******.com";
	public final static String MENU_URL_02= "02";//运维管理/菜单URL
	public final static String MENU_URL_02_VALUE= "wwww.*****.com";
	
	public final static String MSG_HANDLE_STATUS_SEND= "01";//资金汇划/报文处理状态
	public final static String MSG_HANDLE_STATUS_SEND_VALUE= "已发送";
	public final static String MSG_HANDLE_STATUS_UNSEND= "02";//资金汇划/报文处理状态
	public final static String MSG_HANDLE_STATUS_UNSEND_VALUE= "未发送";
	public final static String MSG_HANDLE_STATUS_HANDLED= "03";//资金汇划/报文处理状态
	public final static String MSG_HANDLE_STATUS_HANDLED_VALUE= "已处理";
	
	public final static String NATION_01= "01";//民族/民族
	public final static String NATION_01_VALUE= "汉族";
	public final static String NATION_02= "02";//民族/民族
	public final static String NATION_02_VALUE= "壮族";
	public final static String NATION_03= "03";//民族/民族
	public final static String NATION_03_VALUE= "满族";
	public final static String NATION_04= "04";//民族/民族
	public final static String NATION_04_VALUE= "回族";
	public final static String NATION_05= "05";//民族/民族
	public final static String NATION_05_VALUE= "苗族";
	public final static String NATION_06= "06";//民族/民族
	public final static String NATION_06_VALUE= "维吾尔族";
	public final static String NATION_07= "07";//民族/民族
	public final static String NATION_07_VALUE= "土家族";
	public final static String NATION_08= "08";//民族/民族
	public final static String NATION_08_VALUE= "彝族";
	public final static String NATION_09= "09";//民族/民族
	public final static String NATION_09_VALUE= "蒙古族";
	public final static String NATION_10= "10";//民族/民族
	public final static String NATION_10_VALUE= "藏族";
	public final static String NATION_11= "11";//民族/民族
	public final static String NATION_11_VALUE= "布依族";
	public final static String NATION_12= "12";//民族/民族
	public final static String NATION_12_VALUE= "侗族";
	public final static String NATION_13= "13";//民族/民族
	public final static String NATION_13_VALUE= "瑶族";
	public final static String NATION_14= "14";//民族/民族
	public final static String NATION_14_VALUE= "朝鲜族";
	public final static String NATION_15= "15";//民族/民族
	public final static String NATION_15_VALUE= "白族";
	public final static String NATION_16= "16";//民族/民族
	public final static String NATION_16_VALUE= "哈尼族";
	public final static String NATION_17= "17";//民族/民族
	public final static String NATION_17_VALUE= "哈萨克族";
	public final static String NATION_18= "18";//民族/民族
	public final static String NATION_18_VALUE= "黎族";
	public final static String NATION_19= "19";//民族/民族
	public final static String NATION_19_VALUE= "傣族";
	public final static String NATION_20= "20";//民族/民族
	public final static String NATION_20_VALUE= "畲族";
	public final static String NATION_21= "21";//民族/民族
	public final static String NATION_21_VALUE= "傈僳族";
	public final static String NATION_22= "22";//民族/民族
	public final static String NATION_22_VALUE= "仡佬族";
	public final static String NATION_23= "23";//民族/民族
	public final static String NATION_23_VALUE= "羌族";
	public final static String NATION_24= "24";//民族/民族
	public final static String NATION_24_VALUE= "土族";
	public final static String NATION_25= "25";//民族/民族
	public final static String NATION_25_VALUE= "仫佬族";
	public final static String NATION_26= "26";//民族/民族
	public final static String NATION_26_VALUE= "锡伯族";
	public final static String NATION_27= "27";//民族/民族
	public final static String NATION_27_VALUE= "柯尔克孜族";
	public final static String NATION_28= "28";//民族/民族
	public final static String NATION_28_VALUE= "达斡尔族";
	public final static String NATION_29= "29";//民族/民族
	public final static String NATION_29_VALUE= "景颇族";
	public final static String NATION_30= "30";//民族/民族
	public final static String NATION_30_VALUE= "毛南族";
	public final static String NATION_31= "31";//民族/民族
	public final static String NATION_31_VALUE= "撒拉族";
	public final static String NATION_32= "32";//民族/民族
	public final static String NATION_32_VALUE= "布朗族";
	public final static String NATION_33= "33";//民族/民族
	public final static String NATION_33_VALUE= "塔吉克族";
	public final static String NATION_34= "34";//民族/民族
	public final static String NATION_34_VALUE= "阿昌族";
	public final static String NATION_35= "35";//民族/民族
	public final static String NATION_35_VALUE= "普米族";
	public final static String NATION_36= "36";//民族/民族
	public final static String NATION_36_VALUE= "鄂温克族";
	public final static String NATION_37= "37";//民族/民族
	public final static String NATION_37_VALUE= "怒族";
	public final static String NATION_38= "38";//民族/民族
	public final static String NATION_38_VALUE= "京族";
	public final static String NATION_39= "39";//民族/民族
	public final static String NATION_39_VALUE= "基诺族";
	public final static String NATION_40= "40";//民族/民族
	public final static String NATION_40_VALUE= "德昂族";
	public final static String NATION_41= "41";//民族/民族
	public final static String NATION_41_VALUE= "保安族";
	public final static String NATION_42= "42";//民族/民族
	public final static String NATION_42_VALUE= "俄罗斯族";
	public final static String NATION_43= "43";//民族/民族
	public final static String NATION_43_VALUE= "裕固族";
	public final static String NATION_44= "44";//民族/民族
	public final static String NATION_44_VALUE= "乌兹别克族";
	public final static String NATION_45= "45";//民族/民族
	public final static String NATION_45_VALUE= "门巴族";
	public final static String NATION_46= "46";//民族/民族
	public final static String NATION_46_VALUE= "鄂伦春族";
	public final static String NATION_47= "47";//民族/民族
	public final static String NATION_47_VALUE= "独龙族";
	public final static String NATION_48= "48";//民族/民族
	public final static String NATION_48_VALUE= "塔塔尔族";
	public final static String NATION_49= "49";//民族/民族
	public final static String NATION_49_VALUE= "赫哲族";
	public final static String NATION_50= "50";//民族/民族
	public final static String NATION_50_VALUE= "珞巴族";
	public final static String NATION_51= "51";//民族/民族
	public final static String NATION_51_VALUE= "东乡族";
	public final static String NATION_52= "52";//民族/民族
	public final static String NATION_52_VALUE= "高山族";
	public final static String NATION_53= "53";//民族/民族
	public final static String NATION_53_VALUE= "拉祜族";
	public final static String NATION_54= "54";//民族/民族
	public final static String NATION_54_VALUE= "水族";
	public final static String NATION_55= "55";//民族/民族
	public final static String NATION_55_VALUE= "佤族";
	public final static String NATION_56= "56";//民族/民族
	public final static String NATION_56_VALUE= "纳西族";
	
	public final static String PAYMENT_CNL_WECHAT= "01";//入账管理/支付通道
	public final static String PAYMENT_CNL_WECHAT_VALUE= "微信";
	public final static String PAYMENT_CNL_ZHIFU_BAO= "02";//入账管理/支付通道
	public final static String PAYMENT_CNL_ZHIFU_BAO_VALUE= "支付宝";
	public final static String PAYMENT_CNL_STOCK= "03";//入账管理/支付通道
	public final static String PAYMENT_CNL_STOCK_VALUE= "银证";
	public final static String PAYMENT_CNL_ONLINE_BANKING= "04";//入账管理/支付通道
	public final static String PAYMENT_CNL_ONLINE_BANKING_VALUE= "网银";
	public final static String PAYMENT_CNL_ATM= "05";//入账管理/支付通道
	public final static String PAYMENT_CNL_ATM_VALUE= "ATM";
	public final static String PAYMENT_CNL_COUNTER= "06";//入账管理/支付通道
	public final static String PAYMENT_CNL_COUNTER_VALUE= "柜台转帐";
	public final static String PAYMENT_CNL_QB= "07";//入账管理/支付通道
	public final static String PAYMENT_CNL_QB_VALUE= "钱宝";
	public final static String PAYMENT_CNL_OTHERS= "99";//入账管理/支付通道
	public final static String PAYMENT_CNL_OTHERS_VALUE= "其他";
	
	public final static String PAY_PASSWORD_01= "01";//客户端/支付密码
	public final static String PAY_PASSWORD_01_VALUE= "密码错误，请重新输入";
	public final static String PAY_PASSWORD_02= "02";//客户端/支付密码
	public final static String PAY_PASSWORD_02_VALUE= "密码错误次数超限，已被锁定，3小时后解锁";
	
	public final static String PAY_WAY_IBS_ACCOUNT= "01";//客户端/支付方式
	public final static String PAY_WAY_IBS_ACCOUNT_VALUE= "IBS账户";
	public final static String PAY_WAY_BANK_CARD= "02";//客户端/支付方式
	public final static String PAY_WAY_BANK_CARD_VALUE= "银行卡";
	
	public final static String PENDING_HANDLE_STATUS_NOT_SUBMIT= "01";//卡片账/待处理交易处理状态
	public final static String PENDING_HANDLE_STATUS_NOT_SUBMIT_VALUE= "未提交";
	public final static String PENDING_HANDLE_STATUS_SUBMIT= "02";//卡片账/待处理交易处理状态
	public final static String PENDING_HANDLE_STATUS_SUBMIT_VALUE= "已提交";
	public final static String PENDING_HANDLE_STATUS_SUCCESS= "03";//卡片账/待处理交易处理状态
	public final static String PENDING_HANDLE_STATUS_SUCCESS_VALUE= "审核成功";
	public final static String PENDING_HANDLE_STATUS_FAIL= "04";//卡片账/待处理交易处理状态
	public final static String PENDING_HANDLE_STATUS_FAIL_VALUE= "审核失败";
	
	public final static String POSITION_DIRECTOR= "01";//客户端/职务
	public final static String POSITION_DIRECTOR_VALUE= "总监";
	public final static String POSITION_MANAGER= "02";//客户端/职务
	public final static String POSITION_MANAGER_VALUE= "经理";
	public final static String POSITION_LEADER= "03";//客户端/职务
	public final static String POSITION_LEADER_VALUE= "主管";
	public final static String POSITION_EMPLOYEE= "04";//客户端/职务
	public final static String POSITION_EMPLOYEE_VALUE= "职员";
	
	public final static String REAL_CERT_CNL_POLICE= "01";//客户端/实名认证的渠道
	public final static String REAL_CERT_CNL_POLICE_VALUE= "公安";
	public final static String REAL_CERT_CNL_CTCC= "02";//客户端/实名认证的渠道
	public final static String REAL_CERT_CNL_CTCC_VALUE= "电信";
	public final static String REAL_CERT_CNL_BANK= "03";//客户端/实名认证的渠道
	public final static String REAL_CERT_CNL_BANK_VALUE= "银行";
	
	public final static String RECORD_HANDLE_STATUS_WAITING= "01";//入账管理/入账处理状态
	public final static String RECORD_HANDLE_STATUS_WAITING_VALUE= "待审核";
	public final static String RECORD_HANDLE_STATUS_1ST_CHECK_PASS= "02";//入账管理/入账处理状态
	public final static String RECORD_HANDLE_STATUS_1ST_CHECK_PASS_VALUE= "审核通过";
	public final static String RECORD_HANDLE_STATUS_1ST_CHECK_FAIL= "03";//入账管理/入账处理状态
	public final static String RECORD_HANDLE_STATUS_1ST_CHECK_FAIL_VALUE= "审核失败";
	public final static String RECORD_HANDLE_STATUS_2ND_CHECK_PASS= "04";//入账管理/入账处理状态
	public final static String RECORD_HANDLE_STATUS_2ND_CHECK_PASS_VALUE= "复核通过";
	public final static String RECORD_HANDLE_STATUS_2ND_CHECK_FAIL= "05";//入账管理/入账处理状态
	public final static String RECORD_HANDLE_STATUS_2ND_CHECK_FAIL_VALUE= "复核失败";
	
	public final static String REFUND_STATUS_WAITING= "01";//退款管理/退款状态
	public final static String REFUND_STATUS_WAITING_VALUE= "待审核";
	public final static String REFUND_STATUS_SUCCESS= "02";//退款管理/退款状态
	public final static String REFUND_STATUS_SUCCESS_VALUE= "审核成功";
	public final static String REFUND_STATUS_FAIL= "03";//退款管理/退款状态
	public final static String REFUND_STATUS_FAIL_VALUE= "审核失败";
	public final static String REFUND_STATUS_NOT_REFUND= "04";//退款管理/退款状态
	public final static String REFUND_STATUS_NOT_REFUND_VALUE= "未退款";
	public final static String REFUND_STATUS_REFUNDING= "05";//退款管理/退款状态
	public final static String REFUND_STATUS_REFUNDING_VALUE= "退款中";
	public final static String REFUND_STATUS_REFUND_SUCCESS= "06";//退款管理/退款状态
	public final static String REFUND_STATUS_REFUND_SUCCESS_VALUE= "退款成功";
	public final static String REFUND_STATUS_REFUND_FAIL= "07";//退款管理/退款状态
	public final static String REFUND_STATUS_REFUND_FAIL_VALUE= "退款失败";
	
	public final static String REMARK_ERROR_01= "01";//客户端/备注
	public final static String REMARK_ERROR_01_VALUE= "备注字数超限";
	
	public final static String REQ_STATUS_WAITING= "01";//接口数据管理/接口数据申请单状态
	public final static String REQ_STATUS_WAITING_VALUE= "待处理";
	public final static String REQ_STATUS_PENDING= "02";//接口数据管理/接口数据申请单状态
	public final static String REQ_STATUS_PENDING_VALUE= "处理中";
	public final static String REQ_STATUS_SUCCESS= "03";//接口数据管理/接口数据申请单状态
	public final static String REQ_STATUS_SUCCESS_VALUE= "成功";
	public final static String REQ_STATUS_FAIL= "04";//接口数据管理/接口数据申请单状态
	public final static String REQ_STATUS_FAIL_VALUE= "失败";
	public final static String REQ_STATUS_DATA= "05";//接口数据管理/接口数据申请单状态
	public final static String REQ_STATUS_DATA_VALUE= "异常";
	
	//卡片账/业务跟踪申请单类别
	public final static String REQ_TRACE_TYPE_TOP_UP= "01";
	public final static String REQ_TRACE_TYPE_TOP_UP_VALUE= "充值";
	public final static String REQ_TRACE_TYPE_WITHDRAW= "02";
	public final static String REQ_TRACE_TYPE_WITHDRAW_VALUE= "提现";
	public final static String REQ_TRACE_TYPE_CLEARING_AND_SETTLEMENT= "03";
	public final static String REQ_TRACE_TYPE_CLEARING_AND_SETTLEMENT_VALUE= "清结算";
	public final static String REQ_TRACE_TYPE_INDIVIDUAL_SIGN_UP= "04";
	public final static String REQ_TRACE_TYPE_INDIVIDUAL_SIGN_UP_VALUE= "个人开户";
	public final static String REQ_TRACE_TYPE_UPDATE_INDIVIDUAL_CUSTOMER= "05";
	public final static String REQ_TRACE_TYPE_UPDATE_INDIVIDUAL_CUSTOMER_VALUE= "个人更新";
	public final static String REQ_TRACE_TYPE_ENTERPRISE_SIGN_UP= "06";
	public final static String REQ_TRACE_TYPE_ENTERPRISE_SIGN_UP_VALUE= "企业开户";
	public final static String REQ_TRACE_TYPE_UPDATE_ENTERPRISE_CUSTOMER= "07";
	public final static String REQ_TRACE_TYPE_UPDATE_ENTERPRISE_CUSTOMER_VALUE= "企业更新";
	public final static String REQ_TRACE_TYPE_SIGN_OFF= "08";
	public final static String REQ_TRACE_TYPE_SIGN_OFF_VALUE= "注销";
	public final static String REQ_TRACE_TYPE_OPEN_ACCOUNT= "10";
	public final static String REQ_TRACE_TYPE_OPEN_ACCOUNT_VALUE= "企业开户确认";
	
	//渠道接口管理/申请单错误代码
	public final static String REQ_TRANS_ERROR_CODE_01= "01";
	public final static String REQ_TRANS_ERROR_CODE_01_VALUE= "用户不存在";
	public final static String REQ_TRANS_ERROR_CODE_02= "02";
	public final static String REQ_TRANS_ERROR_CODE_02_VALUE= "用户已存在／重复";
	public final static String REQ_TRANS_ERROR_CODE_03= "03";
	public final static String REQ_TRANS_ERROR_CODE_03_VALUE= "会员已注销";
	public final static String REQ_TRANS_ERROR_CODE_04= "04";
	public final static String REQ_TRANS_ERROR_CODE_04_VALUE= "银行卡号与系统中不符";
	public final static String REQ_TRANS_ERROR_CODE_05= "05";
	public final static String REQ_TRANS_ERROR_CODE_05_VALUE= "订单号已存在";
	public final static String REQ_TRANS_ERROR_CODE_06= "06";
	public final static String REQ_TRANS_ERROR_CODE_06_VALUE= "清算总额不为零";
	public final static String REQ_TRANS_ERROR_CODE_07= "07";
	public final static String REQ_TRANS_ERROR_CODE_07_VALUE= "清算数据总批数有误";
	public final static String REQ_TRANS_ERROR_CODE_08= "08";
	public final static String REQ_TRANS_ERROR_CODE_08_VALUE= "清算数据总条数有误";
	public final static String REQ_TRANS_ERROR_CODE_09= "09";
	public final static String REQ_TRANS_ERROR_CODE_09_VALUE= "实名验证失败";
	public final static String REQ_TRANS_ERROR_CODE_10= "10";
	public final static String REQ_TRANS_ERROR_CODE_10_VALUE= "字段格式异常";
	public final static String REQ_TRANS_ERROR_CODE_11= "11";
	public final static String REQ_TRANS_ERROR_CODE_11_VALUE= "银行验证失败";
	public final static String REQ_TRANS_ERROR_CODE_12= "12";
	public final static String REQ_TRANS_ERROR_CODE_12_VALUE= "会员账户余额不为0";
	public final static String REQ_TRANS_ERROR_CODE_13= "13";
	public final static String REQ_TRANS_ERROR_CODE_13_VALUE= "清算账户余额不一致";
	public final static String REQ_TRANS_ERROR_CODE_14="14";
	public final static String REQ_TRANS_ERROR_CODE_14_VALUE="此清算用户为无效用户";
	
	public final static String REQ_TYPE_TOP_UP= "01";//渠道接口管理/请求类型
	public final static String REQ_TYPE_TOP_UP_VALUE= "充值";
	public final static String REQ_TYPE_WITHDRAW= "02";//渠道接口管理/请求类型
	public final static String REQ_TYPE_WITHDRAW_VALUE= "提现";
	public final static String REQ_TYPE_CLEARING_AND_SETTLEMENT= "03";//渠道接口管理/请求类型
	public final static String REQ_TYPE_CLEARING_AND_SETTLEMENT_VALUE= "清结算";
	public final static String REQ_TYPE_INDIVIDUAL_SIGN_UP= "04";//渠道接口管理/请求类型
	public final static String REQ_TYPE_INDIVIDUAL_SIGN_UP_VALUE= "个人开户";
	public final static String REQ_TYPE_UPDATE_INDIVIDUAL_CUSTOMER= "05";//渠道接口管理/请求类型
	public final static String REQ_TYPE_UPDATE_INDIVIDUAL_CUSTOMER_VALUE= "个人会员更新";
	public final static String REQ_TYPE_ENTERPRISE_SIGN_UP= "06";//渠道接口管理/请求类型
	public final static String REQ_TYPE_ENTERPRISE_SIGN_UP_VALUE= "企业开户";
	public final static String REQ_TYPE_UPDATE_ENTERPRISE_CUSTOMER= "07";//渠道接口管理/请求类型
	public final static String REQ_TYPE_UPDATE_ENTERPRISE_CUSTOMER_VALUE= "企业会员更新";
	public final static String REQ_TYPE_INDIVIDUAL_SIGN_OFF= "08";//渠道接口管理/请求类型
	public final static String REQ_TYPE_INDIVIDUAL_SIGN_OFF_VALUE= "个人注销";
	public final static String REQ_TYPE_ENTERPRISE_SIGN_OFF= "09";//渠道接口管理/请求类型
	public final static String REQ_TYPE_ENTERPRISE_SIGN_OFF_VALUE= "企业注销";
	public final static String REQ_TYPE_ENTERPRISE_OPEN_ACCOUNT= "10";//渠道接口管理/请求类型
	public final static String REQ_TYPE_ENTERPRISE_OPEN_ACCOUNT_VALUE= "企业开户确认";
	
	public final static String SET_PASSWORD_01= "01";//客户端/密码设置
	public final static String SET_PASSWORD_01_VALUE= "密码设置不符合要求，请重新设置";
	public final static String SET_PASSWORD_02= "02";//客户端/密码设置
	public final static String SET_PASSWORD_02_VALUE= "2次输入的密码不一致，请重新输入";
	
	public final static String SRC_SYS_01= "01";//运维管理/来源系统
	public final static String SRC_SYS_01_VALUE= "app";
	
	public final static String TELNO_REGIST_PLEASE_ENTER_CORRECT_NUMBER= "01";//客户端/手机号注册
	public final static String TELNO_REGIST_PLEASE_ENTER_CORRECT_NUMBER_VALUE= "请输入正确的手机号";
	public final static String TELNO_REGIST_ALREADY_REGISTERED= "02";//客户端/手机号注册
	public final static String TELNO_REGIST_ALREADY_REGISTERED_VALUE= "该手机号已注册";
	
	public final static String TEL_TYPE_PHONE= "01";//客户端/电话类型
	public final static String TEL_TYPE_PHONE_VALUE= "固话";
	public final static String TEL_TYPE_CELLPHONE= "02";//客户端/电话类型
	public final static String TEL_TYPE_CELLPHONE_VALUE= "手机";
	
	public final static String TERMIAL_TYPE_PC= "01";//卡片账/交易终端类型
	public final static String TERMIAL_TYPE_PC_VALUE= "PC";
	public final static String TERMIAL_TYPE_MOBILE= "02";//卡片账/交易终端类型
	public final static String TERMIAL_TYPE_MOBILE_VALUE= "手机";
	public final static String TERMIAL_TYPE_SCANNER_GUN= "03";//卡片账/交易终端类型
	public final static String TERMIAL_TYPE_SCANNER_GUN_VALUE= "扫描枪";
	public final static String TERMIAL_TYPE_04= "04";//卡片账/交易终端类型
	public final static String TERMIAL_TYPE_04_VALUE= "Web";
	
	public final static String TIME_TYPE_TRANSACTION_TIME= "01";//资金汇划/时间类型
	public final static String TIME_TYPE_TRANSACTION_TIME_VALUE= "交易时间";
	public final static String TIME_TYPE_INTERFACING_TIME= "02";//资金汇划/时间类型
	public final static String TIME_TYPE_INTERFACING_TIME_VALUE= "接入网关进入时间";
	public final static String TIME_TYPE_HANDLING_TIME= "03";//资金汇划/时间类型
	public final static String TIME_TYPE_HANDLING_TIME_VALUE= "处理时间";
	public final static String TIME_TYPE_RECORD_TIME= "04";//资金汇划/时间类型
	public final static String TIME_TYPE_RECORD_TIME_VALUE= "到帐时间";
	public final static String TIME_TYPE_BANK_REQ_TIME= "05";//资金汇划/时间类型
	public final static String TIME_TYPE_BANK_REQ_TIME_VALUE= "请求时间";
	
	public final static String TITLE_HIGH_LEVEL= "01";//客户端/职称
	public final static String TITLE_HIGH_LEVEL_VALUE= "高级";
	public final static String TITLE_MEDIUM_LEVEL= "02";//客户端/职称
	public final static String TITLE_MEDIUM_LEVEL_VALUE= "中级";
	public final static String TITLE_PRIME_LEVEL= "03";//客户端/职称
	public final static String TITLE_PRIME_LEVEL_VALUE= "初级";
	
	public final static String TRADE_FAIL_REASON_BENEFICIARY_ACCOUNT_NOT_EXIST= "01";//资金汇划/交易失败原因
	public final static String TRADE_FAIL_REASON_BENEFICIARY_ACCOUNT_NOT_EXIST_VALUE= "收款账户不存在";
	public final static String TRADE_FAIL_REASON_PAYING_ACCOUNT_NO_EXIST= "02";//资金汇划/交易失败原因
	public final static String TRADE_FAIL_REASON_PAYING_ACCOUNT_NO_EXIST_VALUE= "付款账户不存在";
	public final static String TRADE_FAIL_REASON_PAYING_ACCOUNT_BALANCE_NOT_ENOUGH= "03";//资金汇划/交易失败原因
	public final static String TRADE_FAIL_REASON_PAYING_ACCOUNT_BALANCE_NOT_ENOUGH_VALUE= "付款账户余额不足";
	public final static String TRADE_FAIL_REASON_ABNORMAL_PAYING_ACCOUNT_= "04";//资金汇划/交易失败原因
	public final static String TRADE_FAIL_REASON_ABNORMAL_PAYING_ACCOUNT__VALUE= "付款账户状态不正常";
	public final static String TRADE_FAIL_REASON_ABNORMAL_BENEFICIARY_ACCOUNT_= "05";//资金汇划/交易失败原因
	public final static String TRADE_FAIL_REASON_ABNORMAL_BENEFICIARY_ACCOUNT__VALUE= "收款账户状态不正常";
	
	public final static String TRANS_DC_INCOMING= "01";//卡片账/交易方向
	public final static String TRANS_DC_INCOMING_VALUE= "收入";
	public final static String TRANS_DC_OUTGOING= "02";//卡片账/交易方向
	public final static String TRANS_DC_OUTGOING_VALUE= "支出";
	
	public final static String TRANS_FAIL_HANDLE_STATUS_UNPROCESSED= "01";//资金汇划/交易失败数据处理状态
	public final static String TRANS_FAIL_HANDLE_STATUS_UNPROCESSED_VALUE= "已处理";
	public final static String TRANS_FAIL_HANDLE_STATUS_PROCESSED= "02";//资金汇划/交易失败数据处理状态
	public final static String TRANS_FAIL_HANDLE_STATUS_PROCESSED_VALUE= "未处理";
	
	public final static String TRANS_STATUS_UNPROCESSED= "01";//卡片账/交易状态
	public final static String TRANS_STATUS_UNPROCESSED_VALUE= "待处理";
	public final static String TRANS_STATUS_PROCESSING= "02";//卡片账/交易状态
	public final static String TRANS_STATUS_PROCESSING_VALUE= "处理中";
	public final static String TRANS_STATUS_SUCCESS= "03";//卡片账/交易状态
	public final static String TRANS_STATUS_SUCCESS_VALUE= "成功";
	public final static String TRANS_STATUS_FAIL= "04";//卡片账/交易状态
	public final static String TRANS_STATUS_FAIL_VALUE= "失败";
	public final static String TRANS_STATUS_DATA= "05";//卡片账/交易状态
	public final static String TRANS_STATUS_DATA_VALUE= "异常";
	
	public final static String TRANS_TYPE_TOPUP= "02";//卡片账/交易类型
	public final static String TRANS_TYPE_TOPUP_VALUE= "充值";
	public final static String TRANS_TYPE_WITHDRAW= "03";//卡片账/交易类型
	public final static String TRANS_TYPE_WITHDRAW_VALUE= "提现";
	public final static String TRANS_TYPE_CLEARING_SETTLEMENT= "04";//卡片账/交易类型
	public final static String TRANS_TYPE_CLEARING_SETTLEMENT_VALUE= "清结算";
	
	public final static String UNIT_PROPERTY_STATE_OWNED_ENTERPRISE= "01";//客户端/企业性质
	public final static String UNIT_PROPERTY_STATE_OWNED_ENTERPRISE_VALUE= "国有企业 ";
	public final static String UNIT_PROPERTY_SINO_FOREIGN_JOINT_VENTURES= "02";//客户端/企业性质
	public final static String UNIT_PROPERTY_SINO_FOREIGN_JOINT_VENTURES_VALUE= "三资企业";
	public final static String UNIT_PROPERTY_COLLECTIVELY_RUN_ENTERPRISE= "03";//客户端/企业性质
	public final static String UNIT_PROPERTY_COLLECTIVELY_RUN_ENTERPRISE_VALUE= "集体企业";
	public final static String UNIT_PROPERTY_PRIVATE_ENTERPRISE= "04";//客户端/企业性质
	public final static String UNIT_PROPERTY_PRIVATE_ENTERPRISE_VALUE= "私营企业 ";
	
	//风控/事件来源
	public final static String VIOLATION_TYPE_ABNORMAL_IP= "01";
	public final static String VIOLATION_TYPE_ABNORMAL_IP_VALUE= "IP异常";
	public final static String VIOLATION_TYPE_BLACK_LIST= "02";
	public final static String VIOLATION_TYPE_BLACK_LIST_VALUE= "黑名单";
	public final static String VIOLATION_TYPE_PAYMENT_EXCEED_LIMIT= "03";
	public final static String VIOLATION_TYPE_PAYMENT_EXCEED_LIMIT_VALUE= "支付超限";
	
	//备付金/凭证类型
	public final static String WARRANT_TYPE_BANK_DRAFT_PROXY= "01";
	public final static String WARRANT_TYPE_BANK_DRAFT_PROXY_VALUE= "银行汇票委托书";
	public final static String WARRANT_TYPE_BANK_DRAFT= "02";
	public final static String WARRANT_TYPE_BANK_DRAFT_VALUE= "银行汇票";
	public final static String WARRANT_TYPE_BANK_DRAFT_MISSING_REPORT_TELEGRAPH= "03";
	public final static String WARRANT_TYPE_BANK_DRAFT_MISSING_REPORT_TELEGRAPH_VALUE= "银行汇票挂失电报";
	
	//客户端/提现次数
	public final static String WITHDRAW_TIMES_ERROR_WITHDRAW_EXCEED_LIMIT= "01";
	public final static String WITHDRAW_TIMES_ERROR_WITHDRAW_EXCEED_LIMIT_VALUE= "提现次数超限";
	
	//客户端/是否已婚
	public final static String YESORNO_YES= "01";
	public final static String YESORNO_YES_VALUE= "是";
	public final static String YESORNO_NO= "02";
	public final static String YESORNO_NO_VALUE= "否";
	public final static String YESORNO_CONFIDENTIAL= "03";
	public final static String YESORNO_CONFIDENTIAL_VALUE= "保密";
	
	public final static String ZONE_GTM8 = "01";//时区
	public final static String ZONE_GTM8_VALUE = "东八区";
	
	// 企业开户申请
	public final static String CUST_TRACE_STATUS_PENDING = "01";
	public final static String CUST_TRACE_STATUS_PENDING_VALUE ="待处理";
	public final static String CUST_TRACE_STATUS_HANDLING ="02";
	public final static String CUST_TRACE_STATUS_HANDLING_VALUE ="处理中";
	public final static String CUST_TRACE_STATUS_BANK_TRANSFER_SUCCEED ="03";
	public final static String CUST_TRACE_STATUS_BANK_TRANSFER_SUCCEED_VALUE = "银行汇款成功";
	public final static String CUST_TRACE_STATUS_BANK_TRANSFER_FAILED ="04";
	public final static String CUST_TRACE_STATUS_BANK_TRANSFER_FAILED_VALUE ="银行汇款失败";
	public final static String CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID ="05";
	public final static String CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID_VALUE ="等待客户验证";
	public final static String CUST_TRACE_STATUS_SUCCESS ="06";
	public final static String CUST_TRACE_STATUS_SUCCESS_VALUE ="成功";
	
	// 银行级别
	public final static String BANK_LEVEL_HEAD = "01";
	public final static String BANK_LEVEL_HEAD_VALUE = "总行";
	public final static String BANK_LEVEL_BRANCH = "02";
	public final static String BANK_LEVEL_BRANCH_VALUE = "分行";
	public final static String BANK_LEVEL_SUB_BRANCH = "03";
	public final static String BANK_LEVEL_SUB_BRANCH_VALUE = "支行";
	
	// 银行返回状态
	public final static String BANK_RETURN_RESULT_SUCCESS = "01";
	public final static String BANK_RETURN_RESULT_SUCCESS_VALUE = "成功";
	public final static String BANK_RETURN_RESULT_FAILED = "02";
	public final static String BANK_RETURN_RESULT_FAILED_VALUE = "失败";
	
	// 备付金类型
	public final static String BANK_RESERVED_TYPE_CUSTODY_ACCOUNT= "01";
	public final static String BANK_RESERVED_TYPE_CUSTODY_ACCOUNT_VALUE= "存管帐户";
	public final static String BANK_RESERVED_TYPE_REMIT_ACCOUNT= "02";
	public final static String BANK_RESERVED_TYPE_REMIT_ACCOUNT_VALUE= "汇缴帐户";
	public final static String BANK_RESERVED_TYPE_CASH_ACCOUNT= "03";
	public final static String BANK_RESERVED_TYPE_CASH_ACCOUNT_VALUE= "收付帐户";
	
	/**
	 * 返回给内蒙古的转账查询结果代码 成功
	 */
	public final static String TRANS_RETTONMG_SUCCESS="0001";
	/**
	 * 返回给内蒙古的转账查询结果代码 失败
	 */
	public final static String TRANS_RETTONMG_FAIL="0002";

	
	
	// ***********************************************************************
	
	
	
	
	/**
	 * 等待审核数据
	 */
	public static final String STATUS_WAIT_AUDIT = "AUDIT";
	public static final String STATUS_WAIT_AUDIT_VALUE = "等待审核数据";
	
	/**
	 * 审核退回数据
	 */
	public static final String STATUS_AUDIT_RETURN = "REJECT";
	public static final String STATUS_AUDIT_RETURN_VALUE = "审核退回数据";
	
	/**
	 * 临时数据
	 */
	public static final String STATUS_TEMP = "TEMP";
	public static final String STATUS_TEMP_VALUE = "草稿数据";
	
 
	
	/**
	 * 启用
	 */
	public static final char INUSED='Y';
	public static final String INUSED_VALUE="INUSED";
//	public static final String INUSED_VALUE_CN="启用";
	
	/**
	 * 员工是否直接挂靠在该组织机构
	 */
	public static final char DIRECT_CONNECT='Y';
	public static final char NOT_DIRECT_CONNECT='N';
	
 
	
	/**
	 * 暂时不可用
	 */
	public static final char UNUSED='N';
	public static final String UNUSED_VALUE="UNUSED";
//	public static final String UNUSED_VALUE_CN="未启用";
	
	
	/**
	 * 组织机构根代码
	 */
//	public static final String ROOT_ORG_CODE="YT0001";
	public static final String ROOT_ORG_CODE="999999";
	public static final String ROOT_ORG_NAME="总公司";
	public static final String NG_ORG_CODE="NG";
	
	/**
	 *  组织机构最大层级
	 */
	public static final int MAX_LEVEL=7;
	
	
	
	/**
	 * 用户资金账号是否默认
	 */
	public static final String  BANK_IS_DEFAULT= "01";
	public static final String BANK_IS_DEFAULT_VALUE = "默认";
	
	/**
	 * 用户资金账号类型
	 */
	public static final String BANK_ANCT_TYPE = "01";
	public static final String BANK_ANCT_TYPE_VALUE = "支付宝";
	
	/**
	 * 父级账户编码
	 */
	public static final String  P_CNL_ACNT_CODE= "00";
	public static final String P_CNL_ACNT_CODE_VALUE = "无";

	
	
	/**
	 * action 常用跳转 url 
	 * **/
	public static final String CREATE_ACTION_URL = "create";
	public static final String MODIFY_ACTION_URL = "modify";
	public static final String ERROR_SAVE_ACTION_URL = "errorsave";
	public static final String AJAX_SAVE_ACTION_URL = "ajaxsave";

	
 
 
	
	/**
	 * 信息分隔符
	 */
	public static final String INFOR_SPLIT="|";
	
	public static final String NAME_SPLIT=",";
	
	public static final String PERHAPS_SPLIT=",，;；|";
	
	/**
	 * 是否全网
	 * **/
	public static final String IS_FOR_ALL = "Y";
	public static final String NOT_FOR_ALL = "N";
	
	public static final String TRUE_KEY="Y";
	public static final String FALSE_KEY="N";
	
	/**
	 * priceRule Expression constant
	 * **/
	public static final String PRICERULE_EXPRESSION_FIXED = "FX"; // 固定值
	public static final String PRICERULE_EXPRESSION_TOTAL = "TL"; // 总重量
	public final static String PRICERULE_EXPRESSION_WEIGHT = "WG"; //重量
	public final static String PRICERULE_EXPRESSION_WEIGHT_PRICE = "WP"; //重量单价
	public final static String PRICERULE_EXPRESSION_FIRST_WEIGHT = "FW"; //首重
	public final static String PRICERULE_EXPRESSION_ADD_WEIGHT = "AW"; //续重
	public final static String PRICERULE_EXPRESSION_FIRST_WEIGHT_PRICE = "FP"; //首重单价
	public final static String PRICERULE_EXPRESSION_ADD_WEIGHT_PRICE = "AP"; //续重单价
	public final static String PRICERULE_EXPRESSION_DISCOUNT = "DC"; //折扣率
	public final static String PRICERULE_EXPRESSION_PARAM1 = "PARAM1"; //变量1
	public final static String PRICERULE_EXPRESSION_PARAM2 = "PARAM2"; //变量2
	public final static String PRICERULE_EXPRESSION_PARAM3 = "PARAM3"; //变量3
	public final static String PRICERULE_EXPRESSION_PARAM4 = "PARAM4"; //变量4
	public final static String PRICERULE_EXPRESSION_PARAM5 = "PARAM5"; //变量5
	public final static String PRICE_BOTTOM_LINE = "BL"; //最低收费价格
	
	/**
	 * 奖惩原因标志
	 * **/
	public static final char SR_FLAG_YES = 'Y';
	public static final char SR_FLAG_NO = 'N';
	public static final char SR_FLAG_YES_VALUE = '是';
	public static final char SR_FLAG_NO_VALUE = '否';
	
	public final static String[] PRICE_RULE_EXPRESSIONS = {
		PRICERULE_EXPRESSION_FIXED,
		PRICERULE_EXPRESSION_TOTAL,
		PRICERULE_EXPRESSION_WEIGHT,
		PRICERULE_EXPRESSION_WEIGHT_PRICE,
		PRICERULE_EXPRESSION_FIRST_WEIGHT,
		PRICERULE_EXPRESSION_ADD_WEIGHT,
		PRICERULE_EXPRESSION_FIRST_WEIGHT_PRICE,
		PRICERULE_EXPRESSION_ADD_WEIGHT_PRICE,
		PRICERULE_EXPRESSION_PARAM1,
		PRICERULE_EXPRESSION_PARAM2,
		PRICERULE_EXPRESSION_PARAM3};

//	public static List<String> priceRuleExpressions = new ArrayList<String>();
//	static{
//		priceRuleExpressions.add(priceRule_expression_fixed);
//		priceRuleExpressions.add(priceRule_expression_total);
//		priceRuleExpressions.add(priceRule_expression_weight);
//		priceRuleExpressions.add(priceRule_expression_weight_price);
//		priceRuleExpressions.add(priceRule_expression_first_weight);
//		priceRuleExpressions.add(priceRule_expression_add_weight);
//		priceRuleExpressions.add(priceRule_expression_first_weight_price);
//		priceRuleExpressions.add(priceRule_expression_add_weight_price);
//		priceRuleExpressions.add(priceRule_expression_param1);
//		priceRuleExpressions.add(priceRule_expression_param2);
//		priceRuleExpressions.add(priceRule_expression_param3);
//	}
	

	public final static String CARRY_TYPE_ROUND = "1"; //四舍五入
	public final static String CARRY_TYPE_REDUCE = "2"; //去位取整
	public static final Map<String,String> CARRY_TYPE_MAP = new HashMap<String,String>();
	static {
		CARRY_TYPE_MAP.put(CARRY_TYPE_ROUND, "四舍五入");
		CARRY_TYPE_MAP.put(CARRY_TYPE_REDUCE, "去位取整");
	}
	
	
	public final static String CALCULATE_TYPE_FIX = "1"; //固定值
	public final static String CALCULATE_TYPE_SCALE = "2"; //比例值
	public final static String CALCULATE_TYPE_BALANCE = "3"; //自动结余
	
	public static final Map<String,String> CALCULATE_TYPE_MAP = new HashMap<String,String>();
	static {
		CALCULATE_TYPE_MAP.put(CALCULATE_TYPE_FIX, "固定值");
		CALCULATE_TYPE_MAP.put(CALCULATE_TYPE_SCALE, "比例值");
		CALCULATE_TYPE_MAP.put(CALCULATE_TYPE_BALANCE, "自动结余");
	}
	
	
	
	/**
	 * 弹出窗口中是否能选择组织机构
	 */
	public final static String CAN_NOT_SELECT_ORG="canNotSelect";
	
 
	/**
	 * 封装json对象使用常量
	 */
	public final static String SUCCESS="success";
	public final static String ERROR="error";
	
 
	public final static List<OptionObjectPair> YES_OR_NO_OPTION_LIST = new ArrayList<OptionObjectPair>();
	static{
		YES_OR_NO_OPTION_LIST.add(new OptionObjectPair(TRUE_KEY,"是"));
		YES_OR_NO_OPTION_LIST.add(new OptionObjectPair(FALSE_KEY,"否"));
	}


	public static final String AJAX_MESSAGE = "ajaxMessage";
	
//	public final static List<OptionObjectPair> GENDER = new ArrayList<OptionObjectPair>();
//	static{
//		GENDER.add(new OptionObjectPair("1","男"));
//		GENDER.add(new OptionObjectPair("2","女"));
//	}
//	
//	public final static Map<String,String> GENDERMap = new HashMap<String,String>();
//	static{
//		GENDERMap.put("1","男");
//		GENDERMap.put("2","女");
//	}
	
	/**
	 * 主数据中的Cache
	 */
	public final static String ORG_CACHE = "_org_cache_region";
	public final static String ORG_BUSINESS_RULE_CACHE = "_org_business_rule_cache_region";
	public final static String DATA_DICT_CACHE = "_data_dict_cache_region";
	public final static String ORG_LAYER_CACHE = "_org_layer_vo_cache_region";
	public final static String DISTRICT_CACHE = "_district_cache_region";
	public final static String ORG_GROUP_CACHE = "_org_group_cache_region";
	public final static String PRODUCT_CACHE = "_product_cache_region";
	public final static String FREIGHT_DETAIL_CACHE = "_freight_detail_cache_region";
	public final static String EFFECTIVE_TYPE_CACHE = "_effective_type_cache_region";
	public final static String EXPRESS_CONTENT_CACHE = "_express_content_cache_region";
	public final static String OP_FREQUENCY_CACHE = "_op_frequency_cache_region";
	public final static String SCAN_RULE_CACHE="_sacn_rule_cache_region";
	public final static String EMP_NAME_CACHE="_emp_name_region";
	public final static String DISTRICT_LAYER_CACHE = "_district_layer_cache_region";
	
	public final static String DISTRICT_LAYER_MAP_CODE_KEY="DISTRICT_LAYER_MAP_CODE_KEY";
	public final static String DISTRICT_LAYER_MAP_ID_KEY="DISTRICT_LAYER_MAP_ID_KEY";
	public final static String DISTRICT_LAYER_ENTRY_KEY="DISTRICT_LAYER_ENTRY_KEY";
	 
	
	
	
	public static final String SCAN_RULE_CACHE_KEY = "SCAN_RULE_CACHE_KEY";
	
	public static final String CUSTOMER_CACHE = "_customer_cache_region";
	
	
	public static final String CACHE_KEY_SEPERATOR = "#";
	
	public static final String ORG_GROUP_TYPE_START ="start";
	public static final String ORG_GROUP_TYPE_END ="end";
	
	public static final String FREIGHT_DEFAULT_END_DATE = "2999-12-31";

	
	public static final String DATA_DICT_IO_TYPE_NORMAL_CODE = "NORMAL";
	public static final String DATA_DICT_IO_TYPE_WITHDRAW_1_CODE = "WITHDRAW_1";

	public static final String CUSTOMER_TYPE_SK="SK";
	/**
	 * 是否常量
	 */
	public static final String YES_OR_NO_Y="Y";
	public static final String YES_OR_NO_N="N";
	/**
	 * 全网网点组
	 */
	public static final String ORG_GROUP_ALL_NET_NODE_ID="ALL_NET_GROUP";
	public static final String ORG_GROUP_ALL_NET_NODE_CODE="ALL_NET_GROUP";
	public static final String ORG_GROUP_ALL_NET_NODE_NAME="全网组";
	public static final String ORG_GROUP_ALL_NET_NODE_PINYIN="quanwangzu";
	
	public final static String PRICE_TYPE_SCOPE_SPLIT = "split"; 
	public final static String PRICE_TYPE_SCOPE_HEADQUARTER = "headquarter"; 
	public final static String PRICE_TYPE_SCOPE_DEPARTMENT = "department"; 
	public final static String PRICE_TYPE_SCOPE_CUSTOMER = "customer"; 
	public final static String PRICE_TYPE_SCOPE_INTERNATIONALDEPT = "internationaldept"; 
	public final static String PRICE_TYPE_SCOPE_INTERNATIONALFOR = "internationalfor"; 
	public static final Map<String,String> PRICE_TYPE_SCOPE = new HashMap<String,String>();
	static {
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_SPLIT, "分成价格类型");
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_HEADQUARTER, "总部价格类型");
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_DEPARTMENT, "分公司价格类型");
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_CUSTOMER, "客户价格类型");
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_INTERNATIONALDEPT, "国际件网点价格类型");
		PRICE_TYPE_SCOPE.put(PRICE_TYPE_SCOPE_INTERNATIONALFOR, "国际件货代价格类型");
		
	}
	
	public static  final String FREIGHT_RANGE_ORG = "org"; // 网点运费
	public static  final String FREIGHT_RANGE_CUSTOMER = "customer";//客户运费
	/**
	 * 物料中普通面单对应的代码，供计费使用
	 */
	public static final String MAT_COMMON_WAYBILL_CODE = "100100";
	
	/**
	 * 计费方式：默认为按票
	 * 注意：此处与com.ibs.core.module.expcommon.dto.WaybillConstant有冗余
	 */
    public static final String EXP_TYPE_WAYBILL = "10"; // 运单
    public static final String EXP_TYPE_WAYBILL_VALUE = "每件"; // 运单
    public static final String EXP_TYPE_PACKAGE = "20"; // 包
    public static final String EXP_TYPE_PACKAGE_VALUE = "每包"; // 包
    public static final String EXP_TYPE_CAGE = "30"; // 笼
    public static final String EXP_TYPE_CAGE_VALUE = "每笼"; // 笼
    public static final String EXP_TYPE_TRUCK = "40"; // 车
    public static final String EXP_TYPE_TRUCK_VALUE = "每车"; // 车
    public static final List<OptionObjectPair> EXP_TYPE_LIST = new ArrayList<OptionObjectPair>();
    static{
    	EXP_TYPE_LIST.add(new OptionObjectPair(EXP_TYPE_WAYBILL,EXP_TYPE_WAYBILL_VALUE));
    	EXP_TYPE_LIST.add(new OptionObjectPair(EXP_TYPE_PACKAGE,EXP_TYPE_PACKAGE_VALUE));
    	EXP_TYPE_LIST.add(new OptionObjectPair(EXP_TYPE_CAGE,EXP_TYPE_CAGE_VALUE));
    	EXP_TYPE_LIST.add(new OptionObjectPair(EXP_TYPE_TRUCK,EXP_TYPE_TRUCK_VALUE));
    }
    
    public static final  String  PLEASE_CHOOSE_ONE = "--pleaseChooseOne--";
    
    /** 启用预警金额的标志位*/
	public final static char ALARM_ENABLED = 'Y';
	
	/** 关闭预警金额的标志位*/
	public final static char ALARM_DISABLED = 'N';
	
	/**
	 * 无承运商
	 */
	public final static String NO_CARRIER_CODE="NOCARRIERCODE";
	
	
    /**
     * 过程适用范围类型    ALL 全网， INTORG 国际部   
     */
	
	/**
	 *   ALL 全网 
	 */
	public final static String ALL="ALL";
	/**
	 *  INTORG 国际部   
	 */
	public final static String INTORG="INTORG";
	
	/**
	 * SYSADMIN 
	 */
	public final static String SYSADMIN = "SYSADMIN";
}
