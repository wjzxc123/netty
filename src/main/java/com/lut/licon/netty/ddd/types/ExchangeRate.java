package com.lut.licon.netty.ddd.types;

import java.math.BigDecimal;
import java.util.Currency;
import lombok.Getter;
import lombok.Setter;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/28 16:24
 */
@Setter
@Getter
public class ExchangeRate {
	private BigDecimal rate;
	private Currency source;
	private Currency target;

	public ExchangeRate(BigDecimal rate, Currency source, Currency target) {
		this.rate = rate;
		this.source = source;
		this.target = target;
	}

	public Money changeTo(Money targetMoney){
		BigDecimal targetAmount = targetMoney.getAmount().multiply(rate);
		return new Money(targetAmount,targetMoney.getCurrency());
	}
}
