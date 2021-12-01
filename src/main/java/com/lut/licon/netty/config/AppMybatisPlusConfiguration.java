package com.lut.licon.netty.config;

import java.util.List;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/30 10:12
 */
@MapperScan(basePackages = {"com.lut.licon.**.persistence.mapper"},
		sqlSessionFactoryRef = "appSqlSessionFactory",
		sqlSessionTemplateRef = "appSqlSessionTemplate")
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureAfter({DataSourceAutoConfiguration.class, MybatisPlusLanguageDriverAutoConfiguration.class})
public class AppMybatisPlusConfiguration extends MybatisPlusConfiguration{
	public AppMybatisPlusConfiguration(MybatisPlusProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ObjectProvider<TypeHandler[]> typeHandlersProvider, ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider, ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider, ApplicationContext applicationContext) {
		super(properties, interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider, applicationContext);
	}

	@Bean(name = "appSqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		return sqlSessionFactory(dataSource);
	}

	@Bean(name = "appSqlSessionTemplate")
	@ConditionalOnMissingBean
	public SqlSessionTemplate sqlSessionTemplateBean(@Qualifier("appSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return sqlSessionTemplate(sqlSessionFactory);
	}
}
