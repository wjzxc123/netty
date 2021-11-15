package com.lut.licon.netty.security;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/11/3 14:38
 */
public class Test {
	public static void main(String[] args) {
		ServiceImpl service = new ServiceImpl();

		Type[] actualTypeArgument = ((ParameterizedType) service.getClass().getGenericSuperclass())
				.getActualTypeArguments();
		for (Type type : actualTypeArgument) {
			System.out.println(type);
		}

		Type[] actualTypeArguments = ((ParameterizedType) service.getClass().getGenericInterfaces()[0])
				.getActualTypeArguments();
		for (Type typeArgument : actualTypeArguments) {
			System.out.println(typeArgument);
		}

	}
}
