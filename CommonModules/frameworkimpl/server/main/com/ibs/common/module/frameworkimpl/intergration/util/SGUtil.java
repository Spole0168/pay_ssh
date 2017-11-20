package com.ibs.common.module.frameworkimpl.intergration.util;

import com.ibs.common.module.frameworkimpl.intergration.dto.OpTypeEnum;


/**
 * <pre>
 * *********************************************
 * Description: 网关接口工具函数
 * *********************************************
 * </pre>
 */
public class SGUtil {

	/**
	 * 检查对象是否为空函数
	 * 
	 * @param value
	 * @return
	 */
	public static boolean checkNull(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			return value.equals("");
		}
		return false;
	}

	/**
	 * 转换操作类型：接口 ---> EXP
	 * 
	 * @param opType
	 * @return
	 */
	public static Long convertOptype(String opType) {
		if (OpTypeEnum.OP_TYPE_NEW.equals(opType)) {
			return 0L;
		}
		if (OpTypeEnum.OP_TYPE_MODIFY.equals(opType)) {
			return 1L;
		}
		if (OpTypeEnum.OP_TYPE_DELETE.equals(opType)) {
			return 2L;
		}
		if (OpTypeEnum.OP_TYPE_AUDIT.equals(opType)) {
			return 3L;
		}
		if (OpTypeEnum.OP_TYPE_SUPPLEMENT.equals(opType)) {
			return 4L;
		}
		return 0L;
	}

	/**
	 * 转换操作类型：EXP ---> 接口
	 * 
	 * @param opType
	 * @return
	 */
	public static String convertOptype(Long opType) {
		if (opType != null) {
			switch (opType.intValue()) {
			case 0:
				return OpTypeEnum.OP_TYPE_NEW;
			case 1:
				return OpTypeEnum.OP_TYPE_MODIFY;
			case 2:
				return OpTypeEnum.OP_TYPE_DELETE;
			case 3:
				return OpTypeEnum.OP_TYPE_AUDIT;
			case 4:
				return OpTypeEnum.OP_TYPE_SUPPLEMENT;
			default:
				return OpTypeEnum.OP_TYPE_NEW;
			}
		} else {
			return OpTypeEnum.OP_TYPE_NEW;
		}
	}
}
