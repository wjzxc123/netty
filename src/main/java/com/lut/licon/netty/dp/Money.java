package com.lut.licon.netty.dp;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/25 16:34
 */
public class Money {
	private BigDecimal money;
	private Currency currency;

	public Money(BigDecimal money, Currency currency) {
		this.money = money;
		this.currency = currency;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Money{" +
				"money=" + money +
				", currency=" + currency +
				'}';
	}
}
