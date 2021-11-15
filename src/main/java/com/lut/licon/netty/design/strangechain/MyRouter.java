package com.lut.licon.netty.design.strangechain;

import java.util.HashMap;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/1/26 9:35
 */
public class MyRouter extends AbstractStrategyRouter<String,Integer> {
	@Override
	protected StrategyMapper<String, Integer> registerStrategyMapper() {
		return t -> new MyHandler();
	}
}
