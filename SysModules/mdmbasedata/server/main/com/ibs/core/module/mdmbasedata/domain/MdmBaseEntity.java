package com.ibs.core.module.mdmbasedata.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;
import com.ibs.portal.framework.server.domain.ISynchronize;

/**
 * 基础数据实体基本类
 * 
 * @author zdp
 * 
 * 
 */
public class MdmBaseEntity extends BaseEntity implements ISynchronize  {
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306717686047939820L;
	// private String id;// 数据ID
	/**
	 * 数据编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 代码和名字
	 */
	private String codeAndName;
	/**
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 数据版本
	 */
	private Long versionNo;

	/**
	 * 数据状态
	 */
	private String status;
	
	private String statusName;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 创建人
	 */
	private String createUserCode;
	/**
	 * 修改人
	 */
	private String modifyUserCode;
	/**
	 * 创建网点
	 */
	private String createOrgCode;
	/**
	 * 修改网点
	 */
	private String modifyOrgCode;

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getModifyUserCode() {
		return modifyUserCode;
	}

	public void setModifyUserCode(String modifyUserCode) {
		this.modifyUserCode = modifyUserCode;
	}

	public String getCreateOrgCode() {
		return createOrgCode;
	}

	public void setCreateOrgCode(String createOrgCode) {
		this.createOrgCode = createOrgCode;
	}

	public String getModifyOrgCode() {
		return modifyOrgCode;
	}

	public void setModifyOrgCode(String modifyOrgCode) {
		this.modifyOrgCode = modifyOrgCode;
	}

 

	// private String dealHost;// 处理主机
	// private String dealStatus;// 处理状态
	// private int dealCount; // 处理次数
	// private Date dealTime;// 处理时间

	public Date getModifyTime() {
		return modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 */
	public String getCode() {
		if(code!=null)
		{
		 code=code.toUpperCase().trim();
			
		}
		return code;
	}

	public void setCode(String codeparm) {
		if(codeparm!=null)
		{
			this.code=codeparm.toUpperCase().trim();
		}else
		{
			this.code = codeparm;
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCodeAndName() {
		String tempCode="";
		if(code!=null)
		{
			tempCode=code;
		}
		String tempName="";
		if(name!=null)
		{
			tempName=name;
		}
		if(tempCode.length()<12)
		{
			 codeAndName =tempCode+" " + tempName;
		}else
		{
			 codeAndName =tempCode + tempName;
		}
	 
		return codeAndName;
	}

	public void setCodeAndName(String codeAndName) {
		this.codeAndName = codeAndName;
	}

}
