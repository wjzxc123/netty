package com.lut.licon.netty.ddd.domian.repository;


import java.util.List;

import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.types.AccountId;
import com.lut.licon.netty.ddd.types.AccountNumber;
import com.lut.licon.netty.ddd.types.UserId;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/19 10:21
 */
public interface AccountRepository extends Repository<Account, UserId>{

	/***
	 * 查询条数
	 * @param queryAccount
	 * @return {@link Long}
	 * @throws
	 * @author Licon
	 * @date 2021/11/22 9:35
	 */
	Long countAccount(QueryAccount queryAccount) throws Exception;

	/***
	 * 分页查询 Account
	 * @param queryAccount
	 * @return {@link List< Account>}
	 * @throws
	 * @author Licon
	 * @date 2021/11/22 9:35
	 */
	List<Account> query(QueryAccount queryAccount) throws Exception;

	/***
	 *查找用户 根据 AccountNumber
	 * @param accountNumber
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account findInAccountNumber(AccountNumber accountNumber) throws Exception;


	/***
	 *查找用户 根据 AccountId
	 * @param accountId
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:11
	 */
	Account findInAccountId(AccountId accountId) throws Exception;
}
