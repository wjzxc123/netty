package com.lut.licon.netty.kafka.configure;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/26 13:49
 */
//@RestControllerAdvice
public class CustomControllerSecurity {

	@ModelAttribute
	public void checkSecurity(HttpServletRequest request){
		System.out.println("获取权限");
	}
}
