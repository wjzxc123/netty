package com.lut.licon.netty;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.lut.licon.netty.ddd.application.service.AccountService;
import com.lut.licon.netty.ddd.domian.ceq.QueryAccount;
import com.lut.licon.netty.ddd.domian.entity.Account;
import com.lut.licon.netty.ddd.persistence.mapper.AccountMapper;
import com.lut.licon.netty.ddd.persistence.po.AccountDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
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
        QueryAccount queryAccount = new QueryAccount();
        queryAccount.setPage(2);
        queryAccount.setPageSize(2);
        List<Account> accounts = accountService.queryAllAccount(queryAccount);
        accounts.forEach(System.out::println);
    }

    @Test
    public void testVersionInsert() throws Exception {
        AccountDO accountDO = new AccountDO();
        accountDO.setUserId(8L)
                .setAccountId(1234567890L)
                .setAccountNumber("1234567890")
                .setAvailable(new BigDecimal(10000))
                .setDailyLimit(new BigDecimal(10000))
                .setCurrency("CNY");
        int update = accountMapper.insert(accountDO);
        System.out.println(update);
    }

    /***
     * 更新时必须带上Version字段，如果为null，则 @version不生效
     * @param
     * @return
     * @throws
     * @author Licon
     * @date 2021/11/23 10:37
     */
    @Test
    public void testVersionUpdate() throws Exception {
        AccountDO accountDO = accountMapper.selectById(7L);
        int update = accountMapper.updateById(accountDO);
        System.out.println(update);
    }

    @Test
    public void createMapper() throws Exception{
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/domian?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT", "root", "root")
                .dbQuery(new MySqlQuery())
                .schema("domain")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());
        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    builder.author("Licon") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\lvshaowei\\Desktop"); // 指定输出目录
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER,TemplateType.SERVICE,TemplateType.SERVICEIMPL)
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("com.lut.licon.netty.ddd") // 设置父包名
                            .moduleName("persistence") // 设置父包模块名
                            .entity("po")
                            .mapper("mapper")
                            .xml("mapper")
                            //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\lvshaowei\\Desktop"))
                            .build(); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("account")
                            .entityBuilder()
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .disableSerialVersionUID()
                            .formatFileName("%sDO")
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    public void product() throws InterruptedException {
        for (int j = 0; j < 2; j++) {
            String context = "";
            for (int i = 0; i < 1000; i++) {
                String s = HttpUtil.get("https://v1.jinrishici.com/all.json");
                JSONObject jsonObject = JSONObject.parseObject(s);
                context += jsonObject.getString("content");
                context+="@";
                context+=jsonObject.getString("author")+"《"+jsonObject.getString("origin")+"》\n";
                Thread.sleep(300);
            }
            FileUtil.appendString(context,"C:\\Users\\lvshaowei\\Desktop\\a.txt", CharsetUtil.GBK);
        }
    }
}
