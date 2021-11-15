package com.lut.licon.netty.build;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 17:00
 */
public class MadeInAmerica extends Car{
    private String carName;
    private String auth;

    MadeInAmerica(Builder builder) {
        super(builder);
    }

    protected static class Builder extends Car.Builder<Builder>{

        private String carName;

        private String auth;

        public Builder setCarName(String carName){
            this.carName = carName;
            return this;
        }

        public Builder setAuth(String auth){
            this.auth = auth;
            return this;
        }
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        Car build() {
            return new MadeInAmerica(this);
        }
    }

    public String getCarName() {
        return carName;
    }

    public String getAuth() {
        return auth;
    }

    @Override
    public String toString() {
        return "MadeInAmerica{" +
                "carName='" + carName + '\'' +
                ", auth='" + auth + '\'' +
                ", wheel='" + wheel + '\'' +
                ", engine='" + engine + '\'' +
                ", whistle='" + whistle + '\'' +
                '}';
    }
}
