/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.interfaces.biz.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.interfaces.biz.ICnlMsgBiz;
import com.ibs.core.module.interfaces.dao.ICnlMsgDao;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_MSG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CnlMsgBizImpl extends BaseBiz implements ICnlMsgBiz {

	private ICnlMsgDao cnlMsgDao;
	private IExcelService excelService;
	private static final String CHANEL_MSG_DIR = "chanelmessage";
	private String cnlSvcLogDir;

	public String getCnlSvcLogDir() {
		return cnlSvcLogDir;
	}

	public void setCnlSvcLogDir(String cnlSvcLogDir) {
		this.cnlSvcLogDir = cnlSvcLogDir;
	}

	@Override
	public IPage<CnlMsg> findCnlMsgByPage(QueryPage queryPage) {
		logger.info("entering action::CnlMsgBizImpl.findCnlMsgByPage()...");
		return cnlMsgDao.findCnlMsgByPage(queryPage);
	}

	@Override
	public CnlMsg getCnlMsgById(String id) {
		logger.info("entering action::CnlMsgBizImpl.getCnlMsgById()...");
		return cnlMsgDao.load(id);
	}

	@Override
	public CnlMsg saveCnlMsg(CnlMsg cnlMsg) {
		logger.info("entering action::CnlMsgBizImpl.saveCnlMsg()...");
		cnlMsg.setId(null);
		cnlMsgDao.saveOrUpdate(cnlMsg);
		return cnlMsg;
	}

	@Override
	public CnlMsg updateCnlMsg(CnlMsg cnlMsg) {
		logger.info("entering BIZ::CnlMsgBizImpl.updateCnlMsg()...");
		cnlMsgDao.saveOrUpdate(cnlMsg);
		return cnlMsg;
	}

	@Override
	public String findByMsgCode(String msgCode, String cnlCnlCode) {
		// TODO Auto-generated method stub
		String text = "";
		logger.info("entering biz::CnlMsgBizImpl.findByMsgCode()...");
		// 通过msgcode查询出报文表数据
		CnlMsg cnlMsg = cnlMsgDao.findByMsgCodeCnlCnlCode(msgCode, cnlCnlCode);
		if (null != cnlMsg) {
			logger.debug("start read file...");
			// 获取路径
			String url = cnlMsg.getMsgFileLocation();
			// 获取文件数据
			String rootPath = cnlSvcLogDir 
					+ File.separator;
			String filePath = rootPath + url;
			logger.debug("...filePath:"+filePath);
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
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		logger.info("exit biz...");
		return text;
	}

	@Override
	public void exportCnlMsg(ExportSetting exportSetting) {
		logger.info("entering action::CnlMsgBizImpl.exportCnlMsg()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CnlMsg> cnlMsgPage = (Page<CnlMsg>) cnlMsgDao.findCnlMsgByPage(exportSetting);

		excelService.exportToFile(cnlMsgPage.getRows(), exportSetting);
	}

	public ICnlMsgDao getCnlMsgDao() {
		logger.info("entering action::CnlMsgBizImpl.getCnlMsgDao()...");
		return cnlMsgDao;
	}

	public void setCnlMsgDao(ICnlMsgDao cnlMsgDao) {
		logger.info("entering action::CnlMsgBizImpl.setCnlMsgDao()...");
		this.cnlMsgDao = cnlMsgDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlMsgBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlMsgBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}
