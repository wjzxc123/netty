package com.lut.licon.netty.ddd.types;

import java.math.BigDecimal;
import java.util.Currency;
import com.lut.licon.netty.ddd.exception.MoneyAmountNotNullException;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/28 16:20
 */
public class Money {
	private BigDecimal amount;
	private Currency currency;

	public Money(BigDecimal amount, Currency currency) {
		if (amount == null){
			throw new MoneyAmountNotNullException("金额不能为空");
		}
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public int compareTo(Money money){
		return this.amount.compareTo(money.getAmount());
	}

	public Money add(Money money){
		BigDecimal resultMoney = this.amount.add(money.getAmount());
		return new Money(resultMoney,this.currency);
	}

	public Money subtract(Money money){
		BigDecimal resultMoney = this.amount.subtract(money.getAmount());
		return new Money(resultMoney,this.currency);
	}
}
