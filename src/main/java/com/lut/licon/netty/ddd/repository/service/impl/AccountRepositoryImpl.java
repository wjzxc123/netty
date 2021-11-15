package com.lut.licon.netty.ddd.repository.service.impl;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.mapper.AccountMapper;
import com.lut.licon.netty.ddd.persistence.po.AccountPO;
import com.lut.licon.netty.ddd.repository.assemble.service.AccountBuilder;
import com.lut.licon.netty.ddd.repository.service.AccountRepository;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.UserId;
import org.springframework.stereotype.Repository;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:48
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	private final AccountMapper accountMapper;

	private final AccountBuilder accountBuilder;

	public AccountRepositoryImpl(AccountBuilder accountBuilder, AccountMapper accountMapper) {
		this.accountBuilder = accountBuilder;
		this.accountMapper = accountMapper;
	}

	@Override
	public Account find(UserId userId) throws Exception{
		AccountPO accountPo = accountMapper.queryByUserId(userId.getUserId());
		return accountBuilder.toAccount(accountPo);
	}

	@Override
	public Account find(AccountNumber accountNumber) throws Exception{
		AccountPO accountPo = accountMapper.queryByAccountNumber(accountNumber.getAccountNumber());
		return accountBuilder.toAccount(accountPo);
	}

	@Override
	public Account find(AccountId accountId) throws Exception{
		AccountPO accountPo = accountMapper.queryByAccountId(accountId.getAccountId());
		return accountBuilder.toAccount(accountPo);
	}

	@Override
	public Account save(Account account) throws Exception{
		int result = 0;
		if (account.getAccountId() == null){
			result = accountMapper.insert(accountBuilder.fromAccount(account));
		}else {
			result = accountMapper.update(accountBuilder.fromAccount(account));
		}

		if (result == 0){
			throw new RuntimeException("保存异常");
		}
		return account;
	}
}
