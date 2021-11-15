package com.lut.licon.netty.factory;

import io.netty.util.internal.StringUtil;
import java.lang.reflect.Constructor;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/18 16:42
 */
public class ReflectAnimalFactory<T extends Animal> implements AnimalFactory<T> {
    private final  Constructor<? extends T> constract ;

    public ReflectAnimalFactory(Class<? extends T> clazz) {
        if (clazz == null){
            throw new NullPointerException("clazz");
        }
        try {
            this.constract = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + StringUtil.simpleClassName(clazz) +
                    " does not have a public non-arg constructor", e);
        }
    }

    @Override
    public T newInstance() {
        try {
            return  constract.newInstance();
        } catch (Throwable t) {
            throw new RuntimeException("not create animal obj");
        }
    }

    @Override
    public String toString() {
        return "ReflectAnimalFactory{" +
                "constract=" + constract +
                '}';
    }
}
