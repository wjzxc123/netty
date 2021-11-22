package com.lut.licon.netty.ddd.application.service;

import java.util.List;

import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/22 10:45
 */
public interface AccountService {
	List<Account> queryAllAccount(QueryAccount queryAccount)throws Exception;
}
