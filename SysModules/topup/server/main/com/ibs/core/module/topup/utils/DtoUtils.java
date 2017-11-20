package com.ibs.core.module.topup.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.CollectionUtils;
import com.ibs.portal.framework.util.DateUtils;

public class DtoUtils extends org.apache.commons.beanutils.BeanUtils{
	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	private DtoUtils() {
	}

	public static void copyBasicTypeProperties(Object toObject,
			Object fromobject) throws InvocationTargetException {
		if (toObject == null || fromobject == null) {
			return;
		}

		PropertyDescriptor[] poDescriptors = PropertyUtils
				.getPropertyDescriptors(fromobject.getClass());
		for (int i = 0; i < poDescriptors.length; i++) {
			String propertyName = poDescriptors[i].getName();
			Class propertyType = poDescriptors[i].getPropertyType();
			try {
				if (isPrimitiveOrLangType(propertyType)
						|| propertyType.equals(Date.class)) {

					Object propertyValue = PropertyUtils.getSimpleProperty(
							fromobject, propertyName);
					if(propertyValue!=null&&StringUtils.isNotEmpty(propertyValue.toString())){
						PropertyUtils.setSimpleProperty(toObject, propertyName,
								propertyValue);
					}
				} else {
					// did nothing
				}
			} catch (Exception e) {
				continue;
			}
		}

	}
	/**
	 * 是否基本类型
	 * 
	 * @param propertyType
	 * @return
	 */
	public static boolean isPrimitiveOrLangType(Class propertyType) {
		return propertyType.isPrimitive() || propertyType.equals(String.class)
				|| propertyType.equals(Integer.class)
				|| propertyType.equals(Character.class)
				|| propertyType.equals(Float.class)
				|| propertyType.equals(Boolean.class)
				|| propertyType.equals(Byte.class)
				|| propertyType.equals(Double.class)
				|| propertyType.equals(Short.class)
				|| propertyType.equals(Number.class)
				|| propertyType.equals(BigDecimal.class)
				|| propertyType.equals(Long.class)
				|| propertyType.equals(Date.class)
				|| propertyType.equals(Timestamp.class);
	}
	/**
	 * 判断是否为空，为空返回无，否则返回原值,如果是date型则转为String
	 * @param o
	 * @return
	 */
	public static String getNotNullString(Object o){
		String s="无";
		//不为空
		if(o!=null){
			if(o instanceof String){
				if("".equals((String)o)){
					s = (String)o;
				}
			}else if(o instanceof Date){
					s = DateUtils.convert((Date)o, "yyyy年MM月dd日 HH:mm:ss");
			}else{
				s = o.toString();
			}
		}
		return s.trim();
	}
	/**
	 * 根据list生成table
	 */
	public static String listToForm(List<String> list){
		if(list == null || list.size() == 0){
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table class='table_form'>");
		for(int i = 0; i < list.size(); i++){
			if(i%2 == 0){
				buffer.append("<tr>");
			}
			buffer.append("<td><font size='4'>").append(list.get(i)).append("</font></td>");
			if(i%2 == 1){
				buffer.append("</tr>");
			}
		}
		buffer.append("</table>");
		return buffer.toString();
	}
	/**
	 * 将代码转为汉字
	 */
	public static void changeToContent(Collection c,IDataDictService dataDictService){
		List<DataDict> col;
		HashMap<String,String> map = new HashMap<String,String>();

		map.put("handleStatus", IDataDictService.DATA_DICT_TYPE__RECORD_HANDLE_STATUS);
		map.put("transCurrency", IDataDictService.DATA_DICT_TYPE__CURRENCY_TYPE);
		map.put("transDc", IDataDictService.DATA_DICT_TYPE__TRANS_DC);
		map.put("transType", IDataDictService.DATA_DICT_TYPE__TRANS_TYPE);
		map.put("transStatus", IDataDictService.DATA_DICT_TYPE__TRANS_STATUS);
		map.put("bankPmtCnlType", IDataDictService.DATA_DICT_TYPE__PAYMENT_CNL);
		map.put("termialType", IDataDictService.DATA_DICT_TYPE__TERMIAL_TYPE);
		map.put("bankReturnResult", IDataDictService.DATA_DICT_TYPE__BANK_RETURN_RESULT);
		map.put("handleResult", IDataDictService.DATA_DICT_TYPE__FAIL_REASON);

		Set<String> keySet = map.keySet();
		for(String key:keySet){
			try{
				String value = map.get(key);
				col = dataDictService.list(value);
				CollectionUtils.transformCollection(c, key, key, col, "code", "name");
			}catch(Exception e){
				
			}
		}
	}
}
