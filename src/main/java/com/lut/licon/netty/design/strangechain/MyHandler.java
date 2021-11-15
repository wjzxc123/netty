package com.lut.licon.netty.design.strangechain;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/1/26 9:36
 */
public class MyHandler implements StrategyHandler<String,Integer>{
	@Override
	public Integer apply(String param) {
		return Integer.valueOf(param);
	}
}
