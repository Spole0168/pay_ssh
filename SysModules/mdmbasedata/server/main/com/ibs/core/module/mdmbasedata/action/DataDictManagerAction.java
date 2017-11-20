package com.ibs.core.module.mdmbasedata.action;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.ibs.core.module.mdmbasedata.biz.IDataDictBiz;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.dto.JsonVO;
import com.ibs.core.module.mdmbasedata.exception.SaveFailureException;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.browser.tags.I18nEl;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.cache.CacheManager;
import com.ibs.portal.framework.server.domain.IUser;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

@SuppressWarnings("serial")
public class DataDictManagerAction extends CrudBaseAction {

	private IDataDictBiz dataDictBiz;

	private String id;
	private DataDict dataDict;
	public static java.util.SortedMap<String, String> typeMap = new TreeMap<String, String>();
	public static Map<String, String> displayTypeMap = new HashMap<String, String>();

	static {

		typeMap = DataDictManagerAction.getDictTypeMap();
	}

	// 类型下拉列表
	private List<OptionObjectPair> typeList;
	// 状态列表
	private List<OptionObjectPair> statusList;
	// 组织业务规则大类列表
//	private List<OptionObjectPair> orgBusTypeList;
	
	// 财务可以维护的所有数据类型列表
//	private List<OptionObjectPair> typeListFin;
	/**
	 * 语言列表
	 */
	private List<OptionObjectPair> languageList;

	/**
	 *ajax 检查时，检查的类型
	 */
	private String checkParm;
	/**
	 * ajax 检查时，检查类型的实际内容
	 */
	private String checkContent;

	private String searchFromWhere;

	private static final String fromMDM = "fromMDM";
	private static final String fromFIN = "fromFIN";

	/**
	 * ajax 检查时，字典类型
	 */
	private String type;

	/**
	 * ajax 检查时，字典语言
	 */
	private String language;

	private String exportFileName;

	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	public List<OptionObjectPair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OptionObjectPair> statusList) {
		this.statusList = statusList;
	}

	public List<OptionObjectPair> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<OptionObjectPair> typeList) {
		this.typeList = typeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DataDict getDataDict() {
		if (dataDict == null) {
			dataDict = new DataDict();
		}
		return dataDict;
	}

	public void setDataDict(DataDict dataDict) {
		this.dataDict = dataDict;
	}

	/**
	 * @param needDefault
	 *            是否需要默认值
	 * @param remainOrRemove
	 *            过滤选项 保持查出来的值 true ， 去除查出来的数据 false
	 */
	protected void initialTypeList(boolean needDefault) {
		// 状态

		statusList = new ArrayList<OptionObjectPair>();
		if (needDefault) {
			statusList.add(OptionObjectPair.getDefaultOptionObjectPair());
		}
		statusList.add(new OptionObjectPair(Constants.IS_VALID_VALID,
				Constants.IS_VALID_VALID_VALUE));
		statusList.add(new OptionObjectPair(Constants.IS_VALID_NOT_VALID,
				Constants.IS_VALID_NOT_VALID_VALUE));

		languageList = dataDictBiz.findSupportLangeage(needDefault);

		// 类型

		typeList = new ArrayList<OptionObjectPair>();
		if (needDefault) {
			typeList.add(OptionObjectPair.getDefaultOptionObjectPair());
		}

		Iterator<String> it = typeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = typeMap.get(key);
			typeList.add(new OptionObjectPair(key.substring(key
					.indexOf(Constants.INFOR_SPLIT) + 1), value));
		}

	}

	@Override
	public String list() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: list() ");
//		searchFromWhere = fromMDM;
		initialTypeList(false);
		return SUCCESS;
	}

	public String listFinancial() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: list() ");
		searchFromWhere = fromFIN;
//		initialTypeList(false, true);
		return SUCCESS;
	}

	/**
	 * Action方法，异步查询列表数据
	 * 
	 * @return
	 */

	public String search() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: search() ");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */

		String fromWhere = (String) getSearchFields().get("searchFromWhere");

		String searchType = (String) getSearchFields().get("searchType");
		if (!StringUtils.isEmpty(searchType)) {
			queryPage.addEqualSearch("type", searchType.trim());
		}

//		if (fromFIN.equalsIgnoreCase(fromWhere)) {
//			// 来自于财务
//			Map<String, DataDict> mapfilter = dataDictBiz
//					.listMap(IDataDictService.DATA_DICT_TYPE__FINANCIAL_DICT);
//
//			if (mapfilter != null && mapfilter.keySet() != null
//					&& mapfilter.keySet().toArray().length > 0) {
//				queryPage.addInSearch("type", mapfilter.keySet().toArray());
//			}else
//			{
//				this.setResult(null);
//				return AJAX_RETURN_TYPE;
//			}
//
//		} else {
//			Map<String, DataDict> mapfilter = dataDictBiz
//					.listMap(IDataDictService.DATA_DICT_TYPE__FINANCIAL_DICT);
//
//			if (mapfilter != null && mapfilter.keySet() != null
//					&& mapfilter.keySet().toArray().length > 0) {
//				queryPage.addNotInSearch("type", mapfilter.keySet().toArray());
//			}
//		}

		// String searchStatus = (String) getSearchFields().get("searchStatus");
		// if (!StringUtils.isEmpty(searchStatus)) {
		// queryPage.addEqualSearch("status", searchStatus);
		// }

		queryPage.addEqualSearch("status", Constants.IS_VALID_VALID);
		String searchLanguage = (String) getSearchFields()
				.get("searchLanguage");
		if (!StringUtils.isEmpty(searchLanguage)) {
			// queryPage.addEqualSearch("locale.id", searchLanguage);
			queryPage.addEqualSearch("languageCode", searchLanguage.trim());
			// queryPage.addAliasEqualSearch("local", "lan", "id",
			// searchLanguage);
		}
		String searchCode = (String) getSearchFields().get("searchCode");
		if (!StringUtils.isEmpty(searchCode)) {
			queryPage.addLikeSearch("code", searchCode.trim().toUpperCase());
		}
		String searchName = (String) getSearchFields().get("searchName");
		if (!StringUtils.isEmpty(searchName)) {
			queryPage.addLikeSearch("name", searchName.trim());
		}

		String searchDescription = (String) getSearchFields().get(
				"searchDescription");
		if (!StringUtils.isEmpty(searchDescription)) {
			queryPage.addLikeSearch("remark", searchDescription.trim());
		}
		// 增加排序，以防止翻页时部分数据会重复出现
		// Map<String, String> sortMap = new HashMap<String, String>();
		// sortMap.put("type", "asc");
		// sortMap.put("displayOrder", "asc");
		// queryPage.setSortMap(sortMap);

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<DataDict> result;
		try {
			result = (Page<DataDict>) dataDictBiz.findDataDictByPage(queryPage);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		setResults(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	@Override
	public String delete() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: delete() ");
		/**
		 * 第一步，获取要删除的ID字符串数组,并转换为Integer数组
		 */
		String[] idStringArray = getSelectIds();
		// Long[] idArray = this.toLongArray(idStringArray);

		/**
		 * 第二步，按ID数组删除对象
		 */
		try {
			dataDictBiz.deleteDataDict(idStringArray);
			message = "OK";
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		// 刷新缓存
		if (null != idStringArray && idStringArray.length > 0) {
			for (int i = 0; i < idStringArray.length; i++) {
				DataDict object = dataDictBiz.load(idStringArray[i]);
				String cacheKey = object.getType()
						+ Constants.CACHE_KEY_SEPERATOR
						+ object.getLanguageCode();
				logger.trace("refresh data dict cache " + cacheKey);
				try {
					CacheManager.getInstance().refresh(
							Constants.DATA_DICT_CACHE, cacheKey);
				} catch (Exception e) {
					logger.error("刷新缓存失败 : \n" + e.getMessage());
					e.printStackTrace();
				}

			}
		}

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}

	/**
	 * Action方法，跳转到新增页面
	 * 
	 * @return
	 */
	public String create() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: create() ");
//		if (fromFIN.equalsIgnoreCase(this.getSearchFromWhere())) {
//			initialTypeList(false, true);
//		} else {
			initialTypeList(false);
//		}
//        if(this.getTypeList()!=null&&this.getTypeList().size()>0)
//        {
//        	typeListFin=new ArrayList<OptionObjectPair>();
//        	for(int k=0;k<this.getTypeList().size();k++)
//        	{
//        		OptionObjectPair o = this.getTypeList().get(k);
//       			typeListFin.add(o);
//        	}
//        }
		
//		orgBusTypeList = dataDictBiz
//				.listOptions(IDataDictService.DATA_DICT_TYPE__ORG_BUSINESS_RULE);

		return SUCCESS;
	}

	/**
	 * Action方法，跳转到修改页面
	 * 
	 * @return
	 */
	public String modify() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: modify() ");
		// 如果ID为空，抛异常
		if (null == id) {
			throw new SaveFailureException("无法获取所选的样例条目");
		}
		// initialTypeList(false);
//		if (fromFIN.equalsIgnoreCase(this.getSearchFromWhere())) {
//			initialTypeList(false, true);
//		} else {
			initialTypeList(false);
//		}
		// 从Biz中获取对象
		this.setDataDict(dataDictBiz.load(id));
		String type = this.getDataDict().getType();

		if (displayTypeMap != null && displayTypeMap.get(type) == null) {
			typeList.add(new OptionObjectPair(type, type));
		}
		// typeList.add(new OptionObjectPair(key.substring(key
		// .indexOf(Constants.INFOR_SPLIT) + 1), value));

		// 设置对象属性给Action的各属性
		// this.getDataDict().setName(dataDict.getName());
		// this.description = dataDict.getDescription();
		// this.logDate = dataDict.getLogDate();

		// 跳转
		return SUCCESS;
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: saveOrUpdate() ");
		try {
			IUser su = getCurrentUser();
			DataDict object = this.getDataDict();
			// 如果是新增
			if (this.getIsModify() == false) {
				int result = dataDictBiz.checkValid(object.getLanguageCode(),
						object.getType(), "code", object.getCode(), object
								.getId());
				if (result > 0) {
					throw new SaveFailureException(I18nEl
							.i18n("mdmdatadict.error.hascode"));
				}

				object.setCreateUserCode(su.getUserCode());
//				object.setCreateOrgCode(su.getOrgCode());
				object.setCreateTime(new Date());
				object.setStatus(Constants.IS_VALID_VALID);

				dataDictBiz.save(object);
			}
			// 如果是修改
			else {

				object.setModifyUserCode(su.getUserCode());
				object.setModifyTime(new Date());
//				object.setModifyOrgCode(su.getOrgCode());
				dataDictBiz.saveOrUpdate(object);
			}

			// 刷新缓存
			if (null != object && null != object.getId()
					&& !"".equals(object.getId())) {
				String cacheKey = object.getType()
						+ Constants.CACHE_KEY_SEPERATOR
						+ object.getLanguageCode();
				logger.trace("refresh data dict cache " + cacheKey);

				try {
					CacheManager.getInstance().refresh(
							Constants.DATA_DICT_CACHE, cacheKey);
				} catch (Exception e) {
					logger.error("刷新缓存失败 : \n" + e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (SaveFailureException e) {
			throw e;
		} catch (Exception e) {
			addActionError(e.getLocalizedMessage());
			return ERROR;
		}
		return AJAX_RETURN_TYPE;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: export() ");
		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */

		String searchType = (String) getSearchFields().get("searchType");
		String fromWhere = (String) getSearchFields().get("searchFromWhere");
		if (!StringUtils.isEmpty(searchType)) {
			exportSetting.addEqualSearch("type", searchType);
		}

//		if (fromFIN.equalsIgnoreCase(fromWhere)) {
//			// 来自于财务
//			Map<String, DataDict> mapfilter = dataDictBiz
//					.listMap(IDataDictService.DATA_DICT_TYPE__FINANCIAL_DICT);
//
//			if (mapfilter != null && mapfilter.keySet() != null&&mapfilter.keySet().size()>0) {
//				exportSetting.addInSearch("type", mapfilter.keySet().toArray());
//			}
//
//		} else {
//			Map<String, DataDict> mapfilter = dataDictBiz
//					.listMap(IDataDictService.DATA_DICT_TYPE__FINANCIAL_DICT);
//
//			if (mapfilter != null && mapfilter.keySet() != null&&mapfilter.keySet().size()>0) {
//				exportSetting.addNotInSearch("type", mapfilter.keySet()
//						.toArray());
//			}
//		}

		// String searchStatus = (String) getSearchFields().get("searchStatus");
		// if (!StringUtils.isEmpty(searchStatus)) {
		// queryPage.addEqualSearch("status", searchStatus);
		// }

		exportSetting.addEqualSearch("status", Constants.IS_VALID_VALID);
		String searchLanguage = (String) getSearchFields()
				.get("searchLanguage");
		if (!StringUtils.isEmpty(searchLanguage)) {
			// queryPage.addEqualSearch("locale.id", searchLanguage);
			exportSetting.addEqualSearch("languageCode", searchLanguage);
			// queryPage.addAliasEqualSearch("local", "lan", "id",
			// searchLanguage);
		}
		String searchCode = (String) getSearchFields().get("searchCode");
		if (searchCode != null && !searchCode.equals("")) {
			exportSetting.addLikeSearch("code", searchCode.toUpperCase());
		}
		String searchName = (String) getSearchFields().get("searchName");
		if (searchName != null && !searchName.equals("")) {
			exportSetting.addLikeSearch("name", searchName);
		}

		String searchDescription = (String) getSearchFields().get(
				"searchDescription");
		if (searchDescription != null && !searchDescription.equals("")) {
			exportSetting.addLikeSearch("remark", searchDescription);
		}

		exportSetting.setFileName(exportFileName);

		dataDictBiz.exportDataDict(exportSetting, displayTypeMap);
		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public static SortedMap<String, String> getDictTypeMap() {

		Comp com = new Comp();
		SortedMap<String, String> returnMap = new TreeMap<String, String>(com);
		Field[] Fields = IDataDictService.class.getFields();

		for (int i = 0; i < Fields.length; i++) {
			try {
				String propertyName = Fields[i].getName();
				String propertyValue = (String) Fields[i].get(propertyName);
				if (propertyName.startsWith("DATA_DICT_TYPE_")
						&& !propertyName.endsWith("_VALUE")) {
					String valName = propertyName + "_VALUE";
					String value = (String) IDataDictService.class.getField(
							valName).get(valName);
					String key = getPinYinString(value).toUpperCase();
					returnMap.put(key + Constants.INFOR_SPLIT + propertyValue,
							value);
					displayTypeMap.put(propertyValue, value);
				}
			} catch (Exception e) {
				throw new SaveFailureException(e.getMessage());
			}
		}
		return returnMap;

	}

	/**
	 * 异步验证
	 * 
	 * @return
	 */
	public String validValue() {
		logger.trace("enter" + this.getClass().getSimpleName()
				+ "-->method: validValue() ");

		try {

			JsonVO jsonvo = new JsonVO();
			int result = dataDictBiz.checkValid(this.language, this.type,
					this.checkParm, this.checkContent, this.id);
			jsonvo.setCode(result + "");
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSONObject.fromObject(jsonvo).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}

		return AJAX_RETURN_TYPE;

	}

	private void setResults(Page<DataDict> result) {
		Iterator<DataDict> it = result.getRows().iterator();
		while (it.hasNext()) {
			DataDict o = (DataDict) it.next();
			o.setTypeName(displayTypeMap.get(o.getType()));

		}

		super.setResult(result);
	}

	public IDataDictBiz getDataDictBiz() {
		return dataDictBiz;
	}

	public void setDataDictBiz(IDataDictBiz dataDictBiz) {
		this.dataDictBiz = dataDictBiz;
	}

	public List<OptionObjectPair> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<OptionObjectPair> languageList) {
		this.languageList = languageList;
	}

	@SuppressWarnings("unchecked")
	public static class Comp implements Comparator {

		public int compare(Object o1, Object o2) {
			return o1.toString().compareTo(o2.toString());

		}

	}

	public String getCheckParm() {
		return checkParm;
	}

	public void setCheckParm(String checkParm) {
		this.checkParm = checkParm;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private static String getPinYinString(String src) {
		StringBuilder pinyinBuf = new StringBuilder();

		// 定义转换的格式
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);// u显示

		try {
			for (int i = 0; i < src.length(); i++) {
				String[] pinYins = PinyinHelper.toHanyuPinyinStringArray(src
						.charAt(i), outputFormat);
				if (pinYins != null && pinYins.length > 0) {// 汉语
					pinyinBuf.append(pinYins[0]);
				} else {// 非汉语
					pinyinBuf.append(src.charAt(i));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}

		return pinyinBuf.toString();

	}

	public String getSearchFromWhere() {
		return searchFromWhere;
	}

	public void setSearchFromWhere(String searchFromWhere) {
		this.searchFromWhere = searchFromWhere;
	}
//
//	public List<OptionObjectPair> getOrgBusTypeList() {
//		return orgBusTypeList;
//	}
//
//	public void setOrgBusTypeList(List<OptionObjectPair> orgBusTypeList) {
//		this.orgBusTypeList = orgBusTypeList;
//	}
//
//	public List<OptionObjectPair> getTypeListFin() {
//		return typeListFin;
//	}
//
//	public void setTypeListFin(List<OptionObjectPair> typeListFin) {
//		this.typeListFin = typeListFin;
//	}

}
