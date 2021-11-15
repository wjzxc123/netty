package com.lut.licon.netty.build;

import javax.smartcardio.CardPermission;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 16:29
 */
public class Car {

    protected String wheel;

    protected String engine;

    protected String whistle;

    Car(Builder<?> builder) {
        this.wheel = builder.wheel;
        this.engine = builder.engine;
        this.whistle = builder.whistle;
    }

    abstract static class Builder<T extends Builder<T>>{

        private String wheel;

        private String engine;

        private String whistle;

        protected abstract T self();

        protected T setWheel(String wheel){
            this.wheel = wheel;
            return self();
        }

        protected T setEngine(String engine){
            this.engine = engine;
            return self();
        }

        protected T setWhistle(String whistle){
            this.whistle = whistle;
            return self();
        }
        abstract Car build();
    }

    public String getWheel() {
        return wheel;
    }

    public String getEngine() {
        return engine;
    }

    public String getWhistle() {
        return whistle;
    }
}
