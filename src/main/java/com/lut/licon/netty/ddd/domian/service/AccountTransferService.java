package com.lut.licon.netty.ddd.domian.service;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.exception.DailyLimitException;
import com.lut.licon.netty.ddd.exception.InsufficientFundsException;
import com.lut.licon.netty.ddd.types.ExchangeRate;
import com.lut.licon.netty.ddd.types.Money;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 16:54
 */
public interface AccountTransferService {
	/***
	 *转账操作
	 * @param sourceAccount
	 * @param targetMoney
	 * @param targetAccount
	 * @param exchangeRate
	 * @return
	 * @throws DailyLimitException
	 * @throws InsufficientFundsException
	 * @author Licon
	 * @date 2021/11/15 16:12
	 */
	void transfer(Account sourceAccount, Money targetMoney,Account targetAccount, ExchangeRate exchangeRate) throws DailyLimitException, InsufficientFundsException;
}
