package com.lut.licon.netty.ddd.persistence.po;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:30
 */
@Getter
@Setter
public class AccountDO {
	private Long userId;
	private Long accountId;
	private String accountNumber;
	private BigDecimal available;
	private BigDecimal dailyLimit;
	private String currency;
}
