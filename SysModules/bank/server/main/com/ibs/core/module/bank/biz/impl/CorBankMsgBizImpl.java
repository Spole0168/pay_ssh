/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.bank.biz.impl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.bank.biz.ICorBankMsgBiz;
import com.ibs.core.module.bank.dao.ICorBankMsgDao;
import com.ibs.core.module.bank.domain.CorBankMsg;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_BANK_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorBankMsgBizImpl extends BaseBiz implements ICorBankMsgBiz {

	private ICorBankMsgDao corBankMsgDao;
	private IExcelService excelService;
	private static final String BANK_MSG_DIR = "bankMessage";
	private String corBankLogDir;

	@Override
	public IPage<CorBankMsg> findCorBankMsgByPage(QueryPage queryPage) {
		logger.info("entering action::CorBankMsgBizImpl.findCorBankMsgByPage()...");
		return corBankMsgDao.findCorBankMsgByPage(queryPage);
	}

	@Override
	public CorBankMsg getCorBankMsgById(String id) {
		logger.info("entering action::CorBankMsgBizImpl.getCorBankMsgById()...");
		return corBankMsgDao.load(id);
	}

	@Override
	public CorBankMsg saveCorBankMsg(CorBankMsg corBankMsg) {
		logger.info("entering action::CorBankMsgBizImpl.saveCorBankMsg()...");
		corBankMsg.setId(null);
		corBankMsgDao.saveOrUpdate(corBankMsg);
		return corBankMsg;
	}

	@Override
	public CorBankMsg updateCorBankMsg(CorBankMsg corBankMsg) {
		logger.info("entering action::CorBankMsgBizImpl.updateCorBankMsg()...");
		corBankMsgDao.saveOrUpdate(corBankMsg);
		return corBankMsg;
	}

	@Override
	public void exportCorBankMsg(ExportSetting exportSetting) {
		logger.info("entering action::CorBankMsgBizImpl.exportCorBankMsg()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorBankMsg> corBankMsgPage = (Page<CorBankMsg>) corBankMsgDao.findCorBankMsgByPage(exportSetting);

		excelService.exportToFile(corBankMsgPage.getRows(), exportSetting);
	}

	public ICorBankMsgDao getCorBankMsgDao() {
		logger.info("entering action::CorBankMsgBizImpl.getCorBankMsgDao()...");
		return corBankMsgDao;
	}

	public void setCorBankMsgDao(ICorBankMsgDao corBankMsgDao) {
		logger.info("entering action::CorBankMsgBizImpl.setCorBankMsgDao()...");
		this.corBankMsgDao = corBankMsgDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorBankMsgBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorBankMsgBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	/**
	 * 
	 */
	@Override
//	public String findByMsgCode(String msgCode, String bankPmtCnlCode) {
	public String findByMsgCode(String msgCode) {
		String text = "";
		logger.info("entering action::CorBankMsgBizImpl.findByMsgCode()...");
		// 通过msgcode查询出报文表数据
//		CorBankMsg corBankMsg = corBankMsgDao.findByMsgCodeCnlCnlCode(msgCode, bankPmtCnlCode);
		CorBankMsg corBankMsg = corBankMsgDao.findByMsgCodeCnlCnlCode(msgCode);
		
		if (null != corBankMsg) {
			// 获取路径
			String url = corBankMsg.getMsgLocation();
			// 获取文件数据
			String rootPath = corBankLogDir +  File.separator;
			String filePath = rootPath + url;

			logger.info("read file...filePath::"+filePath);
			
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				try {
					logger.debug("read file...");
					InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");

					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						text = text + lineTxt;
					}
					logger.debug("read exit...");
					read.close();
				} catch (Exception e) {
					logger.debug("read failed..."+e);
					e.printStackTrace();
				}
			}
		}else{
			logger.debug("msgCode not exit in CorBankMsg...");
			return null;
		}
		logger.info("exit biz...");
		return text;
	}

	public String getCorBankLogDir() {
		return corBankLogDir;
	}

	public void setCorBankLogDir(String corBankLogDir) {
		this.corBankLogDir = corBankLogDir;
	}

}
