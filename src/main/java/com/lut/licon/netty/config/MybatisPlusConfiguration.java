package com.lut.licon.netty.config;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/30 10:09
 */

public abstract class MybatisPlusConfiguration implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(MybatisPlusAutoConfiguration.class);

	private final MybatisPlusProperties properties;

	private final Interceptor[] interceptors;

	private final TypeHandler[] typeHandlers;

	private final LanguageDriver[] languageDrivers;

	private final ResourceLoader resourceLoader;

	private final DatabaseIdProvider databaseIdProvider;

	private final List<ConfigurationCustomizer> configurationCustomizers;

	private final List<MybatisPlusPropertiesCustomizer> mybatisPlusPropertiesCustomizers;

	private final ApplicationContext applicationContext;


	public MybatisPlusConfiguration(MybatisPlusProperties properties,
			ObjectProvider<Interceptor[]> interceptorsProvider,
			ObjectProvider<TypeHandler[]> typeHandlersProvider,
			ObjectProvider<LanguageDriver[]> languageDriversProvider,
			ResourceLoader resourceLoader,
			ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
			ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
			ApplicationContext applicationContext) {
		this.properties = properties;
		this.interceptors = interceptorsProvider.getIfAvailable();
		this.typeHandlers = typeHandlersProvider.getIfAvailable();
		this.languageDrivers = languageDriversProvider.getIfAvailable();
		this.resourceLoader = resourceLoader;
		this.databaseIdProvider = databaseIdProvider.getIfAvailable();
		this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
		this.mybatisPlusPropertiesCustomizers = mybatisPlusPropertiesCustomizerProvider.getIfAvailable();
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() {
		if (!CollectionUtils.isEmpty(mybatisPlusPropertiesCustomizers)) {
			mybatisPlusPropertiesCustomizers.forEach(i -> i.customize(properties));
		}
		checkConfigFileExists();
	}

	private void checkConfigFileExists() {
		if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
			Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
			Assert.state(resource.exists(),
					"Cannot find config location: " + resource + " (please add config file or check your Mybatis configuration)");
		}
	}

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		// TODO ?????? MybatisSqlSessionFactoryBean ????????? SqlSessionFactoryBean
		MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		if (StringUtils.hasText(this.properties.getConfigLocation())) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
		}
		applyConfiguration(factory);
		if (this.properties.getConfigurationProperties() != null) {
			factory.setConfigurationProperties(this.properties.getConfigurationProperties());
		}
		if (!ObjectUtils.isEmpty(this.interceptors)) {
			factory.setPlugins(this.interceptors);
		}
		if (this.databaseIdProvider != null) {
			factory.setDatabaseIdProvider(this.databaseIdProvider);
		}
		if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
		}
		if (this.properties.getTypeAliasesSuperType() != null) {
			factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
		}
		if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.typeHandlers)) {
			factory.setTypeHandlers(this.typeHandlers);
		}
		Resource[] mapperLocations = this.properties.resolveMapperLocations();
		if (!ObjectUtils.isEmpty(mapperLocations)) {
			factory.setMapperLocations(mapperLocations);
		}
		// TODO ???????????????????????? TransactionFactory
		this.getBeanThen(TransactionFactory.class, factory::setTransactionFactory);

		// TODO ??????????????????????????????(??????????????????????????????mybatis??????,????????????????????????)
		Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
		if (!ObjectUtils.isEmpty(this.languageDrivers)) {
			factory.setScriptingLanguageDrivers(this.languageDrivers);
		}
		Optional.ofNullable(defaultLanguageDriver).ifPresent(factory::setDefaultScriptingLanguageDriver);

		// TODO ??????????????????
		if (StringUtils.hasLength(this.properties.getTypeEnumsPackage())) {
			factory.setTypeEnumsPackage(this.properties.getTypeEnumsPackage());
		}
		// TODO ??????????????? NULL
		GlobalConfig globalConfig = this.properties.getGlobalConfig();
		// TODO ???????????????
		this.getBeanThen(MetaObjectHandler.class, globalConfig::setMetaObjectHandler);
		// TODO ?????????????????????
		this.getBeansThen(IKeyGenerator.class, i -> globalConfig.getDbConfig().setKeyGenerators(i));
		// TODO ??????sql?????????
		this.getBeanThen(ISqlInjector.class, globalConfig::setSqlInjector);
		// TODO ??????ID?????????
		this.getBeanThen(IdentifierGenerator.class, globalConfig::setIdentifierGenerator);
		// TODO ?????? GlobalConfig ??? MybatisSqlSessionFactoryBean
		factory.setGlobalConfig(globalConfig);
		return factory.getObject();
	}

	/**
	 * ??????spring???????????????????????????bean,??????????????????
	 *
	 * @param clazz    class
	 * @param consumer ??????
	 * @param <T>      ??????
	 */
	private <T> void getBeanThen(Class<T> clazz, Consumer<T> consumer) {
		if (this.applicationContext.getBeanNamesForType(clazz, false, false).length > 0) {
			consumer.accept(this.applicationContext.getBean(clazz));
		}
	}

	/**
	 * ??????spring???????????????????????????bean,??????????????????
	 *
	 * @param clazz    class
	 * @param consumer ??????
	 * @param <T>      ??????
	 */
	private <T> void getBeansThen(Class<T> clazz, Consumer<List<T>> consumer) {
		if (this.applicationContext.getBeanNamesForType(clazz, false, false).length > 0) {
			final Map<String, T> beansOfType = this.applicationContext.getBeansOfType(clazz);
			List<T> clazzList = new ArrayList<>();
			beansOfType.forEach((k, v) -> clazzList.add(v));
			consumer.accept(clazzList);
		}
	}

	// TODO ???????????? MybatisSqlSessionFactoryBean
	private void applyConfiguration(MybatisSqlSessionFactoryBean factory) {
		// TODO ?????? MybatisConfiguration
		MybatisConfiguration configuration = this.properties.getConfiguration();
		if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
			configuration = new MybatisConfiguration();
		}
		if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
			for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
				customizer.customize(configuration);
			}
		}
		factory.setConfiguration(configuration);
	}


	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		ExecutorType executorType = this.properties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}


	/**
	 * This will just scan the same base package as Spring Boot does. If you want more power, you can explicitly use
	 * {@link org.mybatis.spring.annotation.MapperScan} but this will get typed mappers working correctly, out-of-the-box,
	 * similar to using Spring Data JPA repositories.
	 */
	public static class AutoConfiguredMapperScannerRegistrar implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

		private BeanFactory beanFactory;

		@Override
		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

			if (!AutoConfigurationPackages.has(this.beanFactory)) {
				logger.debug("Could not determine auto-configuration package, automatic mapper scanning disabled.");
				return;
			}

			logger.debug("Searching for mappers annotated with @Mapper");

			List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
			if (logger.isDebugEnabled()) {
				packages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
			}

			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
			builder.addPropertyValue("processPropertyPlaceHolders", true);
			builder.addPropertyValue("annotationClass", Mapper.class);
			builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(packages));
			BeanWrapper beanWrapper = new BeanWrapperImpl(MapperScannerConfigurer.class);
			Set<String> propertyNames = Stream.of(beanWrapper.getPropertyDescriptors()).map(PropertyDescriptor::getName)
					.collect(Collectors.toSet());
			if (propertyNames.contains("lazyInitialization")) {
				// Need to mybatis-spring 2.0.2+
				// TODO ?????????mybatis.lazy-initialization??????
				builder.addPropertyValue("lazyInitialization", "${mybatis-plus.lazy-initialization:${mybatis.lazy-initialization:false}}");
			}
			if (propertyNames.contains("defaultScope")) {
				// Need to mybatis-spring 2.0.6+
				builder.addPropertyValue("defaultScope", "${mybatis-plus.mapper-default-scope:}");
			}
			registry.registerBeanDefinition(MapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
		}

		@Override
		public void setBeanFactory(BeanFactory beanFactory) {
			this.beanFactory = beanFactory;
		}
	}

	/**
	 * If mapper registering configuration or mapper scanning configuration not present, this configuration allow to scan
	 * mappers based on the same component-scanning path as Spring Boot itself.
	 */
	@Configuration(proxyBeanMethods = false)
	@Import(MybatisPlusAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class)
	@ConditionalOnMissingBean({MapperFactoryBean.class, MapperScannerConfigurer.class})
	public static class MapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

		@Override
		public void afterPropertiesSet() {
			logger.debug(
					"Not found configuration for registering mapper bean using @MapperScan, MapperFactoryBean and MapperScannerConfigurer.");
		}
	}
}
