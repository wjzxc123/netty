package com.lut.licon.netty.design.strangechain;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/1/26 9:43
 */
public class Test {
	public static void main(String[] args) {
		MyRouter myRouter = new MyRouter();
		Integer integer = myRouter.applyStrategy("1");
		System.out.println(integer);
		MyRouter2 myRouter2 = new MyRouter2();
		Double aDouble = myRouter2.applyStrategy("2");
		System.out.println(aDouble);
	}
}
