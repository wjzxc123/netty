package com.lut.licon.netty.ddd.exception;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 14:21
 */
public class MoneyAmountNotNullException extends IllegalArgumentException {
	public MoneyAmountNotNullException(String message) {
		super(message);
	}
}
