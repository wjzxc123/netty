package com.lut.licon.netty.factory;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 10:12
 */
@Component
public class Cat implements Animal{
    @Override
    public void call() {
        System.out.println("喵喵喵……");
    }
}
