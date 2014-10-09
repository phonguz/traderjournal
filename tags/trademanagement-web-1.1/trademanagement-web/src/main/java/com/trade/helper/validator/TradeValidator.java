package com.trade.helper.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.trade.helper.bean.TradeBean;
import com.trade.model.Instrument;
import com.trade.util.ObjectErrorFactory;

@Component
public class TradeValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return TradeBean.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountsId", "error.empty.accountsId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "instrumentId", "error.empty.instrumentId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.empty.quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "openprice", "error.empty.openprice");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "closeprice", "error.empty.closeprice");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stoploss", "error.empty.stoploss");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "opentradedate", "error.empty.opentradedate");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "closetradedate", "error.empty.closetradedate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tradetype", "error.empty.tradetype");
		
		TradeBean bean=(TradeBean)obj;
		if(bean.getAccountsId() !=null && !"Select Account".equalsIgnoreCase(bean.getAccountsId())){
			if(!GeneralValidator.isValidAccountName(bean.getAccountsId())){
				errors.rejectValue("accountsId", "error.empty.accountsId");
			}
		}else{
			errors.rejectValue("accountsId", "error.invalid.accountId");
		}
		if((bean.getInstrumentId().equalsIgnoreCase("") || bean.getInstrumentId() == null) &&
				(bean.getInstrumentName().equalsIgnoreCase("") || bean.getInstrumentName() == null)){
			errors.rejectValue("instrumentId", "error.required.instrumentId");
		}
		if((bean.getInstrumentId().equalsIgnoreCase("Select Instrument")) &&
				(bean.getInstrumentName().equalsIgnoreCase("") || bean.getInstrumentName() == null)){
			errors.rejectValue("instrumentId", "error.required.instrumentId");
		}
		if((!bean.getOpentradedate().equalsIgnoreCase("") && bean.getOpentradedate() != null) && 
				(!bean.getClosetradedate().equalsIgnoreCase("") && bean.getClosetradedate() != null)){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date fromdate = (Date)df.parse(bean.getOpentradedate());
				Date todate = (Date)df.parse(bean.getClosetradedate());
				if(fromdate.compareTo(todate) > 0){
					errors.rejectValue("opentradedate", "error.invalidrange.date");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				errors.rejectValue("closetradedate", "error.invalid.date");
			}
		}
		if((!bean.getTradetype().equalsIgnoreCase("") && bean.getTradetype() != null) && !bean.getTradetype().equalsIgnoreCase("Select Trade type")){		
			if((Integer.parseInt(bean.getTradetype()) >=1) && (Integer.parseInt(bean.getTradetype()) <=0)){
				errors.rejectValue("tradetype", "error.invalid.tradetype");
			}
		}else{
			errors.rejectValue("tradetype", "error.empty.tradetype");
		}		
		
		
		if(!"".equalsIgnoreCase(bean.getQuantity()) && bean.getQuantity().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getQuantity())){
				errors.rejectValue("quantity", "error.invalid.quantity");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getOpenprice()) && bean.getOpenprice().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getOpenprice())){
				errors.rejectValue("openprice", "error.invalid.openprice");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getCloseprice()) && bean.getCloseprice().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getCloseprice())){
				errors.rejectValue("closeprice", "error.invalid.closeprice");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getStoploss()) && bean.getStoploss().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getStoploss())){
				errors.rejectValue("stoploss", "error.invalid.stoploss");
			}
		}

		/*if(!"".equalsIgnoreCase(bean.getEntrycoms()) && bean.getEntrycoms().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getEntrycoms())){
				errors.rejectValue("quantity", "error.empty.quantity");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getExitcoms()) && bean.getExitcoms().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getExitcoms())){
				errors.rejectValue("quantity", "error.empty.quantity");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getEntryfees()) && bean.getEntryfees().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getEntryfees())){
				errors.rejectValue("quantity", "error.empty.quantity");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getExitfees()) && bean.getExitfees().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getExitfees())){
				errors.rejectValue("quantity", "error.empty.quantity");
			}
		}
		
		if(!"".equalsIgnoreCase(bean.getCarrycost()) && bean.getCarrycost().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getCarrycost())){
				errors.rejectValue("quantity", "error.empty.quantity");
			}
		}*/
	}
}
