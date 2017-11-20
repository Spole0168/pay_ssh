package com.ibs.portal.framework.util;

import java.util.List;

import com.ibs.portal.framework.server.metadata.OptionObjectPair;

public class SelectRenderUtils {
	public static final String KV = ":";
	public static final String NEXT = ";";

	public static String toRenderString(List<OptionObjectPair> values) {
		StringBuilder sb = new StringBuilder(50);

		for (OptionObjectPair oop : values) {
			sb.append(oop.getKey()).append(KV).append(oop.getValue()).append(
					NEXT);
		}

		if (sb.length() > 1)
			sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	public static void main(String[] args) {
		/*
		 * List<OptionObjectPair> resourceTypeList = new
		 * ArrayList<OptionObjectPair>();
		 * 
		 * resourceTypeList.add(new OptionObjectPair("", "全部"));
		 * resourceTypeList.add(new OptionObjectPair(
		 * Resource.RESOURCE_TYPE_URL_KEY, Resource.RESOURCE_TYPE_URL_VALUE));
		 * resourceTypeList.add(new OptionObjectPair(
		 * Resource.RESOURCE_TYPE_BUTTON_KEY,
		 * Resource.RESOURCE_TYPE_BUTTON_VALUE));
		 * 
		 * System.out.println(toRenderString(resourceTypeList));
		 */
	}

}
