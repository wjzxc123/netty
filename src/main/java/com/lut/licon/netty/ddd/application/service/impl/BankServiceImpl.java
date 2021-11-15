package com.lut.licon.netty.ddd.application.service.impl;

import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.Valid;

import com.lut.licon.netty.ddd.application.dto.TransferCommand;
import com.lut.licon.netty.ddd.application.service.BankService;
import com.lut.licon.netty.ddd.common.ResultData;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.domian.service.AccountTransferService;
import com.lut.licon.netty.ddd.external.service.ExchangeRateService;
import com.lut.licon.netty.ddd.repository.service.AccountRepository;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.ExchangeRate;
import com.lut.licon.netty.ddd.types.Money;
import com.lut.licon.netty.ddd.types.UserId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/1 10:21
 */
@Service
public class BankServiceImpl implements BankService {
	private final ExchangeRateService exchangeRateService;

	private final AccountRepository accountRepository;

	private final AccountTransferService accountTransferService;

	public BankServiceImpl(ExchangeRateService exchangeRateService, AccountRepository accountRepository, AccountTransferService accountTransferService) {
		this.exchangeRateService = exchangeRateService;
		this.accountRepository = accountRepository;
		this.accountTransferService = accountTransferService;
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public ResultData<Boolean> bankTransferBusiness(TransferCommand transferCommand) throws Exception {
		//查找源账户
		Account sourceAccount = accountRepository.find(new UserId(transferCommand.getUserId()));
		//查找目标账户
		Account targetAccount = accountRepository.find(new AccountNumber(transferCommand.getAccountNumber()));
		//获取汇率
		ExchangeRate rate = exchangeRateService.getRate(sourceAccount.getCurrency(), targetAccount.getCurrency());
		//转账操作
		accountTransferService.transfer(sourceAccount,new Money(transferCommand.getOperateMoney(), Currency.getInstance(transferCommand.getCurrency())),targetAccount,rate);

		//金额持久
		accountRepository.save(sourceAccount);
		accountRepository.save(targetAccount);

		//添加审计信息-略
		return ResultData.success();
	}
}
