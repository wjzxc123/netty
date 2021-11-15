package com.lut.licon.netty.ddd.exception;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 14:41
 */
public class DailyLimitException extends Exception{
	public DailyLimitException(String message) {
		super(message);
	}
}
