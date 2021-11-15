package com.lut.licon.netty.ddd.exception;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:13
 */
public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(String message) {
		super(message);
	}
}
