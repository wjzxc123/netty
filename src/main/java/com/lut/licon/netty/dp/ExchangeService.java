package com.lut.licon.netty.dp;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/26 9:08
 */
public class ExchangeService {
	public static ExchangeRage getRate(Currency from,Currency to){
		if (from.equals(to)){
			return new ExchangeRage(new BigDecimal("1"),from,to);
		}else if (from.equals(Currency.getInstance("USD")) && to.equals(Currency.getInstance("CNY"))){
			return new ExchangeRage(new BigDecimal("6.37"),from,to);
		}else if (from.equals(Currency.getInstance("CNY")) && to.equals(Currency.getInstance("USD"))){
			return new ExchangeRage(new BigDecimal("0.1566"),from,to);
		}else {
			return new ExchangeRage(new BigDecimal("0.0000001"),from,to);
		}
	}
}
