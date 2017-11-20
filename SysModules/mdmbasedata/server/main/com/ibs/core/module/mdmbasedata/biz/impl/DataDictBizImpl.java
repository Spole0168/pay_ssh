package com.ibs.core.module.mdmbasedata.biz.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.dao.IDataDictDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class DataDictBizImpl extends BaseBiz implements IDataDictBiz {

	private IDataDictDao dataDictDao;
	private IDataDictService dataDictService;
	private IExcelService excelService;

	public IExcelService getExcelService() {
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		this.excelService = excelService;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public String save(DataDict dto) {

		return dataDictDao.save(dto);

	}

	public void saveOrUpdate(DataDict dto) {

		String id = dto.getId();
		if (null != id) {
			DataDict dataDict = dataDictDao.load(id);
			BeanUtils.copyProperties(dto, dataDict);
			dataDictDao.saveOrUpdate(dataDict);

		} else {
			save(dto);
		}

	}

	public IDataDictDao getDataDictDao() {
		return dataDictDao;
	}

	public void setDataDictDao(IDataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}

	public List<OptionObjectPair> listOptions(String type) {
		return dataDictService.listOptions(type);
	}

	public DataDict load(String id) {

		return dataDictService.load(id);
	}

	public IPage<DataDict> findDataDictByPage(QueryPage queryPage) {

		return dataDictDao.findDataDictByPage(queryPage);
	}

	public void deleteDataDict(String[] idStringArray) {
		for (int i = 0; i < idStringArray.length; i++) {
			dataDictDao.delete(idStringArray[i]);
		}

	}

	@SuppressWarnings("unchecked")
	public List<OptionObjectPair> findSupportLangeage(boolean needDefault) {

		List<OptionObjectPair> result = new ArrayList<OptionObjectPair>();
		if (needDefault)
			result.add(OptionObjectPair.getDefaultOptionObjectPair());
		List lanlist = dataDictService.findSupportLangeage();
		if (lanlist != null)
			result.addAll(lanlist);

		return result;

	}

	public int checkValid(String language, String type, String checkParm,
			String checkContent, String id) {
		if (type == null) {
			return 1;
		}
		if ("code".equalsIgnoreCase(checkParm)) {
			String val = dataDictDao.getCodeName(type, checkContent, language);
			if (val != null) {
				return 1;
			} else {
				return 0;
			}

		} else if ("name".equalsIgnoreCase(checkParm)) {
			List<DataDict> alist = dataDictDao.list(type, language);

			if (!StringUtils.isEmpty(id)) {
				// modify
				if (alist != null && alist.size() > 0) {
					for (int i = 0; i < alist.size(); i++) {
						DataDict object = alist.get(i);
						if (object.getName().equalsIgnoreCase(checkContent)
								&& !id.equalsIgnoreCase(object.getId())) {
							return 1;
						}

					}
					return 0;

				} else {
					return 0;
				}

			} else {
				if (alist != null && alist.size() > 0) {
					for (int i = 0; i < alist.size(); i++) {
						DataDict object = alist.get(i);
						if (object.getName().equalsIgnoreCase(checkContent)) {
							return 1;
						}

					}
					return 0;

				} else {
					return 0;
				}

			}

		} else {
			return 1;
		}
	}

	public void exportDataDict(ExportSetting exportSetting,
			Map<String, String> dictTypeMap) {
		logger.trace("enter " + this.getClass().getSimpleName()
				+ " method exportDataDict");
		if (null == dictTypeMap) {
			logger.trace("input dictTypeMap is null !");
			throw new BizException("无法导出当前信息:服务器错误");
		}
		List<DataDict> dataDictList = dataDictDao
				.getDataListByPage(exportSetting);
		if (null != dataDictList && dataDictList.size() > 0) {
			Iterator<DataDict> it = dataDictList.iterator();
			while (it.hasNext()) {
				DataDict o = (DataDict) it.next();
				o.setTypeName(dictTypeMap.get(o.getType()));
			}
		}
		excelService.exportToFile(dataDictList, exportSetting);
	}

	public Map<String, DataDict> listMap(String type) {

		return dataDictService.listMap(type);
	}
	
 
	

}
