package com.lut.licon.netty.ddd.persistence.mapper;

import com.lut.licon.netty.ddd.persistence.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Component;


/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/29 15:34
 */
@Mapper
@Component
public interface AccountMapper {
	/***
	 *插入
	 * @param account
	 * @return {@link int}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:09
	 */
	int insert(AccountPO account);

	/***
	 *更新
	 * @param account
	 * @return {@link int}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:09
	 */
	int update(AccountPO account);

	/***
	 *根据UserId查询账户
	 * @param userId
	 * @return {@link AccountPO}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:10
	 */
	AccountPO queryByUserId(Long userId);

	/***
	 *根据accountId查询账户
	 * @param accountId
	 * @return {@link AccountPO}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:10
	 */
	AccountPO queryByAccountId(Long accountId);

	/***
	 *根据 accountNumber 查询账户
	 * @param accountNumber
	 * @return {@link AccountPO}
	 * @throws
	 * @author Licon
	 * @date 2021/11/15 16:10
	 */
	AccountPO queryByAccountNumber(String accountNumber);
}
