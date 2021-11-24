package com.lut.licon.netty.ddd.repository.converter.service;

import java.util.Currency;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.po.AccountDO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 16:46
 */
@Mapper
public interface AccountBuilder {
	AccountBuilder INSTANCE = Mappers.getMapper(AccountBuilder.class);
	/***
	 *AccountPO转换为Account
	 * @param accountDo
	 * @return {@link Account}
	 * @throws RuntimeException
	 * @author Licon
	 * @date 2021/11/15 16:08
	 */

	@Mapping(target = "userId.userId",source = "userId")
	@Mapping(target = "accountId.accountId",source = "accountId")
	@Mapping(target = "accountNumber.accountNumber",source = "accountNumber")
	@Mapping(target = "available.amount",source = "available")
	@Mapping(target = "available.currency",source = "currency")
	@Mapping(target = "dailyLimit.amount",source = "dailyLimit")
	@Mapping(target = "dailyLimit.currency",source = "currency")
	@Mapping(target = "currency",source = "currency")
	Account toAccount(AccountDO accountDo) throws RuntimeException;

	/**
	 * @since 1.3+ 添加了 java.lang.String ==> java.util.Currency
	 */
	/*default Currency toCurrency(String currency){
		return Currency.getInstance(currency);
	}*/

	/***
	 *Account转换为AccountPO
	 *
	 * @InheritInverseConfiguration 可以逆向转换
	 * @param account
	 * @return {@link AccountDO}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:09
	 */
	/*@Mapping(target = "userId",source = "userId.userId")
	@Mapping(target = "accountId",source = "accountId.accountId")
	@Mapping(target = "accountNumber",source = "accountNumber.accountNumber")
	@Mapping(target = "available",source = "available.amount")
	@Mapping(target = "dailyLimit",source = "dailyLimit.amount")
	@Mapping(target = "currency",expression = "java(account.getCurrency().getCurrencyCode())")*/
	@InheritInverseConfiguration
	AccountDO fromAccount(Account account) throws Exception;
}
