package com.lut.licon.netty.ddd.repository.service;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.UserId;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:40
 */
public interface AccountRepository {
	/***
	 *查找用户 根据 UserId
	 * @param userId
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account find(UserId userId) throws Exception;

	/***
	 *查找用户 根据 AccountNumber
	 * @param accountNumber
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account find(AccountNumber accountNumber) throws Exception;

	/***
	 *查找用户 根据 AccountId
	 * @param accountId
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account find(AccountId accountId) throws Exception;

	/***
	 *存储用户 Account
	 * @param account
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account save(Account account) throws Exception;
}
