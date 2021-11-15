package com.lut.licon.netty.aware;

import com.lut.licon.netty.factory.Animal;
import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 17:19
 */
@Component
public class AnimalHelper implements AnimalAware{
    static Animal animal;
    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public static Animal getAnimal(){
        return animal;
    }
}
