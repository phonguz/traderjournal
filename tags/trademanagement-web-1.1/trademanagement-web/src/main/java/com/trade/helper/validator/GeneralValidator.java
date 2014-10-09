package com.trade.helper.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralValidator {

	public static boolean isValidEmail(String email) {
		  Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[-A-Za-z0-9]+(\\.[A-Za-z]{1,})+$");
		  Matcher m = p.matcher(email);
		  return m.matches() ? true : false;
	}
	
	public static boolean isValidString(String value){
		Pattern patt =Pattern.compile("^([ a-zA-Z]){1,}");
		Matcher m = patt.matcher(value);
		return m.matches();
	}
	
	public static boolean isValidCurrencyName(String currencyName) {
		  Pattern p = Pattern.compile("^[a-zA-Z]{3,25}+$");
		  Matcher m = p.matcher(currencyName);
		  return m.matches() ? true : false;
	}
	
	public static boolean isValidInstrument(String instrumentName) {
		  Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,200}+$");
		  Matcher m = p.matcher(instrumentName);
		  return m.matches() ? true : false;
	}
	
	public static boolean isValidNumber(String number){
		Pattern p = Pattern.compile("^\\d*$");
		Matcher m = p.matcher(number);
		return m.matches() ? true : false;
	}
	
	public static boolean isValidRiskFactor(String number){
		Pattern p = Pattern.compile("^([0-9]{1,2}([\\.][0-9]{1,})?$|100([\\.][0]{1,})?)$");
		Matcher m = p.matcher(number);
		return m.matches() ? true : false;
	}
	
	public static boolean isValidBalance(String number){
		Pattern p = Pattern.compile("^\\d{1,5}+(\\.\\d\\d)?$");
		Matcher m = p.matcher(number);
		return m.matches() ? true : false;
	}
	
	public static boolean isValidAccountName(String acoountName) {
		  Pattern p = Pattern.compile("^[ a-zA-Z0-9]{1,255}+$");
		  Matcher m = p.matcher(acoountName);
		  return m.matches() ? true : false;
	}
	
	public static boolean isValidEventTypeName(String eventTypeName) {
		  Pattern p = Pattern.compile("^[a-zA-Z0-9]{1,200}+$");
		  Matcher m = p.matcher(eventTypeName);
		  return m.matches() ? true : false;
	}
}
