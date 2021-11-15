package com.lut.licon.netty.aware;

import com.lut.licon.netty.factory.Animal;
import org.springframework.beans.factory.Aware;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 17:05
 */
public interface AnimalAware{
    void setAnimal(Animal animal);
}
