package com.lut.licon.netty.ddd.common;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/15 9:28
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 * 当对用@Valid注释的参数的验证失败时抛出的异常
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public Object controllerCheckHandle(HttpServletRequest request, MethodArgumentNotValidException e){
		List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
		JSONArray errorList = new JSONArray();
		allErrors.forEach(x->{
			JSONObject error = new JSONObject();
			error.put("field",x.getField());
			error.put("message",x.getDefaultMessage());
			errorList.add(error);
		});
		return errorList;
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseBody
	public Object serviceCheckHandle(HttpServletRequest request, ConstraintViolationException e){
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		JSONArray errorList = new JSONArray();
		constraintViolations.forEach(x->{
			JSONObject error = new JSONObject();
			error.put("field",x.getInvalidValue());
			error.put("message",x.getMessage());
			errorList.add(error);
		});
		return errorList;
	}
}
