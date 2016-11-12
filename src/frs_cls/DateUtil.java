package frs_cls;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class DateUtil {

	/**
	 * @param format
	 * @param variance
	 * @return
	 */
	static String CurrentDate;
	public static String getCurrentDate(String format, int variance) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		cal.add(Calendar.DATE,variance);
		CurrentDate= sdf.format(cal.getTime());
		return CurrentDate;
	}	
}

