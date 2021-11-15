package com.lut.licon.netty.aware;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 9:48
 */
@Component
public class Wy {
    String name = "wy";

    String age = "18";

    public Wy() {
    }

    public Wy(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Wy{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
