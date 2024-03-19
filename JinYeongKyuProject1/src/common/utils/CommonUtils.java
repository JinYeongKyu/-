package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtils {
	
	public static boolean isNumber(String value) {
		for(int i=0;i < value.length();i++) {		
			int codeValue = Character.codePointAt(value, i);
			if(!(codeValue >='0' && codeValue<='9')) return false;
		}
		return true;
	}
	
	public static long getDiffBetweenDates(
			String stFDate,
			String stSDate,
			String pattern,
			char delim) throws ParseException {
		SimpleDateFormat dateFormat= new SimpleDateFormat(pattern);
		Date fDate=dateFormat.parse(stFDate);
		Date sDate=dateFormat.parse(stSDate);
		
		long fTime = fDate.getTime();
		long sTime = sDate.getTime();
		long diff = Math.abs(fTime-sTime);
		
		switch(Character.toUpperCase(delim)) {
			case 'D':return diff/1000/60/60/24;
			case 'H':return diff/1000/60/60;
			case 'M':return diff/1000/60;
			default: return diff/1000;
		}
		
	}
	
	public static int[] toIntArray(String value) {
		int[] intArray = new int[value.length()];
		for(int i=0;i<value.length();i++) {
			intArray[i]=(int)value.charAt(i);
		}
		return intArray;
	}
	
	public static char getInitialConsonant(String value) {
		if(!Pattern.matches("^[가-힣]{2,}$", value.trim())) return '0';
		char lastName=value.charAt(0);
		
		int index = (lastName-'가')/28/21;
		char[] initialConsonants= {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		return initialConsonants[index];
	}

}
