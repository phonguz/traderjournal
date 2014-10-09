package com.trade.helper.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.trade.helper.bean.TradeBean;

@Component
public class TradeEventValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return TradeBean.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.empty.description");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newvalue", "error.empty.newvalue");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventtypeid", "error.empty.eventtypeid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventdate", "error.empty.eventdate");		
		
		TradeBean bean=(TradeBean)obj;	
		if(!"".equalsIgnoreCase(bean.getNewvalue()) && bean.getNewvalue().length()>=0){
			if(!GeneralValidator.isValidBalance(bean.getNewvalue())){
				errors.rejectValue("newvalue", "error.invalid.newvalue");
			}
		}		
		if(!"".equalsIgnoreCase(bean.getEventtypeid()) && bean.getEventtypeid() !=null){
			if(!(Integer.parseInt(bean.getEventtypeid()) > 0)){
				errors.rejectValue("eventtypeid", "error.invalid.eventtypeid");
			}
		}		
	}
}
