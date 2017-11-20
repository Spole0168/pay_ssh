package com.ibs.core.module.cnlmgr.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CnlSysIntfCfg {

	private String id;
	private String cnlCnlCode;
	private String cnlCustCode;
	private String accessCode;
	private String currency;
	private BigDecimal perTransLimit;
	private BigDecimal dayLimit;
	private BigDecimal weekLimit;
	private BigDecimal monthLimit;
	private BigDecimal yearLimit;
	private String lmtOpt; //LMT_OPT
	private Date lmtOptTime; //LMT_OPT_TIME
	private String comments;
	
	public String getCnlCnlCode() {
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode) {
		this.cnlCnlCode = cnlCnlCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnlCustCode() {
		return cnlCustCode;
	}
	public void setCnlCustCode(String cnlCustCode) {
		this.cnlCustCode = cnlCustCode;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public BigDecimal getPerTransLimit() {
		return perTransLimit;
	}
	public void setPerTransLimit(BigDecimal perTransLimit) {
		this.perTransLimit = perTransLimit;
	}
	public BigDecimal getDayLimit() {
		return dayLimit;
	}
	public void setDayLimit(BigDecimal dayLimit) {
		this.dayLimit = dayLimit;
	}
	public BigDecimal getWeekLimit() {
		return weekLimit;
	}
	public void setWeekLimit(BigDecimal weekLimit) {
		this.weekLimit = weekLimit;
	}
	public BigDecimal getMonthLimit() {
		return monthLimit;
	}
	public void setMonthLimit(BigDecimal monthLimit) {
		this.monthLimit = monthLimit;
	}
	public BigDecimal getYearLimit() {
		return yearLimit;
	}
	public void setYearLimit(BigDecimal yearLimit) {
		this.yearLimit = yearLimit;
	}
	
	public String getLmtOpt() {
		return lmtOpt;
	}
	public void setLmtOpt(String lmtOpt) {
		this.lmtOpt = lmtOpt;
	}
	public Date getLmtOptTime() {
		return lmtOptTime;
	}
	public void setLmtOptTime(Date lmtOptTime) {
		this.lmtOptTime = lmtOptTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
