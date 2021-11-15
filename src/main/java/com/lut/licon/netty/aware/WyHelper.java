package com.lut.licon.netty.aware;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 9:59
 */
@Component
public class WyHelper implements WyAware{

    private static Wy wy;

    @Override
    public void setWy(Wy wy) {
        System.out.println(wy.toString());
        this.wy = wy;
    }

    public static Wy  getWy(){
        return wy;
    }
}
