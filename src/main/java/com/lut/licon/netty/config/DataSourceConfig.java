package com.lut.licon.netty.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/12/1 9:09
 */
@Slf4j
@Configuration
@Order(-1)
public class DataSourceConfig {
	private static final String DATASOURCE_NAME = "dbDataSource";

	/**
	 * 数据源配置的前缀，必须与application.properties中配置的对应数据源的前缀一致
	 */
	private static final String BUSINESS_DATASOURCE_PREFIX = "spring.datasource.dbdata";

	private static final String  QUARTZ_DATASOURCE_PREFIX= "spring.datasource.quartz";

	@Bean("quartzDatasource")
	@QuartzDataSource
	@ConfigurationProperties(prefix = QUARTZ_DATASOURCE_PREFIX)
	public DataSource getQuartzDatasource(){
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties(prefix = BUSINESS_DATASOURCE_PREFIX)
	@Primary
	public DataSource getDBDatasource(){
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}
