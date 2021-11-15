package com.lut.licon.netty.test;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/24 16:05
 */
public class Father {
	String name = "son";
	String age = "18";
	static {
		System.out.println("father static");
	}

	Father() {
		System.out.println("father constract");
	}

	public void get(){
		System.out.println("father-get");
	}
}
