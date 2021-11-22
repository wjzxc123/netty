package com.lut.licon.netty.ddd.application.service.impl;

import java.util.List;

import com.lut.licon.netty.ddd.application.service.AccountService;
import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.domian.repository.AccountRepository;

import org.springframework.stereotype.Service;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/22 10:47
 */
@Service
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Account> queryAllAccount(QueryAccount queryAccount) throws Exception {
		return accountRepository.query(queryAccount);
	}
}
