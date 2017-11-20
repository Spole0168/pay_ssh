package com.ibs.core.module.topup.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class TopupTransTraceDto extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id; 					// ID
	private String reqNum; 				// 渠道申请单号(检索条件)
	private String reqInnerNum; 		// 系统申请单流水号(检索条件)
	private String cnlCnlCode; 			// 渠道编码(检索条件)
	private String cnlCustCode; 		// 客户编号(检索条件)
	private Date transTime; 			// 交易时间
	private BigDecimal transAmount; 	// 充值金额
	private BigDecimal transLatestAmount; 	// 实际到账金额
	private String bankDebitName; 		// 收款银行
	private String bankDebitCustName; 	// 收款户名
	private String bankDebitCardNum; 	// 收款卡号
	private String bankCreditName; 		// 付款银行
	private String bankCreditCustName; // 付款户名
	private String bankCreditCardNum; 	// 付款卡号
	private String reviewer; 			// 复核人
	private Date reviewTime; 			// 复核时间
	private String handleStatus; 		// 处理状态(检索条件)
	private String handleMsg; 			// 
	private String cnlSysName; 			// 渠道名称(检索条件)
	private String localName; 			// 客户名称(检索条件)

	private String startTime; 			// 交易开始时间(检索条件)
	private String endTime; 			// 交易结束时间(检索条件)
	
	private String operator;			// 审核人
	private Date handleTime	;			// 审核时间
	
	// 查看画面用
	private Date bankReturnTime;		// 到账时间
	private String transCurrency;		// 交易币种
	private String transDc;				// 交易方向
	private String transType;			// 交易类型
	private String transComments;		// 交易摘要
	private String transStatus;			// 交易状态
	private String bankTransNum;		// 银行交易流水号
	private String bankPmtCnlType;		// 交付通道
	private String voucherLocation;		// 凭证图片
	private String bankReturnResult;	// 失败原因
	private String termialType;			// 交易终端类型
	private String transBankSummary;	// 银行摘要
	private String handleResult;		// 处理结果


	// Getter & Setter
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getTransBankSummary() {
		return transBankSummary;
	}
	public void setTransBankSummary(String transBankSummary) {
		this.transBankSummary = transBankSummary;
	}
	public Date getBankReturnTime() {
		return bankReturnTime;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public BigDecimal getTransLatestAmount() {
		return transLatestAmount;
	}

	public void setBankReturnTime(Date bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public void setTransLatestAmount(BigDecimal transLatestAmount) {
		this.transLatestAmount = transLatestAmount;
	}

	public String getTransCurrency() {
		return transCurrency;
	}

	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}

	public String getTransDc() {
		return transDc;
	}

	public void setTransDc(String transDc) {
		this.transDc = transDc;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransComments() {
		return transComments;
	}

	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getBankTransNum() {
		return bankTransNum;
	}

	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}

	public String getBankPmtCnlType() {
		return bankPmtCnlType;
	}

	public void setBankPmtCnlType(String bankPmtCnlType) {
		this.bankPmtCnlType = bankPmtCnlType;
	}

	public String getVoucherLocation() {
		return voucherLocation;
	}

	public void setVoucherLocation(String voucherLocation) {
		this.voucherLocation = voucherLocation;
	}

	public String getBankReturnResult() {
		return bankReturnResult;
	}

	public void setBankReturnResult(String bankReturnResult) {
		this.bankReturnResult = bankReturnResult;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getReqInnerNum() {
		return reqInnerNum;
	}

	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}

	public String getCnlCnlCode() {
		return cnlCnlCode;
	}

	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlCustCode() {
		return cnlCustCode;
	}

	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public String getBankDebitName() {
		return bankDebitName;
	}

	public void setBankDebitName(String bankDebitName) {
		this.bankDebitName = bankDebitName;
	}

	public String getBankDebitCustName() {
		return bankDebitCustName;
	}

	public void setBankDebitCustName(String bankDebitCustName) {
		this.bankDebitCustName = bankDebitCustName;
	}

	public String getBankDebitCardNum() {
		return bankDebitCardNum;
	}

	public void setBankDebitCardNum(String bankDebitCardNum) {
		this.bankDebitCardNum = bankDebitCardNum;
	}

	public String getBankCreditName() {
		return bankCreditName;
	}

	public void setBankCreditName(String bankCreditName) {
		this.bankCreditName = bankCreditName;
	}

	public String getBankCreditCustName() {
		return bankCreditCustName;
	}

	public void setBankCreditCustName(String bankCreditCustName) {
		this.bankCreditCustName = bankCreditCustName;
	}

	public String getBankCreditCardNum() {
		return bankCreditCardNum;
	}

	public void setBankCreditCardNum(String bankCreditCardNum) {
		this.bankCreditCardNum = bankCreditCardNum;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	public String getCnlSysName() {
		return cnlSysName;
	}

	public void setCnlSysName(String cnlSysName) {
		this.cnlSysName = cnlSysName;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getTermialType() {
		return termialType;
	}

	public void setTermialType(String termialType) {
		this.termialType = termialType;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TopupTransTraceDto [id=" + id);
		sb.append(",reqNum=" 				+ reqNum);
		sb.append(",reqInnerNum=" 			+ reqInnerNum);
		sb.append(",cnlCnlCode=" 			+ cnlCnlCode);
		sb.append(",cnlCustCode=" 			+ cnlCustCode);
		sb.append(",transTime=" 			+ transTime);
		sb.append(",transAmount=" 			+ transAmount);
		sb.append(",transLatestAmount=" 	+ transLatestAmount);
		sb.append(",bankDebitName=" 		+ bankDebitName);
		sb.append(",bankDebitCustName=" 	+ bankDebitCustName);
		sb.append(",bankDebitCardNum=" 		+ bankDebitCardNum);
		sb.append(",bankCreditName=" 		+ bankCreditName);
		sb.append(",bankCreditCustName=" 	+ bankCreditCustName);
		sb.append(",bankCreditCardNum=" 	+ bankCreditCardNum);
		sb.append(",reviewer=" 				+ reviewer);
		sb.append(",reviewTime="			+ reviewTime);
		sb.append(",handleStatus=" 			+ handleStatus);
		sb.append(",handleMsg=" 			+ handleMsg);
		sb.append(",cnlSysName=" 			+ cnlSysName);
		sb.append(",localName=" 			+ localName);
		sb.append("]");
		return sb.toString();
	}

}
