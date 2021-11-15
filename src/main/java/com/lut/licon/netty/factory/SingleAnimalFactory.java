package com.lut.licon.netty.factory;


/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 16:54
 */
public class SingleAnimalFactory<T extends Animal> implements AnimalFactory<T>{

    private static Animal animal;

    private static Animal getInstance(Class<? extends Animal> clazz){
        if (animal == null){
            synchronized (SingleAnimalFactory.class){
                if (animal == null){
                    animal = new ReflectAnimalFactory<>(clazz).newInstance();
                }
            }
        }
        return animal;
    }

    @Override
    public T newInstance() {
        return null;
    }
}
