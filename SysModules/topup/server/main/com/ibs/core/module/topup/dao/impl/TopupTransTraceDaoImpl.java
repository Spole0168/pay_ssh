/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.topup.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.account.domain.CnlCommentDto;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.reservedfund.domain.CorReservedFundAcnt;
import com.ibs.core.module.topup.dao.ITopupTransTraceDao;
import com.ibs.core.module.topup.domain.TopupTransTrace;
import com.ibs.core.module.topup.dto.TopupTransTraceDto;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class TopupTransTraceDaoImpl extends BaseEntityDao<TopupTransTrace> implements
		ITopupTransTraceDao {

	@Override
	public IPage<TopupTransTraceDto> findCnlTransBySql(QueryPage queryPage,TopupTransTraceDto conditionDto) {
		logger.info("entering action::findCnlTransBySql.findCnlTransBySql()...");

		queryPage.clearSortMap();
		queryPage.clearQueryCondition();

		// 检索条件：渠道编码
		String cnlCnlCode = conditionDto.getCnlCnlCode();
		String cnlSysName = conditionDto.getCnlSysName();
		String cnlCustCode = conditionDto.getCnlCustCode();
		String localName = conditionDto.getLocalName();

		StringBuffer sb1 = new StringBuffer("(SELECT DISTINCT ");
		sb1.append("CNL_CNL_CODE, CNL_CUST_CODE, CNL_SYS_NAME");
		sb1.append(" FROM CNL.T_CNL_CNL_CFG ");
		sb1.append(" WHERE IS_VALID='").append(Constants.IS_VALID_VALID).append("' ");
		// 检索条件：渠道编码
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			sb1.append(" AND CNL_CNL_CODE = '").append(cnlCnlCode).append("'");
		}
		// 检索条件：客户编号
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			sb1.append(" AND CNL_CUST_CODE = '").append(cnlCustCode).append("'");
		}
		// 检索条件：渠道名称
		if (StringUtils.isNotEmpty(cnlSysName)) {
			sb1.append(" AND CNL_SYS_NAME LIKE '%").append(cnlSysName).append("%'");
		}
		sb1.append(") T2,");

		StringBuffer sb2 = new StringBuffer("(SELECT DISTINCT ");
		sb2.append("CNL_CNL_CODE, CNL_CUST_CODE, LOCAL_NAME");
		sb2.append(" FROM CNL.T_CNL_CUST ");
		sb2.append(" WHERE IS_VALID='").append(Constants.IS_VALID_VALID).append("' ");
		// 检索条件：渠道编码
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			sb2.append(" AND CNL_CNL_CODE = '").append(cnlCnlCode).append("'");
		}
		// 检索条件：客户编号
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			sb2.append(" AND CNL_CUST_CODE = '").append(cnlCustCode).append("'");
		}
		// 检索条件：客户名称
		if (StringUtils.isNotEmpty(localName)) {
			sb2.append(" AND LOCAL_NAME LIKE '%").append(localName).append("%'");
		}
		sb2.append(") T3");

		StringBuffer sb = new StringBuffer("SELECT DISTINCT ");
		sb.append("T1.ID as id,");
		sb.append("T1.REQ_NUM as reqNum,");
		sb.append("T1.REQ_INNER_NUM as reqInnerNum,");
		sb.append("T1.CNL_CNL_CODE as cnlCnlCode,");
		sb.append("T1.CNL_CUST_CODE as cnlCustCode,");
		sb.append("T1.TRANS_TIME as transTime,");
		sb.append("T1.TRANS_AMOUNT as transAmount,");
		sb.append("T1.TRANS_LATEST_AMOUNT as transLatestAmount,");
		sb.append("T1.BANK_DEBIT_NAME as bankDebitName,");
		sb.append("T1.BANK_DEBIT_CUST_NAME as bankDebitCustName,");
		sb.append("T1.BANK_DEBIT_CARD_NUM as bankDebitCardNum,");
		sb.append("T1.BANK_CREDIT_NAME as bankCreditName,");
		sb.append("T1.BANK_CREDIT_CUST_NAME as bankCreditCustName,");
		sb.append("T1.BANK_CREDIT_CARD_NUM as bankCreditCardNum,");
		sb.append("T1.OPERATOR as operator,");
		sb.append("T1.HANDLE_TIME as handleTime,");
		sb.append("T1.REVIEWER as reviewer,");
		sb.append("T1.REVIEW_TIME as reviewTime,");
		sb.append("T1.HANDLE_STATUS as handleStatus,");
		sb.append("T1.HANDLE_MSG as handleMsg,");
		sb.append("T2.CNL_SYS_NAME as cnlSysName,");
		sb.append("T3.LOCAL_NAME as localName,");
		sb.append("T1.TRANS_CURRENCY as transCurrency,");
		sb.append("T1.TRANS_DC as transDc,");
		sb.append("T1.BANK_RETURN_TIME as bankReturnTime,");
		sb.append("T1.TRANS_TYPE as transType,");
		sb.append("T1.TRANS_COMMENTS as transComments,");
		sb.append("T1.TRANS_STATUS as transStatus,");
		sb.append("T1.BANK_TRANS_NUM as bankTransNum,");
		sb.append("T1.BANK_PMT_CNL_TYPE as bankPmtCnlType,");
		sb.append("T1.VOUCHER_LOCATION as voucherLocation,");
		sb.append("T1.BANK_RETURN_RESULT as bankReturnResult,");
		sb.append("T1.TERMIAL_TYPE as termialType,");
		sb.append("T1.TRANS_BANK_SUMMARY as transBankSummary,");
		sb.append("T1.HANDLE_RESULT as handleResult");
		sb.append(" FROM ");
//		sb.append("(CNL.T_CNL_TRANS_TRACE T1 LEFT JOIN CNL.T_CNL_CNL_CFG T2 ON (T1.CNL_CNL_CODE = T2.CNL_CNL_CODE AND T1.CNL_CUST_CODE = T2.CNL_CUST_CODE)) LEFT JOIN CNL.T_CNL_CUST T3 ON (T1.CNL_CUST_CODE = T3.CNL_CUST_CODE AND T1.CNL_CNL_CODE = T3.CNL_CNL_CODE)");
		sb.append("CNL.T_CNL_TRANS_TRACE T1,");
		sb.append(sb1.toString());
		sb.append(sb2.toString());
		sb.append(" WHERE ");
		sb.append(" T1.IS_VALID='").append(Constants.IS_VALID_VALID).append("'");
		sb.append(" AND T1.CNL_CNL_CODE = T2.CNL_CNL_CODE(+) AND T1.CNL_CUST_CODE = T2.CNL_CUST_CODE(+)");
		sb.append(" AND T1.CNL_CNL_CODE = T3.CNL_CNL_CODE(+) AND T1.CNL_CUST_CODE = T3.CNL_CUST_CODE(+)");

		// 固定限定条件:交易状态：待处理
		sb.append(" AND T1.TRANS_STATUS = '").append(Constants.TRANS_STATUS_UNPROCESSED).append("'");

		// 检索条件：渠道申请单号
		String reqNum = conditionDto.getReqNum();
		if (StringUtils.isNotEmpty(reqNum)) {
			sb.append(" AND T1.REQ_NUM = '").append(reqNum).append("'");
		}
		// 检索条件：系统申请单流水号
		String reqInnerNum = conditionDto.getReqInnerNum();
		if (StringUtils.isNotEmpty(reqInnerNum)) {
			sb.append(" AND T1.REQ_INNER_NUM = '").append(reqInnerNum).append("'");
		}
		// 检索条件：渠道编码
		if (StringUtils.isNotEmpty(cnlCnlCode)) {
			sb.append(" AND T1.CNL_CNL_CODE = '").append(cnlCnlCode).append("'");
		}
		// 检索条件：客户编号
		if (StringUtils.isNotEmpty(cnlCustCode)) {
			sb.append(" AND T1.CNL_CUST_CODE = '").append(cnlCustCode).append("'");
		}
		// 检索条件：处理状态
		String handleStatus = conditionDto.getHandleStatus();
		if (StringUtils.isNotEmpty(handleStatus)) {
			sb.append(" AND T1.HANDLE_STATUS = '").append(handleStatus).append("'");
		}
		// 检索条件：交易开始时间
		String startTime = conditionDto.getStartTime();
		if (StringUtils.isNotEmpty(startTime)) {
			sb.append(" AND TO_CHAR(T1.TRANS_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" >= '").append(startTime).append("'");
		}
		// 检索条件：交易结束时间
		String endTime = conditionDto.getEndTime();
		if (StringUtils.isNotEmpty(startTime)) {
			sb.append(" AND TO_CHAR(T1.TRANS_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" <= '").append(endTime).append("'");
		}
		sb.append(" ORDER BY T1.TRANS_TIME ");
		queryPage.setSqlString(sb.toString());
		
		queryPage.addScalar("id" ,					new StringType());
		queryPage.addScalar("reqNum" ,				new StringType());
		queryPage.addScalar("reqInnerNum" ,			new StringType());
		queryPage.addScalar("cnlCnlCode" ,			new StringType());
		queryPage.addScalar("cnlCustCode" ,			new StringType());
		queryPage.addScalar("bankDebitName" ,		new StringType());
		queryPage.addScalar("bankDebitCustName" ,	new StringType());
		queryPage.addScalar("bankDebitCardNum" ,	new StringType());
		queryPage.addScalar("bankCreditName" ,		new StringType());
		queryPage.addScalar("bankCreditCustName" ,	new StringType());
		queryPage.addScalar("bankCreditCardNum" ,	new StringType());
		queryPage.addScalar("operator" ,			new StringType());
		queryPage.addScalar("reviewer" ,			new StringType());
		queryPage.addScalar("handleStatus" ,		new StringType());
		queryPage.addScalar("handleMsg" ,			new StringType());
		queryPage.addScalar("cnlSysName" ,			new StringType());
		queryPage.addScalar("localName" ,			new StringType());
		queryPage.addScalar("transDc" ,				new StringType());
		queryPage.addScalar("transType" ,			new StringType());
		queryPage.addScalar("transComments" ,		new StringType());
		queryPage.addScalar("transStatus" ,			new StringType());
		queryPage.addScalar("bankTransNum" ,		new StringType());
		queryPage.addScalar("bankPmtCnlType" ,		new StringType());
		queryPage.addScalar("voucherLocation" ,		new StringType());
		queryPage.addScalar("bankReturnResult" ,	new StringType());
		queryPage.addScalar("transCurrency" ,		new StringType());
		queryPage.addScalar("termialType" ,			new StringType());
		queryPage.addScalar("transBankSummary" ,	new StringType());
		queryPage.addScalar("handleResult" ,		new StringType());

		queryPage.addScalar("transAmount" ,			new BigDecimalType());
		queryPage.addScalar("transLatestAmount" ,	new BigDecimalType());

		queryPage.addScalar("transTime" ,			new DbTimestampType());
		queryPage.addScalar("handleTime" ,			new DbTimestampType());
		queryPage.addScalar("reviewTime" ,			new DbTimestampType());
		queryPage.addScalar("bankReturnTime" ,		new DbTimestampType());

		return super.findPageBySql(queryPage,TopupTransTraceDto.class);
	}
	public TopupTransTrace load(String id) {
		logger.info("entering action::TopupTransTraceDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(TopupTransTrace topupTransTrace) {
		logger.info("entering action::TopupTransTraceDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(topupTransTrace);
	}
	@Override
	public IPage<TopupTransTraceDto> findTopupTransTraceByPage(QueryPage queryPage, TopupTransTraceDto conditionDto) {
		logger.info("entering action::TopupTransTraceDaoImpl.findTopupTransTraceByPage()...");
		return findCnlTransBySql(queryPage, conditionDto);
	}
	@Override
	public void checks(TopupTransTrace topupTransTraceDao) {
		logger.info("entering action::TopupTransTraceDaoImpl.checks()...");
		super.saveOrUpdate(topupTransTraceDao);
	}
	@Override
	public List<TopupTransTrace> getTopupTransTraceList() {
		logger.info("entering action::TopupTransTraceDaoImpl.checks()...");
		List<TopupTransTrace> topupTransTraceList = super.findAll();
		return topupTransTraceList;
	}
	@Override
	public CorReservedFundAcnt getCorReservedFundAcntByBankCode(String bankCreditCode) {
		logger.info("entering action::TopupTransTraceDaoImpl.getCorReservedFundAcntByBankCode()...");
		
		StringBuffer sb = new StringBuffer("SELECT ");
		sb.append("BANK_ACNT_CODE as bankAcntCode, ");
		sb.append("BANK_NAME as bankName, ");
		sb.append("BANK_CODE as bankCode, ");
		sb.append("BANK_BRANCH_NAME as bankBranchName, ");
		sb.append("BANK_BRANCH_CODE as bankBranchCode, ");
		sb.append("CUST_NAME as custName, ");
		sb.append("BANK_CARD_NUM as bankCardNum, ");
		sb.append("MAX(UPDATE_TIME) AS updateTime ");
		sb.append("FROM ");
		sb.append("COR.T_COR_RESERVED_FUND_ACNT ");
		sb.append("WHERE ");
		sb.append("IS_VALID = '01' ");
		sb.append("AND BANK_CODE = '").append(bankCreditCode).append("' ");
		sb.append("GROUP BY ");
		sb.append("BANK_ACNT_CODE, ");
		sb.append("BANK_NAME, ");
		sb.append("BANK_CODE, ");
		sb.append("BANK_BRANCH_NAME, ");
		sb.append("BANK_BRANCH_CODE, ");
		sb.append("CUST_NAME, ");
		sb.append("BANK_CARD_NUM ");

		List<ColumnType> scallarList = new ArrayList<ColumnType>();
		scallarList.add(new ColumnType("bankAcntCode", 		new StringType()));
		scallarList.add(new ColumnType("bankName", 			new StringType()));
		scallarList.add(new ColumnType("bankCode", 			new StringType()));
		scallarList.add(new ColumnType("bankBranchName", 	new StringType()));
		scallarList.add(new ColumnType("bankBranchCode", 	new StringType()));
		scallarList.add(new ColumnType("custName", 			new StringType()));
		scallarList.add(new ColumnType("bankCardNum", 		new StringType()));
		scallarList.add(new ColumnType("updateTime", 		new DbTimestampType()));
		
		List<CorReservedFundAcnt> corReservedFundAcntList = 
				super.findBySql(sb.toString(), null, null, scallarList, CorReservedFundAcnt.class);
		
		if (corReservedFundAcntList != null && corReservedFundAcntList.size() != 0) {
			return corReservedFundAcntList.get(0);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
