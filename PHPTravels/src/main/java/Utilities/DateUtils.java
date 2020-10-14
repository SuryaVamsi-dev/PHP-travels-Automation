package Utilities;

import java.util.Date;

public class DateUtils {

	public static String getTymStamp() {
		
		Date date=new Date();
		System.out.println(date.toString());
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}
