package com.ibs.core.module.account.domain;

import java.math.BigDecimal;

public class SumAmountDto {

	private BigDecimal dayAmount;
	private BigDecimal weekAmount;
	private BigDecimal monthAmount;
	private BigDecimal yearAmount;
	public BigDecimal getDayAmount() {
		return dayAmount;
	}
	public void setDayAmount(BigDecimal dayAmount) {
		this.dayAmount = dayAmount;
	}
	public BigDecimal getWeekAmount() {
		return weekAmount;
	}
	public void setWeekAmount(BigDecimal weekAmount) {
		this.weekAmount = weekAmount;
	}
	public BigDecimal getMonthAmount() {
		return monthAmount;
	}
	public void setMonthAmount(BigDecimal monthAmount) {
		this.monthAmount = monthAmount;
	}
	public BigDecimal getYearAmount() {
		return yearAmount;
	}
	public void setYearAmount(BigDecimal yearAmount) {
		this.yearAmount = yearAmount;
	}
	public SumAmountDto(BigDecimal dayAmount, BigDecimal weekAmount, BigDecimal monthAmount, BigDecimal yearAmount) {
		super();
		this.dayAmount = dayAmount;
		this.weekAmount = weekAmount;
		this.monthAmount = monthAmount;
		this.yearAmount = yearAmount;
	}
	
}
