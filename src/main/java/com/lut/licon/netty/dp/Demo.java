package com.lut.licon.netty.dp;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/26 9:24
 */
public class Demo {
	public static void main(String[] args) {
		Account a = new Account("a",new Money(new BigDecimal("1000"),Currency.getInstance("USD")),
				"lsw",new Money(new BigDecimal("500"),Currency.getInstance("USD")));

		Account b = new Account("b",new Money(new BigDecimal("10000"),Currency.getInstance("CNY")),
				"wangke",new Money(new BigDecimal("500"),Currency.getInstance("USD")));

		//A账户转账金额（300 USD）-->B账户
		BankService.transfer(a,new Money(new BigDecimal("300"),Currency.getInstance("USD")),b);

		System.out.println(a);
		System.out.println(b);

		Account c = new Account("c",new Money(new BigDecimal("1000"),Currency.getInstance("USD")),
				"lsw",new Money(new BigDecimal("500"),Currency.getInstance("USD")));

		Account d = new Account("d",new Money(new BigDecimal("10000"),Currency.getInstance("CNY")),
				"randog",new Money(new BigDecimal("500"),Currency.getInstance("USD")));

		//C账户转账金额（200 USD）-->D账户
		BankService.transfer(d,new Money(new BigDecimal("200"),Currency.getInstance("USD")),c);

		System.out.println(c);
		System.out.println(d);
	}
}
