package com.ibs.core.module.cnlcust.service;

import java.util.Date;

import com.ibs.core.module.cnlcust.dto.CnlCustDto;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;

public interface ICnlCustService {
	/**
	 * 验证渠道客户编码唯一性
	 * 在同一渠道下，不区分客户状态，根据渠道客户编码只能存在唯一的有效客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return Boolean
	 */
	public Boolean validateCnlCustCode(String cnlCustCode,String cnlCnlCode) throws Exception;
	/**
	 * 验证渠道编码、姓名、证件号码的唯一性
	 * 在同一渠道下，根据姓名和证件号码只能存在唯一的有效并且未注销的客户
	 * @author kaijiezhao
	 * @param cnlCnlCode
	 * @param localName
	 * @param certNum
	 * @return Boolean
	 */
	public Boolean validateCustInCnl(String cnlCnlCode,String localName, String certNum) throws Exception;
	/**
	 * 使用DTO方式新增个人客户
	 * @author kaijiezhao
	 * @param cnlCustPersonalDto
	 * @throws Exception
	 */
	public void addCnlPersonalCust(CnlCustPersonalDto cnlCustPersonalDto) throws Exception;
	/**
	 * 删除渠道客户，将主表中客户状态更改为已注销
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @throws Exception
	 */
	public void deleteCnlCust(String cnlCustCode,String cnlCnlCode) throws Exception;
	/**
	 * 根据渠道客户编码查询客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return CnlCustDto
	 */
	public CnlCustDto getCustByCustCode(String cnlCustCode,String cnlCnlCode) throws Exception;
	/**
	 * 根据渠道客户证件号码查询客户
	 * @author kaijiezhao
	 * @param certNum
	 * @param cnlCnlCode
	 * @return CnlCustDto
	 */
	public CnlCustDto getCustByCertNum(String certNum,String cnlCnlCode) throws Exception;
	/**
	 * 传输多个具体参数来新增企业客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param localName
	 * @param certNum
	 * @param certExpireDate
	 * @param companyTel
	 * @param address
	 * @exception exception
	 * 
	 */
	public void addCnlCompanyCustByParams(String cnlCustCode,String cnlCnlCode,String localName,String certNum,Date certExpireDate,
			String companyTel,String address) throws Exception;
	/**
	 * 根据具体字段参数更新企业客户信息
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param companyTel
	 * @param address
	 * @param certExpireDate
	 * @throws Exception
	 */
	public void updateCnlCompanyCustByParams(String cnlCustCode,String cnlCnlCode,
			String companyTel,String address, Date certExpireDate) throws Exception;
	/**
	 * 根据多个具体参数来新增个人客户
	 * @author kaijiezhao
	 * @exception Exception
	 */
	public void addCnlPersonalCustByParams(String cnlCustCode, String cnlCnlCode,String localName, String certNum, Date certExpireDate,
			String phonenum, String address) throws Exception;
	/**
	 * 根据具体字段参数更新个人客户信息
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param phonenum
	 * @param address
	 * @param certExpireDate
	 * @throws Exception
	 */
	public void updateCnlPersonalCustByParams(String cnlCustCode,String cnlCnlCode,
			String phonenum, String address, Date certExpireDate) throws Exception;
	/**
	 * 更新个人渠道客户
	 * @author kaijiezhao
	 * @param cnlCustPersonalDto
	 * @throws Exception
	 */
	public void updateCnlPersonalCust(CnlCustPersonalDto cnlCustPersonalDto) throws Exception;

}
