package com.trade.helper.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.trade.helper.bean.AccountBean;
import com.trade.model.Accounts;

@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Accounts.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.empty.accountname");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "balance", "error.empty.balance");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "percentageRisk", "error.empty.risk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccyId", "error.empty.currency");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionAmount", "error.empty.transactionamount");

		AccountBean bean=(AccountBean)target;
		
		if(!GeneralValidator.isValidAccountName(bean.getName())){
			errors.rejectValue("name", "error.invalid.accountname");
		}
		
		/*if(!GeneralValidator.isValidBalance(bean.getTransactionAmount())){
			errors.rejectValue("transactionAmount", "error.invalid.transactionamount");
		}*/
		 
		/*if(!GeneralValidator.isValidBalance(bean.getBalance())){
			errors.rejectValue("balance", "error.invalid.balance");
		}*/
		if(!GeneralValidator.isValidRiskFactor(bean.getPercentageRisk())){
			errors.rejectValue("percentageRisk", "error.invalid.risk");
		}
	}

}
