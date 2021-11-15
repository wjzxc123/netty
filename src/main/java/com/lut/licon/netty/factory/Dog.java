package com.lut.licon.netty.factory;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/18 16:54
 */
@Component
public class Dog implements Animal{
    @Override
    public void call() {
        System.out.println("旺旺……");
    }

}
