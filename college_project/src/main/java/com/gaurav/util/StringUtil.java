package com.gaurav.util;

public class StringUtil {

	public static String toCommaSeparatedString(Object[] items) {
		StringBuilder sb = new StringBuilder();
		for (Object object : items) {
			sb.append(object).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
}
