/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.refund.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ibs.common.module.frameworkimpl.config.service.IPropertiesService;
import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.exception.FileServiceException;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.core.module.refund.biz.IRefundTransRefundBiz;
import com.ibs.core.module.refund.domain.RefundTransRefund;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class RefundTransRefundManagerAction extends CrudBaseAction {

	private static final int BUFFER_SIZE = 16 * 1024;
	private IRefundTransRefundBiz refundTransRefundBiz;
	private String id;
	private RefundTransRefund cnlTransRefund;
	
	//凭证上传
	private IFilePersistenceService filePersistenceService;
	private IDataDictService dataDictService;
	// 退款状态
	private List<OptionObjectPair> refundStatusList;
	private String refundStatusRender;
	
	private String startTime;
	private String endTime;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// 页面初期化设置
	protected void initialPage() {		
		
		logger.info("entering action::RefundTransRefundManagerAction.initialTypeList()...");
		
		// 开始时间和结束时间文本框的默认值
		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = format.format(now) + " " + "00:00:00";
		String endTime = format.format(now) + " " + "23:59:59";
		this.setStartTime(startTime); 
		this.setEndTime(endTime);
	}

	/**
	 * Action方法，显示列表页面
	 * 
	 * @return
	 */
	@Override
	public String list() {
		logger.info("entering action::RefundTransRefundManagerAction.list()...");
		
		// 页面初期化
		initialPage();

		// 退款状态下拉框的值
		refundStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__REFUND_STATUS);
		refundStatusRender = SelectRenderUtils.toRenderString(refundStatusList);

		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.info("entering action::RefundTransRefundManagerAction.create()...");
		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到详情页面
	 * 
	 * @return
	 */
	public String detail() {
		logger.info("entering action::RefundTransRefundManagerAction.detail()...");
		
		cnlTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);
		
		// 退款状态下拉框的值
		refundStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__REFUND_STATUS);
		// 各render生成
		refundStatusRender = SelectRenderUtils.toRenderString(refundStatusList);

		return SUCCESS;
	}
	
	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.info("entering action::RefundTransRefundManagerAction.modify()...");
		cnlTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);
		initialPage();
		return SUCCESS;
	}
	
	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */
	public String search() {
		logger.info("entering action::RefundTransRefundManagerAction.search()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<RefundTransRefund> result = (Page<RefundTransRefund>) refundTransRefundBiz
				.findRefundTransRefundByPage(queryPage);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.info("entering action::RefundTransRefundManagerAction.saveOrUpdate()...");
		if(null!=file&&file.length()>0){
			//上传文件：修改为相对路径   shangzhuzi 2016年8月12日16:07:01  
			cnlTransRefund.setVoucherLocation(this.upLoadImage(file));
		}
		boolean f = getIsModify();
		logger.debug("getIsModify=" + f);
		// 如果是新增
		if (this.getIsModify() == false) {
			refundTransRefundBiz.saveRefundTransRefund(cnlTransRefund);
		}
		// 如果是修改
		else {
			refundTransRefundBiz.updateRefundTransRefund(cnlTransRefund);
		}

		return SUCCESS;
	}
	
	/**
	 * Action方法，批量删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		logger.info("entering action::RefundTransRefundManagerAction.delete()...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.info("entering action::RefundTransRefundManagerAction.export()...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("退款管理导出文件.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		refundTransRefundBiz.exportRefundTransRefund(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	public void setQueryCondition() {
		logger.info("entering action::RefundTransRefundManagerAction.setQueryCondition()...");
		// ID
		String id = getSearchFields().get("id");
		if (StringUtils.isNotEmpty(id)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("id", "%" + id + "%");
				queryPage.addLikeSearch("id", id);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("id", "%" + id + "%");
				exportSetting.addLikeSearch("id", id);
			}
		}

		// REFUND_CODE
		String refundCode = getSearchFields().get("refundCode");
		if (StringUtils.isNotEmpty(refundCode)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("refundCode", "%" + refundCode + "%");
				queryPage.addLikeSearch("refundCode", refundCode);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("refundCode", "%" + refundCode + "%");
				exportSetting.addLikeSearch("refundCode", refundCode);
			}
		}
		
		// 退款开始时间
		if(StringUtils.isNotEmpty(startTime)) {
			if(null != queryPage) {
				queryPage.addQueryCondition("refundTime", Timestamp.valueOf(startTime));
				queryPage.addGreatEqualSearch("refundTime", Timestamp.valueOf(startTime));
			}
			if(null != exportSetting) {
				exportSetting.addQueryCondition("refundTime", Timestamp.valueOf(startTime));
				exportSetting.addGreatEqualSearch("refundTime", Timestamp.valueOf(startTime));
			}
		}
		
		// 退款结束时间
		String endTime = getSearchFields().get("endTime");
		if(StringUtils.isNotEmpty(endTime)) {
			if(null != queryPage) {
				queryPage.addQueryCondition("refundTime", Timestamp.valueOf(endTime));
				queryPage.addLessEqualSearch("refundTime", Timestamp.valueOf(endTime));
			}
			if(null != exportSetting) {
				exportSetting.addQueryCondition("refundTime", Timestamp.valueOf(endTime));
				exportSetting.addLessEqualSearch("refundTime", Timestamp.valueOf(endTime));
			}
		}

		// REFUND_STATUS
		String refundStatus = getSearchFields().get("refundStatus");
		if (StringUtils.isNotEmpty(refundStatus)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("refundStatus", "%" + refundStatus + "%");
				queryPage.addLikeSearch("refundStatus", refundStatus);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("refundStatus", "%" + refundStatus + "%");
				exportSetting.addLikeSearch("refundStatus", refundStatus);
			}
		}
	}

	public IRefundTransRefundBiz getRefundTransRefundBiz() {
		logger.info("entering action::RefundTransRefundManagerAction.getRefundTransRefundBiz()...");
		return refundTransRefundBiz;
	}

	public void setRefundTransRefundBiz(IRefundTransRefundBiz refundTransRefundBiz) {
		logger.info("entering action::RefundTransRefundManagerAction.setRefundTransRefundBiz()...");
		this.refundTransRefundBiz = refundTransRefundBiz;
	}

	public RefundTransRefund getCnlTransRefund() {
		logger.info("entering action::RefundTransRefundManagerAction.getCnlTransRefund()...");
		return cnlTransRefund;
	}

	public void setCnlTransRefund(RefundTransRefund cnlTransRefund) {
		logger.info("entering action::RefundTransRefundManagerAction.setCnlTransRefund()...");
		this.cnlTransRefund = cnlTransRefund;
	}

	public String getId() {
		logger.info("entering action::RefundTransRefundManagerAction.getId()...");
		return id;
	}

	public void setId(String id) {
		logger.info("entering action::RefundTransRefundManagerAction.setId()...");
		this.id = id;
	}
	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public String getStartTime() {
		logger.info("entering action::RefundTransRefundManagerAction.getStartTime()...");
		return startTime;
	}
	public void setStartTime(String startTime) {
		logger.info("entering action::RefundTransRefundManagerAction.setStartTime()...");
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		logger.info("entering action::RefundTransRefundManagerAction.getEndTime()...");
		return endTime;
	}

	public void setEndTime(String endTime) {
		logger.info("entering action::RefundTransRefundManagerAction.setEndTime()...");
		this.endTime = endTime;
	}
	
	public List<OptionObjectPair> getRefundStatusList() {
		return refundStatusList;
	}

	public void setRefundStatusList(List<OptionObjectPair> refundStatusList) {
		this.refundStatusList = refundStatusList;
	}
	
	public String getRefundStatusRender() {
		return refundStatusRender;
	}

	public void setRefundStatusRender(String refundStatusRender) {
		this.refundStatusRender = refundStatusRender;
	}
	private Date dealString2Date(String fieldName){
		Date dt = null;
		if(null!=fieldName&&fieldName.length()>0){
			fieldName = getSearchFields().get(fieldName);
			if(StringUtils.isNotEmpty(fieldName)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					dt = sdf.parse(fieldName);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return dt;
	}
	
	private String reviewMsg;
	private String refundFailReason;
	private String refundStatus;
	private IPropertiesService propertiesService;
	private String fileId;
	// 文件标题  
	private String title;  
	// 上传文件域对象  
	private File file;  
	// 上传文件名  
	private String fileFileName;  
	// 上传文件类型  
	private String fileContentType; 
	
	public String getReviewMsg() {
		return reviewMsg;
	}

	public void setReviewMsg(String reviewMsg) {
		this.reviewMsg = reviewMsg;
	}

	public String getRefundFailReason() {
		return refundFailReason;
	}

	public void setRefundFailReason(String refundFailReason) {
		this.refundFailReason = refundFailReason;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public IPropertiesService getPropertiesService() {
		return propertiesService;
	}

	public void setPropertiesService(IPropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}
	
	public IFilePersistenceService getFilePersistenceService() {
		return filePersistenceService;
	}

	public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
		this.filePersistenceService = filePersistenceService;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * Action方法，跳转到审核页面
	 * 
	 * @return
	 */
	public String verify() {
		logger.info("entering action::CnlTransRefundManagerAction.modify()...");
		cnlTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);
		refundStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__REFUND_STATUS);
		return SUCCESS;
	}
	/**
	 * Action方法，跳转到结果更新页面
	 * 
	 * @return
	 */
	public String updateResult() {
		logger.info("entering action::CnlTransRefundManagerAction.modify()...");
		cnlTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);
		// 退款状态下拉框的值
		refundStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__REFUND_STATUS);
		int size = refundStatusList.size();
		for(int i = size -1; i >= 0 ;i--) {
			OptionObjectPair oop = refundStatusList.get(i);
			if(!(oop.getKey().equals(Constants.REFUND_STATUS_NOT_REFUND) 	|| 
				 oop.getKey().equals(Constants.REFUND_STATUS_REFUNDING) 		||
				 oop.getKey().equals(Constants.REFUND_STATUS_REFUND_SUCCESS) ||
				 oop.getKey().equals(Constants.REFUND_STATUS_REFUND_FAIL))
					) {
				refundStatusList.remove(i);
			}
		}
		refundStatusRender = SelectRenderUtils.toRenderString(refundStatusList);
		return SUCCESS;
	}
	
	/**
	 * @author xiehaiping
	 * 审核页面 审核成功 根据id 修改状态为审核成功
	 */
	public String verifyStatusSuccess(){
	   RefundTransRefund refundTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);	
	   if(refundTransRefund!=null){
		   refundTransRefund.setRefundStatus(Constants.REFUND_STATUS_SUCCESS);
		   refundTransRefund.setReviewResult(Constants.REFUND_STATUS_SUCCESS_VALUE);
		   IUser iUser = this.getCurrentUser();
		   refundTransRefund.setReviewer(iUser.getUserName());
		   refundTransRefund.setReviewTime(new Date());
		   refundTransRefundBiz.verifyTransRefund(refundTransRefund);
	   }
	   return SUCCESS;
	   
	}
	/**
	 * @author xiehaiping
	 * 审核页面 审核失败 根据id 修改状态为审核失败
	 */
	public String verifyStatusFail(){
	   RefundTransRefund refundTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);	
	   if(refundTransRefund!=null){
		   refundTransRefund.setRefundStatus(Constants.REFUND_STATUS_FAIL);
		   refundTransRefund.setReviewResult(Constants.REFUND_STATUS_FAIL_VALUE);
		   IUser iUser = this.getCurrentUser();
		   refundTransRefund.setReviewer(iUser.getUserName());
		   refundTransRefund.setReviewTime(new Date());
		   
		   if(StringUtils.isNotEmpty(reviewMsg)){
			   refundTransRefund.setReviewMsg(reviewMsg);
		   }
		   refundTransRefundBiz.verifyTransRefund(refundTransRefund);
	   }
       return SUCCESS;
	 }
	
	//自己封装的一个把源文件对象复制成目标文件对象  
	private static void copy(File src, File dst) {  
		InputStream in = null;  
		OutputStream out = null;  
		try {  
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
			out = new BufferedOutputStream(new FileOutputStream(dst),  
					BUFFER_SIZE);  
			byte[] buffer = new byte[BUFFER_SIZE];  
			int len = 0;  
			while ((len = in.read(buffer)) > 0) {  
				out.write(buffer, 0, len);  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			if (null != in) {  
				try {  
					in.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
			if (null != out) {  
				try {  
					out.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}
		
	//退款申请结果更新
	public String updateAndUpload(){
		
		//根据服务器的文件保存地址和原文件名创建目录文件全路径  
//		String dstPath = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String certificate_path = propertiesService.getPropertyValue("certificate_path");
//		
//		String voucherNum = null;
//		String voucherLocation = null;
//		try {
//			voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
//			voucherLocation = "/downloadFile.action?"+voucherNum;
//		} catch (FileServiceException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		String startTime = sdf.format(new Date());
//		if(StringUtils.isNotEmpty(fileFileName)){
//			
//			String uploadFileFileName = startTime+"_"+StringUtils.getUUID() +"_"+ fileFileName;
//	        dstPath = certificate_path +startTime+"_"+StringUtils.getUUID() +"_"+ fileFileName;  
//	        File dstFile = new File(dstPath);  
	        //判断目标文件所在的目录是否存在  
//	        if(!dstFile.getParentFile().exists()) {  
//	            if(!dstFile.getParentFile().mkdirs()) {  
//	                System.out.println("创建目标文件所在目录失败！");  
//	            }  
//	        }
//	        copy(this.file, dstFile);
//			String path = request.getContextPath();
//			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//			dstPath = basePath+certificate_path+uploadFileFileName;
//			
//	        String realPath = ServletActionContext.getServletContext().getRealPath(certificate_path);
//			//判断文件是否为空,并且文件不能大于2M  
//	        if(file != null && file.length() < 2097152)  
//	        {    
//	            //根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。  
//	            File filePath = new File(new File(realPath), uploadFileFileName);    
//	            //判断路径是否存在    
//	            if(!filePath.getParentFile().exists())  
//	            {  
//	                //如果不存在，则递归创建此路径   
//	                filePath.getParentFile().mkdirs();  
//	            }  
//	            //将文件保存到硬盘上,Struts2会帮我们自动删除临时文件  
//	            try {  
//	                FileUtils.copyFile(file, filePath);  
//	            } catch (IOException e) {  
//	                System.out.println("图片上传失败");   
//	                e.printStackTrace();  
//	            }   
//	        }   
//		}
		String voucherNum = null;
		String voucherLocation = null;
//		if(null!=file&&file.length()>0){
//			String path = request.getContextPath();
//			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//			try {
//				voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
//				voucherLocation = basePath+"downloadFile.action?fileId="+voucherNum;
//			} catch (FileServiceException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		}
		if(null!=file&&file.length()>0){
			//上传文件：修改为相对路径   shangzhuzi 2016年8月12日16:07:01  
			voucherLocation = this.upLoadImage(file);
		}
        
		RefundTransRefund refundTransRefund = refundTransRefundBiz.getRefundTransRefundById(id);	
		if(refundTransRefund!=null){
			IUser iUser = this.getCurrentUser();
			refundTransRefund.setUpdator(iUser.getUserName());
			refundTransRefund.setUpdateTime(new Date());
			refundTransRefund.setRefundVoucherLocation(voucherLocation);
			refundTransRefund.setRefundVoucherNum(cnlTransRefund.getRefundVoucherNum());
			if(StringUtils.isNotEmpty(refundStatus)){
				refundTransRefund.setRefundStatus(refundStatus);
			}
			if(StringUtils.isNotEmpty(refundFailReason)){
				refundTransRefund.setRefundFailReason(refundFailReason);
			}
			refundTransRefundBiz.verifyTransRefund(refundTransRefund);
		}
		return SUCCESS;
	}
	/**
	 * shangzhuzi 
	 * 上传文件：
	 * 		使用相对路径
	 * @param file
	 * @return
	 * 时间：2016年8月12日16:11:50
	 */
	private String upLoadImage(File file){
		String imgURL = null;
		if(null!=file&&file.length()>0){
			String path = request.getContextPath();
			try {
				String imgId = (filePersistenceService.saveFile(file, fileFileName, fileContentType));
				imgURL =  path + "/downloadFile.action?fileId="+imgId;
			} catch (FileServiceException e1) {
				logger.info("文件上传失败"+e1.getLocalizedMessage());
				e1.printStackTrace();
			}
			if(null==imgURL||imgURL.length()<=0){
				logger.info("文件上传失败");
			}
		}
		return imgURL;
	}
}
