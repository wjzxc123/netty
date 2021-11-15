package com.lut.licon.netty.ddd.common;

import java.util.Currency;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/12 14:42
 */
public class CurrencyConstraintValidator implements ConstraintValidator<CurrencyValid,String> {
	@Override
	public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
		try {
			Currency.getInstance(o);
			return true;
		}catch (RuntimeException e){
			return false;
		}
	}
}
