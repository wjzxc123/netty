package com.lut.licon.netty.ddd.repository.assemble.service;

import java.security.AccessControlContext;

import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.po.AccountPO;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 16:46
 */
public interface AccountBuilder {
	/***
	 *AccountPO转换为Account
	 * @param accountPo
	 * @return {@link Account}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:08
	 */
	Account toAccount(AccountPO accountPo) throws Exception;

	/***
	 *Account转换为AccountPO
	 * @param accountPo
	 * @return {@link AccountPO}
	 * @throws Exception
	 * @author Licon
	 * @date 2021/11/15 16:09
	 */
	AccountPO fromAccount(Account accountPo) throws Exception;
}
