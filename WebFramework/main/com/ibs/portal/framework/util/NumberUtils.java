package com.ibs.portal.framework.util;

import java.math.BigDecimal;

public class NumberUtils extends org.apache.commons.lang.math.NumberUtils {
	
	public static Double getDouble(BigDecimal v) {
		Double rtn = null;
		
		if (v != null)
			rtn = createDouble(v.toString());
		
		return rtn;
	}

	public static BigDecimal getBigDecimal(Double v) {
		BigDecimal rtn = null;
		
		if (v != null)
			rtn = createBigDecimal(v.toString());
		
		return rtn;
	}
	
}
