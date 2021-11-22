package com.lut.licon.netty;

import com.lut.licon.netty.ddd.application.service.AccountService;
import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.mapper.AccountMapper;
import com.lut.licon.netty.ddd.persistence.po.AccountDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountService accountService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<AccountDO> userList = accountMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testPage() throws Exception {
        System.out.println(("----- selectAll method test ------"));
        QueryAccount queryAccount = new QueryAccount();
        queryAccount.setPage(2);
        queryAccount.setPageSize(2);
        List<Account> accounts = accountService.queryAllAccount(queryAccount);
        accounts.forEach(System.out::println);
    }
}
