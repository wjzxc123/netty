package com.lut.licon.netty.ddd.common;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/15 16:20
 */
public enum CurrencyEnum {
	/**
	 * 人民币
	 */
	CNY("CNY"),
	/**
	 * 美元
	 */
	USD("USD");

	private String code;

	CurrencyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
