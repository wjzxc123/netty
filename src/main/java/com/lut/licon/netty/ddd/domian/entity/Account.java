package com.lut.licon.netty.ddd.domian.entity;

import java.util.Currency;

import com.lut.licon.netty.ddd.domian.repository.Aggregate;
import com.lut.licon.netty.ddd.exception.DailyLimitException;
import com.lut.licon.netty.ddd.exception.InsufficientFundsException;
import com.lut.licon.netty.ddd.exception.InvalidCurrencyException;
import com.lut.licon.netty.ddd.exception.MoneyAmountNotNullException;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.Money;
import com.lut.licon.netty.ddd.types.UserId;
import lombok.Getter;
import lombok.Setter;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 14:34
 */
@Setter
@Getter
public class Account implements Aggregate<UserId> {
	private UserId userId;
	private AccountId accountId;
	private AccountNumber accountNumber;
	private Money available;
	private Money dailyLimit;
	private Currency currency;

	/***
	 * 存入
	 * @param money
	 * @return
	 * @throws
	 * @author Licon
	 * @date 2021/10/29 15:15
	 */
	public void deposit(Money money)throws InvalidCurrencyException,MoneyAmountNotNullException {
		if (!this.currency.equals(money.getCurrency())){
			throw new InvalidCurrencyException();
		}
		this.available = this.available.add(money);
	}

	/***
	 * 取出
	 * @param money
	 * @return
	 * @throws
	 * @author Licon
	 * @date 2021/10/29 15:11
	 */
	public void fetch(Money money) throws DailyLimitException,InvalidCurrencyException,InsufficientFundsException {
		if (!this.currency.equals(money.getCurrency())){
			throw new InvalidCurrencyException();
		}
		if (available.compareTo(money)<0){
			throw new InsufficientFundsException("账户金额不足");
		}
		if (dailyLimit.compareTo(money)<0){
			throw new DailyLimitException("账户已超过日限额");
		}
		this.available = this.available.subtract(money);
	}

	@Override
	public UserId getId() {
		return this.userId;
	}
}
