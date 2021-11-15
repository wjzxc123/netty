package com.lut.licon.netty.aware;

import com.lut.licon.netty.factory.Animal;
import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 9:33
 */
@Component
public class GetEnviromentAnimal implements AnimalAware {
    Animal animal;
    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal printSystemAnimalInfo(){
        System.out.println("system:"+animal);
        return animal;
    }
}
