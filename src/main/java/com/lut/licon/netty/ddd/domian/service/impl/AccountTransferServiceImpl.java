package com.lut.licon.netty.ddd.domian.service.impl;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.domian.service.AccountTransferService;
import com.lut.licon.netty.ddd.exception.DailyLimitException;
import com.lut.licon.netty.ddd.exception.InsufficientFundsException;
import com.lut.licon.netty.ddd.types.ExchangeRate;
import com.lut.licon.netty.ddd.types.Money;
import org.springframework.stereotype.Service;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 16:55
 */
@Service
public class AccountTransferServiceImpl implements AccountTransferService {
	@Override
	public void transfer(Account sourceAccount, Money targetMoney, Account targetAccount, ExchangeRate exchangeRate) throws DailyLimitException, InsufficientFundsException {
		Money sourceMoney = exchangeRate.changeTo(targetMoney);
		sourceAccount.fetch(sourceMoney);
		targetAccount.deposit(targetMoney);
	}
}
