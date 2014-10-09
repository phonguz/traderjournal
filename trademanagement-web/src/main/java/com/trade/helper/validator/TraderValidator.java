package com.trade.helper.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.trade.helper.bean.TraderBean;

@Component
public class TraderValidator implements Validator{

	private GeneralValidator valid;
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return TraderBean.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.empty.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "error.empty.firstname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "error.empty.lastname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.empty.confirmpassword");
		TraderBean bean=(TraderBean)obj;
		 if(!(bean.getPassword().equals(bean.getConfirmPassword()))){
			errors.rejectValue("password", "error.empty.notmatch");
		 }/*else if(bean.getPassword().length()<=5){
			 errors.rejectValue("password", "error.length.password");
		 }else if(bean.getConfirmPassword().length()<=5){
			 errors.rejectValue("confirmPassword", "error.length.password");
		 }*/
		 if(!bean.getPassword().equalsIgnoreCase("") && bean.getPassword() !=null){
			 if(!GeneralValidator.isValidEmail(bean.getUsername())){
				 errors.rejectValue("username", "error.invalid.username");
			 }
		 }
		 if(!valid.isValidString(bean.getFirstname())){
			 errors.rejectValue("firstname", "error.invalid.firstname");
		 }
		 if(!valid.isValidString(bean.getLastname())){
			 errors.rejectValue("lastname", "error.invalid.lastname");
		 }
	}

}
