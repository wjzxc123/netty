package com.lut.licon.netty.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 9:49
 */
@Component
public class WyAwareProcessor implements BeanPostProcessor, ApplicationContextAware {
    @Nullable
    private Wy wy;

    @Nullable
    private ApplicationContext applicationContext;

    public WyAwareProcessor() {
    }

    public WyAwareProcessor(@Nullable Wy wy) {
        this.wy = wy;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (wy == null){
            wy = applicationContext.getBean("wy",Wy.class);
        }
        if (bean instanceof WyAware){
            ((WyAware) bean).setWy(wy);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
