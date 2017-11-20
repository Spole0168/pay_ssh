package com.ibs.core.module.mdmintbasedata.domain;

import com.ibs.core.module.mdmbasedata.domain.MdmBaseEntity;

/**
 * T_MDM_INTERNATIONAL_FORWARDER 国际件货代
 * 
 * @author Adair
 * 
 */

@SuppressWarnings("serial")
public class Forwarder extends MdmBaseEntity {
	/*
	 * 联系人
	 */
	private String contactPerson;
	/*
	 * 联系方式
	 */
	private String contactType;
	/*
	 * 是否启用
	 */
	private char used;

	/*
	 * 所属国际部ID
	 */
	private String orgId;
	/*
	 * 所属组织Code
	 */
	private String orgCode;
	/*
	 * 所属组织Name
	 */
	private String orgName;
	/*
	 * 创建组织Name
	 */
	private String createOrgName;
	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public char getUsed() {
		return used;
	}

	public void setUsed(char used) {
		this.used = used;
	}

	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}

	public String getCreateOrgName() {
		return createOrgName;
	}
}
