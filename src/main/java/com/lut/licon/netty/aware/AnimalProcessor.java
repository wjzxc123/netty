package com.lut.licon.netty.aware;

import com.lut.licon.netty.config.AnimalConfig;
import com.lut.licon.netty.factory.Animal;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 17:05
 */
@Component
public class AnimalProcessor implements BeanPostProcessor, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Autowired
    AnimalConfig animalConfig;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnimalAware){
            Assert.notNull(animalConfig.getAnimalType(),"type is not null");
            ((AnimalAware)bean).setAnimal(applicationContext.getBean(animalConfig.getAnimalType(), Animal.class));
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
