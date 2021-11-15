package com.lut.licon.netty.build;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 16:41
 */
public class MadeInChina extends Car{
    private String carName;

    public MadeInChina(Builder builder) {
        super(builder);
        carName = builder.carName;
    }

    public static class Builder extends Car.Builder<Builder>{
        private String carName;
        public Builder setCarName(String carName) {
            this.carName = carName;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        Car build() {
            return new MadeInChina(this);
        }
    }

    public String getCarName() {
        return carName;
    }

    @Override
    public String toString() {
        return "MadeInChina{" +
                "carName='" + carName + '\'' +
                ", wheel='" + wheel + '\'' +
                ", engine='" + engine + '\'' +
                ", whistle='" + whistle + '\'' +
                '}';
    }
}
