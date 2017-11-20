/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.cnltrans.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_REQ_TRANS_HIS
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlReqTransHis extends BaseEntity {
	
	private String id; // ID
	private String reqInnerNum; // REQ_INNER_NUM
	private String cnlCnlCode; // CNL_CNL_CODE
	private String cnlIntfCode; // CNL_INTF_CODE
	private String reqNum; // REQ_NUM
	private Long reqBatch; // REQ_BATCH
	private String stlNum; // STL_NUM
	private Long reqTotalBatch; // REQ_TOTAL_BATCH
	private Long reqCnt; // REQ_CNT
	private Long reqTotalCnt; // REQ_TOTAL_CNT
	private String reqType; // REQ_TYPE
	private String reqStatus; // REQ_STATUS
	private String timeZone; // TIME_ZONE
	private Date msgTime; // MSG_TIME
	private Date recieveTime; // RECIEVE_TIME
	private Date handleTime; // HANDLE_TIME
	private String msgCode; // MSG_CODE
	private String errCode; // ERR_CODE
	private String errMsg; // ERR_MSG
	private String isValid; // IS_VALID
	private Date createTime; // CREATE_TIME
	private Date updateTime; // UPDATE_TIME
	private String creator; // CREATOR
	private String updator; // UPDATOR
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getReqInnerNum(){
		return reqInnerNum;
	}
	public void setReqInnerNum(String reqInnerNum){
		this.reqInnerNum = reqInnerNum;
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

	public String getReqNum(){
		return reqNum;
	}
	public void setReqNum(String reqNum){
		this.reqNum = reqNum;
	}

	public Long getReqBatch(){
		return reqBatch;
	}
	public void setReqBatch(Long reqBatch){
		this.reqBatch = reqBatch;
	}

	public String getStlNum(){
		return stlNum;
	}
	public void setStlNum(String stlNum){
		this.stlNum = stlNum;
	}

	public Long getReqTotalBatch(){
		return reqTotalBatch;
	}
	public void setReqTotalBatch(Long reqTotalBatch){
		this.reqTotalBatch = reqTotalBatch;
	}

	public Long getReqCnt(){
		return reqCnt;
	}
	public void setReqCnt(Long reqCnt){
		this.reqCnt = reqCnt;
	}

	public Long getReqTotalCnt(){
		return reqTotalCnt;
	}
	public void setReqTotalCnt(Long reqTotalCnt){
		this.reqTotalCnt = reqTotalCnt;
	}

	public String getReqType(){
		return reqType;
	}
	public void setReqType(String reqType){
		this.reqType = reqType;
	}

	public String getReqStatus(){
		return reqStatus;
	}
	public void setReqStatus(String reqStatus){
		this.reqStatus = reqStatus;
	}

	public String getTimeZone(){
		return timeZone;
	}
	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}

	public Date getMsgTime(){
		return msgTime;
	}
	public void setMsgTime(Date msgTime){
		this.msgTime = msgTime;
	}

	public Date getRecieveTime(){
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime){
		this.recieveTime = recieveTime;
	}

	public Date getHandleTime(){
		return handleTime;
	}
	public void setHandleTime(Date handleTime){
		this.handleTime = handleTime;
	}

	public String getMsgCode(){
		return msgCode;
	}
	public void setMsgCode(String msgCode){
		this.msgCode = msgCode;
	}

	public String getErrCode(){
		return errCode;
	}
	public void setErrCode(String errCode){
		this.errCode = errCode;
	}

	public String getErrMsg(){
		return errMsg;
	}
	public void setErrMsg(String errMsg){
		this.errMsg = errMsg;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
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

	
}
