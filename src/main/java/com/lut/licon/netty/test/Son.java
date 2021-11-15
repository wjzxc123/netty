package com.lut.licon.netty.test;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/24 16:06
 */
public class Son extends Father {
	String name = "son";
	static {
		System.out.println("son static");
	}

	Son() {
		System.out.println("son constract");
		String a = super.age;
		System.out.println(a);
	}

	@Override
	public void get(){
		System.out.println("son-get");
	}
}
