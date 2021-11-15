package com.lut.licon.netty.build;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 16:50
 */
public class Test {
    public static void main(String[] args) {
        Car car1 = new MadeInChina.Builder().setCarName("中国制造").setWheel("黑色轮子")
                .setEngine("2.0引擎").setWhistle("嘀嘀的汽笛").build();

        Car car2 = new MadeInAmerica.Builder().setCarName("美国制造").setWheel("白色轮子")
                .setEngine("1.0引擎").setWhistle("嗡嗡的汽笛").setAuth("中保研授权").build();

        System.out.println(car1);
        System.out.println(car2);

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        System.out.println(sdf.format(new Date()).substring(0,16)+(int)(Math.random()*10));*/
    }
}
