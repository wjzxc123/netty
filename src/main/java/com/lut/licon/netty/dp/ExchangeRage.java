package com.lut.licon.netty.dp;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.util.Assert;

/**
 * Describe:汇率
 *
 * @author Licon
 * @date 2021/10/25 16:38
 */
public class ExchangeRage {
	private BigDecimal rate;
	private Currency from;
	private Currency to;

	public ExchangeRage(BigDecimal rate, Currency from, Currency to) {
		this.rate = rate;
		this.from = from;
		this.to = to;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	//注入行为
	Money exchange(Money fromMoney){
		//1.判空,转换金额不允许为空
		//2.判断币种是否正确
		//3.金额换算
		//4.组装新的Money对象
		Assert.notNull(fromMoney,"转换金额不能为空");
		Assert.isTrue(this.from.equals(fromMoney.getCurrency()),"转换币种不一致");
		BigDecimal targetAmount = fromMoney.getMoney().multiply(rate);
		return new Money(targetAmount,this.to);
	}

}
