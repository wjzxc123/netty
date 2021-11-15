package com.lut.licon.netty.factory;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/18 16:53
 */
public class Test {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new ReflectAnimalFactory(Dog.class);
        Animal animal = animalFactory.newInstance();
        animal.call();

    }
}
