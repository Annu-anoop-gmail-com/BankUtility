package dena.branch.service.utils;

public class StringUtils {

	public static int getIntValue(String myString) {

		return (myString.trim().length() > 0) ? Integer.parseInt(myString) : 0;
	}

	public static double getDoubleValue(String myString) {

		return (myString.trim().length() > 0) ? Double.parseDouble(myString) : 0d;
	}
}
