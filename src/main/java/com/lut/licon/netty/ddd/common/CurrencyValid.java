package com.lut.licon.netty.ddd.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/12 15:23
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = CurrencyConstraintValidator.class)
public @interface CurrencyValid {
	/**
	 * 是否强制校验
	 *
	 * @return 是否强制校验的boolean值
	 */
	boolean required() default true;


	String message() default "";

	/**
	 * 将validator进行分类，不同的类group中会执行不同的validator操作
	 *
	 * @return validator的分类类型
	 */
	Class<?>[] groups() default {};

	/**
	 * 主要是针对bean，很少使用
	 *
	 * @return 负载
	 */
	Class<? extends Payload>[] payload() default {};
}
