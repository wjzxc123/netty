package com.lut.licon.netty.controller;

import com.alibaba.fastjson.JSONObject;
import com.lut.licon.netty.aware.AnimalHelper;
import com.lut.licon.netty.aware.ApplicationContextHelper;
import com.lut.licon.netty.aware.GetEnviromentAnimal;
import com.lut.licon.netty.aware.WyHelper;
import com.lut.licon.netty.factory.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/21 10:02
 */
@RestController
public class Test {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GetEnviromentAnimal enviromentAnimal;

    @GetMapping("/get")
    public Object getWy(HttpServletRequest servletRequest){
        return WyHelper.getWy().toString();
    }

    @GetMapping("/get-1")
    public Object getApplicaiton(HttpServletRequest servletRequest){
        Object wy = ApplicationContextHelper.getApplicationContext().getBean("wy");
        return wy.toString();
    }

    @GetMapping("/get-2")
    public Object getAnimal(HttpServletRequest servletRequest){
        Animal animal = AnimalHelper.getAnimal();
        animal.call();
        return animal.toString();
    }

    @GetMapping("/get-3")
    public Object getSystemAnimal(HttpServletRequest servletRequest){
        Animal animal = enviromentAnimal.printSystemAnimalInfo();
        return animal.toString();
    }


    @GetMapping("/send")
    public Object send(HttpServletRequest servletRequest){
        return  restTemplate.getForObject("http://localhost:8080/get-1", String.class);
    }
}
