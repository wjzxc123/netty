package com.lut.licon.netty.ddd.repository.assemble.service.impl;

import java.util.Currency;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.po.AccountPO;
import com.lut.licon.netty.ddd.repository.assemble.service.AccountBuilder;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.Money;
import com.lut.licon.netty.ddd.types.UserId;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:49
 */
@Component
public class AccountBuilderImpl implements AccountBuilder {
	@Override
	public Account toAccount(AccountPO accountPo)  throws Exception{
		Account account = new Account();
		account.setUserId(new UserId(accountPo.getUserId()));
		account.setAccountId(new AccountId(accountPo.getAccountId()));
		account.setAccountNumber(new AccountNumber(accountPo.getAccountNumber()));
		account.setAvailable(new Money(accountPo.getAvailable(), Currency.getInstance(accountPo.getCurrency())));
		account.setDailyLimit(new Money(accountPo.getDailyLimit(), Currency.getInstance(accountPo.getCurrency())));
		account.setCurrency(Currency.getInstance(accountPo.getCurrency()));
		return account;
	}
	@Override
	public AccountPO fromAccount(Account account)  throws Exception{
		AccountPO accountPo = new AccountPO();
		accountPo.setUserId(account.getUserId().getUserId());
		accountPo.setAccountId(account.getAccountId().getAccountId());
		accountPo.setAccountNumber(account.getAccountNumber().getAccountNumber());
		accountPo.setAvailable(account.getAvailable().getAmount());
		accountPo.setDailyLimit(account.getDailyLimit().getAmount());
		accountPo.setCurrency(account.getCurrency().getCurrencyCode());
		return accountPo;
	}
}
