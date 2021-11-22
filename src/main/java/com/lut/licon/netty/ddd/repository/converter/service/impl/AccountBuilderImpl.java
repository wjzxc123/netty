package com.lut.licon.netty.ddd.repository.converter.service.impl;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:49
 */
/*@Component
public class AccountBuilderImpl implements AccountBuilder {
	@Override
	public Account toAccount(AccountDO accountDo)  throws Exception{
		Account account = new Account();
		account.setUserId(new UserId(accountDo.getUserId()));
		account.setAccountId(new AccountId(accountDo.getAccountId()));
		account.setAccountNumber(new AccountNumber(accountDo.getAccountNumber()));
		account.setAvailable(new Money(accountDo.getAvailable(), Currency.getInstance(accountDo.getCurrency())));
		account.setDailyLimit(new Money(accountDo.getDailyLimit(), Currency.getInstance(accountDo.getCurrency())));
		account.setCurrency(Currency.getInstance(accountDo.getCurrency()));
		return account;
	}
	@Override
	public AccountDO fromAccount(Account account)  throws Exception{
		AccountDO accountDo = new AccountDO();
		accountDo.setUserId(account.getUserId().getUserId());
		accountDo.setAccountId(account.getAccountId().getAccountId());
		accountDo.setAccountNumber(account.getAccountNumber().getAccountNumber());
		accountDo.setAvailable(account.getAvailable().getAmount());
		accountDo.setDailyLimit(account.getDailyLimit().getAmount());
		accountDo.setCurrency(account.getCurrency().getCurrencyCode());
		return accountDo;
	}
}*/
