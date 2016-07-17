package dena.branch.service.utils;

import java.math.BigDecimal;

public class StringUtils {

	public static int getIntValue(String myString) {

		return (myString.trim().length() > 0) ? Integer.parseInt(myString) : 0;
	}

	public static double getDoubleValue(String myString) {

		return (myString.trim().length() > 0) ? Double.parseDouble(myString) : 0d;
	}
	
	public static float getFloatValue(String myString) {
		
		return (myString.trim().length() > 0) ? Float.parseFloat(myString) : 0f;
	}
	
	public static BigDecimal getBigDecimalValue(String myString) {
		
		return (myString.trim().length() > 0) ? new BigDecimal(myString) : new BigDecimal(0);
	}
}
