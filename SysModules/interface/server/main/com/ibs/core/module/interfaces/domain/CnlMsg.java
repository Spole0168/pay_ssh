/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlMsg extends BaseEntity {
	
	private String id; // ID
	private String msgCode; // MSG_CODE
	private String msgFileLocation; // MSG_FILE_LOCATION
	private Date recieveTime; // RECIEVE_TIME
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlIntfCode; // CNL_INTF_CODE
	private String isValid; // IS_VALID
	private String direction; // DIRECTION
	private String creator; // CREATOR
	private String updator; // UPDATOR
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getMsgCode(){
		return msgCode;
	}
	public void setMsgCode(String msgCode){
		this.msgCode = msgCode;
	}

	public String getMsgFileLocation(){
		return msgFileLocation;
	}
	public void setMsgFileLocation(String msgFileLocation){
		this.msgFileLocation = msgFileLocation;
	}

	public Date getRecieveTime(){
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime){
		this.recieveTime = recieveTime;
	}

	public String getCnlCnlCode(){
		return cnlCnlCode;
	}
	public void setCnlCnlCode(String cnlCnlCode){
		this.cnlCnlCode = cnlCnlCode;
	}

	public String getCnlIntfCode(){
		return cnlIntfCode;
	}
	public void setCnlIntfCode(String cnlIntfCode){
		this.cnlIntfCode = cnlIntfCode;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction){
		this.direction = direction;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	
}
