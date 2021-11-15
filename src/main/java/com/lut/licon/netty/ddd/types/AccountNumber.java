package com.lut.licon.netty.ddd.types;

import lombok.Getter;
import lombok.Setter;

import org.springframework.util.StringUtils;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/28 16:21
 */
@Setter
@Getter
public class AccountNumber {
	private String accountNumber;

	public AccountNumber(String accountNumber) {
		if (!StringUtils.hasLength(accountNumber)){
			throw new IllegalArgumentException("账户号不能为空");
		}
		this.accountNumber = accountNumber;
	}
}
