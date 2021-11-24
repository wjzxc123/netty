package com.lut.licon.netty.ddd.repository.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;


import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.domian.repository.AccountRepository;
import com.lut.licon.netty.ddd.domian.repository.Aggregate;
import com.lut.licon.netty.ddd.exception.AttachException;
import com.lut.licon.netty.ddd.exception.DetachException;
import com.lut.licon.netty.ddd.persistence.mapper.AccountMapper;
import com.lut.licon.netty.ddd.persistence.po.AccountDO;
import com.lut.licon.netty.ddd.repository.converter.service.AccountBuilder;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.UserId;
import org.springframework.stereotype.Repository;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/19 10:50
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	private final AccountMapper accountMapper;

	private final AccountBuilder accountBuilder = AccountBuilder.INSTANCE;

	public AccountRepositoryImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public Long countAccount(QueryAccount queryAccount) throws Exception {
		return null;
	}

	@Override
	public List<Account> query(QueryAccount queryAccount) throws Exception {
		return accountMapper.queryPaged(queryAccount)
				.stream().map(accountBuilder::toAccount).collect(Collectors.toList());
	}

	@Override
	public Account findInAccountNumber(AccountNumber accountNumber) throws Exception {
		AccountDO accountDo = accountMapper.queryByAccountNumber(accountNumber.getAccountNumber());
		return accountBuilder.toAccount(accountDo);
	}

	@Override
	public Account findInAccountId(AccountId accountId) throws Exception {
		AccountDO accountDo = accountMapper.queryByAccountId(accountId.getAccountId());
		return accountBuilder.toAccount(accountDo);
	}

	@Override
	public void attach(@NotNull Account aggregate) throws AttachException {

	}

	@Override
	public void detach(@NotNull Account aggregate) throws DetachException {

	}

	@Override
	public Account find(@NotNull UserId userId) throws Exception {
		AccountDO accountDo = accountMapper.queryByUserId(userId.getUserId());
		return accountBuilder.toAccount(accountDo);
	}

	@Override
	public void remove(@NotNull Account aggregate) throws Exception {
		accountMapper.delete(accountBuilder.fromAccount(aggregate));
	}

	@Override
	public Aggregate<UserId> save(@NotNull Account aggregate) throws Exception {
		int result = 0;
		if (aggregate.getAccountId() == null){
			result = accountMapper.insert(accountBuilder.fromAccount(aggregate));
		}else {
			result = accountMapper.updateById(accountBuilder.fromAccount(aggregate));
		}

		if (result == 0){
			throw new RuntimeException("保存异常");
		}
		return aggregate;
	}

}
