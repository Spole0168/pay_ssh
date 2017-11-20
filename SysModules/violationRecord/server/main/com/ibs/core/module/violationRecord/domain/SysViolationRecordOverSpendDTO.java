package com.ibs.core.module.violationRecord.domain;

import java.util.Date;

public class SysViolationRecordOverSpendDTO 
{
  
	private String id;
	private String reqNum;//商户号	T_SYS_VIOLATION_RECORD.REQ_NUM
	private String cnlIntfCode;//	网关接入号 T_SYS_VIOLATION_RECORD.CNL_INTF_CODE
	private Date createTime;//	发生时间
	private String cnlCnlCode; //	渠道申请单号
	private String reqInnerNum; // REQ_INNER_NUM  //	系统申请单流水号
	private String transNum; // TRANS_NUM //	系统交易流水号 
	private String bankTransNum; // BANK_TRANS_NUM	//	银行交易流水号
	private String transCurrency; // TRANS_CURRENCY//	交易币种
	private String violationId; 	//	异常金额 T_SYS_VIOLATION_RECORD.VIOLATION_ID
	private String transDc; // TRANS_DC//	交易方向
	private String transType; // TRANS_TYPE//	交易类型
	private String transStatus; // TRANS_STATUS//	交易状态
	private String violationDesc; // T_SYS_VIOLATION_RECORD.VIOLATION_DESC //	交易摘要
	private Date transTime; // T_COR_BANK_ACNT_TRANS   TRANS_TIME// 银行交易流水	交易时间
	private String bnakHandlePriority; // BNAK_HANDLE_PRIORITY//	银行处理优先级
	private Date bankReqTrnasDate; // BANK_REQ_TRNAS_DATE//	要求转账日期
	private Date bankReqTransTime; // BANK_REQ_TRANS_TIME//	要求转账时间
	private String bnakServiceFeeAct; // BNAK_SERVICE_FEE_ACT//	支付转账手续费账号
	private Date bankAccepteTime; // BANK_ACCEPTE_TIME//	银行网关接入时间
	private Date bankReturnTime; // BANK_RETURN_TIME//	到账时间
	private String bankDebitName; // BANK_DEBIT_NAME//	收款银行
	private String bankDebitCustName; // BANK_DEBIT_CUST_NAME//	收款户名
	private String bankDebitCardNum; // BANK_DEBIT_CARD_NUM//	收款卡号
	private String bankCreditName; // BANK_CREDIT_NAME//	付款银行
	private String bankCreditCustName; // BANK_CREDIT_CUST_NAME//	付款户名
	private String bankCreditCardNum; // BANK_CREDIT_CARD_NUM//	付款卡号
	private String bankPmtCnlType; // BANK_PMT_CNL_TYPE//	支付通道
	private String cnlCustCode; // CNL_CUST_CODE//	渠道来源标识
	private String termialType; // TERMIAL_TYPE//	交易终端类型
	private String isinGl; // ISIN_GL//	入账标志
	private Date transTimeS;	//	T_CNL_CUST_ACNT_DTL.TRANS_TIME	  用户明细账  入账时间 
	private String isPrinted; // IS_PRINTED//	打印标志
	private Date printedTime; // PRINTED_TIME//	打印时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTransTimeS() {
		return transTimeS;
	}
	public void setTransTimeS(Date transTimeS) {
		this.transTimeS = transTimeS;
	}
	public String getReqNum() {
		return reqNum;
	}
	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}
	public String getCnlIntfCode() {
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode) {
		this.cnlIntfCode = cnlIntfCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getReqInnerNum() {
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum) {
		this.reqInnerNum = reqInnerNum;
	}
	public String getTransNum() {
		return transNum;
	}
	public void setTransNum(String transNum) {
		this.transNum = transNum;
	}
	public String getBankTransNum() {
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum) {
		this.bankTransNum = bankTransNum;
	}
	public String getTransCurrency() {
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}
	public String getViolationId() {
		return violationId;
	}
	public void setViolationId(String violationId) {
		this.violationId = violationId;
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
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getViolationDesc() {
		return violationDesc;
	}
	public void setViolationDesc(String violationDesc) {
		this.violationDesc = violationDesc;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getBnakHandlePriority() {
		return bnakHandlePriority;
	}
	public void setBnakHandlePriority(String bnakHandlePriority) {
		this.bnakHandlePriority = bnakHandlePriority;
	}
	public Date getBankReqTrnasDate() {
		return bankReqTrnasDate;
	}
	public void setBankReqTrnasDate(Date bankReqTrnasDate) {
		this.bankReqTrnasDate = bankReqTrnasDate;
	}
	public Date getBankReqTransTime() {
		return bankReqTransTime;
	}
	public void setBankReqTransTime(Date bankReqTransTime) {
		this.bankReqTransTime = bankReqTransTime;
	}
	public String getBnakServiceFeeAct() {
		return bnakServiceFeeAct;
	}
	public void setBnakServiceFeeAct(String bnakServiceFeeAct) {
		this.bnakServiceFeeAct = bnakServiceFeeAct;
	}
	public Date getBankAccepteTime() {
		return bankAccepteTime;
	}
	public void setBankAccepteTime(Date bankAccepteTime) {
		this.bankAccepteTime = bankAccepteTime;
	}
	public Date getBankReturnTime() {
		return bankReturnTime;
	}
	public void setBankReturnTime(Date bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
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
	public String getBankPmtCnlType() {
		return bankPmtCnlType;
	}
	public void setBankPmtCnlType(String bankPmtCnlType) {
		this.bankPmtCnlType = bankPmtCnlType;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getTermialType() {
		return termialType;
	}
	public void setTermialType(String termialType) {
		this.termialType = termialType;
	}
	public String getIsinGl() {
		return isinGl;
	}
	public void setIsinGl(String isinGl) {
		this.isinGl = isinGl;
	}
	public String getIsPrinted() {
		return isPrinted;
	}
	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}
	public Date getPrintedTime() {
		return printedTime;
	}
	public void setPrintedTime(Date printedTime) {
		this.printedTime = printedTime;
	}
	@Override
	public String toString() {
		return "SysViolationRecordOverSpendDTO [reqNum=" + reqNum + ", cnlIntfCode=" + cnlIntfCode + ", createTime="
				+ createTime + ", cnlCnlCode=" + cnlCnlCode + ", reqInnerNum=" + reqInnerNum + ", transNum=" + transNum
				+ ", bankTransNum=" + bankTransNum + ", transCurrency=" + transCurrency + ", violationId=" + violationId
				+ ", transDc=" + transDc + ", transType=" + transType + ", transStatus=" + transStatus
				+ ", violationDesc=" + violationDesc + ", transTime=" + transTime + ", bnakHandlePriority="
				+ bnakHandlePriority + ", bankReqTrnasDate=" + bankReqTrnasDate + ", bankReqTransTime="
				+ bankReqTransTime + ", bnakServiceFeeAct=" + bnakServiceFeeAct + ", bankAccepteTime=" + bankAccepteTime
				+ ", bankReturnTime=" + bankReturnTime + ", bankDebitName=" + bankDebitName + ", bankDebitCustName="
				+ bankDebitCustName + ", bankDebitCardNum=" + bankDebitCardNum + ", bankCreditName=" + bankCreditName
				+ ", bankCreditCustName=" + bankCreditCustName + ", bankCreditCardNum=" + bankCreditCardNum
				+ ", bankPmtCnlType=" + bankPmtCnlType + ", cnlCustCode=" + cnlCustCode + ", termialType=" + termialType
				+ ", isinGl=" + isinGl + ", transTimeS=" + transTimeS + ", isPrinted=" + isPrinted + ", printedTime="
				+ printedTime + "]";
	}
	
	

	

}
