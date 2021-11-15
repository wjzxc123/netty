package com.lut.licon.netty.aware;

import org.springframework.beans.factory.Aware;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 9:47
 */
public interface WyAware extends Aware {
    void setWy(Wy wy);
}
