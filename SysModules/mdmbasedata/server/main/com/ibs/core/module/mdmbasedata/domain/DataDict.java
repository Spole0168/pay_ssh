package com.ibs.core.module.mdmbasedata.domain;

import java.util.Date;

import com.ibs.core.module.mdmbasedata.common.Constants;

/**
 * 数据字典 T_MDM_DICT
 * 
 * @author zdp
 * 
 */
public class DataDict extends MdmBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5634114314212239630L;
	private String type;// 类型
	private int displayOrder;// 排序号
	private String value;// 值
	private Date fromDate; // 生效时间
	private Date endDate;// 失效时间
	private String scanCode;// 扫描代码
	private String remark;// 备注
	private Language locale;// 语言
	private String languageCode;//

	private String typeName;
	private String ctgCode;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String extendAtt1;// 扩展属性1
	// private String orgId;//组织ID
	private char canModifyCode = 'N';// 为Y或N，表示该列的CODE能不能修改，该列能不能删除

	public char getCanModifyCode() {
		return canModifyCode;
	}

	public void setCanModifyCode(char canModifyCode) {
		this.canModifyCode = canModifyCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatusName() {
		if (Constants.IS_VALID_NOT_VALID.equalsIgnoreCase(this.getStatus())) {
			return Constants.IS_VALID_NOT_VALID_VALUE;
		} else if (Constants.IS_VALID_VALID.equalsIgnoreCase(this.getStatus())) {
			return Constants.IS_VALID_VALID_VALUE;
		}
		return "";
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	public String getExtendAtt1() {
		return extendAtt1;
	}

	public void setExtendAtt1(String extendAtt1) {
		this.extendAtt1 = extendAtt1;
	}

	// public String getOrgId() {
	// return orgId;
	// }
	// public void setOrgId(String orgId) {
	// this.orgId = orgId;
	// }

	public Language getLocale() {
		return locale;
	}

	public void setLocale(Language locale) {
		this.locale = locale;
	}

	public String getTypeName() {
		if (typeName == null || typeName.equalsIgnoreCase("")) {
			typeName = type;
		}
		return typeName;

	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCtgCode() {
		return ctgCode;
	}

	public void setCtgCode(String ctgCode) {
		this.ctgCode = ctgCode;
	}

	// private String getPropertiesvalue(Class<IDataDictService> fromobject,
	// String key) {
	// String val = null;
	// Field[] Fields = IDataDictService.class.getFields();
	//
	// for (int i = 0; i < Fields.length; i++) {
	// try {
	// String propertyName = Fields[i].getName();
	// Object propertyValue = Fields[i].get(propertyName);
	//
	// if (key.equalsIgnoreCase(propertyValue.toString())) {
	// String valName = propertyName + "_VALUE";
	// return (String) IDataDictService.class.getField(valName)
	// .get(valName);
	//
	// }
	//
	// } catch (Exception e) {
	// continue;
	// }
	// }
	//
	// return val;
	//
	// }

	// private String getPropertiesvalue(Class<IDataDictService> fromobject,
	// String key) {
	// String val = null;
	//
	// try {
	// String valName = "DATA_DICT_TYPE__" + key+"_VALUE";
	// return (String) IDataDictService.class.getField(valName).get(
	// valName);
	//
	// } catch (Exception e) {
	//
	// }
	//
	// return val;
	//
	// }
}
